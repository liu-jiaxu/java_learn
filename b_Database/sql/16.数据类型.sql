-- 数据类型

	-- 1.MySQL中的数据类型	
		-- 整数类型         TINYINT、SMALLINT、MEDIUMINT、INT(或INTEGER)、BIGINT
		-- 浮点类型         FLOAT、DOUBLE
		-- 定点数类型       DECIMAL
		-- 位类型    	    BIT
		-- 日期时间类型     YEAR、TIME、DATE、DATETIME、TIMESTAMP
		-- 文本字符串类型   CHAR、VARCHAR、TINYTEXT、TEXT、MEDIUMTEXT、LONGTEXT
		-- 枚举类型         ENUM
		-- 集合类型         SET
		-- 二进制字符串类型 BINARY、VARBINARY、TINYBLOB、BLOB、MEDIUMBLOB、LONGBLOB
		-- JSON类型         JSON对象、JSON数组
		-- 空间数据类型     单值：GEOMETRY、POINT、LINESTRING、POLYGON；
		-- 		    集合：MULTIPOINT、MULTILINESTRING、MULTIPOLYGON、GEOMETRYCOLLECTION



	-- 2.常见数据类型的属性
		-- NULL 	      数据列可包含NULL值
		-- NOT NULL 	      数据列不允许包含NULL值
		-- DEFAULT 	      默认值
		-- PRIMARY KEY        主键
		-- AUTO_INCREMENT     自动递增，适用于整数类型
		-- UNSIGNED           无符号
		-- CHARACTER SET name 指定一个字符集(在创建数据库、表、字段时都可指定字符集utf8、gbk...)
			-- 例：
			CREATE TABLE tb1(id INT PRIMARY KEY AUTO_INCREMENT)CHARACTER SET 'gbk';
			DESC tb1;	
	
	
	
	-- 3.整数类型
		-- 整数类型     字节	有符号数取值范围			 无符号数取值范围
		-- TINYINT 	 1 	-128~127 				 0~255
		-- SMALLINT 	 2 	-32768~32767 				 0~65535
		-- MEDIUMINT 	 3 	-8388608~8388607 			 0~16777215
		-- INT、INTEGER  4 	-2147483648~2147483647  		 0~4294967295
		-- BIGINT 	 8 	-9223372036854775808~9223372036854775807 0~18446744073709551615	
			-- 例：超出范围
			INSERT INTO tb1
			VALUES(2147483648); -- 若超出范围则取最大值2147483647
			SELECT *
			FROM tb1;	
	
		-- 三个可选属性
			-- (1)M：表示显示宽度，M的取值范围是(0, 255)。例如，int(5)：当数据宽度小于5位的时候在数字前面需要用字符
			--    填满宽度。该项功能需要配合“ ZEROFILL ”使用，表示用“0”填满宽度，否则指定显示宽度无效。
			-- (2)UNSIGNED : 无符号类型（非负），所有的整数类型都有一个可选的属性UNSIGNED（无符号属性），无符号整数
			--    类型的最小取值为0。所以，如果需要在MySQL数据库中保存非负整数值时，可以将整数类型设置为无符号类型。
			-- (3)ZEROFILL : 0填充,（如果某列是ZEROFILL，那么MySQL会自动为当前列添加UNSIGNED属性），如果指定了ZEROFILL
			--    只是表示不够M位时，用0在左边填充，如果超过M位，只要不超过数据存储范围即可。
		CREATE TABLE IF NOT EXISTS t_int1(
		f1 INT,
		f2 INT(5),
		f3 INT(5) ZEROFILL,
		f4 INT UNSIGNED
		);
		DROP TABLE IF EXISTS t_int1;
		
		INSERT INTO t_int1(f1,f2)
		VALUES(123,123),(123456,123456); -- 不指定ZEROFILL时和普通int一样
		INSERT INTO t_int1(f3)
		VALUES(123),(123456); -- 指定ZEROFILL时不足M位时前面补零
		INSERT INTO t_int1(f4)
		VALUES(4294967295); -- 指定UNSIGNED时位数-1(减掉符号位)，数据存储大小*2
		
		SELECT *
		FROM t_int1;
	
	
	
	-- 4.浮点型
		-- FLOAT 4字节 DOUBLE 8字节
		-- 注：(1)浮点型不要进行运算操作，二进制保存数据有误差
		--     (2)设置精度标度：DOUBLE(8,3)表示只能存储整数位和小数位共8位，小数占三位，此时范围为-99999.999到99999.999(此项设置一般不用！)
	
	
	
	-- 5.定点数类型
		-- 使用 DECIMAL(M,D) 的方式表示高精度小数。其中，M被称为精度，D被称为标度。0<=M<=65，0<=D<=30，D<M。
		-- 例如，定义DECIMAL（5,2）的类型，表示该列取值范围是-999.99~999.99。
		-- 注：(1)DECIMAL(M,D)的最大取值范围与DOUBLE类型一样
		--     (2)定点数在MySQL内部是以字符串的形式进行存储，这就决定了它一定是精准的。
		--     (3)DECIMAL(M,D)会四舍五入超出范围的数据
	CREATE TABLE IF NOT EXISTS t_decimal1(
	f1 DECIMAL,
	f2 DECIMAL(5,2)
	);
	DROP TABLE IF EXISTS t_deciaml1;
	
	INSERT INTO t_decimal1(f1)
	VALUES(4294967295),(4294967295.888); -- 未指明(M,D)时默认只保留整数位，小数位进行四舍五入
	INSERT INTO t_decimal1(f2)
	VALUES(429.888),(4291.888); -- 小数位四舍五入，但超过整数位范围时会报错：Out of range value for column 'f2' at row 2

	SELECT *
	FROM t_decimal1;
	
	
	
	-- 6.位类型
		-- 二进制字符串类型 	长度 	长度范围 	占用空间
		-- 	BIT(M) 	      	 M 	1<=M<=64   约为(M + 7)/8个字节
		-- BIT类型，如果没有指定(M)，默认是1位。这个1位，表示只能存1位的二进制值。这里(M)是表示二进制的位数，位数最小值为1，最大值为64。
	
	CREATE TABLE t_bit1(
	f1 BIT,
	f2 BIT(5),
	f3 BIT(64)
	);
	
	INSERT INTO t_bit1(f1)
	VALUES(1);
	INSERT INTO t_bit1(f1)
	VALUES(2); -- Data too long for column 'f1' at row 1
	INSERT INTO t_bit1(f2)
	VALUES(23); -- 范围：5位二进制，23->10111
	INSERT INTO t_bit1(f3)
	VALUES(41);
	
	SELECT *
	FROM t_bit1;
	SELECT BIN(f1),HEX(f2),f3+0 -- 二进制、十六进制、十进制表示方法
	FROM t_bit1;
	
	
	
	-- 7.日期与时间类型
		-- 	类型 	   名称     字节       日期格式			最小值			  最大值
		-- 	YEAR 	    年	      1        YYYY或YY 		 1901    		   2155
		-- 	TIME 	   时间	      3        HH:MM:SS      	      -838:59:59 		 838:59:59
		-- 	DATE 	   日期       3       YYYY-MM-DD 	      1000-01-01 		 9999-12-03
		--    DATETIME	 日期时间     8	   YYYY-MM-DD HH:MM:SS 	  1000-01-01 00:00:00        9999-12-31 23:59:59
		--    TIMESTAMP	 日期时间     4	   YYYY-MM-DD HH:MM:SS	1970-01-01 00:00:00 UTC	    2038-01-1903:14:07UTC
	-- 例：year
	CREATE TABLE t_time(
	f1 YEAR,
	f2 TIME,
	f3 DATE ,
	f4 DATETIME, -- 使用最多
	f5 TIMESTAMP	
	);
	DROP TABLE t_time;
	
	INSERT INTO t_time(f1)
	VALUES('2020'),('2021');

	INSERT INTO t_time(f2)
	VALUES('2 12:30:29'), ('12:35:29'), ('12:40'), ('2 12:40'), ('1 05'), ('45');
		-- 2day 12:30:29 -> 60:30:29  1day 05:00:00 -> 29:00:00  45 -> 00:00:45
	INSERT INTO t_time(f2)
	VALUES ('123520'), (124011), (1210); -- 12:35:20  12:40:11  00:12:10
	INSERT INTO t_time(f2)
	VALUES (NOW()), (CURRENT_TIME());
 
	INSERT INTO t_time(f3)
	VALUES ('2020-10-01'), ('20201001'), (20201001); -- 2020-10-01
	INSERT INTO t_time(f3)
	VALUES ('00-01-01'), ('000101'), ('69-10-01'), ('691001'), ('70-01-01'), ('700101'), ('99-01-01'), ('990101');
		-- 当年份取值为00到69时，会被转化为2000到2069；当年份取值为70到99时，会被转化为1970到1999(二位日期表示不要用！)
	INSERT INTO t_time(f3)
	VALUES (000301), (690301), (700301), (990301);
	INSERT INTO t_time(f3)
	VALUES (CURRENT_DATE()), (NOW());
 
	INSERT INTO t_time(f4)
	VALUES ('2021-01-01 06:50:30'), ('20210101065030'); -- 2021-01-01 06:50:30
	INSERT INTO t_time(f4)
	VALUES ('99-01-01 00:00:00'), ('990101000000'), ('20-01-01 00:00:00'), ('200101000000');
	INSERT INTO t_time(f4)
	VALUES (20200101000000), (200101000000), (19990101000000), (990101000000);
	INSERT INTO t_time(f4)
	VALUES (CURRENT_TIMESTAMP()), (NOW());

	INSERT INTO t_time(f5)
	VALUES ('1999-01-01 03:04:50'), ('19990101030405'), ('99-01-01 03:04:05'), ('990101030405');
	INSERT INTO t_time(f5)
	VALUES ('2020@01@01@00@00@00'), ('20@01@01@00@00@00');
	INSERT INTO t_time(f5)
	VALUES (CURRENT_TIMESTAMP()), (NOW());
	INSERT INTO t_time(f5)
	VALUES ('2038-01-20 03:14:07'); -- Incorrect datetime value

	SELECT *
	FROM t_time;
	