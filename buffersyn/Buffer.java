package buffersyn;

import java.util.LinkedList;

public class Buffer {
    private LinkedList<Integer> b;
    private static final int MAXSIZE=3;

    public Buffer(){
        b=new LinkedList<>();
    }

    public synchronized void produce(int e) throws InterruptedException {
        while (b.size()>=MAXSIZE){
            System.out.println("Producer is waiting because buffer is full");
            wait();
        }
        b.add(e);
        notify();//It will break wait of other thread
        System.out.println("Produced: "+e);

    }

    public synchronized void consume()  {
        while (b.isEmpty()){
            System.out.println("Consumer is waiting because Buffer is empty");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        System.out.println("Consumed "+b.removeFirst());
        notify();
    }
}
