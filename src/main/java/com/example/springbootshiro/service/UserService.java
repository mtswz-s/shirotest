package com.example.springbootshiro.service;

import com.example.springbootshiro.domain.TPerm;
import com.example.springbootshiro.domain.TRole;
import com.example.springbootshiro.domain.TUser;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sun.xml.internal.ws.api.pipe.Tube;

import java.util.List;

public interface UserService {
    TUser findByName(String name);

    TRole getRole(String name);

    List<TPerm> getPerms(Integer roleId);

    TUser register(TUser user);

    TUser login(String  userName, String pwd);

    DefaultKaptcha getDk();
}
