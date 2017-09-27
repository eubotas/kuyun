use kuyun;
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

#系统设置
delete from upms_system where system_id = 7;
INSERT INTO `upms_system` VALUES ('7', 'zmdi zmdi-cloud', '/resources/kuyun-admin/images/kuyun-oss.png', '#0B8DE5', 'http://marketing.kuyun.cn:9998', '1', 'kuyun-marketing-admin', '营销中心', '营销中心', '7', '7');

delete from upms_permission where permission_id >= 500;
INSERT INTO `upms_permission` VALUES ('500', '7', '0', '网易云信', '1', null, null, 'zmdi zmdi-collection-text', '1', '500', '500');
INSERT INTO `upms_permission` VALUES ('501', '7', '500', '短信设置', '2', 'mkt:smsSetting:read',   '/manage/sms/setting/index', null, '1', '501', '501');
INSERT INTO `upms_permission` VALUES ('502', '7', '501', '新增短信设置', '3', 'mkt:smsSetting:create', '/manage/sms/setting/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('503', '7', '501', '编辑短信设置', '3', 'mkt:smsSetting:update', '/manage/sms/setting/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('504', '7', '501', '删除短信设置', '3', 'mkt:smsSetting:delete', '/manage/sms/setting/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('505', '7', '500', '短信模板', '2', 'mkt:smsTemplate:read',   '/manage/sms/template/index', null, '1', '505', '505');
INSERT INTO `upms_permission` VALUES ('506', '7', '505', '新增短信模板', '3', 'mkt:smsTemplate:create', '/manage/sms/template/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('507', '7', '505', '编辑短信模板', '3', 'mkt:smsTemplate:update', '/manage/sms/template/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('508', '7', '505', '删除短信模板', '3', 'mkt:smsTemplate:delete', '/manage/sms/template/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');


INSERT INTO `upms_permission` VALUES ('509', '7', '500', '短信记录', '2', 'mkt:sms:read',   '/manage/sms/index', null, '1', '509', '509');
INSERT INTO `upms_permission` VALUES ('510', '7', '509', '新增短信记录', '3', 'mkt:sms:create', '/manage/sms/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('511', '7', '509', '编辑短信记录', '3', 'mkt:sms:update', '/manage/sms/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('512', '7', '509', '删除短信记录', '3', 'mkt:sms:delete', '/manage/sms/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');


INSERT INTO `upms_permission` VALUES ('520', '7', '0', '运营统计', '1', null, null, 'zmdi zmdi-collection-text', '1', '520', '520');
INSERT INTO `upms_permission` VALUES ('521', '7', '520', '访问统计', '2', 'mkt:marketing:read',   '/manage/marketing/user/index', null, '1', '521', '521');
INSERT INTO `upms_permission` VALUES ('522', '7', '520', '设备统计', '2', 'mkt:marketing:read',   '/manage/marketing/equipment/index', null, '1', '522', '522');
