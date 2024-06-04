package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.mappers;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.AssignmentRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.AssignmentUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.AssignmentResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Assignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AssignmentMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "lessonId", target = "lessonId.id")
    })
    Assignment toAssignment(AssignmentRequest assignmentRequest);

    AssignmentResponse toAssignmentResponse(Assignment assignment);

    void updateFromAssignmentRequest(AssignmentUpdateRequest assignmentRequest, @MappingTarget Assignment assignment);
}
