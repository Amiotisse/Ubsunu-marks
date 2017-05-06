package com.amiotisse.ubsunu.marks.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * @author himna
 * @since 4/17/2017.
 */
public enum UserType {

    teacher("teacher" ) ,
    student ("student") ;

    private String asString ;
    UserType(String asString){
        this.asString = asString;
    }

    @JsonValue
    @Override
    public String toString() {
        return asString;
    }


    public static Map<String , UserType> fromString = ImmutableMap
            .<String , UserType> builder()
            .put(UserType.teacher.toString() , UserType.teacher)
            .put(UserType.student.toString() , UserType.student)
            .build();

    @JsonCreator
    public static UserType create(String userTypeAsString ){
        return fromString.get(userTypeAsString);
    }
}
