package com.example.userservice.dto;

import java.time.Instant;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private Instant createdAt;

    public UserDto() {}

    public UserDto(Long id, String name, String email, Integer age, Instant createdAt) {
        this.id = id; this.name = name; this.email = email; this.age = age; this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
