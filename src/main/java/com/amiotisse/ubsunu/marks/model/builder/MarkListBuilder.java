package com.amiotisse.ubsunu.marks.model.builder;

import com.amiotisse.ubsunu.marks.model.Mark;
import com.amiotisse.ubsunu.marks.model.MarkList;

import java.util.ArrayList;
import java.util.List;

public class MarkListBuilder {
    private String title;
    private String ownerUserId;
    private List<Mark> marks = new ArrayList<>();

    public MarkListBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public MarkListBuilder setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
        return this;
    }

    public MarkListBuilder addMark(List<Mark> marks) {
        this.marks.addAll(marks);
        return this;
    }

    public MarkListBuilder setMarks(List<Mark> marks) {
        this.marks= marks;
        return this;
    }
    public MarkListBuilder addMark(Mark mark) {
        this.marks.add(mark);
        return this;
    }


    public MarkList build() {
        return new MarkList(title, ownerUserId, marks);
    }
}