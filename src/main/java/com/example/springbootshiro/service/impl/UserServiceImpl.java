package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.dao.UserDao;
import com.example.springbootshiro.domain.TPerm;
import com.example.springbootshiro.domain.TRole;
import com.example.springbootshiro.domain.TUser;
import com.example.springbootshiro.service.UserService;
import com.example.springbootshiro.utils.MD5Util;
import com.example.springbootshiro.utils.SaltUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public TUser findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public TRole getRole(String userName) {
        return userDao.findRoleByUserName(userName);
    }

    @Override
    public List<TPerm> getPerms(Integer roleId) {
        return userDao.findPramByRoleId(roleId);
    }

    @Override
    public TUser register(TUser user) {
        String salt = SaltUtil.getSalt();
        String md5pwd = MD5Util.getMD5pwd(user.getPwd(), salt);
        user.setPwd(md5pwd);
        user.setSalt(salt);
        user.setRoleId(2);
        userDao.insert(user);
        return user;
    }

    @Override
    public TUser login(String userName, String wPwd) {
        TUser byName = this.findByName(userName);
        if(byName == null){
            throw new UnknownAccountException();
        }
        String pwd = MD5Util.getMD5pwd(wPwd,byName.getSalt());
        TUser byUserNameAndPwd = userDao.findByUserNameAndPwd(userName, pwd);
        if(byUserNameAndPwd == null){
            throw new IncorrectCredentialsException();
        }
        return byUserNameAndPwd;
    }

    @Override
    public DefaultKaptcha getDk() {
        DefaultKaptcha dk = new DefaultKaptcha();
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty("kaptcha.border", "yes");
        // 边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        // 字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "red");
        // 图片宽
        properties.setProperty("kaptcha.image.width", "110");
        // 图片高
        properties.setProperty("kaptcha.image.height", "40");
        // 字体大小
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        // session key
        properties.setProperty("kaptcha.session.key", "code");
        // 验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        // 字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        //
        properties.setProperty("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        dk.setConfig(config);
        return dk;
    }
}
