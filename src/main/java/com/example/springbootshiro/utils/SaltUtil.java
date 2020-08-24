package com.example.springbootshiro.utils;

import java.util.UUID;

/**
 * @Author 麦田守望者
 * @Date 2020/8/23 9:54
 * @Version 1.0
 */
public class SaltUtil {
    public static String getSalt(){
        return UUID.randomUUID().toString().split("-")[0];
    }

    public static void main(String[] args) {
        System.out.println(getSalt());
    }
}
