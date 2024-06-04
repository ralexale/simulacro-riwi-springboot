package com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long id;
    private String courseName;
    private String description;

    private UserResponse instructorId;
}
