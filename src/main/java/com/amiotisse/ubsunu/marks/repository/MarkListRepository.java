package com.amiotisse.ubsunu.marks.repository;

import com.amiotisse.ubsunu.marks.model.MarkList;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author himna
 * @since 5/6/2017.
 */
public interface MarkListRepository extends MongoRepository<MarkList, String> {
}
