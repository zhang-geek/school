/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-06 22:11:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_date
-- ----------------------------
DROP TABLE IF EXISTS `base_date`;
CREATE TABLE `base_date` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `base_name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_date
-- ----------------------------
INSERT INTO `base_date` VALUES ('1', '速食', '0', '0');
INSERT INTO `base_date` VALUES ('2', '辣条', '0', '0');
INSERT INTO `base_date` VALUES ('3', '肉食', '0', '0');
INSERT INTO `base_date` VALUES ('4', '零食', '0', '0');
INSERT INTO `base_date` VALUES ('5', '碳酸饮料', '0', '0');
INSERT INTO `base_date` VALUES ('6', '果汁', '0', '0');
INSERT INTO `base_date` VALUES ('7', '雪糕', '0', '0');
INSERT INTO `base_date` VALUES ('8', '冰淇淋', '0', '0');
