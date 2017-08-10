use kuyun;

SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

ALTER TABLE upms_system modify name varchar(50);

ALTER TABLE eam_equipment ADD   province    varchar(10);
ALTER TABLE eam_equipment ADD   city        varchar(10);
ALTER TABLE eam_equipment_model_properties ADD   display_type        varchar(10);