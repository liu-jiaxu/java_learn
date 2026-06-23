-- 运算符

-- 1.算术运算符： +  -  *  /  %(取模)

	-- (1)数据类
		SELECT 100, 100 + 0, 100 - 0, 100 + 50, 100 + 50 * 30, 100 + 35.5, 100 - 35.5 
			-- 数据类型与java类似，整形、浮点型...
		FROM DUAL; -- 此处DUAL表无实际作用，为伪表

	-- (2)注意事项
		SELECT 100+'1'; -- SQL:101(SQL的+没有连接作用，会隐式转换将'1'变为1再计算),Java:1001(Java的+相当于连接符)
		SELECT 100+'a'; -- MySQL结果为100，SQLServer直接报错，数据类型无法转换

	-- (3)数据类型自动转换
		SELECT 100*1,100*1.0,100*1.000,100/2,100/3;
			-- 100  100.0  100.000 50.0000(SqlServer结果为50) 33.3333(SqlServer结果为33)
			-- SQLServer除法自动去小数，MySQL除法结果均为浮点型
			-- -注：SQLServer任意数除以零会报错，但MySQL结果为null

	-- (4)取模
		SELECT 12%3,14%3,-12%5; -- 0  2  -2



-- 2.比较运算符：返回结果：真为1，假为0，其他为null
	-- 符号类
		-- 等于运算符  	   =
		-- 安全等于运算符 <=>
		-- 不等于运算符   !=或<>
		-- 小于类运算符   < <=
		-- 大于类运算符   > >=
		
	-- 非符号类
		-- 为空运算符		IS NULL
		-- 不为空运算符		IS NOT NULL
		-- 最小值运算符		LEAST()
		-- 最大值运算符		GREATEST()
		-- 两值之间运算符	BETWEEN AND
		-- 为空运算符		ISNULL()
		-- 属于运算符		IN
		-- 不属于运算符		NOT IN
		-- 模糊匹配运算符	LIKE
		-- 正则表达式运算符  	REGEXP
		-- 正则表达式		RLIKE
	
	-- (1)符号类= <=> <>!=  <  >  <=  >=
		
		-- [1]= 比较运算
			SELECT 1=2,1!=2,1='1',1='a',0='a'; -- 数字和字符串比较，会隐式转换将字符串转换为0再比较
			SELECT 'a'='a','a'='b'; -- 字符串之间比较不会进行隐式转换
			SELECT 1=NULL,NULL=NULL; -- 结果均为null
	
		-- [2]<=> 比较运算，可以进行null与其他数据之间的比较
			SELECT 1<=>NULL,0<=>NULL;-- null会视为空值，null!=0，null与其他任何非null数据安全等于比较后都为0
			SELECT NULL<=>NULL; -- null与null安全等于比较结果为1
			SELECT *
			FROM employees
			-- WHERE commission_pct=NULL;
			WHERE commission_pct<=>NULL; -- 注意= <=>两者区别
		
		-- [3]其他算术运算符
			SELECT 1!=2,1<>2,1!='1',0!='a',1!=NULL,NULL!=NULL; -- !=结果与=结果判断类似
			SELECT 1>=0,5<9;
	
	-- (2)非符号类
	
		-- [1]IS NULL/IS NOT NULL/ISNULL是否为空
			SELECT last_name,salary,commission_pct
			FROM employees
			WHERE commission_pct IS NULL; -- commission_pct IS NULL相当于commission_pct<=>NULL
			
			SELECT last_name,salary,commission_pct
			FROM employees
			WHERE commission_pct IS NOT NULL;
			
			SELECT last_name,salary,commission_pct
			FROM employees
			WHERE ISNULL(commission_pct); -- ISNULL为函数，相当于commission_pct IS NULL
			-- 数值比较用<=>，数据判断用IS (NOT) NULL			
			
		-- [2]LEAST()最大值/GREATEST()最小值
			SELECT LEAST('a','ab','g','66',100,'dee'),GREATEST('','w','aa','10',1);
			SELECT LEAST(12,100),GREATEST('abc','d');
				-- 比较方法，从前往后依次比较，不看位数，例如12>100,abc<d
			
		-- [3]BETWEEN 条件下界 AND 条件上界 查询两者之间的数值(包括边界值，注意前小后大)
			-- 例：查询薪资在6000-8000之间的员工信息
			SELECT employee_id,last_name,salary
			FROM employees
			WHERE salary BETWEEN 6000 AND 8000; -- salary>=6000 && salary<=8000
			
			-- 补：查询薪资在6000-8000之外的员工信息
			SELECT employee_id,last_name,salary
			FROM employees
			WHERE salary NOT BETWEEN 6000 AND 8000; -- salary<6000 || salary>8000
					
		-- [4]IN (set)/NOT IN (set)查询set集合中的离散值
			-- 例：查询部门为10、20、30部门员工信息
			SELECT *
			FROM employees
			WHERE department_id IN (10,20,30);
			-- 例：多表之间使用in
			SELECT employee_id,department_id
			FROM employees
			WHERE department_id IN (
					        SELECT department_id
					        FROM departments
					        WHERE manager_id >= 200
					        );
			
		-- [5]LIKE模糊查询，查询包含特定值的数据
			-- %表示不确定个字符(0-无穷)
			-- _占用一个随机字符位置
			-- 转义字符，默认为$，可以通过ESCEPE''设置转义字符
		
			-- 例：查询last_name中包含字符'a'的员工信息
			SELECT last_name
			FROM employees
			WHERE last_name LIKE '%a%'; -- 忽略大小写
				-- %表示不确定个字符(0-无穷)
				
			-- 例：查询last_name中以字符'a'开头的员工信息
			SELECT last_name
			FROM employees
			WHERE last_name LIKE 'a%';
			
			-- 例：查询last_name中包含字符'a'和'e'的员工信息
			SELECT last_name
			FROM employees
			WHERE last_name LIKE '%a%' AND last_name LIKE '%e%';
			
			-- 例：查询last_name中第三个字符为'a'的员工信息
			SELECT last_name
			FROM employees
			WHERE last_name LIKE '__a%'; 
				-- _占用一个随机字符位置
				
			-- 例：查询last_name中第二个字符为‘_'且第三个字符为'a'的员工信息
			SELECT last_name
			FROM employees
			WHERE last_name LIKE '_$_a%' ESCAPE '$'; 
				-- 转义字符，默认为$，可以通过ESCEPE''设置转义字符
			
		-- [6]REGEXP/RLIKE正则表达式	
		--     REGEXP运算符用来匹配字符串，语法格式为：expr REGEXP 匹配条件。如果expr满足匹配条件，返回1;如果不满足，则返回0。
		-- 若expr或匹配条件任意一个为NULL，则结果为NULL。
			-- 【1】‘^’匹配以该字符后面的字符开头的字符串。
			-- 【2】‘$’匹配以该字符前面的字符结尾的字符串。
			-- 【3】‘.’匹配任何一个单字符。
			-- 【4】“[...]”匹配在方括号内的任何字符。例如，“[abc]”匹配“a”或“b”或“c”。为了命名字符的范围，
			--	使用一个‘-’。“[a-z]”匹配任何字母，而“[0-9]”匹配任何数字。
			-- 【5】‘*’匹配零个或多个在它前面的字符。例如，“x*”匹配任何数量的‘x’字符，“[0-9]*”匹配任何数
			--	量的数字，而“*”匹配任何数量的任何字符。

			SELECT 'abcdefg' REGEXP '^a','abcd' REGEXP 'a.c';
