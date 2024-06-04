package com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionUpdateRequest {

    @NotNull(message = "Grade is required")
    @Digits(integer = 3, fraction = 2, message = "Grade must have up to 3 integer digits and up to 2 decimal places")
    @DecimalMin(value = "0.00", message = "Grade must be greater than 0")
    @DecimalMax(value = "999.99", message = "Grade must be less than 999.99")
    private BigDecimal grade;

    @NotBlank(message = "Content is required")
    private String content;


    @NotNull(message = "Submission date is required")
    @FutureOrPresent(message = "It is not possible to enter a date later than the current date.")
    private Date submissionDate;
}
