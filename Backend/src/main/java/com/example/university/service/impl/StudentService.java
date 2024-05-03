package com.example.university.service.impl;

import com.example.university.model.Student;
import com.example.university.repository.IStudentRepository;
import com.example.university.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;
    @Override
    public List<Student> all() {
        return studentRepository.findAll();
    }

    @Override
    public Student getById(String id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student add(Student faculty) {
        return studentRepository.save(faculty);
    }

    @Override
    public Student update(Student faculty) {
        return studentRepository.save(faculty);
    }

    @Override
    public void delete(String id) {
            studentRepository.deleteById(id);
    }

    @Override
    public Student getByEmailAndPassword(String email, String password) {
        return studentRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Student getByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
}
