-- 创建和管理表

	-- 一.标识符命名规则
		-- 数据库名、表名不得超过30个字符，变量名限制为29个
		-- 必须只能包含 A–Z, a–z, 0–9, _共63个字符
		-- 数据库名、表名、字段名等对象名中间不要包含空格
		-- 同一个MySQL软件中，数据库不能同名；同一个库中，表不能重名；同一个表中，字段不能重名
		-- 必须保证你的字段没有和保留字、数据库系统或常用方法冲突。如果坚持使用，请在SQL语句中使用`（着重号）引起来
		-- 保持字段名和类型的一致性：在命名字段并为其指定数据类型的时候一定要保证一致性，假如数据
		-- 类型在一个表里是整数，那在另一个表里可就别变成字符型了
		
		
		
	-- 二.MySQL中的数据类型	
		-- 整数类型         TINYINT、SMALLINT、MEDIUMINT、INT(或INTEGER)、BIGINT
		-- 浮点类型         FLOAT、DOUBLE
		-- 定点数类型       DECIMAL
		-- 位类型    	    BIT
		-- 日期时间类型     YEAR、TIME、DATE、DATETIME、TIMESTAMP
		-- 文本字符串类型   CHAR、VARCHAR、TINYTEXT、TEXT、MEDIUMTEXT、LONGTEXT
		-- 枚举类型         ENUM
		-- 集合类型         SET
		-- 二进制字符串类型 BINARY、VARBINARY、TINYBLOB、BLOB、MEDIUMBLOB、LONGBLOB
		-- JSON类型         JSON对象、JSON数组
		-- 空间数据类型     单值：GEOMETRY、POINT、LINESTRING、POLYGON；
		-- 		    集合：MULTIPOINT、MULTILINESTRING、MULTIPOLYGON、GEOMETRYCOLLECTION
		
		
				
	-- 三.常用的几类类型介绍
		-- INT 		 从-2^31到2^31-1的整型数据。存储大小为 4个字节
		-- CHAR(size)    定长字符数据。若未指定，默认为1个字符，最大长度255
		-- VARCHAR(size) 可变长字符数据，根据字符串实际长度保存，必须指定长度
		-- FLOAT(M,D)	 单精度，占用4个字节，M=整数位+小数位，D=小数位。 D<=M<=255,0<=D<=30，默认M+D<=6
		-- DOUBLE(M,D)   双精度，占用8个字节，D<=M<=255,0<=D<=30，默认M+D<=15
		-- DECIMAL(M,D)  高精度小数，占用M+2个字节，D<=M<=65，0<=D<=30，最大取值范围与DOUBLE相同。
		-- DATE 	 日期型数据，格式'YYYY-MM-DD'
		-- BLOB 	 二进制形式的长文本数据，最大可达4G
		-- TEXT 	 长文本数据，最大可达4G
		
		
		
	-- 四.创建和管理数据库	
	-- 1.创建数据库
		-- 方式1：创建数据库
		CREATE DATABASE mytest1;
			-- 创建数据库mytest1，默认字符集utf8mb4
		-- 方式2：创建数据库并指定字符集
		CREATE DATABASE 数据库名 CHARACTER SET 'gbk'; -- (字符集)
		-- 方式3：判断数据库是否已经存在，不存在则创建数据库（推荐）
		CREATE DATABASE IF NOT EXISTS 数据库名;
		-- 例：
		CREATE DATABASE IF NOT EXISTS mytest2 CHARACTER SET 'utf8mb4'; 
		SHOW DATABASES;
		
	-- 2.管理数据库
		-- 查看当前所有的数据库
		SHOW DATABASES; -- 查看当前连接的数据库
		-- 查看当前正在使用的数据库
		SELECT DATABASE();
		-- 查看指定库下的表
		SHOW TABLES;
		SHOW TABLES FROM 数据库名;
		-- 查看数据库的创建信息
		SHOW CREATE DATABASE 数据库名;
		-- 使用/切换数据库
		USE mytest1; -- 切换到数据库mytest1	
		
	-- 3.修改数据库
		-- 更改数据库字符集
		ALTER DATABASE 数据库名 CHARACTER SET 字符集; #比如：gbk、utf8mb3、utf8mb4等
		ALTER DATABASE mytest1 CHARACTER SET 'utf8mb4';
		SHOW CREATE DATABASE mytest1;
		-- 注：数据库不能改名，改名只是新建了一张表之后复制数据，之后删除前一张表并更新日志！
		
		-- 删除数据库
		-- 方式1：删除指定的数据库
		DROP DATABASE 数据库名; -- 若数据库不存在会报错
		DROP DATABASE my; -- 报错：Can't drop database 'my'; database doesn't exist
		DROP DATABASE mytest2;
		-- 方式2：删除指定的数据库（推荐）
		DROP DATABASE IF EXISTS 数据库名; -- 存在则删除成功，不存在也不报错



	-- 五.	创建和管理表
	-- 1.创建数据表(需要用户具备权限！)
	
		-- 必须指定：表名，列名(或字段名)，数据类型，长度
		-- 可选指定：约束条件，默认值
	
		-- 方式一：创建新表
			-- CREATE TABLE [IF NOT EXISTS] 表名(
			-- 字段1, 数据类型 [约束条件] [默认值],
			-- 字段2, 数据类型 [约束条件] [默认值],
			-- 字段3, 数据类型 [约束条件] [默认值],
			-- ……
			-- [表约束条件]
			-- );
		CREATE TABLE IF NOT EXISTS myemp1 (
		id INT,
		emp_name VARCHAR(15),
		lire_date DATE
		); 
		DESC myemp1; -- 查看表和数据结构 
		SHOW CREATE TABLE myemp1; -- 查看表数据
		-- 方式二：基于现有表创建
		USE atguigudb;
		
		CREATE TABLE myemp2
		AS
		SELECT employee_id,last_name,salary
		FROM employees;
		
		DESC myemp2;	
		SELECT *
		FROM myemp2; -- 查询发现不仅复制了数据类型，连数据也复制到新表中了
		
		-- 例1：查询薪资大于15000的员工信息并放入新表new_table1中
		CREATE TABLE IF NOT EXISTS new_table1
		AS
		SELECT employee_id AS eid,last_name AS lname,salary AS sa
		FROM employees
		WHERE sa > 15000;
		
		SELECT *
		FROM new_table1;
		-- 例2：复制employees表但不包括数据
		CREATE TABLE IF NOT EXISTS new_table2
		AS
		SELECT *
		FROM employees
		WHERE 1=2; -- 选择一个条件过滤原表中的所有数据
		
		SELECT *
		FROM new_table2;
		
	-- 2.修改表(ALTER TABLE)
		DESC myemp1; 
		-- (1)添加字段
		ALTER TABLE myemp1
		ADD salary DOUBLE(10,2);
		ALTER TABLE myemp1
		ADD phone_number VARCHAR(20) FIRST; -- 只能单个添加，FIRST表示增加此列为第一个列
		-- (2)修改字段：数据类型、长度、默认值
		ALTER TABLE myemp1
		MODIFY emp_name VARCHAR(25) DEFAULT 'aaa'; -- 修改长度及默认值
		ALTER TABLE myemp1
		MODIFY id INT AUTO_INCREMENT PRIMARY KEY 
		-- (3)重命名字段
		ALTER TABLE myemp1
		CHANGE salary monthly_salary DOUBLE(10,2); -- 数据类型长度必须相同
		ALTER TABLE myemp1
		CHANGE id uid INT
		-- (4)删除字段
		ALTER TABLE myemp1
		DROP COLUMN phone_number;
	
	-- 3.重命名表
		-- 方式一：使用RENAME
		RENAME TABLE myemp2
		TO myemp12;
		DESC myemp12;
		-- 方式二(不用记)
		ALTER TABLE dept
		RENAME [TO] detail_dept; -- [TO]可以省略
	
	-- 4.删除表(同时删除表结构和表数据)
		-- 注：删除不能回滚！
		-- 删除前先备份一下下...
		CREATE TABLE IF NOT EXISTS new_table3
		AS
		SELECT *
		FROM new_table2
		WHERE 1=2;
		-- 删除表new_table2
		DROP TABLE IF EXISTS new_table2;
		
	-- 5.清空表(删除表数据但不删除表结构)
		TRUNCATE TABLE new_table3;
		DESC new_table3;
		-- 注：TRUNCATE语句不能回滚，而使用DELETE删除数据，可以回滚
		
	-- 6.回滚
		-- (1)DCL中的COMMIT和ROOLBACK
			-- [1]COMMIT：提交数据。执行后不可回滚
			-- [2]ROLLBACK：回滚数据。执行后回滚至最近一次操作
		-- (2)TRUNCATE语句不能回滚，而使用DELETE删除数据，可以回滚
		-- (3)DDL和DML说明
			-- [1]DDL执行后不能回滚
			-- [2]DCL默认执行后不能回滚，设置参数SET autocommit = FALSE;后可以回滚
		SET autocommit = FALSE;
		DELETE FROM new_table1;
		#TRUNCATE TABLE emp2;
		SELECT * FROM new_table1;
		ROLLBACK;
		SELECT * FROM new_table1;
				