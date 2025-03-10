package com.projects.attendence_shrimpy.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.attendence_shrimpy.AttendenceShrimpyApplication;
import com.projects.attendence_shrimpy.model.LoginUserRequest;
import com.projects.attendence_shrimpy.model.WebResponse;
import com.projects.attendence_shrimpy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest(classes= AttendenceShrimpyApplication.class)
@AutoConfigureMockMvc
class AuthControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void loginUserNotFound() throws Exception{

        LoginUserRequest request = new LoginUserRequest();
        request.setUsername("Load111");
        request.setPassword("Load123");

        mockMvc.perform(
                post("/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
           WebResponse <String> response= objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
           assertNotNull(response.getMessage());
        });
    }
}