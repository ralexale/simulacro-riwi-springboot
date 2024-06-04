package com.hector.simulacro.riwi.simulacro_spring_riwi.api.controllers;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.CourseRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.CourseResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services.ICourseService;
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
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
    @Autowired
    private ICourseService courseService;


    @PostMapping
    public ResponseEntity<CourseResponse> saveCourse(@Validated @RequestBody CourseRequest courseRequest) {

        return ResponseEntity.ok(courseService.create(courseRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable Long id, @Validated @RequestBody CourseRequest courseRequest) {
        return ResponseEntity.ok(courseService.update(courseRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CourseResponse>> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<CourseResponse>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);

        return ResponseEntity.ok(courseService.getAll(pageable));
    }
}
