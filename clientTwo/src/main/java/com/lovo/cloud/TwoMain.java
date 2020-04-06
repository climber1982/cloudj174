package com.lovo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TwoMain {
    public static void main(String[] args) {
        SpringApplication.run(TwoMain.class);
    }
}
