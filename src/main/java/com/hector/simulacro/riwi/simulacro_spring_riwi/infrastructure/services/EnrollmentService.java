package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.services;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.EnrollmentRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.EnrollmentResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Course;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Enrollment;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.User;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.CourseRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.EnrollmentRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.UserRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services.IEnrollmentService;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.mappers.EnrollmentMapper;
import com.hector.simulacro.riwi.simulacro_spring_riwi.utils.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EnrollmentService implements IEnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private EnrollmentMapper enrollmentMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public EnrollmentResponse create(EnrollmentRequest enrollmentRequest) {
        Enrollment enrollment = enrollmentMapper.toEnrollment(enrollmentRequest);

        User user = userRepository.findById(enrollmentRequest.getUserId())
                .orElseThrow(() -> new IdNotFoundException("USER", enrollmentRequest.getUserId()));

        Course course = courseRepository.findById(enrollmentRequest.getCourseId())
                .orElseThrow(() -> new IdNotFoundException("COURSE", enrollmentRequest.getCourseId()));

        enrollment.setUserId(user);
        enrollment.setCourseId(course);

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentResponse(savedEnrollment);
    }

    @Override
    public void delete(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    public Page<EnrollmentResponse> getAll(Pageable pageable) {
        Page<Enrollment> enrollmentPage = enrollmentRepository.findAll(pageable);
        return enrollmentPage.map(enrollmentMapper::toEnrollmentResponse);
    }

    @Override
    public Optional<EnrollmentResponse> getById(Long id) {
       Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
        if (enrollment.isEmpty()) throw new IdNotFoundException("ENROLLMENT", id);
        return enrollment.map(enrollmentMapper::toEnrollmentResponse);
    }

    @Override
    public Page<EnrollmentResponse> findByCourseId(Pageable pageable, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IdNotFoundException("COURSE", courseId));

        Page<Enrollment> enrollmentPage = enrollmentRepository.findByCourseId(pageable, course);

        return enrollmentPage.map(enrollmentMapper::toEnrollmentResponse);
    }

    @Override
    public Page<EnrollmentResponse> findByUserId(Pageable pageable, Long userId) {
      User user = userRepository.findById(userId)
                .orElseThrow(() -> new IdNotFoundException("USER", userId));
        Page<Enrollment> enrollmentPage = enrollmentRepository.findByUserId(pageable, user);

        return enrollmentPage.map(enrollmentMapper::toEnrollmentResponse);
    }
}
