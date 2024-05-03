package com.example.university.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Scholarship {
    @Id
    private String id;
    private String university;
    private String eligibility;
    private String amount;
    private String rank;
    private String description;

}
