1、fetch-registry：表示是否从eureka server获取注册信息，如果是单一节点，不需要同步其他eureka server节点，则可以设置为false，但此处为集群，应该设置为true，默认为true，可不设置。

　　2、register-with-eureka：表示是否将自己注册到eureka server，因为要构建集群环境，需要将自己注册到及群众，所以应该开启。默认为true，可不显式设置