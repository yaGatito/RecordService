package com.lazovskyi.recordservice.api;

import com.lazovskyi.recordservice.dto.UserDto;
import com.lazovskyi.recordservice.model.AuthRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RequestMapping("/user")
public interface UserApi {

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> save(@RequestBody UserDto user);

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Void> authenticate(@RequestBody AuthRequest authRequest) throws Exception;
}
