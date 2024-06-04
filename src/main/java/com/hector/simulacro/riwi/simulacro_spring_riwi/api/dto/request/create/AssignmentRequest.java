package com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.AssignmentUpdateRequest;
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
public class AssignmentRequest extends AssignmentUpdateRequest {

    @NotNull(message = "Lesson id is required")
    @Min(value = 1, message = "Lesson id must be greater than 0")
    private Long lessonId;
}
