-- 聚合函数(也称组函数)

	-- 常见的聚合函数
	-- GROUP BY的使用 
	-- HAVING的使用
	-- SQL底层执行原理
	
	-- SQL语句完整结构
		-- select ...,...
		-- from ...,...
		-- where 多表连接条件 AND 非聚合函数过滤条件
		-- group by ...,... 分组
		-- having 聚合函数过滤条件
		-- order by ...,... 排序(desc)
		-- limit ...,... 分页

-- 一.常见的聚合函数
	
	-- 注：MySQL中聚合函数不能嵌套，单行函数可以嵌套	
	
	-- 1.1 MAX最大值 MIN最小值 AVG平均值 SUM求和
	SELECT MAX(salary),MIN(salary),AVG(salary),SUM(salary)
	FROM employees; -- AVG和SUM函数仅用于数值型
	SELECT MAX(last_name),MIN(last_name)
	FROM employees; -- 字符串可以使用max和min函数
 
	-- 1.2 COUNT计算指定字段在查询结构中出现的个数
		-- 注：(1)COUNT(*)或COUNT(1)或COUNT(非空字段)计算表中所有列数(记录)
		--     (2)使用MYISAM存储引擎：三者效率相同，均为O(1)；使用InnoDB存储引擎，COUNT(*) = COUNT(1) > COUNT(字段)
	SELECT COUNT(employee_id),COUNT(*)
	FROM employees; 
		-- 注：(1)SUM = AVG * COUNT
		--     (2)AVG、SUM、COUNT函数均不计算null值
	-- 例：计算commission_pct平均值
		-- 错误示例：(未包含null值！)
		SELECT AVG(commission_pct)
		FROM employees;
		-- 正确示例：(将null值归为0计算)
		SELECT SUM(commission_pct)/COUNT(1)
		FROM employees;



-- 二.GROUP BY的使用--分组

	-- 作用：将一个属性下的多个相同字段分为一组
	-- 注：null值参与分组！
	
	-- 2.1单列分组
	-- 例：查询各个部门的平均工资，最高工资
	SELECT department_id,AVG(salary),SUM(salary), COUNT(1)
	FROM employees
	GROUP BY department_id; -- 将department_id中相同值归为一组，再分别计算AVG、SUM

	-- 2.2多个列分组
	-- 例：查询department_id、job_id平均工资
	SELECT job_id,department_id,AVG(salary), COUNT(1)
	FROM employees
	GROUP BY department_id,job_id; -- 前后顺序无关
	-- 注：SELECT中非聚合函数的字段必须声明在GROUP BY中！
		-- 例：错误写法
		SELECT job_id,department_id,AVG(salary)
		FROM employees
		GROUP BY department_id; -- job_id字段必须声明在GROUP BY中！

	-- 2.3附带计算所有组数据平均值：WITH ROLLUP(注：不能与ORDER BY排序同时使用！)
	-- 例：
	SELECT department_id,AVG(salary)
	FROM employees
	GROUP BY department_id WITH ROLLUP;
	
	
	
-- 三.HAVING的使用

	-- 作用：过滤数据
	
	-- 3.1使用前提：
		-- (1)若过滤条件中存在聚合函数，则必须使用HAVING替换WHERE，否则报错
		-- (2)HAVING必须声明在GROUP BY的后面
		-- (3)在没有GROUP BY的情况下一般不使用HAVING(没有必要)，因为数据未分组查询后结果只有一条，没有必要使用HAVING了
			-- 例：错误写法
			SELECT department_id,MAX(salary)
			FROM employees
			WHERE MAX(salary) > 10000 -- 过滤条件中存在聚合函数
			GROUP BY department_id;
			-- 例：正确写法
			SELECT department_id,MAX(salary)
			FROM employees
			GROUP BY department_id
			HAVING MAX(salary) > 10000;

	-- 3.2  WHERE与HAVING语句执行效率对比
	-- 例：	查询部门id为10、20、30、40四个部门中最高工资高于10000的部门信息
		-- 方式一：优先选用，效率更优
		SELECT department_id,MAX(salary)
		FROM employees
		WHERE department_id IN (10,20,30,40)
		GROUP BY department_id
		HAVING MAX(salary) > 10000; 
		-- 方式二：
		SELECT department_id,MAX(salary)
		FROM employees
		GROUP BY department_id
		HAVING department_id IN(10,20,30,40) AND MAX(salary) > 10000; 



-- 四.SQL底层执行原理

	-- 1.SQL语句完整结构
		-- select ...,...
		-- from ...,...(SQL99支持JOIN ... ON ...连接多表)
		-- where 多表连接条件 AND 非聚合函数过滤条件
		-- group by ...,... 分组
		-- having 聚合函数过滤条件
		-- order by ...,... 排序(desc)
		-- limit ...,... 分页

	-- 2.SQL语句执行顺序
		-- SELECT DISTINCT player_id, player_name, count(*) as num 顺序 5
		-- FROM player JOIN team ON player.team_id = team.team_id  顺序 1
		-- WHERE height > 1.80  				   顺序 2
		-- GROUP BY player.team_id  				   顺序 3
		-- HAVING num > 2  					   顺序 4
		-- ORDER BY num DESC  					   顺序 6
		-- LIMIT 2  						   顺序 7

/*		
SELECT 是先执行 FROM 这一步的。在这个阶段，如果是多张表联查，还会经历下面的几个步骤：
	1. 首先先通过 CROSS JOIN 求笛卡尔积，相当于得到虚拟表 vt（virtual table）1-1；
	2. 通过 ON 进行筛选，在虚拟表 vt1-1 的基础上进行筛选，得到虚拟表 vt1-2；
	3. 添加外部行。如果我们使用的是左连接、右链接或者全连接，就会涉及到外部行，也就是在虚拟表 vt1-2 的基础上增加外部行，得到虚拟表 vt1-3。
	当然如果我们操作的是两张以上的表，还会重复上面的步骤，直到所有表都被处理完为止。这个过程得到是我们的原始数据。
	
当我们拿到了查询数据表的原始数据，也就是最终的虚拟表 vt1 ，就可以在此基础上再进行 WHERE 阶段。在这个阶段中，会根据 vt1 表的结果进行筛选过滤，得到虚拟表 vt2 。
    
然后进入第三步和第四步，也就是 GROUP 和 HAVING 阶段。在这个阶段中，实际上是在虚拟表 vt2 的基础上进行分组和分组过滤，得到中间的虚拟表 vt3 和 vt4 。
    
当我们完成了条件筛选部分之后，就可以筛选表中提取的字段，也就是进入到 SELECT 和 DISTINCT 阶段。
	首先在 SELECT 阶段会提取想要的字段，然后在 DISTINCT 阶段过滤掉重复的行，分别得到中间的虚拟表vt5-1 和 vt5-2 。
	当我们提取了想要的字段数据之后，就可以按照指定的字段进行排序，也就是 ORDER BY 阶段，得到虚拟表 vt6 。
	最后在 vt6 的基础上，取出指定行的记录，也就是 LIMIT 阶段，得到最终的结果，对应的是虚拟表 vt7 。
	当然我们在写 SELECT 语句的时候，不一定存在所有的关键字，相应的阶段就会省略。
	
SQL 是一门类似英语的结构化查询语言，所以我们在写 SELECT 语句的时候，还要注意相应的关键字顺序，所谓底层运行的原理，就是执行顺序。
*/
