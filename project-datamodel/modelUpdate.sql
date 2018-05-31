
drop table if exists upms_company_option;
CREATE TABLE upms_company_option (
  company_id int(11) NOT NULL,
  domain_name  varchar(50) NULL,
  system_name  varchar(60) NULL,
  logo_path  varchar(100) NULL,
  create_user_id       int,
  create_time          datetime,
  update_user_id       int,
  update_time          datetime,
  delete_flag          boolean,
  PRIMARY KEY (company_id)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;