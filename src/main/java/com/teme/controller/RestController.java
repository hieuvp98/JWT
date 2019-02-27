package com.teme.controller;


import com.teme.entities.AppUser;
import com.teme.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RestController(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @PostMapping(value = "/users/sign-up")
    public void signUp(@RequestBody AppUser appUser){
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
    }
    @GetMapping(value = "/users/{id}")
    public AppUser findAppUserById(@PathVariable(value = "id") int id, HttpServletRequest request){
        AppUser appUser = null;
        var username = request.getUserPrincipal().getName();
        var temp = appUserRepository.findByUsername(username);
        if (temp.getId() == id)
            appUser = temp;
        return appUser;
    }
}
