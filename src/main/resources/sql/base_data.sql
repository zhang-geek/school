/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-06 23:11:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_data
-- ----------------------------
DROP TABLE IF EXISTS `base_data`;
CREATE TABLE `base_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_data
-- ----------------------------
INSERT INTO `base_data` VALUES ('1', '速食', '9', '0');
INSERT INTO `base_data` VALUES ('2', '辣条', '9', '0');
INSERT INTO `base_data` VALUES ('3', '肉食', '9', '0');
INSERT INTO `base_data` VALUES ('4', '零食', '9', '0');
INSERT INTO `base_data` VALUES ('5', '碳酸饮料', '9', '0');
INSERT INTO `base_data` VALUES ('6', '果汁', '9', '0');
INSERT INTO `base_data` VALUES ('7', '雪糕', '9', '0');
INSERT INTO `base_data` VALUES ('8', '冰淇淋', '9', '0');
INSERT INTO `base_data` VALUES ('9', '商品类别', '0', '0');
