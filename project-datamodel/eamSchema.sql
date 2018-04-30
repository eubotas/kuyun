/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017-06-12 11:16:35 AM                       */
/*==============================================================*/

SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;


drop table if exists eam_equipment_category;
create table eam_equipment_category
(
   equipment_category_id   int not null auto_increment,
   name                 varchar(30),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id           int,
   primary key (equipment_category_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*==============================================================*/
/* Table: eam_equipment                                         */
/*==============================================================*/
drop table if exists eam_equipment;
create table eam_equipment
(
   equipment_id         varchar(32),
   equipment_model_id   int,
   equipment_category_id  int,
   name                 varchar(30),
   number               varchar(30),
   serial_number        varchar(50),
   image_path           varchar(100),
   longitude            decimal(10,5),
   latitude             decimal(10,5),
   province             varchar(10),
   city                 varchar(10),
   user                 varchar(30),
   collect_status       varchar(10) comment '采集状态',
   salve_id             int  comment 'Modbus RTU 从站地址',  
   heart_data           varchar(50) comment 'Modbus RTU',
   modbus_rtu_period    int  comment 'Modbus RTU',
   grm_period           int         comment '巨控采集频率单位秒',
   factory_date         datetime,
   commissioning_date   datetime,
   warranty_start_date  datetime,
   warranty_end_date    datetime,
   maintenance_period   varchar(20),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   is_online            boolean,
   primary key (equipment_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '设备';


/*==============================================================*/
/* Table: eam_equipment_model                                   */
/*==============================================================*/
drop table if exists eam_equipment_model;
create table eam_equipment_model
(
   equipment_model_id   int not null auto_increment,
   name                 varchar(30),
   number               varchar(50),
   protocol_id          int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (equipment_model_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '设备模型';


/*==============================================================*/
/* Table: eam_equipment_model_properties                        */
/*==============================================================*/

drop table if exists eam_equipment_model_properties;
create table eam_equipment_model_properties
(
   equipment_model_property_id int not null auto_increment,
   equipment_model_id   int,
   name                 varchar(30) comment '名称',
   label                varchar(50),
   unit                 varchar(20) comment '单位',
   address              varchar(30),
   data_type            varchar(30) comment '数据类型(analog, digital)',
   display_type         varchar(10) comment '显示类型(LED, pie, guage)',
   refresh_period       varchar(30),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (equipment_model_property_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '设备模型参数';


/*==============================================================*/
/* Table: eam_sensor                                            */
/*==============================================================*/
drop table if exists eam_sensor;
create table eam_sensor
(
   sensor_id            int not null auto_increment,
   equipment_model_property_id int,
   function_code        int  comment 'Modbus RTU 功能码',
   address              int  comment 'Modbus RTU 起始地址',
   data_format          varchar(15) comment 'Modbus RTU 数据格式',
   bit_order            varchar(10) comment 'Modbus RTU 字节顺序',
   period               int  comment 'Modbus RTU',
   quantity             int  comment 'Modbus RTU 地址个数',
   write_number         int  comment 'Modbus RTU',
   grm_action           varchar(5) comment '巨控 读写指令',
   grm_variable         varchar(20) comment '巨控 变量名',
   grm_variable_value   varchar(20) comment '巨控 写变量值',
   osh                  decimal(10,2) comment '换算结果的高限',
   osl                  decimal(10,2) comment '换算结果的低限',
   ish                  decimal(10,2) comment '换算对象的高限',
   isl                  decimal(10,2) comment '换算对象的低限',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (sensor_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '设备传感器';

drop table if exists eam_sensor_write_data;
create table eam_sensor_write_data
(
   id       int not null auto_increment,
   equipment_id         varchar(32),
   sensor_id            int,
   equipment_model_id   int,
   equipment_model_property_id int,
   write_value         varchar(50),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

drop table if exists eam_sensor_write_data_history;
create table eam_sensor_write_data_history
(
   id       int not null auto_increment,
   equipment_id         varchar(32),
   sensor_id            int,
   equipment_model_id   int,
   equipment_model_property_id int,
   write_value         varchar(50),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*==============================================================*/
/* Table: eam_sensor_data                                       */
/*==============================================================*/
drop table if exists eam_sensor_data;
create table eam_sensor_data
(
   sensor_data_id       int not null auto_increment,
   equipment_id         varchar(32),
   sensor_id            int,
   string_value         varchar(50),
   number_value         decimal(10,2),
   boolean_value        boolean,
   longitude_value      decimal(10,5),
   latitude_value       decimal(10,5),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (sensor_data_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '设备传感器数据';

create index eam_sensor_data_create_time on eam_sensor_data (create_time desc);


DROP TABLE IF EXISTS eam_sensor_data_history;
create table eam_sensor_data_history
(
   sensor_data_id       int not null auto_increment,
   equipment_id         varchar(32),
   sensor_id            int,
   string_value         varchar(50),
   number_value         decimal(10,2),
   boolean_value        boolean,
   longitude_value      decimal(10,5),
   latitude_value       decimal(10,5),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (sensor_data_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

alter table eam_sensor_data_history add index idx_eam_sensor_data_history (equipment_id,sensor_id,create_time);

/*==============================================================*/
/* Table: eam_inventory                                         */
/*==============================================================*/
drop table if exists eam_inventory;
create table eam_inventory
(
   inventory_id         int not null auto_increment,
   warehouse_id         int,
   location_id          int,
   part_id              int,
   quantity             decimal(10,2),
   in_task_date         datetime,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (inventory_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '库存明细表';


/*==============================================================*/
/* Table: eam_warehouse                                         */
/*==============================================================*/
drop table if exists eam_warehouse;
create table eam_warehouse
(
   warehouse_id         int not null auto_increment,
   name                 varchar(30),
   comments             varchar(100),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (warehouse_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '仓库信息表';

/*==============================================================*/
/* Table: eam_location                                          */
/*==============================================================*/
drop table if exists eam_location;
create table eam_location
(
   location_id          int not null auto_increment,
   warehouse_id         int,
   number               varchar(20),
   comments             varchar(100),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (location_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '仓位信息表';


/*==============================================================*/
/* Table: eam_parts                                             */
/*==============================================================*/
drop table if exists eam_parts;
create table eam_parts
(
   part_id              int not null auto_increment,
   category_id          int,
   name                 varchar(50),
   spec                 varchar(50),
   model                varchar(50),
   unit                 varchar(20),
   brand                varchar(50),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (part_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '配件';


/*==============================================================*/
/* Table: eam_parts_category                                    */
/*==============================================================*/
drop table if exists eam_parts_category;
create table eam_parts_category
(
   category_id          int not null auto_increment,
   name                 varchar(30),
   organization_id      int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (category_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '配件类别';

/*==============================================================*/
/* Table: eam_protocol                                          */
/*==============================================================*/
drop table if exists eam_protocol;
create table eam_protocol
(
   protocol_id          int not null auto_increment,
   name                 varchar(20),
   IP                   varchar(25),
   port                 int,
   primary key (protocol_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '接入协议';

/*==============================================================*/
/* Table: eam_alarm                                          */
/*==============================================================*/
drop table if exists eam_alarm;
CREATE TABLE eam_alarm (
   alarm_id int(11) NOT NULL AUTO_INCREMENT,
   alarm_type varchar(20) DEFAULT NULL,
   upper_Bound decimal(10,2) DEFAULT NULL,
   lower_Bound decimal(10,2) DEFAULT NULL,
   duration decimal(10,2) DEFAULT NULL,
   alarm_target varchar(10) DEFAULT NULL,
   equipment_model_property_id int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   is_create_ticket     boolean,
   company_id      int,
  PRIMARY KEY (alarm_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists eam_alarm_target_user;
CREATE TABLE eam_alarm_target_user (
   id int(11) NOT NULL AUTO_INCREMENT,
   alarm_id            int(11),
   user_id             int(11),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists eam_alarm_record;
CREATE TABLE eam_alarm_record (
   id int(11) NOT NULL AUTO_INCREMENT,
   alarm_id            int(11),
   equipment_id         varchar(32),
   equipment_model_property_id int,
   alarm_value          varchar(50),
   alarm_status         varchar(10),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists eam_alarm_record_history;
CREATE TABLE eam_alarm_record_history (
   id int(11) NOT NULL AUTO_INCREMENT,
   alarm_id            int(11),
   equipment_id         varchar(32),
   equipment_model_property_id int,
   alarm_value          varchar(50),
   alarm_status         varchar(10),
   alarm_clear_value    varchar(50),
   create_user_id       int,
   create_time          datetime comment '报警发生时间',
   update_user_id       int,
   update_time          datetime comment '报警清除时间',
   delete_flag          boolean,
   company_id      int,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS eam_equipment_company;
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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eam_dtu;
create table eam_dtu
(
   dtu_id         varchar(32),
   name           varchar(50),
   heart_data           varchar(50) comment '心跳包',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (dtu_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eam_dtu_equipment;
create table eam_dtu_equipment
(
   id                   int not null auto_increment,
   dtu_id               varchar(32),
   equipment_id         varchar(32),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eam_grm;
create table eam_grm
(
   grm_id               varchar(32),
   grm                  varchar(50) comment '巨控设备ID',
   grm_password         varchar(50) comment '巨控设备密码',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (grm_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eam_grm_equipment;
create table eam_grm_equipment
(
   id                   int not null auto_increment,
   grm_id               varchar(32),
   equipment_id         varchar(32),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


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

#http://benjaminwhx.com/2015/11/24/Mysql%E4%B8%AD%E7%9A%84%E9%80%92%E5%BD%92%E5%B1%82%E6%AC%A1%E6%9F%A5%E8%AF%A2%EF%BC%88%E7%88%B6%E5%AD%90%E6%9F%A5%E8%AF%A2%EF%BC%89/

#根据传入id查询所有父节点的id
drop FUNCTION if exists getParentList;

delimiter //

CREATE FUNCTION getParentList(rootId INT)
RETURNS varchar(1000) 

BEGIN
  DECLARE sTemp VARCHAR(1000);
  DECLARE sTempPar VARCHAR(1000); 
  SET sTemp = ''; 
  SET sTempPar =rootId; 
  
  #循环递归
  WHILE sTempPar is not null DO 
    #判断是否是第一个，不加的话第一个会为空
    IF sTemp != '' THEN
      SET sTemp = concat(sTemp,',',sTempPar);
    ELSE
      SET sTemp = sTempPar;
    END IF;

    SET sTemp = concat(sTemp,',',sTempPar); 
    SELECT group_concat(pid) INTO sTempPar FROM upms_organization where pid<>organization_id and FIND_IN_SET(organization_id,sTempPar)>0; 
  END WHILE; 
  
RETURN sTemp; 
END
//

delimiter ;

#根据传入id查询所有子节点的id

drop FUNCTION if exists getChildList;
delimiter //
CREATE FUNCTION getChildList(rootId INT)
RETURNS varchar(1000) 

BEGIN
  DECLARE sTemp VARCHAR(1000);
    DECLARE sTempChd VARCHAR(1000);

    SET sTemp = '$';
    SET sTempChd =cast(rootId as CHAR);

    WHILE sTempChd is not null DO
      SET sTemp = concat(sTemp,',',sTempChd);
        SELECT group_concat(organization_id) INTO sTempChd FROM  upms_organization where FIND_IN_SET(pid,sTempChd)>0;
    END WHILE;
    RETURN sTemp; 
END
//

#resotre default delimiter
delimiter ;


  