package com.example.security.mapper;

import com.example.security.dto.UserRequestDTO;
import com.example.security.entity.UserLoginProfile;
import com.example.security.dto.UserResponseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserLoginProfileMapper {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static UserLoginProfile toEntity(final UserRequestDTO dto) {
        return UserLoginProfile.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .build();
    }

    public static UserResponseDTO toResponse(final UserLoginProfile entity) {
        return UserResponseDTO.builder()
                .username(entity.getUsername())
                .build();
    }
}
