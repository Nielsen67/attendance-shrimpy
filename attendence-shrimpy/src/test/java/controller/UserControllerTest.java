package controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.attendence_shrimpy.AttendenceShrimpyApplication;
import com.projects.attendence_shrimpy.model.DeleteUserRequest;
import com.projects.attendence_shrimpy.model.RegisterUserRequest;
import com.projects.attendence_shrimpy.model.WebResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.projects.attendence_shrimpy.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes= AttendenceShrimpyApplication.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testRegisterSuccess() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("TEST DELETE");
        request.setPassword("Load#123");
        request.setName("Nielsen");
        request.setPhone("082282557944");

        mockMvc.perform(
                        post("/users")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isOk())
                .andDo(result -> {
                    WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {});
                    assertEquals("OK", response.getMessage());
                });
    }

    @Test
    void deleteUser() throws Exception {
        DeleteUserRequest request = new DeleteUserRequest();
        request.setUsername("DeleteUserRequest");
        mockMvc.perform(
                        delete("/users")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isOk())
                .andDo(result -> {
                    WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {});
                    assertEquals("Delete success", response.getMessage());
                });
    }

    @Test
    void deleteUserNotExists() throws Exception {
        DeleteUserRequest request = new DeleteUserRequest();
        request.setUsername("DeleteUserRequest");
        mockMvc.perform(
                        delete("/users")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isNotFound())
                .andDo(result -> {
                    WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {});
                    assertNotEquals("Delete success", response.getMessage());
                });
    }

}