INSERT INTO upms_company (company_id, parent_id, name, address, phone, www, fax, zip, create_time, delete_flag) VALUES ('1', null, '江苏新美星包装机械股份有限公司', '江苏省张家港经济技术开发区新泾东路8号', '0512-5869 1111', 'www.newamstar.com', '0512-5867 3920', '215618', now(),  0);


-- ----------------------------
-- Records of upms_organization
-- ----------------------------
INSERT INTO upms_organization (organization_id, pid, name, description, ctime, company_id, delete_flag) VALUES ('1', null, '维修部门', '维修部门', now(), 1, 0);
INSERT INTO upms_organization (organization_id, pid, name, description, ctime, company_id, delete_flag) VALUES ('2', null, '维保部门', '维保部门', now(), 1, 0);
INSERT INTO upms_organization (organization_id, pid, name, description, ctime, company_id, delete_flag) VALUES ('3', null, '报警部门', '报警部门', now(), 1, 0);

-- ----------------------------
-- Records of upms_permission
-- ----------------------------
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


-- ----------------------------
-- Records of upms_role
-- ----------------------------
INSERT INTO upms_role VALUES ('1', 1, 'super', '超级管理员', '拥有所有权限', '1', '1', 0);
INSERT INTO upms_role VALUES ('2', 1, 'ticketCreate', '工单提报', '拥有提报工单权限', '1', '1', 0);
INSERT INTO upms_role VALUES ('3', 1, 'ticketRepair', '工单维修', '拥有维修工单权限', '1', '1', 0);
INSERT INTO upms_role VALUES ('4', 1, 'ticketAppoint', '工单委派', '拥有委派工单权限', '1', '1', 0);
INSERT INTO upms_role VALUES ('5', 1, 'customer', '客户', '拥有客户权限', '1', '1', 0);
-- ----------------------------
-- Records of upms_role_permission
-- ----------------------------

-- For super Role
INSERT INTO upms_role_permission VALUES ('1', '1', '1', 0);
INSERT INTO upms_role_permission VALUES ('2', '1', '2', 0);
INSERT INTO upms_role_permission VALUES ('3', '1', '3', 0);
INSERT INTO upms_role_permission VALUES ('4', '1', '4', 0);
INSERT INTO upms_role_permission VALUES ('5', '1', '5', 0);
INSERT INTO upms_role_permission VALUES ('6', '1', '6', 0);
INSERT INTO upms_role_permission VALUES ('7', '1', '7', 0);
INSERT INTO upms_role_permission VALUES ('8', '1', '8', 0);
INSERT INTO upms_role_permission VALUES ('9', '1', '9', 0);
INSERT INTO upms_role_permission VALUES ('10', '1', '10', 0);
INSERT INTO upms_role_permission VALUES ('11', '1', '11', 0);
INSERT INTO upms_role_permission VALUES ('12', '1', '12', 0);
INSERT INTO upms_role_permission VALUES ('13', '1', '13', 0);
INSERT INTO upms_role_permission VALUES ('14', '1', '14', 0);
INSERT INTO upms_role_permission VALUES ('15', '1', '15', 0);
INSERT INTO upms_role_permission VALUES ('16', '1', '16', 0);
INSERT INTO upms_role_permission VALUES ('17', '1', '17', 0);
INSERT INTO upms_role_permission VALUES ('18', '1', '18', 0);
INSERT INTO upms_role_permission VALUES ('19', '1', '19', 0);
INSERT INTO upms_role_permission VALUES ('20', '1', '20', 0);
INSERT INTO upms_role_permission VALUES ('21', '1', '21', 0);
INSERT INTO upms_role_permission VALUES ('22', '1', '22', 0);
INSERT INTO upms_role_permission VALUES ('23', '1', '23', 0);
INSERT INTO upms_role_permission VALUES ('24', '1', '24', 0);
INSERT INTO upms_role_permission VALUES ('25', '1', '25', 0);
INSERT INTO upms_role_permission VALUES ('26', '1', '26', 0);
INSERT INTO upms_role_permission VALUES ('27', '1', '27', 0);
INSERT INTO upms_role_permission VALUES ('28', '1', '28', 0);
INSERT INTO upms_role_permission VALUES ('29', '1', '29', 0);
INSERT INTO upms_role_permission VALUES ('30', '1', '30', 0);
INSERT INTO upms_role_permission VALUES ('31', '1', '31', 0);
INSERT INTO upms_role_permission VALUES ('32', '1', '32', 0);
INSERT INTO upms_role_permission VALUES ('33', '1', '33', 0);
INSERT INTO upms_role_permission VALUES ('34', '1', '34', 0);
INSERT INTO upms_role_permission VALUES ('35', '1', '35', 0);

-- For Customer Role
INSERT INTO upms_role_permission VALUES ('36', '5', '1', 0);
INSERT INTO upms_role_permission VALUES ('37', '5', '3', 0);
INSERT INTO upms_role_permission VALUES ('38', '5', '4', 0);
INSERT INTO upms_role_permission VALUES ('39', '5', '5', 0);
INSERT INTO upms_role_permission VALUES ('40', '5', '6', 0);
INSERT INTO upms_role_permission VALUES ('41', '5', '7', 0);
INSERT INTO upms_role_permission VALUES ('42', '5', '27', 0);
INSERT INTO upms_role_permission VALUES ('43', '5', '28', 0);
INSERT INTO upms_role_permission VALUES ('44', '5', '29', 0);
INSERT INTO upms_role_permission VALUES ('45', '5', '30', 0);
INSERT INTO upms_role_permission VALUES ('46', '5', '31', 0);
INSERT INTO upms_role_permission VALUES ('47', '5', '32', 0);
INSERT INTO upms_role_permission VALUES ('48', '5', '33', 0);
INSERT INTO upms_role_permission VALUES ('49', '5', '34', 0);
INSERT INTO upms_role_permission VALUES ('50', '5', '35', 0);
INSERT INTO upms_role_permission VALUES ('51', '5', '39', 0);
INSERT INTO upms_role_permission VALUES ('52', '5', '46', 0);
INSERT INTO upms_role_permission VALUES ('53', '5', '48', 0);
INSERT INTO upms_role_permission VALUES ('54', '5', '50', 0);
INSERT INTO upms_role_permission VALUES ('55', '5', '51', 0);
INSERT INTO upms_role_permission VALUES ('56', '5', '201', 0);
INSERT INTO upms_role_permission VALUES ('57', '5', '203', 0);
INSERT INTO upms_role_permission VALUES ('58', '5', '205', 0);
INSERT INTO upms_role_permission VALUES ('59', '5', '206', 0);
INSERT INTO upms_role_permission VALUES ('60', '5', '207', 0);
INSERT INTO upms_role_permission VALUES ('61', '5', '210', 0);
INSERT INTO upms_role_permission VALUES ('62', '5', '211', 0);
INSERT INTO upms_role_permission VALUES ('63', '5', '212', 0);
INSERT INTO upms_role_permission VALUES ('64', '5', '213', 0);
INSERT INTO upms_role_permission VALUES ('65', '5', '214', 0);
INSERT INTO upms_role_permission VALUES ('66', '5', '217', 0);
INSERT INTO upms_role_permission VALUES ('67', '5', '218', 0);
INSERT INTO upms_role_permission VALUES ('68', '5', '240', 0);
INSERT INTO upms_role_permission VALUES ('69', '5', '241', 0);
INSERT INTO upms_role_permission VALUES ('70', '5', '245', 0);
INSERT INTO upms_role_permission VALUES ('71', '5', '270', 0);
INSERT INTO upms_role_permission VALUES ('72', '5', '271', 0);
INSERT INTO upms_role_permission VALUES ('73', '5', '272', 0);
INSERT INTO upms_role_permission VALUES ('74', '5', '273', 0);
INSERT INTO upms_role_permission VALUES ('75', '5', '310', 0);
INSERT INTO upms_role_permission VALUES ('76', '5', '311', 0);
INSERT INTO upms_role_permission VALUES ('77', '5', '312', 0);
INSERT INTO upms_role_permission VALUES ('78', '5', '313', 0);
INSERT INTO upms_role_permission VALUES ('79', '5', '320', 0);
INSERT INTO upms_role_permission VALUES ('80', '5', '330', 0);
INSERT INTO upms_role_permission VALUES ('81', '5', '340', 0);
INSERT INTO upms_role_permission VALUES ('82', '5', '345', 0);
INSERT INTO upms_role_permission VALUES ('83', '5', '350', 0);
INSERT INTO upms_role_permission VALUES ('84', '5', '351', 0);
INSERT INTO upms_role_permission VALUES ('85', '5', '352', 0);
INSERT INTO upms_role_permission VALUES ('86', '5', '353', 0);
INSERT INTO upms_role_permission VALUES ('87', '5', '400', 0);
INSERT INTO upms_role_permission VALUES ('88', '5', '401', 0);
INSERT INTO upms_role_permission VALUES ('89', '5', '402', 0);
INSERT INTO upms_role_permission VALUES ('90', '5', '406', 0);
INSERT INTO upms_role_permission VALUES ('91', '5', '410', 0);
INSERT INTO upms_role_permission VALUES ('92', '5', '414', 0);
INSERT INTO upms_role_permission VALUES ('93', '5', '418', 0);
INSERT INTO upms_role_permission VALUES ('94', '5', '440', 0);
INSERT INTO upms_role_permission VALUES ('95', '5', '441', 0);
INSERT INTO upms_role_permission VALUES ('96', '5', '442', 0);
INSERT INTO upms_role_permission VALUES ('97', '5', '443', 0);
INSERT INTO upms_role_permission VALUES ('98', '5', '447', 0);
INSERT INTO upms_role_permission VALUES ('99', '5', '461', 0);
INSERT INTO upms_role_permission VALUES ('100', '5', '462', 0);
INSERT INTO upms_role_permission VALUES ('101', '5', '463', 0);
INSERT INTO upms_role_permission VALUES ('102', '5', '464', 0);
INSERT INTO upms_role_permission VALUES ('103', '5', '501', 0);
INSERT INTO upms_role_permission VALUES ('104', '5', '505', 0);
INSERT INTO upms_role_permission VALUES ('105', '5', '509', 0);
INSERT INTO upms_role_permission VALUES ('106', '5', '510', 0);
INSERT INTO upms_role_permission VALUES ('107', '5', '511', 0);
INSERT INTO upms_role_permission VALUES ('108', '5', '512', 0);
INSERT INTO upms_role_permission VALUES ('109', '5', '513', 0);
INSERT INTO upms_role_permission VALUES ('110', '5', '517', 0);
INSERT INTO upms_role_permission VALUES ('111', '5', '600', 0);
INSERT INTO upms_role_permission VALUES ('112', '5', '610', 0);
INSERT INTO upms_role_permission VALUES ('113', '5', '611', 0);
INSERT INTO upms_role_permission VALUES ('114', '5', '612', 0);
INSERT INTO upms_role_permission VALUES ('115', '5', '613', 0);
INSERT INTO upms_role_permission VALUES ('116', '5', '700', 0);
INSERT INTO upms_role_permission VALUES ('117', '5', '710', 0);
INSERT INTO upms_role_permission VALUES ('118', '5', '711', 0);
INSERT INTO upms_role_permission VALUES ('119', '5', '712', 0);
INSERT INTO upms_role_permission VALUES ('120', '5', '713', 0);
INSERT INTO upms_role_permission VALUES ('121', '5', '719', 0);


INSERT INTO upms_user_company (user_id, company_id, delete_flag) VALUES (1, 1, 0);

-- ----------------------------
-- Records of upms_system
-- ----------------------------
INSERT INTO upms_system VALUES ('1', 'zmdi zmdi-shield-security', '/resources/kuyun-admin/images/kuyun-upms.png', '#29A176', 'http://upms.kuyun.cn:1111', '1', 'kuyun-upms-server', '权限管理系统', '用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）', '1', '1', 0);

-- ----------------------------
-- Records of upms_user
-- ----------------------------
INSERT INTO upms_user VALUES ('1', 'admin', '3038D9CB63B3152A79B8153FB06C02F7', '66f1b370c660445a8657bf8bf1794486', 'kuyun', '/resources/kuyun-admin/images/avatar.jpg', '13402559532', '469741414@qq.com', '1', '0', '1', 0);


-- ----------------------------
-- Records of upms_user_organization
-- ----------------------------
INSERT INTO upms_user_organization VALUES ('1', '1', '1', 0);
INSERT INTO upms_user_organization VALUES ('2', '1', '2', 0);
INSERT INTO upms_user_organization VALUES ('3', '1', '3', 0);



-- ----------------------------
-- Records of upms_user_permission
-- ----------------------------
-- INSERT INTO upms_user_permission VALUES ('3', '1', '22', '-1', 0);
-- INSERT INTO upms_user_permission VALUES ('4', '1', '22', '1', 0);

-- ----------------------------
-- Records of upms_user_role
-- ----------------------------
INSERT INTO upms_user_role VALUES ('1', '1', '1', 0);
INSERT INTO upms_user_role VALUES ('2', '1', '2', 0);
INSERT INTO upms_user_role VALUES ('3', '1', '3', 0);
INSERT INTO upms_user_role VALUES ('4', '1', '4', 0);