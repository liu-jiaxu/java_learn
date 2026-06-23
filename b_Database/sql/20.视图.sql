-- 视图

	-- 1. 常见的数据库对象
		-- 对象    		描述
		-- 表(TABLE) 		表是存储数据的逻辑单元，以行和列的形式存在，列就是字段，行就是记录
		-- 数据字典		就是系统表，存放数据库相关信息的表。系统表的数据通常由数据库系统维护，通常不应该修改，只可查看
		-- 约束(CONSTRAINT)	执行数据校验的规则，用于保证数据完整性的规则
		-- 视图(VIEW) 		一个或者多个数据表里的数据的逻辑显示，视图并不存储数据
		-- 索引(INDEX) 		用于提高查询性能，相当于书的目录
		-- 存储过程(PROCEDURE)	用于完成一次完整的业务处理，没有返回值，但可通过传出参数将多个值传给调用环境
		-- 存储函数(FUNCTION)   用于完成一次特定的计算，具有一个返回值
		-- 触发器(TRIGGER)      相当于一个事件监听器，当数据库发生特定事件后，触发器被触发，完成相应的处理
		
	
	
	-- 2，视图
		-- 视图是一种虚拟表，本身是不具有数据的，占用很少的内存空间，它是 SQL 中的一个重要概念。
		-- 视图建立在已有表的基础上, 视图赖以建立的这些表称为基表。
		-- 视图的创建和删除只影响视图本身，不影响对应的基表。对视图中的数据进行增删改时，数据表中的数据会相应地发生变化，反之亦然。
		-- 视图通常应用于大型项目
	-- (1)创建视图
		-- CREATE [OR REPLACE]
		-- [ALGORITHM = {UNDEFINED | MERGE | TEMPTABLE}]
		-- VIEW 视图名称 [(字段列表)]
		-- AS 查询语句
		-- [WITH [CASCADED|LOCAL] CHECK OPTION]
	USE `dbtest_view`;	
	
	CREATE DATABASE dbtest_view;
	USE dbtest_view;
	CREATE TABLE emps
	AS SELECT *
	FROM atguigudb.`employees`;
	CREATE TABLE depts
	AS SELECT *
	FROM atguigudb.`departments`; -- 注：复制的表不会复制约束关系！
		
	-- 例1：基本的视图及字段重命名
	CREATE VIEW vu_emp1 -- 创建视图
	AS 
	SELECT employee_id,AVG(salary) AS sal  -- 使用as重命名即可
	FROM emps
	GROUP BY employee_id;
	SELECT * -- 查询视图
	FROM vu_emp1;
	-- 例2：多表视图
	CREATE VIEW vu_emp2
	AS
	SELECT e.employee_id e_id,e.department_id d_id,d.department_name d_name -- 使用as重命名即可
	FROM emps e JOIN depts d
	ON e.`department_id` = d.`department_id`; 
	SELECT *
	FROM vu_emp2;
	-- 例3：视图进行数据格式化
	CREATE VIEW vu_emp3 
	AS 
	SELECT CONCAT(first_name,' ',last_name) `name`
	FROM emps;
	SELECT * 
	FROM vu_emp3;
	-- 例4：基于视图创建视图
	CREATE VIEW vu_emp4 
	AS 
	SELECT e_id,d_id
	FROM vu_emp2;
	SELECT *
	FROM vu_emp4;
	
	-- (2)查看视图
		-- [1]查看视图对象
			SHOW TABLES;
		-- [2]查看视图结构
			DESCRIBE vu_emp1;
		-- [3]查看视图属性
			SHOW TABLE STATUS LIKE 'vu_emp1';
		-- [4]查看视图定义信息
			SHOW CREATE VIEW vu_emp1;
	
	-- (3)更新视图
	UPDATE vu_emp4
	SET e_id = 99
	WHERE e_id = 100; -- 更新视图字段id
	SELECT * FROM vu_emp4; 
	SELECT * FROM vu_emp2; 
	SELECT * FROM emps; -- 注：更新视图后，其父级视图、基表的数据均会被修改！
	
	UPDATE emps
	SET employee_id = 100
	WHERE employee_id = 99;
	SELECT * FROM vu_emp4; 
	SELECT * FROM vu_emp2; 
	SELECT * FROM emps; -- 注：同理，更新基表后，其视图、子级视图的数据均会被修改！
	
	-- 注：若视图中存在原表中不存在的字段时，则更新不成功！
	-- 例：vu_emp1视图中的AVG(salary)是计算得来的，原表中不存在，所以更新sal时会报错
	UPDATE vu_emp1
	SET sal = 5000
	WHERE employee_id = 100; -- The target table vu_emp1 of the UPDATE is not updatable
	DELETE FROM vu_emp1
	WHERE employee_id = 100; -- The target table vu_emp1 of the DELETE is not updatable
		-- 无论是更新还是删除都不可以
	-- 不可更新的视图：
		-- 在定义视图的时候指定了“ALGORITHM = TEMPTABLE”，视图将不支持INSERT和DELETE操作；
		-- 视图中不包含基表中所有被定义为非空又未指定默认值的列，视图将不支持INSERT操作；
		-- 在定义视图的SELECT语句中使用了JOIN联合查询，视图将不支持INSERT和DELETE操作；
		-- 在定义视图的SELECT语句后的字段列表中使用了数学表达式或子查询，视图将不支持INSERT，也
		-- 不支持UPDATE使用了数学表达式、子查询的字段值；
		-- 在定义视图的SELECT语句后的字段列表中使用DISTINCT 、聚合函数、GROUP BY 、HAVING 、
		-- UNION 等，视图将不支持INSERT、UPDATE、DELETE；
		-- 在定义视图的SELECT语句中包含了子查询，而子查询中引用了FROM后面的表，视图将不支持
		-- INSERT、UPDATE、DELETE；
		-- 视图定义基于一个不可更新视图；
		-- 常量视图
	
	-- (4)修改视图
		-- 方式一
		CREATE OR REPLACE VIEW vu_emp3 
		AS
		SELECT CONCAT(first_name,' ',last_name) `name`
		FROM emps
		WHERE last_name LIKE '%a%a%';
		SELECT * 
		FROM vu_emp3;
		-- 方式二
		ALTER VIEW vu_emp3
		AS
		SELECT CONCAT(first_name,' ',last_name) `name`
		FROM emps
		WHERE first_name LIKE '%a%';
		SELECT * 
		FROM vu_emp3;
	
	-- (5)删除视图
	DROP VIEW IF EXISTS vu_emp1,vu_emp2,vu_emp3; -- 可同时删除多个视图
		-- 注：基于视图创建的视图，其父级视图被删除时，子级视图会查询失败，需要手动修改！
	
	
	
	-- 3.视图优点
		-- (1) 操作简单
		--     将经常使用的查询操作定义为视图，可以使开发人员不需要关心视图对应的数据表的结构、表与表之间的关联关系，
		-- 也不需要关心数据表之间的业务逻辑和查询条件，而只需要简单地操作视图即可，极大简化了开发人员对数据库的操作。
		-- (2) 减少数据冗余
		--     视图跟实际数据表不一样，它存储的是查询语句。所以，在使用的时候，我们要通过定义视图的查询语句来获取结果
		-- 集。而视图本身不存储数据，不占用数据存储的资源，减少了数据冗余。
		-- (3) 数据安全
		--     MySQL将用户对数据的访问限制在某些数据的结果集上，而这些数据的结果集可以使用视图来实现。用户不必直接查询
		-- 或操作数据表。这也可以理解为视图具有隔离性。视图相当于在用户和实际的数据表之间加了一层虚拟表。同时，MySQL可
		-- 以根据权限将用户对数据的访问限制在某些视图上，用户不需要查询数据表，可以直接通过视图获取数据表中的信息。这在
		-- 一定程度上保障了数据表中数据的安全性。
		-- (4) 适应灵活多变的需求 
		--     当业务系统的需求发生变化后，如果需要改动数据表的结构，则工作量相对较大，可以使用视图来减少改动的工作量。
		-- 这种方式在实际工作中使用得比较多。
		-- (5) 能够分解复杂的查询逻辑 
		--     数据库中如果存在复杂的查询逻辑，则可以将问题进行分解，创建多个视图获取数据，再将创建的多个视图结合起来，
		-- 完成复杂的查询逻辑。
			