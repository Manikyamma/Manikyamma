package com.example.university.controller;

import com.example.university.model.Student;
import com.example.university.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private IStudentService studentService;


    @PostMapping("/studentLogin")
    private ResponseEntity<?> login(@RequestBody Student user) {
        HashMap<String, String> res = new HashMap<>();
        try {
            if (user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("admin")) {
                res.put("msg", "Admin login successfully");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
            if (user.getEmail().equals("") && user.getPassword().equals("")) {
                res.put("msg", "please fill all the fields");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
            Student checkUser = studentService.getByEmail(user.getEmail());
            if (checkUser == null) {
                res.put("msg", "User not found");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            } else if(checkUser.getPassword().equals(user.getPassword())) {

                res.put("msg", "user login successfully");
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                    res.put("msg", "Password not matched");
                    return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/studentRegister")
    public ResponseEntity<?> register(@RequestBody Student user) {
        HashMap<String, String> res = new HashMap<>();
        try {
            if (user.getEmail() == null || user.getEmail().isEmpty() ||
                    user.getName() == null || user.getName().isEmpty() ||
                    user.getPassword() == null || user.getPassword().isEmpty()) {
                res.put("msg", "Please fill all the fields");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            boolean checkUser = studentService.getByEmail(user.getEmail()) != null;
            if (checkUser) {
                res.put("msg", "Student already exists");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            } else {
                studentService.add(user);
                res.put("msg", "Student added successfully");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/allStudents/{email}")
    private ResponseEntity<?> allUsers(@PathVariable String email) {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(studentService.all().stream().filter(i -> i.getEmail().equals(email)).toList(), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/student")
    private ResponseEntity<?> getAll() {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(studentService.all(), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/studentByEmail/{email}")
    private ResponseEntity<?> getByEmail(@PathVariable String email) {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(studentService.getByEmail(email), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/student")
    private ResponseEntity<?> addUser(@RequestBody Student user) {
        HashMap<String, String> res = new HashMap<>();
        try {
            int count = 0;

            return new ResponseEntity<>(studentService.add(user), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/password")
    private ResponseEntity<?> password(@RequestBody Student student) {
        HashMap<String, String> res = new HashMap<>();
        try {
            Student student1=studentService.getByEmail(student.getEmail());

            student1.setPassword(student.getPassword());
            return new ResponseEntity<>(studentService.update(student1), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/student/{id}")
    private ResponseEntity<?> updateUser(@RequestBody Student user, @PathVariable String id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            Student u = studentService.getById(id);
            if (user.getName() != null) {
                u.setName(user.getName());
            } else {
                u.setName(u.getName());
            }
            if (user.getEmail() != null) {
                u.setEmail(user.getEmail());
            } else {
                u.setEmail(u.getEmail());
            }
            if (user.getPassword() != null) {
                u.setPassword(user.getPassword());
            } else {
                u.setPassword(u.getPassword());
            }
            return new ResponseEntity<>(studentService.update(u), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/student/{id}")
    private ResponseEntity<?> deleteUser(@PathVariable String id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            studentService.delete(id);
            res.put("msg", "User deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
