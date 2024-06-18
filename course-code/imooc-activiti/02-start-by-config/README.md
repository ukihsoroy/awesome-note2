# start-by-config activiti引擎配置

## 基本配置

## 数据源配置

- 默认
- mysql
- druid

## 日志配置

- logback
- mdc

## 历史数据配置

- 历史配置的四个界别

## event logging（事件日志）配置

- 默认为false
- true后开启event log

## event listener

- 配置事件监听器
- 事件监听器的类型

## interceptor 拦截器配置

- 拦截器介绍及配置

## 定时job配置

- 异步配置
- 自定义线程池配置

## activiti与spring集成配置

- 配置数据源及事务，依赖注入使用
- 在流程文件中使用spring bean `${}`