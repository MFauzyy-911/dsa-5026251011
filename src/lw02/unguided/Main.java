import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static final int MAX_BORROW = 2;

    public static void main(String[] args) {

        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        LinkedList<String[]> success = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("borrowing.txt")
        );

        while(scanner.hasNextLine()) {
            String[] req = scanner.nextLine().split(" ");

            requests.add(req);

            String name = req[0];
            boolean exists = false;

            for(String[] member : members) {
                if(member[0].equals(name)) {
                    exists = true;
                    break;
                }
            }

            if(!exists) {
                members.add(new String[]{name, "0"});
            }
        }

        scanner.close();

        for (String[] req : requests) {
            queue.add(req);
        }

        int total = queue.size();

        for (int i = 0; i < total; i++) {
            String[] req = queue.poll();

            String name = req[0];
            String title = req[1];

            String[] book = null;
            for(String[] b : books) {
                if(b[0].equals(title)) {
                    book = b;
                    break;
                }
            }

            String[] member = null;
            for (String[] m : members) {
                if(m[0].equals(name)) {
                    member = m;
                    break;
                }
            }

            int stock = Integer.parseInt(book[1]);
            int borrowed = Integer.parseInt(member[1]);

            if (stock > 0 && borrowed < MAX_BORROW) {
                book[1] = String.valueOf(stock - 1);
                member[1] = String.valueOf(borrowed + 1);
                success.add(req);
            } else{
                failed.push(req);
            }
        }

        System.out.println("=== Successfully Processed Requests ===");

        for (String[] req : success) {
            System.out.println(req[0] + " " + req[1]);
        }

        System.out.println();
        System.out.println("=== Remaining Book Stock ===");

        for (String[] book : books) {
            System.out.println(book[0] + " : " + book[1]);
        }

        System.out.println();
        System.out.println("=== Failed Requests ===");

        while (!failed.isEmpty()) {
            String[] req = failed.pop();
            System.out.println(req[0] + " " + req[1]);
        }
    }
}