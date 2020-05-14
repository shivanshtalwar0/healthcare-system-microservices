package com.cg.authorizationserver.filters;

import com.cg.authorizationserver.dto.SignInUserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;



public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
//    /auth post user

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {

            // 1. Get credentials from request
            SignInUserDTO creds = new ObjectMapper().readValue(req.getInputStream(), SignInUserDTO.class);
            logger.warn(creds.toString());
            // 2. Create auth object (contains credentials) which will be used by auth manager
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    creds.getUsername(), creds.getPassword(), Collections.emptyList());

            // 3. Authentication manager authenticate the user, and use UserDetialsServiceImpl::loadUserByUsername() method to load the user.
            return authenticationManager.authenticate(authToken);

        } catch (IOException e) {
            logger.warn(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {


//        using this to fetch all authorities
        Collection<GrantedAuthority> authenticatedUserAuthorityList =
                ((User) auth.getPrincipal()).getAuthorities();
        Calendar c = Calendar.getInstance();
//        10 minute expiration period
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 10);
        String token = Jwts.builder()
                .setExpiration(c.getTime())
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .claim("roles", authenticatedUserAuthorityList)
                .setIssuer("healthcare")
                .signWith(SignatureAlgorithm.HS512, "secure").compact();

        res.setHeader("Content-Type", "application/json");
        res.getOutputStream().print(String.format("{\"token\":\"%s\",\"authorityList\":%s}",
                token,new ObjectMapper().writeValueAsString(authenticatedUserAuthorityList)));
    }
}
