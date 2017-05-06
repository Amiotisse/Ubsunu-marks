package com.amiotisse.ubsunu.marks.model.builder;

import com.amiotisse.ubsunu.marks.model.Student;

public class StudentBuilder {
    private String lastName;
    private String firstName;
    private String email;

    public StudentBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StudentBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public Student createStudent() {
        return new Student(lastName, firstName, email);
    }
}