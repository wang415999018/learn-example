package com.example.consumer.controller;

import com.example.consumer.entity.User;
import com.example.consumer.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @GetMapping("/findUser/{name}")
    public String findUser(@PathVariable("name") String name){
       return restTemplate.getForEntity("http://USER-SERVICE/findUser/"+name,String.class).getBody();

    }

    @GetMapping("/findUserF/{name}")
    public User findUserByName(@PathVariable("name") String name){
        return userService.findUserByName(name);
    }
}
