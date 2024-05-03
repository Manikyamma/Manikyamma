package com.example.university.service;

import com.example.university.model.Stream;
import com.example.university.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


public interface IStreamService {

    List<Stream> all();
    Stream getById(String id);
    Stream add(Stream faculty);
    Stream update(Stream faculty);
    void delete(String id);

    List<Object> getUniqueStreams();

}
