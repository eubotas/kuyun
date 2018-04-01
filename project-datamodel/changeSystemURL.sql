use xmx;
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

update upms_system set basepath = 'http://139.196.141.29:1111' where system_id = 1;
update upms_system set basepath = 'http://139.196.141.29:9999' where system_id = 2;
update upms_system set basepath = 'http://139.196.141.29:9998' where system_id = 7;
update upms_system set basepath = 'http://139.196.141.29:9498' where system_id = 8;


#update local
update upms_system set basepath = 'http://upms.kuyun.cn:1111' where system_id = 1;
update upms_system set basepath = 'http://eam.kuyun.cn:9999' where system_id = 6;
update upms_system set basepath = 'http://fd.kuyun.cn:9498' where system_id = 8;