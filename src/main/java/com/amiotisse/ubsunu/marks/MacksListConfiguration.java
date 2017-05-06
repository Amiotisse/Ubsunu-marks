package com.amiotisse.ubsunu.marks;

import com.amiotisse.ubsunu.marks.controller.PrivateMarkListController;
import com.amiotisse.ubsunu.marks.controller.PublicMarkListController;
import com.amiotisse.ubsunu.marks.repository.MarkListRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author himna
 * @since 5/6/2017.
 */
@Configuration
public class MacksListConfiguration {

    @Bean
    PublicMarkListController publicMarksListController (MarkListRepository markListRepository){
        return new PublicMarkListController(markListRepository);
    }

    @Bean
    PrivateMarkListController privateMarkListController (MarkListRepository markListRepository){
        return new PrivateMarkListController(markListRepository);
    }
}
