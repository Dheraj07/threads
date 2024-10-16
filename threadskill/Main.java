package threadskill;

public class Main {
    public static void main(String[] args) {
        Thread t1=new Table1();
        Thread t2=new Table2();
        Thread t3=new Table3();
        Thread t4=new Table4();

        //t1.interrupt();

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
