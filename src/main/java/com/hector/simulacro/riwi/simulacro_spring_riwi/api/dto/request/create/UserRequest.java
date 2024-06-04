package com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.create;

import com.hector.simulacro.riwi.simulacro_spring_riwi.api.dto.request.update.UserUpdateRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest extends UserUpdateRequest {

   @NotBlank(message = "the role is required")
   @Pattern(regexp = "ADMIN|STRUDENT|INSTRUCTOR", message = "The state must be ADMIN, STUDENT or INSTRUCTOR")
    private String role;
}
