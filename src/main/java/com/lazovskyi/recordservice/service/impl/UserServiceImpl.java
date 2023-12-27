package com.lazovskyi.recordservice.service.impl;

import com.lazovskyi.recordservice.service.JwtService;
import com.lazovskyi.recordservice.dto.UserDto;
import com.lazovskyi.recordservice.model.AuthRequest;
import com.lazovskyi.recordservice.model.User;
import com.lazovskyi.recordservice.repository.UserRepository;
import com.lazovskyi.recordservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public void save(UserDto userDto) {
        User user = User.builder().
                username(userDto.getUsername()).
                password(passwordEncoder.encode(userDto.getPassword())).
                build();

        userRepository.save(user);
    }

    @Override
    public String authenticateUser(AuthRequest authRequest) {
        // Authenticate using the provided username and password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );

        // If authentication is successful, a JWT is directly generated and returned
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        return jwtService.generateToken(userDetails);
    }
}

