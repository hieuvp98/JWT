package com.teme.service_imp;

import com.teme.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public UserDetailsServiceImp(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var appUser = appUserRepository.findByUsername(s);
        if (appUser == null) throw new UsernameNotFoundException(s);
        return new User(appUser.getUsername(),appUser.getPassword(), Collections.emptyList());
    }
}
