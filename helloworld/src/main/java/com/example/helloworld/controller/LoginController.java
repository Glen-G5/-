package com.example.helloworld.controller;

import com.example.helloworld.entity.User;
import com.example.helloworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// 登录请求实体类
class LoginRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

@RestController
@CrossOrigin(origins = "http://localhost:8080") // 根据前端地址调整
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        Map<String, Object> response = new HashMap<>();
        User user = userService.getUserByUsername(username);

        if (user == null) {
            response.put("success", false);
            response.put("message", "用户不存在");
        } else {
            boolean isValid = userService.validateUser(username, password);
            response.put("success", isValid);
            response.put("message", isValid ? "登录成功" : "密码错误");
        }

        return response;
    }
}