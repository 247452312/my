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

 Date: 09/12/2020 18:56:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_api`;
CREATE TABLE `sys_api`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `api_order` int(0) NULL DEFAULT NULL,
  `frequency` int(0) NULL DEFAULT NULL,
  `head` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `need_repeat` bit(1) NULL DEFAULT NULL,
  `param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `result_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `api_group_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'api表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_api
-- ----------------------------
INSERT INTO `sys_api` VALUES (0, 1593261871, 0, b'1', NULL, 1593262193, 0, 1, 10, '1', b'1', '11', 'GET', 'http://www.baidu.com', NULL, NULL);
INSERT INTO `sys_api` VALUES (1, 1593299553, 1, b'0', NULL, 1593310090, 1, 1, 20, 'Content-type:application/json;charset=UTF-8', b'1', '', 'GET', 'https://tianqiapi.com/api?version=v6&appid=31798799&appsecret=2m59xftt&cityid=101121201', 'unicode', 1);

-- ----------------------------
-- Table structure for sys_api_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_api_group`;
CREATE TABLE `sys_api_group`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `result_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `subscribe` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'api组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_api_group
-- ----------------------------
INSERT INTO `sys_api_group` VALUES (1, 1593480326, 0, b'0', NULL, 1593480326, 0, '免费实况天气接口', 'test${username}', b'1');

-- ----------------------------
-- Table structure for sys_api_subscribe
-- ----------------------------
DROP TABLE IF EXISTS `sys_api_subscribe`;
CREATE TABLE `sys_api_subscribe`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `api_id` bigint(0) NULL DEFAULT NULL,
  `cron` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `api_group_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'api订阅表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_api_subscribe
-- ----------------------------
INSERT INTO `sys_api_subscribe` VALUES (0, 1593498178, 0, b'0', NULL, 1593498178, 0, NULL, '0 0 5 * * ?', 1, 0, 0);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `cron` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `interface_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口名称',
  `method_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `param_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数类型',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `pause` bit(1) NULL DEFAULT NULL COMMENT '暂停',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (0, 1593473870, 0, b'0', NULL, 1593473870, 0, '0 30 17 * * ?', 'PushService', 'push', '订阅推送-晚上5:30', 'indi.uhyils.pojo.request.CronRequest', '{\"cron\":\"0 30 17 * * ?\"}', b'0');
INSERT INTO `sys_job` VALUES (1, 1593391369, 0, b'0', NULL, 1593395040, 0, '0 0 6 * * ? ', 'PushService', 'push', '订阅推送-早6:00', 'indi.uhyils.pojo.request.CronRequest', '{\"cron\":\"0 0 6 * * * ?\"}', b'0');
INSERT INTO `sys_job` VALUES (2, 1593473771, 0, b'0', NULL, 1593480150, 0, '0 0 12 * * ?', 'PushService', 'push', '订阅推送-中午12:00', 'indi.uhyils.pojo.request.CronRequest', '{\"cron\":\"0 0 12 * * * ?\"}', b'0');
INSERT INTO `sys_job` VALUES (3, 1593217767, 0, b'1', NULL, 1593219990, 0, '0/10 * * * * ?', 'RoleService', 'getById', '测试-角色接口-getRoleByRoleId', 'indi.uhyils.pojo.request.base.IdRequest', '{\"id\":\"0f2ab23bbb1cd11e\"}', b'1');
INSERT INTO `sys_job` VALUES (4, 1593473816, 0, b'0', NULL, 1593473816, 0, '0 30 13 * * ?', 'PushService', 'push', '订阅推送-下午1:30', 'indi.uhyils.pojo.request.CronRequest', '{\"cron\":\"0 30 13 * * ?\"}', b'0');
INSERT INTO `sys_job` VALUES (5, 1593473710, 0, b'0', NULL, 1593473735, 0, '0 0 5 * * ?', 'PushService', 'push', '订阅推送-早5:00', 'indi.uhyils.pojo.request.CronRequest', '{\"cron\":\"0 0 5 * * * ?\"}', b'0');
INSERT INTO `sys_job` VALUES (6, 1593473916, 0, b'0', NULL, 1593473916, 0, '0 0 18 * * ?', 'PushService', 'push', '订阅推送-晚上6:00', 'indi.uhyils.pojo.request.CronRequest', '{\"cron\":\"0 0 18 * * ?\"}', b'0');
INSERT INTO `sys_job` VALUES (7, 1593230549, 0, b'1', NULL, 1593391374, 0, '0/10 * * * * ?', 'RoleService', 'getById', '测试-角色接口-getRoleByRoleId', 'indi.uhyils.pojo.request.base.IdRequest', '{\"id\":\"0f2ab23bbb1cd11e\"}', b'1');

-- ----------------------------
-- Table structure for sys_push_msg
-- ----------------------------
DROP TABLE IF EXISTS `sys_push_msg`;
CREATE TABLE `sys_push_msg`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `success` bit(1) NULL DEFAULT NULL,
  `target` bigint(0) NULL DEFAULT NULL,
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '推送日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_push_msg
-- ----------------------------

-- ----------------------------
-- Table structure for sys_push_page_msg
-- ----------------------------
DROP TABLE IF EXISTS `sys_push_page_msg`;
CREATE TABLE `sys_push_page_msg`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `view` bit(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '推送日志信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_push_page_msg
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
