INSERT INTO upms_company (company_id, parent_id, name, address, phone, www, create_time, delete_flag) VALUES ('1', null, '库德莱兹物联科技（苏州）有限公司', '苏州工业园苏虹东路177号方正科技园', '0512-86861827', 'www.coderise.cn', now(),  0);


-- ----------------------------
-- Records of upms_organization   tyler remove
-- ----------------------------
INSERT INTO upms_organization (organization_id, pid, name, description, ctime, company_id, delete_flag) VALUES ('1', null, '维修部', '维修部门', now(), 1, 0);
INSERT INTO upms_organization (organization_id, pid, name, description, ctime, company_id, delete_flag) VALUES ('2', null, '维保部', '维保部门', now(), 1, 0);
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
INSERT INTO upms_role VALUES ('2', 1, 'admin', '管理员', '拥有除权限管理系统外的所有权限', '1', '1', 0);
INSERT INTO upms_role VALUES ('3', 1, 'ticketCreate', '工单提报', '拥有提报工单权限', '1', '1', 0);
INSERT INTO upms_role VALUES ('4', 1, 'ticketRepair', '工单维修', '拥有维修工单权限', '1', '1', 0);
INSERT INTO upms_role VALUES ('5', 1, 'ticketAppoint', '工单委派', '拥有委派工单权限', '1', '1', 0);

-- ----------------------------
-- Records of upms_role_permission
-- ----------------------------
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
INSERT INTO upms_role_permission VALUES ('36', '1', '36', 0);


INSERT INTO upms_user_company (user_id, company_id, delete_flag) VALUES (1, 1, 0);

-- ----------------------------
-- Records of upms_system
-- ----------------------------
INSERT INTO upms_system VALUES ('1', 'zmdi zmdi-shield-security', '/resources/kuyun-admin/images/kuyun-upms.png', '#29A176', 'http://upms.kuyun.cn:1111', '1', 'kuyun-upms-server', '权限管理系统', '用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）', '1', '1', 0);

-- ----------------------------
-- Records of upms_user
-- ----------------------------
INSERT INTO upms_user VALUES ('1', '13402559532', '3038D9CB63B3152A79B8153FB06C02F7', '66f1b370c660445a8657bf8bf1794486', 'kuyun', '/resources/kuyun-admin/images/avatar.jpg', '13402559532', '469741414@qq.com', '1', '0', '1', 0);


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
INSERT INTO upms_user_role VALUES ('5', '1', '5', 0);
