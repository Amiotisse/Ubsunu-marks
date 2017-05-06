package com.amiotisse.ubsunu.marks.model.builder;

import com.amiotisse.ubsunu.marks.model.Mark;
import com.amiotisse.ubsunu.marks.model.Student;

public class MarkBuilder {
    private Student student;
    private float value;

    public MarkBuilder setStudent(Student student) {
        this.student = student;
        return this;
    }

    public MarkBuilder setValue(float value) {
        this.value = value;
        return this;
    }

    public Mark build() {
        return new Mark(student, value);
    }
}