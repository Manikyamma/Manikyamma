package com.example.university.repository;

import com.example.university.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends MongoRepository<Student,String> {
    Student findByEmailAndPassword(String email,String password);
    Student findByEmail(String email);
}
