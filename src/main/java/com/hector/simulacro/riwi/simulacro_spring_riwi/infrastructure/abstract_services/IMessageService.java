package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.MessageRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IMessageService extends  CreateReadDeleteService<MessageRequest, MessageResponse, Long>{

    Page<MessageResponse> findBySenderIdAndReceiverId(Pageable pageable, Long senderId, Long receiverId);
    Page<MessageResponse> findByCourseId(Pageable pageable, Long courseId);
}
