package com.example.zk.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class ZKLockAspetc {

    @Autowired
    private CuratorFramework zkClien;

    @Pointcut("execution(* com.example.zk.server.*(..))")
    public void excuteServcie(){}

    @Around("excuteServcie()")
    public void around(ProceedingJoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        try {
            if(targetMethod.isAnnotationPresent(ZKClusterLock.class)){
                ZKClusterLock zkClusterLock = targetMethod.getAnnotation(ZKClusterLock.class);
                String path = zkClusterLock.value();

                //过去zk 分布式锁
                InterProcessMutex lock = new InterProcessMutex(zkClien,path);
                if (lock.acquire(2*60, TimeUnit.SECONDS)){
                    try {
                        joinPoint.proceed();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        lock.release();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
