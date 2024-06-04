package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.mappers;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.MessageRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.MessageResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {CourseMapper.class, UserMapper.class})
public interface MessageMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "courseId", target = "courseId.id"),
            @Mapping(source = "senderId", target = "senderId.id"),
            @Mapping(source = "receiverId", target = "receiverId.id")
    })
    Message toMessage(MessageRequest messageRequest);

    MessageResponse toMessageResponse(Message message);
}
