package menu;

import java.util.Scanner;

public class MenuUpdateStudent {
    public static int getChoice(Long id, Scanner scanner) {
        int choice;
        System.out.println("\n=============== Update Student Id " + id + " ===============");
        System.out.println("1. Update Student Code");
        System.out.println("2. Update Name");
        System.out.println("3. Update Date of Birth");
        System.out.println("4. Update Address");
        System.out.println("5. Update Height");
        System.out.println("6. Update Weight");
        System.out.println("7. Update University");
        System.out.println("8. Update Start Year");
        System.out.println("9. Update GPA");
        System.out.println("0. Exit");
        System.out.print("Please, select an option: ");

        choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    }
}
