package com.lovo.basic.queue;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

//放
public class NoBlockQueuePush extends  Thread  {

    BlockingQueue<String> queue=null;
    //持有队列
   public NoBlockQueuePush(BlockingQueue<String> queue){
       this.queue=queue;
   }

    public  void run(){
          for(int i=0;i<10;i++){
              try {
                  queue.put(Thread.currentThread().getName()+"/"+i);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
    }

}
