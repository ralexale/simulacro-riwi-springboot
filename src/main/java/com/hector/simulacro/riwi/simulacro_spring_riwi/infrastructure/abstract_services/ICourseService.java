package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.CourseRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.CourseUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.CourseResponse;


public interface ICourseService extends  CreateReadDeleteService<CourseRequest, CourseResponse, Long>,
        UpdateService<CourseUpdateRequest, CourseResponse, Long> {


}
