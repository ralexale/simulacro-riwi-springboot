package com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories;

import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
