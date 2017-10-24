/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017-06-12 11:16:35 AM                       */
/*==============================================================*/
use kuyun;
SET FOREIGN_KEY_CHECKS=0;

drop table if exists eam_equipment;

drop table if exists eam_equipment_model;

drop table if exists eam_equipment_model_properties;

drop table if exists eam_inventory;

drop table if exists eam_location;

drop table if exists eam_maintenance;

drop table if exists eam_parts;

drop table if exists eam_parts_category;

drop table if exists eam_protocol;

drop table if exists eam_sensor;

drop table if exists eam_sensor_data;

drop table if exists eam_warehouse;

drop table if exists eam_alarm;

drop table if exists eam_alarm_record;

drop table if exists eam_alarm_target_user;

drop table if exists eam_ticket_type;

drop table if exists eam_ticket;

drop table if exists eam_ticket_record;

/*==============================================================*/
/* Table: eam_equipment                                         */
/*==============================================================*/
create table eam_equipment
(
   equipment_id         varchar(32),
   equipment_model_id   int,
   --company_id      int,
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
   heart_data           varchar(50) comment 'Modbus RTU',
   modbus_rtu_period    int  comment 'Modbus RTU',
   grm                  varchar(50) comment '巨控设备ID',
   grm_password         varchar(50) comment '巨控设备密码',
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
);

alter table eam_equipment comment '设备';

/*==============================================================*/
/* Table: eam_equipment_model                                   */
/*==============================================================*/
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
);

alter table eam_equipment_model comment '设备模型';

/*==============================================================*/
/* Table: eam_equipment_model_properties                        */
/*==============================================================*/
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
);

alter table eam_equipment_model_properties comment '设备模型参数';


/*==============================================================*/
/* Table: eam_sensor                                            */
/*==============================================================*/
create table eam_sensor
(
   sensor_id            int not null auto_increment,
   equipment_model_property_id int,
   salve_id             int  comment 'Modbus RTU 从站地址',   
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
   --grm_variable_order   int comment '巨控 读写变量顺序',
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
);

alter table eam_sensor comment '设备传感器';

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
);

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
);

/*==============================================================*/
/* Table: eam_sensor_data                                       */
/*==============================================================*/
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
);

alter table eam_sensor_data comment '设备传感器数据';

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
);

/*==============================================================*/
/* Table: eam_inventory                                         */
/*==============================================================*/
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
);

alter table eam_inventory comment ' 库存明细表';

/*==============================================================*/
/* Table: eam_warehouse                                         */
/*==============================================================*/
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
);

alter table eam_warehouse comment '仓库信息表';

/*==============================================================*/
/* Table: eam_location                                          */
/*==============================================================*/
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
);

alter table eam_location comment '仓位信息表';

/*==============================================================*/
/* Table: eam_maintenance                                       */
/*==============================================================*/
create table eam_maintenance
(
   maintenance_id       int not null auto_increment,
   equipment_id         varchar(32),
   part_id              int,
   reason               varchar(200),
   content              varchar(250),
   part_quantity        decimal(10,2),
   maintain_user_id     int,
   maintain_time        datetime,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (maintenance_id)
);

alter table eam_maintenance comment ' 维保';

/*==============================================================*/
/* Table: eam_parts                                             */
/*==============================================================*/
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
);

alter table eam_parts comment '配件';

/*==============================================================*/
/* Table: eam_parts_category                                    */
/*==============================================================*/
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
);

alter table eam_parts_category comment ' 配件类别';

/*==============================================================*/
/* Table: eam_protocol                                          */
/*==============================================================*/
create table eam_protocol
(
   protocol_id          int not null auto_increment,
   name                 varchar(20),
   IP                   varchar(25),
   port                 int,
   primary key (protocol_id)
);

alter table eam_protocol comment ' 接入协议';

/*==============================================================*/
/* Table: eam_alarm                                          */
/*==============================================================*/
CREATE TABLE `eam_alarm` (
  `alarm_id` int(11) NOT NULL AUTO_INCREMENT,
  `alarm_type` varchar(20) DEFAULT NULL,
  `upper_Bound` decimal(10,2) DEFAULT NULL,
  `lower_Bound` decimal(10,2) DEFAULT NULL,
  `duration` decimal(10,2) DEFAULT NULL,
  `alarm_target` varchar(10) DEFAULT NULL,
   equipment_model_property_id int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
  PRIMARY KEY (`alarm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `eam_alarm_target_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alarm_id`            int(11),
  `user_id`             int(11),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `eam_alarm_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alarm_id`            int(11),
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `eam_alarm_record_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alarm_id`            int(11),
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#工单分类
create table eam_ticket_type
(
   id                   int not null auto_increment,
   name                 varchar(30),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (id)
);

#工单
create table eam_ticket
(
   ticket_id            int not null auto_increment,
   ticket_type_id       int,
   description          varchar(200)  COMMENT '描述',
   image_path_1         varchar(20000)  COMMENT '上传图片',
   image_path_2         varchar(100)  COMMENT '上传图片',
   image_path_3         varchar(100)  COMMENT '上传图片',
   image_path_4         varchar(100)  COMMENT '上传图片',
   priority             varchar(10)   COMMENT '优先级（一般，紧急, 非常紧急）',
   executor_id          int           COMMENT '处理人',
   status               varchar(10)   COMMENT '状态（未解决, 处理中, 已解决, 不需处理）',
   end_date             datetime,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (ticket_id)
);

#工单记录
create table eam_ticket_record
(
   id                   int not null auto_increment,
   ticket_id            int,
   step                 varchar(20) COMMENT '处理步骤',
   comments             varchar(200) COMMENT '处理内容',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (id)
);


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



alter table eam_equipment_model add constraint FK_Reference_10 foreign key (protocol_id)
      references eam_protocol (protocol_id) on delete restrict on update restrict;

alter table eam_equipment add constraint FK_Reference_42 foreign key (equipment_model_id)
      references eam_equipment_model (equipment_model_id) on delete restrict on update restrict;

alter table eam_equipment_model_properties add constraint FK_Reference_41 foreign key (equipment_model_id)
      references eam_equipment_model (equipment_model_id) on delete restrict on update restrict;

alter table eam_inventory add constraint FK_Reference_46 foreign key (part_id)
      references eam_parts (part_id) on delete restrict on update restrict;

alter table eam_inventory add constraint FK_Reference_48 foreign key (warehouse_id)
      references eam_warehouse (warehouse_id) on delete restrict on update restrict;

alter table eam_inventory add constraint FK_Reference_49 foreign key (location_id)
      references eam_location (location_id) on delete restrict on update restrict;

alter table eam_location add constraint FK_Reference_47 foreign key (warehouse_id)
      references eam_warehouse (warehouse_id) on delete restrict on update restrict;

alter table eam_maintenance add constraint FK_Reference_43 foreign key (equipment_id)
      references eam_equipment (equipment_id) on delete restrict on update restrict;

alter table eam_maintenance add constraint FK_Reference_44 foreign key (part_id)
      references eam_parts (part_id) on delete restrict on update restrict;

alter table eam_parts add constraint FK_Reference_45 foreign key (category_id)
      references eam_parts_category (category_id) on delete restrict on update restrict;

alter table eam_sensor add constraint FK_Reference_11 foreign key (equipment_model_property_id)
      references eam_equipment_model_properties (equipment_model_property_id) on delete restrict on update restrict;

alter table eam_sensor_data add constraint FK_Reference_13 foreign key (sensor_id)
      references eam_sensor (sensor_id) on delete restrict on update restrict;

alter table eam_sensor_data add constraint FK_Reference_14 foreign key (equipment_id)
      references eam_equipment (equipment_id) on delete restrict on update restrict;




#http://benjaminwhx.com/2015/11/24/Mysql%E4%B8%AD%E7%9A%84%E9%80%92%E5%BD%92%E5%B1%82%E6%AC%A1%E6%9F%A5%E8%AF%A2%EF%BC%88%E7%88%B6%E5%AD%90%E6%9F%A5%E8%AF%A2%EF%BC%89/

#根据传入id查询所有父节点的id
drop FUNCTION if exists getParentList;

delimiter //

CREATE FUNCTION `getParentList`(rootId INT)
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
CREATE FUNCTION `getChildList`(rootId INT)
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

##ALTER TABLE eam_equipment ADD heart_data varchar(50);

##ALTER TABLE eam_sensor ADD quantity int;

#ALTER TABLE eam_sensor ADD   grm_action           varchar(5) comment '巨控';
#ALTER TABLE eam_sensor ADD   grm_variable         varchar(20) comment '巨控';
#ALTER TABLE eam_sensor ADD   grm_variable_value   varchar(20) comment '巨控';
#ALTER TABLE eam_equipment ADD grm_period int comment '巨控采集频率单位秒';

  