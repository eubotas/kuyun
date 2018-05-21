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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


#数据点
drop table if exists eam_data_element;
create table eam_data_element
(  
   id                   int not null auto_increment,
   name                 varchar(20) comment '变量名',
   lable_name           varchar(20) comment '显示名称',
   unit                 varchar(20) comment '单位',
   data_type            varchar(20) comment '数据类型(analog, digital)',
   equipment_category_id int ,
   is_statistic         boolean comment '是否统计累计量（用电量）',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists eam_equipment;
create table eam_equipment
(
   equipment_id         varchar(32),
   equipment_category_id  int,
   product_line_id      varchar(32),
   name                 varchar(30),
   task_number          varchar(15) comment '任务单号',
   number               varchar(30) comment '设备型号',
   serial_number        varchar(50) comment '出厂编号',
   image_path           varchar(100),
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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 -- ALTER TABLE `eam_equipment`DROP COLUMN `output`;

drop table if exists eam_equipment_property;
create table eam_equipment_property
(
   equipment_property_id   int not null auto_increment,
   equipment_id   varchar(32) not null,
   property_label  varchar(50) not null,
   property_value  varchar(50) not null,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id           int,
   primary key (equipment_property_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
   grm_period           int         comment '巨控采集频率单位秒',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists eam_grm_variable_data;
create table eam_grm_variable_data
(
   id                   int not null auto_increment,
   eam_grm_variable_id  int,
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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists eam_grm_variable_data_history;
create table eam_grm_variable_data_history
(
   id                   int not null auto_increment,
   eam_grm_variable_id  int,
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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER table eam_grm_variable_data_history ADD INDEX index_update_time(update_time);


drop table if exists eam_grm_variable_data_by_day;
create table eam_grm_variable_data_by_day
(
   id                   int not null auto_increment,
   eam_grm_variable_id  int,
   equipment_id         varchar(32),
   product_line_id      varchar(32),
   data_element_id      int,
   value                varchar(30),
   date                 date,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER table eam_grm_variable_data_by_day ADD INDEX index_date(date);

drop table if exists eam_grm_variable_data_by_month;
create table eam_grm_variable_data_by_month
(
   id                   int not null auto_increment,
   eam_grm_variable_id  int,
   equipment_id         varchar(32),
   product_line_id      varchar(32),
   data_element_id      int,
   year                 int,
   month                int,
   value                varchar(30),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER table eam_grm_variable_data_by_month ADD INDEX index_update_time(update_time);

drop table if exists eam_grm_variable_data_by_year;
create table eam_grm_variable_data_by_year
(
   id                   int not null auto_increment,
   eam_grm_variable_id  int,
   equipment_id         varchar(32),
   product_line_id      varchar(32),
   data_element_id      int,
   year                 int,
   value                varchar(30),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
);

ALTER table eam_grm_variable_data_by_year ADD INDEX index_update_time(update_time);
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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table eam_inventory comment ' 库存明细表';

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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table eam_warehouse comment '仓库信息表';

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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table eam_location comment '仓位信息表';

/*==============================================================*/
/* Table: eam_maintenance                                       */
/*==============================================================*/
drop table if exists eam_maintenance;
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
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table eam_maintenance comment ' 维保';

/*==============================================================*/
/* Table: eam_parts                                             */
/*==============================================================*/
drop table if exists eam_parts;
create table eam_parts
(
   part_id              int not null auto_increment,
   product_line_id      varchar(32),
   equipment_id         varchar(32),
   category_id          int,
   seq_id               int,
   symbol               varchar(50) comment '代号',
   name                 varchar(50) comment '名称',
   model                varchar(50) comment '型号',
   material             varchar(50) comment '材料',
   quantity             int comment '数量',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (part_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table eam_parts comment '备件';

/*==============================================================*/
/* Table: eam_parts_category                                    */
/*==============================================================*/
drop table if exists eam_parts_category;
create table eam_parts_category
(
   category_id          int not null auto_increment,
   name                 varchar(30),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id           int,
   primary key (category_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table eam_parts_category comment ' 备件类别';

drop table if exists eam_file_template;
create table eam_file_template
(
   id                   int not null auto_increment,
   template_type        int,
   name                 varchar(30),
   path                 varchar(100),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id           int,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists eam_order;
CREATE TABLE eam_order (
  id                           int(11) NOT NULL AUTO_INCREMENT,
  company_name                 varchar(100) NOT NULL,
  short_name                   varchar(50) DEFAULT NULL,
  year                         varchar(5) DEFAULT NULL COMMENT '年份',
  task_number                  varchar(10) DEFAULT NULL COMMENT '任务单号',
  state                        varchar(8) DEFAULT NULL COMMENT '洲',
  country                      varchar(10) DEFAULT NULL COMMENT '国家',
  province                     varchar(10) DEFAULT NULL COMMENT '省/州',
  city                         varchar(10) DEFAULT NULL COMMENT '地/市',
  industry                     varchar(15) DEFAULT NULL COMMENT '所属行业',
  product_line_type            varchar(3) DEFAULT NULL COMMENT '产线类型',
  has_cxg                      boolean DEFAULT NULL COMMENT '是否含吹灌旋',
  has_znlk                     boolean DEFAULT NULL COMMENT '是否含智能立库',
  product_line_capacity        varchar(3) DEFAULT NULL COMMENT '生产线产能',
  packaging_material           varchar(3) DEFAULT NULL COMMENT '包装材质',
  product_spec                 varchar(3) DEFAULT NULL COMMENT '产品规格',
  major_equipment              varchar(500) DEFAULT NULL COMMENT '主要设备',
  comment                      TEXT DEFAULT NULL COMMENT '备注',
  create_user_id               int,
  create_time                  datetime,
  update_user_id               int,
  update_time                  datetime,
  delete_flag                  boolean,
  primary key (id)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists eam_shift_data_element;
create table eam_shift_data_element
(  
   id                   int not null auto_increment,
   equipment_id         varchar(32),
   product_line_id      varchar(32),
   data_element_id      int,
   summation            boolean COMMENT '是否累加',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists eam_shift_data_element_value;
create table eam_shift_data_element_value
(  
   id                   int not null auto_increment,
   equipment_id         varchar(32),
   product_line_id      varchar(32),
   data_element_id      int,
   shift                varchar(10) COMMENT '班次',
   value                varchar(30),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;