package semaphoreBanking;

import java.util.concurrent.Semaphore;

class BankAccount {
    private int balance = 10000;
    private final int minBalance = 10000;
    private final int maxBalance = 150000;

    private final Semaphore balanceLock = new Semaphore(1);
    private final Semaphore sufficientBalance = new Semaphore(0);
    private final Semaphore spaceAvailable = new Semaphore(1);

    public void withdraw(int amount) throws InterruptedException {

        balanceLock.acquire();
        try {
            while (balance - amount < minBalance) {
                System.out.println("Insufficient balance for withdrawal. Waiting for deposit...");

                balanceLock.release();
                sufficientBalance.acquire();
                balanceLock.acquire();
            }

            // Perform withdrawal
            balance -= amount;
            System.out.println("Withdrew: " + amount + ", New balance: " + balance);

            spaceAvailable.release();
        } finally {
            balanceLock.release();
        }
    }

    public void deposit(int amount) throws InterruptedException {
        // Acquire lock to modify balance
        balanceLock.acquire();
        try {
            while (balance + amount > maxBalance) {
                System.out.println("Balance limit reached for deposit. Waiting for withdrawal...");
                balanceLock.release();
                spaceAvailable.acquire();
                balanceLock.acquire();
            }

            balance += amount;
            System.out.println("Deposited: " + amount + ", New balance: " + balance);

            sufficientBalance.release();
        } finally {
            balanceLock.release();
        }
    }

    public int getBalance() throws InterruptedException {
        balanceLock.acquire();
        try {
            return balance;
        } finally {
            balanceLock.release();
        }
    }
}
