package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.dao.UserDao;
import com.example.springbootshiro.domain.User;
import com.example.springbootshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }
}
