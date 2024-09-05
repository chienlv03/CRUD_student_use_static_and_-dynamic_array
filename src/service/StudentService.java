package service;

import entity.Student;

import java.util.Optional;

public interface StudentService {
    void create(Student student);
    Optional<Student> findById(Long id);
    void remove(Long id);
    void update(Long id);
    void getAll();
}
