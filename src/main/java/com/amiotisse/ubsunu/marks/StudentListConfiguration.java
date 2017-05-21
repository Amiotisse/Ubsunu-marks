package com.amiotisse.ubsunu.marks;

import com.amiotisse.ubsunu.marks.controller.StudentListController;
import com.amiotisse.ubsunu.marks.model.Student;
import com.amiotisse.ubsunu.marks.model.StudentList;
import com.amiotisse.ubsunu.marks.model.factory.StudentListFactory;
import com.amiotisse.ubsunu.marks.service.PrivateListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author himna
 * @since 5/21/2017.
 */
@Configuration
public class StudentListConfiguration {



    @Bean
    StudentListFactory studentListFactory(){
        return new StudentListFactory();
    }

    @Bean
    PrivateListService<Student, StudentList> studentListPrivateListService(MongoRepository<StudentList, String> studentListRepository, StudentListFactory studentListFactory){
        return new PrivateListService<>(studentListRepository, studentListFactory);
    }

    @Bean
    StudentListController studentListController( PrivateListService<Student, StudentList> StudentListService){
        return new StudentListController(StudentListService);
    }
}
