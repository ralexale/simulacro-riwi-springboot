package com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.AssignmentResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Assignment;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository  extends JpaRepository<Assignment, Long> {
    Page<Assignment> findByLessonId(Pageable pageable, Lesson lessonId);

}
