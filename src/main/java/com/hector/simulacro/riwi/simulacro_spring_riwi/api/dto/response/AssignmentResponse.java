package com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentResponse {
    private Long id;
    private String assignmentTitle;
    private String description;
    private Date dueDate;

    private LessonResponse lessonId;
}
