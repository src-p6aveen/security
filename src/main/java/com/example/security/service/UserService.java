package com.example.security.service;

import com.example.security.dto.UserRequestDTO;
import com.example.security.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO create(final UserRequestDTO rq);
}
