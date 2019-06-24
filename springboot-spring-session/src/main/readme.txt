a.通过nginx的负载均衡其中一种ip绑定来实现（通过ip绑定服务器其中一台，就没有集群概念了）；
b.通过cookie备份session实现(因为cookie数据保存在客户端，所以不安全);
c.通过redis备份session实现（可靠）；

开启 @EnableRedisHttpSession