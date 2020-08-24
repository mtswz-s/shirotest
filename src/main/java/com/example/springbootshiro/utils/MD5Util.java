package com.example.springbootshiro.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @Author 麦田守望者
 * @Date 2020/8/22 14:38
 * @Version 1.0
 */
public class MD5Util {

    public static String getMD5pwd(String pwd ,String salt){
        Md5Hash md5Hash = new Md5Hash(pwd,salt,20);
        String s = md5Hash.toHex();
        return s;
    }
    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("abcd","uid5%#(",20);
        String s = md5Hash.toHex();
        System.out.println(s);
    }
}
