package com.example.security.controller;

import com.example.security.utils.EmployeeData;
import com.example.security.dto.EmployeeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @GetMapping("/employee")
    public List<EmployeeDto> getAllEmployee() {
        return EmployeeData.getPets();
    }
}
