package com.amiotisse.ubsunu.marks.model;

/**
 * @author himna
 * @since 5/22/2017.
 */
public class TitleCoef {

    private String markListTitle;
    private int coef ;

    public TitleCoef(String markListTitle, int coef) {
        this.markListTitle = markListTitle;
        this.coef = coef;
    }

    public String getMarkListTitle() {
        return markListTitle;
    }

    public void setMarkListTitle(String markListTitle) {
        this.markListTitle = markListTitle;
    }

    public int getCoef() {
        return coef;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

    @Override
    public String toString() {
        return "TitleCoef{" +
                "markListTitle='" + markListTitle + '\'' +
                ", coef=" + coef +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TitleCoef that = (TitleCoef) o;

        if (coef != that.coef) return false;
        return markListTitle != null ? markListTitle.equals(that.markListTitle) : that.markListTitle == null;
    }

    @Override
    public int hashCode() {
        int result = markListTitle != null ? markListTitle.hashCode() : 0;
        result = 31 * result + coef;
        return result;
    }
}
