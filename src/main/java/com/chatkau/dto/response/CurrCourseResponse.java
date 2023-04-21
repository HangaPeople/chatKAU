package com.chatkau.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrCourseResponse {
    private Long curriculumId;
    private Long courseId;
}
