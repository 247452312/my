/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.101
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 192.168.1.101:3306
 Source Schema         : my_log

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 15/11/2020 17:56:23
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_black_list
-- ----------------------------
DROP TABLE IF EXISTS `sys_black_list`;
CREATE TABLE `sys_black_list`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `create_date` bigint(0) NULL DEFAULT NULL,
    `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `delete_flag` bit(1) NULL DEFAULT NULL,
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `update_date` bigint(0) NULL DEFAULT NULL,
    `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `ip`          varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户ip',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '黑名单'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
    `id`               varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `create_date`      bigint(0) NULL DEFAULT NULL,
    `create_user`      varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `delete_flag`      bit(1) NULL DEFAULT NULL,
    `remark`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `update_date`      bigint(0) NULL DEFAULT NULL,
    `update_user`      varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `exception_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '错误信息',
    `interface_name`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口名称',
    `method_name`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名称',
    `ip`               varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求ip',
    `log_type`         int(0) NULL DEFAULT NULL COMMENT '日志类型',
    `params`           text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数',
    `time`             bigint(0) NULL DEFAULT NULL COMMENT '发生时间',
    `user_id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作用户id',
    `link`             varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '日志'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log_monitor
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_monitor`;
CREATE TABLE `sys_log_monitor`
(
    `id`                varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `ip`                varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip',
    `service_name`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务名称',
    `time`              bigint(0) NULL DEFAULT NULL COMMENT 'jvm开启时间',
    `jvm_total_mem`     double NULL DEFAULT NULL COMMENT 'jvm最大内存',
    `heap_init_mem`     double NULL DEFAULT NULL COMMENT '堆初始内存',
    `heap_total_mem`    double NULL DEFAULT NULL COMMENT '堆最大内存',
    `no_heap_init_mem`  double NULL DEFAULT NULL COMMENT '非堆区初始内存',
    `no_heap_total_mem` double NULL DEFAULT NULL COMMENT '非堆区最大内存',
    `end_time`          bigint(0) NULL DEFAULT NULL COMMENT '服务jvm结束假想时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'JVM日志表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log_monitor_interface_call
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_monitor_interface_call`;
CREATE TABLE `sys_log_monitor_interface_call`
(
    `id`             varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `fid`            varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主表id',
    `interface_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口名称',
    `method_name`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名称',
    `run_time`       bigint(0) NULL DEFAULT NULL COMMENT '方法执行时间',
    `success`        bit(1) NULL DEFAULT NULL COMMENT '是否成功',
    `time`           bigint(0) NULL DEFAULT NULL COMMENT '这一条发送时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'JVM日志接口调用表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log_monitor_jvm_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_monitor_jvm_status`;
CREATE TABLE `sys_log_monitor_jvm_status`
(
    `id`              varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `fid`             varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主表id',
    `time`            bigint(0) NULL DEFAULT NULL COMMENT 'status生成时间',
    `heap_use_mem`    double NULL DEFAULT NULL COMMENT '堆 使用内存',
    `no_heap_use_mem` double NULL DEFAULT NULL COMMENT '非堆区使用内存',
    `use_mem`         double NULL DEFAULT NULL COMMENT '总使用内存',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'JVM状态子表'
  ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
