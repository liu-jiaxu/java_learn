-- 排序与分页

-- 1.排序(无排序下系统按原先顺序排序)
	-- ORDRE BY 字段，默认升序排列
	-- 升序：ORDRE BY 字段 (ASC)
	-- 降序：ORDRE BY 字段 DESC
	-- 注：NULL参与排序时一般会有NULL<0
	
	-- (1)排序与条件搭配使用
	-- 例：查询department_id是50/60，salary大于等于6000且按年薪降序排列
		SELECT employee_id,salary,salary*12 AS annual_sal,department_id
		FROM employees
		WHERE department_id IN (50,60) AND salary>=6000 -- 注意WHERE的位置在FROM之后，ORDER BY之前
		ORDER BY annual_sal DESC; -- 列的别名可以在ORDER BY中使用，但不能在WHERE中使用
	
	-- (2)二级排序
	-- 例：显示员工信息，先按department_id降序排列，再按salary升序排列
		SELECT employee_id,salary,salary*12 AS annual_sal,department_id
		FROM employees
		ORDER BY department_id DESC,salary ASC; -- 先降序排department_id，之后若department_id相同时按salary升序排
	


-- 2.分页：使用LIMIT实现数据分页显示
	-- LIMIT p,c; 原有结果集中从p(包括p，p为索引从0开始)开始共显示c个数据，其余不显示
	-- LIMIT 0,c; 等价于 LIMIT c;(偏移量为0时可省略不写)
	-- LIMIT位于ORDER BY之后
	
	-- (1)例：从第二个数据之后开始，每页显示20条记录
		SELECT employee_id,last_name
		FROM employees
		LIMIT 2,20;
		
	-- (2)分页显示，每页显示30条记录
	-- 第一页
		SELECT employee_id,last_name
		FROM employees
		LIMIT 0,30;
	-- 第二页
		SELECT employee_id,last_name
		FROM employees
		LIMIT 30,30;
	-- 第三页
		SELECT employee_id,last_name
		FROM employees
		LIMIT 60,30;
	-- 第n页：显示pageSize条记录，第pageNO页
	-- LIMIT (pageNO-1)*pageSize,pageSize;
	
	-- (3)综合使用WHERE/ORDER BY/LIMIT
		SELECT employee_id,salary,salary*12 AS annual_sal,department_id
		FROM employees
		WHERE salary BETWEEN 6000 AND 8000
		ORDER BY department_id DESC,salary ASC
		LIMIT 0,5;
	
	-- (4)MySQL8.0新特性：LIMIT c OFFSET p 交换了两个参数的位置
		SELECT employee_id,last_name
		FROM employees
		LIMIT 30 OFFSET 0; -- 相当于LIMIT 0,30;
		
	-- (5)LIMIT查询最值问题
	-- 例：查询salary中最高者
		SELECT employee_id,last_name,salary
		FROM employees
		ORDER BY salary DESC
		LIMIT 1; -- 偏移量为0时可省略不写，相当于LIMIT 0,1;
		-- 或用MAX
		SELECT employee_id,last_name,MAX(salary) AS salary
		FROM employees

	-- (6)LIMIT不同数据库拓展，写法不同
	-- 例：Oracle中：用隐藏行rownum显示行数，再用条件语句筛选
		-- SELECT rownum，employee_id,last_name,salary
		-- FROM employees 
		-- WHERE rownum<=10;
		