ALTER TABLE eam_alarm_record_history ADD alarm_content        varchar(250);
ALTER TABLE eam_alarm_record_history ADD alarm_content        varchar(250);

ALTER TABLE eam_product_line ADD preform_quantity_id       int comment '瓶坯总数';
ALTER TABLE eam_product_line ADD cap_quantity_id           int comment '旋盖总数';




ALTER TABLE eam_equipment_product Add packing              int         comment '包装规格';
ALTER TABLE eam_equipment_product Add stacking             int         comment '码垛规格';
