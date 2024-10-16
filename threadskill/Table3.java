package threadskill;

public class Table3 extends Thread {
    public void run(){
        for(int i=1;i<=10;i++){
            System.out.println("4 * "+i+" = "+4*i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
