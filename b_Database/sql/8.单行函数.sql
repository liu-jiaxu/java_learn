-- 函数
-- 实现的功能角度可以分为数值函数、字符串函数、日期和时间函数、流程控制函数、加密与解密函数、获取MySQL信息函数、聚合函数等
-- 单行函数：操作数据对象、接受参数返回一个结果、只对一行进行变换、每行返回一个结果、可以嵌套、参数可以是一列或一个值
-- 聚合函数(分组函数)： 聚合函数作用于一组数据，并对一组数据返回一个值

-- 一.数值函数
-- 1.基本函数
	-- ABS(x) 返回x的绝对值
	-- SIGN(X) 返回X的符号。正数返回1，负数返回-1，0返回0
	-- PI() 返回圆周率的值
	-- CEIL(x)，CEILING(x) (天花板函数)返回大于或等于某个值的最小整数
	-- FLOOR(x) (地板函数)返回小于或等于某个值的最大整数
	-- LEAST(e1,e2,e3…) 返回列表中的最小值
	-- GREATEST(e1,e2,e3…) 返回列表中的最大值
	-- MOD(x,y) 返回X除以Y后的余数
	-- RAND() 返回0~1的随机值
	-- RAND(x)返回0~1的随机值，其中x的值用作种子值，相同的X值会产生相同的随机数
	-- ROUND(x) 返回一个对x的值进行四舍五入后，最接近于X的整数
	-- ROUND(x,y) 返回一个对x的值进行四舍五入后最接近X的值，并保留到小数点后面Y位
	-- TRUNCATE(x,y) 返回数字x截断为y位小数的结果
	-- SQRT(x) 返回x的平方根。当X的值为负数时，返回NULL
	
	SELECT ABS(-123),ABS(32),SIGN(-23),SIGN(43),PI(),CEIL(32.32),CEILING(-43.23),FLOOR(32.32),FLOOR(-43.23),MOD(12,5)
	FROM DUAL; 
	-- 123  32  -1  1  3.141593  33  -43  32  -44  2
	
	SELECT RAND(),RAND(),RAND(10),RAND(10),RAND(-1),RAND(-1) 
	FROM DUAL; -- RAND(10)的10作为标注，之后所有的RAND(10)都与第一次数值相同
	-- 0.7691410330768126  0.7541602065359803  0.6570515219653505  0.6570515219653505  0.9050373219931845  0.9050373219931845
	
	SELECT ROUND(12.33),ROUND(12.343,2),ROUND(12.324,-1),TRUNCATE(12.66,1),TRUNCATE(19.66,-1),TRUNCATE(19.66,-2)
	FROM DUAL; 
	-- 12  12.34  10  12.6  10  0
	-- TRUNCATE(12.324,1) 保留到1位小数。结果12.3
	-- TRUNCATE(12.324,0) 仅保留整数位。结果12
	-- TRUNCATE(12.324,-1) 去小数位，整数位个位变为0。结果10
	-- TRUNCATE(12.324,-2) 去小数位，整数位十位变为0.结果0
	
	SELECT ABS(TRUNCATE(-25.55,1)) -- 函数嵌套
	FROM DUAL; 
	
-- 2.角度与弧度互换函数
	-- RADIANS(x) 将角度转化为弧度，其中，参数x为角度值
	-- DEGREES(x) 将弧度转化为角度，其中，参数x为弧度值
	SELECT RADIANS(30),RADIANS(60),RADIANS(90),DEGREES(2*PI()),DEGREES(RADIANS(90))
	FROM DUAL; 
	
-- 3.三角函数(先转换为弧度)
	-- SIN(x) 返回x的正弦值，其中，参数x为弧度值
	-- ASIN(x) 返回x的反正弦值，即获取正弦为x的值。如果x的值不在-1到1之间，则返回NULL
	-- COS(x) 返回x的余弦值，其中，参数x为弧度值
	-- ACOS(x) 返回x的反余弦值，即获取余弦为x的值。如果x的值不在-1到1之间，则返回NULL
	-- TAN(x) 返回x的正切值，其中，参数x为弧度值
	-- ATAN(x) 返回x的反正切值，即返回正切值为x的值
	-- ATAN2(m,n) 返回两个参数的反正切值
	-- COT(x) 返回x的余切值，其中，X为弧度值
	SELECT SIN(RADIANS(30)),DEGREES(ASIN(1)),TAN(RADIANS(45)),DEGREES(ATAN(1)),DEGREES(ATAN2(1,1))
	FROM DUAL;
	
-- 4.指数与对数
	-- POW(x,y)，POWER(X,Y) 返回x的y次方
	-- EXP(X) 返回e的X次方，其中e是一个常数，2.718281828459045
	-- LN(X)，LOG(X) 返回以e为底的X的对数，当X <= 0 时，返回的结果为NULL
	-- LOG10(X) 返回以10为底的X的对数，当X <= 0 时，返回的结果为NULL
	-- LOG2(X) 返回以2为底的X的对数，当X <= 0 时，返回NULL
	SELECT POW(2,5),POWER(2,6),EXP(2),LN(10),LN(EXP(666)),LOG10(10),LOG2(4)
	FROM DUAL; 
	-- 32  16  7.38905609893065  2.302585092994046	666  1  2
	
-- 5.进制间的转换
	-- BIN(x) 返回x的二进制编码
	-- HEX(x) 返回x的十六进制编码
	-- OCT(x) 返回x的八进制编码
	-- CONV(x,f1,f2) 返回f1进制数变成f2进制数	
	SELECT BIN(10),HEX(10),OCT(10),CONV(10,2,8) -- CONV(10,2,8)解释：2进制的10(不是十，是一零)转换为8进制的数
	FROM DUAL;
	-- 1010  A  12  2


	
-- 二.字符串函数(补充：SQL索引从1开始！)	
	-- ASCII(S) 返回字符串S中的第一个字符的ASCII码值
		SELECT ASCII('abc'); -- 97
	-- CHAR_LENGTH(s) 返回字符串s的字符数。作用与CHARACTER_LENGTH(s)相同
		SELECT CHAR_LENGTH('aqxwc'); -- 5
		SELECT CHAR_LENGTH('我们');  -- 2
	-- LENGTH(s) 返回字符串s的字节数，和字符集有关
		SELECT LENGTH('aqxwc'); -- 5
		SELECT TRUNCATE(LENGTH('我们的') / 3, 0) AS `length`; -- 9/3(utf8一个汉字占3字节)
	-- CONCAT(s1,s2,......,sn) 连接s1,s2,......,sn为一个字符串
		SELECT CONCAT(emp.last_name,' worked for ',mgr.last_name)
		FROM employees emp JOIN employees mgr
		WHERE emp.`manager_id`=mgr.`employee_id`;
	-- CONCAT_WS(x,s1,s2,......,sn)同CONCAT(s1,s2,...)函数，但是每个字符串之间要加上x
		SELECT CONCAT_WS(' hahaha ','am','bm','cm'); -- am hahaha bm hahaha cm
	-- INSERT(str, idx, len,replacestr)将字符串str从第idx位置开始，len个字符长的子串替换为字符串replacestr
		SELECT INSERT('abcdefghijk',3,5,'zyyz'); -- abzyyzhijk
	-- REPLACE(str, a, b) 用字符串b替换字符串str中所有出现的字符串a
		SELECT REPLACE('str1str2', 'tr', 'abc'); -- sabc1sabc2
		SELECT REPLACE('str1str2', 'atr', 'abc'); -- str1str2(未找到不替换)
	-- UPPER(s) 或 UCASE(s) 将字符串s的所有字母转成大写字母
	-- LOWER(s) 或LCASE(s) 将字符串s的所有字母转成小写字母
		SELECT UPPER('abcdEfg'); -- ABCDEFG
		SELECT LOWER('ABCdefG'); -- abcdefg
	-- LEFT(str,n) 返回字符串str最左边的n个字符
	-- RIGHT(str,n) 返回字符串str最右边的n个字符
		SELECT LEFT('abcd',2); -- ab
		SELECT RIGHT('abcd',3); -- bcd
	-- LPAD(str, len, pad) 用字符串pad对str最左边进行填充，直到str的长度为len个字符
	-- RPAD(str ,len, pad) 用字符串pad对str最右边进行填充，直到str的长度为len个字符
		SELECT LPAD('abcdefg',12,'qsaa'); -- qsaaqabcdefg
		SELECT LPAD(salary,10,' ') -- 右对齐(金额默认右对齐)
		FROM employees;
		SELECT RPAD(salary,10,'*') -- 左对齐
		FROM employees;
	-- LTRIM(s) 去掉字符串s左侧的空格
	-- RTRIM(s) 去掉字符串s右侧的空格
	-- TRIM(s) 去掉字符串s开始与结尾的空格
		SELECT LTRIM('  abc  '),RTRIM('  eee  '),TRIM(' qa q '); -- abc  eee  qa q
		SELECT CONCAT(LTRIM('  hello '),TRIM(' world!')); -- hello world!
	-- TRIM(s1 FROM s) 去掉字符串s开始与结尾的s1
	-- TRIM(LEADING s1 FROM s)去掉字符串s开始处的s1
	-- TRIM(TRAILING s1 FROM s)去掉字符串s结尾处的s1
		SELECT TRIM('abc' FROM 'abcdefgabc'); -- defg
		SELECT TRIM(LEADING 'abc' FROM 'abcdefgabc'); -- defgabc
		SELECT TRIM(TRAILING 'abc' FROM 'abcdefgabc'); -- abcdefg
	-- REPEAT(str, n) 返回str重复n次的结果
		SELECT REPEAT('str',3); -- strstrstr
	-- SPACE(n) 返回n个空格
	-- STRCMP(s1,s2) 比较字符串s1,s2的ASCII码值的大小(从前往后按位比较。返回值：1前大，0一样大，-1后大)
		SELECT STRCMP('azbc','ac'); -- 1
		SELECT STRCMP('abc','ac'); -- -1
		SELECT STRCMP('a','A'); -- 0
	-- SUBSTR(s,index,len)返回从字符串s的index位置其len个字符，作用与SUBSTRING(s,n,len)、MID(s,n,len)相同
		SELECT SUBSTR('hello',2,2); -- el
		SELECT SUBSTR('hello',11,2); -- 
	-- LOCATE(substr,str)返回字符串substr在字符串str中首次出现的位置，作用于POSITION(substr IN str)、INSTR(str,substr)相同。未找到，返回0
		SELECT LOCATE('ab','bcabaaba'); -- 3
	-- ELT(m,s1,s2,…,sn)返回指定位置的字符串，如果m=1，则返回s1，如果m=2，则返回s2，如果m=n，则返回sn
		SELECT ELT(2,'a','b','c'); -- b
	-- FIELD(s,s1,s2,…,sn) 返回字符串s在字符串列表中第一次出现的位置
		SELECT FIELD('as',' ','as','as',' '); -- 2
	-- FIND_IN_SET(s1,s2)返回字符串s1在字符串s2中出现的位置。其中，字符串s2是一个以逗号分隔的字符串
		SELECT FIND_IN_SET('a','abc,ac,a,ew,a'); -- 3
	-- REVERSE(s) 返回s反转后的字符串
		SELECT REVERSE('abcdefg'); -- gfedcba
	-- NULLIF(value1,value2)比较两个字符串，如果value1与value2相等，则返回NULL，否则返回value1的长度	
		SELECT employee_id,NULLIF(LENGTH(first_name),LENGTH(last_name))
		FROM employees;
	