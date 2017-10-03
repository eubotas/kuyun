use kuyun;

SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

drop table if exists upms_company;
CREATE TABLE `upms_company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `www` varchar(20) DEFAULT NULL,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
  PRIMARY KEY (`company_id`),
  KEY `company_company_410d0aac` (`parent_id`),
  CONSTRAINT `parent_id_refs_id_d95e7d2a` FOREIGN KEY (`parent_id`) REFERENCES `upms_company` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

drop table if exists upms_user_company;
CREATE TABLE `upms_user_company` (
  `user_company_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `company_id` int(10) unsigned NOT NULL COMMENT '公司编号',
  PRIMARY KEY (`user_company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='用户公司关联表';

DROP TABLE IF EXISTS `upms_organization_role`;
CREATE TABLE `upms_organization_role` (
  `organization_role_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `organization_id` int(10) unsigned NOT NULL COMMENT '组织编号',
  `role_id` int(10) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`organization_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='组织角色关联表';

DROP TABLE IF EXISTS `eam_equipment_company`;
create table eam_equipment_company
(
   equipment_company_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
   equipment_id         varchar(32),
   company_id           int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (equipment_company_id)
);

ALTER TABLE eam_equipment ADD modbus_rtu_period  int;

ALTER TABLE upms_organization ADD company_id  int;

ALTER TABLE eam_equipment change organization_id company_id int;
ALTER TABLE eam_equipment_model change organization_id company_id int;
ALTER TABLE eam_equipment_model_properties change organization_id company_id int;
ALTER TABLE eam_sensor change organization_id company_id int;
ALTER TABLE eam_sensor_data change organization_id company_id int;
ALTER TABLE eam_inventory change organization_id company_id int;
ALTER TABLE eam_warehouse change organization_id company_id int;
ALTER TABLE eam_location change organization_id company_id int;
ALTER TABLE eam_maintenance change organization_id company_id int;
ALTER TABLE eam_parts change organization_id company_id int;
ALTER TABLE eam_parts_category change organization_id company_id int;
ALTER TABLE eam_alarm change organization_id company_id int;
ALTER TABLE eam_alarm_target_user change organization_id company_id int;
ALTER TABLE eam_alarm_record change organization_id company_id int;
ALTER TABLE eam_ticket_type change organization_id company_id int;
ALTER TABLE eam_ticket change organization_id company_id int;
ALTER TABLE eam_ticket_record change organization_id company_id int;

ALTER TABLE mkt_sms_setting change organization_id company_id int;
ALTER TABLE mkt_sms_template change organization_id company_id int;
ALTER TABLE mkt_sms change organization_id company_id int;
ALTER TABLE mkt_sms_user change organization_id company_id int;

ALTER TABLE fd_files change organization_id company_id int;
ALTER TABLE fd_oss_files change organization_id company_id int;


insert eam_equipment_company(equipment_id, company_id)
select equipment_id, company_id from eam_equipment;

ALTER TABLE eam_equipment DROP COLUMN company_id;

insert upms_company (company_id, name)
SELECT organization_id, name FROM upms_organization;

insert upms_user_company(user_id, company_id)
select user_id, organization_id from upms_user_organization;


INSERT INTO `upms_permission` VALUES ('90', '1', '1', '公司管理', '2', 'upms:company:read', '/manage/company/index', '', '1', '2', '2');
INSERT INTO `upms_permission` VALUES ('91', '1', '90', '新增公司', '3', 'upms:company:create', '/manage/company/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('92', '1', '90', '编辑公司', '3', 'upms:company:update', '/manage/company/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('93', '1', '90', '删除公司', '3', 'upms:company:delete', '/manage/company/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('219', '6', '0', ' 客户管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '219', '219');
INSERT INTO `upms_permission` VALUES ('270', '6', '219', '客户管理', '2', 'eam:company:read',   '/manage/company/index', null, '1', '270', '270');
INSERT INTO `upms_permission` VALUES ('271', '6', '270', '新增客户', '3', 'eam:company:create', '/manage/company/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('272', '6', '270', '编辑客户', '3', 'eam:company:update', '/manage/company/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('273', '6', '270', '删除客户', '3', 'eam:company:delete', '/manage/company/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');


