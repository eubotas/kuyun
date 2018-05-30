use xmx_job;
SET FOREIGN_KEY_CHECKS=0;

truncate QRTZ_TRIGGERS;
truncate QRTZ_JOB_DETAILS;
truncate QRTZ_FIRED_TRIGGERS;
truncate QRTZ_SIMPLE_TRIGGERS;

use xmx;
truncate eam_alarm_record;
truncate eam_alarm_record_history;
truncate eam_ticket;
truncate eam_ticket_appointed_record;
truncate eam_ticket_assessment;
truncate eam_ticket_record;
truncate eam_maintain_plan;
truncate eam_maintain_user;
truncate eam_maintain_ticket;
truncate eam_alert_message;

truncate eam_grm_variable;
truncate eam_grm_variable_group;
truncate eam_grm_variable_data;
truncate eam_grm_variable_data_history;
truncate eam_grm_variable_data_by_day;
truncate eam_grm_variable_data_by_month;
truncate eam_grm_variable_data_by_year;
truncate eam_shift_data_element_value;

