package com.hector.simulacro.riwi.simulacro_spring_riwi.api.controllers;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.LessonRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.LessonResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services.ILessonService;
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
@RequestMapping("/lessons")
@AllArgsConstructor
public class LessonController {
    @Autowired
    private ILessonService lessonService;

    @PostMapping
    public ResponseEntity<LessonResponse> saveLesson(@Validated @RequestBody LessonRequest lessonRequest) {
        return ResponseEntity.ok(lessonService.create(lessonRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonResponse> updateLesson(@PathVariable Long id, @Validated @RequestBody LessonRequest lessonRequest) {
        return ResponseEntity.ok(lessonService.update(lessonRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LessonResponse>> getLesson(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/courses/{courseId}/lessons")
    public ResponseEntity<Page<LessonResponse>> getLessonsByCourseId(
            @PathVariable Long courseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(lessonService.findByCourseId(pageable, courseId));
    }

}
