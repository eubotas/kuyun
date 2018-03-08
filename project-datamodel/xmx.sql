use xmx;
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
);

#数据点
drop table if exists eam_data_element;
create table eam_data_element
(  
   id                   int not null auto_increment,
   name                 varchar(20) comment '变量名',
   lable_name           varchar(20) comment '显示名称',
   unit                 varchar(20) comment '单位',
   data_type            varchar(20) comment '数据类型(analog, digital)',
   equipment_category_id int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
);

#数据点分组
drop table if exists eam_data_element_group;
create table eam_data_element_group
(  
   id                   int not null auto_increment,
   name                 varchar(20) comment '分组名称',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
);

drop table if exists eam_alarm_model;
CREATE TABLE eam_alarm_model (
   alarm_model_id       int(11) NOT NULL AUTO_INCREMENT,
   name                 varchar(30) comment '报警名称',
   eam_data_element_id  int comment '数据点ID',
   alarm_type           varchar(20) DEFAULT NULL comment '数值高于X',
   upper_Bound          decimal(10,2) DEFAULT NULL,
   lower_Bound          decimal(10,2) DEFAULT NULL,
   duration             decimal(10,2) DEFAULT NULL,
   is_create_ticket     boolean,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
  PRIMARY KEY (alarm_model_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#产线
drop table if exists eam_product_line;
create table eam_product_line
(
   product_line_id      varchar(32) not null,
   name                 varchar(30),
   image_path           varchar(100),
   longitude            decimal(10,5),
   latitude             decimal(10,5),
   province             varchar(10),
   city                 varchar(10),
   grm                  varchar(50) comment '巨控设备ID',
   grm_password         varchar(50) comment '巨控设备密码',
   grm_period           int         comment '巨控采集频率单位秒',
   collect_status       varchar(10) comment '采集状态',
   is_online            boolean,
   morning_shift_start_time  varchar(10),
   morning_shift_end_time    varchar(10),
   middle_shift_start_time   varchar(10),
   middle_shift_end_time     varchar(10),
   night_shift_start_time    varchar(10),
   night_shift_end_time      varchar(10),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (product_line_id)
);

#产线数据点
drop table if exists eam_product_line_data_element;
create table eam_product_line_data_element
(
   id                   int not null auto_increment,
   product_line_id      varchar(32) not null,
   eam_data_element_id  int  comment '数据点ID',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
);

DROP TABLE IF EXISTS eam_product_line_company;
create table eam_product_line_company
(
   id                   int not null auto_increment,
   product_line_id      varchar(32),
   company_id           int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
);

drop table if exists eam_equipment;
create table eam_equipment
(
   equipment_id         varchar(32),
   #equipment_model_id   int,
   equipment_category_id  int,
   product_line_id      varchar(32),
   name                 varchar(30),
   number               varchar(30) comment '设备型号',
   serial_number        varchar(50) comment '出厂编号',
   image_path           varchar(100),
   output               varchar(20) comment '产量',
   dimension            varchar(20) comment '外形尺寸',
   weight               varchar(20) comment '设备重量',
   power                varchar(20) comment '使用电源',
   capacity             varchar(20) comment '装机容量',
   user                 varchar(30),
   collect_status       varchar(10) comment '采集状态',
   grm                  varchar(50) comment '巨控设备ID',
   grm_password         varchar(50) comment '巨控设备密码',
   grm_period           int         comment '巨控采集频率单位秒',
   factory_date         datetime,
   commissioning_date   datetime,
   warranty_start_date  datetime,
   warranty_end_date    datetime,
   maintenance_period   varchar(20),
   left_position        decimal(10,2),
   top_position         decimal(10,2),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   is_online            boolean,
   primary key (equipment_id)
);

DROP TABLE IF EXISTS eam_equipment_data_group;
create table eam_equipment_data_group
(
   id                   int not null auto_increment,
   equipment_id         varchar(32),
   data_group_id        int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
);

DROP TABLE IF EXISTS eam_equipment_data_group_elemets;
create table eam_equipment_data_group_elemets
(
   id                      int not null auto_increment,
   equipment_data_group_id int comment '设备数据分组ID',
   equipment_id            varchar(32),
   data_group_id           int,
   data_element_id         int comment '数据点ID',
   create_user_id          int,
   create_time             datetime,
   update_user_id          int,
   update_time             datetime,
   delete_flag             boolean,
   primary key (id)
);

DROP TABLE IF EXISTS eam_alarm;
CREATE TABLE eam_alarm (
   alarm_id int(11) NOT NULL AUTO_INCREMENT,
   product_line_id      varchar(32),
   equipment_id         varchar(32),
   name                 varchar(30) comment '报警名称',
   eam_data_element_id  int comment '数据点ID',
   alarm_type           varchar(20) DEFAULT NULL,
   upper_Bound          decimal(10,2) DEFAULT NULL,
   lower_Bound          decimal(10,2) DEFAULT NULL,
   duration decimal(10,2) DEFAULT NULL,
   alarm_target varchar(10) DEFAULT NULL,
   is_create_ticket     boolean,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
  PRIMARY KEY (alarm_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eam_alarm_target_user;
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

DROP TABLE IF EXISTS eam_alarm_record;
CREATE TABLE eam_alarm_record (
  id int(11) NOT NULL AUTO_INCREMENT,
  alarm_id            int(11),
  product_line_id      varchar(32),
  equipment_id         varchar(32),
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
  product_line_id      varchar(32),
  equipment_id         varchar(32),
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


drop table if exists eam_grm_variable;
create table eam_grm_variable
(
   id                   int not null auto_increment,
   equipment_id         varchar(32),
   product_line_id      varchar(32),
   data_group_id        int,
   equipment_data_group_id int comment '设备数据分组ID',
   data_element_id      int,
   name                 varchar(30) comment '变量名',
   type                 varchar(5) comment '变量类型 B/I/F，分别代表 开关量/整数/浮点数',
   attribute            varchar(5) comment '读写属性 R/W，分别代表 只读/可读写',
   network_permisstion  varchar(5) comment '网络权限 0/1/2，分别代表 低/中/高',
   group_name           varchar(30) comment '变量组名，返回值为字符串。如果有两级变量组，中间是.分隔',
   description          varchar(50) comment '变量描述，返回值为字符串',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
);

drop table if exists eam_grm_variable_data;
create table eam_grm_variable_data
(
   id                   int not null auto_increment,
   equipment_id         varchar(32),
   product_line_id      varchar(32),
   data_group_id        int,
   equipment_data_group_id int comment '设备数据分组ID',
   data_element_id      int,
   value                varchar(30),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
);

drop table if exists eam_grm_variable_data_history;
create table eam_grm_variable_data_history
(
   id                   int not null auto_increment,
   equipment_id         varchar(32),
   product_line_id      varchar(32),
   data_group_id        int,
   equipment_data_group_id int comment '设备数据分组ID',
   data_element_id      int,
   value                varchar(30),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
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
   company_id           int,
   primary key (category_id)
);

alter table eam_parts_category comment ' 配件类别';

