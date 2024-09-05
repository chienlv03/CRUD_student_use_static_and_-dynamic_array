package menu;

import utils.Messages;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HandleUpdateStudent {

    public static int getChoice(Long id, Scanner scanner) {
        int choice;
        System.out.println("|======================= Update Student Id " + id + " =========================|");
        System.out.println("| 1. Update Student Code                      6. Update Weight        |");
        System.out.println("| 2. Update Name                              7. Update University    |");
        System.out.println("| 3. Update Date of Birth                     8. Update Start Year    |");
        System.out.println("| 4. Update Address                           9. Update GPA           |");
        System.out.println("| 5. Update Height                            0. Exit                 |");
        System.out.println("|---------------------------------------------------------------------|");
        System.out.print(Messages.SELECT_OPTION);

        choice = scanner.nextInt();
        return choice;
    }

    public static int getValidChoice(Scanner scanner, Long id) {
        int choice;
        while (true) {
            try {
                choice = getChoice(id, scanner);
                if (choice >= 0 && choice <= 9) {
                    return choice;
                } else {
                    System.out.println(Messages.INVALID_OPTION);
                }
            } catch (InputMismatchException e) {
                System.out.println(Messages.ERROR_NOT_NUMERIC);
                scanner.nextLine();
            }
        }
    }
}
