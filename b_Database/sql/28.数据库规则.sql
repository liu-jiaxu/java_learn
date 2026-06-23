-- 数据库规则

	CREATE TABLE test1(id INT,`name` VARCHAR(25));
	INSERT INTO test1 VALUES (1,'aaa');
	INSERT INTO test1 VALUES (2,'bbb');

	 -- 1.字符集utf8介绍
		-- (1)对比
			-- [1]utf8：一个字符使用1-4个字节表示(在MySQL中一个字符用1-3个字节表示，可以节省空间)
			-- [2]uft8mb3：一个字符用1-3个字节表示(一般使用这个)
			-- [3]utf8mb4：一个字符使用1-4个字节表示(存储一些emoji表情等等用到这个)
		
		-- (2)查看MySQL支持字符集
		SHOW CHARSET;
		
		-- (3)比较规则(后缀介绍)
			-- _ai   不区分重音
			-- _as   区分重音
			-- _ci   不区分大小写
			-- _cs   区分大小写
			-- _bin  以二进制方式比较
			
		-- (4)查看字符集比较规则
				-- [1]gbk(一个汉字占2字节)
				SHOW COLLATION LIKE 'gbk%';
				-- [2]utf8(一个汉字占3字节)
				SHOW COLLATION LIKE 'utf8%';
				-- [3]服务器
				SHOW VARIABLES LIKE '%_server';
				-- [4]数据库
				SHOW VARIABLES LIKE '%_database';
				-- [5]表
				SHOW TABLE STATUS FROM dbtest1 LIKE 'emp';
			
		-- (5)utf8常用比较规则	
			-- utf8_general_ci校对速度快，准确性稍差(对于中英文无区别)
			-- utf8_unicode_ci校对速度慢，准确性高(适合查询德语、法语等外语)			
			
		-- (6)查看字符集
			-- [1]数据库
			SHOW CREATE DATABASE dbtest1;
			-- [2]表
			SHOW CREATE TABLE emp;
			
		-- (7)修改具体数据库/表字符集
			-- [1]修改数据库
			ALTER DATABASE dbtest1 DEFAULT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
			-- [2]修改表
			ALTER TABLE emp DEFAULT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
			
		-- (8)发送与返回时字符集总结
			-- 客户端发送到处理器、处理器翻译字符集使用的编码必须一致
			-- 服务器返回字符集、客户端响应的字符集编码必须一致
		
		
			
	-- 2.SQL大小写规范
		-- (1)Windows & Linux
			-- Windows大小写不敏感，Linux大小写敏感
			-- 查看设置
			SHOW VARIABLES LIKE '%lower_case_table_names%';
				-- 参数Value：0(默认)：大小写敏感
				--	      1：大小写不敏感。SQL语句均转换为小写存放
				--	      2：创建语句依照原语句格式存放，查询语句转换为小写
			
		-- (2)Linux具体规则：
			-- [1]数据库名、表名、表别名、变量名区分大小写
			-- [2]关键字、函数名不区分大小写
			-- [3]列名(字段名)、列别名忽略大小写
			
		-- (3)建议
			-- [1]关键字和函数名大写
			-- [2]数据库名、表名、表别名、字段名、字段别名小写
			-- [3]SQL语句结尾加;
		
		
			
	-- 3.Linux数据库存放位置
		cd /var/lib/mysql  
		ll		
		
		
			 
	-- 4.登录MySQL服务器
		-- 完整登录语句：mysql -h hostname|hostIP -P port -u username -p DatabaseName -e "SQL语句"
			-- -h：后接主机名或IP
			-- -P：后接服务端口(MySQL默认端口3306)
			-- -u：后接用户名
			-- -p：提示输入密码
			-- DatabaseName：指定具体数据库
			-- -e：登录完成后自动执行的SQL语句
		-- 例：mysql -uroot -p -hlocalhost -P3306 mysql -e "select host from user;"
		-- 简易登录语句：mysql -uroot -p密码(以root用户登录MySQL数据库)
	
	
	
	-- 5.用户设置
		-- MySQL默认提供四个用户，其中root是权限最大的用户，其余三个是系统用户
		
		-- (1)创建
		-- 创建用户
		CREATE USER 'zhangsan' IDENTIFIED BY 'abc123';
		-- 创建用户并指定权限
		CREATE USER 'zhangsan'@'localhost' IDENTIFIED BY 'abc123'; -- 指定权限为localhost
	
		-- (2)查看用户及权限
		USE mysql; 
		SELECT `host`,`user`
		FROM `user`; -- user表使用联合主键，host及user两个字段联合主键
	
		-- (3)以创建用户登录
		-- mysql -uroot -pabc123
			-- 默认情况下新创建的用户仅有登录权限
	
		-- (4)修改用户名
		UPDATE mysql.user SET `user` = 'lisi'
		WHERE `user` = 'zhangsan' AND `host` = '%';
	
		-- (5)删除用户(删除后要刷新设置)
		DROP USER zhangsan,lisi; -- 可一次删除多个(但默认只删除host='%'的用户)
		DROP USER 'zhangsan'@'localhost'; -- 删除具体用户
		FLUSH PRIVILEGES; -- 刷新权限
	
		-- (6)设置用户密码(哪个用户登录修改的就是哪个用户的密码)
			-- [1]修改root用户密码
			-- 方式一：
			ALTER USER USER() IDENTIFIED BY 'abcabc'; -- 此处修改的是root用户的密码，修改为abcabc
			-- 方式二：
			SET PASSWORD = 'zgh2960425';
		
			-- [2]修改其他用户密码(必须以root用户登录修改)
			-- 方式一
			ALTER USER 'lisi'@'%' IDENTIFIED BY 'Hello123';
			-- 方式二
			SET PASSWORD FOR 'lisi'@'%' = 'abc123';
	
	
	
	-- 6.MySQL密码管理策略
		-- (1)密码过期策略：定期修改密码
			-- [1]管理员手动设置立马过期
			ALTER USER 'lisi'@'%' PASSWORD EXPIRE;
			-- [2]指定过期时间
			CREATE USER 'wangwu'@'localhost' IDENTIFIED BY 'abc123' PASSWORD EXPIRE INTERVAL 90 DAY; -- 可在创建用户时指定
			ALTER USER 'lisi'@'%' PASSWORD EXPIRE INTERVAL 90 DAY;
			-- [3]永不过期
			ALTER USER 'lisi'@'%' PASSWORD EXPIRE NEVER;
			-- [4]沿用全局密码过期策略
			ALTER USER 'lisi'@'%' PASSWORD EXPIRE DEFAULT;
	
		-- (2)密码重用策略
			-- 可以设置新密码要求：不能与前几次/前几天的密码相同
				-- password_history：密码重用数量
				-- password_reuse_interval：密码重用周期
			-- 例：设置不能使用最近5个密码，也不能使用365天内的密码
			-- 创建用户时
			CREATE USER 'zhouyi'@'localhost' IDENTIFIED BY 'abc123' 
			PASSWORD HISTORY 5
			PASSWORD REUSE INTERVAL 365 DAY;
			-- 修改用户
			ALTER USER 'zhouyi'@'localhost'
			PASSWORD HISTORY 5
			PASSWORD REUSE INTERVAL 365 DAY;
	
			SELECT `host`,`user`
			FROM `user`;
	
		-- (3)密码强度
			-- [1]启动服务
			INSTALL PLUGIN validate_password SONAME 'validate_password.so';
			-- [2]查看密码强度参数
			SHOW VARIABLES LIKE 'validate_password%';
				-- 		选项			默认值				介绍
				-- validate_password_check_user_name	ON          设置为ON的时候表示能将密码设置成当前用户名。
				-- validate_password_dictionary_file		    用于检查密码的字典文件的路径名，默认为空
				-- validate_password_length		8	    密码的最小长度,也就是说密码长度必须大于或等于8
				-- validate_password_mixed_case_count	1	    如果密码策略是中等或更强的，validate_password要求密码具有的
				--					        小写和大写字符的最小数量。对于给定的这个值密码必须有那么多小
				--					        写字符和那么多大写字符。
				-- validate_password_number_count	1	    密码必须包含的数字个数
				-- validate_password_policy		MEDIUM      密码强度检验等级，可以使用数值0、1、2或相应的符号值LOW,MEDIUM, 
				--						STRONG来指定。0/LOW:只检查长度。1/MEDIUM:检查长度、数字、大小写、
				--						特殊字符。2/STRONG:检查长度、数字、大小写、特殊字符、字典文件。
				-- validate_password_special_char_count	1	    密码必须包含的特殊字符个数
			-- [3]修改参数
			SET GLOBAL validate_password_policy = LOW; 
			SET GLOBAL validate_password_number_count = 2;
			SET GLOBAL validate_password_lenght = 1; -- 修改密码字符长度
			-- [4]删除服务
			UNINSTALL PLUGIN validate_password;
				