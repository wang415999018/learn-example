package com.example.cache.controller;

import com.example.cache.dao.IUserDao;
import com.example.cache.entity.User;
import com.example.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    public User save(){
        User user  = new User();
        user.setName("zhangsan");
        user.setNickName("张三");
        user.setPassword("123456");
        userService.saveUser(user);
        return user;
    }

    @RequestMapping("/findUser/{name}")
    @ResponseBody
    public User findUser(@PathVariable("name") String name){
        User user = userService.findByName(name);
       // System.out.println("name: "+user.getName());
        return user;
    }

    @RequestMapping("/delete/{name}")
    public void deleteUser(@PathVariable("name") String name){
        userService.deleteUser(name);
    }


}
