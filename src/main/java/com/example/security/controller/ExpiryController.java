package com.example.security.controller;


import com.example.security.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExpiryController {

    @GetMapping("/session-expired")
    public ResponseEntity<ErrorDto> expiry() {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode("9990");
        errorDto.setErrorDescription("Error Due to Concurrent Login");
        return new ResponseEntity<>(errorDto, HttpStatus.UNAUTHORIZED);
    }
}
