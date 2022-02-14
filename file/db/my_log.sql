/*
 Navicat Premium Data Transfer

 Source Server         : mac
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : prod:3306
 Source Schema         : my_log

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 14/02/2022 09:29:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_black_list
-- ----------------------------
DROP TABLE IF EXISTS `sys_black_list`;
CREATE TABLE `sys_black_list`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `ip` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '黑名单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_black_list
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log_monitor
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_monitor`;
CREATE TABLE `sys_log_monitor`  (
  `id` bigint(0) NOT NULL,
  `ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip',
  `service_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务名称',
  `time` bigint(0) NULL DEFAULT NULL COMMENT 'jvm开启时间',
  `jvm_total_mem` double NULL DEFAULT NULL COMMENT 'jvm最大内存',
  `heap_init_mem` double NULL DEFAULT NULL COMMENT '堆初始内存',
  `heap_total_mem` double NULL DEFAULT NULL COMMENT '堆最大内存',
  `no_heap_init_mem` double NULL DEFAULT NULL COMMENT '非堆区初始内存',
  `no_heap_total_mem` double NULL DEFAULT NULL COMMENT '非堆区最大内存',
  `end_time` bigint(0) NULL DEFAULT NULL COMMENT '服务jvm结束假想时间',
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'JVM日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_monitor
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log_monitor_jvm_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_monitor_jvm_status`;
CREATE TABLE `sys_log_monitor_jvm_status`  (
  `id` bigint(0) NOT NULL,
  `fid` bigint(0) NULL DEFAULT NULL COMMENT '主表id',
  `time` bigint(0) NULL DEFAULT NULL COMMENT 'status生成时间',
  `heap_use_mem` double NULL DEFAULT NULL COMMENT '堆 使用内存',
  `no_heap_use_mem` double NULL DEFAULT NULL COMMENT '非堆区使用内存',
  `use_mem` double NULL DEFAULT NULL COMMENT '总使用内存',
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'JVM状态子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_monitor_jvm_status
-- ----------------------------

-- ----------------------------
-- Table structure for sys_relegation
-- ----------------------------
DROP TABLE IF EXISTS `sys_relegation`;
CREATE TABLE `sys_relegation`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `service_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口名称',
  `method_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `param_length` int(0) NULL DEFAULT NULL COMMENT '入参个数',
  `level` int(0) NULL DEFAULT NULL COMMENT '服务等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '接口降级策略' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_relegation
-- ----------------------------

-- ----------------------------
-- Table structure for sys_trace_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_trace_detail`;
CREATE TABLE `sys_trace_detail`  (
  `id` bigint(0) NOT NULL,
  `trace_id` bigint(0) NULL DEFAULT NULL,
  `hash_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `end_time` bigint(0) NULL DEFAULT NULL COMMENT '结束时间',
  `use_time` bigint(0) NULL DEFAULT NULL COMMENT '用时',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型',
  `other_one` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `other_two` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` tinyint(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_endtime`(`end_time`) USING BTREE,
  INDEX `idx_type_endtime`(`type`, `end_time`) USING BTREE,
  INDEX `idx_traceid`(`trace_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '链路跟踪信息详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_trace_detail
-- ----------------------------

-- ----------------------------
-- Table structure for sys_trace_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_trace_info`;
CREATE TABLE `sys_trace_info`  (
  `id` bigint(0) NOT NULL,
  `logger_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志名称',
  `level` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志等级',
  `trace_id` bigint(0) NULL DEFAULT NULL,
  `start_time` bigint(0) NULL DEFAULT NULL COMMENT '开始时间',
  `log_type` int(0) NULL DEFAULT NULL COMMENT '日志类型',
  `rpc_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip',
  `thread_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '线程名称',
  `project_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `use_time` bigint(0) NULL DEFAULT NULL COMMENT '使用时间',
  `hash_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '此条hash值',
  `other` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '其他',
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` tinyint(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_traceId_rpcid`(`trace_id`, `rpc_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '链路跟踪信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_trace_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_trace_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_trace_log`;
CREATE TABLE `sys_trace_log`  (
  `id` bigint(0) NOT NULL,
  `logger_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志名称',
  `log_level` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志等级',
  `trace_id` bigint(0) NULL DEFAULT NULL,
  `rpc_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `now_time` bigint(0) NULL DEFAULT NULL,
  `log` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` tinyint(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_traceid`(`trace_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '链路跟踪日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_trace_log
-- ----------------------------

-- ----------------------------
-- View structure for v_sys_trace_detail_statistics
-- ----------------------------
DROP VIEW IF EXISTS `v_sys_trace_detail_statistics`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_sys_trace_detail_statistics` AS select date_format(from_unixtime((`std`.`end_time` / 1000)),'%Y-%m-%d %H:%i') AS `time`,`std`.`type` AS `type`,(case `std`.`type` when 1 then 'RPC' when 2 then 'MQ' when 3 then 'DB' when 4 then 'TASK' when 5 then 'CONTROLLER' else 'OTHER' end) AS `type_name`,`std`.`hash_code` AS `hash_code`,count(0) AS `count`,sum(`std`.`use_time`) AS `sum`,avg(`std`.`use_time`) AS `avg` from `sys_trace_detail` `std` where (`std`.`end_time` > (unix_timestamp((now() - interval 30 minute)) * 1000)) group by `time`,`std`.`type`,`std`.`hash_code`;

-- ----------------------------
-- Records of sys_trace_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
