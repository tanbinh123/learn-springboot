/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : spring

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2020-10-10 17:59:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `s_role`
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('1', 'ROLE_ADMIN', '管理员');
INSERT INTO `s_role` VALUES ('2', 'ROLE_USER', '普通用户');

-- ----------------------------
-- Table structure for `s_user`
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `enabled` int NOT NULL DEFAULT '0' COMMENT '0不可用，1可用',
  `account_locked` int NOT NULL DEFAULT '0' COMMENT '账号是否被锁，0未锁，1被锁',
  `account_expired` int NOT NULL DEFAULT '0' COMMENT '账号是否过期，0：未过期，1：过期',
  `credentials_expired` int NOT NULL COMMENT '密码是否过期，0：未过期，1：过期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'zhangsan', 'fd4aa356ab2efcacf0fabfdd25a12775a9e0a257801559143ed61acf6714924b0ff4913356d00f4e', '1', '0', '0', '0');
INSERT INTO `s_user` VALUES ('2', 'lisi', 'fd4aa356ab2efcacf0fabfdd25a12775a9e0a257801559143ed61acf6714924b0ff4913356d00f4e', '1', '0', '0', '0');
INSERT INTO `s_user` VALUES ('3', 'wangwu', 'fd4aa356ab2efcacf0fabfdd25a12775a9e0a257801559143ed61acf6714924b0ff4913356d00f4e', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for `s_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('1', '1', '1');
INSERT INTO `s_user_role` VALUES ('2', '1', '2');
INSERT INTO `s_user_role` VALUES ('3', '2', '2');
INSERT INTO `s_user_role` VALUES ('4', '3', '2');
