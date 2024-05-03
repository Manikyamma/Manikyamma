package com.example.university.repository;

import com.example.university.model.University;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUniversityRepository extends MongoRepository<University,String> {
}
