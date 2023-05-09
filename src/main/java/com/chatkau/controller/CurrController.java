package com.chatkau.controller;

import com.chatkau.dto.CurriculumDto;
import com.chatkau.service.CurrService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/curr")
public class CurrController {

    private final CurrService currService;

    @Operation(summary = "유저가 생성한 커리큘럼 전체 조회", description = "userId를 받아 커리큘럼을 전체 조회한다.")
    @GetMapping("/{userId}")
    public ResponseEntity<List<CurriculumDto>> getCurriculumsById(@PathVariable Long userId) {
        List<CurriculumDto> curriculums = currService.getCurriculumsByUser(userId);
        return ResponseEntity.ok(curriculums);
    }

    @Operation(summary = "커리큘럼 생성", description = "curriculumDto를 받아 커리큘럼을 생성한다.")
    @PostMapping
    public ResponseEntity<CurriculumDto> createCurriculum(@RequestBody CurriculumDto curriculumDto) {
        CurriculumDto createdCurriculum = currService.createCurriculum(curriculumDto);
        return ResponseEntity.ok(createdCurriculum);
    }

    @Operation(summary = "커리큘럼 수정", description = "curriculumDto를 받아 커리큘럼을 수정한다.")
    @PutMapping
    public ResponseEntity<CurriculumDto> updateCurriculum(@RequestBody CurriculumDto curriculumDto) {
        CurriculumDto updatedCurriculum = currService.updateCurriculum(curriculumDto);
        return ResponseEntity.ok(updatedCurriculum);
    }

    @Operation(summary = "커리큘럼 삭제", description = "커리큘럼을 id를 받아 삭제를 한다.")
    @DeleteMapping("/{currId}")
    public ResponseEntity<CurriculumDto> deleteCurriculum(@PathVariable Long currId) {
        CurriculumDto deletedCurriculum = currService.deleteCurriculum(currId);
        return ResponseEntity.ok(deletedCurriculum);
    }
}
