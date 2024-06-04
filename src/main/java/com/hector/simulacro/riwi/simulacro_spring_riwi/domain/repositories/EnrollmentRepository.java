package com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.EnrollmentResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Course;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Enrollment;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Page<Enrollment> findByCourseId(Pageable pageable, Course courseId);
    Page<Enrollment> findByUserId(Pageable pageable, User userId);
}
