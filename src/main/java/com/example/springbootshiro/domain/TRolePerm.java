package com.example.springbootshiro.domain;

import java.io.Serializable;

/**
 * (TRolePerm)实体类
 *
 * @author makejava
 * @since 2020-08-22 18:31:10
 */
public class TRolePerm implements Serializable {
    private static final long serialVersionUID = -20780079204056879L;
    
    private Integer id;
    
    private Integer roleId;
    
    private Integer permId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermId() {
        return permId;
    }

    public void setPermId(Integer permId) {
        this.permId = permId;
    }

}