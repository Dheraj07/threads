package bankingSystem;

public class Main {
    public static void main(String[] args) {
        BankAccount acc=new BankAccount();
        Thread withDrawThread=new Thread(
                ()->{
                        for (int i = 0; i < 20; i++) {
                            try {
                                acc.withdraw(2000);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                });
        Thread depositThread=new Thread(
                ()->{
                    for (int i = 0; i < 20; i++) {
                        try {
                            acc.deposit(10000);
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                });
        Thread balanceThread=new Thread(
                ()->{
                    for (int i = 0; i < 20; i++) {
                        synchronized (acc) { // Synchronize to ensure balance is read correctly
                            try {
                                System.out.println("Balance after transaction: " + acc.getBalance());
                                acc.wait(500); // Wait to simulate some delay for balance checking
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                });

        withDrawThread.start();
        depositThread.start();
        balanceThread.start();
    }
}
