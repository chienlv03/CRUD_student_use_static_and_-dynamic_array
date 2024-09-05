package menu;

import entity.Student;
import service.StaticArray;
import service.StudentService;
import utils.InputHandler;
import static utils.Messages.*;

import java.util.InputMismatchException;
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
                System.out.println(INVALID_OPTION);
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
        System.out.print(SELECT_OPTION);
    }

    private void handleMenuSelection(int choose) {
        switch (choose) {
            case 1 -> getAllStudents();
            case 2 -> createNewStudent();
            case 3 -> findStudentById();
            case 4 -> updateStudent();
            case 5 -> removeStudent();
            case 0 -> System.out.println("Exiting program...");
            default -> System.out.println(INVALID_OPTION);
        }
    }

    // ok
    private void createNewStudent() {
        System.out.println("\n============= Create new student ===============");
        Student[] currentStudents = StaticArray.getStudents();
        studentService.create(InputHandler.inputStudent(currentStudents));
        getAllStudents();
    }

    private void getAllStudents() {
        if (isEmptyStudentArray()) return;
        System.out.println("\n======================================= LIST OF STUDENTS " +
                "=======================================");
        studentService.getAll();
    }

    // ok
    private void findStudentById() {
        if (isEmptyStudentArray()) return;
        System.out.print(ENTER_ID);
        try {
            Long id = scanner.nextLong();
            scanner.nextLine();
            studentService.findById(id)
                    .ifPresentOrElse(
                            student -> System.out.println(student + "\n"),
                            () -> System.out.println(ERROR_NOT_FOUND_ID + id)
                    );
        } catch (InputMismatchException e) {
            System.out.println(ERROR_NOT_NUMERIC);
            scanner.nextLine();
        }
    }

    private void updateStudent() {
        if (isEmptyStudentArray()) return;
        System.out.print(ENTER_ID);
        try {
            Long id = scanner.nextLong();
            scanner.nextLine();
            studentService.update(id);
        } catch (InputMismatchException e) {
            System.out.println(ERROR_NOT_NUMERIC);
            scanner.nextLine();
        }
    }

    private void removeStudent() {
        if (isEmptyStudentArray()) return;
        System.out.print(ENTER_ID);
        try {
            Long id = scanner.nextLong();
            scanner.nextLine();
            studentService.remove(id);
        } catch (InputMismatchException e) {
            System.out.println(ERROR_NOT_NUMERIC);
            scanner.nextLine();
        }
    }

    private boolean isEmptyStudentArray() {
        if (StaticArray.isEmpty()) {
            System.out.println(EMPTY_LIST);
            return true;
        }
        return false;
    }
}
