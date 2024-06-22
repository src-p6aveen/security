package com.example.security.service.impl;

import com.example.security.dto.UserRequestDTO;
import com.example.security.entity.UserLoginProfile;
import com.example.security.mapper.UserLoginProfileMapper;
import com.example.security.repository.UserLoginProfileRepository;
import com.example.security.service.UserService;
import com.example.security.dto.UserResponseDTO;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserLoginProfileRepository repository;

    @Override
    public UserResponseDTO create(final UserRequestDTO rq) {
        final UserLoginProfile userLoginProfile = UserLoginProfileMapper.toEntity(rq);
        final UserLoginProfile result = this.repository.save(userLoginProfile);
        return UserLoginProfileMapper.toResponse(result);
    }
}
