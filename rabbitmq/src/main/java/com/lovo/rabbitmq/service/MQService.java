package com.lovo.rabbitmq.service;

import com.lovo.rabbitmq.vo.UserVo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service(value = "mQService")
public class MQService {
//    @RabbitListener(queues = "pointQueue")//监听MQ
//    public void getPointQueueInfo(String message){
//        System.out.println("收到队列发来的消息："+message);
//    }
    @RabbitListener(queues = "pointQueue")//监听MQ
    public void getPointQueueObject(UserVo userVo){
        System.out.println("收到队列发来的消息："+userVo.getUserName());
    }
}
