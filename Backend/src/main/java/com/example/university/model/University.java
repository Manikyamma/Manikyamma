package com.example.university.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class University {
    @Id
    private String id;
    private String name;
    private String location;
    private String rank;
    Set<Stream> streams= new HashSet<>();
    private List<Scholarship> scholarships = new ArrayList<>();
}
