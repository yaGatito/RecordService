package com.lazovskyi.recordservice.service;

import com.lazovskyi.recordservice.dto.UserDto;
import com.lazovskyi.recordservice.model.AuthRequest;

public interface UserService {
    void save(UserDto user);

    String authenticateUser(AuthRequest authRequest) throws Exception;
}
