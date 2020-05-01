# Docker image for {tomcat? springBoot?}
# VERSION {版本号}
# Author: uhyils

FROM java:8

#作者
MAINTAINER uhyils <uhyils@qq.com>

#应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD target/my-web-0.0.2.jar /app.jar

#启动容器时的进程
ENTRYPOINT ["java","-jar","/app.jar"]

#暴露8082端口
EXPOSE 8080 20880