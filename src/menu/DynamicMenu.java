package menu;

import entity.Student;
import service.DynamicArray;
import service.StudentService;
import utils.InputHandler;
import static utils.Messages.*;

import java.util.InputMismatchException;
import java.util.List;
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
                System.out.println(INVALID_OPTION);
                scanner.nextLine();
                choose = -1;
            }
        } while (choose != 0);
    }

    private void printMenu() {
        System.out.println("\n|------------------------------------------ Student management with dynamic array --------------------------------------|");
        System.out.println("| 1.Display list of students                                            2.Create new student                            |");
        System.out.println("| 3.Find student by id                                                  4.Edit student's information                    |");
        System.out.println("| 5.Displays the student's academic performance percentage              6.Displays the GPA of the students              |");
        System.out.println("| 7.Displays a list of students by academic ability                     8.Remove student                                |");
        System.out.println("|                                               0. Save to file and Exit                                                |");
        System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
        System.out.print(SELECT_OPTION);
    }

    private void handleMenuSelection(int choose) {
        switch (choose) {
            case 1 -> getAllStudents();
            case 2 -> createNewStudent();
            case 3 -> findStudentById();
            case 4 -> updateStudent();
            case 5 -> displayAcademicPercentage();
            case 6 -> displayAverageScorePercentage();
            case 7 -> displayStudentsByAcademicAchievement();
            case 8 -> removeStudent();
            case 0 -> writeStudentsToFile();
            default -> System.out.println(INVALID_OPTION);
        }
    }

    private boolean isEmptyStudentList() {
        if (DynamicArray.isEmpty()) {
            System.out.println(EMPTY_LIST);
            return true;
        }
        return false;
    }

    private void createNewStudent() {
        System.out.println("\n============= Create new student ===============");
        List<Student> currentStudents = DynamicArray.getStudents(); // Lấy danh sách sinh viên hiện tại
        studentService.create(InputHandler.inputStudent(currentStudents));
        getAllStudents();
    }

    private void getAllStudents() {
        if (isEmptyStudentList()) return;
        System.out.println("\n======================================= LIST OF STUDENTS =======================================");
        studentService.getAll();
    }

    private void findStudentById() {
        if (isEmptyStudentList()) return;
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
        if (isEmptyStudentList()) return;
        System.out.print(ENTER_ID);
        Long studentId = scanner.nextLong();
        scanner.nextLine();
        studentService.update(studentId);
//        getAllStudents();
    }

    private void displayAcademicPercentage() {
        if (isEmptyStudentList()) return;
        DynamicArray.displayAcademicPercentage();
    }

    private void displayAverageScorePercentage() {
        if (isEmptyStudentList()) return;
        DynamicArray.displayAverageScorePercentage();
    }

    private void displayStudentsByAcademicAchievement() {
        if (isEmptyStudentList()) return;

        String selectedAchievement;
        while (true) {
            System.out.print("Enter academic achievement (Xuất sắc, Giỏi, Khá, Trung bình, Yếu, Kém) or '0' to exit: ");
            selectedAchievement = scanner.nextLine().trim();

            if (selectedAchievement.equals("0")) {
                System.out.println("Exiting...");
                break;
            } else if (List.of("Xuất sắc", "Giỏi", "Khá", "Trung bình", "Yếu", "Kém").contains(selectedAchievement)) {
                DynamicArray.displayStudentsByAcademicAchievement(selectedAchievement);
                System.out.println();
            } else {
                System.out.println(">> Invalid academic achievement. Please try again.");
            }
        }
    }

    private void writeStudentsToFile() {
        String fileName;
        while (true) {
            System.out.print("Enter file name to save: ");
            fileName = scanner.nextLine().trim();

            if(fileName.isEmpty()) {
                System.out.println(">> Please, enter file name to save");
            } else {
                break;
            }
        }
        DynamicArray.writeStudentsToFile(fileName);
    }

    private void removeStudent() {
        if (isEmptyStudentList()) return;
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

}
