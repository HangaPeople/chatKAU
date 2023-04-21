package com.chatkau.controller;

import com.chatkau.dto.request.CurrCourseRequest;
import com.chatkau.dto.response.CurrCourseResponse;
import com.chatkau.entity.Course;
import com.chatkau.service.CurrCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/curriculum-course")
public class CurrCourseController {

    private final CurrCourseService currCourseService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Course>> getCurriculumCourseById(@PathVariable Long id) {
        List<Course> curriculumCourse = currCourseService.findCoursesByCurriculumId(id);
        return ResponseEntity.ok(curriculumCourse);
    }

    @PostMapping
    public ResponseEntity<CurrCourseResponse> createCurriculumCourse(@RequestBody CurrCourseRequest currRequest) {
        CurrCourseResponse savedCurriculumCourse = currCourseService.createCurriculumCourse(currRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCurriculumCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurriculumCourse(@PathVariable Long id) {
        currCourseService.deleteCurriculumCourse(id);
        return ResponseEntity.noContent().build();
    }
}
