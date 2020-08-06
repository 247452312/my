# Docker image for {tomcat? springBoot?}
# VERSION {版本号}
# Author: uhyils
# docker stop prod-my-service-mongo
# docker rm prod-my-service-mongo
# docker rmi my-service/prod-my-service-mongo:latest
# docker build --build-arg version=0.0.5 --build-arg runEnv=prod -t my-service/prod-my-service-mongo .
# docker run -it -d  -p 8053:8057 -p 20853:20857 -v /my/logs:/my/logs --name prod-my-service-mongo my-service/prod-my-service-mongo:latest

FROM java:8

ARG runEnv
ARG version

#作者
MAINTAINER uhyils <uhyils@qq.com>

#应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD target/my-service-mongo-$version.jar /app.jar

#启动容器时的进程
CMD ["java","-XX:+UseG1GC -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/my/logs/mongo/JVM_DOWN.dump -Xms512m -Xmx512m -XX:NewRatio=1 -Xloggc:/my/logs/mongo/JVM.log","-jar","/app.jar","--spring.profiles.active=$runEnv"]
