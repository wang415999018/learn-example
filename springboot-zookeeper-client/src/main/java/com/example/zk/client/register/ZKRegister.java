package com.example.zk.client.register;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceInstanceBuilder;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ZKRegister {

    @Autowired
    private CuratorFramework zkClien;

    @Autowired
    private ServiceDiscovery<ServiceDetail> serviceDiscovery;

    private static final String SERVER_PATH="/CLIENT";
    private static final int ZK_TIMEOUT=2000;

    public void register(ServiceInstanceBuilder<ServiceDetail> sib) {
        try{
            ServiceInstance<ServiceDetail> instance = sib.build();
            //服务注册
            serviceDiscovery.registerService(instance);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
