package com.lazovskyi.recordservice.controller;

import com.lazovskyi.recordservice.api.UserApi;
import com.lazovskyi.recordservice.dto.UserDto;
import com.lazovskyi.recordservice.model.AuthRequest;
import com.lazovskyi.recordservice.model.AuthResponse;
import com.lazovskyi.recordservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<Void> save(UserDto user) {
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> authenticate(AuthRequest authRequest) throws Exception {
        return ResponseEntity.ok().header(
                HttpHeaders.AUTHORIZATION, userService.authenticateUser(authRequest)
        ).build();
    }
}
