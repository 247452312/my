# my

my is a Java project for many technologies.

## 项目简介

* 基于docker的中间件: nacos(rpc注册中心) mysql redis MongoDB rabbitmq
* 基于DDD项目架构
* 应用内事件发布框架
* 基于my-rpc的微服务架构
* 基于my-rpc SPI的Filter熔断(Hystrix)
* 基于redis的分布式锁 + 热点自动发现技术
* 基于rabbitmq的分布式事务
* 基于mq的JVM内存监控 + JVM调优
* 基于docker的mysql读写分离
* 集中处理并转发请求的网关服务
* 基于MongoDB的文件系统
* 基于nginx的负载匀衡
* 爬虫流量隔离
* Aop代理加token的方式实现不同用户的登录
* 基于Github jenkins docker 的自动化部署
* 雪花算法生成id
* java实现的遗传算法与神经网络
* traceId日志链路跟踪
* 自定义异常断言
* 基于mybatis-plus的orm映射
* 在线服务降级
* java调用python模块

## Related projects(相关项目)
上述 my-rpc  项目见: [地址](https://github.com/247452312/my-rpc)

## Usage(如何使用项目)

### 运行代码需要的环境
* java1.8环境
* nacos
* mysql
* mongoDB
* redis
* rabbitMQ
* 本地启动时需要加入 --spring.profiles.active=dev命令

### 依次运行下列项目
user-log-web 即可最小化运行

## Contributing(贡献)
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.  
欢迎拉取请求。对于重大更改，请先打开一个问题，讨论您想要更改的内容  
Please make sure to update tests as appropriate.  
请确保根据需要更新测试  
Now I am the only one maintaining this project. I look forward to new people joining  
现在此项目只有我一个人在维护,期待有新人加入  
[贡献者公约](code_of_conduct.md)  
[如何贡献](CONTRIBUTING-template.md)

## License
[MIT](https://choosealicense.com/licenses/mit/)  
[本项目许可说明](LICENSE)
