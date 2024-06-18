#!/usr/bin/env bash

# mvn打包
mvn clean package

# 构建docker镜像
docker build -t user-service:latest .