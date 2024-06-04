package com.hector.simulacro.riwi.simulacro_spring_riwi.api.controllers;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.MessageRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.MessageResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services.IMessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {
    @Autowired
    private IMessageService messageService;

    @PostMapping
    public ResponseEntity<MessageResponse> sendMessage(@Validated @RequestBody MessageRequest messageRequest) {
        return ResponseEntity.ok(messageService.create(messageRequest));
    }

    @GetMapping("/courses/{courseId}/messages")
    public ResponseEntity<Page<MessageResponse>> getMessagesByCourseId(
            @PathVariable Long courseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);

        return ResponseEntity.ok(messageService.findByCourseId(pageable, courseId));
    }

    @GetMapping
    public ResponseEntity<Page<MessageResponse>> getMessagesBySenderAndReceiverId(
            @PathVariable Long senderId,
            @PathVariable Long receiverId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);

        return ResponseEntity.ok(messageService.findBySenderIdAndReceiverId(pageable, senderId, receiverId));
    }
}
