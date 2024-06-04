package com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.SubmissionUpdateRequest;
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
public class SubmissionRequest extends SubmissionUpdateRequest {

    @NotNull(message = "User id is required")
    @Min(value = 1, message = "User id must be greater than 0")
    private Long userId;

    @NotNull(message = "Assignment id is required")
    @Min(value = 1, message = "Assignment id must be greater than 0")
    private Long assignmentId;

}
