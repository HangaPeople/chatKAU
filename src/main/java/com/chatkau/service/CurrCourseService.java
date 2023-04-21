package com.chatkau.service;

import com.chatkau.config.BaseResponseStatus;
import com.chatkau.config.exception.BaseException;
import com.chatkau.dto.request.CurrCourseRequest;
import com.chatkau.dto.response.CurrCourseResponse;
import com.chatkau.entity.Course;
import com.chatkau.entity.Curriculum;
import com.chatkau.entity.CurrCourse;
import com.chatkau.repository.CourseRepository;
import com.chatkau.repository.CurrCourseRepository;
import com.chatkau.repository.CurrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrCourseService {

    private final CurrCourseRepository currCourseRepository;
    private final CourseRepository courseRepository;
    private final CurrRepository currRepository;

    public List<Course> findCoursesByCurriculumId(Long curriculumId) {
        return currCourseRepository.findCoursesByCurriculumId(curriculumId);
    }

    public CurrCourseResponse createCurriculumCourse(CurrCourseRequest currRequest) {
        Curriculum curriculum = currRepository.findById(currRequest.getCurriculumId())
                .orElseThrow(() -> new RuntimeException("Curriculum not found"));
        Course course = courseRepository.findById(currRequest.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        CurrCourse curr = CurrCourse.builder()
                .curriculum(curriculum)
                .course(course)
                .build();

        currCourseRepository.save(curr);

        CurrCourseResponse res = CurrCourseResponse.builder()
                .curriculumId(curriculum.getId())
                .courseId(course.getId())
                .build();

        return res;
    }

    public void deleteCurriculumCourse(Long id) {
        CurrCourse currCourse = currCourseRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.DATABASE_CONNECTION_ERROR));

        currCourseRepository.delete(currCourse);
    }
}
