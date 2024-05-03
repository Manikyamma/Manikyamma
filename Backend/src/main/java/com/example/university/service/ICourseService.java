package com.example.university.service;

import com.example.university.model.Course;
import com.example.university.model.Student;

import java.util.List;

public interface ICourseService {

    List<Course> all();
    Course getById(String id);
    Course add(Course faculty);
    Course update(Course faculty);
    void delete(String id);
}
