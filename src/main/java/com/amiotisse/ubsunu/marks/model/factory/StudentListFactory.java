package com.amiotisse.ubsunu.marks.model.factory;

import com.amiotisse.ubsunu.marks.model.Student;
import com.amiotisse.ubsunu.marks.model.StudentList;
import com.amiotisse.ubsunu.marks.model.builder.StudentListBuilder;

import java.util.List;

/**
 * @author himna
 * @since 5/21/2017.
 */
public class StudentListFactory implements PrivateListFactory<Student , StudentList>{
    @Override
    public StudentList build(String title, String ownerUserId, List<Student> list) {
        return new StudentListBuilder()
                .setTitle(title)
                .setOwnerUserId(ownerUserId)
                .setStudentList(list)
                .build();
    }
}
