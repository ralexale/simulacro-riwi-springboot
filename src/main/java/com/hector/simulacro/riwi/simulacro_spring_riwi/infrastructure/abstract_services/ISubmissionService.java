package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.SubmissionRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.SubmissionUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.SubmissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISubmissionService extends CreateReadDeleteService<SubmissionRequest, SubmissionResponse, Long>,
        UpdateService<SubmissionUpdateRequest, SubmissionResponse, Long> {
    Page<SubmissionResponse> findByAssignmentId(Pageable pageable, Long assignmentId);
    Page<SubmissionResponse> findByUserId(Pageable pageable, Long userId);
}
