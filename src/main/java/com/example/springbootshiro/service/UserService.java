package com.example.springbootshiro.service;

import com.example.springbootshiro.domain.User;

public interface UserService {
    public User findByName(String name);
}
