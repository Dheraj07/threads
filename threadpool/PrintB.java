package threadpool;

public class PrintB implements Runnable{
    public void run(){
        int i=0;
        for (i=0;i<100;i++){
            System.out.print("B ");
        }
    }

}
