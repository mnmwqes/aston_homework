package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserRequest;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public UserDto createUser(UserRequest request) {
        User user = new User(request.getName(), request.getEmail(), request.getAge());
        User saved = userRepository.save(user);
        return toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        return userRepository.findById(id).map(this::toDto).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDto updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (request.getName() != null) user.setName(request.getName());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getAge() != null) user.setAge(request.getAge());
        return toDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto toDto(User u) {
        return new UserDto(u.getId(), u.getName(), u.getEmail(), u.getAge(), u.getCreatedAt());
    }
}
