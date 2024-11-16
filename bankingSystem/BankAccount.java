package bankingSystem;

class BankAccount {
    private int balance = 10000;
    private final int minBalance = 10000;
    private final int maxBalance = 150000;

    public synchronized void withdraw(int amount) throws InterruptedException {
        while (balance - amount < minBalance) {
            System.out.println("Insufficient balance for withdrawal. Waiting for deposit...");
            wait();
        }
        balance -= amount;
        System.out.println("Withdrew: " + amount + ", New balance: " + balance);
        notifyAll();
    }

    public synchronized void deposit(int amount) throws InterruptedException {
        while (balance + amount > maxBalance) {
            System.out.println("Balance limit reached for deposit. Waiting for withdrawal...");
            wait();
        }
        balance += amount;
        System.out.println("Deposited: " + amount + ", New balance: " + balance);
        notifyAll();
    }

    public synchronized int getBalance() {
        return balance;
    }
}


