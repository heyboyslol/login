package com.ssafy.mockstockinvestment.user.controller;

import java.net.URI;

import com.ssafy.mockstockinvestment.user.dto.request.CreateUserRequest;
import com.ssafy.mockstockinvestment.user.dto.request.LoginRequest;
import com.ssafy.mockstockinvestment.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CreateUserRequest createUserRequest) {
        String createdUserId = userService.register(createUserRequest);
        return ResponseEntity.created(URI.create("/auth/register" + createdUserId)).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestParam LoginRequest loginRequest) {
        userService.login(loginRequest);
        return ResponseEntity.ok().build();
    }

}
