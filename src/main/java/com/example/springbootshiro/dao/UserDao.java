package com.example.springbootshiro.dao;

import com.example.springbootshiro.domain.TPerm;
import com.example.springbootshiro.domain.TRole;
import com.example.springbootshiro.domain.TUser;

import java.util.List;

public interface UserDao {
    TUser findByName(String name);

    TRole findRoleByUserName(String name);

    List<TPerm> findPramByRoleId(Integer roleId);

    void insert(TUser user);

    TUser findByUserNameAndPwd(String name, String pwd);




}
