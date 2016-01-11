/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2015-12-21 16:04:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `content` mediumtext NOT NULL,
  `publishTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', 'JFinal Demo Title here', 'JFinal Demo Content here', null);
INSERT INTO `blog` VALUES ('7', '标题', '', null);
INSERT INTO `blog` VALUES ('8', '标题1', '内容11', null);
INSERT INTO `blog` VALUES ('9', '标题2', '内容2', '2015-03-30 14:49:56');
INSERT INTO `blog` VALUES ('10', '标题3', 'aaa1', null);
INSERT INTO `blog` VALUES ('11', '标题3', '', null);
INSERT INTO `blog` VALUES ('13', 'asfasf', '555', '2015-03-30 16:13:13');
INSERT INTO `blog` VALUES ('14', 'ibatis title', 'update content1', '2015-03-31 10:05:05');
INSERT INTO `blog` VALUES ('15', 'test title', 'test content', null);
INSERT INTO `blog` VALUES ('38', 'test mapper title 2222222222', 'test mapper content 222222', '2015-07-29 14:07:14');
INSERT INTO `blog` VALUES ('39', 'test mapper title 2222222222', 'test mapper content 222222', '2015-07-29 15:03:31');
INSERT INTO `blog` VALUES ('40', 'test mapper title 2222222222', 'test mapper content 222222', '2015-07-30 15:10:24');
INSERT INTO `blog` VALUES ('51', '标题3', '内容3', null);
INSERT INTO `blog` VALUES ('55', 'test db title', 'test db content', '2015-10-21 15:55:01');
INSERT INTO `blog` VALUES ('56', 'test db title', 'test db content', '2015-10-23 11:10:29');
INSERT INTO `blog` VALUES ('57', 'test db title', '', '2015-10-23 11:10:44');
