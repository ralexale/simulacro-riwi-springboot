package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.services;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.AssignmentRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.AssignmentUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.AssignmentResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Assignment;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Lesson;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.AssignmentRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.LessonRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services.IAssignmentService;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.mappers.AssignmentMapper;
import com.hector.simulacro.riwi.simulacro_spring_riwi.utils.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AssignmentService implements IAssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssignmentMapper assignmentMapper;

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public AssignmentResponse create(AssignmentRequest assignmentRequest) {
        Assignment assignment = assignmentMapper.toAssignment(assignmentRequest);

        Lesson lesson = lessonRepository.findById(assignmentRequest.getLessonId())
                .orElseThrow(() -> new IdNotFoundException("LESSON", assignmentRequest.getLessonId()));

        assignment.setLessonId(lesson);
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return assignmentMapper.toAssignmentResponse(savedAssignment);
    }

    @Override
    public void delete(Long id) {
        assignmentRepository.deleteById(id);
    }

    @Override
    public Page<AssignmentResponse> getAll(Pageable pageable) {
       Page<Assignment> assignmentPage = assignmentRepository.findAll(pageable);
        return assignmentPage.map(assignmentMapper::toAssignmentResponse);
    }

    @Override
    public Optional<AssignmentResponse> getById(Long aLong) {
       Optional<Assignment> assignment = assignmentRepository.findById(aLong);
        if (assignment.isEmpty()) throw new IdNotFoundException("ASSIGNMENT", aLong);
        return assignment.map(assignmentMapper::toAssignmentResponse);
    }

    @Override
    public Page<AssignmentResponse> findByLessonId(Pageable pageable, Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IdNotFoundException("LESSON", lessonId));

        Page<Assignment> assignmentPage = assignmentRepository.findByLessonId(pageable, lesson);

        return assignmentPage.map(assignmentMapper::toAssignmentResponse);
    }

    @Override
    public AssignmentResponse update(AssignmentUpdateRequest assignmentUpdateRequest, Long id) {
        Assignment existingAssignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("ASSIGNMENT", id));

        assignmentMapper.updateFromAssignmentRequest(assignmentUpdateRequest, existingAssignment);
        Assignment updateAssignment = assignmentRepository.save(existingAssignment);
        return assignmentMapper.toAssignmentResponse(updateAssignment);
    }
}
