package com.example.consumer.controller;

import com.example.consumer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/findUser/{name}")
    public String findUser(@PathVariable("name") String name){
       return restTemplate.getForEntity("http://USER-SERVICE/findUser/"+name,String.class).getBody();

    }
}
