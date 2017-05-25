package com.amiotisse.ubsunu.marks.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author himna
 * @since 5/23/2017.
 */
public class MarkListCoef {

    int coef;
    List<Mark> marks;

    public MarkListCoef(int coef, List<Mark> marks) {
        this.coef = coef;

        this.marks = marks;
    }

    public int getCoef() {
        return coef;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public MarkListCoef add ( MarkListCoef that){
        Map<Student,Float> thatMap = that.marks.stream().collect(Collectors.toMap(Mark::getStudent,mark -> (mark.getValue() * that.coef)/(that.coef + this.coef)));
        Map<Student,Float> thisMap = this.marks.stream().collect(Collectors.toMap(Mark::getStudent,mark -> (mark.getValue() * this.coef)/(that.coef + this.coef)));

        List<Mark> marks = Stream
                .of(thisMap,thatMap)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (markValue1, markValue2) -> markValue1 + markValue2
                        )
                ).entrySet()
                .stream()
                .map(entry -> new Mark(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());

        return new MarkListCoef( coef + that.coef , marks);
    }
}
