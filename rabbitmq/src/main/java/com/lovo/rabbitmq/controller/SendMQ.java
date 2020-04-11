package com.lovo.rabbitmq.controller;

import com.lovo.rabbitmq.vo.UserVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMQ {

    //注入rabbitmq模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("sendMQ")
    public String sendMQ(String str){
        rabbitTemplate.convertAndSend("pointDirectExchange","pointKey",str);
         return "发送成功";
    }
    @RequestMapping("sendMQObject")
    public String sendMQObject(UserVo vo){
        rabbitTemplate.convertAndSend("pointDirectExchange","pointKey",vo);
        return "发送成功";
    }
}
