package service;

import entity.Student;
import menu.MenuUpdateStudent;
import utils.InputHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DynamicArray implements StudentService {
    private static final List<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    private boolean isEmpty() {
        if (students.isEmpty()) {
            System.out.println(">> The student list is empty.\n");
            return true;
        }
        return false;
    }

    @Override
    public void create() {
        students.add(InputHandler.inputStudent(students));
        System.out.println(">> Student created successfully!");
        getAll();
    }

    @Override
    public void getAll() {
        System.out.println("\n============= LIST OF STUDENTS =============");
        if (students.isEmpty()) {
            System.out.println(">> The student list is empty.\n");
            return;
        }
        students.forEach(System.out::println);
        System.out.println();
    }

    @Override
    public void findById() {
        if (isEmpty()) return;

        Long id = null;
        boolean isValidId = false;

        while (!isValidId) {
            System.out.print("Enter student id: ");
            try {
                id = scanner.nextLong();
                scanner.nextLine();

                Long finalId1 = id;
                isValidId = students.stream()
                        .anyMatch(student -> Objects.equals(student.getId(), finalId1));

                if (!isValidId) {
                    System.out.println(">> Cannot find student with id: " + id + ". Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println(">> Invalid input. Please enter a numeric student id.");
                scanner.nextLine();
            }
        }

        Long finalId = id;
        students.stream()
                .filter(student -> Objects.equals(student.getId(), finalId))
                .findFirst()
                .ifPresent(student -> System.out.println(student + "\n"));
    }


    @Override
    public void update() {
        if (isEmpty()) return;

        Long id = null;
        boolean validInput = false;

        // Loop to validate the input ID
        while (!validInput) {
            System.out.print("Enter student id to update: ");
            try {
                id = scanner.nextLong();
                scanner.nextLine();

                Long finalId1 = id;
                validInput = students.stream()
                        .anyMatch(student -> student != null && Objects.equals(student.getId(), finalId1));
                if (!validInput) System.out.println("Cannot find student with id: " + id + ". Please try again.");
            } catch (InputMismatchException e) {
                System.out.println(">> Invalid ID, please enter a valid numerical ID.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        Long finalId = id;
        Optional<Student> studentOpt = students.stream()
                .filter(student -> Objects.equals(student.getId(), finalId))
                .findFirst();

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            int choice;
            do {
                try {
                    choice = MenuUpdateStudent.getChoice(id, scanner);
                    switch (choice) {
                        case 1 -> student.setStudentCode(InputHandler.inputStudentCode(students));
                        case 2 -> student.setName(InputHandler.inputName());
                        case 3 -> student.setDob(InputHandler.inputDateOfBirth());
                        case 4 -> student.setAddress(InputHandler.inputAddress());
                        case 5 -> student.setHeight(InputHandler.inputHeight());
                        case 6 -> student.setWeight(InputHandler.inputWeight());
                        case 7 -> student.setUniversity(InputHandler.inputUniversity());
                        case 8 -> student.setStartYear(InputHandler.inputStartYear());
                        case 9 -> student.setGpa(InputHandler.inputGPA());
                        case 0 -> System.out.println(">> Changes saved.");
                        default -> System.out.println(">> Invalid option, please try again.");
                    }
                    if (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6 || choice == 7 || choice == 8 || choice == 9) {
                        System.out.println(">> Update student successfully");
                    }
                } catch (InputMismatchException e) {
                    System.out.println(">> Invalid option, please try again.");
                    scanner.nextLine(); // Clear the invalid input
                    choice = -1;
                }
            } while (choice != 0);
//            System.out.println(">> Update student successfully!");
            System.out.println("\n======================================== Student after update ========================================");
            System.out.println(student);
            System.out.println();
        } else {
            System.out.println(">> Student not found with ID: " + id);
        }
    }


    @Override
    public void remove() {
        if (isEmpty()) return;

        Long id = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Enter student id to update: ");
            try {
                id = scanner.nextLong();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println(">> Invalid ID, please enter a valid numerical ID.");
                scanner.nextLine();
            }
        }

        Long finalId = id;
        Optional<Student> studentOpt = students.stream()
                .filter(student -> Objects.equals(student.getId(), finalId))
                .findFirst();

        if (studentOpt.isPresent()) {
            students.remove(studentOpt.get());
            System.out.println(">> Student removed successfully.\n");
            getAll();
        } else {
            System.out.println(">> Student not found with ID: " + id);
        }
    }

    public static void displayAcademicPercentage() {
        if (students.isEmpty()) {
            System.out.println(">> The student list is empty.\n");
            return;
        }

        List<String> academicLevels = Arrays.asList("Xuất sắc", "Giỏi", "Khá", "Trung bình", "Yếu", "Kém");

        // Tính toán số lượng sinh viên ở từng mức học lực
        Map<String, Long> academicCounts = students.stream()
                .collect(Collectors.groupingBy(student -> student.getAcademicAchievement().getValue(), Collectors.counting()));

        // Tổng số lượng sinh viên
        long totalStudents = students.size();

        // Tính tỷ lệ phần trăm học lực
        List<Map.Entry<String, Long>> sortedAcademicPercentage = academicCounts.entrySet().stream()
                .sorted(Comparator
                        // Sắp xếp theo tỷ lệ phần trăm giảm dần
                        .comparing((Map.Entry<String, Long> entry) -> (double) entry.getValue() / totalStudents * 100)
                        .reversed()
                        // Nếu tỷ lệ phần trăm bằng nhau, sắp xếp theo thứ tự học lực từ "Xuất sắc" đến "Kém"
                        .thenComparing(entry -> academicLevels.indexOf(entry.getKey())))
                .toList();

        // Hiển thị kết quả
        System.out.println("\n============= ACADEMIC PERFORMANCE PERCENTAGE =============");
        for (Map.Entry<String, Long> entry : sortedAcademicPercentage) {
            double percentage = (double) entry.getValue() / totalStudents * 100;
            System.out.printf("                 %s - %.2f%%\n", entry.getKey(), percentage);
        }
    }

    public static void displayAverageScorePercentage() {
        if (students.isEmpty()) {
            System.out.println(">> The student list is empty.\n");
            return;
        }

        long studentCount = students.size();

        // đếm số sinh viên có cùng điểm gpa
        Map<Double, Long> gpaCounts = students.stream()
                .collect(Collectors.groupingBy(Student::getGpa, Collectors.counting()));

        Map<Double, Double> gpaPercentages = gpaCounts.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> (double) entry.getValue() / studentCount * 100
                        ));

        List<Map.Entry<Double, Double>> sortedGpaPercentage = gpaPercentages.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .toList();

        System.out.println("\n============= AVERAGE SCORE PERCENTAGE =============");
        for (Map.Entry<Double, Double> entry : sortedGpaPercentage) {
            System.out.printf("                 %.1f : %.2f%%\n", entry.getKey(), entry.getValue());
        }
    }

    public static void displayStudentsByAcademicAchievement() {
        if (students.isEmpty()) {
            System.out.println(">> The student list is empty.\n");
            return;
        }

        int choice;
        String selectedAchievement = "";
        do {
            System.out.println("\n=========== Select Academic Achievement ===========");
            System.out.println("|-----------------------------------------------------|");
            System.out.println("| 1. Xuất sắc                         2. Giỏi         |");
            System.out.println("| 3. Khá                              4. Trung bình   |");
            System.out.println("| 5. Yếu                              6. Kém          |");
            System.out.println("|                   0. Thoát                          |");
            System.out.println("|-----------------------------------------------------|");
            System.out.print("Please select: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> selectedAchievement = "Xuất sắc";
                    case 2 -> selectedAchievement = "Giỏi";
                    case 3 -> selectedAchievement = "Khá";
                    case 4 -> selectedAchievement = "Trung bình";
                    case 5 -> selectedAchievement = "Yếu";
                    case 6 -> selectedAchievement = "Kém";
                    case 0 -> System.out.println("Exiting...");
                    default -> System.out.println(">> invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println(">> Invalid option. Please try again.");
                scanner.nextLine();
                choice = -1;
            }

            String finalSelectedAchievement = selectedAchievement;
            List<Student> filteredStudents = students.stream()
                    .filter(student -> student.getAcademicAchievement().getValue().equals(finalSelectedAchievement))
                    .toList();

            if (filteredStudents.isEmpty()) {
                System.out.println(">> No students found with academic achievement: " + selectedAchievement);
            } else {
                System.out.println("\n====================== List of students with academic achievement " +
                        selectedAchievement + " ======================");
                filteredStudents.forEach(System.out::println);
            }

        } while (choice != 0);
    }

    public static void writeStudentsToFile() {
        if (students.isEmpty()) {
            System.out.println(">> The student list is empty.\n");
            return;
        }

        System.out.print("Enter file name to save: ");
        String fileName = scanner.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
            System.out.println(">> Student list has been written to file: " + fileName);
        } catch (IOException e) {
            System.err.println(">> An error occurred while writing to file: " + e.getMessage());
        }
    }
}