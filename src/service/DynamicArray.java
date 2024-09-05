package service;

import entity.Student;
import utils.InputHandler;
import utils.Messages;

import static menu.HandleUpdateStudent.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;



public class DynamicArray implements StudentService {
    private static final List<Student> students = new ArrayList<>();

    public static boolean isEmpty() {
        return students.isEmpty();
    }

    @Override
    public void create(Student student) {
        students.add(student);
        System.out.println(Messages.STUDENT_CREATE);
    }

    @Override
    public void getAll() {
        students.forEach(System.out::println);
        System.out.println();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return students.stream()
                .filter(student -> Objects.equals(student.getId(), id))
                .findFirst();
    }

    @Override
    public void update(Long id) {
        Optional<Student> studentOpt = findById(id);

        if (studentOpt.isPresent()) {
            Scanner scanner = new Scanner(System.in);
            Student student = studentOpt.get();
            while (true) {
                int choice = getValidChoice(scanner, id);

                if (choice == 0) {
                    break;
                }
                switch (choice) {
                    case 1 -> student.setStudentCode(InputHandler.inputStudentCode(DynamicArray.getStudents()));
                    case 2 -> student.setName(InputHandler.inputName());
                    case 3 -> student.setDob(InputHandler.inputDateOfBirth());
                    case 4 -> student.setAddress(InputHandler.inputAddress());
                    case 5 -> student.setHeight(InputHandler.inputHeight());
                    case 6 -> student.setWeight(InputHandler.inputWeight());
                    case 7 -> student.setUniversity(InputHandler.inputUniversity());
                    case 8 -> student.setStartYear(InputHandler.inputStartYear());
                    case 9 -> student.setGpa(InputHandler.inputGPA());
                }
                System.out.println(Messages.STUDENT_UPDATE);
            }
            System.out.println("==================== Student after update ====================");
            System.out.println(student);
            System.out.println();
        } else {
            System.out.println(Messages.ERROR_NOT_FOUND_ID + id + "\n");
        }
    }

    public static void displayAcademicPercentage() {

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

        System.out.println("\n============= ACADEMIC PERFORMANCE PERCENTAGE =============");
        for (Map.Entry<String, Long> entry : sortedAcademicPercentage) {
            double percentage = (double) entry.getValue() / totalStudents * 100;
            System.out.printf("                 %s - %.2f%%\n", entry.getKey(), percentage);
        }
    }

    public static void displayAverageScorePercentage() {

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
            System.out.printf("               GPA: %.1f - %.2f%%\n", entry.getKey(), entry.getValue());
        }
    }

    public static void displayStudentsByAcademicAchievement(String selectedAchievement) {
        if (selectedAchievement.equals("0")) {
            System.out.println("Exiting...");
            return;
        }

        List<Student> filteredStudents = students.stream()
                .filter(student -> student.getAcademicAchievement().getValue().equals(selectedAchievement))
                .toList();

        if (filteredStudents.isEmpty()) {
            System.out.println(">> No students found with academic achievement: " + selectedAchievement);
        } else {
            System.out.println("\n====================== List of students with academic achievement " +
                    selectedAchievement + " ======================");
            filteredStudents.forEach(System.out::println);
        }
    }



    public static void writeStudentsToFile(String fileName) {
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

    @Override
    public void remove(Long id) {
        Optional<Student> studentOpt = findById(id);

        if (studentOpt.isPresent()) {
            students.remove(studentOpt.get());
            System.out.println(Messages.STUDENT_REMOVED);
        } else {
            System.out.println(Messages.ERROR_NOT_FOUND_ID + id);
        }
    }

    public static List<Student> getStudents() {
        return List.copyOf(students);
    }
}
