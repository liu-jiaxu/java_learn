-- 流程控制

	-- 一.三类流程
		-- 顺序结构：程序从上往下依次执行
		-- 分支结构：程序按条件进行选择执行，从两条或多条路径中选择一条执行
		-- 循环结构：程序满足一定条件下，重复执行一组语句
		
		-- 流程控制语句主要有3类。注意：只能用于存储程序。
			-- 条件判断语句：IF 语句和 CASE 语句
			-- 循环语句：LOOP、WHILE 和 REPEAT 语句
			-- 跳转语句：ITERATE 和 LEAVE 语句
	
	
	
	-- 二.分支结构IF
		-- 语法结构：(使用在begin end中)
			IF 表达式1 THEN 操作1
			ELSEIF 表达式2 THEN 操作2
			……
			ELSE 操作N
			END IF
		
		-- 例1：声明存储过程“update_salary_by_eid1”，定义IN参数emp_id，输入员工编号。判断该员工薪资如果低于8000元并且入职时间
		--      超过5年，就涨薪500元；否则就不变。
		DELIMITER //
		CREATE PROCEDURE update_salary_by_eid1(IN emp_id INT)
		BEGIN
			DECLARE emp_salary DOUBLE;
			DECLARE hire_year DOUBLE;
			
			SELECT salary INTO emp_salary 
			FROM emps 
			WHERE employee_id = emp_id;
			
			SELECT DATEDIFF(CURDATE(),hire_date)/365 INTO hire_year
			FROM emps 
			WHERE employee_id = emp_id;
			
			IF emp_salary < 8000 AND hire_year > 5
			THEN 
				UPDATE emps 
				SET salary = salary + 500 
				WHERE employee_id = emp_id;
			END IF;
		END //	
		
		DROP PROCEDURE IF EXISTS update_salary_by_eid1;
		CALL update_salary_by_eid1(105);	
		SELECT salary
		FROM emps
		WHERE employee_id = 105; -- 涨薪500	
			
		-- 例2：声明存储过程“update_salary_by_eid2”，定义IN参数emp_id，输入员工编号。判断该员工薪资如果低于9000元，就更新薪资
		--      为9000元；薪资如果大于等于9000元且低于10000的，但是奖金比例为NULL的，就更新奖金比例为0.01；其他的涨薪100元。
		DESC emps;
		DELIMITER $
		CREATE PROCEDURE update_salary_by_eid2(IN emp_id INT)
		BEGIN
			DECLARE emp_sal INT;
			DECLARE emp_pct DOUBLE(2,2);
			
			SELECT salary INTO emp_sal
			FROM emps
			WHERE employee_id = emp_id;
			
			SELECT commission_pct INTO emp_pct
			FROM emps
			WHERE employee_id = emp_id;
	
			IF emp_sal < 9000
			THEN 
				UPDATE emps
				SET salary = 9000
				WHERE employee_id = emp_id;
			ELSEIF emp_sal >= 9000 AND emp_sal < 10000 AND emp_pct IS NULL
			THEN
				UPDATE emps
				SET commission_pct = 0.01
				WHERE employee_id = emp_id;
			ELSE 
				UPDATE emps
				SET salary = salary + 100
				WHERE employee_id = emp_id;
			END IF;
		END $
		
		CALL update_salary_by_eid2(173); -- salary = 6100
		CALL update_salary_by_eid2(163); -- salary = 9500
		CALL update_salary_by_eid2(108); -- salary = 12000
		CALL update_salary_by_eid2(109); -- salary = 9000,commission_pct IS NULL
		SELECT employee_id,salary,commission_pct 
		FROM emps
		WHERE employee_id IN(173,163,108,109);
	
	
	
	-- 三.分支结构CASE
		-- CASE 语句的语法结构1：
			-- 情况一：类似于switch
			CASE 表达式
			WHEN 值1 THEN 结果1或语句1(如果是语句，需要加分号)
			WHEN 值2 THEN 结果2或语句2(如果是语句，需要加分号)
			...
			ELSE 结果n或语句n(如果是语句，需要加分号)
			END [CASE]（如果是放在begin end中需要加上case，如果放在select后面不需要）
		-- CASE 语句的语法结构2：
			-- 情况二：类似于多重if
			CASE
			WHEN 条件1 THEN 结果1或语句1(如果是语句，需要加分号)
			WHEN 条件2 THEN 结果2或语句2(如果是语句，需要加分号)
			...
			ELSE 结果n或语句n(如果是语句，需要加分号)
			END [CASE]（如果是放在begin end中需要加上case，如果放在select后面不需要）
			
		-- 例1：工资分类
		SELECT last_name,salary,CASE WHEN salary>=15000 THEN 'high'
					     WHEN salary>=10000 THEN 'normal'
					     WHEN salary>=5000 THEN 'low'
					     ELSE 'grass roots' 
					     END AS 'details' 
		FROM emps;
	
		-- 例2：声明存储过程update_salary_by_eid3，定义IN参数emp_id，输入员工编号。判断该员工的入职年限(今年2002年)，如果是0年，
		--      薪资涨50；如果是1年，薪资涨100；如果是2年，薪资涨200；如果是3年，薪资涨300；如果是4年，薪资涨400；其他的涨薪500。
		DELIMITER $
		CREATE PROCEDURE update_salary_by_eid3(IN emp_id INT)
		BEGIN 
			
			DECLARE hire_year INT;
			
			SELECT ROUND(DATEDIFF('2002-01-01',hire_date)/365) INTO hire_year -- ROUND()函数表示四舍五入
			FROM emps
			WHERE employee_id = emp_id;
			 
			CASE hire_year
				WHEN 0 
				THEN 
					UPDATE emps
					SET salary=salary+50 
					WHERE employee_id = emp_id;
				WHEN 1 
				THEN 
					UPDATE emps
					SET salary=salary+100 
					WHERE employee_id = emp_id;
				WHEN 2 
				THEN 
					UPDATE emps
					SET salary=salary+200 
					WHERE employee_id = emp_id;
				WHEN 3 
				THEN 
					UPDATE emps
					SET salary=salary+300 
					WHERE employee_id = emp_id;
				WHEN 4 
				THEN 
					UPDATE emps
					SET salary=salary+400 
					WHERE employee_id = emp_id;
				ELSE 
					UPDATE emps
					SET salary=salary+500 
					WHERE employee_id = emp_id;
			END CASE;
		END $
		
		DROP PROCEDURE IF EXISTS update_salary_by_eid3;
		CALL update_salary_by_eid3(101); -- salary = 17000 hire_date = 1989
		CALL update_salary_by_eid3(113); -- salary = 6900 hire_date = 1999
		SELECT employee_id,salary
		FROM emps
		WHERE employee_id IN(101,113); -- 17500  7100 
