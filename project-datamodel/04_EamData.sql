
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

#系统设置
delete from upms_system where system_id = 2;
INSERT INTO upms_system VALUES ('2', 'zmdi zmdi-cloud', '/resources/kuyun-admin/images/kuyun-oss.png', '#0B8DE5', 'http://eam.kuyun.cn:9999', '1', 'kuyun-eam-admin', '设备管理系统', '设备管理系统', '2', '2', 0);

delete from upms_permission where permission_id >= 200 and permission_id < 2000;

INSERT INTO upms_permission VALUES ('200', '2', '0', '设备及模型管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '200', '200',0);
INSERT INTO upms_permission VALUES ('201', '2', '200', '设备模型管理', '2', 'eam:equipmentModel:read',   '/manage/equipment/model/index', null, '1', '201', '201',0);
INSERT INTO upms_permission VALUES ('202', '2', '201', '新增设备模型', '3', 'eam:equipmentModel:create', '/manage/equipment/model/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('203', '2', '201', '编辑设备模型', '3', 'eam:equipmentModel:update', '/manage/equipment/model/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('204', '2', '201', '删除设备模型', '3', 'eam:equipmentModel:delete', '/manage/equipment/model/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);



INSERT INTO upms_permission VALUES ('205', '2', '200', '设备信息管理', '2', 'eam:equipment:read',   '/manage/equipment/index', null, '1', '205', '205',0);
INSERT INTO upms_permission VALUES ('206', '2', '205', '新增设备', '3', 'eam:equipment:create', '/manage/equipment/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('207', '2', '205', '编辑设备', '3', 'eam:equipment:update', '/manage/equipment/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('208', '2', '205', '删除设备', '3', 'eam:equipment:delete', '/manage/equipment/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);
INSERT INTO upms_permission VALUES ('209', '2', '205', '设备接入', '3', 'eam:equipment:update', '/manage/equipment/connect', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);

INSERT INTO upms_permission VALUES ('210', '2', '0', '报警设置', '1', null, null, 'zmdi zmdi-collection-text', '1', '210', '210',0);
INSERT INTO upms_permission VALUES ('211', '2', '210', '报警管理', '2', 'eam:alarm:read',   '/manage/alarm/index', null, '1', '211', '211',0);
INSERT INTO upms_permission VALUES ('212', '2', '211', '新增报警', '3', 'eam:alarm:create', '/manage/alarm/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('213', '2', '211', '编辑报警', '3', 'eam:alarm:update', '/manage/alarm/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('214', '2', '211', '删除报警', '3', 'eam:alarm:delete', '/manage/alarm/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('215', '2', '0', '数据采集', '1', null, null, 'zmdi zmdi-collection-text', '1', '210', '210',0);
INSERT INTO upms_permission VALUES ('216', '2', '215', '数据采集', '2', 'eam:equipment:update',   '/manage/equipment/collect/index', null, '1', '216', '216',0);
INSERT INTO upms_permission VALUES ('217', '2', '216', '启动', '3', 'eam:equipment:update', '/manage/equipment/collect/start', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('218', '2', '216', '停止', '3', 'eam:equipment:update', '/manage/equipment/collect/stop', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);


INSERT INTO upms_permission VALUES ('220', '2', '0', ' 仓储管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '220', '220',0);
INSERT INTO upms_permission VALUES ('221', '2', '220', '仓库管理', '2', 'eam:warehouse:read',   '/manage/warehouse/index', null, '1', '221', '221',0);
INSERT INTO upms_permission VALUES ('222', '2', '221', '新增仓库', '3', 'eam:warehouse:create', '/manage/warehouse/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('223', '2', '221', '编辑仓库', '3', 'eam:warehouse:update', '/manage/warehouse/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('224', '2', '221', '删除仓库', '3', 'eam:warehouse:delete', '/manage/warehouse/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('225', '2', '220', '仓位管理', '2', 'eam:location:read',   '/manage/location/index', null, '1', '225', '225',0);
INSERT INTO upms_permission VALUES ('226', '2', '225', '新增仓位', '3', 'eam:location:create', '/manage/location/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('227', '2', '225', '编辑仓位', '3', 'eam:location:update', '/manage/location/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('228', '2', '225', '删除仓位', '3', 'eam:location:delete', '/manage/location/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('229', '2', '220', '库存管理', '2', 'eam:inventory:read',   '/manage/inventory/index', null, '1', '229', '229',0);
INSERT INTO upms_permission VALUES ('230', '2', '229', '新增库存明细', '3', 'eam:inventory:create', '/manage/inventory/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('231', '2', '229', '编辑库存明细', '3', 'eam:inventory:update', '/manage/inventory/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('232', '2', '229', '删除库存明细', '3', 'eam:inventory:delete', '/manage/inventory/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('240', '2', '0', '备品备件', '1', null, null, 'zmdi zmdi-collection-text', '1', '240', '240',0);
INSERT INTO upms_permission VALUES ('241', '2', '240', '配件类别管理', '2', 'eam:partCategory:read',   '/manage/part/category/index', null, '1', '241', '241',0);
INSERT INTO upms_permission VALUES ('242', '2', '241', '新增配件类别', '3', 'eam:partCategory:create', '/manage/part/category/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('243', '2', '241', '编辑配件类别', '3', 'eam:partCategory:update', '/manage/part/category/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('244', '2', '241', '删除配件类别', '3', 'eam:partCategory:delete', '/manage/part/category/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('245', '2', '240', '配件管理', '2', 'eam:part:read',   '/manage/part/index', null, '1', '245', '245',0);
INSERT INTO upms_permission VALUES ('246', '2', '245', '新增配件', '3', 'eam:part:create', '/manage/part/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('247', '2', '245', '编辑配件', '3', 'eam:part:update', '/manage/part/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('248', '2', '245', '删除配件', '3', 'eam:part:delete', '/manage/part/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('250', '2', '200', '设备模型参数', '2', 'eam:equipmentModelProperty:read',   '/manage/equipmentmodel/property/index', null, '1', '202', '202',0);
INSERT INTO upms_permission VALUES ('251', '2', '250', '新增设备模型参数', '3', 'eam:equipmentModelProperty:create', '/manage/equipment/model/property/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('252', '2', '250', '编辑设备模型参数', '3', 'eam:equipmentModelProperty:update', '/manage/equipment/model/property/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('253', '2', '250', '删除设备模型参数', '3', 'eam:equipmentModelProperty:delete', '/manage/equipment/model/property/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);


INSERT INTO upms_permission VALUES ('219', '2', '0', ' 客户管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '219', '219',0);
INSERT INTO upms_permission VALUES ('270', '2', '219', '客户管理', '2', 'eam:company:read',   '/manage/company/index', null, '1', '270', '270',0);
INSERT INTO upms_permission VALUES ('271', '2', '270', '新增客户', '3', 'eam:company:create', '/manage/company/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('272', '2', '270', '编辑客户', '3', 'eam:company:update', '/manage/company/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('273', '2', '270', '删除客户', '3', 'eam:company:delete', '/manage/company/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('280', '2', '205', '数据写入', '3', 'eam:equipmentSensor:write', '/manage/equipment/sensor/', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);


INSERT INTO upms_permission VALUES ('290', '2', '0', '网关管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '205', '205',0);
INSERT INTO upms_permission VALUES ('291', '2', '290', 'DTU', '2', 'eam:dtu:read',   '/manage/dtu/index', null, '1', '291', '291',0);
INSERT INTO upms_permission VALUES ('292', '2', '291', '新增DTU', '3', 'eam:dtu:create', '/manage/dtu/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('293', '2', '291', '编辑DTU', '3', 'eam:dtu:update', '/manage/dtu/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('294', '2', '291', '删除DTU', '3', 'eam:dtu:delete', '/manage/dtu/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('295', '2', '200', '设备类别', '2', 'eam:equipmentCategory:read',   '/manage/equipment/category/index', null, '1', '202', '202',0);
INSERT INTO upms_permission VALUES ('296', '2', '295', '新增设备类别', '3', 'eam:equipmentCategory:create', '/manage/equipment/category/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('297', '2', '295', '编辑设备类别', '3', 'eam:equipmentCategory:update', '/manage/equipment/category/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('298', '2', '295', '删除设备类别', '3', 'eam:equipmentCategory:delete', '/manage/equipment/category/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

delete from upms_permission where uri like '/manage/ticket%';

INSERT INTO upms_permission VALUES ('310', '6', '300', '工单类型', '2', 'eam:ticketType:read', '/manage/ticket/type/index', NULL, 1, 221, 410, 0);
INSERT INTO upms_permission VALUES ('311', '6', '310', '新增类型', '3', 'eam:ticketType:create', '/manage/ticket/type/create', 'zmdi zmdi-plus', 1, 1489820150404, 311,0);
INSERT INTO upms_permission VALUES ('312', '6', '310', '编辑类型', '3', 'eam:ticketType:update', '/manage/ticket/type/update', 'zmdi zmdi-edit', 1, 1489820178269, 312,0);
INSERT INTO upms_permission VALUES ('313', '6', '310', '删除类型', '3', 'eam:ticketType:delete', '/manage/ticket/type/delete', 'zmdi zmdi-close', 1, 1489820207607, 313,0);
INSERT INTO upms_permission VALUES ('320', '6', '300', '我的未处理工单', '2', 'eam:ticket:read', '/manage/ticket/index?category=myOpen', NULL, 1, 225, 320,0);
INSERT INTO upms_permission VALUES ('330', '6', '300', '我的全部工单', '2', 'eam:ticket:read', '/manage/ticket/index?category=myAll', NULL, 1, 225, 330,0);
INSERT INTO upms_permission VALUES ('340', '6', '300', '未委派工单', '2', 'eam:ticket:read', '/manage/ticket/index?category=init', NULL, 1, 225, 340,0);
INSERT INTO upms_permission VALUES ('345', '6', '300', '工单统计', '2', 'eam:ticket:summary', '/manage/ticket/summary', NULL, 1, 225, 460,0);
INSERT INTO upms_permission VALUES ('350', '6', '300', '全部工单', '2', 'eam:ticket:read', '/manage/ticket/index?category=all', NULL, 1, 225, 350,0);
INSERT INTO upms_permission VALUES ('351', '6', '350', '新增工单', '3', 'eam:ticket:create', '/manage/ticket/create', 'zmdi zmdi-plus', 1, 1489820150404, 1489820150404,0);
INSERT INTO upms_permission VALUES ('352', '6', '350', '编辑工单', '3', 'eam:ticket:update', '/manage/ticket/update', 'zmdi zmdi-edit', 1, 1489820178269, 1489820178269,0);
INSERT INTO upms_permission VALUES ('353', '6', '350', '删除工单', '3', 'eam:ticket:delete', '/manage/ticket/delete', 'zmdi zmdi-close', 1, 1489820207607, 1489820207607,0);

INSERT INTO upms_permission VALUES ('440', '6', '300', '工单评价标签', '2', 'eam:ticketTag:read', '/manage/ticket/tag/index', NULL, 1, 1, 440,0);
INSERT INTO upms_permission VALUES ('441', '6', '440', '新增工单评价标签', '3', 'eam:ticketTag:create', '/manage/ticket/tag/create', 'zmdi zmdi-plus', 1, 1489820150404, 441,0);
INSERT INTO upms_permission VALUES ('442', '6', '440', '编辑工单评价标签', '3', 'eam:ticketTag:update', '/manage/ticket/tag/update', 'zmdi zmdi-edit', 1, 1489820178269, 442,0);
INSERT INTO upms_permission VALUES ('443', '6', '440', '删除工单评价标签', '3', 'eam:ticketTag:delete', '/manage/ticket/tag/delete', 'zmdi zmdi-close', 1, 1489820207607, 443,0);
INSERT INTO upms_permission VALUES ('445', '6', '350', '新增工单记录', '3', 'eam:ticketRecord:create', '/manage/ticket/{tid}/record/create', 'zmdi zmdi-plus', 1, 1489820150404, 445,0);
INSERT INTO upms_permission VALUES ('446', '6', '350', '编辑工单记录', '3', 'eam:ticketRecord:update', '/manage/ticket/{tid}/record/update', 'zmdi zmdi-edit', 1, 1489820178269, 444,0);
INSERT INTO upms_permission VALUES ('447', '6', '350', '工单记录', '3', 'eam:ticketRecord:read', '/manage/ticket/{tid}/record/index', 'zmdi zmdi-close', 1, 1489820207607, 447,0);
INSERT INTO upms_permission VALUES ('451', '6', '350', '新增委派记录', '3', 'eam:ticketAppointedRecord:create', '/manage/ticket/{ticketId}/appoint/create', 'zmdi zmdi-plus', 1, 1489820150404, 451,0);
INSERT INTO upms_permission VALUES ('452', '6', '350', '编辑委派记录', '3', 'eam:ticketAppointedRecord:update', '/manage/ticket/{ticketId}/appoint/update', 'zmdi zmdi-edit', 1, 1489820178269, 452,0);
INSERT INTO upms_permission VALUES ('453', '6', '350', '删除委派记录', '3', 'eam:ticketAppointedRecord:delete', '/manage/ticket/{ticketId}/appoint/delete', 'zmdi zmdi-close', 1, 1489820207607, 453,0);
INSERT INTO upms_permission VALUES ('454', '6', '350', '委派记录', '3', 'eam:ticketAppointedRecord:read', '/manage/ticket/{ticketId}/appoint/index', NULL, 1, 1, 454,0);
INSERT INTO upms_permission VALUES ('461', '6', '350', '新增工单评价', '3', 'eam:ticketAssessment:create', '/manage/ticket/{ticketId}/assessment/create', 'zmdi zmdi-plus', 1, 1489820150404, 461,0);
INSERT INTO upms_permission VALUES ('462', '6', '350', '编辑工单评价', '3', 'eam:ticketAssessment:update', '/manage/ticket/{ticketId}/assessment/update', 'zmdi zmdi-edit', 1, 1489820178269, 462,0);
INSERT INTO upms_permission VALUES ('463', '6', '350', '删除工单评价', '3', 'eam:ticketAssessment:delete', '/manage/ticket/{ticketId}/assessment/delete', 'zmdi zmdi-close', 1, 1489820207607, 463,0);
INSERT INTO upms_permission VALUES ('464', '6', '350', '工单评价', '3', 'eam:ticketAssessment:read', '/manage/ticket/{ticketId}/assessment/index', '', 1, 1, 464,0);


INSERT INTO upms_permission VALUES ('400', '2', '0', '知识管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '400', '400',0);
INSERT INTO upms_permission VALUES ('401', '2', '400', '知识搜索', '2', 'eam:knowledge:read',   '/manage/knowledge/index', null, '1', '270', '270',0);
INSERT INTO upms_permission VALUES ('402', '2', '400', '培训视频', '2', 'eam:trainingVideo:read',   '/manage/knowledge/training/video/index', null, '1', '270', '270',0);
INSERT INTO upms_permission VALUES ('403', '2', '402', '新增培训视频', '3', 'eam:trainingVideo:create', '/manage/knowledge/training/video/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('404', '2', '402', '编辑培训视频', '3', 'eam:trainingVideo:update', '/manage/knowledge/training/video/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('405', '2', '402', '删除培训视频', '3', 'eam:trainingVideo:delete', '/manage/knowledge/training/video/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('406', '2', '400', '培训文档', '2', 'eam:trainingDoc:read',   '/manage/knowledge/training/doc/index', null, '1', '270', '270',0);
INSERT INTO upms_permission VALUES ('407', '2', '406', '新增培训文档', '3', 'eam:trainingDoc:create', '/manage/knowledge/training/doc/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('408', '2', '406', '编辑培训文档', '3', 'eam:trainingDoc:update', '/manage/knowledge/training/doc/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('409', '2', '406', '删除培训文档', '3', 'eam:trainingDoc:delete', '/manage/knowledge/training/doc/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('410', '2', '400', '保养知识', '2', 'eam:maintainKnowledge:read',   '/manage/knowledge/maintain/index', null, '1', '270', '270',0);
INSERT INTO upms_permission VALUES ('411', '2', '410', '新增保养知识', '3', 'eam:maintainKnowledge:create', '/manage/knowledge/maintain/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('412', '2', '410', '编辑保养知识', '3', 'eam:maintainKnowledge:update', '/manage/knowledge/maintain/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('413', '2', '410', '删除保养知识', '3', 'eam:maintainKnowledge:delete', '/manage/knowledge/maintain/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('414', '2', '400', '维修知识', '2', 'eam:repairKnowledge:read',   '/manage/knowledge/repair/index', null, '1', '270', '270',0);
INSERT INTO upms_permission VALUES ('415', '2', '414', '新增维修知识', '3', 'eam:repairKnowledge:create', '/manage/knowledge/repair/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('416', '2', '414', '编辑维修知识', '3', 'eam:repairKnowledge:update', '/manage/knowledge/repair/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('417', '2', '414', '删除维修知识', '3', 'eam:repairKnowledge:delete', '/manage/knowledge/repair/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('418', '2', '400', '设备手册', '2', 'eam:equipmentManual:read',   '/manage/knowledge/manual/index', null, '1', '270', '270',0);
INSERT INTO upms_permission VALUES ('419', '2', '418', '新增设备手册', '3', 'eam:equipmentManual:create', '/manage/knowledge/manual/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('420', '2', '418', '编辑设备手册', '3', 'eam:equipmentManual:update', '/manage/knowledge/manual/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('421', '2', '418', '删除设备手册', '3', 'eam:equipmentManual:delete', '/manage/knowledge/manual/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('430', '2', '290', '智库网关', '2', 'eam:grm:read',   '/manage/grm/index', null, '1', '450', '450',0);
INSERT INTO upms_permission VALUES ('431', '2', '430', '新增智库网关', '3', 'eam:grm:create', '/manage/grm/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('432', '2', '430', '编辑智库网关', '3', 'eam:grm:update', '/manage/grm/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('433', '2', '430', '删除智库网关', '3', 'eam:grm:delete', '/manage/grm/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('600', '2', '0', '维修计划管理', '1', null, null, 'zmdi zmdi-collection-text', 1, 1, 600,0);
INSERT INTO upms_permission VALUES ('610', '2', '600', '维修计划管理', '2', 'eam:maintainPlan:read', '/manage/maintainPlan/index', NULL, 1, 1, 610,0);
INSERT INTO upms_permission VALUES ('611', '2', '610', '新增维修计划', '3', 'eam:maintainPlan:create', '/manage/maintainPlan/create', NULL, 1, 1, 611,0);
INSERT INTO upms_permission VALUES ('612', '2', '610', '编辑维修计划', '3', 'eam:maintainPlan:update', '/manage/maintainPlan/update', NULL, 1, 1, 612,0);
INSERT INTO upms_permission VALUES ('613', '2', '610', '删除维修计划', '3', 'eam:maintainPlan:delete', '/manage/maintainPlan/delete', NULL, 1, 1, 613,0);

INSERT INTO upms_permission VALUES (700,6,0,'基础数据设置',1,NULL,NULL,'zmdi zmdi-collection-text',1,100,700,0);
INSERT INTO upms_permission VALUES ('710', '2', '700', '数据字典', '2', 'eam:codeValue:read', '/manage/codeValue/index', NULL, 1, 1, 710,0);
INSERT INTO upms_permission VALUES ('711', '2', '710', '新增数据字典', '3', 'eam:codeValue:create', '/manage/codeValue/create', 'zmdi zmdi-plus', 1, 1, 711,0);
INSERT INTO upms_permission VALUES ('712', '2', '710', '编辑数据字典', '3', 'eam:codeValue:update', '/manage/codeValue/update', 'zmdi zmdi-edit', 1, 1, 712,0);
INSERT INTO upms_permission VALUES ('713', '2', '710', '删除数据字典', '3', 'eam:codeValue:delete', '/manage/codeValue/delete', 'zmdi zmdi-close', 1, 1, 713,0);

TRUNCATE TABLE eam_code_value;
INSERT INTO eam_code_value (id,category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES (1,'MAINTAIN_PLAN_UNIT','YEAR','年','',1,'2018-01-30 09:53:16',1,'2018-01-30 09:53:16',0);
INSERT INTO eam_code_value (id,category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES (2,'MAINTAIN_PLAN_UNIT','MONTH','月','',1,'2018-01-30 09:53:39',1,'2018-01-30 09:53:39',0);
INSERT INTO eam_code_value (id,category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES (3,'MAINTAIN_PLAN_UNIT','WEEK','周','',1,'2018-01-30 09:54:03',1,'2018-01-30 09:54:03',0);
INSERT INTO eam_code_value (id,category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES (4,'MAINTAIN_PLAN_UNIT','DAY','天','',1,'2018-01-30 09:54:18',1,'2018-01-30 09:54:18',0);

TRUNCATE TABLE eam_ticket_type;
INSERT INTO eam_ticket_type VALUES
(1,'故障报修',1,now(),1,now(),0,1),
(2,'维保计划',1,now(),1,now(),0,1),
(3,'报警工单',1,now(),1,now(),0,1);


TRUNCATE TABLE eam_protocol;
INSERT INTO eam_protocol VALUES (1, 'Modbus RTU', 'mbrtu.coderise.cn', 8234);
#INSERT INTO eam_protocol VALUES (2, 'Modbus TCP', '118.89.140.11', 8233);
#INSERT INTO eam_protocol VALUES (3, 'MQTT', '118.89.140.11', 8232);
INSERT INTO eam_protocol VALUES (4, '智库网关', 'www.yunplc.com', 7080);

