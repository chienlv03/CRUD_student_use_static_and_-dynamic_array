package utils;

import entity.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static utils.Messages.*;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    static long idStatic = 1L;
    static long idDynamic = 1L;

    public static String inputName() {
        String fullName;
        do {
            System.out.print(ENTER_NAME);
            fullName = scanner.nextLine();
        } while (!InputValidator.validateName(fullName));
        return fullName;
    }

    public static LocalDate inputDateOfBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dob;
        do {
            System.out.print(ENTER_DOB);
            dob = scanner.nextLine();
        } while (!InputValidator.validateDateOfBirth(dob));
        return formatter.parse(dob, LocalDate::from);
    }

    public static String inputAddress() {
        String address;
        do {
            System.out.print(ENTER_ADDRESS);
            address = scanner.nextLine().trim();
        } while (!InputValidator.validateAddress(address));
        return address;
    }

    public static double inputHeight() {
        String heightInput;
        do {
            System.out.print(ENTER_HEIGHT);
            heightInput = scanner.nextLine().trim();
        } while (!InputValidator.validateHeight(heightInput));
        return Double.parseDouble(heightInput);
    }

    public static double inputWeight() {
        String weightInput;
        do {
            System.out.print(ENTER_WEIGHT);
            weightInput = scanner.nextLine().trim();
        } while (InputValidator.validateWeight(weightInput));
        return Double.parseDouble(weightInput);
    }

    public static String inputStudentCode(Student[] students) {
        String studentCode;
        do {
            System.out.print(ENTER_STUDENT_CODE);
            studentCode = scanner.nextLine().trim();
        } while (!InputValidator.validateStudentCode(studentCode, students));
        return studentCode;
    }

    //
    public static String inputStudentCode(List<Student> students) {
        String studentCode;
        do {
            System.out.print(ENTER_STUDENT_CODE);
            studentCode = scanner.nextLine().trim();
        } while (!InputValidator.validateStudentCode(studentCode, students));
        return studentCode;
    }

    public static String inputUniversity() {
        String university;
        do {
            System.out.print(ENTER_UNIVERSITY);
            university = scanner.nextLine().trim();
        } while (!InputValidator.validateUniversity(university));
        return university;
    }

    public static int inputStartYear() {
        String startYear;
        do {
            System.out.print(ENTER_START_YEAR);
            startYear = scanner.nextLine().trim();
        } while (!InputValidator.validateStartYear(startYear));
        return Integer.parseInt(startYear);
    }

    public static double inputGPA() {
        String gpa;
        do {
            System.out.print(ENTER_GPA);
            gpa = scanner.nextLine().trim();
        } while (!InputValidator.validateGPA(gpa));
        return Double.parseDouble(gpa);
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
