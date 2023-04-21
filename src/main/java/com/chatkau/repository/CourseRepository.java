package com.chatkau.repository;

import com.chatkau.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByMajor(String major);
    List<Course> findByGrade(String grade);
    List<Course> findByMajorAndGrade(String major, String grade);
}
