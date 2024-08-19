package menu;

import service.StudentService;

import java.util.Scanner;

public class StaticMenu {
    private final StudentService studentService;
    private final Scanner scanner = new Scanner(System.in);

    public StaticMenu(StudentService studentService) {
        this.studentService = studentService;
    }

    public void displayMenu() {
        int choose;
        do {
            printMenu();
            try {
                choose = scanner.nextInt();
                scanner.nextLine();
                handleMenuSelection(choose);
            } catch (Exception e) {
                System.out.println(">>Invalid option, please try again.");
                scanner.nextLine();
                choose = -1;
            }
        } while (choose != 0);
    }

    private void printMenu() {
        System.out.println("|--------------------- Student management with static array -------------------------|");
        System.out.println("| 1.Display list of students                        2.Create new student             |");
        System.out.println("| 3.Find student by id                              4.Edit student's information     |");
        System.out.println("| 5.Remove student                                  0.Exit                           |");
        System.out.println("|------------------------------------------------------------------------------------|\n");
        System.out.print("Please, select feature: ");
    }

    private void handleMenuSelection(int choose) {
        switch (choose) {
            case 1 -> studentService.getAll();
            case 2 -> studentService.create();
            case 3 -> studentService.findById();
            case 4 -> studentService.update();
            case 5 -> studentService.remove();
            case 0 -> System.out.println("Exiting program...");
            default -> System.out.println(">>Invalid option, please try again.");
        }
    }
}
