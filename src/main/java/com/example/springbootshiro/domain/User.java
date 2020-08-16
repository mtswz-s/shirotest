package com.example.springbootshiro.domain;

import java.io.Serializable;

public class User implements Serializable {

    private Integer id;

    private String name;

    private String pwd;

    private String prams;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", prams='" + prams + '\'' +
                '}';
    }

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPrams() {
        return prams;
    }

    public void setPrams(String prams) {
        this.prams = prams;
    }

    public User(Integer id, String name, String pwd, String prams) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.prams = prams;
    }
}
