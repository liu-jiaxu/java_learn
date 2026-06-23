-- 函数

-- 四.流程控制函数

	-- IF(value,value1,value2) 如果value的值为TRUE，返回value1，否则返回value2
	-- IFNULL(value1, value2) 如果value1不为NULL，返回value1，否则返回value2
	-- CASE WHEN 条件1 THEN 结果1 WHEN 条件2 THEN 结果2.... [ELSE resultn] END相当于Java的if...else if...else...
	-- CASE expr WHEN 常量值1 THEN 值1 WHEN 常量值1 THEN值1 .... [ELSE 值n] END相当于Java的switch...case...
	SELECT last_name,salary,IF(salary>=10000,salary,last_name)
	FROM employees;
	SELECT last_name,commission_pct,IFNULL(commission_pct,0) "details"
	FROM employees;
	SELECT last_name,salary,CASE WHEN salary>=15000 THEN 'high'
				     WHEN salary>=10000 THEN 'normal'
				     WHEN salary>=5000 THEN 'low'
				     ELSE 'grass roots' END AS 'details' -- else相当于java的else
	FROM employees;
	-- CASE expr练习：查询部门号为10,20,30的余员工信息，打印其工资*部门号的倍数，其余部门直接打印薪资
	SELECT department_id,CASE department_id WHEN 10 THEN salary*10
						WHEN 20 THEN salary*20
						WHEN 30 THEN salary*30
						ELSE salary END "datails" -- else相当于java的default
	FROM employees	
	WHERE department_id IN(10,20,30)
	ORDER BY datails DESC;
	
-- 五.加密与解密函数

	-- MD5(str)返回字符串str的md5加密后的值，也是一种加密方式。若参数为NULL，则会返回NULL
	-- SHA(str)从原明文密码str计算并返回加密后的密码字符串，当参数为NULL时，返回NULL。SHA加密算法比MD5更加安全。
	-- ENCODE(value,password_seed) 返回使用password_seed作为加密密码加密value
	-- DECODE(value,password_seed) 返回使用password_seed作为加密密码解密value,弃用！
	SELECT MD5('12345'),SHA('12345')
	FROM DUAL;



-- 六.MySQL信息函数

	-- VERSION() 返回当前MySQL的版本号
	-- CONNECTION_ID() 返回当前MySQL服务器的连接数
	-- DATABASE()，SCHEMA() 返回MySQL命令行当前所在的数据库
	-- USER()，CURRENT_USER()、SYSTEM_USER()，SESSION_USER()返回当前连接MySQL的用户名，返回结果格式为“主机名@用户名”
	-- CHARSET(value) 返回字符串value自变量的字符集
	-- COLLATION(value) 返回字符串value的比较规则
	SELECT VERSION(),CONNECTION_ID(),DATABASE(),USER(),CHARSET('a'),COLLATION('a')
	FROM DUAL;



-- 七.其他函数

	-- FORMAT(value,n)返回对数字value进行格式化后的结果数据。n表示四舍五入后保留到小数点后n位
	-- CONV(value,from,to) 将value的值进行不同进制之间的转换
	-- INET_ATON(ipvalue) 将以点分隔的IP地址转化为一个数字
	-- INET_NTOA(value) 将数字形式的IP地址转化为以点分隔的IP地址
	-- BENCHMARK(n,expr)将表达式expr重复执行n次。用于测试MySQL处理expr表达式所耗费的时间
	-- CONVERT(value USINGchar_code)将value所使用的字符编码修改为char_code
	SELECT FORMAT(123.456,2),FORMAT(123.444,6),FORMAT(123.444,-1)
	FROM DUAL; -- 123.46  123.444000  123
	SELECT CONV(1234,10,2),CONV(1234,10,16),CONV(357,8,10); -- CONV(1234,10,2)将1234从十进制转换为二进制
	SELECT INET_ATON('192.168.0.1'),INET_NTOA(3232235521); -- 转换关系192*256的3次方+168*256的2次方+0*256+1
	SELECT BENCHMARK(100000,MD5('12345')),BENCHMARK(100000,SHA('12345'));
	SELECT CHARSET('abcde'),CHARSET(CONVERT('abcde' USING 'utf8mb4')),CHARSET(CONVERT('abcde' USING 'gbk')); -- 字符集转换
	