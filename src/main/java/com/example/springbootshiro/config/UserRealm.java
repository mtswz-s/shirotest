package com.example.springbootshiro.config;

import com.example.springbootshiro.cache.MySalt;
import com.example.springbootshiro.domain.TPerm;
import com.example.springbootshiro.domain.TRole;
import com.example.springbootshiro.domain.TUser;
import com.example.springbootshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");
        Subject subject = SecurityUtils.getSubject();
        //获取并添加角色
        TRole role = userService.getRole((String) subject.getPrincipal());
        info.addRole(role.getName());

        List<TPerm> perms = userService.getPerms(role.getId());
        perms.forEach((perm)->{
            info.addStringPermission(perm.getPerm());
        });
        System.out.println("执行了授权。。。。。");
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证。。。。。");
        UsernamePasswordToken token= (UsernamePasswordToken)authenticationToken;
        TUser user = userService.login(token.getUsername(),new String(token.getPassword()));
        System.out.println("==========="+user+"============");
        if (!ObjectUtils.isEmpty(user)) {
            SecurityUtils.getSubject().getSession().setAttribute("user",user);
            return new SimpleAuthenticationInfo(user.getName(),user.getPwd(), new MySalt(user.getSalt()),getName());
        }
        return null;
    }
}
