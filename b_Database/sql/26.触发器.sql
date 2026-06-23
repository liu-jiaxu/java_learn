-- 触发器

	-- 1.介绍
		-- MySQL从5.0.2 版本开始支持触发器。MySQL的触发器和存储过程一样，都是嵌入到MySQL服务器的一段程序。
		-- 触发器是由事件来触发某个操作，这些事件包括INSERT 、UPDATE 、DELETE 事件。所谓事件就是指用户的动作或者触发某项行为。
		-- 如果定义了触发程序，当数据库执行这些语句时候，就相当于事件发生了，就会自动激发触发器执行相应的操作。
		
	-- 2.创建触发器
		-- 语法结构
			CREATE TRIGGER 触发器名称
			{BEFORE|AFTER} {INSERT|UPDATE|DELETE} ON 表名
			FOR EACH ROW
			触发器执行的语句块;
			-- 表名：表示触发器监控的对象。
			-- BEFORE|AFTER ：表示触发的时间。BEFORE 表示在事件之前触发；AFTER 表示在事件之后触发。
			-- INSERT|UPDATE|DELETE ：表示触发的事件。
			-- INSERT 表示插入记录时触发；
			-- UPDATE 表示更新记录时触发；
			-- DELETE 表示删除记录时触发。
			-- 触发器执行的语句块：可以是单条SQL语句，也可以是由BEGIN…END结构组成的复合语句块。		
		
		-- 例1：创建触发器：创建名称为before_insert的触发器，向test_trigger数据表插入数据之前，向test_trigger_log数据表中插入
		--     'before_insert'的日志信息。
		CREATE TABLE test_trigger (
		id INT PRIMARY KEY AUTO_INCREMENT,
		t_note VARCHAR(30)
		);
		CREATE TABLE test_trigger_log (
		id INT PRIMARY KEY AUTO_INCREMENT,
		t_log VARCHAR(30)
		);
		
		DELIMITER //
		CREATE TRIGGER before_insert
		BEFORE INSERT ON test_trigger
		FOR EACH ROW
		BEGIN
			INSERT INTO test_trigger_log (t_log)
			VALUES('before_insert');
		END //	
					
		INSERT INTO test_trigger (t_note) 
		VALUES ('测试 BEFORE INSERT 触发器');
		SELECT * 
		FROM test_trigger_log;
		
		-- 例2：定义触发器“salary_check_trigger”，基于员工表“emps”的INSERT事件，在INSERT之前检查将要添加的新员工薪资是否
		--      大于他领导的薪资，如果大于领导薪资，则报sqlstate_value为'HY000'的错误，从而使得添加失败。
		DELIMITER $
		CREATE TRIGGER salary_check_trigger
		BEFORE INSERT ON emps
		FOR EACH ROW
		BEGIN 
			DECLARE mgr_sal DOUBLE(10,2);
			
			SELECT salary INTO mgr_sal
			FROM emps 
			WHERE employee_id = NEW.manager_id;
			
			IF NEW.salary > mgr_sal -- NEW关键字代表INSERT添加语句的新记录。
			THEN SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = '薪资高于领导薪资错误'; -- 自定义错误
		END IF;
		END $
		
		-- 103管理者薪资为6794.75
		INSERT INTO emps(employee_id,last_name,email,hire_date,job_id,salary,manager_id)
		VALUES(300,'Tom','tom126@.com','2000-01-01','AD_VP',3000,103); -- 添加成功
		INSERT INTO emps(employee_id,last_name,email,hire_date,job_id,salary,manager_id)
		VALUES(300,'Tom1','tom1126@.com','2000-01-01','AD_VP',9000,103); -- 添加失败
		SELECT *
		FROM emps;
		DELETE FROM emps
		WHERE employee_id = 300;
		
	-- 3.查看触发器
		-- 方式1：查看当前数据库的所有触发器的定义
		-- SHOW TRIGGERS;
		-- 方式2：查看当前数据库中某个触发器的定义
		-- SHOW CREATE TRIGGER 触发器名;
		-- 方式3：从系统库information_schema的TRIGGERS表中查询“salary_check_trigger”触发器的信息。	
		-- SELECT * FROM information_schema.TRIGGERS;		
		SHOW TRIGGERS;		
		SHOW CREATE TRIGGER salary_check_trigger;
		SELECT * FROM information_schema.TRIGGERS;
		
	-- 4.删除触发器
		DROP TRIGGER IF EXISTS 触发器名称;
		
		
	-- 5.触发器的优缺点	
		-- (1)优点
			-- 触发器可以确保数据的完整性。
			-- 触发器可以帮助我们记录操作日志。
			-- 触发器还可以用在操作数据前，对数据进行合法性检查。
		-- (2)缺点
			-- 触发器最大的一个问题就是可读性差。
			-- 相关数据的变更，可能会导致触发器出错。
		