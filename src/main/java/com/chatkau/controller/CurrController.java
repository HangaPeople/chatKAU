package com.chatkau.controller;

import com.chatkau.dto.CurriculumDto;
import com.chatkau.service.CurrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/curr")
public class CurrController {

    private final CurrService currService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CurriculumDto>> getCurriculumsById(@PathVariable Long userId) {
        List<CurriculumDto> curriculums = currService.getCurriculumsByUser(userId);
        return ResponseEntity.ok(curriculums);
    }

    @PostMapping
    public ResponseEntity<CurriculumDto> createCurriculum(@RequestBody CurriculumDto curriculumDto) {
        CurriculumDto createdCurriculum = currService.createCurriculum(curriculumDto);
        return ResponseEntity.ok(createdCurriculum);
    }

    @PutMapping
    public ResponseEntity<CurriculumDto> updateCurriculum(@RequestBody CurriculumDto curriculumDto) {
        CurriculumDto updatedCurriculum = currService.updateCurriculum(curriculumDto);
        return ResponseEntity.ok(updatedCurriculum);
    }

    @DeleteMapping("/{currId}")
    public ResponseEntity<CurriculumDto> deleteCurriculum(@PathVariable Long currId) {
        CurriculumDto deletedCurriculum = currService.deleteCurriculum(currId);
        return ResponseEntity.ok(deletedCurriculum);
    }
}
