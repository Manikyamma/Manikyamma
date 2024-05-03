package com.example.university.service;

import com.example.university.model.Student;
import com.example.university.model.University;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IUniversityService  {
    List<University> all();
    University getById(String id);
    University add(University faculty);
    University update(University faculty);

    void deleteScholarship(String scholarshipId,String universityId);

    void delete(String id);
}
