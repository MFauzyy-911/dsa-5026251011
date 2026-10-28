import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("rentals.txt"));

        List<Rental> rentals = new ArrayList<>();

        while (scanner.hasNext()) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();

            if (type.equals("LAPTOP")) {
                rentals.add(new LaptopRental(id, days));
            } else if (type.equals("PROJECTOR")) {
                rentals.add(new ProjectorRental(id, days));
            }
        }

        scanner.close();

        for (Rental rental : rentals) {
            System.out.println(rental.summary());
        }
    }
}