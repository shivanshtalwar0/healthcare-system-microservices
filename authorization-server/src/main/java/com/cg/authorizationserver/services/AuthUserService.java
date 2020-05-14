package com.cg.authorizationserver.services;

import com.cg.authorizationserver.entity.AuthUser;
import com.cg.authorizationserver.repository.AuthUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthUserService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(AuthUserService.class);
    private final AuthUserRepository authUserRepository;
    @Autowired
    public AuthUserService(AuthUserRepository authUserRepository){
        this.authUserRepository=authUserRepository;

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> optionalAuthUser=authUserRepository.findByUsername(username);

        if(optionalAuthUser.isPresent()){
            AuthUser authUser=optionalAuthUser.get();
            List<String>rolesArray=authUser.getRolesList().stream().map((roles -> roles.getRole().name())).collect(Collectors.toList());
            logger.warn(String.join(",",rolesArray));
            List<GrantedAuthority> authorityList=AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",",rolesArray));
            return new User(authUser.getUsername(),authUser.getPassword(),authorityList);
        }
        throw new UsernameNotFoundException("not found");


    }
}
