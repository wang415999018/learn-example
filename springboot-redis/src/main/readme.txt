redis要掌握的知识
一.数据结构
String
key - value

hash(map)
key - item ,value
      item ，value

list 可重复 可根据下标检索 有序
key - value,value,value

set 不可以重复 无序
key - value.value.value

sorted set 有序 不可重复
key - sort(排序)，value;sort(排序)，value

二.配置文件
spring boot
## Redis数据库索引（默认为0） 数据库索引号 index 用数字值指定，以 0 作为起始索引值 起到数据区分作用
spring.redis.database=0
## Redis服务器地址
spring.redis.host=127.0.0.1
## Redis服务器连接端口
spring.redis.port=6379
## Redis服务器连接密码（默认为空） redis-conf  requirepass 属性
#spring.redis.password=redis!123
## 连接池最大连接数（使用负值表示没有限制）单个应用程序中，接口的并发的连接数的1.5倍足够满足需求。
spring.redis.pool.max-active=8
## 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.pool.max-wait=60000
## 连接池中的最大空闲连接
spring.redis.pool.max-idle=20
## 连接池中的最小空闲连接
spring.redis.pool.min-idle=10
## 连接超时时间（毫秒）
spring.redis.timeout=5000


redis-conf  见redis-conf 文件

主要几点
bind 端口绑定
进程
日志级别  名称目录
数据库 选择
持久化规则  1.rdb 文件   2.aof 文件
master-slave  主从同步
AOF 更新写日志，防止宕机  数据丢失

三.消息队列
消息监听  com.example.redis.message.MsgRedisConfig
消息生产  com.example.redis.message.RedisClienSendMsg
消息消费  com.example.redis.message.MsgListenerHandle

四.分布式锁
com.example.redis.Lock.RedisLockHelper

五.redis 自定义序列化
虚拟机是否允许反序列化，不仅取决于类路径和功能代码是否一致，一个非常重要的一点是两个类的序列化 ID 是否一致（就是 private static final long serialVersionUID = 1L）
未定义serialVersionUID的值  类加载的时候会自动生产，如果有两个程序共享一个redis，这个时候反序列化就会出现问题。

当我们的数据存储到 Redis 的时候，我们的键（key）和 值（value）都是通过 Spring 提供的 Serializer 序列化到数据可中的。

RedisTemplate 默认使用的是 JdkSerializationRedisSerializer
StringRedisTemplate 默认使用的是 StringRedisSerializer

五.集群
Redis 官方提供了 redis-trib.rb 这个工具，就在解压目录的 src 目录中，第三步中已将它复制到 /usr/local/bin 目录中，可以直接在命令行中使用了。使用下面这个命令即可完成安装。

redis-trib.rb  create  --replicas  1  192.168.31.245:7000 192.168.31.245:7001  192.168.31.245:7002 192.168.31.210:7003  192.168.31.210:7004  192.168.31.210:7005
其中，前三个 ip:port 为第一台机器的节点，剩下三个为第二台机器。

等等，出错了。这个工具是用 ruby 实现的，所以需要安装 ruby。安装命令如下：

yum -y install ruby ruby-devel rubygems rpm-build
gem install redis
