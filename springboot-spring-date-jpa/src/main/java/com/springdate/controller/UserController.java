package com.springdate.controller;

import com.springdate.entity.User;
import com.springdate.jpa.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private IUserDao userDao;

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
        User user = userDao.findByName(name);
        System.out.println("name: "+user.getName());
        return user;
    }
}
