drop table if exists eam_equipment_product;
create table eam_equipment_product
(
   id                   int not null auto_increment,
   equipment_id         varchar(32) not null,
   materiel_number      varchar(10) comment '物料ID',
   materiel_name        varchar(20) comment '物料名称',
   capacity             int         comment '额定产能',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id           int,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '设备生产品相';

