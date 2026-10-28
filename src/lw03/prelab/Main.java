import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        problem1();
        System.out.println();
        problem2();
        System.out.println();
        problem3();
    }

    static void problem1() {
        List<String> playlist = new ArrayList<>();

        if (Main.class.getResource("playlist.txt") == null) {
            System.out.println("File playlist.txt tidak ditemukan.");
            return;
        }

        try (Scanner sc = new Scanner(Main.class.getResourceAsStream("playlist.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ", 2);
                String type = parts[0];

                if (type.equals("ADD")) {
                    playlist.add(parts[1]);
                } else if (type.equals("INSERT")) {
                    String[] p = line.split(" ", 3);
                    int index = Integer.parseInt(p[1]);
                    if (index >= 0 && index <= playlist.size()) {
                        playlist.add(index, p[2]);
                    }
                } else if (type.equals("REMOVE")) {
                    playlist.remove(parts[1]);
                }
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    static void problem2() {
        Set<String> participants = new LinkedHashSet<>();
        int duplicates = 0;

        if (Main.class.getResource("participants.txt") == null) {
            System.out.println("File participants.txt tidak ditemukan.");
            return;
        }

        try (Scanner sc = new Scanner(Main.class.getResourceAsStream("participants.txt"))) {
            while (sc.hasNextLine()) {
                String name = sc.nextLine().trim();
                if (name.isEmpty()) continue;

                if (!participants.add(name)) {
                    duplicates++;
                }
            }
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int no = 1;
        for (String name : participants) {
            System.out.println(no++ + ". " + name);
        }
        System.out.println("Duplicate registrations: " + duplicates);
    }

    static void problem3() {
        Map<String, Integer> stock = new LinkedHashMap<>();
        int failedSales = 0;

        if (Main.class.getResource("inventory.txt") == null) {
            System.out.println("File inventory.txt tidak ditemukan.");
            return;
        }

        try (Scanner sc = new Scanner(Main.class.getResourceAsStream("inventory.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");
                String type = parts[0];
                String product = parts[1];
                int qty = Integer.parseInt(parts[2]);

                if (type.equals("ADD")) {
                    stock.put(product, stock.getOrDefault(product, 0) + qty);
                } else if (type.equals("SELL")) {
                    if (stock.containsKey(product) && stock.get(product) >= qty) {
                        stock.put(product, stock.get(product) - qty);
                    } else {
                        failedSales++;
                    }
                }
            }
        }

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}