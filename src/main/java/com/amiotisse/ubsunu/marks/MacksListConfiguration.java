package com.amiotisse.ubsunu.marks;

import com.amiotisse.ubsunu.marks.client.MailerFiegnClient;
import com.amiotisse.ubsunu.marks.controller.PrivateMarkListController;
import com.amiotisse.ubsunu.marks.controller.PublicMarkListController;
import com.amiotisse.ubsunu.marks.model.Mark;
import com.amiotisse.ubsunu.marks.model.MarkList;
import com.amiotisse.ubsunu.marks.model.factory.MarkListFactory;
import com.amiotisse.ubsunu.marks.repository.MarkListRepository;
import com.amiotisse.ubsunu.marks.service.ArgsChecker;
import com.amiotisse.ubsunu.marks.service.MarkListService;
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
    PublicMarkListController publicMarksListController (
            MarkListRepository markListRepository
    ){
        return new PublicMarkListController(markListRepository);
    }

    @Bean
    MarkListFactory markListFactory(){
        return new MarkListFactory ();
    }

    @Bean
    PrivateListService<Mark, MarkList> markListPrivateListService(
            MongoRepository<MarkList, String> markListRepository,
            MarkListFactory markListFactory,
            ArgsChecker checker
    ){
        return new PrivateListService<>(markListRepository, markListFactory , checker);
    }

    @Bean
    MarkListService markListService(
            MarkListRepository markListRepository,
            ArgsChecker argsChecker,
            MailerFiegnClient mailerFiegnClient
    ){
        return new MarkListService(
                markListRepository,
                argsChecker,
                mailerFiegnClient,
                isMailerEnable
        );
    }

    @Bean
    PrivateMarkListController privateMarkListController(PrivateListService<Mark, MarkList> service, MarkListService markListService){
        return new PrivateMarkListController(service , markListService);
    }
}
