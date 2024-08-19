package service;

import entity.Student;
import menu.MenuUpdateStudent;
import utils.InputHandler;

import java.util.*;

public class StaticArray implements StudentService {
    Scanner scanner = new Scanner(System.in);
    private final int MAX_STUDENTS = 100;
    private final Student[] students = new Student[MAX_STUDENTS];
    private static int count = 0;

    private boolean isEmpty() {
        if (count == 0) {
            System.out.println(">>List students are empty\n");
            return true;
        }
        return false;
    }

    @Override
    public void create() {
        if (count < MAX_STUDENTS) {
            System.out.println("\n============= Create new student ===============");
            students[count++] = InputHandler.inputStudent(students);
            System.out.println(">>Create student successfully");
            getAll();
        } else {
            System.out.println(">>Cannot add more student\n");
        }
    }

    @Override
    public void getAll() {
        System.out.println("\n================================================================== LIST STUDENTS ==================================================================");
        isEmpty();
        for (Student student : students) {
            if (student != null) {
                System.out.println(student);
            }
        }
        System.out.println();
    }

    @Override
    public void findById() {
        if (isEmpty()) return;

        Long id = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter student id: ");
            try {
                id = scanner.nextLong();
                scanner.nextLine();

                Long finalId1 = id;
                validInput = Arrays.stream(students)
                        .anyMatch(student -> student != null && Objects.equals(student.getId(), finalId1));

                if (!validInput) {
                    System.out.println(">> Cannot find student with id: " + id + ". Please try again.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println(">> Invalid input. Please enter a numeric student id.\n");
                scanner.nextLine(); // Clear the buffer to avoid infinite loop
            }
        }

        Long finalId = id;
        Arrays.stream(students)
                .filter(student -> student != null && Objects.equals(student.getId(), finalId))
                .findFirst()
                .ifPresent(student -> System.out.println(student + "\n"));
    }

    @Override
    public void update() {
        if (isEmpty()) return;

        Long id = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter student id to update: ");
            try {
                id = scanner.nextLong();
                scanner.nextLine();

                Long finalId1 = id;
                validInput = Arrays.stream(students)
                        .anyMatch(student -> student != null && Objects.equals(student.getId(), finalId1));
            } catch (InputMismatchException e) {
                System.out.println(">> Invalid ID, please enter a valid numerical ID.");
                scanner.nextLine();
            }
        }

        Long finalId = id;
        Optional<Student> studentOpt = Arrays.stream(students)
                .filter(student -> Objects.equals(student.getId(), finalId))
                .findFirst();

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            int choice;
            do {
                try {
                    choice = MenuUpdateStudent.getChoice(id, scanner);
                } catch (InputMismatchException e) {
                    System.out.println(">> Invalid option, please try again.");
                    scanner.nextLine();
                    choice = -1;
                }

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
            } while (choice != 0);
            System.out.println("\n==================== Student after update ====================");
            System.out.println(student);
            System.out.println();
        } else {
            System.out.println(">> Cannot find student with id: " + id + "\n");
        }
    }

    @Override
    public void remove() {
        if (isEmpty()) return;

        Long id = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter student id to update: ");
            try {
                id = scanner.nextLong();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println(">> Invalid ID, please enter a valid numerical ID.");
                scanner.nextLine();
            }

        }

        Long finalId = id;
        Optional<Student> studentOpt = Arrays.stream(students)
                .filter(student -> Objects.equals(student.getId(), finalId))
                .findFirst();

        if (studentOpt.isPresent()) {
            for (int i = 0; i < count; i++) {
                if (Objects.equals(students[i].getId(), id)) {
                    // Shift elements left
                    for (int j = i; j < count - 1; j++) {
                        students[j] = students[j + 1];
                    }
                    // Nullify the last element
                    students[count - 1] = null;
                    count--;
                    System.out.println(">> Remove student successfully");
                    getAll();
                    return;
                }
            }
        }
        System.out.println(">> Cannot find student with id: " + id);
    }
}
