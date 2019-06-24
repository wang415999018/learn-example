package com.example.mongodb.entity;

import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;

public class User implements Serializable,IMongoEntity {

    private String id;
    private String userName;
    private String nickName;
    private int age;
    private String password;

    public User(){}

    public User(String userName , String password){
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Query getQueryForUpdateById() {
        return null;
    }

    @Override
    public Query getQuery() {
        Query query = new Query();
        if(this.id != null){
            query.addCriteria(Criteria.where("id").is(this.id));
        }
        if(this.age != 0){
            query.addCriteria(Criteria.where("age").is(this.age));
        }
        if(this.userName !=null){
            query.addCriteria(Criteria.where("userName").is(this.userName));
        }
        if(this.nickName != null){
            query.addCriteria(Criteria.where("nickName").is(this.nickName));
        }
        if(this.password != null){
            query.addCriteria(Criteria.where("pssword").is(this.password));
        }
        return query;
    }
}
