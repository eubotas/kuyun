
DELIMITER //  

CREATE PROCEDURE update_upms_base_data()
BEGIN
  DECLARE v_organization_id_1 int;
  DECLARE v_organization_id_2 int;
  DECLARE v_organization_id_3 int;

  DECLARE v_role_id_1 int;
  DECLARE v_role_id_2 int;
  DECLARE v_role_id_3 int;
  DECLARE v_role_id_4 int;

  DECLARE v_user_id int;

  DECLARE permissionCount  INT;

  DECLARE v_company_id int;

  DECLARE done int;

  DECLARE cursor_user_company CURSOR FOR SELECT min(user_id), company_id FROM upms_user_company where delete_flag = 0 group by company_id;

  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
  
  SET SQL_SAFE_UPDATES = 0;
  SET FOREIGN_KEY_CHECKS=0;

  truncate upms_organization;
  truncate upms_role;
  truncate upms_role_permission;
  truncate upms_user_organization;
  truncate upms_user_role;
  truncate eam_ticket_type;

  OPEN cursor_user_company;
out_loop:
LOOP
  FETCH cursor_user_company INTO v_user_id, v_company_id;
  IF done = 1 THEN
    LEAVE out_loop;
  END IF;
  
	#1. insert upms_organization 
	INSERT INTO upms_organization (name, description, ctime, company_id, delete_flag) VALUES ('维修部门', '维修部门', now(), v_company_id, 0);
	SELECT max(organization_id) INTO v_organization_id_1 FROM upms_organization;
	INSERT INTO upms_organization (name, description, ctime, company_id, delete_flag) VALUES ('维保部门', '维保部门', now(), v_company_id, 0);
	SELECT max(organization_id) INTO v_organization_id_2 FROM upms_organization;
	INSERT INTO upms_organization (name, description, ctime, company_id, delete_flag) VALUES ('报警部门', '报警部门', now(), v_company_id, 0);
	SELECT max(organization_id) INTO v_organization_id_3 FROM upms_organization;
	
	#2. insert upms_role
	INSERT INTO upms_role (company_id, name, title, description, ctime, orders, delete_flag) VALUES (v_company_id, 'super', '超级管理员', '拥有所有权限', '1', '1', 0);
	SELECT max(role_id) INTO v_role_id_1 FROM upms_role;
	
	INSERT INTO upms_role (company_id, name, title, description, ctime, orders, delete_flag) VALUES (v_company_id, 'ticketCreate', '工单提报', '拥有提报工单权限', '1', '1', 0);
	SELECT max(role_id) INTO v_role_id_2 FROM upms_role;

	INSERT INTO upms_role (company_id, name, title, description, ctime, orders, delete_flag) VALUES (v_company_id, 'ticketRepair', '工单维修', '拥有维修工单权限', '1', '1', 0);
	SELECT max(role_id) INTO v_role_id_3 FROM upms_role;
	
	INSERT INTO upms_role (company_id, name, title, description, ctime, orders, delete_flag) VALUES (v_company_id, 'ticketAppoint', '工单委派', '拥有委派工单权限', '1', '1', 0);
	SELECT max(role_id) INTO v_role_id_4 FROM upms_role;

	#3. insert upms_role_permission
	SET permissionCount = 1; 
	WHILE permissionCount  <= 35 DO

	 INSERT INTO upms_role_permission (role_id, permission_id, delete_flag) VALUES (v_role_id_1, permissionCount, 0);

	 SET  permissionCount = permissionCount + 1; 
    END WHILE;
	
	#4. insert upms_user_organization
	INSERT INTO upms_user_organization (user_id, organization_id, delete_flag) VALUES (v_user_id, v_organization_id_1, 0);
	INSERT INTO upms_user_organization (user_id, organization_id, delete_flag) VALUES (v_user_id, v_organization_id_2, 0);
	INSERT INTO upms_user_organization (user_id, organization_id, delete_flag) VALUES (v_user_id, v_organization_id_3, 0);

	#5. insert upms_user_role
	INSERT INTO upms_user_role (user_id, role_id, delete_flag) VALUES (v_user_id, v_role_id_1, 0);
	INSERT INTO upms_user_role (user_id, role_id, delete_flag) VALUES (v_user_id, v_role_id_2, 0);
	INSERT INTO upms_user_role (user_id, role_id, delete_flag) VALUES (v_user_id, v_role_id_3, 0);
	INSERT INTO upms_user_role (user_id, role_id, delete_flag) VALUES (v_user_id, v_role_id_4, 0);

	#6. insert eam_ticket_type
	INSERT INTO eam_ticket_type (name, create_time, update_time, delete_flag, company_id)VALUES ('故障报修', now(), now(),0,1, v_company_id);
	INSERT INTO eam_ticket_type (name, create_time, update_time, delete_flag, company_id)VALUES ('维保计划', now(), now(),0,1, v_company_id);
	INSERT INTO eam_ticket_type (name, create_time, update_time, delete_flag, company_id)VALUES ('报警工单', now(), now(),0,1, v_company_id);
   
SET done=0;
END LOOP out_loop;
  CLOSE cursor_user_company;
END//



