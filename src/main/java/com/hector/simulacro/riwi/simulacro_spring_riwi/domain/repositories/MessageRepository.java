package com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.MessageResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Course;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Message;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findBySenderIdAndReceiverId(Pageable pageable, User senderId, User receiverId);
    Page<Message> findByCourseId(Pageable pageable, Course course);
}
