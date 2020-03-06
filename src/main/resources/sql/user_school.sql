/*
Navicat MySQL Data Transfer

Source Server         : localhostMysql
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-06 17:52:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_school
-- ----------------------------
DROP TABLE IF EXISTS `user_school`;
CREATE TABLE `user_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_login_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `user_status` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `is_verify` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_school
-- ----------------------------
INSERT INTO `user_school` VALUES ('1', 'admin', '40dbf72868f76c801b23b035f755e544', '2020-02-29 16:41:05', '2020-03-04 21:12:48', '2020-03-04 17:35:56', '0', '1', '1073114568@qq.com', '18392886657', 'b477a901bdce2a74c06f04f770c027b3', '0');
INSERT INTO `user_school` VALUES ('2', 'zs', 'd24c00d5a5332df200f59919c6c2fb2a', '2020-02-29 21:41:30', '2020-03-04 21:12:48', '2020-03-03 16:53:17', '0', '1', '107311468@qq.com', '18392886656', '75d58c7c7bfe05556043f8376736367d', '0');
INSERT INTO `user_school` VALUES ('3', 'ls', '45a9358c55da63dcc51de72f43f13d6e', '2020-03-03 15:47:35', '2020-03-04 21:12:48', '2020-03-03 16:54:39', '0', '1', '1233@qq.com', '18392886564', '79412041c89e11a9fa3a207b4f65bc07', '0');
