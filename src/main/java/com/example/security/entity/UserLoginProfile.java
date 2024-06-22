package com.example.security.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginProfile {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
