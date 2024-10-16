package Threading;

public class  PrintA implements Runnable{
    public void run(){
        int i=0;
        for (i=0;i<100;i++){
            System.out.print("A ");
        }
    }

}
