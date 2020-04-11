package com.lovo.rabbitmq.cofig;

import org.springframework.amqp.core.Binding;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    //注入工厂（已经预加载）
    @Autowired
    CachingConnectionFactory connectionFactory;
    //rabbitmq模板
    @Bean(value = "rabbitTemplate")
    public RabbitTemplate rabbitTemplate(){
           return new RabbitTemplate(connectionFactory);
    }

    //创建队列
    @Bean
    public Queue pointQueue(){
       return new Queue("pointQueue");
    }
    //创建交换机
    @Bean
    public DirectExchange pointDirectExchange(){
       return  new DirectExchange("pointDirectExchange");
    }
    //把队列与交换机进行绑定
    public Binding pointQueueTopointDirectExchange(Queue pointQueue,DirectExchange pointDirectExchange){
        return BindingBuilder.bind(pointQueue).to(pointDirectExchange).with("pointKey");
    }
}
