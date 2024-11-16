package semaphorebuffer;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Buffer {
    private LinkedList<Integer> b;
    private static final int MAXSIZE=3;
//    private Semaphore empty=new Semaphore(MAXSIZE);
//    private Semaphore mutex=new Semaphore(1);
//    private Semaphore full=new Semaphore(0);

    private Semaphore s=new Semaphore(1);

    public Buffer(){
        b=new LinkedList<>();
    }

    public void produce(int e) throws InterruptedException {
//        empty.acquire();
//        mutex.acquire();
        s.acquire();


        b.add(e);
        System.out.println("Produced "+e);
//        mutex.release();
//        full.release();


    }

    public void consume() throws InterruptedException {
//        full.acquire();
//        mutex.acquire();
        s.release();

        int it=b.removeFirst();
        System.out.println("Consumed "+it);
//        mutex.release();
//        empty.release();

    }

}
