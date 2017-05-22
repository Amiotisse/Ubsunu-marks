package com.amiotisse.ubsunu.marks.service;

import com.amiotisse.ubsunu.marks.model.MarkListCoef;
import com.amiotisse.ubsunu.marks.model.TitleCoef;
import com.amiotisse.ubsunu.marks.model.Mark;
import com.amiotisse.ubsunu.marks.model.MarkList;
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



    public List<Mark> moy (List<TitleCoef> titleCoefs){
        Map<String , Integer > coefs = titleCoefs
                .parallelStream()
                .collect(Collectors.toMap(TitleCoef::getMarkListTitle , TitleCoef::getCoef));

        return titleCoefs
                .parallelStream()
                .map(titleCoef -> repository.findOne(titleCoef.getMarkListTitle()))
                .map((ml) -> new MarkListCoef(coefs.get(ml.getTitle()),ml.getList()) )
                .reduce( new MarkListCoef(0 , Lists.newArrayList()) ,MarkListCoef::add )
                .getNormalyzedMarks();
    }


}
