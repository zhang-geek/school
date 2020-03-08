/*
Navicat MySQL Data Transfer

Source Server         : 1904
Source Server Version : 50720
Source Host           : 299253e6k0.wicp.vip:59720
Source Database       : school

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-08 18:15:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_school
-- ----------------------------
DROP TABLE IF EXISTS `order_school`;
CREATE TABLE `order_school` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `shop_id` int(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `shop_price` int(11) DEFAULT NULL,
  `order_num` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_school
-- ----------------------------
