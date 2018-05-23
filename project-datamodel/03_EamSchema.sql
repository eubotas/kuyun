/*==============================================================*/
/* Table: eam_protocol                                          */
/*==============================================================*/
drop table if exists eam_protocol;
create table eam_protocol
(
   protocol_id          int not null auto_increment,
   name                 varchar(20),
   IP                   varchar(25),
   port                 int,
   primary key (protocol_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '接入协议';

DROP TABLE IF EXISTS `eam_ticket`;
CREATE TABLE `eam_ticket` (
  `ticket_id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_type_id` int(11) DEFAULT NULL,
  `ticket_number` varchar(32),
  `equipment_category_id` int(11) DEFAULT NULL,
  `product_line_id` varchar(32) DEFAULT NULL,
  `equipment_id` varchar(32) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `voice_path` varchar(2000) DEFAULT NULL COMMENT '语音',
  `image_path` varchar(2000) DEFAULT NULL COMMENT '上传图片',
  `priority` varchar(10) DEFAULT NULL COMMENT '优先级（一般，紧急, 非常紧急）',
  `executor_id` int(11) DEFAULT NULL COMMENT '处理人',
  `status` varchar(10) DEFAULT NULL COMMENT '状态（待派工, 待维修, 维修中, 待评价, 评价完成）',
  `end_date` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ticket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `eam_ticket_appointed_record`
-- ----------------------------
DROP TABLE IF EXISTS `eam_ticket_appointed_record`;
CREATE TABLE `eam_ticket_appointed_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_id` int(11) DEFAULT NULL,
  `order_taker_id` int(11) DEFAULT NULL COMMENT '接单人',
  `appoint_commont` varchar(100) DEFAULT NULL COMMENT '委派备注，可为空',
  `action`         varchar(10) DEFAULT NULL COMMENT '接单/拒单',
  `reject_commont` varchar(100) DEFAULT NULL COMMENT '拒单原因，可为空',
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `eam_ticket_assessment`
-- ----------------------------
DROP TABLE IF EXISTS `eam_ticket_assessment`;
CREATE TABLE `eam_ticket_assessment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_id` int(11) DEFAULT NULL,
  `assessment_user_id` int(11) DEFAULT NULL COMMENT '评价人ID',
  `assessment_level` int(11) DEFAULT NULL COMMENT '评价星级',
  `description` varchar(2000) DEFAULT NULL COMMENT '评价描述',
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `eam_ticket_assessment_tag`
-- ----------------------------
DROP TABLE IF EXISTS `eam_ticket_assessment_tag`;
CREATE TABLE `eam_ticket_assessment_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_id` int(11) DEFAULT NULL,
  `assessment_id` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL COMMENT '评价标签ID，from eam_tag',
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `eam_ticket_record`
-- ----------------------------
DROP TABLE IF EXISTS `eam_ticket_record`;
CREATE TABLE `eam_ticket_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_id` int(11) DEFAULT NULL,
  `step` varchar(20) DEFAULT NULL COMMENT '处理步骤',
  `comments` varchar(200) DEFAULT NULL COMMENT '处理内容',
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `eam_ticket_tag`
-- ----------------------------
DROP TABLE IF EXISTS `eam_ticket_tag`;
CREATE TABLE `eam_ticket_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL COMMENT '标签名',
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `eam_ticket_type`
-- ----------------------------
DROP TABLE IF EXISTS `eam_ticket_type`;
CREATE TABLE `eam_ticket_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(1) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS eam_maintain_plan;
CREATE TABLE eam_maintain_plan (
   plan_id int(11) NOT NULL AUTO_INCREMENT,
   equipment_category_id int(11) DEFAULT NULL,
   product_line_id varchar(32) DEFAULT NULL,
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
)ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

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
  plan_id            int(11),
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



#http://benjaminwhx.com/2015/11/24/Mysql%E4%B8%AD%E7%9A%84%E9%80%92%E5%BD%92%E5%B1%82%E6%AC%A1%E6%9F%A5%E8%AF%A2%EF%BC%88%E7%88%B6%E5%AD%90%E6%9F%A5%E8%AF%A2%EF%BC%89/

#根据传入id查询所有父节点的id
drop FUNCTION if exists getParentList;

delimiter //

CREATE FUNCTION `getParentList`(rootId INT)
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
CREATE FUNCTION `getChildList`(rootId INT)
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