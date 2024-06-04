package com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories;


import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Assignment;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Submission;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    Page<Submission> findByAssignmentId(Pageable pageable, Assignment assignment);
    Page<Submission> findByUserId(Pageable pageable, User user);
}
