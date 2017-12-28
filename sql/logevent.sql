# 移动日志存储过程
DROP PROCEDURE IF EXISTS mvlog;
CREATE PROCEDURE mvlog()

  BEGIN

    DECLARE deadline DATE;

    SET deadline = ADDDATE(CURDATE(), -7);
    SET @STMT := CONCAT('CREATE TABLE request_params_log_', DATE_FORMAT(deadline, '%Y_%m_%d'),
                        ' (SELECT * FROM request_params_log WHERE ctime < \'', deadline, '\');');


    PREPARE STMT FROM @STMT;
    EXECUTE STMT;
    DELETE FROM request_params_log
    WHERE ctime < deadline;
  END;

# 移动日志定时任务
DROP EVENT IF EXISTS mvlogevent;
CREATE EVENT mvlogevent
  ON SCHEDULE EVERY 7 DAY STARTS '2017-05-03 03:00:00'
  ON COMPLETION PRESERVE ENABLE DO
  CALL mvlog();