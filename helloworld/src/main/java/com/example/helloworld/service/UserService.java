package com.example.helloworld.service;

import com.example.helloworld.entity.User;

import java.util.Map;

public interface UserService {

//    登录
    boolean validateUser(String username, String password);

//    注册
    Map<String, Object> register(User user);

    User getUserByUsername(String username); // 新增方法
}