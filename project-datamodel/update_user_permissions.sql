DELIMITER //  

CREATE PROCEDURE update_user_permissions()
BEGIN
  

  DECLARE v_user_id int;
  DECLARE v_permission_id int;

  DECLARE done int;

  DECLARE cursor_user CURSOR FOR SELECT user_id FROM upms_user;
  DECLARE curson_permission CURSOR FOR SELECT permission_id FROM upms_permission WHERE permission_id >=200 and permission_id <2000;
  
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
  

  OPEN cursor_user;
out_loop:
LOOP
  FETCH cursor_user INTO v_user_id;
  IF done = 1 THEN
    LEAVE out_loop;
  END IF;
  
  open curson_permission;
  inner_loop:LOOP
     
    FETCH curson_permission INTO v_permission_id; 
    IF done = 1 THEN
       LEAVE inner_loop;
    end IF;
     
      insert upms_user_permission(user_id, permission_id, type, delete_flag) values(v_user_id, v_permission_id, 1, 0);

   
  
  end LOOP inner_loop;
  CLOSE curson_permission; 
   
SET done=0;
END LOOP out_loop;
  CLOSE cursor_user;
END//