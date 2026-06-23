-- 索引优化与查询优化

	-- 宝宝快要累死了，，，

	-- 数据库调优维度
		-- 索引失效，未充分利用索引：索引建立
		-- 关联查询太多JOIN：SQL优化
		-- 服务器调优及各个参数设置(缓冲、线程数等)：调整my.cnf
		-- 数据过多：分库分表
	-- 物理查询优化：索引、表连接优化
	-- 逻辑查询优化：SQL等价交换
	
	

	-- 1. 数据准备

	CREATE DATABASE atguigudb2;
	USE atguigudb2;

	-- 建表
	CREATE TABLE `class` (
	 `id` INT(11) NOT NULL AUTO_INCREMENT,
	 `className` VARCHAR(30) DEFAULT NULL,
	 `address` VARCHAR(40) DEFAULT NULL,
	 `monitor` INT NULL ,
	 PRIMARY KEY (`id`)
	) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
	 
	CREATE TABLE `student` (
	 `id` INT(11) NOT NULL AUTO_INCREMENT,
	 `stuno` INT NOT NULL ,
	 `name` VARCHAR(20) DEFAULT NULL,
	 `age` INT(3) DEFAULT NULL,
	 `classId` INT(11) DEFAULT NULL,
	 PRIMARY KEY (`id`)
	 -- CONSTRAINT `fk_class_id` FOREIGN KEY (`classId`) REFERENCES `t_class` (`id`)
	) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

	SET GLOBAL log_bin_trust_function_creators=1; 

	 -- 随机产生字符串
	DELIMITER //
	CREATE FUNCTION rand_string(n INT) RETURNS VARCHAR(255)
	BEGIN    
	DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ';
	DECLARE return_str VARCHAR(255) DEFAULT '';
	DECLARE i INT DEFAULT 0;
	WHILE i < n DO  
	SET return_str =CONCAT(return_str,SUBSTRING(chars_str,FLOOR(1+RAND()*52),1));  
	SET i = i + 1;
	END WHILE;
	RETURN return_str;
	END //
	DELIMITER ;

	-- 用于随机产生多少到多少的编号
	DELIMITER //
	CREATE FUNCTION  rand_num (from_num INT ,to_num INT) RETURNS INT(11)
	BEGIN   
	DECLARE i INT DEFAULT 0;  
	SET i = FLOOR(from_num +RAND()*(to_num - from_num+1))   ;
	RETURN i;  
	END //
	DELIMITER ;

	-- 创建往stu表中插入数据的存储过程
	DELIMITER //
	CREATE PROCEDURE  insert_stu(  START INT ,  max_num INT )
	BEGIN  
	DECLARE i INT DEFAULT 0;   
	 SET autocommit = 0;    -- 设置手动提交事务
	 REPEAT  -- 循环
	 SET i = i + 1;  -- 赋值
	 INSERT INTO student (stuno, NAME ,age ,classId ) VALUES ((START+i),rand_string(6),rand_num(1,50),rand_num(1,1000));  
	 UNTIL i = max_num  
	 END REPEAT;  
	 COMMIT;  -- 提交事务
	END //
	DELIMITER ;

	-- 执行存储过程，往class表添加随机数据
	DELIMITER //
	CREATE PROCEDURE `insert_class`(  max_num INT )
	BEGIN  
	DECLARE i INT DEFAULT 0;   
	 SET autocommit = 0;    
	 REPEAT  
	 SET i = i + 1;  
	 INSERT INTO class ( classname,address,monitor ) VALUES (rand_string(8),rand_string(10),rand_num(1,100000));  
	 UNTIL i = max_num  
	 END REPEAT;  
	 COMMIT; 
	END //
	DELIMITER ;

	-- 执行存储过程，往class表添加1万条数据  
	CALL insert_class(10000);

	-- 执行存储过程，往stu表添加50万条数据  
	CALL insert_stu(100000,500000);

	SELECT COUNT(*) FROM class;

	SELECT COUNT(*) FROM student;

	DELIMITER //
	CREATE  PROCEDURE `proc_drop_index`(dbname VARCHAR(200),tablename VARCHAR(200))
	BEGIN
	       DECLARE done INT DEFAULT 0;
	       DECLARE ct INT DEFAULT 0;
	       DECLARE _index VARCHAR(200) DEFAULT '';
	       DECLARE _cur CURSOR FOR  SELECT   index_name   FROM information_schema.STATISTICS   WHERE table_schema=dbname AND table_name=tablename AND seq_in_index=1 AND    index_name <>'PRIMARY'  ;
	-- 每个游标必须使用不同的declare continue handler for not found set done=1来控制游标的结束
	       DECLARE  CONTINUE HANDLER FOR NOT FOUND SET done=2 ;      
	-- 若没有数据返回,程序继续,并将变量done设为2
		OPEN _cur;
		FETCH _cur INTO _index;
		WHILE  _index<>'' DO 
		       SET @str = CONCAT("drop index " , _index , " on " , tablename ); 
		       PREPARE sql_str FROM @str ;
		       EXECUTE  sql_str;
		       DEALLOCATE PREPARE sql_str;
		       SET _index=''; 
		       FETCH _cur INTO _index; 
		END WHILE;
	   CLOSE _cur;
	END //
	DELIMITER ;

	CALL proc_drop_index(dbname,tablename);



	-- 人麻了。。学死了

	-- 2. 索引失效案例
		-- 1）全值匹配我最爱
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age=30;
			-- SQL_NO_CACHE：对当前query的产生的结果集不缓存至系统query cache里，下次相同query花费时间会多点
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age=30 AND classId=4;
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age=30 AND classId=4 AND NAME = 'abcd';
			-- 有几个索引就使用对应的联合索引

		SELECT SQL_NO_CACHE * FROM student WHERE age=30 AND classId=4 AND NAME = 'abcd';

		CREATE INDEX idx_age ON student(age);
		CREATE INDEX idx_age_classid ON student(age,classId);
		CREATE INDEX idx_age_classid_name ON student(age,classId,NAME);

		-- 2）最佳左前缀法则
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE student.age=30 AND student.name = 'abcd';
			-- 此时只有age有索引，只按age索引查询
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE student.classid=1 AND student.name = 'abcd';
			-- classid、name无对应联合索引，无法使用索引，只能全表搜索
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE classid=4 AND student.age=30 AND student.name = 'abcd'; 
			-- 当加了age后，构成了联合索引idx_age_classid_name，就可以使用了

		DROP INDEX idx_age ON student;
		DROP INDEX idx_age_classid ON student;
		SHOW INDEX FROM student;

		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE student.age=30 AND student.name = 'abcd'; 
			-- (1)不删除idx_age、idx_age_classid时默认会先使用单值索引查询，再使用短的联合索引查询
			-- (2)虽然此处使用了idx_age_classid_name联合索引，但是仅匹配了其第一个索引age，因为其后没有classId字段，因此无法
			--    继续向后使用索引了，因此最终只按照联合索引的第一个索引age查询，相当于使用单值索引了

		-- 3)主键插入顺序(单调)

		-- 4)计算、函数、类型转换(自动或手动)导致索引失效

		-- 此语句比下一条要好！（能够使用上索引）
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE student.name LIKE 'abc%';
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE LEFT(student.name,3) = 'abc'; 
			-- 使用函数后无法使用索引，因为无法确定函数操作

		CREATE INDEX idx_name ON student(NAME);
		CREATE INDEX idx_sno ON student(stuno);

		EXPLAIN SELECT SQL_NO_CACHE id, stuno, NAME FROM student WHERE stuno+1 = 900001;
			-- 运算时要依次取出数据运算后再判断，无法使用索引，类似函数
		EXPLAIN SELECT SQL_NO_CACHE id, stuno, NAME FROM student WHERE stuno = 900000;
		EXPLAIN SELECT id, stuno, NAME FROM student WHERE SUBSTRING(NAME, 1,3)='abc';

		-- 5)类型转换导致索引失效

		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE NAME = 123; 
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE NAME = '123'; 

		-- 6)范围条件右边的列索引失效
		SHOW INDEX FROM student;
		CALL proc_drop_index('atguigudb2','student');

		CREATE INDEX idx_age_classId_name ON student(age,classId,NAME);

		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE student.age=30 AND student.classId>20 AND student.name = 'abc' ; 
			-- 当联合索引的某个索引存在范围判断时，该联合索引仅会使用范围判断索引及其之前的索引，而不使用之后的索引了
			-- 例：上述classId存在范围判断，仅可以使用联合索引的age和classId进行查询，而无法使用name了
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE student.age=30 AND student.name = 'abc' AND student.classId>20; 
			-- 交换属性位置不会起作用，优化器会自动调整顺序！
		CREATE INDEX idx_age_name_cid ON student(age,NAME,classId);
			-- 此时要使用索引的话，可以将判断范围的索引放在联合索引最后即可！ 

		-- 7)不等于(!= 或者<>)索引失效
		CREATE INDEX idx_name ON student(NAME);

		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE student.name <> 'abc' ;
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE student.name != 'abc' ;

		-- 8）is null可以使用索引，is not null无法使用索引
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age IS NULL; 
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age IS NOT NULL; 

		-- 9)like以通配符%开头索引失效
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE NAME LIKE 'ab%'; 
		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE NAME LIKE '%ab%'; -- 底层B+数无法查找

		-- 10)OR 前后存在非索引的列，索引失效
		SHOW INDEX FROM student;
		CALL proc_drop_index('atguigudb2','student');

		CREATE INDEX idx_age ON student(age);

		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age = 10 OR classid = 100;
			-- OR连接的左右属性必须全部有索引才可以使用索引查询

		CREATE INDEX idx_cid ON student(classid);

		--  11)数据库和表的字符集统一使用utf8mb4
			-- 一旦转换就使用函数，无法使用索引



	-- 3. 关联查询优化
		-- 添加数据
		
		-- 分类
		CREATE TABLE IF NOT EXISTS `type` (
		`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
		`card` INT(10) UNSIGNED NOT NULL,
		PRIMARY KEY (`id`)
		);
		-- 图书
		CREATE TABLE IF NOT EXISTS `book` (
		`bookid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
		`card` INT(10) UNSIGNED NOT NULL,
		PRIMARY KEY (`bookid`)
		);

		-- 向分类表中添加20条记录
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));

		-- 向图书表中添加20条记录
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));
		INSERT INTO book(card) VALUES(FLOOR(1 + (RAND() * 20)));

		--  情况1：左外连接
			EXPLAIN SELECT SQL_NO_CACHE * FROM `type` LEFT JOIN book ON type.card = book.card;

			-- 添加索引
			CREATE INDEX Y ON book(card);
			EXPLAIN SELECT SQL_NO_CACHE * FROM `type` LEFT JOIN book ON type.card = book.card;

			CREATE INDEX X ON `type`(card);
				-- 添加对应两个索引后，左外连接均可以正常使用，但注意两表的连接判断数据数据类型必须一致！
				-- 左外连接左表会全部遍历，所以系统查询可能使用的索引时直接显示了NULL，但实际查询时还是会使用对应索引
			EXPLAIN SELECT SQL_NO_CACHE * FROM `type` LEFT JOIN book ON type.card = book.card;

			DROP INDEX Y ON book;
			EXPLAIN SELECT SQL_NO_CACHE * FROM `type` LEFT JOIN book ON type.card = book.card;

		--  情况2：内连接
			DROP INDEX X ON `type`;
			EXPLAIN SELECT SQL_NO_CACHE * FROM `type` INNER JOIN book ON type.card = book.card;

			-- 添加索引
			CREATE INDEX Y ON book(card);
			EXPLAIN SELECT SQL_NO_CACHE * FROM `type` INNER JOIN book ON type.card = book.card;

			CREATE INDEX X ON `type`(card);
			-- 结论：对于内连接来说，查询优化器可以决定谁作为驱动表，谁作为被驱动表出现的
			EXPLAIN SELECT SQL_NO_CACHE * FROM `type` INNER JOIN book ON type.card = book.card;

			-- 删除索引
			DROP INDEX Y ON book;
			-- 结论：对于内连接来讲，如果表的连接条件中只能有一个字段有索引，则有索引的字段所在的表会被作为被驱动表出现。
			EXPLAIN SELECT SQL_NO_CACHE * FROM `type` INNER JOIN book ON type.card = book.card;
				-- 只有type有索引，作为被驱动表

			CREATE INDEX Y ON book(card);
			EXPLAIN SELECT SQL_NO_CACHE * FROM `type` INNER JOIN book ON type.card = book.card;

			-- 向type表中添加数据（20条数据）
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			INSERT INTO `type`(card) VALUES(FLOOR(1 + (RAND() * 20)));
			
			DROP TABLE `book`;
			DROP TABLE `type`;

			-- 结论：对于内连接来说，在两个表的连接条件都存在索引的情况下，会选择小表作为驱动表。“小表驱动大表”
			EXPLAIN SELECT SQL_NO_CACHE * FROM `type` INNER JOIN book ON type.card = book.card;
				-- book数据少，为驱动表

		-- JOIN的底层原理
			-- 驱动表：主表，查询结果始终在从表上面
			-- 被驱动表：从表
			
			-- 外连接无where：左边的表为驱动表
			-- 外连接有where：优化器会根据where条件，可能会转换为内连接重新判断
			-- 内连接：查询优化器可以决定谁作为驱动表，谁作为被驱动表出现的
			--         如果表的连接条件中只能有一个字段有索引，则有索引的字段所在的表会被作为被驱动表出现。
			--         在两个表的连接条件都存在索引的情况下，会选择小表作为驱动表。“小表驱动大表”
			
			-- (1)简单嵌套循环连接（Simple Nested-Loop Join）
				-- 从驱动表A取出1条数据，遍历被驱动表B，结果存放至result，之后依次循环结束
				-- 开销统计		SNLJ 
				-- 外表扫描次数:	1
				-- 内表扫描次数:	A
				-- 读取记录数:		A+B*A
				-- JOIN比较次数:	B*A
				-- 回表读取记录次数:  	0
			-- 为了让读取记录数更小，使驱动表A数据量更少，即选择小表作为驱动表！
			
			-- (2)索引嵌套循环连接（Index Nested-Loop Join）
			-- 减少内层表数据匹配次数，即首先为被驱动表添加索引
				-- 开销统计		SNLJ	INLJ
				-- 外表扫描次数:	1	1
				-- 内表扫描次数:	A 	0
				-- 读取记录数:		A+B*A   A+B(MATCH)
				-- JOIN比较次数:	B*A	A*INDEX(Height)
				-- 回表读取记录次数:	0 	B(MATCH) (IF possible)

			-- (3)块嵌套循环连接（Block Nested-Loop Join）
				-- 一次性的将A表中多条数据(数据多少取决于缓冲区join buffer大小)和被驱动表数据进行遍历 
				-- 开销统计		SNLJ	INLJ			BNLJ
				-- 外表扫描次数:	1	1			1
				-- 内表扫描次数:	A	0			A* used_column_size / join_buffer_size+1
				-- 读取记录数:		A+B*A   A+B(match)		A+B * (A* used_column_size /join_buffer_size)
				-- JOIN比较次数:	B*A 	A*Index(Height) 	B*A
				-- 回表读取记录次数:	0	B(match)(if possible)	0
			SHOW VARIABLES LIKE '%optimizer_switch%'; -- block_nested_loop默认为ON;
			SHOW VARIABLES LIKE '%join_buffer%'; -- 查看缓冲区大小
			SET join_buffer_size = 262144; -- 设置缓冲区大小
			
			-- (4)Hash JOIN
				-- MySQL8.0.20之后就不用上面的连接原理了。。。默认使用hash join
				-- hash join只能应用等值连接，非等值连接还要用之前的(hash特点)
				
			-- 总结
				-- [1]整体效率比较 INLJ > BNLJ > SNLJ
				-- [2]小结果集驱动大结果集(减少外层循环的数据数量)
				-- [3]被驱动表添加索引(减少内层表的循环匹配次数)
				-- [4]增大join buffer size的大小(一次缓存的数据越多，内存包的扫表数就越少)
				-- [5]减少驱动表不必要的字段查询(字段越少，join buffer所存的数据就越多)

		CREATE TABLE a(f1 INT, f2 INT, INDEX(f1))ENGINE=INNODB;
		CREATE TABLE b(f1 INT, f2 INT)ENGINE=INNODB;

		INSERT INTO a VALUES(1,1),(2,2),(3,3),(4,4),(5,5),(6,6);
		INSERT INTO b VALUES(3,3),(4,4),(5,5),(6,6),(7,7),(8,8);

		-- 测试1
		EXPLAIN SELECT * FROM a LEFT JOIN b ON(a.f1=b.f1) WHERE (a.f2=b.f2);

		-- 测试2
		EXPLAIN SELECT * FROM a LEFT JOIN b ON(a.f1=b.f1) AND (a.f2=b.f2);

		-- 测试3
		EXPLAIN SELECT * FROM a JOIN b ON(a.f1=b.f1) WHERE (a.f2=b.f2);



	-- 4. 子查询的优化     
		-- 创建班级表中班长的索引
		CREATE INDEX idx_monitor ON class(monitor);

		-- 查询班长的信息
		-- 子查询
		EXPLAIN SELECT * FROM student stu1
		WHERE stu1.`stuno` IN (
		SELECT monitor
		FROM class c
		WHERE monitor IS NOT NULL
		); -- 0.020s
		-- 多表查询
		EXPLAIN SELECT stu1.* FROM student stu1 JOIN class c 
		ON stu1.`stuno` = c.`monitor`
		WHERE c.`monitor` IS NOT NULL; -- 0.002s

		-- 查询不为班长的学生信息
		EXPLAIN SELECT SQL_NO_CACHE a.* 
		FROM student a 
		WHERE  a.stuno  NOT  IN (
					SELECT monitor FROM class b 
					WHERE monitor IS NOT NULL);

		EXPLAIN SELECT SQL_NO_CACHE a.*
		FROM  student a LEFT OUTER JOIN class b 
		ON a.stuno =b.monitor
		WHERE b.monitor IS NULL;



	-- 5. 排序优化
		-- 删除student和class表中的非主键索引
		CALL proc_drop_index('atguigudb2','student');
		CALL proc_drop_index('atguigudb2','class');
		SHOW INDEX FROM student;
		SHOW INDEX FROM class;
		
		-- 过程一：
		EXPLAIN SELECT SQL_NO_CACHE * FROM student ORDER BY age,classid; 
		EXPLAIN SELECT SQL_NO_CACHE * FROM student ORDER BY age,classid LIMIT 10; 

		-- 过程二：order by时不limit，索引失效
		-- 创建索引  
		CREATE  INDEX idx_age_classid_name ON student (age,classid,NAME);

		-- 不限制,索引失效
		EXPLAIN  SELECT SQL_NO_CACHE * FROM student ORDER BY age,classid; 
		-- EXPLAIN  SELECT SQL_NO_CACHE age,classid,name,id FROM student ORDER BY age,classid; 

		-- 增加limit过滤条件，使用上索引了。
		EXPLAIN  SELECT SQL_NO_CACHE * FROM student ORDER BY age,classid LIMIT 10;  

		-- 过程三：order by时顺序错误，索引失效

		-- 创建索引age,classid,stuno
		CREATE  INDEX idx_age_classid_stuno ON student (age,classid,stuno); 

		-- 以下哪些索引失效?
		EXPLAIN  SELECT * FROM student ORDER BY classid LIMIT 10;
		EXPLAIN  SELECT * FROM student ORDER BY classid,NAME LIMIT 10;  
		EXPLAIN  SELECT * FROM student ORDER BY age,classid,stuno LIMIT 10; 
		EXPLAIN  SELECT * FROM student ORDER BY age,classid LIMIT 10;
		EXPLAIN  SELECT * FROM student ORDER BY age LIMIT 10;

		-- 过程四：order by时规则不一致, 索引失效 （顺序错，不索引；方向反，不索引）
		EXPLAIN  SELECT * FROM student ORDER BY age DESC, classid ASC LIMIT 10;
		EXPLAIN  SELECT * FROM student ORDER BY classid DESC, NAME DESC LIMIT 10;
		EXPLAIN  SELECT * FROM student ORDER BY age ASC,classid DESC LIMIT 10; 
		EXPLAIN  SELECT * FROM student ORDER BY age DESC, classid DESC LIMIT 10; -- 只有这个可以用

		-- 过程五：无过滤，不索引
		EXPLAIN  SELECT * FROM student WHERE age=45 ORDER BY classid; 
			-- 先考虑where过滤条件，若过滤之后还剩很少数据，那么就不会再使用索引了，直接排序了
		EXPLAIN  SELECT * FROM student WHERE age=45 ORDER BY classid,NAME; 
		EXPLAIN  SELECT * FROM student WHERE classid=45 ORDER BY age;
		EXPLAIN  SELECT * FROM student WHERE classid=45 ORDER BY age LIMIT 10; -- 使用索引idx_age_classid_name

		CREATE INDEX idx_cid ON student(classid);
		EXPLAIN  SELECT * FROM student WHERE classid=45 ORDER BY age;

		-- 实战：测试filesort和index排序
		CALL proc_drop_index('atguigudb2','student');

		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age = 30 AND stuno <101000 ORDER BY NAME ;

		-- 方案一: 为了去掉filesort我们可以把索引建成

		CREATE INDEX idx_age_name ON student(age,NAME);

		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age = 30 AND stuno <101000 ORDER BY NAME ;

		-- 方案二：

		CREATE INDEX idx_age_stuno_name ON student(age,stuno,NAME);

		EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age = 30 AND stuno <101000 ORDER BY NAME ;

		DROP INDEX idx_age_stuno_name ON student;

		CREATE INDEX idx_age_stuno ON student(age,stuno);

	-- filesort算法：多路排序、单路排序(速度快，IO多占内存)

	-- 6. 覆盖索引
		-- 覆盖索引：对于一些select查询，如果查询的字段刚好在二级索引中可以完全查找到，那么就不用查询索引后再回表查询原始数据了，
		-- 因为查询的索引已经包含了select的字段了，相当于使用对应索引，减少回表操作。(之前的一些索引失效的情况也可以使用覆盖索引)
		
		-- 删除之前的索引
		-- 举例1：
		DROP INDEX idx_age_stuno ON student;

		CREATE INDEX idx_age_name ON student (age,NAME);

		EXPLAIN SELECT * FROM student WHERE age <> 20;

		EXPLAIN SELECT age,NAME FROM student WHERE age <> 20;

		-- 举例2：
		EXPLAIN SELECT * FROM student WHERE NAME LIKE '%abc';

		EXPLAIN SELECT id,age FROM student WHERE NAME LIKE '%abc';

		-- -- -- 
		SELECT CRC32('hello')
		FROM DUAL;



	-- 7. 索引条件下推(ICP)
		-- 举例1：
		USE atguigudb1;

		EXPLAIN SELECT * FROM s1 WHERE key1 > 'z' AND key1 LIKE '%a';
			-- 此时先执行key1 LIKE '%a'，执行后再执行key1 > 'z'就减少了回表次数，这就是索引下推

		-- 举例2：
		CREATE TABLE `people` (
		  `id` INT NOT NULL AUTO_INCREMENT,
		  `zipcode` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL,
		  `firstname` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL,
		  `lastname` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL,
		  `address` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL,
		  PRIMARY KEY (`id`),
		  KEY `zip_last_first` (`zipcode`,`lastname`,`firstname`)
		) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

		INSERT INTO `people` VALUES 
		('1', '000001', '三', '张', '北京市'), 
		('2', '000002', '四', '李', '南京市'), 
		('3', '000003', '五', '王', '上海市'), 
		('4', '000001', '六', '赵', '天津市');

		EXPLAIN SELECT * FROM people
		WHERE zipcode='000001'
		AND lastname LIKE '%张%' 
			-- 索引失效，联合索引只使用了zipcode单个索引，索引先执行了过滤操作，再依照索引进行的回表
			-- 系统按照性能自动选择先过滤还是先回表
		AND address LIKE '%北京市%';

		EXPLAIN SELECT * FROM people
		WHERE zipcode='000001'
		AND lastname LIKE '张%'
		AND firstname LIKE '三%';

		-- 索引下推的使用与关闭
		SET optimizer_switch = 'index_condition_pushdown=on'; -- 打开索引下推
		SET optimizer_switch = 'index_condition_pushdown=off'; -- 关闭索引下推

		-- 创建存储过程，向people表中添加1000000条数据，测试ICP开启和关闭状态下的性能
		DELIMITER //
		CREATE PROCEDURE  insert_people( max_num INT )
		BEGIN  
		DECLARE i INT DEFAULT 0;   
		 SET autocommit = 0;    
		 REPEAT  
		 SET i = i + 1;  
		 INSERT INTO people ( zipcode,firstname,lastname,address ) VALUES ('000001', '六', '赵', '天津市');  
		 UNTIL i = max_num  
		 END REPEAT;  
		 COMMIT; 
		END //
		DELIMITER ;

		CALL insert_people(1000000);



	-- 8.EXISTS & OR
		-- 两个表A、B
		-- 当A>B，使用IN
			SELECT * FROM A WHERE c1 IN (SELECT c1 FROM B);
		-- 当A<B，使用EXISTS
			SELECT * FROM A WHERE EXISTS (SELECT c1 FROM B WHERE B.c1=A.c1);



	-- 9.MySQL中COUNT(*),COUNT(1),COUNT(字段)等查询
		-- (1)COUNT(*)和COUNT(1)执行效率相同，在myisam中有参数meta统计行数row_count，所以复杂度为O(1),在innodb中只能全表查询，
		--    复杂度为O(n),COUNT(字段)则使用二级索引效率更高。
		
		-- (2)select*：不推荐使用，会将*转换为所有列名耗费资源时间，且无法使用覆盖索引
		
		-- (3)LIMIT 1优化影响
			-- 对于全表查询且已知结果仅有一条数据，则添加LIMIT 1会加快查询速度
			-- 对于已有索引，则不用全表查询，LIMIT 1没有实际意义
			
		-- (4)多使用COMMIT
			-- COMMIT释放的资源：回滚段上用于恢复数据的信息，被程序语句获得的锁，
			--                   redo/undo log buffer中的空间，管理上述3种资源中的内部花费



	-- 10.淘宝数据库主键如何设计
		-- 设置主键为非业务字段，例如主键为：订单号=时间+去重字段+用户ID后6位
		-- 或使用UUID
		SELECT UUID() FROM DUAL;
		SET @uuid = UUID();
		SELECT @uuid,uuid_to_bin(@uuid),uuid_to_bin(@uuid,TRUE);
		