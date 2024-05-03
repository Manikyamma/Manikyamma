package com.example.university.repository;

import com.example.university.model.Scholarship;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IScholarshipRepository extends MongoRepository<Scholarship,String> {
}
