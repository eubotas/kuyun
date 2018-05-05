ALTER TABLE upms_role ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_log ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_organization ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_permission ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_system ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_user ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_organization_role ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_role_permission ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_user_company ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_user_organization ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_user_permission ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;
ALTER TABLE upms_user_role ADD COLUMN delete_flag  tinyint(1) NULL DEFAULT 0;

ALTER TABLE upms_role ADD COLUMN company_id  int(11) NULL DEFAULT NULL ;
ALTER TABLE eam_alarm ADD COLUMN is_create_ticket     boolean;

ALTER TABLE upms_company ADD COLUMN admin_name      varchar(20) DEFAULT NULL;
ALTER TABLE upms_company ADD COLUMN admin_password  varchar(20) DEFAULT NULL;


delete from upms_company where name in ('总部', '河北分部', '河南分部', '湖北分部', '湖南分部');

truncate upms_system;
INSERT INTO upms_system VALUES ('1', 'zmdi zmdi-shield-security', '/resources/kuyun-admin/images/kuyun-upms.png', '#29A176', 'http://upms.kuyun.cn:1111', '1', 'kuyun-upms-server', '权限管理系统', '用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）', '1', '1', 0);

truncate upms_permission;
INSERT INTO upms_permission VALUES ('1', '1', '0', '系统组织管理', '1', '', '', 'zmdi zmdi-accounts-list', '1', '1', '1',0);
INSERT INTO upms_permission VALUES ('2', '1', '1', '系统管理', '2', 'upms:system:read', '/manage/system/index', '', '1', '2', '2',0);
INSERT INTO upms_permission VALUES ('3', '1', '1', '公司管理', '2', 'upms:company:read', '/manage/company/index', '', '1', '2', '2',0);
INSERT INTO upms_permission VALUES ('4', '1', '1', '组织管理', '2', 'upms:organization:read', '/manage/organization/index', '', '1', '3', '3',0);

INSERT INTO upms_permission VALUES ('5', '1', '0', '角色用户管理', '1', '', '', 'zmdi zmdi-accounts', '1', '4', '4',0);
INSERT INTO upms_permission VALUES ('6', '1', '5', '角色管理', '2', 'upms:role:read', '/manage/role/index', '', '1', '6', '6',0);
INSERT INTO upms_permission VALUES ('7', '1', '5', '用户管理', '2', 'upms:user:read', '/manage/user/index', '', '1', '5', '5',0);

INSERT INTO upms_permission VALUES ('8', '1', '0', '权限资源管理', '1', '', '', 'zmdi zmdi-lock-outline', '1', '7', '7',0);
INSERT INTO upms_permission VALUES ('9', '1', '8', '权限管理', '2', 'upms:permission:read', '/manage/permission/index', null, '1', '39', '39',0);

INSERT INTO upms_permission VALUES ('10', '1', '0', '其他数据管理', '1', '', '', 'zmdi zmdi-more', '1', '12', '12',0);
INSERT INTO upms_permission VALUES ('11', '1', '9', '会话管理', '2', 'upms:session:read', '/manage/session/index', '', '1', '14', '14',0);
INSERT INTO upms_permission VALUES ('12', '1', '9', '日志记录', '2', 'upms:log:read', '/manage/log/index', '', '1', '15', '15',0);

INSERT INTO upms_permission VALUES ('13', '1', '2', '新增系统', '3', 'upms:system:create', '/manage/system/create', 'zmdi zmdi-plus', '1', '24', '24',0);
INSERT INTO upms_permission VALUES ('14', '1', '2', '编辑系统', '3', 'upms:system:update', '/manage/system/update', 'zmdi zmdi-edit', '1', '25', '25',0);
INSERT INTO upms_permission VALUES ('15', '1', '2', '删除系统', '3', 'upms:system:delete', '/manage/system/delete', 'zmdi zmdi-close', '1', '26', '26',0);

INSERT INTO upms_permission VALUES ('16', '1', '3', '新增公司', '3', 'upms:company:create', '/manage/company/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404',0);
INSERT INTO upms_permission VALUES ('17', '1', '3', '编辑公司', '3', 'upms:company:update', '/manage/company/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269',0);
INSERT INTO upms_permission VALUES ('18', '1', '3', '删除公司', '3', 'upms:company:delete', '/manage/company/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607',0);

INSERT INTO upms_permission VALUES ('19', '1', '4', '新增组织', '3', 'upms:organization:create', '/manage/organization/create', 'zmdi zmdi-plus', '1', '27', '27',0);
INSERT INTO upms_permission VALUES ('20', '1', '4', '编辑组织', '3', 'upms:organization:update', '/manage/organization/update', 'zmdi zmdi-edit', '1', '28', '28',0);
INSERT INTO upms_permission VALUES ('21', '1', '4', '删除组织', '3', 'upms:organization:delete', '/manage/organization/delete', 'zmdi zmdi-close', '1', '29', '29',0);

INSERT INTO upms_permission VALUES ('22', '1', '7', '新增用户', '3', 'upms:user:create', '/manage/user/create', 'zmdi zmdi-plus', '1', '30', '30',0);
INSERT INTO upms_permission VALUES ('23', '1', '7', '编辑用户', '3', 'upms:user:update', '/manage/user/update', 'zmdi zmdi-edit', '1', '31', '31',0);
INSERT INTO upms_permission VALUES ('24', '1', '7', '删除用户', '3', 'upms:user:delete', '/manage/user/delete', 'zmdi zmdi-close', '1', '32', '32',0);
INSERT INTO upms_permission VALUES ('25', '1', '7', '用户组织', '3', 'upms:user:organization', '/manage/user/organization', 'zmdi zmdi-accounts-list', '1', '1488120011165', '1488120011165',0);
INSERT INTO upms_permission VALUES ('26', '1', '7', '用户角色', '3', 'upms:user:role', '/manage/user/role', 'zmdi zmdi-accounts', '1', '1488120554175', '1488120554175',0);
INSERT INTO upms_permission VALUES ('27', '1', '7', '用户权限', '3', 'upms:user:permission', '/manage/user/permission', 'zmdi zmdi-key', '1', '1488092013302', '1488092013302',0);

INSERT INTO upms_permission VALUES ('28', '1', '6', '新增角色', '3', 'upms:role:create', '/manage/role/create', 'zmdi zmdi-plus', '1', '33', '33',0);
INSERT INTO upms_permission VALUES ('29', '1', '6', '编辑角色', '3', 'upms:role:update', '/manage/role/update', 'zmdi zmdi-edit', '1', '34', '34',0);
INSERT INTO upms_permission VALUES ('30', '1', '6', '删除角色', '3', 'upms:role:delete', '/manage/role/delete', 'zmdi zmdi-close', '1', '35', '35',0);
INSERT INTO upms_permission VALUES ('31', '1', '6', '角色权限', '3', 'upms:role:permission', '/manage/role/permission', 'zmdi zmdi-key', '1', '1488091928257', '1488091928257',0);

INSERT INTO upms_permission VALUES ('32', '1', '9', '新增权限', '3', 'upms:permission:create', '/manage/permission/create', 'zmdi zmdi-plus', '1', '36', '36',0);
INSERT INTO upms_permission VALUES ('33', '1', '9', '编辑权限', '3', 'upms:permission:update', '/manage/permission/update', 'zmdi zmdi-edit', '1', '37', '37',0);
INSERT INTO upms_permission VALUES ('34', '1', '9', '删除权限', '3', 'upms:permission:delete', '/manage/permission/delete', 'zmdi zmdi-close', '1', '38', '38',0);

INSERT INTO upms_permission VALUES ('35', '1', '11', '强制退出', '3', 'upms:session:forceout', '/manage/session/forceout', 'zmdi zmdi-run', '1', '1488379514715', '1488379514715',0);


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

-- ----------------------------
-- Table structure for eam_ticket
-- ----------------------------
DROP TABLE IF EXISTS eam_ticket;
CREATE TABLE eam_ticket (
  ticket_id int(11) NOT NULL AUTO_INCREMENT,
  ticket_type_id int(11) DEFAULT NULL,
  ticket_number varchar(32),
  equipment_category_id int(11) DEFAULT NULL,
  equipment_id varchar(32) DEFAULT NULL,
  description varchar(200) DEFAULT NULL COMMENT '描述',
  voice_path varchar(2000) DEFAULT NULL COMMENT '语音',
  image_path varchar(2000) DEFAULT NULL COMMENT '上传图片',
  priority varchar(10) DEFAULT NULL COMMENT '优先级（一般，紧急, 非常紧急）',
  executor_id int(11) DEFAULT NULL COMMENT '处理人',
  status varchar(10) DEFAULT NULL COMMENT '状态（待派工, 待维修, 维修中, 待评价, 评价完成）',
  end_date datetime DEFAULT NULL,
  create_user_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_user_id int(11) DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  delete_flag tinyint(1) DEFAULT NULL,
  company_id int(11) DEFAULT NULL,
  PRIMARY KEY (ticket_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for eam_ticket_appointed_record
-- ----------------------------
DROP TABLE IF EXISTS eam_ticket_appointed_record;
CREATE TABLE eam_ticket_appointed_record (
  id int(11) NOT NULL AUTO_INCREMENT,
  ticket_id int(11) DEFAULT NULL,
  order_taker_id int(11) DEFAULT NULL COMMENT '接单人',
  appoint_commont varchar(100) DEFAULT NULL COMMENT '委派备注，可为空',
  action         varchar(10) DEFAULT NULL COMMENT '接单/拒单',
  reject_commont varchar(100) DEFAULT NULL COMMENT '拒单原因，可为空',
  create_user_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  delete_flag tinyint(1) DEFAULT NULL,
  company_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for eam_ticket_assessment
-- ----------------------------
DROP TABLE IF EXISTS eam_ticket_assessment;
CREATE TABLE eam_ticket_assessment (
  id int(11) NOT NULL AUTO_INCREMENT,
  ticket_id int(11) DEFAULT NULL,
  assessment_user_id int(11) DEFAULT NULL COMMENT '评价人ID',
  assessment_level int(11) DEFAULT NULL COMMENT '评价星级',
  description varchar(2000) DEFAULT NULL COMMENT '评价描述',
  create_user_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_user_id int(11) DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  delete_flag tinyint(1) DEFAULT NULL,
  company_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for eam_ticket_assessment_tag
-- ----------------------------
DROP TABLE IF EXISTS eam_ticket_assessment_tag;
CREATE TABLE eam_ticket_assessment_tag (
  id int(11) NOT NULL AUTO_INCREMENT,
  ticket_id int(11) DEFAULT NULL,
  assessment_id int(11) DEFAULT NULL,
  tag_id int(11) DEFAULT NULL COMMENT '评价标签ID，from eam_tag',
  create_user_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_user_id int(11) DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  delete_flag tinyint(1) DEFAULT NULL,
  company_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for eam_ticket_record
-- ----------------------------
DROP TABLE IF EXISTS eam_ticket_record;
CREATE TABLE eam_ticket_record (
  id int(11) NOT NULL AUTO_INCREMENT,
  ticket_id int(11) DEFAULT NULL,
  step varchar(20) DEFAULT NULL COMMENT '处理步骤',
  comments varchar(200) DEFAULT NULL COMMENT '处理内容',
  create_user_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_user_id int(11) DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  delete_flag tinyint(1) DEFAULT NULL,
  company_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for eam_ticket_tag
-- ----------------------------
DROP TABLE IF EXISTS eam_ticket_tag;
CREATE TABLE eam_ticket_tag (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(30) DEFAULT NULL COMMENT '标签名',
  create_user_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_user_id int(11) DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  delete_flag tinyint(1) DEFAULT NULL,
  company_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for eam_ticket_type
-- ----------------------------
DROP TABLE IF EXISTS eam_ticket_type;
CREATE TABLE eam_ticket_type (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(30) DEFAULT NULL,
  create_user_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_user_id int(11) DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  delete_flag tinyint(1) DEFAULT NULL,
  company_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS eam_maintain_plan;
CREATE TABLE eam_maintain_plan (
  plan_id int(11) NOT NULL AUTO_INCREMENT,
  equipment_category_id int(11) DEFAULT NULL,
  equipment_id varchar(32) NOT NULL,
  work_content varchar(2000) not NULL COMMENT '维保内容',
  next_maintain_date datetime COMMENT '由job产生ticket时修改',
  maintain_frequency_unit varchar(32) NOT NULL COMMENT '年/月/天',
  maintain_frequency_quantity int NOT NULL COMMENT '1年/3月/10天',
  maintain_type varchar(32) NULL COMMENT '年检/常规',
  remind_time          int COMMENT '到维修期提前提醒时间(天)',
  create_user_id       int,
  create_time          datetime,
  update_user_id       int,
  update_time          datetime,
  delete_flag          boolean,
  company_id           int,
  PRIMARY KEY (plan_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eam_maintain_user;
CREATE TABLE eam_maintain_user (
  id int(11) NOT NULL AUTO_INCREMENT,
  plan_id            int(11),
  user_id             int(11),
  create_user_id       int,
  create_time          datetime,
  update_user_id       int,
  update_time          datetime,
  delete_flag          boolean,
  company_id      int,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eam_maintain_ticket;
CREATE TABLE eam_maintain_ticket (
  id int(11) NOT NULL AUTO_INCREMENT,
  plan_id int(11) NOT NULL,
  ticket_id int(11) NOT NULL,
  create_user_id       int,
  create_time          datetime,
  update_user_id       int,
  update_time          datetime,
  delete_flag          boolean,
  company_id      int,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 点击read后不弹出
DROP TABLE IF EXISTS eam_alert_message;
CREATE TABLE eam_alert_message (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id             int(11),
  message_title       varchar(200) NOT NULL,
  content             varchar(2000) not NULL COMMENT '消息内容',
  read_flag           boolean,
  read_time           datetime,
  alert_start_date    datetime COMMENT '消息发送开始日期',
  alert_end_date      datetime COMMENT '消息发送结束日期, 超过结束日期后不提示',
  create_user_id      int,
  create_time         datetime,
  update_user_id      int,
  update_time         datetime,
  delete_flag         boolean,
  company_id          int,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS eam_code_value;
CREATE TABLE eam_code_value (
  id int(11) NOT NULL AUTO_INCREMENT,
  category    varchar(30) NOT NULL,
  code_value  varchar(30) NOT NULL,
  code_name   varchar(100) NOT NULL,
  description varchar(2000) DEFAULT NULL,
  create_user_id       int,
  create_time          datetime,
  update_user_id       int,
  update_time          datetime,
  delete_flag          boolean,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS eam_grm;
create table eam_grm
(
   grm_id               varchar(32),
   grm                  varchar(50) comment '巨控设备ID',
   grm_password         varchar(50) comment '巨控设备密码',
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (grm_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eam_grm_equipment;
create table eam_grm_equipment
(
   id                   int not null auto_increment,
   grm_id               varchar(32),
   equipment_id         varchar(32),
   create_user_id       int,
   create_time          datetime,
   update_user_id       int,
   update_time          datetime,
   delete_flag          boolean,
   company_id      int,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eam_code_value;
CREATE TABLE eam_code_value (
  id int(11) NOT NULL AUTO_INCREMENT,
  category    varchar(30) NOT NULL,
  code_value  varchar(30) NOT NULL,
  code_name   varchar(100) NOT NULL,
  description varchar(2000) DEFAULT NULL,
  create_user_id       int,
  create_time          datetime,
  update_user_id       int,
  update_time          datetime,
  delete_flag          boolean,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

truncate eam_code_value;
INSERT INTO eam_code_value (id,category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES (1,'MAINTAIN_PLAN_UNIT','YEAR','年','',1, now(),1,now(),0);
INSERT INTO eam_code_value (id,category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES (2,'MAINTAIN_PLAN_UNIT','MONTH','月','',1,now(),1,now(),0);
INSERT INTO eam_code_value (id,category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES (3,'MAINTAIN_PLAN_UNIT','WEEK','周','',1,now(),1,now(),0);
INSERT INTO eam_code_value (id,category,code_value,code_name,description,create_user_id,create_time,update_user_id,update_time,delete_flag) VALUES (4,'MAINTAIN_PLAN_UNIT','DAY','天','',1,now(),1,now(),0);


drop FUNCTION if exists getParentList;

delimiter //

CREATE FUNCTION getParentList(rootId INT)
RETURNS varchar(1000) 

BEGIN
  DECLARE sTemp VARCHAR(1000);
  DECLARE sTempPar VARCHAR(1000); 
  SET sTemp = ''; 
  SET sTempPar =rootId; 
  
  #循环递归
  WHILE sTempPar is not null DO 
    #判断是否是第一个，不加的话第一个会为空
    IF sTemp != '' THEN
      SET sTemp = concat(sTemp,',',sTempPar);
    ELSE
      SET sTemp = sTempPar;
    END IF;

    SET sTemp = concat(sTemp,',',sTempPar); 
    SELECT group_concat(pid) INTO sTempPar FROM upms_organization where pid<>organization_id and FIND_IN_SET(organization_id,sTempPar)>0; 
  END WHILE; 
  
RETURN sTemp; 
END
//

delimiter ;

#根据传入id查询所有子节点的id

drop FUNCTION if exists getChildList;
delimiter //
CREATE FUNCTION getChildList(rootId INT)
RETURNS varchar(1000) 

BEGIN
  DECLARE sTemp VARCHAR(1000);
    DECLARE sTempChd VARCHAR(1000);

    SET sTemp = '$';
    SET sTempChd =cast(rootId as CHAR);

    WHILE sTempChd is not null DO
      SET sTemp = concat(sTemp,',',sTempChd);
        SELECT group_concat(organization_id) INTO sTempChd FROM  upms_organization where FIND_IN_SET(pid,sTempChd)>0;
    END WHILE;
    RETURN sTemp; 
END
//

#resotre default delimiter
delimiter ;