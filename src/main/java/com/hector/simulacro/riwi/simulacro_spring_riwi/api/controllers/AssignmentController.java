package com.hector.simulacro.riwi.simulacro_spring_riwi.api.controllers;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.AssignmentRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.AssignmentResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services.IAssignmentService;
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
@RequestMapping("/assignments")
@AllArgsConstructor
public class AssignmentController {
    @Autowired
    private IAssignmentService assignmentService;


    @PostMapping
    public ResponseEntity<AssignmentResponse> saveAssignment(@Validated @RequestBody AssignmentRequest assignmentRequest) {
        return ResponseEntity.ok(assignmentService.create(assignmentRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentResponse> updateAssignment(@PathVariable Long id, @Validated @RequestBody AssignmentRequest assignmentRequest) {
        return ResponseEntity.ok(assignmentService.update(assignmentRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AssignmentResponse>> getAssignment(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.getById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lessons/{lessonId}")
    public ResponseEntity<Page<AssignmentResponse>>getAssignmentsByLessonId(
            @PathVariable Long lessonId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);

        return ResponseEntity.ok(assignmentService.findByLessonId(pageable,lessonId));
    }
}
