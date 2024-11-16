package buffer;
//using locks concept

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
   private LinkedList<Integer> b;
   private static final int MAXSIZE=3;

   private static Lock l=new ReentrantLock();
   private static Condition pc=l.newCondition();
   private static Condition cc=l.newCondition();

   public Buffer(){
       b=new LinkedList<>();
   }

   public void produce(int e) throws InterruptedException {
       l.lock();
        while (b.size()>=MAXSIZE){
            System.out.println("Producer is waiting because buffer is full");
            pc.await();
        }
        b.add(e);

       System.out.println("Produced: "+e);
       cc.signal();

   }

   public synchronized void consume()  {
       l.lock();
       while (b.isEmpty()){
           System.out.println("Consumer is waiting because Buffer is empty");
           try {
               cc.await();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }

       }

       System.out.println("Consumed "+b.removeFirst());
       pc.signal();
   }
}


//semafore