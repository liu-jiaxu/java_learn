-- 性能分析

	-- 1.优化三步骤：慢查询、EXPLAIN、SHOW PROFILING
	
	-- 2.修改措施
	-- 硬件  系统配置  数据库表结构  SQL及索引
		-- 成本：高 -> 低
		-- 效果：低 -> 高
		
	-- 3.查看系统性能参数
	-- 在MySQL中,可以使用SHOW STATUS语句查询一些MySQL数据库服务器的性能参数、执行效率。
		-- SHOW STATUS语句语法:SHOW [GLOBAL | SESSION] STATUS LIKE '参数';
			-- 一些常用的性能参数如下:
			-- Connections:连接MysQL服务器的次数
			-- Uptime: MysQ服务器的上线时间
			-- Slow_queries:慢查询的次数
			-- Innodb_rows_read: Select查询返回的行数
			-- Innodb_rows_inserted:执行INSERT操作插入的行数
			-- Innedb_rows_updated: 执行UPDATE操作更新的行数
			-- Innodb_rows_deleted: 执行DELETE操作删除的行数
			-- Com_select:查操作的次数
			-- Com_insert:入操作的次数。对于批量插入的INSERT操作,只累加一次
			-- Com_update:更作的次数
			-- Com_delete:删作的次数
		SHOW STATUS LIKE 'Connections'; 
		SHOW STATUS LIKE 'Uptime'; 
		SHOW STATUS LIKE 'Innodb_rows_inserted'; 
		SHOW STATUS LIKE 'Com_update'; 
	
	-- 4.统计SQL查询成本
		-- 查询最后一条语句执行成本
		SELECT * FROM student_info;
		SHOW STATUS LIKE 'last_query_cost';
			-- Variable_name	Value
			-- Last_query_cost	181142.349000
				-- 最后一条语句执行后的数据来自181142.349个数据页
	
	-- 5.定位执行慢的SQL：慢查询日志
		-- [1]查看、打开慢查询
		SHOW VARIABLES LIKE '%slow_query_log';
		SET GLOBAL slow_query_log = ON;
		-- [2]查看、修改慢查询默认时间(重启失效)
		SHOW VARIABLES LIKE '%long_query_time%';
		SET long_query_time = 5; -- 修改慢查询时间为5s
			-- 永久修改：my.cnf文件
			[mysqld]
			slow_query_log = ON;
			slow_query_log_file = var/lib/mysql/atguigu-slow.log;
			long_query_time=5;
			long_output=FILE;
			
		-- [3]查询慢查询记录
		SET sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
			-- 去除only_full_group_by错误，但每次连接都要执行

		SELECT student_id, COUNT(*) AS num FROM student_info 
		GROUP BY student_id 
		ORDER BY create_time DESC 
		LIMIT 100; -- 15s
		SHOW STATUS LIKE 'Slow_queries'; -- 执行上述语句后慢查询记录+1 
		
	-- 6.SHOW PROFILING
		-- 查看、开启
		SHOW VARIABLES LIKE 'profiling';
		SET profiling = 'ON';
	
		-- 查看会话查询信息
		SHOW PROFILES;
		
		-- 查看最近一次查询信息
		SHOW PROFILE;
	
		-- 查看指定一条查询信息
		SHOW PROFILE cpu,block io FOR QUERY 400;
	