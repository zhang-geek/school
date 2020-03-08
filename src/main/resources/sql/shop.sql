/*
Navicat MySQL Data Transfer

Source Server         : 1904
Source Server Version : 50720
Source Host           : 299253e6k0.wicp.vip:59720
Source Database       : school

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-08 15:59:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `base_data_id` int(11) DEFAULT NULL,
  `shop_name` varchar(255) DEFAULT NULL,
  `shop_price` decimal(10,2) DEFAULT NULL,
  `shop_status` int(11) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL COMMENT '0: 置顶 1: 取消置顶',
  `top_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1', '2', '鱼香肉丝米饭', '18.00', '0', '0', '2020-03-08 15:58:53', '2020-03-06 14:58:06', '0');
INSERT INTO `shop` VALUES ('2', '6', '康师傅绿茶', '3.50', '0', '0', '2020-03-08 15:58:55', '2020-03-06 15:58:19', '0');
INSERT INTO `shop` VALUES ('3', '2', '三全水饺', '24.00', '0', '0', '2020-03-08 15:58:57', '2020-03-07 16:58:26', '0');
INSERT INTO `shop` VALUES ('4', '3', '卫龙', '0.50', '0', '0', '2020-03-08 15:58:59', '2020-03-08 17:58:35', '0');
