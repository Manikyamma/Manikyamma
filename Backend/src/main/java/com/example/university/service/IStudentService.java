package com.example.university.service;

import com.example.university.model.Student;

import java.util.List;

public interface IStudentService {

    List<Student> all();
    Student getById(String id);
    Student add(Student faculty);
    Student update(Student faculty);
    void delete(String id);
    Student getByEmailAndPassword(String email,String password);

    Student getByEmail(String email);
}
