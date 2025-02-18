package com.example.helloworld.controller;

import com.example.helloworld.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    //http://www.baidu.com
    // http://localhost:8080/hello
    //http://localhost:8080/hello?nikename=zhangsan&phonenum=123
    @RequestMapping(value = "/hello",method= RequestMethod.GET)
    public String hello(String nikename,String phonenum){
        System.out.println(phonenum);
        return "你好啊" + nikename;
    }
    @RequestMapping(value = "/get1",method= RequestMethod.GET)
    public String get1(@RequestParam(value = "nikename", required = false) String name){
        return "你好啊" + name;
    }

    @RequestMapping(value = "/post1",method= RequestMethod.POST)
    public String post1( String username,String password){
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        return "你好啊";
    }

    @RequestMapping(value = "/post2",method= RequestMethod.POST)
    public String post2(User user){
        System.out.println(user);
        return "你好啊post2";
    }
    @RequestMapping(value = "/post3",method= RequestMethod.POST)
    public String post3(@RequestBody User user){
        System.out.println(user);
        return "你好啊post3";
    }
    @GetMapping("/test/**")
    public String test(){
        return "这是**的用法";
    }
}
