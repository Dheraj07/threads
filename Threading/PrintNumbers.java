package Threading;
//print 100 numbers
//print letters

public class PrintNumbers implements Runnable{

    public void run(){
        int i=0;
        for(i=0;i<100;i++){
            System.out.print(i+" ");
        }
    }

}
