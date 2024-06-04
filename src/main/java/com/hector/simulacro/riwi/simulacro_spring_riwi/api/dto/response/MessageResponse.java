package com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.response;

import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.Course;
import com.hector.simulacro.riwi.simulacro_spring_riwi.domain.entitites.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {



    private Long id;
    private String messageContent;
    private Date sentDate;

    private CourseResponse courseId;
    private UserResponse senderId;
    private UserResponse receiverId;


}
