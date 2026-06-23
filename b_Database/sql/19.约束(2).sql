-- 约束

	-- 4.主键约束primary key：用来唯一标识表中的一行记录(相当于非空+唯一性)
		-- 一个表最多只能有一个主键约束，建立主键约束可以在列级别创建，也可以在表级别上创建。
		-- 主键约束对应着表中的一列或者多列（复合主键）
		-- 如果是多列组合的复合主键约束，那么这些列都不允许为空值，并且组合的值不允许重复。
		-- MySQL的主键名总是PRIMARY，就算自己命名了主键约束名也没用。
		-- 当创建主键约束时，系统默认会在所在的列或列组合上建立对应的主键索引（能够根据主键查询的，就根据主键查询，效率更高）。
		-- 如果删除主键约束了，主键约束对应的索引就自动删除了。
		-- 需要注意的一点是，不要修改主键字段的值。因为主键是数据记录的唯一标识，如果修改了主键的值，就有可能会破坏数据的完整性。
	
	-- (1)建表时添加primary key
	CREATE TABLE test3(
	id INT PRIMARY KEY, -- 列级约束，实际中注意主键的选择：唯一且非空的数据！
	last_name VARCHAR(15) NOT NULL,
	email VARCHAR(25) UNIQUE,
	salary DECIMAL(10,2)
	-- constraint primary key(salary)-- 表级约束，同一个表只能有一个非空主键
	);
	
	DESC test3;
	SELECT * FROM information_schema.table_constraints
	WHERE table_name = 'test3';
	
	INSERT INTO test3
	VALUES(1,'Tom','123@.com',3410);
	INSERT INTO test3
	VALUES(1,'Tom1','124@.com',3410); -- 主键重复：Duplicate entry '1' for key 'test3.PRIMARY'
	INSERT INTO test3
	VALUES('Tom2','125@.com',3410); -- 主键为空：Column count doesn't match value count at row 1
	
	-- (2)建表后添加primary key
	CREATE TABLE test4(
	sid INT);
	ALTER TABLE test4
	ADD PRIMARY KEY (sid);
	
	CREATE TABLE test5(
	id INT NOT NULL,
	`NAME` VARCHAR(20),
	pwd VARCHAR(15)
	);
	ALTER TABLE test5
	ADD PRIMARY KEY(`NAME`,pwd); -- 添加复合主键
	
	-- (3)复合主键
	CREATE TABLE test6(
	id INT NOT NULL,
	NAME VARCHAR(20),
	pwd VARCHAR(15),
	CONSTRAINT pk PRIMARY KEY(NAME,pwd)
	); -- 注：复合主键所有字段均不能为空，所有字段中可以有相同的数据(不能全部相同)
	
	-- (4)删除主键
	ALTER TABLE test4 
	DROP PRIMARY KEY; -- 实际中不会删除主键！
	
	
	
	-- 5.自增列auto_increment：某个字段的值自增
		-- 一个表最多只能有一个自增长列
		-- 当需要产生唯一标识符或顺序值时，可设置自增长
		-- 自增长列约束的列必须是键列（主键列，唯一键列）
		-- 自增约束的列的数据类型必须是整数类型
		-- 如果自增列指定了 0 和 null，会在当前最大值的基础上自增；如果自增列手动指定了具体值，直接赋值为具体值。
	
	-- (1)添加auto_increment
	-- 建表时
	CREATE TABLE test7(
	eid INT PRIMARY KEY AUTO_INCREMENT, -- 注：实际中声明了主键和自增，则不要给其赋值了！
	ename VARCHAR(20),
	h_time INT
	);
	-- 建表后
	ALTER TABLE test7
	MODIFY h_time INT AUTO_INCREMENT;
	
	INSERT INTO test7(ename)
	VALUES('Tom'); -- eid默认从1自增，不用赋值
	INSERT INTO test7(ename)
	VALUES('Tom1'); -- 此时eid=2
	INSERT INTO test7(eid,ename)
	VALUES(NULL,'Tom2'); -- 指定eid=null时不会为空，会在表中eid最大值的基础上继续自增
	INSERT INTO test7(eid,ename)
	VALUES(10,'Tom3'); -- eid=10，可以指定具体数据
	INSERT INTO test7(eid,ename)
	VALUES(-10,'Tom4'); -- eid=-10
	
	SELECT *
	FROM test7;
	
	-- (2)删除auto_increment
	ALTER TABLE test7
	MODIFY eid INT;
	
	
	
	-- 6.外键约束foreign key：限定某个表的某个字段的引用完整性。
		-- 主表（父表）：被引用的表，被参考的表，字段设置主键primary key
		-- 从表（子表）：引用别人的表，参考别人的表，字段设置外键foreign key
		-- 主表和从表的字段分别设置了主键和外键，进行关联
	
		-- 从表的外键列，必须引用/参考主表的主键或唯一约束的列。因为被依赖/被参考的值必须是唯一的
		-- 在创建外键约束时，如果不给外键约束命名，默认名不是列名，而是自动产生一个外键名（例如test_ibfk_1;），也可以指定外键约束名。
		-- 创建(CREATE)表时就指定外键约束的话，先创建主表，再创建从表
		-- 删表时，先删从表（或先删除外键约束），再删除主表
		-- 当主表的记录被从表参照时，主表的记录将不允许删除，如果要删除数据，需要先删除从表中依赖该记录的数据，然后才可以删除主表的数据
		-- 在“从表”中指定外键约束，并且一个表可以建立多个外键约束
		-- 从表的外键列与主表被参照的列名字可以不相同，但是数据类型必须一样，逻辑意义一致。如果类型不一样，创建子表时，就会出现错误
		-- 当创建外键约束时，系统默认会在所在的列上建立对应的普通索引。但是索引名是外键的约束名。（根据外键查询效率很高）
		-- 删除外键约束后，必须手动删除对应的索引
	
	-- (1)建表时添加外键：先建主表与主键约束，再建从表与外键约束
	CREATE TABLE dept( -- 主表
	dept_id INT PRIMARY KEY AUTO_INCREMENT, -- 部门编号
	dept_name VARCHAR(50) -- 部门名称
	);
	CREATE TABLE emp( -- 从表
	emp_id INT PRIMARY KEY AUTO_INCREMENT, -- 员工编号
	emp_name VARCHAR(5), -- 员工姓名
	department_id INT, -- 员工所在的部门
	CONSTRAINT fk_emp1_dept_id FOREIGN KEY (department_id) REFERENCES dept(dept_id) -- 在从表中指定外键约束
		-- emp表的department_id和和dept表的dept_id的数据类型一致，意义都是表示部门的编号
	);
	
	-- (2)建表后添加外键
	ALTER TABLE emp 
	ADD FOREIGN KEY (department_id) REFERENCES dept(dept_id);
	
	INSERT INTO dept VALUES(1001,'教学部');
	INSERT INTO dept VALUES(1003, '财务部');
	INSERT INTO emp VALUES(1,'张三',1001); -- 添加从表记录成功，在添加这条记录时，要求部门表有1001部门
	INSERT INTO emp VALUES(2,'李四',1005); -- 添加从表记录失败，主表没有1005记录
	
	DESC dept;
	DESC emp;
	SELECT *
	FROM dept;
	SELECT *
	FROM emp;
	
	-- (3)删除外键：先删除外键再删除索引
	ALTER TABLE emp
	DROP FOREIGN KEY fk_emp1_dept_id; -- 删除外键(注意找外键名)
	ALTER TABLE emp 
	DROP INDEX fk_emp1_dept_id; -- 删除索引
	
	-- (4)问题
	UPDATE emp 
	SET dept_id = 1002 
	WHERE emp_id = 1;-- 修改从表失败，部门表did字段现在没有1002的值，所以员工表中不能修改员工所在部门deptid为1002
	
	UPDATE dept 
	SET dept_id = 1002 
	WHERE dept_id = 1001;-- 修改主表失败，部门表did的1001字段已经被emp引用了，所以部门表的1001字段就不能修改了。

	UPDATE dept 
	SET dept_id = 1002 
	WHERE dept_id = 1003;-- 修改主表成功 因为部门表的1003部门没有被emp表引用，所以可以修改
	
	DELETE FROM dept 
	WHERE dept_id=1001; -- 删除主表失败，因为部门表did的1001字段已经被emp引用了，所以部门表的1001字段对应的记录就不能被删除
	
	-- (5)约束等级
		-- Cascade方式：在父表上update/delete记录时，同步update/delete掉子表的匹配记录
		-- Set null方式：在父表上update/delete记录时，将子表上匹配记录的列设为null，但是要注意子表的外键列不能为not null
		-- No action方式：如果子表中有匹配的记录，则不允许对父表对应候选键进行update/delete操作
		-- Restrict方式：同no action，都是立即检查外键约束
		-- Set default方式（在可视化工具SQLyog中可能显示空白）：父表有变更时，子表将外键列设置成一个默认的值，但Innodb不能识别
		-- 如果没有指定等级，就相当于Restrict方式。对于外键约束，最好是采用: ON UPDATE CASCADE ON DELETE RESTRICT 的方式。
		
	CREATE TABLE dept2(
	did INT PRIMARY KEY, -- 部门编号
	dname VARCHAR(50) -- 部门名称
	);
	CREATE TABLE emp2(
	eid INT PRIMARY KEY, -- 员工编号
	ename VARCHAR(5), -- 员工姓名
	deptid INT, -- 员工所在的部门
	FOREIGN KEY (deptid) REFERENCES dept2(did) ON UPDATE CASCADE ON DELETE SET NULL
		-- ON UPDATE CASCADE：把修改操作设置为级联修改等级，ON DELETE SET NULL：把删除操作设置为set null等级
	);
	
	INSERT INTO dept2 VALUES(1001,'教学部');
	INSERT INTO dept2 VALUES(1002, '财务部');
	INSERT INTO dept2 VALUES(1003, '咨询部');
	INSERT INTO emp2 VALUES(1,'张三',1001); -- 在添加这条记录时，要求部门表有1001部门
	INSERT INTO emp2 VALUES(2,'李四',1001);
	INSERT INTO emp2 VALUES(3,'王五',1002);
	
	-- 修改主表成功，从表也跟着修改，修改了主表被引用的字段1002为1004，从表的引用字段就跟着修改为1004了
	UPDATE dept2
	SET did = 1004 
	WHERE did = 1002;	
	-- 删除主表的记录成功，从表对应的字段的值被修改为null
	DELETE FROM dept2 
	WHERE did = 1001;	
	
	SELECT * 
	FROM dept2;
	SELECT * 
	FROM emp2;
	
	
	
	-- 7.检查约束check：检查某个字段的值是否符号xx要求，一般指的是值的范围(MySQL8.0开始支持)
	-- (1)建表时添加check
	CREATE TABLE test8(
	id INT,
	last_name VARCHAR(15),
	salary DECIMAL(10,2) CHECK(salary > 2000),
	sex VARCHAR(6),	CHECK(sex IN ('男','女')),
	height DECIMAL(3,2) 
	);
	
	-- (2)建表后添加check
	ALTER TABLE test8
	MODIFY height DECIMAL(3,2) CHECK(height>=0 AND height<3);
	
	INSERT INTO test8
	VALUES(1,'Tom',2500,'男',1.88);
	INSERT INTO test8
	VALUES(1,'Dc',1500,'男',1.78); -- 添加失败，salary小于2000：Check constraint 'test8_chk_1' is violated.
	
	-- (3)删除check
	SELECT * FROM information_schema.table_constraints
	WHERE table_name = 'test8'; -- 先查询出check的约束名
	ALTER TABLE test8
	DROP CONSTRAINT test8_chk_4; -- 删除check
	
	SELECT * FROM information_schema.table_constraints
	WHERE table_name = 'test8';
	
	
	
	-- 8.DEFAULT约束：给某个字段/某列指定默认值，一旦设置默认值，在插入数据时，如果此字段没有显式赋值，则赋值为默认值。
	-- (1)建表时添加default
	CREATE TABLE test9(
	eid INT PRIMARY KEY,
	ename VARCHAR(20) NOT NULL,
	gender CHAR ,
	tel CHAR(11) NOT NULL DEFAULT '' #默认是空字符串
	);
	DROP TABLE test9;
	
	-- (2)建表后添加default
	ALTER TABLE test9
	MODIFY gender CHAR DEFAULT '男';

	INSERT INTO test9
	VALUES(1,'汪飞','男','13700102535');
	INSERT INTO test9(eid,ename) 
	VALUES(2,'天琪'); -- 默认性别为男
	INSERT INTO test9(eid,ename) 
	VALUES(3,'二虎'); -- 如果tel有唯一性约束的话会报错，如果tel没有唯一性约束，可以添加成功
	
	SELECT *
	FROM test9;
	
	-- (3)删除default
	ALTER TABLE test9
	MODIFY gender CHAR;
	
	
	
	-- 9.补充
	-- 面试1、为什么建表时，加 not null default '' 或 default 0
		-- 答：不想让表中出现null值。
	-- 面试2、为什么不想要 null 的值
		-- 答:（1）不好比较。null是一种特殊值，比较时只能用专门的is null 和 is not null来比较。碰到运算符，通常返回null。
		--    （2）效率不高。影响提高索引效果。因此，我们往往在建表时 not null default '' 或 default 0
	-- 面试3、带AUTO_INCREMENT约束的字段值是从1开始的吗？ 
		--     在MySQL中，默认AUTO_INCREMENT的初始值是1，每新增一条记录，字段值自动加1。设置自增属性（AUTO_INCREMENT）的
		-- 时候，还可以指定第一条插入记录的自增字段的值，这样新插入的记录的自增字段值从初始值开始递增，如在表中插入第一条
		-- 记录，同时指定id值为5，则以后插入的记录的id值就会从6开始往上增加。添加主键约束时，往往需要设置字段自动增加属性。
	-- 面试4、并不是每个表都可以任意选择存储引擎？ 外键约束（FOREIGN KEY）不能跨引擎使用。
		--     MySQL支持多种存储引擎，每一个表都可以指定一个不同的存储引擎，需要注意的是：外键约束是用来保证数据的参照完整
		-- 性的，如果表之间需要关联外键，却指定了不同的存储引擎，那么这些表之间是不能创建外键约束的。所以说，存储引擎的选择也
		-- 不完全是随意的。	
	