package com.projects.attendence_shrimpy.controller;

import com.projects.attendence_shrimpy.entity.User;
import com.projects.attendence_shrimpy.model.LoginUserRequest;
import com.projects.attendence_shrimpy.model.TokenResponse;
import com.projects.attendence_shrimpy.model.WebResponse;
import com.projects.attendence_shrimpy.repository.UserRepository;
import com.projects.attendence_shrimpy.service.AuthService;
import com.projects.attendence_shrimpy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(
            path = "/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public WebResponse<TokenResponse> login (@RequestBody LoginUserRequest request){
        TokenResponse tokenResponse = authService.login(request);
        return WebResponse.<TokenResponse>builder().status(200).message("Login Success").data(tokenResponse).build();
    }

    @DeleteMapping(
            path = "/auth/logout",
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public WebResponse<String> logout (User user){
        authService.logout(user);
        return WebResponse.<String>builder().status(200).message("Logout Success").data(null).build();
    }
    
}
