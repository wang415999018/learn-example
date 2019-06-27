package com.example.zk.client.controller;

import com.example.zk.client.register.ServiceDetail;
import com.example.zk.client.register.ZKRegister;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceInstanceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Collection;

@RestController
public class testController {

    @Autowired
    private CuratorFramework client;

    @Autowired
    private ZKRegister zkRegister;
    @Autowired
    private ServiceDiscovery<ServiceDetail> serviceDiscovery;

    @GetMapping("/getService")
    public String getService(){
        String serviceStr = null;

        try {
            //根据名称获取服务
            Collection<String> serviceNames = serviceDiscovery.queryForNames();
            for(String name : serviceNames){
                Collection<ServiceInstance<ServiceDetail>> services = serviceDiscovery.queryForInstances(name);
                for(ServiceInstance<ServiceDetail> service : services) {
                    System.out.println(name);
                    System.out.println(service.getPayload().getDesc());
                    System.out.println(service.getAddress() + "\t" + service.getPort());
                    System.out.println("---------------------");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return serviceStr;
    }

    @GetMapping("/registerService")
    public String registerService()throws Exception{
        ServiceInstanceBuilder<ServiceDetail> sib = ServiceInstance.builder();
        sib.address("192.168.1.100");
        sib.port(8866);
        sib.name("tomcat");
        sib.payload(new ServiceDetail("主站web程序", 2));
        zkRegister.register(sib);

        ServiceInstanceBuilder<ServiceDetail> sib1 = ServiceInstance.builder();
        sib1.address("192.168.1.101");
        sib1.port(8867);
        sib1.name("tomcat1");
        sib1.payload(new ServiceDetail("主站web程序", 2));
        zkRegister.register(sib1);

        ServiceInstanceBuilder<ServiceDetail> sib2 = ServiceInstance.builder();
        sib2.address("192.168.1.102");
        sib2.port(8868);
        sib2.name("tomcat1");
        sib2.payload(new ServiceDetail("主站web程序", 2));
        zkRegister.register(sib2);
        return null;
    }
}
