-- 索引使用

	-- 额，这玩意和约束好像。。。

	-- 一.索引分类
		-- 1.功能逻辑：
			-- 普通索引：不附加任何限制条件，仅用于提高查询效率
			-- 唯一索引：使用UNIQUE参数设置为唯一性索引，索引值唯一或为NULL，一张表可以有多个唯一索引
			-- 主键索引：唯一+非空索引，一张表最多一个(底层实现方式决定)
			-- 全文索引：自然语言全文检索和布尔全文检索
		-- 2.物理实现方式：
			-- 聚簇索引：按主键值大小进行记录和页的排序，B+tree叶子结点存储的是完整的用户记录
			-- 非聚簇索引：二级索引等
		-- 3.作用字段个数：
			-- 单列索引：在单个字段上创建索引，可以是普通索引、唯一性索引、全文索引
			-- 联合(多列)索引：作用在多个字段上
			
			
			
			
	-- 二.创建索引的两种方式(特娘的，终于熬过来了啊，终于可以敲代码了。。。)
		-- 1.建表时		
			-- (1)隐式的方式创建索引。在声明有主键约束、唯一性约束、外键约束的字段上，会自动的添加相关的索引
			CREATE DATABASE dbtest2;
			USE dbtest2;
			
			CREATE TABLE dept(
			dept_id INT PRIMARY KEY AUTO_INCREMENT,
			dept_name VARCHAR(20)
			);
			CREATE TABLE emp(
			emp_id INT PRIMARY KEY AUTO_INCREMENT,
			emp_name VARCHAR(20) UNIQUE,
			dept_id INT,
			CONSTRAINT emp_dept_id_fk FOREIGN KEY(dept_id) REFERENCES dept(dept_id)
			);
			
			-- (2)显式的方式创建：
				-- [1]基本格式：
				CREATE TABLE table_name [col_name,data_type]
				[UNIQUE | FULLTEXT | SPATIAL] [INDEX | KEY] [index_name] (col_name [LENGTH]) [ASC | DESC]
					-- UNIQUE | FULLTEXT | SPATIAL：唯一索引、全文索引、空间索引
					-- INDEX | KEY：两者作用相同，用来指定创建索引
					-- index_name：索引名，不指定默认为字段名
					-- col_name [LENGTH]：表的指定列col_name创建索引，索引长度只有字符串类型的字段才需要指定
					-- ASC | DESC：升序或倒序存储索引值
			
				-- [2]创建普通的索引
				CREATE TABLE book(
				book_id INT ,
				book_name VARCHAR(100),
				AUTHORS VARCHAR(100),
				info VARCHAR(100) ,
				COMMENT VARCHAR(100),
				year_publication YEAR,
				INDEX idx_bname(book_name) -- 声明索引
				);

			-- 查看索引
				-- 方式1：
				SHOW CREATE TABLE book;
				-- 方式2：
				SHOW INDEX FROM book;

			-- 性能分析工具：EXPLAIN
			EXPLAIN SELECT * FROM book WHERE book_name = 'mysql高级';

				-- [3]创建唯一索引
				--  声明有唯一索引的字段，在添加数据时，要保证唯一性，但是可以添加null
				CREATE TABLE book1(
				book_id INT ,
				book_name VARCHAR(100),
				AUTHORS VARCHAR(100),
				info VARCHAR(100) ,
				COMMENT VARCHAR(100),
				year_publication YEAR,
				UNIQUE INDEX uk_idx_cmt(COMMENT) -- 声明索引
				);

				SHOW INDEX FROM book1;

				INSERT INTO book1(book_id,book_name,COMMENT)
				VALUES(1,'Mysql高级','适合有数据库开发经验的人员学习');
				INSERT INTO book1(book_id,book_name,COMMENT)
				VALUES(2,'Mysql高级',NULL);
				SELECT * FROM book1;

				-- [4]主键索引
				-- 通过定义主键约束的方式定义主键索引
				CREATE TABLE book2(
				book_id INT PRIMARY KEY , -- 声明索引
				book_name VARCHAR(100),
				AUTHORS VARCHAR(100),
				info VARCHAR(100) ,
				COMMENT VARCHAR(100),
				year_publication YEAR
				);

				SHOW INDEX FROM book2;
				-- 通过删除主键约束的方式删除主键索引
				ALTER TABLE book2
				DROP PRIMARY KEY;

				-- [5]创建单列索引
				CREATE TABLE book3(
				book_id INT ,
				book_name VARCHAR(100),
				AUTHORS VARCHAR(100),
				info VARCHAR(100) ,
				COMMENT VARCHAR(100),
				year_publication YEAR,
				UNIQUE INDEX idx_bname(book_name) -- 声明索引
				);

				SHOW INDEX FROM book3;

				--  [6]创建联合索引
				CREATE TABLE book4(
				book_id INT ,
				book_name VARCHAR(100),
				AUTHORS VARCHAR(100),
				info VARCHAR(100) ,
				COMMENT VARCHAR(100),
				year_publication YEAR,
				INDEX mul_bid_bname_info(book_id,book_name,info) -- 声明索引
					-- 联合索引按索引添加顺序排序，其中的主键先按book_id排，相同的话再按book_name排，依次类推
				);

				SHOW INDEX FROM book4;

				-- 分析
				EXPLAIN SELECT * FROM book4 WHERE book_id = 1001 AND book_name = 'mysql';
				EXPLAIN SELECT * FROM book4 WHERE book_name = 'mysql';

				--  [7]创建全文索引
				CREATE TABLE test4(
				id INT NOT NULL,
				NAME CHAR(30) NOT NULL,
				age INT NOT NULL,
				info VARCHAR(255),
				FULLTEXT INDEX futxt_idx_info(info(50)) -- 只按前50个字符排序索引
				)

				SHOW INDEX FROM test4;
				-- 全文索引的查询方式
				SELECT * FROM test4 WHERE MATCH(info) AGAINST('查询字符串');
		


		-- 第2种：表已经创建成功
			-- [1]方式一：ALTER TABLE ... ADD ...
			CREATE TABLE book5(
			book_id INT ,
			book_name VARCHAR(100),
			AUTHORS VARCHAR(100),
			info VARCHAR(100) ,
			COMMENT VARCHAR(100),
			year_publication YEAR
			);

			SHOW INDEX FROM book5;

			ALTER TABLE book5 ADD INDEX idx_cmt(COMMENT);
			ALTER TABLE book5 ADD UNIQUE uk_idx_bname(book_name);
			ALTER TABLE book5 ADD INDEX mul_bid_bname_info(book_id,book_name,info);

			-- [2]方式二：CREATE INDEX ... ON ...
			CREATE TABLE book6(
			book_id INT ,
			book_name VARCHAR(100),
			AUTHORS VARCHAR(100),
			info VARCHAR(100) ,
			COMMENT VARCHAR(100),
			year_publication YEAR
			);

			SHOW INDEX FROM book6;

			CREATE INDEX idx_cmt ON book6(COMMENT);
			CREATE UNIQUE INDEX  uk_idx_bname ON book6(book_name);
			CREATE INDEX mul_bid_bname_info ON book6(book_id,book_name,info);
			
			
			
	-- 三.索引删除		
		SHOW INDEX FROM book5;

		-- 方式1：ALTER TABLE .... DROP INDEX ....
		ALTER TABLE book5 
		DROP INDEX idx_cmt;

		-- 方式2：DROP INDEX ... ON ...
		DROP INDEX uk_idx_bname ON book5;

		-- 方式三：删除索引中的相关字段
		-- 原联合索引顺序：book_id,book_name,info
		ALTER TABLE book5
		DROP COLUMN book_name; -- 删除book_name后，info索引代替book_name的位置，变为2
		ALTER TABLE book5
		DROP COLUMN book_id; -- 继续删除book_id后，仅剩的info又代替book_id的位置，变为1
		ALTER TABLE book5
		DROP COLUMN info; -- 当删除info后，表示此联合索引以全部删除
			-- 删除表中的列时，对应的索引也会一同删除！
		
		
		
	-- 四.MySQL8.0索引新特性：降序索引、隐藏索引
		-- 1. 支持降序索引
			CREATE TABLE ts1(
			a INT,
			b INT,
			INDEX idx_a_b(a ASC,b DESC) -- a字段索引值升序，b字段索引值降序
			);
			SHOW CREATE TABLE ts1;

			DELIMITER //
			CREATE PROCEDURE ts_insert()
			BEGIN
				DECLARE i INT DEFAULT 1;
				WHILE i < 800
				DO
					INSERT INTO ts1 SELECT RAND()*80000,RAND()*80000;
					SET i = i + 1;
				END WHILE;
				COMMIT;
			END //
			DELIMITER ;

			CALL ts_insert();
			SELECT COUNT(*) FROM ts1;
			
			-- 优化测试
			EXPLAIN SELECT * FROM ts1 ORDER BY a,b DESC LIMIT 5;
			-- 不推荐
			EXPLAIN SELECT * FROM ts1 ORDER BY a DESC,b DESC LIMIT 5;
				-- 当要按a、b均降序查询时性能降低，建议重新创建a、b都降序的索引

		-- 2. 隐藏索引及创建、修改
			-- 作用1：软删除
				-- 当显式删除索引后报错，只能重新通过显式的方式添加回来，很耗费资源。
				-- 因此MySQL8.0支持隐藏索引，隐藏索引不起作用，设置隐藏索引后再删除索引，减少资源消耗
				-- 软删除：先设置索引为隐藏索引，在删除索引的方式
			-- 作用2：对比查询性能
				-- 验证某个索引删除后的查询性能影响，可以暂时使用隐藏索引
			-- 注：主键不能设置为隐藏索引！
		
			-- [1]创建表时，隐藏索引
			CREATE TABLE book7(
			book_id INT ,
			book_name VARCHAR(100),
			AUTHORS VARCHAR(100),
			info VARCHAR(100) ,
			COMMENT VARCHAR(100),
			year_publication YEAR,
			INDEX idx_cmt(COMMENT) invisible -- 创建不可见的索引，不写的话默认为visible可见
			);

			SHOW INDEX FROM book7;
			EXPLAIN SELECT * FROM book7 WHERE COMMENT = 'mysql....'; -- 隐藏索引不可用

			-- [2]创建表以后
				-- 方式一
				ALTER TABLE book7
				ADD UNIQUE INDEX uk_idx_bname(book_name) invisible;
				-- 方式二
				CREATE INDEX idx_year_pub ON book7(year_publication) visible; -- 可见

			EXPLAIN SELECT * FROM book7 WHERE year_publication = '2022';

			-- [3]修改索引的可见性
			ALTER TABLE book7 ALTER INDEX idx_year_pub invisible; -- 可见--->不可见
			ALTER TABLE book7 ALTER INDEX idx_cmt visible; -- 不可见 ---> 可见
			
			-- 注：隐藏索引实时更新，消耗性能，所以不用的话删除即可！

			-- 了解：使隐藏索引对查询优化器可见
			SELECT @@optimizer_switch \G
			SET SESSION optimizer_switch="use_invisible_indexes=on";
			EXPLAIN SELECT * FROM book7 WHERE year_publication = '2022';
					