package com.himnabil.alphau.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author himna
 * @since 4/16/2017.
 */
@FeignClient(value = "${alphau.server.url}" , url = "${alphau.server.url}")
public interface AlphaUFiegnClient {

    @RequestMapping(value = "/key" , method = RequestMethod.GET)
    String getPublicKey();

}
