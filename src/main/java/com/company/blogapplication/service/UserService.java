package com.company.blogapplication.service;


import com.company.blogapplication.dto.RegistrationDto;
import com.company.blogapplication.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
