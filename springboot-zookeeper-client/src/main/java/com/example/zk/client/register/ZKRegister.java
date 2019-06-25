package com.example.zk.client.register;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ZKRegister {

    @Autowired
    private CuratorFramework zkClien;

    private static final String SERVER_PATH="/CLIENT";
    private static final int ZK_TIMEOUT=2000;

    public void register(String address){

    }
}
