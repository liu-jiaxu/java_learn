-- 数据处理增删改

	USE atguigudb; 
	CREATE TABLE IF NOT EXISTS emp1 (
	id INT,
	`name` VARCHAR(15),
	hire_date DATE,
	salary DOUBLE(10,2)
	);
	DESC emp1;
	DROP TABLE IF EXISTS emp1;
	
	-- 1.添加数据
	-- 方式一：逐行添加
		-- (1)按顺序添加
		INSERT INTO emp1
		VALUES (1,'Tom','2000-12-21',3400);
		-- (2)指明添加字段
		INSERT INTO emp1(`name`,id,hire_date,salary)
		VALUES ('Sc',2,'2002-2-21',3100),
		       ('Dc',3,'2002-4-1',5100); -- 可同时插入多行数据
		INSERT INTO emp1(`name`,id,hire_date) -- 不指明字段为null
		VALUES ('Amy',4,'2002-2-21');
	SELECT *
	FROM emp1;
	-- 方式二：将查询结果插入表中
		INSERT INTO emp1(id,`name`,hire_date,salary) -- 此处若不指定字段则要保证select一一对应
		SELECT employee_id,last_name,hire_date,salary
		FROM employees
		WHERE department_id IN (60,70);
		-- 注：要查询的表(employees)中的字段数据类型长度要小于等于插入表(emp1)的数据类型长度，否则会添加失败
	SELECT *
	FROM emp1;
	
	-- 2.更新数据
	-- UPDATE...SET...WHERE...
		-- 例1：添加Amy薪资
		UPDATE emp1
		SET salary = 5000
		WHERE id = 4;
		-- 例2：同时修改多条数据
		UPDATE emp1
		SET salary = salary/1.1,hire_date = CURDATE() 
		WHERE `name` LIKE '%Sc%';
		-- 注：数据可能修改不成功(存在约束问题)
	SELECT *
	FROM emp1;
	
	-- 3.删除数据
	-- DELETE FROM...WHERE...
		SET autocommit = FALSE; -- 回滚设置
		DELETE FROM emp1
		WHERE `name` = 'Dc';
		ROLLBACK;
		-- 注：数据可能删除不成功(存在约束问题)
	SELECT *
	FROM emp1;
	
	-- 4.MySQL8新特性：计算列
	CREATE TABLE emp2(
	id INT,
	a INT,
	b INT,
	c INT GENERATED ALWAYS AS (a + b) VIRTUAL -- 设置c列值为a+b
	);
	INSERT INTO emp2(a,b) 
	VALUES (200,100),(10,20);
	SELECT *
	FROM emp2;
	