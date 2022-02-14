/*
 Navicat Premium Data Transfer

 Source Server         : mac
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : prod:3306
 Source Schema         : my_order

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 14/02/2022 09:30:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_order_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_api`;
CREATE TABLE `sys_order_api`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `bean_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'bean名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '节点api表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_api
-- ----------------------------

-- ----------------------------
-- Table structure for sys_order_apply
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_apply`;
CREATE TABLE `sys_order_apply`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `apply_user_id` bigint(0) NULL DEFAULT NULL COMMENT '申请人,上一个节点的处理人',
  `order_id` bigint(0) NULL DEFAULT NULL COMMENT '申请处理的工单id',
  `order_node_id` bigint(0) NULL DEFAULT NULL COMMENT '工单节点id',
  `monitor_user_id` bigint(0) NULL DEFAULT NULL COMMENT '此工单监管人id',
  `target_user_id` bigint(0) NULL DEFAULT NULL COMMENT '目标人id',
  `type` int(0) NULL DEFAULT NULL COMMENT '申请类型 0->转交申请',
  `status` int(0) NULL DEFAULT NULL COMMENT '申请状态 0->未查看 1->未受理 2->已受理 3->已同意 4->已驳回',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_apply
-- ----------------------------

-- ----------------------------
-- Table structure for sys_order_base_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_base_info`;
CREATE TABLE `sys_order_base_info`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工单名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工单描述',
  `priority` int(0) NULL DEFAULT NULL COMMENT '优先级 0->普通 1->加急',
  `son` tinyint(0) NULL DEFAULT NULL COMMENT '是否是子流程',
  `monitor_user_id` bigint(0) NULL DEFAULT NULL COMMENT '监管人id',
  `query_user_ids` bigint(0) NULL DEFAULT NULL COMMENT '可查询人id',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型 dict的OrderType',
  `limit_time` int(0) NULL DEFAULT NULL COMMENT '运行时限(分钟)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '工单基础信息样例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_base_info
-- ----------------------------
INSERT INTO `sys_order_base_info` VALUES (1685736299651137568, 1607643413, 0, b'0', NULL, 1607643413, 0, '单事务工单', '单事务工单', 0, 0, 0, 1, 1, 60);

-- ----------------------------
-- Table structure for sys_order_base_node
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_base_node`;
CREATE TABLE `sys_order_base_node`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `base_info_id` bigint(0) NULL DEFAULT NULL COMMENT '基础表id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点描述',
  `run_deal_user_id` bigint(0) NULL DEFAULT NULL COMMENT '处理人id',
  `notice_user_id` bigint(0) NULL DEFAULT NULL COMMENT '抄送人id',
  `run_type` int(0) NULL DEFAULT NULL COMMENT '处理类型 0->自动处理 1->人工处理',
  `type` int(0) NULL DEFAULT NULL COMMENT '节点类型 0->开始节点 1->中间节点 2->结束节点',
  `limit_time` int(0) NULL DEFAULT NULL COMMENT '限时(分钟)',
  `init_api_id` bigint(0) NULL DEFAULT NULL COMMENT '节点初始化事件id',
  `run_api_id` bigint(0) NULL DEFAULT NULL COMMENT '节点执行事件id',
  `save_api_id` bigint(0) NULL DEFAULT NULL COMMENT '保存执行事件id',
  `trans_api_id` bigint(0) NULL DEFAULT NULL COMMENT '流转执行事件id',
  `sync` tinyint(0) NULL DEFAULT NULL COMMENT '是否同步(所有指向此节点的前提事件结束才执行此事件)',
  `hidden` tinyint(0) NULL DEFAULT NULL COMMENT '是否隐藏,如果隐藏,则在创建项目时不自动添加此节点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '工单节点样例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_base_node
-- ----------------------------
INSERT INTO `sys_order_base_node` VALUES (1685769026380234784, 1607674623, 0, b'0', NULL, 1607674623, 0, 1685736299651137568, '起始节点', '起始节点,请输入要告知对方的信息', 0, 0, 0, 0, 60, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_order_base_node` VALUES (1685769092066181152, 1607674686, 0, b'0', NULL, 1607674686, 0, 1685736299651137568, '对方节点', '对方节点', 0, 0, 0, 2, 60, NULL, NULL, NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for sys_order_base_node_field
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_base_node_field`;
CREATE TABLE `sys_order_base_node_field`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` bigint(0) NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `base_order_id` bigint(0) NULL DEFAULT NULL COMMENT '节点id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性备注',
  `default_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认值',
  `empty` tinyint(0) NULL DEFAULT NULL COMMENT '是否可以为空',
  `edit` tinyint(0) NULL DEFAULT NULL COMMENT '是否可编辑',
  `type` int(0) NULL DEFAULT NULL COMMENT '字段类型(1->编辑框 2->单选框 3->多选框 4->下拉框 5->文本框)',
  `value_type` int(0) NULL DEFAULT NULL COMMENT '数值类型 1->字符串 2->数值 3->只允许英文 4->email 5->日期)',
  `data_sources` int(0) NULL DEFAULT NULL COMMENT '数据来源',
  `relation_id` bigint(0) NULL DEFAULT NULL COMMENT '如果是单选,多选,下拉框,那么具体的值关联id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '工单节点属性样例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_base_node_field
-- ----------------------------
INSERT INTO `sys_order_base_node_field` VALUES (1685769256076050464, 1607674842, '0', b'0', NULL, 1607674842, 0, 1685769026380234784, '标题', '标题', '无', 0, 1, 1, 1, NULL, NULL);
INSERT INTO `sys_order_base_node_field` VALUES (1685769338857979936, 1607674921, '0', b'0', NULL, 1607674921, 0, 1685769026380234784, '内容', '单事务节点内容', '无', 0, 1, 5, 1, NULL, NULL);
INSERT INTO `sys_order_base_node_field` VALUES (1685769399457284128, 1607674979, '0', b'0', NULL, 1607674979, 0, 1685769092066181152, '处理结果标题', '处理结果标题', '无', 0, 1, 0, 1, NULL, NULL);
INSERT INTO `sys_order_base_node_field` VALUES (1685769467227799584, 1607675044, '0', b'0', NULL, 1607675044, 0, 1685769092066181152, '处理结果内容', '处理结果内容', '无', 0, 1, 5, 1, NULL, NULL);

-- ----------------------------
-- Table structure for sys_order_base_node_result_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_base_node_result_type`;
CREATE TABLE `sys_order_base_node_result_type`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `base_node_id` bigint(0) NULL DEFAULT NULL COMMENT '节点id',
  `deal_result_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理结果名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '工单节点处理结果样例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_base_node_result_type
-- ----------------------------
INSERT INTO `sys_order_base_node_result_type` VALUES (1685769564134047776, 1607675136, 0, b'0', NULL, 1607675170, 0, 1685769026380234784, '单节点事务发送成功');
INSERT INTO `sys_order_base_node_result_type` VALUES (1685769572049748000, 1607675144, 0, b'0', NULL, 1607675174, 0, 1685769026380234784, '单节点事务发送失败');
INSERT INTO `sys_order_base_node_result_type` VALUES (1685769583705718816, 1607675155, 0, b'0', NULL, 1607675155, 0, 1685769092066181152, '单节点事务处理成功');
INSERT INTO `sys_order_base_node_result_type` VALUES (1685769719072686112, 1607675284, 0, b'0', NULL, 1607675284, 0, 1685769092066181152, '单节点事务处理失败');

-- ----------------------------
-- Table structure for sys_order_base_node_route
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_base_node_route`;
CREATE TABLE `sys_order_base_node_route`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `prev_node_id` bigint(0) NULL DEFAULT NULL COMMENT '上一个节点id',
  `result_id` bigint(0) NULL DEFAULT NULL COMMENT '对应结果类型',
  `next_node_id` bigint(0) NULL DEFAULT NULL COMMENT '下一个节点id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '节点间关联路由样例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_base_node_route
-- ----------------------------
INSERT INTO `sys_order_base_node_route` VALUES (1685769857262420000, 1607675416, 0, b'0', NULL, 1607675416, 0, 1685769026380234784, 1685769564134047776, 1685769092066181152);

-- ----------------------------
-- Table structure for sys_order_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_info`;
CREATE TABLE `sys_order_info`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工单名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工单描述',
  `priority` int(0) NULL DEFAULT NULL COMMENT '优先级 0->普通 1->加急',
  `son` tinyint(0) NULL DEFAULT NULL COMMENT '是否是子流程',
  `monitor_user_id` bigint(0) NULL DEFAULT NULL COMMENT '监管人id',
  `query_user_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '可查询人id',
  `status` int(0) NULL DEFAULT NULL COMMENT '运行状态\r\n0->停用\r\n1->启用\r\n2->撤回中\r\n3->已撤回\r\n4->停用中\r\n5->回退中',
  `limit_time` int(0) NULL DEFAULT NULL COMMENT '运行时限(分钟)',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型 dict的OrderType',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '工单基础信息样例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_info
-- ----------------------------
INSERT INTO `sys_order_info` VALUES (1685850790926745632, 1607752600, 0, b'0', NULL, 1607752600, 0, '单事务工单', '单事务工单', 0, 0, 0, '1', 1, 60, NULL);

-- ----------------------------
-- Table structure for sys_order_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_log`;
CREATE TABLE `sys_order_log`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `info_id` bigint(0) NULL DEFAULT NULL COMMENT '主表id',
  `node_id` bigint(0) NULL DEFAULT NULL COMMENT '节点id',
  `deal_user_id` bigint(0) NULL DEFAULT NULL COMMENT '处理人id(冗余)',
  `monitor_user_id` bigint(0) NULL DEFAULT NULL COMMENT '监管人id(冗余)',
  `result_status` int(0) NULL DEFAULT NULL COMMENT '处理结果0->处理成功 1->处理失败 2->处理终止 3->已转交',
  `fault_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '错误原因(只要不是处理成功就要填写此列)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_order_node
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_node`;
CREATE TABLE `sys_order_node`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `base_info_id` bigint(0) NULL DEFAULT NULL COMMENT '基础表id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点描述',
  `run_deal_user_id` bigint(0) NULL DEFAULT NULL COMMENT '处理人id',
  `notice_user_id` bigint(0) NULL DEFAULT NULL COMMENT '抄送人id',
  `run_type` int(0) NULL DEFAULT NULL COMMENT '处理类型 0->自动处理 1->人工处理',
  `status` int(0) NULL DEFAULT NULL COMMENT '节点状态 0->未开始 1->等待开始 2->处理中 3->结束',
  `type` int(0) NULL DEFAULT NULL COMMENT '节点类型 0->开始节点 1->中间节点 2->结束节点',
  `limit_time` int(0) NULL DEFAULT NULL COMMENT '限时(分钟)',
  `init_api_id` bigint(0) NULL DEFAULT NULL COMMENT '节点初始化事件id',
  `run_api_id` bigint(0) NULL DEFAULT NULL COMMENT '节点执行事件id(自动处理有效)',
  `save_api_id` bigint(0) NULL DEFAULT NULL COMMENT '保存执行事件id(自动处理有效)',
  `trans_api_id` bigint(0) NULL DEFAULT NULL COMMENT '流转执行事件id(自动处理有效)',
  `result_type` int(0) NULL DEFAULT NULL COMMENT '处理结果类型 0->处理成功 1->处理失败 2->转交别人处理',
  `result_id` bigint(0) NULL DEFAULT NULL COMMENT '处理结果id',
  `sync` tinyint(0) NULL DEFAULT NULL COMMENT '是否同步(所有指向此节点的前提事件结束才执行此事件)',
  `suggest` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理人建议',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '工单节点样例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_node
-- ----------------------------
INSERT INTO `sys_order_node` VALUES (1685850790934085664, 1607752600, 0, b'0', NULL, 1607752600, 0, 1685850790926745632, '起始节点', '起始节点,请输入要告知对方的信息', 0, 0, 0, NULL, 0, 60, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_order_node` VALUES (1685850790959251488, 1607752600, 0, b'0', NULL, 1607752600, 0, 1685850790926745632, '对方节点', '对方节点', 0, 0, 0, NULL, 2, 60, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);

-- ----------------------------
-- Table structure for sys_order_node_field
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_node_field`;
CREATE TABLE `sys_order_node_field`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `base_order_node_id` bigint(0) NULL DEFAULT NULL COMMENT '节点id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性备注',
  `default_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认值',
  `empty` tinyint(0) NULL DEFAULT NULL COMMENT '是否可以为空',
  `edit` tinyint(0) NULL DEFAULT NULL COMMENT '是否可编辑',
  `type` int(0) NULL DEFAULT NULL COMMENT '字段类型(1->编辑框 2->单选框 3->多选框 4->下拉框 5->文本框)',
  `value_type` int(0) NULL DEFAULT NULL COMMENT '数值类型 1->字符串 2->数值 3->只允许英文 4->email 5->日期)',
  `data_sources` int(0) NULL DEFAULT NULL COMMENT '数据来源',
  `relation_id` bigint(0) NULL DEFAULT NULL COMMENT '如果是单选,多选,下拉框,那么具体的值关联id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '工单节点属性样例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_node_field
-- ----------------------------
INSERT INTO `sys_order_node_field` VALUES (1685850790948765728, 1607752600, 0, b'0', NULL, 1607752600, 0, 1685850790934085664, '标题', '标题', '无', 0, 1, 1, 1, NULL, NULL);
INSERT INTO `sys_order_node_field` VALUES (1685850790951911456, 1607752600, 0, b'0', NULL, 1607752600, 0, 1685850790934085664, '内容', '单事务节点内容', '无', 0, 1, 5, 1, NULL, NULL);
INSERT INTO `sys_order_node_field` VALUES (1685850790968688672, 1607752600, 0, b'0', NULL, 1607752600, 0, 1685850790959251488, '处理结果标题', '处理结果标题', '无', 0, 1, 0, 1, NULL, NULL);
INSERT INTO `sys_order_node_field` VALUES (1685850790972882976, 1607752600, 0, b'0', NULL, 1607752600, 0, 1685850790959251488, '处理结果内容', '处理结果内容', '无', 0, 1, 5, 1, NULL, NULL);

-- ----------------------------
-- Table structure for sys_order_node_field_value
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_node_field_value`;
CREATE TABLE `sys_order_node_field_value`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `node_field_id` bigint(0) NULL DEFAULT NULL COMMENT '对应节点属性的id',
  `real_value` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单节点属性真实值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_node_field_value
-- ----------------------------
INSERT INTO `sys_order_node_field_value` VALUES (1685850803421577248, 1607752612, 0, b'0', NULL, 1607752612, 0, 1685850790948765728, 'test 标题');
INSERT INTO `sys_order_node_field_value` VALUES (1685850803424722976, 1607752612, 0, b'0', NULL, 1607752612, 0, 1685850790951911456, 'test 内容');

-- ----------------------------
-- Table structure for sys_order_node_result_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_node_result_type`;
CREATE TABLE `sys_order_node_result_type`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `base_node_id` bigint(0) NULL DEFAULT NULL COMMENT '节点id',
  `deal_result_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理结果名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '工单节点处理结果样例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_node_result_type
-- ----------------------------
INSERT INTO `sys_order_node_result_type` VALUES (1685850790954008608, 1607752600, 0, b'0', NULL, 1607752600, 0, 1685850790934085664, '单节点事务发送成功');
INSERT INTO `sys_order_node_result_type` VALUES (1685850790957154336, 1607752600, 0, b'0', NULL, 1607752600, 0, 1685850790934085664, '单节点事务发送失败');
INSERT INTO `sys_order_node_result_type` VALUES (1685850790974980128, 1607752600, 0, b'0', NULL, 1607752600, 0, 1685850790959251488, '单节点事务处理成功');
INSERT INTO `sys_order_node_result_type` VALUES (1685850790976028704, 1607752600, 0, b'0', NULL, 1607752600, 0, 1685850790959251488, '单节点事务处理失败');

-- ----------------------------
-- Table structure for sys_order_node_route
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_node_route`;
CREATE TABLE `sys_order_node_route`  (
  `id` bigint(0) NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` bigint(0) NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` bigint(0) NULL DEFAULT NULL,
  `prev_node_id` bigint(0) NULL DEFAULT NULL COMMENT '上一个节点id',
  `result_id` bigint(0) NULL DEFAULT NULL COMMENT '对应结果类型',
  `next_node_id` bigint(0) NULL DEFAULT NULL COMMENT '下一个节点id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '节点间关联路由样例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_node_route
-- ----------------------------
INSERT INTO `sys_order_node_route` VALUES (1685850790978125856, 1607752600, 0, b'0', NULL, 1607752600, 0, NULL, NULL, 1685850790959251488);

SET FOREIGN_KEY_CHECKS = 1;
