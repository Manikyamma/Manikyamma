package com.example.university.service.impl;

import com.example.university.model.Scholarship;
import com.example.university.model.University;
import com.example.university.repository.IScholarshipRepository;
import com.example.university.repository.IUniversityRepository;
import com.example.university.service.IUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService implements IUniversityService {
    @Autowired
    private IUniversityRepository universityRepository;

    @Autowired
    private IScholarshipRepository scholarshipRepository;


    @Override
    public List<University> all() {
        return universityRepository.findAll();
    }

    @Override
    public University getById(String id) {
        return universityRepository.findById(id).get();
    }

    @Override
    public University add(University faculty) {
        return universityRepository.save(faculty);
    }

    @Override
    public University update(University faculty) {
        return universityRepository.save(faculty);
    }

    @Override
    public void deleteScholarship(String universityId,String scholarshipId) {
        University university = universityRepository.findById(universityId).orElse(null);
        if (university != null) {
            List<Scholarship> scholarships = university.getScholarships();
            scholarships.removeIf(scholarship -> scholarship.getId().equals(scholarshipId));
            university.setScholarships(scholarships);
            scholarshipRepository.deleteById(scholarshipId);
            universityRepository.save(university);
        }
    }


    @Override
    public void delete(String id) {
            universityRepository.deleteById(id);
    }
}
