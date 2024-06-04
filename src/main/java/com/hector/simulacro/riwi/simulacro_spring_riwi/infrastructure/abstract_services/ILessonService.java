package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.LessonRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.LessonUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.LessonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ILessonService extends CreateReadDeleteService<LessonRequest, LessonResponse, Long>,
        UpdateService<LessonUpdateRequest, LessonResponse, Long> {

    Page<LessonResponse> findByCourseId(Pageable pageable, Long courseId);
}
