# Docker image for {tomcat? springBoot?}
# VERSION {版本号}
# Author: uhyils

FROM java:8

#作者
MAINTAINER uhyils <uhyils@qq.com>

#系环境变量
#ENV {环境变量名称}={值}

#声明一个挂载点，容器内此路径会对应宿主机的某个文件夹
VOLUME /usr/local/docker_data/my_service_user

#应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD target/my-service-user-0.0.2.jar my-service-user-0.0.2.jar

#启动容器时的进程
ENTRYPOINT ["java","-jar","/my-service-user-0.0.2.jar"]

#暴露8080端口
EXPOSE 8080