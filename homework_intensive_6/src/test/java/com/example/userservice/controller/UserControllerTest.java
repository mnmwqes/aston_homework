package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserRequest;
import com.example.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateUser() throws Exception {
        UserDto dto = new UserDto(1L,"Alice","alice@example.com",25, Instant.now());
        UserRequest req = new UserRequest("Alice","alice@example.com",25);
        Mockito.when(userService.createUser(any(UserRequest.class))).thenReturn(dto);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.name").value("Alice"))
                .andExpect(jsonPath("$.content.email").value("alice@example.com"));
    }
}
