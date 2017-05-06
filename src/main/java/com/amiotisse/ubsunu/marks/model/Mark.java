package com.amiotisse.ubsunu.marks.model;

/**
 * @author himna
 * @since 5/6/2017.
 */
public class Mark {

    private Student student;
    private float value ;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Mark(Student student, float value) {
        this.student = student;
        this.value = value;
    }

    public Mark() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mark mark = (Mark) o;

        if (Float.compare(mark.value, value) != 0) return false;
        return student != null ? student.equals(mark.student) : mark.student == null;
    }

    @Override
    public int hashCode() {
        int result = student != null ? student.hashCode() : 0;
        result = 31 * result + (value != +0.0f ? Float.floatToIntBits(value) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "student=" + student +
                ", value=" + value +
                '}';
    }
}
