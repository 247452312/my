/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.101
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 192.168.1.101:3306
 Source Schema         : my

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 07/06/2020 18:33:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_content
-- ----------------------------
DROP TABLE IF EXISTS `sys_content`;
CREATE TABLE `sys_content`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(20) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(20) NULL DEFAULT NULL,
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
  `create_date` bigint(20) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(20) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept_menu
-- ----------------------------
INSERT INTO `sys_dept_menu` VALUES ('03e96b278b3e7bdd', '6551f75c465a45bc', '6d2d9727b9cd94c8');
INSERT INTO `sys_dept_menu` VALUES ('072309df159f2a23', 'f1547c178c79b1c5', 'bdfc9c161b61af22');
INSERT INTO `sys_dept_menu` VALUES ('0821327cf4b15355', '6551f75c465a45bc', '2051158803092292');
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
INSERT INTO `sys_dept_menu` VALUES ('73132f1e746f1707', 'f1547c178c79b1c5', '2051158803092292');
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_dept_power` VALUES ('27520a9c0dddda80', '6551f75c465a45bc', '4f38966db144ddab');
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
INSERT INTO `sys_dept_power` VALUES ('b585da25d080df0a', 'f1547c178c79b1c5', '4f38966db144ddab');
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
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(20) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(20) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(20) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(20) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bean_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cron` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `method_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pause` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(20) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(20) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `interface_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `log_type` int(11) NULL DEFAULT NULL,
  `method_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `time` bigint(20) NULL DEFAULT NULL,
  `user_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(20) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(20) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `i_frame` int(11) NULL DEFAULT NULL,
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` bit(1) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0bc916517931ec27', 1591524769, '49ba59abbe56e057', b'0', NULL, 1591524769, '49ba59abbe56e057', '2776dbc3d11e5958', 1, 'icon0', '权限管理', 2, '_self', b'1', 'page/power.html');
INSERT INTO `sys_menu` VALUES ('13a809f2b6223b3d', 1591421821, '49ba59abbe56e057', b'0', NULL, 1591421821, '49ba59abbe56e057', 'a7e0448690bbb112', 1, 'icon0', '异常页面', 7, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('171242cf975407a7', 1591422020, '49ba59abbe56e057', b'0', NULL, 1591422020, '49ba59abbe56e057', 'd8db718af9ea9ca0', 1, 'icon0', '登录-2', 2, '_blank', b'1', 'page/login-2.html');
INSERT INTO `sys_menu` VALUES ('2051158803092292', 1591417727, '49ba59abbe56e057', b'0', NULL, 1591421690, '49ba59abbe56e057', 'a7e0448690bbb112', 1, 'icon0', '菜单管理', 2, '_self', b'1', 'page/menu.html');
INSERT INTO `sys_menu` VALUES ('2776dbc3d11e5958', 1591524719, '49ba59abbe56e057', b'0', NULL, 1591524719, '49ba59abbe56e057', '2f4b3196f0a23652', 1, 'icon0', '权限管理', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('2f4b3196f0a23652', 1, '49ba59abbe56e057', b'0', NULL, 1591417498, '49ba59abbe56e057', '', 1, 'icon0', '我的页面', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('3c1d8ee22772db7a', 1, '49ba59abbe56e057', b'0', NULL, 1591417700, '49ba59abbe56e057', '', 1, 'icon0', '其他管理', 3, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('3f232dd9b523fcdf', 1591421937, '49ba59abbe56e057', b'0', NULL, 1591421937, '49ba59abbe56e057', 'fb018d62c7e3286d', 1, 'icon0', '普通表单', 1, '_self', b'1', 'page/form.html');
INSERT INTO `sys_menu` VALUES ('56c1680bebdb08fd', 1591422290, '49ba59abbe56e057', b'0', NULL, 1591422290, '49ba59abbe56e057', '9a7233c34fae5188', 1, 'icon0', '省市县区选择器', 7, '_self', b'1', 'page/area.html');
INSERT INTO `sys_menu` VALUES ('6d2d9727b9cd94c8', 1591422101, '49ba59abbe56e057', b'0', NULL, 1591422101, '49ba59abbe56e057', 'e9b648568a0f9990', 1, 'icon0', '弹出层', 2, '_self', b'1', 'page/layer.html');
INSERT INTO `sys_menu` VALUES ('6e54f3558a6bc91d', 1591422420, '49ba59abbe56e057', b'0', NULL, 1591422420, '49ba59abbe56e057', 'a91fa5d15ce84c73', 1, 'icon0', '按钮4', 2, '_self', b'1', 'page/form.html?v=1');
INSERT INTO `sys_menu` VALUES ('70bad23c6497c8c8', 1591422310, '49ba59abbe56e057', b'0', NULL, 1591422310, '49ba59abbe56e057', '3c1d8ee22772db7a', 1, 'icon0', '多级菜单', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('799962d65dfa819b', 1591422264, '49ba59abbe56e057', b'0', NULL, 1591422264, '49ba59abbe56e057', '9a7233c34fae5188', 1, 'icon0', '富文本编辑器', 6, '_self', b'1', 'page/editor.html');
INSERT INTO `sys_menu` VALUES ('7f7ac8d39b0b5c2b', 1591422333, '49ba59abbe56e057', b'0', NULL, 1591422333, '49ba59abbe56e057', '3c1d8ee22772db7a', 1, 'icon0', '失效菜单', 2, '_self', b'1', 'page/error.html');
INSERT INTO `sys_menu` VALUES ('8443efbb0d49201a', 1591422152, '49ba59abbe56e057', b'0', NULL, 1591422152, '49ba59abbe56e057', '9a7233c34fae5188', 1, 'icon0', '图标选择', 2, '_self', b'1', 'page/icon-picker.html');
INSERT INTO `sys_menu` VALUES ('859ff5d974e2e6ab', 1591422129, '49ba59abbe56e057', b'0', NULL, 1591422129, '49ba59abbe56e057', '9a7233c34fae5188', 1, 'icon0', '图标列表', 1, '_self', b'1', 'page/icon.html');
INSERT INTO `sys_menu` VALUES ('864868c4d270210f', 1591421740, '49ba59abbe56e057', b'0', NULL, 1591421740, '49ba59abbe56e057', 'a7e0448690bbb112', 1, 'icon0', '表格示例', 4, '_self', b'1', 'page/table.html');
INSERT INTO `sys_menu` VALUES ('8a3d33c4a4491749', 1591422393, '49ba59abbe56e057', b'0', NULL, 1591422393, '49ba59abbe56e057', 'a91fa5d15ce84c73', 1, 'icon0', '按钮3', 1, '_self', b'1', 'page/button.html?v=3');
INSERT INTO `sys_menu` VALUES ('8d5732647336d4c7', 1591524814, '49ba59abbe56e057', b'0', NULL, 1591524814, '49ba59abbe56e057', '2776dbc3d11e5958', 1, 'icon0', '角色管理', 4, '_self', b'1', 'page/role.html');
INSERT INTO `sys_menu` VALUES ('8d8daed3ae6a690c', 1591422352, '49ba59abbe56e057', b'0', NULL, 1591422352, '49ba59abbe56e057', '70bad23c6497c8c8', 1, 'icon0', '按钮1', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('8e71e8d3638f6592', 1591421677, '49ba59abbe56e057', b'0', NULL, 1591421677, '49ba59abbe56e057', 'a7e0448690bbb112', 1, 'icon0', '系统设置', 3, '_self', b'1', 'page/setting.html');
INSERT INTO `sys_menu` VALUES ('9a7233c34fae5188', 1, '49ba59abbe56e057', b'0', NULL, 1591417700, '49ba59abbe56e057', '', 1, 'icon0', '组件管理', 2, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('9f82981e563a516e', 1591524846, '49ba59abbe56e057', b'0', NULL, 1591524846, '49ba59abbe56e057', '2776dbc3d11e5958', 1, 'icon0', '用户管理', 5, '_self', b'1', 'page/user.html');
INSERT INTO `sys_menu` VALUES ('a7e0448690bbb112', 1, '49ba59abbe56e057', b'0', NULL, 1591417498, '49ba59abbe56e057', '', 1, 'icon0', '常规管理', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('a91fa5d15ce84c73', 1591422368, '49ba59abbe56e057', b'0', NULL, 1591422368, '49ba59abbe56e057', '8d8daed3ae6a690c', 1, 'icon0', '按钮2', 1, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('accd3db9aa88e27f', 1591422077, '49ba59abbe56e057', b'0', NULL, 1591422077, '49ba59abbe56e057', 'e9b648568a0f9990', 1, 'icon0', '按钮示例', 1, '_self', b'1', 'page/button.html');
INSERT INTO `sys_menu` VALUES ('bdfc9c161b61af22', 1591422179, '49ba59abbe56e057', b'0', NULL, 1591422179, '49ba59abbe56e057', '9a7233c34fae5188', 1, 'icon0', '颜色选择', 3, '_self', b'1', 'page/color-select.html');
INSERT INTO `sys_menu` VALUES ('cab15d86936dc6d2', 1591524740, '49ba59abbe56e057', b'0', NULL, 1591524740, '49ba59abbe56e057', '2776dbc3d11e5958', 1, 'icon0', '菜单管理', 1, '_self', b'1', 'page/menu.html');
INSERT INTO `sys_menu` VALUES ('cb66e93bf6df43bb', 1591422240, '49ba59abbe56e057', b'0', NULL, 1591422240, '49ba59abbe56e057', '9a7233c34fae5188', 1, 'icon0', '文件上传', 5, '_self', b'1', 'page/upload.html');
INSERT INTO `sys_menu` VALUES ('cff03c3ff625db25', 1591422045, '49ba59abbe56e057', b'0', NULL, 1591422045, '49ba59abbe56e057', '13a809f2b6223b3d', 1, 'icon0', '404页面', 1, '_self', b'1', 'page/404.html');
INSERT INTO `sys_menu` VALUES ('d8db718af9ea9ca0', 1591421807, '49ba59abbe56e057', b'0', NULL, 1591421807, '49ba59abbe56e057', 'a7e0448690bbb112', 1, 'icon0', '登录模板', 6, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('e9b648568a0f9990', 1591421832, '49ba59abbe56e057', b'0', NULL, 1591421832, '49ba59abbe56e057', 'a7e0448690bbb112', 1, 'icon0', '其他页面', 8, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('eb0058ce33fc0f70', 1591421962, '49ba59abbe56e057', b'0', NULL, 1591421962, '49ba59abbe56e057', 'fb018d62c7e3286d', 1, 'icon0', '分步表单', 2, '_self', b'1', 'page/form-step.html');
INSERT INTO `sys_menu` VALUES ('f502af6e0d7a173a', 1591422214, '49ba59abbe56e057', b'0', NULL, 1591422214, '49ba59abbe56e057', '9a7233c34fae5188', 1, 'icon0', '下拉选择', 4, '_self', b'1', 'page/table-select.html');
INSERT INTO `sys_menu` VALUES ('f815a449ebaa6f98', 1591524787, '49ba59abbe56e057', b'0', NULL, 1591524787, '49ba59abbe56e057', '2776dbc3d11e5958', 1, 'icon0', '权限集管理', 3, '_self', b'1', 'page/dept.html');
INSERT INTO `sys_menu` VALUES ('fb018d62c7e3286d', 1591421762, '49ba59abbe56e057', b'0', NULL, 1591421762, '49ba59abbe56e057', 'a7e0448690bbb112', 1, 'icon0', '表单示例', 5, '', b'0', '');
INSERT INTO `sys_menu` VALUES ('fbe5dd4f2df47d4d', 1591421995, '49ba59abbe56e057', b'0', NULL, 1591421995, '49ba59abbe56e057', 'd8db718af9ea9ca0', 1, 'icon0', '登录-1', 1, '_blank', b'1', 'page/login-1.html');

-- ----------------------------
-- Table structure for sys_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_power`;
CREATE TABLE `sys_power`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(20) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(20) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `interface_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `method_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `test` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_power` VALUES ('4f38966db144ddab', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES ('4f59f9d8f9928219', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES ('5022bc9752a36f85', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'getIndexMenu', NULL);
INSERT INTO `sys_power` VALUES ('5185d68ce1fc645e', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'putMenusToDept', NULL);
INSERT INTO `sys_power` VALUES ('5864324df3a3b516', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'getById', NULL);
INSERT INTO `sys_power` VALUES ('5c56f85e0e4ad1ac', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'PowerService', 'insert', NULL);
INSERT INTO `sys_power` VALUES ('5e365d070328ddb6', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'insert', NULL);
INSERT INTO `sys_power` VALUES ('6235bbf24cab81d3', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'PowerService', 'getById', NULL);
INSERT INTO `sys_power` VALUES ('67034e753949e587', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'delete', NULL);
INSERT INTO `sys_power` VALUES ('6d76d28f2cf09362', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'PowerService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES ('7d0b60c69d31eefe', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'getAllMenuWithHaveMark', NULL);
INSERT INTO `sys_power` VALUES ('7d0b6bb7e845a085', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES ('8d8cd55454c18c24', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'delete', NULL);
INSERT INTO `sys_power` VALUES ('96acbd5182cd0d3e', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'getMenuTree', NULL);
INSERT INTO `sys_power` VALUES ('a06da41d1077d0c1', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'putDeptsToRole', NULL);
INSERT INTO `sys_power` VALUES ('a1acd418e7a93451', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'PowerService', 'update', NULL);
INSERT INTO `sys_power` VALUES ('adb7c89fac5eb483', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'getUsers', NULL);
INSERT INTO `sys_power` VALUES ('ae4b4f1e7ac72e36', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'countByArgs', NULL);
INSERT INTO `sys_power` VALUES ('b1ebeb0f15821c46', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'getUserTokenNoToken', NULL);
INSERT INTO `sys_power` VALUES ('b371c3f2c1aad78c', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'RoleService', 'getUserDeptsByRoleId', NULL);
INSERT INTO `sys_power` VALUES ('b74b38b65f045e2c', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'PowerService', 'delete', NULL);
INSERT INTO `sys_power` VALUES ('ba21d33e5076c188', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'PowerService', 'getPowers', NULL);
INSERT INTO `sys_power` VALUES ('ca88a9880af7bfc0', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'deleteDeptPower', NULL);
INSERT INTO `sys_power` VALUES ('cd25ac015ce75f13', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES ('d00d25d5f9c4f4f2', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'update', NULL);
INSERT INTO `sys_power` VALUES ('d9a0c39cb69d2925', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'getDeptsByMenuId', NULL);
INSERT INTO `sys_power` VALUES ('e4e94b65c7ce87a2', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'DeptService', 'getById', NULL);
INSERT INTO `sys_power` VALUES ('e9aba7bc3ab3595b', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'PowerService', 'getByArgs', NULL);
INSERT INTO `sys_power` VALUES ('f9dd4b0897c71a8c', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'MenuService', 'update', NULL);
INSERT INTO `sys_power` VALUES ('facb12d4b4d1a7f6', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'getUserByIdNoToken', NULL);
INSERT INTO `sys_power` VALUES ('fbe3a84eda3bb9fd', 1591516117, '49ba59abbe56e057', b'0', NULL, 1591516117, '49ba59abbe56e057', 'UserService', 'countByArgs', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(20) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(20) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('7e0b67e7e018b9b1', '6551f75c465a45bc', '0f2ab23bbb1cd11e');
INSERT INTO `sys_role_dept` VALUES ('dafd7b5d37b2785a', 'f1547c178c79b1c5', '8c77ce2fa1a0c960');

-- ----------------------------
-- Table structure for sys_server
-- ----------------------------
DROP TABLE IF EXISTS `sys_server`;
CREATE TABLE `sys_server`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(20) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(20) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `port` int(11) NULL DEFAULT NULL,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_software
-- ----------------------------
DROP TABLE IF EXISTS `sys_software`;
CREATE TABLE `sys_software`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(20) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(20) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `other_1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `other_2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `port` int(11) NULL DEFAULT NULL,
  `server_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `start_sh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `status_sh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `stop_sh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sub_type` int(11) NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_date` bigint(20) NULL DEFAULT NULL,
  `create_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delete_flag` bit(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` bigint(20) NULL DEFAULT NULL,
  `update_user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('49ba59abbe56e057', 1, 'god', b'0', '这个号是超级管理员', 1591452854, '49ba59abbe56e057', '超级管理员', '49ba59abbe56e057', '8c77ce2fa1a0c960', 'admin');
INSERT INTO `sys_user` VALUES ('5e3f98c07f37f815', 1591513241, '49ba59abbe56e057', b'0', NULL, 1591513241, '49ba59abbe56e057', '测试用户', 'ad97df80a4e03cb5', '0f2ab23bbb1cd11e', 'uhyils');

SET FOREIGN_KEY_CHECKS = 1;
