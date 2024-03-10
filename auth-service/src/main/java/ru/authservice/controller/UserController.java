package ru.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.authservice.dto.Account;
import ru.authservice.dto.UserDto;
import ru.authservice.entity.User;
import ru.authservice.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Optional<Account>> getUserAccountById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getAccountClient(id));
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail())
        );
    }

    @DeleteMapping("/user-delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

}
