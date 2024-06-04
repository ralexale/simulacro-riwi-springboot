package com.hector.simulacro.riwi.simulacro_spring_riwi.api.controllers;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.SubmissionRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.SubmissionResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services.ISubmissionService;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.services.SubmissionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/submissions")
@AllArgsConstructor
public class SubmissionController {

    @Autowired
    private ISubmissionService submissionService;

    @PostMapping
    public ResponseEntity<SubmissionResponse> saveSubmission(@Validated @RequestBody SubmissionRequest submissionRequest) {
        return ResponseEntity.ok(submissionService.create(submissionRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<SubmissionResponse>> getSubmission(@PathVariable Long id) {
        return ResponseEntity.ok(submissionService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
        submissionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubmissionResponse> updateSubmission(@PathVariable Long id, @Validated @RequestBody SubmissionRequest submissionRequest) {
        return ResponseEntity.ok(submissionService.update(submissionRequest, id));
    }

    @GetMapping("/users/{userId}/submissions")
    public ResponseEntity<Page<SubmissionResponse>> getSubmissionsByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(submissionService.findByUserId(pageable, userId));
    }

    @GetMapping("/assignment/{assignmentId}/submissions")
    public ResponseEntity<Page<SubmissionResponse>> getSubmissionsByCourseId(
            @PathVariable Long assignmentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(submissionService.findByAssignmentId(pageable, assignmentId));
    }
}
