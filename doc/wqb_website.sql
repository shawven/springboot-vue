/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.10
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 192.168.1.10:3306
 Source Schema         : wqb_website

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 18/05/2019 18:15:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for advertising
-- ----------------------------
DROP TABLE IF EXISTS `advertising`;
CREATE TABLE `advertising`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告名称',
  `site_id` int(11) NOT NULL COMMENT '广告位置id',
  `pic_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `sort_no` int(10) NOT NULL DEFAULT 0 COMMENT '序号',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `is_enabled` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否启用：0不启用，1启用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `site_id`(`site_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of advertising
-- ----------------------------
INSERT INTO `advertising` VALUES (32, '发票', 1, 'D:\\images\\Override equals.png', NULL, 0, NULL, NULL, 1);
INSERT INTO `advertising` VALUES (33, '招商', 2, 'D:\\images\\功能结构图.jpg', NULL, 0, NULL, NULL, 1);
INSERT INTO `advertising` VALUES (34, '感恩回馈', 3, 'D:\\images\\功能结构图2.jpg', 'www.baidu.com', 0, NULL, NULL, 1);

-- ----------------------------
-- Table structure for advertising_site
-- ----------------------------
DROP TABLE IF EXISTS `advertising_site`;
CREATE TABLE `advertising_site`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告位名称',
  `platform` tinyint(4) NOT NULL DEFAULT 0 COMMENT '所属平台：0财税官网，1云服务软件，2商城',
  `width` double(10, 0) NULL DEFAULT NULL COMMENT '宽度',
  `height` double(10, 0) NULL DEFAULT NULL COMMENT '高度',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告位置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of advertising_site
-- ----------------------------
INSERT INTO `advertising_site` VALUES (1, '首页广告', 0, 1920, 580, NULL);
INSERT INTO `advertising_site` VALUES (2, '招商加盟banner广告', 0, 1920, 580, NULL);
INSERT INTO `advertising_site` VALUES (3, '财税知识banner广告', 0, 1920, 580, NULL);
INSERT INTO `advertising_site` VALUES (4, '商学院banner广告', 0, 1920, 580, NULL);
INSERT INTO `advertising_site` VALUES (5, '云记账学堂banner广告', 1, 1920, 580, NULL);
INSERT INTO `advertising_site` VALUES (6, '云记账学堂中间广告', 1, 1920, 580, NULL);

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主题',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章简介',
  `click_count` int(10) NULL DEFAULT NULL COMMENT '点击次数',
  `sort_no` int(10) NOT NULL DEFAULT 0 COMMENT '序号',
  `keyword` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字，以逗号隔开',
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `cover_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面图片',
  `is_enabled` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否启用：0不启用，1启用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '智慧财税时代已经来临，如何才能把握时代浪潮？', '系统包括报表平衡、资金负数、\r\n存货负数、往来长期挂账、 成本倒挂、资不抵债等\r\n多达26项税务风险点检测， 风险问题智能预警；\r\n企业财税健康度智能评分体系，\r\n将企业财税风险降到最低；', 1, 0, '财税,浪潮,时代,智慧', 'lxb', 'D:\\images\\功能结构图.jpg', 1, '2019-05-15 14:51:53', NULL);
INSERT INTO `article` VALUES (2, '微企宝“小规模自助记账报税”功能上线，仅需39.9元', '仪式上，北京春日启投资管理有限公司创始人CEO齐春香表示，为了适应春日启近年来高速发展的态势，在此之前完成了对会计服务的重新调整，未来会将会计从繁忙的工作过中解脱出来，开启“会计工厂”的代账模式，在这个契机下，与南京云帐房公司合作，两者强强联合，必能助力春日启公司快速发展壮大。齐总还表达了对公司未来的憧憬，她表示，春日启本着“连接、赋能、增值、普惠”的发展理念，实现共生、互生、再生的财税生态圈，打造出一家托拉斯式的财税帝国。', NULL, 1, '微企宝,小规模,报税,记账,自助', NULL, 'D:\\images\\功能结构图.jpg', 1, '2019-05-15 17:35:00', NULL);
INSERT INTO `article` VALUES (3, '国家又出减税政策，手把手教你get一般纳税人和小规模纳税人要点', '国家又出减税政策，手把手教你get一般纳税人和小规模纳税人要点', NULL, 2, '减税,一般纳税人,小规模', NULL, 'D:\\images\\功能结构图.jpg', 1, '2019-05-15 17:35:00', NULL);
INSERT INTO `article` VALUES (8, '新主题钉钉', '看看', NULL, 0, NULL, NULL, 'D:\\images\\账套初始化.jpg', 1, '2019-05-18 17:35:00', NULL);

-- ----------------------------
-- Table structure for article_content
-- ----------------------------
DROP TABLE IF EXISTS `article_content`;
CREATE TABLE `article_content`  (
  `article_id` int(11) NOT NULL COMMENT '文章id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章内容',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_content
-- ----------------------------
INSERT INTO `article_content` VALUES (1, '<p>\r\n    <br/>\r\n</p>\r\n<h1 style=\"border-bottom-color:#cccccc;border-bottom-width:2px;border-bottom-style:solid;padding:0px 4px 0px 0px;text-align:center;margin:0px 0px 20px;\" class=\"ue_t\">\r\n    [此处键入文章标题]\r\n</h1>\r\n<p>\r\n    <img src=\"http://img.baidu.com/hi/youa/y_0034.gif\" width=\"300\" height=\"200\"/>图文混排方法\r\n</p>\r\n<p>\r\n    1. 图片居左，文字围绕图片排版\r\n</p>\r\n<p>\r\n    方法：在文字前面插入图片，设置居左对齐，然后即可在右边输入多行文本\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>\r\n<p>\r\n    2. 图片居右，文字围绕图片排版\r\n</p>\r\n<p>\r\n    方法：在文字前面插入图片，设置居右对齐，然后即可在左边输入多行文本\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>\r\n<p>\r\n    3. 图片居中环绕排版\r\n</p>\r\n<p>\r\n    方法：亲，这个真心没有办法。。。\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>\r\n<p>\r\n    <img src=\"http://img.baidu.com/hi/youa/y_0040.gif\" width=\"300\" height=\"300\"/>\r\n</p>\r\n<p>\r\n    还有没有什么其他的环绕方式呢？这里是居右环绕\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>\r\n<p>\r\n    欢迎大家多多尝试，为UEditor提供更多高质量模板！\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>\r\n<p>\r\n    占位\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>\r\n<p>\r\n    占位\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>\r\n<p>\r\n    占位\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>\r\n<p>\r\n    占位\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>\r\n<p>\r\n    占位\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>');
INSERT INTO `article_content` VALUES (2, '<h1 style=\"border-bottom-color:#cccccc;border-bottom-width:2px;border-bottom-style:solid;padding:0px 4px 0px 0px;margin:0px 0px 10px;\">\r\n    <span style=\"color:#e36c09;\" class=\"ue_t\">[此处键入简历标题]</span>\r\n</h1>\r\n<p>\r\n    <span style=\"color:#e36c09;\"><br/></span>\r\n</p>\r\n<table width=\"100%\" border=\"1\" style=\"border-collapse:collapse;\">\r\n    <tbody>\r\n        <tr class=\"firstRow\">\r\n            <td width=\"200\" style=\"text-align:center;\" class=\"ue_t\">\r\n                【此处插入照片】\r\n            </td>\r\n            <td>\r\n                <p>\r\n                    <br/>\r\n                </p>\r\n                <p>\r\n                    联系电话：<span class=\"ue_t\">[键入您的电话]</span>\r\n                </p>\r\n                <p>\r\n                    <br/>\r\n                </p>\r\n                <p>\r\n                    电子邮件：<span class=\"ue_t\">[键入您的电子邮件地址]</span>\r\n                </p>\r\n                <p>\r\n                    <br/>\r\n                </p>\r\n                <p>\r\n                    家庭住址：<span class=\"ue_t\">[键入您的地址]</span>\r\n                </p>\r\n                <p>\r\n                    <br/>\r\n                </p>\r\n            </td>\r\n        </tr>\r\n    </tbody>\r\n</table>\r\n<h3>\r\n    <span style=\"color:#e36c09;font-size:20px;\">目标职位</span>\r\n</h3>\r\n<p style=\"text-indent:2em;\" class=\"ue_t\">\r\n    [此处键入您的期望职位]\r\n</p>\r\n<h3>\r\n    <span style=\"color:#e36c09;font-size:20px;\">学历</span>\r\n</h3>\r\n<p>\r\n    <span style=\"display:none;line-height:0px;\">﻿</span>\r\n</p>\r\n<ol style=\"list-style-type: decimal;\" class=\" list-paddingleft-2\">\r\n    <li>\r\n        <p>\r\n            <span class=\"ue_t\">[键入起止时间]</span> <span class=\"ue_t\">[键入学校名称] </span> <span class=\"ue_t\">[键入所学专业]</span> <span class=\"ue_t\">[键入所获学位]</span>\r\n        </p>\r\n    </li>\r\n    <li>\r\n        <p>\r\n            <span class=\"ue_t\">[键入起止时间]</span> <span class=\"ue_t\">[键入学校名称]</span> <span class=\"ue_t\">[键入所学专业]</span> <span class=\"ue_t\">[键入所获学位]</span>\r\n        </p>\r\n    </li>\r\n</ol>\r\n<h3>\r\n    <span style=\"color:#e36c09;font-size:20px;\" class=\"ue_t\">工作经验</span>\r\n</h3>\r\n<ol style=\"list-style-type: decimal;\" class=\" list-paddingleft-2\">\r\n    <li>\r\n        <p>\r\n            <span class=\"ue_t\">[键入起止时间]</span> <span class=\"ue_t\">[键入公司名称]</span> <span class=\"ue_t\">[键入职位名称]</span>\r\n        </p>\r\n    </li>\r\n    <ol style=\"list-style-type: lower-alpha;\" class=\" list-paddingleft-2\">\r\n        <li>\r\n            <p>\r\n                <span class=\"ue_t\">[键入负责项目]</span> <span class=\"ue_t\">[键入项目简介]</span>\r\n            </p>\r\n        </li>\r\n        <li>\r\n            <p>\r\n                <span class=\"ue_t\">[键入负责项目]</span> <span class=\"ue_t\">[键入项目简介]</span>\r\n            </p>\r\n        </li>\r\n    </ol>\r\n    <li>\r\n        <p>\r\n            <span class=\"ue_t\">[键入起止时间]</span> <span class=\"ue_t\">[键入公司名称]</span> <span class=\"ue_t\">[键入职位名称]</span>\r\n        </p>\r\n    </li>\r\n    <ol style=\"list-style-type: lower-alpha;\" class=\" list-paddingleft-2\">\r\n        <li>\r\n            <p>\r\n                <span class=\"ue_t\">[键入负责项目]</span> <span class=\"ue_t\">[键入项目简介]</span>\r\n            </p>\r\n        </li>\r\n    </ol>\r\n</ol>\r\n<p>\r\n    <span style=\"color:#e36c09;font-size:20px;\">掌握技能</span>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n    &nbsp;<span class=\"ue_t\">[这里可以键入您所掌握的技能]</span><br/>\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>');
INSERT INTO `article_content` VALUES (3, '<h2 style=\"border-bottom-color:#cccccc;border-bottom-width:2px;border-bottom-style:solid;padding:0px 4px 0px 0px;margin:0px 0px 10px;text-align:center;\" class=\"ue_t\">\r\n    [键入文章标题]\r\n</h2>\r\n<p>\r\n    <strong><span style=\"font-size:12px;\">摘要</span></strong><span style=\"font-size:12px;\" class=\"ue_t\">：这里可以输入很长很长很长很长很长很长很长很长很差的摘要</span>\r\n</p>\r\n<p style=\"line-height:1.5em;\">\r\n    <strong>标题 1</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n    <span style=\"font-size:14px;\" class=\"ue_t\">这里可以输入很多内容，可以图文混排，可以有列表等。</span>\r\n</p>\r\n<p style=\"line-height:1.5em;\">\r\n    <strong>标题 2</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n    <span style=\"font-size:14px;\" class=\"ue_t\">来个列表瞅瞅：</span>\r\n</p>\r\n<ol style=\"list-style-type: lower-alpha;\" class=\" list-paddingleft-2\">\r\n    <li>\r\n        <p class=\"ue_t\">\r\n            列表 1\r\n        </p>\r\n    </li>\r\n    <li>\r\n        <p class=\"ue_t\">\r\n            列表 2\r\n        </p>\r\n    </li>\r\n    <ol style=\"list-style-type: lower-roman;\" class=\" list-paddingleft-2\">\r\n        <li>\r\n            <p class=\"ue_t\">\r\n                多级列表 1\r\n            </p>\r\n        </li>\r\n        <li>\r\n            <p class=\"ue_t\">\r\n                多级列表 2\r\n            </p>\r\n        </li>\r\n    </ol>\r\n    <li>\r\n        <p class=\"ue_t\">\r\n            列表 3<br/>\r\n        </p>\r\n    </li>\r\n</ol>\r\n<p style=\"line-height:1.5em;\">\r\n    <strong>标题 3</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n    <span style=\"font-size:14px;\" class=\"ue_t\">来个文字图文混排的</span>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n    <span style=\"font-size:14px;\" class=\"ue_t\">这里可以多行</span>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n    <span style=\"font-size:14px;\" class=\"ue_t\">右边是图片</span>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n    <span style=\"font-size:14px;\" class=\"ue_t\">绝对没有问题的，不信你也可以试试看</span>\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>');
INSERT INTO `article_content` VALUES (8, '咔咔咔咔咔咔');

-- ----------------------------
-- Table structure for article_type
-- ----------------------------
DROP TABLE IF EXISTS `article_type`;
CREATE TABLE `article_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `art_id` int(11) NOT NULL COMMENT '文章id',
  `type_id` int(11) NOT NULL COMMENT '栏目id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_type
-- ----------------------------
INSERT INTO `article_type` VALUES (3, 1, 1);
INSERT INTO `article_type` VALUES (4, 1, 2);
INSERT INTO `article_type` VALUES (5, 2, 1);
INSERT INTO `article_type` VALUES (6, 8, 1);

-- ----------------------------
-- Table structure for partner
-- ----------------------------
DROP TABLE IF EXISTS `partner`;
CREATE TABLE `partner`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contacts` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '类型：0个人代理，1企业代理',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态：0未处理，1已处理',
  `level` tinyint(4) NULL DEFAULT NULL COMMENT '级别：0创客，1合伙人，2VIP合伙人',
  `company` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司',
  `province` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省级',
  `city` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市级',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '加盟合作' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of partner
-- ----------------------------
INSERT INTO `partner` VALUES (1, '黄小米', '13821312345\r\n\r\n', 0, 1, 1, '\r\n深圳天源迪科科技有限公司', '广东省', '深圳市', NULL, '2019-05-17 20:45:31', NULL);
INSERT INTO `partner` VALUES (2, '二嘎子', '15680364200', 1, 1, 2, '\r\n深圳微企宝科技有限公司', '广东省', '深圳市', NULL, '2019-05-17 20:45:31', NULL);
INSERT INTO `partner` VALUES (3, '张二蛋', '15680364200', 1, 1, 0, '\r\n深圳荣灿科技有限公司', '广东省', '深圳市', NULL, '2019-05-17 20:45:31', NULL);

-- ----------------------------
-- Table structure for platform_type
-- ----------------------------
DROP TABLE IF EXISTS `platform_type`;
CREATE TABLE `platform_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '栏目名称',
  `platform` tinyint(4) NOT NULL DEFAULT 0 COMMENT '所属平台：0财税官网，1云服务软件，2商城',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0禁用，1启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_type
-- ----------------------------
INSERT INTO `platform_type` VALUES (1, '公司动态', 0, 1);
INSERT INTO `platform_type` VALUES (2, '新闻资讯', 1, 1);
INSERT INTO `platform_type` VALUES (3, '财税头条', 1, 1);

-- ----------------------------
-- Table structure for trial_customer
-- ----------------------------
DROP TABLE IF EXISTS `trial_customer`;
CREATE TABLE `trial_customer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contacts` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：0试用客户，1正式客户，2已过期',
  `follow_up` tinyint(255) NOT NULL DEFAULT 0 COMMENT '回访状态：0未处理，1已处理',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trial_customer
-- ----------------------------
INSERT INTO `trial_customer` VALUES (1, '张三', '15900000000', 0, 1, '列表按申请时间降序分页显示；1111111', '2019-05-15 17:35:00', '2019-05-18 17:32:02');
INSERT INTO `trial_customer` VALUES (2, '李四', '13865425478', 1, 0, '333333333333333', '2019-05-17 20:42:18', '2019-05-18 17:23:15');
INSERT INTO `trial_customer` VALUES (3, '王五', '13965425487', 2, 0, '没必要11333333333', '2019-05-17 20:43:10', '2019-05-18 17:23:29');
INSERT INTO `trial_customer` VALUES (4, '小六', '15963245784', 1, 0, '关键词搜索：联系人姓名  or  联系电话  进行333模糊筛选；', '2019-05-17 20:43:12', '2019-05-18 15:40:24');

SET FOREIGN_KEY_CHECKS = 1;
