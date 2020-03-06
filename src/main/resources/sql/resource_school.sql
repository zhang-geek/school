/*
Navicat MySQL Data Transfer

Source Server         : localhostMysql
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-06 17:53:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for resource_school
-- ----------------------------
DROP TABLE IF EXISTS `resource_school`;
CREATE TABLE `resource_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `is_del` int(11) NOT NULL,
  `resource_type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource_school
-- ----------------------------
INSERT INTO `resource_school` VALUES ('1', '权限管理', 'not:exist', '0', '0', '1');
INSERT INTO `resource_school` VALUES ('2', '角色管理', '/role/toShow', '1', '0', '1');
INSERT INTO `resource_school` VALUES ('3', '资源管理', '/resource/toShow', '1', '0', '1');
INSERT INTO `resource_school` VALUES ('11', '角色新增', 'role:add', '2', '0', '2');
INSERT INTO `resource_school` VALUES ('13', '用户管理', '/user/toShow', '1', '0', '1');
INSERT INTO `resource_school` VALUES ('17', '资源新增', 'resource:add', '3', '0', '2');
INSERT INTO `resource_school` VALUES ('18', '资源修改', 'resource:update', '3', '0', '2');
INSERT INTO `resource_school` VALUES ('19', '资源删除', 'resource:delete', '3', '0', '2');
INSERT INTO `resource_school` VALUES ('20', '角色修改', 'role:update', '2', '0', '2');
INSERT INTO `resource_school` VALUES ('21', '角色删除', 'role:delete', '2', '0', '2');
INSERT INTO `resource_school` VALUES ('22', '用户新增', 'user:add', '13', '0', '2');
INSERT INTO `resource_school` VALUES ('23', '用户修改', 'user:update', '13', '0', '2');
INSERT INTO `resource_school` VALUES ('24', '用户删除', 'user:delete', '13', '0', '2');
INSERT INTO `resource_school` VALUES ('25', '用户激活', 'user:activate', '13', '0', '2');
INSERT INTO `resource_school` VALUES ('26', '用户授权', 'user:auth', '13', '0', '2');
INSERT INTO `resource_school` VALUES ('27', '关联资源', 'role:related_resource', '2', '0', '2');
