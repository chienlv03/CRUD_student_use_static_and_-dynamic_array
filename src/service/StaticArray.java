package service;

import entity.Student;
import utils.InputHandler;
import utils.Messages;

import java.util.*;
import static menu.HandleUpdateStudent.*;

public class StaticArray implements StudentService {
    private static final int MAX_STUDENTS = 100;
    private static final Student[] students = new Student[MAX_STUDENTS];
    private static int count = 0;

    public static boolean isEmpty() {
        return count == 0;
    }

    public static Student[] getStudents() {
        return Arrays.copyOf(students, count);
    }


    @Override
    public void create(Student student) {
        if (count < MAX_STUDENTS) {
            students[count++] = student;
            System.out.println(Messages.STUDENT_CREATE);
        } else {
            System.out.println(">> Cannot add more students, array is full.\n");
        }
    }

    @Override
    public void getAll() {
        Arrays.stream(students)
                .filter(Objects::nonNull)
                .forEach(System.out::println);
        System.out.println();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Arrays.stream(students)
                .filter(student -> student != null && Objects.equals(student.getId(), id))
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
                    case 1 -> student.setStudentCode(InputHandler.inputStudentCode(StaticArray.getStudents()));
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


    //////////////////////////////////////////////////////////////////
    @Override
    public void remove(Long id) {
        Optional<Student> studentOpt = findById(id);

        if (studentOpt.isPresent()) {
            int index = Arrays.asList(students).indexOf(studentOpt.get());
            System.arraycopy(students, index + 1, students, index, count - index - 1);
            students[--count] = null;
            System.out.println(Messages.STUDENT_REMOVED);
        } else {
            System.out.println(Messages.ERROR_NOT_FOUND_ID + id);
        }
    }
}
