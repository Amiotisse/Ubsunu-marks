package com.amiotisse.ubsunu.marks;

import com.amiotisse.ubsunu.marks.client.MailerFiegnClient;
import com.amiotisse.ubsunu.marks.controller.PrivateMarkListController;
import com.amiotisse.ubsunu.marks.controller.PublicMarkListController;
import com.amiotisse.ubsunu.marks.model.Mark;
import com.amiotisse.ubsunu.marks.model.MarkList;
import com.amiotisse.ubsunu.marks.model.factory.MarkListFactory;
import com.amiotisse.ubsunu.marks.model.factory.PrivateListFactory;
import com.amiotisse.ubsunu.marks.repository.MarkListRepository;
import com.amiotisse.ubsunu.marks.service.PrivateListService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author himna
 * @since 5/6/2017.
 */
@Configuration
@EnableFeignClients(clients = {MailerFiegnClient.class})
public class MacksListConfiguration {

    @Value("${ubsunu.mailer.enable}")
    boolean isMailerEnable;

    @Bean
    PublicMarkListController publicMarksListController (MarkListRepository markListRepository){
        return new PublicMarkListController(markListRepository);
    }

    @Bean
    MarkListFactory markListFactory(){
        return new MarkListFactory ();
    }

    @Bean
    PrivateListService<Mark, MarkList> markListService(MongoRepository<MarkList, String> markListRepository, MarkListFactory markListFactory){
        return new PrivateListService<>(markListRepository, markListFactory);
    }

    @Bean
    PrivateMarkListController privateMarkListController( MailerFiegnClient mailerFiegnClient, PrivateListService<Mark, MarkList> markListService){
        return new PrivateMarkListController(markListService , mailerFiegnClient , isMailerEnable);
    }
}
