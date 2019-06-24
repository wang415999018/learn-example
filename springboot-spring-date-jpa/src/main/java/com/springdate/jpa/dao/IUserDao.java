package com.springdate.jpa.dao;

import com.springdate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserDao extends JpaRepository<User,Long> {

    public User findByName(String name);

    public User getUserByNickName(String NickName);

}
