/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.101
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 192.168.1.101:3306
 Source Schema         : my_algorithm

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 15/11/2020 17:55:52
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_algorithm
-- ----------------------------
DROP TABLE IF EXISTS `sys_algorithm`;
CREATE TABLE `sys_algorithm`
(
    `id`              varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `create_date`     bigint(0) NULL DEFAULT NULL,
    `create_user`     varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `delete_flag`     bit(1) NULL DEFAULT NULL,
    `remark`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `update_date`     bigint(0) NULL DEFAULT NULL,
    `update_user`     varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `name`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '算法名称',
    `model_file_path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模型所在地',
    `accuracy`        double(4, 3
) NULL DEFAULT NULL COMMENT '准确率',
    `in_param_size`   int(0)                                                        NULL DEFAULT NULL COMMENT '输入参数',
    `out_param_size`  int(0)                                                        NULL DEFAULT NULL COMMENT '输出参数',
    `type`            int(0)                                                        NULL DEFAULT NULL COMMENT '算法类型(1->深度学习,2->神经网络)',
    `structure`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '算法结构,load时用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '算法表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_out_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_out_api`;
CREATE TABLE `sys_out_api`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `create_date` bigint(0) NULL DEFAULT NULL,
    `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `delete_flag` bit(1) NULL DEFAULT NULL,
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `update_date` bigint(0) NULL DEFAULT NULL,
    `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `app_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `secret_id`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `secret_key`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '开放api'
  ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
