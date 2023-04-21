package com.chatkau.dto;

import com.chatkau.entity.Curriculum;
import com.chatkau.entity.CurrCourse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CurriculumDto {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private List<CurrCourse> courses;

    public static CurriculumDto fromEntity(Curriculum curr) {
        return new CurriculumDto(
                curr.getId(),
                curr.getTitle(),
                curr.getDescription(),
                curr.getUser().getId(),
                curr.getCurrCours()
        );
    }
}
