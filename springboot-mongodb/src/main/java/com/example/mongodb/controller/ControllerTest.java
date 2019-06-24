package com.example.mongodb.controller;

import com.example.mongodb.dao.MongodbService;
import com.example.mongodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ControllerTest {

    @Autowired
    private MongodbService mongodbService;

    @RequestMapping("/test")
    public Object save(){
        User user = new User();
        user.setAge(25);
        user.setId("1");
        user.setNickName("小王");
        user.setUserName("xiaowang");
        user.setPassword("123456");
        //mongodbService.save(user);
        //return mongodbService.find(user).get(0);
        Map<String,String> map = new HashMap<>();
        map.put("id","1");
        User user1 = (User)(mongodbService.findByMapForAnd(map,User.class).get(0));
        System.out.println(user1.getNickName());
        return user1;
    }
}
