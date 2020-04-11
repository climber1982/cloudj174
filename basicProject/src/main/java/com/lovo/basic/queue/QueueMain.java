package com.lovo.basic.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueMain {
    public static void main(String[] args) {
        //1 创建队列
       //Queue<String> queue=new LinkedList<String>();

        BlockingQueue queue=new ArrayBlockingQueue(10);
        //创建2个添加线程
        NoBlockQueuePush push1=new NoBlockQueuePush(queue);
        NoBlockQueuePush push2=new NoBlockQueuePush(queue);
        //创建3个获取线程
        NoBlockQueuePop pop1=new NoBlockQueuePop(queue);
        NoBlockQueuePop pop2=new NoBlockQueuePop(queue);
        NoBlockQueuePop pop3=new NoBlockQueuePop(queue);
        //执行
        push1.start();
        push2.start();
        pop1.start();
        pop2.start();
        pop3.start();



    }
}
