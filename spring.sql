/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : spring

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2020-10-20 17:11:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `persistent_logins`
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('zhangsan', '+Sj+Ez7e+ZzuS9i/kEtVsA==', 'AnKiMULIJXB3WztUkAGeiQ==', '2020-10-20 13:36:44');
INSERT INTO `persistent_logins` VALUES ('zhangsan', 'JjrEcx1TlMumfRFy2s6pjw==', 'nw/DIFFLDwFkphKJQ9I+eQ==', '2020-10-20 10:42:16');
INSERT INTO `persistent_logins` VALUES ('zhangsan', 'LGwmvvvEYMmmwOPaH+xDWw==', 'gFZc6/fYGG0FLRCTlnENAQ==', '2020-10-20 10:39:10');
INSERT INTO `persistent_logins` VALUES ('zhangsan', 'MjIG0UT9lwlhf5qnCDLQlQ==', 'bSe6NCC4OF4mUavpbotKgw==', '2020-10-20 13:29:58');
INSERT INTO `persistent_logins` VALUES ('zhangsan', 'pKCRbicptEZNR8RmvuBrpA==', 'OqjW8hclCgXZLVVVa0nA9A==', '2020-10-20 13:32:09');

-- ----------------------------
-- Table structure for `s_animal`
-- ----------------------------
DROP TABLE IF EXISTS `s_animal`;
CREATE TABLE `s_animal` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` int NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of s_animal
-- ----------------------------

-- ----------------------------
-- Table structure for `s_client`
-- ----------------------------
DROP TABLE IF EXISTS `s_client`;
CREATE TABLE `s_client` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `client_id` varchar(100) NOT NULL,
  `resource_ids` varchar(100) NOT NULL,
  `scopes` varchar(100) NOT NULL,
  `access_token_validity_seconds` bigint NOT NULL,
  `authorized_grant_types` varchar(100) NOT NULL,
  `secret` varchar(100) NOT NULL,
  `authorities` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of s_client
-- ----------------------------
INSERT INTO `s_client` VALUES ('1', 'client_one', 'rid', 'all', '1800', 'password,refresh_token', 'fd4aa356ab2efcacf0fabfdd25a12775a9e0a257801559143ed61acf6714924b0ff4913356d00f4e', 'ROLE_USER');

-- ----------------------------
-- Table structure for `s_menu`
-- ----------------------------
DROP TABLE IF EXISTS `s_menu`;
CREATE TABLE `s_menu` (
  `id` bigint NOT NULL,
  `pattern` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of s_menu
-- ----------------------------
INSERT INTO `s_menu` VALUES ('1', '/admin/**');
INSERT INTO `s_menu` VALUES ('2', '/user/**');
INSERT INTO `s_menu` VALUES ('3', '/supporter/**');

-- ----------------------------
-- Table structure for `s_role`
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('1', 'ROLE_ADMIN', '管理员');
INSERT INTO `s_role` VALUES ('2', 'ROLE_USER', '普通用户');
INSERT INTO `s_role` VALUES ('3', 'ROLE_SUPPORTER', '技术支持');

-- ----------------------------
-- Table structure for `s_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `s_role_menu`;
CREATE TABLE `s_role_menu` (
  `id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of s_role_menu
-- ----------------------------
INSERT INTO `s_role_menu` VALUES ('1', '1', '1');
INSERT INTO `s_role_menu` VALUES ('2', '1', '2');
INSERT INTO `s_role_menu` VALUES ('3', '2', '2');
INSERT INTO `s_role_menu` VALUES ('4', '3', '3');

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
