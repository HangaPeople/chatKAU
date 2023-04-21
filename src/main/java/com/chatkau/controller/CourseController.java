package com.chatkau.controller;

import com.chatkau.entity.Course;
import com.chatkau.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/major/{major}")
    private List<Course> getCourseByMajor(@PathVariable String major) {
        return courseService.getCourseByMajor(major);
    }

    @GetMapping("/grade/{grade}")
    private List<Course> getCourseByGrade(@PathVariable String grade) {
        return courseService.getCourseByGrade(grade);
    }

    @GetMapping("/major/{major}/grade/{grade}")
    private List<Course> getCourseByMajorAndGrade(@PathVariable String major,
                                                  @PathVariable String grade) {
        return courseService.getCourseByMajorAndGrade(major, grade);
    }
}
