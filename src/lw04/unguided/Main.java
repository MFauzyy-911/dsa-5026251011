package lw04.unguided;
import java.util.*;

public class Main {

    static void sortCards(int[] arr, int passes) {
        for (int i = 0; i < passes && i < arr.length; i++) {
            int idx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[idx]) {
                    idx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }
    }

    static int findCard(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("cards.txt"));

        int n = sc.nextInt();
        int[] cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] requests = new int[m];
        for (int i = 0; i < m; i++) {
            requests[i] = sc.nextInt();
        }
        sc.close();

        sortCards(cards, 2);

        for (int i = 0; i < n; i++) {
            System.out.print(cards[i]);
            if (i < n - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        for (int t : requests) {
            int idx = findCard(cards, t);
            if (idx == -1) {
                System.out.println(t + ": NOT FOUND");
            } else {
                System.out.println(t + ": FOUND at index " + idx);
            }
        }
    }
}