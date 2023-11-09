package com.yuzarsif.youcontribute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YoucontributeApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoucontributeApplication.class, args);
    }

}
