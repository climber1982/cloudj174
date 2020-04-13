package com.lovo.rabbitmq.service;

import com.lovo.rabbitmq.vo.UserVo;
import com.rabbitmq.client.Channel;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service(value = "mQService")
public class MQService {
    @RabbitListener(queues = "pointQueue")//监听MQ
    public void getPointQueueInfo(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag){
          //收到确认消息
        try {
             //tag 唯一标志从队列中获取，然后右放回去一一对应，false-->当前队列
            channel.basicAck(tag,false);
           System.out.println("收到队列发来的消息："+message);


        } catch (IOException e) {
            try {
                //重新去队列中获取
                channel.basicNack(tag,false,true);
                System.out.println("收到队列发来的消息："+message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }
//    @RabbitListener(queues = "pointQueue")//监听MQ
//    public void getPointQueueObject(UserVo userVo){
//        System.out.println("收到队列发来的消息："+userVo.getUserName());
//    }
}
