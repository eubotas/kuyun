DELIMITER $$
 
USE `xmx`$$
 
DROP PROCEDURE IF EXISTS `create_partition_by_day`$$
 
CREATE PROCEDURE `create_partition_by_day`(IN_SCHEMANAME VARCHAR(64), IN_TABLENAME VARCHAR(64))
BEGIN
    DECLARE ROWS_CNT INT UNSIGNED;
    DECLARE BEGINTIME TIMESTAMP;
        DECLARE ENDTIME INT UNSIGNED;
        DECLARE PARTITIONNAME VARCHAR(16);
        SET BEGINTIME = DATE(NOW()) + INTERVAL 1 DAY;
        SET PARTITIONNAME = DATE_FORMAT( BEGINTIME, 'p%Y_%m_%d' );
 
        SET ENDTIME = UNIX_TIMESTAMP(BEGINTIME + INTERVAL 1 DAY);
 
        SELECT COUNT(*) INTO ROWS_CNT
                FROM information_schema.partitions
                WHERE table_schema = IN_SCHEMANAME AND table_name = IN_TABLENAME AND partition_name = PARTITIONNAME;
 
    IF ROWS_CNT = 0 THEN
                     SET @SQL = CONCAT( 'ALTER TABLE `', IN_SCHEMANAME, '`.`', IN_TABLENAME, '`',
                                ' ADD PARTITION (PARTITION ', PARTITIONNAME, ' VALUES LESS THAN (', ENDTIME, '));' );
                PREPARE STMT FROM @SQL;
                EXECUTE STMT;
                DEALLOCATE PREPARE STMT;
        ELSE
        SELECT CONCAT("partition `", PARTITIONNAME, "` for table `",IN_SCHEMANAME, ".", IN_TABLENAME, "` already exists") AS result;
        END IF;
END$$
 
DELIMITER ;


DELIMITER $$
 
USE `xmx`$$

CREATE EVENT IF NOT EXISTS `daily_perform_partition_event`
       ON SCHEDULE EVERY 1 DAY
       STARTS '2018-06-01 00:00:00'
       ON COMPLETION PRESERVE
       ENABLE
       COMMENT 'Creating partitions'
       DO BEGIN
            CALL xmx.create_partition_by_day('xmx', 'eam_grm_variable_data_history');
       END$$
DELIMITER ;