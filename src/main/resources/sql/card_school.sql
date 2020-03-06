/*
Navicat MySQL Data Transfer

Source Server         : Zzy
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : school

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-06 18:04:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for card_school
-- ----------------------------
DROP TABLE IF EXISTS `card_school`;
CREATE TABLE `card_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_number` varchar(255) DEFAULT NULL COMMENT '卡号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `card_money` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `card_status` int(11) DEFAULT NULL COMMENT '校园卡状态  0正常  1挂失',
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL COMMENT '删除状态 0正常 1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card_school
-- ----------------------------
