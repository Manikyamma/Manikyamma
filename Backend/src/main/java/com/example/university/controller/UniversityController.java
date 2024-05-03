package com.example.university.controller;

import com.example.university.model.Scholarship;
import com.example.university.model.Stream;
import com.example.university.model.University;
import com.example.university.service.IScholarshipService;
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
public class UniversityController {
    @Autowired
    private IUniversityService universityService;
    @Autowired
    private IStreamService streamService;
    @Autowired
    private IScholarshipService scholarshipService;

    @GetMapping("/university/{id}")
    private ResponseEntity<?> getById(@PathVariable String id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(universityService.getById(id),HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getByLocation/{location}")
    private ResponseEntity<?> getByLocation(@PathVariable String location) {
        HashMap<String, String> res = new HashMap<>();
        try {
            List<University> universities= universityService.all();
            Set<University> universitySet= new HashSet<>();
            for(University u:universities){
                if(u.getLocation().equals(location)){
                    universitySet.add(u);
                }
            }
            return new ResponseEntity<>(universitySet,HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/locations")
    private ResponseEntity<?> all() {
        HashMap<String, String> res = new HashMap<>();
        try {
            Set<String> locations= new HashSet<>();
            List<University>universities=universityService.all();
            for(University u:universities){
                locations.add(u.getLocation());
            }
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     
    @GetMapping("/university")
    private ResponseEntity<?> addUser() {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(universityService.all(), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/scholarshipData")
    public ResponseEntity<List<Scholarship>> ScholarshipData(@RequestBody HashMap<String, String[]> body) {
        List<Scholarship> scholarships = new ArrayList<>();
        try {
            String[] universityIds = body.get("universities");
            if (universityIds == null) {
                return ResponseEntity.badRequest().build();
            }

            for (String u : universityIds) {
                University university = universityService.getById(u);
                if (university != null && university.getScholarships() != null) {
                    scholarships.addAll(university.getScholarships());
                } else {
                    // Log or handle the case where university or its scholarships are null
                    return ResponseEntity.notFound().build();
                }
            }

            return new ResponseEntity<>(scholarships, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/university")
    private ResponseEntity<?> addUser(@RequestBody University user) {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(universityService.add(user), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*@PostMapping("/addScholarship/{id}/{sId}")
    private ResponseEntity<?> add(@PathVariable String id,@PathVariable String sId) {
        HashMap<String, String> res = new HashMap<>();
        try {
            University university=universityService.getById(id);
            List<Scholarship> stream=scholarshipService.all();
            university.getScholarships().add(stream);
            return new ResponseEntity<>(universityService.update(university), HttpStatus.OK);

        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @PostMapping("/addStream/{id}/{sId}")
    private ResponseEntity<?> addUser(@PathVariable String id,@PathVariable String sId) {
        HashMap<String, String> res = new HashMap<>();
        try {
            University university=universityService.getById(id);
            Stream stream=streamService.getById(sId);
            university.getStreams().add(stream);
            return new ResponseEntity<>(universityService.update(university), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/scholarship/{scholarshipId}/{universityId}")
    private ResponseEntity<?> deleteScholarshipByScholarshipId(@PathVariable String scholarshipId,@PathVariable String universityId)
    {
        HashMap<String,Object> res = new HashMap<>();
        try
        {
            universityService.deleteScholarship(scholarshipId,universityId);
            res.put("success",true);
            res.put("msg","Scholarship Deleted Successfully");
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        catch(Exception e)
        {
            res.put("success",false);
            res.put("msg","Failed to delete the scholarship by provided scholarship id is"+scholarshipId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }


    @DeleteMapping("/university/{id}")
    private ResponseEntity<?> addUser(@PathVariable String id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            universityService.delete(id);
            res.put("msg","University deleted successfully");
            return new ResponseEntity<>(res,HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
