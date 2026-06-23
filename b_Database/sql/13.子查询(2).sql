-- 子查询

	-- 在SQL的SELECT语句中，除了GROUP BY和LIMIT之外都可以声明子查询。

	-- 7.相关子查询(关联子查询)
		-- 如果子查询的执行依赖于外部查询，通常情况下都是因为子查询中的表用到了外部的表，并进行了条件关联，
		-- 因此每执行一次外部查询，子查询都要重新计算一次，这样的子查询就称之为关联子查询。
	
	-- 例1：查询员工的id,salary,按照department_name 排序
	SELECT employee_id,salary
	FROM employees e
	ORDER BY (
		  SELECT department_name
		  FROM departments d
		  WHERE e.`department_id` = d.`department_id`
		  );
		
	-- 例2：若employees表中employee_id与job_history表中employee_id相同的数目不小于2，输出这些相同id的员工的employee_id,last_name和其job_id
	SELECT employee_id,last_name,job_id
	FROM employees 
	WHERE 2 <= (
		    SELECT COUNT(*)
		    FROM job_history j
		    WHERE j.`employee_id` = employees.`employee_id`
		    );
	
	
	
	-- 8.关键字EXISTS & NOT EXISTS
		-- EXISTS：如果在子查询中不存在满足条件的行：条件返回FALSE，继续在子查询中查找
		--     	   如果在子查询中存在满足条件的行：条件返回TRUE，不在子查询中继续查找
		-- NOT EXISTS：关键字表示如果不存在某种条件，则返回TRUE，否则返回FALSE。
	
	-- 例1：查询公司管理者的employee_id，last_name，job_id，department_id信息
	-- 方式一：自连接
	SELECT DISTINCT e1.employee_id, e1.last_name, e1.job_id, e1.department_id
	FROM employees e1 JOIN employees e2
	WHERE e1.employee_id = e2.manager_id;
	-- 方式二：EXISTS
	SELECT employee_id, last_name, job_id, department_id
	FROM employees e1
	WHERE EXISTS (
		      SELECT *
		      FROM employees e2
		      WHERE e1.employee_id = e2.manager_id
		      );
	
	-- 例2：查询departments表中，不存在于employees表中的部门的department_id和department_name
	SELECT department_id,department_name
	FROM departments d
	WHERE NOT EXISTS(SELECT *
			 FROM employees e
			 WHERE d.`department_id` = e.`department_id` 
			 );
	
	
	
	-- 9.相关更新
		UPDATE table1 alias1
		SET COLUMN = (SELECT expression
		FROM table2 alias2
		WHERE alias1.column = alias2.column);
		
	
	
	-- 10.相关删除
		DELETE FROM table1 alias1
		WHERE COLUMN operator (SELECT expression
		FROM table2 alias2
		WHERE alias1.column = alias2.column);
	