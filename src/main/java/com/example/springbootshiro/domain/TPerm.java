package com.example.springbootshiro.domain;

import java.io.Serializable;

/**
 * (TPerm)实体类
 *
 * @author makejava
 * @since 2020-08-22 18:31:08
 */
public class TPerm implements Serializable {
    private static final long serialVersionUID = -38542262775073620L;
    
    private Integer id;
    
    private String perm;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

}