package com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpdateRequest {


    @NotBlank(message = "the course name is required")
    @Size(
            max = 100,
            message = "the course name must be less than 100 characters"
    )
    private String courseName;


    @NotBlank(message = "the description is required")
    private String description;
}
