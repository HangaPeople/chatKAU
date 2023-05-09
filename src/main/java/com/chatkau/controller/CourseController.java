package com.chatkau.controller;

import com.chatkau.entity.Course;
import com.chatkau.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "전공에 해당하는 과목 조회", description = "전공을 입력받아 전공에 해당하는 모든 과목을 조회한다.")
    @GetMapping("/major/{major}")
    private List<Course> getCourseByMajor(@PathVariable String major) {
        return courseService.getCourseByMajor(major);
    }

    @Operation(summary = "학년에 해당하는 과목 조회", description = "학년을 입력받아 학년에 해당하는 모든 과목을 조회한다.")
    @GetMapping("/grade/{grade}")
    private List<Course> getCourseByGrade(@PathVariable String grade) {
        return courseService.getCourseByGrade(grade);
    }

    @Operation(summary = "전공, 학년에 해당하는 과목 조회", description = "전공, 학년을 입력받아 해당하는 모든 과목을 조회한다.")
    @GetMapping("/major/{major}/grade/{grade}")
    private List<Course> getCourseByMajorAndGrade(@PathVariable String major,
                                                  @PathVariable String grade) {
        return courseService.getCourseByMajorAndGrade(major, grade);
    }
}
