package com.example.security.controller;

import com.example.security.dto.JwtAuthenticationResponse;
import com.example.security.service.jwt.JwtBlacklistService;
import com.example.security.service.jwt.JwtTokenUtil;
import com.example.security.dto.LoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecurityContextLogoutHandler logoutHandler;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private @Autowired JwtBlacklistService jwtBlacklistService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
            );

            String token = jwtTokenUtil.generateToken(loginRequestDto.getUsername());
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            //this has to be db in the large user base. this for demo only.
            jwtBlacklistService.addToBlacklist(jwt);
            SecurityContextHolder.clearContext();        };
        return ResponseEntity.ok("User logged out successfully");
    }
}

