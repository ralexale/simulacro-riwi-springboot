package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.mappers;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.EnrollmentRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.EnrollmentResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CourseMapper.class})
public interface EnrollmentMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "userId", target = "userId.id"),
            @Mapping(source = "courseId", target = "courseId.id")
    })
    Enrollment toEnrollment(EnrollmentRequest enrollmentRequest);

    EnrollmentResponse toEnrollmentResponse(Enrollment enrollment);
}
