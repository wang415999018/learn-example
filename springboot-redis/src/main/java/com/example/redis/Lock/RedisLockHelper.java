package com.example.redis.Lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by toutou on 2019/1/27.
 */
@Component
public class RedisLockHelper {
    @Autowired
    private static RedisTemplate redisTemplate;

    private static final Long SUCCESS = 1L;

    /**
     * 获取锁
     * @param lockKey
     * @param value
     * @param expireTime：单位-秒
     * @return
     */
    public static boolean getLock(String lockKey, String value, int expireTime){
        boolean ret = false;
        try{
            String script = "if redis.call('setNx',KEYS[1],ARGV[1]) then if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end end";

            RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);

            Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey),value,expireTime);

            if(SUCCESS.equals(result)){
                return true;
            }

        }catch(Exception e){

        }
        return ret;
    }

    /**
     * 释放锁
     * @param lockKey
     * @param value
     * @return
     */
    public static boolean releaseLock(String lockKey, String value) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);

        Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value);
        if (SUCCESS.equals(result)) {
            return true;
        }

        return false;
    }
}