package com.example.springbootshiro.domain;

import java.io.Serializable;

/**
 * (TRole)实体类
 *
 * @author makejava
 * @since 2020-08-22 18:31:10
 */
public class TRole implements Serializable {
    private static final long serialVersionUID = -71812303369708799L;
    
    private Integer id;
    
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}