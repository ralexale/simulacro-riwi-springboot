package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.mappers;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.LessonRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.LessonUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.LessonResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface LessonMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "courseId", target = "courseId.id")
    })
    Lesson toLesson(LessonRequest lessonRequest);

    LessonResponse toLessonResponse(Lesson lesson);

    void updateFromLessonRequest(LessonUpdateRequest lessonRequest, @MappingTarget Lesson lesson);
}
