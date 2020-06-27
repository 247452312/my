# Docker image for {tomcat? springBoot?}
# VERSION {版本号}
# Author: uhyils
# docker stop prod-my-service-log
# docker rm prod-my-service-log
# docker rmi my-service/prod-my-service-log:latest
# docker build --build-arg version=0.0.3 --build-arg runEnv=prod -t my-service/prod-my-service-log .
# docker run -it -d  -p 8053:8053 -p 20853:20853 --name prod-my-service-log my-service/prod-my-service-log:latest java -jar /app.jar --spring.profiles.active=prod

FROM java:8

ARG runEnv
ARG version

#作者
MAINTAINER uhyils <uhyils@qq.com>

#应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD target/my-service-push-$version.jar /app.jar

#启动容器时的进程
CMD ["java","-jar","/app.jar","--spring.profiles.active=$runEnv"]
