package com.teme.repository;

import com.teme.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    AppUser findByUsername(String username);
    boolean existsAppUserByUsernameAndPassword(String username, String password);
}
