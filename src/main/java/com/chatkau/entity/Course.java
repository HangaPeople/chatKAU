package com.chatkau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String major;
    private String grade;
    private String classify;
    private String course;
    private String courseCode;
    private String credit;
    private String professor;
    private String time;
    private String courseType;
    private String note;
    @Column(length = 1000)
    private String description;
    @Column(length = 1000)
    private String goal;
    @Column(length = 1000)
    private String preCourse;
    @Column(length = 1000)
    private String process;
}
