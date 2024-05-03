package com.example.university.repository;

import com.example.university.model.Stream;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStreamRepository extends MongoRepository<Stream,String> {


}
