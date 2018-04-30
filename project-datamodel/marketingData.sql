use kuyun;
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

#系统设置
delete from upms_system where system_id = 3;
INSERT INTO `upms_system` VALUES ('3', 'zmdi zmdi-cloud', '/resources/kuyun-admin/images/kuyun-oss.png', '#0B8DE5', 'http://marketing.kuyun.cn:9998', '1', 'kuyun-marketing-admin', '营销中心', '营销中心', '3', '3');

delete from upms_permission where permission_id >= 2000;
INSERT INTO `upms_permission` VALUES ('2000', '3', '0', '网易云信', '1', null, null, 'zmdi zmdi-collection-text', '1', '2000', '2000');
INSERT INTO `upms_permission` VALUES ('2001', '3', '2000', '短信设置', '2', 'mkt:smsSetting:read',   '/manage/sms/setting/index', null, '1', '2001', '2001');
INSERT INTO `upms_permission` VALUES ('2002', '3', '2001', '新增短信设置', '3', 'mkt:smsSetting:create', '/manage/sms/setting/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('2003', '3', '2001', '编辑短信设置', '3', 'mkt:smsSetting:update', '/manage/sms/setting/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('2004', '3', '2001', '删除短信设置', '3', 'mkt:smsSetting:delete', '/manage/sms/setting/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('2005', '3', '2000', '短信模板', '2', 'mkt:smsTemplate:read',   '/manage/sms/template/index', null, '1', '2005', '2005');
INSERT INTO `upms_permission` VALUES ('2006', '3', '2005', '新增短信模板', '3', 'mkt:smsTemplate:create', '/manage/sms/template/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('2007', '3', '2005', '编辑短信模板', '3', 'mkt:smsTemplate:update', '/manage/sms/template/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('2008', '3', '2005', '删除短信模板', '3', 'mkt:smsTemplate:delete', '/manage/sms/template/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');


INSERT INTO `upms_permission` VALUES ('2009', '3', '2000', '短信记录', '2', 'mkt:sms:read',   '/manage/sms/index', null, '1', '2009', '2009');
INSERT INTO `upms_permission` VALUES ('2010', '3', '2009', '新增短信记录', '3', 'mkt:sms:create', '/manage/sms/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('2011', '3', '2009', '编辑短信记录', '3', 'mkt:sms:update', '/manage/sms/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('2012', '3', '2009', '删除短信记录', '3', 'mkt:sms:delete', '/manage/sms/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');


INSERT INTO `upms_permission` VALUES ('2020', '3', '0', '运营统计', '1', null, null, 'zmdi zmdi-collection-text', '1', '2020', '2020');
INSERT INTO `upms_permission` VALUES ('2021', '3', '2020', '访问统计', '2', 'mkt:marketing:read',   '/manage/marketing/user/index', null, '1', '2021', '2021');
INSERT INTO `upms_permission` VALUES ('2022', '3', '2020', '设备统计', '2', 'mkt:marketing:read',   '/manage/marketing/equipment/index', null, '1', '2022', '2022');
