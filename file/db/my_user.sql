/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.101
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 192.168.1.101:3306
 Source Schema         : my_user

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 15/11/2020 17:57:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_content
-- ----------------------------
DROP TABLE IF EXISTS `sys_content`;
CREATE TABLE `sys_content`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
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
INSERT INTO `sys_content` VALUES ('3e09c120f1de9069', 1, '49ba59abbe56e057', b'0', NULL, 1, '49ba59abbe56e057', 'logoInfo', 'href', 'image', 'title', NULL, NULL, NULL, '', '/pic/my-uhyils.png', 'my-uhyils', NULL, NULL, NULL);
INSERT INTO `sys_content` VALUES ('9cfb0521646c1866', 1, '49ba59abbe56e057', b'0', NULL, 1, '49ba59abbe56e057', 'indexIframe', 'indexIframe', '', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_content` VALUES ('cc956e333b6acbc8', 1, '49ba59abbe56e057', b'0', NULL, 1, '49ba59abbe56e057', 'honeInfo', 'title', 'href', NULL, NULL, NULL, NULL, '首页', 'page/welcome-1.html', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('6551f75c465a45bc', 1591512277, '49ba59abbe56e057', b'0', NULL, 1591512277, '49ba59abbe56e057', '测试用户权限集');
INSERT INTO `sys_dept` VALUES ('f1547c178c79b1c5', 1591512159, '49ba59abbe56e057', b'0', NULL, 1591512159, '49ba59abbe56e057', '超级管理员权限集');

-- ----------------------------
-- Table structure for sys_dept_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_menu`;
CREATE TABLE `sys_dept_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `dept_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门-菜单关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept_menu
-- ----------------------------
INSERT INTO `sys_dept_menu` VALUES ('03e96b278b3e7bdd', '6551f75c465a45bc', '6d2d9727b9cd94c8');
INSERT INTO `sys_dept_menu` VALUES ('072309df159f2a23', 'f1547c178c79b1c5', 'bdfc9c161b61af22');
INSERT INTO `sys_dept_menu` VALUES ('096730c9af74be12', 'f1547c178c79b1c5', '8a3d33c4a4491749');
INSERT INTO `sys_dept_menu` VALUES ('1aa7a85e2a88cc26', 'f1547c178c79b1c5', 'cff03c3ff625db25');
INSERT INTO `sys_dept_menu` VALUES ('23c88365a3c0aa6c', 'f1547c178c79b1c5', '859ff5d974e2e6ab');
INSERT INTO `sys_dept_menu` VALUES ('28965137a0749833', 'f1547c178c79b1c5', '7f7ac8d39b0b5c2b');
INSERT INTO `sys_dept_menu` VALUES ('2aac953a1d6a8956', '6551f75c465a45bc', 'accd3db9aa88e27f');
INSERT INTO `sys_dept_menu` VALUES ('37e63956e142f2a1', '6551f75c465a45bc', '56c1680bebdb08fd');
INSERT INTO `sys_dept_menu` VALUES ('4b8d54d8858ad737', '6551f75c465a45bc', '7f7ac8d39b0b5c2b');
INSERT INTO `sys_dept_menu` VALUES ('4ed32365dac6c301', 'f1547c178c79b1c5', 'accd3db9aa88e27f');
INSERT INTO `sys_dept_menu` VALUES ('5eeb7103313f740a', 'f1547c178c79b1c5', '171242cf975407a7');
INSERT INTO `sys_dept_menu` VALUES ('65e6c3417d1dcc56', 'f1547c178c79b1c5', '8443efbb0d49201a');
INSERT INTO `sys_dept_menu` VALUES ('68c4323a44c569ef', 'f1547c178c79b1c5', '799962d65dfa819b');
INSERT INTO `sys_dept_menu` VALUES ('69cdbc8e890b70c4', '6551f75c465a45bc', '864868c4d270210f');
INSERT INTO `sys_dept_menu` VALUES ('77941bc06d3d3d39', '6551f75c465a45bc', '859ff5d974e2e6ab');
INSERT INTO `sys_dept_menu` VALUES ('81b5ab82dcff6bce', 'f1547c178c79b1c5', '56c1680bebdb08fd');
INSERT INTO `sys_dept_menu` VALUES ('840f429774c6f231', '6551f75c465a45bc', 'f502af6e0d7a173a');
INSERT INTO `sys_dept_menu` VALUES ('8a54da40b1130834', 'f1547c178c79b1c5', 'cb66e93bf6df43bb');
INSERT INTO `sys_dept_menu` VALUES ('92a7168c27ae99b2', 'f1547c178c79b1c5', '3f232dd9b523fcdf');
INSERT INTO `sys_dept_menu` VALUES ('96f54d31d3a3d5cf', '6551f75c465a45bc', '6e54f3558a6bc91d');
INSERT INTO `sys_dept_menu` VALUES ('9cb18866b40ccd6a', '6551f75c465a45bc', '799962d65dfa819b');
INSERT INTO `sys_dept_menu` VALUES ('9d1fea0315d97346', '6551f75c465a45bc', '8443efbb0d49201a');
INSERT INTO `sys_dept_menu` VALUES ('a3d73854241fa741', '6551f75c465a45bc', 'bdfc9c161b61af22');
INSERT INTO `sys_dept_menu` VALUES ('ab82ebc5bee6910d', '6551f75c465a45bc', 'cb66e93bf6df43bb');
INSERT INTO `sys_dept_menu` VALUES ('abc661345404f8a4', '6551f75c465a45bc', '3f232dd9b523fcdf');
INSERT INTO `sys_dept_menu` VALUES ('b25efaff434bab36', 'f1547c178c79b1c5', '8e71e8d3638f6592');
INSERT INTO `sys_dept_menu` VALUES ('b978da8d3029c326', 'f1547c178c79b1c5', '864868c4d270210f');
INSERT INTO `sys_dept_menu` VALUES ('be875283b625d1b1', '6551f75c465a45bc', '8a3d33c4a4491749');
INSERT INTO `sys_dept_menu` VALUES ('c80aedf886cbd5cd', '6551f75c465a45bc', 'eb0058ce33fc0f70');
INSERT INTO `sys_dept_menu` VALUES ('cf7f6b762303adc5', '6551f75c465a45bc', '8e71e8d3638f6592');
INSERT INTO `sys_dept_menu` VALUES ('d4a696db7efcdcd4', '6551f75c465a45bc', 'fbe5dd4f2df47d4d');
INSERT INTO `sys_dept_menu` VALUES ('d553b96427d49139', 'f1547c178c79b1c5', 'fbe5dd4f2df47d4d');
INSERT INTO `sys_dept_menu` VALUES ('dd3d3109ba640f11', 'f1547c178c79b1c5', 'eb0058ce33fc0f70');
INSERT INTO `sys_dept_menu` VALUES ('e334a25e704a4ad1', '6551f75c465a45bc', '171242cf975407a7');
INSERT INTO `sys_dept_menu` VALUES ('e36628a4f2fa6a63', 'f1547c178c79b1c5', '6d2d9727b9cd94c8');
INSERT INTO `sys_dept_menu` VALUES ('eec90bd6900842d4', 'f1547c178c79b1c5', '6e54f3558a6bc91d');
INSERT INTO `sys_dept_menu` VALUES ('f0d2fa6b1025fa92', '6551f75c465a45bc', 'cff03c3ff625db25');
INSERT INTO `sys_dept_menu` VALUES ('feac021c97a1c443', 'f1547c178c79b1c5', 'f502af6e0d7a173a');

-- ----------------------------
-- Table structure for sys_dept_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_power`;
CREATE TABLE `sys_dept_power`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `dept_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `power_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门-权限关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept_power
-- ----------------------------
INSERT INTO `sys_dept_power` VALUES ('01ab5bc437091ac6', '6551f75c465a45bc', '5022bc9752a36f85');
INSERT INTO `sys_dept_power` VALUES ('0299f3297518d12c', 'f1547c178c79b1c5', 'f9dd4b0897c71a8c');
INSERT INTO `sys_dept_power` VALUES ('02b252573c4ef227', 'f1547c178c79b1c5', 'a1acd418e7a93451');
INSERT INTO `sys_dept_power` VALUES ('04ddd3d9a832945b', '6551f75c465a45bc', '2b9264d09ebc4725');
INSERT INTO `sys_dept_power` VALUES ('061d095bcb0cbbf3', 'f1547c178c79b1c5', 'e9aba7bc3ab3595b');
INSERT INTO `sys_dept_power` VALUES ('0dfde28eb1032a3a', 'f1547c178c79b1c5', '34ade768ce9194ab');
INSERT INTO `sys_dept_power` VALUES ('0f2f2ffa26881188', '6551f75c465a45bc', '67034e753949e587');
INSERT INTO `sys_dept_power` VALUES ('12671efdce870c48', 'f1547c178c79b1c5', 'b371c3f2c1aad78c');
INSERT INTO `sys_dept_power` VALUES ('1c9637fc057a4d8a', 'f1547c178c79b1c5', 'ba21d33e5076c188');
INSERT INTO `sys_dept_power` VALUES ('1ce8a3667925f060', 'f1547c178c79b1c5', '1d6400353b2c7df9');
INSERT INTO `sys_dept_power` VALUES ('2188a38d2605803e', '6551f75c465a45bc', '5185d68ce1fc645e');
INSERT INTO `sys_dept_power` VALUES ('23366d982f4f7f56', 'f1547c178c79b1c5', 'b74b38b65f045e2c');
INSERT INTO `sys_dept_power` VALUES ('2426bb4fec8ea99d', 'f1547c178c79b1c5', 'ca88a9880af7bfc0');
INSERT INTO `sys_dept_power` VALUES ('24487e6b025b52e7', 'f1547c178c79b1c5', '4208423c6adaf651');
INSERT INTO `sys_dept_power` VALUES ('265145c2f373ad2e', 'f1547c178c79b1c5', 'b1ebeb0f15821c46');
INSERT INTO `sys_dept_power` VALUES ('288263e3b0a41583', '6551f75c465a45bc', '4208423c6adaf651');
INSERT INTO `sys_dept_power` VALUES ('2a722f5f3b37c9f0', '6551f75c465a45bc', '0633b68b40bb450c');
INSERT INTO `sys_dept_power` VALUES ('2da79c0b26aae2c4', '6551f75c465a45bc', '8d8cd55454c18c24');
INSERT INTO `sys_dept_power` VALUES ('301361a34db0072c', '6551f75c465a45bc', '361bde39e6450974');
INSERT INTO `sys_dept_power` VALUES ('31c32a075b8cd6e1', 'f1547c178c79b1c5', '073b9b45094a9b6e');
INSERT INTO `sys_dept_power` VALUES ('367406b6b8e76694', 'f1547c178c79b1c5', '6235bbf24cab81d3');
INSERT INTO `sys_dept_power` VALUES ('37919338c203c13a', '6551f75c465a45bc', '4f59f9d8f9928219');
INSERT INTO `sys_dept_power` VALUES ('3805a27487ce16ce', 'f1547c178c79b1c5', '96acbd5182cd0d3e');
INSERT INTO `sys_dept_power` VALUES ('3b6695b6287718a4', '6551f75c465a45bc', '2c2aa105d280b382');
INSERT INTO `sys_dept_power` VALUES ('3d1570b43f68a8ba', 'f1547c178c79b1c5', '4a97f6d1b114a281');
INSERT INTO `sys_dept_power` VALUES ('405d6c95d69e74d4', 'f1547c178c79b1c5', '8d8cd55454c18c24');
INSERT INTO `sys_dept_power` VALUES ('43db8209affbfd73', '6551f75c465a45bc', '12ee6109e0bb3a05');
INSERT INTO `sys_dept_power` VALUES ('4416c40f88cd7e56', 'f1547c178c79b1c5', '26ad7913a42a4012');
INSERT INTO `sys_dept_power` VALUES ('456c06ee81fde97a', '6551f75c465a45bc', 'a1acd418e7a93451');
INSERT INTO `sys_dept_power` VALUES ('4c027d3a8829632c', '6551f75c465a45bc', 'adb7c89fac5eb483');
INSERT INTO `sys_dept_power` VALUES ('4dafeba7d6eb8c29', '6551f75c465a45bc', '087eb73ee78ac859');
INSERT INTO `sys_dept_power` VALUES ('4ecfed57e44ff09d', 'f1547c178c79b1c5', '5c56f85e0e4ad1ac');
INSERT INTO `sys_dept_power` VALUES ('4f0563d4ec1ad010', '6551f75c465a45bc', 'd9a0c39cb69d2925');
INSERT INTO `sys_dept_power` VALUES ('567891784b997ebb', '6551f75c465a45bc', '441ae4672723532a');
INSERT INTO `sys_dept_power` VALUES ('56a2d2422c450630', '6551f75c465a45bc', '5e365d070328ddb6');
INSERT INTO `sys_dept_power` VALUES ('57fcfec869d6d06c', 'f1547c178c79b1c5', '4ec1d33f3ad93171');
INSERT INTO `sys_dept_power` VALUES ('60bc568ddfaa141b', 'f1547c178c79b1c5', '2b9264d09ebc4725');
INSERT INTO `sys_dept_power` VALUES ('649e744e025c7880', 'f1547c178c79b1c5', '7d0b6bb7e845a085');
INSERT INTO `sys_dept_power` VALUES ('656078a3379f5a04', '6551f75c465a45bc', 'ca88a9880af7bfc0');
INSERT INTO `sys_dept_power` VALUES ('6591bf7f41405bd6', 'f1547c178c79b1c5', '0bab9e19aafba33b');
INSERT INTO `sys_dept_power` VALUES ('692dd40731b46485', 'f1547c178c79b1c5', '441ae4672723532a');
INSERT INTO `sys_dept_power` VALUES ('6aba8ecad036effc', '6551f75c465a45bc', '4b0ec247c55bf8dc');
INSERT INTO `sys_dept_power` VALUES ('73f5a9044c23a3cf', '6551f75c465a45bc', '4a97f6d1b114a281');
INSERT INTO `sys_dept_power` VALUES ('7400b22231d4c0db', 'f1547c178c79b1c5', '5185d68ce1fc645e');
INSERT INTO `sys_dept_power` VALUES ('74dc5ad651158a6a', '6551f75c465a45bc', '26ad7913a42a4012');
INSERT INTO `sys_dept_power` VALUES ('7e8278920bd37ff4', 'f1547c178c79b1c5', '4b0ec247c55bf8dc');
INSERT INTO `sys_dept_power` VALUES ('86e0c6d9d79c92ad', 'f1547c178c79b1c5', '5864324df3a3b516');
INSERT INTO `sys_dept_power` VALUES ('87452c8ad75f7768', 'f1547c178c79b1c5', '03617f440cea6b74');
INSERT INTO `sys_dept_power` VALUES ('90428221f2905498', '6551f75c465a45bc', '5864324df3a3b516');
INSERT INTO `sys_dept_power` VALUES ('90d828052e2ff710', '6551f75c465a45bc', '6d76d28f2cf09362');
INSERT INTO `sys_dept_power` VALUES ('929bec288413f802', 'f1547c178c79b1c5', '5e365d070328ddb6');
INSERT INTO `sys_dept_power` VALUES ('9490f74b00387209', 'f1547c178c79b1c5', 'd00d25d5f9c4f4f2');
INSERT INTO `sys_dept_power` VALUES ('9578596239ae6d1a', '6551f75c465a45bc', 'e4e94b65c7ce87a2');
INSERT INTO `sys_dept_power` VALUES ('95bd291f9fffadee', '6551f75c465a45bc', 'a06da41d1077d0c1');
INSERT INTO `sys_dept_power` VALUES ('96ae01fd0500ae85', '6551f75c465a45bc', 'ae4b4f1e7ac72e36');
INSERT INTO `sys_dept_power` VALUES ('96fb078d96698949', 'f1547c178c79b1c5', '361bde39e6450974');
INSERT INTO `sys_dept_power` VALUES ('9aae048a42bda6ed', '6551f75c465a45bc', 'facb12d4b4d1a7f6');
INSERT INTO `sys_dept_power` VALUES ('9d7734f3e274c278', '6551f75c465a45bc', 'b371c3f2c1aad78c');
INSERT INTO `sys_dept_power` VALUES ('a02a6c7f452e0ae4', 'f1547c178c79b1c5', 'facb12d4b4d1a7f6');
INSERT INTO `sys_dept_power` VALUES ('a0a6a87cd0448f66', 'f1547c178c79b1c5', '1e62e83e98579200');
INSERT INTO `sys_dept_power` VALUES ('a17592354449680d', '6551f75c465a45bc', '5c56f85e0e4ad1ac');
INSERT INTO `sys_dept_power` VALUES ('a3005bb7f1658a22', '6551f75c465a45bc', 'fbe3a84eda3bb9fd');
INSERT INTO `sys_dept_power` VALUES ('a91e0fe47a888e63', '6551f75c465a45bc', 'cd25ac015ce75f13');
INSERT INTO `sys_dept_power` VALUES ('ad42dd47f1542349', '6551f75c465a45bc', 'b74b38b65f045e2c');
INSERT INTO `sys_dept_power` VALUES ('af2dc11be47fe9d0', '6551f75c465a45bc', 'b1ebeb0f15821c46');
INSERT INTO `sys_dept_power` VALUES ('afe7db6ced990db5', '6551f75c465a45bc', '1e62e83e98579200');
INSERT INTO `sys_dept_power` VALUES ('b03f0acfc7f71fb9', '6551f75c465a45bc', '4ec1d33f3ad93171');
INSERT INTO `sys_dept_power` VALUES ('b5a66a1889180b66', 'f1547c178c79b1c5', '7d0b60c69d31eefe');
INSERT INTO `sys_dept_power` VALUES ('b87e38d03ab97194', '6551f75c465a45bc', '1bd4b8cbb8a484d0');
INSERT INTO `sys_dept_power` VALUES ('b8d6dca9263c949f', 'f1547c178c79b1c5', '67034e753949e587');
INSERT INTO `sys_dept_power` VALUES ('b91c0e75c23132e8', '6551f75c465a45bc', '0ea708b67b6db61a');
INSERT INTO `sys_dept_power` VALUES ('b949cc0e46c6a780', '6551f75c465a45bc', 'f9dd4b0897c71a8c');
INSERT INTO `sys_dept_power` VALUES ('c01ac2b8ce404544', '6551f75c465a45bc', '2fde0905418f905f');
INSERT INTO `sys_dept_power` VALUES ('c30f5e728783e41f', '6551f75c465a45bc', '1d6400353b2c7df9');
INSERT INTO `sys_dept_power` VALUES ('c39c4686deab3607', '6551f75c465a45bc', '96acbd5182cd0d3e');
INSERT INTO `sys_dept_power` VALUES ('c40eb7deb40799fa', 'f1547c178c79b1c5', '4633fbcba3ae4e48');
INSERT INTO `sys_dept_power` VALUES ('c6e3e2c0f2027d3d', 'f1547c178c79b1c5', '087eb73ee78ac859');
INSERT INTO `sys_dept_power` VALUES ('c8732b76d9c8c452', 'f1547c178c79b1c5', 'cd25ac015ce75f13');
INSERT INTO `sys_dept_power` VALUES ('cb4b3932c42eab80', 'f1547c178c79b1c5', '1bd4b8cbb8a484d0');
INSERT INTO `sys_dept_power` VALUES ('cbe17d026ea4f5bd', 'f1547c178c79b1c5', 'fbe3a84eda3bb9fd');
INSERT INTO `sys_dept_power` VALUES ('cc417d98d670612f', '6551f75c465a45bc', '03617f440cea6b74');
INSERT INTO `sys_dept_power` VALUES ('cf9241774b5a13d8', 'f1547c178c79b1c5', 'a06da41d1077d0c1');
INSERT INTO `sys_dept_power` VALUES ('d063af55143518f4', '6551f75c465a45bc', '34ade768ce9194ab');
INSERT INTO `sys_dept_power` VALUES ('d0f62dde76bbf486', 'f1547c178c79b1c5', 'ae4b4f1e7ac72e36');
INSERT INTO `sys_dept_power` VALUES ('d21a06696c116161', 'f1547c178c79b1c5', '6d76d28f2cf09362');
INSERT INTO `sys_dept_power` VALUES ('d2d00d3212b2a2c2', 'f1547c178c79b1c5', '0633b68b40bb450c');
INSERT INTO `sys_dept_power` VALUES ('d4e2621a0df06cb2', '6551f75c465a45bc', '7d0b6bb7e845a085');
INSERT INTO `sys_dept_power` VALUES ('dc3c0d3da2c5723d', '6551f75c465a45bc', '0bab9e19aafba33b');
INSERT INTO `sys_dept_power` VALUES ('dd565ba3b3d7eba9', '6551f75c465a45bc', '7d0b60c69d31eefe');
INSERT INTO `sys_dept_power` VALUES ('e0e0240fc1aee910', '6551f75c465a45bc', '073b9b45094a9b6e');
INSERT INTO `sys_dept_power` VALUES ('e37ecf6c5c84e6a3', 'f1547c178c79b1c5', 'e4e94b65c7ce87a2');
INSERT INTO `sys_dept_power` VALUES ('e4dd192c146f1c27', '6551f75c465a45bc', 'e9aba7bc3ab3595b');
INSERT INTO `sys_dept_power` VALUES ('e8502614dae85734', 'f1547c178c79b1c5', '0ea708b67b6db61a');
INSERT INTO `sys_dept_power` VALUES ('eeffdfd7c33b4d00', '6551f75c465a45bc', 'ba21d33e5076c188');
INSERT INTO `sys_dept_power` VALUES ('efafcb5bb4cdc489', '6551f75c465a45bc', '6235bbf24cab81d3');
INSERT INTO `sys_dept_power` VALUES ('f012347b80eac511', 'f1547c178c79b1c5', 'd9a0c39cb69d2925');
INSERT INTO `sys_dept_power` VALUES ('f0a37dfd2f9f1920', 'f1547c178c79b1c5', '2c2aa105d280b382');
INSERT INTO `sys_dept_power` VALUES ('f2abd72f57b6f3ba', '6551f75c465a45bc', 'd00d25d5f9c4f4f2');
INSERT INTO `sys_dept_power` VALUES ('f40d8ca97b6897ed', 'f1547c178c79b1c5', '4f59f9d8f9928219');
INSERT INTO `sys_dept_power` VALUES ('f84ff0748a5c5507', 'f1547c178c79b1c5', '2fde0905418f905f');
INSERT INTO `sys_dept_power` VALUES ('fa4f7ae3edfc7190', '6551f75c465a45bc', '4633fbcba3ae4e48');
INSERT INTO `sys_dept_power` VALUES ('fb493fe02993f5c8', 'f1547c178c79b1c5', '12ee6109e0bb3a05');
INSERT INTO `sys_dept_power` VALUES ('fd485ccfec310cd2', 'f1547c178c79b1c5', 'adb7c89fac5eb483');
INSERT INTO `sys_dept_power` VALUES ('ff1685a55829a7fd', 'f1547c178c79b1c5', '5022bc9752a36f85');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('14f7933e647d4368', 1592697362, '49ba59abbe56e057', b'0', NULL, 1592697362, '49ba59abbe56e057', 'lastPlan', '下一步计划', '下一步计划', 1);
INSERT INTO `sys_dict` VALUES ('231a95087199851f', 1592697179, '49ba59abbe56e057', b'0', NULL, 1592697179, '49ba59abbe56e057', 'myVersionCode', '首页展示的版本信息', '版本信息', 1);
INSERT INTO `sys_dict` VALUES ('24c8f0997bdde222', 1593385798, '49ba59abbe56e057', b'0', NULL, 1593385798, '49ba59abbe56e057', 'pushType', '推送时发送信息的方式', '推送类型', 0);
INSERT INTO `sys_dict` VALUES ('4119bf79166ebb2b', 1593473419, '49ba59abbe56e057', b'0', NULL, 1593473419, '49ba59abbe56e057', 'job_cron', '服务订阅可用时间', '服务订阅可用时间', 1);
INSERT INTO `sys_dict` VALUES ('497f9230e03d32ef', 1600420004, '49ba59abbe56e057', b'0', NULL, 1600420058, '49ba59abbe56e057', 'concurrent_num_dict_code', '服务自动降级并发数', '服务自动降级并发数', 0);
INSERT INTO `sys_dict` VALUES ('57616e9f77c65a1f', 1593310051, '49ba59abbe56e057', b'0', NULL, 1593310051, '49ba59abbe56e057', 'result-code', 'api返回值编码', 'api返回值编码', 1);
INSERT INTO `sys_dict` VALUES ('62c376815cf83ce1', 1593216064, '49ba59abbe56e057', b'0', NULL, 1593216064, '49ba59abbe56e057', 'job-paramType', '定时任务请求类', '定时任务请求类', 1);
INSERT INTO `sys_dict` VALUES ('8327faa0b36efb79', 1592348517, '49ba59abbe56e057', b'0', NULL, 1592348517, '49ba59abbe56e057', 'icon-class', '整个项目的图标库', '图标', 1);
INSERT INTO `sys_dict` VALUES ('8bfce8cad90e0d79', 1592298770, '49ba59abbe56e057', b'1', NULL, 1592348263, '49ba59abbe56e057', 'serviceCode', '页面请求后台时返回的编码', '请求返回值', 0);
INSERT INTO `sys_dict` VALUES ('a9f3986d7fd688a4', 1592705561, '49ba59abbe56e057', b'0', NULL, 1592705561, '49ba59abbe56e057', 'quickStart', '首页的快捷入口', '快捷入口', 1);
INSERT INTO `sys_dict` VALUES ('c66ca4e5415b47f4', 1592348794, '49ba59abbe56e057', b'0', NULL, 1592348794, '49ba59abbe56e057', 'server-type', '服务器系统类型', '服务器类型', 0);
INSERT INTO `sys_dict` VALUES ('c6d5c865cb89de02', 1604036329, '49ba59abbe56e057', b'0', NULL, 1604036329, '49ba59abbe56e057', 'doc_iframe', '文档适用场景', '文档适用场景', 0);
INSERT INTO `sys_dict` VALUES ('d43f938a97b62a6a', 1593215861, '49ba59abbe56e057', b'0', NULL, 1593215861, '49ba59abbe56e057', 'job-interfaceName', '定时任务可用接口', '定时任务可用接口', 1);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dict_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort_order` int(0) NULL DEFAULT NULL,
  `text` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典子项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('00454fc7968e072c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-ils', '8327faa0b36efb79', 409, 'fa-ils', 'fa fa-ils', NULL);
INSERT INTO `sys_dict_item` VALUES ('007b891189e49e84', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-wordpress', '8327faa0b36efb79', 637, 'fa-wordpress', 'fa fa-wordpress', NULL);
INSERT INTO `sys_dict_item` VALUES ('00839c0281cfff29', 1593473559, '49ba59abbe56e057', b'0', NULL, 1593473559, '49ba59abbe56e057', '晚上5:30', '4119bf79166ebb2b', 5, '晚上5:30', '0 30 17 * * ?', NULL);
INSERT INTO `sys_dict_item` VALUES ('01ed868d2a151df7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-motorcycle', '8327faa0b36efb79', 341, 'fa-motorcycle', 'fa fa-motorcycle', NULL);
INSERT INTO `sys_dict_item` VALUES ('022ea520404ccac5', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-eject', '8327faa0b36efb79', 506, 'fa-eject', 'fa fa-eject', NULL);
INSERT INTO `sys_dict_item` VALUES ('025d92ad2d107706', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-institution', '8327faa0b36efb79', 161, 'fa-institution', 'fa fa-institution', NULL);
INSERT INTO `sys_dict_item` VALUES ('02cc00d724805850', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-chevron-circle-left', '8327faa0b36efb79', 488, 'fa-chevron-circle-left', 'fa fa-chevron-circle-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('02f488023b3adf08', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bus', '8327faa0b36efb79', 37, 'fa-bus', 'fa fa-bus', NULL);
INSERT INTO `sys_dict_item` VALUES ('03296d8a7b1e2f3e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-pinterest-p', '8327faa0b36efb79', 598, 'fa-pinterest-p', 'fa fa-pinterest-p', NULL);
INSERT INTO `sys_dict_item` VALUES ('04326c1f6523907f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-university', '8327faa0b36efb79', 317, 'fa-university', 'fa fa-university', NULL);
INSERT INTO `sys_dict_item` VALUES ('04c5a363a920b976', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-credit-card', '8327faa0b36efb79', 79, 'fa-credit-card', 'fa fa-credit-card', NULL);
INSERT INTO `sys_dict_item` VALUES ('056233c1f08a0c4a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-facebook', '8327faa0b36efb79', 561, 'fa-facebook', 'fa fa-facebook', NULL);
INSERT INTO `sys_dict_item` VALUES ('0562dc96583b7c98', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-hospital-o', '8327faa0b36efb79', 528, 'fa-hospital-o', 'fa fa-hospital-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('05da96151aee028b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-fire-extinguisher', '8327faa0b36efb79', 127, 'fa-fire-extinguisher', 'fa fa-fire-extinguisher', NULL);
INSERT INTO `sys_dict_item` VALUES ('05e913efd2ccd752', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-slideshare', '8327faa0b36efb79', 613, 'fa-slideshare', 'fa fa-slideshare', NULL);
INSERT INTO `sys_dict_item` VALUES ('05ef969f4c342bcf', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-fighter-jet', '8327faa0b36efb79', 340, 'fa-fighter-jet', 'fa fa-fighter-jet', NULL);
INSERT INTO `sys_dict_item` VALUES ('05ff53074d592203', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-calculator', '8327faa0b36efb79', 39, 'fa-calculator', 'fa fa-calculator', NULL);
INSERT INTO `sys_dict_item` VALUES ('06da787fc216e64f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-star', '8327faa0b36efb79', 277, 'fa-star', 'fa fa-star', NULL);
INSERT INTO `sys_dict_item` VALUES ('06e6a2fd6a401fef', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrow-circle-o-right', '8327faa0b36efb79', 469, 'fa-arrow-circle-o-right', 'fa fa-arrow-circle-o-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('06e883ff549ed36d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-mail-reply-all', '8327faa0b36efb79', 183, 'fa-mail-reply-all', 'fa fa-mail-reply-all', NULL);
INSERT INTO `sys_dict_item` VALUES ('089c1951120462e8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-linkedin', '8327faa0b36efb79', 586, 'fa-linkedin', 'fa fa-linkedin', NULL);
INSERT INTO `sys_dict_item` VALUES ('08af62196ce03d47', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-navicon', '8327faa0b36efb79', 200, 'fa-navicon', 'fa fa-navicon', NULL);
INSERT INTO `sys_dict_item` VALUES ('08cb1872b4008fd2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-fax', '8327faa0b36efb79', 107, 'fa-fax', 'fa fa-fax', NULL);
INSERT INTO `sys_dict_item` VALUES ('0901b3f92c1e56a4', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-spoon', '8327faa0b36efb79', 274, 'fa-spoon', 'fa fa-spoon', NULL);
INSERT INTO `sys_dict_item` VALUES ('0914d94788eacf9a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-vk', '8327faa0b36efb79', 632, 'fa-vk', 'fa fa-vk', NULL);
INSERT INTO `sys_dict_item` VALUES ('09f86244041327f9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-film', '8327faa0b36efb79', 124, 'fa-film', 'fa fa-film', NULL);
INSERT INTO `sys_dict_item` VALUES ('0a9764c4f0cee537', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrows-h', '8327faa0b36efb79', 477, 'fa-arrows-h', 'fa fa-arrows-h', NULL);
INSERT INTO `sys_dict_item` VALUES ('0aba8856e4d5c778', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-folder-open-o', '8327faa0b36efb79', 136, 'fa-folder-open-o', 'fa fa-folder-open-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('0af2bab412327650', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-o', '8327faa0b36efb79', 437, 'fa-file-o', 'fa fa-file-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('0b0f203107286e56', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-chevron-left', '8327faa0b36efb79', 492, 'fa-chevron-left', 'fa fa-chevron-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('0b37b292f6ca82aa', 1593473490, '49ba59abbe56e057', b'0', NULL, 1593473490, '49ba59abbe56e057', '早上6点', '4119bf79166ebb2b', 2, '早6:00', '0 0 6 * * ?', NULL);
INSERT INTO `sys_dict_item` VALUES ('0b8d57f2a9bab06a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-android', '8327faa0b36efb79', 535, 'fa-android', 'fa fa-android', NULL);
INSERT INTO `sys_dict_item` VALUES ('0ba43e9da09db83e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-rocket', '8327faa0b36efb79', 237, 'fa-rocket', 'fa fa-rocket', NULL);
INSERT INTO `sys_dict_item` VALUES ('0d354deb7660ce97', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-forward', '8327faa0b36efb79', 510, 'fa-forward', 'fa fa-forward', NULL);
INSERT INTO `sys_dict_item` VALUES ('0d42df578b3d0474', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-inbox', '8327faa0b36efb79', 158, 'fa-inbox', 'fa fa-inbox', NULL);
INSERT INTO `sys_dict_item` VALUES ('0da07a8836a54b84', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-fire', '8327faa0b36efb79', 126, 'fa-fire', 'fa fa-fire', NULL);
INSERT INTO `sys_dict_item` VALUES ('0e2206cc0c178291', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-unsorted', '8327faa0b36efb79', 320, 'fa-unsorted', 'fa fa-unsorted', NULL);
INSERT INTO `sys_dict_item` VALUES ('0f18df76ed66a604', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-exchange', '8327faa0b36efb79', 98, 'fa-exchange', 'fa fa-exchange', NULL);
INSERT INTO `sys_dict_item` VALUES ('0f54e1ded650c5e1', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-neuter', '8327faa0b36efb79', 357, 'fa-neuter', 'fa fa-neuter', NULL);
INSERT INTO `sys_dict_item` VALUES ('0f9a605f499944eb', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-facebook-official', '8327faa0b36efb79', 562, 'fa-facebook-official', 'fa fa-facebook-official', NULL);
INSERT INTO `sys_dict_item` VALUES ('10159ee62df865f4', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-truck', '8327faa0b36efb79', 348, 'fa-truck', 'fa fa-truck', NULL);
INSERT INTO `sys_dict_item` VALUES ('101f2d5f3bebfd14', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-trello', '8327faa0b36efb79', 623, 'fa-trello', 'fa fa-trello', NULL);
INSERT INTO `sys_dict_item` VALUES ('103c4e5c6441c87e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-archive-o', '8327faa0b36efb79', 110, 'fa-file-archive-o', 'fa fa-file-archive-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('10412e2678e2fac7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-shirtsinbulk', '8327faa0b36efb79', 608, 'fa-shirtsinbulk', 'fa fa-shirtsinbulk', NULL);
INSERT INTO `sys_dict_item` VALUES ('10746993b909963e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-audio-o', '8327faa0b36efb79', 111, 'fa-file-audio-o', 'fa fa-file-audio-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('11fd3221092483fc', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-motorcycle', '8327faa0b36efb79', 198, 'fa-motorcycle', 'fa fa-motorcycle', NULL);
INSERT INTO `sys_dict_item` VALUES ('12090f38717dbf67', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-headphones', '8327faa0b36efb79', 150, 'fa-headphones', 'fa fa-headphones', NULL);
INSERT INTO `sys_dict_item` VALUES ('1228665231e691ff', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-long-arrow-left', '8327faa0b36efb79', 500, 'fa-long-arrow-left', 'fa fa-long-arrow-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('1249967d758f72ef', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-thumb-tack', '8327faa0b36efb79', 294, 'fa-thumb-tack', 'fa fa-thumb-tack', NULL);
INSERT INTO `sys_dict_item` VALUES ('12c2e07b003dba35', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-car', '8327faa0b36efb79', 44, 'fa-car', 'fa fa-car', NULL);
INSERT INTO `sys_dict_item` VALUES ('131b168e548ebabb', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-paypal', '8327faa0b36efb79', 399, 'fa-paypal', 'fa fa-paypal', NULL);
INSERT INTO `sys_dict_item` VALUES ('136c8cd1b29aead6', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-sound-o', '8327faa0b36efb79', 120, 'fa-file-sound-o', 'fa fa-file-sound-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('137d66717653a8ed', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cc-paypal', '8327faa0b36efb79', 547, 'fa-cc-paypal', 'fa fa-cc-paypal', NULL);
INSERT INTO `sys_dict_item` VALUES ('13a90194f24869cf', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cc-stripe', '8327faa0b36efb79', 395, 'fa-cc-stripe', 'fa fa-cc-stripe', NULL);
INSERT INTO `sys_dict_item` VALUES ('1401a7368b5633fb', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-tty', '8327faa0b36efb79', 315, 'fa-tty', 'fa fa-tty', NULL);
INSERT INTO `sys_dict_item` VALUES ('145e69d8079feda3', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-behance-square', '8327faa0b36efb79', 539, 'fa-behance-square', 'fa fa-behance-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('1485a347b2536a13', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-plane', '8327faa0b36efb79', 214, 'fa-plane', 'fa fa-plane', NULL);
INSERT INTO `sys_dict_item` VALUES ('15fabcc43f62342e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-lock', '8327faa0b36efb79', 178, 'fa-lock', 'fa fa-lock', NULL);
INSERT INTO `sys_dict_item` VALUES ('16137d9b8213e68b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-folder', '8327faa0b36efb79', 133, 'fa-folder', 'fa fa-folder', NULL);
INSERT INTO `sys_dict_item` VALUES ('162ca429ce89fb92', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-crop', '8327faa0b36efb79', 80, 'fa-crop', 'fa fa-crop', NULL);
INSERT INTO `sys_dict_item` VALUES ('163f50003baab62d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-meanpath', '8327faa0b36efb79', 590, 'fa-meanpath', 'fa fa-meanpath', NULL);
INSERT INTO `sys_dict_item` VALUES ('1672300184d8afba', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-circle-thin', '8327faa0b36efb79', 62, 'fa-circle-thin', 'fa fa-circle-thin', NULL);
INSERT INTO `sys_dict_item` VALUES ('16a57b809603d9b8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-spinner', '8327faa0b36efb79', 379, 'fa-spinner', 'fa fa-spinner', NULL);
INSERT INTO `sys_dict_item` VALUES ('170213a8671aad4e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-mars-stroke-v', '8327faa0b36efb79', 355, 'fa-mars-stroke-v', 'fa fa-mars-stroke-v', NULL);
INSERT INTO `sys_dict_item` VALUES ('18067fd302adea7e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-reddit-square', '8327faa0b36efb79', 603, 'fa-reddit-square', 'fa fa-reddit-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('1828aac0489e3972', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-home', '8327faa0b36efb79', 155, 'fa-home', 'fa fa-home', NULL);
INSERT INTO `sys_dict_item` VALUES ('19be224a9dfa50e4', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-group', '8327faa0b36efb79', 148, 'fa-group', 'fa fa-group', NULL);
INSERT INTO `sys_dict_item` VALUES ('1a412a3d56b920cd', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-keyboard-o', '8327faa0b36efb79', 163, 'fa-keyboard-o', 'fa fa-keyboard-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('1a713aca51c40b82', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-asterisk', '8327faa0b36efb79', 8, 'fa-asterisk', 'fa fa-asterisk', NULL);
INSERT INTO `sys_dict_item` VALUES ('1ae7b39c8839b9b1', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-photo', '8327faa0b36efb79', 211, 'fa-photo', 'fa fa-photo', NULL);
INSERT INTO `sys_dict_item` VALUES ('1b03ddaa3e5a2e27', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-toggle-left', '8327faa0b36efb79', 305, 'fa-toggle-left', 'fa fa-toggle-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('1b9e20bff4278815', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-buysellads', '8327faa0b36efb79', 543, 'fa-buysellads', 'fa fa-buysellads', NULL);
INSERT INTO `sys_dict_item` VALUES ('1bd51c5e68e5e200', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrow-circle-up', '8327faa0b36efb79', 466, 'fa-arrow-circle-up', 'fa fa-arrow-circle-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('1c54233134db2994', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sort-amount-asc', '8327faa0b36efb79', 264, 'fa-sort-amount-asc', 'fa fa-sort-amount-asc', NULL);
INSERT INTO `sys_dict_item` VALUES ('1c9b829278cdf946', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-google', '8327faa0b36efb79', 573, 'fa-google', 'fa fa-google', NULL);
INSERT INTO `sys_dict_item` VALUES ('1d53d466546ef546', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-info-circle', '8327faa0b36efb79', 160, 'fa-info-circle', 'fa fa-info-circle', NULL);
INSERT INTO `sys_dict_item` VALUES ('1d5534898a416e57', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-share', '8327faa0b36efb79', 246, 'fa-share', 'fa fa-share', NULL);
INSERT INTO `sys_dict_item` VALUES ('1d5ee46ebf1b2274', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-toggle-right', '8327faa0b36efb79', 308, 'fa-toggle-right', 'fa fa-toggle-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('1dea618045a295d9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-frown-o', '8327faa0b36efb79', 137, 'fa-frown-o', 'fa fa-frown-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('1e1ea156ff388131', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-tree', '8327faa0b36efb79', 312, 'fa-tree', 'fa fa-tree', NULL);
INSERT INTO `sys_dict_item` VALUES ('205ddccfcadcf667', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-rebel', '8327faa0b36efb79', 601, 'fa-rebel', 'fa fa-rebel', NULL);
INSERT INTO `sys_dict_item` VALUES ('20da5bb9d430aa29', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-play', '8327faa0b36efb79', 512, 'fa-play', 'fa fa-play', NULL);
INSERT INTO `sys_dict_item` VALUES ('20ebaf3b266fe90b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrows-alt', '8327faa0b36efb79', 476, 'fa-arrows-alt', 'fa fa-arrows-alt', NULL);
INSERT INTO `sys_dict_item` VALUES ('226e18cf94f38e76', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-user-secret', '8327faa0b36efb79', 324, 'fa-user-secret', 'fa fa-user-secret', NULL);
INSERT INTO `sys_dict_item` VALUES ('2316a9f1d772be24', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-users', '8327faa0b36efb79', 326, 'fa-users', 'fa fa-users', NULL);
INSERT INTO `sys_dict_item` VALUES ('23664135dccc2f9c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cc-amex', '8327faa0b36efb79', 391, 'fa-cc-amex', 'fa fa-cc-amex', NULL);
INSERT INTO `sys_dict_item` VALUES ('23767e20a3a3e65d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bell-slash', '8327faa0b36efb79', 21, 'fa-bell-slash', 'fa fa-bell-slash', NULL);
INSERT INTO `sys_dict_item` VALUES ('23c33683bbadf6ec', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-share-alt', '8327faa0b36efb79', 606, 'fa-share-alt', 'fa fa-share-alt', NULL);
INSERT INTO `sys_dict_item` VALUES ('23cfcba8e314b571', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-random', '8327faa0b36efb79', 228, 'fa-random', 'fa fa-random', NULL);
INSERT INTO `sys_dict_item` VALUES ('24cb1b7b85b8b78e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-share-alt', '8327faa0b36efb79', 247, 'fa-share-alt', 'fa fa-share-alt', NULL);
INSERT INTO `sys_dict_item` VALUES ('24d6beb1af3e82f5', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-compass', '8327faa0b36efb79', 77, 'fa-compass', 'fa fa-compass', NULL);
INSERT INTO `sys_dict_item` VALUES ('25d1d26fea0f62fb', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-volume-off', '8327faa0b36efb79', 329, 'fa-volume-off', 'fa fa-volume-off', NULL);
INSERT INTO `sys_dict_item` VALUES ('25ef380c994e7be9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-joomla', '8327faa0b36efb79', 581, 'fa-joomla', 'fa fa-joomla', NULL);
INSERT INTO `sys_dict_item` VALUES ('268afd7db534ad6a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-caret-square-o-right', '8327faa0b36efb79', 485, 'fa-caret-square-o-right', 'fa fa-caret-square-o-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('268f563dd0c98c1b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-support', '8327faa0b36efb79', 286, 'fa-support', 'fa fa-support', NULL);
INSERT INTO `sys_dict_item` VALUES ('275c45810c4c3cc9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-fast-backward', '8327faa0b36efb79', 508, 'fa-fast-backward', 'fa fa-fast-backward', NULL);
INSERT INTO `sys_dict_item` VALUES ('27755875b0d9e45d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sort-alpha-desc', '8327faa0b36efb79', 263, 'fa-sort-alpha-desc', 'fa fa-sort-alpha-desc', NULL);
INSERT INTO `sys_dict_item` VALUES ('27806639ca56c30f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-car', '8327faa0b36efb79', 336, 'fa-car', 'fa fa-car', NULL);
INSERT INTO `sys_dict_item` VALUES ('28074a33dc22f4dd', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bomb', '8327faa0b36efb79', 27, 'fa-bomb', 'fa fa-bomb', NULL);
INSERT INTO `sys_dict_item` VALUES ('28354cffe042543b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-transgender', '8327faa0b36efb79', 358, 'fa-transgender', 'fa fa-transgender', NULL);
INSERT INTO `sys_dict_item` VALUES ('294426d8b4f2ca68', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sign-out', '8327faa0b36efb79', 255, 'fa-sign-out', 'fa fa-sign-out', NULL);
INSERT INTO `sys_dict_item` VALUES ('29472aa028eae511', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-magnet', '8327faa0b36efb79', 180, 'fa-magnet', 'fa fa-magnet', NULL);
INSERT INTO `sys_dict_item` VALUES ('29edd598d7899657', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-font', '8327faa0b36efb79', 420, 'fa-font', 'fa fa-font', NULL);
INSERT INTO `sys_dict_item` VALUES ('29ef8979991c92aa', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrow-circle-left', '8327faa0b36efb79', 464, 'fa-arrow-circle-left', 'fa fa-arrow-circle-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('2a0af413985edd78', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-heart-o', '8327faa0b36efb79', 526, 'fa-heart-o', 'fa fa-heart-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('2a65a81e895b03fb', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-chevron-circle-up', '8327faa0b36efb79', 490, 'fa-chevron-circle-up', 'fa fa-chevron-circle-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('2bd486ff24622434', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-share-square-o', '8327faa0b36efb79', 250, 'fa-share-square-o', 'fa fa-share-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('2bd61639947ff461', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-rub', '8327faa0b36efb79', 413, 'fa-rub', 'fa fa-rub', NULL);
INSERT INTO `sys_dict_item` VALUES ('2c14820e758d77f7', 1593385826, '49ba59abbe56e057', b'0', NULL, 1593385826, '49ba59abbe56e057', '邮件推送', '24c8f0997bdde222', 1, '邮件推送', '1', NULL);
INSERT INTO `sys_dict_item` VALUES ('2c6e33197e93a758', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-ambulance', '8327faa0b36efb79', 523, 'fa-ambulance', 'fa fa-ambulance', NULL);
INSERT INTO `sys_dict_item` VALUES ('2cadbacfb1b89e56', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-chevron-circle-down', '8327faa0b36efb79', 487, 'fa-chevron-circle-down', 'fa fa-chevron-circle-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('2d41a3f0c0392aa6', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-flask', '8327faa0b36efb79', 132, 'fa-flask', 'fa fa-flask', NULL);
INSERT INTO `sys_dict_item` VALUES ('2d6fddc7308bdc86', 1593310078, '49ba59abbe56e057', b'0', NULL, 1593310078, '49ba59abbe56e057', 'unicode编码', '57616e9f77c65a1f', 2, 'unicode', 'unicode', NULL);
INSERT INTO `sys_dict_item` VALUES ('2da891975fb4daab', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-share-alt-square', '8327faa0b36efb79', 607, 'fa-share-alt-square', 'fa fa-share-alt-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('2da8e19ae21d1bb6', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cc-discover', '8327faa0b36efb79', 392, 'fa-cc-discover', 'fa fa-cc-discover', NULL);
INSERT INTO `sys_dict_item` VALUES ('2dabad78f0d9ee2c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-caret-square-o-left', '8327faa0b36efb79', 46, 'fa-caret-square-o-left', 'fa fa-caret-square-o-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('2e6578ac20da0a55', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-linux', '8327faa0b36efb79', 588, 'fa-linux', 'fa fa-linux', NULL);
INSERT INTO `sys_dict_item` VALUES ('3086baf30de88dff', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-youtube', '8327faa0b36efb79', 642, 'fa-youtube', 'fa fa-youtube', NULL);
INSERT INTO `sys_dict_item` VALUES ('30a657f6e1053fd9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-pie-chart', '8327faa0b36efb79', 213, 'fa-pie-chart', 'fa fa-pie-chart', NULL);
INSERT INTO `sys_dict_item` VALUES ('30c94fb8bc9fd0d2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-search', '8327faa0b36efb79', 240, 'fa-search', 'fa fa-search', NULL);
INSERT INTO `sys_dict_item` VALUES ('30e406326f51f0fd', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bars', '8327faa0b36efb79', 16, 'fa-bars', 'fa fa-bars', NULL);
INSERT INTO `sys_dict_item` VALUES ('31ce12dc0404c9b3', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-square-o', '8327faa0b36efb79', 390, 'fa-square-o', 'fa fa-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('32114a7e858fab28', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-share-square', '8327faa0b36efb79', 249, 'fa-share-square', 'fa fa-share-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('321dad3f0e0f353b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-hand-o-down', '8327faa0b36efb79', 495, 'fa-hand-o-down', 'fa fa-hand-o-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('328e54b7d8bd94ce', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-area-chart', '8327faa0b36efb79', 400, 'fa-area-chart', 'fa fa-area-chart', NULL);
INSERT INTO `sys_dict_item` VALUES ('32bd959e40a8b295', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-server', '8327faa0b36efb79', 245, 'fa-server', 'fa fa-server', NULL);
INSERT INTO `sys_dict_item` VALUES ('32e3cb6197b3339a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-code-fork', '8327faa0b36efb79', 69, 'fa-code-fork', 'fa fa-code-fork', NULL);
INSERT INTO `sys_dict_item` VALUES ('33375d80c6daf420', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-image-o', '8327faa0b36efb79', 114, 'fa-file-image-o', 'fa fa-file-image-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('3368c138adf80a07', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-eyedropper', '8327faa0b36efb79', 106, 'fa-eyedropper', 'fa fa-eyedropper', NULL);
INSERT INTO `sys_dict_item` VALUES ('33a841c85980c1e2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-search-minus', '8327faa0b36efb79', 241, 'fa-search-minus', 'fa fa-search-minus', NULL);
INSERT INTO `sys_dict_item` VALUES ('33b4280707cf3aa3', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-youtube-square', '8327faa0b36efb79', 644, 'fa-youtube-square', 'fa fa-youtube-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('33e8aeface76c46c', 1592705701, '49ba59abbe56e057', b'0', NULL, 1592706471, '49ba59abbe56e057', 'push测试快捷入口', 'a9f3986d7fd688a4', 4, 'push测试', '0bc916517931ec27', NULL);
INSERT INTO `sys_dict_item` VALUES ('3409403a2a26ac23', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-text-height', '8327faa0b36efb79', 425, 'fa-text-height', 'fa fa-text-height', NULL);
INSERT INTO `sys_dict_item` VALUES ('342f113d1ce3221d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-leaf', '8327faa0b36efb79', 166, 'fa-leaf', 'fa fa-leaf', NULL);
INSERT INTO `sys_dict_item` VALUES ('34bb1806eb80837b', 1592697405, '49ba59abbe56e057', b'0', NULL, 1592697405, '49ba59abbe56e057', '第一行显示', '14f7933e647d4368', 1, '第一项', '1.将启动和停止功能修改为多选启动停止', NULL);
INSERT INTO `sys_dict_item` VALUES ('354baa72383c3d41', 1592705677, '49ba59abbe56e057', b'0', NULL, 1592706464, '49ba59abbe56e057', '文件管理快捷入口', 'a9f3986d7fd688a4', 3, '文件管理', '0bc916517931ec27', NULL);
INSERT INTO `sys_dict_item` VALUES ('3655d27fa7036c3a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-ellipsis-v', '8327faa0b36efb79', 93, 'fa-ellipsis-v', 'fa fa-ellipsis-v', NULL);
INSERT INTO `sys_dict_item` VALUES ('36d1d6d540ab5317', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-video-camera', '8327faa0b36efb79', 327, 'fa-video-camera', 'fa fa-video-camera', NULL);
INSERT INTO `sys_dict_item` VALUES ('36e2f3b776a29551', 1593216123, '49ba59abbe56e057', b'0', NULL, 1593216123, '49ba59abbe56e057', '默认请求', '62c376815cf83ce1', 1, 'defaultRequest', 'indi.uhyils.pojo.request.base.DefaultRequest', NULL);
INSERT INTO `sys_dict_item` VALUES ('36f73986f5b584d8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-long-arrow-right', '8327faa0b36efb79', 501, 'fa-long-arrow-right', 'fa fa-long-arrow-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('373732b92db7d891', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-puzzle-piece', '8327faa0b36efb79', 222, 'fa-puzzle-piece', 'fa fa-puzzle-piece', NULL);
INSERT INTO `sys_dict_item` VALUES ('373ac8c8f3a1ac67', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-skype', '8327faa0b36efb79', 611, 'fa-skype', 'fa fa-skype', NULL);
INSERT INTO `sys_dict_item` VALUES ('3794a3b06fb1c076', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sort-asc', '8327faa0b36efb79', 266, 'fa-sort-asc', 'fa fa-sort-asc', NULL);
INSERT INTO `sys_dict_item` VALUES ('379e3687ce7de9c7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-random', '8327faa0b36efb79', 522, 'fa-random', 'fa fa-random', NULL);
INSERT INTO `sys_dict_item` VALUES ('37b6830159934ad8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrows', '8327faa0b36efb79', 475, 'fa-arrows', 'fa fa-arrows', NULL);
INSERT INTO `sys_dict_item` VALUES ('38276361ceea5326', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-key', '8327faa0b36efb79', 162, 'fa-key', 'fa fa-key', NULL);
INSERT INTO `sys_dict_item` VALUES ('38778d6371587681', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-paw', '8327faa0b36efb79', 205, 'fa-paw', 'fa fa-paw', NULL);
INSERT INTO `sys_dict_item` VALUES ('39475d130815bf81', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-qq', '8327faa0b36efb79', 600, 'fa-qq', 'fa fa-qq', NULL);
INSERT INTO `sys_dict_item` VALUES ('39581c7d4ccba5c6', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-behance', '8327faa0b36efb79', 538, 'fa-behance', 'fa fa-behance', NULL);
INSERT INTO `sys_dict_item` VALUES ('39cc7af6647f3aa4', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrows-h', '8327faa0b36efb79', 6, 'fa-arrows-h', 'fa fa-arrows-h', NULL);
INSERT INTO `sys_dict_item` VALUES ('3aa30151f34778e9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-plus-square', '8327faa0b36efb79', 387, 'fa-plus-square', 'fa fa-plus-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('3b449bca3e6b3845', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-minus-circle', '8327faa0b36efb79', 190, 'fa-minus-circle', 'fa fa-minus-circle', NULL);
INSERT INTO `sys_dict_item` VALUES ('3bbb37c5bb66f75c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-adjust', '8327faa0b36efb79', 1, 'fa-adjust', 'fa fa-adjust', NULL);
INSERT INTO `sys_dict_item` VALUES ('3c0eb6f16f415dcf', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-jsfiddle', '8327faa0b36efb79', 582, 'fa-jsfiddle', 'fa fa-jsfiddle', NULL);
INSERT INTO `sys_dict_item` VALUES ('3c20826b692bee52', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-renren', '8327faa0b36efb79', 604, 'fa-renren', 'fa fa-renren', NULL);
INSERT INTO `sys_dict_item` VALUES ('3c5b5cb075bf6531', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sign-in', '8327faa0b36efb79', 254, 'fa-sign-in', 'fa fa-sign-in', NULL);
INSERT INTO `sys_dict_item` VALUES ('3c5cbdb370e951ed', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-tachometer', '8327faa0b36efb79', 288, 'fa-tachometer', 'fa fa-tachometer', NULL);
INSERT INTO `sys_dict_item` VALUES ('3cd22918e74650da', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-vine', '8327faa0b36efb79', 631, 'fa-vine', 'fa fa-vine', NULL);
INSERT INTO `sys_dict_item` VALUES ('3ce345bab44e7c0a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-venus-double', '8327faa0b36efb79', 361, 'fa-venus-double', 'fa fa-venus-double', NULL);
INSERT INTO `sys_dict_item` VALUES ('3d2d5ca87e7f1fda', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-crosshairs', '8327faa0b36efb79', 81, 'fa-crosshairs', 'fa fa-crosshairs', NULL);
INSERT INTO `sys_dict_item` VALUES ('3d41a8ac3b4f6c6a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-jpy', '8327faa0b36efb79', 405, 'fa-jpy', 'fa fa-jpy', NULL);
INSERT INTO `sys_dict_item` VALUES ('3d916d2cd966eba5', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-gamepad', '8327faa0b36efb79', 139, 'fa-gamepad', 'fa fa-gamepad', NULL);
INSERT INTO `sys_dict_item` VALUES ('3dd2b5272a423ec1', 1592299479, '49ba59abbe56e057', b'1', NULL, 1592299479, '49ba59abbe56e057', '服务器内部错误', '8bfce8cad90e0d79', 6, 'ERROR', '500', NULL);
INSERT INTO `sys_dict_item` VALUES ('3e85b64ac7d6e0f2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-comment-o', '8327faa0b36efb79', 74, 'fa-comment-o', 'fa fa-comment-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('3e993fef6fc17be2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-stethoscope', '8327faa0b36efb79', 531, 'fa-stethoscope', 'fa fa-stethoscope', NULL);
INSERT INTO `sys_dict_item` VALUES ('3eb68e3d28b9c6ab', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-envelope', '8327faa0b36efb79', 94, 'fa-envelope', 'fa fa-envelope', NULL);
INSERT INTO `sys_dict_item` VALUES ('3ef0da0275ff701c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-hdd-o', '8327faa0b36efb79', 149, 'fa-hdd-o', 'fa fa-hdd-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('3f178976f70f089a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-xing', '8327faa0b36efb79', 638, 'fa-xing', 'fa fa-xing', NULL);
INSERT INTO `sys_dict_item` VALUES ('3f710d9bc91fa2fe', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-xing-square', '8327faa0b36efb79', 639, 'fa-xing-square', 'fa fa-xing-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('3f77f10fc30da290', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-coffee', '8327faa0b36efb79', 70, 'fa-coffee', 'fa fa-coffee', NULL);
INSERT INTO `sys_dict_item` VALUES ('3f86ff0455072baa', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-google-plus', '8327faa0b36efb79', 574, 'fa-google-plus', 'fa fa-google-plus', NULL);
INSERT INTO `sys_dict_item` VALUES ('3f8b702a723b8ec0', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-flag-checkered', '8327faa0b36efb79', 129, 'fa-flag-checkered', 'fa fa-flag-checkered', NULL);
INSERT INTO `sys_dict_item` VALUES ('3fa4218e3a59d54c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-check-square-o', '8327faa0b36efb79', 57, 'fa-check-square-o', 'fa fa-check-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('3fb23975f30cf355', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-wrench', '8327faa0b36efb79', 334, 'fa-wrench', 'fa fa-wrench', NULL);
INSERT INTO `sys_dict_item` VALUES ('3fc19c0c0678fda7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrows-alt', '8327faa0b36efb79', 503, 'fa-arrows-alt', 'fa fa-arrows-alt', NULL);
INSERT INTO `sys_dict_item` VALUES ('411d128d15af9c69', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-wheelchair', '8327faa0b36efb79', 533, 'fa-wheelchair', 'fa fa-wheelchair', NULL);
INSERT INTO `sys_dict_item` VALUES ('415dcb70c056a235', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-git-square', '8327faa0b36efb79', 568, 'fa-git-square', 'fa fa-git-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('43b54515a7db1b9f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-google-wallet', '8327faa0b36efb79', 576, 'fa-google-wallet', 'fa fa-google-wallet', NULL);
INSERT INTO `sys_dict_item` VALUES ('43d308d182bc1de6', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-space-shuttle', '8327faa0b36efb79', 345, 'fa-space-shuttle', 'fa fa-space-shuttle', NULL);
INSERT INTO `sys_dict_item` VALUES ('43dd5502f31e3c5b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-superscript', '8327faa0b36efb79', 428, 'fa-superscript', 'fa fa-superscript', NULL);
INSERT INTO `sys_dict_item` VALUES ('4474adf497f9d1fd', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-desktop', '8327faa0b36efb79', 87, 'fa-desktop', 'fa fa-desktop', NULL);
INSERT INTO `sys_dict_item` VALUES ('44d546f8762d7c93', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sliders', '8327faa0b36efb79', 258, 'fa-sliders', 'fa fa-sliders', NULL);
INSERT INTO `sys_dict_item` VALUES ('4552440865ac0126', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-eraser', '8327faa0b36efb79', 435, 'fa-eraser', 'fa fa-eraser', NULL);
INSERT INTO `sys_dict_item` VALUES ('458ca0e2fd731e80', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-long-arrow-down', '8327faa0b36efb79', 499, 'fa-long-arrow-down', 'fa fa-long-arrow-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('4689c381291766dd', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-skyatlas', '8327faa0b36efb79', 610, 'fa-skyatlas', 'fa fa-skyatlas', NULL);
INSERT INTO `sys_dict_item` VALUES ('46d045c8248d190a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-rss', '8327faa0b36efb79', 238, 'fa-rss', 'fa fa-rss', NULL);
INSERT INTO `sys_dict_item` VALUES ('4834ce711435b335', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-pdf-o', '8327faa0b36efb79', 371, 'fa-file-pdf-o', 'fa fa-file-pdf-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('4975fc748c90965d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-facebook-square', '8327faa0b36efb79', 563, 'fa-facebook-square', 'fa fa-facebook-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('49cc211d6f803c04', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-circle-o', '8327faa0b36efb79', 383, 'fa-circle-o', 'fa fa-circle-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('4a248dd3202b7ea8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-line-chart', '8327faa0b36efb79', 176, 'fa-line-chart', 'fa fa-line-chart', NULL);
INSERT INTO `sys_dict_item` VALUES ('4a30b52b844186a7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-mars-stroke', '8327faa0b36efb79', 353, 'fa-mars-stroke', 'fa fa-mars-stroke', NULL);
INSERT INTO `sys_dict_item` VALUES ('4a5f3f1f57c80acf', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-paper-plane', '8327faa0b36efb79', 203, 'fa-paper-plane', 'fa fa-paper-plane', NULL);
INSERT INTO `sys_dict_item` VALUES ('4a9b02746a059862', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-dot-circle-o', '8327faa0b36efb79', 384, 'fa-dot-circle-o', 'fa fa-dot-circle-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('4ac36a98c798fb07', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-mars-stroke-h', '8327faa0b36efb79', 354, 'fa-mars-stroke-h', 'fa fa-mars-stroke-h', NULL);
INSERT INTO `sys_dict_item` VALUES ('4acf040ef440862d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-caret-left', '8327faa0b36efb79', 480, 'fa-caret-left', 'fa fa-caret-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('4ad96cb5ee569c4d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-calendar', '8327faa0b36efb79', 40, 'fa-calendar', 'fa fa-calendar', NULL);
INSERT INTO `sys_dict_item` VALUES ('4aecc5ea3d181c9d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-excel-o', '8327faa0b36efb79', 367, 'fa-file-excel-o', 'fa fa-file-excel-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('4b26b80de063c839', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-yelp', '8327faa0b36efb79', 641, 'fa-yelp', 'fa fa-yelp', NULL);
INSERT INTO `sys_dict_item` VALUES ('4b9897c37dfc4064', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-minus-square', '8327faa0b36efb79', 191, 'fa-minus-square', 'fa fa-minus-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('4bac31309a617268', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-wheelchair', '8327faa0b36efb79', 349, 'fa-wheelchair', 'fa fa-wheelchair', NULL);
INSERT INTO `sys_dict_item` VALUES ('4bae533343cd7a7a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bitbucket-square', '8327faa0b36efb79', 541, 'fa-bitbucket-square', 'fa fa-bitbucket-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('4c0719746f1fba5c', 1593391294, '49ba59abbe56e057', b'0', NULL, 1593391294, '49ba59abbe56e057', '推送用', '62c376815cf83ce1', 3, 'CronRequest', 'indi.uhyils.pojo.request.CronRequest', NULL);
INSERT INTO `sys_dict_item` VALUES ('4c55ce692bb1c125', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cab', '8327faa0b36efb79', 38, 'fa-cab', 'fa fa-cab', NULL);
INSERT INTO `sys_dict_item` VALUES ('4c59914e9e9f5dcc', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-map-marker', '8327faa0b36efb79', 185, 'fa-map-marker', 'fa fa-map-marker', NULL);
INSERT INTO `sys_dict_item` VALUES ('4c79c0eb9fc1a1d3', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-maxcdn', '8327faa0b36efb79', 589, 'fa-maxcdn', 'fa fa-maxcdn', NULL);
INSERT INTO `sys_dict_item` VALUES ('4c8e6f9b843841a1', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-try', '8327faa0b36efb79', 414, 'fa-try', 'fa fa-try', NULL);
INSERT INTO `sys_dict_item` VALUES ('4ca8e98866f30ef1', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bug', '8327faa0b36efb79', 32, 'fa-bug', 'fa fa-bug', NULL);
INSERT INTO `sys_dict_item` VALUES ('4d4b917ed5f54b85', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-graduation-cap', '8327faa0b36efb79', 147, 'fa-graduation-cap', 'fa fa-graduation-cap', NULL);
INSERT INTO `sys_dict_item` VALUES ('4e4c3033b4f39b25', 1593310069, '49ba59abbe56e057', b'0', NULL, 1593310069, '49ba59abbe56e057', 'utf-8编码', '57616e9f77c65a1f', 1, 'utf-8', 'utf-8', NULL);
INSERT INTO `sys_dict_item` VALUES ('4f093fc7b03e0e55', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bicycle', '8327faa0b36efb79', 23, 'fa-bicycle', 'fa fa-bicycle', NULL);
INSERT INTO `sys_dict_item` VALUES ('4f43d0e378259878', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-life-bouy', '8327faa0b36efb79', 171, 'fa-life-bouy', 'fa fa-life-bouy', NULL);
INSERT INTO `sys_dict_item` VALUES ('4fd149440a2709f1', 1592697342, '49ba59abbe56e057', b'0', NULL, 1592697342, '49ba59abbe56e057', '项目包含的功能', '231a95087199851f', 3, '包含功能', '1.用户-角色-部门-权限-菜单 模型管理 <br>2.日志,服务,服务器,JVM管理<br> 3.软件管理-redis,zookeeper<br> 4.外界API获取<br> 5.消息推送功能<br> 6.智能算法模块<br> 7.整合智能算法-> 模拟物联网', NULL);
INSERT INTO `sys_dict_item` VALUES ('4fef67de02f4fe48', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-fast-forward', '8327faa0b36efb79', 509, 'fa-fast-forward', 'fa fa-fast-forward', NULL);
INSERT INTO `sys_dict_item` VALUES ('5035ab8f38f64368', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-download', '8327faa0b36efb79', 90, 'fa-download', 'fa fa-download', NULL);
INSERT INTO `sys_dict_item` VALUES ('50c439c3211c4b92', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-volume-up', '8327faa0b36efb79', 330, 'fa-volume-up', 'fa fa-volume-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('51337a2140991459', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-hotel', '8327faa0b36efb79', 156, 'fa-hotel', 'fa fa-hotel', NULL);
INSERT INTO `sys_dict_item` VALUES ('51dd88b5a703185f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-microphone-slash', '8327faa0b36efb79', 188, 'fa-microphone-slash', 'fa fa-microphone-slash', NULL);
INSERT INTO `sys_dict_item` VALUES ('520b29031f03e842', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-square', '8327faa0b36efb79', 389, 'fa-square', 'fa fa-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('529330857f459d04', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bookmark', '8327faa0b36efb79', 29, 'fa-bookmark', 'fa fa-bookmark', NULL);
INSERT INTO `sys_dict_item` VALUES ('530d076f7aa05dbc', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-camera-retro', '8327faa0b36efb79', 43, 'fa-camera-retro', 'fa fa-camera-retro', NULL);
INSERT INTO `sys_dict_item` VALUES ('53205dc6d6126cbc', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-word-o', '8327faa0b36efb79', 122, 'fa-file-word-o', 'fa fa-file-word-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('5354ef577ceff2a6', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cc-stripe', '8327faa0b36efb79', 548, 'fa-cc-stripe', 'fa fa-cc-stripe', NULL);
INSERT INTO `sys_dict_item` VALUES ('53552c87b85a43b3', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-tencent-weibo', '8327faa0b36efb79', 622, 'fa-tencent-weibo', 'fa fa-tencent-weibo', NULL);
INSERT INTO `sys_dict_item` VALUES ('53aa55e7adf135d9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cogs', '8327faa0b36efb79', 72, 'fa-cogs', 'fa fa-cogs', NULL);
INSERT INTO `sys_dict_item` VALUES ('53ad28a5f4136932', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-mobile', '8327faa0b36efb79', 193, 'fa-mobile', 'fa fa-mobile', NULL);
INSERT INTO `sys_dict_item` VALUES ('53b52c2a415f0521', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-user-plus', '8327faa0b36efb79', 323, 'fa-user-plus', 'fa fa-user-plus', NULL);
INSERT INTO `sys_dict_item` VALUES ('5498eabcd86c50c6', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-question-circle', '8327faa0b36efb79', 225, 'fa-question-circle', 'fa fa-question-circle', NULL);
INSERT INTO `sys_dict_item` VALUES ('552626f9914f1873', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-hand-o-right', '8327faa0b36efb79', 497, 'fa-hand-o-right', 'fa fa-hand-o-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('55b91f4af8a46f6e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bullhorn', '8327faa0b36efb79', 35, 'fa-bullhorn', 'fa fa-bullhorn', NULL);
INSERT INTO `sys_dict_item` VALUES ('55de26dc8523ad17', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-history', '8327faa0b36efb79', 154, 'fa-history', 'fa fa-history', NULL);
INSERT INTO `sys_dict_item` VALUES ('5613a93f3e008663', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bitbucket', '8327faa0b36efb79', 540, 'fa-bitbucket', 'fa fa-bitbucket', NULL);
INSERT INTO `sys_dict_item` VALUES ('562e9b7726e90b7e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-powerpoint-o', '8327faa0b36efb79', 372, 'fa-file-powerpoint-o', 'fa fa-file-powerpoint-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('563f86957956d1d1', 1593216015, '49ba59abbe56e057', b'0', NULL, 1593216015, '49ba59abbe56e057', '包含角色借口一系列的方法', 'd43f938a97b62a6a', 1, '测试-角色接口', 'RoleService', NULL);
INSERT INTO `sys_dict_item` VALUES ('5770b270cf6ccaf3', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-search-plus', '8327faa0b36efb79', 242, 'fa-search-plus', 'fa fa-search-plus', NULL);
INSERT INTO `sys_dict_item` VALUES ('58b1e13d3daea454', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-laptop', '8327faa0b36efb79', 165, 'fa-laptop', 'fa fa-laptop', NULL);
INSERT INTO `sys_dict_item` VALUES ('59296b28d351e30c', 1592697466, '49ba59abbe56e057', b'0', NULL, 1592697485, '49ba59abbe56e057', '第四行显示', '14f7933e647d4368', 4, '第四项', '4.后来的计划中 我想将人工智能与物联网融合进去', NULL);
INSERT INTO `sys_dict_item` VALUES ('5981a78226d85db1', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-codepen', '8327faa0b36efb79', 550, 'fa-codepen', 'fa fa-codepen', NULL);
INSERT INTO `sys_dict_item` VALUES ('59c7ad892d9541da', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-street-view', '8327faa0b36efb79', 283, 'fa-street-view', 'fa fa-street-view', NULL);
INSERT INTO `sys_dict_item` VALUES ('5a973507e20508c8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-compress', '8327faa0b36efb79', 505, 'fa-compress', 'fa fa-compress', NULL);
INSERT INTO `sys_dict_item` VALUES ('5aa9c7445bd7e12c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file', '8327faa0b36efb79', 363, 'fa-file', 'fa fa-file', NULL);
INSERT INTO `sys_dict_item` VALUES ('5b76d5448028b8b7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrow-down', '8327faa0b36efb79', 471, 'fa-arrow-down', 'fa fa-arrow-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('5b900aecf67a9392', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-mail-reply', '8327faa0b36efb79', 182, 'fa-mail-reply', 'fa fa-mail-reply', NULL);
INSERT INTO `sys_dict_item` VALUES ('5c238485ca3911c7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-plus-square-o', '8327faa0b36efb79', 388, 'fa-plus-square-o', 'fa fa-plus-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('5c3ab484f8227149', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-align-justify', '8327faa0b36efb79', 416, 'fa-align-justify', 'fa fa-align-justify', NULL);
INSERT INTO `sys_dict_item` VALUES ('5c47e2c691c80650', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-list-ol', '8327faa0b36efb79', 447, 'fa-list-ol', 'fa fa-list-ol', NULL);
INSERT INTO `sys_dict_item` VALUES ('5ca0889799d2b1ee', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-angle-down', '8327faa0b36efb79', 459, 'fa-angle-down', 'fa fa-angle-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('5d1fe6be2d1113ba', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-moon-o', '8327faa0b36efb79', 196, 'fa-moon-o', 'fa fa-moon-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('5d22d3b59f71e0b4', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-mars', '8327faa0b36efb79', 351, 'fa-mars', 'fa fa-mars', NULL);
INSERT INTO `sys_dict_item` VALUES ('5d2fcbced713906c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-circle-o-notch', '8327faa0b36efb79', 377, 'fa-circle-o-notch', 'fa fa-circle-o-notch', NULL);
INSERT INTO `sys_dict_item` VALUES ('5d8b1a32f91f8061', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-copyright', '8327faa0b36efb79', 78, 'fa-copyright', 'fa fa-copyright', NULL);
INSERT INTO `sys_dict_item` VALUES ('5dd487bfd210d909', 1592705617, '49ba59abbe56e057', b'0', NULL, 1592706447, '49ba59abbe56e057', 'jvm管理快捷入口', 'a9f3986d7fd688a4', 1, 'JVM管理', '0bc916517931ec27', NULL);
INSERT INTO `sys_dict_item` VALUES ('5ee20f5e204f06ca', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-comment', '8327faa0b36efb79', 73, 'fa-comment', 'fa fa-comment', NULL);
INSERT INTO `sys_dict_item` VALUES ('5eec06e06f5dd3f7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-pagelines', '8327faa0b36efb79', 593, 'fa-pagelines', 'fa fa-pagelines', NULL);
INSERT INTO `sys_dict_item` VALUES ('5fa3c05e06ef7a84', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-check-square', '8327faa0b36efb79', 380, 'fa-check-square', 'fa fa-check-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('5fb6865590aed3b0', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-windows', '8327faa0b36efb79', 636, 'fa-windows', 'fa fa-windows', NULL);
INSERT INTO `sys_dict_item` VALUES ('60cbbc7dc94357af', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-pinterest-square', '8327faa0b36efb79', 599, 'fa-pinterest-square', 'fa fa-pinterest-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('6113781aafff97f2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-plus-square', '8327faa0b36efb79', 218, 'fa-plus-square', 'fa fa-plus-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('6211b1a0414dd62c', 1593473585, '49ba59abbe56e057', b'0', NULL, 1593473585, '49ba59abbe56e057', '晚上6:00', '4119bf79166ebb2b', 6, '晚上6:00', '0 0 18 * * ?', NULL);
INSERT INTO `sys_dict_item` VALUES ('62953ef331a48e64', 1592299413, '49ba59abbe56e057', b'1', NULL, 1592299413, '49ba59abbe56e057', '没有权限', '8bfce8cad90e0d79', 3, 'NONE_AUTH_ERROR', '401', NULL);
INSERT INTO `sys_dict_item` VALUES ('62aef287579f4ef2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-check', '8327faa0b36efb79', 53, 'fa-check', 'fa fa-check', NULL);
INSERT INTO `sys_dict_item` VALUES ('6303a36eff619916', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cart-plus', '8327faa0b36efb79', 50, 'fa-cart-plus', 'fa fa-cart-plus', NULL);
INSERT INTO `sys_dict_item` VALUES ('63274d2308d9de50', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-folder-o', '8327faa0b36efb79', 134, 'fa-folder-o', 'fa fa-folder-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('6370cac0d33ebd93', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-heart-o', '8327faa0b36efb79', 152, 'fa-heart-o', 'fa fa-heart-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('63805083b3bb21bd', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-circle-thin', '8327faa0b36efb79', 350, 'fa-circle-thin', 'fa fa-circle-thin', NULL);
INSERT INTO `sys_dict_item` VALUES ('63a7b3c0df150b1e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-circle', '8327faa0b36efb79', 382, 'fa-circle', 'fa fa-circle', NULL);
INSERT INTO `sys_dict_item` VALUES ('63cf607506159785', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-instagram', '8327faa0b36efb79', 579, 'fa-instagram', 'fa fa-instagram', NULL);
INSERT INTO `sys_dict_item` VALUES ('63de01f99144dd8f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-edit', '8327faa0b36efb79', 91, 'fa-edit', 'fa fa-edit', NULL);
INSERT INTO `sys_dict_item` VALUES ('63e76310cc343ca4', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-paper-plane-o', '8327faa0b36efb79', 204, 'fa-paper-plane-o', 'fa fa-paper-plane-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('65621a93143c8e11', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-lastfm-square', '8327faa0b36efb79', 584, 'fa-lastfm-square', 'fa fa-lastfm-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('656e749480a537f7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-question', '8327faa0b36efb79', 224, 'fa-question', 'fa fa-question', NULL);
INSERT INTO `sys_dict_item` VALUES ('658b4a387f31aa57', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-ship', '8327faa0b36efb79', 252, 'fa-ship', 'fa fa-ship', NULL);
INSERT INTO `sys_dict_item` VALUES ('65edc11441b0856f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-send-o', '8327faa0b36efb79', 244, 'fa-send-o', 'fa fa-send-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('6643fa7563a5a22b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-files-o', '8327faa0b36efb79', 432, 'fa-files-o', 'fa fa-files-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('6653363d88cf6e70', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-steam-square', '8327faa0b36efb79', 619, 'fa-steam-square', 'fa fa-steam-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('667d0398bef2d235', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-check-circle', '8327faa0b36efb79', 54, 'fa-check-circle', 'fa fa-check-circle', NULL);
INSERT INTO `sys_dict_item` VALUES ('66aa8e727739d23f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-caret-down', '8327faa0b36efb79', 479, 'fa-caret-down', 'fa fa-caret-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('66ab8151896ef170', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-plus', '8327faa0b36efb79', 216, 'fa-plus', 'fa fa-plus', NULL);
INSERT INTO `sys_dict_item` VALUES ('66ae7a4c54348990', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-mars-double', '8327faa0b36efb79', 352, 'fa-mars-double', 'fa fa-mars-double', NULL);
INSERT INTO `sys_dict_item` VALUES ('66d57667580bbdc9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-text', '8327faa0b36efb79', 438, 'fa-file-text', 'fa fa-file-text', NULL);
INSERT INTO `sys_dict_item` VALUES ('66e7dcf1339dd248', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-volume-down', '8327faa0b36efb79', 520, 'fa-volume-down', 'fa fa-volume-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('66f06bf5e3e3e740', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-envelope-square', '8327faa0b36efb79', 96, 'fa-envelope-square', 'fa fa-envelope-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('676f919b6d70be7d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-gavel', '8327faa0b36efb79', 140, 'fa-gavel', 'fa fa-gavel', NULL);
INSERT INTO `sys_dict_item` VALUES ('686d11c2cd1b3f84', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-medium', '8327faa0b36efb79', 591, 'fa-medium', 'fa fa-medium', NULL);
INSERT INTO `sys_dict_item` VALUES ('6919e8b3d11d85cf', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-weibo', '8327faa0b36efb79', 634, 'fa-weibo', 'fa fa-weibo', NULL);
INSERT INTO `sys_dict_item` VALUES ('694af3699441409d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-pied-piper-alt', '8327faa0b36efb79', 596, 'fa-pied-piper-alt', 'fa fa-pied-piper-alt', NULL);
INSERT INTO `sys_dict_item` VALUES ('695c3f346128b58c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-reorder', '8327faa0b36efb79', 232, 'fa-reorder', 'fa fa-reorder', NULL);
INSERT INTO `sys_dict_item` VALUES ('6a1a8d877ebd48ec', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-th-large', '8327faa0b36efb79', 453, 'fa-th-large', 'fa fa-th-large', NULL);
INSERT INTO `sys_dict_item` VALUES ('6a30af7728fe6266', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bank', '8327faa0b36efb79', 12, 'fa-bank', 'fa fa-bank', NULL);
INSERT INTO `sys_dict_item` VALUES ('6a5810d92e03a15d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-meh-o', '8327faa0b36efb79', 186, 'fa-meh-o', 'fa fa-meh-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('6a91b3d496cf99a5', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-spotify', '8327faa0b36efb79', 615, 'fa-spotify', 'fa fa-spotify', NULL);
INSERT INTO `sys_dict_item` VALUES ('6b270f28b6b54386', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-strikethrough', '8327faa0b36efb79', 426, 'fa-strikethrough', 'fa fa-strikethrough', NULL);
INSERT INTO `sys_dict_item` VALUES ('6b6e6c519ddf9c0e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-male', '8327faa0b36efb79', 184, 'fa-male', 'fa fa-male', NULL);
INSERT INTO `sys_dict_item` VALUES ('6c3fc3ba34b13372', 1592299383, '49ba59abbe56e057', b'1', NULL, 1592299383, '49ba59abbe56e057', '前台传值错误', '8bfce8cad90e0d79', 2, 'REQUEST_PARAM_ERROR', '400', NULL);
INSERT INTO `sys_dict_item` VALUES ('6ca41b0047b93033', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-warning', '8327faa0b36efb79', 331, 'fa-warning', 'fa fa-warning', NULL);
INSERT INTO `sys_dict_item` VALUES ('6cb3b0b90974de24', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-close', '8327faa0b36efb79', 64, 'fa-close', 'fa fa-close', NULL);
INSERT INTO `sys_dict_item` VALUES ('6cdc9ff10cb01c17', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-header', '8327faa0b36efb79', 422, 'fa-header', 'fa fa-header', NULL);
INSERT INTO `sys_dict_item` VALUES ('6cebad03ce30e08e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-columns', '8327faa0b36efb79', 434, 'fa-columns', 'fa fa-columns', NULL);
INSERT INTO `sys_dict_item` VALUES ('6da8a01ed3a6d113', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-upload', '8327faa0b36efb79', 321, 'fa-upload', 'fa fa-upload', NULL);
INSERT INTO `sys_dict_item` VALUES ('6dc5fe29d8878ca6', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-paragraph', '8327faa0b36efb79', 450, 'fa-paragraph', 'fa fa-paragraph', NULL);
INSERT INTO `sys_dict_item` VALUES ('6dd895e3e08996f8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-minus-square', '8327faa0b36efb79', 385, 'fa-minus-square', 'fa fa-minus-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('6e6bcbd9d4dc7695', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-birthday-cake', '8327faa0b36efb79', 25, 'fa-birthday-cake', 'fa fa-birthday-cake', NULL);
INSERT INTO `sys_dict_item` VALUES ('6e6fc167414bcd2a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-at', '8327faa0b36efb79', 9, 'fa-at', 'fa fa-at', NULL);
INSERT INTO `sys_dict_item` VALUES ('6ea3eea5de3f074a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-paperclip', '8327faa0b36efb79', 449, 'fa-paperclip', 'fa fa-paperclip', NULL);
INSERT INTO `sys_dict_item` VALUES ('6ecb96de5d2ced95', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sitemap', '8327faa0b36efb79', 257, 'fa-sitemap', 'fa fa-sitemap', NULL);
INSERT INTO `sys_dict_item` VALUES ('6ef3092c45ba3591', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cloud-download', '8327faa0b36efb79', 66, 'fa-cloud-download', 'fa fa-cloud-download', NULL);
INSERT INTO `sys_dict_item` VALUES ('6f2c219de3873140', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-photo-o', '8327faa0b36efb79', 117, 'fa-file-photo-o', 'fa fa-file-photo-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('6f4d613c0458f2c2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-hand-o-up', '8327faa0b36efb79', 498, 'fa-hand-o-up', 'fa fa-hand-o-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('70f8f3d5595bf45d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-stumbleupon-circle', '8327faa0b36efb79', 621, 'fa-stumbleupon-circle', 'fa fa-stumbleupon-circle', NULL);
INSERT INTO `sys_dict_item` VALUES ('729b05e3c78b2c7c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-leanpub', '8327faa0b36efb79', 585, 'fa-leanpub', 'fa fa-leanpub', NULL);
INSERT INTO `sys_dict_item` VALUES ('731016e170eda989', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-anchor', '8327faa0b36efb79', 2, 'fa-anchor', 'fa fa-anchor', NULL);
INSERT INTO `sys_dict_item` VALUES ('7321e2bbb552ccc2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrows', '8327faa0b36efb79', 5, 'fa-arrows', 'fa fa-arrows', NULL);
INSERT INTO `sys_dict_item` VALUES ('733498d7490823cf', 1592697219, '49ba59abbe56e057', b'0', NULL, 1592697219, '49ba59abbe56e057', '整个项目的名称', '231a95087199851f', 1, '项目名称', 'my', NULL);
INSERT INTO `sys_dict_item` VALUES ('73559b0436c58ffc', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-circle-o', '8327faa0b36efb79', 60, 'fa-circle-o', 'fa fa-circle-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('7362d676b26b5199', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-binoculars', '8327faa0b36efb79', 24, 'fa-binoculars', 'fa fa-binoculars', NULL);
INSERT INTO `sys_dict_item` VALUES ('73684342e4350f43', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-word-o', '8327faa0b36efb79', 375, 'fa-file-word-o', 'fa fa-file-word-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('7408e541d1e05925', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-underline', '8327faa0b36efb79', 423, 'fa-underline', 'fa fa-underline', NULL);
INSERT INTO `sys_dict_item` VALUES ('742831f69c1ab114', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cutlery', '8327faa0b36efb79', 84, 'fa-cutlery', 'fa fa-cutlery', NULL);
INSERT INTO `sys_dict_item` VALUES ('74bde967568d70a6', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-check-circle-o', '8327faa0b36efb79', 55, 'fa-check-circle-o', 'fa fa-check-circle-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('74d6c0821c32cc32', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-exclamation-triangle', '8327faa0b36efb79', 101, 'fa-exclamation-triangle', 'fa fa-exclamation-triangle', NULL);
INSERT INTO `sys_dict_item` VALUES ('7505509e6d919578', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-stop', '8327faa0b36efb79', 517, 'fa-stop', 'fa fa-stop', NULL);
INSERT INTO `sys_dict_item` VALUES ('760679af8f5b415b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-external-link-square', '8327faa0b36efb79', 103, 'fa-external-link-square', 'fa fa-external-link-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('7666f4e17ebc5d74', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sellsy', '8327faa0b36efb79', 605, 'fa-sellsy', 'fa fa-sellsy', NULL);
INSERT INTO `sys_dict_item` VALUES ('767aa57c2d72afc1', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-undo', '8327faa0b36efb79', 442, 'fa-undo', 'fa fa-undo', NULL);
INSERT INTO `sys_dict_item` VALUES ('76ceeeb93e2ae5cb', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-soundcloud', '8327faa0b36efb79', 614, 'fa-soundcloud', 'fa fa-soundcloud', NULL);
INSERT INTO `sys_dict_item` VALUES ('76ec65b036add431', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-twitch', '8327faa0b36efb79', 626, 'fa-twitch', 'fa fa-twitch', NULL);
INSERT INTO `sys_dict_item` VALUES ('783abd2daab9408f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-minus-square-o', '8327faa0b36efb79', 386, 'fa-minus-square-o', 'fa fa-minus-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('78d3004bc9130990', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-mail-forward', '8327faa0b36efb79', 181, 'fa-mail-forward', 'fa fa-mail-forward', NULL);
INSERT INTO `sys_dict_item` VALUES ('78e9ce466d9f6a68', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-clipboard', '8327faa0b36efb79', 431, 'fa-clipboard', 'fa fa-clipboard', NULL);
INSERT INTO `sys_dict_item` VALUES ('799a461fd600a162', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-pied-piper', '8327faa0b36efb79', 595, 'fa-pied-piper', 'fa fa-pied-piper', NULL);
INSERT INTO `sys_dict_item` VALUES ('79bcc98e45dd57a8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-heartbeat', '8327faa0b36efb79', 153, 'fa-heartbeat', 'fa fa-heartbeat', NULL);
INSERT INTO `sys_dict_item` VALUES ('7ab5ea80af3c8d59', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-beer', '8327faa0b36efb79', 18, 'fa-beer', 'fa fa-beer', NULL);
INSERT INTO `sys_dict_item` VALUES ('7b043f7b212c7a9f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-archive-o', '8327faa0b36efb79', 364, 'fa-file-archive-o', 'fa fa-file-archive-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('7c2b22d107392153', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-flag-o', '8327faa0b36efb79', 130, 'fa-flag-o', 'fa fa-flag-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('7c55ca2698d88eaf', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-steam', '8327faa0b36efb79', 618, 'fa-steam', 'fa fa-steam', NULL);
INSERT INTO `sys_dict_item` VALUES ('7c6bdec839e5d900', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-usd', '8327faa0b36efb79', 406, 'fa-usd', 'fa fa-usd', NULL);
INSERT INTO `sys_dict_item` VALUES ('7cc94c0282bd6a80', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-deviantart', '8327faa0b36efb79', 555, 'fa-deviantart', 'fa fa-deviantart', NULL);
INSERT INTO `sys_dict_item` VALUES ('7cd5937fac30904c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-female', '8327faa0b36efb79', 108, 'fa-female', 'fa fa-female', NULL);
INSERT INTO `sys_dict_item` VALUES ('7cde39d14b2b4ffc', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-ticket', '8327faa0b36efb79', 299, 'fa-ticket', 'fa fa-ticket', NULL);
INSERT INTO `sys_dict_item` VALUES ('7cf9e69a4a5b96b2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-heartbeat', '8327faa0b36efb79', 527, 'fa-heartbeat', 'fa fa-heartbeat', NULL);
INSERT INTO `sys_dict_item` VALUES ('7d05a1d69fec5549', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-mortar-board', '8327faa0b36efb79', 197, 'fa-mortar-board', 'fa fa-mortar-board', NULL);
INSERT INTO `sys_dict_item` VALUES ('7e32e98ddea138a8', 1592299199, '49ba59abbe56e057', b'1', NULL, 1592299390, '49ba59abbe56e057', '所有向后台的请求正常以及返回正常的均为200', '8bfce8cad90e0d79', 1, 'SUCCESS', '200', NULL);
INSERT INTO `sys_dict_item` VALUES ('7e8eb39d526d193b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bus', '8327faa0b36efb79', 338, 'fa-bus', 'fa fa-bus', NULL);
INSERT INTO `sys_dict_item` VALUES ('7ee470280ca1bdf9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-smile-o', '8327faa0b36efb79', 259, 'fa-smile-o', 'fa fa-smile-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('7f09a47e9c0a67fd', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-image-o', '8327faa0b36efb79', 368, 'fa-file-image-o', 'fa fa-file-image-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('7f0ddf7d8d1a5d01', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-terminal', '8327faa0b36efb79', 293, 'fa-terminal', 'fa fa-terminal', NULL);
INSERT INTO `sys_dict_item` VALUES ('7f4d9b223bff7a72', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-circle-o-notch', '8327faa0b36efb79', 61, 'fa-circle-o-notch', 'fa fa-circle-o-notch', NULL);
INSERT INTO `sys_dict_item` VALUES ('8055b8f53d1fcf36', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-plus-circle', '8327faa0b36efb79', 217, 'fa-plus-circle', 'fa fa-plus-circle', NULL);
INSERT INTO `sys_dict_item` VALUES ('8059d729984cb797', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-venus-mars', '8327faa0b36efb79', 362, 'fa-venus-mars', 'fa fa-venus-mars', NULL);
INSERT INTO `sys_dict_item` VALUES ('806e93f12ba59eb4', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-code-o', '8327faa0b36efb79', 366, 'fa-file-code-o', 'fa fa-file-code-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('809c7779e7bb2a91', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cc-mastercard', '8327faa0b36efb79', 546, 'fa-cc-mastercard', 'fa fa-cc-mastercard', NULL);
INSERT INTO `sys_dict_item` VALUES ('80d6f9474fe16590', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-gears', '8327faa0b36efb79', 142, 'fa-gears', 'fa fa-gears', NULL);
INSERT INTO `sys_dict_item` VALUES ('80e3d34d8195ca8a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-gear', '8327faa0b36efb79', 141, 'fa-gear', 'fa fa-gear', NULL);
INSERT INTO `sys_dict_item` VALUES ('81ae6b78e34cb648', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-h-square', '8327faa0b36efb79', 524, 'fa-h-square', 'fa fa-h-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('82b88222a71f03a9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-folder-open', '8327faa0b36efb79', 135, 'fa-folder-open', 'fa fa-folder-open', NULL);
INSERT INTO `sys_dict_item` VALUES ('839184eea31482cf', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-transgender-alt', '8327faa0b36efb79', 359, 'fa-transgender-alt', 'fa fa-transgender-alt', NULL);
INSERT INTO `sys_dict_item` VALUES ('83c63cb8a639f74f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-space-shuttle', '8327faa0b36efb79', 272, 'fa-space-shuttle', 'fa fa-space-shuttle', NULL);
INSERT INTO `sys_dict_item` VALUES ('8454d797b0038bbb', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-times', '8327faa0b36efb79', 300, 'fa-times', 'fa fa-times', NULL);
INSERT INTO `sys_dict_item` VALUES ('849cfd17758c0071', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-caret-up', '8327faa0b36efb79', 482, 'fa-caret-up', 'fa fa-caret-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('8676ee97e6a5ff77', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-plus-square', '8327faa0b36efb79', 530, 'fa-plus-square', 'fa fa-plus-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('869294e435475e26', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-angle-double-left', '8327faa0b36efb79', 456, 'fa-angle-double-left', 'fa fa-angle-double-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('86c0e5a40c87d909', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-heart', '8327faa0b36efb79', 525, 'fa-heart', 'fa fa-heart', NULL);
INSERT INTO `sys_dict_item` VALUES ('86f76f729aea659a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-medkit', '8327faa0b36efb79', 529, 'fa-medkit', 'fa fa-medkit', NULL);
INSERT INTO `sys_dict_item` VALUES ('88edd7f6fe372694', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-expand', '8327faa0b36efb79', 507, 'fa-expand', 'fa fa-expand', NULL);
INSERT INTO `sys_dict_item` VALUES ('8a1efdbe19fd1b20', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-scissors', '8327faa0b36efb79', 433, 'fa-scissors', 'fa fa-scissors', NULL);
INSERT INTO `sys_dict_item` VALUES ('8a38b019f168788f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-suitcase', '8327faa0b36efb79', 284, 'fa-suitcase', 'fa fa-suitcase', NULL);
INSERT INTO `sys_dict_item` VALUES ('8a71dfef02871d23', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-shield', '8327faa0b36efb79', 251, 'fa-shield', 'fa fa-shield', NULL);
INSERT INTO `sys_dict_item` VALUES ('8afbe3bf998f9b29', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-align-left', '8327faa0b36efb79', 417, 'fa-align-left', 'fa fa-align-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('8b47ec3d8bd047c2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-trash', '8327faa0b36efb79', 310, 'fa-trash', 'fa fa-trash', NULL);
INSERT INTO `sys_dict_item` VALUES ('8be5bec62d3a96ad', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-tag', '8327faa0b36efb79', 289, 'fa-tag', 'fa fa-tag', NULL);
INSERT INTO `sys_dict_item` VALUES ('8be6113aeced55e9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-powerpoint-o', '8327faa0b36efb79', 119, 'fa-file-powerpoint-o', 'fa fa-file-powerpoint-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('8bec752a4d9eda0f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-life-buoy', '8327faa0b36efb79', 172, 'fa-life-buoy', 'fa fa-life-buoy', NULL);
INSERT INTO `sys_dict_item` VALUES ('8c08ff2bb3b06658', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bicycle', '8327faa0b36efb79', 337, 'fa-bicycle', 'fa fa-bicycle', NULL);
INSERT INTO `sys_dict_item` VALUES ('8c19495484569e39', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-code', '8327faa0b36efb79', 68, 'fa-code', 'fa fa-code', NULL);
INSERT INTO `sys_dict_item` VALUES ('8cec16df5b815327', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-align-right', '8327faa0b36efb79', 418, 'fa-align-right', 'fa fa-align-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('8d1872687eb06acb', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-child', '8327faa0b36efb79', 58, 'fa-child', 'fa fa-child', NULL);
INSERT INTO `sys_dict_item` VALUES ('8d43cb90145abb2a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-audio-o', '8327faa0b36efb79', 365, 'fa-file-audio-o', 'fa fa-file-audio-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('8d6ed1e22079d537', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-viacoin', '8327faa0b36efb79', 629, 'fa-viacoin', 'fa fa-viacoin', NULL);
INSERT INTO `sys_dict_item` VALUES ('8e33d066745b6d21', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bookmark-o', '8327faa0b36efb79', 30, 'fa-bookmark-o', 'fa fa-bookmark-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('8edc80d69d7598b2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-list', '8327faa0b36efb79', 445, 'fa-list', 'fa fa-list', NULL);
INSERT INTO `sys_dict_item` VALUES ('8ef9cc481e7aa79c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-location-arrow', '8327faa0b36efb79', 177, 'fa-location-arrow', 'fa fa-location-arrow', NULL);
INSERT INTO `sys_dict_item` VALUES ('8f370bbe5bcb1a89', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-minus-square-o', '8327faa0b36efb79', 192, 'fa-minus-square-o', 'fa fa-minus-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('8fb719d0dc963063', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-glass', '8327faa0b36efb79', 145, 'fa-glass', 'fa fa-glass', NULL);
INSERT INTO `sys_dict_item` VALUES ('8ffbc1bc7a22090f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-angle-double-right', '8327faa0b36efb79', 457, 'fa-angle-double-right', 'fa fa-angle-double-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('900a13c0e6a2e42c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-flag', '8327faa0b36efb79', 128, 'fa-flag', 'fa fa-flag', NULL);
INSERT INTO `sys_dict_item` VALUES ('903d33b36a1a8031', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-paypal', '8327faa0b36efb79', 594, 'fa-paypal', 'fa fa-paypal', NULL);
INSERT INTO `sys_dict_item` VALUES ('904ea046319956f4', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-repeat', '8327faa0b36efb79', 441, 'fa-repeat', 'fa fa-repeat', NULL);
INSERT INTO `sys_dict_item` VALUES ('90a04afe17f1dea7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sort-numeric-asc', '8327faa0b36efb79', 269, 'fa-sort-numeric-asc', 'fa fa-sort-numeric-asc', NULL);
INSERT INTO `sys_dict_item` VALUES ('91697c08512eb970', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-fighter-jet', '8327faa0b36efb79', 109, 'fa-fighter-jet', 'fa fa-fighter-jet', NULL);
INSERT INTO `sys_dict_item` VALUES ('91f7ba3829a62527', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-reply', '8327faa0b36efb79', 233, 'fa-reply', 'fa fa-reply', NULL);
INSERT INTO `sys_dict_item` VALUES ('9231883572217b57', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cog', '8327faa0b36efb79', 71, 'fa-cog', 'fa fa-cog', NULL);
INSERT INTO `sys_dict_item` VALUES ('92c153b2f38a994a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-google-plus-square', '8327faa0b36efb79', 575, 'fa-google-plus-square', 'fa fa-google-plus-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('9315693ccbea546d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-chevron-down', '8327faa0b36efb79', 491, 'fa-chevron-down', 'fa fa-chevron-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('94907e4599881fd0', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-certificate', '8327faa0b36efb79', 52, 'fa-certificate', 'fa fa-certificate', NULL);
INSERT INTO `sys_dict_item` VALUES ('9549bcb6524496a9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-angle-double-down', '8327faa0b36efb79', 455, 'fa-angle-double-down', 'fa fa-angle-double-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('954bc6b064e0f876', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-refresh', '8327faa0b36efb79', 230, 'fa-refresh', 'fa fa-refresh', NULL);
INSERT INTO `sys_dict_item` VALUES ('9582101e9b38113b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-ship', '8327faa0b36efb79', 344, 'fa-ship', 'fa fa-ship', NULL);
INSERT INTO `sys_dict_item` VALUES ('9683532a319244b0', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-github-square', '8327faa0b36efb79', 571, 'fa-github-square', 'fa fa-github-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('975474f8a00e6d13', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bell-slash-o', '8327faa0b36efb79', 22, 'fa-bell-slash-o', 'fa fa-bell-slash-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('97adf363034ee813', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrows-v', '8327faa0b36efb79', 478, 'fa-arrows-v', 'fa fa-arrows-v', NULL);
INSERT INTO `sys_dict_item` VALUES ('983b1dd94b8a0738', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-caret-square-o-left', '8327faa0b36efb79', 484, 'fa-caret-square-o-left', 'fa fa-caret-square-o-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('99094b00d87eed52', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-list-alt', '8327faa0b36efb79', 446, 'fa-list-alt', 'fa fa-list-alt', NULL);
INSERT INTO `sys_dict_item` VALUES ('9942ce7ddedad951', 1600421475, '49ba59abbe56e057', b'0', NULL, 1600421475, '49ba59abbe56e057', '服务自动降级并发数', '497f9230e03d32ef', 1, '服务自动降级并发数', '200', NULL);
INSERT INTO `sys_dict_item` VALUES ('9973c03ecf15171c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sort-amount-desc', '8327faa0b36efb79', 265, 'fa-sort-amount-desc', 'fa fa-sort-amount-desc', NULL);
INSERT INTO `sys_dict_item` VALUES ('9974154892cd2307', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-qrcode', '8327faa0b36efb79', 223, 'fa-qrcode', 'fa fa-qrcode', NULL);
INSERT INTO `sys_dict_item` VALUES ('999ed3dca5755a49', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-subscript', '8327faa0b36efb79', 427, 'fa-subscript', 'fa fa-subscript', NULL);
INSERT INTO `sys_dict_item` VALUES ('9a0bd3f017d16dd1', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-hacker-news', '8327faa0b36efb79', 577, 'fa-hacker-news', 'fa fa-hacker-news', NULL);
INSERT INTO `sys_dict_item` VALUES ('9a58832fbb0e4ee1', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-inr', '8327faa0b36efb79', 410, 'fa-inr', 'fa fa-inr', NULL);
INSERT INTO `sys_dict_item` VALUES ('9ae536dfe59442ff', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-gift', '8327faa0b36efb79', 144, 'fa-gift', 'fa fa-gift', NULL);
INSERT INTO `sys_dict_item` VALUES ('9d5d0fe3f0233d38', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bolt', '8327faa0b36efb79', 26, 'fa-bolt', 'fa fa-bolt', NULL);
INSERT INTO `sys_dict_item` VALUES ('9e461e122cf0e251', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-thumbs-o-up', '8327faa0b36efb79', 297, 'fa-thumbs-o-up', 'fa fa-thumbs-o-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('9ea492d523c888e4', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-line-chart', '8327faa0b36efb79', 402, 'fa-line-chart', 'fa fa-line-chart', NULL);
INSERT INTO `sys_dict_item` VALUES ('9f541b1e0154a747', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-plus-square-o', '8327faa0b36efb79', 219, 'fa-plus-square-o', 'fa fa-plus-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('9f571c6d6f23f365', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-check-square-o', '8327faa0b36efb79', 381, 'fa-check-square-o', 'fa fa-check-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('9f9dca3b99affb31', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-o', '8327faa0b36efb79', 370, 'fa-file-o', 'fa fa-file-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('a02e555e3a027040', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-envelope-o', '8327faa0b36efb79', 95, 'fa-envelope-o', 'fa fa-envelope-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('a11e22d36922b2ea', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-eur', '8327faa0b36efb79', 407, 'fa-eur', 'fa fa-eur', NULL);
INSERT INTO `sys_dict_item` VALUES ('a19135e69ddf8dae', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-play-circle', '8327faa0b36efb79', 513, 'fa-play-circle', 'fa fa-play-circle', NULL);
INSERT INTO `sys_dict_item` VALUES ('a1c9011df1ded702', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-volume-down', '8327faa0b36efb79', 328, 'fa-volume-down', 'fa fa-volume-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('a2662b134f84220d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-toggle-off', '8327faa0b36efb79', 306, 'fa-toggle-off', 'fa fa-toggle-off', NULL);
INSERT INTO `sys_dict_item` VALUES ('a2d0158dbfbc5e68', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-link', '8327faa0b36efb79', 429, 'fa-link', 'fa fa-link', NULL);
INSERT INTO `sys_dict_item` VALUES ('a317c6e4d2f62c32', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-dashboard', '8327faa0b36efb79', 85, 'fa-dashboard', 'fa fa-dashboard', NULL);
INSERT INTO `sys_dict_item` VALUES ('a3368467fef23e91', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-subway', '8327faa0b36efb79', 346, 'fa-subway', 'fa fa-subway', NULL);
INSERT INTO `sys_dict_item` VALUES ('a3dd3808cfa6c13a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-database', '8327faa0b36efb79', 86, 'fa-database', 'fa fa-database', NULL);
INSERT INTO `sys_dict_item` VALUES ('a49fe5172cdac125', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cc-visa', '8327faa0b36efb79', 549, 'fa-cc-visa', 'fa fa-cc-visa', NULL);
INSERT INTO `sys_dict_item` VALUES ('a4c11bf4b0a9e4fd', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-tags', '8327faa0b36efb79', 290, 'fa-tags', 'fa fa-tags', NULL);
INSERT INTO `sys_dict_item` VALUES ('a501b8d106978ad9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-area-chart', '8327faa0b36efb79', 4, 'fa-area-chart', 'fa fa-area-chart', NULL);
INSERT INTO `sys_dict_item` VALUES ('a52efe16e80a7c22', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-send', '8327faa0b36efb79', 243, 'fa-send', 'fa fa-send', NULL);
INSERT INTO `sys_dict_item` VALUES ('a5b927261401629e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-mobile-phone', '8327faa0b36efb79', 194, 'fa-mobile-phone', 'fa fa-mobile-phone', NULL);
INSERT INTO `sys_dict_item` VALUES ('a6eb1c049b97ddc5', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-gratipay', '8327faa0b36efb79', 572, 'fa-gratipay', 'fa fa-gratipay', NULL);
INSERT INTO `sys_dict_item` VALUES ('a70b1cac2ff7fa87', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-eye-slash', '8327faa0b36efb79', 105, 'fa-eye-slash', 'fa fa-eye-slash', NULL);
INSERT INTO `sys_dict_item` VALUES ('a71fbdf5c7d13efa', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-pie-chart', '8327faa0b36efb79', 403, 'fa-pie-chart', 'fa fa-pie-chart', NULL);
INSERT INTO `sys_dict_item` VALUES ('a78dacfe477fa05a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-thumbs-down', '8327faa0b36efb79', 295, 'fa-thumbs-down', 'fa fa-thumbs-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('a7d05b26ccf7868c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrow-circle-o-up', '8327faa0b36efb79', 470, 'fa-arrow-circle-o-up', 'fa fa-arrow-circle-o-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('a7fd84d4425fdcc6', 1592697449, '49ba59abbe56e057', b'0', NULL, 1592697479, '49ba59abbe56e057', '第三行显示', '14f7933e647d4368', 3, '第三项', '3.将redis zookeeper,mq,kfk,mysql等技术融合进去', NULL);
INSERT INTO `sys_dict_item` VALUES ('a80aa6bcfce8f52e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrow-circle-down', '8327faa0b36efb79', 463, 'fa-arrow-circle-down', 'fa fa-arrow-circle-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('a8183d989a3a6031', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-rss-square', '8327faa0b36efb79', 239, 'fa-rss-square', 'fa fa-rss-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('a905eb8a2ee34d2e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-weixin', '8327faa0b36efb79', 633, 'fa-weixin', 'fa fa-weixin', NULL);
INSERT INTO `sys_dict_item` VALUES ('a9224aae76d3295e', 1592349269, '49ba59abbe56e057', b'0', NULL, 1592349269, '49ba59abbe56e057', 'win10系统', 'c66ca4e5415b47f4', 1, 'windows10', '1', NULL);
INSERT INTO `sys_dict_item` VALUES ('a9dbd1518d7c7c5a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-long-arrow-up', '8327faa0b36efb79', 502, 'fa-long-arrow-up', 'fa fa-long-arrow-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('a9dffc3c6ddfcab1', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-unlock-alt', '8327faa0b36efb79', 319, 'fa-unlock-alt', 'fa fa-unlock-alt', NULL);
INSERT INTO `sys_dict_item` VALUES ('aa495fc932c7dba8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-star-half-full', '8327faa0b36efb79', 280, 'fa-star-half-full', 'fa fa-star-half-full', NULL);
INSERT INTO `sys_dict_item` VALUES ('ab9b9ef2424016af', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-external-link', '8327faa0b36efb79', 102, 'fa-external-link', 'fa fa-external-link', NULL);
INSERT INTO `sys_dict_item` VALUES ('abac4d4ab8053407', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-floppy-o', '8327faa0b36efb79', 440, 'fa-floppy-o', 'fa fa-floppy-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('ac071372d8eb7e31', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-wheelchair', '8327faa0b36efb79', 332, 'fa-wheelchair', 'fa fa-wheelchair', NULL);
INSERT INTO `sys_dict_item` VALUES ('ad909a7b5fb5be68', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-caret-square-o-right', '8327faa0b36efb79', 47, 'fa-caret-square-o-right', 'fa fa-caret-square-o-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('addca60f0be4d605', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-eye', '8327faa0b36efb79', 104, 'fa-eye', 'fa fa-eye', NULL);
INSERT INTO `sys_dict_item` VALUES ('adf01b668d716ecb', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrow-circle-o-left', '8327faa0b36efb79', 468, 'fa-arrow-circle-o-left', 'fa fa-arrow-circle-o-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('adfb850b916fd84c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-thumbs-up', '8327faa0b36efb79', 298, 'fa-thumbs-up', 'fa fa-thumbs-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('ae5ed5e82ef63921', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-th', '8327faa0b36efb79', 452, 'fa-th', 'fa fa-th', NULL);
INSERT INTO `sys_dict_item` VALUES ('aed329d853fc45c7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrow-right', '8327faa0b36efb79', 473, 'fa-arrow-right', 'fa fa-arrow-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('aef1b859140f2fd5', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-zip-o', '8327faa0b36efb79', 123, 'fa-file-zip-o', 'fa fa-file-zip-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('af6a35276265ba6f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cc', '8327faa0b36efb79', 51, 'fa-cc', 'fa fa-cc', NULL);
INSERT INTO `sys_dict_item` VALUES ('b077e196d06d329b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrow-circle-o-down', '8327faa0b36efb79', 467, 'fa-arrow-circle-o-down', 'fa fa-arrow-circle-o-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('b08392b48081d648', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cart-arrow-down', '8327faa0b36efb79', 49, 'fa-cart-arrow-down', 'fa fa-cart-arrow-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('b0e09fa9eb592b87', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-chevron-up', '8327faa0b36efb79', 494, 'fa-chevron-up', 'fa fa-chevron-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('b15f836c0f859cbd', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-train', '8327faa0b36efb79', 347, 'fa-train', 'fa fa-train', NULL);
INSERT INTO `sys_dict_item` VALUES ('b167e24901347e5b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-wifi', '8327faa0b36efb79', 333, 'fa-wifi', 'fa fa-wifi', NULL);
INSERT INTO `sys_dict_item` VALUES ('b190651a2e34fb1c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-camera', '8327faa0b36efb79', 42, 'fa-camera', 'fa fa-camera', NULL);
INSERT INTO `sys_dict_item` VALUES ('b191165fc12b03ce', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-pdf-o', '8327faa0b36efb79', 116, 'fa-file-pdf-o', 'fa fa-file-pdf-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('b1f764530f2bf415', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-delicious', '8327faa0b36efb79', 554, 'fa-delicious', 'fa fa-delicious', NULL);
INSERT INTO `sys_dict_item` VALUES ('b260530edbbafdb6', 1592697425, '49ba59abbe56e057', b'0', NULL, 1592697472, '49ba59abbe56e057', '第二行显示', '14f7933e647d4368', 2, '第二项', '2.将系统对redis依赖过强的问题解决', NULL);
INSERT INTO `sys_dict_item` VALUES ('b31c7e072497ea47', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-times-circle-o', '8327faa0b36efb79', 302, 'fa-times-circle-o', 'fa fa-times-circle-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('b31f1006119283bb', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-volume-off', '8327faa0b36efb79', 519, 'fa-volume-off', 'fa fa-volume-off', NULL);
INSERT INTO `sys_dict_item` VALUES ('b3a53a62c357a7d1', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-star-half-empty', '8327faa0b36efb79', 279, 'fa-star-half-empty', 'fa fa-star-half-empty', NULL);
INSERT INTO `sys_dict_item` VALUES ('b41c8b31ca214036', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-calendar-o', '8327faa0b36efb79', 41, 'fa-calendar-o', 'fa fa-calendar-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('b4afa2e340bc7952', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-barcode', '8327faa0b36efb79', 15, 'fa-barcode', 'fa fa-barcode', NULL);
INSERT INTO `sys_dict_item` VALUES ('b4d594e4d0daa516', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-lemon-o', '8327faa0b36efb79', 168, 'fa-lemon-o', 'fa fa-lemon-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('b5cae46ba50512b5', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-github', '8327faa0b36efb79', 569, 'fa-github', 'fa fa-github', NULL);
INSERT INTO `sys_dict_item` VALUES ('b614519b164cc432', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-road', '8327faa0b36efb79', 236, 'fa-road', 'fa fa-road', NULL);
INSERT INTO `sys_dict_item` VALUES ('b65d4593530a6ed8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sort-numeric-desc', '8327faa0b36efb79', 270, 'fa-sort-numeric-desc', 'fa fa-sort-numeric-desc', NULL);
INSERT INTO `sys_dict_item` VALUES ('b6771cf3c9fdad5e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-indent', '8327faa0b36efb79', 444, 'fa-indent', 'fa fa-indent', NULL);
INSERT INTO `sys_dict_item` VALUES ('b67f7efe722635d6', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cc-paypal', '8327faa0b36efb79', 394, 'fa-cc-paypal', 'fa fa-cc-paypal', NULL);
INSERT INTO `sys_dict_item` VALUES ('b6cab2cfbe2b127c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-backward', '8327faa0b36efb79', 504, 'fa-backward', 'fa fa-backward', NULL);
INSERT INTO `sys_dict_item` VALUES ('b6e7ee38bd95a7c2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-print', '8327faa0b36efb79', 221, 'fa-print', 'fa fa-print', NULL);
INSERT INTO `sys_dict_item` VALUES ('b723dbb66bdd14f8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-circle', '8327faa0b36efb79', 59, 'fa-circle', 'fa fa-circle', NULL);
INSERT INTO `sys_dict_item` VALUES ('b76fb4da5bdced0d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-phone', '8327faa0b36efb79', 209, 'fa-phone', 'fa fa-phone', NULL);
INSERT INTO `sys_dict_item` VALUES ('b7b6fe8b1e018847', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-refresh', '8327faa0b36efb79', 378, 'fa-refresh', 'fa fa-refresh', NULL);
INSERT INTO `sys_dict_item` VALUES ('b7e859b0e8ea152b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-dribbble', '8327faa0b36efb79', 557, 'fa-dribbble', 'fa fa-dribbble', NULL);
INSERT INTO `sys_dict_item` VALUES ('b84ad3124b0ece04', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-caret-square-o-up', '8327faa0b36efb79', 486, 'fa-caret-square-o-up', 'fa fa-caret-square-o-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('b88564e0caeaaecc', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-ambulance', '8327faa0b36efb79', 335, 'fa-ambulance', 'fa fa-ambulance', NULL);
INSERT INTO `sys_dict_item` VALUES ('b91db817bf89b9b4', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-genderless', '8327faa0b36efb79', 143, 'fa-genderless', 'fa fa-genderless', NULL);
INSERT INTO `sys_dict_item` VALUES ('b941d2cae57a8f7b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-pinterest', '8327faa0b36efb79', 597, 'fa-pinterest', 'fa fa-pinterest', NULL);
INSERT INTO `sys_dict_item` VALUES ('b9f9da84623f28bc', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-reddit', '8327faa0b36efb79', 602, 'fa-reddit', 'fa fa-reddit', NULL);
INSERT INTO `sys_dict_item` VALUES ('bac74eaf00177fc8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bed', '8327faa0b36efb79', 17, 'fa-bed', 'fa fa-bed', NULL);
INSERT INTO `sys_dict_item` VALUES ('bad5772515e4e1bd', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-volume-up', '8327faa0b36efb79', 521, 'fa-volume-up', 'fa fa-volume-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('bb66eaa68b3a7305', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-flickr', '8327faa0b36efb79', 564, 'fa-flickr', 'fa fa-flickr', NULL);
INSERT INTO `sys_dict_item` VALUES ('bbf3947b9a732f3b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-google-wallet', '8327faa0b36efb79', 398, 'fa-google-wallet', 'fa fa-google-wallet', NULL);
INSERT INTO `sys_dict_item` VALUES ('bbfe1951d0e1bc4b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-level-up', '8327faa0b36efb79', 170, 'fa-level-up', 'fa fa-level-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('bc5cb504615b7b4a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-pencil', '8327faa0b36efb79', 206, 'fa-pencil', 'fa fa-pencil', NULL);
INSERT INTO `sys_dict_item` VALUES ('bc661750b1e8b28f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sort-down', '8327faa0b36efb79', 268, 'fa-sort-down', 'fa fa-sort-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('bca55527c10e6ecc', 1593473464, '49ba59abbe56e057', b'0', NULL, 1593473464, '49ba59abbe56e057', '早上5点', '4119bf79166ebb2b', 1, '早5:00', '0 0 5 * * ?', NULL);
INSERT INTO `sys_dict_item` VALUES ('bd75d0fabc296101', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-twitter', '8327faa0b36efb79', 627, 'fa-twitter', 'fa fa-twitter', NULL);
INSERT INTO `sys_dict_item` VALUES ('bdbecf965b300862', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-legal', '8327faa0b36efb79', 167, 'fa-legal', 'fa fa-legal', NULL);
INSERT INTO `sys_dict_item` VALUES ('bdc0be54e8a63c6b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-pencil-square-o', '8327faa0b36efb79', 208, 'fa-pencil-square-o', 'fa fa-pencil-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('be51978ec690504b', 1593216158, '49ba59abbe56e057', b'0', NULL, 1593216158, '49ba59abbe56e057', '包含id的请求', '62c376815cf83ce1', 2, 'IdRequest', 'indi.uhyils.pojo.request.base.IdRequest', NULL);
INSERT INTO `sys_dict_item` VALUES ('be57bc1ad0a06e1b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-toggle-down', '8327faa0b36efb79', 304, 'fa-toggle-down', 'fa fa-toggle-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('beca9abfe33a682f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-recycle', '8327faa0b36efb79', 229, 'fa-recycle', 'fa fa-recycle', NULL);
INSERT INTO `sys_dict_item` VALUES ('bf543b00b96f1789', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-caret-right', '8327faa0b36efb79', 481, 'fa-caret-right', 'fa fa-caret-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('bf61b80811555340', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-krw', '8327faa0b36efb79', 411, 'fa-krw', 'fa fa-krw', NULL);
INSERT INTO `sys_dict_item` VALUES ('bf926864e4714659', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-rocket', '8327faa0b36efb79', 343, 'fa-rocket', 'fa fa-rocket', NULL);
INSERT INTO `sys_dict_item` VALUES ('bfaf73ce4558a69b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-phone-square', '8327faa0b36efb79', 210, 'fa-phone-square', 'fa fa-phone-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('c04b8231d6a41e08', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-empire', '8327faa0b36efb79', 560, 'fa-empire', 'fa fa-empire', NULL);
INSERT INTO `sys_dict_item` VALUES ('c1a6d2ca401d1905', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-times-circle', '8327faa0b36efb79', 301, 'fa-times-circle', 'fa fa-times-circle', NULL);
INSERT INTO `sys_dict_item` VALUES ('c1e7a1a1357299a8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-archive', '8327faa0b36efb79', 3, 'fa-archive', 'fa fa-archive', NULL);
INSERT INTO `sys_dict_item` VALUES ('c1f686a18646cad0', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-pencil-square', '8327faa0b36efb79', 207, 'fa-pencil-square', 'fa fa-pencil-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('c205f4fbd1c5bab7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-shopping-cart', '8327faa0b36efb79', 253, 'fa-shopping-cart', 'fa fa-shopping-cart', NULL);
INSERT INTO `sys_dict_item` VALUES ('c26fef6c0474c1e9', 1593473512, '49ba59abbe56e057', b'0', NULL, 1593473512, '49ba59abbe56e057', '中午12点', '4119bf79166ebb2b', 3, '中午12点', '0 0 12 * * ?', NULL);
INSERT INTO `sys_dict_item` VALUES ('c3979d93f61194ed', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bold', '8327faa0b36efb79', 419, 'fa-bold', 'fa fa-bold', NULL);
INSERT INTO `sys_dict_item` VALUES ('c4a4bbefebc2e825', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-ioxhost', '8327faa0b36efb79', 580, 'fa-ioxhost', 'fa fa-ioxhost', NULL);
INSERT INTO `sys_dict_item` VALUES ('c4f60dd9498a27ea', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-youtube-play', '8327faa0b36efb79', 518, 'fa-youtube-play', 'fa fa-youtube-play', NULL);
INSERT INTO `sys_dict_item` VALUES ('c5486771b6adf146', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-minus', '8327faa0b36efb79', 189, 'fa-minus', 'fa fa-minus', NULL);
INSERT INTO `sys_dict_item` VALUES ('c58d10dd52da8593', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-dropbox', '8327faa0b36efb79', 558, 'fa-dropbox', 'fa fa-dropbox', NULL);
INSERT INTO `sys_dict_item` VALUES ('c6dba0fcc1315e30', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-caret-square-o-down', '8327faa0b36efb79', 483, 'fa-caret-square-o-down', 'fa fa-caret-square-o-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('c6e16d4ce2c4b7ee', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-exclamation', '8327faa0b36efb79', 99, 'fa-exclamation', 'fa fa-exclamation', NULL);
INSERT INTO `sys_dict_item` VALUES ('c724885090e129e3', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cc-visa', '8327faa0b36efb79', 396, 'fa-cc-visa', 'fa fa-cc-visa', NULL);
INSERT INTO `sys_dict_item` VALUES ('c74abc039f6fe6b6', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bell', '8327faa0b36efb79', 19, 'fa-bell', 'fa fa-bell', NULL);
INSERT INTO `sys_dict_item` VALUES ('c759d82e9873443b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-picture-o', '8327faa0b36efb79', 118, 'fa-file-picture-o', 'fa fa-file-picture-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('c76e07d6dd8310aa', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-eraser', '8327faa0b36efb79', 97, 'fa-eraser', 'fa fa-eraser', NULL);
INSERT INTO `sys_dict_item` VALUES ('c788184f3dd45d96', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-simplybuilt', '8327faa0b36efb79', 609, 'fa-simplybuilt', 'fa fa-simplybuilt', NULL);
INSERT INTO `sys_dict_item` VALUES ('c79dd1d0ce1d7fa8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bar-chart', '8327faa0b36efb79', 401, 'fa-bar-chart', 'fa fa-bar-chart', NULL);
INSERT INTO `sys_dict_item` VALUES ('c7cdc6af0cfdd13b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-tumblr', '8327faa0b36efb79', 624, 'fa-tumblr', 'fa fa-tumblr', NULL);
INSERT INTO `sys_dict_item` VALUES ('c7d4f5bede3a5dc2', 1592299429, '49ba59abbe56e057', b'1', NULL, 1592299429, '49ba59abbe56e057', '登录已过期', '8bfce8cad90e0d79', 4, 'LOGIN_TIME_OUT_ERROR', '402', NULL);
INSERT INTO `sys_dict_item` VALUES ('c803da54105fb588', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-angle-left', '8327faa0b36efb79', 460, 'fa-angle-left', 'fa fa-angle-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('c8dbcbf81f48d7ff', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-dot-circle-o', '8327faa0b36efb79', 89, 'fa-dot-circle-o', 'fa fa-dot-circle-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('c90c9674d0cea9f4', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cubes', '8327faa0b36efb79', 83, 'fa-cubes', 'fa fa-cubes', NULL);
INSERT INTO `sys_dict_item` VALUES ('c961bad05d8251d2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-taxi', '8327faa0b36efb79', 339, 'fa-taxi', 'fa fa-taxi', NULL);
INSERT INTO `sys_dict_item` VALUES ('c9910b2f68c65c4a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-video-o', '8327faa0b36efb79', 121, 'fa-file-video-o', 'fa fa-file-video-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('c9aa0d300748a4d8', 1592349302, '49ba59abbe56e057', b'0', NULL, 1592349302, '49ba59abbe56e057', 'centos7系统', 'c66ca4e5415b47f4', 2, 'linux_centos7', '2', NULL);
INSERT INTO `sys_dict_item` VALUES ('c9d63d027977612f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-newspaper-o', '8327faa0b36efb79', 201, 'fa-newspaper-o', 'fa fa-newspaper-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('cac3acb73e1fee2c', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-plane', '8327faa0b36efb79', 342, 'fa-plane', 'fa fa-plane', NULL);
INSERT INTO `sys_dict_item` VALUES ('cb6c604e1bd937ba', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-stack-overflow', '8327faa0b36efb79', 617, 'fa-stack-overflow', 'fa fa-stack-overflow', NULL);
INSERT INTO `sys_dict_item` VALUES ('cbad263abced779a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-angle-double-up', '8327faa0b36efb79', 458, 'fa-angle-double-up', 'fa fa-angle-double-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('cbe20670e3dbb4b2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-automobile', '8327faa0b36efb79', 10, 'fa-automobile', 'fa fa-automobile', NULL);
INSERT INTO `sys_dict_item` VALUES ('cc0b5d47d794101e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-star-half', '8327faa0b36efb79', 278, 'fa-star-half', 'fa fa-star-half', NULL);
INSERT INTO `sys_dict_item` VALUES ('cdf047e7f6d86889', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-quote-right', '8327faa0b36efb79', 227, 'fa-quote-right', 'fa fa-quote-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('ceea3dc771a6c9ab', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-microphone', '8327faa0b36efb79', 187, 'fa-microphone', 'fa fa-microphone', NULL);
INSERT INTO `sys_dict_item` VALUES ('cef128954007b14b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-life-ring', '8327faa0b36efb79', 173, 'fa-life-ring', 'fa fa-life-ring', NULL);
INSERT INTO `sys_dict_item` VALUES ('cf439b2180ace1f5', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-btc', '8327faa0b36efb79', 404, 'fa-btc', 'fa fa-btc', NULL);
INSERT INTO `sys_dict_item` VALUES ('cfeaecb235d3a591', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-play-circle-o', '8327faa0b36efb79', 514, 'fa-play-circle-o', 'fa fa-play-circle-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('d00ee2b46c748d50', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-trash-o', '8327faa0b36efb79', 311, 'fa-trash-o', 'fa fa-trash-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('d08bc744783b15bd', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-outdent', '8327faa0b36efb79', 443, 'fa-outdent', 'fa fa-outdent', NULL);
INSERT INTO `sys_dict_item` VALUES ('d144e81b340ee5a8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-table', '8327faa0b36efb79', 451, 'fa-table', 'fa fa-table', NULL);
INSERT INTO `sys_dict_item` VALUES ('d153e96195c548f5', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-btc', '8327faa0b36efb79', 542, 'fa-btc', 'fa fa-btc', NULL);
INSERT INTO `sys_dict_item` VALUES ('d19f929b56d0073e', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-chain-broken', '8327faa0b36efb79', 430, 'fa-chain-broken', 'fa fa-chain-broken', NULL);
INSERT INTO `sys_dict_item` VALUES ('d1be9176eb98f667', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-thumbs-o-down', '8327faa0b36efb79', 296, 'fa-thumbs-o-down', 'fa fa-thumbs-o-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('d25593ea8db0b0da', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-dashcube', '8327faa0b36efb79', 553, 'fa-dashcube', 'fa fa-dashcube', NULL);
INSERT INTO `sys_dict_item` VALUES ('d29ea07d0382afb2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cube', '8327faa0b36efb79', 82, 'fa-cube', 'fa fa-cube', NULL);
INSERT INTO `sys_dict_item` VALUES ('d2b1278504efae17', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-foursquare', '8327faa0b36efb79', 566, 'fa-foursquare', 'fa fa-foursquare', NULL);
INSERT INTO `sys_dict_item` VALUES ('d340fee586310cc3', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file', '8327faa0b36efb79', 436, 'fa-file', 'fa fa-file', NULL);
INSERT INTO `sys_dict_item` VALUES ('d395b46b33d5fc3f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-angle-right', '8327faa0b36efb79', 461, 'fa-angle-right', 'fa fa-angle-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('d5331deaa8c45dde', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-exclamation-circle', '8327faa0b36efb79', 100, 'fa-exclamation-circle', 'fa fa-exclamation-circle', NULL);
INSERT INTO `sys_dict_item` VALUES ('d53618600b4f3f93', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-star-o', '8327faa0b36efb79', 282, 'fa-star-o', 'fa fa-star-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('d55922be8db290e8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-yahoo', '8327faa0b36efb79', 640, 'fa-yahoo', 'fa fa-yahoo', NULL);
INSERT INTO `sys_dict_item` VALUES ('d58519d0c798728f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-openid', '8327faa0b36efb79', 592, 'fa-openid', 'fa fa-openid', NULL);
INSERT INTO `sys_dict_item` VALUES ('d58b0cf8fabc1e15', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-music', '8327faa0b36efb79', 199, 'fa-music', 'fa fa-music', NULL);
INSERT INTO `sys_dict_item` VALUES ('d5bff8e7931d66f4', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-drupal', '8327faa0b36efb79', 559, 'fa-drupal', 'fa fa-drupal', NULL);
INSERT INTO `sys_dict_item` VALUES ('d63ab981c8d5202b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-html5', '8327faa0b36efb79', 578, 'fa-html5', 'fa fa-html5', NULL);
INSERT INTO `sys_dict_item` VALUES ('d682edae04573f88', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-apple', '8327faa0b36efb79', 537, 'fa-apple', 'fa fa-apple', NULL);
INSERT INTO `sys_dict_item` VALUES ('d6927ff9b5bce59f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-pause', '8327faa0b36efb79', 511, 'fa-pause', 'fa fa-pause', NULL);
INSERT INTO `sys_dict_item` VALUES ('d71ff29adbf9e480', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cc-discover', '8327faa0b36efb79', 545, 'fa-cc-discover', 'fa fa-cc-discover', NULL);
INSERT INTO `sys_dict_item` VALUES ('d7d1a5c2d4b2a8bf', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-retweet', '8327faa0b36efb79', 235, 'fa-retweet', 'fa fa-retweet', NULL);
INSERT INTO `sys_dict_item` VALUES ('d976730a0291e4a0', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cloud', '8327faa0b36efb79', 65, 'fa-cloud', 'fa fa-cloud', NULL);
INSERT INTO `sys_dict_item` VALUES ('d97f874afca46077', 1593385841, '49ba59abbe56e057', b'0', NULL, 1593385841, '49ba59abbe56e057', '页面推送', '24c8f0997bdde222', 2, '页面推送', '2', NULL);
INSERT INTO `sys_dict_item` VALUES ('da169beee5971bc8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-step-forward', '8327faa0b36efb79', 516, 'fa-step-forward', 'fa fa-step-forward', NULL);
INSERT INTO `sys_dict_item` VALUES ('da73e76c7b8ae0e4', 1592299463, '49ba59abbe56e057', b'1', NULL, 1592299463, '49ba59abbe56e057', '未登录', '8bfce8cad90e0d79', 5, 'NO_LOGIN__ERROR', '403', NULL);
INSERT INTO `sys_dict_item` VALUES ('daa650d0d6220e95', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bullseye', '8327faa0b36efb79', 36, 'fa-bullseye', 'fa fa-bullseye', NULL);
INSERT INTO `sys_dict_item` VALUES ('db133d9119f50bbf', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-square-o', '8327faa0b36efb79', 276, 'fa-square-o', 'fa fa-square-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('db199b3efff62275', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-adn', '8327faa0b36efb79', 534, 'fa-adn', 'fa fa-adn', NULL);
INSERT INTO `sys_dict_item` VALUES ('db1d29e8cacd3e18', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-share-alt-square', '8327faa0b36efb79', 248, 'fa-share-alt-square', 'fa fa-share-alt-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('db1e4f2b7f2d5d93', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-magic', '8327faa0b36efb79', 179, 'fa-magic', 'fa fa-magic', NULL);
INSERT INTO `sys_dict_item` VALUES ('db411492b3f79ec7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-venus', '8327faa0b36efb79', 360, 'fa-venus', 'fa fa-venus', NULL);
INSERT INTO `sys_dict_item` VALUES ('db6259cd4d676c69', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-money', '8327faa0b36efb79', 195, 'fa-money', 'fa fa-money', NULL);
INSERT INTO `sys_dict_item` VALUES ('db9d5040559319dc', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cog', '8327faa0b36efb79', 376, 'fa-cog', 'fa fa-cog', NULL);
INSERT INTO `sys_dict_item` VALUES ('dba2cdee837a4c6d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-toggle-on', '8327faa0b36efb79', 307, 'fa-toggle-on', 'fa fa-toggle-on', NULL);
INSERT INTO `sys_dict_item` VALUES ('dbb728f20839a4d0', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sort-up', '8327faa0b36efb79', 271, 'fa-sort-up', 'fa fa-sort-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('dc836e448224bb44', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-text-o', '8327faa0b36efb79', 374, 'fa-file-text-o', 'fa fa-file-text-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('dc9fd831c75bc0eb', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-chevron-right', '8327faa0b36efb79', 493, 'fa-chevron-right', 'fa fa-chevron-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('dcad23c3122f27f7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-angellist', '8327faa0b36efb79', 536, 'fa-angellist', 'fa fa-angellist', NULL);
INSERT INTO `sys_dict_item` VALUES ('dcb569c08ba118ba', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-plug', '8327faa0b36efb79', 215, 'fa-plug', 'fa fa-plug', NULL);
INSERT INTO `sys_dict_item` VALUES ('dd3a7eef261d68ba', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bell-o', '8327faa0b36efb79', 20, 'fa-bell-o', 'fa fa-bell-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('ddc5429ada35e87f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-futbol-o', '8327faa0b36efb79', 138, 'fa-futbol-o', 'fa fa-futbol-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('de00ce2c4c9c15b3', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-italic', '8327faa0b36efb79', 421, 'fa-italic', 'fa fa-italic', NULL);
INSERT INTO `sys_dict_item` VALUES ('de4124126c8f2440', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-reply-all', '8327faa0b36efb79', 234, 'fa-reply-all', 'fa fa-reply-all', NULL);
INSERT INTO `sys_dict_item` VALUES ('deb4a70eab21a2c8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-slack', '8327faa0b36efb79', 612, 'fa-slack', 'fa fa-slack', NULL);
INSERT INTO `sys_dict_item` VALUES ('df05de713968cc01', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-credit-card', '8327faa0b36efb79', 397, 'fa-credit-card', 'fa fa-credit-card', NULL);
INSERT INTO `sys_dict_item` VALUES ('e033f97c4f76c419', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cc-mastercard', '8327faa0b36efb79', 393, 'fa-cc-mastercard', 'fa fa-cc-mastercard', NULL);
INSERT INTO `sys_dict_item` VALUES ('e117ed9dd16ee5c9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-twitter-square', '8327faa0b36efb79', 628, 'fa-twitter-square', 'fa fa-twitter-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('e2689613ca88d004', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-text-width', '8327faa0b36efb79', 424, 'fa-text-width', 'fa fa-text-width', NULL);
INSERT INTO `sys_dict_item` VALUES ('e290db4f6b71a7c8', 1592705724, '49ba59abbe56e057', b'0', NULL, 1592706476, '49ba59abbe56e057', '智能操作查询快捷入口', 'a9f3986d7fd688a4', 5, '智能操作查询', '0bc916517931ec27', NULL);
INSERT INTO `sys_dict_item` VALUES ('e2e40926c2230411', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-clock-o', '8327faa0b36efb79', 63, 'fa-clock-o', 'fa fa-clock-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('e38b49c8cff5c1e3', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-taxi', '8327faa0b36efb79', 292, 'fa-taxi', 'fa fa-taxi', NULL);
INSERT INTO `sys_dict_item` VALUES ('e3b1c666e6c84437', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sort-alpha-asc', '8327faa0b36efb79', 262, 'fa-sort-alpha-asc', 'fa fa-sort-alpha-asc', NULL);
INSERT INTO `sys_dict_item` VALUES ('e43d010759a8493d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-align-center', '8327faa0b36efb79', 415, 'fa-align-center', 'fa fa-align-center', NULL);
INSERT INTO `sys_dict_item` VALUES ('e47d7d22dbf0c6f5', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrow-left', '8327faa0b36efb79', 472, 'fa-arrow-left', 'fa fa-arrow-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('e4bce6d172a1abb5', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-text', '8327faa0b36efb79', 373, 'fa-file-text', 'fa fa-file-text', NULL);
INSERT INTO `sys_dict_item` VALUES ('e5056a18cda991bc', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-movie-o', '8327faa0b36efb79', 115, 'fa-file-movie-o', 'fa fa-file-movie-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('e5a6c645134ecc75', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-forumbee', '8327faa0b36efb79', 565, 'fa-forumbee', 'fa fa-forumbee', NULL);
INSERT INTO `sys_dict_item` VALUES ('e5ca980839907411', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-stack-exchange', '8327faa0b36efb79', 616, 'fa-stack-exchange', 'fa fa-stack-exchange', NULL);
INSERT INTO `sys_dict_item` VALUES ('e5d9a99115dfa42a', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-quote-left', '8327faa0b36efb79', 226, 'fa-quote-left', 'fa fa-quote-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('e6574d5c90b1e8c8', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-info', '8327faa0b36efb79', 159, 'fa-info', 'fa fa-info', NULL);
INSERT INTO `sys_dict_item` VALUES ('e691f0c0a1228276', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-paint-brush', '8327faa0b36efb79', 202, 'fa-paint-brush', 'fa fa-paint-brush', NULL);
INSERT INTO `sys_dict_item` VALUES ('e699ad47b401d8ba', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-soccer-ball-o', '8327faa0b36efb79', 260, 'fa-soccer-ball-o', 'fa fa-soccer-ball-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('e7262e605812ddf4', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrows-v', '8327faa0b36efb79', 7, 'fa-arrows-v', 'fa fa-arrows-v', NULL);
INSERT INTO `sys_dict_item` VALUES ('e7bc7087878161e1', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-globe', '8327faa0b36efb79', 146, 'fa-globe', 'fa fa-globe', NULL);
INSERT INTO `sys_dict_item` VALUES ('e83b64d9b1eba513', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-ban', '8327faa0b36efb79', 11, 'fa-ban', 'fa fa-ban', NULL);
INSERT INTO `sys_dict_item` VALUES ('e8ddfe7df68104ef', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-excel-o', '8327faa0b36efb79', 113, 'fa-file-excel-o', 'fa fa-file-excel-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('e9747d2f3608d03d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-vimeo-square', '8327faa0b36efb79', 630, 'fa-vimeo-square', 'fa fa-vimeo-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('e9d9a8594a1a7774', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-github-alt', '8327faa0b36efb79', 570, 'fa-github-alt', 'fa fa-github-alt', NULL);
INSERT INTO `sys_dict_item` VALUES ('ea7a1fd138b1bc7d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-remove', '8327faa0b36efb79', 231, 'fa-remove', 'fa fa-remove', NULL);
INSERT INTO `sys_dict_item` VALUES ('eaf6ff31a78060f3', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-tint', '8327faa0b36efb79', 303, 'fa-tint', 'fa fa-tint', NULL);
INSERT INTO `sys_dict_item` VALUES ('eafeae38c3c8d38f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-chevron-circle-right', '8327faa0b36efb79', 489, 'fa-chevron-circle-right', 'fa fa-chevron-circle-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('eb596abf1ac44f37', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-building', '8327faa0b36efb79', 33, 'fa-building', 'fa fa-building', NULL);
INSERT INTO `sys_dict_item` VALUES ('eb6b4309f839d5ee', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cc-amex', '8327faa0b36efb79', 544, 'fa-cc-amex', 'fa fa-cc-amex', NULL);
INSERT INTO `sys_dict_item` VALUES ('eb958cf30a330439', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-star-half-o', '8327faa0b36efb79', 281, 'fa-star-half-o', 'fa fa-star-half-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('ec767c639133a880', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-user-times', '8327faa0b36efb79', 325, 'fa-user-times', 'fa fa-user-times', NULL);
INSERT INTO `sys_dict_item` VALUES ('ec8edeb6a38a3e4b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-mercury', '8327faa0b36efb79', 356, 'fa-mercury', 'fa fa-mercury', NULL);
INSERT INTO `sys_dict_item` VALUES ('ec933b010f8c8ea2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-briefcase', '8327faa0b36efb79', 31, 'fa-briefcase', 'fa fa-briefcase', NULL);
INSERT INTO `sys_dict_item` VALUES ('ecaf1ab91ca58d84', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-angle-up', '8327faa0b36efb79', 462, 'fa-angle-up', 'fa fa-angle-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('ecf0eecbd640e2d2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-trophy', '8327faa0b36efb79', 313, 'fa-trophy', 'fa fa-trophy', NULL);
INSERT INTO `sys_dict_item` VALUES ('ed06aa9be1dbfedc', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrow-circle-right', '8327faa0b36efb79', 465, 'fa-arrow-circle-right', 'fa fa-arrow-circle-right', NULL);
INSERT INTO `sys_dict_item` VALUES ('ee765151179b5a04', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sun-o', '8327faa0b36efb79', 285, 'fa-sun-o', 'fa fa-sun-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('ee7db1d84ff86610', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-user', '8327faa0b36efb79', 322, 'fa-user', 'fa fa-user', NULL);
INSERT INTO `sys_dict_item` VALUES ('eeb425fee8f9945b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-heart', '8327faa0b36efb79', 151, 'fa-heart', 'fa fa-heart', NULL);
INSERT INTO `sys_dict_item` VALUES ('eee6398a0645987f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-arrow-up', '8327faa0b36efb79', 474, 'fa-arrow-up', 'fa fa-arrow-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('ef2b7dfa52a8b850', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-lightbulb-o', '8327faa0b36efb79', 175, 'fa-lightbulb-o', 'fa fa-lightbulb-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('f05c9cfe86f9f38b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-user-md', '8327faa0b36efb79', 532, 'fa-user-md', 'fa fa-user-md', NULL);
INSERT INTO `sys_dict_item` VALUES ('f0e0d97b1fc9dd74', 1592705651, '49ba59abbe56e057', b'0', NULL, 1592706456, '49ba59abbe56e057', 'API调用快捷入口', 'a9f3986d7fd688a4', 2, 'API调用', '0bc916517931ec27', NULL);
INSERT INTO `sys_dict_item` VALUES ('f10297bb6b7c0eae', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-step-backward', '8327faa0b36efb79', 515, 'fa-step-backward', 'fa fa-step-backward', NULL);
INSERT INTO `sys_dict_item` VALUES ('f156dc002c695bba', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-stumbleupon', '8327faa0b36efb79', 620, 'fa-stumbleupon', 'fa fa-stumbleupon', NULL);
INSERT INTO `sys_dict_item` VALUES ('f16b4d922f13d3b7', 1592697246, '49ba59abbe56e057', b'0', NULL, 1592697246, '49ba59abbe56e057', '项目当前版本信息', '231a95087199851f', 2, '当前版本', 'v0.0.3', NULL);
INSERT INTO `sys_dict_item` VALUES ('f35c63120d009d3f', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-hand-o-left', '8327faa0b36efb79', 496, 'fa-hand-o-left', 'fa fa-hand-o-left', NULL);
INSERT INTO `sys_dict_item` VALUES ('f47a40b15605b866', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-check-square', '8327faa0b36efb79', 56, 'fa-check-square', 'fa fa-check-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('f4c5cd5bd893a011', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-life-saver', '8327faa0b36efb79', 174, 'fa-life-saver', 'fa fa-life-saver', NULL);
INSERT INTO `sys_dict_item` VALUES ('f4def87538eb6398', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-filter', '8327faa0b36efb79', 125, 'fa-filter', 'fa fa-filter', NULL);
INSERT INTO `sys_dict_item` VALUES ('f53d3ac6d9c9eb09', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sort-desc', '8327faa0b36efb79', 267, 'fa-sort-desc', 'fa fa-sort-desc', NULL);
INSERT INTO `sys_dict_item` VALUES ('f56b0cd528efc875', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-text-o', '8327faa0b36efb79', 439, 'fa-file-text-o', 'fa fa-file-text-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('f594750fd28569f9', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-square', '8327faa0b36efb79', 275, 'fa-square', 'fa fa-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('f5a3d5094de3282b', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-money', '8327faa0b36efb79', 412, 'fa-money', 'fa fa-money', NULL);
INSERT INTO `sys_dict_item` VALUES ('f5b2e99c37a81793', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-connectdevelop', '8327faa0b36efb79', 551, 'fa-connectdevelop', 'fa fa-connectdevelop', NULL);
INSERT INTO `sys_dict_item` VALUES ('f5caea1af331c3a0', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-list-ul', '8327faa0b36efb79', 448, 'fa-list-ul', 'fa fa-list-ul', NULL);
INSERT INTO `sys_dict_item` VALUES ('f5d648a7ff617bfd', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-signal', '8327faa0b36efb79', 256, 'fa-signal', 'fa fa-signal', NULL);
INSERT INTO `sys_dict_item` VALUES ('f6227f59af88c797', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-youtube-play', '8327faa0b36efb79', 643, 'fa-youtube-play', 'fa fa-youtube-play', NULL);
INSERT INTO `sys_dict_item` VALUES ('f6b12af0df728d64', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-cloud-upload', '8327faa0b36efb79', 67, 'fa-cloud-upload', 'fa fa-cloud-upload', NULL);
INSERT INTO `sys_dict_item` VALUES ('f6de600ebd7f27f1', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-truck', '8327faa0b36efb79', 314, 'fa-truck', 'fa fa-truck', NULL);
INSERT INTO `sys_dict_item` VALUES ('f727c46084c5a079', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-code-o', '8327faa0b36efb79', 112, 'fa-file-code-o', 'fa fa-file-code-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('f729e86f478c6495', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-digg', '8327faa0b36efb79', 556, 'fa-digg', 'fa fa-digg', NULL);
INSERT INTO `sys_dict_item` VALUES ('f73140eb9c4b50ab', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-comments', '8327faa0b36efb79', 75, 'fa-comments', 'fa fa-comments', NULL);
INSERT INTO `sys_dict_item` VALUES ('f782303eba97f257', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-flash', '8327faa0b36efb79', 131, 'fa-flash', 'fa fa-flash', NULL);
INSERT INTO `sys_dict_item` VALUES ('f7e1336e693ab1df', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-comments-o', '8327faa0b36efb79', 76, 'fa-comments-o', 'fa fa-comments-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('f80c9a7e283214c0', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-language', '8327faa0b36efb79', 164, 'fa-language', 'fa fa-language', NULL);
INSERT INTO `sys_dict_item` VALUES ('f87561553110f1a6', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-ellipsis-h', '8327faa0b36efb79', 92, 'fa-ellipsis-h', 'fa fa-ellipsis-h', NULL);
INSERT INTO `sys_dict_item` VALUES ('f8ab9ef84c2c66a2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-tasks', '8327faa0b36efb79', 291, 'fa-tasks', 'fa fa-tasks', NULL);
INSERT INTO `sys_dict_item` VALUES ('f8cb523267e370a0', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bar-chart-o', '8327faa0b36efb79', 14, 'fa-bar-chart-o', 'fa fa-bar-chart-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('f9137c78e52c8ae7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-bar-chart', '8327faa0b36efb79', 13, 'fa-bar-chart', 'fa fa-bar-chart', NULL);
INSERT INTO `sys_dict_item` VALUES ('f9ced26a65d1c7a0', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-spinner', '8327faa0b36efb79', 273, 'fa-spinner', 'fa fa-spinner', NULL);
INSERT INTO `sys_dict_item` VALUES ('f9d210d4a4ea7802', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-caret-square-o-up', '8327faa0b36efb79', 48, 'fa-caret-square-o-up', 'fa fa-caret-square-o-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('f9e66b3d6b81ac86', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-unlock', '8327faa0b36efb79', 318, 'fa-unlock', 'fa fa-unlock', NULL);
INSERT INTO `sys_dict_item` VALUES ('fa17ee31bc5ac0fe', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-gbp', '8327faa0b36efb79', 408, 'fa-gbp', 'fa fa-gbp', NULL);
INSERT INTO `sys_dict_item` VALUES ('fa23b045cb7116f3', 1593473536, '49ba59abbe56e057', b'0', NULL, 1593473536, '49ba59abbe56e057', '下午1:30', '4119bf79166ebb2b', 4, '下午1:30', '0 30 13 * * ?', NULL);
INSERT INTO `sys_dict_item` VALUES ('fa4fe2e36dcda042', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-tumblr-square', '8327faa0b36efb79', 625, 'fa-tumblr-square', 'fa fa-tumblr-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('fa549cfa717f51b2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-level-down', '8327faa0b36efb79', 169, 'fa-level-down', 'fa fa-level-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('fa61a45a405dee25', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-sort', '8327faa0b36efb79', 261, 'fa-sort', 'fa fa-sort', NULL);
INSERT INTO `sys_dict_item` VALUES ('fa7067cba60d25c1', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-image', '8327faa0b36efb79', 157, 'fa-image', 'fa fa-image', NULL);
INSERT INTO `sys_dict_item` VALUES ('fa85ef2ca208d6be', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-diamond', '8327faa0b36efb79', 88, 'fa-diamond', 'fa fa-diamond', NULL);
INSERT INTO `sys_dict_item` VALUES ('fb6c420d993350d7', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-linkedin-square', '8327faa0b36efb79', 587, 'fa-linkedin-square', 'fa fa-linkedin-square', NULL);
INSERT INTO `sys_dict_item` VALUES ('fbd84f74941d02ed', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-book', '8327faa0b36efb79', 28, 'fa-book', 'fa fa-book', NULL);
INSERT INTO `sys_dict_item` VALUES ('fbe458dcb31d04d6', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-building-o', '8327faa0b36efb79', 34, 'fa-building-o', 'fa fa-building-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('fbf6ee623d131c4d', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-picture-o', '8327faa0b36efb79', 212, 'fa-picture-o', 'fa fa-picture-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('fc21d3c10b5d0a02', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-css3', '8327faa0b36efb79', 552, 'fa-css3', 'fa fa-css3', NULL);
INSERT INTO `sys_dict_item` VALUES ('fc46727e54ffeb22', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-th-list', '8327faa0b36efb79', 454, 'fa-th-list', 'fa fa-th-list', NULL);
INSERT INTO `sys_dict_item` VALUES ('fca374c3399df470', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-file-video-o', '8327faa0b36efb79', 369, 'fa-file-video-o', 'fa fa-file-video-o', NULL);
INSERT INTO `sys_dict_item` VALUES ('fcc23efdf9da9906', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-tablet', '8327faa0b36efb79', 287, 'fa-tablet', 'fa fa-tablet', NULL);
INSERT INTO `sys_dict_item` VALUES ('fcc891f5045cfbfe', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-git', '8327faa0b36efb79', 567, 'fa-git', 'fa fa-git', NULL);
INSERT INTO `sys_dict_item` VALUES ('fcf6842527e55361', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-toggle-up', '8327faa0b36efb79', 309, 'fa-toggle-up', 'fa fa-toggle-up', NULL);
INSERT INTO `sys_dict_item` VALUES ('fd2adee035eced97', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-power-off', '8327faa0b36efb79', 220, 'fa-power-off', 'fa fa-power-off', NULL);
INSERT INTO `sys_dict_item` VALUES ('fe35f0f8e71eb256', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-caret-square-o-down', '8327faa0b36efb79', 45, 'fa-caret-square-o-down', 'fa fa-caret-square-o-down', NULL);
INSERT INTO `sys_dict_item` VALUES ('feb2e8c6402b91d0', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-lastfm', '8327faa0b36efb79', 583, 'fa-lastfm', 'fa fa-lastfm', NULL);
INSERT INTO `sys_dict_item` VALUES ('ff3178c9dd4371bb', 1593391158, '49ba59abbe56e057', b'0', NULL, 1593391158, '49ba59abbe56e057', '订阅推送', 'd43f938a97b62a6a', 2, '定时推送接口', 'PushService', NULL);
INSERT INTO `sys_dict_item` VALUES ('ffa59c996bff49e2', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-whatsapp', '8327faa0b36efb79', 635, 'fa-whatsapp', 'fa fa-whatsapp', NULL);
INSERT INTO `sys_dict_item` VALUES ('ffd51a1ae0e29467', 1592348628, '49ba59abbe56e057', b'0', NULL, 1592348628, '49ba59abbe56e057', 'fa-umbrella', '8327faa0b36efb79', 316, 'fa-umbrella', 'fa fa-umbrella', NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
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
INSERT INTO `sys_menu` VALUES ('0438619977be913a', 1601205728, '49ba59abbe56e057', b'1', NULL, 1601205754, '49ba59abbe56e057', '50450bb14061011b', 1, 'fa fa-list-ul', '设备参数管理', 2, '_self', b'1', 'page/DeviceArg.html');
INSERT INTO `sys_menu` VALUES ('06134cc09475bf50', 1591941492, '49ba59abbe56e057', b'0', NULL, 1593472283, '49ba59abbe56e057', '2f4b3196f0a23652', 1, 'fa fa-github-alt', '软件管理', 3, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('0bc916517931ec27', 1591524769, '49ba59abbe56e057', b'0', NULL, 1592801070, '49ba59abbe56e057', '2776dbc3d11e5958', 1, 'fa fa-key', '权限管理', 4, '_self', b'1', 'page/power.html');
INSERT INTO `sys_menu` VALUES ('0c5d0fb27b427b13', 1604910738, '49ba59abbe56e057', b'0', NULL, 1604911013, '49ba59abbe56e057', '18294d8b91ceb32f', 1, 'fa fa-birthday-cake', '工单基础信息表', 7, '_self', b'1', 'page/OrderInfo.html');
INSERT INTO `sys_menu` VALUES ('1349ee765dc3343f', 1591941591, '49ba59abbe56e057', b'0', NULL, 1592800726, '49ba59abbe56e057', '376ae7b1d7bd119b', 1, 'fa fa-server', '服务器管理', 2, '_self', b'1', 'page/server.html');
INSERT INTO `sys_menu` VALUES ('13a809f2b6223b3d', 1591421821, '49ba59abbe56e057', b'0', NULL, 1591421821, '49ba59abbe56e057', 'a7e0448690bbb112', 1, 'fa fa-adjust', '异常页面', 7, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('171242cf975407a7', 1591422020, '49ba59abbe56e057', b'0', NULL, 1591422020, '49ba59abbe56e057', 'd8db718af9ea9ca0', 1, 'fa fa-adjust', '登录-2', 2, '_blank', b'1', 'page/login-2.html');
INSERT INTO `sys_menu` VALUES ('18294d8b91ceb32f', 1604910502, '49ba59abbe56e057', b'0', NULL, 1604910502, '49ba59abbe56e057', '2f4b3196f0a23652', 1, 'fa fa-book', '工单', 5, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('2051158803092292', 1591417727, '49ba59abbe56e057', b'1', NULL, 1591421690, '49ba59abbe56e057', 'a7e0448690bbb112', 1, 'fa fa-adjust', '菜单管理', 2, '_self', b'1', 'page/menu.html');
INSERT INTO `sys_menu` VALUES ('22f863fffdc41f4c', 1604910873, '49ba59abbe56e057', b'0', NULL, 1604911045, '49ba59abbe56e057', '18294d8b91ceb32f', 1, 'fa fa-birthday-cake', '工单节点处理结果表', 11, '_self', b'1', 'page/OrderNodeResultType.html');
INSERT INTO `sys_menu` VALUES ('2776dbc3d11e5958', 1591524719, '49ba59abbe56e057', b'0', NULL, 1592800114, '49ba59abbe56e057', '2f4b3196f0a23652', 1, 'fa fa-key', '权限管理', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('292ee9025c3624bb', 1593215606, '49ba59abbe56e057', b'0', NULL, 1593215606, '49ba59abbe56e057', '2f4b3196f0a23652', 1, 'fa fa-bars', '系统功能', 4, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('2f4b3196f0a23652', 1, '49ba59abbe56e057', b'0', NULL, 1592800039, '49ba59abbe56e057', '', 1, 'fa fa-adjust', '我的页面', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('340577a0bbc07ace', 1604910707, '49ba59abbe56e057', b'0', NULL, 1604911004, '49ba59abbe56e057', '18294d8b91ceb32f', 1, 'fa fa-birthday-cake', '节点间关联路由样例表', 6, '_self', b'1', 'page/OrderBaseNodeRoute.html');
INSERT INTO `sys_menu` VALUES ('376ae7b1d7bd119b', 1591745747, '49ba59abbe56e057', b'0', NULL, 1592800420, '49ba59abbe56e057', '2f4b3196f0a23652', 1, 'fa fa-cogs', '系统管理', 2, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('3c1d8ee22772db7a', 1, '49ba59abbe56e057', b'0', NULL, 1591535726, '49ba59abbe56e057', '', 1, 'fa fa-adjust', '其他管理', 3, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('3f232dd9b523fcdf', 1591421937, '49ba59abbe56e057', b'0', NULL, 1591421937, '49ba59abbe56e057', 'fb018d62c7e3286d', 1, 'fa fa-adjust', '普通表单', 1, '_self', b'1', 'page/form.html');
INSERT INTO `sys_menu` VALUES ('4104193c14df7220', 1593480277, '49ba59abbe56e057', b'0', NULL, 1593480277, '49ba59abbe56e057', '292ee9025c3624bb', 1, 'fa fa-cart-arrow-down', 'api群组管理', 2, '_self', b'1', 'page/ApiGroup.html');
INSERT INTO `sys_menu` VALUES ('43806b37ecb4bbb3', 1593261800, '49ba59abbe56e057', b'1', NULL, 1593261800, '49ba59abbe56e057', '292ee9025c3624bb', 1, 'fa fa-cart-arrow-down', 'api管理', 2, '_self', b'1', 'page/api.html');
INSERT INTO `sys_menu` VALUES ('4e06442869fa4d09', 1593262276, '49ba59abbe56e057', b'0', NULL, 1593262276, '49ba59abbe56e057', '292ee9025c3624bb', 1, 'fa fa-cart-plus', 'api订阅管理', 3, '_self', b'1', 'page/apiSubscibe.html');
INSERT INTO `sys_menu` VALUES ('5014d045df4c4893', 1604036089, '49ba59abbe56e057', b'1', NULL, 1604036089, '49ba59abbe56e057', '2f4b3196f0a23652', 1, 'fa fa-book', '文档与工单', 6, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('50450bb14061011b', 1598828741, '49ba59abbe56e057', b'1', NULL, 1598828741, '49ba59abbe56e057', '2f4b3196f0a23652', 1, 'fa fa-home', '智能家居', 5, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('5138808c835cf0ee', 1604036120, '49ba59abbe56e057', b'1', NULL, 1604036120, '49ba59abbe56e057', '5014d045df4c4893', 1, 'fa fa-book', '文档', 1, '_self', b'1', '/page/Document.html');
INSERT INTO `sys_menu` VALUES ('53e027a70fd487bd', 1604910907, '49ba59abbe56e057', b'0', NULL, 1604911054, '49ba59abbe56e057', '18294d8b91ceb32f', 1, 'fa fa-birthday-cake', '节点间关联路由表', 13, '_self', b'1', 'page/OrderNodeRoute.html');
INSERT INTO `sys_menu` VALUES ('563f9a9cfec1ac12', 1593215670, '49ba59abbe56e057', b'0', NULL, 1593215670, '49ba59abbe56e057', '292ee9025c3624bb', 1, 'fa fa-bell-o', '定时任务管理', 1, '_self', b'1', 'page/job.html');
INSERT INTO `sys_menu` VALUES ('56c1680bebdb08fd', 1591422290, '49ba59abbe56e057', b'0', NULL, 1591422290, '49ba59abbe56e057', '9a7233c34fae5188', 1, 'fa fa-adjust', '省市县区选择器', 7, '_self', b'1', 'page/area.html');
INSERT INTO `sys_menu` VALUES ('581970ef9b74923e', 1600480405, '49ba59abbe56e057', b'0', NULL, 1604910942, '49ba59abbe56e057', '2776dbc3d11e5958', 1, 'fa fa-remove', '接口禁用', 5, '_self', b'1', 'page/serviceDisable.html');
INSERT INTO `sys_menu` VALUES ('58a72dab64ae9cbf', 1591745873, '49ba59abbe56e057', b'0', NULL, 1592800770, '49ba59abbe56e057', '376ae7b1d7bd119b', 1, 'fa fa-calendar', '日志管理', 1, '_self', b'1', 'page/log.html');
INSERT INTO `sys_menu` VALUES ('633365df7572f5b0', 1593472407, '49ba59abbe56e057', b'0', NULL, 1593472407, '49ba59abbe56e057', '292ee9025c3624bb', 1, 'fa fa-taxi', '服务订阅', 4, '_self', b'1', 'page/subscribe.html');
INSERT INTO `sys_menu` VALUES ('680105cd3ebe0fc1', 1592298244, '49ba59abbe56e057', b'0', NULL, 1592800750, '49ba59abbe56e057', '376ae7b1d7bd119b', 1, 'fa fa-book', '服务字典', 3, '_self', b'1', 'page/dictionary.html');
INSERT INTO `sys_menu` VALUES ('6d2d9727b9cd94c8', 1591422101, '49ba59abbe56e057', b'0', NULL, 1591422101, '49ba59abbe56e057', 'e9b648568a0f9990', 1, 'fa fa-adjust', '弹出层', 2, '_self', b'1', 'page/layer.html');
INSERT INTO `sys_menu` VALUES ('6e54f3558a6bc91d', 1591422420, '49ba59abbe56e057', b'0', NULL, 1591422420, '49ba59abbe56e057', 'a91fa5d15ce84c73', 1, 'fa fa-adjust', '按钮4', 2, '_self', b'1', 'page/form.html?v=1');
INSERT INTO `sys_menu` VALUES ('70bad23c6497c8c8', 1591422310, '49ba59abbe56e057', b'0', NULL, 1591422310, '49ba59abbe56e057', '3c1d8ee22772db7a', 1, 'fa fa-adjust', '多级菜单', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('744d92fcf70625ac', 1593499822, '49ba59abbe56e057', b'0', NULL, 1593499822, '49ba59abbe56e057', '292ee9025c3624bb', 1, 'fa fa-envelope-square', '消息查看', 5, '_self', b'1', 'page/msg.html');
INSERT INTO `sys_menu` VALUES ('799962d65dfa819b', 1591422264, '49ba59abbe56e057', b'0', NULL, 1591422264, '49ba59abbe56e057', '9a7233c34fae5188', 1, 'fa fa-adjust', '富文本编辑器', 6, '_self', b'1', 'page/editor.html');
INSERT INTO `sys_menu` VALUES ('7f7ac8d39b0b5c2b', 1591422333, '49ba59abbe56e057', b'0', NULL, 1591422333, '49ba59abbe56e057', '3c1d8ee22772db7a', 1, 'fa fa-adjust', '失效菜单', 2, '_self', b'1', 'page/error.html');
INSERT INTO `sys_menu` VALUES ('80dd9c34642cfc57', 1591951156, '49ba59abbe56e057', b'0', NULL, 1592801007, '49ba59abbe56e057', '06134cc09475bf50', 1, 'fa fa-circle-o-notch', 'redis管理', 1, '_self', b'1', 'page/redis.html');
INSERT INTO `sys_menu` VALUES ('83431e6c12322cd6', 1604910849, '49ba59abbe56e057', b'0', NULL, 1604911038, '49ba59abbe56e057', '18294d8b91ceb32f', 1, 'fa fa-birthday-cake', '工单节点属性表', 10, '_self', b'1', 'page/OrderNodeField.html');
INSERT INTO `sys_menu` VALUES ('8443efbb0d49201a', 1591422152, '49ba59abbe56e057', b'0', NULL, 1591422152, '49ba59abbe56e057', '9a7233c34fae5188', 1, 'fa fa-adjust', '图标选择', 2, '_self', b'1', 'page/icon-picker.html');
INSERT INTO `sys_menu` VALUES ('859ff5d974e2e6ab', 1591422129, '49ba59abbe56e057', b'0', NULL, 1591422129, '49ba59abbe56e057', '9a7233c34fae5188', 1, 'fa fa-adjust', '图标列表', 1, '_self', b'1', 'page/icon.html');
INSERT INTO `sys_menu` VALUES ('864868c4d270210f', 1591421740, '49ba59abbe56e057', b'0', NULL, 1591421740, '49ba59abbe56e057', 'a7e0448690bbb112', 1, 'fa fa-adjust', '表格示例', 4, '_self', b'1', 'page/table.html');
INSERT INTO `sys_menu` VALUES ('8841a7debe30d900', 1604910617, '49ba59abbe56e057', b'0', NULL, 1604910978, '49ba59abbe56e057', '18294d8b91ceb32f', 1, 'fa fa-birthday-cake', '工单节点样例表', 2, '_self', b'1', 'page/OrderBaseNode.html');
INSERT INTO `sys_menu` VALUES ('89126878bda19995', 1604910818, '49ba59abbe56e057', b'0', NULL, 1604911029, '49ba59abbe56e057', '18294d8b91ceb32f', 1, 'fa fa-birthday-cake', '工单节点表', 9, '_self', b'1', 'page/OrderNode.html');
INSERT INTO `sys_menu` VALUES ('8a3d33c4a4491749', 1591422393, '49ba59abbe56e057', b'0', NULL, 1591422393, '49ba59abbe56e057', 'a91fa5d15ce84c73', 1, 'fa fa-adjust', '按钮3', 1, '_self', b'1', 'page/button.html?v=3');
INSERT INTO `sys_menu` VALUES ('8b6558c63b708331', 1592367550, '49ba59abbe56e057', b'1', NULL, 1592367587, '49ba59abbe56e057', '2f4b3196f0a23652', 1, 'fa fa-adjust', 'dubbo控制台', 4, '_self', b'1', 'http://localhost:8080/');
INSERT INTO `sys_menu` VALUES ('8d5732647336d4c7', 1591524814, '49ba59abbe56e057', b'0', NULL, 1592801055, '49ba59abbe56e057', '2776dbc3d11e5958', 1, 'fa fa-users', '角色管理', 2, '_self', b'1', 'page/role.html');
INSERT INTO `sys_menu` VALUES ('8d8daed3ae6a690c', 1591422352, '49ba59abbe56e057', b'0', NULL, 1591422352, '49ba59abbe56e057', '70bad23c6497c8c8', 1, 'fa fa-adjust', '按钮1', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('8e71e8d3638f6592', 1591421677, '49ba59abbe56e057', b'0', NULL, 1591421677, '49ba59abbe56e057', 'a7e0448690bbb112', 1, 'fa fa-adjust', '系统设置', 3, '_self', b'1', 'page/setting.html');
INSERT INTO `sys_menu` VALUES ('9a7233c34fae5188', 1, '49ba59abbe56e057', b'0', NULL, 1591535719, '49ba59abbe56e057', '', 1, 'fa fa-adjust', '组件管理', 2, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('9acabaca0cf49102', 1604910582, '49ba59abbe56e057', b'0', NULL, 1604910964, '49ba59abbe56e057', '18294d8b91ceb32f', 1, 'fa fa-birthday-cake', '工单基础信息样例表', 1, '_self', b'1', 'page/OrderBaseInfo.html');
INSERT INTO `sys_menu` VALUES ('9f82981e563a516e', 1591524846, '49ba59abbe56e057', b'0', NULL, 1592801046, '49ba59abbe56e057', '2776dbc3d11e5958', 1, 'fa fa-user', '用户管理', 1, '_self', b'1', 'page/user.html');
INSERT INTO `sys_menu` VALUES ('a7e0448690bbb112', 1, '49ba59abbe56e057', b'0', NULL, 1592800048, '49ba59abbe56e057', '', 1, 'fa fa-adjust', '常规管理', 4, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('a91fa5d15ce84c73', 1591422368, '49ba59abbe56e057', b'0', NULL, 1591422368, '49ba59abbe56e057', '8d8daed3ae6a690c', 1, 'fa fa-adjust', '按钮2', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('ac30bb40e8dd9446', 1596065638, '49ba59abbe56e057', b'0', NULL, 1596066742, '49ba59abbe56e057', '292ee9025c3624bb', 1, 'fa fa-pagelines', 'Kpro', 6, '_self', b'1', 'page/table/kpro.html');
INSERT INTO `sys_menu` VALUES ('accd3db9aa88e27f', 1591422077, '49ba59abbe56e057', b'0', NULL, 1591422077, '49ba59abbe56e057', 'e9b648568a0f9990', 1, 'fa fa-adjust', '按钮示例', 1, '_self', b'1', 'page/button.html');
INSERT INTO `sys_menu` VALUES ('bdfc9c161b61af22', 1591422179, '49ba59abbe56e057', b'0', NULL, 1591422179, '49ba59abbe56e057', '9a7233c34fae5188', 1, 'fa fa-adjust', '颜色选择', 3, '_self', b'1', 'page/color-select.html');
INSERT INTO `sys_menu` VALUES ('cab15d86936dc6d2', 1591524740, '49ba59abbe56e057', b'0', NULL, 1592801078, '49ba59abbe56e057', '2776dbc3d11e5958', 1, 'fa fa-bars', '菜单管理', 5, '_self', b'1', 'page/menu.html');
INSERT INTO `sys_menu` VALUES ('cb66e93bf6df43bb', 1591422240, '49ba59abbe56e057', b'0', NULL, 1591422240, '49ba59abbe56e057', '9a7233c34fae5188', 1, 'fa fa-adjust', '文件上传', 5, '_self', b'1', 'page/upload.html');
INSERT INTO `sys_menu` VALUES ('cff03c3ff625db25', 1591422045, '49ba59abbe56e057', b'0', NULL, 1591422045, '49ba59abbe56e057', '13a809f2b6223b3d', 1, 'fa fa-adjust', '404页面', 1, '_self', b'1', 'page/404.html');
INSERT INTO `sys_menu` VALUES ('d8db718af9ea9ca0', 1591421807, '49ba59abbe56e057', b'0', NULL, 1591421807, '49ba59abbe56e057', 'a7e0448690bbb112', 1, 'fa fa-adjust', '登录模板', 6, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('da1eb0e3b098d8be', 1604910781, '49ba59abbe56e057', b'0', NULL, 1604911020, '49ba59abbe56e057', '18294d8b91ceb32f', 1, 'fa fa-birthday-cake', '日志表', 8, '_self', b'1', 'page/OrderLog.html');
INSERT INTO `sys_menu` VALUES ('e4a2b4761649c976', 1598828851, '49ba59abbe56e057', b'1', NULL, 1598828851, '49ba59abbe56e057', '50450bb14061011b', 1, 'fa fa-camera', '设备管理', 1, '_self', b'1', 'page/device.html');
INSERT INTO `sys_menu` VALUES ('e9b648568a0f9990', 1591421832, '49ba59abbe56e057', b'0', NULL, 1591421832, '49ba59abbe56e057', 'a7e0448690bbb112', 1, 'fa fa-adjust', '其他页面', 8, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('eb0058ce33fc0f70', 1591421962, '49ba59abbe56e057', b'0', NULL, 1591421962, '49ba59abbe56e057', 'fb018d62c7e3286d', 1, 'fa fa-adjust', '分步表单', 2, '_self', b'1', 'page/form-step.html');
INSERT INTO `sys_menu` VALUES ('eb9e6eb74c5d64be', 1604910681, '49ba59abbe56e057', b'0', NULL, 1604910995, '49ba59abbe56e057', '18294d8b91ceb32f', 1, 'fa fa-birthday-cake', '工单节点处理结果样例表', 5, '_self', b'1', 'page/OrderBaseNodeResultType.html');
INSERT INTO `sys_menu` VALUES ('f15dff3695b542ea', 1591951182, '49ba59abbe56e057', b'0', NULL, 1592801022, '49ba59abbe56e057', '06134cc09475bf50', 1, 'fa fa-tree', 'zookeeper管理', 2, '_self', b'1', 'page/zookeeper.html');
INSERT INTO `sys_menu` VALUES ('f502af6e0d7a173a', 1591422214, '49ba59abbe56e057', b'0', NULL, 1591422214, '49ba59abbe56e057', '9a7233c34fae5188', 1, 'fa fa-adjust', '下拉选择', 4, '_self', b'1', 'page/table-select.html');
INSERT INTO `sys_menu` VALUES ('f815a449ebaa6f98', 1591524787, '49ba59abbe56e057', b'0', NULL, 1592800643, '49ba59abbe56e057', '2776dbc3d11e5958', 1, 'fa fa-check-circle', '权限集管理', 3, '_self', b'1', 'page/dept.html');
INSERT INTO `sys_menu` VALUES ('fa16e16ffa52123e', 1604910649, '49ba59abbe56e057', b'0', NULL, 1604910986, '49ba59abbe56e057', '18294d8b91ceb32f', 1, 'fa fa-birthday-cake', '工单节点属性样例表', 3, '_self', b'1', 'page/OrderBaseNodeField.html');
INSERT INTO `sys_menu` VALUES ('fb018d62c7e3286d', 1591421762, '49ba59abbe56e057', b'0', NULL, 1591421762, '49ba59abbe56e057', 'a7e0448690bbb112', 1, 'fa fa-adjust', '表单示例', 5, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('fbe5dd4f2df47d4d', 1591421995, '49ba59abbe56e057', b'0', NULL, 1591421995, '49ba59abbe56e057', 'd8db718af9ea9ca0', 1, 'fa fa-adjust', '登录-1', 1, '_blank', b'1', 'page/login-1.html');

-- ----------------------------
-- Table structure for sys_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_power`;
CREATE TABLE `sys_power`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `interface_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `method_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `test` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_power
-- ----------------------------
INSERT INTO `sys_power` VALUES ('03617f440cea6b74', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'getById', NULL);
INSERT INTO `sys_power` VALUES ('0633b68b40bb450c', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'deleteMenu', NULL);
INSERT INTO `sys_power` VALUES ('073b9b45094a9b6e', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'userLoginNoToken', NULL);
INSERT INTO `sys_power` VALUES ('087eb73ee78ac859', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'delete', NULL);
INSERT INTO `sys_power` VALUES ('0bab9e19aafba33b', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'getDepts', NULL);
INSERT INTO `sys_power` VALUES ('0ea708b67b6db61a', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'getById', NULL);
INSERT INTO `sys_power` VALUES ('12ee6109e0bb3a05', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'putPowersToDept', NULL);
INSERT INTO `sys_power` VALUES ('1bd4b8cbb8a484d0', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'deleteRoleDept', NULL);
INSERT INTO `sys_power` VALUES ('1d6400353b2c7df9', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES ('1e62e83e98579200', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'getAllDeptWithHaveMark', NULL);
INSERT INTO `sys_power` VALUES ('26ad7913a42a4012', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'insert', NULL);
INSERT INTO `sys_power` VALUES ('2921260d38938488', 1591928422, '49ba59abbe56e057', b'0', NULL, 1591928422, '49ba59abbe56e057', 'PowerService', 'deletePower', NULL);
INSERT INTO `sys_power` VALUES ('2b9264d09ebc4725', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'delete', NULL);
INSERT INTO `sys_power` VALUES ('2c2aa105d280b382', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES ('2fde0905418f905f', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'getRoles', NULL);
INSERT INTO `sys_power` VALUES ('34ade768ce9194ab', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'getTokenInfoByTokenNoToken', NULL);
INSERT INTO `sys_power` VALUES ('361bde39e6450974', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'update', NULL);
INSERT INTO `sys_power` VALUES ('4208423c6adaf651', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'getAllPowerWithHaveMark', NULL);
INSERT INTO `sys_power` VALUES ('441ae4672723532a', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'putDeptsToMenu', NULL);
INSERT INTO `sys_power` VALUES ('4633fbcba3ae4e48', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'insert', NULL);
INSERT INTO `sys_power` VALUES ('4a97f6d1b114a281', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'insert', NULL);
INSERT INTO `sys_power` VALUES ('4b0ec247c55bf8dc', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'update', NULL);
INSERT INTO `sys_power` VALUES ('4ec1d33f3ad93171', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'getUserById', NULL);
INSERT INTO `sys_power` VALUES ('4f38966db144ddab', 1591516117, '49ba59abbe56e057', b'1', NULL, 1592021427, '49ba59abbe56e057', 'DeptService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES ('4f59f9d8f9928219', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES ('5022bc9752a36f85', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'getIndexMenu', NULL);
INSERT INTO `sys_power` VALUES ('5185d68ce1fc645e', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'putMenusToDept', NULL);
INSERT INTO `sys_power` VALUES ('5864324df3a3b516', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'getById', NULL);
INSERT INTO `sys_power` VALUES ('5c56f85e0e4ad1ac', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'PowerService', 'insert', NULL);
INSERT INTO `sys_power` VALUES ('5e365d070328ddb6', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'insert', NULL);
INSERT INTO `sys_power` VALUES ('609162ad1792e7ab', 1591928421, '49ba59abbe56e057', b'0', NULL, 1591928421, '49ba59abbe56e057', 'LogService', 'pushRequestLogNoToken', NULL);
INSERT INTO `sys_power` VALUES ('6235bbf24cab81d3', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'PowerService', 'getById', NULL);
INSERT INTO `sys_power` VALUES ('6566d790e205feab', 1591928422, '49ba59abbe56e057', b'0', NULL, 1591928422, '49ba59abbe56e057', 'PowerService', 'initPowerInProStartNoToken', NULL);
INSERT INTO `sys_power` VALUES ('67034e753949e587', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'delete', NULL);
INSERT INTO `sys_power` VALUES ('6d76d28f2cf09362', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'PowerService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES ('71c56235bb525bb9', 1591928422, '49ba59abbe56e057', b'0', NULL, 1591928422, '49ba59abbe56e057', 'PowerService', 'getInterfaces', NULL);
INSERT INTO `sys_power` VALUES ('75624cb334ee12ea', 1591928421, '49ba59abbe56e057', b'0', NULL, 1591928421, '49ba59abbe56e057', 'LogService', 'insert', NULL);
INSERT INTO `sys_power` VALUES ('7d0b60c69d31eefe', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'getAllMenuWithHaveMark', NULL);
INSERT INTO `sys_power` VALUES ('7d0b6bb7e845a085', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES ('82e61a790c699a51', 1591928422, '49ba59abbe56e057', b'0', NULL, 1591928422, '49ba59abbe56e057', 'PowerService', 'checkUserHavePowerNoToken', NULL);
INSERT INTO `sys_power` VALUES ('8d8cd55454c18c24', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'delete', NULL);
INSERT INTO `sys_power` VALUES ('96acbd5182cd0d3e', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'getMenuTree', NULL);
INSERT INTO `sys_power` VALUES ('a06da41d1077d0c1', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'putDeptsToRole', NULL);
INSERT INTO `sys_power` VALUES ('a1acd418e7a93451', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'PowerService', 'update', NULL);
INSERT INTO `sys_power` VALUES ('adb7c89fac5eb483', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'getUsers', NULL);
INSERT INTO `sys_power` VALUES ('ae4b4f1e7ac72e36', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES ('b1ebeb0f15821c46', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'getUserTokenNoToken', NULL);
INSERT INTO `sys_power` VALUES ('b371c3f2c1aad78c', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'getUserDeptsByRoleId', NULL);
INSERT INTO `sys_power` VALUES ('b6dd845b313d96a5', 1592021694, '49ba59abbe56e057', b'0', NULL, 1592021694, '49ba59abbe56e057', 'DeptService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES ('b74b38b65f045e2c', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'PowerService', 'delete', NULL);
INSERT INTO `sys_power` VALUES ('ba21d33e5076c188', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'PowerService', 'getPowers', NULL);
INSERT INTO `sys_power` VALUES ('ba6a036120aeeaa4', 1591928421, '49ba59abbe56e057', b'0', NULL, 1591928421, '49ba59abbe56e057', 'LogService', 'update', NULL);
INSERT INTO `sys_power` VALUES ('bc498cd49ce0f365', 1591928422, '49ba59abbe56e057', b'0', NULL, 1591928422, '49ba59abbe56e057', 'RoleService', 'deleteRole', NULL);
INSERT INTO `sys_power` VALUES ('bcb1e1f4c62ed7cd', 1591928422, '49ba59abbe56e057', b'0', NULL, 1591928422, '49ba59abbe56e057', 'RoleService', 'getRoleByRoleIdNoToken', NULL);
INSERT INTO `sys_power` VALUES ('c5f7f4995324bbcc', 1591928421, '49ba59abbe56e057', b'0', NULL, 1591928421, '49ba59abbe56e057', 'LogService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES ('c6614064cea9512f', 1591928421, '49ba59abbe56e057', b'0', NULL, 1591928421, '49ba59abbe56e057', 'LogService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES ('ca88a9880af7bfc0', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'deleteDeptPower', NULL);
INSERT INTO `sys_power` VALUES ('cd25ac015ce75f13', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES ('d00d25d5f9c4f4f2', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'update', NULL);
INSERT INTO `sys_power` VALUES ('d959933225cad2db', 1591928422, '49ba59abbe56e057', b'0', NULL, 1591928422, '49ba59abbe56e057', 'DeptService', 'deleteDept', NULL);
INSERT INTO `sys_power` VALUES ('d9a0c39cb69d2925', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'getDeptsByMenuId', NULL);
INSERT INTO `sys_power` VALUES ('e4e94b65c7ce87a2', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'getById', NULL);
INSERT INTO `sys_power` VALUES ('e9aba7bc3ab3595b', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'PowerService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES ('e9b61283dd5f7daf', 1591928421, '49ba59abbe56e057', b'0', NULL, 1591928421, '49ba59abbe56e057', 'LogService', 'delete', NULL);
INSERT INTO `sys_power` VALUES ('ec727f845dae4342', 1591928421, '49ba59abbe56e057', b'0', NULL, 1591928421, '49ba59abbe56e057', 'LogService', 'getById', NULL);
INSERT INTO `sys_power` VALUES ('ef3ecb0af408d6fb', 1591928422, '49ba59abbe56e057', b'0', NULL, 1591928422, '49ba59abbe56e057', 'PowerService', 'getMethodNameByInterfaceName', NULL);
INSERT INTO `sys_power` VALUES ('f9dd4b0897c71a8c', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'update', NULL);
INSERT INTO `sys_power` VALUES ('facb12d4b4d1a7f6', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'getUserByIdNoToken', NULL);
INSERT INTO `sys_power` VALUES ('fbe3a84eda3bb9fd', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES ('fdb018e842f115ba', 1592021694, '49ba59abbe56e057', b'0', NULL, 1592021694, '49ba59abbe56e057', 'PowerService', 'initPowerInProStart', NULL);

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
INSERT INTO `sys_role` VALUES ('0f2ab23bbb1cd11e', 1591512602, '49ba59abbe56e057', b'0', NULL, 1591512602, '49ba59abbe56e057', 1, '测试角色');
INSERT INTO `sys_role` VALUES ('8c77ce2fa1a0c960', 1590636825, '49ba59abbe56e057', b'0', NULL, 1590636825, '49ba59abbe56e057', 1, '超级管理员角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `dept_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色-部门关联图' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('7e0b67e7e018b9b1', '6551f75c465a45bc', '0f2ab23bbb1cd11e');
INSERT INTO `sys_role_dept` VALUES ('dafd7b5d37b2785a', 'f1547c178c79b1c5', '8c77ce2fa1a0c960');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(0) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(0) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mail` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `head_portrait` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('49ba59abbe56e057', 1, 'god', b'0', '这个号是超级管理员', 1594204605, '49ba59abbe56e057', '超级管理员', '49ba59abbe56e057', '0f2ab23bbb1cd11e', 'admin', '247452312@qq.com', '17864217772', '01595660e75d3faf');
INSERT INTO `sys_user` VALUES ('5e3f98c07f37f815', 1591513241, '49ba59abbe56e057', b'0', NULL, 1591513241, '49ba59abbe56e057', '测试用户', 'ad97df80a4e03cb5', '0f2ab23bbb1cd11e', 'uhyils', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
