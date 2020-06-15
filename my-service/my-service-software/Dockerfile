# Docker image for {tomcat? springBoot?}
# VERSION {版本号}
# Author: uhyils

FROM java:8

ARG runEnv
ARG version

#作者
MAINTAINER uhyils <uhyils@qq.com>

#应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD target/my-service-software-$version.jar /app.jar

#启动容器时的进程
CMD ["java","-jar","/app.jar","--spring.profiles.active=$runEnv"]
