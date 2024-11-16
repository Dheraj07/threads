package linkedBlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Buffer {
    private final BlockingQueue<Integer> queue;
    private static final int MAXSIZE = 3;

    public Buffer() {
        queue = new LinkedBlockingQueue<>(MAXSIZE);
    }

    public void produce(int e) throws InterruptedException {
        while (queue.size()>MAXSIZE){
            System.out.println("Producer is waiting because buffer is full");
        }
        queue.put(e);
        System.out.println("Produced: " + e);
    }

    public void consume() throws InterruptedException {
        while (queue.isEmpty()){
            System.out.println("Consumer is waiting because buffer is empty");
        }
        int item = queue.take();
        System.out.println("Consumed: " + item);
    }
}
