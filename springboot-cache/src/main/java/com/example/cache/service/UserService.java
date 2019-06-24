package com.example.cache.service;

import com.example.cache.dao.IUserDao;
import com.example.cache.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    @Autowired
    private IUserDao userDao;

    @Cacheable(key = "#name")
    public User findByName(String name){
        System.out.println("没有取缓存");
        return  userDao.findByName(name);
    }

    public User getUserByNickName(String nickName){
        return userDao.getUserByNickName(nickName);
    }

    @CachePut(key = "#user.name")
    public User saveUser(User user){

        userDao.save(user);
        return user;
    }

    @CacheEvict(key ="#name")
    public void deleteUser(String name){
        userDao.delete(userDao.findByName(name));
    }
}
