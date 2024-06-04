package com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.LessonUpdateRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequest extends LessonUpdateRequest {

    @NotNull(message = "Course id is required")
    @Min(value = 1, message = "Course id must be greater than 0")
    private Long courseId;
}
