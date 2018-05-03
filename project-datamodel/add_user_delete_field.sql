ALTER TABLE upms_role ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_log ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_organization ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_permission ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_system ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_user ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_organization_role ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_role_permission ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_user_company ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_user_organization ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_user_permission ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_user_role ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;

ALTER TABLE upms_role ADD COLUMN company_id  int(11) NULL DEFAULT NULL ;
ALTER TABLE eam_alarm ADD COLUMN is_create_ticket     boolean;

-- ----------------------------
-- Table structure for eam_ticket
-- ----------------------------
DROP TABLE IF EXISTS eam_ticket;
CREATE TABLE eam_ticket (
  ticket_id int(11) NOT NULL AUTO_INCREMENT,
  ticket_type_id int(11) DEFAULT NULL,
  ticket_number varchar(32),
  equipment_category_id int(11) DEFAULT NULL,
  equipment_id varchar(32) DEFAULT NULL,
  description varchar(200) DEFAULT NULL COMMENT '描述',
  voice_path varchar(2000) DEFAULT NULL COMMENT '语音',
  image_path varchar(2000) DEFAULT NULL COMMENT '上传图片',
  priority varchar(10) DEFAULT NULL COMMENT '优先级（一般，紧急, 非常紧急）',
  executor_id int(11) DEFAULT NULL COMMENT '处理人',
  status varchar(10) DEFAULT NULL COMMENT '状态（待派工, 待维修, 维修中, 待评价, 评价完成）',
  end_date datetime DEFAULT NULL,
  create_user_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_user_id int(11) DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  delete_flag tinyint(1) DEFAULT NULL,
  company_id int(11) DEFAULT NULL,
  PRIMARY KEY (ticket_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for eam_ticket_appointed_record
-- ----------------------------
DROP TABLE IF EXISTS eam_ticket_appointed_record;
CREATE TABLE eam_ticket_appointed_record (
  id int(11) NOT NULL AUTO_INCREMENT,
  ticket_id int(11) DEFAULT NULL,
  order_taker_id int(11) DEFAULT NULL COMMENT '接单人',
  appoint_commont varchar(100) DEFAULT NULL COMMENT '委派备注，可为空',
  action         varchar(10) DEFAULT NULL COMMENT '接单/拒单',
  reject_commont varchar(100) DEFAULT NULL COMMENT '拒单原因，可为空',
  create_user_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  delete_flag tinyint(1) DEFAULT NULL,
  company_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for eam_ticket_assessment
-- ----------------------------
DROP TABLE IF EXISTS eam_ticket_assessment;
CREATE TABLE eam_ticket_assessment (
  id int(11) NOT NULL AUTO_INCREMENT,
  ticket_id int(11) DEFAULT NULL,
  assessment_user_id int(11) DEFAULT NULL COMMENT '评价人ID',
  assessment_level int(11) DEFAULT NULL COMMENT '评价星级',
  description varchar(2000) DEFAULT NULL COMMENT '评价描述',
  create_user_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_user_id int(11) DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  delete_flag tinyint(1) DEFAULT NULL,
  company_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for eam_ticket_assessment_tag
-- ----------------------------
DROP TABLE IF EXISTS eam_ticket_assessment_tag;
CREATE TABLE eam_ticket_assessment_tag (
  id int(11) NOT NULL AUTO_INCREMENT,
  ticket_id int(11) DEFAULT NULL,
  assessment_id int(11) DEFAULT NULL,
  tag_id int(11) DEFAULT NULL COMMENT '评价标签ID，from eam_tag',
  create_user_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_user_id int(11) DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  delete_flag tinyint(1) DEFAULT NULL,
  company_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for eam_ticket_record
-- ----------------------------
DROP TABLE IF EXISTS eam_ticket_record;
CREATE TABLE eam_ticket_record (
  id int(11) NOT NULL AUTO_INCREMENT,
  ticket_id int(11) DEFAULT NULL,
  step varchar(20) DEFAULT NULL COMMENT '处理步骤',
  comments varchar(200) DEFAULT NULL COMMENT '处理内容',
  create_user_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_user_id int(11) DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  delete_flag tinyint(1) DEFAULT NULL,
  company_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for eam_ticket_tag
-- ----------------------------
DROP TABLE IF EXISTS eam_ticket_tag;
CREATE TABLE eam_ticket_tag (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(30) DEFAULT NULL COMMENT '标签名',
  create_user_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_user_id int(11) DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  delete_flag tinyint(1) DEFAULT NULL,
  company_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for eam_ticket_type
-- ----------------------------
DROP TABLE IF EXISTS eam_ticket_type;
CREATE TABLE eam_ticket_type (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(30) DEFAULT NULL,
  create_user_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_user_id int(11) DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  delete_flag tinyint(1) DEFAULT NULL,
  company_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS eam_maintain_plan;
CREATE TABLE eam_maintain_plan (
  plan_id int(11) NOT NULL AUTO_INCREMENT,
  equipment_category_id int(11) DEFAULT NULL,
  equipment_id varchar(32) NOT NULL,
  work_content varchar(2000) not NULL COMMENT '维保内容',
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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eam_maintain_user;
CREATE TABLE eam_maintain_user (
  id int(11) NOT NULL AUTO_INCREMENT,
  plan_id            int(11),
  user_id             int(11),
  create_user_id       int,
  create_time          datetime,
  update_user_id       int,
  update_time          datetime,
  delete_flag          boolean,
  company_id      int,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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



