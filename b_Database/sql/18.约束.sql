-- 约束

	-- 为保证数据完整性，对表中的字段的限制。
	-- 分类：
		-- 约束字段的个数：单列约束 vs 多列约束
		-- 约束的作用范围：列级约束 vs 表级约束
	-- 约束作用关键字
		-- not null	非空约束
		-- unique	唯一性约束
		-- primary key  主键约束
		-- foreign key  外键约束
		-- check	检查约束
		-- default	默认值约束
	-- 添加删除约束
		-- CREATE TABLE 约束 
		-- ALTER TABLE 约束



	-- 1.查看表的约束
	#information_schema数据库名（系统库）
	#table_constraints表名称（专门存储各个表的约束）
	SELECT * FROM information_schema.table_constraints
	WHERE table_name = 'employees';



	CREATE DATABASE dbtest1;
	USE dbtest1;
	-- 2.非空约束not null
	-- (1)在创建表时添加not null
	CREATE TABLE test1(
	id INT NOT NULL, -- 设置not null
	last_name VARCHAR(15) NOT NULL,
	email VARCHAR(25),
	salary DECIMAL(10,2)
	);
	DROP TABLE test1;
	
	DESC test1; -- 查询如下：可见not null的设置
		-- Field      Type		Null	Default
		-- id	      int		NO	NULL		
		-- last_name  varchar(15)	NO	NULL	
		-- email      varchar(25)	YES	NULL	
		-- salary     decimal(10,2)	YES	NULL		
	
	INSERT INTO test1(id,last_name,email,salary)
	VALUES (1,'Tom','tom1@126.com',3400);
	INSERT INTO test1(id,last_name,email)
	VALUES (2,'DM','dm1@126.com'); -- 添加数据成功
	INSERT INTO test1(id,last_name,email,salary)
	VALUES (3,NULL,'tom1@126.com',3400); -- 不接受null值：Column 'last_name' cannot be null
	INSERT INTO test1(id,salary)
	VALUES (4,4400); -- 不设置值时自动为null：Unknown column 'idsalary' in 'field list'
	
	-- (2)在建表后添加not null
	ALTER TABLE test1
	MODIFY salary DECIMAL(10,2) NOT NULL; -- Invalid use of NULL value
		-- 注：在添加not null约束时，注意原表字段中不能存在null值，否则报错，应当先修改null值。
	SELECT *
	FROM test1; -- 查询发现salary存在null值，无法添加约束
	
	-- 修改null值的salary
	UPDATE test1
	SET salary = 2450
	WHERE id = 2;
	-- 之后添加not null约束
	ALTER TABLE test1
	MODIFY salary DECIMAL(10,2) NOT NULL; 
	DESC test1; -- 查询not null约束发现已修改
	
	-- (3)删除not null约束
	ALTER TABLE test1
	MODIFY salary DECIMAL(10,2) NULL; 
	DESC test1; -- 查询not null约束发现已修改
	
	

	-- 3.唯一性约束unique：用来限制某个字段/某列的值不能重复(允许多个null值)
		-- 同一个表可以有多个唯一约束。
		-- 唯一约束可以是某一个列的值唯一，也可以多个列组合的值唯一。
		-- 唯一性约束允许列值为空。
		-- 在创建唯一约束的时候，如果不给唯一约束命名，就默认和列名相同。
		-- MySQL会给唯一约束的列上默认创建一个唯一索引。
	-- (1)在创建表时添加unique
	CREATE TABLE test2(
	id INT UNIQUE, -- 列级约束
	last_name VARCHAR(15),
	email VARCHAR(25),
	salary DECIMAL(10,2),
	CONSTRAINT uk_test2_email UNIQUE(email) -- 表级约束
	);
	DESC test2;
	
	INSERT INTO test2(id,last_name,email,salary)
	VALUES(1,'Tom','tom126@.com',3400);
	INSERT INTO test2(id,last_name,email,salary)
	VALUES(2,'Tom2','tom126@.com',3400); -- error：Duplicate entry 'tom126@.com' for key 'test2.uk_test2_email'
	
	-- (2)建表后添加unique
	ALTER TABLE test2
	ADD phone_number VARCHAR(20);
		-- 方式一
		ALTER TABLE test2
		ADD CONSTRAINT uk_test2_pn UNIQUE(phone_number);
		-- 方式二
		ALTER TABLE test2
		MODIFY last_name VARCHAR(15) UNIQUE;
	
	-- 查看约束
	SELECT * FROM information_schema.table_constraints
	WHERE table_name = 'test2';
	
	-- (3)复合唯一约束：作用于两个及以上字段同时唯一约束
	CREATE TABLE USER(
	id INT NOT NULL,
	`NAME` VARCHAR(25),
	`PASSWORD` VARCHAR(16),
	CONSTRAINT uk_name_pwd UNIQUE(`NAME`,`PASSWORD`) -- 使用表级约束语法
	);
	
	INSERT INTO USER
	VALUES(1,'dc','abc');
	INSERT INTO USER
	VALUES(2,'sc','abc'); -- 均可添加成功
	
	SELECT *
	FROM USER;
	
	-- (4)实际案例
	#学生表
	CREATE TABLE student(
	sid INT, #学号
	sname VARCHAR(20), #姓名
	tel CHAR(11) UNIQUE KEY, #电话
	cardid CHAR(18) UNIQUE KEY #身份证号
	);
	#课程表
	CREATE TABLE course(
	cid INT, #课程编号
	cname VARCHAR(20) #课程名称
	);
	#选课表
	CREATE TABLE student_course(
	id INT,
	sid INT,
	cid INT,
	score INT,
	UNIQUE KEY(sid,cid) #复合唯一
	);
	
	INSERT INTO student VALUES(1,'张三','13710011002','101223199012015623');#成功
	INSERT INTO student VALUES(2,'李四','13710011003','101223199012015624');#成功
	INSERT INTO course VALUES(1001,'Java'),(1002,'MySQL');#成功
	INSERT INTO student_course VALUES
	(1, 1, 1001, 89),
	(2, 1, 1002, 90),
	(3, 2, 1001, 88),
	(4, 2, 1002, 56);#成功	
	
	SELECT * FROM student;
	SELECT * FROM course;
	SELECT * FROM student_course;
	
	INSERT INTO student_course VALUES (5, 1, 1001, 88);#失败
		#ERROR 1062 (23000): Duplicate entry '1-1001' for key 'sid' 违反sid-cid的复合唯一
	
	-- (5)删除unique
		-- 添加唯一性约束的列上也会自动创建唯一索引。
		-- 删除唯一约束只能通过删除唯一索引的方式删除。
		-- 删除时需要指定唯一索引名，唯一索引名就和唯一约束名一样。
		-- 如果创建唯一约束时未指定名称，如果是单列，就默认和列名相同；如果是组合列，那么默认和()
		-- 中排在第一个的列名相同。也可以自定义唯一性约束名。
	ALTER TABLE test2
	DROP INDEX last_name; -- 未指定约束名称且是单列，就默认和列名相同
	ALTER TABLE USER
	DROP INDEX uk_name_pwd; -- 删除复合唯一性约束
	