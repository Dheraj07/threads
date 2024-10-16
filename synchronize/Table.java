package synchronize;

public class Table {
    public static synchronized void printTable(int n) {

            for (int i = 1; i <= 10; i++) {
                System.out.println(n + " * " + i + " = " + n * i);
            }
        }

}
