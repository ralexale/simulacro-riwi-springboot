package com.hector.simulacro.riwi.simulacro_spring_riwi.api.controllers;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.EnrollmentRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.EnrollmentResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services.IEnrollmentService;
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
@RequestMapping("/enrollments")
@AllArgsConstructor
public class EnrollmentController {
    @Autowired
    private IEnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponse> saveEnrollment(@Validated @RequestBody EnrollmentRequest enrollmentRequest) {
        return ResponseEntity.ok( enrollmentService.create(enrollmentRequest));
    }



    @GetMapping("/{id}")
    public ResponseEntity<Optional<EnrollmentResponse>> getEnrollment(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{userId}/enrollments")
    public ResponseEntity<Page<EnrollmentResponse>> getEnrollmentsByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(enrollmentService.findByUserId(pageable ,userId));
    }

    @GetMapping("/courses/{courseId}/enrollments")
    public ResponseEntity<Page<EnrollmentResponse>> getEnrollmentsByCourseId(
            @PathVariable Long courseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(enrollmentService.findByCourseId(pageable ,courseId));
    }
}
