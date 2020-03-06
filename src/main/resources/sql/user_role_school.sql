/*
Navicat MySQL Data Transfer

Source Server         : localhostMysql
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-06 17:52:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_role_school
-- ----------------------------
DROP TABLE IF EXISTS `user_role_school`;
CREATE TABLE `user_role_school` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role_school
-- ----------------------------
INSERT INTO `user_role_school` VALUES ('1', '1');
INSERT INTO `user_role_school` VALUES ('2', '2');
INSERT INTO `user_role_school` VALUES ('3', '2');
