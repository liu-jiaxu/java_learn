 -- 1.多表查询
 
 /*
 一般SQL语句
	SELECT ..., ..., ...
	FROM ...
	WHERE ... AND/OR/NOT ...
	ORDER BY ... ASC/DESC, ..., ...
	LIMIT ..., ...;
 */
 
 -- 为什么不把所有信息存储到一张表，而要分多张表？
 -- 因为所有数据存储在一张表时会造成数据冗余，查询时会造成查询速率下降，同时数据维护会变复杂，无法实现多并发，单表只能进行单线程查询...
 
 -- 多表查询实现
 -- 例：查询多表中的多个属性
 
 -- 错误写法，这样写会将employee_id的所有信息与department_name所有信息依次匹配，最后得到笛卡尔积(查询结果=两者数据数相乘)
 -- 错误原因：缺少了多表的连接条件，直接查询了笛卡尔积
	SELECT employee_id,department_name
	FROM employees,departments;

 -- 改正后：关联查询(先筛选出笛卡尔积，再从其中筛选)
	SELECT employee_id,department_name
	FROM employees,departments
	WHERE employees.`department_id` = departments.department_id; -- 两表之间要想关联，必须有至少一条字段相同！
	-- 这里加``着重号保证系统可以识别某些系统关键字的列名
 
 -- 延伸
 -- 若查询的字段同时出现在两张表中，要指明查询的是哪个表的字段，例如department_id同时在employees,departments两张表中，要用
 -- employees.`department_id`或departments.department_id区分是哪个表的字段
 -- 优化SQL建议：建议多表查询时，每个字段都指明来自于哪个表，既可以防止出错，又可以节省查询时间！
	SELECT employee_id,department_name,employees.department_id
	FROM employees,departments
	WHERE employees.`department_id` = departments.department_id;
 
 -- 多表查询优化SQL
 -- 为防止字段指明表来源造成代码冗余，可以给表起一个简单的别名(一般按顺序用t1、t2、t3...即可)，之后用别名指明字段即可。
 -- 注：若给表起别名，则在SELECT、WHERE中必须使用表的别名，因为别名在系统中已经覆盖了表的原名！
	SELECT t1.employee_id,t2.department_name,t1.department_id -- 2.用表的别名指明
	FROM employees t1,departments t2 -- 1.给表起别名
	WHERE t1.`department_id` = t2.department_id;
 
 -- 例：查询员工的employee_id,last_name,department_name,city并按department_name降序排列
 -- 有n个表实现多表查询，至少需要n-1个关联条件！
	SELECT e.`employee_id`, e.`last_name`, l.`city`
	FROM employees e, departments d, locations l
	WHERE e.`department_id` = d.`department_id` AND d.`location_id` = l.`location_id`
	ORDER BY d.`department_name` DESC, e.`employee_id`;
		-- ORDER BY仅出现在一张表的字段可以不用指定表(例：d.department_name可写成department_name)(一般都加上指定表！)
 
 
 
 -- 2.多表查询分类
 -- (1)等值连接--非等值连接
 -- (2)自连接--非自连接
 -- (3)内连接--外连接
 
 -- (1)等值连接--非等值连接
 -- 等值连接：不同表中的相同字段连接
 -- 非等值连接
	SELECT last_name,salary,grade_level
	FROM employees e,job_grades j
	WHERE e.`salary` BETWEEN j.`lowest_sal` AND j.`highest_sal`; -- 非等值连接
 
 -- (2)自连接--非自连接
 -- 自连接：自己连接自己(例：一条记录的某属性值又对应其他记录的值)
	-- 例：查询员工id，姓名及管理者id和姓名(员工有对应的经理编号，经理也有自己的编号，同一个表中自连接)
		SELECT emp.employee_id,emp.last_name,mgr.employee_id,mgr.last_name
		FROM employees emp,employees mgr
		WHERE emp.`manager_id`=mgr.`employee_id`;
 
 -- (3)内连接--外连接
 -- 内连接：合并具有同一列的多个表，但结果不包含不匹配的行
	-- 例：普通内连接
		SELECT employee_id,department_name
		FROM employees,departments
		WHERE employees.`department_id` = departments.department_id;
		
	-- 例：(INNER) JOIN ON实现内连接
		SELECT last_name,department_name
		FROM employees e INNER JOIN departments d
		ON e.`department_id`=d.`department_id`;
		
 -- 外连接：合并具有同一列的多个表，结果包含匹配的行和多个表不匹配的行
	-- 左外连接：查询结果包含匹配行与不匹配行的左表(左表记录全部记录，右表仅保留符合条件的记录，无记录为null)
	-- 右外连接：查询结果包含匹配行与不匹配行的右表
	-- 满外连接：查询结果包含匹配行与不匹配行的表
	
	-- 例：查询所有员工的last_name,department_name,city信息(所有信息用外连接，SQL99语法 (OUTER) JOIN ON实现)
		SELECT last_name,department_name,city
		FROM employees e LEFT OUTER JOIN departments d -- 左外连接LEFT (OUTER) JOIN
		ON e.`department_id`=d.`department_id`
		RIGHT OUTER JOIN locations l -- 右外连接RIGHT (OUTER) JOIN 
		ON d.`location_id`=l.`location_id`;
			-- JOIN ON实现主表连接另一个表，多个JOIN ON可连接多个表
			
	-- 例：满外连接(MySQL不识别FULL，所以不能用，其他数据库可以)
		-- SELECT last_name,department_name
		-- FROM employees e FULL OUTER JOIN departments d
		-- ON e.`department_id`=d.`department_id`;
 
 
 
 -- SQL实现七种JOIN操作

	-- UNION将多条SELECT语句组合成单个结果集，合并时保证两表列数和数据类型必须相同并对应
	-- UNION：返回结果去重
	-- UNION ALL：返回结果不去重(效率高，一般用)
 
	#内连接 A∩B
		SELECT employee_id,last_name,department_name
		FROM employees e JOIN departments d
		ON e.`department_id` = d.`department_id`;
	
	#左外连接
		SELECT employee_id,last_name,department_name
		FROM employees e LEFT JOIN departments d
		ON e.`department_id` = d.`department_id`;
		
	#右外连接
		SELECT employee_id,last_name,department_name
		FROM employees e RIGHT JOIN departments d
		ON e.`department_id` = d.`department_id`;
	
	#查询A表并去除A表与B表的重复数据 A - A∩B
		SELECT employee_id,last_name,department_name
		FROM employees e LEFT JOIN departments d
		ON e.`department_id` = d.`department_id`
		WHERE d.`department_id` IS NULL;
		
	#查询B表并去除A表与B表的重复数据 B-A∩B
		SELECT employee_id,last_name,department_name
		FROM employees e RIGHT JOIN departments d
		ON e.`department_id` = d.`department_id`
		WHERE e.`department_id` IS NULL;
		
		SELECT employee_id,last_name,department_name
		FROM employees e RIGHT JOIN departments d
		ON e.`department_id` = d.`department_id` 
		WHERE e.`department_id`IS NULL;
		
	#满外连接 A∪B
		#实现方式：(A-A∩B) UNION (ALL) B 或 (B-A∩B) UNION (ALL) A
		SELECT employee_id,last_name,department_name
		FROM employees e LEFT JOIN departments d
		ON e.`department_id` = d.`department_id`
		WHERE d.`department_id` IS NULL
		UNION ALL #没有去重操作，效率高(两表无重复数据使用)
		SELECT employee_id,last_name,department_name
		FROM employees e RIGHT JOIN departments d
		ON e.`department_id` = d.`department_id`;

	#查询两表之间不同的数据
		#实现方式：A∪B - A∩B 或 (A - A∩B) ∪（B - A∩B）
		SELECT employee_id,last_name,department_name
		FROM employees e LEFT JOIN departments d
		ON e.`department_id` = d.`department_id`
		WHERE d.`department_id` IS NULL
		UNION ALL
		SELECT employee_id,last_name,department_name
		FROM employees e RIGHT JOIN departments d
		ON e.`department_id` = d.`department_id`
		WHERE e.`department_id` IS NULL;
	
 
 
 -- SQL99语法新特性
	-- 1.自然连接 NATURAL JOIN：自动连接两张表的所有相同字段，然后等值连接
		SELECT employee_id,last_name,department_name
		FROM employees e NATURAL JOIN departments d;
	-- 2.USING连接：指定表中的同名字段进行等值连接，只能配合JOIN使用
		SELECT employee_id,last_name,department_name
		FROM employees e JOIN departments d
		USING (department_id);
 