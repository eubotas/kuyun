ALTER TABLE `upms_role` ADD COLUMN `delete_flag`  tinyint(1) NULL DEFAULT 0;
ALTER TABLE `upms_log` ADD COLUMN `delete_flag`  tinyint(1) NULL DEFAULT 0;
ALTER TABLE `upms_organization` ADD COLUMN `delete_flag`  tinyint(1) NULL DEFAULT 0;
ALTER TABLE `upms_permission` ADD COLUMN `delete_flag`  tinyint(1) NULL DEFAULT 0;
ALTER TABLE `upms_system` ADD COLUMN `delete_flag`  tinyint(1) NULL DEFAULT 0;
ALTER TABLE `upms_user` ADD COLUMN `delete_flag`  tinyint(1) NULL DEFAULT 0;
ALTER TABLE `upms_organization_role` ADD COLUMN `delete_flag`  tinyint(1) NULL DEFAULT 0;
ALTER TABLE `upms_role_permission` ADD COLUMN `delete_flag`  tinyint(1) NULL DEFAULT 0;
ALTER TABLE `upms_user_company` ADD COLUMN `delete_flag`  tinyint(1) NULL DEFAULT 0;
ALTER TABLE `upms_user_organization` ADD COLUMN `delete_flag`  tinyint(1) NULL DEFAULT 0;
ALTER TABLE `upms_user_permission` ADD COLUMN `delete_flag`  tinyint(1) NULL DEFAULT 0;
ALTER TABLE `upms_user_role` ADD COLUMN `delete_flag`  tinyint(1) NULL DEFAULT 0;

ALTER TABLE `upms_role` ADD COLUMN `company_id`  int(11) NULL DEFAULT NULL ;