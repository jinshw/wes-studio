# FROM:指明当前镜像继承的基镜像,编译当前镜像时候会自动下载基镜像
FROM java:8u111-jdk
# MAINTAINER:当前镜像的作者和邮箱,使用空格隔开
MAINTAINER jinshw jinshw@xxx.com
# VOLUME:挂载目录
#VOLUME /ROOT
WORKDIR /ROOT
# ADD:从当前工作目录复制文件到镜像目录中并重新命名
ADD roadrule-prod.jar roadrule.jar
# RUN:在当前镜像上执行Linux命令,这里我执行了2个run指令,第二个run指令是为了解决容器和宿主机时间不一致的问题
RUN bash -c 'touch /roadrule.jar'
RUN bash -c 'mkdir /data'
RUN bash -c 'mkdir /data/nginx_data'
RUN bash -c 'mkdir /data/nginx_data/obj'

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone

# EXPOSE:监听的端口号
EXPOSE 8085

# ENTRYPOINT:让容器像一个可执行程序一样运行
#ENTRYPOINT ["java", "-jar", "mapserver.jar"]
CMD java -jar roadrule.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE}
#ENTRYPOINT ["java","-jar","roadrule.jar"]
#CMD ["--spring.profiles.active=prod"]