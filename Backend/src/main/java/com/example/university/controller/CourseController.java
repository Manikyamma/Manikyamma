package com.example.university.controller;

import com.example.university.model.Course;
import com.example.university.model.Stream;
import com.example.university.model.Student;
import com.example.university.model.University;
import com.example.university.service.ICourseService;
import com.example.university.service.IStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class CourseController {
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IStreamService streamService;

    @GetMapping("/course")
    private ResponseEntity<?> addUser() {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(courseService.all(), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/course/{id}")
    private ResponseEntity<?> getById(@PathVariable String id) {
        HashMap<String, String> res = new HashMap<>();
        try {

            return new ResponseEntity<>(courseService.getById(id),HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        @GetMapping("/getCourse/{id}")
    private ResponseEntity<?> getBy(@PathVariable String id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            Stream stream= streamService.getById(id);
            return new ResponseEntity<>(stream.getCourses(),HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/course")
    private ResponseEntity<?> addUser(@RequestBody Course user) {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(courseService.add(user), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/course/{id}")
    private ResponseEntity<?> addUser(@PathVariable String id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            courseService.delete(id);
            res.put("msg","Course deleted successfully");
            return new ResponseEntity<>(res,HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
