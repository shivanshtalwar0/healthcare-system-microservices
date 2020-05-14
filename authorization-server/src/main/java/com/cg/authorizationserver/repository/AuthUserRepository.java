package com.cg.authorizationserver.repository;

import com.cg.authorizationserver.entity.AuthUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthUserRepository extends CrudRepository<AuthUser,Long> {
    Optional<AuthUser> findByUsername(String username);
	Boolean existsByUsername(String username);
}
