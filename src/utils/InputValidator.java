package utils;

import entity.Student;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Pattern NAME_PATTERN = Pattern.compile("[\\p{L}a-zA-Z ]+");
    private static final Pattern STUDENT_CODE_PATTERN = Pattern.compile("^[a-zA-Z0-9]{10}$");


    public static boolean validateName(String name) {
        if (name.isEmpty()) {
            System.out.println(">> Name cannot be empty");
            return false;
        }
        if(name.trim().length() > 100) {
            System.out.println(">> Name must be less than 100 characters");
            return false;
        }
        if(!NAME_PATTERN.matcher(name).matches()) {
            System.out.println(">> Name must contain only letters and spaces");
            return false;
        }
        return true;
    }

    public static boolean validateDateOfBirth(String dob) {
        if(dob.isEmpty()) {
            System.out.println(">> Date of birth cannot be empty");
            return false;
        }
        try {
            LocalDate dateOfBirth = LocalDate.parse(dob, DATE_FORMATTER);
            if (dateOfBirth.getYear() >= 1900 && dateOfBirth.getYear() <= LocalDate.now().getYear()) {
                return true;
            } else {
                System.out.println(">> Date of birth must be from year 1900");
                return false;
            }
        } catch (DateTimeParseException e) {
            System.out.println(">> Invalid date format. Please enter date in format dd/MM/yyyy");
            return false;
        }
    }

    public static boolean validateAddress(String address) {
        if(address.isEmpty()) {
            System.out.println(">> Address cannot be empty");
            return false;
        }
        if (address.length() >= 300) {
            System.out.println(">> Address must be less than 300 characters");
            return false;
        }
        return true;
    }

    public static boolean validateHeight(double height) {
        if (height < 50.0 || height > 300.0) {
            System.out.println(">> Height must be between 50.0 and 300.0");
            return false;
        }
        return true;
    }

    public static boolean validateWeight(double weight) {
        if(weight < 10.0 || weight > 1000.0) {
            System.out.println(">> Weight must be between 10.0 and 1000.0");
            return false;
        }
        return true;
    }

    // Mảng tĩnh
    public static boolean validateStudentCode(String studentCode, Student[] students) {
        if (studentCode == null || !STUDENT_CODE_PATTERN.matcher(studentCode).matches()) {
            System.out.println(">> Invalid student code. It must be exactly 10 alphanumeric characters.");
            return false;
        }
        for (Student student : students) {
            if(student!= null && student.getStudentCode().equals(studentCode)) {
                System.out.println(">> Student code already exists");
                return false;
            }
        }
        return true;
    }

    // Mảng động
    public static boolean validateStudentCode(String studentCode, List<Student> students) {
        if (studentCode == null || !STUDENT_CODE_PATTERN.matcher(studentCode).matches()) {
            System.out.println(">> Invalid student code. It must be exactly 10 alphanumeric characters.");
            return false;
        }
        for (Student student : students) {
            if (student.getStudentCode().equals(studentCode)) {
                System.out.println(">> Student code already exists");
                return false;
            }
        }
        return true;
    }

    public static boolean validateUniversity(String university) {
        if (university.isEmpty()) {
            System.out.println(">> University name cannot be empty");
            return false;
        }
        if(university.length() >= 200) {
            System.out.println(">> University name must be less than 200 characters");
            return false;
        }
        return true;
    }

    public static boolean validateStartYear(int startYear) {
        int currentYear = LocalDate.now().getYear();
        if(startYear < 1900 || startYear > currentYear) {
            System.out.println(">> The start year must be from year 1900 to current year");
            return false;
        }
        return true;
    }

    public static boolean validateGPA(double gpa) {
        if(Double.isNaN(gpa)) {
            System.out.println(">> Value must be a number");
            return false;
        }
        if (gpa < 0.0 || gpa > 10.0) {
            System.out.println(">> GPA must be between 0.0 and 10.0");
            return false;
        }
        return true;
    }
}
