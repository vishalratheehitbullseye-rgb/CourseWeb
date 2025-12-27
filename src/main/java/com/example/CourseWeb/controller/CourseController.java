package com.example.CourseWeb.controller;

import com.example.CourseWeb.model.Course;
import com.example.CourseWeb.service.CourseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/courses")
@CrossOrigin("http://localhost:5173")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/allCourses")
    public ResponseEntity<List<Course>> getAllCourses(){
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourseBycourseId(@PathVariable Integer courseId){
        Course c = courseService.getCourseBycourseId(courseId);
        if(c == null){
            var logM = new HashMap<>();
            logM.put("Message" , "Request Denied" );
            List<String> errorList = new ArrayList<>();
            errorList.add("Could not find the given Id");
            logM.put("Errors" , errorList );
            return new ResponseEntity<>(logM, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(c, HttpStatus.OK);
    }
//@GetMapping("/{courseId}")
//public ResponseEntity<Course> getCourseBycourseId(@Valid @PathVariable Integer courseId){
//    return ResponseEntity.ok(courseService.getCourseBycourseId(courseId));
//}

    @PostMapping("/")
    public ResponseEntity<Course> saveCourse(@Valid @RequestBody Course newCourse)  {
        return ResponseEntity.ok(courseService.saveCourse(newCourse));
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<?> updateCourse(@Valid @RequestBody Course updatedCourse,
                                          @PathVariable Integer courseId){
        // complete this
        Course c = courseService.updateCourse(updatedCourse, courseId);
        if(c == null){
            var logM = new HashMap<>();
            logM.put("Message" , "Request Denied" );
            List<String> errorList = new ArrayList<>();
            errorList.add("Could not find the given Id");
            logM.put("Errors" , errorList );
            return new ResponseEntity<>(logM, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(c, HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId){
        // complete this
        if(getCourseBycourseId(courseId).getStatusCode() == HttpStatus.BAD_REQUEST){
            var logM = new HashMap<>();
            logM.put("Message" , "Request Denied" );
            List<String> errorList = new ArrayList<>();
            errorList.add("Could not find the given Id");
            logM.put("Errors" , errorList );
            return new ResponseEntity<>(logM, HttpStatus.BAD_REQUEST);
        }
        var logM = new HashMap<>();
        logM.put("Message" , "Request Accepted" );
        List<String> errorList = new ArrayList<>();
        errorList.add("Course was deleted with courseId " + courseId);
        logM.put("Status" , errorList );
        return new ResponseEntity<>(logM, HttpStatus.OK);
    }


}
