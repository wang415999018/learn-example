package com.example.producer.controller;

import com.example.producer.dao.IUserDao;
import com.example.producer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/save")
    public User save(){
        User user  = new User();
        user.setName("zhangsan");
        user.setNickName("张三");
        user.setPassword("123456");
        userDao.save(user);
        return user;
    }

    @RequestMapping("/findUser/{name}")
    @ResponseBody
    public User findUser(@PathVariable("name") String name){
        List<ServiceInstance> instances = client.getInstances("USER-SERVICE");
        for(ServiceInstance instance : instances){
            System.out.println(instance.getHost()+instance.getPort()+instance.getUri());
        }
        User user = userDao.findByName(name);
        System.out.println("name: "+user.getName());
        return user;
    }
}
