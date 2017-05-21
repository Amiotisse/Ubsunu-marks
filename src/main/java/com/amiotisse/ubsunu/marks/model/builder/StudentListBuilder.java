package com.amiotisse.ubsunu.marks.model.builder;

import com.amiotisse.ubsunu.marks.model.Student;
import com.amiotisse.ubsunu.marks.model.StudentList;

import java.util.ArrayList;
import java.util.List;

public class StudentListBuilder {
    private String title;
    private String ownerUserId;
    private List<Student> studentList = new ArrayList<>();

    public StudentListBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public StudentListBuilder setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
        return this;
    }

    public StudentListBuilder setStudentList(List<Student> studentList) {
        this.studentList = studentList;
        return this;
    }

    public StudentListBuilder addStudent (Student student) {
        this.studentList.add(student);
        return this;
    }

    public StudentList build() {
        return new StudentList(title, ownerUserId, studentList);
    }
}