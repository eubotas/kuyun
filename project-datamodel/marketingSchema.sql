use kuyun;
SET FOREIGN_KEY_CHECKS=0;

drop table if exists mkt_sms_setting;
drop table if exists mkt_sms_template;
drop table if exists mkt_sms;
drop table if exists mkt_sms_user;

create table mkt_sms_setting
(
   id                   int not null auto_increment,
   app_name			        varchar(50) comment '应用名称',
   app_key              varchar(50) comment 'appKey',
   app_secret           varchar(50) comment 'appSecret',
   organization_id      int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime comment '更新时间',
   delete_flag          boolean,
   primary key (id)
);

create table mkt_sms_template
(
   id                   int not null auto_increment,
   template_id          varchar(10),
   name					        varchar(50) comment '名称',
   content              varchar(500) comment '短信模板',
   status               varchar(50) comment '审核状态',
   organization_id      int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime comment '更新时间',
   delete_flag          boolean,
   primary key (id)
);

create table mkt_sms
(
   id                   int not null auto_increment,
   sms_template_id      int,
   send_time            datetime comment '发送时间',
   organization_id      int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime comment '更新时间',
   delete_flag          boolean,
   primary key (id)
);

create table mkt_sms_user
(
   id                   int not null auto_increment,
   sms_id               int,
   user_id              int,
   organization_id      int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime comment '更新时间',
   delete_flag          boolean,
   primary key (id)
);

