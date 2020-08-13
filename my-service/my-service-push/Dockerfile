# Docker image for {tomcat? springBoot?}
# VERSION {版本号}
# Author: uhyils
# docker stop prod-my-service-push
# docker rm prod-my-service-push
# docker rmi my-service/prod-my-service-push:latest
# docker build -t my-service/prod-my-service-push .
# docker run -it -d  -p 8056:8056 -p 20856:20856 -v /my/logs:/my/logs --name prod-my-service-push my-service/prod-my-service-push:latest

FROM java:8

#作者
MAINTAINER uhyils <uhyils@qq.com>


#挂载
VOLUME /my/logs

#暴露端口
EXPOSE 8056 20856

#应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD target/my-*.jar /app.jar

#启动容器时的进程
ENTRYPOINT ["java","-Xms512m","-Xmx512m","-XX:+PrintGCTimeStamps","-XX:+UseG1GC","-XX:+HeapDumpOnOutOfMemoryError","-XX:HeapDumpPath=/my/logs/push/JVM_DOWN.dump","-XX:NewRatio=1","-Xloggc:/my/logs/push/JVM.log","-jar","/app.jar","--spring.profiles.active=prod"]
