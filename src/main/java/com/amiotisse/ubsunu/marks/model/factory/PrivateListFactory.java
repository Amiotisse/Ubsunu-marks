package com.amiotisse.ubsunu.marks.model.factory;

import com.amiotisse.ubsunu.marks.model.PrivateList;

import java.util.List;

/**
 * @author himna
 * @since 5/21/2017.
 */
public interface PrivateListFactory< Entity, L extends PrivateList<Entity>  > {

   L build (String title , String ownerUserId , List<Entity> list);
}
