package com.lovo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient //客户端
@EnableFeignClients   //feign进行远程调用
public class OneMain {
    public static void main(String[] args) {
        SpringApplication.run(OneMain.class);
    }
    
}
