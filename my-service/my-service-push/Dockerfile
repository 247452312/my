# Docker image for {tomcat? springBoot?}
# VERSION {版本号}
# Author: uhyils
# docker stop prod-my-service-push
# docker rm prod-my-service-push
# docker rmi my-service/prod-my-service-push:latest
# docker build --build-arg version=0.0.5 --build-arg runEnv=prod -t my-service/prod-my-service-push .
# docker run -it -d  -p 8056:8056 -p 20856:20856 -v /my/logs:/my/logs --name prod-my-service-push my-service/prod-my-service-push:latest

FROM java:8

ARG runEnv
ARG version

#作者
MAINTAINER uhyils <uhyils@qq.com>

#应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD target/my-service-push-$version.jar /app.jar

#启动容器时的进程
CMD ["java","-XX:+UseG1GC -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/my/logs/push/JVM_DOWN.dump -Xms512m -Xmx512m -XX:NewRatio=1 -Xloggc:/my/logs/push/JVM.log","-jar","/app.jar","--spring.profiles.active=$runEnv"]
