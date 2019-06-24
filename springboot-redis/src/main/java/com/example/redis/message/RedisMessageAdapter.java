package com.example.redis.message;

public interface RedisMessageAdapter {

    RedisChannel getRedisChannel();

    void doWork(String message);

}
