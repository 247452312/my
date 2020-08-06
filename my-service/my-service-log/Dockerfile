# Docker image for {tomcat? springBoot?}
# VERSION {版本号}
# Author: uhyils
# docker stop prod-my-service-log
# docker rm prod-my-service-log
# docker rmi my-service/prod-my-service-log:latest
# docker build --build-arg version=0.0.5 --build-arg runEnv=prod -t my-service/prod-my-service-log .
# docker run -it -d  -p 8053:8053 -p 20853:20853 -v /my/logs:/my/logs --name prod-my-service-log my-service/prod-my-service-log:latest

FROM java:8

#作者
MAINTAINER uhyils <uhyils@qq.com>


#挂载
VOLUME /my/logs

#暴露端口
EXPOSE 8053 20853

#应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD target/my-*.jar /

#启动容器时的进程
ENTRYPOINT ["java","-Xms512m","-Xmx512m","-XX:+PrintGCTimeStamps","-XX:+UseG1GC","-XX:+HeapDumpOnOutOfMemoryError","-XX:HeapDumpPath=/my/logs/log/JVM_DOWN.dump","-XX:NewRatio=1","-Xloggc:/my/logs/log/JVM.log","-jar","/app.jar","--spring.profiles.active=prod"]
