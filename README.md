# my
### 项目简介
    基于docker的中间件: nacos(dubbo注册中心) mysql redis MongoDB rabbitmq zookeeper(dubbo原注册中心 已弃用)  nginx
    基于dubbo的微服务架构
    基于dubbo SPI的Filter熔断(Hystrix)
    基于redis的分布式锁 + 热点缓存技术
    基于rabbitmq的分布式事务
    基于mq的JVM内存监控 + JVM调优
    基于docker的mysql读写分离
    集中处理并转发请求的网关服务
    基于MongoDB的文件系统
    基于nginx的负载匀衡
    爬虫流量隔离
    Aop代理加token的方式实现不同用户的登录
    基于Github genkins docker 的自动化部署
    卷积神经网络识别图像
## 运行代码需要的环境
* java1.8环境
* nacos
* mysql
* mongoDB
* redis
* rabbitMQ
* 本地启动时需要加入 --spring.profiles.active=dev命令
