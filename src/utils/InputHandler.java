package utils;

import entity.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    static long idStatic = 1L;
    static long idDynamic = 1L;

    public static String inputName() {
        String fullName;
        do {
            System.out.print("Enter student's fullName: ");
            fullName = scanner.nextLine();
        } while (!InputValidator.validateName(fullName));
        return fullName;
    }

    public static LocalDate inputDateOfBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dob;
        do {
            System.out.print("Enter date of birth (dd/MM/yyyy): ");
            dob = scanner.nextLine();
        } while (!InputValidator.validateDateOfBirth(dob));
        return formatter.parse(dob, LocalDate::from);
    }

    public static String inputAddress() {
        String address;
        do {
            System.out.print("Enter address (less than 300 characters): ");
            address = scanner.nextLine().trim();
        } while (!InputValidator.validateAddress(address));
        return address;
    }

    public static double inputHeight() {
        double height;
        while (true) {
            System.out.print("Enter height (cm, between 50.0 and 300.0): ");
            if (scanner.hasNextDouble()) {
                height = scanner.nextDouble();
                scanner.nextLine();
                if (InputValidator.validateHeight(height)) break;
            } else {
                System.out.println(">> Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
        return height;
    }

    public static double inputWeight() {
        double weight;
        while (true) {
            System.out.print("Enter weight (kg, between 10.0 and 1000.0): ");
            if (scanner.hasNextDouble()) {
                weight = scanner.nextDouble();
                scanner.nextLine();
                if (InputValidator.validateWeight(weight)) break;
            } else {
                System.out.println(">> Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
        return weight;
    }

    public static String inputStudentCode(Student[] students) {
        String studentCode;
        do {
            System.out.print("Enter student code (10 characters, not empty, no duplicates): ");
            studentCode = scanner.nextLine().trim();
        } while (!InputValidator.validateStudentCode(studentCode, students));
        return studentCode;
    }

    //
    public static String inputStudentCode(List<Student> students) {
        String studentCode;
        do {
            System.out.print("Enter student code (10 characters, not empty, no duplicates): ");
            studentCode = scanner.nextLine().trim();
        } while (!InputValidator.validateStudentCode(studentCode, students));
        return studentCode;
    }

    public static String inputUniversity() {
        String university;
        do {
            System.out.print("Enter university name (less than 200 characters, not empty): ");
            university = scanner.nextLine().trim();
        } while (!InputValidator.validateUniversity(university));
        return university;
    }

    public static int inputStartYear() {
        int startYear;
        while (true) {
            System.out.print("Enter start year (between 1900 and current year): ");
            if (scanner.hasNextInt()) {
                startYear = scanner.nextInt();
                scanner.nextLine();
                if (InputValidator.validateStartYear(startYear)) break;
            } else {
                System.out.println(">> Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
        return startYear;
    }

    public static double inputGPA() {
        double gpa;
        while (true) {
            System.out.print("Enter GPA (between 0.0 and 10.0): ");
            if (scanner.hasNextDouble()) {
                gpa = scanner.nextDouble();
                scanner.nextLine();
                if (InputValidator.validateGPA(gpa)) break;
            } else {
                System.out.println(">> Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
        return gpa;
    }

    // Mang tinh
    public static Student inputStudent(Student[] students) {
        return new Student(
                idStatic++,
                inputStudentCode(students),
                inputName(),
                inputDateOfBirth(),
                inputAddress(),
                inputHeight(),
                inputWeight(),
                inputUniversity(),
                inputStartYear(),
                inputGPA()
        );
    }

    // Mang dong
    public static Student inputStudent(List<Student> students) {
        return new Student(
                idDynamic++,
                inputStudentCode(students),
                inputName(),
                inputDateOfBirth(),
                inputAddress(),
                inputHeight(),
                inputWeight(),
                inputUniversity(),
                inputStartYear(),
                inputGPA()
        );
    }
}
