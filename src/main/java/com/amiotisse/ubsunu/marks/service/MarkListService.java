package com.amiotisse.ubsunu.marks.service;

import com.amiotisse.ubsunu.marks.client.MailerFiegnClient;
import com.amiotisse.ubsunu.marks.exception.TitleAlreadyTakenException;
import com.amiotisse.ubsunu.marks.exception.UserTypeNotAllowedException;
import com.amiotisse.ubsunu.marks.model.*;
import com.amiotisse.ubsunu.marks.model.builder.MarkListBuilder;
import com.amiotisse.ubsunu.marks.repository.MarkListRepository;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author himna
 * @since 5/22/2017.
 */
public class MarkListService {


    private MarkListRepository repository;
    private ArgsChecker checker;
    private MailerFiegnClient mailerFiegnClient;
    private boolean isMailerEnable;

    public MarkListService(
            MarkListRepository repository,
            ArgsChecker checker,
            MailerFiegnClient mailerFiegnClient,
            boolean isMailerEnable
    ) {
        this.repository = repository;
        this.checker = checker;
        this.mailerFiegnClient = mailerFiegnClient;
        this.isMailerEnable = isMailerEnable;
    }

    public List<Mark> average (List<TitleCoef> titleCoefs){
        Map<String , Integer > coefs = titleCoefs
                .parallelStream()
                .collect(Collectors.toMap(TitleCoef::getMarkListTitle , TitleCoef::getCoef));
        int sumCoef = titleCoefs.stream().mapToInt(TitleCoef::getCoef).sum();
        return titleCoefs
                .parallelStream()
                .map(titleCoef -> repository.findOne(titleCoef.getMarkListTitle()))
                .map((ml) -> new MarkListCoef(coefs.get(ml.getTitle()),ml.getList()) )
                .reduce( new MarkListCoef(0 , Lists.newArrayList()) ,MarkListCoef::add )
                .getNormalyzedMarks(sumCoef);
    }

    public MarkList computeAvrageAndSave (String title , UserToken userToken, List<TitleCoef> titleCoefs)
            throws UserTypeNotAllowedException , TitleAlreadyTakenException
    {
        checker.checkUserToken(userToken);
        checker.checkTitleDoNotExist(title,repository);
        return repository.save(new MarkListBuilder()
                .setTitle(title)
                .setOwnerUserId(userToken.getId())
                .setMarks(average(titleCoefs))
                .build());
    }

    public void sendMails ( MarkList markList){
        if( isMailerEnable ){
            mailerFiegnClient.publishMarkList(markList);
        }
    }

}
