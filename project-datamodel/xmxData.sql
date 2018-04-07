use xmx;

SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

TRUNCATE TABLE eam_code_value;

#维修频率设定
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('MAINTAIN_PLAN_UNIT','YEAR','年','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('MAINTAIN_PLAN_UNIT','MONTH','月','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('MAINTAIN_PLAN_UNIT','WEEK','周','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('MAINTAIN_PLAN_UNIT','DAY','天','',1,now(),1,now(),0);

#洲
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('STATE','ASIA','亚洲','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('STATE','AMERICA','美洲','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('STATE','EUROPE','欧洲','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('STATE','AUSTRALIA','澳洲','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('STATE','AFRICA','非洲','',1,now(),1,now(),0);

#所属行业
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('INDUSTRY','BEVERAGE','饮料','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('INDUSTRY','DAIRY_PRODUCT','乳制品','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('INDUSTRY','CONDIMENT','调味品','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('INDUSTRY','DAILY_CHEMICAL','日化','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('INDUSTRY','PHARMACY','制药','',1,now(),1,now(),0);

#产线类型
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_TYPE','1','无菌灌装','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_TYPE','2','超洁净灌装','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_TYPE','3','热灌装','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_TYPE','4','饮用水灌装','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_TYPE','5','桶装水灌装','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_TYPE','6','含气饮料灌装','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_TYPE','7','酒类灌装','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_TYPE','8','调味品灌装','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_TYPE','9','食用油灌装','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_TYPE','10','日化品灌装','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_TYPE','11','水处理','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_TYPE','12','前处理','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_TYPE','13','智能立库','',1,now(),1,now(),0);

#生产线产能
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','1','300','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','2','450','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','3','600','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','4','1200','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','5','1500','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','6','1800','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','7','2000','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','8','3000','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','9','4000','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','10','6000','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','11','8000','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','12','12000','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','13','15000','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','14','18000','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','15','24000','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','16','28000','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','17','30000','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','18','36000','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','19','40000','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','20','48000','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_LINE_CAPACITY','21','72000','',1,now(),1,now(),0);

#包装材质
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PACKAGING_MATERIAL','1','PET瓶','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PACKAGING_MATERIAL','2','玻璃瓶','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PACKAGING_MATERIAL','3','易拉罐','',1,now(),1,now(),0);


#产品规格
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_SPEC','1','小瓶','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_SPEC','2','中瓶','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_SPEC','3','大瓶','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_SPEC','4','超大瓶','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('PRODUCT_SPEC','5','桶','',1,now(),1,now(),0);

#文件模板
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('FILE_TEMPLATE','1','任务单模板','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('FILE_TEMPLATE','2','备件资料模板','',1,now(),1,now(),0);
INSERT INTO eam_code_value (category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES ('FILE_TEMPLATE','3','客户资料模板','',1,now(),1,now(),0);
