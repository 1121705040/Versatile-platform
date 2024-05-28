# 数据库初始化
# @author <a href="https://github.com/liyupi">程序员鱼皮</a>
# @from <a href="https://yupi.icu">编程导航知识星球</a>

-- 创建库
create database if not exists oj_db;

-- 切换库
use oj_db;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (unionId)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 帖子表
create table if not exists post
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    thumbNum   int      default 0                 not null comment '点赞数',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表（硬删除）
create table if not exists post_thumb
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子点赞';

-- 帖子收藏表（硬删除）
create table if not exists post_favour
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子收藏';
-- 题目表
drop table if exists problems;
create table if not exists problems
(
    id          bigint auto_increment comment 'id' primary key,
    title       varchar(128)                       null comment '标题',
    userId      bigint                             not null comment '用户id',
    pbDesc      varchar(1024)                      null comment '题目描述',
    commitCount int      default 0                 not null comment '提交数',
    pbInOutDesc varchar(2048)                      null comment '输入输出描述(json)',
    pbInOutSpl  varchar(4096)                      null comment '输入输出案例(json)',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP null comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除',
    index inx_userId (userId)
) comment '题目表';
-- 提交表
drop table if exists submit_problems;
create table submit_problems
(
    id          bigint auto_increment comment 'id' primary key,
    userId      bigint                             not null comment '用户id',
    pbId        bigint                             not null comment '题目id',
    maxMemory   int      default 0                 null comment '最大内存(kb)',
    maxTime     int      default 0                 null comment '最大执行时间(ms)',
    runLanguage varchar(64)                        null comment '使用的语言',
    userCode    varchar(2048)                      not null comment '用户代码',
    runResult   int(2)                             null comment '执行结果 1.正确 2.答错 3.编译错误 4.案例错误 5.内存限制超出 6.运行时错误 7.超出输出限制 8.超出时间限制',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP null comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除',
    index inx_userId (userId),
    index inx_pbIid (pbId)
) comment '题目表';
