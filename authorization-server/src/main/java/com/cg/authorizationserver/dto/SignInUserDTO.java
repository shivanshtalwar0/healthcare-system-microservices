package com.cg.authorizationserver.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class SignInUserDTO {
    @NotEmpty(message = "Please Enter Email")
    @Email
    private String username;
	@NotEmpty(message = "Please Enter Password")
    private String password;
}
