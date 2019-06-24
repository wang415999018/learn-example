常用 Spring Cache 缓存注解

一旦配置好 Spring Boot 缓存，就可以在 Spring 管理的 Bean 中使用缓存注解，通常可以直接放在 Service 类上。

@CacheConfig，在类上设置当前缓存的一些公共设置，比如缓存名称；

@Cacheable，作用在方法上，触发缓存读取操作。表明该方法的结果是可以缓存的，如果缓存存在，则目标方法不会被调用，直接取出缓存。可以为方法声明多个缓存，如果至少有一个缓存有缓存项，则其缓存项将被返回；

@CacheEvice，作用在方法上，触发缓存失效操作，删除缓存项或者清空缓存；

@CachePut，作用在方法上，触发缓存更新操作，添加该注解后总是会执行方法体，并且使用返回的结果更新缓存，同 Cacheable 一样，支持 condition、unless、key 选项，也支持 KeyGenerator；

@Caching，作用在方法上，综合上面的各种操作，在有些场景上，调用业务会触发多种缓存操作。

通常清空下，直接使用 SpEL 表达式来指定缓存项的 Key 比自定义一个 KeyGenerator 更为简单。

单机版

spring.cache.type=SIMPLE    SIMPLE springboot 单机本地缓存  key 缓存可以   return 结果  缓存value    类似于currentHashMap

spring.cache.type=ehcache  本地缓存 集成ehcache

Ehcache.xml 文件配置详解：

diskStore：为缓存路径，ehcache分为内存和磁盘两级，此属性定义磁盘的缓存位置。
defaultCache：默认缓存策略，当ehcache找不到定义的缓存时，则使用这个缓存策略。只能定义一个。 name:缓存名称。
maxElementsInMemory:缓存最大数目 maxElementsOnDisk：硬盘最大缓存个数。
eternal:对象是否永久有效，一但设置了，timeout将不起作用。 overflowToDisk:是否保存到磁盘，当系统当机时
timeToIdleSeconds:设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。缓存创建以后，最后一次访问缓存的日期至失效之时的时间间
timeToLiveSeconds:设置对象在失效前允许存活时间（单位：秒）。最大时间介于创建时间和失效时间之间。仅当eternal=false对象不是永久有效时使用，默认是0.，也就是对象存活时间无穷大。缓存自创建日期起至失效时的间隔时间x
diskPersistent：是否缓存虚拟机重启期数据 Whether the disk store persists between restarts of the Virtual Machine. The default value is false.
diskSpoolBufferSizeMB：这个参数设置DiskStore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区。
diskExpiryThreadIntervalSeconds：磁盘失效线程运行时间间隔，默认是120秒。
memoryStoreEvictionPolicy：当达到maxElementsInMemory限制时，Ehcache将会根据指定的策略去清理内存。默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。
clearOnFlush：内存数量最大时是否清除。
maxEntriesLocalHeap   堆内存大小