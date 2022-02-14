/*
 Navicat Premium Data Transfer

 Source Server         : mac
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : prod:3306
 Source Schema         : my_user

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 14/02/2022 09:40:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_content
-- ----------------------------
DROP TABLE IF EXISTS `sys_content`;
CREATE TABLE `sys_content`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title6` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `var1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `var2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `var3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `var4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `var5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `var6` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `nameIndex`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公共参数' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_content
-- ----------------------------
INSERT INTO `sys_content` VALUES (1685466876911550496, 1, 0, b'0', NULL, 1, 0, 'logoInfo', 'href', 'image', 'title', NULL, NULL, NULL, '', '/pic/my-uhyils.png', 'my-uhyils', NULL, NULL, NULL);
INSERT INTO `sys_content` VALUES (1685466876915744800, 1, 0, b'0', NULL, 1, 0, 'indexIframe', 'indexIframe', '', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_content` VALUES (1685466876916793376, 1, 0, b'0', NULL, 1, 0, 'honeInfo', 'title', 'href', NULL, NULL, NULL, NULL, '首页', 'page/welcome-1.html', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1685466876918890528, 1591512277, 0, b'0', NULL, 1591512277, 0, '测试用户权限集');
INSERT INTO `sys_dept` VALUES (1685466876920987680, 1591512159, 0, b'0', NULL, 1591512159, 0, '超级管理员权限集');

-- ----------------------------
-- Table structure for sys_dept_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_menu`;
CREATE TABLE `sys_dept_menu`  (
  `id` bigint(0) NOT NULL,
  `dept_id` bigint(0) NULL DEFAULT NULL,
  `menu_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门-菜单关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_power`;
CREATE TABLE `sys_dept_power`  (
  `id` bigint(0) NOT NULL,
  `dept_id` bigint(0) NULL DEFAULT NULL,
  `power_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门-权限关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept_power
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1685467061000601632, 1592697362, 0, b'0', NULL, 1592697362, 0, 'lastPlan', '下一步计划', '下一步计划', 1);
INSERT INTO `sys_dict` VALUES (1685467061004795936, 1592697179, 0, b'0', NULL, 1592697179, 0, 'myVersionCode', '首页展示的版本信息', '版本信息', 1);
INSERT INTO `sys_dict` VALUES (1685467061006893088, 1593385798, 0, b'0', NULL, 1593385798, 0, 'pushType', '推送时发送信息的方式', '推送类型', 0);
INSERT INTO `sys_dict` VALUES (1685467061008990240, 1593473419, 0, b'0', NULL, 1593473419, 0, 'job_cron', '服务订阅可用时间', '服务订阅可用时间', 1);
INSERT INTO `sys_dict` VALUES (1685467061010038816, 1600420004, 0, b'0', NULL, 1600420058, 0, 'concurrent_num_dict_code', '服务自动降级并发数', '服务自动降级并发数', 0);
INSERT INTO `sys_dict` VALUES (1685467061012135968, 1593310051, 0, b'0', NULL, 1593310051, 0, 'result-code', 'api返回值编码', 'api返回值编码', 1);
INSERT INTO `sys_dict` VALUES (1685467061013184544, 1593216064, 0, b'0', NULL, 1593216064, 0, 'job-paramType', '定时任务请求类', '定时任务请求类', 1);
INSERT INTO `sys_dict` VALUES (1685467061014233120, 1592348517, 0, b'0', NULL, 1592348517, 0, 'icon-class', '整个项目的图标库', '图标', 1);
INSERT INTO `sys_dict` VALUES (1685467061018427424, 1592705561, 0, b'0', NULL, 1592705561, 0, 'quickStart', '首页的快捷入口', '快捷入口', 1);
INSERT INTO `sys_dict` VALUES (1685467061020524576, 1592348794, 0, b'0', NULL, 1592348794, 0, 'server-type', '服务器系统类型', '服务器类型', 0);
INSERT INTO `sys_dict` VALUES (1685467061022621728, 1604036329, 0, b'0', NULL, 1604036329, 0, 'doc_iframe', '文档适用场景', '文档适用场景', 0);
INSERT INTO `sys_dict` VALUES (1685467061024718880, 1593215861, 0, b'0', NULL, 1593215861, 0, 'job-interfaceName', '定时任务可用接口', '定时任务可用接口', 1);
INSERT INTO `sys_dict` VALUES (1685467061027864608, 1592298770, 0, b'1', NULL, 1592348263, 0, 'serviceCode', '页面请求后台时返回的编码', '请求返回值', 0);
INSERT INTO `sys_dict` VALUES (1685647517794959392, 1607558744, 0, b'0', NULL, 1607558744, 0, 'OrderType', '订单类型', '订单类型', 0);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dict_id` bigint(0) NULL DEFAULT NULL,
  `sort_order` int(0) NULL DEFAULT NULL,
  `text` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典子项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (0, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-ils', 1685467061014233120, 409, 'fa-ils', 'fa fa-ils', NULL);
INSERT INTO `sys_dict_item` VALUES (1, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-wordpress', 1685467061014233120, 637, 'fa-wordpress', 'fa fa-wordpress', NULL);
INSERT INTO `sys_dict_item` VALUES (2, 1593473559, 0, b'0', NULL, 1593473559, 0, '晚上5:30', 1685467061008990240, 5, '晚上5:30', '0 30 17 * * ?', NULL);
INSERT INTO `sys_dict_item` VALUES (3, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-motorcycle', 1685467061014233120, 341, 'fa-motorcycle', 'fa fa-motorcycle', NULL);
INSERT INTO `sys_dict_item` VALUES (4, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-eject', 1685467061014233120, 506, 'fa-eject', 'fa fa-eject', NULL);
INSERT INTO `sys_dict_item` VALUES (5, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-institution', 1685467061014233120, 161, 'fa-institution', 'fa fa-institution', NULL);
INSERT INTO `sys_dict_item` VALUES (6, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-chevron-circle-left', 1685467061014233120, 488, 'fa-chevron-circle-left', 'fa fa-chevron-circle-left', NULL);
INSERT INTO `sys_dict_item` VALUES (7, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bus', 1685467061014233120, 37, 'fa-bus', 'fa fa-bus', NULL);
INSERT INTO `sys_dict_item` VALUES (8, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-pinterest-p', 1685467061014233120, 598, 'fa-pinterest-p', 'fa fa-pinterest-p', NULL);
INSERT INTO `sys_dict_item` VALUES (9, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-university', 1685467061014233120, 317, 'fa-university', 'fa fa-university', NULL);
INSERT INTO `sys_dict_item` VALUES (10, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-credit-card', 1685467061014233120, 79, 'fa-credit-card', 'fa fa-credit-card', NULL);
INSERT INTO `sys_dict_item` VALUES (11, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-facebook', 1685467061014233120, 561, 'fa-facebook', 'fa fa-facebook', NULL);
INSERT INTO `sys_dict_item` VALUES (12, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-hospital-o', 1685467061014233120, 528, 'fa-hospital-o', 'fa fa-hospital-o', NULL);
INSERT INTO `sys_dict_item` VALUES (13, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-fire-extinguisher', 1685467061014233120, 127, 'fa-fire-extinguisher', 'fa fa-fire-extinguisher', NULL);
INSERT INTO `sys_dict_item` VALUES (14, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-slideshare', 1685467061014233120, 613, 'fa-slideshare', 'fa fa-slideshare', NULL);
INSERT INTO `sys_dict_item` VALUES (15, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-fighter-jet', 1685467061014233120, 340, 'fa-fighter-jet', 'fa fa-fighter-jet', NULL);
INSERT INTO `sys_dict_item` VALUES (16, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-calculator', 1685467061014233120, 39, 'fa-calculator', 'fa fa-calculator', NULL);
INSERT INTO `sys_dict_item` VALUES (17, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-star', 1685467061014233120, 277, 'fa-star', 'fa fa-star', NULL);
INSERT INTO `sys_dict_item` VALUES (18, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrow-circle-o-right', 1685467061014233120, 469, 'fa-arrow-circle-o-right', 'fa fa-arrow-circle-o-right', NULL);
INSERT INTO `sys_dict_item` VALUES (19, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-mail-reply-all', 1685467061014233120, 183, 'fa-mail-reply-all', 'fa fa-mail-reply-all', NULL);
INSERT INTO `sys_dict_item` VALUES (20, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-linkedin', 1685467061014233120, 586, 'fa-linkedin', 'fa fa-linkedin', NULL);
INSERT INTO `sys_dict_item` VALUES (21, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-navicon', 1685467061014233120, 200, 'fa-navicon', 'fa fa-navicon', NULL);
INSERT INTO `sys_dict_item` VALUES (22, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-fax', 1685467061014233120, 107, 'fa-fax', 'fa fa-fax', NULL);
INSERT INTO `sys_dict_item` VALUES (23, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-spoon', 1685467061014233120, 274, 'fa-spoon', 'fa fa-spoon', NULL);
INSERT INTO `sys_dict_item` VALUES (24, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-vk', 1685467061014233120, 632, 'fa-vk', 'fa fa-vk', NULL);
INSERT INTO `sys_dict_item` VALUES (25, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-film', 1685467061014233120, 124, 'fa-film', 'fa fa-film', NULL);
INSERT INTO `sys_dict_item` VALUES (26, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrows-h', 1685467061014233120, 477, 'fa-arrows-h', 'fa fa-arrows-h', NULL);
INSERT INTO `sys_dict_item` VALUES (27, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-folder-open-o', 1685467061014233120, 136, 'fa-folder-open-o', 'fa fa-folder-open-o', NULL);
INSERT INTO `sys_dict_item` VALUES (28, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-o', 1685467061014233120, 437, 'fa-file-o', 'fa fa-file-o', NULL);
INSERT INTO `sys_dict_item` VALUES (29, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-chevron-left', 1685467061014233120, 492, 'fa-chevron-left', 'fa fa-chevron-left', NULL);
INSERT INTO `sys_dict_item` VALUES (30, 1593473490, 0, b'0', NULL, 1593473490, 0, '早上6点', 1685467061008990240, 2, '早6:00', '0 0 6 * * ?', NULL);
INSERT INTO `sys_dict_item` VALUES (31, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-android', 1685467061014233120, 535, 'fa-android', 'fa fa-android', NULL);
INSERT INTO `sys_dict_item` VALUES (32, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-rocket', 1685467061014233120, 237, 'fa-rocket', 'fa fa-rocket', NULL);
INSERT INTO `sys_dict_item` VALUES (33, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-forward', 1685467061014233120, 510, 'fa-forward', 'fa fa-forward', NULL);
INSERT INTO `sys_dict_item` VALUES (34, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-inbox', 1685467061014233120, 158, 'fa-inbox', 'fa fa-inbox', NULL);
INSERT INTO `sys_dict_item` VALUES (35, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-fire', 1685467061014233120, 126, 'fa-fire', 'fa fa-fire', NULL);
INSERT INTO `sys_dict_item` VALUES (36, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-unsorted', 1685467061014233120, 320, 'fa-unsorted', 'fa fa-unsorted', NULL);
INSERT INTO `sys_dict_item` VALUES (37, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-exchange', 1685467061014233120, 98, 'fa-exchange', 'fa fa-exchange', NULL);
INSERT INTO `sys_dict_item` VALUES (38, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-neuter', 1685467061014233120, 357, 'fa-neuter', 'fa fa-neuter', NULL);
INSERT INTO `sys_dict_item` VALUES (39, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-facebook-official', 1685467061014233120, 562, 'fa-facebook-official', 'fa fa-facebook-official', NULL);
INSERT INTO `sys_dict_item` VALUES (40, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-truck', 1685467061014233120, 348, 'fa-truck', 'fa fa-truck', NULL);
INSERT INTO `sys_dict_item` VALUES (41, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-trello', 1685467061014233120, 623, 'fa-trello', 'fa fa-trello', NULL);
INSERT INTO `sys_dict_item` VALUES (42, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-archive-o', 1685467061014233120, 110, 'fa-file-archive-o', 'fa fa-file-archive-o', NULL);
INSERT INTO `sys_dict_item` VALUES (43, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-shirtsinbulk', 1685467061014233120, 608, 'fa-shirtsinbulk', 'fa fa-shirtsinbulk', NULL);
INSERT INTO `sys_dict_item` VALUES (44, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-audio-o', 1685467061014233120, 111, 'fa-file-audio-o', 'fa fa-file-audio-o', NULL);
INSERT INTO `sys_dict_item` VALUES (45, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-motorcycle', 1685467061014233120, 198, 'fa-motorcycle', 'fa fa-motorcycle', NULL);
INSERT INTO `sys_dict_item` VALUES (46, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-headphones', 1685467061014233120, 150, 'fa-headphones', 'fa fa-headphones', NULL);
INSERT INTO `sys_dict_item` VALUES (47, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-long-arrow-left', 1685467061014233120, 500, 'fa-long-arrow-left', 'fa fa-long-arrow-left', NULL);
INSERT INTO `sys_dict_item` VALUES (48, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-thumb-tack', 1685467061014233120, 294, 'fa-thumb-tack', 'fa fa-thumb-tack', NULL);
INSERT INTO `sys_dict_item` VALUES (49, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-car', 1685467061014233120, 44, 'fa-car', 'fa fa-car', NULL);
INSERT INTO `sys_dict_item` VALUES (50, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-paypal', 1685467061014233120, 399, 'fa-paypal', 'fa fa-paypal', NULL);
INSERT INTO `sys_dict_item` VALUES (51, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-sound-o', 1685467061014233120, 120, 'fa-file-sound-o', 'fa fa-file-sound-o', NULL);
INSERT INTO `sys_dict_item` VALUES (52, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cc-paypal', 1685467061014233120, 547, 'fa-cc-paypal', 'fa fa-cc-paypal', NULL);
INSERT INTO `sys_dict_item` VALUES (53, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cc-stripe', 1685467061014233120, 395, 'fa-cc-stripe', 'fa fa-cc-stripe', NULL);
INSERT INTO `sys_dict_item` VALUES (54, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-tty', 1685467061014233120, 315, 'fa-tty', 'fa fa-tty', NULL);
INSERT INTO `sys_dict_item` VALUES (55, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-behance-square', 1685467061014233120, 539, 'fa-behance-square', 'fa fa-behance-square', NULL);
INSERT INTO `sys_dict_item` VALUES (56, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-plane', 1685467061014233120, 214, 'fa-plane', 'fa fa-plane', NULL);
INSERT INTO `sys_dict_item` VALUES (57, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-lock', 1685467061014233120, 178, 'fa-lock', 'fa fa-lock', NULL);
INSERT INTO `sys_dict_item` VALUES (58, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-folder', 1685467061014233120, 133, 'fa-folder', 'fa fa-folder', NULL);
INSERT INTO `sys_dict_item` VALUES (59, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-crop', 1685467061014233120, 80, 'fa-crop', 'fa fa-crop', NULL);
INSERT INTO `sys_dict_item` VALUES (60, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-meanpath', 1685467061014233120, 590, 'fa-meanpath', 'fa fa-meanpath', NULL);
INSERT INTO `sys_dict_item` VALUES (61, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-circle-thin', 1685467061014233120, 62, 'fa-circle-thin', 'fa fa-circle-thin', NULL);
INSERT INTO `sys_dict_item` VALUES (62, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-spinner', 1685467061014233120, 379, 'fa-spinner', 'fa fa-spinner', NULL);
INSERT INTO `sys_dict_item` VALUES (63, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-mars-stroke-v', 1685467061014233120, 355, 'fa-mars-stroke-v', 'fa fa-mars-stroke-v', NULL);
INSERT INTO `sys_dict_item` VALUES (64, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-reddit-square', 1685467061014233120, 603, 'fa-reddit-square', 'fa fa-reddit-square', NULL);
INSERT INTO `sys_dict_item` VALUES (65, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-home', 1685467061014233120, 155, 'fa-home', 'fa fa-home', NULL);
INSERT INTO `sys_dict_item` VALUES (66, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-group', 1685467061014233120, 148, 'fa-group', 'fa fa-group', NULL);
INSERT INTO `sys_dict_item` VALUES (67, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-keyboard-o', 1685467061014233120, 163, 'fa-keyboard-o', 'fa fa-keyboard-o', NULL);
INSERT INTO `sys_dict_item` VALUES (68, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-asterisk', 1685467061014233120, 8, 'fa-asterisk', 'fa fa-asterisk', NULL);
INSERT INTO `sys_dict_item` VALUES (69, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-photo', 1685467061014233120, 211, 'fa-photo', 'fa fa-photo', NULL);
INSERT INTO `sys_dict_item` VALUES (70, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-toggle-left', 1685467061014233120, 305, 'fa-toggle-left', 'fa fa-toggle-left', NULL);
INSERT INTO `sys_dict_item` VALUES (71, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-buysellads', 1685467061014233120, 543, 'fa-buysellads', 'fa fa-buysellads', NULL);
INSERT INTO `sys_dict_item` VALUES (72, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrow-circle-up', 1685467061014233120, 466, 'fa-arrow-circle-up', 'fa fa-arrow-circle-up', NULL);
INSERT INTO `sys_dict_item` VALUES (73, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sort-amount-asc', 1685467061014233120, 264, 'fa-sort-amount-asc', 'fa fa-sort-amount-asc', NULL);
INSERT INTO `sys_dict_item` VALUES (74, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-google', 1685467061014233120, 573, 'fa-google', 'fa fa-google', NULL);
INSERT INTO `sys_dict_item` VALUES (75, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-info-circle', 1685467061014233120, 160, 'fa-info-circle', 'fa fa-info-circle', NULL);
INSERT INTO `sys_dict_item` VALUES (76, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-share', 1685467061014233120, 246, 'fa-share', 'fa fa-share', NULL);
INSERT INTO `sys_dict_item` VALUES (77, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-toggle-right', 1685467061014233120, 308, 'fa-toggle-right', 'fa fa-toggle-right', NULL);
INSERT INTO `sys_dict_item` VALUES (78, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-frown-o', 1685467061014233120, 137, 'fa-frown-o', 'fa fa-frown-o', NULL);
INSERT INTO `sys_dict_item` VALUES (79, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-tree', 1685467061014233120, 312, 'fa-tree', 'fa fa-tree', NULL);
INSERT INTO `sys_dict_item` VALUES (80, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-rebel', 1685467061014233120, 601, 'fa-rebel', 'fa fa-rebel', NULL);
INSERT INTO `sys_dict_item` VALUES (81, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-play', 1685467061014233120, 512, 'fa-play', 'fa fa-play', NULL);
INSERT INTO `sys_dict_item` VALUES (82, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrows-alt', 1685467061014233120, 476, 'fa-arrows-alt', 'fa fa-arrows-alt', NULL);
INSERT INTO `sys_dict_item` VALUES (83, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-user-secret', 1685467061014233120, 324, 'fa-user-secret', 'fa fa-user-secret', NULL);
INSERT INTO `sys_dict_item` VALUES (84, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-users', 1685467061014233120, 326, 'fa-users', 'fa fa-users', NULL);
INSERT INTO `sys_dict_item` VALUES (85, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cc-amex', 1685467061014233120, 391, 'fa-cc-amex', 'fa fa-cc-amex', NULL);
INSERT INTO `sys_dict_item` VALUES (86, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bell-slash', 1685467061014233120, 21, 'fa-bell-slash', 'fa fa-bell-slash', NULL);
INSERT INTO `sys_dict_item` VALUES (87, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-share-alt', 1685467061014233120, 606, 'fa-share-alt', 'fa fa-share-alt', NULL);
INSERT INTO `sys_dict_item` VALUES (88, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-random', 1685467061014233120, 228, 'fa-random', 'fa fa-random', NULL);
INSERT INTO `sys_dict_item` VALUES (89, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-share-alt', 1685467061014233120, 247, 'fa-share-alt', 'fa fa-share-alt', NULL);
INSERT INTO `sys_dict_item` VALUES (90, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-compass', 1685467061014233120, 77, 'fa-compass', 'fa fa-compass', NULL);
INSERT INTO `sys_dict_item` VALUES (91, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-volume-off', 1685467061014233120, 329, 'fa-volume-off', 'fa fa-volume-off', NULL);
INSERT INTO `sys_dict_item` VALUES (92, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-joomla', 1685467061014233120, 581, 'fa-joomla', 'fa fa-joomla', NULL);
INSERT INTO `sys_dict_item` VALUES (93, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-caret-square-o-right', 1685467061014233120, 485, 'fa-caret-square-o-right', 'fa fa-caret-square-o-right', NULL);
INSERT INTO `sys_dict_item` VALUES (94, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-support', 1685467061014233120, 286, 'fa-support', 'fa fa-support', NULL);
INSERT INTO `sys_dict_item` VALUES (95, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-fast-backward', 1685467061014233120, 508, 'fa-fast-backward', 'fa fa-fast-backward', NULL);
INSERT INTO `sys_dict_item` VALUES (96, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sort-alpha-desc', 1685467061014233120, 263, 'fa-sort-alpha-desc', 'fa fa-sort-alpha-desc', NULL);
INSERT INTO `sys_dict_item` VALUES (97, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-car', 1685467061014233120, 336, 'fa-car', 'fa fa-car', NULL);
INSERT INTO `sys_dict_item` VALUES (98, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bomb', 1685467061014233120, 27, 'fa-bomb', 'fa fa-bomb', NULL);
INSERT INTO `sys_dict_item` VALUES (99, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-transgender', 1685467061014233120, 358, 'fa-transgender', 'fa fa-transgender', NULL);
INSERT INTO `sys_dict_item` VALUES (100, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sign-out', 1685467061014233120, 255, 'fa-sign-out', 'fa fa-sign-out', NULL);
INSERT INTO `sys_dict_item` VALUES (101, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-magnet', 1685467061014233120, 180, 'fa-magnet', 'fa fa-magnet', NULL);
INSERT INTO `sys_dict_item` VALUES (102, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-font', 1685467061014233120, 420, 'fa-font', 'fa fa-font', NULL);
INSERT INTO `sys_dict_item` VALUES (103, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrow-circle-left', 1685467061014233120, 464, 'fa-arrow-circle-left', 'fa fa-arrow-circle-left', NULL);
INSERT INTO `sys_dict_item` VALUES (104, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-heart-o', 1685467061014233120, 526, 'fa-heart-o', 'fa fa-heart-o', NULL);
INSERT INTO `sys_dict_item` VALUES (105, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-chevron-circle-up', 1685467061014233120, 490, 'fa-chevron-circle-up', 'fa fa-chevron-circle-up', NULL);
INSERT INTO `sys_dict_item` VALUES (106, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-share-square-o', 1685467061014233120, 250, 'fa-share-square-o', 'fa fa-share-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES (107, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-rub', 1685467061014233120, 413, 'fa-rub', 'fa fa-rub', NULL);
INSERT INTO `sys_dict_item` VALUES (108, 1593385826, 0, b'0', NULL, 1593385826, 0, '邮件推送', 1685467061006893088, 1, '邮件推送', '1', NULL);
INSERT INTO `sys_dict_item` VALUES (109, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-ambulance', 1685467061014233120, 523, 'fa-ambulance', 'fa fa-ambulance', NULL);
INSERT INTO `sys_dict_item` VALUES (110, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-chevron-circle-down', 1685467061014233120, 487, 'fa-chevron-circle-down', 'fa fa-chevron-circle-down', NULL);
INSERT INTO `sys_dict_item` VALUES (111, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-flask', 1685467061014233120, 132, 'fa-flask', 'fa fa-flask', NULL);
INSERT INTO `sys_dict_item` VALUES (112, 1593310078, 0, b'0', NULL, 1593310078, 0, 'unicode编码', 1685467061012135968, 2, 'unicode', 'unicode', NULL);
INSERT INTO `sys_dict_item` VALUES (113, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-share-alt-square', 1685467061014233120, 607, 'fa-share-alt-square', 'fa fa-share-alt-square', NULL);
INSERT INTO `sys_dict_item` VALUES (114, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cc-discover', 1685467061014233120, 392, 'fa-cc-discover', 'fa fa-cc-discover', NULL);
INSERT INTO `sys_dict_item` VALUES (115, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-caret-square-o-left', 1685467061014233120, 46, 'fa-caret-square-o-left', 'fa fa-caret-square-o-left', NULL);
INSERT INTO `sys_dict_item` VALUES (116, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-linux', 1685467061014233120, 588, 'fa-linux', 'fa fa-linux', NULL);
INSERT INTO `sys_dict_item` VALUES (117, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-youtube', 1685467061014233120, 642, 'fa-youtube', 'fa fa-youtube', NULL);
INSERT INTO `sys_dict_item` VALUES (118, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-pie-chart', 1685467061014233120, 213, 'fa-pie-chart', 'fa fa-pie-chart', NULL);
INSERT INTO `sys_dict_item` VALUES (119, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-search', 1685467061014233120, 240, 'fa-search', 'fa fa-search', NULL);
INSERT INTO `sys_dict_item` VALUES (120, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bars', 1685467061014233120, 16, 'fa-bars', 'fa fa-bars', NULL);
INSERT INTO `sys_dict_item` VALUES (121, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-square-o', 1685467061014233120, 390, 'fa-square-o', 'fa fa-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES (122, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-share-square', 1685467061014233120, 249, 'fa-share-square', 'fa fa-share-square', NULL);
INSERT INTO `sys_dict_item` VALUES (123, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-hand-o-down', 1685467061014233120, 495, 'fa-hand-o-down', 'fa fa-hand-o-down', NULL);
INSERT INTO `sys_dict_item` VALUES (124, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-area-chart', 1685467061014233120, 400, 'fa-area-chart', 'fa fa-area-chart', NULL);
INSERT INTO `sys_dict_item` VALUES (125, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-server', 1685467061014233120, 245, 'fa-server', 'fa fa-server', NULL);
INSERT INTO `sys_dict_item` VALUES (126, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-code-fork', 1685467061014233120, 69, 'fa-code-fork', 'fa fa-code-fork', NULL);
INSERT INTO `sys_dict_item` VALUES (127, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-image-o', 1685467061014233120, 114, 'fa-file-image-o', 'fa fa-file-image-o', NULL);
INSERT INTO `sys_dict_item` VALUES (128, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-eyedropper', 1685467061014233120, 106, 'fa-eyedropper', 'fa fa-eyedropper', NULL);
INSERT INTO `sys_dict_item` VALUES (129, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-search-minus', 1685467061014233120, 241, 'fa-search-minus', 'fa fa-search-minus', NULL);
INSERT INTO `sys_dict_item` VALUES (130, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-youtube-square', 1685467061014233120, 644, 'fa-youtube-square', 'fa fa-youtube-square', NULL);
INSERT INTO `sys_dict_item` VALUES (131, 1592705701, 0, b'0', NULL, 1592706471, 0, 'push测试快捷入口', 1685467061018427424, 4, 'push测试', '1685590241748975648', NULL);
INSERT INTO `sys_dict_item` VALUES (132, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-text-height', 1685467061014233120, 425, 'fa-text-height', 'fa fa-text-height', NULL);
INSERT INTO `sys_dict_item` VALUES (133, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-leaf', 1685467061014233120, 166, 'fa-leaf', 'fa fa-leaf', NULL);
INSERT INTO `sys_dict_item` VALUES (134, 1592697405, 0, b'0', NULL, 1592697405, 0, '第一行显示', 1685467061000601632, 1, '第一项', '1.将启动和停止功能修改为多选启动停止', NULL);
INSERT INTO `sys_dict_item` VALUES (135, 1592705677, 0, b'0', NULL, 1592706464, 0, '文件管理快捷入口', 1685467061018427424, 3, '文件管理', '1685590241748975648', NULL);
INSERT INTO `sys_dict_item` VALUES (136, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-ellipsis-v', 1685467061014233120, 93, 'fa-ellipsis-v', 'fa fa-ellipsis-v', NULL);
INSERT INTO `sys_dict_item` VALUES (137, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-video-camera', 1685467061014233120, 327, 'fa-video-camera', 'fa fa-video-camera', NULL);
INSERT INTO `sys_dict_item` VALUES (138, 1593216123, 0, b'0', NULL, 1593216123, 0, '默认请求', 1685467061013184544, 1, 'defaultRequest', 'indi.uhyils.pojo.request.base.DefaultRequest', NULL);
INSERT INTO `sys_dict_item` VALUES (139, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-long-arrow-right', 1685467061014233120, 501, 'fa-long-arrow-right', 'fa fa-long-arrow-right', NULL);
INSERT INTO `sys_dict_item` VALUES (140, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-puzzle-piece', 1685467061014233120, 222, 'fa-puzzle-piece', 'fa fa-puzzle-piece', NULL);
INSERT INTO `sys_dict_item` VALUES (141, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-skype', 1685467061014233120, 611, 'fa-skype', 'fa fa-skype', NULL);
INSERT INTO `sys_dict_item` VALUES (142, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sort-asc', 1685467061014233120, 266, 'fa-sort-asc', 'fa fa-sort-asc', NULL);
INSERT INTO `sys_dict_item` VALUES (143, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-random', 1685467061014233120, 522, 'fa-random', 'fa fa-random', NULL);
INSERT INTO `sys_dict_item` VALUES (144, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrows', 1685467061014233120, 475, 'fa-arrows', 'fa fa-arrows', NULL);
INSERT INTO `sys_dict_item` VALUES (145, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-key', 1685467061014233120, 162, 'fa-key', 'fa fa-key', NULL);
INSERT INTO `sys_dict_item` VALUES (146, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-paw', 1685467061014233120, 205, 'fa-paw', 'fa fa-paw', NULL);
INSERT INTO `sys_dict_item` VALUES (147, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-qq', 1685467061014233120, 600, 'fa-qq', 'fa fa-qq', NULL);
INSERT INTO `sys_dict_item` VALUES (148, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-behance', 1685467061014233120, 538, 'fa-behance', 'fa fa-behance', NULL);
INSERT INTO `sys_dict_item` VALUES (149, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrows-h', 1685467061014233120, 6, 'fa-arrows-h', 'fa fa-arrows-h', NULL);
INSERT INTO `sys_dict_item` VALUES (150, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-plus-square', 1685467061014233120, 387, 'fa-plus-square', 'fa fa-plus-square', NULL);
INSERT INTO `sys_dict_item` VALUES (151, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-minus-circle', 1685467061014233120, 190, 'fa-minus-circle', 'fa fa-minus-circle', NULL);
INSERT INTO `sys_dict_item` VALUES (152, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-adjust', 1685467061014233120, 1, 'fa-adjust', 'fa fa-adjust', NULL);
INSERT INTO `sys_dict_item` VALUES (153, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-jsfiddle', 1685467061014233120, 582, 'fa-jsfiddle', 'fa fa-jsfiddle', NULL);
INSERT INTO `sys_dict_item` VALUES (154, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-renren', 1685467061014233120, 604, 'fa-renren', 'fa fa-renren', NULL);
INSERT INTO `sys_dict_item` VALUES (155, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sign-in', 1685467061014233120, 254, 'fa-sign-in', 'fa fa-sign-in', NULL);
INSERT INTO `sys_dict_item` VALUES (156, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-tachometer', 1685467061014233120, 288, 'fa-tachometer', 'fa fa-tachometer', NULL);
INSERT INTO `sys_dict_item` VALUES (157, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-vine', 1685467061014233120, 631, 'fa-vine', 'fa fa-vine', NULL);
INSERT INTO `sys_dict_item` VALUES (158, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-venus-double', 1685467061014233120, 361, 'fa-venus-double', 'fa fa-venus-double', NULL);
INSERT INTO `sys_dict_item` VALUES (159, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-crosshairs', 1685467061014233120, 81, 'fa-crosshairs', 'fa fa-crosshairs', NULL);
INSERT INTO `sys_dict_item` VALUES (160, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-jpy', 1685467061014233120, 405, 'fa-jpy', 'fa fa-jpy', NULL);
INSERT INTO `sys_dict_item` VALUES (161, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-gamepad', 1685467061014233120, 139, 'fa-gamepad', 'fa fa-gamepad', NULL);
INSERT INTO `sys_dict_item` VALUES (162, 1592299479, 0, b'1', NULL, 1592299479, 0, '服务器内部错误', 1685467061027864608, 6, 'ERROR', '500', NULL);
INSERT INTO `sys_dict_item` VALUES (163, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-comment-o', 1685467061014233120, 74, 'fa-comment-o', 'fa fa-comment-o', NULL);
INSERT INTO `sys_dict_item` VALUES (164, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-stethoscope', 1685467061014233120, 531, 'fa-stethoscope', 'fa fa-stethoscope', NULL);
INSERT INTO `sys_dict_item` VALUES (165, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-envelope', 1685467061014233120, 94, 'fa-envelope', 'fa fa-envelope', NULL);
INSERT INTO `sys_dict_item` VALUES (166, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-hdd-o', 1685467061014233120, 149, 'fa-hdd-o', 'fa fa-hdd-o', NULL);
INSERT INTO `sys_dict_item` VALUES (167, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-xing', 1685467061014233120, 638, 'fa-xing', 'fa fa-xing', NULL);
INSERT INTO `sys_dict_item` VALUES (168, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-xing-square', 1685467061014233120, 639, 'fa-xing-square', 'fa fa-xing-square', NULL);
INSERT INTO `sys_dict_item` VALUES (169, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-coffee', 1685467061014233120, 70, 'fa-coffee', 'fa fa-coffee', NULL);
INSERT INTO `sys_dict_item` VALUES (170, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-google-plus', 1685467061014233120, 574, 'fa-google-plus', 'fa fa-google-plus', NULL);
INSERT INTO `sys_dict_item` VALUES (171, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-flag-checkered', 1685467061014233120, 129, 'fa-flag-checkered', 'fa fa-flag-checkered', NULL);
INSERT INTO `sys_dict_item` VALUES (172, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-check-square-o', 1685467061014233120, 57, 'fa-check-square-o', 'fa fa-check-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES (173, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-wrench', 1685467061014233120, 334, 'fa-wrench', 'fa fa-wrench', NULL);
INSERT INTO `sys_dict_item` VALUES (174, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrows-alt', 1685467061014233120, 503, 'fa-arrows-alt', 'fa fa-arrows-alt', NULL);
INSERT INTO `sys_dict_item` VALUES (175, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-wheelchair', 1685467061014233120, 533, 'fa-wheelchair', 'fa fa-wheelchair', NULL);
INSERT INTO `sys_dict_item` VALUES (176, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-git-square', 1685467061014233120, 568, 'fa-git-square', 'fa fa-git-square', NULL);
INSERT INTO `sys_dict_item` VALUES (177, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-google-wallet', 1685467061014233120, 576, 'fa-google-wallet', 'fa fa-google-wallet', NULL);
INSERT INTO `sys_dict_item` VALUES (178, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-space-shuttle', 1685467061014233120, 345, 'fa-space-shuttle', 'fa fa-space-shuttle', NULL);
INSERT INTO `sys_dict_item` VALUES (179, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-superscript', 1685467061014233120, 428, 'fa-superscript', 'fa fa-superscript', NULL);
INSERT INTO `sys_dict_item` VALUES (180, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-desktop', 1685467061014233120, 87, 'fa-desktop', 'fa fa-desktop', NULL);
INSERT INTO `sys_dict_item` VALUES (181, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sliders', 1685467061014233120, 258, 'fa-sliders', 'fa fa-sliders', NULL);
INSERT INTO `sys_dict_item` VALUES (182, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-eraser', 1685467061014233120, 435, 'fa-eraser', 'fa fa-eraser', NULL);
INSERT INTO `sys_dict_item` VALUES (183, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-long-arrow-down', 1685467061014233120, 499, 'fa-long-arrow-down', 'fa fa-long-arrow-down', NULL);
INSERT INTO `sys_dict_item` VALUES (184, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-skyatlas', 1685467061014233120, 610, 'fa-skyatlas', 'fa fa-skyatlas', NULL);
INSERT INTO `sys_dict_item` VALUES (185, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-rss', 1685467061014233120, 238, 'fa-rss', 'fa fa-rss', NULL);
INSERT INTO `sys_dict_item` VALUES (186, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-pdf-o', 1685467061014233120, 371, 'fa-file-pdf-o', 'fa fa-file-pdf-o', NULL);
INSERT INTO `sys_dict_item` VALUES (187, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-facebook-square', 1685467061014233120, 563, 'fa-facebook-square', 'fa fa-facebook-square', NULL);
INSERT INTO `sys_dict_item` VALUES (188, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-circle-o', 1685467061014233120, 383, 'fa-circle-o', 'fa fa-circle-o', NULL);
INSERT INTO `sys_dict_item` VALUES (189, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-line-chart', 1685467061014233120, 176, 'fa-line-chart', 'fa fa-line-chart', NULL);
INSERT INTO `sys_dict_item` VALUES (190, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-mars-stroke', 1685467061014233120, 353, 'fa-mars-stroke', 'fa fa-mars-stroke', NULL);
INSERT INTO `sys_dict_item` VALUES (191, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-paper-plane', 1685467061014233120, 203, 'fa-paper-plane', 'fa fa-paper-plane', NULL);
INSERT INTO `sys_dict_item` VALUES (192, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-dot-circle-o', 1685467061014233120, 384, 'fa-dot-circle-o', 'fa fa-dot-circle-o', NULL);
INSERT INTO `sys_dict_item` VALUES (193, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-mars-stroke-h', 1685467061014233120, 354, 'fa-mars-stroke-h', 'fa fa-mars-stroke-h', NULL);
INSERT INTO `sys_dict_item` VALUES (194, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-caret-left', 1685467061014233120, 480, 'fa-caret-left', 'fa fa-caret-left', NULL);
INSERT INTO `sys_dict_item` VALUES (195, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-calendar', 1685467061014233120, 40, 'fa-calendar', 'fa fa-calendar', NULL);
INSERT INTO `sys_dict_item` VALUES (196, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-excel-o', 1685467061014233120, 367, 'fa-file-excel-o', 'fa fa-file-excel-o', NULL);
INSERT INTO `sys_dict_item` VALUES (197, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-yelp', 1685467061014233120, 641, 'fa-yelp', 'fa fa-yelp', NULL);
INSERT INTO `sys_dict_item` VALUES (198, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-minus-square', 1685467061014233120, 191, 'fa-minus-square', 'fa fa-minus-square', NULL);
INSERT INTO `sys_dict_item` VALUES (199, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-wheelchair', 1685467061014233120, 349, 'fa-wheelchair', 'fa fa-wheelchair', NULL);
INSERT INTO `sys_dict_item` VALUES (200, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bitbucket-square', 1685467061014233120, 541, 'fa-bitbucket-square', 'fa fa-bitbucket-square', NULL);
INSERT INTO `sys_dict_item` VALUES (201, 1593391294, 0, b'0', NULL, 1593391294, 0, '推送用', 1685467061013184544, 3, 'CronRequest', 'indi.uhyils.pojo.request.CronRequest', NULL);
INSERT INTO `sys_dict_item` VALUES (202, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cab', 1685467061014233120, 38, 'fa-cab', 'fa fa-cab', NULL);
INSERT INTO `sys_dict_item` VALUES (203, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-map-marker', 1685467061014233120, 185, 'fa-map-marker', 'fa fa-map-marker', NULL);
INSERT INTO `sys_dict_item` VALUES (204, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-maxcdn', 1685467061014233120, 589, 'fa-maxcdn', 'fa fa-maxcdn', NULL);
INSERT INTO `sys_dict_item` VALUES (205, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-try', 1685467061014233120, 414, 'fa-try', 'fa fa-try', NULL);
INSERT INTO `sys_dict_item` VALUES (206, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bug', 1685467061014233120, 32, 'fa-bug', 'fa fa-bug', NULL);
INSERT INTO `sys_dict_item` VALUES (207, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-graduation-cap', 1685467061014233120, 147, 'fa-graduation-cap', 'fa fa-graduation-cap', NULL);
INSERT INTO `sys_dict_item` VALUES (208, 1593310069, 0, b'0', NULL, 1593310069, 0, 'utf-8编码', 1685467061012135968, 1, 'utf-8', 'utf-8', NULL);
INSERT INTO `sys_dict_item` VALUES (209, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bicycle', 1685467061014233120, 23, 'fa-bicycle', 'fa fa-bicycle', NULL);
INSERT INTO `sys_dict_item` VALUES (210, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-life-bouy', 1685467061014233120, 171, 'fa-life-bouy', 'fa fa-life-bouy', NULL);
INSERT INTO `sys_dict_item` VALUES (211, 1592697342, 0, b'0', NULL, 1592697342, 0, '项目包含的功能', 1685467061004795936, 3, '包含功能', '1.用户-角色-部门-权限-菜单 模型管理 <br>2.日志,服务,服务器,JVM管理<br> 3.软件管理-redis,zookeeper<br> 4.外界API获取<br> 5.消息推送功能<br> 6.智能算法模块<br> 7.整合智能算法-> 模拟物联网', NULL);
INSERT INTO `sys_dict_item` VALUES (212, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-fast-forward', 1685467061014233120, 509, 'fa-fast-forward', 'fa fa-fast-forward', NULL);
INSERT INTO `sys_dict_item` VALUES (213, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-download', 1685467061014233120, 90, 'fa-download', 'fa fa-download', NULL);
INSERT INTO `sys_dict_item` VALUES (214, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-volume-up', 1685467061014233120, 330, 'fa-volume-up', 'fa fa-volume-up', NULL);
INSERT INTO `sys_dict_item` VALUES (215, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-hotel', 1685467061014233120, 156, 'fa-hotel', 'fa fa-hotel', NULL);
INSERT INTO `sys_dict_item` VALUES (216, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-microphone-slash', 1685467061014233120, 188, 'fa-microphone-slash', 'fa fa-microphone-slash', NULL);
INSERT INTO `sys_dict_item` VALUES (217, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-square', 1685467061014233120, 389, 'fa-square', 'fa fa-square', NULL);
INSERT INTO `sys_dict_item` VALUES (218, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bookmark', 1685467061014233120, 29, 'fa-bookmark', 'fa fa-bookmark', NULL);
INSERT INTO `sys_dict_item` VALUES (219, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-camera-retro', 1685467061014233120, 43, 'fa-camera-retro', 'fa fa-camera-retro', NULL);
INSERT INTO `sys_dict_item` VALUES (220, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-word-o', 1685467061014233120, 122, 'fa-file-word-o', 'fa fa-file-word-o', NULL);
INSERT INTO `sys_dict_item` VALUES (221, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cc-stripe', 1685467061014233120, 548, 'fa-cc-stripe', 'fa fa-cc-stripe', NULL);
INSERT INTO `sys_dict_item` VALUES (222, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-tencent-weibo', 1685467061014233120, 622, 'fa-tencent-weibo', 'fa fa-tencent-weibo', NULL);
INSERT INTO `sys_dict_item` VALUES (223, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cogs', 1685467061014233120, 72, 'fa-cogs', 'fa fa-cogs', NULL);
INSERT INTO `sys_dict_item` VALUES (224, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-mobile', 1685467061014233120, 193, 'fa-mobile', 'fa fa-mobile', NULL);
INSERT INTO `sys_dict_item` VALUES (225, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-user-plus', 1685467061014233120, 323, 'fa-user-plus', 'fa fa-user-plus', NULL);
INSERT INTO `sys_dict_item` VALUES (226, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-question-circle', 1685467061014233120, 225, 'fa-question-circle', 'fa fa-question-circle', NULL);
INSERT INTO `sys_dict_item` VALUES (227, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-hand-o-right', 1685467061014233120, 497, 'fa-hand-o-right', 'fa fa-hand-o-right', NULL);
INSERT INTO `sys_dict_item` VALUES (228, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bullhorn', 1685467061014233120, 35, 'fa-bullhorn', 'fa fa-bullhorn', NULL);
INSERT INTO `sys_dict_item` VALUES (229, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-history', 1685467061014233120, 154, 'fa-history', 'fa fa-history', NULL);
INSERT INTO `sys_dict_item` VALUES (230, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bitbucket', 1685467061014233120, 540, 'fa-bitbucket', 'fa fa-bitbucket', NULL);
INSERT INTO `sys_dict_item` VALUES (231, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-powerpoint-o', 1685467061014233120, 372, 'fa-file-powerpoint-o', 'fa fa-file-powerpoint-o', NULL);
INSERT INTO `sys_dict_item` VALUES (232, 1593216015, 0, b'0', NULL, 1593216015, 0, '包含角色借口一系列的方法', 1685467061024718880, 1, '测试-角色接口', 'RoleService', NULL);
INSERT INTO `sys_dict_item` VALUES (233, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-search-plus', 1685467061014233120, 242, 'fa-search-plus', 'fa fa-search-plus', NULL);
INSERT INTO `sys_dict_item` VALUES (234, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-laptop', 1685467061014233120, 165, 'fa-laptop', 'fa fa-laptop', NULL);
INSERT INTO `sys_dict_item` VALUES (235, 1592697466, 0, b'0', NULL, 1592697485, 0, '第四行显示', 1685467061000601632, 4, '第四项', '4.后来的计划中 我想将人工智能与物联网融合进去', NULL);
INSERT INTO `sys_dict_item` VALUES (236, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-codepen', 1685467061014233120, 550, 'fa-codepen', 'fa fa-codepen', NULL);
INSERT INTO `sys_dict_item` VALUES (237, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-street-view', 1685467061014233120, 283, 'fa-street-view', 'fa fa-street-view', NULL);
INSERT INTO `sys_dict_item` VALUES (238, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-compress', 1685467061014233120, 505, 'fa-compress', 'fa fa-compress', NULL);
INSERT INTO `sys_dict_item` VALUES (239, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file', 1685467061014233120, 363, 'fa-file', 'fa fa-file', NULL);
INSERT INTO `sys_dict_item` VALUES (240, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrow-down', 1685467061014233120, 471, 'fa-arrow-down', 'fa fa-arrow-down', NULL);
INSERT INTO `sys_dict_item` VALUES (241, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-mail-reply', 1685467061014233120, 182, 'fa-mail-reply', 'fa fa-mail-reply', NULL);
INSERT INTO `sys_dict_item` VALUES (242, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-plus-square-o', 1685467061014233120, 388, 'fa-plus-square-o', 'fa fa-plus-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES (243, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-align-justify', 1685467061014233120, 416, 'fa-align-justify', 'fa fa-align-justify', NULL);
INSERT INTO `sys_dict_item` VALUES (244, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-list-ol', 1685467061014233120, 447, 'fa-list-ol', 'fa fa-list-ol', NULL);
INSERT INTO `sys_dict_item` VALUES (245, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-angle-down', 1685467061014233120, 459, 'fa-angle-down', 'fa fa-angle-down', NULL);
INSERT INTO `sys_dict_item` VALUES (246, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-moon-o', 1685467061014233120, 196, 'fa-moon-o', 'fa fa-moon-o', NULL);
INSERT INTO `sys_dict_item` VALUES (247, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-mars', 1685467061014233120, 351, 'fa-mars', 'fa fa-mars', NULL);
INSERT INTO `sys_dict_item` VALUES (248, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-circle-o-notch', 1685467061014233120, 377, 'fa-circle-o-notch', 'fa fa-circle-o-notch', NULL);
INSERT INTO `sys_dict_item` VALUES (249, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-copyright', 1685467061014233120, 78, 'fa-copyright', 'fa fa-copyright', NULL);
INSERT INTO `sys_dict_item` VALUES (250, 1592705617, 0, b'0', NULL, 1592706447, 0, 'jvm管理快捷入口', 1685467061018427424, 1, 'JVM管理', '1685590241748975648', NULL);
INSERT INTO `sys_dict_item` VALUES (251, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-comment', 1685467061014233120, 73, 'fa-comment', 'fa fa-comment', NULL);
INSERT INTO `sys_dict_item` VALUES (252, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-pagelines', 1685467061014233120, 593, 'fa-pagelines', 'fa fa-pagelines', NULL);
INSERT INTO `sys_dict_item` VALUES (253, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-check-square', 1685467061014233120, 380, 'fa-check-square', 'fa fa-check-square', NULL);
INSERT INTO `sys_dict_item` VALUES (254, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-windows', 1685467061014233120, 636, 'fa-windows', 'fa fa-windows', NULL);
INSERT INTO `sys_dict_item` VALUES (255, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-pinterest-square', 1685467061014233120, 599, 'fa-pinterest-square', 'fa fa-pinterest-square', NULL);
INSERT INTO `sys_dict_item` VALUES (256, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-plus-square', 1685467061014233120, 218, 'fa-plus-square', 'fa fa-plus-square', NULL);
INSERT INTO `sys_dict_item` VALUES (257, 1593473585, 0, b'0', NULL, 1593473585, 0, '晚上6:00', 1685467061008990240, 6, '晚上6:00', '0 0 18 * * ?', NULL);
INSERT INTO `sys_dict_item` VALUES (258, 1592299413, 0, b'1', NULL, 1592299413, 0, '没有权限', 1685467061027864608, 3, 'NONE_AUTH_ERROR', '401', NULL);
INSERT INTO `sys_dict_item` VALUES (259, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-check', 1685467061014233120, 53, 'fa-check', 'fa fa-check', NULL);
INSERT INTO `sys_dict_item` VALUES (260, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cart-plus', 1685467061014233120, 50, 'fa-cart-plus', 'fa fa-cart-plus', NULL);
INSERT INTO `sys_dict_item` VALUES (261, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-folder-o', 1685467061014233120, 134, 'fa-folder-o', 'fa fa-folder-o', NULL);
INSERT INTO `sys_dict_item` VALUES (262, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-heart-o', 1685467061014233120, 152, 'fa-heart-o', 'fa fa-heart-o', NULL);
INSERT INTO `sys_dict_item` VALUES (263, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-circle-thin', 1685467061014233120, 350, 'fa-circle-thin', 'fa fa-circle-thin', NULL);
INSERT INTO `sys_dict_item` VALUES (264, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-circle', 1685467061014233120, 382, 'fa-circle', 'fa fa-circle', NULL);
INSERT INTO `sys_dict_item` VALUES (265, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-instagram', 1685467061014233120, 579, 'fa-instagram', 'fa fa-instagram', NULL);
INSERT INTO `sys_dict_item` VALUES (266, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-edit', 1685467061014233120, 91, 'fa-edit', 'fa fa-edit', NULL);
INSERT INTO `sys_dict_item` VALUES (267, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-paper-plane-o', 1685467061014233120, 204, 'fa-paper-plane-o', 'fa fa-paper-plane-o', NULL);
INSERT INTO `sys_dict_item` VALUES (268, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-lastfm-square', 1685467061014233120, 584, 'fa-lastfm-square', 'fa fa-lastfm-square', NULL);
INSERT INTO `sys_dict_item` VALUES (269, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-question', 1685467061014233120, 224, 'fa-question', 'fa fa-question', NULL);
INSERT INTO `sys_dict_item` VALUES (270, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-ship', 1685467061014233120, 252, 'fa-ship', 'fa fa-ship', NULL);
INSERT INTO `sys_dict_item` VALUES (271, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-send-o', 1685467061014233120, 244, 'fa-send-o', 'fa fa-send-o', NULL);
INSERT INTO `sys_dict_item` VALUES (272, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-files-o', 1685467061014233120, 432, 'fa-files-o', 'fa fa-files-o', NULL);
INSERT INTO `sys_dict_item` VALUES (273, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-steam-square', 1685467061014233120, 619, 'fa-steam-square', 'fa fa-steam-square', NULL);
INSERT INTO `sys_dict_item` VALUES (274, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-check-circle', 1685467061014233120, 54, 'fa-check-circle', 'fa fa-check-circle', NULL);
INSERT INTO `sys_dict_item` VALUES (275, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-caret-down', 1685467061014233120, 479, 'fa-caret-down', 'fa fa-caret-down', NULL);
INSERT INTO `sys_dict_item` VALUES (276, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-plus', 1685467061014233120, 216, 'fa-plus', 'fa fa-plus', NULL);
INSERT INTO `sys_dict_item` VALUES (277, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-mars-double', 1685467061014233120, 352, 'fa-mars-double', 'fa fa-mars-double', NULL);
INSERT INTO `sys_dict_item` VALUES (278, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-text', 1685467061014233120, 438, 'fa-file-text', 'fa fa-file-text', NULL);
INSERT INTO `sys_dict_item` VALUES (279, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-volume-down', 1685467061014233120, 520, 'fa-volume-down', 'fa fa-volume-down', NULL);
INSERT INTO `sys_dict_item` VALUES (280, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-envelope-square', 1685467061014233120, 96, 'fa-envelope-square', 'fa fa-envelope-square', NULL);
INSERT INTO `sys_dict_item` VALUES (281, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-gavel', 1685467061014233120, 140, 'fa-gavel', 'fa fa-gavel', NULL);
INSERT INTO `sys_dict_item` VALUES (282, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-medium', 1685467061014233120, 591, 'fa-medium', 'fa fa-medium', NULL);
INSERT INTO `sys_dict_item` VALUES (283, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-weibo', 1685467061014233120, 634, 'fa-weibo', 'fa fa-weibo', NULL);
INSERT INTO `sys_dict_item` VALUES (284, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-pied-piper-alt', 1685467061014233120, 596, 'fa-pied-piper-alt', 'fa fa-pied-piper-alt', NULL);
INSERT INTO `sys_dict_item` VALUES (285, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-reorder', 1685467061014233120, 232, 'fa-reorder', 'fa fa-reorder', NULL);
INSERT INTO `sys_dict_item` VALUES (286, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-th-large', 1685467061014233120, 453, 'fa-th-large', 'fa fa-th-large', NULL);
INSERT INTO `sys_dict_item` VALUES (287, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bank', 1685467061014233120, 12, 'fa-bank', 'fa fa-bank', NULL);
INSERT INTO `sys_dict_item` VALUES (288, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-meh-o', 1685467061014233120, 186, 'fa-meh-o', 'fa fa-meh-o', NULL);
INSERT INTO `sys_dict_item` VALUES (289, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-spotify', 1685467061014233120, 615, 'fa-spotify', 'fa fa-spotify', NULL);
INSERT INTO `sys_dict_item` VALUES (290, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-strikethrough', 1685467061014233120, 426, 'fa-strikethrough', 'fa fa-strikethrough', NULL);
INSERT INTO `sys_dict_item` VALUES (291, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-male', 1685467061014233120, 184, 'fa-male', 'fa fa-male', NULL);
INSERT INTO `sys_dict_item` VALUES (292, 1592299383, 0, b'1', NULL, 1592299383, 0, '前台传值错误', 1685467061027864608, 2, 'REQUEST_PARAM_ERROR', '400', NULL);
INSERT INTO `sys_dict_item` VALUES (293, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-warning', 1685467061014233120, 331, 'fa-warning', 'fa fa-warning', NULL);
INSERT INTO `sys_dict_item` VALUES (294, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-close', 1685467061014233120, 64, 'fa-close', 'fa fa-close', NULL);
INSERT INTO `sys_dict_item` VALUES (295, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-header', 1685467061014233120, 422, 'fa-header', 'fa fa-header', NULL);
INSERT INTO `sys_dict_item` VALUES (296, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-columns', 1685467061014233120, 434, 'fa-columns', 'fa fa-columns', NULL);
INSERT INTO `sys_dict_item` VALUES (297, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-upload', 1685467061014233120, 321, 'fa-upload', 'fa fa-upload', NULL);
INSERT INTO `sys_dict_item` VALUES (298, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-paragraph', 1685467061014233120, 450, 'fa-paragraph', 'fa fa-paragraph', NULL);
INSERT INTO `sys_dict_item` VALUES (299, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-minus-square', 1685467061014233120, 385, 'fa-minus-square', 'fa fa-minus-square', NULL);
INSERT INTO `sys_dict_item` VALUES (300, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-birthday-cake', 1685467061014233120, 25, 'fa-birthday-cake', 'fa fa-birthday-cake', NULL);
INSERT INTO `sys_dict_item` VALUES (301, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-at', 1685467061014233120, 9, 'fa-at', 'fa fa-at', NULL);
INSERT INTO `sys_dict_item` VALUES (302, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-paperclip', 1685467061014233120, 449, 'fa-paperclip', 'fa fa-paperclip', NULL);
INSERT INTO `sys_dict_item` VALUES (303, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sitemap', 1685467061014233120, 257, 'fa-sitemap', 'fa fa-sitemap', NULL);
INSERT INTO `sys_dict_item` VALUES (304, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cloud-download', 1685467061014233120, 66, 'fa-cloud-download', 'fa fa-cloud-download', NULL);
INSERT INTO `sys_dict_item` VALUES (305, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-photo-o', 1685467061014233120, 117, 'fa-file-photo-o', 'fa fa-file-photo-o', NULL);
INSERT INTO `sys_dict_item` VALUES (306, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-hand-o-up', 1685467061014233120, 498, 'fa-hand-o-up', 'fa fa-hand-o-up', NULL);
INSERT INTO `sys_dict_item` VALUES (307, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-stumbleupon-circle', 1685467061014233120, 621, 'fa-stumbleupon-circle', 'fa fa-stumbleupon-circle', NULL);
INSERT INTO `sys_dict_item` VALUES (308, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-leanpub', 1685467061014233120, 585, 'fa-leanpub', 'fa fa-leanpub', NULL);
INSERT INTO `sys_dict_item` VALUES (309, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-anchor', 1685467061014233120, 2, 'fa-anchor', 'fa fa-anchor', NULL);
INSERT INTO `sys_dict_item` VALUES (310, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrows', 1685467061014233120, 5, 'fa-arrows', 'fa fa-arrows', NULL);
INSERT INTO `sys_dict_item` VALUES (311, 1592697219, 0, b'0', NULL, 1592697219, 0, '整个项目的名称', 1685467061004795936, 1, '项目名称', 'my', NULL);
INSERT INTO `sys_dict_item` VALUES (312, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-circle-o', 1685467061014233120, 60, 'fa-circle-o', 'fa fa-circle-o', NULL);
INSERT INTO `sys_dict_item` VALUES (313, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-binoculars', 1685467061014233120, 24, 'fa-binoculars', 'fa fa-binoculars', NULL);
INSERT INTO `sys_dict_item` VALUES (314, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-word-o', 1685467061014233120, 375, 'fa-file-word-o', 'fa fa-file-word-o', NULL);
INSERT INTO `sys_dict_item` VALUES (315, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-underline', 1685467061014233120, 423, 'fa-underline', 'fa fa-underline', NULL);
INSERT INTO `sys_dict_item` VALUES (316, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cutlery', 1685467061014233120, 84, 'fa-cutlery', 'fa fa-cutlery', NULL);
INSERT INTO `sys_dict_item` VALUES (317, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-check-circle-o', 1685467061014233120, 55, 'fa-check-circle-o', 'fa fa-check-circle-o', NULL);
INSERT INTO `sys_dict_item` VALUES (318, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-exclamation-triangle', 1685467061014233120, 101, 'fa-exclamation-triangle', 'fa fa-exclamation-triangle', NULL);
INSERT INTO `sys_dict_item` VALUES (319, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-stop', 1685467061014233120, 517, 'fa-stop', 'fa fa-stop', NULL);
INSERT INTO `sys_dict_item` VALUES (320, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-external-link-square', 1685467061014233120, 103, 'fa-external-link-square', 'fa fa-external-link-square', NULL);
INSERT INTO `sys_dict_item` VALUES (321, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sellsy', 1685467061014233120, 605, 'fa-sellsy', 'fa fa-sellsy', NULL);
INSERT INTO `sys_dict_item` VALUES (322, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-undo', 1685467061014233120, 442, 'fa-undo', 'fa fa-undo', NULL);
INSERT INTO `sys_dict_item` VALUES (323, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-soundcloud', 1685467061014233120, 614, 'fa-soundcloud', 'fa fa-soundcloud', NULL);
INSERT INTO `sys_dict_item` VALUES (324, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-twitch', 1685467061014233120, 626, 'fa-twitch', 'fa fa-twitch', NULL);
INSERT INTO `sys_dict_item` VALUES (325, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-minus-square-o', 1685467061014233120, 386, 'fa-minus-square-o', 'fa fa-minus-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES (326, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-mail-forward', 1685467061014233120, 181, 'fa-mail-forward', 'fa fa-mail-forward', NULL);
INSERT INTO `sys_dict_item` VALUES (327, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-clipboard', 1685467061014233120, 431, 'fa-clipboard', 'fa fa-clipboard', NULL);
INSERT INTO `sys_dict_item` VALUES (328, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-pied-piper', 1685467061014233120, 595, 'fa-pied-piper', 'fa fa-pied-piper', NULL);
INSERT INTO `sys_dict_item` VALUES (329, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-heartbeat', 1685467061014233120, 153, 'fa-heartbeat', 'fa fa-heartbeat', NULL);
INSERT INTO `sys_dict_item` VALUES (330, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-beer', 1685467061014233120, 18, 'fa-beer', 'fa fa-beer', NULL);
INSERT INTO `sys_dict_item` VALUES (331, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-archive-o', 1685467061014233120, 364, 'fa-file-archive-o', 'fa fa-file-archive-o', NULL);
INSERT INTO `sys_dict_item` VALUES (332, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-flag-o', 1685467061014233120, 130, 'fa-flag-o', 'fa fa-flag-o', NULL);
INSERT INTO `sys_dict_item` VALUES (333, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-steam', 1685467061014233120, 618, 'fa-steam', 'fa fa-steam', NULL);
INSERT INTO `sys_dict_item` VALUES (334, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-usd', 1685467061014233120, 406, 'fa-usd', 'fa fa-usd', NULL);
INSERT INTO `sys_dict_item` VALUES (335, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-deviantart', 1685467061014233120, 555, 'fa-deviantart', 'fa fa-deviantart', NULL);
INSERT INTO `sys_dict_item` VALUES (336, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-female', 1685467061014233120, 108, 'fa-female', 'fa fa-female', NULL);
INSERT INTO `sys_dict_item` VALUES (337, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-ticket', 1685467061014233120, 299, 'fa-ticket', 'fa fa-ticket', NULL);
INSERT INTO `sys_dict_item` VALUES (338, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-heartbeat', 1685467061014233120, 527, 'fa-heartbeat', 'fa fa-heartbeat', NULL);
INSERT INTO `sys_dict_item` VALUES (339, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-mortar-board', 1685467061014233120, 197, 'fa-mortar-board', 'fa fa-mortar-board', NULL);
INSERT INTO `sys_dict_item` VALUES (340, 1592299199, 0, b'1', NULL, 1592299390, 0, '所有向后台的请求正常以及返回正常的均为200', 1685467061027864608, 1, 'SUCCESS', '200', NULL);
INSERT INTO `sys_dict_item` VALUES (341, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bus', 1685467061014233120, 338, 'fa-bus', 'fa fa-bus', NULL);
INSERT INTO `sys_dict_item` VALUES (342, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-smile-o', 1685467061014233120, 259, 'fa-smile-o', 'fa fa-smile-o', NULL);
INSERT INTO `sys_dict_item` VALUES (343, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-image-o', 1685467061014233120, 368, 'fa-file-image-o', 'fa fa-file-image-o', NULL);
INSERT INTO `sys_dict_item` VALUES (344, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-terminal', 1685467061014233120, 293, 'fa-terminal', 'fa fa-terminal', NULL);
INSERT INTO `sys_dict_item` VALUES (345, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-circle-o-notch', 1685467061014233120, 61, 'fa-circle-o-notch', 'fa fa-circle-o-notch', NULL);
INSERT INTO `sys_dict_item` VALUES (346, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-plus-circle', 1685467061014233120, 217, 'fa-plus-circle', 'fa fa-plus-circle', NULL);
INSERT INTO `sys_dict_item` VALUES (347, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-venus-mars', 1685467061014233120, 362, 'fa-venus-mars', 'fa fa-venus-mars', NULL);
INSERT INTO `sys_dict_item` VALUES (348, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-code-o', 1685467061014233120, 366, 'fa-file-code-o', 'fa fa-file-code-o', NULL);
INSERT INTO `sys_dict_item` VALUES (349, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cc-mastercard', 1685467061014233120, 546, 'fa-cc-mastercard', 'fa fa-cc-mastercard', NULL);
INSERT INTO `sys_dict_item` VALUES (350, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-gears', 1685467061014233120, 142, 'fa-gears', 'fa fa-gears', NULL);
INSERT INTO `sys_dict_item` VALUES (351, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-gear', 1685467061014233120, 141, 'fa-gear', 'fa fa-gear', NULL);
INSERT INTO `sys_dict_item` VALUES (352, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-h-square', 1685467061014233120, 524, 'fa-h-square', 'fa fa-h-square', NULL);
INSERT INTO `sys_dict_item` VALUES (353, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-folder-open', 1685467061014233120, 135, 'fa-folder-open', 'fa fa-folder-open', NULL);
INSERT INTO `sys_dict_item` VALUES (354, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-transgender-alt', 1685467061014233120, 359, 'fa-transgender-alt', 'fa fa-transgender-alt', NULL);
INSERT INTO `sys_dict_item` VALUES (355, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-space-shuttle', 1685467061014233120, 272, 'fa-space-shuttle', 'fa fa-space-shuttle', NULL);
INSERT INTO `sys_dict_item` VALUES (356, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-times', 1685467061014233120, 300, 'fa-times', 'fa fa-times', NULL);
INSERT INTO `sys_dict_item` VALUES (357, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-caret-up', 1685467061014233120, 482, 'fa-caret-up', 'fa fa-caret-up', NULL);
INSERT INTO `sys_dict_item` VALUES (358, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-plus-square', 1685467061014233120, 530, 'fa-plus-square', 'fa fa-plus-square', NULL);
INSERT INTO `sys_dict_item` VALUES (359, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-angle-double-left', 1685467061014233120, 456, 'fa-angle-double-left', 'fa fa-angle-double-left', NULL);
INSERT INTO `sys_dict_item` VALUES (360, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-heart', 1685467061014233120, 525, 'fa-heart', 'fa fa-heart', NULL);
INSERT INTO `sys_dict_item` VALUES (361, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-medkit', 1685467061014233120, 529, 'fa-medkit', 'fa fa-medkit', NULL);
INSERT INTO `sys_dict_item` VALUES (362, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-expand', 1685467061014233120, 507, 'fa-expand', 'fa fa-expand', NULL);
INSERT INTO `sys_dict_item` VALUES (363, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-scissors', 1685467061014233120, 433, 'fa-scissors', 'fa fa-scissors', NULL);
INSERT INTO `sys_dict_item` VALUES (364, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-suitcase', 1685467061014233120, 284, 'fa-suitcase', 'fa fa-suitcase', NULL);
INSERT INTO `sys_dict_item` VALUES (365, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-shield', 1685467061014233120, 251, 'fa-shield', 'fa fa-shield', NULL);
INSERT INTO `sys_dict_item` VALUES (366, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-align-left', 1685467061014233120, 417, 'fa-align-left', 'fa fa-align-left', NULL);
INSERT INTO `sys_dict_item` VALUES (367, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-trash', 1685467061014233120, 310, 'fa-trash', 'fa fa-trash', NULL);
INSERT INTO `sys_dict_item` VALUES (368, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-tag', 1685467061014233120, 289, 'fa-tag', 'fa fa-tag', NULL);
INSERT INTO `sys_dict_item` VALUES (369, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-powerpoint-o', 1685467061014233120, 119, 'fa-file-powerpoint-o', 'fa fa-file-powerpoint-o', NULL);
INSERT INTO `sys_dict_item` VALUES (370, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-life-buoy', 1685467061014233120, 172, 'fa-life-buoy', 'fa fa-life-buoy', NULL);
INSERT INTO `sys_dict_item` VALUES (371, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bicycle', 1685467061014233120, 337, 'fa-bicycle', 'fa fa-bicycle', NULL);
INSERT INTO `sys_dict_item` VALUES (372, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-code', 1685467061014233120, 68, 'fa-code', 'fa fa-code', NULL);
INSERT INTO `sys_dict_item` VALUES (373, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-align-right', 1685467061014233120, 418, 'fa-align-right', 'fa fa-align-right', NULL);
INSERT INTO `sys_dict_item` VALUES (374, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-child', 1685467061014233120, 58, 'fa-child', 'fa fa-child', NULL);
INSERT INTO `sys_dict_item` VALUES (375, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-audio-o', 1685467061014233120, 365, 'fa-file-audio-o', 'fa fa-file-audio-o', NULL);
INSERT INTO `sys_dict_item` VALUES (376, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-viacoin', 1685467061014233120, 629, 'fa-viacoin', 'fa fa-viacoin', NULL);
INSERT INTO `sys_dict_item` VALUES (377, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bookmark-o', 1685467061014233120, 30, 'fa-bookmark-o', 'fa fa-bookmark-o', NULL);
INSERT INTO `sys_dict_item` VALUES (378, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-list', 1685467061014233120, 445, 'fa-list', 'fa fa-list', NULL);
INSERT INTO `sys_dict_item` VALUES (379, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-location-arrow', 1685467061014233120, 177, 'fa-location-arrow', 'fa fa-location-arrow', NULL);
INSERT INTO `sys_dict_item` VALUES (380, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-minus-square-o', 1685467061014233120, 192, 'fa-minus-square-o', 'fa fa-minus-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES (381, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-glass', 1685467061014233120, 145, 'fa-glass', 'fa fa-glass', NULL);
INSERT INTO `sys_dict_item` VALUES (382, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-angle-double-right', 1685467061014233120, 457, 'fa-angle-double-right', 'fa fa-angle-double-right', NULL);
INSERT INTO `sys_dict_item` VALUES (383, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-flag', 1685467061014233120, 128, 'fa-flag', 'fa fa-flag', NULL);
INSERT INTO `sys_dict_item` VALUES (384, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-paypal', 1685467061014233120, 594, 'fa-paypal', 'fa fa-paypal', NULL);
INSERT INTO `sys_dict_item` VALUES (385, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-repeat', 1685467061014233120, 441, 'fa-repeat', 'fa fa-repeat', NULL);
INSERT INTO `sys_dict_item` VALUES (386, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sort-numeric-asc', 1685467061014233120, 269, 'fa-sort-numeric-asc', 'fa fa-sort-numeric-asc', NULL);
INSERT INTO `sys_dict_item` VALUES (387, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-fighter-jet', 1685467061014233120, 109, 'fa-fighter-jet', 'fa fa-fighter-jet', NULL);
INSERT INTO `sys_dict_item` VALUES (388, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-reply', 1685467061014233120, 233, 'fa-reply', 'fa fa-reply', NULL);
INSERT INTO `sys_dict_item` VALUES (389, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cog', 1685467061014233120, 71, 'fa-cog', 'fa fa-cog', NULL);
INSERT INTO `sys_dict_item` VALUES (390, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-google-plus-square', 1685467061014233120, 575, 'fa-google-plus-square', 'fa fa-google-plus-square', NULL);
INSERT INTO `sys_dict_item` VALUES (391, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-chevron-down', 1685467061014233120, 491, 'fa-chevron-down', 'fa fa-chevron-down', NULL);
INSERT INTO `sys_dict_item` VALUES (392, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-certificate', 1685467061014233120, 52, 'fa-certificate', 'fa fa-certificate', NULL);
INSERT INTO `sys_dict_item` VALUES (393, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-angle-double-down', 1685467061014233120, 455, 'fa-angle-double-down', 'fa fa-angle-double-down', NULL);
INSERT INTO `sys_dict_item` VALUES (394, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-refresh', 1685467061014233120, 230, 'fa-refresh', 'fa fa-refresh', NULL);
INSERT INTO `sys_dict_item` VALUES (395, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-ship', 1685467061014233120, 344, 'fa-ship', 'fa fa-ship', NULL);
INSERT INTO `sys_dict_item` VALUES (396, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-github-square', 1685467061014233120, 571, 'fa-github-square', 'fa fa-github-square', NULL);
INSERT INTO `sys_dict_item` VALUES (397, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bell-slash-o', 1685467061014233120, 22, 'fa-bell-slash-o', 'fa fa-bell-slash-o', NULL);
INSERT INTO `sys_dict_item` VALUES (398, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrows-v', 1685467061014233120, 478, 'fa-arrows-v', 'fa fa-arrows-v', NULL);
INSERT INTO `sys_dict_item` VALUES (399, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-caret-square-o-left', 1685467061014233120, 484, 'fa-caret-square-o-left', 'fa fa-caret-square-o-left', NULL);
INSERT INTO `sys_dict_item` VALUES (400, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-list-alt', 1685467061014233120, 446, 'fa-list-alt', 'fa fa-list-alt', NULL);
INSERT INTO `sys_dict_item` VALUES (401, 1600421475, 0, b'0', NULL, 1600421475, 0, '服务自动降级并发数', 1685467061010038816, 1, '服务自动降级并发数', '200', NULL);
INSERT INTO `sys_dict_item` VALUES (402, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sort-amount-desc', 1685467061014233120, 265, 'fa-sort-amount-desc', 'fa fa-sort-amount-desc', NULL);
INSERT INTO `sys_dict_item` VALUES (403, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-qrcode', 1685467061014233120, 223, 'fa-qrcode', 'fa fa-qrcode', NULL);
INSERT INTO `sys_dict_item` VALUES (404, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-subscript', 1685467061014233120, 427, 'fa-subscript', 'fa fa-subscript', NULL);
INSERT INTO `sys_dict_item` VALUES (405, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-hacker-news', 1685467061014233120, 577, 'fa-hacker-news', 'fa fa-hacker-news', NULL);
INSERT INTO `sys_dict_item` VALUES (406, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-inr', 1685467061014233120, 410, 'fa-inr', 'fa fa-inr', NULL);
INSERT INTO `sys_dict_item` VALUES (407, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-gift', 1685467061014233120, 144, 'fa-gift', 'fa fa-gift', NULL);
INSERT INTO `sys_dict_item` VALUES (408, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bolt', 1685467061014233120, 26, 'fa-bolt', 'fa fa-bolt', NULL);
INSERT INTO `sys_dict_item` VALUES (409, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-thumbs-o-up', 1685467061014233120, 297, 'fa-thumbs-o-up', 'fa fa-thumbs-o-up', NULL);
INSERT INTO `sys_dict_item` VALUES (410, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-line-chart', 1685467061014233120, 402, 'fa-line-chart', 'fa fa-line-chart', NULL);
INSERT INTO `sys_dict_item` VALUES (411, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-plus-square-o', 1685467061014233120, 219, 'fa-plus-square-o', 'fa fa-plus-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES (412, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-check-square-o', 1685467061014233120, 381, 'fa-check-square-o', 'fa fa-check-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES (413, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-o', 1685467061014233120, 370, 'fa-file-o', 'fa fa-file-o', NULL);
INSERT INTO `sys_dict_item` VALUES (414, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-envelope-o', 1685467061014233120, 95, 'fa-envelope-o', 'fa fa-envelope-o', NULL);
INSERT INTO `sys_dict_item` VALUES (415, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-eur', 1685467061014233120, 407, 'fa-eur', 'fa fa-eur', NULL);
INSERT INTO `sys_dict_item` VALUES (416, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-play-circle', 1685467061014233120, 513, 'fa-play-circle', 'fa fa-play-circle', NULL);
INSERT INTO `sys_dict_item` VALUES (417, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-volume-down', 1685467061014233120, 328, 'fa-volume-down', 'fa fa-volume-down', NULL);
INSERT INTO `sys_dict_item` VALUES (418, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-toggle-off', 1685467061014233120, 306, 'fa-toggle-off', 'fa fa-toggle-off', NULL);
INSERT INTO `sys_dict_item` VALUES (419, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-link', 1685467061014233120, 429, 'fa-link', 'fa fa-link', NULL);
INSERT INTO `sys_dict_item` VALUES (420, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-dashboard', 1685467061014233120, 85, 'fa-dashboard', 'fa fa-dashboard', NULL);
INSERT INTO `sys_dict_item` VALUES (421, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-subway', 1685467061014233120, 346, 'fa-subway', 'fa fa-subway', NULL);
INSERT INTO `sys_dict_item` VALUES (422, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-database', 1685467061014233120, 86, 'fa-database', 'fa fa-database', NULL);
INSERT INTO `sys_dict_item` VALUES (423, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cc-visa', 1685467061014233120, 549, 'fa-cc-visa', 'fa fa-cc-visa', NULL);
INSERT INTO `sys_dict_item` VALUES (424, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-tags', 1685467061014233120, 290, 'fa-tags', 'fa fa-tags', NULL);
INSERT INTO `sys_dict_item` VALUES (425, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-area-chart', 1685467061014233120, 4, 'fa-area-chart', 'fa fa-area-chart', NULL);
INSERT INTO `sys_dict_item` VALUES (426, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-send', 1685467061014233120, 243, 'fa-send', 'fa fa-send', NULL);
INSERT INTO `sys_dict_item` VALUES (427, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-mobile-phone', 1685467061014233120, 194, 'fa-mobile-phone', 'fa fa-mobile-phone', NULL);
INSERT INTO `sys_dict_item` VALUES (428, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-gratipay', 1685467061014233120, 572, 'fa-gratipay', 'fa fa-gratipay', NULL);
INSERT INTO `sys_dict_item` VALUES (429, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-eye-slash', 1685467061014233120, 105, 'fa-eye-slash', 'fa fa-eye-slash', NULL);
INSERT INTO `sys_dict_item` VALUES (430, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-pie-chart', 1685467061014233120, 403, 'fa-pie-chart', 'fa fa-pie-chart', NULL);
INSERT INTO `sys_dict_item` VALUES (431, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-thumbs-down', 1685467061014233120, 295, 'fa-thumbs-down', 'fa fa-thumbs-down', NULL);
INSERT INTO `sys_dict_item` VALUES (432, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrow-circle-o-up', 1685467061014233120, 470, 'fa-arrow-circle-o-up', 'fa fa-arrow-circle-o-up', NULL);
INSERT INTO `sys_dict_item` VALUES (433, 1592697449, 0, b'0', NULL, 1592697479, 0, '第三行显示', 1685467061000601632, 3, '第三项', '3.将redis zookeeper,mq,kfk,mysql等技术融合进去', NULL);
INSERT INTO `sys_dict_item` VALUES (434, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrow-circle-down', 1685467061014233120, 463, 'fa-arrow-circle-down', 'fa fa-arrow-circle-down', NULL);
INSERT INTO `sys_dict_item` VALUES (435, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-rss-square', 1685467061014233120, 239, 'fa-rss-square', 'fa fa-rss-square', NULL);
INSERT INTO `sys_dict_item` VALUES (436, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-weixin', 1685467061014233120, 633, 'fa-weixin', 'fa fa-weixin', NULL);
INSERT INTO `sys_dict_item` VALUES (437, 1592349269, 0, b'0', NULL, 1592349269, 0, 'win10系统', 1685467061020524576, 1, 'windows10', '1', NULL);
INSERT INTO `sys_dict_item` VALUES (438, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-long-arrow-up', 1685467061014233120, 502, 'fa-long-arrow-up', 'fa fa-long-arrow-up', NULL);
INSERT INTO `sys_dict_item` VALUES (439, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-unlock-alt', 1685467061014233120, 319, 'fa-unlock-alt', 'fa fa-unlock-alt', NULL);
INSERT INTO `sys_dict_item` VALUES (440, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-star-half-full', 1685467061014233120, 280, 'fa-star-half-full', 'fa fa-star-half-full', NULL);
INSERT INTO `sys_dict_item` VALUES (441, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-external-link', 1685467061014233120, 102, 'fa-external-link', 'fa fa-external-link', NULL);
INSERT INTO `sys_dict_item` VALUES (442, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-floppy-o', 1685467061014233120, 440, 'fa-floppy-o', 'fa fa-floppy-o', NULL);
INSERT INTO `sys_dict_item` VALUES (443, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-wheelchair', 1685467061014233120, 332, 'fa-wheelchair', 'fa fa-wheelchair', NULL);
INSERT INTO `sys_dict_item` VALUES (444, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-caret-square-o-right', 1685467061014233120, 47, 'fa-caret-square-o-right', 'fa fa-caret-square-o-right', NULL);
INSERT INTO `sys_dict_item` VALUES (445, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-eye', 1685467061014233120, 104, 'fa-eye', 'fa fa-eye', NULL);
INSERT INTO `sys_dict_item` VALUES (446, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrow-circle-o-left', 1685467061014233120, 468, 'fa-arrow-circle-o-left', 'fa fa-arrow-circle-o-left', NULL);
INSERT INTO `sys_dict_item` VALUES (447, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-thumbs-up', 1685467061014233120, 298, 'fa-thumbs-up', 'fa fa-thumbs-up', NULL);
INSERT INTO `sys_dict_item` VALUES (448, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-th', 1685467061014233120, 452, 'fa-th', 'fa fa-th', NULL);
INSERT INTO `sys_dict_item` VALUES (449, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrow-right', 1685467061014233120, 473, 'fa-arrow-right', 'fa fa-arrow-right', NULL);
INSERT INTO `sys_dict_item` VALUES (450, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-zip-o', 1685467061014233120, 123, 'fa-file-zip-o', 'fa fa-file-zip-o', NULL);
INSERT INTO `sys_dict_item` VALUES (451, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cc', 1685467061014233120, 51, 'fa-cc', 'fa fa-cc', NULL);
INSERT INTO `sys_dict_item` VALUES (452, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrow-circle-o-down', 1685467061014233120, 467, 'fa-arrow-circle-o-down', 'fa fa-arrow-circle-o-down', NULL);
INSERT INTO `sys_dict_item` VALUES (453, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cart-arrow-down', 1685467061014233120, 49, 'fa-cart-arrow-down', 'fa fa-cart-arrow-down', NULL);
INSERT INTO `sys_dict_item` VALUES (454, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-chevron-up', 1685467061014233120, 494, 'fa-chevron-up', 'fa fa-chevron-up', NULL);
INSERT INTO `sys_dict_item` VALUES (455, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-train', 1685467061014233120, 347, 'fa-train', 'fa fa-train', NULL);
INSERT INTO `sys_dict_item` VALUES (456, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-wifi', 1685467061014233120, 333, 'fa-wifi', 'fa fa-wifi', NULL);
INSERT INTO `sys_dict_item` VALUES (457, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-camera', 1685467061014233120, 42, 'fa-camera', 'fa fa-camera', NULL);
INSERT INTO `sys_dict_item` VALUES (458, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-pdf-o', 1685467061014233120, 116, 'fa-file-pdf-o', 'fa fa-file-pdf-o', NULL);
INSERT INTO `sys_dict_item` VALUES (459, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-delicious', 1685467061014233120, 554, 'fa-delicious', 'fa fa-delicious', NULL);
INSERT INTO `sys_dict_item` VALUES (460, 1592697425, 0, b'0', NULL, 1592697472, 0, '第二行显示', 1685467061000601632, 2, '第二项', '2.将系统对redis依赖过强的问题解决', NULL);
INSERT INTO `sys_dict_item` VALUES (461, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-times-circle-o', 1685467061014233120, 302, 'fa-times-circle-o', 'fa fa-times-circle-o', NULL);
INSERT INTO `sys_dict_item` VALUES (462, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-volume-off', 1685467061014233120, 519, 'fa-volume-off', 'fa fa-volume-off', NULL);
INSERT INTO `sys_dict_item` VALUES (463, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-star-half-empty', 1685467061014233120, 279, 'fa-star-half-empty', 'fa fa-star-half-empty', NULL);
INSERT INTO `sys_dict_item` VALUES (464, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-calendar-o', 1685467061014233120, 41, 'fa-calendar-o', 'fa fa-calendar-o', NULL);
INSERT INTO `sys_dict_item` VALUES (465, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-barcode', 1685467061014233120, 15, 'fa-barcode', 'fa fa-barcode', NULL);
INSERT INTO `sys_dict_item` VALUES (466, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-lemon-o', 1685467061014233120, 168, 'fa-lemon-o', 'fa fa-lemon-o', NULL);
INSERT INTO `sys_dict_item` VALUES (467, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-github', 1685467061014233120, 569, 'fa-github', 'fa fa-github', NULL);
INSERT INTO `sys_dict_item` VALUES (468, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-road', 1685467061014233120, 236, 'fa-road', 'fa fa-road', NULL);
INSERT INTO `sys_dict_item` VALUES (469, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sort-numeric-desc', 1685467061014233120, 270, 'fa-sort-numeric-desc', 'fa fa-sort-numeric-desc', NULL);
INSERT INTO `sys_dict_item` VALUES (470, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-indent', 1685467061014233120, 444, 'fa-indent', 'fa fa-indent', NULL);
INSERT INTO `sys_dict_item` VALUES (471, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cc-paypal', 1685467061014233120, 394, 'fa-cc-paypal', 'fa fa-cc-paypal', NULL);
INSERT INTO `sys_dict_item` VALUES (472, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-backward', 1685467061014233120, 504, 'fa-backward', 'fa fa-backward', NULL);
INSERT INTO `sys_dict_item` VALUES (473, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-print', 1685467061014233120, 221, 'fa-print', 'fa fa-print', NULL);
INSERT INTO `sys_dict_item` VALUES (474, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-circle', 1685467061014233120, 59, 'fa-circle', 'fa fa-circle', NULL);
INSERT INTO `sys_dict_item` VALUES (475, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-phone', 1685467061014233120, 209, 'fa-phone', 'fa fa-phone', NULL);
INSERT INTO `sys_dict_item` VALUES (476, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-refresh', 1685467061014233120, 378, 'fa-refresh', 'fa fa-refresh', NULL);
INSERT INTO `sys_dict_item` VALUES (477, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-dribbble', 1685467061014233120, 557, 'fa-dribbble', 'fa fa-dribbble', NULL);
INSERT INTO `sys_dict_item` VALUES (478, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-caret-square-o-up', 1685467061014233120, 486, 'fa-caret-square-o-up', 'fa fa-caret-square-o-up', NULL);
INSERT INTO `sys_dict_item` VALUES (479, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-ambulance', 1685467061014233120, 335, 'fa-ambulance', 'fa fa-ambulance', NULL);
INSERT INTO `sys_dict_item` VALUES (480, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-genderless', 1685467061014233120, 143, 'fa-genderless', 'fa fa-genderless', NULL);
INSERT INTO `sys_dict_item` VALUES (481, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-pinterest', 1685467061014233120, 597, 'fa-pinterest', 'fa fa-pinterest', NULL);
INSERT INTO `sys_dict_item` VALUES (482, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-reddit', 1685467061014233120, 602, 'fa-reddit', 'fa fa-reddit', NULL);
INSERT INTO `sys_dict_item` VALUES (483, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bed', 1685467061014233120, 17, 'fa-bed', 'fa fa-bed', NULL);
INSERT INTO `sys_dict_item` VALUES (484, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-volume-up', 1685467061014233120, 521, 'fa-volume-up', 'fa fa-volume-up', NULL);
INSERT INTO `sys_dict_item` VALUES (485, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-flickr', 1685467061014233120, 564, 'fa-flickr', 'fa fa-flickr', NULL);
INSERT INTO `sys_dict_item` VALUES (486, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-google-wallet', 1685467061014233120, 398, 'fa-google-wallet', 'fa fa-google-wallet', NULL);
INSERT INTO `sys_dict_item` VALUES (487, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-level-up', 1685467061014233120, 170, 'fa-level-up', 'fa fa-level-up', NULL);
INSERT INTO `sys_dict_item` VALUES (488, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-pencil', 1685467061014233120, 206, 'fa-pencil', 'fa fa-pencil', NULL);
INSERT INTO `sys_dict_item` VALUES (489, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sort-down', 1685467061014233120, 268, 'fa-sort-down', 'fa fa-sort-down', NULL);
INSERT INTO `sys_dict_item` VALUES (490, 1593473464, 0, b'0', NULL, 1593473464, 0, '早上5点', 1685467061008990240, 1, '早5:00', '0 0 5 * * ?', NULL);
INSERT INTO `sys_dict_item` VALUES (491, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-twitter', 1685467061014233120, 627, 'fa-twitter', 'fa fa-twitter', NULL);
INSERT INTO `sys_dict_item` VALUES (492, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-legal', 1685467061014233120, 167, 'fa-legal', 'fa fa-legal', NULL);
INSERT INTO `sys_dict_item` VALUES (493, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-pencil-square-o', 1685467061014233120, 208, 'fa-pencil-square-o', 'fa fa-pencil-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES (494, 1593216158, 0, b'0', NULL, 1593216158, 0, '包含id的请求', 1685467061013184544, 2, 'IdRequest', 'indi.uhyils.pojo.request.base.IdRequest', NULL);
INSERT INTO `sys_dict_item` VALUES (495, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-toggle-down', 1685467061014233120, 304, 'fa-toggle-down', 'fa fa-toggle-down', NULL);
INSERT INTO `sys_dict_item` VALUES (496, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-recycle', 1685467061014233120, 229, 'fa-recycle', 'fa fa-recycle', NULL);
INSERT INTO `sys_dict_item` VALUES (497, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-caret-right', 1685467061014233120, 481, 'fa-caret-right', 'fa fa-caret-right', NULL);
INSERT INTO `sys_dict_item` VALUES (498, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-krw', 1685467061014233120, 411, 'fa-krw', 'fa fa-krw', NULL);
INSERT INTO `sys_dict_item` VALUES (499, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-rocket', 1685467061014233120, 343, 'fa-rocket', 'fa fa-rocket', NULL);
INSERT INTO `sys_dict_item` VALUES (500, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-phone-square', 1685467061014233120, 210, 'fa-phone-square', 'fa fa-phone-square', NULL);
INSERT INTO `sys_dict_item` VALUES (501, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-empire', 1685467061014233120, 560, 'fa-empire', 'fa fa-empire', NULL);
INSERT INTO `sys_dict_item` VALUES (502, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-times-circle', 1685467061014233120, 301, 'fa-times-circle', 'fa fa-times-circle', NULL);
INSERT INTO `sys_dict_item` VALUES (503, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-archive', 1685467061014233120, 3, 'fa-archive', 'fa fa-archive', NULL);
INSERT INTO `sys_dict_item` VALUES (504, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-pencil-square', 1685467061014233120, 207, 'fa-pencil-square', 'fa fa-pencil-square', NULL);
INSERT INTO `sys_dict_item` VALUES (505, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-shopping-cart', 1685467061014233120, 253, 'fa-shopping-cart', 'fa fa-shopping-cart', NULL);
INSERT INTO `sys_dict_item` VALUES (506, 1593473512, 0, b'0', NULL, 1593473512, 0, '中午12点', 1685467061008990240, 3, '中午12点', '0 0 12 * * ?', NULL);
INSERT INTO `sys_dict_item` VALUES (507, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bold', 1685467061014233120, 419, 'fa-bold', 'fa fa-bold', NULL);
INSERT INTO `sys_dict_item` VALUES (508, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-ioxhost', 1685467061014233120, 580, 'fa-ioxhost', 'fa fa-ioxhost', NULL);
INSERT INTO `sys_dict_item` VALUES (509, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-youtube-play', 1685467061014233120, 518, 'fa-youtube-play', 'fa fa-youtube-play', NULL);
INSERT INTO `sys_dict_item` VALUES (510, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-minus', 1685467061014233120, 189, 'fa-minus', 'fa fa-minus', NULL);
INSERT INTO `sys_dict_item` VALUES (511, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-dropbox', 1685467061014233120, 558, 'fa-dropbox', 'fa fa-dropbox', NULL);
INSERT INTO `sys_dict_item` VALUES (512, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-caret-square-o-down', 1685467061014233120, 483, 'fa-caret-square-o-down', 'fa fa-caret-square-o-down', NULL);
INSERT INTO `sys_dict_item` VALUES (513, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-exclamation', 1685467061014233120, 99, 'fa-exclamation', 'fa fa-exclamation', NULL);
INSERT INTO `sys_dict_item` VALUES (514, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cc-visa', 1685467061014233120, 396, 'fa-cc-visa', 'fa fa-cc-visa', NULL);
INSERT INTO `sys_dict_item` VALUES (515, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bell', 1685467061014233120, 19, 'fa-bell', 'fa fa-bell', NULL);
INSERT INTO `sys_dict_item` VALUES (516, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-picture-o', 1685467061014233120, 118, 'fa-file-picture-o', 'fa fa-file-picture-o', NULL);
INSERT INTO `sys_dict_item` VALUES (517, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-eraser', 1685467061014233120, 97, 'fa-eraser', 'fa fa-eraser', NULL);
INSERT INTO `sys_dict_item` VALUES (518, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-simplybuilt', 1685467061014233120, 609, 'fa-simplybuilt', 'fa fa-simplybuilt', NULL);
INSERT INTO `sys_dict_item` VALUES (519, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bar-chart', 1685467061014233120, 401, 'fa-bar-chart', 'fa fa-bar-chart', NULL);
INSERT INTO `sys_dict_item` VALUES (520, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-tumblr', 1685467061014233120, 624, 'fa-tumblr', 'fa fa-tumblr', NULL);
INSERT INTO `sys_dict_item` VALUES (521, 1592299429, 0, b'1', NULL, 1592299429, 0, '登录已过期', 1685467061027864608, 4, 'LOGIN_TIME_OUT_ERROR', '402', NULL);
INSERT INTO `sys_dict_item` VALUES (522, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-angle-left', 1685467061014233120, 460, 'fa-angle-left', 'fa fa-angle-left', NULL);
INSERT INTO `sys_dict_item` VALUES (523, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-dot-circle-o', 1685467061014233120, 89, 'fa-dot-circle-o', 'fa fa-dot-circle-o', NULL);
INSERT INTO `sys_dict_item` VALUES (524, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cubes', 1685467061014233120, 83, 'fa-cubes', 'fa fa-cubes', NULL);
INSERT INTO `sys_dict_item` VALUES (525, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-taxi', 1685467061014233120, 339, 'fa-taxi', 'fa fa-taxi', NULL);
INSERT INTO `sys_dict_item` VALUES (526, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-video-o', 1685467061014233120, 121, 'fa-file-video-o', 'fa fa-file-video-o', NULL);
INSERT INTO `sys_dict_item` VALUES (527, 1592349302, 0, b'0', NULL, 1592349302, 0, 'centos7系统', 1685467061020524576, 2, 'linux_centos7', '2', NULL);
INSERT INTO `sys_dict_item` VALUES (528, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-newspaper-o', 1685467061014233120, 201, 'fa-newspaper-o', 'fa fa-newspaper-o', NULL);
INSERT INTO `sys_dict_item` VALUES (529, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-plane', 1685467061014233120, 342, 'fa-plane', 'fa fa-plane', NULL);
INSERT INTO `sys_dict_item` VALUES (530, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-stack-overflow', 1685467061014233120, 617, 'fa-stack-overflow', 'fa fa-stack-overflow', NULL);
INSERT INTO `sys_dict_item` VALUES (531, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-angle-double-up', 1685467061014233120, 458, 'fa-angle-double-up', 'fa fa-angle-double-up', NULL);
INSERT INTO `sys_dict_item` VALUES (532, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-automobile', 1685467061014233120, 10, 'fa-automobile', 'fa fa-automobile', NULL);
INSERT INTO `sys_dict_item` VALUES (533, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-star-half', 1685467061014233120, 278, 'fa-star-half', 'fa fa-star-half', NULL);
INSERT INTO `sys_dict_item` VALUES (534, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-quote-right', 1685467061014233120, 227, 'fa-quote-right', 'fa fa-quote-right', NULL);
INSERT INTO `sys_dict_item` VALUES (535, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-microphone', 1685467061014233120, 187, 'fa-microphone', 'fa fa-microphone', NULL);
INSERT INTO `sys_dict_item` VALUES (536, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-life-ring', 1685467061014233120, 173, 'fa-life-ring', 'fa fa-life-ring', NULL);
INSERT INTO `sys_dict_item` VALUES (537, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-btc', 1685467061014233120, 404, 'fa-btc', 'fa fa-btc', NULL);
INSERT INTO `sys_dict_item` VALUES (538, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-play-circle-o', 1685467061014233120, 514, 'fa-play-circle-o', 'fa fa-play-circle-o', NULL);
INSERT INTO `sys_dict_item` VALUES (539, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-trash-o', 1685467061014233120, 311, 'fa-trash-o', 'fa fa-trash-o', NULL);
INSERT INTO `sys_dict_item` VALUES (540, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-outdent', 1685467061014233120, 443, 'fa-outdent', 'fa fa-outdent', NULL);
INSERT INTO `sys_dict_item` VALUES (541, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-table', 1685467061014233120, 451, 'fa-table', 'fa fa-table', NULL);
INSERT INTO `sys_dict_item` VALUES (542, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-btc', 1685467061014233120, 542, 'fa-btc', 'fa fa-btc', NULL);
INSERT INTO `sys_dict_item` VALUES (543, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-chain-broken', 1685467061014233120, 430, 'fa-chain-broken', 'fa fa-chain-broken', NULL);
INSERT INTO `sys_dict_item` VALUES (544, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-thumbs-o-down', 1685467061014233120, 296, 'fa-thumbs-o-down', 'fa fa-thumbs-o-down', NULL);
INSERT INTO `sys_dict_item` VALUES (545, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-dashcube', 1685467061014233120, 553, 'fa-dashcube', 'fa fa-dashcube', NULL);
INSERT INTO `sys_dict_item` VALUES (546, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cube', 1685467061014233120, 82, 'fa-cube', 'fa fa-cube', NULL);
INSERT INTO `sys_dict_item` VALUES (547, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-foursquare', 1685467061014233120, 566, 'fa-foursquare', 'fa fa-foursquare', NULL);
INSERT INTO `sys_dict_item` VALUES (548, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file', 1685467061014233120, 436, 'fa-file', 'fa fa-file', NULL);
INSERT INTO `sys_dict_item` VALUES (549, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-angle-right', 1685467061014233120, 461, 'fa-angle-right', 'fa fa-angle-right', NULL);
INSERT INTO `sys_dict_item` VALUES (550, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-exclamation-circle', 1685467061014233120, 100, 'fa-exclamation-circle', 'fa fa-exclamation-circle', NULL);
INSERT INTO `sys_dict_item` VALUES (551, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-star-o', 1685467061014233120, 282, 'fa-star-o', 'fa fa-star-o', NULL);
INSERT INTO `sys_dict_item` VALUES (552, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-yahoo', 1685467061014233120, 640, 'fa-yahoo', 'fa fa-yahoo', NULL);
INSERT INTO `sys_dict_item` VALUES (553, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-openid', 1685467061014233120, 592, 'fa-openid', 'fa fa-openid', NULL);
INSERT INTO `sys_dict_item` VALUES (554, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-music', 1685467061014233120, 199, 'fa-music', 'fa fa-music', NULL);
INSERT INTO `sys_dict_item` VALUES (555, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-drupal', 1685467061014233120, 559, 'fa-drupal', 'fa fa-drupal', NULL);
INSERT INTO `sys_dict_item` VALUES (556, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-html5', 1685467061014233120, 578, 'fa-html5', 'fa fa-html5', NULL);
INSERT INTO `sys_dict_item` VALUES (557, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-apple', 1685467061014233120, 537, 'fa-apple', 'fa fa-apple', NULL);
INSERT INTO `sys_dict_item` VALUES (558, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-pause', 1685467061014233120, 511, 'fa-pause', 'fa fa-pause', NULL);
INSERT INTO `sys_dict_item` VALUES (559, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cc-discover', 1685467061014233120, 545, 'fa-cc-discover', 'fa fa-cc-discover', NULL);
INSERT INTO `sys_dict_item` VALUES (560, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-retweet', 1685467061014233120, 235, 'fa-retweet', 'fa fa-retweet', NULL);
INSERT INTO `sys_dict_item` VALUES (561, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cloud', 1685467061014233120, 65, 'fa-cloud', 'fa fa-cloud', NULL);
INSERT INTO `sys_dict_item` VALUES (562, 1593385841, 0, b'0', NULL, 1593385841, 0, '页面推送', 1685467061006893088, 2, '页面推送', '2', NULL);
INSERT INTO `sys_dict_item` VALUES (563, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-step-forward', 1685467061014233120, 516, 'fa-step-forward', 'fa fa-step-forward', NULL);
INSERT INTO `sys_dict_item` VALUES (564, 1592299463, 0, b'1', NULL, 1592299463, 0, '未登录', 1685467061027864608, 5, 'NO_LOGIN__ERROR', '403', NULL);
INSERT INTO `sys_dict_item` VALUES (565, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bullseye', 1685467061014233120, 36, 'fa-bullseye', 'fa fa-bullseye', NULL);
INSERT INTO `sys_dict_item` VALUES (566, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-square-o', 1685467061014233120, 276, 'fa-square-o', 'fa fa-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES (567, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-adn', 1685467061014233120, 534, 'fa-adn', 'fa fa-adn', NULL);
INSERT INTO `sys_dict_item` VALUES (568, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-share-alt-square', 1685467061014233120, 248, 'fa-share-alt-square', 'fa fa-share-alt-square', NULL);
INSERT INTO `sys_dict_item` VALUES (569, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-magic', 1685467061014233120, 179, 'fa-magic', 'fa fa-magic', NULL);
INSERT INTO `sys_dict_item` VALUES (570, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-venus', 1685467061014233120, 360, 'fa-venus', 'fa fa-venus', NULL);
INSERT INTO `sys_dict_item` VALUES (571, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-money', 1685467061014233120, 195, 'fa-money', 'fa fa-money', NULL);
INSERT INTO `sys_dict_item` VALUES (572, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cog', 1685467061014233120, 376, 'fa-cog', 'fa fa-cog', NULL);
INSERT INTO `sys_dict_item` VALUES (573, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-toggle-on', 1685467061014233120, 307, 'fa-toggle-on', 'fa fa-toggle-on', NULL);
INSERT INTO `sys_dict_item` VALUES (574, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sort-up', 1685467061014233120, 271, 'fa-sort-up', 'fa fa-sort-up', NULL);
INSERT INTO `sys_dict_item` VALUES (575, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-text-o', 1685467061014233120, 374, 'fa-file-text-o', 'fa fa-file-text-o', NULL);
INSERT INTO `sys_dict_item` VALUES (576, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-chevron-right', 1685467061014233120, 493, 'fa-chevron-right', 'fa fa-chevron-right', NULL);
INSERT INTO `sys_dict_item` VALUES (577, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-angellist', 1685467061014233120, 536, 'fa-angellist', 'fa fa-angellist', NULL);
INSERT INTO `sys_dict_item` VALUES (578, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-plug', 1685467061014233120, 215, 'fa-plug', 'fa fa-plug', NULL);
INSERT INTO `sys_dict_item` VALUES (579, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bell-o', 1685467061014233120, 20, 'fa-bell-o', 'fa fa-bell-o', NULL);
INSERT INTO `sys_dict_item` VALUES (580, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-futbol-o', 1685467061014233120, 138, 'fa-futbol-o', 'fa fa-futbol-o', NULL);
INSERT INTO `sys_dict_item` VALUES (581, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-italic', 1685467061014233120, 421, 'fa-italic', 'fa fa-italic', NULL);
INSERT INTO `sys_dict_item` VALUES (582, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-reply-all', 1685467061014233120, 234, 'fa-reply-all', 'fa fa-reply-all', NULL);
INSERT INTO `sys_dict_item` VALUES (583, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-slack', 1685467061014233120, 612, 'fa-slack', 'fa fa-slack', NULL);
INSERT INTO `sys_dict_item` VALUES (584, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-credit-card', 1685467061014233120, 397, 'fa-credit-card', 'fa fa-credit-card', NULL);
INSERT INTO `sys_dict_item` VALUES (585, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cc-mastercard', 1685467061014233120, 393, 'fa-cc-mastercard', 'fa fa-cc-mastercard', NULL);
INSERT INTO `sys_dict_item` VALUES (586, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-twitter-square', 1685467061014233120, 628, 'fa-twitter-square', 'fa fa-twitter-square', NULL);
INSERT INTO `sys_dict_item` VALUES (587, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-text-width', 1685467061014233120, 424, 'fa-text-width', 'fa fa-text-width', NULL);
INSERT INTO `sys_dict_item` VALUES (588, 1592705724, 0, b'0', NULL, 1592706476, 0, '智能操作查询快捷入口', 1685467061018427424, 5, '智能操作查询', '1685590241748975648', NULL);
INSERT INTO `sys_dict_item` VALUES (589, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-clock-o', 1685467061014233120, 63, 'fa-clock-o', 'fa fa-clock-o', NULL);
INSERT INTO `sys_dict_item` VALUES (590, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-taxi', 1685467061014233120, 292, 'fa-taxi', 'fa fa-taxi', NULL);
INSERT INTO `sys_dict_item` VALUES (591, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sort-alpha-asc', 1685467061014233120, 262, 'fa-sort-alpha-asc', 'fa fa-sort-alpha-asc', NULL);
INSERT INTO `sys_dict_item` VALUES (592, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-align-center', 1685467061014233120, 415, 'fa-align-center', 'fa fa-align-center', NULL);
INSERT INTO `sys_dict_item` VALUES (593, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrow-left', 1685467061014233120, 472, 'fa-arrow-left', 'fa fa-arrow-left', NULL);
INSERT INTO `sys_dict_item` VALUES (594, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-text', 1685467061014233120, 373, 'fa-file-text', 'fa fa-file-text', NULL);
INSERT INTO `sys_dict_item` VALUES (595, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-movie-o', 1685467061014233120, 115, 'fa-file-movie-o', 'fa fa-file-movie-o', NULL);
INSERT INTO `sys_dict_item` VALUES (596, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-forumbee', 1685467061014233120, 565, 'fa-forumbee', 'fa fa-forumbee', NULL);
INSERT INTO `sys_dict_item` VALUES (597, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-stack-exchange', 1685467061014233120, 616, 'fa-stack-exchange', 'fa fa-stack-exchange', NULL);
INSERT INTO `sys_dict_item` VALUES (598, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-quote-left', 1685467061014233120, 226, 'fa-quote-left', 'fa fa-quote-left', NULL);
INSERT INTO `sys_dict_item` VALUES (599, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-info', 1685467061014233120, 159, 'fa-info', 'fa fa-info', NULL);
INSERT INTO `sys_dict_item` VALUES (600, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-paint-brush', 1685467061014233120, 202, 'fa-paint-brush', 'fa fa-paint-brush', NULL);
INSERT INTO `sys_dict_item` VALUES (601, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-soccer-ball-o', 1685467061014233120, 260, 'fa-soccer-ball-o', 'fa fa-soccer-ball-o', NULL);
INSERT INTO `sys_dict_item` VALUES (602, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrows-v', 1685467061014233120, 7, 'fa-arrows-v', 'fa fa-arrows-v', NULL);
INSERT INTO `sys_dict_item` VALUES (603, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-globe', 1685467061014233120, 146, 'fa-globe', 'fa fa-globe', NULL);
INSERT INTO `sys_dict_item` VALUES (604, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-ban', 1685467061014233120, 11, 'fa-ban', 'fa fa-ban', NULL);
INSERT INTO `sys_dict_item` VALUES (605, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-excel-o', 1685467061014233120, 113, 'fa-file-excel-o', 'fa fa-file-excel-o', NULL);
INSERT INTO `sys_dict_item` VALUES (606, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-vimeo-square', 1685467061014233120, 630, 'fa-vimeo-square', 'fa fa-vimeo-square', NULL);
INSERT INTO `sys_dict_item` VALUES (607, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-github-alt', 1685467061014233120, 570, 'fa-github-alt', 'fa fa-github-alt', NULL);
INSERT INTO `sys_dict_item` VALUES (608, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-remove', 1685467061014233120, 231, 'fa-remove', 'fa fa-remove', NULL);
INSERT INTO `sys_dict_item` VALUES (609, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-tint', 1685467061014233120, 303, 'fa-tint', 'fa fa-tint', NULL);
INSERT INTO `sys_dict_item` VALUES (610, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-chevron-circle-right', 1685467061014233120, 489, 'fa-chevron-circle-right', 'fa fa-chevron-circle-right', NULL);
INSERT INTO `sys_dict_item` VALUES (611, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-building', 1685467061014233120, 33, 'fa-building', 'fa fa-building', NULL);
INSERT INTO `sys_dict_item` VALUES (612, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cc-amex', 1685467061014233120, 544, 'fa-cc-amex', 'fa fa-cc-amex', NULL);
INSERT INTO `sys_dict_item` VALUES (613, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-star-half-o', 1685467061014233120, 281, 'fa-star-half-o', 'fa fa-star-half-o', NULL);
INSERT INTO `sys_dict_item` VALUES (614, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-user-times', 1685467061014233120, 325, 'fa-user-times', 'fa fa-user-times', NULL);
INSERT INTO `sys_dict_item` VALUES (615, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-mercury', 1685467061014233120, 356, 'fa-mercury', 'fa fa-mercury', NULL);
INSERT INTO `sys_dict_item` VALUES (616, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-briefcase', 1685467061014233120, 31, 'fa-briefcase', 'fa fa-briefcase', NULL);
INSERT INTO `sys_dict_item` VALUES (617, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-angle-up', 1685467061014233120, 462, 'fa-angle-up', 'fa fa-angle-up', NULL);
INSERT INTO `sys_dict_item` VALUES (618, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-trophy', 1685467061014233120, 313, 'fa-trophy', 'fa fa-trophy', NULL);
INSERT INTO `sys_dict_item` VALUES (619, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrow-circle-right', 1685467061014233120, 465, 'fa-arrow-circle-right', 'fa fa-arrow-circle-right', NULL);
INSERT INTO `sys_dict_item` VALUES (620, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sun-o', 1685467061014233120, 285, 'fa-sun-o', 'fa fa-sun-o', NULL);
INSERT INTO `sys_dict_item` VALUES (621, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-user', 1685467061014233120, 322, 'fa-user', 'fa fa-user', NULL);
INSERT INTO `sys_dict_item` VALUES (622, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-heart', 1685467061014233120, 151, 'fa-heart', 'fa fa-heart', NULL);
INSERT INTO `sys_dict_item` VALUES (623, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-arrow-up', 1685467061014233120, 474, 'fa-arrow-up', 'fa fa-arrow-up', NULL);
INSERT INTO `sys_dict_item` VALUES (624, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-lightbulb-o', 1685467061014233120, 175, 'fa-lightbulb-o', 'fa fa-lightbulb-o', NULL);
INSERT INTO `sys_dict_item` VALUES (625, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-user-md', 1685467061014233120, 532, 'fa-user-md', 'fa fa-user-md', NULL);
INSERT INTO `sys_dict_item` VALUES (626, 1592705651, 0, b'0', NULL, 1592706456, 0, 'API调用快捷入口', 1685467061018427424, 2, 'API调用', '1685590241748975648', NULL);
INSERT INTO `sys_dict_item` VALUES (627, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-step-backward', 1685467061014233120, 515, 'fa-step-backward', 'fa fa-step-backward', NULL);
INSERT INTO `sys_dict_item` VALUES (628, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-stumbleupon', 1685467061014233120, 620, 'fa-stumbleupon', 'fa fa-stumbleupon', NULL);
INSERT INTO `sys_dict_item` VALUES (629, 1592697246, 0, b'0', NULL, 1592697246, 0, '项目当前版本信息', 1685467061004795936, 2, '当前版本', 'v0.0.3', NULL);
INSERT INTO `sys_dict_item` VALUES (630, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-hand-o-left', 1685467061014233120, 496, 'fa-hand-o-left', 'fa fa-hand-o-left', NULL);
INSERT INTO `sys_dict_item` VALUES (631, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-check-square', 1685467061014233120, 56, 'fa-check-square', 'fa fa-check-square', NULL);
INSERT INTO `sys_dict_item` VALUES (632, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-life-saver', 1685467061014233120, 174, 'fa-life-saver', 'fa fa-life-saver', NULL);
INSERT INTO `sys_dict_item` VALUES (633, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-filter', 1685467061014233120, 125, 'fa-filter', 'fa fa-filter', NULL);
INSERT INTO `sys_dict_item` VALUES (634, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sort-desc', 1685467061014233120, 267, 'fa-sort-desc', 'fa fa-sort-desc', NULL);
INSERT INTO `sys_dict_item` VALUES (635, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-text-o', 1685467061014233120, 439, 'fa-file-text-o', 'fa fa-file-text-o', NULL);
INSERT INTO `sys_dict_item` VALUES (636, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-square', 1685467061014233120, 275, 'fa-square', 'fa fa-square', NULL);
INSERT INTO `sys_dict_item` VALUES (637, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-money', 1685467061014233120, 412, 'fa-money', 'fa fa-money', NULL);
INSERT INTO `sys_dict_item` VALUES (638, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-connectdevelop', 1685467061014233120, 551, 'fa-connectdevelop', 'fa fa-connectdevelop', NULL);
INSERT INTO `sys_dict_item` VALUES (639, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-list-ul', 1685467061014233120, 448, 'fa-list-ul', 'fa fa-list-ul', NULL);
INSERT INTO `sys_dict_item` VALUES (640, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-signal', 1685467061014233120, 256, 'fa-signal', 'fa fa-signal', NULL);
INSERT INTO `sys_dict_item` VALUES (641, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-youtube-play', 1685467061014233120, 643, 'fa-youtube-play', 'fa fa-youtube-play', NULL);
INSERT INTO `sys_dict_item` VALUES (642, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-cloud-upload', 1685467061014233120, 67, 'fa-cloud-upload', 'fa fa-cloud-upload', NULL);
INSERT INTO `sys_dict_item` VALUES (643, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-truck', 1685467061014233120, 314, 'fa-truck', 'fa fa-truck', NULL);
INSERT INTO `sys_dict_item` VALUES (644, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-code-o', 1685467061014233120, 112, 'fa-file-code-o', 'fa fa-file-code-o', NULL);
INSERT INTO `sys_dict_item` VALUES (645, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-digg', 1685467061014233120, 556, 'fa-digg', 'fa fa-digg', NULL);
INSERT INTO `sys_dict_item` VALUES (646, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-comments', 1685467061014233120, 75, 'fa-comments', 'fa fa-comments', NULL);
INSERT INTO `sys_dict_item` VALUES (647, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-flash', 1685467061014233120, 131, 'fa-flash', 'fa fa-flash', NULL);
INSERT INTO `sys_dict_item` VALUES (648, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-comments-o', 1685467061014233120, 76, 'fa-comments-o', 'fa fa-comments-o', NULL);
INSERT INTO `sys_dict_item` VALUES (649, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-language', 1685467061014233120, 164, 'fa-language', 'fa fa-language', NULL);
INSERT INTO `sys_dict_item` VALUES (650, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-ellipsis-h', 1685467061014233120, 92, 'fa-ellipsis-h', 'fa fa-ellipsis-h', NULL);
INSERT INTO `sys_dict_item` VALUES (651, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-tasks', 1685467061014233120, 291, 'fa-tasks', 'fa fa-tasks', NULL);
INSERT INTO `sys_dict_item` VALUES (652, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bar-chart-o', 1685467061014233120, 14, 'fa-bar-chart-o', 'fa fa-bar-chart-o', NULL);
INSERT INTO `sys_dict_item` VALUES (653, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-bar-chart', 1685467061014233120, 13, 'fa-bar-chart', 'fa fa-bar-chart', NULL);
INSERT INTO `sys_dict_item` VALUES (654, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-spinner', 1685467061014233120, 273, 'fa-spinner', 'fa fa-spinner', NULL);
INSERT INTO `sys_dict_item` VALUES (655, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-caret-square-o-up', 1685467061014233120, 48, 'fa-caret-square-o-up', 'fa fa-caret-square-o-up', NULL);
INSERT INTO `sys_dict_item` VALUES (656, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-unlock', 1685467061014233120, 318, 'fa-unlock', 'fa fa-unlock', NULL);
INSERT INTO `sys_dict_item` VALUES (657, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-gbp', 1685467061014233120, 408, 'fa-gbp', 'fa fa-gbp', NULL);
INSERT INTO `sys_dict_item` VALUES (658, 1593473536, 0, b'0', NULL, 1593473536, 0, '下午1:30', 1685467061008990240, 4, '下午1:30', '0 30 13 * * ?', NULL);
INSERT INTO `sys_dict_item` VALUES (659, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-tumblr-square', 1685467061014233120, 625, 'fa-tumblr-square', 'fa fa-tumblr-square', NULL);
INSERT INTO `sys_dict_item` VALUES (660, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-level-down', 1685467061014233120, 169, 'fa-level-down', 'fa fa-level-down', NULL);
INSERT INTO `sys_dict_item` VALUES (661, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-sort', 1685467061014233120, 261, 'fa-sort', 'fa fa-sort', NULL);
INSERT INTO `sys_dict_item` VALUES (662, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-image', 1685467061014233120, 157, 'fa-image', 'fa fa-image', NULL);
INSERT INTO `sys_dict_item` VALUES (663, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-diamond', 1685467061014233120, 88, 'fa-diamond', 'fa fa-diamond', NULL);
INSERT INTO `sys_dict_item` VALUES (664, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-linkedin-square', 1685467061014233120, 587, 'fa-linkedin-square', 'fa fa-linkedin-square', NULL);
INSERT INTO `sys_dict_item` VALUES (665, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-book', 1685467061014233120, 28, 'fa-book', 'fa fa-book', NULL);
INSERT INTO `sys_dict_item` VALUES (666, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-building-o', 1685467061014233120, 34, 'fa-building-o', 'fa fa-building-o', NULL);
INSERT INTO `sys_dict_item` VALUES (667, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-picture-o', 1685467061014233120, 212, 'fa-picture-o', 'fa fa-picture-o', NULL);
INSERT INTO `sys_dict_item` VALUES (668, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-css3', 1685467061014233120, 552, 'fa-css3', 'fa fa-css3', NULL);
INSERT INTO `sys_dict_item` VALUES (669, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-th-list', 1685467061014233120, 454, 'fa-th-list', 'fa fa-th-list', NULL);
INSERT INTO `sys_dict_item` VALUES (670, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-file-video-o', 1685467061014233120, 369, 'fa-file-video-o', 'fa fa-file-video-o', NULL);
INSERT INTO `sys_dict_item` VALUES (671, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-tablet', 1685467061014233120, 287, 'fa-tablet', 'fa fa-tablet', NULL);
INSERT INTO `sys_dict_item` VALUES (672, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-git', 1685467061014233120, 567, 'fa-git', 'fa fa-git', NULL);
INSERT INTO `sys_dict_item` VALUES (673, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-toggle-up', 1685467061014233120, 309, 'fa-toggle-up', 'fa fa-toggle-up', NULL);
INSERT INTO `sys_dict_item` VALUES (674, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-power-off', 1685467061014233120, 220, 'fa-power-off', 'fa fa-power-off', NULL);
INSERT INTO `sys_dict_item` VALUES (675, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-caret-square-o-down', 1685467061014233120, 45, 'fa-caret-square-o-down', 'fa fa-caret-square-o-down', NULL);
INSERT INTO `sys_dict_item` VALUES (676, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-lastfm', 1685467061014233120, 583, 'fa-lastfm', 'fa fa-lastfm', NULL);
INSERT INTO `sys_dict_item` VALUES (677, 1593391158, 0, b'0', NULL, 1593391158, 0, '订阅推送', 1685467061024718880, 2, '定时推送接口', 'PushService', NULL);
INSERT INTO `sys_dict_item` VALUES (678, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-whatsapp', 1685467061014233120, 635, 'fa-whatsapp', 'fa fa-whatsapp', NULL);
INSERT INTO `sys_dict_item` VALUES (679, 1592348628, 0, b'0', NULL, 1592348628, 0, 'fa-umbrella', 1685467061014233120, 316, 'fa-umbrella', 'fa fa-umbrella', NULL);
INSERT INTO `sys_dict_item` VALUES (1685647718291079200, 1607558935, 0, b'0', NULL, 1607558935, 0, '开发内部工单', 1685647517794959392, 1, '开发内部工单', '1', NULL);

-- ----------------------------
-- Table structure for sys_dynamic_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_dynamic_code`;
CREATE TABLE `sys_dynamic_code`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `service_mark` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用标记,每个应用自行配置自己的标记',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类名称',
  `content` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件内容',
  `group_id` int(0) NULL DEFAULT NULL COMMENT '组名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '动态代码主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dynamic_code
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dynamic_code_history
-- ----------------------------
DROP TABLE IF EXISTS `sys_dynamic_code_history`;
CREATE TABLE `sys_dynamic_code_history`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `service_mark` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用标记,每个应用自行配置自己的标记',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类名称',
  `content` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件内容',
  `group_id` int(0) NULL DEFAULT NULL COMMENT '组名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '动态代码历史记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dynamic_code_history
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `fid` bigint(0) NULL DEFAULT NULL,
  `i_frame` int(0) NULL DEFAULT NULL,
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort` int(0) NULL DEFAULT NULL,
  `target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` bit(1) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1685590241744781344, 1591422214, 0, b'0', NULL, 1591422214, 0, 1685590241833910304, 1, 'fa fa-adjust', '下拉选择', 4, '_self', b'1', 'page/table-select.html');
INSERT INTO `sys_menu` VALUES (1685590241746878496, 1591421807, 0, b'0', NULL, 1591421807, 0, 1685590241803501600, 1, 'fa fa-adjust', '登录模板', 6, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241748975648, 1591422179, 0, b'0', NULL, 1591422179, 0, 1685590241833910304, 1, 'fa fa-adjust', '颜色选择', 3, '_self', b'1', 'page/color-select.html');
INSERT INTO `sys_menu` VALUES (1685590241750024224, 1598828741, 0, b'1', NULL, 1598828741, 0, 1685590241754218528, 1, 'fa fa-home', '智能家居', 5, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241752121376, 1591524719, 0, b'0', NULL, 1592800114, 0, 1685590241754218528, 1, 'fa fa-key', '权限管理', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241754218528, 1, 0, b'0', NULL, 1592800039, 0, 0, 1, 'fa fa-adjust', '我的页面', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241755267104, 1591941492, 0, b'0', NULL, 1593472283, 0, 1685590241754218528, 1, 'fa fa-github-alt', '软件管理', 3, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241756315680, 1, 0, b'0', NULL, 1591535726, 0, 0, 1, 'fa fa-adjust', '其他管理', 3, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241758412832, 1601205728, 0, b'1', NULL, 1601205754, 0, 1685590241750024224, 1, 'fa fa-list-ul', '设备参数管理', 2, '_self', b'1', 'page/DeviceArg.html');
INSERT INTO `sys_menu` VALUES (1685590241760509984, 1593215606, 0, b'0', NULL, 1593215606, 0, 1685590241754218528, 1, 'fa fa-bars', '系统功能', 4, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241762607136, 1604910707, 0, b'0', NULL, 1604911004, 0, 1685590241777287200, 1, 'fa fa-birthday-cake', '节点间关联路由样例表', 6, '_self', b'1', 'page/OrderBaseNodeRoute.html');
INSERT INTO `sys_menu` VALUES (1685590241764704288, 1591417727, 0, b'1', NULL, 1591421690, 0, 1685590241803501600, 1, 'fa fa-adjust', '菜单管理', 2, '_self', b'1', 'page/menu.html');
INSERT INTO `sys_menu` VALUES (1685590241766801440, 1591422020, 0, b'0', NULL, 1591422020, 0, 1685590241746878496, 1, 'fa fa-adjust', '登录-2', 2, '_blank', b'1', 'page/login-2.html');
INSERT INTO `sys_menu` VALUES (1685590241768898592, 1600480405, 0, b'0', NULL, 1604910942, 0, 1685590241752121376, 1, 'fa fa-remove', '接口禁用', 5, '_self', b'1', 'page/serviceDisable.html');
INSERT INTO `sys_menu` VALUES (1685590241770995744, 1604036089, 0, b'1', NULL, 1604036089, 0, 1685590241754218528, 1, 'fa fa-book', '文档与工单', 6, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241773092896, 1593480277, 0, b'0', NULL, 1593480277, 0, 1685590241760509984, 1, 'fa fa-cart-arrow-down', 'api群组管理', 2, '_self', b'1', 'page/ApiGroup.html');
INSERT INTO `sys_menu` VALUES (1685590241774141472, 1591422152, 0, b'0', NULL, 1591422152, 0, 1685590241833910304, 1, 'fa fa-adjust', '图标选择', 2, '_self', b'1', 'page/icon-picker.html');
INSERT INTO `sys_menu` VALUES (1685590241776238624, 1593472407, 0, b'0', NULL, 1593472407, 0, 1685590241760509984, 1, 'fa fa-taxi', '服务订阅', 4, '_self', b'1', 'page/subscribe.html');
INSERT INTO `sys_menu` VALUES (1685590241777287200, 1604910502, 0, b'0', NULL, 1604910502, 0, 1685590241754218528, 1, 'fa fa-book', '工单', 5, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241779384352, 1598828851, 0, b'1', NULL, 1598828851, 0, 1685590241750024224, 1, 'fa fa-camera', '设备管理', 1, '_self', b'1', 'page/device.html');
INSERT INTO `sys_menu` VALUES (1685590241781481504, 1593262276, 0, b'0', NULL, 1593262276, 0, 1685590241760509984, 1, 'fa fa-cart-plus', 'api订阅管理', 3, '_self', b'1', 'page/apiSubscibe.html');
INSERT INTO `sys_menu` VALUES (1685590241783578656, 1591422101, 0, b'0', NULL, 1591422101, 0, 1685590241816084512, 1, 'fa fa-adjust', '弹出层', 2, '_self', b'1', 'page/layer.html');
INSERT INTO `sys_menu` VALUES (1685590241785675808, 1604910649, 0, b'0', NULL, 1604910986, 0, 1685590241777287200, 1, 'fa fa-birthday-cake', '工单节点属性样例表', 3, '_self', b'1', 'page/OrderBaseNodeField.html');
INSERT INTO `sys_menu` VALUES (1685590241787772960, 1593215670, 0, b'0', NULL, 1593215670, 0, 1685590241760509984, 1, 'fa fa-bell-o', '定时任务管理', 1, '_self', b'1', 'page/job.html');
INSERT INTO `sys_menu` VALUES (1685590241788821536, 1591745873, 0, b'0', NULL, 1592800770, 0, 1685590241801404448, 1, 'fa fa-calendar', '日志管理', 1, '_self', b'1', 'page/log.html');
INSERT INTO `sys_menu` VALUES (1685590241790918688, 1591524740, 0, b'0', NULL, 1592801078, 0, 1685590241752121376, 1, 'fa fa-bars', '菜单管理', 5, '_self', b'1', 'page/menu.html');
INSERT INTO `sys_menu` VALUES (1685590241793015840, 1604910873, 0, b'0', NULL, 1604911045, 0, 1685590241777287200, 1, 'fa fa-birthday-cake', '工单节点处理结果表', 11, '_self', b'1', 'page/OrderNodeResultType.html');
INSERT INTO `sys_menu` VALUES (1685590241794064416, 1591951182, 0, b'0', NULL, 1592801022, 0, 1685590241755267104, 1, 'fa fa-tree', 'zookeeper管理', 2, '_self', b'1', 'page/zookeeper.html');
INSERT INTO `sys_menu` VALUES (1685590241796161568, 1591421821, 0, b'0', NULL, 1591421821, 0, 1685590241803501600, 1, 'fa fa-adjust', '异常页面', 7, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241798258720, 1591422240, 0, b'0', NULL, 1591422240, 0, 1685590241833910304, 1, 'fa fa-adjust', '文件上传', 5, '_self', b'1', 'page/upload.html');
INSERT INTO `sys_menu` VALUES (1685590241799307296, 1591422290, 0, b'0', NULL, 1591422290, 0, 1685590241833910304, 1, 'fa fa-adjust', '省市县区选择器', 7, '_self', b'1', 'page/area.html');
INSERT INTO `sys_menu` VALUES (1685590241801404448, 1591745747, 0, b'0', NULL, 1592800420, 0, 1685590241754218528, 1, 'fa fa-cogs', '系统管理', 2, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241803501600, 1, 0, b'0', NULL, 1592800048, 0, 0, 1, 'fa fa-adjust', '常规管理', 4, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241805598752, 1591421677, 0, b'0', NULL, 1591421677, 0, 1685590241803501600, 1, 'fa fa-adjust', '系统设置', 3, '_self', b'1', 'page/setting.html');
INSERT INTO `sys_menu` VALUES (1685590241807695904, 1591421995, 0, b'0', NULL, 1591421995, 0, 1685590241746878496, 1, 'fa fa-adjust', '登录-1', 1, '_blank', b'1', 'page/login-1.html');
INSERT INTO `sys_menu` VALUES (1685590241809793056, 1592367550, 0, b'1', NULL, 1592367587, 0, 1685590241754218528, 1, 'fa fa-adjust', 'dubbo控制台', 4, '_self', b'1', 'http://localhost:8080/');
INSERT INTO `sys_menu` VALUES (1685590241811890208, 1591422077, 0, b'0', NULL, 1591422077, 0, 1685590241816084512, 1, 'fa fa-adjust', '按钮示例', 1, '_self', b'1', 'page/button.html');
INSERT INTO `sys_menu` VALUES (1685590241813987360, 1591422333, 0, b'0', NULL, 1591422333, 0, 1685590241756315680, 1, 'fa fa-adjust', '失效菜单', 2, '_self', b'1', 'page/error.html');
INSERT INTO `sys_menu` VALUES (1685590241816084512, 1591421832, 0, b'0', NULL, 1591421832, 0, 1685590241803501600, 1, 'fa fa-adjust', '其他页面', 8, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241818181664, 1591422420, 0, b'0', NULL, 1591422420, 0, 1685590241865367584, 1, 'fa fa-adjust', '按钮4', 2, '_self', b'1', 'page/form.html?v=1');
INSERT INTO `sys_menu` VALUES (1685590241820278816, 1604910582, 0, b'0', NULL, 1604910964, 0, 1685590241777287200, 1, 'fa fa-birthday-cake', '工单基础信息样例表', 1, '_self', b'1', 'page/OrderBaseInfo.html');
INSERT INTO `sys_menu` VALUES (1685590241822375968, 1591422264, 0, b'0', NULL, 1591422264, 0, 1685590241833910304, 1, 'fa fa-adjust', '富文本编辑器', 6, '_self', b'1', 'page/editor.html');
INSERT INTO `sys_menu` VALUES (1685590241824473120, 1604036120, 0, b'1', NULL, 1604036120, 0, 1685590241770995744, 1, 'fa fa-book', '文档', 1, '_self', b'1', '/page/Document.html');
INSERT INTO `sys_menu` VALUES (1685590241825521696, 1591422045, 0, b'0', NULL, 1591422045, 0, 1685590241796161568, 1, 'fa fa-adjust', '404页面', 1, '_self', b'1', 'page/404.html');
INSERT INTO `sys_menu` VALUES (1685590241826570272, 1591422310, 0, b'0', NULL, 1591422310, 0, 1685590241756315680, 1, 'fa fa-adjust', '多级菜单', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241827618848, 1604910681, 0, b'0', NULL, 1604910995, 0, 1685590241777287200, 1, 'fa fa-birthday-cake', '工单节点处理结果样例表', 5, '_self', b'1', 'page/OrderBaseNodeResultType.html');
INSERT INTO `sys_menu` VALUES (1685590241829716000, 1591422393, 0, b'0', NULL, 1591422393, 0, 1685590241865367584, 1, 'fa fa-adjust', '按钮3', 1, '_self', b'1', 'page/button.html?v=3');
INSERT INTO `sys_menu` VALUES (1685590241831813152, 1604910849, 0, b'0', NULL, 1604911038, 0, 1685590241777287200, 1, 'fa fa-birthday-cake', '工单节点属性表', 10, '_self', b'1', 'page/OrderNodeField.html');
INSERT INTO `sys_menu` VALUES (1685590241833910304, 1, 0, b'0', NULL, 1591535719, 0, 0, 1, 'fa fa-adjust', '组件管理', 2, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241836007456, 1591941591, 0, b'0', NULL, 1592800726, 0, 1685590241801404448, 1, 'fa fa-server', '服务器管理', 2, '_self', b'1', 'page/server.html');
INSERT INTO `sys_menu` VALUES (1685590241838104608, 1591524814, 0, b'0', NULL, 1592801055, 0, 1685590241752121376, 1, 'fa fa-users', '角色管理', 2, '_self', b'1', 'page/role.html');
INSERT INTO `sys_menu` VALUES (1685590241839153184, 1593499822, 0, b'0', NULL, 1593499822, 0, 1685590241760509984, 1, 'fa fa-envelope-square', '消息查看', 5, '_self', b'1', 'page/msg.html');
INSERT INTO `sys_menu` VALUES (1685590241841250336, 1591421762, 0, b'0', NULL, 1591421762, 0, 1685590241803501600, 1, 'fa fa-adjust', '表单示例', 5, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241843347488, 1604910617, 0, b'0', NULL, 1604910978, 0, 1685590241777287200, 1, 'fa fa-birthday-cake', '工单节点样例表', 2, '_self', b'1', 'page/OrderBaseNode.html');
INSERT INTO `sys_menu` VALUES (1685590241845444640, 1593261800, 0, b'1', NULL, 1593261800, 0, 1685590241760509984, 1, 'fa fa-cart-arrow-down', 'api管理', 2, '_self', b'1', 'page/api.html');
INSERT INTO `sys_menu` VALUES (1685590241846493216, 1591421937, 0, b'0', NULL, 1591421937, 0, 1685590241841250336, 1, 'fa fa-adjust', '普通表单', 1, '_self', b'1', 'page/form.html');
INSERT INTO `sys_menu` VALUES (1685590241847541792, 1591421740, 0, b'0', NULL, 1591421740, 0, 1685590241803501600, 1, 'fa fa-adjust', '表格示例', 4, '_self', b'1', 'page/table.html');
INSERT INTO `sys_menu` VALUES (1685590241849638944, 1592298244, 0, b'0', NULL, 1592800750, 0, 1685590241801404448, 1, 'fa fa-book', '服务字典', 3, '_self', b'1', 'page/dictionary.html');
INSERT INTO `sys_menu` VALUES (1685590241851736096, 1596065638, 0, b'0', NULL, 1596066742, 0, 1685590241760509984, 1, 'fa fa-pagelines', 'Kpro', 6, '_self', b'1', 'page/table/kpro.html');
INSERT INTO `sys_menu` VALUES (1685590241854881824, 1591951156, 0, b'0', NULL, 1592801007, 0, 1685590241755267104, 1, 'fa fa-circle-o-notch', 'redis管理', 1, '_self', b'1', 'page/redis.html');
INSERT INTO `sys_menu` VALUES (1685590241855930400, 1604910907, 0, b'0', NULL, 1604911054, 0, 1685590241777287200, 1, 'fa fa-birthday-cake', '节点间关联路由表', 13, '_self', b'1', 'page/OrderNodeRoute.html');
INSERT INTO `sys_menu` VALUES (1685590241858027552, 1591422129, 0, b'0', NULL, 1591422129, 0, 1685590241833910304, 1, 'fa fa-adjust', '图标列表', 1, '_self', b'1', 'page/icon.html');
INSERT INTO `sys_menu` VALUES (1685590241860124704, 1604910738, 0, b'0', NULL, 1604911013, 0, 1685590241777287200, 1, 'fa fa-birthday-cake', '工单基础信息表', 7, '_self', b'1', 'page/OrderInfo.html');
INSERT INTO `sys_menu` VALUES (1685590241862221856, 1591524769, 0, b'0', NULL, 1592801070, 0, 1685590241752121376, 1, 'fa fa-key', '权限管理', 4, '_self', b'1', 'page/power.html');
INSERT INTO `sys_menu` VALUES (1685590241863270432, 1604910818, 0, b'0', NULL, 1604911029, 0, 1685590241777287200, 1, 'fa fa-birthday-cake', '工单节点表', 9, '_self', b'1', 'page/OrderNode.html');
INSERT INTO `sys_menu` VALUES (1685590241865367584, 1591422368, 0, b'0', NULL, 1591422368, 0, 1685590241873756192, 1, 'fa fa-adjust', '按钮2', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241867464736, 1604910781, 0, b'0', NULL, 1604911020, 0, 1685590241777287200, 1, 'fa fa-birthday-cake', '日志表', 8, '_self', b'1', 'page/OrderLog.html');
INSERT INTO `sys_menu` VALUES (1685590241869561888, 1591524787, 0, b'0', NULL, 1592800643, 0, 1685590241752121376, 1, 'fa fa-check-circle', '权限集管理', 3, '_self', b'1', 'page/dept.html');
INSERT INTO `sys_menu` VALUES (1685590241871659040, 1591421962, 0, b'0', NULL, 1591421962, 0, 1685590241841250336, 1, 'fa fa-adjust', '分步表单', 2, '_self', b'1', 'page/form-step.html');
INSERT INTO `sys_menu` VALUES (1685590241873756192, 1591422352, 0, b'0', NULL, 1591422352, 0, 1685590241826570272, 1, 'fa fa-adjust', '按钮1', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685590241875853344, 1591524846, 0, b'0', NULL, 1592801046, 0, 1685590241752121376, 1, 'fa fa-user', '用户管理', 1, '_self', b'1', 'page/user.html');
INSERT INTO `sys_menu` VALUES (1685838935606951968, 1607741294, 0, b'0', NULL, 1607741294, 0, 1685590241754218528, 1, 'fa fa-reorder', '工单正式节点', 6, '', b'0', '');
INSERT INTO `sys_menu` VALUES (1685839021221085216, 1607741376, 0, b'0', NULL, 1607741376, 0, 1685838935606951968, 1, 'fa fa-circle-o', '所有工单选择', 1, '_self', b'1', '/page/Order.html');

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
  `interface_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `method_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `test` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_power
-- ----------------------------
INSERT INTO `sys_power` VALUES (1685592108659900448, 1591516117, 0, b'0', NULL, 1591516117, 0, 'MenuService', 'getById', NULL);
INSERT INTO `sys_power` VALUES (1685592108671434784, 1591516117, 0, b'0', NULL, 1591516117, 0, 'MenuService', 'deleteMenu', NULL);
INSERT INTO `sys_power` VALUES (1685592108675629088, 1591516117, 0, b'0', NULL, 1591516117, 0, 'UserService', 'userLoginNoToken', NULL);
INSERT INTO `sys_power` VALUES (1685592108681920544, 1591516117, 0, b'0', NULL, 1591516117, 0, 'DeptService', 'delete', NULL);
INSERT INTO `sys_power` VALUES (1685592108687163424, 1591516117, 0, b'0', NULL, 1591516117, 0, 'DeptService', 'getDepts', NULL);
INSERT INTO `sys_power` VALUES (1685592108691357728, 1591516117, 0, b'0', NULL, 1591516117, 0, 'RoleService', 'getById', NULL);
INSERT INTO `sys_power` VALUES (1685592108696600608, 1591516117, 0, b'0', NULL, 1591516117, 0, 'DeptService', 'putPowersToDept', NULL);
INSERT INTO `sys_power` VALUES (1685592108712329248, 1591516117, 0, b'0', NULL, 1591516117, 0, 'RoleService', 'deleteRoleDept', NULL);
INSERT INTO `sys_power` VALUES (1685592108716523552, 1591516117, 0, b'0', NULL, 1591516117, 0, 'MenuService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES (1685592108720717856, 1591516117, 0, b'0', NULL, 1591516117, 0, 'RoleService', 'getAllDeptWithHaveMark', NULL);
INSERT INTO `sys_power` VALUES (1685592108725960736, 1591516117, 0, b'0', NULL, 1591516117, 0, 'RoleService', 'insert', NULL);
INSERT INTO `sys_power` VALUES (1685592108730155040, 1591928422, 0, b'0', NULL, 1591928422, 0, 'PowerService', 'deletePower', NULL);
INSERT INTO `sys_power` VALUES (1685592108736446496, 1591516117, 0, b'0', NULL, 1591516117, 0, 'RoleService', 'delete', NULL);
INSERT INTO `sys_power` VALUES (1685592108741689376, 1591516117, 0, b'0', NULL, 1591516117, 0, 'UserService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES (1685592108747980832, 1591516117, 0, b'0', NULL, 1591516117, 0, 'RoleService', 'getRoles', NULL);
INSERT INTO `sys_power` VALUES (1685592108753223712, 1591516117, 0, b'0', NULL, 1591516117, 0, 'UserService', 'getTokenInfoByTokenNoToken', NULL);
INSERT INTO `sys_power` VALUES (1685592108759515168, 1591516117, 0, b'0', NULL, 1591516117, 0, 'DeptService', 'update', NULL);
INSERT INTO `sys_power` VALUES (1685592108763709472, 1591516117, 0, b'0', NULL, 1591516117, 0, 'DeptService', 'getAllPowerWithHaveMark', NULL);
INSERT INTO `sys_power` VALUES (1685592108767903776, 1591516117, 0, b'0', NULL, 1591516117, 0, 'DeptService', 'putDeptsToMenu', NULL);
INSERT INTO `sys_power` VALUES (1685592108774195232, 1591516117, 0, b'0', NULL, 1591516117, 0, 'DeptService', 'insert', NULL);
INSERT INTO `sys_power` VALUES (1685592108778389536, 1591516117, 0, b'0', NULL, 1591516117, 0, 'MenuService', 'insert', NULL);
INSERT INTO `sys_power` VALUES (1685592108784680992, 1591516117, 0, b'0', NULL, 1591516117, 0, 'RoleService', 'update', NULL);
INSERT INTO `sys_power` VALUES (1685592108789923872, 1591516117, 0, b'0', NULL, 1591516117, 0, 'UserService', 'getUserById', NULL);
INSERT INTO `sys_power` VALUES (1685592108796215328, 1591516117, 0, b'1', NULL, 1591516117, 0, 'DeptService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES (1685592108800409632, 1591516117, 0, b'0', NULL, 1591516117, 0, 'RoleService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES (1685592108804603936, 1591516117, 0, b'0', NULL, 1591516117, 0, 'MenuService', 'getIndexMenu', NULL);
INSERT INTO `sys_power` VALUES (1685592108809846816, 1591516117, 0, b'0', NULL, 1591516117, 0, 'DeptService', 'putMenusToDept', NULL);
INSERT INTO `sys_power` VALUES (1685592108812992544, 1591516117, 0, b'0', NULL, 1591516117, 0, 'UserService', 'getById', NULL);
INSERT INTO `sys_power` VALUES (1685592108818235424, 1591516117, 0, b'0', NULL, 1591516117, 0, 'PowerService', 'insert', NULL);
INSERT INTO `sys_power` VALUES (1685592108822429728, 1591516117, 0, b'0', NULL, 1591516117, 0, 'UserService', 'insert', NULL);
INSERT INTO `sys_power` VALUES (1685592108826624032, 1591928421, 0, b'0', NULL, 1591928421, 0, 'LogService', 'pushRequestLogNoToken', NULL);
INSERT INTO `sys_power` VALUES (1685592108831866912, 1591516117, 0, b'0', NULL, 1591516117, 0, 'PowerService', 'getById', NULL);
INSERT INTO `sys_power` VALUES (1685592108836061216, 1591928422, 0, b'0', NULL, 1591928422, 0, 'PowerService', 'initPowerInProStartNoToken', NULL);
INSERT INTO `sys_power` VALUES (1685592108840255520, 1591516117, 0, b'0', NULL, 1591516117, 0, 'MenuService', 'delete', NULL);
INSERT INTO `sys_power` VALUES (1685592108844449824, 1591516117, 0, b'0', NULL, 1591516117, 0, 'PowerService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES (1685592108847595552, 1591928422, 0, b'0', NULL, 1591928422, 0, 'PowerService', 'getInterfaces', NULL);
INSERT INTO `sys_power` VALUES (1685592108850741280, 1591928421, 0, b'0', NULL, 1591928421, 0, 'LogService', 'insert', NULL);
INSERT INTO `sys_power` VALUES (1685592108854935584, 1591516117, 0, b'0', NULL, 1591516117, 0, 'DeptService', 'getAllMenuWithHaveMark', NULL);
INSERT INTO `sys_power` VALUES (1685592108860178464, 1591516117, 0, b'0', NULL, 1591516117, 0, 'MenuService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES (1685592108864372768, 1591928422, 0, b'0', NULL, 1591928422, 0, 'PowerService', 'checkUserHavePowerNoToken', NULL);
INSERT INTO `sys_power` VALUES (1685592108868567072, 1591516117, 0, b'0', NULL, 1591516117, 0, 'UserService', 'delete', NULL);
INSERT INTO `sys_power` VALUES (1685592108873809952, 1591516117, 0, b'0', NULL, 1591516117, 0, 'MenuService', 'getMenuTree', NULL);
INSERT INTO `sys_power` VALUES (1685592108878004256, 1591516117, 0, b'0', NULL, 1591516117, 0, 'RoleService', 'putDeptsToRole', NULL);
INSERT INTO `sys_power` VALUES (1685592108882198560, 1591516117, 0, b'0', NULL, 1591516117, 0, 'PowerService', 'update', NULL);
INSERT INTO `sys_power` VALUES (1685592108886392864, 1591516117, 0, b'0', NULL, 1591516117, 0, 'UserService', 'getUsers', NULL);
INSERT INTO `sys_power` VALUES (1685592108890587168, 1591516117, 0, b'0', NULL, 1591516117, 0, 'RoleService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES (1685592108896878624, 1591516117, 0, b'0', NULL, 1591516117, 0, 'UserService', 'getUserTokenNoToken', NULL);
INSERT INTO `sys_power` VALUES (1685592108904218656, 1591516117, 0, b'0', NULL, 1591516117, 0, 'RoleService', 'getUserDeptsByRoleId', NULL);
INSERT INTO `sys_power` VALUES (1685592108909461536, 1592021694, 0, b'0', NULL, 1592021694, 0, 'DeptService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES (1685592108914704416, 1591516117, 0, b'0', NULL, 1591516117, 0, 'PowerService', 'delete', NULL);
INSERT INTO `sys_power` VALUES (1685592108918898720, 1591516117, 0, b'0', NULL, 1591516117, 0, 'PowerService', 'getPowers', NULL);
INSERT INTO `sys_power` VALUES (1685592108923093024, 1591928421, 0, b'0', NULL, 1591928421, 0, 'LogService', 'update', NULL);
INSERT INTO `sys_power` VALUES (1685592108928335904, 1591928422, 0, b'0', NULL, 1591928422, 0, 'RoleService', 'deleteRole', NULL);
INSERT INTO `sys_power` VALUES (1685592108932530208, 1591928422, 0, b'0', NULL, 1591928422, 0, 'RoleService', 'getRoleByRoleIdNoToken', NULL);
INSERT INTO `sys_power` VALUES (1685592108935675936, 1591928421, 0, b'0', NULL, 1591928421, 0, 'LogService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES (1685592108938821664, 1591928421, 0, b'0', NULL, 1591928421, 0, 'LogService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES (1685592108944064544, 1591516117, 0, b'0', NULL, 1591516117, 0, 'DeptService', 'deleteDeptPower', NULL);
INSERT INTO `sys_power` VALUES (1685592108948258848, 1591516117, 0, b'0', NULL, 1591516117, 0, 'DeptService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES (1685592108951404576, 1591516117, 0, b'0', NULL, 1591516117, 0, 'UserService', 'update', NULL);
INSERT INTO `sys_power` VALUES (1685592108954550304, 1591928422, 0, b'0', NULL, 1591928422, 0, 'DeptService', 'deleteDept', NULL);
INSERT INTO `sys_power` VALUES (1685592108958744608, 1591516117, 0, b'0', NULL, 1591516117, 0, 'MenuService', 'getDeptsByMenuId', NULL);
INSERT INTO `sys_power` VALUES (1685592108963987488, 1591516117, 0, b'0', NULL, 1591516117, 0, 'DeptService', 'getById', NULL);
INSERT INTO `sys_power` VALUES (1685592108968181792, 1591516117, 0, b'0', NULL, 1591516117, 0, 'PowerService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES (1685592108972376096, 1591928421, 0, b'0', NULL, 1591928421, 0, 'LogService', 'delete', NULL);
INSERT INTO `sys_power` VALUES (1685592108977618976, 1591928421, 0, b'0', NULL, 1591928421, 0, 'LogService', 'getById', NULL);
INSERT INTO `sys_power` VALUES (1685592108982861856, 1591928422, 0, b'0', NULL, 1591928422, 0, 'PowerService', 'getMethodNameByInterfaceName', NULL);
INSERT INTO `sys_power` VALUES (1685592108987056160, 1591516117, 0, b'0', NULL, 1591516117, 0, 'MenuService', 'update', NULL);
INSERT INTO `sys_power` VALUES (1685592108993347616, 1591516117, 0, b'0', NULL, 1591516117, 0, 'UserService', 'getUserByIdNoToken', NULL);
INSERT INTO `sys_power` VALUES (1685592108997541920, 1591516117, 0, b'0', NULL, 1591516117, 0, 'UserService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES (1685592109001736224, 1592021694, 0, b'0', NULL, 1592021694, 0, 'PowerService', 'initPowerInProStart', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `level` int(0) NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0', 1590636825, '0', b'0', NULL, 1590636825, '0', 1, '超级管理员角色');
INSERT INTO `sys_role` VALUES ('1', 1591512602, '0', b'0', NULL, 1591512602, '0', 1, '测试角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `id` bigint(0) NOT NULL,
  `dept_id` bigint(0) NULL DEFAULT NULL,
  `role_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色-部门关联图' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `nick_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_id` bigint(0) NULL DEFAULT NULL,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mail` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `head_portrait` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (0, 1, 0, b'0', '这个号是超级管理员', -1453475, 0, '超级管理员', '49ba59abbe56e057', 0, 'admin', '247452312@qq.com', '17864217772', '1859777892434e39');
INSERT INTO `sys_user` VALUES (1, 1591513241, 0, b'0', NULL, 1591513241, 0, '测试用户', 'ad97df80a4e03cb5', 1, 'uhyils', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
