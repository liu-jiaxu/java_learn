-- 范式

	-- 第一次愣是没学懂啥是啥QAQ

	-- 一.范式
		-- 1.在关系数据库中，关于数据表设计的基本原则、规则称为范式(Normal Form)，简称NF。
		
		-- 2.范式级别从低到高：
			-- 第一范式(1NF)、第二范式(2NF)、第三范式、巴斯-科德范式(BCNF)，第四范式、第五范式(完美范式)
			
		-- 3.反范式化：为提高查询性能破坏原有范式规则
		
		-- 4.键和属性
			-- 超键：能唯一标识元组的属性集叫做超键，是主键与其它属性结合的属性集。
			-- 候选键：如果超键不包括多余的属性,那么这个超键就是候选键。
			-- 主键：用户可以从候选键中选择一个作为主键。
			-- 外键：如果数据表R1中的某属性集不是R1的主键，而是另一个数据表R2的主键，那么这个属性集就是数据表R1的外键。
			-- 主属性：包含在任一候选键中的属性称为主属性。
			-- 非主属性：与主属性相对，指的是不包含在任何一个候选键中的属性。
				-- 通常,我们也将候选键称之为“码”,把主键也称为“主码”。因为键可能是由多个属性组成的,针对单个属性,我们
				-- 还可以用主属性和非主属性来进行区分。
				
			-- 例：球员表：球员编号、姓名、身份证号、年龄、球队编号
			--     球队表：球队编号、主教练、球队所在地
				-- 对于球员表：
					-- 超键：球员编号/身份证号+其它任意个属性形成的属性集
					-- 候选键：球员编号/身份证号
					-- 主键：从球员编号或身份证号选一
					-- 外键：球员表的球队编号
					-- 主属性：球员编号、身份证号
					-- 非主属性：姓名、年龄、球队编号
		


	-- 二.范式级别
		-- 1.第一范式(1NF)
			-- 数据表中每个字段的值必须具有原子性，也就是说每个字段的值为不可再次拆分的最小数据单元
			-- 原子性具有主观性，需要根据实际需求是否拆分(例如地址是否要拆分为省市县住址)
			-- 数据表的最基本要求。所有DBMS都会满足第一范式要求

			-- 例：字段：用户信息(包含姓名、电话、地址等)，可以进行拆分，因此此字段不满足第一范式原子性
		
		-- 2.第二范式(2NF)
			-- 第一范式+数据表的每一条数据记录都是可唯一标识的。所有非主键字段都必须完全依赖全部主键，不能仅满足一部分主键
			
			-- 例1：成绩表：学号、课程号、成绩、任课教师
				-- 主键必须为联合主键(学号、课程号)，只有这样其它的非主键成绩、任课教师才可以完全依赖，只有学号
				-- 无法知道是哪科成绩和任课教师，必须使用联合主键才满足
				
			-- 例2：比赛表：球员编号、姓名、年龄、比赛编号、比赛时间、比赛场地、个人得分
				-- (1)分键：(球员编号、比赛编号) -> (姓名、年龄、比赛时间、比赛场地、个人得分)
				-- (2)依赖关系：
					-- (球员编号、比赛编号) -> (个人得分)
					-- (球员编号) -> (姓名、年龄)
					-- (比赛编号) -> (比赛时间、比赛场地)
				-- (3)依照字段划分发现该表不满足第二范式
				-- (4)带来的问题：[1]数据冗余(一个球员编号可能对应多个比赛编号，而这些数据必须全部记录)
				--   	          [2]插入、删除、更新异常(调整联合主键的单个主键时会影响其他主键)
				-- (5)解决方案：分成三张表
					-- 球员表：球员编号、姓名、年龄
					-- 比赛表：比赛编号、比赛时间、比赛场地
					-- 球员比赛关系表：球员编号、比赛编号、个人得分
			
		-- 3.第三范式(3NF)
			-- 第二范式+要求每个非主键字段均不能依赖于其他非主键字段，必须相互独立(主键与非主键不能有传递依赖)
			
			-- 例1：商品表：编号、类别编号、类别名称、商品名称、价格
				-- (1)分键：(编号) -> (类别编号、类别名称、商品名称、价格) 
					-- 类别名称依赖类别编号，而类别编号又依赖于编号(编号与类别名称存在传递依赖)，不满足第三范式
					-- 非主键字段完全依赖于主键，满足第二范式
				-- (2)解决方案：分为两张表
					-- 商品类别表：编号、类别名称
					-- 商品表：编号、类别编号、商品名称、价格
						-- 商品表通过类别编号和商品类别表关联
			
			-- 例2：球员表：球员编号、姓名、球队名称、球队教练
				-- (1)分键：(球员编号) -> (姓名、球队名称、球队教练)
					-- 球队教练依赖球队名称，主键与球队教练存在传递依赖，不符合3NF
				-- (2)分表：
					-- 球员表：球员编号、姓名、球队名称
					-- 球队表：球队名称、球队教练
			
		-- 4.巴斯范式(BCNF)
			-- 第三范式改进，消除主属性对候选键的部分依赖或传递依赖关系
				
			-- 例：仓库表：仓库名、管理员、物品名、数量
				-- 候选键：(管理员、物品名)，(仓库名、物品名)
				-- 主属性：仓库名、物品名、管理员
				-- 满足第三范式，分表以满足巴斯范式
					-- 仓库表：仓库名、管理员
					-- 库存表：仓库名、物品名、数量
					
		-- 5.第四范式(4NF)
			-- 多值依赖的概念：
				-- 多值依赖：即属性之间的一对多关系，记为K→→A。
				-- 函数依赖：事实上是单值依赖,所以不能表达属性值之间的一对多关系。
				-- 平凡的多值依赖：全集U=K+A，一个K可以对应于多个A，即K→→A。此时整个表就是一组一对多关系。
				-- 非平凡的多值依赖：全集U=K+A+B,一个K可以对应于多个A,也可以对应于多个B,A与B互相独立,即K→→A, K→→B。整个表有
				--                   多组一对多关系，且有： “一”部分是相同的属性集合， “多”部分是互相独立的属性集合。
			-- 第四范式即在满足巴斯-科德范式（BCNF）的基础上，消除非平凡与函数依赖的多值依赖（即把同一表内的多对多关系删除）。
				
			-- 例：职工表：职工编号、职工孩子姓名、职工选修课程
				-- 一个职工可能有多个孩子及选修课程，存在多值对应多值，不符合第四范式。
				-- 分表：
					-- 职工表1：职工编号、职工孩子姓名
					-- 职工表2：职工编号、职工选修课程
						-- 一张表只存在一个多值对应，满足第四范式
						
		-- 6.第五范式、域键范式
			-- 关系模式R中的每一个连接依赖均由R的候选键所隐含，无实际意义。
			-- 域键范式：不研究了
			
			
			
	-- 三.反范式化
		-- 通过在数据表中增加冗余字段提高数据库读取性能
	
		CREATE DATABASE atguigudb3;
		USE atguigudb3;
		
		-- 学生表
		CREATE TABLE student(
		stu_id INT PRIMARY KEY AUTO_INCREMENT,
		stu_name VARCHAR(25),
		create_time DATETIME
		);
		
		-- 课程评论表
		CREATE TABLE class_comment(
		comment_id INT PRIMARY KEY AUTO_INCREMENT,
		class_id INT,
		comment_text VARCHAR(35),
		comment_time DATETIME,
		stu_id INT
		);
		
		-- -- -- 创建向学生表中添加数据的存储过程
		DELIMITER //
		CREATE PROCEDURE batch_insert_student(IN START INT(10), IN max_num INT(10))
		BEGIN
		DECLARE i INT DEFAULT 0;
		DECLARE date_start DATETIME DEFAULT ('2017-01-01 00:00:00');
		DECLARE date_temp DATETIME;
		SET date_temp = date_start;
		SET autocommit=0;
		REPEAT
		SET i=i+1;
		SET date_temp = DATE_ADD(date_temp, INTERVAL RAND()*60 SECOND);
		INSERT INTO student(stu_id, stu_name, create_time)
		VALUES((START+i), CONCAT('stu_',i), date_temp);
		UNTIL i = max_num
		END REPEAT;
		COMMIT;
		END //
		DELIMITER ;
		
		-- 调用存储过程，学生id从10001开始，添加1000000数据
		CALL batch_insert_student(10000,1000000);
		
		-- 创建向课程评论表中添加数据的存储过程
		DELIMITER //
		CREATE PROCEDURE batch_insert_class_comments(IN START INT(10), IN max_num INT(10))
		BEGIN
		DECLARE i INT DEFAULT 0;
		DECLARE date_start DATETIME DEFAULT ('2018-01-01 00:00:00');
		DECLARE date_temp DATETIME;
		DECLARE comment_text VARCHAR(25);
		DECLARE stu_id INT;
		SET date_temp = date_start;
		SET autocommit=0;
		REPEAT
		SET i=i+1;
		SET date_temp = DATE_ADD(date_temp, INTERVAL RAND()*60 SECOND);
		SET comment_text = SUBSTR(MD5(RAND()),1, 20);
		SET stu_id = FLOOR(RAND()*1000000);
		INSERT INTO class_comment(comment_id, class_id, comment_text, comment_time, stu_id)
		VALUES((START+i), 10001, comment_text, date_temp, stu_id);
		UNTIL i = max_num
		END REPEAT;
		COMMIT;
		END //
		DELIMITER ;
		
		-- 添加数据的存储过程的调用，一共1000000条记录
		CALL batch_insert_class_comments(10000,1000000);

		-- -- -- -- -- -- -- -- -- 
		SELECT COUNT(*) FROM student;
		SELECT COUNT(*) FROM class_comment;

		-- -- -- 需求-- -- -- -- -- -- 
		SELECT p.comment_text, p.comment_time, stu.stu_name 
		FROM class_comment AS p LEFT JOIN student AS stu 
		ON p.stu_id = stu.stu_id 
		WHERE p.class_id = 10001 
		ORDER BY p.comment_id DESC 
		LIMIT 10000;

		-- -- -- -- -- 进行反范式化的设计-- -- -- -- -- -- 
		-- 表的复制
		CREATE TABLE class_comment1
		AS
		SELECT * FROM class_comment;

		-- 添加主键，保证class_comment1与class_comment的结构相同
		ALTER TABLE class_comment1
		ADD PRIMARY KEY (comment_id);

		SHOW INDEX FROM class_comment1;

		-- 向课程评论表中增加stu_name字段
		ALTER TABLE class_comment1
		ADD stu_name VARCHAR(25);

		-- 给新添加的字段赋值
		UPDATE class_comment1 c
		SET stu_name = (
		SELECT stu_name
		FROM student s
		WHERE c.stu_id = s.stu_id
		);

		-- 查询同样的需求
		SELECT comment_text, comment_time, stu_name 
		FROM class_comment1 
		WHERE class_id = 10001 
		ORDER BY comment_id DESC 
		LIMIT 10000;

		-- 问题：
			-- (1)存储空间变大
			-- (2)一个表中字段修改后，另一个表中冗余字段也需要同步修改，否则数据不一致
			-- (3)更新频繁消耗系统资源
			-- (4)数据量小时反范式化无优势
		-- 使用建议；
			-- (1)冗余字段不需要经常修改，查询时不可或缺
			-- (2)历史快照、历史数据的存储
