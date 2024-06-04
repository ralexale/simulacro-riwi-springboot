package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.services;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.CourseRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.CourseUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.CourseResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Course;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.CourseRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services.ICourseService;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.mappers.CourseMapper;
import com.hector.simulacro.riwi.simulacro_spring_riwi.utils.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public CourseResponse create(CourseRequest courseRequest) {
       Course course = courseMapper.toCourse(courseRequest);

        System.out.println(course);

        Course savedCourse = courseRepository.save(course);
        return courseMapper.toCourseResponse(savedCourse);
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Page<CourseResponse> getAll(Pageable pageable) {
        Page<Course> coursePage = courseRepository.findAll(pageable);

        return coursePage.map(courseMapper::toCourseResponse);
    }

    @Override
    public Optional<CourseResponse> getById(Long aLong) {
       Optional<Course> course = courseRepository.findById(aLong);
        if (course.isEmpty()) throw new IdNotFoundException("COURSE", aLong);
        return course.map(courseMapper::toCourseResponse);
    }

    @Override
    public CourseResponse update(CourseUpdateRequest courseUpdateRequest, Long aLong) {
       Course existingCourse = courseRepository.findById(aLong)
                .orElseThrow(() -> new IdNotFoundException("COURSE", aLong));

        courseMapper.updateFromCourseRequest(courseUpdateRequest, existingCourse);
        Course updateCourse = courseRepository.save(existingCourse);
        return courseMapper.toCourseResponse(updateCourse);
    }
}
