package com.example.consumer.server;

import com.example.consumer.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="USER-SERVICE")
public interface UserService {

    @RequestMapping(value = "/findUser/{name}", method= RequestMethod.GET)
    public User findUserByName(@PathVariable("name") String name);
}
