/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.101
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 192.168.1.101:3306
 Source Schema         : my_push

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 15/11/2020 17:57:05
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_api`;
CREATE TABLE `sys_api`
(
    `id`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `create_date`  bigint(0) NULL DEFAULT NULL,
    `create_user`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `delete_flag`  bit(1) NULL DEFAULT NULL,
    `remark`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `update_date`  bigint(0) NULL DEFAULT NULL,
    `update_user`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `api_order`    int(0) NULL DEFAULT NULL,
    `frequency`    int(0) NULL DEFAULT NULL,
    `head`         text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
    `need_repeat`  bit(1) NULL DEFAULT NULL,
    `param`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
    `type`         varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `url`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `result_code`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `api_group_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'api表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_api_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_api_group`;
CREATE TABLE `sys_api_group`
(
    `id`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `create_date`   bigint(0) NULL DEFAULT NULL,
    `create_user`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `delete_flag`   bit(1) NULL DEFAULT NULL,
    `remark`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `update_date`   bigint(0) NULL DEFAULT NULL,
    `update_user`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `name`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `result_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `subscribe`     bit(1) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'api组表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_api_subscribe
-- ----------------------------
DROP TABLE IF EXISTS `sys_api_subscribe`;
CREATE TABLE `sys_api_subscribe`
(
    `id`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `create_date`  bigint(0) NULL DEFAULT NULL,
    `create_user`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `delete_flag`  bit(1) NULL DEFAULT NULL,
    `remark`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `update_date`  bigint(0) NULL DEFAULT NULL,
    `update_user`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `api_id`       varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `cron`         varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `type`         int(0) NULL DEFAULT NULL,
    `user_id`      varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `api_group_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'api订阅表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`
(
    `id`             varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `create_date`    bigint(0) NULL DEFAULT NULL,
    `create_user`    varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `delete_flag`    bit(1) NULL DEFAULT NULL,
    `remark`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `update_date`    bigint(0) NULL DEFAULT NULL,
    `update_user`    varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `cron`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `interface_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口名称',
    `method_name`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名称',
    `name`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `param_type`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数类型',
    `params`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
    `pause`          bit(1) NULL DEFAULT NULL COMMENT '暂停',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '定时任务表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_push_msg
-- ----------------------------
DROP TABLE IF EXISTS `sys_push_msg`;
CREATE TABLE `sys_push_msg`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `create_date` bigint(0) NULL DEFAULT NULL,
    `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `delete_flag` bit(1) NULL DEFAULT NULL,
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `update_date` bigint(0) NULL DEFAULT NULL,
    `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `content`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
    `success`     bit(1) NULL DEFAULT NULL,
    `target`      varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `title`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `type`        int(0) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '推送日志表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_push_page_msg
-- ----------------------------
DROP TABLE IF EXISTS `sys_push_page_msg`;
CREATE TABLE `sys_push_page_msg`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `create_date` bigint(0) NULL DEFAULT NULL,
    `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `delete_flag` bit(1) NULL DEFAULT NULL,
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `update_date` bigint(0) NULL DEFAULT NULL,
    `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `content`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `title`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `user_id`     varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `view`        bit(1) NULL DEFAULT b'0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '推送日志信息表'
  ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
