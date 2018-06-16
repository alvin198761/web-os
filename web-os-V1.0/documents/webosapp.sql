/*
Navicat MySQL Data Transfer

Source Server         : webosapp_db
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : webosapp

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-06-16 11:44:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app_icon`
-- ----------------------------
DROP TABLE IF EXISTS `app_icon`;
CREATE TABLE `app_icon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(4) DEFAULT NULL COMMENT '图标类型',
  `title` char(20) DEFAULT NULL COMMENT '显示文本',
  `tip` varchar(100) DEFAULT NULL COMMENT '悬停提示',
  `icon` varchar(200) DEFAULT NULL COMMENT '闁搞儳鍋撻悥?',
  `publish_id` bigint(20) DEFAULT NULL COMMENT '发布人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点',
  `route_url` varchar(200) DEFAULT NULL COMMENT '对应路由地址',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='应用程序图标';

-- ----------------------------
-- Records of app_icon
-- ----------------------------
INSERT INTO `app_icon` VALUES ('1', '1', '首页', '首页', 'home.png', '0', '2018-05-12 17:24:27', '0', null, '1');
INSERT INTO `app_icon` VALUES ('2', '1', '联系人', '联系人', 'email.png', '0', '2018-05-12 17:26:08', '0', null, '1');
INSERT INTO `app_icon` VALUES ('3', '1', '音乐', '音乐', 'music.png', '0', '2018-05-12 17:26:51', '0', null, '1');
INSERT INTO `app_icon` VALUES ('4', '1', '视频', '视频', 'video.png', '0', '2018-05-12 17:27:58', '0', null, '1');
INSERT INTO `app_icon` VALUES ('5', '1', '历史', '历史', 'history.png', '0', '2018-05-12 17:28:46', '0', null, '1');
INSERT INTO `app_icon` VALUES ('6', '1', '日历', '日历', 'calendar.png', '0', '2018-05-12 17:29:43', '0', null, '1');
INSERT INTO `app_icon` VALUES ('7', '1', '订阅', '订阅', 'rss.png', '0', '2018-05-12 17:30:32', '0', null, '1');
INSERT INTO `app_icon` VALUES ('8', '2', 'Windows 远程桌面', 'Windows 远程桌面', 'rdp.png', '0', '2018-05-12 17:49:08', '0', 'rdp', '1');
INSERT INTO `app_icon` VALUES ('9', '2', 'VNC 连接', 'VNC 连接', 'vnc.png', '0', '2018-05-12 17:49:55', '0', 'vnc', '1');
INSERT INTO `app_icon` VALUES ('10', '2', 'Putty SSH 连接', 'Putty SSH 连接', 'putty.png', '0', '2018-05-12 17:50:53', '0', 'putty', '1');
INSERT INTO `app_icon` VALUES ('11', '2', 'Ipmi 连接', 'Ipmi 连接', 'ipmi.png', '0', '2018-05-12 17:52:10', '0', 'ipmi', '1');
INSERT INTO `app_icon` VALUES ('12', '2', '邮箱', '邮箱', 'mail.png', '0', '2018-05-12 17:53:32', '0', 'mail', '1');
INSERT INTO `app_icon` VALUES ('13', '2', '时钟', '时钟', 'time.png', '0', '2018-05-12 17:54:27', '0', 'time', '1');
INSERT INTO `app_icon` VALUES ('14', '1', '公文包', '公文包', 'portfolio.png', '0', '2018-05-12 18:30:09', '0', null, '1');
INSERT INTO `app_icon` VALUES ('15', '3', '百度', '百度一下，你就知道', 'http://47.98.132.116/img/baidu.png', '0', '2018-05-26 11:12:40', '1', 'https://www.baidu.com', '1');
INSERT INTO `app_icon` VALUES ('16', '3', '网易新闻', '三石哥家的网站', 'http://47.98.132.116/img/163.png', '0', '2018-05-26 17:03:38', '6', 'https://www.163.com', '1');
INSERT INTO `app_icon` VALUES ('17', '3', '月球王子', '月球王子的github', 'http://47.98.132.116/img/163.png', '0', '2018-05-26 17:03:42', '1', 'https://www.sina.com.cn/', '1');
INSERT INTO `app_icon` VALUES ('19', '3', '必应', '微软程序', 'http://47.98.132.116/img/163.png', '0', '2018-05-26 17:12:44', '2', 'https://cn.bing.com', '1');
INSERT INTO `app_icon` VALUES ('20', '3', '酷狗音乐', 'hello kugou', 'http://47.98.132.116/img/163.png', '0', '2018-05-26 17:15:08', '3', 'https://www.kugou.com', '1');
INSERT INTO `app_icon` VALUES ('21', '3', 'bilibili', '搞比利', 'http://47.98.132.116/img/163.png', '0', '2018-05-26 17:27:37', '4', 'https://www.bilibili.com/', '1');
INSERT INTO `app_icon` VALUES ('22', '3', '凤凰网', '一个之前喜欢的网站', 'http://47.98.132.116/img/163.png', '0', '2018-05-26 17:28:34', '5', 'https://www.ifeng.com', '1');
INSERT INTO `app_icon` VALUES ('23', '4', '第一个测试目录', '测试一下', 'http://47.98.132.116/img/folder_o.png', '0', '2018-05-26 17:58:42', '7', null, '1');
INSERT INTO `app_icon` VALUES ('24', '4', '空目录', '新建一个空目录，证明我的存在', 'http://47.98.123.116/img/folder_o.png', '0', '2018-06-09 13:38:24', '7', null, '1');
INSERT INTO `app_icon` VALUES ('25', '3', '随便', '啦啦啦啦啦啦啦', 'http://47.98.132.116/img/163.png', '0', '2018-06-09 13:40:51', '7', 'https://bbs.csdn.net', '1');
INSERT INTO `app_icon` VALUES ('26', '4', 'abc', 'abcx', 'http://47.98.123.116/img/folder_o.png', '0', '2018-06-13 16:15:39', '1', null, '1');

-- ----------------------------
-- Table structure for `notes`
-- ----------------------------
DROP TABLE IF EXISTS `notes`;
CREATE TABLE `notes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `content` varchar(5000) DEFAULT NULL COMMENT '内容',
  `last_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `author` bigint(20) DEFAULT NULL COMMENT '作者',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='便签信息';

-- ----------------------------
-- Records of notes
-- ----------------------------

-- ----------------------------
-- Table structure for `protocols`
-- ----------------------------
DROP TABLE IF EXISTS `protocols`;
CREATE TABLE `protocols` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `domain` varchar(100) DEFAULT NULL COMMENT 'ad域',
  `user` varchar(50) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip地址',
  `port` int(11) DEFAULT NULL COMMENT '端口',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `author` bigint(20) DEFAULT NULL COMMENT '作者',
  `create_time` datetime DEFAULT NULL COMMENT '时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `type` tinyint(4) DEFAULT NULL COMMENT '协议类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='远程协议';

-- ----------------------------
-- Records of protocols
-- ----------------------------
INSERT INTO `protocols` VALUES ('1', 'test', 'test', 'test', '3389', 'test', '0', '2018-06-09 16:45:57', '1', '1');
INSERT INTO `protocols` VALUES ('3', null, '123', '123231', '5900', '123', '0', '2018-06-09 16:46:12', '1', '2');
INSERT INTO `protocols` VALUES ('4', null, 'wer', 'ewr', '5900', 'ewr', '0', '2018-06-09 16:46:16', '1', '2');
INSERT INTO `protocols` VALUES ('5', null, 'adsf', 'adfs', '22', 'dsf', '0', '2018-06-09 16:46:25', '1', '3');
INSERT INTO `protocols` VALUES ('6', null, 'adsf', 'afds', '0', 'adfs', '0', '2018-06-09 16:46:35', '1', '4');
INSERT INTO `protocols` VALUES ('7', null, 'sdf', 'afd', '0', 'sdf', '0', '2018-06-09 16:46:40', '1', '4');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login_name` varchar(100) DEFAULT NULL COMMENT '登录名称',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `auth` tinyint(4) DEFAULT NULL COMMENT '身份',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `author` bigint(20) DEFAULT NULL COMMENT '创建人',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
-- Records of user_info
-- ----------------------------
