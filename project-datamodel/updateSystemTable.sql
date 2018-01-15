use kuyun;

alter table upms_role add company_id int not null;

INSERT INTO upms_company (company_id, name, delete_flag) VALUES (1, '新美星', 0);
INSERT INTO upms_user_company (user_id, company_id) VALUES (1, 1);

update upms_role set company_id = 1 where role_id in (1, 2);