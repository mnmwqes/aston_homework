package com.example.userservice;

import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserRequest;
import com.example.userservice.service.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping
    public ResponseEntity<EntityModel<UserDto>> createUser(@RequestBody UserRequest request) {
        UserDto created = userService.createUser(request);
        EntityModel<UserDto> model = EntityModel.of(created,
                linkTo(methodOn(UserController.class).getUser(created.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users"));
        return ResponseEntity.ok(model);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<UserDto>>> getAllUsers() {
        List<EntityModel<UserDto>> users = userService.getAllUsers().stream()
                .map(u -> EntityModel.of(u,
                        linkTo(methodOn(UserController.class).getUser(u.getId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users")))
                .collect(Collectors.toList());
        CollectionModel<EntityModel<UserDto>> collection = CollectionModel.of(users,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UserDto>> getUser(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        EntityModel<UserDto> model = EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUser(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users"));
        return ResponseEntity.ok(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UserDto>> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        UserDto updated = userService.updateUser(id, request);
        EntityModel<UserDto> model = EntityModel.of(updated,
                linkTo(methodOn(UserController.class).getUser(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users"));
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
