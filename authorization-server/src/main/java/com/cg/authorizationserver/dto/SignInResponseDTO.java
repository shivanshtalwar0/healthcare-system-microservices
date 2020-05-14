package com.cg.authorizationserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDTO {
    private String token;
    private boolean success;
    private Collection<GrantedAuthority> authorityList;
}
