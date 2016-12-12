package com.pivotal.willchen;

/**
 * Created by chenw13 on 7/29/16.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetController {

    @Autowired
    GreetAgentService greetAgent;

    private Log log = LogFactory.getLog(GreetController.class);

    public GreetController() {
        log.info("Hello GreetController initialized");
    }

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value="salutation", defaultValue="Hello") String salutation,  @RequestParam(value="name", defaultValue="Bob") String name, @RequestParam(value="add", defaultValue="addi") String add) {
        log.info("Start to call remote service");
        String greeting =  greetAgent.greet(salutation,name, add);
        return greeting;
    }

}
