-- 存储过程与存储函数

	-- 一.分类
		-- 1、没有参数（无参数无返回） 
		-- 2、仅仅带 IN 类型（有参数无返回） 
		-- 3、仅仅带 OUT 类型（无参数有返回） 
		-- 4、既带 IN 又带 OUT（有参数有返回） 
		-- 5、带 INOUT（有参数有返回）
	
	
	
	-- 二.存储过程
		-- DELIMITER $
		-- CREATE PROCEDURE 存储过程名(IN(默认)|OUT|INOUT 参数名 参数类型,...)
		-- [characteristics ...]
		-- BEGIN
		-- sql语句1;
		-- sql语句2;
		-- END $
	-- 1.参数介绍：
	-- (1)参数前面的符号的意思
		-- IN ：当前参数为输入参数，也就是表示入参；存储过程只是读取这个参数的值。如果没有定义参数种类， 默认就是 IN ，表示输入参数。
		-- OUT ：当前参数为输出参数，也就是表示出参；执行完成之后，调用这个存储过程的客户端或者应用程序就可以读取这个参数返回的值了。
		-- INOUT ：当前参数既可以为输入参数，也可以为输出参数。
	-- (2)形参类型可以是 MySQL数据库中的任意类型。
	-- (3)characteristics 表示创建存储过程时指定的对存储过程的约束条件，其取值信息如下：
		-- LANGUAGE SQL ：说明存储过程执行体是由SQL语句组成的，当前系统支持的语言为SQL。
		-- [NOT] DETERMINISTIC ：指明存储过程执行的结果是否确定。DETERMINISTIC表示结果是确定的。每次执行存储过程时，相同的输入会得到相
		-- 			 同的输出。NOT DETERMINISTIC表示结果是不确定的，相同的输入可能得到不同的输出。如果没有指定任意一个值，
		--			 默认为NOT DETERMINISTIC。
		-- { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA } ：指明子程序使用SQL语句的限制。
			-- CONTAINS SQL表示当前存储过程的子程序包含SQL语句，但是并不包含读写数据的SQL语句；
			-- NO SQL表示当前存储过程的子程序中不包含任何SQL语句；
			-- READS SQL DATA表示当前存储过程的子程序中包含读数据的SQL语句；
			-- MODIFIES SQL DATA表示当前存储过程的子程序中包含写数据的SQL语句。默认情况下，系统会指定为CONTAINS SQL。
		-- SQL SECURITY { DEFINER | INVOKER } ：执行当前存储过程的权限，即指明哪些用户能够执行当前存储过程。
			-- DEFINER 表示只有当前存储过程的创建者或者定义者才能执行当前存储过程；
			-- INVOKER 表示拥有当前存储过程的访问权限的用户能够执行当前存储过程。如果没有设置相关的值，则MySQL默认指定值为DEFINER。	
		-- COMMENT 'string' ：注释信息，可以用来描述存储过程。
	-- (4)存储过程体中可以有多条 SQL 语句，如果仅仅一条SQL 语句，则可以省略 BEGIN 和 END	
	-- (5)需要设置新的结束标记	
		-- DELIMITER 新的结束标记，避免使用反斜杠（‘\’）字符

	USE `dbtest_view`;
	
	-- 2.无参数无返回值		
	-- 例1：创建存储过程select_all_data()，查看 emps 表的所有数据
	DELIMITER $ -- 定义新的结束标记(不定义会报错，因为不定义MySQL默认以;结尾，遇到BEGIN END中的查询语句就会结束无法成功创建存储过程)
	CREATE PROCEDURE select_all_data()
	BEGIN
		SELECT * -- 存储过程中执行的SQL语句
		FROM emps;
	END $ -- 结束标记不要忘！
	CALL select_all_data(); -- 调用存储过程
		-- 注：MySQL存储过程执行仅需要选中本语句，多选了空格也会报错！
	-- 例2：创建存储过程show_max_salary()，用来查看“emps”表的最高薪资值。
	DELIMITER // -- 结束标记定义
	CREATE PROCEDURE show_max_salary()
		-- 写明约束条件characteristics
		LANGUAGE SQL -- 说明存储过程执行体是由SQL语句组成的
		NOT DETERMINISTIC --  表示结果是不确定，相同的输入可能得到不同的输出
		CONTAINS SQL -- 表示当前存储过程的子程序包含SQL语句，但是并不包含读写数据的SQL语句；
		SQL SECURITY DEFINER -- 表示只有当前存储过程的创建者或者定义者才能执行当前存储过程；
		COMMENT '查看最高薪资' -- 注释
	BEGIN
		SELECT MAX(salary) 
		FROM emps;
	END //
	CALL show_max_salary();
	
	-- 3.参数带OUT
	-- 例1：创建存储过程show_min_salary()，查看“emps”表的最低薪资值。并将最低薪资通过OUT参数“ms”输出
	DELIMITER $
	CREATE PROCEDURE show_min_salary(OUT ms DOUBLE) -- 声明输出参数 变量 变量数据类型
	BEGIN
		SELECT MIN(salary) INTO ms
		FROM emps;
	END $
	CALL show_min_salary(@ms); -- 声明用户自定义变量@ms
	SELECT @ms; -- 查询变量@ms
	
	-- 4.参数带IN
	-- 例：创建存储过程show_someone_salary()，查看“emps”表的某个员工的薪资，并用IN参数empname输入员工姓名。
	DELIMITER //
	CREATE PROCEDURE show_someone_salary(IN empname VARCHAR(20))
	BEGIN
		SELECT salary 
		FROM emps 
		WHERE last_name = empname;
	END //
	CALL show_someone_salary('King');
	SET @empname ='King'; -- 自定义变量
	CALL show_someone_salary(@empname); -- 用变量作为参数
	
	-- 5.参数同时带IN和OUT
	-- 例：创建存储过程show_someone_salary2()，查看“emps”表的某个员工的薪资，并用IN参数lname输入员工姓名，用OUT参数empsalary输出员工薪资。
	DELIMITER $
	CREATE PROCEDURE show_someone_salary2(IN lname VARCHAR(20),OUT outlname VARCHAR(20),OUT empsalary DECIMAL(10,2))
	BEGIN 
		SELECT last_name,salary INTO outlname,empsalary -- INTO进行多个变量赋值
		FROM emps
		WHERE last_name = lname;
	END $
	CALL show_someone_salary2('Kochhar',@outlname,@empsalary);
	SELECT @outlname AS last_name,@empsalary AS salary;
	
	-- 6.带INOUT
	-- 例：创建存储过程show_mgr_name()，查询某个员工领导的姓名，并用INOUT参数“ename”输入员工姓名，输出领导的姓名。
	DELIMITER $
	CREATE PROCEDURE show_mgr_name(INOUT ename VARCHAR(25))
	BEGIN
		SELECT last_name INTO ename
		FROM emps 
		WHERE  employee_id = (
				     SELECT manager_id
				     FROM emps 
				     WHERE last_name = ename
				     );	
	END $
	SET @ename := 'Abel';
	CALL show_mgr_name(@ename);  -- 注：INOUT类型只能传递参数调用
	SELECT @ename;
	
	-- 7.补充
		-- =
		-- 只有在set和update时才是和:=一样，赋值的作用，其它都是等于的作用。鉴于此，用变量实现行号时，必须用:=
		-- :=
		-- 不只在set和update时是赋值的作用，在select也是赋值的作用。
	
	
	-- 三.存储函数
		-- [SET GLOBAL log_bin_trust_function_creators = 1;]
		-- DELIMITER $
		-- CREATE FUNCTION 函数名(参数名 参数类型,...)
		-- RETURNS 返回值类型
		-- [characteristics ...]
		-- BEGIN
		-- 函数体(函数体中肯定有RETURN语句)
		-- END $
	-- 1.参数介绍
		-- (1)参数列表：指定参数为IN、OUT或INOUT只对PROCEDURE是合法的，FUNCTION中总是默认为IN参数。
		-- (2)RETURNS type 语句表示函数返回数据的类型；RETURNS子句只能对FUNCTION做指定，对函数而言这是强制的。它用来指定函数的返回类型，
		--    而且函数体必须包含一个RETURN value 语句。
		-- (3)characteristic 创建函数时指定的对函数的约束。取值与创建存储过程时相同，这里不再赘述。
		-- (4)函数体也可以用BEGIN…END来表示SQL代码的开始和结束。如果函数体只有一条语句，也可以省略BEGIN…END。
		-- (5)若在创建存储函数中报错“ you might want to use the less safelog_bin_trust_function_creators variable ”，有两种处理方法：
			-- 方式1：加上必要的函数特性“[NOT] DETERMINISTIC”和“{CONTAINS SQL | NO SQL | READS SQL DATA |MODIFIES SQL DATA}”
			-- 方式2：SET GLOBAL log_bin_trust_function_creators = 1;

	-- 2.创建存储函数
	-- 例1：创建存储函数，名称为email_by_name()，参数定义为空，该函数查询Abel的email，并返回，数据类型为字符串型。
	DELIMITER //
	CREATE FUNCTION email_by_name()
	RETURNS VARCHAR(25)
		DETERMINISTIC
		CONTAINS SQL
	BEGIN
		RETURN (
			SELECT email 
			FROM emps 
			WHERE last_name = 'Abel'
			);
	END //
	SELECT email_by_name();
	
	SET GLOBAL log_bin_trust_function_creators=TRUE;
	DELIMITER //
	CREATE FUNCTION aaa(dept_id INT)
	RETURNS INT
	BEGIN	
		RETURN (
			SELECT COUNT(*)
			FROM emps
			WHERE department_id = dept_id
		);
	END //
	SELECT aaa(30);
	
	
	-- 例2：创建存储函数count_by_id()，参数传入dept_id，该函数查询dept_id部门的员工人数，并返回，数据类型为整型。
	DELIMITER $
	CREATE FUNCTION count_by_id(dept_id INT)
	RETURNS INT
	BEGIN
		RETURN (
			SELECT COUNT(*)
			FROM emps
			WHERE department_id = dept_id
			);
	END $	
	SELECT count_by_id(30);
	SET @dept_id := 100;
	SELECT count_by_id(@dept_id);
	
	
	
	-- 四.存储过程和存储函数区别 
		-- 名称      关键字     调用语法        返回值 		   应用场景
		-- 存储过程  PROCEDURE  CALL存储过程()  理解为有0个或多个  一般用于更新
		-- 存储函数  FUNCTION   SELECT函数()    只能是一个 	   一般用于查询结果为一个值并返回时
		
		-- 存储函数可以放在查询语句中使用，存储过程不行。反之，存储过程的功能更加强大，包括能够执行对表
		-- 的操作（比如创建表，删除表等）和事务操作，这些功能是存储函数不具备的。
	
	
	
	-- 五.存储过程和函数的查看、修改、删除
	-- 1.使用SHOW CREATE语句查看存储过程和函数的创建信息
		-- 语法结构：SHOW CREATE PROCEDURE/FUNCTION 存储过程名或函数名;
		SHOW CREATE PROCEDURE select_all_data;
		SHOW CREATE FUNCTION count_by_id;
		
	-- 2.使用SHOW STATUS语句查看存储过程和函数的状态信息	
		-- 语法结构：SHOW PROCEDURE/FUNCTION STATUS [LIKE 'pattern'];
		-- [LIKE 'pattern']：匹配存储过程或函数的名称，可以省略。当省略不写时，会列出MySQL数据库中存在的所有存储过程或函数的信息。
		SHOW PROCEDURE STATUS LIKE 'show%';
		SHOW FUNCTION STATUS;
	
	-- 3.从information_schema.Routines表中查看存储过程和函数的信息
		-- MySQL中存储过程和函数的信息存储在information_schema数据库下的Routines表中。可以通过查询该表的记录来查询存储过程和函数的信息。
		-- 语法结构：
			-- SELECT * FROM information_schema.Routines
			-- WHERE ROUTINE_NAME='存储过程或函数的名' [AND ROUTINE_TYPE = PROCEDURE/FUNCTION];
		-- 如果在MySQL数据库中存在存储过程和函数名称相同的情况，最好指定ROUTINE_TYPE查询条件来指明查询的是存储过程还是函数。
		SELECT * FROM information_schema.Routines
		WHERE ROUTINE_NAME = 'count_by_id' AND ROUTINE_TYPE = 'FUNCTION';
	
	-- 4.修改
		-- 修改存储过程或函数，不影响存储过程或函数功能，只是修改相关特性。使用ALTER语句实现。
		-- 语法结构：ALTER PROCEDURE/FUNCTION 存储过程或函数的名 [characteristic ...]
		-- 其中，characteristic指定存储过程或函数的特性，其取值信息与创建存储过程、函数时的取值信息略有不同。
		-- characteristic参数：
			-- { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
			-- SQL SECURITY { DEFINER | INVOKER }
			-- COMMENT 'string'
				-- CONTAINS SQL ，表示子程序包含SQL语句，但不包含读或写数据的语句。
				-- NO SQL ，表示子程序中不包含SQL语句。
				-- READS SQL DATA ，表示子程序中包含读数据的语句。
				-- MODIFIES SQL DATA ，表示子程序中包含写数据的语句。
				-- SQL SECURITY { DEFINER | INVOKER } ，指明谁有权限来执行。
				-- DEFINER ，表示只有定义者自己才能够执行。
				-- INVOKER ，表示调用者可以执行。
				-- COMMENT 'string' ，表示注释信息。
		-- 例1：修改存储过程show_max_salary的定义。将读写权限改为MODIFIES SQL DATA，并指明调用者可以执行
		ALTER PROCEDURE show_max_salary
		MODIFIES SQL DATA
		SQL SECURITY INVOKER;
		-- 例2：修改存储函数email_by_name的定义。将读写权限改为READS SQL DATA，并加上注释信息“FIND NAME”
		ALTER FUNCTION email_by_name
		READS SQL DATA
		COMMENT 'FIND NAME';
	
	-- 5.删除
		-- 语法结构：DROP PROCEDURE/FUNCTION [IF EXISTS] 存储过程或函数的名
		-- 例：删除存储过程和存储函数
		DELIMITER //
		CREATE PROCEDURE show_salary()
		BEGIN
			SELECT salary 
			FROM emps; 
		END //
		CALL show_salary();
		DELIMITER $
		CREATE FUNCTION emp_id()
		RETURNS INT
		BEGIN
			RETURN (
				SELECT employee_id
				FROM emps
				WHERE employee_id = 100
				);
		END $
		SELECT emp_id();
		DROP PROCEDURE IF EXISTS show_salary;
		DROP FUNCTION IF EXISTS emp_id;
	
	
	
	-- 六.关于存储过程使用的争议
	-- 优点
		-- 1、存储过程可以一次编译多次使用。存储过程只在创建时进行编译，之后的使用都不需要重新编译，这就提升了 SQL 的执行效率。
		-- 2、可以减少开发工作量。将代码封装成模块，实际上是编程的核心思想之一，这样可以把复杂的问题拆解成不同的模块，然后模块
		--    之间可以重复使用，在减少开发工作量的同时，还能保证代码的结构清晰。
		-- 3、存储过程的安全性强。我们在设定存储过程的时候可以设置对用户的使用权限，这样就和视图一样具有较强的安全性。
		-- 4、可以减少网络传输量。因为代码封装到存储过程中，每次使用只需要调用存储过程即可，这样就减少了网络传输量。
		-- 5、良好的封装性。在进行相对复杂的数据库操作时，原本需要使用一条一条的 SQL 语句，可能要连接多次数据库才能完成的操作，
		--    现在变成了一次存储过程，只需要连接一次即可。
	-- 缺点
		-- 1、可移植性差。存储过程不能跨数据库移植，比如在 MySQL、Oracle 和 SQL Server 里编写的存储过程，在换成其他数据库时都
		--    需要重新编写。
		-- 2、调试困难。只有少数 DBMS 支持存储过程的调试。对于复杂的存储过程来说，开发和维护都不容易。虽然也有一些第三方工具可
		--    以对存储过程进行调试，但要收费。
		-- 3、存储过程的版本管理很困难。比如数据表索引发生变化了，可能会导致存储过程失效。我们在开发软件的时候往往需要进行版本
		--    管理，但是存储过程本身没有版本控制，版本迭代更新的时候很麻烦。
		-- 4、它不适合高并发的场景。高并发的场景需要减少数据库的压力，有时数据库会采用分库分表的方式，而且对可扩展性要求很高，
		--    在这种情况下，存储过程会变得难以维护， 增加数据库的压力，显然就不适用了。
