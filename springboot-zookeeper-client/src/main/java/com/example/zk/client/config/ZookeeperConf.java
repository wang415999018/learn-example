package com.example.zk.client.config;

import com.example.zk.client.register.ServiceDetail;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConf {
    @Value("${spring.cloud.zookeeper.connectString}")
    private String zkUrl;

    @Bean
    public CuratorFramework getCuratorFramework(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkUrl,retryPolicy);
        client.start();
        return client;
    }

    @Bean
    public ServiceDiscovery<ServiceDetail> getServiceDiscovery(CuratorFramework client){
        try {
            ServiceDiscovery<ServiceDetail> serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceDetail.class)
                    .client(client)
                    .basePath(ServiceDetail.SERVICE_ROOT_PATH)
                    .build();
            serviceDiscovery.start();
            return serviceDiscovery;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
