package com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response;


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
public class SubmissionResponse {
    private Long id;
    private String content;
    private Date submissionDate;
    private BigDecimal grade;

    private UserResponse userId;
    private AssignmentResponse assignmentId;
}
