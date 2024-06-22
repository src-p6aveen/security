package com.example.security.repository;

import com.example.security.entity.UserLoginProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginProfileRepository extends CrudRepository<UserLoginProfile,Long> {
    UserLoginProfile findByUsername(final String username);
}
