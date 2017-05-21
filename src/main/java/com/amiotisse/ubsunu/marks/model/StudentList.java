package com.amiotisse.ubsunu.marks.model;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @author himna
 * @since 5/21/2017.
 */
public class StudentList implements PrivateList<Student> {

    @Id
    private String title;

    private String ownerUserId;
    private List<Student> list;

    public StudentList(String title, String ownerUserId, List<Student> list) {
        this.title = title;
        this.ownerUserId = ownerUserId;
        this.list = list;
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

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentList that = (StudentList) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (ownerUserId != null ? !ownerUserId.equals(that.ownerUserId) : that.ownerUserId != null) return false;
        return list != null ? list.equals(that.list) : that.list == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (ownerUserId != null ? ownerUserId.hashCode() : 0);
        result = 31 * result + (list != null ? list.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StudentList{" +
                "title='" + title + '\'' +
                ", ownerUserId='" + ownerUserId + '\'' +
                ", list=" + list +
                '}';
    }
}
