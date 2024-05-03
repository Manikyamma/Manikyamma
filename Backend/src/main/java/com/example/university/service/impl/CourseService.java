package com.example.university.service.impl;

import com.example.university.model.Course;
import com.example.university.repository.ICourseRepository;
import com.example.university.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public List<Course> all() {
        return courseRepository.findAll();
    }

    @Override
    public Course getById(String id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Course add(Course faculty) {
        return courseRepository.save(faculty);
    }

    @Override
    public Course update(Course faculty) {
        return courseRepository.save(faculty);
    }

    @Override
    public void delete(String id) {
        courseRepository.deleteById(id);
    }
}
