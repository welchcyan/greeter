package com.pivotal.willchen;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenw13 on 7/29/16.
 */
@Service
public class GreetAgentService {

    @Autowired
    MessageGenerationClient helloClient;

    @HystrixCommand(fallbackMethod = "getBackupGreet")
    public String greet(String salutation, String name, String add) {
        String greeting =  helloClient.greeting(salutation, name);
        GreetingTransffered gt = new GreetingTransffered(greeting,add);
        gt.setMessage(doSomeOtherWork(gt.getMessage()));
        return gt.getMessage();
    }

    public String getBackupGreet(String salutation, String name, String addit) {
        GreetingTransffered gt = new GreetingTransffered( "Oops! Something wrong", "This called from Hytrix fallback");
        return gt.getMessage();
    }

    private static class GreetingTransffered {

        private static final String template = "%s, addt %s!";

        private String message;

        public GreetingTransffered(String message, String add) {
            this.message = String.format(template, message, add);
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    private String doSomeOtherWork(String message) {
        return "This message:" + message + ": is transfferd from Greet";
    }
}
