/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.101
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 192.168.1.101:3306
 Source Schema         : my_software

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 09/12/2020 18:56:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_server
-- ----------------------------
DROP TABLE IF EXISTS `sys_server`;
CREATE TABLE `sys_server`
(
    `id`          bigint(0)                                                     NOT NULL,
    `create_date` bigint(0)                                                     NULL DEFAULT NULL,
    `create_user` bigint(0)                                                     NULL DEFAULT NULL,
    `delete_flag` bit(1)                                                        NULL DEFAULT NULL,
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `update_date` bigint(0)                                                     NULL DEFAULT NULL,
    `update_user` bigint(0)                                                     NULL DEFAULT NULL,
    `ip`          varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `port`        int(0)                                                        NULL DEFAULT NULL,
    `username`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `type`        int(0)                                                        NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '服务器表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_server
-- ----------------------------
INSERT INTO `sys_server`
VALUES (0, 1591950996, 0, b'0', NULL, 1592352590, 0, '192.168.1.101', '自己电脑上的虚拟机', '1234123', 22, 'root', 2);

-- ----------------------------
-- Table structure for sys_software
-- ----------------------------
DROP TABLE IF EXISTS `sys_software`;
CREATE TABLE `sys_software`
(
    `id`          bigint(0)                                                     NOT NULL,
    `create_date` bigint(0)                                                     NULL DEFAULT NULL,
    `create_user` bigint(0)                                                     NULL DEFAULT NULL,
    `delete_flag` bit(1)                                                        NULL DEFAULT NULL,
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `update_date` bigint(0)                                                     NULL DEFAULT NULL,
    `update_user` bigint(0)                                                     NULL DEFAULT NULL,
    `other_1`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `other_2`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `port`        int(0)                                                        NULL DEFAULT NULL,
    `server_id`   bigint(0)                                                     NULL DEFAULT NULL,
    `start_sh`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `status`      int(0)                                                        NULL DEFAULT NULL,
    `status_sh`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `stop_sh`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `type`        int(0)                                                        NULL DEFAULT NULL,
    `username`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `version`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '中间件表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_software
-- ----------------------------
INSERT INTO `sys_software`
VALUES (0, 1591968077, 0, b'0', NULL, 1592352634, 0, 'redis-single', NULL, 'uhyils', 6379, 0,
        'docker start redis-single', 1, 'docker inspect --format=\'{{.State.Running}}\' redis-single',
        'docker stop redis-single', 1, NULL, '5.0.8');

SET FOREIGN_KEY_CHECKS = 1;
