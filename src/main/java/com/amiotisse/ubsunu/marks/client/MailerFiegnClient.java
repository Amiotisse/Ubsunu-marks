package com.amiotisse.ubsunu.marks.client;

import com.amiotisse.ubsunu.marks.model.MarkList;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author himna
 * @since 5/8/2017.
 */
@FeignClient(value = "${ubsunu.mailer.server.url}", url = "${ubsunu.mailer.server.url}")
public interface MailerFiegnClient {

    @RequestMapping(value = "/marks/publish" , method = RequestMethod.POST)
    void publishMarkList(@RequestBody MarkList markList);


}
