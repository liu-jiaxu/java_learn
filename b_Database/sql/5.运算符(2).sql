-- 运算符

-- 3.逻辑运算符：返回1、0、NULL
	-- NOT或!   逻辑非
	-- AND或&&  逻辑与
	-- OR或||   逻辑或
	-- XOR	    逻辑异或
	
	-- NOT
		SELECT *
		FROM employees
		WHERE salary NOT BETWEEN 6000 AND 8000;
	
	-- AND OR
		SELECT last_name,salary,department_id
		FROM employees
		WHERE department_id=50 OR department_id=20; 
		-- WHERE department_id=50 AND salary>600;

		SELECT last_name,salary,department_id
		FROM employees
		WHERE department_id=50 && salary>6000;
	
	-- XOR(A XOR B，仅满足A、B两条件的一种)
		SELECT last_name,department_id,salary
		FROM employees
		WHERE department_id=20 XOR salary>=15000;
	
	
	
-- 4.位运算符：先转换为二进制运算再转换回去	
	-- &  按位与，都是1则为1
	-- |  按位或，有一个1就是1
	-- ^  按位异或，二进制对应两数据不同时为1，否则为0
	-- ~  按位取反，一个数的二进制数1变0,0变1
	-- >> 按位右移(每右移一位相当于/2，去尾保留)，4>>2为4右移2位，变为1，左侧空出的位置补0
	-- << 按位左移(每左移一位相当于*2)，4>>1为左移1为，变为8，右侧空出的位置补0
	
	SELECT 12&8, 12|8, 12^8, ~7, 23>>2, 13<<1;
	
-- 5.优先级(加括号就行)
