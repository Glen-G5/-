package com.example.helloworld.service.Impl;

import com.example.helloworld.entity.User;
import com.example.helloworld.mapper.UserMapper;
import com.example.helloworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
//  登录
//    @Autowired // 确保已经配置了 BCryptPasswordEncoder 的 Bean
//    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
    @Override
    public boolean validateUser(String username, String password) {
        User user = userMapper.selectByUsername(username);
        // 使用 BCrypt 验证密码
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
//    注册
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Map<String, Object> register(User user) {
        Map<String, Object> result = new HashMap<>();

        // 校验参数
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "用户名不能为空");
            return result;
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "密码不能为空");
            return result;
        }

        // 检查用户名是否已存在
        User existingUser = userMapper.findByUsername(user.getUsername());
        if (existingUser != null) {
            result.put("success", false);
            result.put("message", "用户名已存在");
            return result;
        }

        try {
            // 加密密码
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            // 保存用户
            userMapper.insertUser(user);

            result.put("success", true);
            result.put("message", "注册成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "注册失败，请稍后重试");
            e.printStackTrace();
        }

        return result;
    }
}