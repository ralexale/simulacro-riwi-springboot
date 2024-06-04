package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.services;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.LessonRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.LessonUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.LessonResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Course;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Lesson;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.CourseRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.LessonRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services.ILessonService;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.mappers.LessonMapper;
import com.hector.simulacro.riwi.simulacro_spring_riwi.utils.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public LessonResponse create(LessonRequest lessonRequest) {
        Lesson lesson = lessonMapper.toLesson(lessonRequest);

        Course course = courseRepository.findById(lessonRequest.getCourseId())
                .orElseThrow(() -> new IdNotFoundException("COURSE", lessonRequest.getCourseId()));

        lesson.setCourseId(course);
        Lesson savedLesson = lessonRepository.save(lesson);
        return lessonMapper.toLessonResponse(savedLesson);
    }

    @Override
    public void delete(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public Page<LessonResponse> getAll(Pageable pageable) {
            Page<Lesson> lessonPage = lessonRepository.findAll(pageable);
            return lessonPage.map(lessonMapper::toLessonResponse);
    }

    @Override
    public Optional<LessonResponse> getById(Long id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if (lesson.isEmpty()) throw new IdNotFoundException("LESSON", id);

        return lesson.map(lessonMapper::toLessonResponse);
    }

    @Override
    public Page<LessonResponse> findByCourseId(Pageable pageable, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IdNotFoundException("COURSE", courseId));

        Page<Lesson> lessonPage = lessonRepository.findByCourseId(pageable, course);

        return lessonPage.map(lessonMapper::toLessonResponse);
    }

    @Override
    public LessonResponse update(LessonUpdateRequest lessonUpdateRequest, Long id) {
        Lesson existingLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("LESSON", id));

        lessonMapper.updateFromLessonRequest(lessonUpdateRequest, existingLesson);
        Lesson updateLesson = lessonRepository.save(existingLesson);
        return lessonMapper.toLessonResponse(updateLesson);
    }
}
