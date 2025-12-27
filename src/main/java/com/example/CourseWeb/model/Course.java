package com.example.CourseWeb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer courseId;

    @NotBlank
    String courseName;
    @Column(nullable = false)
    String courseDescription;
    String tags;
    Integer duration;
    @Column(nullable = false)
    LocalDate listedOn;
    @Column(nullable = false)
    Double price;
}
