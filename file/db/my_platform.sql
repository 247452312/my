/*
 Navicat Premium Data Transfer

 Source Server         : mac
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : prod:3306
 Source Schema         : my_platform

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 14/02/2022 09:38:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_consumer_filter
-- ----------------------------
DROP TABLE IF EXISTS `sys_consumer_filter`;
CREATE TABLE `sys_consumer_filter`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `consumer_id` bigint(0) NULL DEFAULT NULL COMMENT '消费方id',
  `interface_id` bigint(0) NULL DEFAULT NULL COMMENT '接口表id',
  `rule` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规则',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消费过滤表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_consumer_filter
-- ----------------------------

-- ----------------------------
-- Table structure for sys_consumer_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_consumer_info`;
CREATE TABLE `sys_consumer_info`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `responsibility_tel_phone` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '责任人电话',
  `responsibility_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '责任人名称',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色id',
  `access_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'accessKey',
  `secret_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'secretKey',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消费者名称',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态(0->申请中 1->使用中 2->已停用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '服务消费方信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_consumer_info
-- ----------------------------
INSERT INTO `sys_consumer_info` VALUES (1, 1, 1, b'0', NULL, 1, 1, '17864217772', 'uhyils', 1, 'kjlsdavghkajhsdgf', '123456', 'root', 1);

-- ----------------------------
-- Table structure for sys_db_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_db_info`;
CREATE TABLE `sys_db_info`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库url',
  `type` int(0) NULL DEFAULT NULL COMMENT '数据库类型 0->mysql 1->oracle',
  `username` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据库连接表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_db_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_http_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_http_info`;
CREATE TABLE `sys_http_info`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `param_type` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '入参类型自定义规则',
  `return_type` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结果类型(demo)',
  `request_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'http连接表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_http_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_interface_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_interface_info`;
CREATE TABLE `sys_interface_info`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `provider_id` bigint(0) NULL DEFAULT NULL COMMENT '服务提供方id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口名称,在mysql中为表名',
  `pid` bigint(0) NULL DEFAULT NULL COMMENT '父类id,父类写连表sql,子类写接口',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型 0->数据库 1->mq 2->接口',
  `mark_id` bigint(0) NULL DEFAULT NULL COMMENT '类型对应的id,例如类型是0数据库,此字段就是数据库表id',
  `sql` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sql语句,详情见sql规则文档',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '接口信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_interface_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_mq_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_mq_info`;
CREATE TABLE `sys_mq_info`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'mq地址',
  `topic` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'MQ-topic',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'mq-tag',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'mq连接信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_mq_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_power`;
CREATE TABLE `sys_power`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色id',
  `interface_id` bigint(0) NULL DEFAULT NULL COMMENT '接口id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '接口权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_power
-- ----------------------------

-- ----------------------------
-- Table structure for sys_provider_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_provider_info`;
CREATE TABLE `sys_provider_info`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `unique_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一标识',
  `responsibility_tel_phone` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '责任人电话',
  `responsibility_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '责任人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '服务提供者表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_provider_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
