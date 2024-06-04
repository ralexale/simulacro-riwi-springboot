package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.mappers;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.UserRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.UserUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.UserResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(UserRequest userRequest);


    UserResponse toUserResponse(User user);

    void updateFromUserRequest(UserUpdateRequest userRequest, @MappingTarget User user);
}
