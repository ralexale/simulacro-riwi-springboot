package com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    @NotNull(message = "Sender id is required")
    @Min(value = 1, message = "Sender id must be greater than 0")
    private Long senderId;

    @NotNull(message = "Receiver id is required")
    @Min(value = 1, message = "Receiver id must be greater than 0")
    private Long receiverId;

    @NotNull(message = "Course id is required")
    @Min(value = 1, message = "Course id must be greater than 0")
    private Long courseId;

    @NotBlank(message = "Message content is required")
    private String messageContent;

    @NotNull(message = "Sent date is required")
    @FutureOrPresent(message = "It is not possible to enter a date later than the current date.")
    private Date sentDate;
}
