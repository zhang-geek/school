/*
Navicat MySQL Data Transfer

Source Server         : firstlink
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : school

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-06 19:52:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0：上架 1：下架',
  `count` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `shelf_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('12', '放风筝的人', 'XXX', '15', '0', '20', '1', '0', '2020-02-26 20:58:20');
INSERT INTO `book` VALUES ('13', '飘', 'XX', '15', '0', '24', '1', '1', '2020-02-27 20:58:24');
INSERT INTO `book` VALUES ('14', '木偶人', 'XX', '16', '0', '10', '1', '1', '2020-02-28 20:58:29');
INSERT INTO `book` VALUES ('15', '啊啊', 'XXX', '17', '0', '9', '1', '0', '2020-02-29 23:46:08');
INSERT INTO `book` VALUES ('16', '111', 'XXX', '17', '0', '9', '4', '0', '2020-02-29 23:47:09');
INSERT INTO `book` VALUES ('18', 'JAVA基础', 'XXX', '14', '0', '9', '4', '0', '2020-03-02 14:00:48');
INSERT INTO `book` VALUES ('19', '点金荣誉', 'XXX', '15', '0', '10', '4', '0', '2020-03-02 14:13:03');
INSERT INTO `book` VALUES ('20', '我和你', 'XXX', '14', '0', '9', '6', '0', '2020-03-05 00:04:53');
