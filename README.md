# my
### 项目简介
    自己磨练技能的地方,知道的东西全都加进去
## 运行代码需要的环境
* java1.8环境
* zookeeper
* mysql
* 本地启动时需要加入 --spring.profiles.active=dev命令
## 使用说明
zookeeper配置在dubbo.xml中
mysql配置在application.properties中
### 项目结构  
    * my-api ------  微服务提供的api   
    * my-common ------  公共类 包含了所有模块都需要的类,包括  :
        * 默认Dao 包含增删改 以及通过id查询
        * 服务提供者的responseCode
        * 服务消费者查询的规范请求
        * 服务提供者返回的标准回复
        * Entity默认继承的类->DataEntity 包含createDate,updateDate,delMark,remark  
    * my-dubbo-admin >------ dubbo控制台
    * my-service ------ 微服务,即服务提供者
    * my-web ----- 整个项目前台模块

### dubbo泛化调用
* 调用api为 http://localhost:8083/action
* post请求
* json为: {
                 "interfaceName": "XxxService",
                 "methodName": "xx",
                 "args": {
                     "paging": true,
                     "args": {
                         "name": "id",
                         "symbol": "=",
                         "data": "id"
                     }
                 }
             }
### 请求链路跟踪

