package com.example.redis.controller;

import com.example.redis.entity.User;
import com.example.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/save")
    public User save(){
        User user1 = new User("张一","12345");
        User user2 = new User("张二","123456");
        User user3 = new User("张三","123457");
        Map<String,Object> userMap = new HashMap<String, Object>() {
            {
                put(user1.getUserName(),user1);
                put(user2.getUserName(),user2);
                put(user3.getUserName(),user3);
            }
        };
        List<Object> userList = new ArrayList<Object>(){
            {
                add(user1);
                add(user2);
                add(user3);
            }
        };


        //字符串类型
        redisUtil.set("str",user1);
        System.out.println("String类型:"+ ((User)redisUtil.get("str")).getUserName());

        //hashMap  key-value
        redisUtil.hset("set","user1",user1);
        redisUtil.hset("set","user2",user2);
        redisUtil.hset("set","user3",user3);
        System.out.println("set.user1:"+redisUtil.hget("set","user1"));
        System.out.println("set.user2:"+redisUtil.hget("set","user3"));
        System.out.println("set.user3:"+redisUtil.hget("set","user3"));
        redisUtil.hmset("map",userMap);
        System.out.println("map:"+redisUtil.hmget("map").toString());

        //set  不重复 查询 判定
        redisUtil.sSet("user",user1,user2,user3,user1,user3);
        System.out.println("set.user:"+redisUtil.sGet("user"));

        //list 重复 排序
        redisUtil.lSet("user1",user1);
        redisUtil.lSet("user1",userList);
        redisUtil.lSet("user1",user2);
        redisUtil.lSet("list",userList);
        System.out.println("list.user1:"+redisUtil.lGet("user1",0,5).toString());
        return user1;
    }
}
