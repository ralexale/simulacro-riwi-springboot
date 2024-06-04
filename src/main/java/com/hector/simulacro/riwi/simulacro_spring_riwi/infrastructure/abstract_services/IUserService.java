package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.UserRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.UserUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.UserResponse;


public interface IUserService extends CreateReadDeleteService<UserRequest, UserResponse, Long>,
        UpdateService<UserUpdateRequest, UserResponse, Long> { }

