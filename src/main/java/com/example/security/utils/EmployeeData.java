package com.example.security.utils;

import com.example.security.dto.EmployeeDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeData {
    private EmployeeData(){}

    public static List<EmployeeDto> getPets() {
        return Stream.of(EmployeeDto.builder().name("praveen").build(),
                EmployeeDto.builder().name("kamal").build(),
                EmployeeDto.builder().name("kamal2").build(),
                EmployeeDto.builder().name("kamal3").build()).collect(Collectors.toList());
    }
}
