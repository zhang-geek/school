/*
Navicat MySQL Data Transfer

Source Server         : localhostMysql
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-06 17:53:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role_school
-- ----------------------------
DROP TABLE IF EXISTS `role_school`;
CREATE TABLE `role_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  `is_del` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_school
-- ----------------------------
INSERT INTO `role_school` VALUES ('1', '超级管理员', '0');
INSERT INTO `role_school` VALUES ('2', '普通用户', '0');
