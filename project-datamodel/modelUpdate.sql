use kuyun;

SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

ALTER TABLE upms_system modify name varchar(50);

ALTER TABLE eam_equipment ADD   province    varchar(10);
ALTER TABLE eam_equipment ADD   city        varchar(10);
ALTER TABLE eam_equipment_model_properties ADD   display_type        varchar(10);

ALTER TABLE eam_sensor ADD bit_order  varchar(10) comment 'Modbus RTU 字节顺序';

ALTER TABLE eam_sensor ADD grm_variable_order   int comment '巨控 读写变量顺序';

ALTER TABLE eam_equipment ADD collect_status       varchar(10) comment '采集状态';