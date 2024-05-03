package com.example.university.service;

import com.example.university.model.Course;
import com.example.university.model.Scholarship;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IScholarshipService {
    List<Scholarship> all();
    Scholarship getById(String id);
    Scholarship add(Scholarship faculty);
    Scholarship update(Scholarship faculty);
    void delete(String id);
}
