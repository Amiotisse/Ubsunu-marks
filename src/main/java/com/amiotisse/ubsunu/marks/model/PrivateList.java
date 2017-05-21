package com.amiotisse.ubsunu.marks.model;

import java.util.List;

/**
 * @author himna
 * @since 5/21/2017.
 */
public interface PrivateList <T> {
    String getTitle();
    List<T> getList ();
    String getOwnerUserId();
    void setTitle(String title);
    void setList (List<T> List);
    void setOwnerUserId(String getOwnerUserId);
}
