package com.example.CourseWeb.service;

import com.example.CourseWeb.model.Course;
import com.example.CourseWeb.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepo courseRepo;

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course getCourseBycourseId(Integer courseId) {
        return courseRepo.getCourseByCourseId(courseId);
    }

    public Course saveCourse(Course newCourse)  {
        return courseRepo.save(newCourse);
    }

    public Course updateCourse(Course updatedCourse, Integer courseId) {
        Course existing = courseRepo.getCourseByCourseId(courseId);
        if(existing == null) return null;
        int updatedCount = courseRepo.updateCourseByCourseId(
                courseId,
                updatedCourse.getCourseName(),
                updatedCourse.getCourseDescription(),
                updatedCourse.getTags(),
                updatedCourse.getDuration(),
                updatedCourse.getListedOn(),
                updatedCourse.getPrice()
        );
        if(updatedCount == 0) return null;
        existing.setCourseName(updatedCourse.getCourseName());
        existing.setCourseDescription(updatedCourse.getCourseDescription());
        existing.setTags(updatedCourse.getTags());
        existing.setDuration(updatedCourse.getDuration());
        existing.setListedOn(updatedCourse.getListedOn());
        existing.setPrice(updatedCourse.getPrice());
        return existing;
    }

    public boolean deleteCourse(Integer courseId) {
        return courseRepo.deleteBycourseId(courseId);
    }
}
