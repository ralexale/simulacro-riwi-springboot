package com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response;

import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Assignment;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse {
    private Long id;
    private String lessonTitle;
    private String description;

    private CourseResponse courseId;
}
