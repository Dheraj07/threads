package Threading;

public class EndUser {
    public static void main(String[] args) {
        //Thread t1=new Thread(new PrintNumbers());
       // Thread t2=new Thread(new PrintA());
       // Thread t3=new Thread(new PrintB());
        Thread t1=new Thread(()-> {
            for (int i = 1; i <= 100; i++) {
                System.out.print(i+" ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                   // throw new RuntimeException(e);
                    e.printStackTrace();
                }
            }
        });

        Thread t2=new Thread(
                ()->{
                    for (int i=1;i<=100;i++){
                        System.out.print("A ");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        Thread t3=new Thread(
                ()->{
                    for (int i=1;i<=100;i++){
                        System.out.print("B ");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        t1.start(); 
        t2.start();
        t3.start();






//        try {
//            t1.join();
////            t2.join();
////            t3.join();
//        }
//       catch (InterruptedException e){
//           System.out.println(e);
//       }
        System.out.println("\nEnd of main thread");

    }
}
//join() method in thread