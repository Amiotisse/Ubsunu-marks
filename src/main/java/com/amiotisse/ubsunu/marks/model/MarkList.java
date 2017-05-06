package com.amiotisse.ubsunu.marks.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * @author himna
 * @since 5/6/2017.
 */
public class MarkList {

    @Id
    private String title;

    private String ownerUserId;
    private List<Mark> marks;

    public MarkList(String title, String ownerUserId, List<Mark> marks) {
        this.title = title;
        this.ownerUserId = ownerUserId;
        this.marks = marks;
    }

    public MarkList() {
        this.marks = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarkList markList = (MarkList) o;

        if (title != null ? !title.equals(markList.title) : markList.title != null) return false;
        if (ownerUserId != null ? !ownerUserId.equals(markList.ownerUserId) : markList.ownerUserId != null)
            return false;
        return marks != null ? marks.equals(markList.marks) : markList.marks == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (ownerUserId != null ? ownerUserId.hashCode() : 0);
        result = 31 * result + (marks != null ? marks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MarkList{" +
                "title='" + title + '\'' +
                ", ownerUserId='" + ownerUserId + '\'' +
                ", marks=" + marks +
                '}';
    }

}
