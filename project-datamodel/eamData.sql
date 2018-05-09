use xmx;
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

#系统设置
delete from upms_system where system_id = 6;
INSERT INTO `upms_system` VALUES ('6', 'zmdi zmdi-cloud', '/resources/kuyun-admin/images/kuyun-oss.png', '#0B8DE5', 'http://eam.kuyun.cn:9999', '1', 'kuyun-eam-admin', '设备管理系统', '设备管理系统', '6', '6');

delete from upms_permission where permission_id >= 200;

INSERT INTO `upms_permission` VALUES ('200', '6', '0', '产线管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '200', '200');
INSERT INTO `upms_permission` VALUES ('201', '6', '200', '产线管理', '2', 'eam:productLine:read',   '/manage/productLine/index', null, '1', '201', '201');
INSERT INTO `upms_permission` VALUES ('202', '6', '201', '新增产线', '3', 'eam:productLine:create', '/manage/productLine/create', 'zmdi zmdi-plus', '1', '1489820150404', '202');
INSERT INTO `upms_permission` VALUES ('203', '6', '201', '编辑产线', '3', 'eam:productLine:update', '/manage/productLine/update', 'zmdi zmdi-edit', '1', '1489820178269', '203');
INSERT INTO `upms_permission` VALUES ('204', '6', '201', '删除产线', '3', 'eam:productLine:delete', '/manage/productLine/delete', 'zmdi zmdi-close', '1', '1489820207607', '204');

INSERT INTO `upms_permission` VALUES ('205', '6', '200', '设备管理', '2', 'eam:equipment:read',   '/manage/{productLineId}/equipment/index', null, '1', '205', '205');
INSERT INTO `upms_permission` VALUES ('206', '6', '205', '新增设备', '3', 'eam:equipment:create', '/manage/{productLineId}/equipment/create', 'zmdi zmdi-plus', '1', '1489820150404', '206');
INSERT INTO `upms_permission` VALUES ('207', '6', '205', '编辑设备', '3', 'eam:equipment:update', '/manage/{productLineId}/equipment/update', 'zmdi zmdi-edit', '1', '1489820178269', '207');
INSERT INTO `upms_permission` VALUES ('208', '6', '205', '删除设备', '3', 'eam:equipment:delete', '/manage/{productLineId}/equipment/delete', 'zmdi zmdi-close', '1', '1489820207607', '208');

INSERT INTO `upms_permission` VALUES ('210', '6', '0', '报警设置', '1', null, null, 'zmdi zmdi-collection-text', '1', '210', '210');
INSERT INTO `upms_permission` VALUES ('211', '6', '210', '报警管理', '2', 'eam:alarm:read',   '/manage/{productLineId}/alarm/index', null, '1', '211', '211');
INSERT INTO `upms_permission` VALUES ('212', '6', '211', '新增报警', '3', 'eam:alarm:create', '/manage/{productLineId}/alarm/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('213', '6', '211', '编辑报警', '3', 'eam:alarm:update', '/manage/{productLineId}/alarm/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('214', '6', '211', '删除报警', '3', 'eam:alarm:delete', '/manage/{productLineId}/alarm/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('215', '6', '0', '数据采集', '1', null, null, 'zmdi zmdi-collection-text', '1', '210', '210');
INSERT INTO `upms_permission` VALUES ('216', '6', '215', '数据采集', '2', 'eam:productLine:update',   '/manage/productLine/collect/index', null, '1', '216', '216');
INSERT INTO `upms_permission` VALUES ('217', '6', '216', '启动', '3', 'eam:productLine:update', '/manage/productLine/collect/start', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('218', '6', '216', '停止', '3', 'eam:productLine:update', '/manage/productLine/collect/stop', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');

INSERT INTO `upms_permission` VALUES ('240', '6', '0', '备品备件', '1', null, null, 'zmdi zmdi-collection-text', '1', '240', '240');
INSERT INTO `upms_permission` VALUES ('241', '6', '240', '备件类别管理', '2', 'eam:partCategory:read',   '/manage/part/category/index', null, '1', '241', '241');
INSERT INTO `upms_permission` VALUES ('242', '6', '241', '新增备件类别', '3', 'eam:partCategory:create', '/manage/part/category/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('243', '6', '241', '编辑备件类别', '3', 'eam:partCategory:update', '/manage/part/category/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('244', '6', '241', '删除备件类别', '3', 'eam:partCategory:delete', '/manage/part/category/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('245', '6', '240', '备件管理', '2', 'eam:part:read',   '/manage/part/index', null, '1', '245', '245');
INSERT INTO `upms_permission` VALUES ('246', '6', '245', '新增备件', '3', 'eam:part:create', '/manage/part/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('247', '6', '245', '编辑备件', '3', 'eam:part:update', '/manage/part/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('248', '6', '245', '删除备件', '3', 'eam:part:delete', '/manage/part/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');


INSERT INTO `upms_permission` VALUES ('219', '6', '0', ' 客户管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '219', '219');
INSERT INTO `upms_permission` VALUES ('270', '6', '219', '客户管理', '2', 'eam:company:read',   '/manage/company/index', null, '1', '270', '270');
INSERT INTO `upms_permission` VALUES ('271', '6', '270', '新增客户', '3', 'eam:company:create', '/manage/company/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('272', '6', '270', '编辑客户', '3', 'eam:company:update', '/manage/company/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('273', '6', '270', '删除客户', '3', 'eam:company:delete', '/manage/company/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('274', '6', '219', '任务单管理', '2', 'eam:order:read',   '/manage/order/index', null, '1', '270', '270');
INSERT INTO `upms_permission` VALUES ('275', '6', '274', '新增任务单', '3', 'eam:order:create', '/manage/order/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('276', '6', '274', '编辑任务单', '3', 'eam:order:update', '/manage/order/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('277', '6', '274', '删除任务单', '3', 'eam:order:delete', '/manage/order/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('280', '6', '205', '数据写入', '3', 'eam:equipmentSensor:write', '/manage/equipment/sensor/', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');


delete from upms_permission where uri like '/manage/ticket%';
INSERT INTO `upms_permission` VALUES ('300', '6', '0', '工单管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '220', '300');
INSERT INTO `upms_permission` VALUES ('310', '6', '300', '工单类型', '2', 'eam:ticketType:read', '/manage/ticket/type/index', NULL, 1, 221, 410);
INSERT INTO `upms_permission` VALUES ('311', '6', '310', '新增类型', '3', 'eam:ticketType:create', '/manage/ticket/type/create', 'zmdi zmdi-plus', 1, 1489820150404, 311);
INSERT INTO `upms_permission` VALUES ('312', '6', '310', '编辑类型', '3', 'eam:ticketType:update', '/manage/ticket/type/update', 'zmdi zmdi-edit', 1, 1489820178269, 312);
INSERT INTO `upms_permission` VALUES ('313', '6', '310', '删除类型', '3', 'eam:ticketType:delete', '/manage/ticket/type/delete', 'zmdi zmdi-close', 1, 1489820207607, 313);
INSERT INTO `upms_permission` VALUES ('320', '6', '300', '我的未处理工单', '2', 'eam:myOpenTicket:read', '/manage/ticket/index?category=myOpen', NULL, 1, 225, 320);
INSERT INTO `upms_permission` VALUES ('330', '6', '300', '我的全部工单', '2', 'eam:myAllTicket:read', '/manage/ticket/index?category=myAll', NULL, 1, 225, 330);
INSERT INTO `upms_permission` VALUES ('340', '6', '300', '未委派工单', '2', 'eam:initTicket:read', '/manage/ticket/index?category=init', NULL, 1, 225, 340);
INSERT INTO `upms_permission` VALUES ('350', '6', '300', '全部工单', '2', 'eam:ticket:read', '/manage/ticket/index?category=all', NULL, 1, 225, 350);
INSERT INTO `upms_permission` VALUES ('351', '6', '350', '新增工单', '3', 'eam:ticket:create', '/manage/ticket/create', 'zmdi zmdi-plus', 1, 1489820150404, 1489820150404);
INSERT INTO `upms_permission` VALUES ('352', '6', '350', '编辑工单', '3', 'eam:ticket:update', '/manage/ticket/update', 'zmdi zmdi-edit', 1, 1489820178269, 1489820178269);
INSERT INTO `upms_permission` VALUES ('353', '6', '350', '删除工单', '3', 'eam:ticket:delete', '/manage/ticket/delete', 'zmdi zmdi-close', 1, 1489820207607, 1489820207607);

INSERT INTO `upms_permission` VALUES ('440', '6', '300', '工单评价标签', '2', 'eam:ticketTag:read', '/manage/ticket/tag/index', NULL, 1, 1, 440);
INSERT INTO `upms_permission` VALUES ('441', '6', '440', '新增工单评价标签', '3', 'eam:ticketTag:create', '/manage/ticket/tag/create', 'zmdi zmdi-plus', 1, 1489820150404, 441);
INSERT INTO `upms_permission` VALUES ('442', '6', '440', '编辑工单评价标签', '3', 'eam:ticketTag:update', '/manage/ticket/tag/update', 'zmdi zmdi-edit', 1, 1489820178269, 442);
INSERT INTO `upms_permission` VALUES ('443', '6', '440', '删除工单评价标签', '3', 'eam:ticketTag:delete', '/manage/ticket/tag/delete', 'zmdi zmdi-close', 1, 1489820207607, 443);
INSERT INTO `upms_permission` VALUES ('445', '6', '350', '新增工单记录', '3', 'eam:ticketRecord:create', '/manage/ticket/{tid}/record/create', 'zmdi zmdi-plus', 1, 1489820150404, 445);
INSERT INTO `upms_permission` VALUES ('446', '6', '350', '编辑工单记录', '3', 'eam:ticketRecord:update', '/manage/ticket/{tid}/record/update', 'zmdi zmdi-edit', 1, 1489820178269, 444);
INSERT INTO `upms_permission` VALUES ('447', '6', '350', '工单记录', '3', 'eam:ticketRecord:read', '/manage/ticket/{tid}/record/index', 'zmdi zmdi-close', 1, 1489820207607, 447);
INSERT INTO `upms_permission` VALUES ('451', '6', '350', '新增委派记录', '3', 'eam:ticketAppointedRecord:create', '/manage/ticket/{ticketId}/appoint/create', 'zmdi zmdi-plus', 1, 1489820150404, 451);
INSERT INTO `upms_permission` VALUES ('452', '6', '350', '编辑委派记录', '3', 'eam:ticketAppointedRecord:update', '/manage/ticket/{ticketId}/appoint/update', 'zmdi zmdi-edit', 1, 1489820178269, 452);
INSERT INTO `upms_permission` VALUES ('453', '6', '350', '删除委派记录', '3', 'eam:ticketAppointedRecord:delete', '/manage/ticket/{ticketId}/appoint/delete', 'zmdi zmdi-close', 1, 1489820207607, 453);
INSERT INTO `upms_permission` VALUES ('454', '6', '350', '委派记录', '3', 'eam:ticketAppointedRecord:read', '/manage/ticket/{ticketId}/appoint/index', NULL, 1, 1, 454);
INSERT INTO `upms_permission` VALUES ('461', '6', '350', '新增工单评价', '3', 'eam:ticketAssessment:create', '/manage/ticket/{ticketId}/assessment/create', 'zmdi zmdi-plus', 1, 1489820150404, 461);
INSERT INTO `upms_permission` VALUES ('462', '6', '350', '编辑工单评价', '3', 'eam:ticketAssessment:update', '/manage/ticket/{ticketId}/assessment/update', 'zmdi zmdi-edit', 1, 1489820178269, 462);
INSERT INTO `upms_permission` VALUES ('463', '6', '350', '删除工单评价', '3', 'eam:ticketAssessment:delete', '/manage/ticket/{ticketId}/assessment/delete', 'zmdi zmdi-close', 1, 1489820207607, 463);
INSERT INTO `upms_permission` VALUES ('464', '6', '350', '工单评价', '3', 'eam:ticketAssessment:read', '/manage/ticket/{ticketId}/assessment/index', '', 1, 1, 464);
INSERT INTO `upms_permission` VALUES ('345', '6', '300', '工单统计', '2', 'eam:ticket:summary', '/manage/ticket/summary', NULL, 1, 225, 460);

INSERT INTO `upms_permission` VALUES ('400', '6', '0', '知识管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '400', '400');
INSERT INTO `upms_permission` VALUES ('401', '6', '400', '知识搜索', '2', 'eam:knowledge:read',   '/manage/knowledge/index', null, '1', '270', '270');
INSERT INTO `upms_permission` VALUES ('402', '6', '400', '培训视频', '2', 'eam:trainingVideo:read',   '/manage/knowledge/training/video/index', null, '1', '270', '270');
INSERT INTO `upms_permission` VALUES ('403', '6', '402', '新增培训视频', '3', 'eam:trainingVideo:create', '/manage/knowledge/training/video/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('404', '6', '402', '编辑培训视频', '3', 'eam:trainingVideo:update', '/manage/knowledge/training/video/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('405', '6', '402', '删除培训视频', '3', 'eam:trainingVideo:delete', '/manage/knowledge/training/video/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('406', '6', '400', '培训文档', '2', 'eam:trainingDoc:read',   '/manage/knowledge/training/doc/index', null, '1', '270', '270');
INSERT INTO `upms_permission` VALUES ('407', '6', '406', '新增培训文档', '3', 'eam:trainingDoc:create', '/manage/knowledge/training/doc/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('408', '6', '406', '编辑培训文档', '3', 'eam:trainingDoc:update', '/manage/knowledge/training/doc/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('409', '6', '406', '删除培训文档', '3', 'eam:trainingDoc:delete', '/manage/knowledge/training/doc/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('410', '6', '400', '保养知识', '2', 'eam:maintainKnowledge:read',   '/manage/knowledge/maintain/index', null, '1', '270', '270');
INSERT INTO `upms_permission` VALUES ('411', '6', '410', '新增保养知识', '3', 'eam:maintainKnowledge:create', '/manage/knowledge/maintain/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('412', '6', '410', '编辑保养知识', '3', 'eam:maintainKnowledge:update', '/manage/knowledge/maintain/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('413', '6', '410', '删除保养知识', '3', 'eam:maintainKnowledge:delete', '/manage/knowledge/maintain/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('414', '6', '400', '维修知识', '2', 'eam:repairKnowledge:read',   '/manage/knowledge/repair/index', null, '1', '270', '270');
INSERT INTO `upms_permission` VALUES ('415', '6', '414', '新增维修知识', '3', 'eam:repairKnowledge:create', '/manage/knowledge/repair/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('416', '6', '414', '编辑维修知识', '3', 'eam:repairKnowledge:update', '/manage/knowledge/repair/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('417', '6', '414', '删除维修知识', '3', 'eam:repairKnowledge:delete', '/manage/knowledge/repair/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('418', '6', '400', '设备手册', '2', 'eam:equipmentManual:read',   '/manage/knowledge/manual/index', null, '1', '270', '270');
INSERT INTO `upms_permission` VALUES ('419', '6', '418', '新增设备手册', '3', 'eam:equipmentManual:create', '/manage/knowledge/manual/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('420', '6', '418', '编辑设备手册', '3', 'eam:equipmentManual:update', '/manage/knowledge/manual/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('421', '6', '418', '删除设备手册', '3', 'eam:equipmentManual:delete', '/manage/knowledge/manual/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');


INSERT INTO `upms_permission` VALUES ('500', '6', '0', ' 基础数据管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '199', '199');
INSERT INTO `upms_permission` VALUES ('501', '6', '500', '数据点管理', '2', 'eam:dataElement:read',   '/manage/dataElement/index', null, '1', '270', '270');
INSERT INTO `upms_permission` VALUES ('502', '6', '501', '新增数据点', '3', 'eam:dataElement:create', '/manage/dataElement/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('503', '6', '501', '编辑数据点', '3', 'eam:dataElement:update', '/manage/dataElement/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('504', '6', '501', '删除数据点', '3', 'eam:dataElement:delete', '/manage/dataElement/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('505', '6', '500', '数据点分组管理', '2', 'eam:dataElementGroup:read',   '/manage/dataElementGroup/index', null, '1', '270', '270');
INSERT INTO `upms_permission` VALUES ('506', '6', '505', '新增数据点分组', '3', 'eam:dataElementGroup:create', '/manage/dataElementGroup/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('507', '6', '505', '编辑数据点分组', '3', 'eam:dataElementGroup:update', '/manage/dataElementGroup/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('508', '6', '505', '删除数据点分组', '3', 'eam:dataElementGroup:delete', '/manage/dataElementGroup/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('509', '6', '500', '报警模板管理', '2', 'eam:alarmModel:read',   '/manage/alarmModel/index', null, '1', '270', '270');
INSERT INTO `upms_permission` VALUES ('510', '6', '509', '新增报警模板', '3', 'eam:alarmModel:create', '/manage/alarmModel/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('511', '6', '509', '编辑报警模板', '3', 'eam:alarmModel:update', '/manage/alarmModel/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('512', '6', '509', '删除报警模板', '3', 'eam:alarmModel:delete', '/manage/alarmModel/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('513', '6', '500', '设备类别管理', '2', 'eam:equipmentCategory:read',   '/manage/equipment/category/index', null, '1', '270', '270');
INSERT INTO `upms_permission` VALUES ('514', '6', '513', '新增设备类别', '3', 'eam:equipmentCategory:create', '/manage/equipment/category/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404');
INSERT INTO `upms_permission` VALUES ('515', '6', '513', '编辑设备类别', '3', 'eam:equipmentCategory:update', '/manage/equipment/category/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269');
INSERT INTO `upms_permission` VALUES ('516', '6', '513', '删除设备类别', '3', 'eam:equipmentCategory:delete', '/manage/equipment/category/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607');

INSERT INTO `upms_permission` VALUES ('517', '6', '201', '数据点分组', '3', 'eam:dataGroup:read', '/manage/{equipmentId}/dataGroup/index', 'zmdi zmdi-close', '1', '1489820207607', '208');


INSERT INTO `upms_permission` VALUES ('600', '6', '0', '维修计划管理', '1', null, null, 'zmdi zmdi-collection-text', 1, 1, 600);
INSERT INTO `upms_permission` VALUES ('610', '6', '600', '维修计划管理', '2', 'eam:maintainPlan:read', '/manage/maintainPlan/index', NULL, 1, 1, 610);
INSERT INTO `upms_permission` VALUES ('611', '6', '610', '新增维修计划', '3', 'eam:maintainPlan:create', '/manage/maintainPlan/create', NULL, 1, 1, 611);
INSERT INTO `upms_permission` VALUES ('612', '6', '610', '编辑维修计划', '3', 'eam:maintainPlan:update', '/manage/maintainPlan/update', NULL, 1, 1, 612);
INSERT INTO `upms_permission` VALUES ('613', '6', '610', '删除维修计划', '3', 'eam:maintainPlan:delete', '/manage/maintainPlan/delete', NULL, 1, 1, 613);

INSERT INTO `upms_permission` VALUES (700,6,0,'基础数据设置',1,NULL,NULL,'zmdi zmdi-collection-text',1,100,700);
INSERT INTO `upms_permission` VALUES ('710', '6', '700', '数据字典', '2', 'eam:codeValue:read', '/manage/codeValue/index', NULL, 1, 1, 710);
INSERT INTO `upms_permission` VALUES ('711', '6', '710', '新增数据字典', '3', 'eam:codeValue:create', '/manage/codeValue/create', 'zmdi zmdi-plus', 1, 1, 711);
INSERT INTO `upms_permission` VALUES ('712', '6', '710', '编辑数据字典', '3', 'eam:codeValue:update', '/manage/codeValue/update', 'zmdi zmdi-edit', 1, 1, 712);
INSERT INTO `upms_permission` VALUES ('713', '6', '710', '删除数据字典', '3', 'eam:codeValue:delete', '/manage/codeValue/delete', 'zmdi zmdi-close', 1, 1, 713);

INSERT INTO `upms_permission` VALUES ('714', '6', '700', '文件模板管理', '2', 'eam:fileTemplate:read', '/manage/fileTemplate/index', NULL, 1, 1, 714);
INSERT INTO `upms_permission` VALUES ('715', '6', '714', '新增文件模板', '3', 'eam:fileTemplate:create', '/manage/fileTemplate/create', 'zmdi zmdi-plus', 1, 1, 715);
INSERT INTO `upms_permission` VALUES ('716', '6', '714', '编辑文件模板', '3', 'eam:fileTemplate:update', '/manage/fileTemplate/update', 'zmdi zmdi-edit', 1, 1, 716);
INSERT INTO `upms_permission` VALUES ('717', '6', '714', '删除文件模板', '3', 'eam:fileTemplate:delete', '/manage/fileTemplate/delete', 'zmdi zmdi-close', 1, 1, 717);



TRUNCATE TABLE `eam_ticket_type`;
INSERT INTO `eam_ticket_type` VALUES (1, '报警工单',1,now(),1,now(),0,1),(2, '其它工单',1,now(),1,now(),0,1),(3,'手工工单',1,now(),1,now(),0,1);

# end prepare ticket menu item

TRUNCATE TABLE `eam_protocol`;
INSERT INTO `eam_protocol` VALUES (4, '巨控', 'www.yunplc.com', 7080);

