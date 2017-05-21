package com.amiotisse.ubsunu.marks.repository;

import com.amiotisse.ubsunu.marks.model.StudentList;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author himna
 * @since 5/21/2017.
 */
public interface StudentListRepository extends MongoRepository<StudentList, String> {
}
