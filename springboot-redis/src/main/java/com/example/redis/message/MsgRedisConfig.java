package com.example.redis.message;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class MsgRedisConfig {
    @Bean
    public RedisMessageListenerContainer initRedisContainer(RedisConnectionFactory connectionFactory, MsgListenerHandle msgListenerHandle){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(msgListenerHandle,new PatternTopic("*"));
        container.setTaskExecutor(redisScheduler());
        return container;
    }

    @Bean
    public ThreadPoolTaskScheduler redisScheduler(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        return scheduler;
    }
}
