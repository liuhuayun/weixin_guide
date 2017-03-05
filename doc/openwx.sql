/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50627
 Source Host           : localhost
 Source Database       : openwx

 Target Server Version : 50627
 File Encoding         : utf-8

 Date: 03/05/2017 18:00:29 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `idea`
-- ----------------------------
DROP TABLE IF EXISTS `idea`;
CREATE TABLE `idea` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `account` varchar(255) NOT NULL COMMENT '联系号码',
  `contact` varchar(255) NOT NULL COMMENT '联系方式',
  `context` text NOT NULL COMMENT '意见反馈内容',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `rId` varchar(11) DEFAULT NULL COMMENT '随机数',
  `nickName` varchar(255) NOT NULL COMMENT '昵称',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `tel` varchar(15) DEFAULT NULL COMMENT '手机号码',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `password` varchar(255) NOT NULL COMMENT 'MD5密码',
  `password2` varchar(255) DEFAULT NULL COMMENT '明文密码',
  `openId` varchar(255) DEFAULT NULL COMMENT '微信用户的唯一识别',
  `remember` tinyint(1) DEFAULT '0' COMMENT '是否记住密码',
  `registerDate` datetime NOT NULL COMMENT '注册时间',
  `lastLoginDate` datetime DEFAULT NULL COMMENT '最后登录时间',
  `level` int(10) DEFAULT '0' COMMENT '等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `wx_articles`
-- ----------------------------
DROP TABLE IF EXISTS `wx_articles`;
CREATE TABLE `wx_articles` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `appId` varchar(255) NOT NULL COMMENT 'appId',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '类别(0 文本 1 链接)',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `describe` tinytext COMMENT '文章描述',
  `author` varchar(255) DEFAULT NULL,
  `content` text NOT NULL COMMENT '文章内容',
  `url` varchar(255) DEFAULT NULL COMMENT '文章链接',
  `imageUrl` varchar(255) DEFAULT NULL COMMENT '封面',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `tags` varchar(255) DEFAULT NULL COMMENT '标签',
  `crateTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `wx_config`
-- ----------------------------
DROP TABLE IF EXISTS `wx_config`;
CREATE TABLE `wx_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `sysuid` int(11) DEFAULT NULL COMMENT '系统用户ID',
  `appId` varchar(255) NOT NULL COMMENT 'appId',
  `appSecret` varchar(255) NOT NULL COMMENT 'appSecret',
  `token` varchar(255) NOT NULL DEFAULT 'Javen' COMMENT 'token',
  `encryptMessage` tinyint(1) DEFAULT '0' COMMENT '是否加密',
  `encodingAesKey` varchar(255) DEFAULT NULL COMMENT '随机数',
  `mchId` varchar(255) DEFAULT NULL COMMENT '商户ID',
  `paternerKey` varchar(255) DEFAULT NULL COMMENT '密钥设置',
  `certPath` varchar(255) DEFAULT NULL COMMENT '证书路径',
  `domain` varchar(255) DEFAULT NULL COMMENT '授权域名',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '激活',
  `uuid` varchar(255) NOT NULL COMMENT '唯一识别',
  `creatTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remake` varchar(255) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `wx_keyword`
-- ----------------------------
DROP TABLE IF EXISTS `wx_keyword`;
CREATE TABLE `wx_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `appId` varchar(255) DEFAULT NULL COMMENT 'appId',
  `replyType` tinyint(4) DEFAULT '1' COMMENT '关注回复类型 1、文本 2、图文  3、图片 4、发红包',
  `keyWord` varchar(255) NOT NULL,
  `content` text COMMENT '回复内容',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remake` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `wx_subscription`
-- ----------------------------
DROP TABLE IF EXISTS `wx_subscription`;
CREATE TABLE `wx_subscription` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `appId` varchar(255) NOT NULL COMMENT 'appId',
  `replyType` int(5) NOT NULL DEFAULT '1' COMMENT '关注回复类型 1、文本 2、图文  3、图片 4、发红包',
  `content` text NOT NULL COMMENT '回复内容',
  `status` tinyint(1) DEFAULT '0' COMMENT '激活',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `wx_user`
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `appId` varchar(255) DEFAULT NULL COMMENT 'appId',
  `openId` varchar(255) DEFAULT NULL COMMENT '用户标示',
  `subscribeTime` datetime DEFAULT NULL COMMENT '关注时间',
  `unsubscribeTime` datetime DEFAULT NULL COMMENT '取消关注',
  `nickName` varchar(255) DEFAULT NULL COMMENT '昵称',
  `unionid` varchar(255) DEFAULT NULL COMMENT 'unionid',
  `privilege` varchar(255) DEFAULT NULL COMMENT '特权信息',
  `headimgurl` varchar(255) DEFAULT NULL COMMENT '图像',
  `country` varchar(10) DEFAULT NULL COMMENT '国家',
  `city` varchar(10) DEFAULT NULL COMMENT '城市',
  `province` varchar(10) DEFAULT NULL COMMENT '省份',
  `sex` int(1) DEFAULT NULL COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
