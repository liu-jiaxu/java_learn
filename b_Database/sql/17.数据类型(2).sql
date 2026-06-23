-- 数据类型

	-- 8.文本字符串类型
		-- 字符串(文本)类型  特点	     	长度  长度范围			      占用的存储空间
	-- CHAR	-- CHAR(M)           固定长度            M    0 <= M <= 255		         M个字节
		-- VARCHAR(M)        可变长度            M    0 <= M <= 65535 		         (实际长度 + 1) 个字节
	-- TEXT	-- TINYTEXT          小文本、可变长度    L    0 <= L <= 255 		         L + 2 个字节
		-- TEXT              文本、可变长度      L    0 <= L <= 65535 		         L + 2 个字节
		-- MEDIUMTEXT        中等文本、可变长度  L    0 <= L <= 16777215 		 L + 3 个字节
		-- LONGTEXT          大文本、可变长度    L    0 <= L <= 4294967295（相当于4GB）  L + 4 个字节	
		
	-- (1)CHAR类型：
	-- CHAR(M) 类型一般需要预先定义字符串长度。如果不指定(M)，则表示长度默认是1个字符。
	-- 如果保存时数据实际长度比CHAR类型声明的长度小,则会在右侧填充空格以达到指定的长度。当MySQL检索CHAR类型数据时，CHAR类型的字段会去除尾部空格。
	-- 定义CHAR类型字段时，声明的字段长度即为CHAR类型字段所占的存储空间的字节数。
	CREATE TABLE t_char1(
	c1 CHAR,
	c2 CHAR(5)
	);
	DESC t_char1;
	
	INSERT INTO t_char1
	VALUES('a','Tom');
	SELECT c1,CONCAT(c2,'***')
	FROM t_char1;
	INSERT INTO t_char1(c2)
	VALUES('a ');
	SELECT CHAR_LENGTH(c2)
	FROM t_char1;

	-- (2)VARCHAR类型：
	-- VARCHAR(M) 定义时， 必须指定长度M，否则报错。
	-- MySQL4.0版本以下，varchar(20)：指的是20字节，如果存放UTF8汉字时，只能存6个（每个汉字3字节）；MySQL5.0版本以上，varchar(20)：指的是20字符。
	-- 检索VARCHAR类型的字段数据时，会保留数据尾部的空格。VARCHAR类型的字段所占用的存储空间为字符串实际长度加1个字节。
	CREATE TABLE t_varchar1(
	NAME VARCHAR(5)
	);
	
	INSERT INTO t_varchar1
	VALUES('尚硅谷'),('尚硅谷教育');
	INSERT INTO t_varchar1
	VALUES('尚硅谷IT教育'); -- Data too long for column 'NAME' at row 1
		
	SELECT *
	FROM t_varchar1;	
	-- (3)哪些情况使用 CHAR 或 VARCHAR 更好
	-- 类型        特点      空间上        时间上  适用场景
	-- CHAR(M)     固定长度  浪费存储空间  效率高  存储不大，速度要求高
	-- VARCHAR(M)  可变长度  节省存储空间  效率低  非CHAR的情况

	-- (4)TEXT类型
	-- 注：由于实际存储的长度不确定，MySQL不允许TEXT类型的字段做主键。只能采用CHAR(M)，或者VARCHAR(M)。
	CREATE TABLE t_text(
	tx TEXT
	);
	INSERT INTO t_text
	VALUES('atguigu ');
	SELECT CHAR_LENGTH(tx)
	FROM t_text; #10



	-- 9.ENUM类型(多选一)
		-- 文本字符串类型  长度  长度范围  	  占用的存储空间
		-- ENUM 	    L    1 <= L <= 65535  1或2个字节	
	-- 当ENUM类型包含1～255个成员时，需要1个字节的存储空间；
	-- 当ENUM类型包含256～65535个成员时，需要2个字节的存储空间。
	-- ENUM类型的成员个数的上限为65535个。
	CREATE TABLE t_enum(
	season ENUM('春','夏','秋','冬','unknow')
	);
	
	INSERT INTO t_enum
	VALUES('春'),('秋');
	INSERT INTO t_enum
	VALUES('UNKNOW'); -- 忽略大小写
	INSERT INTO t_enum
	VALUES('1'),(3); -- 允许按照角标的方式获取指定索引位置的枚举值
	INSERT INTO t_enum
	VALUES('ab'); -- Data truncated for column 'season' at row 1
	INSERT INTO t_enum
	VALUES(NULL); -- 当ENUM类型的字段没有声明为NOT NULL时，插入NULL也是有效的
	
	SELECT *
	FROM t_enum;
	
	
	
	-- 10.SET类型(多选)
		-- SET表示一个字符串对象，可以包含0个或多个成员，但成员个数的上限为64，设置字段值时，可以取取值范围内的0个或多个值。
	-- 成员个数范围（L表示实际成员个数） 占用的存储空间
	-- 1 <= L <= 8 				1个字节
	-- 9 <= L <= 16 			2个字节
	-- 17 <= L <= 24 			3个字节
	-- 25 <= L <= 32 			4个字节
	-- 33 <= L <= 64 			8个字节
	CREATE TABLE t_set(
	s SET ('A', 'B', 'C')
	);
	INSERT INTO t_set (s) VALUES ('A'), ('A,B');
	INSERT INTO t_set (s) VALUES ('A,B,C,A'); -- 插入重复的SET类型成员时，MySQL会自动删除重复的成员
	INSERT INTO t_set (s) VALUES ('A,B,C,D'); -- 向SET类型的字段插入SET成员中不存在的值时，MySQL会抛出错误。
	
	SELECT *
	FROM t_set;
	
	-- 例：具体应用
	CREATE TABLE temp_mul(
	gender ENUM('男','女'),
	hobby SET('吃饭','睡觉','打豆豆','写代码')
	);
	
	INSERT INTO temp_mul VALUES('男','睡觉,打豆豆'); 
	INSERT INTO temp_mul VALUES('男,女','睡觉,写代码'); -- Data truncated for column 'gender' at row 1
	INSERT INTO temp_mul VALUES('妖','睡觉,写代码'); -- Data truncated for column 'gender' at row 1
	INSERT INTO temp_mul VALUES('男','睡觉,写代码,吃饭'); 
		
	SELECT *
	FROM temp_mul;
	
	
	
	-- 11.二进制字符串类型
		-- BINARY和VARBINARY类似于CHAR和VARCHAR，只是它们存储的是二进制字符串。
		-- 二进制字符串类型  特点值的长度  		  占用空间
		-- BINARY(M) 	     固定长度M（0 <= M <= 255）   M个字节
		-- VARBINARY(M)      可变长度M（0 <= M <= 65535） M+1个字节
		
	CREATE TABLE t_binary1(
	f1 BINARY,
	f2 BINARY(3),
	# f3 VARBINARY,
	f4 VARBINARY(10)
	);
	
	INSERT INTO t_binary1(f1,f2)
	VALUES('a','a');
	INSERT INTO t_binary1(f1,f2)
	VALUES('尚','尚'); -- error
	INSERT INTO t_binary1(f2,f4)
	VALUES('ab','ab');
	
	SELECT *
	FROM t_binary1;
	
	
	
	-- 12.JSON类型
	-- JSON 可以将JavaScript对象中表示的一组数据转换为字符串，然后就可以在网络或者程序之间传递这个字符串，
	-- 并在需要的时候将它还原为各编程语言所支持的数据格式
	CREATE TABLE t_json(
	js json
	);
	INSERT INTO t_json (js)
	VALUES ('{"name":"songhk", "age":18, "address":{"province":"beijing",
	"city":"beijing"}}');
	SELECT *
	FROM t_json;
	-- 当需要检索JSON类型的字段中数据的某个具体值时，可以使用“->”和“->>”符号。
	SELECT js -> '$.name' AS NAME,js -> '$.age' AS age ,js -> '$.address.province' AS province, js -> '$.address.city' AS city
	FROM t_json;
	
	
	
	-- 13. 空间类型(了解)
	
	
	
	-- 14.补充
	-- 阿里巴巴《Java开发手册》之MySQL数据库：
	-- 1.任何字段如果为非负数，必须是 UNSIGNED
	-- 2.小数类型为 DECIMAL，禁止使用 FLOAT 和 DOUBLE。
	--   说明：在存储的时候，FLOAT 和 DOUBLE 都存在精度损失的问题，很可能在比较值的时候，得到不正确的结果。如果存储的数据
	--   范围超过 DECIMAL 的范围，建议将数据拆成整数和小数并分开存储。
	-- 3.如果存储的字符串长度几乎相等，使用 CHAR 定长字符串类型。
	-- 4.VARCHAR 是可变长字符串，不预先分配存储空间，长度不要超过 5000。如果存储长度大于此值，定义字段类型为 TEXT，独立
	--   出来一张表，用主键来对应，避免影响其它字段索引效率。
	