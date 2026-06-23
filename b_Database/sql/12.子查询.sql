-- 子查询(内查询)

	-- 1.子查询示例
	-- 例：查询谁的工资高于Abel并且查询Abel的工资
	SELECT last_name,salary -- 外(主)查询
	FROM employees
	WHERE salary >= ( -- 便于可读性，子查询写在比较条件右侧
			SELECT salary -- 内(子)查询：先于外查询执行，结果被主查询使用
			FROM employees
			WHERE last_name = 'Abel'
			);
			
			
			
	-- 2.子查询分类
		-- (1)子查询返回结果的条目数
		--    单行子查询(1条) and 多行子查询(多条)
		-- (2)子查询是否被执行多次
		--    相关子查询(多次) and 不相关子查询(1次)
		
		
			
	-- 3.单行子查询(均为不相关子查询)
	-- 例1：查询工资大于149号员工工资的员工信息
		-- 子查询：先找定语和主语，定语是子查询，主语是外查询,可以先将子查询和主查询分开，先测试子查询之后嵌套进主查询
	SELECT *
	FROM employees
	WHERE salary > (
			SELECT salary
			FROM employees
			WHERE employee_id = 149
			);
			
	-- 例2：查询与141号或174号员工的manager_id和department_id相同的其他员工的employee_id，manager_id，department_id		
	-- 我写的...很好...
	SELECT employee_id,manager_id,department_id
	FROM employees 
	WHERE manager_id IN (
			    SELECT manager_id
			    FROM employees
			    WHERE employee_id IN (141,174) -- 注：子查询有多个结果只能用in，=会报错：Subquery returns more than 1 row
			    )
	AND department_id IN (
			     SELECT department_id
			     FROM employees
			     WHERE employee_id IN (141,174)
			     )
	AND employee_id NOT IN(174,141); -- 注意是其他员工
	-- 成对比较(了解即可)
	SELECT employee_id,manager_id,department_id
	FROM employees 
	WHERE (manager_id, department_id) IN (
					      SELECT manager_id, department_id
					      FROM employees
					      WHERE employee_id IN (141,174)
					      )
	AND employee_id NOT IN (141,174);
	
	-- 例3：查询最低工资大于110号部门最低工资的部门id和其最低工资
	-- HAVING中使用子查询
	SELECT department_id,MIN(salary)
	FROM employees
	WHERE department_id IS NOT NULL
	GROUP BY department_id
	HAVING MIN(salary) > (
			      SELECT MIN(salary)
			      FROM employees
			      WHERE department_id = 110
			      );
	
	-- 例4：显示员工的employee_id,last_name和location。其中，若员工department_id与location_id为1800的department_id相同，
	--      则location为’Canada’，其余则为’USA’。(需求真特么长...)
	-- 在CASE表达式中使用单列子查询
	SELECT employee_id,last_name,CASE department_id WHEN (
							       SELECT department_id
							       FROM departments
							       WHERE location_id = 1800
							       ) THEN 'Canada'
							ELSE 'USA' 
							END AS "location"
	FROM employees;
	
	
	-- 4.单行子查询问题
		-- (1)子查询中的空值问题
			SELECT last_name, job_id
			FROM employees
			WHERE job_id =
			(SELECT job_id
			FROM employees
			WHERE last_name = 'Haas'); -- 子查询为空值
			
		-- (2)非法使用子查询
			SELECT employee_id, last_name
			FROM employees
			WHERE salary =
			(SELECT MIN(salary)
			FROM employees
			GROUP BY department_id);
			-- error：Subquery returns more than 1 row子查询结果多余1行


	
	-- 5.多行子查询(集合比较子查询)(均为不相关子查询)
		-- 多行子查询比较操作符：
			-- IN 等于列表中的任意一个
			-- ANY(SOME) 需要和单行比较操作符一起使用，和子查询返回的某一个值比较
			-- ALL 需要和单行比较操作符一起使用，和子查询返回的所有值比较
	
	-- 例1：返回其它job_id中比job_id为‘IT_PROG’部门任一工资低的员工的员工号、姓名、job_id 以及salary
	SELECT employee_id,last_name,job_id,salary
	FROM employees
	WHERE job_id <> 'IT_PROG'
	AND salary < ANY (
			  SELECT salary
			  FROM employees
			  WHERE job_id = 'IT_PROG'
			  );
	
	-- 例2：查询平均工资最低的部门id
	-- MySQL中不支持聚合函数嵌套：MIN(AVG(salary))
	-- 方式一
	SELECT department_id -- (1)主语为查找部门id
	FROM employees
	GROUP BY department_id -- (2)查询各个部门要分组
	HAVING AVG(salary) = ( -- (6)判断条件：当平均工资为最低工资时
			      SELECT MIN(avg_sal) -- (5)查询t_dept_avg_sal表中各个部门的最低工资
			      FROM (
				    SELECT AVG(salary) avg_sal -- (3)因为聚合函数不能嵌套，所以起别名
				    FROM employees
				    GROUP BY department_id
				    ) t_dept_avg_sal -- (4)给查询的虚拟表命名，此表存储的是各个部门的平均工资
			      );
	-- 方式二：ALL优化
	SELECT department_id
	FROM employees
	GROUP BY department_id
	HAVING AVG(salary) <= ALL ( -- 条件：平均工资小于等于所有平均工资(相当于选取了平均工资中的最低工资)
			           SELECT AVG(salary)
			           FROM employees
			           GROUP BY department_id
		                   );
	   
			
	
	-- 6.多行子查询问题
		-- 空值问题
		SELECT last_name
		FROM employees
		WHERE employee_id NOT IN (
					  SELECT manager_id
					  FROM employees
					  #WHERE manager_id IS NOT NULL
					  ); -- 子查询包含null值 
	