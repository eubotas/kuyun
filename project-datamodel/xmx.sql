use xmx;
#产线
drop table if exists eam_product_line;
create table eam_product_line
(
   product_line_id      varchar(32) not null,
   name                 varchar(30),
   image_path           varchar(100),
   grm                  varchar(50) comment '巨控设备ID',
   grm_password         varchar(50) comment '巨控设备密码',
   grm_period           int         comment '巨控采集频率单位秒',
   collect_status       varchar(10) comment '采集状态',
   is_online            boolean,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (product_line_id)
);

drop table if exists eam_product_line_property;
create table eam_product_line_property
(  
   id                   int not null auto_increment,
   product_line_id      varchar(32) not null,
   type                 varchar(30),
   code                 varchar(30),
   name                 varchar(30),
   value                varchar(30),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   primary key (id)
);

DROP TABLE IF EXISTS `eam_product_line_company`;
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

ALTER TABLE eam_equipment ADD product_line_id  varchar(32);
ALTER TABLE eam_equipment ADD left_position  decimal(10,2);
ALTER TABLE eam_equipment ADD top_position  decimal(10,2);



