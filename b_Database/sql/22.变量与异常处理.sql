-- 变量与异常处理

	-- 一.系统变量
		-- 网址：https://dev.mysql.com/doc/refman/8.0/en/server-systemvariables.html
		-- 全局系统变量（需要添加global关键字）静态变量属于特殊的全局系统变量。
		-- 会话系统变量（默认，需要添加session关键字）
			-- 全局系统变量针对于所有会话（连接）有效，但不能跨重启
			-- 会话系统变量仅针对于当前会话（连接）有效。当前会话对某个会话系统变量值的修改，不会影响其他会话同一个会话系统变量的值。
			-- 会话1对某个全局系统变量值的修改会导致其余会话中同一个全局系统变量值的修改。
	
	-- 1.查看系统变量
	-- (1)查看所有或部分系统变量
		-- 查看所有全局变量
		SHOW GLOBAL VARIABLES; -- 632条
		-- 查看所有会话变量
		SHOW SESSION VARIABLES; -- 655条
			-- 或SHOW VARIABLES;
		
		-- 查看满足条件的部分系统变量/会话变量。
		SHOW GLOBAL/SESSION VARIABLES LIKE '%标识符%';
		-- 例：
		SHOW GLOBAL VARIABLES LIKE 'admin_%';
		SHOW VARIABLES LIKE 'default%'; -- 默认会话系统变量
	-- (2)查看指定系统变量
		-- 查看指定的系统变量的值
		SELECT @@global.变量名;
		-- 查看指定的会话变量的值
		SELECT @@session.变量名;
		-- 例：
		SELECT @@global.max_connections;
	
	-- 2.修改系统变量的值
		-- 方式1：修改MySQL配置文件，继而修改MySQL系统变量的值(该方法需要重启MySQL服务，一劳永逸，单重启服务不方便)
		-- 方式2：在MySQL服务运行期间，使用“set”命令重新设置系统变量的值
	-- 为某个系统变量赋值
	-- 方式1：
	SET @@global.变量名=变量值;
	-- 方式2：
	SET GLOBAL 变量名=变量值;
	
	-- 为某个会话变量赋值	
	-- 方式1：
	SET @@session.变量名=变量值;
	-- 方式2：
	SET SESSION 变量名=变量值;
	
	-- 例：
	SELECT @@global.autocommit;
	SET GLOBAL autocommit=0; -- 当系统服务重启后全局变量设置失效，恢复默认值
	SELECT @@session.default_table_encryption;
	SET @@session.default_table_encryption='ON'; -- 当建立新连接或重启服务后会话系统变量设置失效，恢复默认值
	
	-- 3.补充：全局变量的持久化
		-- 使用SET GLOBAL语句设置的变量值只会临时生效。数据库重启后，服务器又会从MySQL配置文件中读取变量的默认值。 
		-- MySQL 8.0版本新增了SET PERSIST 命令，使得设置过的全局变量在数据库重启后依然保持设置的值不变。
	-- 例：设置服务器的最大连接数为1000，保证重启数据库后设置的值不变：
	SET GLOBAL max_connections = 1000; -- 这样数据库重启后会恢复默认值
	SET PERSIST GLOBAL max_connections = 1000;
	
	
	
	-- 二.用户变量
		-- 会话用户变量：作用域和会话变量一样，只对当前连接会话有效。需用@声明
		-- 局部变量：只在BEGIN和END语句块中有效。局部变量只能在存储过程和函数中使用。不需用@声明
	
	-- 1.会话用户变量
	-- (1)定义
		-- 方式1：“=”或“:=”(推荐用:=，不用区分赋值和比较)
		SET @用户变量 = 值;
		SET @用户变量 := 值;
		-- 方式2：“:=” 或 INTO关键字
		SELECT @用户变量 := 表达式 [FROM 等子句];
		SELECT 表达式 INTO @用户变量 [FROM 等子句];
		-- 例：
		SET @a := 2;
		SELECT employee_id INTO @eid
		FROM emps
		WHERE last_name = 'Abel';
		SET @aa := @a + @eid;	
		
	-- (2)查看、比较、运算等
		-- SELECT @用户变量
		-- 例：
		SELECT @a,@eid,@aa,@b; -- 1  174  175  NULL
		SELECT AVG(salary) INTO @avgsalary FROM emps;
		SELECT @avgsalary;
	
	-- 2.局部变量
		-- 定义：可以使用DECLARE 语句定义一个局部变量
		-- 作用域：仅仅在定义它的 BEGIN ... END 中有效
		-- 位置：只能放在 BEGIN ... END 中，而且只能放在第一句
		DELIMITER //
		CREATE PROCEDURE 存储过程名()
		BEGIN
			-- 声明局部变量
			DECLARE 变量名1 变量数据类型 [DEFAULT 变量默认值];
			DECLARE 变量名2,变量名3,... 变量数据类型 [DEFAULT 变量默认值];
			-- 为局部变量赋值
			SET 变量名1 := 值;
			SELECT 值 INTO 变量名2 [FROM 子句];
			-- 查看局部变量的值
			SELECT 变量1,变量2,变量3;
		END //
		
		-- 例1：声明局部变量，并分别赋值为employees表中employee_id为102的last_name和salary
		DELIMITER //
		CREATE PROCEDURE set_value()
		BEGIN
			DECLARE emp_name VARCHAR(25);
			DECLARE sal DOUBLE(10,2); -- 声明
			SELECT last_name,salary INTO emp_name,sal -- 赋值
			FROM emps
			WHERE employee_id = 102;
			SELECT emp_name,sal; -- 查询
		END //
		CALL set_value();
		
		-- 例2：声明两个变量，求和并打印 （分别使用会话用户变量、局部变量的方式实现）
		-- 用户变量
		SET @a2 :=1;
		SET @b2 :=1; -- 注：保证一次会话的变量值名称不同！
		SET @num := @a2 + @b2; 
		SELECT @num;
		-- 局部变量
		DELIMITER $
		CREATE PROCEDURE num()
		BEGIN
			DECLARE a3 INT;
			DECLARE b3 INT;
			DECLARE num2 INT;
			SET num2 := a3 + b3;
			SELECT num2;
		END $
		CALL num();
		
		-- 例3：创建存储过程different_salary查询某员工和他领导的薪资差距，并用IN参数emp_id接收员工id，用OUT参数dif_salary输出薪资差距结果。
		
		DELIMITER $
		CREATE PROCEDURE different_salary(IN emp_id INT,OUT dif_salary DOUBLE(10,3))
		BEGIN
			DECLARE emp_sal DOUBLE DEFAULT 0.0; -- 定义员工薪资，默认值为0
			DECLARE mgr_sal DOUBLE DEFAULT 0.0; -- 定义管理者薪资
			DECLARE mgr_id INT DEFAULT 0; -- 定义员工的管理者id
			SELECT salary INTO emp_sal FROM emps WHERE employee_id = emp_id; -- 查询员工薪资
			SELECT manager_id INTO mgr_id FROM emps WHERE employee_id = emp_id; -- 查询管理者id
			SELECT salary INTO mgr_sal FROM emps WHERE employee_id = mgr_id; -- 通过员工的管理者id查询管理者薪资
			SET dif_salary := mgr_sal - emp_sal; -- 赋值计算薪资差距
		END $ 
		CALL different_salary(105,@dif_salary);
		SELECT @dif_salary; -- 卧槽太特码难了。。。
	
	-- 三.定义条件与处理程序(异常处理)
	-- 1.定义条件(可选)
		-- 定义条件就是给MySQL中的错误码命名，这有助于存储的程序代码更清晰。它将一个错误名字和指定的错误条件关联起来。
		-- 这个名字可以随后被用在定义处理程序的DECLARE HANDLER 语句中。
		-- 语法格式：
			DECLARE 错误名称 CONDITION FOR [SQLSTATE] 错误码（或错误条件）
			-- 错误码：MySQL_error_code和sqlstate_value都可以表示MySQL的错误。
				-- (1)MySQL_error_code是数值类型错误代码。
				-- (2)sqlstate_value是长度为5的字符串类型错误代码。
				-- 注：sqlyog中只能看见MySQL_error_code错误！
			-- 例如，在ERROR 1418 (HY000)中，1418是MySQL_error_code，'HY000'是sqlstate_value。
			-- 例如，在ERROR 1142（42000）中，1142是MySQL_error_code，'42000'是sqlstate_value。
			
		-- 例1：定义“Field_Not_Be_NULL”错误名与MySQL中违反非空约束的错误类型是“ERROR 1048 (23000)”对应。
		-- 使用MySQL_error_code
		DECLARE Field_Not_Be_NULL CONDITION FOR 1048;
		-- 使用sqlstate_value
		DECLARE Field_Not_Be_NULL CONDITION FOR SQLSTATE '23000'; -- 加上SQLSTATE为防止和MySQL_error_code混淆
		
		-- 例2：定义"ERROR 1148(42000)"错误，名称为command_not_allowed。
		-- 使用MySQL_error_code
		DECLARE command_not_allowed CONDITION FOR 1148;
		-- 使用sqlstate_value
		DECLARE command_not_allowed CONDITION FOR SQLSTATE '42000';
	
	-- 2.定义处理程序(解决异常)
		-- 可以为SQL执行过程中发生的某种类型的错误定义特殊的处理程序。
		-- 语法格式：
			DECLARE 处理方式 HANDLER FOR 错误类型 处理语句
			-- 处理方式：处理方式有3个取值：CONTINUE、EXIT、UNDO。
				-- CONTINUE ：表示遇到错误不处理，继续执行。
				-- EXIT ：表示遇到错误马上退出。
				-- UNDO ：表示遇到错误后撤回之前的操作。MySQL中暂时不支持这样的操作。
			-- 错误类型（即条件）可以有如下取值：(一般使用前三种，对应上述的定义条件)
				-- SQLSTATE '字符串错误码' ：表示长度为5的sqlstate_value类型的错误代码；
				-- MySQL_error_code ：匹配数值类型错误代码；
				-- 错误名称：表示DECLARE ... CONDITION定义的错误条件名称。
				-- SQLWARNING ：匹配所有以01开头的SQLSTATE错误代码；
				-- NOT FOUND ：匹配所有以02开头的SQLSTATE错误代码；
				-- SQLEXCEPTION ：匹配所有没有被SQLWARNING或NOT FOUND捕获的SQLSTATE错误代码；
			-- 处理语句：如果出现上述条件之一，则采用对应的处理方式，并执行指定的处理语句。语句可以是像“ SET 变量 = 值”
			--	     这样的简单语句，也可以是使用BEGIN ... END 编写的复合语句。
		-- 错误类型的六种选择：
			-- 方法1：捕获sqlstate_value
			DECLARE CONTINUE HANDLER FOR SQLSTATE '42S02' SET @info = 'NO_SUCH_TABLE';
			-- 方法2：捕获mysql_error_value
			DECLARE CONTINUE HANDLER FOR 1146 SET @info = 'NO_SUCH_TABLE';
			-- 方法3：先定义条件，再调用
			DECLARE no_such_table CONDITION FOR 1146;
			DECLARE CONTINUE HANDLER FOR NO_SUCH_TABLE SET @info = 'NO_SUCH_TABLE';
			-- 方法4：使用SQLWARNING
			DECLARE EXIT HANDLER FOR SQLWARNING SET @info = 'ERROR';
			-- 方法5：使用NOT FOUND
			DECLARE EXIT HANDLER FOR NOT FOUND SET @info = 'NO_SUCH_TABLE';
			-- 方法6：使用SQLEXCEPTION
			DECLARE EXIT HANDLER FOR SQLEXCEPTION SET @info = 'ERROR';
		
	-- 3.示例：在存储过程中定义处理程序，捕获sqlstate_value值，当遇到MySQL_error_code值为1048时执行CONTINUE操作，并且将@proc_value的值设置为-1。	
	ALTER TABLE depts
	ADD CONSTRAINT uk_dept_name UNIQUE(department_id); -- 添加唯一性约束
	
	DELIMITER //
	CREATE PROCEDURE InsertDataWithCondition()
	BEGIN
		-- 处理程序
		DECLARE duplicate_entry CONDITION FOR 1062 ; -- 定义条件duplicate_entry
		DECLARE CONTINUE HANDLER FOR duplicate_entry SET @proc_value = -1; -- CONTINUE：遇到错误继续执行，并将@proc_value的值设置为-1
		-- 测试
		SET @x = 1;
		INSERT INTO depts(department_name) VALUES('测试');
		SET @x = 2; 
		INSERT INTO depts(department_name) VALUES('测试'); -- 未定义处理程序时，违反唯一性定义，程序报错停止运行，最终@x = 2; 
		SET @x = 3;
	END //
	DROP PROCEDURE IF EXISTS InsertDataWithCondition;
	CALL InsertDataWithCondition(); -- 定义处理程序后调用不会报错，而是继续执行
	SELECT @x,@proc_value; -- 3  -1
	
	DELIMITER //
	CREATE PROCEDURE InsertDataWithCondition2()
	BEGIN
		-- 处理程序
		DECLARE duplicate_entry CONDITION FOR 1062 ; -- 定义条件duplicate_entry
		DECLARE EXIT HANDLER FOR duplicate_entry SET @proc_value = -1; -- EXIT：遇到错误直接退出，并将@proc_value的值设置为-1
		-- 测试
		SET @x = 1;
		INSERT INTO depts(department_name) VALUES('测试');
		SET @x = 2; 
		INSERT INTO depts(department_name) VALUES('测试'); -- 未定义处理程序时，违反唯一性定义，程序报错停止运行，最终@x = 2; 
		SET @x = 3;
	END //
	DROP PROCEDURE IF EXISTS InsertDataWithCondition2;
	CALL InsertDataWithCondition2(); -- 定义处理程序后调用报错后直接退出
	SELECT @x,@proc_value; -- 1  -1 因为之前已经添加了数据，所以此时一添加数据就会违反唯一性约束报错， @x = 1;
	