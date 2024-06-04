package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.services;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.SubmissionRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.SubmissionUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.SubmissionResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Assignment;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Submission;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.User;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.AssignmentRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.SubmissionRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.UserRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services.ISubmissionService;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.mappers.SubmissionMapper;
import com.hector.simulacro.riwi.simulacro_spring_riwi.utils.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SubmissionService  implements ISubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private final AssignmentRepository assignmentRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public SubmissionResponse update(SubmissionUpdateRequest submissionUpdateRequest, Long id) {
        Submission existingSubmission = submissionRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("ENROLLMENT", id));

        submissionMapper.updateFromSubmissionRequest(submissionUpdateRequest, existingSubmission);
        Submission updateSubmission = submissionRepository.save(existingSubmission);
        return submissionMapper.toSubmissionResponse(updateSubmission);
    }

    @Override
    public SubmissionResponse create(SubmissionRequest submissionRequest) {
        Submission submission = submissionMapper.toSubmission(submissionRequest);

        User user = userRepository.findById(submissionRequest.getUserId())
                .orElseThrow(() -> new IdNotFoundException("USER", submissionRequest.getUserId()));

        Assignment assignment = assignmentRepository.findById(submissionRequest.getAssignmentId())
                .orElseThrow(() -> new IdNotFoundException("COURSE", submissionRequest.getAssignmentId()));

        submission.setUserId(user);
        submission.setAssignmentId(assignment);

        Submission savedSubmission = submissionRepository.save(submission);
        return submissionMapper.toSubmissionResponse(savedSubmission);
    }

    @Override
    public void delete(Long id) {
        submissionRepository.deleteById(id);
    }

    @Override
    public Page<SubmissionResponse> getAll(Pageable pageable) {
       Page<Submission> submissionPage = submissionRepository.findAll(pageable);
        return submissionPage.map(submissionMapper::toSubmissionResponse);
    }

    @Override
    public Optional<SubmissionResponse> getById(Long id) {
      Optional<Submission> submission = submissionRepository.findById(id);
        if (submission.isEmpty()) throw new IdNotFoundException("SUBMISSION", id);
        return submission.map(submissionMapper::toSubmissionResponse);
    }

    @Override
    public Page<SubmissionResponse> findByAssignmentId(Pageable pageable, Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IdNotFoundException("ASSIGNMENT", assignmentId));

        Page<Submission> submissionPage = submissionRepository.findByAssignmentId(pageable, assignment);

        return submissionPage.map(submissionMapper::toSubmissionResponse);
    }

    @Override
    public Page<SubmissionResponse> findByUserId(Pageable pageable, Long userId) {
       User user = userRepository.findById(userId)
                .orElseThrow(() -> new IdNotFoundException("USER", userId));

        Page<Submission> submissionPage = submissionRepository.findByUserId(pageable, user);

        return submissionPage.map(submissionMapper::toSubmissionResponse);
    }
}
