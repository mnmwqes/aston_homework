package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserRequest;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void testCreateUser() {
        UserRequest req = new UserRequest("Alice","alice@example.com",25);
        User saved = new User("Alice","alice@example.com",25);
        saved.setId(1L);
        saved.setCreatedAt(Instant.now());
        when(userRepository.save(any(User.class))).thenReturn(saved);

        UserDto dto = userService.createUser(req);
        assertNotNull(dto);
        assertEquals("Alice", dto.getName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        User u = new User("Bob","bob@example.com",30);
        u.setId(2L); u.setCreatedAt(Instant.now());
        when(userRepository.findById(2L)).thenReturn(Optional.of(u));

        UserDto dto = userService.getUserById(2L);
        assertEquals("Bob", dto.getName());
    }
}

