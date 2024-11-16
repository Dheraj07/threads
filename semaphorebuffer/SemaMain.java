package semaphorebuffer;

public class SemaMain {
    public static void main(String[] args) {
        Buffer b= new Buffer();

        Thread tp=new Thread(
                ()->{
                    try {
                        int e=0;
                        while (true) {
                            b.produce(e++);
                            Thread.sleep(2000);
                        }
                    }
                    catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }

                }
        );

        Thread tc=new Thread(
                ()->{
                    while (true){

                        try {

                            b.consume();
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        tp.start();
        tc.start();
    }
}
