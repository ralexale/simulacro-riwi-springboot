package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.AssignmentRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.AssignmentUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.AssignmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAssignmentService extends CreateReadDeleteService<AssignmentRequest, AssignmentResponse, Long>,
        UpdateService<AssignmentUpdateRequest, AssignmentResponse, Long> {

    Page<AssignmentResponse> findByLessonId(Pageable pageable, Long lessonId);
}
