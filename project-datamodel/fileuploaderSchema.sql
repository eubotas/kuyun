use kuyun;
SET FOREIGN_KEY_CHECKS=0;

drop table if exists fd_files; 
drop table if exists fd_oss_files;

create table fd_files 
(
   uuid                 varchar(100),
   file_name            varchar(500) comment '上传时候的文件名',
   mime                 varchar(500) ,
   size                 bigint zerofill,
   saved_file_name      varchar(500) comment '文件系统中的文件名，防重名做随机化处理',
   moudle_name          varchar(500) comment '模块名，文件会存放在以模块名相同的子目录下',
   oss_id               int comment 'oss信息',
   organization_id      int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime comment '更新时间',
   delete_flag          boolean,
   primary key (uuid)
);

create table fd_oss_files
(
   id                   int not null auto_increment,
# TODO: add related fields, like policy
   organization_id      int,
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime comment '更新时间',
   delete_flag          boolean,
   primary key (id)
);
