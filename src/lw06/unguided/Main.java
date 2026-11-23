package lw06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        String[] values = sc.nextLine().trim().split("\\s+");
        int target = Integer.parseInt(sc.nextLine().trim());

        BinarySearchTree bst = new BinarySearchTree();
        for (String v : values) {
            bst.insert(Integer.parseInt(v));
        }

        System.out.print("Preorder : ");
        bst.preOrder();

        System.out.print("Inorder : ");
        bst.inOrder();

        System.out.print("Postorder : ");
        bst.postOrder();

        System.out.println("Search " + target + " : " + bst.search(target));
        System.out.println("Minimum : " + bst.findMin());
        System.out.println("Maximum : " + bst.findMax());

        sc.close();
    }
}