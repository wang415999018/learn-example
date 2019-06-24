package com.example.redis.message;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public final class MsgListenerHandle implements MessageListener {

    private static List<RedisMessageAdapter> adapters = new ArrayList<>();

    private ThreadPoolTaskScheduler redisScheduler;
    @Override
    public void onMessage(Message message, byte[] bytes) {
         String channle = new String(bytes);
         if(adapters.size()>0){
             adapters.stream()
                     .filter(entity ->channle.equals(entity.getRedisChannel().getChannelName()))
                     .collect(Collectors.toList())
                     .forEach(item ->redisScheduler.execute(new RedisDoMessageThread(item,message.toString())));
         }
    }

    public static void subMessage(RedisMessageAdapter adapter){
        adapters.add(adapter);
    }
}
