--  SELECT语句

	-- 1.没有任何子句
		SELECT 1; 
		SELECT 9.5/2,5/2.4; -- 4.75000，2.0833
		SELECT 9/2; -- 4.5000，注意运算规则！

	-- 2.SELECT 字段1,字段2...(*选择所有字段) FROM 表名
		SELECT * 
		FROM employees;
		SELECT country_id 
		FROM countries;
		-- 查询结果表称为结果集

	-- 3.SELECT 字段 AS(AS可省略) 别名 FROM 表名
		SELECT min_salary ms
		FROM jobs;
		SELECT max_salary AS "最高收入"
		FROM jobs;
		-- 可以在选择时对字段进行计算等操作(KSCJ-60)
		-- 别名只要没有空格分隔，即使是汉字也可以不用引号包括，但别名有空格分隔要用""包括，不要用''！

	-- 4.去除重复行 SELECT DISTINCT 字段...
		-- 注：(1)DISTINCT要放到所有列名前面
		-- 	  (2)DISTINCT是对其后所有字段进行去重
		-- 例：查询所有班级
		SELECT DISTINCT manager_id 
		FROM employees;

	-- 5.空值(NULL)参与运算
		-- 注：(1)NULL!=0、' '、'null';
		-- 	  (2)null运算后结果也为null，用函数IFNULL(null,0)将null->0

	-- 6.着重号``(左上方按键) 作用：将与系统关键字重复的表名转换为正常表名
		-- SqlServer着重号为""或[]
		-- 例：(1)错误写法SELECT * FROM ORDER;
		--     (2)正确写法SELECT * FROM `order`;
		SELECT * FROM `ORDER`;

	-- 7.查询常数，作用在结果集中添加一列或多列注释类内容
		-- 例：下方的'薪资：'就是常数，用于注解
		SELECT DISTINCT '薪资',salary 
		FROM employees; 

	-- 8.显示表结构 DESCRIBE或DESC 表名;(用于获取表各个字段的数据类型)
		DESC employees;
		-- (SqlServer为sp_help 表名;但要保证此语句前无其它语句，否则不能执行！
		
	-- 9.过滤数据 WHERE 条件
		SELECT *
		FROM employees
		WHERE first_name='Steven'
	-- 赋值用双引号，直接输出或查找用单引号
