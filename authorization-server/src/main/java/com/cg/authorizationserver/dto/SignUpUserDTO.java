package com.cg.authorizationserver.dto;

import com.cg.authorizationserver.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class SignUpUserDTO {
    @NotEmpty(message = "Please Enter Email")
    @Email
    private String username;
    @NotEmpty(message = "Please Enter First Name")
	private String firstName;
	@NotEmpty(message = "Please Enter Last Name")
	private String lastName;
	@NotNull(message = "Please Enter Contact Number")
	private long contactNo;
	@NotEmpty(message = "Please Enter Password")
    private String password;
    List<Role> rolesList;
}
