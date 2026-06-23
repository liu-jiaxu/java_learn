-- 游标(光标)

	-- 1.游标介绍
		-- (1)游标，提供了一种灵活的操作方式，让我们能够对结果集中的每一条记录进行定位，并对指向的记录中的数据进行操作的数据结构。
		-- (2)游标让 SQL 这种面向集合的语言有了面向过程开发的能力。
		-- (3)在 SQL 中，游标是一种临时的数据库对象，可以指向存储在数据库表中的数据行指针。这里游标充当了指针的作用，我们可以通过
		--    操作游标来对数据行进行操作。
		-- (4)MySQL中游标可以在存储过程和函数中使用。
		
	-- 2.使用游标(四个步骤)	
	-- (1)声明游标
		DECLARE cursor_name CURSOR FOR select_statement;
		-- 这个语法适用于 MySQL，SQL Server，DB2 和 MariaDB。
		-- 如果是用 Oracle 或者 PostgreSQL，需要写成：DECLARE cursor_name CURSOR IS select_statement;
	-- (2)打开游标	
		OPEN cursor_name;
	-- (3)使用游标（从游标中取得数据）
		FETCH cursor_name INTO var_name [, var_name] ... ;-- 注var_name必须在声明游标之前就定义好。
		-- 这句的作用是使用 cursor_name 这个游标来读取当前行，并且将数据保存到 var_name 这个变量中，游标指针指到下一行。
		-- 如果游标读取的数据行有多个列名，则在 INTO 关键字后面赋值给多个变量名即可。
	-- (4)关闭游标
		CLOSE cursor_name;
		-- [1]游标会占用系统资源，如果不及时关闭，游标会一直保持到存储过程结束，影响系统运行的效率。
		-- [2]关闭游标之后，就不能再检索查询结果中的数据行，如果需要检索只能再次打开游标。
	
	-- 3.示例1：创建存储过程get_count_by_limit_total_salary()，声明IN参数 limit_total_salary，DOUBLE类型；声明OUT参数total_count，INT类型。
	--         函数的功能可以实现累加薪资最高的几个员工的薪资值，直到薪资总和达到limit_total_salary参数的值，返回累加的人数给total_count。	
	DELIMITER //
	CREATE PROCEDURE get_count_by_limit_total_salary(IN limit_total_salary DOUBLE,OUT total_count INT)
	BEGIN
		DECLARE sum_salary DOUBLE DEFAULT 0; -- 记录累加的总工资
		DECLARE cursor_salary DOUBLE DEFAULT 0; -- 记录某一个工资值
		DECLARE emp_count INT DEFAULT 0; -- 记录循环个数
		
		-- 定义游标
		DECLARE emp_cursor CURSOR FOR SELECT salary FROM emps ORDER BY salary DESC;
		
		-- 打开游标
		OPEN emp_cursor;
		
		REPEAT
			-- 使用游标（从游标中获取数据）
			FETCH emp_cursor INTO cursor_salary;
			SET sum_salary = sum_salary + cursor_salary;
			SET emp_count = emp_count + 1;
			UNTIL sum_salary >= limit_total_salary
		END REPEAT;
		
		SET total_count = emp_count;
		SELECT sum_salary;
		
		-- 关闭游标
		CLOSE emp_cursor;
	END //	
		-- 和链表差不多啊。。。 
		
	CALL get_count_by_limit_total_salary(200000,@total_count);
	SELECT @total_count;
	
	-- 4.示例2：查询emps表中id在105-200之间(不包括两值)的员工薪资总和
	SELECT SUM(salary) -- 先用普通查询语句查一查
	FROM emps
	WHERE employee_id BETWEEN 106 AND 199; -- 420293.49
	
	-- 下面练习游标，只是练习。。。
	-- 利用游标查询任意区间内的id总薪资
	DELIMITER //
	CREATE PROCEDURE count_sum_salary(IN first_id INT,IN last_id INT) -- 设置两参数为取值区间
	BEGIN
		DECLARE cursor_salary DOUBLE DEFAULT 0; -- 某个员工薪资 
		DECLARE sum_salary DOUBLE DEFAULT 0; -- 薪资总和
		DECLARE emp_id INT DEFAULT (first_id + 1); -- 表示员工id
	
		-- 定义游标
		DECLARE sum_sal CURSOR FOR SELECT salary FROM emps WHERE employee_id BETWEEN (first_id + 1) AND (last_id - 1);
		
		-- 打开游标
		OPEN sum_sal;
		
		-- 循环赋值
		loop_label:LOOP
			
			-- 循环条件为大于区间id最大值时结束循环
			IF emp_id > (last_id - 1) 
			THEN LEAVE loop_label;
			END IF;
			
			-- 使用游标
			FETCH sum_sal INTO cursor_salary;
			SET emp_id = emp_id + 1;
			SET sum_salary = sum_salary + cursor_salary;
			
		END LOOP loop_label;
		
		SELECT sum_salary;
		-- 关闭游标
		CLOSE sum_sal;
	END //
	
	DROP PROCEDURE count_sum_salary;
	CALL count_sum_salary(105,200); -- 420293.49
	-- 我太厉害了QAQ，但是实际没啥用，直接select查快多了。。。
		