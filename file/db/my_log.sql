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

 Date: 09/12/2020 18:56:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_black_list
-- ----------------------------
DROP TABLE IF EXISTS `sys_black_list`;
CREATE TABLE `sys_black_list`
(
    `id`          bigint(0)                                                     NOT NULL,
    `create_date` bigint(0)                                                     NULL DEFAULT NULL,
    `create_user` bigint(0)                                                     NULL DEFAULT NULL,
    `delete_flag` bit(1)                                                        NULL DEFAULT NULL,
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `update_date` bigint(0)                                                     NULL DEFAULT NULL,
    `update_user` bigint(0)                                                     NULL DEFAULT NULL,
    `ip`          varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '用户ip',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '黑名单'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_black_list
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
    `id`               bigint(0)                                                      NOT NULL,
    `create_date`      bigint(0)                                                      NULL DEFAULT NULL,
    `create_user`      bigint(0)                                                      NULL DEFAULT NULL,
    `delete_flag`      bit(1)                                                         NULL DEFAULT NULL,
    `remark`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `update_date`      bigint(0)                                                      NULL DEFAULT NULL,
    `update_user`      bigint(0)                                                      NULL DEFAULT NULL,
    `exception_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          NULL COMMENT '错误信息',
    `interface_name`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL DEFAULT NULL COMMENT '接口名称',
    `method_name`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL DEFAULT NULL COMMENT '方法名称',
    `ip`               varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL DEFAULT NULL COMMENT '请求ip',
    `log_type`         int(0)                                                         NULL DEFAULT NULL COMMENT '日志类型',
    `params`           text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          NULL COMMENT '请求参数',
    `time`             bigint(0)                                                      NULL DEFAULT NULL COMMENT '发生时间',
    `user_id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL DEFAULT NULL COMMENT '操作用户id',
    `link`             varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '日志'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log`
VALUES (1685399458974531616, 1607322176, 0, b'0', NULL, 1607322176, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322176879, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685399459181101088, 1607322177, 0, b'0', NULL, 1607322177, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322177099, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685399460569415712, 1607322178, 0, b'0', NULL, 1607322178, 0, NULL, 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 200,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607322178427, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MongoServiceImpl : getByFileName-->');
INSERT INTO `sys_log`
VALUES (1685399460888182816, 1607322178, 0, b'0', NULL, 1607322178, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322178731, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685399461980799008, 1607322179, 0, b'0', NULL, 1607322179, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322179771, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685399775989465120, 1607322479, 0, b'0', NULL, 1607322479, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322479235, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685399818930749472, 1607322520, 0, b'0', NULL, 1607322520, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322520184, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685399818974789664, 1607322520, 0, b'0', NULL, 1607322520, 0, NULL, 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 200,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607322520227, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MongoServiceImpl : getByFileName-->');
INSERT INTO `sys_log`
VALUES (1685399819184504864, 1607322520, 0, b'0', NULL, 1607322520, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322520427, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685399819751784480, 1607322520, 0, b'0', NULL, 1607322520, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322520969, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685400012011339808, 1607322704, 0, b'0', NULL, 1607322704, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322704320, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685400012061671456, 1607322704, 0, b'0', NULL, 1607322704, 0, NULL, 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 200,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607322704371, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MongoServiceImpl : getByFileName-->');
INSERT INTO `sys_log`
VALUES (1685400012259852320, 1607322704, 0, b'0', NULL, 1607322704, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322704560, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685400012841812000, 1607322705, 0, b'0', NULL, 1607322705, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322705112, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685400020222738464, 1607322712, 0, b'0', NULL, 1607322712, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322712153, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685400020261535776, 1607322712, 0, b'0', NULL, 1607322712, 0, NULL, 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 200,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607322712188, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MongoServiceImpl : getByFileName-->');
INSERT INTO `sys_log`
VALUES (1685400020579254304, 1607322712, 0, b'0', NULL, 1607322712, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322712493, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685400021110882336, 1607322713, 0, b'0', NULL, 1607322713, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322713000, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685400137793273888, 1607322824, 0, b'0', NULL, 1607322824, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322824270, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685400137834168352, 1607322824, 0, b'0', NULL, 1607322824, 0, NULL, 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 200,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607322824317, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MongoServiceImpl : getByFileName-->');
INSERT INTO `sys_log`
VALUES (1685400247733321760, 1607322929, 0, b'0', NULL, 1607322929, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322929125, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685400248258658336, 1607322929, 0, b'0', NULL, 1607322929, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322929626, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685400294131761184, 1607322973, 0, b'0', NULL, 1607322973, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322973373, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685400294192578592, 1607322973, 0, b'0', NULL, 1607322973, 0, NULL, 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 200,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607322973431, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MongoServiceImpl : getByFileName-->');
INSERT INTO `sys_log`
VALUES (1685400320457310240, 1607322998, 0, b'0', NULL, 1607322998, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322998480, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685400320964821024, 1607322998, 0, b'0', NULL, 1607322998, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607322998963, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685400324979818528, 1607323002, 0, b'0', NULL, 1607323002, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323002791, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685400325023858720, 1607323002, 0, b'0', NULL, 1607323002, 0, NULL, 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 200,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607323002835, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MongoServiceImpl : getByFileName-->');
INSERT INTO `sys_log`
VALUES (1685400454274482208, 1607323126, 0, b'0', NULL, 1607323126, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323126096, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685400454789333024, 1607323126, 0, b'0', NULL, 1607323126, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323126588, 'h/ltFySrcZVrRfawbpCm4cFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685400769872789536, 1607323427, 0, b'0', NULL, 1607323427, 0, '未登录', 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607323427055,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685400816240820256, 1607323471, 0, b'0', NULL, 1607323471, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323471294, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685400816435855392, 1607323471, 0, b'0', NULL, 1607323471, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323471480, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685400816509255712, 1607323471, 0, b'0', NULL, 1607323471, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607323471548, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685400823861870624, 1607323478, 0, b'0', NULL, 1607323478, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323478562, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685400824264523808, 1607323478, 0, b'0', NULL, 1607323478, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323478944, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685400829189685280, 1607323483, 0, b'0', NULL, 1607323483, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323483640, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685400829222191136, 1607323483, 0, b'0', NULL, 1607323483, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607323483673, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685400904538259488, 1607323555, 0, b'0', NULL, 1607323555, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323555502, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685400904987050016, 1607323555, 0, b'0', NULL, 1607323555, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323555929, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685400961649999904, 1607323609, 0, b'0', NULL, 1607323609, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323609967, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685400961682505760, 1607323610, 0, b'0', NULL, 1607323610, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607323609999, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685400961960378400, 1607323610, 0, b'0', NULL, 1607323610, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323610264, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685400962394488864, 1607323610, 0, b'0', NULL, 1607323610, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323610673, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685400965736300576, 1607323613, 0, b'0', NULL, 1607323613, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323613866, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685401022719066144, 1607323668, 0, b'0', NULL, 1607323668, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323668198, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685401022738989088, 1607323668, 0, b'0', NULL, 1607323668, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607323668223, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685401023082922016, 1607323668, 0, b'0', NULL, 1607323668, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323668556, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685401023393300512, 1607323668, 0, b'0', NULL, 1607323668, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323668852, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685401027515252768, 1607323672, 0, b'0', NULL, 1607323672, 0, '查询成功,此账号没有角色,请添加', 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 400,
        '{\"iFrame\":1,\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323672782, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685401042770984992, 1607323687, 0, b'0', NULL, 1607323687, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323687331, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685401042789859360, 1607323687, 0, b'0', NULL, 1607323687, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607323687351, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685401042963922976, 1607323687, 0, b'0', NULL, 1607323687, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323687514, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685401043217678368, 1607323687, 0, b'0', NULL, 1607323687, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323687758, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685401045386133536, 1607323689, 0, b'0', NULL, 1607323689, 0, '查询成功,此账号没有角色,请添加', 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 400,
        '{\"iFrame\":1,\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323689827, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685401056091045920, 1607323700, 0, b'0', NULL, 1607323700, 0, '查询成功,此账号没有角色,请添加', 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 400,
        '{\"iFrame\":1,\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323700034, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685401067997626400, 1607323711, 0, b'0', NULL, 1607323711, 0, '查询成功,此账号没有角色,请添加', 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 400,
        '{\"iFrame\":1,\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323711389, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685401078767550496, 1607323721, 0, b'0', NULL, 1607323721, 0, '查询成功,此账号没有角色,请添加', 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 400,
        '{\"iFrame\":1,\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323721659, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685401150491197472, 1607323790, 0, b'0', NULL, 1607323790, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323790062, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685401150521606176, 1607323790, 0, b'0', NULL, 1607323790, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607323790088, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685401150848761888, 1607323790, 0, b'0', NULL, 1607323790, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323790402, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685401151533482016, 1607323791, 0, b'0', NULL, 1607323791, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323791052, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685401151702302752, 1607323791, 0, b'0', NULL, 1607323791, 0, '查询成功,此账号没有角色,请添加', 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 400,
        '{\"iFrame\":1,\"token\":\"LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323791216, 'LXcfSn0fIlLrH2IhRwD6IcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685401364397555744, 1607323994, 0, b'0', NULL, 1607323994, 0, '未登录', 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607323994031,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685401364642922528, 1607323994, 0, b'0', NULL, 1607323994, 0, '未登录', 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607323994290,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685401367118610464, 1607323996, 0, b'0', NULL, 1607323996, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323996654, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685401367283236896, 1607323996, 0, b'0', NULL, 1607323996, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"QdJBPQhUnYIeCmc72bVsBMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323996808, 'QdJBPQhUnYIeCmc72bVsBMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685401367356637216, 1607323996, 0, b'0', NULL, 1607323996, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"QdJBPQhUnYIeCmc72bVsBMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607323996880, 'QdJBPQhUnYIeCmc72bVsBMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685401367615635488, 1607323997, 0, b'0', NULL, 1607323997, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"QdJBPQhUnYIeCmc72bVsBMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323997128, 'QdJBPQhUnYIeCmc72bVsBMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685401367964811296, 1607323997, 0, b'0', NULL, 1607323997, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"QdJBPQhUnYIeCmc72bVsBMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607323997458, 'QdJBPQhUnYIeCmc72bVsBMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685401409553432608, 1607324037, 0, b'0', NULL, 1607324037, 0, '查询成功,此账号没有角色,请添加', 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 400,
        '{\"iFrame\":1,\"token\":\"QdJBPQhUnYIeCmc72bVsBMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607324037106, 'QdJBPQhUnYIeCmc72bVsBMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685401682948653088, 1607324297, 0, b'0', NULL, 1607324297, 0, '未登录', 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607324297830,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685401684003520544, 1607324298, 0, b'0', NULL, 1607324298, 0, '未登录', 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607324298852,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685401684323336224, 1607324299, 0, b'0', NULL, 1607324299, 0, '未登录', 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607324299162,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685401687178608672, 1607324301, 0, b'0', NULL, 1607324301, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607324301885, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685401687358963744, 1607324302, 0, b'0', NULL, 1607324302, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607324302056, 'Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685401687426072608, 1607324302, 0, b'0', NULL, 1607324302, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607324302120, 'Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685401687689265184, 1607324302, 0, b'0', NULL, 1607324302, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607324302372, 'Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685401688024809504, 1607324302, 0, b'0', NULL, 1607324302, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607324302693, 'Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685401756957147168, 1607324368, 0, b'0', NULL, 1607324368, 0, NULL, 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 200,
        '{\"iFrame\":1,\"token\":\"Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607324368430, 'Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685402002621726752, 1607324602, 0, b'0', NULL, 1607324602, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607324602718, 'Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685402053689475104, 1607324651, 0, b'0', NULL, 1607324651, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607324651418, 'Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685402053713592352, 1607324651, 0, b'0', NULL, 1607324651, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607324651441, 'Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685402054864928800, 1607324652, 0, b'0', NULL, 1607324652, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607324652540, 'Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685402055309525024, 1607324652, 0, b'0', NULL, 1607324652, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607324652959, 'Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685402058792894496, 1607324656, 0, b'0', NULL, 1607324656, 0, NULL, 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 200,
        '{\"iFrame\":1,\"token\":\"Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607324656285, 'Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685402060321718304, 1607324657, 0, b'0', NULL, 1607324657, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607324657739, 'Zt2yZH0U6YgTSBJdSPr/oMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685421334228631584, 1607343038, 0, b'0', NULL, 1607343038, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343038743, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685421334539010080, 1607343039, 0, b'0', NULL, 1607343039, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343039065, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685421334626041888, 1607343039, 0, b'0', NULL, 1607343039, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607343039150, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685421335148232736, 1607343039, 0, b'0', NULL, 1607343039, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343039649, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685421335715512352, 1607343040, 0, b'0', NULL, 1607343040, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343040191, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685421339342536736, 1607343043, 0, b'0', NULL, 1607343043, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343043649, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685421350239338528, 1607343054, 0, b'0', NULL, 1607343054, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343054041, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685421350270795808, 1607343054, 0, b'0', NULL, 1607343054, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607343054071, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685421350570688544, 1607343054, 0, b'0', NULL, 1607343054, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343054352, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685421350996410400, 1607343054, 0, b'0', NULL, 1607343054, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343054762, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685421491791855648, 1607343189, 0, b'0', NULL, 1607343189, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343189034, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685421491831701536, 1607343189, 0, b'0', NULL, 1607343189, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607343189076, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685421492081262624, 1607343189, 0, b'0', NULL, 1607343189, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343189307, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685421493011349536, 1607343190, 0, b'0', NULL, 1607343190, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343190202, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685421500021080096, 1607343196, 0, b'0', NULL, 1607343196, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343196886, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685421500046245920, 1607343196, 0, b'0', NULL, 1607343196, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607343196908, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685421500318875680, 1607343197, 0, b'0', NULL, 1607343197, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343197169, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685421500765570080, 1607343197, 0, b'0', NULL, 1607343197, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343197597, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685421737997500448, 1607343423, 0, b'0', NULL, 1607343423, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343423836, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685421770189832224, 1607343454, 0, b'0', NULL, 1607343454, 0, NULL, 'DictService', 'insert', '0:0:0:0:0:0:0:1',
        200,
        '{\"data\":{\"id\":\"\",\"name\":\"版本信息\",\"code\":\"myVersionCode\",\"description\":\"版本信息\",\"type\":\"0\"},\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343454540, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : insert-->');
INSERT INTO `sys_log`
VALUES (1685421770230726688, 1607343454, 0, b'0', NULL, 1607343454, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[{\"name\":\"1\",\"symbol\":\"=\",\"data\":\"1\"}],\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343454578, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685421775746236448, 1607343459, 0, b'0', NULL, 1607343459, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"1685421770170957800\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343459837, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685421800534573088, 1607343483, 0, b'0', NULL, 1607343483, 0, NULL, 'DictService', 'insertItem',
        '0:0:0:0:0:0:0:1', 200,
        '{\"data\":{\"id\":\"\",\"dictId\":\"1685421770170957800\",\"text\":\"test\",\"value\":\"4\",\"description\":\"test\",\"sortOrder\":\"0\"},\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343483479, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : insertItem-->');
INSERT INTO `sys_log`
VALUES (1685421800564981792, 1607343483, 0, b'0', NULL, 1607343483, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"1685421770170957800\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[{\"name\":\"1\",\"symbol\":\"=\",\"data\":\"1\"}],\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343483507, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685421803097292832, 1607343485, 0, b'0', NULL, 1607343485, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343485922, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685421803125604384, 1607343485, 0, b'0', NULL, 1607343485, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607343485940, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685421803356291104, 1607343486, 0, b'0', NULL, 1607343486, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343486167, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685421803898404896, 1607343486, 0, b'0', NULL, 1607343486, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343486684, 'Oh1YsCO331bmSzJI+UTacMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685422091405361184, 1607343760, 0, b'0', NULL, 1607343760, 0, '未登录', 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607343760875,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685422091646533664, 1607343761, 0, b'0', NULL, 1607343761, 0, '未登录', 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607343761102,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685422093843300384, 1607343763, 0, b'0', NULL, 1607343763, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343763197, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685422094153678880, 1607343763, 0, b'0', NULL, 1607343763, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"gJ03U+ThfCxcu7tLwIRtdMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343763495, 'gJ03U+ThfCxcu7tLwIRtdMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685422094180941856, 1607343763, 0, b'0', NULL, 1607343763, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"gJ03U+ThfCxcu7tLwIRtdMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607343763522, 'gJ03U+ThfCxcu7tLwIRtdMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685422094516486176, 1607343763, 0, b'0', NULL, 1607343763, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"gJ03U+ThfCxcu7tLwIRtdMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343763841, 'gJ03U+ThfCxcu7tLwIRtdMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685422095065940000, 1607343764, 0, b'0', NULL, 1607343764, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"gJ03U+ThfCxcu7tLwIRtdMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343764363, 'gJ03U+ThfCxcu7tLwIRtdMFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685422209704656928, 1607343873, 0, b'0', NULL, 1607343873, 0, '未登录', 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 403,
        '{\"iFrame\":1,\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343873691, NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685422211162177568, 1607343875, 0, b'0', NULL, 1607343875, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343875084, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685422211317366816, 1607343875, 0, b'0', NULL, 1607343875, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"3uVbGtl+qokwX8cGSC6VtcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343875232, '3uVbGtl+qokwX8cGSC6VtcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685422211343581216, 1607343875, 0, b'0', NULL, 1607343875, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"3uVbGtl+qokwX8cGSC6VtcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607343875252, '3uVbGtl+qokwX8cGSC6VtcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685422211524984864, 1607343875, 0, b'0', NULL, 1607343875, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"3uVbGtl+qokwX8cGSC6VtcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343875431, '3uVbGtl+qokwX8cGSC6VtcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685422212004184096, 1607343875, 0, b'0', NULL, 1607343875, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"3uVbGtl+qokwX8cGSC6VtcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343875886, '3uVbGtl+qokwX8cGSC6VtcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685422240979484704, 1607343903, 0, b'0', NULL, 1607343903, 0, '未登录', 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607343903520,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685422241122091040, 1607343903, 0, b'0', NULL, 1607343903, 0, '未登录', 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607343903654,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685422242549202976, 1607343905, 0, b'0', NULL, 1607343905, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343905017, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685422242640429088, 1607343905, 0, b'0', NULL, 1607343905, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343905104, '+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685422242787229728, 1607343905, 0, b'0', NULL, 1607343905, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607343905118, '+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685422242826027040, 1607343905, 0, b'0', NULL, 1607343905, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343905280, '+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685422243151085600, 1607343905, 0, b'0', NULL, 1607343905, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343905589, '+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685422249690005536, 1607343911, 0, b'0', NULL, 1607343911, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343911826, '+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685422249738240032, 1607343911, 0, b'0', NULL, 1607343911, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607343911844, '+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685422249906012192, 1607343912, 0, b'0', NULL, 1607343912, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343912032, '+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685422250277208096, 1607343912, 0, b'0', NULL, 1607343912, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343912387, '+S7eVghMtUP7JVTIlq+3AcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685422337816526880, 1607343995, 0, b'0', NULL, 1607343995, 0, '未登录', 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607343995871,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685422338120613920, 1607343996, 0, b'0', NULL, 1607343996, 0, '未登录', 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607343996160,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685422340671799328, 1607343998, 0, b'0', NULL, 1607343998, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343998595, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685422340797628448, 1607343998, 0, b'0', NULL, 1607343998, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343998712, 'rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685422340815454240, 1607343998, 0, b'0', NULL, 1607343998, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607343998729, 'rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685422340972740640, 1607343998, 0, b'0', NULL, 1607343998, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607343998881, 'rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685422357087256608, 1607344014, 0, b'0', NULL, 1607344014, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344014249, 'rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->');
INSERT INTO `sys_log`
VALUES (1685422445075365920, 1607344098, 0, b'0', NULL, 1607344098, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344098154, 'rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685422445108920352, 1607344098, 0, b'0', NULL, 1607344098, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607344098187, 'rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685422445403570208, 1607344098, 0, b'0', NULL, 1607344098, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344098473, 'rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685422456094851104, 1607344108, 0, b'0', NULL, 1607344108, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344108668, 'rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685422530977857568, 1607344180, 0, b'0', NULL, 1607344180, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344180081, 'rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685422531007217696, 1607344180, 0, b'0', NULL, 1607344180, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607344180112, 'rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685422531233710112, 1607344180, 0, b'0', NULL, 1607344180, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344180328, 'rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685422541303185440, 1607344189, 0, b'0', NULL, 1607344189, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344189931, 'rfCaH46CR4caNv2PaGDLHcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685422846392664096, 1607344480, 0, b'0', NULL, 1607344480, 0, '未登录', 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607344480861,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685422859614158880, 1607344493, 0, b'0', NULL, 1607344493, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344493494, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685422859738939424, 1607344493, 0, b'0', NULL, 1607344493, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"fw6RLSafJ6/YTXEvOzkyUcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344493613, 'fw6RLSafJ6/YTXEvOzkyUcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685422859799756832, 1607344493, 0, b'0', NULL, 1607344493, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"fw6RLSafJ6/YTXEvOzkyUcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607344493672, 'fw6RLSafJ6/YTXEvOzkyUcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685422860060852256, 1607344493, 0, b'0', NULL, 1607344493, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"fw6RLSafJ6/YTXEvOzkyUcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344493921, 'fw6RLSafJ6/YTXEvOzkyUcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685422860276858912, 1607344494, 0, b'0', NULL, 1607344494, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"fw6RLSafJ6/YTXEvOzkyUcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344494127, 'fw6RLSafJ6/YTXEvOzkyUcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685422863800074272, 1607344497, 0, b'0', NULL, 1607344497, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"fw6RLSafJ6/YTXEvOzkyUcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344497487, 'fw6RLSafJ6/YTXEvOzkyUcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685422866773835808, 1607344500, 0, b'0', NULL, 1607344500, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"1685421770170957800\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"fw6RLSafJ6/YTXEvOzkyUcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344500263, 'fw6RLSafJ6/YTXEvOzkyUcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685422880598261792, 1607344513, 0, b'0', NULL, 1607344513, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"1685421770170957800\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"fw6RLSafJ6/YTXEvOzkyUcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344513505, 'fw6RLSafJ6/YTXEvOzkyUcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685423010602811424, 1607344637, 0, b'0', NULL, 1607344637, 0, '未登录', 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 403,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344637459, NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685423013646827552, 1607344640, 0, b'0', NULL, 1607344640, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344640392, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685423013822988320, 1607344640, 0, b'0', NULL, 1607344640, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344640557, '7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685423013901631520, 1607344640, 0, b'0', NULL, 1607344640, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607344640634, '7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685423014234030112, 1607344640, 0, b'0', NULL, 1607344640, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344640950, '7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685423014638780448, 1607344641, 0, b'0', NULL, 1607344641, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344641335, '7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685423018750246944, 1607344645, 0, b'0', NULL, 1607344645, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344645258, '7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685423086383398944, 1607344709, 0, b'0', NULL, 1607344709, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344709755, '7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685423186295914528, 1607344805, 0, b'0', NULL, 1607344805, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344805038, '7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685423197037527072, 1607344815, 0, b'0', NULL, 1607344815, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"1685421770170957800\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344815288, '7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685423329255620640, 1607344941, 0, b'0', NULL, 1607344941, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344941379, '7RWefFEk48P1Yl/dkXo7q8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685423335834386464, 1607344947, 0, b'0', NULL, 1607344947, 0, '未登录', 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607344947654,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685423335990624288, 1607344947, 0, b'0', NULL, 1607344947, 0, '未登录', 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607344947803,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685423337404104736, 1607344949, 0, b'0', NULL, 1607344949, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344949150, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685423337491136544, 1607344949, 0, b'0', NULL, 1607344949, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344949232, 'WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685423337511059488, 1607344949, 0, b'0', NULL, 1607344949, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607344949249, 'WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685423337653665824, 1607344949, 0, b'0', NULL, 1607344949, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344949390, 'WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685423337905324064, 1607344949, 0, b'0', NULL, 1607344949, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344949630, 'WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685423341294321696, 1607344952, 0, b'0', NULL, 1607344952, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344952862, 'WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685423346708119584, 1607344958, 0, b'0', NULL, 1607344958, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344958024, 'WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685423380067516448, 1607344989, 0, b'0', NULL, 1607344989, 0, '查询失败', 'DictService', 'getById',
        '0:0:0:0:0:0:0:1', 400,
        '{\"id\":\"1685421770170957800\",\"token\":\"WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607344989839, 'WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getById-->');
INSERT INTO `sys_log`
VALUES (1685423653460639776, 1607345250, 0, b'0', NULL, 1607345250, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607345250566, 'WodZJ8PETIt3WCDc0zGVxsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685423891296550944, 1607345477, 0, b'0', NULL, 1607345477, 0, '未登录', 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607345477384,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685423891448594464, 1607345477, 0, b'0', NULL, 1607345477, 0, '未登录', 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607345477528,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685423892714225696, 1607345478, 0, b'0', NULL, 1607345478, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607345478737, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685423892806500384, 1607345478, 0, b'0', NULL, 1607345478, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607345478823, 'BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685423892832714784, 1607345478, 0, b'0', NULL, 1607345478, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607345478840, 'BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685423892952252448, 1607345478, 0, b'0', NULL, 1607345478, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607345478961, 'BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685423893256339488, 1607345479, 0, b'0', NULL, 1607345479, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607345479254, 'BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685423895942791200, 1607345481, 0, b'0', NULL, 1607345481, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607345481816, 'BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685423909669699616, 1607345494, 0, b'0', NULL, 1607345494, 0, '查询失败', 'DictService', 'getById',
        '0:0:0:0:0:0:0:1', 400,
        '{\"id\":\"1685421770170957800\",\"token\":\"BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607345494906, 'BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getById-->');
INSERT INTO `sys_log`
VALUES (1685424207837528096, 1607345779, 0, b'0', NULL, 1607345779, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607345779260, 'BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685424522728046624, 1607346079, 0, b'0', NULL, 1607346079, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607346079564, 'BiZYKOIbIYGEGgmAVaDRQsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685424670412636192, 1607346220, 0, b'0', NULL, 1607346220, 0,
        'Class indi.uhyils.controller.AllController can not access a member of class com.alibaba.fastjson.JSONObject with modifiers \"private static final\"',
        'UserService', 'login', '0:0:0:0:0:0:0:1', 200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607346220390, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685424714480091168, 1607346262, 0, b'0', NULL, 1607346262, 0,
        'Class indi.uhyils.controller.AllController can not access a member of class com.alibaba.fastjson.JSONObject with modifiers \"private static final\"',
        'UserService', 'login', '0:0:0:0:0:0:0:1', 200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607346262430, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685424808491221024, 1607346352, 0, b'0', NULL, 1607346352, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607346349170, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685424809454862368, 1607346353, 0, b'0', NULL, 1607346353, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"LizLetcoMMzpfpVvH5CJ48FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607346352679, 'LizLetcoMMzpfpVvH5CJ48FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685424809504145440, 1607346353, 0, b'0', NULL, 1607346353, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"LizLetcoMMzpfpVvH5CJ48FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607346353054, 'LizLetcoMMzpfpVvH5CJ48FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685424810437378080, 1607346353, 0, b'0', NULL, 1607346353, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"LizLetcoMMzpfpVvH5CJ48FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607346353634, 'LizLetcoMMzpfpVvH5CJ48FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685424810997317664, 1607346354, 0, b'0', NULL, 1607346354, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"LizLetcoMMzpfpVvH5CJ48FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607346354228, 'LizLetcoMMzpfpVvH5CJ48FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685425026172452896, 1607346559, 0, b'0', NULL, 1607346559, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607346559674,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685425026383216672, 1607346559, 0, b'0', NULL, 1607346559, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607346559884,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685425048479858720, 1607346580, 0, b'0', NULL, 1607346580, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607346580959,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685425179397718048, 1607346705, 0, b'0', NULL, 1607346705, 0, '未登录', 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607346705798,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685425179861188640, 1607346706, 0, b'0', NULL, 1607346706, 0, '未登录', 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607346706252,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685425181528424480, 1607346707, 0, b'0', NULL, 1607346707, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607346707845, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685425181845094432, 1607346708, 0, b'0', NULL, 1607346708, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"U6W7TLwPSI79BrFEszWHhsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607346708147, 'U6W7TLwPSI79BrFEszWHhsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685425181895426080, 1607346708, 0, b'0', NULL, 1607346708, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"U6W7TLwPSI79BrFEszWHhsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607346708194, 'U6W7TLwPSI79BrFEszWHhsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685425182183784480, 1607346708, 0, b'0', NULL, 1607346708, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"U6W7TLwPSI79BrFEszWHhsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607346708470, 'U6W7TLwPSI79BrFEszWHhsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685425203997311008, 1607346729, 0, b'0', NULL, 1607346729, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"U6W7TLwPSI79BrFEszWHhsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607346729272, 'U6W7TLwPSI79BrFEszWHhsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685425204024573984, 1607346729, 0, b'0', NULL, 1607346729, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"U6W7TLwPSI79BrFEszWHhsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607346729299, 'U6W7TLwPSI79BrFEszWHhsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685425204266795040, 1607346729, 0, b'0', NULL, 1607346729, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"U6W7TLwPSI79BrFEszWHhsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607346729530, 'U6W7TLwPSI79BrFEszWHhsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685425347867181088, 1607346866, 0, b'0', NULL, 1607346866, 0, '未登录', 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607346866463,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685425348144005152, 1607346866, 0, b'0', NULL, 1607346866, 0, '未登录', 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607346866741,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685425349671780384, 1607346868, 0, b'0', NULL, 1607346868, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607346868197, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685425397228896288, 1607346913, 0, b'0', NULL, 1607346913, 0, '未登录', 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607346913554,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685425397420785696, 1607346913, 0, b'0', NULL, 1607346913, 0, '未登录', 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607346913737,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685425399382671392, 1607346915, 0, b'0', NULL, 1607346915, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607346915605, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685425923772383264, 1607347415, 0, b'0', NULL, 1607347415, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607347415683, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685425924081713184, 1607347416, 0, b'0', NULL, 1607347416, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607347415997, 'fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685425924179230752, 1607347416, 0, b'0', NULL, 1607347416, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607347416083, 'fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685425924601806880, 1607347416, 0, b'0', NULL, 1607347416, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607347416494, 'fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685425925138677792, 1607347417, 0, b'0', NULL, 1607347417, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607347417005, 'fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685425933190692896, 1607347424, 0, b'0', NULL, 1607347424, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607347424685, 'fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685425935031992352, 1607347426, 0, b'0', NULL, 1607347426, 0, NULL, 'DictService', 'getById',
        '0:0:0:0:0:0:0:1', 200,
        '{\"id\":\"1685421770170957856\",\"token\":\"fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607347426442, 'fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getById-->');
INSERT INTO `sys_log`
VALUES (1685425941187133472, 1607347432, 0, b'0', NULL, 1607347432, 0, NULL, 'DictService', 'getById',
        '0:0:0:0:0:0:0:1', 200,
        '{\"id\":\"1685421770170957856\",\"token\":\"fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607347432311, 'fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getById-->');
INSERT INTO `sys_log`
VALUES (1685425974026436640, 1607347463, 0, b'0', NULL, 1607347463, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607347463630, 'fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685425974044262432, 1607347463, 0, b'0', NULL, 1607347463, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607347463648, 'fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685425974208888864, 1607347463, 0, b'0', NULL, 1607347463, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607347463803, 'fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685425974493052960, 1607347464, 0, b'0', NULL, 1607347464, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607347464076, 'fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685425974584279072, 1607347464, 0, b'0', NULL, 1607347464, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607347464152, 'fpoTJVCJyhXphf3OhJmSAsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685464594459394080, 1607384294, 0, b'0', NULL, 1607384294, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384294922, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685464594773966880, 1607384295, 0, b'0', NULL, 1607384295, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Yh9wLxDqVflyhdLHtkbQl8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384295240, 'Yh9wLxDqVflyhdLHtkbQl8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685464594821152800, 1607384295, 0, b'0', NULL, 1607384295, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Yh9wLxDqVflyhdLHtkbQl8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607384295290, 'Yh9wLxDqVflyhdLHtkbQl8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685464595419889696, 1607384295, 0, b'0', NULL, 1607384295, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Yh9wLxDqVflyhdLHtkbQl8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384295862, 'Yh9wLxDqVflyhdLHtkbQl8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685464595924254752, 1607384296, 0, b'0', NULL, 1607384296, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"Yh9wLxDqVflyhdLHtkbQl8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384296341, 'Yh9wLxDqVflyhdLHtkbQl8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685464681224863776, 1607384377, 0, b'0', NULL, 1607384377, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"token\":\"Yh9wLxDqVflyhdLHtkbQl8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384377691, 'Yh9wLxDqVflyhdLHtkbQl8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685464681532096544, 1607384377, 0, b'0', NULL, 1607384377, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384377983, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685464681553068064, 1607384378, 0, b'0', NULL, 1607384378, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607384378004, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685464682071064608, 1607384378, 0, b'0', NULL, 1607384378, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384378496, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685464683020025888, 1607384379, 0, b'0', NULL, 1607384379, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384379403, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685464782667251744, 1607384474, 0, b'0', NULL, 1607384474, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384474433, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685464796169764896, 1607384487, 0, b'0', NULL, 1607384487, 0,
        'java.lang.Integer cannot be cast to com.alibaba.fastjson.JSONObject', 'DictService', 'insert',
        '0:0:0:0:0:0:0:1', 200,
        '{\"data\":{\"id\":\"\",\"name\":\"下一步计划\",\"code\":\"lastPlan\",\"description\":\"下一步计划\",\"type\":\"1\"},\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384487310, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : insert-->');
INSERT INTO `sys_log`
VALUES (1685464796203319328, 1607384487, 0, b'0', NULL, 1607384487, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[{\"name\":\"1\",\"symbol\":\"=\",\"data\":\"1\"}],\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384487344, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685464799083757600, 1607384490, 0, b'0', NULL, 1607384490, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"1685464796150890528\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384490088, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685464822007726112, 1607384511, 0, b'0', NULL, 1607384511, 0,
        'java.lang.Boolean cannot be cast to com.alibaba.fastjson.JSONObject', 'DictService', 'insertItem',
        '0:0:0:0:0:0:0:1', 200,
        '{\"data\":{\"id\":\"\",\"dictId\":\"1685464796150890528\",\"text\":\"test1\",\"value\":\"ttttt内容\",\"description\":\"描述\",\"sortOrder\":\"1\"},\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384511953, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : insertItem-->');
INSERT INTO `sys_log`
VALUES (1685464822036037664, 1607384511, 0, b'0', NULL, 1607384511, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"1685464796150890528\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[{\"name\":\"1\",\"symbol\":\"=\",\"data\":\"1\"}],\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384511978, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685464828013969440, 1607384517, 0, b'0', NULL, 1607384517, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384517682, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685464828041232416, 1607384517, 0, b'0', NULL, 1607384517, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607384517702, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685464828474294304, 1607384518, 0, b'0', NULL, 1607384518, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384518118, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685464828990193696, 1607384518, 0, b'0', NULL, 1607384518, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384518612, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685464840012824608, 1607384529, 0, b'0', NULL, 1607384529, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384529124, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685464940615303200, 1607384625, 0, b'0', NULL, 1607384625, 0,
        'java.lang.Integer cannot be cast to com.alibaba.fastjson.JSONObject', 'DictService', 'insert',
        '0:0:0:0:0:0:0:1', 200,
        '{\"data\":{\"id\":\"\",\"name\":\"快捷入口\",\"code\":\"quickStart\",\"description\":\"快捷入口\",\"type\":\"0\"},\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384625066, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : insert-->');
INSERT INTO `sys_log`
VALUES (1685464940636274720, 1607384625, 0, b'0', NULL, 1607384625, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[{\"name\":\"1\",\"symbol\":\"=\",\"data\":\"1\"}],\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384625086, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685464968599699488, 1607384651, 0, b'0', NULL, 1607384651, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"1685464796150890528\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384651754, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685464976449339424, 1607384659, 0, b'0', NULL, 1607384659, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"1685421770170957856\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384659240, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685464980720189472, 1607384663, 0, b'0', NULL, 1607384663, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"0\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384663312, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685464983033348128, 1607384665, 0, b'0', NULL, 1607384665, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"1685421770170957856\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384665519, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685464985824657440, 1607384668, 0, b'0', NULL, 1607384668, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"1685464796150890528\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384668181, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685464988993454112, 1607384671, 0, b'0', NULL, 1607384671, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"1685421770170957856\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384671203, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685464995965435936, 1607384677, 0, b'0', NULL, 1607384677, 0,
        'java.lang.Boolean cannot be cast to com.alibaba.fastjson.JSONObject', 'DictService', 'deleteItem',
        '0:0:0:0:0:0:0:1', 200,
        '{\"id\":\"1685421800516747296\",\"token\":\"2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384677852, '2B6aF+Ixmm6BgGMDzck5g8FDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : deleteItem-->');
INSERT INTO `sys_log`
VALUES (1685465123943088160, 1607384799, 0, b'0', NULL, 1607384799, 0, '未登录', 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607384799886,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685465124158046240, 1607384800, 0, b'0', NULL, 1607384800, 0, '未登录', 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607384800104,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685465125486592032, 1607384801, 0, b'0', NULL, 1607384801, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384801372, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685465125582012448, 1607384801, 0, b'0', NULL, 1607384801, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384801463, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685465125648072736, 1607384801, 0, b'0', NULL, 1607384801, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607384801526, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685465125974179872, 1607384801, 0, b'0', NULL, 1607384801, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384801834, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685465126391513120, 1607384802, 0, b'0', NULL, 1607384802, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384802231, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685465133839548448, 1607384809, 0, b'0', NULL, 1607384809, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384809337, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685465133868908576, 1607384809, 0, b'0', NULL, 1607384809, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607384809363, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685465134293581856, 1607384809, 0, b'0', NULL, 1607384809, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384809771, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685465134655340576, 1607384810, 0, b'0', NULL, 1607384810, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384810114, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685465211454095392, 1607384883, 0, b'0', NULL, 1607384883, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384883357, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685465215797297184, 1607384887, 0, b'0', NULL, 1607384887, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"1685464940602720288\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384887495, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685465283200811040, 1607384951, 0, b'0', NULL, 1607384951, 0, NULL, 'DictService', 'insertItem',
        '0:0:0:0:0:0:0:1', 200,
        '{\"data\":{\"id\":\"\",\"dictId\":\"1685464940602720288\",\"text\":\"服务字典\",\"value\":\"2\",\"description\":\"服务字典\",\"sortOrder\":\"0\"},\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384951780, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : insertItem-->');
INSERT INTO `sys_log`
VALUES (1685465283234365472, 1607384951, 0, b'0', NULL, 1607384951, 0, NULL, 'DictService', 'getByItemArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"dictId\":\"1685464940602720288\",\"page\":1,\"size\":10,\"paging\":true,\"args\":[{\"name\":\"1\",\"symbol\":\"=\",\"data\":\"1\"}],\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384951811, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByItemArgs-->');
INSERT INTO `sys_log`
VALUES (1685465285595758624, 1607384954, 0, b'0', NULL, 1607384954, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384954062, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685465285617778720, 1607384954, 0, b'0', NULL, 1607384954, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607384954084, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685465285987926048, 1607384954, 0, b'0', NULL, 1607384954, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384954438, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685465289977757728, 1607384958, 0, b'0', NULL, 1607384958, 0,
        'java.lang.String cannot be cast to java.lang.Long', 'DistributeService', 'getWelcomeData', '0:0:0:0:0:0:0:1',
        500,
        '{\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607384958244, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->');
INSERT INTO `sys_log`
VALUES (1685465397539635232, 1607385060, 0, b'0', NULL, 1607385060, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385060823, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685465397565849632, 1607385060, 0, b'0', NULL, 1607385060, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607385060847, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685465398505373728, 1607385061, 0, b'0', NULL, 1607385061, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385061744, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685465399184850976, 1607385062, 0, b'0', NULL, 1607385062, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385062392, 'tivJqRgwHFm0zQ0AKQufncFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685465688299274272, 1607385338, 0, b'0', NULL, 1607385338, 0, '未登录', 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607385338088,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685465688626429984, 1607385338, 0, b'0', NULL, 1607385338, 0, '未登录', 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607385338422,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685465690951122976, 1607385340, 0, b'0', NULL, 1607385340, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385340641, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685465691346436128, 1607385341, 0, b'0', NULL, 1607385341, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385341018, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685465691404107808, 1607385341, 0, b'0', NULL, 1607385341, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607385341072, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685465691729166368, 1607385341, 0, b'0', NULL, 1607385341, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385341383, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685465708409913376, 1607385357, 0, b'0', NULL, 1607385357, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385357286, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685465708434030624, 1607385357, 0, b'0', NULL, 1607385357, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607385357311, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685465708754894880, 1607385357, 0, b'0', NULL, 1607385357, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385357620, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685465772865880096, 1607385418, 0, b'0', NULL, 1607385418, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385418758, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685465772909920288, 1607385418, 0, b'0', NULL, 1607385418, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607385418778, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685465773144801312, 1607385419, 0, b'0', NULL, 1607385419, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385419027, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685465773502365728, 1607385419, 0, b'0', NULL, 1607385419, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385419368, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685465777101078560, 1607385422, 0, b'0', NULL, 1607385422, 0, NULL, 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 200,
        '{\"iFrame\":1,\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385422800, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685465779830521888, 1607385425, 0, b'0', NULL, 1607385425, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385425403, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685465786937770016, 1607385432, 0, b'0', NULL, 1607385432, 0, NULL, 'DictService', 'getAllMenuIcon',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385432172, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getAllMenuIcon-->');
INSERT INTO `sys_log`
VALUES (1685465787013267488, 1607385432, 0, b'0', NULL, 1607385432, 0, '您好,您请求的服务暂时不可用,请一分钟后重试!', 'MenuService',
        'getById', '0:0:0:0:0:0:0:1', 400,
        '{\"id\":\"\",\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385432252, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685465802803773472, 1607385447, 0, b'0', NULL, 1607385447, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385447312, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685465802823696416, 1607385447, 0, b'0', NULL, 1607385447, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607385447331, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685465803162386464, 1607385447, 0, b'0', NULL, 1607385447, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385447653, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685465803753783328, 1607385448, 0, b'0', NULL, 1607385448, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385448210, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685465806411923488, 1607385450, 0, b'0', NULL, 1607385450, 0, NULL, 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 200,
        '{\"iFrame\":1,\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385450751, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685465853829578784, 1607385495, 0, b'0', NULL, 1607385495, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"token\":\"Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385495974, 'Ja/j2Wuc67yks2bQW9bROsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685465854040342560, 1607385496, 0, b'0', NULL, 1607385496, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385496176, 'ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685465854061314080, 1607385496, 0, b'0', NULL, 1607385496, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607385496196, 'ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685465854367498272, 1607385496, 0, b'0', NULL, 1607385496, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385496487, 'ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685465854797414432, 1607385496, 0, b'0', NULL, 1607385496, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385496898, 'ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685465860352770080, 1607385502, 0, b'0', NULL, 1607385502, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385502196, 'ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685465869699776544, 1607385511, 0, b'0', NULL, 1607385511, 0, NULL, 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 200,
        '{\"iFrame\":1,\"token\":\"ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385511110, 'ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685465891887644704, 1607385532, 0, b'0', NULL, 1607385532, 0, NULL, 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 200,
        '{\"iFrame\":1,\"token\":\"ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385532268, 'ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685466021641584672, 1607385656, 0, b'0', NULL, 1607385656, 0, NULL, 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 200,
        '{\"iFrame\":1,\"token\":\"ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385656011, 'ROnSPYKK0NcR/NIPo/VkmcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685466050741665824, 1607385683, 0, b'0', NULL, 1607385683, 0, '未登录', 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607385683765,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685466050929360928, 1607385683, 0, b'0', NULL, 1607385683, 0, '未登录', 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607385683943,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685466052871323680, 1607385685, 0, b'0', NULL, 1607385685, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385685796, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685466052956258336, 1607385685, 0, b'0', NULL, 1607385685, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"KONeBksKBMUFIYZr+OAEYsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385685877, 'KONeBksKBMUFIYZr+OAEYsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685466052975132704, 1607385685, 0, b'0', NULL, 1607385685, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"KONeBksKBMUFIYZr+OAEYsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607385685893, 'KONeBksKBMUFIYZr+OAEYsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685466053127176224, 1607385686, 0, b'0', NULL, 1607385686, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"KONeBksKBMUFIYZr+OAEYsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385686038, 'KONeBksKBMUFIYZr+OAEYsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685466053365202976, 1607385686, 0, b'0', NULL, 1607385686, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"KONeBksKBMUFIYZr+OAEYsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385686267, 'KONeBksKBMUFIYZr+OAEYsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685466056106180640, 1607385688, 0, b'0', NULL, 1607385688, 0, NULL, 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 200,
        '{\"iFrame\":1,\"token\":\"KONeBksKBMUFIYZr+OAEYsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385688881, 'KONeBksKBMUFIYZr+OAEYsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685466058295607328, 1607385690, 0, b'0', NULL, 1607385690, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"KONeBksKBMUFIYZr+OAEYsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607385690969, 'KONeBksKBMUFIYZr+OAEYsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685466368364773408, 1607385986, 0, b'0', NULL, 1607385986, 0, '未登录', 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607385986653,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685466682531774496, 1607386286, 0, b'0', NULL, 1607386286, 0, '未登录', 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607386286286,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685466740743471136, 1607386341, 0, b'0', NULL, 1607386341, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607386341801, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685466740872445984, 1607386341, 0, b'0', NULL, 1607386341, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607386341920, 'Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685466740931166240, 1607386341, 0, b'0', NULL, 1607386341, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607386341979, 'Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685466741230010400, 1607386342, 0, b'0', NULL, 1607386342, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607386342265, 'Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685466741480620064, 1607386342, 0, b'0', NULL, 1607386342, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607386342504, 'Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685466744504713248, 1607386345, 0, b'0', NULL, 1607386345, 0, NULL, 'DictService', 'getByArgs',
        '0:0:0:0:0:0:0:1', 200,
        '{\"page\":1,\"size\":10,\"paging\":true,\"args\":[],\"token\":\"Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607386345388, 'Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DictServiceImpl : getByArgs-->');
INSERT INTO `sys_log`
VALUES (1685466747259322400, 1607386348, 0, b'0', NULL, 1607386348, 0, NULL, 'MenuService', 'getMenuTree',
        '0:0:0:0:0:0:0:1', 200,
        '{\"iFrame\":1,\"token\":\"Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607386348016, 'Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getMenuTree-->');
INSERT INTO `sys_log`
VALUES (1685467056069148704, 1607386642, 0, b'0', NULL, 1607386642, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607386642519, 'Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685467370717446176, 1607386942, 0, b'0', NULL, 1607386942, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607386942588, 'Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685467685203214368, 1607387242, 0, b'0', NULL, 1607387242, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607387242508, 'Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685467999781257248, 1607387542, 0, b'0', NULL, 1607387542, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607387542513, 'Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685468314347765792, 1607387842, 0, b'0', NULL, 1607387842, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607387842508, 'Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685468628917420064, 1607388142, 0, b'0', NULL, 1607388142, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607388142504, 'Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685468943495462944, 1607388442, 0, b'0', NULL, 1607388442, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607388442508, 'Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685469259007787040, 1607388743, 0, b'0', NULL, 1607388743, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607388743406, 'Fc9dK7ZKhhtqjsd4v7gfosFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685472739570221088, 1607392062, 0, b'0', NULL, 1607392062, 0, '未登录', 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607392062703,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685473033869852704, 1607392343, 0, b'0', NULL, 1607392343, 0, '未登录', 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607392343394,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685473252103684128, 1607392551, 0, b'0', NULL, 1607392551, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607392551518, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685473253600002080, 1607392552, 0, b'0', NULL, 1607392552, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607392552944, 'ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685473253656625184, 1607392553, 0, b'0', NULL, 1607392553, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607392552998, 'ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685473271760289824, 1607392570, 0, b'0', NULL, 1607392570, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607392570262, 'ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685473271785455648, 1607392570, 0, b'0', NULL, 1607392570, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607392570285, 'ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685473273643532320, 1607392572, 0, b'0', NULL, 1607392572, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607392572059, 'ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685473275896922144, 1607392574, 0, b'0', NULL, 1607392574, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607392574209, 'ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685473348482498592, 1607392643, 0, b'0', NULL, 1607392643, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607392643430, 'ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685473401637961760, 1607392694, 0, b'0', NULL, 1607392694, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607392694125, 'ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685473401819365408, 1607392694, 0, b'0', NULL, 1607392694, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607392694257, 'ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685473403829485600, 1607392696, 0, b'0', NULL, 1607392696, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607392696113, 'ZQa46vJrCLRKhiJe24CflcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685476833182613536, 1607395966, 0, b'0', NULL, 1607395966, 0, '未登录', 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 403,
        '{\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}', 1607395966681,
        NULL, '页面请求-->');
INSERT INTO `sys_log`
VALUES (1685477189583110176, 1607396306, 0, b'0', NULL, 1607396306, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607396306591, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685477189887197216, 1607396306, 0, b'0', NULL, 1607396306, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607396306880, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685477189951160352, 1607396306, 0, b'0', NULL, 1607396306, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607396306940, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685477190332842016, 1607396307, 0, b'0', NULL, 1607396307, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607396307304, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685477191478935584, 1607396308, 0, b'0', NULL, 1607396308, 0, 'For input string: \"0bc916517931ec27\"',
        'DistributeService', 'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607396308397, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->');
INSERT INTO `sys_log`
VALUES (1685477234931925024, 1607396349, 0, b'0', NULL, 1607396349, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607396349834, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685477234957090848, 1607396349, 0, b'0', NULL, 1607396349, 0, '远程调用错误,具体见日志', 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 500,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607396349861, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4', '');
INSERT INTO `sys_log`
VALUES (1685477244681584672, 1607396359, 0, b'0', NULL, 1607396359, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607396359136, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685477260816023584, 1607396374, 0, b'0', NULL, 1607396374, 0, '远程调用错误,具体见日志', 'DistributeService',
        'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607396374524, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685477559582588960, 1607396659, 0, b'0', NULL, 1607396659, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607396659451, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685477874172166176, 1607396959, 0, b'0', NULL, 1607396959, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607396959466, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685478188739723296, 1607397259, 0, b'0', NULL, 1607397259, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607397259461, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685478503333494816, 1607397559, 0, b'0', NULL, 1607397559, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607397559481, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685478817882177568, 1607397859, 0, b'0', NULL, 1607397859, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607397859458, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685479132464414752, 1607398159, 0, b'0', NULL, 1607398159, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607398159466, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685479447027777568, 1607398459, 0, b'0', NULL, 1607398459, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607398459459, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685479762595676192, 1607398760, 0, b'0', NULL, 1607398760, 0, NULL, 'JvmService',
        'getJvmDataStatisticsResponse', '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607398760407, 'Q30fqUAW98vhCw+WksCLwsFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->JvmServiceImpl : getJvmDataStatisticsResponse-->');
INSERT INTO `sys_log`
VALUES (1685593107455803424, 1607506854, 0, b'0', NULL, 1607506854, 0, NULL, 'UserService', 'login', '0:0:0:0:0:0:0:1',
        200,
        '{\"username\":\"admin\",\"password\":\"123456\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607506854456, NULL, '页面请求-->UserServiceImpl : login-->');
INSERT INTO `sys_log`
VALUES (1685593107830145056, 1607506854, 0, b'0', NULL, 1607506854, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607506854845, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685593108514865184, 1607506855, 0, b'0', NULL, 1607506855, 0, NULL, 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 200,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607506855498, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MongoServiceImpl : getByFileName-->');
INSERT INTO `sys_log`
VALUES (1685593109345337376, 1607506856, 0, b'0', NULL, 1607506856, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607506856291, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685593110513451040, 1607506857, 0, b'0', NULL, 1607506857, 0, 'For input string: \"0bc916517931ec27\"',
        'DistributeService', 'getWelcomeData', '0:0:0:0:0:0:0:1', 500,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607506857403, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->');
INSERT INTO `sys_log`
VALUES (1685593392767041568, 1607507126, 0, b'0', NULL, 1607507126, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507126577, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685593392821567520, 1607507126, 0, b'0', NULL, 1607507126, 0, NULL, 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 200,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607507126635, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MongoServiceImpl : getByFileName-->');
INSERT INTO `sys_log`
VALUES (1685593393056448544, 1607507126, 0, b'0', NULL, 1607507126, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507126858, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685593397213003808, 1607507130, 0, b'0', NULL, 1607507130, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507130822, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685593414859489312, 1607507147, 0, b'0', NULL, 1607507147, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507147651, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685593414890946592, 1607507147, 0, b'0', NULL, 1607507147, 0, NULL, 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 200,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607507147681, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MongoServiceImpl : getByFileName-->');
INSERT INTO `sys_log`
VALUES (1685593415101710368, 1607507147, 0, b'0', NULL, 1607507147, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507147882, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685593415584055328, 1607507148, 0, b'0', NULL, 1607507148, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507148344, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685593490333892640, 1607507219, 0, b'0', NULL, 1607507219, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507219618, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685593490370592800, 1607507219, 0, b'0', NULL, 1607507219, 0, NULL, 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 200,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607507219666, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MongoServiceImpl : getByFileName-->');
INSERT INTO `sys_log`
VALUES (1685593490624348192, 1607507219, 0, b'0', NULL, 1607507219, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507219905, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685593490982961184, 1607507220, 0, b'0', NULL, 1607507220, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507220247, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685593497482035232, 1607507226, 0, b'0', NULL, 1607507226, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507226447, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685593497527124000, 1607507226, 0, b'0', NULL, 1607507226, 0, NULL, 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 200,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607507226490, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MongoServiceImpl : getByFileName-->');
INSERT INTO `sys_log`
VALUES (1685593497758859296, 1607507226, 0, b'0', NULL, 1607507226, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507226710, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685593498319847456, 1607507227, 0, b'0', NULL, 1607507227, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507227247, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');
INSERT INTO `sys_log`
VALUES (1685593506938093600, 1607507235, 0, b'0', NULL, 1607507235, 0, NULL, 'UserService', 'getUserByToken',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507235465, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->UserServiceImpl : getUserByToken-->');
INSERT INTO `sys_log`
VALUES (1685593506966405152, 1607507235, 0, b'0', NULL, 1607507235, 0, NULL, 'MongoService', 'getByFileName',
        '0:0:0:0:0:0:0:1', 200,
        '{\"name\":\"0\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"},\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\"}',
        1607507235492, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MongoServiceImpl : getByFileName-->');
INSERT INTO `sys_log`
VALUES (1685593507299852320, 1607507235, 0, b'0', NULL, 1607507235, 0, NULL, 'MenuService', 'getIndexMenu',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507235799, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->MenuServiceImpl : getIndexMenu-->');
INSERT INTO `sys_log`
VALUES (1685593507739205664, 1607507236, 0, b'0', NULL, 1607507236, 0, NULL, 'DistributeService', 'getWelcomeData',
        '0:0:0:0:0:0:0:1', 200,
        '{\"token\":\"TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4\",\"requestLink\":{\"data\":\"页面请求\",\"class\":\"indi.uhyils.pojo.request.model.LinkNode\"}}',
        1607507236227, 'TagOSrq7yS8cQXH/BViMzcFDxmmQDtzHAkgogp4VydfX/2cFYevn+/U3BgwQYYP4',
        '页面请求-->DistributeServiceImpl : getWelcomeData-->JvmServiceImpl : getJvmDataStatisticsResponse-->MenuServiceImpl : getQuickStartResponse-->JvmServiceImpl : getJvmInfoLogResponse-->DictServiceImpl : getVersionInfoResponse-->DictServiceImpl : getLastPlanResponse-->');

-- ----------------------------
-- Table structure for sys_log_monitor
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_monitor`;
CREATE TABLE `sys_log_monitor`
(
    `id`                bigint(0)                                                    NOT NULL,
    `ip`                varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip',
    `service_name`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务名称',
    `time`              bigint(0)                                                    NULL DEFAULT NULL COMMENT 'jvm开启时间',
    `jvm_total_mem`     double                                                       NULL DEFAULT NULL COMMENT 'jvm最大内存',
    `heap_init_mem`     double                                                       NULL DEFAULT NULL COMMENT '堆初始内存',
    `heap_total_mem`    double                                                       NULL DEFAULT NULL COMMENT '堆最大内存',
    `no_heap_init_mem`  double                                                       NULL DEFAULT NULL COMMENT '非堆区初始内存',
    `no_heap_total_mem` double                                                       NULL DEFAULT NULL COMMENT '非堆区最大内存',
    `end_time`          bigint(0)                                                    NULL DEFAULT NULL COMMENT '服务jvm结束假想时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'JVM日志表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_monitor
-- ----------------------------
INSERT INTO `sys_log_monitor`
VALUES (1685398126819541024, '192.168.22.1', 'provider-user', 1607317568567, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607317904587);
INSERT INTO `sys_log_monitor`
VALUES (1685398126849949728, '192.168.22.1', 'provider-log', 1607317587709, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607317928256);
INSERT INTO `sys_log_monitor`
VALUES (1685398126873018400, '192.168.22.1', 'provider-user', 1607320790667, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607321008740);
INSERT INTO `sys_log_monitor`
VALUES (1685398126892941344, '192.168.22.1', 'provider-log', 1607320894276, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607321008777);
INSERT INTO `sys_log_monitor`
VALUES (1685398234071040032, '192.168.22.1', 'provider-user', 1607320969752, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607321098002);
INSERT INTO `sys_log_monitor`
VALUES (1685398234102497312, '192.168.22.1', 'provider-log', 1607320996712, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607321098049);
INSERT INTO `sys_log_monitor`
VALUES (1685398327677419552, '192.168.22.1', 'provider-user', 1607321070287, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607321430110);
INSERT INTO `sys_log_monitor`
VALUES (1685398327712022560, '192.168.22.1', 'provider-log', 1607321085911, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607321430202);
INSERT INTO `sys_log_monitor`
VALUES (1685398846446764064, '192.168.22.1', 'provider-user', 1607321559539, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607321673399);
INSERT INTO `sys_log_monitor`
VALUES (1685398846476124192, '192.168.22.1', 'provider-log', 1607321579072, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607321673439);
INSERT INTO `sys_log_monitor`
VALUES (1685398931018612768, '192.168.22.1', 'provider-user', 1607321642657, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607321786435);
INSERT INTO `sys_log_monitor`
VALUES (1685398931053215776, '192.168.22.1', 'provider-log', 1607321659056, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607321786473);
INSERT INTO `sys_log_monitor`
VALUES (1685399049546498080, '192.168.22.1', 'provider-user', 1607321734657, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607321877338);
INSERT INTO `sys_log_monitor`
VALUES (1685399049577955360, '192.168.22.1', 'provider-log', 1607321774271, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607321877370);
INSERT INTO `sys_log_monitor`
VALUES (1685399144861007904, '192.168.22.1', 'provider-user', 1607321825453, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607323221060);
INSERT INTO `sys_log_monitor`
VALUES (1685399144890368032, '192.168.22.1', 'provider-log', 1607321865239, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607323221117);
INSERT INTO `sys_log_monitor`
VALUES (1685399205421514784, '192.168.22.1', 'consumer-web', 1607321923892, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607323230291);
INSERT INTO `sys_log_monitor`
VALUES (1685399233939636256, '192.168.22.1', 'provider-algorithm', 1607321954451, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607323230186);
INSERT INTO `sys_log_monitor`
VALUES (1685399239944831008, '192.168.22.1', 'provider-distribute', 1607321959176, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607323230217);
INSERT INTO `sys_log_monitor`
VALUES (1685399247045787680, '192.168.22.1', 'provider-mongo', 1607321964360, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607323230211);
INSERT INTO `sys_log_monitor`
VALUES (1685399254404694048, '192.168.22.1', 'provider-order', 1607321969977, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607323230251);
INSERT INTO `sys_log_monitor`
VALUES (1685399260314468384, '192.168.22.1', 'provider-push', 1607321976868, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607323230245);
INSERT INTO `sys_log_monitor`
VALUES (1685399287472586784, '192.168.22.1', 'provider-software', 1607322006272, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607323230211);
INSERT INTO `sys_log_monitor`
VALUES (1685400553864036384, '192.168.22.1', 'provider-user', 1607323196588, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607323319763);
INSERT INTO `sys_log_monitor`
VALUES (1685400553912270880, '192.168.22.1', 'provider-log', 1607323201183, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607323319819);
INSERT INTO `sys_log_monitor`
VALUES (1685400657373167648, '192.168.22.1', 'provider-user', 1607323238405, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607323835898);
INSERT INTO `sys_log_monitor`
VALUES (1685400657407770656, '192.168.22.1', 'provider-log', 1607323309431, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607323873156);
INSERT INTO `sys_log_monitor`
VALUES (1685400715466375200, '192.168.22.1', 'consumer-web', 1607323366400, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607323830161);
INSERT INTO `sys_log_monitor`
VALUES (1685401237590114336, '192.168.22.1', 'provider-user', 1607323809924, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607324148357);
INSERT INTO `sys_log_monitor`
VALUES (1685401237623668768, '192.168.22.1', 'provider-log', 1607323860709, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607324200614);
INSERT INTO `sys_log_monitor`
VALUES (1685401302895427616, '192.168.22.1', 'consumer-web', 1607323924742, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607324262358);
INSERT INTO `sys_log_monitor`
VALUES (1685401631166824480, '192.168.22.1', 'provider-user', 1607324068990, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607324585044);
INSERT INTO `sys_log_monitor`
VALUES (1685401631190941728, '192.168.22.1', 'provider-log', 1607324238386, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607324730190);
INSERT INTO `sys_log_monitor`
VALUES (1685401645732593696, '192.168.22.1', 'consumer-web', 1607324252271, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607324730175);
INSERT INTO `sys_log_monitor`
VALUES (1685401984091291680, '192.168.22.1', 'provider-user', 1607324577232, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607324914873);
INSERT INTO `sys_log_monitor`
VALUES (1685421010120081440, '192.168.22.1', 'provider-user', 1607342628598, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607344424577);
INSERT INTO `sys_log_monitor`
VALUES (1685421010146295840, '192.168.22.1', 'provider-log', 1607342716526, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607344424644);
INSERT INTO `sys_log_monitor`
VALUES (1685421035100307488, '192.168.22.1', 'consumer-web', 1607342739804, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607344467843);
INSERT INTO `sys_log_monitor`
VALUES (1685421436264513568, '192.168.22.1', 'provider-distribute', 1607343127685, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607344530144);
INSERT INTO `sys_log_monitor`
VALUES (1685422787357835296, '192.168.22.1', 'provider-user', 1607344390802, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607344602298);
INSERT INTO `sys_log_monitor`
VALUES (1685422787465838624, '192.168.22.1', 'provider-log', 1607344409741, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607344602340);
INSERT INTO `sys_log_monitor`
VALUES (1685422832718184480, '192.168.22.1', 'consumer-web', 1607344455822, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607344625235);
INSERT INTO `sys_log_monitor`
VALUES (1685422973711810592, '192.168.22.1', 'provider-user', 1607344554302, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607347530099);
INSERT INTO `sys_log_monitor`
VALUES (1685422973751656480, '192.168.22.1', 'provider-log', 1607344589213, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607347530218);
INSERT INTO `sys_log_monitor`
VALUES (1685422997756706848, '192.168.22.1', 'consumer-web', 1607344612332, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607346141916);
INSERT INTO `sys_log_monitor`
VALUES (1685424588108857376, '192.168.22.1', 'consumer-web', 1607346130759, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607346304657);
INSERT INTO `sys_log_monitor`
VALUES (1685424758760407072, '192.168.22.1', 'consumer-web', 1607346288935, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607346480742);
INSERT INTO `sys_log_monitor`
VALUES (1685424943409397792, '192.168.22.1', 'consumer-web', 1607346467756, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607346660085);
INSERT INTO `sys_log_monitor`
VALUES (1685425131448434720, '192.168.22.1', 'consumer-web', 1607346648273, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607346832039);
INSERT INTO `sys_log_monitor`
VALUES (1685425311755272224, '192.168.22.1', 'consumer-web', 1607346821130, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607347230246);
INSERT INTO `sys_log_monitor`
VALUES (1685425917074079776, '192.168.22.1', 'consumer-web', 1607347398270, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607347739230);
INSERT INTO `sys_log_monitor`
VALUES (1685464364831735840, '192.168.22.1', 'provider-user', 1607384040182, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607385039214);
INSERT INTO `sys_log_monitor`
VALUES (1685464364889407520, '192.168.22.1', 'provider-log', 1607384064021, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607385270933);
INSERT INTO `sys_log_monitor`
VALUES (1685464391472906272, '192.168.22.1', 'consumer-web', 1607384089897, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607384770046);
INSERT INTO `sys_log_monitor`
VALUES (1685464613031772192, '192.168.22.1', 'provider-distribute', 1607384306185, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607385330202);
INSERT INTO `sys_log_monitor`
VALUES (1685465092638900256, '192.168.22.1', 'consumer-web', 1607384757861, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607385314179);
INSERT INTO `sys_log_monitor`
VALUES (1685465374884102176, '192.168.22.1', 'provider-user', 1607385031529, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607385270882);
INSERT INTO `sys_log_monitor`
VALUES (1685465617818189856, '192.168.22.1', 'provider-user', 1607385252255, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607385930169);
INSERT INTO `sys_log_monitor`
VALUES (1685465617860132896, '192.168.22.1', 'provider-log', 1607385257200, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607385930129);
INSERT INTO `sys_log_monitor`
VALUES (1685465663207899168, '192.168.22.1', 'consumer-web', 1607385301516, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607385930195);
INSERT INTO `sys_log_monitor`
VALUES (1685466342328631328, '192.168.22.1', 'provider-user', 1607385923821, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607388930119);
INSERT INTO `sys_log_monitor`
VALUES (1685466342385254432, '192.168.22.1', 'provider-log', 1607385948475, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607388930269);
INSERT INTO `sys_log_monitor`
VALUES (1685466364693708832, '192.168.22.1', 'consumer-web', 1607385970965, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607388930154);
INSERT INTO `sys_log_monitor`
VALUES (1685466778937851936, '192.168.22.1', 'provider-distribute', 1607386371388, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607388930077);
INSERT INTO `sys_log_monitor`
VALUES (1685472362507534368, '192.168.22.1', 'provider-user', 1607391672511, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607392830184);
INSERT INTO `sys_log_monitor`
VALUES (1685472362550525984, '192.168.22.1', 'provider-log', 1607391691618, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607392830337);
INSERT INTO `sys_log_monitor`
VALUES (1685472393440526368, '192.168.22.1', 'consumer-web', 1607391721859, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607392830220);
INSERT INTO `sys_log_monitor`
VALUES (1685473294110687264, '192.168.22.1', 'provider-distribute', 1607392580524, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607392921457);
INSERT INTO `sys_log_monitor`
VALUES (1685476654632140832, '192.168.22.1', 'provider-user', 1607395769014, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607396430116);
INSERT INTO `sys_log_monitor`
VALUES (1685476654668840992, '192.168.22.1', 'provider-log', 1607395784451, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607403030150);
INSERT INTO `sys_log_monitor`
VALUES (1685476759780196384, '192.168.22.1', 'consumer-web', 1607395886438, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607403030098);
INSERT INTO `sys_log_monitor`
VALUES (1685476777114206240, '192.168.22.1', 'provider-distribute', 1607395907234, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607403030132);
INSERT INTO `sys_log_monitor`
VALUES (1685592826209894432, '192.168.22.1', 'provider-user', 1607506552227, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607507430222);
INSERT INTO `sys_log_monitor`
VALUES (1685592826314752032, '192.168.22.1', 'provider-log', 1607506573653, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607507430387);
INSERT INTO `sys_log_monitor`
VALUES (1685592898376040480, '192.168.22.1', 'consumer-web', 1607506641537, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607507430183);
INSERT INTO `sys_log_monitor`
VALUES (1685592980463812640, '192.168.22.1', 'provider-distribute', 1607506726704, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607507430138);
INSERT INTO `sys_log_monitor`
VALUES (1685592997438160928, '192.168.22.1', 'provider-software', 1607506741235, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607507430144);
INSERT INTO `sys_log_monitor`
VALUES (1685593002266853408, '192.168.22.1', 'provider-mongo', 1607506746474, 511.9999990463257, 512, 512, 2.4375,
        -0.00000095367431640625, 1607507430185);

-- ----------------------------
-- Table structure for sys_log_monitor_interface_call
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_monitor_interface_call`;
CREATE TABLE `sys_log_monitor_interface_call`
(
    `id`             bigint(0)                                                    NOT NULL,
    `fid`            bigint(0)                                                    NULL DEFAULT NULL COMMENT '主表id',
    `interface_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口名称',
    `method_name`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名称',
    `run_time`       bigint(0)                                                    NULL DEFAULT NULL COMMENT '方法执行时间',
    `success`        bit(1)                                                       NULL DEFAULT NULL COMMENT '是否成功',
    `time`           bigint(0)                                                    NULL DEFAULT NULL COMMENT '这一条发送时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'JVM日志接口调用表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_monitor_interface_call
-- ----------------------------
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399204106600480, 1685399144890368032, 'BlackListServiceImpl', 'getAllIpBlackList', 9, b'1', 1607321933839);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399459172712480, 1685399144861007904, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607322177093);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399460880842784, 1685399144861007904, 'MenuServiceImpl', 'getIndexMenu', 34, b'1', 1607322178723);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399461744869408, 1685399144890368032, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 38, b'1',
        1607322179548);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399461813026848, 1685399144861007904, 'MenuServiceImpl', 'getQuickStartResponse', 12, b'1', 1607322179613);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399461851824160, 1685399144890368032, 'JvmServiceImpl', 'getJvmInfoLogResponse', 24, b'1', 1607322179649);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399461910544416, 1685399144861007904, 'DictServiceImpl', 'getVersionInfoResponse', 9, b'1', 1607322179704);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399461930467360, 1685399144861007904, 'DictServiceImpl', 'getLastPlanResponse', 7, b'1', 1607322179724);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399461948293152, 1685399239944831008, 'DistributeServiceImpl', 'getWelcomeData', 370, b'1', 1607322179740);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399775988416544, 1685399144890368032, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 24, b'1',
        1607322479232);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399818926555168, 1685399144861007904, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607322520181);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399819181359136, 1685399144861007904, 'MenuServiceImpl', 'getIndexMenu', 20, b'1', 1607322520425);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399819669995552, 1685399144890368032, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 21, b'1',
        1607322520892);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399819679432736, 1685399144861007904, 'MenuServiceImpl', 'getQuickStartResponse', 4, b'1', 1607322520902);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399819707744288, 1685399144890368032, 'JvmServiceImpl', 'getJvmInfoLogResponse', 21, b'1', 1607322520926);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399819720327200, 1685399144861007904, 'DictServiceImpl', 'getVersionInfoResponse', 6, b'1', 1607322520941);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399819732910112, 1685399144861007904, 'DictServiceImpl', 'getLastPlanResponse', 9, b'1', 1607322520953);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685399819737104416, 1685399239944831008, 'DistributeServiceImpl', 'getWelcomeData', 85, b'1', 1607322520954);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400012006096928, 1685399144861007904, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607322704315);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400012256706592, 1685399144861007904, 'MenuServiceImpl', 'getIndexMenu', 21, b'1', 1607322704557);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400012770508832, 1685399144890368032, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 23, b'1',
        1607322705046);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400012783091744, 1685399144861007904, 'MenuServiceImpl', 'getQuickStartResponse', 5, b'1', 1607322705058);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400012809306144, 1685399144890368032, 'JvmServiceImpl', 'getJvmInfoLogResponse', 20, b'1', 1607322705082);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400012819791904, 1685399144861007904, 'DictServiceImpl', 'getVersionInfoResponse', 5, b'1', 1607322705094);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400012830277664, 1685399144861007904, 'DictServiceImpl', 'getLastPlanResponse', 6, b'1', 1607322705104);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400012835520544, 1685399239944831008, 'DistributeServiceImpl', 'getWelcomeData', 86, b'1', 1607322705106);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400020218544160, 1685399144861007904, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607322712145);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400020576108576, 1685399144861007904, 'MenuServiceImpl', 'getIndexMenu', 19, b'1', 1607322712491);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400021039579168, 1685399144890368032, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 27, b'1',
        1607322712931);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400021051113504, 1685399144861007904, 'MenuServiceImpl', 'getQuickStartResponse', 5, b'1', 1607322712944);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400021081522208, 1685399144890368032, 'JvmServiceImpl', 'getJvmInfoLogResponse', 23, b'1', 1607322712972);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400021093056544, 1685399144861007904, 'DictServiceImpl', 'getVersionInfoResponse', 5, b'1', 1607322712982);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400021100396576, 1685399144861007904, 'DictServiceImpl', 'getLastPlanResponse', 4, b'1', 1607322712992);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400021109833760, 1685399239944831008, 'DistributeServiceImpl', 'getWelcomeData', 92, b'1', 1607322712993);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400137782788128, 1685399144861007904, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607322824266);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400247731224608, 1685399144861007904, 'MenuServiceImpl', 'getIndexMenu', 104544, b'1', 1607322929121);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400248192598048, 1685399144890368032, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 20, b'1',
        1607322929563);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400248204132384, 1685399144861007904, 'MenuServiceImpl', 'getQuickStartResponse', 6, b'1', 1607322929574);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400248226152480, 1685399144890368032, 'JvmServiceImpl', 'getJvmInfoLogResponse', 18, b'1', 1607322929596);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400248239783968, 1685399144861007904, 'DictServiceImpl', 'getVersionInfoResponse', 7, b'1', 1607322929608);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400248247124000, 1685399144861007904, 'DictServiceImpl', 'getLastPlanResponse', 4, b'1', 1607322929615);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400248256561184, 1685399239944831008, 'DistributeServiceImpl', 'getWelcomeData', 74, b'1', 1607322929615);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400294128615456, 1685399144861007904, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607322973370);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400320454164512, 1685399144861007904, 'MenuServiceImpl', 'getIndexMenu', 24812, b'1', 1607322998476);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400320904003616, 1685399144890368032, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 18, b'1',
        1607322998905);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400320915537952, 1685399144861007904, 'MenuServiceImpl', 'getQuickStartResponse', 6, b'1', 1607322998915);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400320937558048, 1685399144890368032, 'JvmServiceImpl', 'getJvmInfoLogResponse', 17, b'1', 1607322998938);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400320950140960, 1685399144861007904, 'DictServiceImpl', 'getVersionInfoResponse', 6, b'1', 1607322998949);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400320954335264, 1685399144861007904, 'DictServiceImpl', 'getLastPlanResponse', 3, b'1', 1607322998956);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400320960626720, 1685399239944831008, 'DistributeServiceImpl', 'getWelcomeData', 72, b'1', 1607322998956);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400324974575648, 1685399144861007904, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607323002788);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400454257704992, 1685399144861007904, 'MenuServiceImpl', 'getIndexMenu', 122947, b'1', 1607323126078);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400454727467040, 1685399144890368032, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 19, b'1',
        1607323126531);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400454741098528, 1685399144861007904, 'MenuServiceImpl', 'getQuickStartResponse', 10, b'1', 1607323126544);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400454767312928, 1685399144890368032, 'JvmServiceImpl', 'getJvmInfoLogResponse', 21, b'1', 1607323126568);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400454776750112, 1685399144861007904, 'DictServiceImpl', 'getVersionInfoResponse', 4, b'1', 1607323126577);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400454781992992, 1685399144861007904, 'DictServiceImpl', 'getLastPlanResponse', 3, b'1', 1607323126583);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400454788284448, 1685399239944831008, 'DistributeServiceImpl', 'getWelcomeData', 74, b'1', 1607323126584);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400714547822624, 1685400657407770656, 'BlackListServiceImpl', 'getAllIpBlackList', 8, b'1', 1607323374307);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400816432709664, 1685400657373167648, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607323471475);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400823856627744, 1685400657373167648, 'MenuServiceImpl', 'getIndexMenu', 6823, b'1', 1607323478550);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400829182345248, 1685400657373167648, 'UserServiceImpl', 'getUserByToken', 1, b'1', 1607323483636);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400904523579424, 1685400657373167648, 'MenuServiceImpl', 'getIndexMenu', 71531, b'1', 1607323555485);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400961660485664, 1685400657373167648, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607323609964);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685400961957232672, 1685400657373167648, 'MenuServiceImpl', 'getIndexMenu', 31, b'1', 1607323610262);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685401022720114720, 1685400657373167648, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607323668193);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685401023080824864, 1685400657373167648, 'MenuServiceImpl', 'getIndexMenu', 25, b'1', 1607323668553);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685401042778325024, 1685400657373167648, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607323687328);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685401042959728672, 1685400657373167648, 'MenuServiceImpl', 'getIndexMenu', 13, b'1', 1607323687510);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685401150489100320, 1685400657373167648, 'UserServiceImpl', 'getUserByToken', 1, b'1', 1607323790056);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685401150845616160, 1685400657373167648, 'MenuServiceImpl', 'getIndexMenu', 33, b'1', 1607323790398);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685401301788131360, 1685401237623668768, 'BlackListServiceImpl', 'getAllIpBlackList', 11, b'1', 1607323934340);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685401367277994016, 1685401237590114336, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607323996802);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685401367600955424, 1685401237590114336, 'MenuServiceImpl', 'getIndexMenu', 43, b'1', 1607323997113);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685401644627394592, 1685401631190941728, 'BlackListServiceImpl', 'getAllIpBlackList', 9, b'1', 1607324261299);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685401687355818016, 1685401631166824480, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607324302049);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685401687674585120, 1685401631166824480, 'MenuServiceImpl', 'getIndexMenu', 43, b'1', 1607324302357);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685402002615435296, 1685401631190941728, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 19, b'1',
        1607324602710);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685402053701009440, 1685401984091291680, 'UserServiceImpl', 'getUserByToken', 5, b'1', 1607324651410);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685402054856540192, 1685401984091291680, 'MenuServiceImpl', 'getIndexMenu', 807, b'1', 1607324652529);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421033735061536, 1685421010146295840, 'BlackListServiceImpl', 'getAllIpBlackList', 10, b'1', 1607342752194);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421334531670048, 1685421010120081440, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607343039060);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421335128309792, 1685421010120081440, 'MenuServiceImpl', 'getIndexMenu', 52, b'1', 1607343039631);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421350235144224, 1685421010120081440, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607343054037);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421350562299936, 1685421010120081440, 'MenuServiceImpl', 'getIndexMenu', 20, b'1', 1607343054348);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421491795001376, 1685421010120081440, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607343189031);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421492070776864, 1685421010120081440, 'MenuServiceImpl', 'getIndexMenu', 32, b'1', 1607343189301);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421492775419936, 1685421010146295840, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 32, b'1',
        1607343189973);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421492865597472, 1685421010120081440, 'MenuServiceImpl', 'getQuickStartResponse', 13, b'1', 1607343190062);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421492889714720, 1685421010146295840, 'JvmServiceImpl', 'getJvmInfoLogResponse', 14, b'1', 1607343190085);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421492943192096, 1685421010120081440, 'DictServiceImpl', 'getVersionInfoResponse', 6, b'1', 1607343190136);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421492961017888, 1685421010120081440, 'DictServiceImpl', 'getLastPlanResponse', 4, b'1', 1607343190152);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421492981989408, 1685421436264513568, 'DistributeServiceImpl', 'getWelcomeData', 414, b'1', 1607343190168);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421500020031520, 1685421010120081440, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607343196882);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421500315729952, 1685421010120081440, 'MenuServiceImpl', 'getIndexMenu', 24, b'1', 1607343197165);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421500706848800, 1685421010146295840, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 13, b'1',
        1607343197538);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421500723626016, 1685421010120081440, 'MenuServiceImpl', 'getQuickStartResponse', 8, b'1', 1607343197553);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421500740403232, 1685421010146295840, 'JvmServiceImpl', 'getJvmInfoLogResponse', 13, b'1', 1607343197572);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421500749840416, 1685421010120081440, 'DictServiceImpl', 'getVersionInfoResponse', 4, b'1', 1607343197582);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421500758229024, 1685421010120081440, 'DictServiceImpl', 'getLastPlanResponse', 3, b'1', 1607343197588);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421500765569056, 1685421436264513568, 'DistributeServiceImpl', 'getWelcomeData', 70, b'1', 1607343197591);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421803093098528, 1685421010120081440, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607343485917);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421803351048224, 1685421010120081440, 'MenuServiceImpl', 'getIndexMenu', 17, b'1', 1607343486165);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421803852267552, 1685421010146295840, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 11, b'1',
        1607343486643);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421803860656160, 1685421010120081440, 'MenuServiceImpl', 'getQuickStartResponse', 3, b'1', 1607343486649);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421803873239072, 1685421010146295840, 'JvmServiceImpl', 'getJvmInfoLogResponse', 8, b'1', 1607343486663);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421803883724832, 1685421010120081440, 'DictServiceImpl', 'getVersionInfoResponse', 6, b'1', 1607343486672);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421803888967712, 1685421010120081440, 'DictServiceImpl', 'getLastPlanResponse', 3, b'1', 1607343486678);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685421803894210592, 1685421436264513568, 'DistributeServiceImpl', 'getWelcomeData', 49, b'1', 1607343486679);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422094151581728, 1685421010120081440, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607343763492);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422094511243296, 1685421010120081440, 'MenuServiceImpl', 'getIndexMenu', 18, b'1', 1607343763836);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422095006171168, 1685421010146295840, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 13, b'1',
        1607343764308);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422095018754080, 1685421010120081440, 'MenuServiceImpl', 'getQuickStartResponse', 6, b'1', 1607343764320);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422095035531296, 1685421010146295840, 'JvmServiceImpl', 'getJvmInfoLogResponse', 11, b'1', 1607343764336);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422095044968480, 1685421010120081440, 'DictServiceImpl', 'getVersionInfoResponse', 4, b'1', 1607343764345);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422095055454240, 1685421010120081440, 'DictServiceImpl', 'getLastPlanResponse', 6, b'1', 1607343764355);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422095060697120, 1685421436264513568, 'DistributeServiceImpl', 'getWelcomeData', 64, b'1', 1607343764357);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422211315269664, 1685421010120081440, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607343875229);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422211523936288, 1685421010120081440, 'MenuServiceImpl', 'getIndexMenu', 11, b'1', 1607343875429);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422211945463840, 1685421010146295840, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 22, b'1',
        1607343875830);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422211953852448, 1685421010120081440, 'MenuServiceImpl', 'getQuickStartResponse', 4, b'1', 1607343875838);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422211969581088, 1685421010146295840, 'JvmServiceImpl', 'getJvmInfoLogResponse', 11, b'1', 1607343875854);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422211984261152, 1685421010120081440, 'DictServiceImpl', 'getVersionInfoResponse', 5, b'1', 1607343875865);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422211997892640, 1685421010120081440, 'DictServiceImpl', 'getLastPlanResponse', 6, b'1', 1607343875878);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422212008378400, 1685421436264513568, 'DistributeServiceImpl', 'getWelcomeData', 74, b'1', 1607343875880);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422242638331936, 1685421010120081440, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607343905102);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422242823929888, 1685421010120081440, 'MenuServiceImpl', 'getIndexMenu', 13, b'1', 1607343905278);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422243107045408, 1685421010146295840, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 12, b'1',
        1607343905549);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422243116482592, 1685421010120081440, 'MenuServiceImpl', 'getQuickStartResponse', 4, b'1', 1607343905555);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422243126968352, 1685421010146295840, 'JvmServiceImpl', 'getJvmInfoLogResponse', 7, b'1', 1607343905568);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422243138502688, 1685421010120081440, 'DictServiceImpl', 'getVersionInfoResponse', 5, b'1', 1607343905576);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422243143745568, 1685421010120081440, 'DictServiceImpl', 'getLastPlanResponse', 3, b'1', 1607343905584);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422243150037024, 1685421436264513568, 'DistributeServiceImpl', 'getWelcomeData', 49, b'1', 1607343905585);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422249686859808, 1685421010120081440, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607343911821);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422249903915040, 1685421010120081440, 'MenuServiceImpl', 'getIndexMenu', 14, b'1', 1607343912029);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422250231070752, 1685421010146295840, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 11, b'1',
        1607343912340);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422250239459360, 1685421010120081440, 'MenuServiceImpl', 'getQuickStartResponse', 4, b'1', 1607343912351);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422250254139424, 1685421010146295840, 'JvmServiceImpl', 'getJvmInfoLogResponse', 11, b'1', 1607343912365);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422250263576608, 1685421010120081440, 'DictServiceImpl', 'getVersionInfoResponse', 3, b'1', 1607343912372);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422250271965216, 1685421010120081440, 'DictServiceImpl', 'getLastPlanResponse', 4, b'1', 1607343912382);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422250280353824, 1685421436264513568, 'DistributeServiceImpl', 'getWelcomeData', 56, b'1', 1607343912384);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422340792385568, 1685421010120081440, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607343998709);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422340970643488, 1685421010120081440, 'MenuServiceImpl', 'getIndexMenu', 13, b'1', 1607343998878);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422341277876256, 1685421010146295840, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 14, b'1',
        1607343999172);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422341284167712, 1685421010120081440, 'MenuServiceImpl', 'getQuickStartResponse', 3, b'1', 1607343999178);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422341298847776, 1685421010146295840, 'JvmServiceImpl', 'getJvmInfoLogResponse', 11, b'1', 1607343999192);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422432133840928, 1685421010120081440, 'DictServiceImpl', 'getVersionInfoResponse', 86618, b'1',
        1607344085814);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422434104115232, 1685421010120081440, 'DictServiceImpl', 'getVersionInfoResponse', 47159, b'1',
        1607344087692);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422434112503840, 1685421010120081440, 'DictServiceImpl', 'getVersionInfoResponse', 47162, b'1',
        1607344087696);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422445069074464, 1685421010120081440, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607344098149);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422445400424480, 1685421010120081440, 'MenuServiceImpl', 'getIndexMenu', 14, b'1', 1607344098471);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422445702414368, 1685421010146295840, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 9, b'1',
        1607344098756);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422445710802976, 1685421010120081440, 'MenuServiceImpl', 'getQuickStartResponse', 4, b'1', 1607344098767);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422445724434464, 1685421010146295840, 'JvmServiceImpl', 'getJvmInfoLogResponse', 9, b'1', 1607344098779);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422456078073888, 1685421010120081440, 'DictServiceImpl', 'getVersionInfoResponse', 6, b'1', 1607344108647);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422456087511072, 1685421010120081440, 'DictServiceImpl', 'getLastPlanResponse', 5, b'1', 1607344108662);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422456096948256, 1685421436264513568, 'DistributeServiceImpl', 'getWelcomeData', 9919, b'1', 1607344108663);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422508144066592, 1685421010120081440, 'DictServiceImpl', 'getVersionInfoResponse', 59514, b'1',
        1607344158298);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422530972614688, 1685421010120081440, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607344180078);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422531231612960, 1685421010120081440, 'MenuServiceImpl', 'getIndexMenu', 13, b'1', 1607344180326);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422531450765344, 1685421010146295840, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 10, b'1',
        1607344180534);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422531461251104, 1685421010120081440, 'MenuServiceImpl', 'getQuickStartResponse', 4, b'1', 1607344180544);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422531473834016, 1685421010146295840, 'JvmServiceImpl', 'getJvmInfoLogResponse', 10, b'1', 1607344180557);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422541292699680, 1685421010120081440, 'DictServiceImpl', 'getVersionInfoResponse', 11, b'1', 1607344189921);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422541297942560, 1685421010120081440, 'DictServiceImpl', 'getLastPlanResponse', 1, b'1', 1607344189926);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422541303186464, 1685421436264513568, 'DistributeServiceImpl', 'getWelcomeData', 9407, b'1', 1607344189928);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422643000377376, 1685421010120081440, 'DictServiceImpl', 'getVersionInfoResponse', 106345, b'1',
        1607344286905);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422831254372384, 1685422787465838624, 'BlackListServiceImpl', 'getAllIpBlackList', 9, b'1', 1607344466442);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422859736842272, 1685422787357835296, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607344493608);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422860045123616, 1685422787357835296, 'MenuServiceImpl', 'getIndexMenu', 41, b'1', 1607344493904);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685422996292894752, 1685422973751656480, 'BlackListServiceImpl', 'getAllIpBlackList', 9, b'1', 1607344623834);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685423013820891168, 1685422973711810592, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607344640550);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685423014221447200, 1685422973711810592, 'MenuServiceImpl', 'getIndexMenu', 46, b'1', 1607344640936);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685423329246183456, 1685422973751656480, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 28, b'1',
        1607344941369);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685423337485893664, 1685422973711810592, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607344949229);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685423337652617248, 1685422973711810592, 'MenuServiceImpl', 'getIndexMenu', 18, b'1', 1607344949387);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685423653458542624, 1685422973751656480, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 14, b'1',
        1607345250562);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685423892815937568, 1685422973711810592, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607345478820);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685423892950155296, 1685422973711810592, 'MenuServiceImpl', 'getIndexMenu', 15, b'1', 1607345478959);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685424207834382368, 1685422973751656480, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 17, b'1',
        1607345779256);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685424522726998048, 1685422973751656480, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 14, b'1',
        1607346079560);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685424586780311584, 1685422973751656480, 'BlackListServiceImpl', 'getAllIpBlackList', 1, b'1', 1607346140648);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685424756971536416, 1685422973751656480, 'BlackListServiceImpl', 'getAllIpBlackList', 2, b'1', 1607346302955);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685424808599224352, 1685422973711810592, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607346352193);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685424809729589280, 1685422973711810592, 'MenuServiceImpl', 'getIndexMenu', 12, b'1', 1607346353270);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685424941945585696, 1685422973751656480, 'BlackListServiceImpl', 'getAllIpBlackList', 1, b'1', 1607346479361);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685425130012934176, 1685422973751656480, 'BlackListServiceImpl', 'getAllIpBlackList', 1, b'1', 1607346658716);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685425181840900128, 1685422973711810592, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607346708143);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685425182177493024, 1685422973711810592, 'MenuServiceImpl', 'getIndexMenu', 12, b'1', 1607346708465);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685425203993116704, 1685422973711810592, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607346729268);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685425204262600736, 1685422973711810592, 'MenuServiceImpl', 'getIndexMenu', 17, b'1', 1607346729526);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685425310444552224, 1685422973751656480, 'BlackListServiceImpl', 'getAllIpBlackList', 1, b'1', 1607346830790);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685425915741339680, 1685422973751656480, 'BlackListServiceImpl', 'getAllIpBlackList', 1, b'1', 1607347408046);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685425924069130272, 1685422973711810592, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607347415986);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685425924593418272, 1685422973711810592, 'MenuServiceImpl', 'getIndexMenu', 19, b'1', 1607347416486);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685425974024339488, 1685422973711810592, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607347463628);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685425974203645984, 1685422973711810592, 'MenuServiceImpl', 'getIndexMenu', 13, b'1', 1607347463800);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464390032162848, 1685464364889407520, 'BlackListServiceImpl', 'getAllIpBlackList', 17, b'1', 1607384099980);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464594765578272, 1685464364831735840, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607384295234);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464595410452512, 1685464364831735840, 'MenuServiceImpl', 'getIndexMenu', 131, b'1', 1607384295850);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464681527902240, 1685464364831735840, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607384377978);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464682065821728, 1685464364831735840, 'MenuServiceImpl', 'getIndexMenu', 16, b'1', 1607384378492);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464682773610528, 1685464364889407520, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 25, b'1',
        1607384379168);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464682866933792, 1685464364831735840, 'MenuServiceImpl', 'getQuickStartResponse', 38, b'1', 1607384379257);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464682894196768, 1685464364889407520, 'JvmServiceImpl', 'getJvmInfoLogResponse', 11, b'1', 1607384379280);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464682943479840, 1685464364831735840, 'DictServiceImpl', 'getVersionInfoResponse', 13, b'1', 1607384379328);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464682966548512, 1685464364831735840, 'DictServiceImpl', 'getLastPlanResponse', 8, b'1', 1607384379351);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464682985422880, 1685464613031772192, 'DistributeServiceImpl', 'getWelcomeData', 352, b'1', 1607384379363);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464828021309472, 1685464364831735840, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607384517678);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464828471148576, 1685464364831735840, 'MenuServiceImpl', 'getIndexMenu', 14, b'1', 1607384518116);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464828937764896, 1685464364889407520, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 14, b'1',
        1607384518560);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464828947202080, 1685464364831735840, 'MenuServiceImpl', 'getQuickStartResponse', 4, b'1', 1607384518568);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464828962930720, 1685464364889407520, 'JvmServiceImpl', 'getJvmInfoLogResponse', 9, b'1', 1607384518583);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464828973416480, 1685464364831735840, 'DictServiceImpl', 'getVersionInfoResponse', 6, b'1', 1607384518595);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464828982853664, 1685464364831735840, 'DictServiceImpl', 'getLastPlanResponse', 4, b'1', 1607384518602);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685464828988096544, 1685464613031772192, 'DistributeServiceImpl', 'getWelcomeData', 61, b'1', 1607384518605);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465091220176928, 1685464364889407520, 'BlackListServiceImpl', 'getAllIpBlackList', 2, b'1', 1607384768694);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465125577818144, 1685464364831735840, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607384801460);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465125969985568, 1685464364831735840, 'MenuServiceImpl', 'getIndexMenu', 14, b'1', 1607384801819);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465126316015648, 1685464364889407520, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 15, b'1',
        1607384802164);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465126332792864, 1685464364831735840, 'MenuServiceImpl', 'getQuickStartResponse', 3, b'1', 1607384802179);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465126346424352, 1685464364889407520, 'JvmServiceImpl', 'getJvmInfoLogResponse', 10, b'1', 1607384802193);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465126359007264, 1685464364831735840, 'DictServiceImpl', 'getVersionInfoResponse', 6, b'1', 1607384802205);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465126367395872, 1685464364831735840, 'DictServiceImpl', 'getLastPlanResponse', 3, b'1', 1607384802212);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465126376833056, 1685464613031772192, 'DistributeServiceImpl', 'getWelcomeData', 67, b'1', 1607384802214);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465133834305568, 1685464364831735840, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607384809333);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465134290436128, 1685464364831735840, 'MenuServiceImpl', 'getIndexMenu', 17, b'1', 1607384809766);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465134597668896, 1685464364889407520, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 15, b'1',
        1607384810062);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465134608154656, 1685464364831735840, 'MenuServiceImpl', 'getQuickStartResponse', 6, b'1', 1607384810071);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465134623883296, 1685464364889407520, 'JvmServiceImpl', 'getJvmInfoLogResponse', 10, b'1', 1607384810085);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465134634369056, 1685464364831735840, 'DictServiceImpl', 'getVersionInfoResponse', 7, b'1', 1607384810097);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465134645903392, 1685464364831735840, 'DictServiceImpl', 'getLastPlanResponse', 6, b'1', 1607384810107);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465134651146272, 1685464613031772192, 'DistributeServiceImpl', 'getWelcomeData', 63, b'1', 1607384810109);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465285591564320, 1685464364831735840, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607384954058);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465285982683168, 1685464364831735840, 'MenuServiceImpl', 'getIndexMenu', 14, b'1', 1607384954433);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465286326616096, 1685464364889407520, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 13, b'1',
        1607384954761);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465397537538080, 1685465374884102176, 'UserServiceImpl', 'getUserByToken', 8, b'1', 1607385060817);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465398495936544, 1685465374884102176, 'MenuServiceImpl', 'getIndexMenu', 577, b'1', 1607385061733);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465399102013472, 1685464364889407520, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 13, b'1',
        1607385062311);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465399126130720, 1685465374884102176, 'MenuServiceImpl', 'getQuickStartResponse', 15, b'1', 1607385062334);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465399148150816, 1685464364889407520, 'JvmServiceImpl', 'getJvmInfoLogResponse', 11, b'1', 1607385062355);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465399162830880, 1685465374884102176, 'DictServiceImpl', 'getVersionInfoResponse', 10, b'1', 1607385062369);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465399171219488, 1685465374884102176, 'DictServiceImpl', 'getLastPlanResponse', 4, b'1', 1607385062379);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465399177510944, 1685464613031772192, 'DistributeServiceImpl', 'getWelcomeData', 87, b'1', 1607385062383);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465661716824096, 1685465617860132896, 'BlackListServiceImpl', 'getAllIpBlackList', 7, b'1', 1607385312755);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465691344338976, 1685465617818189856, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607385341011);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465708403621920, 1685465617818189856, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607385357283);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465772861685792, 1685465617818189856, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607385418755);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465773132218400, 1685465617818189856, 'MenuServiceImpl', 'getIndexMenu', 26, b'1', 1607385419014);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465786938818592, 1685465617818189856, 'DictServiceImpl', 'getAllMenuIcon', 13, b'1', 1607385432170);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465802801676320, 1685465617818189856, 'UserServiceImpl', 'getUserByToken', 1, b'1', 1607385447309);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465803159240736, 1685465617818189856, 'MenuServiceImpl', 'getIndexMenu', 23, b'1', 1607385447650);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465854049779744, 1685465617818189856, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607385496170);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685465854366449696, 1685465617818189856, 'MenuServiceImpl', 'getIndexMenu', 18, b'1', 1607385496482);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685466052965695520, 1685465617818189856, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607385685872);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685466053124030496, 1685465617818189856, 'MenuServiceImpl', 'getIndexMenu', 13, b'1', 1607385686036);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685466363171176480, 1685466342385254432, 'BlackListServiceImpl', 'getAllIpBlackList', 10, b'1', 1607385981711);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685466740867203104, 1685466342328631328, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607386341914);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685466741215330336, 1685466342328631328, 'MenuServiceImpl', 'getIndexMenu', 45, b'1', 1607386342249);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685467056062857248, 1685466342385254432, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 19, b'1',
        1607386642513);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685467370713251872, 1685466342385254432, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 28, b'1',
        1607386942581);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685467685200068640, 1685466342385254432, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 16, b'1',
        1607387242505);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685467999778111520, 1685466342385254432, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 14, b'1',
        1607387542510);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685468314345668640, 1685466342385254432, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 13, b'1',
        1607387842504);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685468628914274336, 1685466342385254432, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 11, b'1',
        1607388142502);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685468943490220064, 1685466342385254432, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 9, b'1',
        1607388442505);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685469259005689888, 1685466342385254432, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 10, b'1',
        1607388743404);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685472392186429472, 1685472362550525984, 'BlackListServiceImpl', 'getAllIpBlackList', 7, b'1', 1607391731429);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685473253596856352, 1685472362507534368, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607392552938);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685473271767629856, 1685472362507534368, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607392570260);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685473273629900832, 1685472362507534368, 'MenuServiceImpl', 'getIndexMenu', 44, b'1', 1607392572047);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685473348472012832, 1685472362550525984, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 28, b'1',
        1607392643419);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685473401776373792, 1685472362507534368, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607392694122);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685473403830534176, 1685472362507534368, 'MenuServiceImpl', 'getIndexMenu', 307, b'1', 1607392696108);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685476758441164832, 1685476654668840992, 'BlackListServiceImpl', 'getAllIpBlackList', 9, b'1', 1607395895416);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685477189884051488, 1685476654632140832, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607396306872);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685477190317113376, 1685476654632140832, 'MenuServiceImpl', 'getIndexMenu', 51, b'1', 1607396307289);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685477191354155040, 1685476654668840992, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 40, b'1',
        1607396308275);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685477234801901600, 1685476654632140832, 'UserServiceImpl', 'getUserByToken', 2, b'1', 1607396349680);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685477244688924704, 1685476654632140832, 'MenuServiceImpl', 'getIndexMenu', 8211, b'1', 1607396359133);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685477245040197664, 1685476654668840992, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 17, b'1',
        1607396359478);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685477559580491808, 1685476654668840992, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 8, b'1',
        1607396659447);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685477874169020448, 1685476654668840992, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 13, b'1',
        1607396959463);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685478188736577568, 1685476654668840992, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 13, b'1',
        1607397259459);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685478503331397664, 1685476654668840992, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 15, b'1',
        1607397559479);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685478817881128992, 1685476654668840992, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 8, b'1',
        1607397859455);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685479132459171872, 1685476654668840992, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 9, b'1',
        1607398159463);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685479447026728992, 1685476654668840992, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 8, b'1',
        1607398459457);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685479762592530464, 1685476654668840992, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 10, b'1',
        1607398760404);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685592896976191520, 1685592826314752032, 'BlackListServiceImpl', 'getAllIpBlackList', 32, b'1', 1607506653750);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593107825950752, 1685592826209894432, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607506854840);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593109323317280, 1685592826209894432, 'MenuServiceImpl', 'getIndexMenu', 107, b'1', 1607506856267);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593110323658784, 1685592826314752032, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 31, b'1',
        1607506857221);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593392761798688, 1685592826209894432, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607507126574);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593393047011360, 1685592826209894432, 'MenuServiceImpl', 'getIndexMenu', 26, b'1', 1607507126848);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593393399332896, 1685592826314752032, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 22, b'1',
        1607507127182);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593397019017248, 1685592826209894432, 'MenuServiceImpl', 'getQuickStartResponse', 3450, b'1',
        1607507130639);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593397066203168, 1685592826314752032, 'JvmServiceImpl', 'getJvmInfoLogResponse', 18, b'1', 1607507130681);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593397119680544, 1685592826209894432, 'DictServiceImpl', 'getVersionInfoResponse', 4, b'1', 1607507130731);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593397144846368, 1685592826209894432, 'DictServiceImpl', 'getLastPlanResponse', 11, b'1', 1607507130758);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593397167915040, 1685592980463812640, 'DistributeServiceImpl', 'getWelcomeData', 3607, b'1', 1607507130776);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593414855295008, 1685592826209894432, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607507147646);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593415096467488, 1685592826209894432, 'MenuServiceImpl', 'getIndexMenu', 22, b'1', 1607507147875);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593415471857696, 1685592826314752032, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 24, b'1',
        1607507148235);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593415525335072, 1685592826209894432, 'MenuServiceImpl', 'getQuickStartResponse', 48, b'1', 1607507148289);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593415548403744, 1685592826314752032, 'JvmServiceImpl', 'getJvmInfoLogResponse', 14, b'1', 1607507148308);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593415563083808, 1685592826209894432, 'DictServiceImpl', 'getVersionInfoResponse', 9, b'1', 1607507148322);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593415575666720, 1685592826209894432, 'DictServiceImpl', 'getLastPlanResponse', 6, b'1', 1607507148333);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593415589298208, 1685592980463812640, 'DistributeServiceImpl', 'getWelcomeData', 129, b'1', 1607507148337);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593490319212576, 1685592826209894432, 'UserServiceImpl', 'getUserByToken', 1, b'1', 1607507219616);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593490615959584, 1685592826209894432, 'MenuServiceImpl', 'getIndexMenu', 19, b'1', 1607507219899);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593490882297888, 1685592826314752032, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 19, b'1',
        1607507220153);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593490912706592, 1685592826209894432, 'MenuServiceImpl', 'getQuickStartResponse', 23, b'1', 1607507220182);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593490937872416, 1685592826314752032, 'JvmServiceImpl', 'getJvmInfoLogResponse', 19, b'1', 1607507220206);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593490951503904, 1685592826209894432, 'DictServiceImpl', 'getVersionInfoResponse', 5, b'1', 1607507220219);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593490968281120, 1685592826209894432, 'DictServiceImpl', 'getLastPlanResponse', 7, b'1', 1607507220234);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593490977718304, 1685592980463812640, 'DistributeServiceImpl', 'getWelcomeData', 107, b'1', 1607507220237);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593497479938080, 1685592826209894432, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607507226442);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593497750470688, 1685592826209894432, 'MenuServiceImpl', 'getIndexMenu', 23, b'1', 1607507226703);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593498234912800, 1685592826314752032, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 17, b'1',
        1607507227165);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593498255884320, 1685592826209894432, 'MenuServiceImpl', 'getQuickStartResponse', 17, b'1', 1607507227185);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593498281050144, 1685592826314752032, 'JvmServiceImpl', 'getJvmInfoLogResponse', 17, b'1', 1607507227207);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593498290487328, 1685592826209894432, 'DictServiceImpl', 'getVersionInfoResponse', 4, b'1', 1607507227218);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593498303070240, 1685592826209894432, 'DictServiceImpl', 'getLastPlanResponse', 6, b'1', 1607507227228);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593498308313120, 1685592980463812640, 'DistributeServiceImpl', 'getWelcomeData', 86, b'1', 1607507227232);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593506934947872, 1685592826209894432, 'UserServiceImpl', 'getUserByToken', 0, b'1', 1607507235461);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593507283075104, 1685592826209894432, 'MenuServiceImpl', 'getIndexMenu', 20, b'1', 1607507235792);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593507642736672, 1685592826314752032, 'JvmServiceImpl', 'getJvmDataStatisticsResponse', 22, b'1',
        1607507236136);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593507674193952, 1685592826209894432, 'MenuServiceImpl', 'getQuickStartResponse', 27, b'1', 1607507236167);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593507702505504, 1685592826314752032, 'JvmServiceImpl', 'getJvmInfoLogResponse', 19, b'1', 1607507236191);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593507715088416, 1685592826209894432, 'DictServiceImpl', 'getVersionInfoResponse', 6, b'1', 1607507236205);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593507727671328, 1685592826209894432, 'DictServiceImpl', 'getLastPlanResponse', 5, b'1', 1607507236216);
INSERT INTO `sys_log_monitor_interface_call`
VALUES (1685593507737108512, 1685592980463812640, 'DistributeServiceImpl', 'getWelcomeData', 108, b'1', 1607507236219);

-- ----------------------------
-- Table structure for sys_log_monitor_jvm_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_monitor_jvm_status`;
CREATE TABLE `sys_log_monitor_jvm_status`
(
    `id`              bigint(0) NOT NULL,
    `fid`             bigint(0) NULL DEFAULT NULL COMMENT '主表id',
    `time`            bigint(0) NULL DEFAULT NULL COMMENT 'status生成时间',
    `heap_use_mem`    double    NULL DEFAULT NULL COMMENT '堆 使用内存',
    `no_heap_use_mem` double    NULL DEFAULT NULL COMMENT '非堆区使用内存',
    `use_mem`         double    NULL DEFAULT NULL COMMENT '总使用内存',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'JVM状态子表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_monitor_jvm_status
-- ----------------------------
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398126811152416, NULL, 1607320800148, 35.763038635253906, 65.43405151367188, 101.19709014892578);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398126831075360, 1685398126819541024, 1607317574587, 24.09778594970703, 48.38633728027344,
        72.48412322998047);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398126855192608, 1685398126849949728, 1607317598256, 36.480438232421875, 66.70138549804688,
        103.18182373046875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398126878261280, 1685398126873018400, 1607320796809, 24.57921600341797, 48.492286682128906,
        73.07150268554688);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398126897135648, 1685398126892941344, 1607320904442, 37.575767517089844, 66.6903076171875,
        104.26607513427734);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398234083622944, 1685398234071040032, 1607320975620, 24.573890686035156, 48.503013610839844,
        73.076904296875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398234110885920, 1685398234102497312, 1607321006743, 37.93116760253906, 66.68272399902344,
        104.6138916015625);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398327691051040, 1685398327677419552, 1607321076154, 24.086074829101562, 48.43223571777344,
        72.518310546875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398327717265440, 1685398327712022560, 1607321095979, 36.37676239013672, 66.66979217529297,
        103.04655456542969);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398329969606688, 1685398327677419552, 1607321100110, 38.43034362792969, 83.05901336669922,
        121.4893569946289);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398329982189600, 1685398327712022560, 1607321100202, 34.511077880859375, 81.7400131225586,
        116.25109100341797);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398846458298400, 1685398846446764064, 1607321565821, 24.10059356689453, 48.53228759765625,
        72.63288116455078);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398846481367072, 1685398846476124192, 1607321590417, 37.614501953125, 66.65326690673828,
        104.26776885986328);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398931030147104, 1685398931018612768, 1607321648604, 24.389480590820312, 48.47388458251953,
        72.86336517333984);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685398931060555808, 1685398931053215776, 1607321670916, 36.59418487548828, 66.60877990722656,
        103.20296478271484);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399049558032416, 1685399049546498080, 1607321740597, 24.106491088867188, 48.498008728027344,
        72.60449981689453);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399049585295392, 1685399049577955360, 1607321784167, 37.329803466796875, 66.68798065185547,
        104.01778411865234);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399144872542240, 1685399144861007904, 1607321831401, 24.118743896484375, 48.505332946777344,
        72.62407684326172);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399144894562336, 1685399144890368032, 1607321875316, 37.83464813232422, 66.65719604492188,
        104.4918441772461);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399205425709088, 1685399205421514784, 1607321935005, 36.600074768066406, 65.54083251953125,
        102.14090728759766);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399233948024864, 1685399233939636256, 1607321962140, 25.272903442382812, 48.599334716796875,
        73.87223815917969);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399239950073888, 1685399239944831008, 1607321967840, 25.187538146972656, 48.56505584716797,
        73.75259399414062);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399247054176288, 1685399247045787680, 1607321974549, 25.09040069580078, 48.703643798828125,
        73.7940444946289);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399254409936928, 1685399254404694048, 1607321981677, 25.175559997558594, 49.06792449951172,
        74.24348449707031);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399260320759840, 1685399260314468384, 1607321987323, 30.515533447265625, 54.44580078125, 84.96133422851562);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399273742532640, 1685399205421514784, 1607322000233, 29.465309143066406, 72.93440246582031,
        102.39971160888672);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399273931276320, 1685399254404694048, 1607322000239, 34.999549865722656, 75.33170318603516,
        110.33125305175781);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399273942810656, 1685399260314468384, 1607322000254, 35.1988525390625, 81.19927978515625,
        116.39813232421875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399273953296416, 1685399233939636256, 1607322000276, 35.28504180908203, 69.11831665039062,
        104.40335845947266);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399273962733600, 1685399239944831008, 1607322000278, 34.477210998535156, 68.32938385009766,
        102.80659484863281);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399273970073632, 1685399144861007904, 1607322000281, 36.24287414550781, 78.16619110107422,
        114.40906524658203);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399273978462240, 1685399247045787680, 1607322000289, 34.12040710449219, 68.72157287597656,
        102.84197998046875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399273987899424, 1685399144890368032, 1607322000443, 34.084747314453125, 84.47582244873047,
        118.5605697631836);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399287476781088, 1685399287472586784, 1607322013246, 24.009613037109375, 48.52647399902344,
        72.53608703613281);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399588486250528, 1685399233939636256, 1607322300274, 28.157081604003906, 69.76988220214844,
        97.92696380615234);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399588500930592, 1685399144890368032, 1607322300308, 34.77625274658203, 87.6119613647461,
        122.38821411132812);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399588510367776, 1685399260314468384, 1607322300321, 34.067848205566406, 82.83171081542969,
        116.8995590209961);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399588525047840, 1685399254404694048, 1607322300368, 33.773643493652344, 77.30799865722656,
        111.0816421508789);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399588540776480, 1685399247045787680, 1607322300376, 33.048744201660156, 74.18142700195312,
        107.23017120361328);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399588560699424, 1685399239944831008, 1607322300376, 30.071075439453125, 73.08403015136719,
        103.15510559082031);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399588574330912, 1685399205421514784, 1607322300378, 33.63172912597656, 78.32511901855469,
        111.95684814453125);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399588584816672, 1685399144861007904, 1607322300412, 40.68278503417969, 88.05363464355469,
        128.73641967773438);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399588594253856, 1685399287472586784, 1607322300505, 37.42950439453125, 72.07299041748047,
        109.50249481201172);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399903034933280, 1685399233939636256, 1607322600222, 28.31786346435547, 70.03529357910156,
        98.35315704345703);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399903042273312, 1685399287472586784, 1607322600230, 28.95128631591797, 72.49242401123047,
        101.44371032714844);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399903053807648, 1685399254404694048, 1607322600270, 34.890228271484375, 77.53207397460938,
        112.42230224609375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399903059050528, 1685399239944831008, 1607322600288, 27.501556396484375, 73.55054473876953,
        101.0521011352539);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399903070584864, 1685399247045787680, 1607322600296, 29.217811584472656, 74.53877258300781,
        103.75658416748047);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399903077924896, 1685399260314468384, 1607322600300, 34.59307098388672, 83.49260711669922,
        118.08567810058594);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399903087362080, 1685399144861007904, 1607322600350, 36.76544952392578, 88.98165893554688,
        125.74710845947266);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399903094702112, 1685399205421514784, 1607322600378, 32.17443084716797, 79.37313842773438,
        111.54756927490234);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685399903105187872, 1685399144890368032, 1607322600389, 32.11631774902344, 88.9283218383789,
        121.04463958740234);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400217418989600, 1685399233939636256, 1607322900186, 25.731903076171875, 70.18508911132812, 95.9169921875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400217449398304, 1685399287472586784, 1607322900211, 26.16802978515625, 72.67241668701172,
        98.84044647216797);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400217458835488, 1685399247045787680, 1607322900211, 26.355743408203125, 74.55477905273438,
        100.9105224609375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400217467224096, 1685399239944831008, 1607322900217, 27.756393432617188, 74.24877166748047,
        102.00516510009766);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400217475612704, 1685399260314468384, 1607322900245, 31.304412841796875, 83.20429229736328,
        114.50870513916016);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400217489244192, 1685399254404694048, 1607322900251, 31.565162658691406, 77.14785766601562,
        108.71302032470703);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400217498681376, 1685399205421514784, 1607322900291, 32.403114318847656, 80.74698638916016,
        113.15010070800781);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400220812181536, 1685399144861007904, 1607322903445, 34.264244079589844, 89.3305435180664,
        123.59478759765625);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400220984148000, 1685399144890368032, 1607322903613, 32.12999725341797, 90.00475311279297,
        122.13475036621094);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400553882910752, 1685400553864036384, 1607323207656, 25.217132568359375, 48.64765930175781,
        73.86479187011719);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400553919610912, 1685400553912270880, 1607323214647, 35.085235595703125, 63.520599365234375,
        98.6058349609375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400657384701984, 1685400657373167648, 1607323248334, 24.355186462402344, 48.495033264160156,
        72.8502197265625);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400657414062112, 1685400657407770656, 1607323317732, 37.90362548828125, 66.6297607421875,
        104.53338623046875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400715470569504, 1685400715466375200, 1607323375098, 36.45134735107422, 65.48222351074219,
        101.9335708618164);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400846535229472, 1685400715466375200, 1607323500161, 32.98035430908203, 78.00885772705078,
        110.98921203613281);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400846546763808, 1685400657407770656, 1607323500188, 34.34873962402344, 85.06687927246094,
        119.41561889648438);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685400852524695584, 1685400657373167648, 1607323505898, 46.308311462402344, 86.69815826416016,
        133.0064697265625);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685401161144729632, 1685400657407770656, 1607323800186, 33.784889221191406, 87.76083374023438,
        121.54572296142578);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685401237604794400, 1685401237590114336, 1607323818357, 24.092117309570312, 48.519317626953125,
        72.61143493652344);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685401237628911648, 1685401237623668768, 1607323870614, 37.484779357910156, 66.7187728881836,
        104.20355224609375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685401302902767648, 1685401302895427616, 1607323935278, 35.57611083984375, 65.53114318847656,
        101.10725402832031);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685401631160533024, NULL, 1607324100112, 31.986534118652344, 74.118896484375, 106.10543060302734);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685401631173115936, 1685401631166824480, 1607324075433, 24.572792053222656, 48.53839111328125,
        73.1111831665039);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685401631196184608, 1685401631190941728, 1607324246388, 37.93041229248047, 66.63517761230469,
        104.56558990478516);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685401645738885152, 1685401645732593696, 1607324262235, 36.60993194580078, 65.49993133544922, 102.10986328125);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685401790256775200, 1685401645732593696, 1607324400175, 33.345848083496094, 78.44076538085938,
        111.78661346435547);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685401790272503840, 1685401631190941728, 1607324400190, 34.36420440673828, 85.09998321533203,
        119.46418762207031);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685401790282989600, 1685401631166824480, 1607324400192, 41.42144775390625, 86.93407440185547,
        128.35552215576172);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685401984097583136, 1685401984091291680, 1607324584873, 24.097213745117188, 48.49229431152344,
        72.58950805664062);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421010112741408, NULL, 1607342700110, 33.05049133300781, 74.40143585205078, 107.4519271850586);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421010126372896, 1685421010120081440, 1607342638372, 24.11298370361328, 48.495887756347656,
        72.60887145996094);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421010150490144, 1685421010146295840, 1607342727273, 37.91741180419922, 66.69931030273438,
        104.6167221069336);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421035105550368, 1685421035100307488, 1607342753393, 37.47278594970703, 65.52420043945312,
        102.99698638916016);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421293717946400, 1685421010120081440, 1607343000130, 33.882591247558594, 79.26787567138672,
        113.15046691894531);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421293728432160, 1685421010146295840, 1607343000137, 34.24455261230469, 85.42735290527344,
        119.67190551757812);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421293746257952, 1685421035100307488, 1607343000143, 29.57660675048828, 73.3619155883789,
        102.93852233886719);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421436267659296, 1685421436264513568, 1607343135954, 24.20978546142578, 48.42250061035156,
        72.63228607177734);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421608395604000, 1685421436264513568, 1607343300187, 30.041793823242188, 72.71688079833984,
        102.75867462158203);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421608409235488, 1685421010146295840, 1607343300221, 32.09453582763672, 88.3265609741211,
        120.42109680175781);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421608419721248, 1685421035100307488, 1607343300238, 32.07311248779297, 80.0509033203125,
        112.12401580810547);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421608430207008, 1685421010120081440, 1607343300247, 41.31353759765625, 89.43392944335938,
        130.74746704101562);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421922864594976, 1685421436264513568, 1607343600140, 30.221824645996094, 73.37035369873047,
        103.59217834472656);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421922879275040, 1685421010120081440, 1607343600149, 34.42320251464844, 89.87600708007812,
        124.29920959472656);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421923034464288, 1685421035100307488, 1607343600167, 32.2386474609375, 80.90325927734375,
        113.14190673828125);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685421923043901472, 1685421010146295840, 1607343600299, 32.119529724121094, 89.3797607421875,
        121.4992904663086);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422237458366496, 1685421035100307488, 1607343900151, 32.715431213378906, 82.08786010742188,
        114.80329132080078);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422237607264288, 1685421010120081440, 1607343900166, 34.758392333984375, 90.54690551757812,
        125.3052978515625);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422237618798624, 1685421436264513568, 1607343900198, 27.78600311279297, 73.87513732910156,
        101.66114044189453);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422237629284384, 1685421010146295840, 1607343900298, 32.35436248779297, 90.27031707763672,
        122.62467956542969);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422551992369184, 1685421035100307488, 1607344200124, 33.18330383300781, 83.28448486328125,
        116.46778869628906);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422552039555104, 1685421436264513568, 1607344200144, 28.005821228027344, 74.98920440673828,
        102.99502563476562);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422557258317856, 1685421010146295840, 1607344205146, 32.224281311035156, 91.15797424316406,
        123.38225555419922);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422642996183072, 1685421010120081440, 1607344286905, 34.02427673339844, 91.19831085205078,
        125.22258758544922);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422787369369632, 1685422787357835296, 1607344397413, 24.577178955078125, 48.4696044921875,
        73.04678344726562);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422787473178656, 1685422787465838624, 1607344422167, 36.532127380371094, 66.7508544921875,
        103.2829818725586);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422832725524512, 1685422832718184480, 1607344467737, 36.63609313964844, 65.49141693115234,
        102.12751007080078);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422866774884384, 1685422787357835296, 1607344500176, 39.82707977294922, 85.7713623046875,
        125.59844207763672);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422866789564448, 1685422832718184480, 1607344500179, 35.12077331542969, 77.2608871459961,
        112.38166046142578);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422866807390240, 1685422787465838624, 1607344500316, 35.19230651855469, 84.48350524902344,
        119.67581176757812);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422973725442080, 1685422973711810592, 1607344562025, 25.60558319091797, 48.5267333984375,
        74.13231658935547);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422973757947936, 1685422973751656480, 1607344599879, 37.92932891845703, 66.7391586303711,
        104.66848754882812);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685422997760901152, 1685422997756706848, 1607344625114, 36.60137939453125, 65.4163589477539,
        102.01773834228516);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685423181192495136, 1685422997756706848, 1607344800174, 33.25148010253906, 78.77410888671875,
        112.02558898925781);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685423184410574880, 1685422973751656480, 1607344803241, 34.26612091064453, 85.23866271972656,
        119.5047836303711);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685423186298011680, 1685422973711810592, 1607344805039, 47.079872131347656, 86.5045394897461,
        133.58441162109375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685423495740129312, 1685422973711810592, 1607345100130, 36.62371063232422, 88.88048553466797,
        125.50419616699219);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685423495755857952, 1685422973751656480, 1607345100145, 31.649681091308594, 87.24686431884766,
        118.89654541015625);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685423495764246560, 1685422997756706848, 1607345100166, 34.802818298339844, 81.002197265625,
        115.80501556396484);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685423810287763488, 1685422997756706848, 1607345400120, 32.46991729736328, 80.75857543945312,
        113.2284927368164);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685423810299297824, 1685422973751656480, 1607345400123, 31.913497924804688, 88.6563949584961,
        120.56989288330078);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685423810307686432, 1685422973711810592, 1607345400142, 34.33013153076172, 89.38046264648438,
        123.7105941772461);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685424124993732640, 1685422973711810592, 1607345700129, 34.151878356933594, 89.94438171386719,
        124.09626007080078);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685424125003169824, 1685422997756706848, 1607345700145, 32.479209899902344, 81.51769256591797,
        113.99690246582031);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685424125010509856, 1685422973751656480, 1607345700252, 31.862319946289062, 89.72884368896484,
        121.5911636352539);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685424439545561120, 1685422973711810592, 1607346000127, 33.47930908203125, 90.12760925292969,
        123.60691833496094);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685424439582261280, 1685422997756706848, 1607346000132, 32.05145263671875, 81.67591857910156,
        113.72737121582031);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685424439604281376, 1685422973751656480, 1607346000231, 31.641639709472656, 90.24948120117188,
        121.89112091064453);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685424588130877472, 1685424588108857376, 1607346141821, 36.46985626220703, 65.46286010742188,
        101.9327163696289);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685424754023989280, 1685422973711810592, 1607346300145, 34.23248291015625, 90.27156066894531,
        124.50404357910156);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685424754169741344, 1685422973751656480, 1607346300277, 32.15269470214844, 90.58899688720703,
        122.74169158935547);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685424758763552800, 1685424758760407072, 1607346304526, 36.77252197265625, 65.71595001220703,
        102.48847198486328);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685424943413592096, 1685424943409397792, 1607346480636, 36.61329650878906, 65.51654052734375,
        102.12983703613281);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685425068589449248, 1685422973711810592, 1607346600125, 34.553123474121094, 90.54035949707031,
        125.0934829711914);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685425068594692128, 1685422973751656480, 1607346600136, 32.49268341064453, 90.85102081298828,
        123.34370422363281);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685425131452629024, 1685425131448434720, 1607346659971, 35.608604431152344, 65.36149597167969,
        100.97010040283203);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685425311758417952, 1685425311755272224, 1607346831947, 36.60772705078125, 65.37983703613281,
        101.98756408691406);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685425383214678048, 1685422973711810592, 1607346900179, 35.654808044433594, 90.80142211914062,
        126.45623016357422);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685425383228309536, 1685422973751656480, 1607346900182, 33.62945556640625, 91.7523193359375,
        125.38177490234375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685425383277592608, 1685425311755272224, 1607346900246, 34.53203582763672, 78.5484390258789,
        113.08047485351562);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685425697693106208, 1685422973711810592, 1607347200099, 34.99115753173828, 90.98340606689453,
        125.97456359863281);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685425697825226784, 1685422973751656480, 1607347200218, 33.078277587890625, 91.87591552734375,
        124.95419311523438);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685425917077225504, 1685425917074079776, 1607347409230, 36.69683074951172, 65.47856903076172,
        102.17539978027344);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685464364841173024, 1685464364831735840, 1607384054421, 24.347198486328125, 48.483924865722656,
        72.83112335205078);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685464364895698976, 1685464364889407520, 1607384073852, 37.95402526855469, 66.69145202636719,
        104.64547729492188);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685464390274383904, 1685464364831735840, 1607384100119, 35.170562744140625, 76.87081909179688,
        112.0413818359375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685464390288015392, 1685464364889407520, 1607384100219, 34.32279968261719, 83.03671264648438,
        117.35951232910156);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685464391477100576, 1685464391472906272, 1607384101277, 36.70793151855469, 65.57032012939453,
        102.27825164794922);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685464613035966496, 1685464613031772192, 1607384312570, 25.237632751464844, 48.47882080078125,
        73.7164535522461);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685464705108279328, 1685464364831735840, 1607384400250, 40.533470153808594, 87.8833236694336,
        128.4167938232422);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685464705116667936, 1685464613031772192, 1607384400289, 31.008682250976562, 72.29949188232422,
        103.30817413330078);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685464705141833760, 1685464391472906272, 1607384400341, 33.74951171875, 78.53528594970703, 112.28479766845703);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685464705149173792, 1685464364889407520, 1607384400457, 34.70664978027344, 86.70216369628906,
        121.4088134765625);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465019315126304, 1685464613031772192, 1607384700118, 30.17615509033203, 73.38970947265625,
        103.56586456298828);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465019573076000, 1685464364831735840, 1607384700169, 37.34667205810547, 90.22575378417969,
        127.57242584228516);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465019585658912, 1685464391472906272, 1607384700183, 32.28875732421875, 80.873779296875,
        113.16253662109375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465019594047520, 1685464364889407520, 1607384700368, 32.133872985839844, 88.6470718383789,
        120.78094482421875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465092647288864, 1685465092638900256, 1607384769941, 37.674102783203125, 65.5057144165039,
        103.17981719970703);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465334001172512, 1685464364831735840, 1607385000182, 34.25486755371094, 90.6621322631836,
        124.91699981689453);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465334018998304, 1685464613031772192, 1607385000202, 27.929588317871094, 74.15703582763672,
        102.08662414550781);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465334025289760, 1685464364889407520, 1607385000225, 32.18489074707031, 89.78717041015625,
        121.97206115722656);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465334034726944, 1685465092638900256, 1607385000225, 34.08997344970703, 79.95185852050781,
        114.04183197021484);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465374890393632, 1685465374884102176, 1607385039115, 25.572357177734375, 48.533966064453125,
        74.1063232421875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465617836015648, 1685465617818189856, 1607385260241, 24.104530334472656, 48.470672607421875,
        72.57520294189453);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465617864327200, 1685465617860132896, 1607385267112, 33.868988037109375, 63.359458923339844,
        97.22844696044922);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465648670441504, 1685465617818189856, 1607385300154, 35.08740997314453, 76.73613739013672,
        111.82354736328125);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465648686170144, 1685465617860132896, 1607385300310, 34.54492950439453, 82.34029388427734,
        116.88522338867188);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465663213142048, 1685465663207899168, 1607385314071, 36.693397521972656, 65.52043914794922,
        102.21383666992188);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465963056594976, 1685465617860132896, 1607385600129, 34.447654724121094, 86.6801986694336,
        121.12785339355469);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465963090149408, 1685465617818189856, 1607385600169, 40.484107971191406, 88.70122528076172,
        129.18533325195312);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685465963116363808, 1685465663207899168, 1607385600195, 34.49732971191406, 81.49678802490234,
        115.9941177368164);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685466342365331488, 1685466342328631328, 1607385930557, 24.12194061279297, 48.396766662597656,
        72.51870727539062);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685466342395740192, 1685466342385254432, 1607385959625, 38.81364440917969, 66.67669677734375,
        105.49034118652344);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685466364698951712, 1685466364693708832, 1607385983057, 36.551597595214844, 65.62428283691406,
        102.1758804321289);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685466592203243552, 1685466364693708832, 1607386200132, 31.618316650390625, 76.75613403320312,
        108.37445068359375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685466592368918560, 1685466342328631328, 1607386200168, 35.56282043457031, 78.78677368164062,
        114.34959411621094);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685466592379404320, 1685466342385254432, 1607386200299, 34.16014862060547, 85.32782745361328,
        119.48797607421875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685466778944143392, 1685466778937851936, 1607386378124, 25.17828369140625, 48.371192932128906,
        73.54947662353516);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685466906886144032, 1685466342328631328, 1607386500144, 40.93623352050781, 88.82950592041016,
        129.76573944091797);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685466906893484064, 1685466778937851936, 1607386500147, 34.43440246582031, 69.21106719970703,
        103.64546966552734);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685466906908164128, 1685466364693708832, 1607386500165, 31.48218536376953, 78.89753723144531,
        110.37972259521484);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685466906913407008, 1685466342385254432, 1607386500237, 31.558258056640625, 87.43441772460938,
        118.99267578125);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685467221380300832, 1685466778937851936, 1607386800118, 28.07166290283203, 69.54005432128906,
        97.6117172241211);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685467221392883744, 1685466342385254432, 1607386800159, 31.65886688232422, 88.43260955810547,
        120.09147644042969);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685467221406515232, 1685466342328631328, 1607386800176, 33.89183044433594, 88.90386962890625,
        122.79570007324219);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685467221413855264, 1685466364693708832, 1607386800197, 31.68536376953125, 79.5362548828125,
        111.22161865234375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685467535947857952, 1685466778937851936, 1607387100120, 28.160690307617188, 69.8182373046875,
        97.97892761230469);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685467535961489440, 1685466342385254432, 1607387100154, 31.93872833251953, 89.52113342285156,
        121.4598617553711);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685467536084172832, 1685466342328631328, 1607387100177, 34.1395263671875, 89.25270080566406,
        123.39222717285156);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685467536089415712, 1685466364693708832, 1607387100210, 31.978622436523438, 79.77227783203125,
        111.75090026855469);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685467850480812064, 1685466778937851936, 1607387400114, 25.496726989746094, 69.6893081665039, 95.18603515625);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685467850495492128, 1685466364693708832, 1607387400127, 31.55065155029297, 79.90106201171875,
        111.45171356201172);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685467850648584224, 1685466342328631328, 1607387400156, 33.337303161621094, 89.49330139160156,
        122.83060455322266);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685467850656972832, 1685466342385254432, 1607387400282, 31.684494018554688, 90.08171844482422,
        121.7662124633789);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685468165115478048, 1685466778937851936, 1607387700107, 25.396240234375, 69.95865631103516, 95.35489654541016);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685468165142741024, 1685466342328631328, 1607387700141, 33.89348602294922, 89.65498352050781,
        123.54846954345703);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685468165147983904, 1685466364693708832, 1607387700183, 31.807334899902344, 80.01099395751953,
        111.81832885742188);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685468165153226784, 1685466342385254432, 1607387700187, 31.960189819335938, 90.42957305908203,
        122.38976287841797);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685468479645286432, 1685466778937851936, 1607388000110, 25.46808624267578, 69.99759674072266,
        95.46568298339844);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685468479655772192, 1685466342385254432, 1607388000148, 32.12049102783203, 90.7278060913086,
        122.84829711914062);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685468479676743712, 1685466342328631328, 1607388000151, 34.11640167236328, 89.89418029785156,
        124.01058197021484);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685468479684083744, 1685466364693708832, 1607388000181, 32.174217224121094, 80.1584243774414,
        112.3326416015625);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685468794180337696, 1685466778937851936, 1607388300110, 25.460113525390625, 70.3731460571289,
        95.83325958251953);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685468794200260640, 1685466364693708832, 1607388300131, 32.10467529296875, 80.27782440185547,
        112.38249969482422);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685468794218086432, 1685466342328631328, 1607388300148, 33.286338806152344, 89.98092651367188,
        123.26726531982422);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685468794390052896, 1685466342385254432, 1607388300305, 31.646163940429688, 90.86194610595703,
        122.50811004638672);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685469108718534688, 1685466778937851936, 1607388600077, 25.53644561767578, 70.5525894165039,
        96.08903503417969);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685469108762574880, 1685466342328631328, 1607388600119, 33.82007598876953, 90.07168579101562,
        123.89176177978516);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685469108919861280, 1685466364693708832, 1607388600154, 32.47624969482422, 80.42340850830078,
        112.899658203125);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685469108959707168, 1685466342385254432, 1607388600269, 31.950714111328125, 91.08058166503906,
        123.03129577636719);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685472362516971552, 1685472362507534368, 1607391678328, 25.592430114746094, 48.458091735839844,
        74.05052185058594);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685472362557866016, 1685472362550525984, 1607391701242, 37.45276641845703, 66.67938232421875,
        104.13214874267578);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685472393444720672, 1685472393440526368, 1607391732545, 35.68201446533203, 65.51009368896484,
        101.19210815429688);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685472569062326304, 1685472393440526368, 1607391900115, 29.514793395996094, 73.08329010009766,
        102.59808349609375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685472569233244192, 1685472362507534368, 1607391900151, 35.53528594970703, 78.83802795410156,
        114.3733139038086);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685472569294061600, 1685472362550525984, 1607391900282, 35.138397216796875, 84.96996307373047,
        120.10836029052734);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685472883671826464, 1685472362507534368, 1607392200122, 34.20832824707031, 80.20368957519531,
        114.41201782226562);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685472883785072672, 1685472362550525984, 1607392200145, 31.574417114257812, 87.00865173339844,
        118.58306884765625);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685472883793461280, 1685472393440526368, 1607392200147, 31.807289123535156, 77.02989959716797,
        108.83718872070312);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685473198445953056, 1685472362507534368, 1607392500184, 29.994171142578125, 80.42074584960938,
        110.4149169921875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685473198482653216, 1685472393440526368, 1607392500220, 30.498558044433594, 77.55416870117188,
        108.05272674560547);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685473198503624736, 1685472362550525984, 1607392500337, 31.705734252929688, 88.00261688232422,
        119.7083511352539);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685473294115930144, 1685473294110687264, 1607392591457, 25.17607879638672, 48.41307830810547,
        73.58915710449219);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685476654642626592, 1685476654632140832, 1607395774898, 25.58319854736328, 48.48040008544922,
        74.0635986328125);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685476654674083872, 1685476654668840992, 1607395794424, 37.57080841064453, 66.66820526123047,
        104.239013671875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685476658517114912, 1685476654668840992, 1607395800122, 34.54877471923828, 81.80642700195312,
        116.3552017211914);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685476658531794976, 1685476654632140832, 1607395800129, 35.02632141113281, 76.2963638305664,
        111.32268524169922);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685476759785439264, 1685476759780196384, 1607395896609, 36.516090393066406, 65.63079071044922,
        102.14688110351562);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685476777118400544, 1685476777114206240, 1607395913145, 24.948875427246094, 48.38929748535156,
        73.33817291259766);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685476973254541344, 1685476654632140832, 1607396100116, 32.78565216064453, 79.57916259765625,
        112.36481475830078);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685476973263978528, 1685476777114206240, 1607396100155, 34.445823669433594, 68.42523193359375,
        102.87105560302734);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685476973271318560, 1685476759780196384, 1607396100164, 32.20745849609375, 77.03047943115234,
        109.2379379272461);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685476973282852896, 1685476654668840992, 1607396100272, 34.378204345703125, 85.7074966430664,
        120.08570098876953);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685477287691026464, 1685476759780196384, 1607396400140, 31.675949096679688, 79.03205871582031, 110.7080078125);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685477287699415072, 1685476777114206240, 1607396400148, 27.473770141601562, 71.94342041015625,
        99.41719055175781);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685477290901766176, 1685476654668840992, 1607396403207, 32.037193298339844, 87.94690704345703,
        119.98410034179688);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685477602283749408, 1685476777114206240, 1607396700114, 27.402610778808594, 72.74600982666016,
        100.14862060546875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685477602294235168, 1685476759780196384, 1607396700142, 31.89714813232422, 79.80811309814453,
        111.70526123046875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685477602300526624, 1685476654668840992, 1607396700168, 32.10014343261719, 88.65474700927734,
        120.75489044189453);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685477916833480736, 1685476777114206240, 1607397000108, 27.55803680419922, 73.43767547607422,
        100.99571228027344);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685477916850257952, 1685476654668840992, 1607397000144, 32.33702850341797, 89.44722747802734,
        121.78425598144531);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685477916857597984, 1685476759780196384, 1607397000162, 32.42057800292969, 79.93375396728516,
        112.35433197021484);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685478231399989280, 1685476777114206240, 1607397300125, 27.467926025390625, 73.67650604248047,
        101.1444320678711);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685478231409426464, 1685476759780196384, 1607397300127, 31.993270874023438, 80.1669692993164,
        112.16024017333984);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685478231417815072, 1685476654668840992, 1607397300147, 32.207969665527344, 89.93730163574219,
        122.14527130126953);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685478545972789280, 1685476777114206240, 1607397600105, 27.70984649658203, 73.75589752197266,
        101.46574401855469);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685478545986420768, 1685476654668840992, 1607397600145, 32.489585876464844, 90.17384338378906,
        122.6634292602539);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685478545991663648, 1685476759780196384, 1607397600155, 32.394012451171875, 80.3720932006836,
        112.76610565185547);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685478860515180576, 1685476777114206240, 1607397900088, 27.567352294921875, 73.85054016113281,
        101.41789245605469);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685478860522520608, 1685476654668840992, 1607397900117, 32.39234924316406, 90.31159973144531,
        122.70394897460938);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685478860548735008, 1685476759780196384, 1607397900149, 32.37168884277344, 80.6105728149414,
        112.98226165771484);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685479175098466336, 1685476777114206240, 1607398200097, 27.627197265625, 73.95790100097656,
        101.58509826660156);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685479175113146400, 1685476654668840992, 1607398200128, 32.13648986816406, 90.53775024414062,
        122.67424011230469);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685479175121535008, 1685476759780196384, 1607398200145, 32.63948059082031, 80.90287017822266,
        113.54235076904297);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685479489702723616, 1685476777114206240, 1607398500115, 27.639122009277344, 74.05033874511719,
        101.68946075439453);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685479489710063648, 1685476759780196384, 1607398500150, 32.379066467285156, 80.99732208251953,
        113.37638854980469);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685479489718452256, 1685476654668840992, 1607398500158, 32.38886260986328, 90.72523498535156,
        123.11409759521484);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685479804246163488, 1685476777114206240, 1607398800094, 27.508995056152344, 74.15498352050781,
        101.66397857666016);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685479804252454944, 1685476759780196384, 1607398800125, 32.26652526855469, 81.19749450683594,
        113.46401977539062);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685479804259794976, 1685476654668840992, 1607398800129, 32.27650451660156, 90.78184509277344,
        123.058349609375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685480118826303520, 1685476654668840992, 1607399100134, 32.60973358154297, 90.80491638183594,
        123.4146499633789);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685480118839935008, 1685476759780196384, 1607399100141, 32.669090270996094, 81.25419616699219,
        113.92328643798828);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685480118858809376, 1685476777114206240, 1607399100157, 27.76549530029297, 74.18711853027344,
        101.9526138305664);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685480433423220768, 1685476777114206240, 1607399400118, 27.512474060058594, 74.22636413574219,
        101.73883819580078);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685480433433706528, 1685476654668840992, 1607399400159, 32.17628479003906, 90.82469177246094, 123.0009765625);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685480433444192288, 1685476759780196384, 1607399400165, 32.103477478027344, 81.30766296386719,
        113.41114044189453);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685480747963514912, 1685476777114206240, 1607399700114, 27.507827758789062, 74.28672790527344,
        101.7945556640625);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685480747979243552, 1685476654668840992, 1607399700128, 32.16765594482422, 90.88456726074219,
        123.0522232055664);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685480747996020768, 1685476759780196384, 1607399700156, 32.450584411621094, 81.39945983886719,
        113.85004425048828);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685481062552043552, 1685476777114206240, 1607400000117, 27.575218200683594, 74.37940979003906,
        101.95462799072266);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685481062561480736, 1685476654668840992, 1607400000138, 32.41985321044922, 90.93484497070312,
        123.35469818115234);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685481062574063648, 1685476759780196384, 1607400000164, 32.6151123046875, 81.46507263183594,
        114.08018493652344);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685481377096532000, 1685476759780196384, 1607400300098, 32.142303466796875, 81.4935073852539,
        113.63581085205078);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685481377100726304, 1685476777114206240, 1607400300099, 27.54938507080078, 74.46096801757812,
        102.0103530883789);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685481377105969184, 1685476654668840992, 1607400300113, 32.109771728515625, 90.95794677734375,
        123.06771850585938);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685481691679817760, 1685476759780196384, 1607400600106, 32.4296875, 81.59146881103516, 114.02115631103516);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685481691686109216, 1685476777114206240, 1607400600109, 27.77483367919922, 74.4920654296875,
        102.26689910888672);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685481691691352096, 1685476654668840992, 1607400600127, 32.35077667236328, 90.97924041748047,
        123.33001708984375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685482006255763488, 1685476759780196384, 1607400900097, 32.153076171875, 81.64313507080078,
        113.79621124267578);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685482006264152096, 1685476777114206240, 1607400900123, 27.60010528564453, 74.58365631103516,
        102.18376159667969);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685482006271492128, 1685476654668840992, 1607400900127, 32.2349853515625, 91.09845733642578,
        123.33344268798828);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685482320841146400, 1685476759780196384, 1607401200087, 32.42400360107422, 81.7386245727539,
        114.16262817382812);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685482320848486432, 1685476777114206240, 1607401200131, 27.622337341308594, 74.62540435791016,
        102.24774169921875);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685482320853729312, 1685476654668840992, 1607401200141, 32.03294372558594, 91.1386489868164,
        123.17159271240234);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685482635384586272, 1685476759780196384, 1607401500084, 32.178489685058594, 81.78288269042969,
        113.96137237548828);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685482635391926304, 1685476777114206240, 1607401500109, 27.713829040527344, 74.67459106445312,
        102.38842010498047);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685482635399266336, 1685476654668840992, 1607401500113, 32.21819305419922, 91.1540298461914,
        123.37222290039062);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685482949925928992, 1685476654668840992, 1607401800081, 32.09593200683594, 91.1895523071289,
        123.28548431396484);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685482949948997664, 1685476759780196384, 1607401800105, 32.18450164794922, 81.89651489257812,
        114.08101654052734);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685482949976260640, 1685476777114206240, 1607401800130, 27.582183837890625, 74.72720336914062,
        102.30938720703125);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685483264537526304, 1685476759780196384, 1607402100083, 32.512168884277344, 81.92813110351562,
        114.44029998779297);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685483264543817760, 1685476777114206240, 1607402100098, 27.78839874267578, 74.75088500976562,
        102.5392837524414);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685483264551157792, 1685476654668840992, 1607402100117, 32.45115661621094, 91.2004165649414,
        123.65157318115234);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685483579112423456, 1685476654668840992, 1607402400122, 32.05564880371094, 91.23624420166016,
        123.2918930053711);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685483579121860640, 1685476759780196384, 1607402400123, 32.055381774902344, 81.98318481445312,
        114.03856658935547);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685483579136540704, 1685476777114206240, 1607402400144, 27.625343322753906, 74.75918579101562,
        102.38452911376953);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685483893722972192, 1685476759780196384, 1607402700098, 32.27916717529297, 82.047607421875,
        114.32677459716797);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685483893729263648, 1685476777114206240, 1607402700132, 27.6163330078125, 74.78317260742188,
        102.39950561523438);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685483893734506528, 1685476654668840992, 1607402700150, 32.06878662109375, 91.2482681274414,
        123.31705474853516);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685592826220380192, 1685592826209894432, 1607506560243, 24.094436645507812, 48.57440948486328,
        72.6688461303711);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685592826346209312, 1685592826314752032, 1607506584155, 37.99201202392578, 66.7652359008789,
        104.75724792480469);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685592898380234784, 1685592898376040480, 1607506655002, 36.75182342529297, 65.68008422851562,
        102.4319076538086);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685592980466958368, 1685592980463812640, 1607506733280, 24.185546875, 48.443359375, 72.62890625);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685592997443403808, 1685592997438160928, 1607506749418, 25.403518676757812, 48.55085754394531,
        73.95437622070312);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685593002273144864, 1685593002266853408, 1607506754067, 25.49327850341797, 48.736061096191406,
        74.22933959960938);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685593050603061280, 1685593002266853408, 1607506800228, 34.11360168457031, 68.57090759277344,
        102.68450927734375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685593050613547040, 1685592980463812640, 1607506800233, 33.374412536621094, 68.25505828857422,
        101.62947082519531);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685593050621935648, 1685592898376040480, 1607506800240, 29.542510986328125, 73.22940063476562,
        102.77191162109375);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685593050656538656, 1685592997438160928, 1607506800244, 36.34321594238281, 71.8072738647461,
        108.1504898071289);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685593050664927264, 1685592826209894432, 1607506800252, 34.603614807128906, 78.7584228515625,
        113.3620376586914);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685593050673315872, 1685592826314752032, 1607506800266, 34.30937957763672, 85.30807495117188,
        119.6174545288086);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685593365053177888, 1685592980463812640, 1607507100138, 29.91480255126953, 72.47364044189453,
        102.38844299316406);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685593365081489440, 1685592997438160928, 1607507100144, 28.80548858642578, 72.38410186767578,
        101.18959045410156);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685593365094072352, 1685592898376040480, 1607507100183, 34.12188720703125, 78.96940612792969,
        113.09129333496094);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685593365104558112, 1685593002266853408, 1607507100185, 33.050025939941406, 74.08125305175781,
        107.13127899169922);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685593365295398944, 1685592826209894432, 1607507100222, 41.061614990234375, 89.04822540283203,
        130.1098403930664);
INSERT INTO `sys_log_monitor_jvm_status`
VALUES (1685593365315321888, 1685592826314752032, 1607507100387, 32.102020263671875, 87.74688720703125,
        119.84890747070312);

SET FOREIGN_KEY_CHECKS = 1;
