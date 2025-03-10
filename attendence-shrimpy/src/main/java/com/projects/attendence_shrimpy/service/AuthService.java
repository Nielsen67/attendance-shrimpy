package com.projects.attendence_shrimpy.service;

import com.projects.attendence_shrimpy.entity.User;
import com.projects.attendence_shrimpy.model.LoginUserRequest;
import com.projects.attendence_shrimpy.model.TokenResponse;
import com.projects.attendence_shrimpy.repository.UserRepository;
import com.projects.attendence_shrimpy.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TokenResponse login(LoginUserRequest request) {
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password is not valid"));
        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {

            Long defaultExpiredAt = 3600000L;
            Long remainingTime;

            if (user.getToken() == null || user.getTokenExpiredAt() <= System.currentTimeMillis()) {
                // Generate a new token and set its expiration time
                user.setToken(UUID.randomUUID().toString());
                user.setTokenExpiredAt(System.currentTimeMillis()+ defaultExpiredAt); // 1 hour from current time
                remainingTime = defaultExpiredAt; // Full expiration time
                userRepository.save(user); // Save the updated user entity
            } else {
                // Token exists and is valid, calculate remaining time
                remainingTime = user.getTokenExpiredAt() - System.currentTimeMillis();
            }

            // Return the token and the remaining expiration time
            return TokenResponse.builder()
                    .token(user.getToken())
                    .tokenExpiredAt(remainingTime/1000) // Remaining time until expiration
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password is not valid");
        }
    }
    public void logout(User user){
        user.setToken(null);
        user.setTokenExpiredAt(null);

        userRepository.save(user);
    }

}
