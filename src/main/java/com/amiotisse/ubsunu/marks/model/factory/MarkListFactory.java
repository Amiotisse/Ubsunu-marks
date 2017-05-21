package com.amiotisse.ubsunu.marks.model.factory;

import com.amiotisse.ubsunu.marks.model.Mark;
import com.amiotisse.ubsunu.marks.model.MarkList;
import com.amiotisse.ubsunu.marks.model.builder.MarkListBuilder;

import java.util.List;

/**
 * @author himna
 * @since 5/21/2017.
 */
public class MarkListFactory implements PrivateListFactory<Mark , MarkList> {
    @Override
    public MarkList build(String title, String ownerUserId, List<Mark> list) {
        return new MarkListBuilder()
                .setTitle(title)
                .setMarks(list)
                .setOwnerUserId(ownerUserId)
                .build();
    }
}
