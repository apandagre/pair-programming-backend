package com.pairprogrmmingbackend.pairprogramming.controllers;

import com.pairprogrmmingbackend.pairprogramming.config.UserAuthProvider;
import com.pairprogrmmingbackend.pairprogramming.dtos.CredentialsDto;
import com.pairprogrmmingbackend.pairprogramming.dtos.SignUpDto;
import com.pairprogrmmingbackend.pairprogramming.dtos.UserDto;
import com.pairprogrmmingbackend.pairprogramming.entities.User;
import com.pairprogrmmingbackend.pairprogramming.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto.getEmail()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(user.getEmail()));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

}