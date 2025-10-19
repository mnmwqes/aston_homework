package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserDto(u.getId(), u.getName(), u.getEmail(), u.getAge()))
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(u -> new UserDto(u.getId(), u.getName(), u.getEmail(), u.getAge()))
                .orElse(null);
    }

    public UserDto createUser(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        userRepository.save(user);
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getAge());
    }

    public UserDto updateUser(Long id, UserDto dto) {
        return userRepository.findById(id).map(u -> {
            u.setName(dto.getName());
            u.setEmail(dto.getEmail());
            u.setAge(dto.getAge());
            userRepository.save(u);
            return new UserDto(u.getId(), u.getName(), u.getEmail(), u.getAge());
        }).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}