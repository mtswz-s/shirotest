package com.example.springbootshiro.dao;

import com.example.springbootshiro.domain.User;

public interface UserDao {
    public User findByName(String name);
}
