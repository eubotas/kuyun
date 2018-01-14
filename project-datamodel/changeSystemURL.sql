use xmx;
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

update upms_system set basepath = 'http://119.3.2.68:1111' where system_id = 1;
update upms_system set basepath = 'http://119.3.2.68:9999' where system_id = 6;
update upms_system set basepath = 'http://119.3.2.68:9998' where system_id = 7;
update upms_system set basepath = 'http://119.3.2.68:9498' where system_id = 8;