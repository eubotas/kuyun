/*
Navicat MySQL Data Transfer

Source Server         : localhost_root
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : kuyun

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2018-01-06 07:43:24
*/

SET FOREIGN_KEY_CHECKS=0;
SET SQL_SAFE_UPDATES = 0;

-- ----------------------------
-- Table structure for `eam_ticket`
-- ----------------------------
DROP TABLE IF EXISTS `eam_ticket`;
CREATE TABLE `eam_ticket` (
  `ticket_id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_type_id` int(11) DEFAULT NULL,
  `equipment_category_id` int(11) DEFAULT NULL,
  `equipment_id` varchar(32) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `voice_path` varchar(2000) DEFAULT NULL COMMENT '语音',
  `image_path` varchar(2000) DEFAULT NULL COMMENT '上传图片',
  `priority` varchar(10) DEFAULT NULL COMMENT '优先级（一般，紧急, 非常紧急）',
  `executor_id` int(11) DEFAULT NULL COMMENT '处理人',
  `status` varchar(10) DEFAULT NULL COMMENT '状态（待派工, 待维修, 维修中, 待评价, 评价完成）',
  `end_date` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ticket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `eam_ticket_appointed_record`
-- ----------------------------
DROP TABLE IF EXISTS `eam_ticket_appointed_record`;
CREATE TABLE `eam_ticket_appointed_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_id` int(11) DEFAULT NULL,
  `order_taker_id` int(11) DEFAULT NULL COMMENT '接单人',
  `reject_commont` varchar(100) DEFAULT NULL COMMENT '拒单原因，可为空',
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `eam_ticket_assessment`
-- ----------------------------
DROP TABLE IF EXISTS `eam_ticket_assessment`;
CREATE TABLE `eam_ticket_assessment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_id` int(11) DEFAULT NULL,
  `assessment_user_id` int(11) DEFAULT NULL COMMENT '评价人ID',
  `assessment_level` int(11) DEFAULT NULL COMMENT '评价星级',
  `description` varchar(2000) DEFAULT NULL COMMENT '评价描述',
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `eam_ticket_assessment_tag`
-- ----------------------------
DROP TABLE IF EXISTS `eam_ticket_assessment_tag`;
CREATE TABLE `eam_ticket_assessment_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_id` int(11) DEFAULT NULL,
  `assessment_id` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL COMMENT '评价标签ID，from eam_tag',
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `eam_ticket_record`
-- ----------------------------
DROP TABLE IF EXISTS `eam_ticket_record`;
CREATE TABLE `eam_ticket_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_id` int(11) DEFAULT NULL,
  `step` varchar(20) DEFAULT NULL COMMENT '处理步骤',
  `comments` varchar(200) DEFAULT NULL COMMENT '处理内容',
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `eam_ticket_tag`
-- ----------------------------
DROP TABLE IF EXISTS `eam_ticket_tag`;
CREATE TABLE `eam_ticket_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL COMMENT '标签名',
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `eam_ticket_type`
-- ----------------------------
DROP TABLE IF EXISTS `eam_ticket_type`;
CREATE TABLE `eam_ticket_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



delete from upms_permission where uri like '/manage/ticket%';

INSERT INTO `upms_permission` VALUES ('310', '6', '300', '工单类型', '2', 'eam:ticketType:read', '/manage/ticket/type/index', NULL, 1, 221, 410);
INSERT INTO `upms_permission` VALUES ('311', '6', '310', '新增类型', '3', 'eam:ticketType:create', '/manage/ticket/type/create', 'zmdi zmdi-plus', 1, 1489820150404, 311);
INSERT INTO `upms_permission` VALUES ('312', '6', '310', '编辑类型', '3', 'eam:ticketType:update', '/manage/ticket/type/update', 'zmdi zmdi-edit', 1, 1489820178269, 312);
INSERT INTO `upms_permission` VALUES ('313', '6', '310', '删除类型', '3', 'eam:ticketType:delete', '/manage/ticket/type/delete', 'zmdi zmdi-close', 1, 1489820207607, 313);
INSERT INTO `upms_permission` VALUES ('320', '6', '300', '我的未处理工单', '2', 'eam:ticket:read', '/manage/ticket/index?category=myOpen', NULL, 1, 225, 320);
INSERT INTO `upms_permission` VALUES ('330', '6', '300', '我的全部工单', '2', 'eam:ticket:read', '/manage/ticket/index?category=myAll', NULL, 1, 225, 330);
INSERT INTO `upms_permission` VALUES ('340', '6', '300', '未委派工单', '2', 'eam:ticket:read', '/manage/ticket/index?category=init', NULL, 1, 225, 340);
INSERT INTO `upms_permission` VALUES ('350', '6', '300', '全部工单', '2', 'eam:ticket:read', '/manage/ticket/index?category=all', NULL, 1, 225, 350);
INSERT INTO `upms_permission` VALUES ('351', '6', '350', '新增工单', '3', 'eam:ticket:create', '/manage/ticket/create', 'zmdi zmdi-plus', 1, 1489820150404, 1489820150404);
INSERT INTO `upms_permission` VALUES ('352', '6', '350', '编辑工单', '3', 'eam:ticket:update', '/manage/ticket/update', 'zmdi zmdi-edit', 1, 1489820178269, 1489820178269);
INSERT INTO `upms_permission` VALUES ('353', '6', '350', '删除工单', '3', 'eam:ticket:delete', '/manage/ticket/delete', 'zmdi zmdi-close', 1, 1489820207607, 1489820207607);

INSERT INTO `upms_permission` VALUES ('440', '6', '300', '工单评价标签', '2', 'eam:ticketTag:read', '/manage/ticket/tag/index', NULL, 1, 1, 440);
INSERT INTO `upms_permission` VALUES ('441', '6', '440', '新增工单评价标签', '3', 'eam:ticketTag:create', '/manage/ticket/tag/create', 'zmdi zmdi-plus', 1, 1489820150404, 441);
INSERT INTO `upms_permission` VALUES ('442', '6', '440', '编辑工单评价标签', '3', 'eam:ticketTag:update', '/manage/ticket/tag/update', 'zmdi zmdi-edit', 1, 1489820178269, 442);
INSERT INTO `upms_permission` VALUES ('443', '6', '440', '删除工单评价标签', '3', 'eam:ticketTag:delete', '/manage/ticket/tag/delete', 'zmdi zmdi-close', 1, 1489820207607, 443);
INSERT INTO `upms_permission` VALUES ('445', '6', '350', '新增工单记录', '3', 'eam:ticketRecord:create', '/manage/ticket/{tid}/record/create', 'zmdi zmdi-plus', 1, 1489820150404, 445);
INSERT INTO `upms_permission` VALUES ('446', '6', '350', '编辑工单记录', '3', 'eam:ticketRecord:update', '/manage/ticket/{tid}/record/update', 'zmdi zmdi-edit', 1, 1489820178269, 444);
INSERT INTO `upms_permission` VALUES ('447', '6', '350', '工单记录', '3', 'eam:ticketRecord:read', '/manage/ticket/{tid}/record/index', 'zmdi zmdi-close', 1, 1489820207607, 447);
INSERT INTO `upms_permission` VALUES ('451', '6', '350', '新增委派记录', '3', 'eam:ticketAppointedRecord:create', '/manage/ticket/{ticketId}/appoint/create', 'zmdi zmdi-plus', 1, 1489820150404, 451);
INSERT INTO `upms_permission` VALUES ('452', '6', '350', '编辑委派记录', '3', 'eam:ticketAppointedRecord:update', '/manage/ticket/{ticketId}/appoint/update', 'zmdi zmdi-edit', 1, 1489820178269, 452);
INSERT INTO `upms_permission` VALUES ('453', '6', '350', '删除委派记录', '3', 'eam:ticketAppointedRecord:delete', '/manage/ticket/{ticketId}/appoint/delete', 'zmdi zmdi-close', 1, 1489820207607, 453);
INSERT INTO `upms_permission` VALUES ('454', '6', '350', '委派记录', '3', 'eam:ticketAppointedRecord:read', '/manage/ticket/{ticketId}/appoint/index', NULL, 1, 1, 454);
INSERT INTO `upms_permission` VALUES ('461', '6', '350', '新增工单评价', '3', 'eam:ticketAssessment:create', '/manage/ticket/{ticketId}/assessment/create', 'zmdi zmdi-plus', 1, 1489820150404, 461);
INSERT INTO `upms_permission` VALUES ('462', '6', '350', '编辑工单评价', '3', 'eam:ticketAssessment:update', '/manage/ticket/{ticketId}/assessment/update', 'zmdi zmdi-edit', 1, 1489820178269, 462);
INSERT INTO `upms_permission` VALUES ('463', '6', '350', '删除工单评价', '3', 'eam:ticketAssessment:delete', '/manage/ticket/{ticketId}/assessment/delete', 'zmdi zmdi-close', 1, 1489820207607, 463);
INSERT INTO `upms_permission` VALUES ('464', '6', '350', '工单评价', '3', 'eam:ticketAssessment:read', '/manage/ticket/{ticketId}/assessment/index', '', 1, 1, 464);

commit;
