DROP DATABASE IF EXISTS xmx;
create database xmx;
use xmx;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for upms_log
-- ----------------------------
DROP TABLE IF EXISTS upms_log;
CREATE TABLE upms_log (
  log_id int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  description varchar(100) DEFAULT NULL COMMENT '操作描述',
  username varchar(20) DEFAULT NULL COMMENT '操作用户',
  start_time bigint(20) DEFAULT NULL COMMENT '操作时间',
  spend_time int(11) DEFAULT NULL COMMENT '消耗时间',
  base_path varchar(100) DEFAULT NULL COMMENT '根路径',
  uri varchar(200) DEFAULT NULL COMMENT 'URI',
  url varchar(200) DEFAULT NULL COMMENT 'URL',
  method varchar(10) DEFAULT NULL COMMENT '请求类型',
  parameter mediumtext,
  user_agent varchar(500) DEFAULT NULL COMMENT '用户标识',
  ip varchar(30) DEFAULT NULL COMMENT 'IP地址',
  result mediumtext,
  permissions varchar(100) DEFAULT NULL COMMENT '权限值',
  PRIMARY KEY (log_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志';


drop table if exists upms_company;
CREATE TABLE upms_company (
  company_id int(11) NOT NULL AUTO_INCREMENT,
  parent_id int(11) DEFAULT NULL,
  name varchar(100) NOT NULL,
  address varchar(100) DEFAULT NULL,
  phone varchar(15) DEFAULT NULL,
  fax varchar(15) DEFAULT NULL,
  zip varchar(10) DEFAULT NULL,
  www varchar(50) DEFAULT NULL,
  admin_name           varchar(20) DEFAULT NULL,
  admin_password       varchar(20) DEFAULT NULL,
  create_user_id       int,
  create_time          datetime,
  update_user_id       int,
  update_time          datetime,
  delete_flag          boolean,
  PRIMARY KEY (company_id)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for upms_organization
-- ----------------------------
DROP TABLE IF EXISTS upms_organization;
CREATE TABLE upms_organization (
  organization_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  pid int(10) DEFAULT NULL COMMENT '所属上级',
  name varchar(20) DEFAULT NULL COMMENT '组织名称',
  description varchar(1000) DEFAULT NULL COMMENT '组织描述',
  ctime bigint(20) DEFAULT NULL COMMENT '创建时间',
  company_id int(11),
  delete_flag  boolean,
  PRIMARY KEY (organization_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织';

-- ----------------------------
-- Table structure for upms_permission
-- ----------------------------
DROP TABLE IF EXISTS upms_permission;
CREATE TABLE upms_permission (
  permission_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  system_id int(10) unsigned NOT NULL COMMENT '所属系统',
  pid int(10) DEFAULT NULL COMMENT '所属上级',
  name varchar(20) DEFAULT NULL COMMENT '名称',
  type tinyint(4) DEFAULT NULL COMMENT '类型(1:目录,2:菜单,3:按钮)',
  permission_value varchar(50) DEFAULT NULL COMMENT '权限值',
  uri varchar(100) DEFAULT NULL COMMENT '路径',
  icon varchar(50) DEFAULT NULL COMMENT '图标',
  status tinyint(4) DEFAULT NULL COMMENT '状态(0:禁止,1:正常)',
  ctime bigint(20) DEFAULT NULL COMMENT '创建时间',
  orders bigint(20) DEFAULT NULL COMMENT '排序',
  delete_flag  boolean,
  PRIMARY KEY (permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限';


-- ----------------------------
-- Table structure for upms_role
-- ----------------------------
DROP TABLE IF EXISTS upms_role;
CREATE TABLE upms_role (
  role_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  company_id int(11) DEFAULT NULL,
  name varchar(20) DEFAULT NULL COMMENT '角色名称',
  title varchar(20) DEFAULT NULL COMMENT '角色标题',
  description varchar(1000) DEFAULT NULL COMMENT '角色描述',
  ctime bigint(20) NOT NULL COMMENT '创建时间',
  orders bigint(20) NOT NULL COMMENT '排序',
  delete_flag boolean,
  PRIMARY KEY (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';



-- ----------------------------
-- Table structure for upms_role_permission
-- ----------------------------
DROP TABLE IF EXISTS upms_role_permission;
CREATE TABLE upms_role_permission (
  role_permission_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  role_id int(10) unsigned NOT NULL COMMENT '角色编号',
  permission_id int(10) unsigned NOT NULL COMMENT '权限编号',
  delete_flag boolean,
  PRIMARY KEY (role_permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';


-- ----------------------------
-- Table structure for upms_system
-- ----------------------------
DROP TABLE IF EXISTS upms_system;
CREATE TABLE upms_system (
  system_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  icon varchar(50) DEFAULT NULL COMMENT '图标',
  banner varchar(50) DEFAULT NULL COMMENT '背景',
  theme varchar(50) DEFAULT NULL COMMENT '主题',
  basepath varchar(100) DEFAULT NULL COMMENT '根目录',
  status tinyint(4) DEFAULT NULL COMMENT '状态(-1:黑名单,1:正常)',
  name varchar(30) DEFAULT NULL COMMENT '系统名称',
  title varchar(20) DEFAULT NULL COMMENT '系统标题',
  description varchar(300) DEFAULT NULL COMMENT '系统描述',
  ctime bigint(20) DEFAULT NULL COMMENT '创建时间',
  orders bigint(20) DEFAULT NULL COMMENT '排序',
  delete_flag boolean,
  PRIMARY KEY (system_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统';


-- ----------------------------
-- Table structure for upms_user
-- ----------------------------
DROP TABLE IF EXISTS upms_user;
CREATE TABLE upms_user (
  user_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  username varchar(20) NOT NULL COMMENT '帐号',
  password varchar(32) NOT NULL COMMENT '密码MD5(密码+盐)',
  salt varchar(32) DEFAULT NULL COMMENT '盐',
  realname varchar(20) DEFAULT NULL COMMENT '姓名',
  avatar varchar(50) DEFAULT NULL COMMENT '头像',
  phone varchar(20) DEFAULT NULL COMMENT '电话',
  email varchar(50) DEFAULT NULL COMMENT '邮箱',
  sex tinyint(4) DEFAULT NULL COMMENT '性别',
  locked tinyint(4) DEFAULT NULL COMMENT '状态(0:正常,1:锁定)',
  ctime bigint(20) DEFAULT NULL COMMENT '创建时间',
  delete_flag boolean,
  PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';


-- ----------------------------
-- Table structure for upms_user_organization
-- ----------------------------
DROP TABLE IF EXISTS upms_user_organization;
CREATE TABLE upms_user_organization (
  user_organization_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  user_id int(10) unsigned NOT NULL COMMENT '用户编号',
  organization_id int(10) unsigned NOT NULL COMMENT '组织编号',
  delete_flag boolean,
  PRIMARY KEY (user_organization_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组织关联表';


-- ----------------------------
-- Table structure for upms_user_permission
-- ----------------------------
DROP TABLE IF EXISTS upms_user_permission;
CREATE TABLE upms_user_permission (
  user_permission_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  user_id int(10) unsigned NOT NULL COMMENT '用户编号',
  permission_id int(10) unsigned NOT NULL COMMENT '权限编号',
  type tinyint(4) NOT NULL COMMENT '权限类型(-1:减权限,1:增权限)',
  delete_flag boolean,
  PRIMARY KEY (user_permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权限关联表';

-- ----------------------------
-- Table structure for upms_user_role
-- ----------------------------
DROP TABLE IF EXISTS upms_user_role;
CREATE TABLE upms_user_role (
  user_role_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  user_id int(10) unsigned NOT NULL COMMENT '用户编号',
  role_id int(10) DEFAULT NULL COMMENT '角色编号',
  delete_flag boolean,
  PRIMARY KEY (user_role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

drop table if exists upms_user_company;
CREATE TABLE upms_user_company (
  user_company_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  user_id int(10) unsigned NOT NULL COMMENT '用户编号',
  company_id int(10) unsigned NOT NULL COMMENT '公司编号',
  delete_flag  boolean,
  PRIMARY KEY (user_company_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户公司关联表';

DROP TABLE IF EXISTS upms_organization_role;
CREATE TABLE upms_organization_role (
  organization_role_id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  organization_id int(10) unsigned NOT NULL COMMENT '组织编号',
  role_id int(10) DEFAULT NULL COMMENT '角色编号',
  delete_flag  boolean,
  PRIMARY KEY (organization_role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织角色关联表';