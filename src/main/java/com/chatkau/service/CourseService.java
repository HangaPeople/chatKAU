package com.chatkau.service;

import com.chatkau.entity.Course;
import com.chatkau.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getCourseByMajor(String major) {
        return courseRepository.findByMajor(major);
    }

    public List<Course> getCourseByGrade(String grade) {
        return courseRepository.findByGrade(grade);
    }

    public List<Course> getCourseByMajorAndGrade(String major, String grade) {
        return courseRepository.findByMajorAndGrade(major, grade);
    }
}
