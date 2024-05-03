package com.example.university.service.impl;

import com.example.university.model.Scholarship;
import com.example.university.repository.IScholarshipRepository;
import com.example.university.service.IScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScholarshipService implements IScholarshipService {

    @Autowired
    private IScholarshipRepository scholarshipRepository;

    @Override
    public List<Scholarship> all() {
        return scholarshipRepository.findAll();
    }

    @Override
    public Scholarship getById(String id) {
        return scholarshipRepository.findById(id).get();
    }

    @Override
    public Scholarship add(Scholarship faculty) {
        return scholarshipRepository.save(faculty);
    }

    @Override
    public Scholarship update(Scholarship faculty) {
        return scholarshipRepository.save(faculty);
    }

    @Override
    public void delete(String id) {
            scholarshipRepository.deleteById(id);
    }
}
