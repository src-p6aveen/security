package com.example.security.controller;

import com.example.security.service.UserService;
import com.example.security.dto.UserRequestDTO;
import com.example.security.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/user")
    public ResponseEntity<UserResponseDTO> createUser(final @Valid @RequestBody UserRequestDTO rq) {
        return new ResponseEntity<>(this.service.create(rq), HttpStatus.CREATED);
    }
}
