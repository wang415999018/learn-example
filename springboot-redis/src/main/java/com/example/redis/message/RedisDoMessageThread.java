package com.example.redis.message;

public class RedisDoMessageThread implements Runnable{
    private RedisMessageAdapter redisMessageAdapter;
    private String message;

    public RedisDoMessageThread(RedisMessageAdapter redisMessageAdapter,String message){
        this.redisMessageAdapter = redisMessageAdapter;
        this.message = message;
    }
    @Override
    public void run() {
        redisMessageAdapter.doWork(message);
    }
}
