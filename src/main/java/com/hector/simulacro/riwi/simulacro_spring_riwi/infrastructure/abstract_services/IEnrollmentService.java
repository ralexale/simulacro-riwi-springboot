package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.EnrollmentRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.EnrollmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IEnrollmentService extends CreateReadDeleteService<EnrollmentRequest, EnrollmentResponse, Long>{

    Page<EnrollmentResponse> findByCourseId(Pageable pageable, Long courseId);
    Page<EnrollmentResponse> findByUserId(Pageable pageable, Long userId);
}
