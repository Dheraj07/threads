package threadskill;

public class Table2 extends Thread {
    public void run(){
        for(int i=1;i<=10;i++){
            System.out.println("3 * "+i+" = "+3*i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
