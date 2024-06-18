# imooc-microservice

- docker与k8s实战：[https://coding.imooc.com/class/198.html](https://coding.imooc.com/class/198.html)

## 功能简介

- 用户服务
  - 用户登录
  - 用户注册
  - 用户基本信息查询
  - 无状态，无session
  - 单点登录
- 课程服务
  - 登录验证
  - 课程curd
- 信息服务
  - 发送邮件
  - 发送短信
- 用户edgeservice
- 课程edgeservice
- API Gateway


```dockerfile
# 依赖的镜像
FROM openjdk:8-jre

# 作者以及邮箱等信息
MAINTAINER K.O ko.shen@hotmail.com

# 从那拷贝到哪里
COPY target/user-thrift-service-1.0.0.jar /user-service.jar

# 启动命令
ENTRYPOINT ["java", "-jar", "/user-service.jar"]
```

- 构建镜像：`docker build -t user-service:latest` 
- link docker: `docker-compose up -d`