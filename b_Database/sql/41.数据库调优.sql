-- 数据库调优

	-- 一.设计方向
		-- 1.目标
			-- 节省系统资源
			-- 提高用户操作的相应速度
			-- 提高数据库整体性能
		
		-- 2.定位问题
			-- 主要
				-- 用户反馈
				-- 日志分析
			-- 次要
				-- 服务器资源使用监控
				-- 数据库内部状况监控
				-- 事物、锁
				
		-- 3.调优维度及步骤
			-- (1)选择适合的DBMS(数据库产品)
				-- [1]事务性处理和安全性要求高：用商业数据库产品SqlServer、Oracle
				-- [2]采用开源数据库MySQL时指明存储引擎，事务处理用innodb，非事务用myisam
				-- [3]其他数据库选择：键值型数据库、文档型数据库、搜索引擎、列式存储、图形数据库等等
			-- (2)优化表设计
				-- [1]表遵循第三范式或巴斯范式
				-- [2]多表查询可以反范式化提高效率
				-- [3]表字段的数据类型选择
			-- (3)优化逻辑查询
				-- 子查询优化、条件简化、等等
			-- (4)优化物理查询
				-- 使用索引，注意多表连接顺序
			-- (5)使用Redis或Memcached作为缓存
			-- (6)库级优化
				-- 主从架构：读写分离、数据分片
				
				
				
	-- 二.优化策略
		-- 1.优化SQL服务器
			-- (1)优化服务器硬件
				-- 配置较大的内存，增加缓冲区容量减少磁盘IO
				-- 配置高速磁盘系统
				-- 合理分布磁盘IO
				-- 配置多处理器
			-- (2)优化SQL参数(调整my.cnf和my.ini文件的[mysqld]并重启服务器)
				-- [1]innodb_buffer_pool_size：InnoDB类型的表和索引的最大缓存，越大查询越快，也越影响系统性能
				-- [2]key_buffer_size：索引缓冲区大小(4GB内存设置为256M-384M)
				-- [3]table_cache：同时打开表的个数(512-1024)
				-- [4]query_cache_size：查询缓冲区的大小(MySQL8.0之后失效)
				-- [5]query_cache_type：=0 不使用查询缓存
				--			=1 使用查询缓存(除非指定SQL_NO_CACHE：SELECT SQL_NO_CACHE * FROM TABLE;)
				--			=2 只有在使用SQL_CACHE时使用查询缓存
				-- [6]sort_buffer_size：需要进行排序的线程分配的缓冲区的大小(ORDER BY & GROUP BY)，默认2MB(内存4G设置为6-8MB) 
				--			每个连接独享
				-- [7]join_buffer_size：联合查询操作使用缓冲区大小，每个连接独享
				-- [8]read_buffer_size：每个线程连续扫描时每张表分配的缓冲区大小(默认64KB)
				-- [9]innode_flush_log_at_trx_commit：何时将缓冲区的数据写入日志文件
				--			=0 每秒1次将数据写入日志文件并日志文件存入磁盘，
				--			   速度快不安全，进程崩溃时会导致上一秒数据全部丢失
				--			=1 每次提交事务时...，最慢最安全
				--			=2 每次提交事务时将数据写入日志文件，每隔1s将日志文件写入磁盘
				-- [10]innodb_log_buffer_size：事务日志缓冲区大小
				-- [11]max_connections：允许连接到MySQL数据库的最大数量，默认151
				-- [12]back_log：控制MySQL监听TCP端口时设置的积压请求栈大小(默认50+，不超过900)
				-- [13]thread_cache_size：线程池缓存线程数量的大小，默认60,可设置为120
				-- [14]wait_timeout：指定一个请求的最大连接时间，对于4GB左右内存的服务器可以设置为5-10。
				-- [15]Τinteractive_timeout：表示服务器在关闭连接前等待行动的秒数。
			
		-- 2.优化数据库结构
			-- [1]拆分冷热数据，分离表的常用不常用数据
			-- [2]增加中间表，把多个关联表中常用的数据放在一起
			-- [3]增加冗余字段
			-- [4]优化数据类型，选择最小数据类型
				-- {1}整形优化：非负unsigned
				-- {2}使用整形而非文本类型
				-- {3}避免TEXT、BLOB大数据类型
				-- {4}避免使用ENUM类型，使用TINYINT代替ENUM
				-- {5}使用TIMESTAMP(4字节)存储时间，DATETIME(8字节)
				-- {6}用DECIMAL代替非精确浮点型
			
		-- 3.优化插入记录的速度
			-- [1]MyISAM引擎的表
				-- {1}禁用索引
				--        对于非空表,插入记录时, MySQL会根据表的索引对插入的记录建立索引。如果插入大量数据,建立索引就会
				--    降低插入记录的速度。为了解决这种情况,可以在插入记录之前禁用索引，数据插入完毕后再开启索引。
					-- 禁用索引的语句如下：
						ALTER TABLE table_name DISABLE KEYS;
					-- 重新开启索引的语句如下：
						ALTER TABLE table_name ENABLE KEYS;
				--    若对于空表批量导入数据,则不需要进行此操作,因为MyISAM引擎的表是在导入数据之后才建立索引的。
				
				-- {2}禁用唯一性检查
				--        插入数据时, MySQL会对插入的记录进行唯一性校验。这种唯一性校验会降低插入记录的速度。为了降低这
				--    种情况对查询速度的影响，可以在插入记录之前禁用唯一性检查，等到记录插入完毕后再开启。
					-- 禁用唯一性检查的语句如下：
						SET UNIQUE_CHECKS=0;
					-- 开启唯一性检查的语句如下：
						SET UNIQUE_CHECKS=1;
						
				-- {3}使用批量插入
				--    mysql插入多条记录时,可以使用一条INSERT语句插入一条记录,也可以使用一条INSERT语句插入多条记录。
					-- 插入一条记录的INSERT语句情形如下：
						INSERT INTO student VALUES(1,'zhangsan',18,1);
						INSERT INTO student VALUES(2.'1isi',17,1);
						INSERT INTO student VALUES(3,'wangwu',17,1);
						INSERT INTO student VALUES(4, 'zhaoliu',19,1);
					-- 使用一条INSERT语句插入多条记录的情形如下:
						INSERT INTO student VALUE;
						(1, 'zhangsan',18,1),(2, 'lisi',17,1),
						(3, 'wangwu',17,1), (4,'zhaoliu',19,1);
				--    第2种情形的插入速度要比第1种情形快。
				
				-- {4}使用LOAD DATA INFILE批量导入
				--        当需要批量导入数据时，如果能用LOAD DATA INFILE语句，就尽量使用，因为LOAD DATA INFILE语句导
				--    入数据的速度比INSERT语句快。
				
			-- [2]InnoDB引擎的表
				-- {1}禁用唯一性检查
				--        插入数据之前执行 SET unique_checks=0 来禁止对唯一索引的检查，数据导入完成之后再运行 SET
				--    unique_checks=1。这个和MyISAM引擎的使用方法一样。
				
				-- {2}禁用外键检查
				--        插入数据之前执行禁止对外键的检查,数据插入完成之后再恢复对外键的检查。
					-- 禁用外键检查的语句如下:
						SET FOREIGN_KEY_CHECKS=0;
					-- 恢复对外键的检查语句如下:
						SET FOREIGN_KEY_CHECKS=1;
						
				-- {3}禁止自动提交
				--        插入数据之前禁止事务的自动提交,数据导入完成之后,执行恢复自动提交操作。
					-- 禁止自动提交的语句如下:
						SET autocommit=0;
					-- 恢复自动提交的语句如下：
						SET autocommit=1;
		-- 4.使用非空约束
			-- 在设计字段的时候，如果业务允许，建议尽量使用非空约束。这样做的好处是：
				-- [1]进行比较和计算时，省去要对NULL值的字段判断是否为空的开销，提高存储效率。
				-- [2]非空字段也容易创建索引。因为索引NULL列需要额外的空间来保存，所以要占用更多的空间。
				--    使用非空约束,就可以节省存储空间(每个字段1个bit)。
				
		-- 5.分析表、检查表与优化表
			-- MySQL提供了分析表、检查表和优化表的语句。分析表主要是分析关键字的分布,检查表主要是检查表是否存在错误，
			-- 优化表主要是消除删除或者更新造成的空间浪费。
					
			CREATE TABLE `user1` (
			  `id` INT NOT NULL AUTO_INCREMENT,
			  `name` VARCHAR(255) DEFAULT NULL,
			  `age` INT DEFAULT NULL,
			  `sex` VARCHAR(255) DEFAULT NULL,
			  PRIMARY KEY (`id`),
			  KEY `idx_name` (`name`) USING BTREE
			) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

			SET GLOBAL log_bin_trust_function_creators = 1;

			DELIMITER //
			CREATE FUNCTION rand_num (from_num INT ,to_num INT) RETURNS INT(11)
			BEGIN   
			DECLARE i INT DEFAULT 0;  
			SET i = FLOOR(from_num +RAND()*(to_num - from_num+1))   ;
			RETURN i;  
			END //
			DELIMITER ;

			DELIMITER //
			CREATE PROCEDURE insert_user( max_num INT )
			BEGIN  
			DECLARE i INT DEFAULT 0;   
			 SET autocommit = 0;    
			 REPEAT  
			 SET i = i + 1;  
			 INSERT INTO `user1` ( NAME,age,sex ) 
			 VALUES ("atguigu",rand_num(1,20),"male");  
			 UNTIL i = max_num  
			 END REPEAT;  
			 COMMIT; 
			END //
			DELIMITER;

			CALL insert_user(1000);

			SHOW INDEX FROM user1;
			SELECT * FROM user1;
			UPDATE user1 SET NAME = 'atguigu03' WHERE id = 3;

			-- 分析表
			ANALYZE TABLE user1;
				-- 查询表索引时，分析表语句会更新参数Cardinality的值。参数Cardinality表示表中索引对应的字段有多少不同值

			-- 检查表(了解)
			CHECK TABLE user1;

			-- 优化表：只对varchar、bolb、text类型起作用
			CREATE TABLE t1(id INT,NAME VARCHAR(15)) ENGINE = MYISAM;
			OPTIMIZE TABLE t1;

			CREATE TABLE t2(id INT,NAME VARCHAR(15)) ENGINE = INNODB;
			OPTIMIZE TABLE t2;
				-- 对表进行增删后进行碎片整理，节省空间

		-- 6.大表优化(单表数据过多时)
			-- [1]限定查询范围：使用条件查询
			-- [2]读写分离(主从模式)
			-- [3]垂直拆分：将多个表平均分配到多个主机上，减少单个主机的查询量
			-- [4]水平拆分：将多个表进行分表，根据数据的查询量将各表的数据平衡分配到各个主机上
			--              不推荐，因为要将所有数据平均分配到各个主机上，逻辑、部署、运维复杂度高
		
		-- 7.其他优化策略	
			-- [1]创建全局通用表空间
				--  创建名为atguigu1的共享表空间
				CREATE TABLESPACE atguigu1 ADD DATAFILE 'atguigu1.ibd' file_block_size=16k;
				-- 指定将新建的表放在atguigu1表空间中，而不是其他空间中
				CREATE TABLE test(id INT,NAME VARCHAR(10)) ENGINE=INNODB DEFAULT CHARSET utf8mb4 TABLESPACE atguigu1;
				-- 将已有表放在atguigu1表空间中
				ALTER TABLE test TABLESPACE atguigu1;
				-- 删除表空间(需要先删除或移出表空间内的所有表)
				DROP TABLESPACE atguigu1;
				DROP TABLE test;
				
			-- [2]服务器语句超时处理
				-- 用户访问服务器超时时报错。
				SET SESSION MAX_EXECUTION_TIME = 2000;
					-- 设置会话中访问超时时间(0为无限大)
				
			-- [3]隐藏索引(见前)
				