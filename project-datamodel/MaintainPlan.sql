
DROP TABLE IF EXISTS eam_maintain_plan;
CREATE TABLE eam_maintain_plan (
   plan_id int(11) NOT NULL AUTO_INCREMENT,
   equipment_category_id int(11) NOT NULL,
   equipment_id varchar(32) NOT NULL,
   work_content varchar(2000) not NULL COMMENT '维保内容',
   org_id int(11) DEFAULT NULL COMMENT '负责部门',
   next_maintain_date datetime COMMENT '由job产生ticket时修改',
   maintain_frequency_unit varchar(32) NOT NULL COMMENT '年/月/天',
   maintain_frequency_quantity int NOT NULL COMMENT '1年/3月/10天',
   maintain_type varchar(32) NULL COMMENT '年检/常规',
   remind_time          int COMMENT '到维修期提前提醒时间(天)',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id           int,
 PRIMARY KEY (plan_id)
)ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eam_maintain_ticket;
CREATE TABLE eam_maintain_ticket (
  id int(11) NOT NULL AUTO_INCREMENT,
  plan_id int(11) NOT NULL,
  ticket_id int(11) NOT NULL,
  create_user_id       int,
  create_time          datetime,
  update_user_id       int,
  update_time          datetime,
  delete_flag          boolean,
  company_id      int,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 点击read后不弹出
DROP TABLE IF EXISTS eam_alert_message;
CREATE TABLE eam_alert_message (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id             int(11),
  message_title       varchar(200) NOT NULL,
  content             varchar(2000) not NULL COMMENT '消息内容',
  read_flag           boolean,
  read_time           datetime,
  alert_start_date    datetime COMMENT '消息发送开始日期',
  alert_end_date      datetime COMMENT '消息发送结束日期, 超过结束日期后不提示',
  create_user_id      int,
  create_time         datetime,
  update_user_id      int,
  update_time         datetime,
  delete_flag         boolean,
  company_id          int,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS eam_code_value;
CREATE TABLE eam_code_value (
  id int(11) NOT NULL AUTO_INCREMENT,
  category    varchar(30) NOT NULL,
  code_value  varchar(30) NOT NULL,
  code_name   varchar(100) NOT NULL,
  description varchar(2000) DEFAULT NULL,
  create_user_id       int,
  create_time          datetime,
  update_user_id       int,
  update_time          datetime,
  delete_flag          boolean,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `upms_permission` VALUES ('600', '6', '0', '维修计划', '1', null, null, 'zmdi zmdi-collection-text', 1, 1, 600);
INSERT INTO `upms_permission` VALUES ('610', '6', '600', '维修计划', '2', 'eam:maintainPlan:read', '/manage/maintainPlan/index', NULL, 1, 1, 610);
INSERT INTO `upms_permission` VALUES ('611', '6', '610', '维修计划', '3', 'eam:maintainPlan:create', '/manage/maintainPlan/create', NULL, 1, 1, 611);
INSERT INTO `upms_permission` VALUES ('612', '6', '610', '维修计划', '3', 'eam:maintainPlan:update', '/manage/maintainPlan/update', NULL, 1, 1, 612);
INSERT INTO `upms_permission` VALUES ('613', '6', '610', '维修计划', '3', 'eam:maintainPlan:delete', '/manage/maintainPlan/delete', NULL, 1, 1, 613);

INSERT INTO `upms_permission` VALUES (700,6,0,'基础数据设置',1,NULL,NULL,'zmdi zmdi-collection-text',1,100,700);
INSERT INTO `upms_permission` VALUES ('710', '6', '700', '数据字典', '2', 'eam:codeValue:read', '/manage/codeValue/index', NULL, 1, 1, 710);
INSERT INTO `upms_permission` VALUES ('711', '6', '710', '新增数据字典', '3', 'eam:codeValue:create', '/manage/codeValue/create', 'zmdi zmdi-plus', 1, 1, 711);
INSERT INTO `upms_permission` VALUES ('712', '6', '710', '编辑数据字典', '3', 'eam:codeValue:update', '/manage/codeValue/update', 'zmdi zmdi-edit', 1, 1, 712);
INSERT INTO `upms_permission` VALUES ('713', '6', '710', '删除数据字典', '3', 'eam:codeValue:delete', '/manage/codeValue/delete', 'zmdi zmdi-close', 1, 1, 713);

INSERT INTO `eam_code_value` (`id`,`category`,`code_value`,`code_name`,`description`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`delete_flag`) VALUES (1,'MAINTAIN_PLAN_UNIT','YEAR','年','',1,'2018-01-30 09:53:16',1,'2018-01-30 09:53:16',0);
INSERT INTO `eam_code_value` (`id`,`category`,`code_value`,`code_name`,`description`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`delete_flag`) VALUES (2,'MAINTAIN_PLAN_UNIT','MONTH','月','',1,'2018-01-30 09:53:39',1,'2018-01-30 09:53:39',0);
INSERT INTO `eam_code_value` (`id`,`category`,`code_value`,`code_name`,`description`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`delete_flag`) VALUES (3,'MAINTAIN_PLAN_UNIT','WEEK','星期','',1,'2018-01-30 09:54:03',1,'2018-01-30 09:54:03',0);
INSERT INTO `eam_code_value` (`id`,`category`,`code_value`,`code_name`,`description`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`delete_flag`) VALUES (4,'MAINTAIN_PLAN_UNIT','DAY','天','',1,'2018-01-30 09:54:18',1,'2018-01-30 09:54:18',0);
