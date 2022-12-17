# 数字图书馆课程设计 yiwen.chen

### 概述
基于Spring + Spring MVC + MyBatis的图书馆管理系统，使用Maven进行包管理。
系统分为读者端和管理员端。

### 环境配置
#### 开发环境：Windows 10，IntelliJ IDEA 2018.3
#### 运行配置
1. 首先安装Mysql5.7，设置用户名为root，密码为123456，并保证其在运行状态，并执行library.sql文件导入数据。
2. 然后再配置Maven到环境变量中，在源代码目录下运行
```sh
# mvn jetty:run
```
3. 使用浏览器访问http://localhost:8080即可进入系统。
4. 读者端用户名：10000 密码：123456；管理员端用户名：123456 密码：123456