package com.amiotisse.ubsunu.marks;

import com.amiotisse.ubsunu.marks.client.MailerFiegnClient;
import com.amiotisse.ubsunu.marks.controller.PrivateMarkListController;
import com.amiotisse.ubsunu.marks.controller.PublicMarkListController;
import com.amiotisse.ubsunu.marks.repository.MarkListRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    PrivateMarkListController privateMarkListController (MarkListRepository markListRepository , MailerFiegnClient mailerFiegnClient){
        return new PrivateMarkListController(markListRepository , mailerFiegnClient , isMailerEnable);
    }
}
