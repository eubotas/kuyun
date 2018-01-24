
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
   maintain_type varchar(32) NOT NULL COMMENT '年检/常规',
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
  message_method      varchar(30) DEFAULT 'WEB' NOT NULL,
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
  code        varchar(30) NOT NULL,
  code_name   varchar(100) NOT NULL,
  description varchar(2000) DEFAULT NULL,
  create_user_id       int,
  create_time          datetime,
  update_user_id       int,
  update_time          datetime,
  delete_flag          boolean,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

