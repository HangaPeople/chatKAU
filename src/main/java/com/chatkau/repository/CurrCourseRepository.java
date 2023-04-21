package com.chatkau.repository;

import com.chatkau.entity.Course;
import com.chatkau.entity.CurrCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrCourseRepository extends JpaRepository<CurrCourse, Long> {

    @Query("SELECT cc.course FROM CurrCourse cc WHERE cc.curriculum.id = :curriculumId")
    List<Course> findCoursesByCurriculumId(Long curriculumId);
}
