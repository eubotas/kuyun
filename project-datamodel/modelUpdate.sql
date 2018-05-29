drop table if exists eam_grm_variable;
create table eam_grm_variable
(
   id                   int not null auto_increment,
   equipment_id         varchar(32),
   product_line_id      varchar(32),
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
   data_element_id      int,
   value                varchar(10),
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
   id                   bigint unsigned not null auto_increment,
   eam_grm_variable_id  int,
   equipment_id         varchar(32),
   product_line_id      varchar(32),
   data_element_id      int,
   value                varchar(10),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER table eam_grm_variable_data_history ADD INDEX index_update_time(update_time);


drop table if exists eam_grm_variable_group;
create table eam_grm_variable_group
(
   id                   int not null auto_increment,
   eam_grm_variable_id   int,
   data_group_id        int,
   equipment_data_group_id int comment '设备数据分组ID',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table if exists eam_grm_variable_data_group;
create table eam_grm_variable_data_group
(
   id                   int not null auto_increment,
   eam_grm_variable_data_id   int,
   data_group_id        int,
   equipment_data_group_id int comment '设备数据分组ID',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists eam_grm_variable_data_history_group;
create table eam_grm_variable_data_history_group
(
   id                   int not null auto_increment,
   eam_grm_variable_data_history_id   bigint,
   data_group_id        int,
   equipment_data_group_id int comment '设备数据分组ID',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists eam_grm_variable_data_by_day;
create table eam_grm_variable_data_by_day
(
   id                   bigint unsigned not null auto_increment,
   eam_grm_variable_id  int,
   equipment_id         varchar(32),
   product_line_id      varchar(32),
   data_element_id      int,
   value                varchar(10),
   date                 date,
   switch_value         boolean COMMENT '开关量 --有值,模拟量--空',
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
   id                   bigint unsigned not null auto_increment,
   eam_grm_variable_id  int,
   equipment_id         varchar(32),
   product_line_id      varchar(32),
   data_element_id      int,
   year                 int,
   month                int,
   value                varchar(10),
   switch_value         boolean COMMENT '开关量 --有值,模拟量--空',
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
   id                   bigint unsigned not null auto_increment,
   eam_grm_variable_id  int,
   equipment_id         varchar(32),
   product_line_id      varchar(32),
   data_element_id      int,
   year                 int,
   value                varchar(10),
   switch_value         boolean COMMENT '开关量 --有值,模拟量--空',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
);

ALTER table eam_grm_variable_data_by_year ADD INDEX index_update_time(update_time);
drop table if exists eam_shift_data_element_value;
create table eam_shift_data_element_value
(  
   id                   bigint unsigned not null auto_increment,
   eam_grm_variable_id  int,
   equipment_id         varchar(32),
   product_line_id      varchar(32),
   data_element_id      int,
   shift                varchar(8) COMMENT '班次',
   value                varchar(10),
   switch_value         boolean  COMMENT '开关量 --有值,模拟量--空',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;