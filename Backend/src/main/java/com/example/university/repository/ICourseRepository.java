package com.example.university.repository;

import com.example.university.model.Course;
import com.example.university.model.Stream;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends MongoRepository<Course, String> {
}
