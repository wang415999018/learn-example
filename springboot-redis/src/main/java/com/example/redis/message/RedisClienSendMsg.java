package com.example.redis.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisClienSendMsg {
    @Autowired
    private static RedisTemplate redisTemplate;

    private RedisClienSendMsg(){}

    public static void redisSendMsg(RedisChannel channel){

        redisTemplate.convertAndSend(channel.getChannelName(), channel.toJsonStr());
    }
}
