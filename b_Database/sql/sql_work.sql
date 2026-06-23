-- 青理工 刘家旭

-- 1 列出部门名称和这些部门的员工信息，包括没有员工的部门（外连接）
	SELECT d.`dname`, e.*
	FROM dept1 d LEFT JOIN emp1 e ON d.`deptno` = e.`deptno`;

-- 2 列出在每个部门工作的员工数量、平均工资（分组）
	SELECT COUNT(deptno), AVG(sal)
	FROM emp1
	GROUP BY deptno;

-- 3 列出薪金比"刘一"多的所有员工。（子查询）
	SELECT *
	FROM emp1 e1
	WHERE sal > (
		SELECT sal
		FROM emp1 e2
		WHERE e2.`ename` = '刘一'
	);

-- 4 列出与"周八"从事相同工作的所有员工	
	SELECT *
	FROM emp1 e1
	WHERE job = (
		SELECT job
		FROM emp1 e2
		WHERE e2.`ename` = '周八'
	);

-- 5 查询出所有奖金（comm）字段不为空的人员的所有信息	
	SELECT *
	FROM emp1
	WHERE commission IS NOT NULL;

-- 6 查询出emp表中部门编号为20，薪水在2000以上（不包括2000）的所有员工，显示他们的员工号，
--   姓名以及薪水，以如下列名显示：员工编号 员工名字 薪水
	SELECT e1.`id` AS "员工编号", e1.`ename` AS "员工编号", e1.`sal` AS "薪水"
	FROM emp1 e1
	WHERE e1.`deptno` = 20 AND e1.`sal` > 2000;

-- 7 列出在部门 "销售部" 工作的员工的姓名
	SELECT e1.`ename`
	FROM emp1 e1
	WHERE e1.`deptno` = (
		SELECT d1.`deptno` 
		FROM dept1 d1
		WHERE d1.`dname` = '销售部'
	);

-- 8 查询出emp表中所有的工作种类（无重复）
	SELECT DISTINCT job
	FROM emp1;

-- 9 查询员工姓名，工资和 工资级别(工资>=3000 为3级，工资>2000 为2级，工资<=2000 为1级)
--  （提示case when ... then ... when ... then ... else ... end）
	SELECT e.`ename`, e.`sal`, CASE WHEN e.`sal` > 3000 THEN '3级'
					WHEN e.`sal` > 2000 THEN '2级' 
					ELSE '1级' 
					END AS "commission"
	FROM emp1 e;

-- 10 列出各种工作的最低工资（分组）
	SELECT job, MIN(sal)
	FROM emp1
	GROUP BY job;

-- 11 将所有员工按薪水升序排序，薪水相同的按照入职时间降序排序	
	SELECT *
	FROM emp1
	ORDER BY sal ASC, hiredate DESC;
 
-- 12 显示所有员工的名字、薪水、奖金，如果没有奖金，暂时显示100
	SELECT e.`ename`, e.`sal`, IF(e.`commission` IS NOT NULL, e.`commission`, 100) AS "commission"
	FROM emp1 e;