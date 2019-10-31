# 运行环境
- 数据库：mysql-5.7
- JDK：1.8

# 项目启动
1. 执行sql
```mysql
CREATE DATABASE login;
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
   `id` BIGINT(20) AUTO_INCREMENT NOT NULL,
   `user_name` VARCHAR(20) NOT NULL,
   `id_card_num` VARCHAR(80) NOT NULL,
   `mobile` VARCHAR(50) NOT NULL,
   `password` VARCHAR(255) NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `idx_user_name` (`user_name`)
 ) ENGINE=INNODB AUTO_INCREMENT = 2 CHARSET=utf8;
```
2. application.properties文件中配置使用的环境
，目前已配置的是dev和pro
3. 启动项目

# 业务描述
## 注册
**参数要求** 
- 姓名：必填，姓名只能是英文字母或者数字
- 密码：必填，密码只能是英文字母或者数字
- 手机号：必填，手机号只能是数字
- 身份证号：必填，身份证号只能是英文字母或者数字

**其他**
- 同一个姓名只能注册一次
- 密码使用MD5+慢哈希加密存储
- 手机号和身份证号使用AES-128加密存储
- 页面防XSS漏洞和CSRF漏洞
## 登陆
- 页面防XSS漏洞和CSRF漏洞

# 待优化问题
- 客户端密码明文传输
- 全局的异常处理
- AES-128加密算法临时网上找的，可能有缺陷
