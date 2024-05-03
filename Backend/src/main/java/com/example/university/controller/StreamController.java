package com.example.university.controller;

import com.example.university.model.Course;
import com.example.university.model.Stream;
import com.example.university.model.University;
import com.example.university.service.ICourseService;
import com.example.university.service.IStreamService;
import com.example.university.service.IUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class StreamController {

    @Autowired
    private IStreamService streamService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IUniversityService universityService;

    @GetMapping("/stream")
    private ResponseEntity<?> addUser() {
        HashMap<String, String> res = new HashMap<>();
        try {
//            List<Object> streams = streamService.getUniqueStreams();
                List<Stream> streams= streamService.all();
            return new ResponseEntity<>(streams, HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stream/{id}")
    private ResponseEntity<?> getBy(@PathVariable String id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(streamService.getById(id),HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getStream/{id}")
    private ResponseEntity<?> getByStream(@PathVariable String id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            University university=universityService.getById(id);
            return new ResponseEntity<>(university.getStreams(),HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


        @PostMapping("/stream")
    private ResponseEntity<?> addStream(@RequestBody Stream user) {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(streamService.add(user), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/addCourse/{id}/{cId}")
    private ResponseEntity<?> addUser(@PathVariable String id,@PathVariable String cId) {
        HashMap<String, String> res = new HashMap<>();
        try {
            Stream stream=streamService.getById(id);
            Course course = courseService.getById(cId);
            stream.getCourses().add(course);
            return new ResponseEntity<>(streamService.update(stream), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/stream/{id}")
    private ResponseEntity<?> addUser(@PathVariable String id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            streamService.delete(id);
            res.put("msg","Course deleted successfully");
            return new ResponseEntity<>(res,HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
