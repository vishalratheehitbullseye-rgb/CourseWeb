package com.example.CourseWeb.repo;

import com.example.CourseWeb.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {

    Course getCourseByCourseId(Integer courseId);

    @Modifying
    @Transactional
    @Query("""
        UPDATE Course c
        SET c.courseName = :courseName,
            c.courseDescription = :courseDescription,
            c.tags = :tags,
            c.duration = :duration,
            c.listedOn = :listedOn,
            c.price = :price
        WHERE c.courseId = :courseId
    """)
    int updateCourseByCourseId(
            @Param("courseId") Integer courseId,
            @Param("courseName") String courseName,
            @Param("courseDescription") String courseDescription,
            @Param("tags") String tags,
            @Param("duration") Integer duration,
            @Param("listedOn") LocalDate listedOn,
            @Param("price") Double price
    );

    boolean deleteBycourseId(Integer courseId);
}
