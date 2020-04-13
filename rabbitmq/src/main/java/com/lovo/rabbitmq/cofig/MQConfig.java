package com.lovo.rabbitmq.cofig;

import org.springframework.amqp.core.*;

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
    //创建队列
    @Bean
    public Queue topicQueueA(){
        return new Queue("topic.queue.a");
    }
    //创建交换机
    @Bean
    public DirectExchange pointDirectExchange(){
       return  new DirectExchange("pointDirectExchange");
    }
   @Bean
    public  TopicExchange topicExchangeA(){
      return new TopicExchange("topicExchangeA");
    }

   @Bean
    public  FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    //把队列与交换机进行绑定
    @Bean
    public Binding pointQueueTopointDirectExchange(Queue pointQueue,DirectExchange pointDirectExchange){
        return BindingBuilder.bind(pointQueue).to(pointDirectExchange).with("pointKey");
    }
   @Bean
    public Binding  topicQueueATotopicExchangeA(Queue topicQueueA,TopicExchange topicExchangeA){
     return  BindingBuilder.bind(topicQueueA).to(topicExchangeA).with("topic.#");
    }

    /*****绑定fanout****/
   @Bean
   public Binding fanoutExchangtoPointQueue(Queue pointQueue,FanoutExchange fanoutExchange){
     return   BindingBuilder.bind(pointQueue).to(fanoutExchange);
   }
    @Bean
    public Binding fanoutExchangtotopicQueueA(Queue topicQueueA,FanoutExchange fanoutExchange){
        return   BindingBuilder.bind(topicQueueA).to(fanoutExchange);
    }




}
