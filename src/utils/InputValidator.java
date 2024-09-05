package utils;

import entity.Student;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import static utils.Constants.*;
import static utils.Messages.*;

public class InputValidator {
    public static boolean validateName(String name) {
        if (name.trim().isEmpty()) {
            System.out.println(ERROR_FIELD_EMPTY);
            return false;
        }
        if (name.trim().length() > MAX_LENGTH_NAME) {
            System.out.println(">> Name must be less than 100 characters");
            return false;
        }
        if (!NAME_PATTERN.matcher(name).matches()) {
            System.out.println(">> Name must contain only letters and spaces");
            return false;
        }
        return true;
    }

    public static boolean validateDateOfBirth(String dob) {
        if (dob.isEmpty()) {
            System.out.println(ERROR_FIELD_EMPTY);
            return false;
        }
        try {
            LocalDate dateOfBirth = LocalDate.parse(dob, DATE_FORMATTER);
            if (dateOfBirth.getYear() <= MIN_YEAR || dateOfBirth.getYear() >= MAX_YEAR) {
                System.out.println(">> Date of birth must be from year 1900 to current year");
                return false;
            }
        } catch (DateTimeParseException e) {
            System.out.println(ERROR_NOT_VALID_DATE);
            return false;
        }
        return true;
    }

    public static boolean validateAddress(String address) {
        if (address.trim().isEmpty()) {
            System.out.println(ERROR_FIELD_EMPTY);
            return false;
        }
        if (address.trim().length() >= MAX_LENGTH_ADDRESS) {
            System.out.println(">> Address must be less than 300 characters");
            return false;
        }
        return true;
    }

    public static boolean validateHeight(String heightInput) {
        if (heightInput.trim().isEmpty()) {
            System.out.println(ERROR_FIELD_EMPTY);
            return false;
        }
        try {
            double height = Double.parseDouble(heightInput);
            if (height < MIN_HEIGHT || height > MAX_HEIGHT) {
                System.out.println(">> Height must be between 50.0 and 300.0");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println(ERROR_NOT_NUMERIC);
            return false;
        }
        return true;
    }

    public static boolean validateWeight(String weightInput) {
        if (weightInput.trim().isEmpty()) {
            System.out.println(ERROR_FIELD_EMPTY);
            return true;
        }
        try {
            double weight = Double.parseDouble(weightInput);
            if (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
                System.out.println(">> Weight must be between 10.0 and 1000.0");
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println(ERROR_NOT_NUMERIC);
            return true;
        }
        return false;
    }


    // Mảng tĩnh
    public static boolean validateStudentCode(String studentCode, Student[] students) {
        if (studentCode == null || !STUDENT_CODE_PATTERN.matcher(studentCode).matches()) {
            System.out.println(INVALID_STUDENT_CODE);
            return false;
        }
        for (Student student : students) {
            if (student != null && student.getStudentCode().equals(studentCode)) {
                System.out.println(STUDENT_CODE_EXISTS);
                return false;
            }
        }
        return true;
    }

    // Mảng động
    public static boolean validateStudentCode(String studentCode, List<Student> students) {
        if (studentCode == null || !STUDENT_CODE_PATTERN.matcher(studentCode).matches()) {
            System.out.println(INVALID_STUDENT_CODE);
            return false;
        }
        for (Student student : students) {
            if (student.getStudentCode().equals(studentCode)) {
                System.out.println(STUDENT_CODE_EXISTS);
                return false;
            }
        }
        return true;
    }

    public static boolean validateUniversity(String university) {
        if (university.trim().isEmpty()) {
            System.out.println(ERROR_FIELD_EMPTY);
            return false;
        }
        if (university.trim().length() >= MAX_LENGTH_UNIVERSITY) {
            System.out.println(">> University name must be less than 200 characters");
            return false;
        }
        return true;
    }

    public static boolean validateStartYear(String startYearInput) {
        if (startYearInput.trim().isEmpty()) {
            System.out.println(ERROR_FIELD_EMPTY);
            return false;
        }

        try {
            int startYear = Integer.parseInt(startYearInput);
            if (startYear < MIN_YEAR || startYear > MAX_YEAR) {
                System.out.println(">> The start year must be from year 1900 to current year");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println(ERROR_NOT_NUMERIC);
            return false;
        }
        return true;
    }

    public static boolean validateGPA(String gpaInput) {
        if (gpaInput.trim().isEmpty()) {
            System.out.println(ERROR_FIELD_EMPTY);
            return false;
        }

        try {
            double gpa = Double.parseDouble(gpaInput);
            if (gpa < MIN_GPA || gpa > MAX_GPA) {
                System.out.println(">> GPA must be between 0.0 and 10.0");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println(ERROR_NOT_NUMERIC);
            return false;
        }
        return true;
    }
}
