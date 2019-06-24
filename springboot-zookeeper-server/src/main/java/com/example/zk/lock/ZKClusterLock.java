package com.example.zk.lock;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZKClusterLock {
    String value() ;
}
