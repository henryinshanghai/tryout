FROM java:8 # 父镜像是Java 镜像版本为8

MAINTAINER henry # Dockerfile的作者是henry

ADD ch10docker-0.0.1-SNAPSHOT.jar app.jar # 把当前目录中的xxx.jar文件 复制到 镜像(的目录)中，并重命名为 app.jar

EXPOSE 8080 # 运行镜像时所得到的容器，需要监听8080端口

ENTRYPOINT ["java", "-jar", "/app.jar"] # 启动容器时需要执行的命令为：java -jar app.jar 【就是把每一个item依次拼起来】