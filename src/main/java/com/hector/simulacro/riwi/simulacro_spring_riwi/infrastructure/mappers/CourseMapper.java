package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.mappers;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.CourseRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.CourseUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.CourseResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring" , uses = {UserMapper.class})
public interface CourseMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "instructorId", target = "instructorId.id")

    })
    Course toCourse(CourseRequest courseRequest);

    CourseResponse toCourseResponse(Course course);

    void updateFromCourseRequest(CourseUpdateRequest courseRequest, @MappingTarget Course course);
}
