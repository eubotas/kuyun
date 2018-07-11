ALTER TABLE eam_alarm_record_history ADD alarm_content        varchar(250);
ALTER TABLE eam_alarm_record_history ADD alarm_content        varchar(250);

ALTER TABLE eam_product_line ADD preform_quantity_id       int comment '瓶坯总数';
ALTER TABLE eam_product_line ADD cap_quantity_id           int comment '旋盖总数';
ALTER TABLE eam_product_line ADD wrap_quantity_id          int comment '膜包数量';
ALTER TABLE eam_product_line ADD electricity_unit_price    decimal(10,2) comment '电费单价';

ALTER TABLE eam_product_line_shift_data ADD preform_consume       int comment '瓶坯损耗';
ALTER TABLE eam_product_line_shift_data ADD cap_consume           int comment '瓶盖消耗';


ALTER TABLE eam_equipment ADD  output_id            int         comment '产量ID';
ALTER TABLE eam_equipment ADD  electricity_id       int         comment '耗电量ID';
ALTER TABLE eam_equipment ADD  video_path           varchar(100) comment '视频地址';


ALTER TABLE eam_equipment_product Add packing              int         comment '包装规格';
ALTER TABLE eam_equipment_product Add stacking             int         comment '码垛规格';

ALTER TABLE eam_shift_statistic_data MODIFY COLUMN code varchar(30);
ALTER TABLE eam_statistic_data_by_day MODIFY COLUMN code varchar(30);
ALTER TABLE eam_statistic_data_by_month MODIFY COLUMN code varchar(30);
ALTER TABLE eam_statistic_data_by_year MODIFY COLUMN code varchar(30);
