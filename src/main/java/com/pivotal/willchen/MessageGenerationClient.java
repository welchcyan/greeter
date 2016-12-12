package com.pivotal.willchen;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by chenw13 on 7/29/16.
 */
@FeignClient("message-generation")
public interface MessageGenerationClient {
    @RequestMapping(value = "/greeting", method = GET)
    String greeting(@RequestParam("salutation") String salutation, @RequestParam("name") String name);
}