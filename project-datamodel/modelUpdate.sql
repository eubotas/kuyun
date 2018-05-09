ALTER TABLE eam_grm_variable ADD grm_period           int ;

ALTER TABLE eam_order ADD short_name varchar(50) DEFAULT NULL;

ALTER TABLE eam_parts ADD  product_line_id      varchar(32);

ALTER TABLE eam_grm_variable_data ADD eam_grm_variable_id  int;
ALTER TABLE eam_grm_variable_data_history ADD eam_grm_variable_id  int;
ALTER TABLE eam_grm_variable_data_by_day ADD eam_grm_variable_id  int;
ALTER TABLE eam_grm_variable_data_by_month ADD eam_grm_variable_id  int;
ALTER TABLE eam_grm_variable_data_by_year ADD eam_grm_variable_id  int;

INSERT INTO `upms_role` VALUES ('7', 1, 'customer', '客户', '拥有客户权限', '1', '1');


