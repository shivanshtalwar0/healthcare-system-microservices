package com.cg.authorizationserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AuthUser", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class AuthUser {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name ="username")
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

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity = Roles.class)
    List<Roles> rolesList;

    @Override
    public String toString() {
        return "AuthUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
