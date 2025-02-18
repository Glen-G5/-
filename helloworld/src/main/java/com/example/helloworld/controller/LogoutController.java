package com.example.helloworld.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LogoutController {

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 使Session失效
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            response.put("success", true);
            response.put("message", "注销成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "注销失败");
            return ResponseEntity.status(500).body(response);
        }
    }
}