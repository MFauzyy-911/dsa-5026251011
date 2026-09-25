import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.io.FileNotFoundException;

public class BankTransaction {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(BankTransaction.class.getResourceAsStream("transactions.txt"));

        LinkedList<String[]> transactions = new LinkedList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] data = line.split(" ");
            transactions.add(data);
        }
        sc.close();

        LinkedList<String[]> customers = new LinkedList<>();
        for (String[] t : transactions) {
            String name = t[0];
            boolean found = false;
            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                customers.add(new String[]{name, "0"});
            }
        }

        Queue<String[]> queue = new LinkedList<>();
        for (String[] t : transactions) {
            queue.add(t);
        }

        Stack<String[]> failedStack = new Stack<>();

        while (!queue.isEmpty()) {
            String[] t = queue.poll();
            String name = t[0];
            String type = t[1];
            int amount = Integer.parseInt(t[2]);

            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    int balance = Integer.parseInt(c[1]);
                    if (type.equals("DEPOSIT")) {
                        balance += amount;
                        c[1] = String.valueOf(balance);
                    } else if (type.equals("WITHDRAW")) {
                        if (amount > balance) {
                            failedStack.push(t);
                        } else {
                            balance -= amount;
                            c[1] = String.valueOf(balance);
                        }
                    }
                    break;
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] f = failedStack.pop();
            System.out.println(f[0] + " " + f[1] + " " + f[2]);
        }
    }
}