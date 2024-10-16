package threadskill;

public class Table4 extends Thread {
    public void run(){
        for(int i=1;i<=10;i++){
            System.out.println("5 * "+i+" = "+5*i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
