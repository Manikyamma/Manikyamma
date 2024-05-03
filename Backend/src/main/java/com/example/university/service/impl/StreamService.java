package com.example.university.service.impl;

import com.example.university.model.Stream;
import com.example.university.repository.IStreamRepository;
import com.example.university.service.IStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Service
public class StreamService implements IStreamService {
    @Autowired

    private IStreamRepository streamRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Stream> all() {
        return streamRepository.findAll();
    }

    @Override
    public Stream getById(String id) {
        return streamRepository.findById(id).get();
    }

    @Override
    public Stream add(Stream faculty) {
        return streamRepository.save(faculty);
    }

    @Override
    public Stream update(Stream faculty) {
        return streamRepository.save(faculty);
    }

    @Override
    public void delete(String id) {
        streamRepository.deleteById(id);
    }

    @Override
    public List<Object> getUniqueStreams() {
        var groupByName = group("name");
        var aggregation =  newAggregation(groupByName);
        return mongoTemplate.aggregate(aggregation , "stream", Object.class).getMappedResults();
    }


}
