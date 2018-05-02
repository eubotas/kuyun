use kuyun;
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

update upms_system set basepath = 'http://122.112.237.243:1111' where system_id = 1;
update upms_system set basepath = 'http://122.112.237.243:9999' where system_id = 2;
update upms_system set basepath = 'http://122.112.237.243:9998' where system_id = 3;
update upms_system set basepath = 'http://122.112.237.243:9498' where system_id = 4;