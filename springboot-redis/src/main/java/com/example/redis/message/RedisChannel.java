package com.example.redis.message;

public interface RedisChannel {
    String getChannelName();

    String toJsonStr();

    RedisChannel JsonToObject();
}
