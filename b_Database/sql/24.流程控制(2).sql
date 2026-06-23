-- 流程控制

	-- 循环结构4要素：
		-- 1.初始化条件
		-- 2.循环条件
		-- 3.循环体
		-- 4.迭代条件

	-- 四.循环结构LOOP
		-- LOOP循环语句用来重复执行某些语句。LOOP内的语句一直重复执行直到循环被退出（使用LEAVE子句），跳出循环过程。
		-- 基本格式：
			[loop_label:] LOOP 
				循环执行的语句 
			LEAVE -- 结束循环
			END LOOP [loop_label] -- loop_label表示LOOP语句的标注名称
	
		-- 例：声明存储过程“update_salary_loop()”，声明OUT参数num，输出循环次数。存储过程中实现循环给大家涨薪，薪资涨为原来
		--     的1.1倍。直到全公司的平均薪资达到12000结束。并统计循环次数。
		DELIMITER $
		CREATE PROCEDURE update_salary_loop(OUT num INT)
		BEGIN
			DECLARE avg_sal DOUBLE(7,2);
			DECLARE count_num INT DEFAULT 0;
			
			-- (1)循环初始化
			SELECT SUM(salary)/COUNT(1) INTO avg_sal 
				-- AVG(salary)替换为SUM(salary)/COUNT(1)是为了防止空值出现而使聚合函数忽略空值
			FROM emps;
			
			JUMP_PAY:LOOP
				-- (2)循环条件
				-- 结束循环条件
				IF avg_sal >= 12000  
				THEN LEAVE JUMP_PAY;
				END IF;
				
				-- (3)循环体
				-- 更新薪资、循环次数、平均薪资
				UPDATE emps
				SET salary = salary * 1.1;
				SET count_num = count_num + 1; 
					-- 为防止在存储过程外定义的num变量不是从0开始计数，所以要重新定义一个0变量计数，循环结束后在赋值给num
				-- (4)迭代条件·
				SELECT SUM(salary)/COUNT(1) INTO avg_sal 
				FROM emps;
			END LOOP JUMP_PAY;
			SET num = count_num; 
		END $
		
		CALL update_salary_loop(@num);
		SELECT @num;
		SELECT AVG(e1.salary),AVG(e2.salary)
		FROM dbtest_view.`emps` e1,atguigudb.`employees` e2; -- 12670.304579  6461.682243
	
	
	
	-- 五.循环结构WHILE
		-- WHILE语句创建一个带条件判断的循环过程。WHILE在执行语句执行时，先对指定的表达式进行判断，若为真，就执行循环内的语句，否则退出循环。
		-- 基本格式：
			[while_label:] WHILE 循环条件 DO
				循环体
			END WHILE [while_label]; -- while_label为WHILE语句的标注名称
			
		-- 例：声明存储过程“update_salary_while()”，声明OUT参数num，输出循环次数。存储过程中实现循环给大家降薪，薪资降为
		--     原来的90%。直到全公司的平均薪资达到5000结束。并统计循环次数。
		DELIMITER $
		CREATE PROCEDURE update_salary_while(OUT num INT)
		BEGIN
			DECLARE avg_sal DOUBLE(7,2);
			DECLARE count_num INT DEFAULT 0;
			
			SELECT AVG(salary) INTO avg_sal
			FROM emps;
			
			pay_cut:WHILE avg_sal > 5000 DO
				UPDATE emps
				SET salary = salary*0.9;
				SET count_num = count_num + 1;
				SELECT AVG(salary) INTO avg_sal 
				FROM emps;
			END WHILE pay_cut;
			SET num = count_num;
		END $ 
	
		CALL update_salary_while(@num2);
		SELECT @num2;
		SELECT AVG(e1.salary),AVG(e2.salary)
		FROM dbtest_view.`emps` e1,atguigudb.`employees` e2;
	
	
	
	-- 六.循环结构REPEAT
		-- REPEAT语句创建一个带条件判断的循环过程。与WHILE循环不同的是，REPEAT 循环首先会执行一次循环，然后在 UNTIL 中进行
		-- 表达式的判断，如果满足条件就退出，即 END REPEAT；如果条件不满足，则会就继续执行循环，直到满足退出条件为止
		-- 基本格式：
			[repeat_label:] REPEAT
		　　　　	循环体的语句
			UNTIL 结束循环的条件表达式
			END REPEAT [repeat_label] -- repeat_label为REPEAT语句的标注名称
			
		-- 例：声明存储过程“update_salary_repeat()”，声明OUT参数num，输出循环次数。存储过程中实现循环给大家涨薪，薪资涨为原
		--     来的1.15倍。直到全公司的平均薪资达到13000结束。并统计循环次数。
		DELIMITER //
		CREATE PROCEDURE update_salary_repeat(OUT num INT)
		BEGIN
			DECLARE avg_sal DOUBLE ;
			DECLARE repeat_count INT DEFAULT 0;
			
			SELECT AVG(salary) INTO avg_sal 
			FROM employees;
			
			REPEAT -- REPEAT不管条件，必先执行一次！
				UPDATE employees 
				SET salary = salary * 1.15;
				SET repeat_count = repeat_count + 1;
				SELECT AVG(salary) INTO avg_sal 
				FROM employees;
				UNTIL avg_sal >= 13000 -- 注意UNTIL不加;
			END REPEAT;
			SET num = repeat_count;
		END //
	
	-- 对比三种循环结构：
		-- 1、这三种循环都可以省略名称，但如果循环中添加了循环控制语句（LEAVE或ITERATE）则必须添加名称。 
		-- 2、 LOOP：一般用于实现简单的"死"循环 WHILE：先判断后执行 REPEAT：先执行后判断，无条件至少执行一次
	
	
	
	-- 七.跳转语句LEAVE
		-- 可以用在循环语句内，或者以 BEGIN 和 END 包裹起来的程序体内，表示跳出循环或者跳出程序体的操作(相当于break label)
		-- 基本格式：
			LEAVE 标记名 -- label参数表示循环的标志。LEAVE和BEGIN ... END或循环一起被使用。
			
		-- 例：创建存储过程leave_begin()，声明INT类型的IN参数num。给BEGIN...END加标记名，并在BEGIN...END中使用IF语句判断num参数的值。
		-- 	如果num<=0，则使用LEAVE语句退出BEGIN...END；
		-- 	如果num=1，则查询“emps”表的平均薪资；
		-- 	如果num=2，则查询“emps”表的最低薪资；
		-- 	如果num>2，则查询“emps”表的最高薪资。
		-- 	IF语句结束后查询“emps”表的总人数。
		DELIMITER $
		CREATE PROCEDURE leave_begin(IN num INT)
		begin_lable:BEGIN
			IF num <=0
			THEN LEAVE begin_lable; -- 跳出GEGIN，结束语句
			ELSEIF num = 1
			THEN
				SELECT AVG(salary)
				FROM emps;
			ELSEIF num = 2
			THEN 
				SELECT MIN(salary)
				FROM emps;
			ELSE 
				SELECT MAX(salary)
				FROM emps;
			END IF;
			
			SELECT COUNT(*)
			FROM emps;
		END $
		
		CALL leave_begin(0);
		CALL leave_begin(1);
		CALL leave_begin(2);
		CALL leave_begin(3);
	
	
	
	-- 八.跳转语句ITERATE
		-- 只能用在循环语句（LOOP、REPEAT和WHILE语句）内，表示重新开始循环，将执行顺序转到语句段开头处(相当于continue)
		-- 基本格式：
			ITERATE label -- label参数表示循环的标志。ITERATE语句必须跟在循环标志前面。
			
		-- 例：定义局部变量num，初始值为0。循环结构中执行num + 1操作,将id为110-115的员工信息添加到新表中
		--     如果num < 10，则继续执行循环；
		--     如果num > 15，则退出循环结构；
		CREATE TABLE IF NOT EXISTS emp1 (
		id INT,
		`name` VARCHAR(25),
		salary DOUBLE(10,2)
		);
		CREATE TABLE IF NOT EXISTS emp2 (
		id INT,
		`name` VARCHAR(25),
		salary DOUBLE(10,2)
		);
		
		-- 注意两个存储过程的区别
		DELIMITER //
		CREATE PROCEDURE test_iterate()
		BEGIN
			-- 循环初始值
			DECLARE num INT DEFAULT 0;
			
			loop_lable:LOOP
				-- 迭代条件
				SET num = num + 1; -- 若将此迭代条件放在循环条件后面会成为死循环
				
				-- 循环条件
				IF num < 10
				THEN ITERATE loop_lable; -- num<10时都不会继续往下执行，而是立即重新执行循环 
				ELSEIF num > 15 -- num>15则结束程序
				THEN LEAVE loop_lable;
				END IF;
				
				-- 循环体
				INSERT INTO emp1(id,`name`,salary) 	
				SELECT employee_id,last_name,salary
				FROM emps
				WHERE employee_id = 100 + num;
				
			END LOOP loop_lable;
		END //
	
		DROP PROCEDURE test_iterate;
		CALL test_iterate();
		SELECT * -- 查询发现只添加了id为110-115名员工的信息，正确
		FROM emp1;
	
		DELIMITER //
		CREATE PROCEDURE test_iterate2()
		BEGIN
			DECLARE num INT DEFAULT 0;
			
			loop_lable:LOOP
				SET num = num + 1;
				
				INSERT INTO emp2(id,`name`,salary) 	
				SELECT employee_id,last_name,salary
				FROM emps
				WHERE employee_id = 100 + num;	
					-- 将循环体放在ITERATE上面，则当num<10时也会执行这些语句，所以会把id为101-116的员工信息都添加到新表中了
				
				IF num < 10
				THEN ITERATE loop_lable;
				ELSEIF num > 15
				THEN LEAVE loop_lable;
				END IF;
				
			END LOOP loop_lable;
		END //
	
		DROP PROCEDURE test_iterate2;
		CALL test_iterate2();
		SELECT *
		FROM emp2;
	