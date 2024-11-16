package threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService es= Executors.newFixedThreadPool(2);
        es.execute(new PrintNumbers());
        es.execute(new PrintA());
        es.execute(new PrintB());

        es.shutdown();

        while (!es.isTerminated()) {
        }
        System.out.println("Main Thread completed");

    }
}
