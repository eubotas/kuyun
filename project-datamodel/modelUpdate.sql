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


