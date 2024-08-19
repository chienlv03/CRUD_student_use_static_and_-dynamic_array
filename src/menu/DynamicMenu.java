package menu;

import service.DynamicArray;
import service.StudentService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DynamicMenu {
    private final StudentService studentService;
    private final Scanner scanner = new Scanner(System.in);

    public DynamicMenu(StudentService studentService) {
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
            } catch (InputMismatchException e) {
                System.out.println(">> Invalid option, please try again.");
                scanner.nextLine();
                choose = -1;
            }
        } while (choose != 0);
    }

    private void printMenu() {
        System.out.println("\n|------------------------------- Student management with dynamic array --------------------------------|");
        System.out.println("| 1.Display list of students                                        2.Create new student                 |");
        System.out.println("| 3.Find student by id                                              4.Edit student's information         |");
        System.out.println("| 5.Displays the student's academic performance percentage          8.Write student list to file         |");
        System.out.println("| 6.Displays the average score percentage of the students           9.Remove student                     |");
        System.out.println("| 7.Displays a list of students by academic ability                 0. Exit                              |");
        System.out.println("|--------------------------------------------------------------------------------------------------------|");
        System.out.print("Please, select feature: ");
    }

    private void handleMenuSelection(int choose) {
        switch (choose) {
            case 1 -> studentService.getAll();
            case 2 -> studentService.create();
            case 3 -> studentService.findById();
            case 4 -> studentService.update();
            case 5 -> DynamicArray.displayAcademicPercentage();
            case 6 -> DynamicArray.displayAverageScorePercentage();
            case 7 -> DynamicArray.displayStudentsByAcademicAchievement();
            case 8 -> DynamicArray.writeStudentsToFile();
            case 9 -> studentService.remove();
            case 0 -> System.out.println("Exiting program...");
            default -> System.out.println(">> Invalid option, please try again.");
        }
    }
}
