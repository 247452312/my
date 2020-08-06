# Docker image for {tomcat? springBoot?}
# VERSION {版本号}
# Author: uhyils

# docker stop prod-my-web
# docker rm prod-my-web
# docker rmi my-service/prod-my-web:latest
# docker build --build-arg version=0.0.5 --build-arg runEnv=prod -t my-service/prod-my-web .
# docker run -it -d  -p 8051:8051 -p 20851:20851 -v /my/logs:/my/logs --name prod-my-web my-service/prod-my-web:latest

FROM java:8

#作者
MAINTAINER uhyils <uhyils@qq.com>

ARG runEnv
ARG version

#挂载
VOLUME /my/logs

#暴露端口
EXPOSE 8051 20851

#应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD target/my-web-$version.jar /app.jar

#启动容器时的进程
ENTRYPOINT ["java","-Xms512m","-Xmx512m","-XX:+PrintGCTimeStamps","-XX:+UseG1GC","-XX:+HeapDumpOnOutOfMemoryError","-XX:HeapDumpPath=/my/logs/web/JVM_DOWN.dump","-XX:NewRatio=1","-Xloggc:/my/logs/web/JVM.log","-jar","/app.jar","--spring.profiles.active=$runEnv"]
