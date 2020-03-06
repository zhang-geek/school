/*
Navicat MySQL Data Transfer

Source Server         : firstlink
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : school

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-06 19:52:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for borrow_car
-- ----------------------------
DROP TABLE IF EXISTS `borrow_car`;
CREATE TABLE `borrow_car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `borrow_time` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL COMMENT '0 未审核 1 审核成功',
  `is_del` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow_car
-- ----------------------------
INSERT INTO `borrow_car` VALUES ('13', '12', '1', 'XXX', '2', '2020-03-04 21:13:23', '1', '1', '14');
INSERT INTO `borrow_car` VALUES ('14', '12', '1', 'XXX', '2', '2020-03-05 00:25:54', '1', '0', '15');
