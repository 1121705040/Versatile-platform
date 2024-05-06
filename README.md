# lx的用户管理(不含前端)

### 项目介绍

企业开发中的基础模块,项目实现的接口有:

1. **用户登陆**
2. **用户注册**
3. **用户查询**
4. **用户删除**
5. **用户鉴权**
6. **用户态存储(Session)**
7. **用户注销**
8. **自定义异常类/AOP异常处理器**

### 搭建流程

1. **需求分析**(这个需要到底有没有做的必要?)

#### 	需求分析

**需要做一个用户中心**,因为我们很多服务都需要用户登陆之后才能够操作,但如果让用户没点击一个服务就登陆一次,体验极差,我的要做的是让用户只登陆一次就能使用我们的所有服务,也就是单点登录.

是刚需吗?

频率高吗?

人数多吗?

1. **设计**(概要设计,核心的功能是什么?详细设计,架构设计)

   1. 竞品分析,是不是人无我有,人有我精?

   #### 概要设计:

   1. 用户登陆注册
   2. 用户管理
   3. 用户校验
   4. 权限管理

2. **技术选型**(用什么技术来实现)

   1. 前端

      1. #### 技术选型:

         **后端:**

         1. java
         2. spring(java开发框架,帮你管理java对象)
         3. spring MVC(web开发框架,开放接口的访问,restful规范等)
         4. spring boot(帮你便捷的管理Java对象,使用依赖注入的方式,让你快速启动项目)
         5. mybatis + mybatis plus(持久层框架,封装jdbc,和数据库打交道/对前者的增强)
         6. mysql(数据库)
         7. junit(单元测试)

**初始化后端**:

1. 打开idea新建spring Boot项目

2. 选择阿里提供的搭建网站(https://start.aliyun.com/),因为Oracle默认创建spring Boot3.x, 不支持java8

3. 选择要集成的框架

   1. spring web (提供外部访问能力)

   2. SpringBoot devtools(热更新)

   3. lombok(自动生成get/set方法之类)

   4. spring configuration processor(支持读取属性文件的一些注解)

   5. MySQL driver (数据库链接驱动)

   6. Mybatis framework(数据访问层的框架,对数据进行crud)

   7. 默认没有junit(可以使用https://mvnrepository.com/中心仓库去添加)

   8. 默认没有Mybatis plus 需要引入

   9. ```java
      <dependency>
          <groupId>com.baomidou</groupId>
          <artifactId>mybatis-plus-boot-starter</artifactId>
          <version>3.5.1</version>
      </dependency>
      ```

      ### 功能登陆/注册/用户管理

      1. 库表设计,添加用户表

         1. ```sql
            #用户表
            #唯一标识 id   binint
            #用户名称 user_name varcha
            #用户账号 user_account varcha
            #用户密码 user_password varcha
            #用户头像 user_avatar varcha
            #用户邮箱 user_email varcha
            #用户手机 user_phone_number varcha
            #星球编号 planet_number int
            #用户性别 user_gender int
            #用户状态 user_status int
            #用户权限 user_permissions int
            #创建日期 create_date datetime
            #更新日期 update_date datetime
            #建表语句
            drop table user_info;
            create table user_info
            (
                user_id           int auto_increment
                    PRIMARY KEY comment '用户唯一标识',
                user_name         varchar(64)      null comment '用户名称',
                user_password     varchar(64)      not null comment '用户密码',
                user_avatar       varchar(256)     null comment '用户头像',
                user_email        varchar(64)      null comment '用户邮箱',
                planet_number     int(8)           null comment '星球编号',
                user_phone_number varchar(64)      null comment '用户手机号码',
                user_gender       int(2)           null comment '用户性别 --0女 --1男
            ',
                user_status       int(2) default 0 null comment '用户状态 --0正常 --1删除',
                user_permissions  int(2) default 0 null comment '用户权限 --0管理员 --1普通用户',
                create_date       timestamp DEFAULT CURRENT_TIMESTAMP comment '创建日期',
                update_date       timestamp  null comment '更新日期'
            );
            
            
            ```

      2. 实现用户注册功能

         1. 使用mybatis持久层框架进行数据访问,
            1. mapping.java---mapping.xml
            1. 使用MybatisX自动生成后端代码
            1. 配置application.yml文件中的数据库链接,配置方式参考Mybatis官方文档
         2. 用户注册,
            1. 用户名--> 默认为账号
            2. 非空
            3. 账号<=18 &&<=6,不能重复
            4. 密码<=18 &&<=6,需要加密
            5. 两次舒服密码是否一致
            6. 星球编号 <5
            7. 用户名是否存在特殊字符:正则参考:https://blog.csdn.net/weixin_43718414/article/details/98493703
            8. 密码加密
         3. 用户登陆
            1. 每次登陆保存用户态到session
            2. 页面查询用户态,直接返回session里的内容,,,(TODO)
            3. 登陆参数
               1. 用户名,密码,httprequest
            4. 请求方法 post
            5. 返回值: 用户信息(脱敏)
            6. 逻辑
               1. 登陆参数校验
                  1. 非空
                  2. 用户名是否存在特殊字符:
                  3. 用户名,密码<=18 &&<=6,需要加密

      3. 用户管理

         1. 查询/删除✓
            1. 鉴权!!!判断用户是否为管理员
         2. 退出登陆✓
            1. 删除登陆态

      4. 接口测试完成

   ### 后端优化:

   1. 通用返回对象
      1. 目的,给返回对象补充一些信息,告诉前端这个请求在业务层面上是成功还是失败.200,404,500,502,503
   2. 封装全局异常处理
      1. 自定义异常类
         1. 作用:相对java的异常类,支持更多的字段,更灵活
      2. 自定义异常处理器
         1. 捕获代码中的所有异常,内部消化,集中处理,让前端得到更详细的业务报错/信息
         2. 同事屏蔽掉项目框架本身的异常(不暴露服务的内部状态)
   3. 全局请求日志和登陆校验

   
