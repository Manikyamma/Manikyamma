package com.example.university.controller;

import com.example.university.model.Course;
import com.example.university.model.Scholarship;
import com.example.university.model.University;
import com.example.university.service.IScholarshipService;
import com.example.university.service.IUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ScholarshipController {
    @Autowired
    private IScholarshipService scholarshipService;

    @Autowired
    private IUniversityService universityService;

    @PostMapping("/scholarship/{universityId}")
    public ResponseEntity<?> addScholarshipToUniversity(@PathVariable String universityId,@RequestBody Scholarship scholarship) {
        HashMap<String, Object> res = new HashMap<>();
        try {
            University university = universityService.getById(universityId);
            if (university == null) {
                res.put("success", false);
                res.put("msg", "University not found for ID: " + universityId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }

            Scholarship addedScholarship = scholarshipService.add(scholarship);
            university.getScholarships().add(addedScholarship);
            universityService.update(university);

            res.put("success", true);
            res.put("msg", "Scholarship details successfully assigned to the university");
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("success", false);
            res.put("msg", "Failed to assign scholarship: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @GetMapping("/scholarship")
    private ResponseEntity<?> addUser() {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(scholarshipService.all(), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/scholarship/{id}")
    private ResponseEntity<?> getById(@PathVariable String id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(scholarshipService.getById(id),HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/scholarship/{id}")
    private ResponseEntity<?> deleteScholarshipById(@PathVariable String id) {
        HashMap<String,Object> res = new HashMap<>();
        try {
            scholarshipService.delete(id);
            res.put("success",true);
            res.put("msg","Scholarship deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            res.put("success",false);
            res.put("msg","Failed to delete the scholarship by provided id is"+id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

}
