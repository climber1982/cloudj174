package com.lovo.rabbitmq.controller;

import com.lovo.rabbitmq.vo.UserVo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.util.UIUtil;

import java.util.UUID;

@RestController
public class SendMQ {

    //注入rabbitmq模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

       //消息确认
       RabbitTemplate.ConfirmCallback confirmCallback= new  RabbitTemplate.ConfirmCallback(){

           public void confirm(CorrelationData correlationData, boolean b, String s) {
               System.out.println("correlationData="+correlationData);
               System.out.println("b="+b);
               System.out.println("s="+s);
               //写业务  如果 b=true 代表数据已经放入到队列，根据correlationData 修改本地数据tag=1()
           }
       };
        //回退
    RabbitTemplate.ReturnCallback returnCallback=new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("message="+message);
                System.out.println("i="+i);
                System.out.println("s="+s);
                System.out.println("s1="+s1);
                System.out.println("s2="+s2);
            }
        };
    @RequestMapping("sendMQ")
    public String sendMQ(String str){
        rabbitTemplate.setMandatory(true);//开启消息确认
        //绑定回调函数
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        String uuid=UUID.randomUUID().toString();
        //消息确认唯一标志，一定要保证唯一
        CorrelationData correlationData=new CorrelationData(uuid);
        System.out.println(correlationData.getId()+"/uuid="+uuid);
        //发送消息，不一定成功
        rabbitTemplate.convertAndSend("pointDirectExchange","pointKey",str,correlationData);
          //保存发送的消息，到本地数据库 ，本地数据设置一个tag=0 （0-预处理，1-正式数据）

         return "发送成功";
    }
    @RequestMapping("sendMQObject")
    public String sendMQObject(UserVo vo){
        rabbitTemplate.convertAndSend("pointDirectExchange","pointKey",vo);
        return "发送成功";
    }
    @RequestMapping("sendTopic")
    public String sendTopic(){
        rabbitTemplate.convertAndSend("topicExchangeA","topic.hello","hello topic");
      return "发送成功";
    }
    @RequestMapping("sendfanout")
    public  String sendfanout(){
        rabbitTemplate.convertAndSend("fanoutExchange","","hello fanout");
        return "发送成功";
    }
}
