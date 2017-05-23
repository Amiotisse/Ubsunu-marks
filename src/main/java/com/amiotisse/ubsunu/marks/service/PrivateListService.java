package com.amiotisse.ubsunu.marks.service;

import com.amiotisse.ubsunu.marks.exception.TitleAlreadyTakenException;
import com.amiotisse.ubsunu.marks.exception.UserTypeNotAllowedException;
import com.amiotisse.ubsunu.marks.model.PrivateList;
import com.amiotisse.ubsunu.marks.model.UserToken;
import com.amiotisse.ubsunu.marks.model.UserType;
import com.amiotisse.ubsunu.marks.model.factory.PrivateListFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
import java.util.stream.Collectors;

/**
 * @author himna
 * @since 5/21/2017.
 */
public class PrivateListService<Entity , L extends PrivateList<Entity>> {

    private MongoRepository< L , String> repository;
    private PrivateListFactory<Entity, L> factory;
    private ArgsChecker checker;

    public PrivateListService(
            MongoRepository<L, String> repository,
            PrivateListFactory<Entity, L> factory,
            ArgsChecker checker
    ) {
        this.repository = repository;
        this.factory = factory;
        this.checker = checker;
    }

    public L save (String title ,
                   UserToken userToken ,
                   List<Entity> list
    ) throws
            UserTypeNotAllowedException,
            TitleAlreadyTakenException
    {
        checker.checkUserToken(userToken);
        checker.checkTitleDoNotExist(title,repository);
        return repository.save( factory.build(title , userToken.getId(), list) );
    }

    public List<String> getTitles (UserToken userToken){
        L example = factory.build(null , userToken.getId() , null);
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);

        return repository.findAll(Example.of(example,matcher))
                .stream()
                .map(PrivateList::getTitle)
                .collect(Collectors.toList());
    }

    public L getPrivateList(String title, UserToken userToken){
        L found = repository.findOne(title);
        if ( found != null && found.getOwnerUserId().equals(userToken.getId()) ){
            return found ;
        }
        else{
            return null ;
        }
    }

}
