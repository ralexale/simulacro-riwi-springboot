package com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentUpdateRequest {

    @NotBlank(message = "the assignment title is required")
    @Size(
            max = 100,
            message = "the assignment title must be less than 100 characters"
    )
    private String assignmentTitle;


    @NotBlank(message = "the description is required")
    private String description;


    @NotNull(message = "the due date is required")
    @FutureOrPresent(message = "the due date must be in the future")
    private Date dueDate;
}
