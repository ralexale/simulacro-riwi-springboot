package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.services;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.MessageRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.MessageResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Course;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Message;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.User;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.CourseRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.MessageRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.UserRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services.IMessageService;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.mappers.MessageMapper;
import com.hector.simulacro.riwi.simulacro_spring_riwi.utils.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageService implements IMessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public MessageResponse create(MessageRequest messageRequest) {
        Message message = messageMapper.toMessage(messageRequest);

        Course course = courseRepository.findById(messageRequest.getCourseId())
                .orElseThrow(() -> new IdNotFoundException("COURSE", messageRequest.getCourseId()));

        User sender = userRepository.findById(messageRequest.getSenderId())
                .orElseThrow(() -> new IdNotFoundException("USER", messageRequest.getSenderId()));

        User receiver = userRepository.findById(messageRequest.getReceiverId())
                .orElseThrow(() -> new IdNotFoundException("USER", messageRequest.getReceiverId()));

        message.setSenderId(sender);
        message.setReceiverId(receiver);
        message.setCourseId(course);

        Message savedMessage = messageRepository.save(message);
        return messageMapper.toMessageResponse(savedMessage);
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Page<MessageResponse> getAll(Pageable pageable) {
        Page<Message> messagePage = messageRepository.findAll(pageable);
        return messagePage.map(messageMapper::toMessageResponse);
    }

    @Override
    public Optional<MessageResponse> getById(Long id) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isEmpty()) throw new IdNotFoundException("MESSAGE", id);

        return message.map(messageMapper::toMessageResponse);
    }

    @Override
    public Page<MessageResponse> findBySenderIdAndReceiverId(Pageable pageable, Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IdNotFoundException("USER", senderId));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IdNotFoundException("USER", receiverId));

        Page<Message> messagePage = messageRepository.findBySenderIdAndReceiverId(pageable, sender, receiver);

        return messagePage.map(messageMapper::toMessageResponse);
    }

    @Override
    public Page<MessageResponse> findByCourseId(Pageable pageable, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IdNotFoundException("COURSE", courseId));

        Page<Message> messagePage = messageRepository.findByCourseId(pageable, course);

        return messagePage.map(messageMapper::toMessageResponse);
    }



}
