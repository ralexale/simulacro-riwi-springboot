package com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.services;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create.UserRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.UserUpdateRequest;
import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response.UserResponse;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.User;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.repositories.UserRepository;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.abstract_services.IUserService;
import com.hector.simulacro.riwi.simulacro_spring_riwi.infrastructure.mappers.UserMapper;
import com.hector.simulacro.riwi.simulacro_spring_riwi.utils.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponse create(UserRequest userRequest) {
        User user = userMapper.toUser(userRequest);

       User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
     Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(userMapper::toUserResponse);
    }

    @Override
    public Optional<UserResponse> getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new IdNotFoundException("USER", id);

        return user.map(userMapper::toUserResponse);
    }

    @Override
    public UserResponse update(UserUpdateRequest userUpdateRequest, Long id) {
       User existingUser = userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("USER", id));
       userMapper.updateFromUserRequest(userUpdateRequest, existingUser);

       User updatedUser = userRepository.save(existingUser);
       return userMapper.toUserResponse(updatedUser);
    }

}
