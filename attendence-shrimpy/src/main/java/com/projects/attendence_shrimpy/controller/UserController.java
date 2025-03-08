package com.projects.attendence_shrimpy.controller;

import com.projects.attendence_shrimpy.entity.User;
import com.projects.attendence_shrimpy.model.*;
import com.projects.attendence_shrimpy.service.UserService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping(
            path = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public WebResponse<String> register (@RequestBody RegisterUserRequest request){
        userService.register(request);
        return WebResponse.<String>builder().status(200).message("OK").data(null).build();
    }

    @GetMapping(
            path = "/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> get (User user){
        UserResponse userResponse = userService.get(user);
        return WebResponse.<UserResponse>builder().status(200).data(userResponse).build();
    }

    @DeleteMapping(
            path = "/users/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete (User user, @PathVariable String username){
        userService.delete(user,username);
        return WebResponse.<String>builder().status(200).message("Delete success").data(null).build();
    }

    @PutMapping(
            path = "/users/current/change-password",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public WebResponse<String> updatePassword (User user, @RequestBody UpdatePasswordRequest request){
        userService.updatePassword(user, request);
        return WebResponse.<String>builder().status(200).message("Password changed").data(null).build();
    }

}
