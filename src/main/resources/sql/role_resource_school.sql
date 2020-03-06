/*
Navicat MySQL Data Transfer

Source Server         : localhostMysql
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-06 17:53:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role_resource_school
-- ----------------------------
DROP TABLE IF EXISTS `role_resource_school`;
CREATE TABLE `role_resource_school` (
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resource_school
-- ----------------------------
INSERT INTO `role_resource_school` VALUES ('1', '1');
INSERT INTO `role_resource_school` VALUES ('1', '2');
INSERT INTO `role_resource_school` VALUES ('1', '11');
INSERT INTO `role_resource_school` VALUES ('1', '20');
INSERT INTO `role_resource_school` VALUES ('1', '21');
INSERT INTO `role_resource_school` VALUES ('1', '27');
INSERT INTO `role_resource_school` VALUES ('1', '3');
INSERT INTO `role_resource_school` VALUES ('1', '17');
INSERT INTO `role_resource_school` VALUES ('1', '18');
INSERT INTO `role_resource_school` VALUES ('1', '19');
INSERT INTO `role_resource_school` VALUES ('1', '13');
INSERT INTO `role_resource_school` VALUES ('1', '22');
INSERT INTO `role_resource_school` VALUES ('1', '23');
INSERT INTO `role_resource_school` VALUES ('1', '24');
INSERT INTO `role_resource_school` VALUES ('1', '25');
INSERT INTO `role_resource_school` VALUES ('1', '26');
INSERT INTO `role_resource_school` VALUES ('2', '1');
INSERT INTO `role_resource_school` VALUES ('2', '13');
