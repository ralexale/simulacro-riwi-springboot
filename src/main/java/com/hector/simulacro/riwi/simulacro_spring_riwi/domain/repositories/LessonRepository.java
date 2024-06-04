package com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories;


import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Course;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LessonRepository  extends JpaRepository<Lesson, Long> {
    Page<Lesson> findByCourseId(Pageable pageable, Course courseId);
}
