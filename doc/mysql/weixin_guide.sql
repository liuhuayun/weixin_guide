/*
Navicat MySQL Data Transfer

Source Server         : localhost-3306
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : weixin_guide

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-06-01 18:07:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `wx_admin`
-- ----------------------------
DROP TABLE IF EXISTS `wx_admin`;
CREATE TABLE `wx_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `nickname` varchar(255) NOT NULL COMMENT '昵称',
  `mobilephone` varchar(11) NOT NULL COMMENT '手机号',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `login_pwd` varchar(255) NOT NULL COMMENT '登陆密码',
  `rid` int(11) NOT NULL COMMENT '角色编号',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态：0=禁用,1=可用',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_admin
-- ----------------------------
INSERT INTO `wx_admin` VALUES ('1', 'Javen', '13545191278', '572839485@qq.com', '7698946764209BE8D1AE96CF1D7DF463', '1', '1', '深圳', '2017-06-01 15:38:58', '2017-06-01 16:10:16');
INSERT INTO `wx_admin` VALUES ('2', 'Javen.zhou', '111111', 'qq@qq.com', '7698946764209BE8D1AE96CF1D7DF463', '5', '1', '深圳', '2017-06-01 15:57:15', '2017-06-01 15:57:15');
INSERT INTO `wx_admin` VALUES ('3', 'CM001', '13545191277', 'qq@qq.com', '89BDF69372C2EF53EA409CDF020B5694', '5', '1', '', '2017-06-01 16:04:46', '2017-06-01 16:09:32');

-- ----------------------------
-- Table structure for `wx_config`
-- ----------------------------
DROP TABLE IF EXISTS `wx_config`;
CREATE TABLE `wx_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `uid` int(11) NOT NULL COMMENT '所属用户',
  `app_id` varchar(255) NOT NULL COMMENT '应用编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updae_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_config
-- ----------------------------
INSERT INTO `wx_config` VALUES ('1', '1', 'wx100000', '2017-05-31 18:03:24', '2017-05-31 18:03:27');
INSERT INTO `wx_config` VALUES ('2', '3', 'wx1222222', '2017-05-31 18:21:57', '2017-05-31 18:22:00');
