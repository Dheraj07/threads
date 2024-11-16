package lockBanking;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private int balance = 10000;
    private final int minBalance = 10000;
    private final int maxBalance = 150000;
    private final Lock lock = new ReentrantLock();
    private final Condition wc = lock.newCondition();
    private final Condition dc = lock.newCondition();

    public void withdraw(int amount) throws InterruptedException {
        lock.lock();
        try {
            while (balance - amount < minBalance) {
                System.out.println("Insufficient balance for withdrawal. Waiting for deposit...");
                wc.await();
            }
            balance -= amount;
            System.out.println("Withdrew: " + amount + ", New balance: " + balance);
            dc.signalAll(); // Notify depositors
        } finally {
            lock.unlock();
        }
    }

    public void deposit(int amount) throws InterruptedException {
        lock.lock();
        try {
            while (balance + amount > maxBalance) {
                System.out.println("Balance limit reached for deposit. Waiting for withdrawal...");
                dc.await();
            }
            balance += amount;
            System.out.println("Deposited: " + amount + ", New balance: " + balance);
            wc.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
