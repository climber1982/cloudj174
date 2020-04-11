package com.lovo.basic.queue;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

/**
 * 取
 */
public class NoBlockQueuePop extends Thread {
    BlockingQueue<String> queue=null;

    public NoBlockQueuePop(BlockingQueue<String> queue){
        this.queue=queue;
    }

    public void run(){
        while (true){
            try {
                System.out.println(
                 Thread.currentThread().getName()+"/取："
                 +queue.take()
                );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
