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
drop table if exists eam_alarm_type_value;
drop table if exists eam_alarm_target_person;




/*==============================================================*/
/* Table: eam_equipment                                         */
/*==============================================================*/
create table eam_equipment
(
   equipment_id         varchar(32),
   equipment_model_id   int,
   organization_id      int,
   protocol_id          int,
   name                 varchar(30),
   number               varchar(30),
   serial_number        varchar(50),
   image_path           varchar(100),
   longitude            decimal(10,5),
   latitude             decimal(10,5),
   user                 varchar(30),
   heart_data           varchar(50),
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
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   organization_id      int,
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
   name                 varchar(30),
   lable                varchar(50),
   unit                 varchar(20),
   address              varchar(30),
   data_type            varchar(30),
   alarm_type           varchar(30),
   refresh_period       varchar(30),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   organization_id      int,
   primary key (equipment_model_property_id)
);

alter table eam_equipment_model_properties comment '设备模型参数';

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
   organization_id      int,
   primary key (inventory_id)
);

alter table eam_inventory comment ' 库存明细表';

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
   organization_id      int,
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
   organization_id      int,
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
   organization_id      int,
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
/* Table: eam_sensor                                            */
/*==============================================================*/
create table eam_sensor
(
   sensor_id            int not null auto_increment,
   equipment_id         varchar(32),
   equipment_model_property_id int,
   salve_id             int,
   function_code        int,
   address              int,
   data_format          varchar(10),
   period               int,
   quantity             int,
   write_number         int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   organization_id      int,
   primary key (sensor_id)
);

alter table eam_sensor comment '设备传感器';

/*==============================================================*/
/* Table: eam_sensor_data                                       */
/*==============================================================*/
create table eam_sensor_data
(
   sensor_data_id       int not null auto_increment,
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
   organization_id      int,
   primary key (sensor_data_id)
);

alter table eam_sensor_data comment '设备传感器数据';

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
   organization_id      int,
   primary key (warehouse_id)
);

alter table eam_warehouse comment '仓库信息表';

/*==============================================================*/
/* Table: eam_alarm                                          */
/*==============================================================*/
CREATE TABLE `eam_alarm` (
  `alarm_id` int(11) NOT NULL AUTO_INCREMENT,
  `equipment_id` varchar(32) DEFAULT NULL,
  `sensor_id` int(11) DEFAULT NULL,
  `alarm_type` varchar(20) DEFAULT NULL,
  `alarm_target` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`alarm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `eam_alarm_type_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alarm_id` int(11) DEFAULT NULL,
  `upper_Bound` decimal(10,2) DEFAULT NULL,
  `lower_Bound` decimal(10,2) DEFAULT NULL,
  `duration` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `eam_alarm_target_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alarm_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


alter table eam_equipment add constraint FK_Reference_10 foreign key (protocol_id)
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

alter table eam_sensor add constraint FK_Reference_12 foreign key (equipment_id)
      references eam_equipment (equipment_id) on delete restrict on update restrict;

alter table eam_sensor_data add constraint FK_Reference_13 foreign key (sensor_id)
      references eam_sensor (sensor_id) on delete restrict on update restrict;




--http://benjaminwhx.com/2015/11/24/Mysql%E4%B8%AD%E7%9A%84%E9%80%92%E5%BD%92%E5%B1%82%E6%AC%A1%E6%9F%A5%E8%AF%A2%EF%BC%88%E7%88%B6%E5%AD%90%E6%9F%A5%E8%AF%A2%EF%BC%89/

--根据传入id查询所有父节点的id
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

--根据传入id查询所有子节点的id
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


##ALTER TABLE eam_equipment ADD heart_data varchar(50);

##ALTER TABLE eam_sensor ADD quantity int;