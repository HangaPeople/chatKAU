package com.chatkau.controller;

import com.chatkau.dto.request.CurrCourseRequest;
import com.chatkau.dto.response.CurrCourseResponse;
import com.chatkau.entity.Course;
import com.chatkau.service.CurrCourseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/curr-course")
public class CurrCourseController {

    private final CurrCourseService currCourseService;

    @Operation(summary = "커리큘럼 과목 단건 조회", description = "id를 받아 커리큘럼 과목을 조회한다.")
    @GetMapping("/{id}")
    public ResponseEntity<List<Course>> getCurriculumCourseById(@PathVariable Long id) {
        List<Course> curriculumCourse = currCourseService.findCoursesByCurriculumId(id);
        return ResponseEntity.ok(curriculumCourse);
    }

    @Operation(summary = "커리큘럼 과목 생성", description = "curr-course Request를 받아 커리큘럼 과목을 생성한다.")
    @PostMapping
    public ResponseEntity<CurrCourseResponse> createCurriculumCourse(@RequestBody CurrCourseRequest currRequest) {
        CurrCourseResponse savedCurriculumCourse = currCourseService.createCurriculumCourse(currRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCurriculumCourse);
    }

    @Operation(summary = "커리큘럼 과목 삭제", description = "id를 받아 커리큘럼 과목을 삭제한다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurriculumCourse(@PathVariable Long id) {
        currCourseService.deleteCurriculumCourse(id);
        return ResponseEntity.noContent().build();
    }
}
