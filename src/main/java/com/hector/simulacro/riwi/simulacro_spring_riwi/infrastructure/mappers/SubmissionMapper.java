package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.mappers;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.SubmissionRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.SubmissionUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.SubmissionResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Submission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {AssignmentMapper.class, UserMapper.class})
public interface SubmissionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "assignmentId", target = "assignmentId.id"),
            @Mapping(source = "userId", target = "userId.id")
    })
    Submission toSubmission(SubmissionRequest submissionRequest);

    SubmissionResponse toSubmissionResponse(Submission submission);

    void updateFromSubmissionRequest(SubmissionUpdateRequest submissionRequest, @MappingTarget Submission submission);
}
