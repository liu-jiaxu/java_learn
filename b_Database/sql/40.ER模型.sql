-- ER模型

	-- 1.三要素：
		-- 实体：强实体(不依赖其他实体)、弱实体(依赖...)，可独立存在的。矩形表示
		-- 属性：实体的特性，表的字段，不可再分。椭圆形表示
		-- 关系：实体之间的联系，菱形表示
		
	-- 2.关系模型
		-- 一对一：实体之间一一对应(个人和身份证)
		-- 一对多：(学生和班级)
		-- 多对多：(学生和选课)
		
	-- 3.ER模型转换为数据表
		-- (1)一个实体通常转换成一个数据表
		-- (2)一个多对多的关系通常也转换成一个数据表(依靠中间表连接)
		-- (3)一对一、一对多关系通过外键表示(实际业务尽量不要外键约束)
		-- (4)属性变为字段
		
	-- 4.数据表设计原则
		-- (1)数据表个数越少越好
		-- (2)数据表字段个数越少越好。数据冗余，检索效率
		-- (3)联合主键的字段越少越好。联合主键索引占用空间大
		-- (4)使用主键和外键(逻辑实现，ER模型实现)越多越好。数据表独立性
		
	-- 5.数据库对象编写建议
		-- (1)库
		1.【强制】库的名称必须控制在32个字符以内，只能使用英文字母、数字和下划线，建议以英文字母开头。
		2 【强制】库名中英文一律小写，不同单词采用下划线分割，须见名知意。
		3.【强制】库的名称格式：业务系统名称_子系统名。
		4.【强制】库名禁止使用关键字（如type,order等）。
		5.【强制】创建数据库时必须显式指定字符集，并且字符集只能是utf8或者utf8mb4。
			  创建数据库SQL举例: CREATE DATABASE crm_fund DEFAULT CHARACTER SET'utf8';
		6.【建议】对于程序连接数据库账号，遵循权限最小原则。
			  使用数据库账号只能在一个DB下使用，不准跨库，程序使用的账号原则上不准有drop权限。
		7.【建议】临时库以tmp_为前缀，并以日期为后缀
			  备份库以bak_为前缀，并以日期为后缀。
			  
		-- (2)表、列
		1. 【强制】表和列的名称必须控制在32个字符以内，表名只能使用英文字母、数字和下划线，建议以英文字母开头。
		2. 【强制】表名，列名一律小写，不同单词采用下划线分割。须见名知意。
		3. 【强制】表名要求有模块名强相关，同一模块的表名尽量使用统一前缀。比如：crm_fund_item。
		4. 【强制】创建表时必须显式指定字符集为utf8或utf8mb4。
		5. 【强制】表名、列名禁止使用关键字（如type,order等）。
		6. 【强制】创建表时必须显式指定表存储引擎类型。如无特殊需求，一律为innoDB。
		7. 【强制】建表必须有comment注释。
		8. 【强制】字段命名应尽可能使用表达实际含义的英文单词或缩写。
			   如:公司ID不要使用corporation-id而用corp_id即可。
		9. 【强制】布尔值类型的字段命名为is_描述。
			   如member表上表示是否为enabled的会员的字段命名为is_enabled,
		10.【强制】禁止在数据库中存储图片、文件等大的二进制数据。
			   通常文件很大，短时间内造成数据量快速增长，数据库进行数据库读取时，通常会进行大量的随机IO操作，文件很大时，
			   IO操作很耗时，通常存储于文件服务器，数据库只存储文件地址信息。
		11.【建议】建表时关于主键：表必须有主键。
			   (1)强制要求主键为id,类型为int或bigint，且为auto_increment建议使用unsigned无符号型
			   (2)标识表里每一行主体的字段不要设为主键,建议设为其他字段如user_id,order_id等,并建立unique key索引。
			      因为如果设为主键且主键值为随机插入，则会导致innodb内部页分裂和大量随机IO，性能下降。
		12.【建议】核心表（如用户表)必须有行数据的创建时间字段(create_time）和最后更新时间字段(undate TIME)，便于查问题。
		13.【建议】表中所有字段尽量都是NOT NULL属性，业务可以根据需要定义DEFAULT值。因为使用NULL值会存在每一行都会占用额外存储空间、
			   数据迁移容易出错、聚合函数计算结果偏差等问题。
		14.【建议】所有存储相同数据的列名和列类型必须一致（一般作为关联列，如果查询时关联列类型不一致会自动进行数据类型隐式转换，会
			   造成列上的索引失效，导致查询效率降低）。
		15.【建议】中间表(临时表)用于保留中间结果集，名称以tmp_开头。备份表用于备份或抓取源表快照,名称以bak-开头。中间表和备份表定期清理。
		16.【示范】一个较为规范的建表语句：
		CREATE TABLE user_info (
		`id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键', 
		`user_id` BIGINT(11) NOT NULL COMMENT '用户id',
		`username` VARCHAR(45) NOT NULL COMMENT '真实姓名',
		`email` VARCHAR(30) NOT NULL COMMENT '户箱',
		`nickname` VARCHAR(45) NOT NULL COMMENT'昵称',
		`birthday` DATE NOT NULL COMMENT '生日',
		`sex` TINYINT(4) DEFAULT '0' COMMENT'性别',
		`short_introduce` VARCHAR(150) DEFAULT NULL COMMENT '一句话介绍自己，最多50个汉字',
		`user_resume` VARCHAR(300) NOT NULL COMMENT '用户提交的简历存放地址',
		`user_register_ip` INT NOT NULL COMMENT '用户注册时的源ip',
		`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间', 
		`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
		`user_review_status` TINYINT NOT NULL COMMENT '用户资料审核状态：1为通过,2为审核中,3为未通过,4为还未提交审核',
		PRIMARY KEY (`id`),
		UNIQUE KEY `uniq_user_id` (`user_id`),
		KEY idx_username (`username`),
		KEY idx_create_time_status (`create_time`, `user_review_status`)
		) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='网站用户基本信息';
		17, 【建议】创建表时，可以使用可视化工具。这样可以确保表，字段相关的约定都能设置。
			    实际上,我们通常很少自己写DDL语句,可以使用一些可视化工具来创建和操作数据库和数据表。
			    可视化工具除了方便,还能直接帮我们将数据库的结构定义转化成SQL语言，方便数据库和数据表结构的导出和导入。
		
		-- (3)索引
		1.【强制】InnoDB表必须主键为id INT/BIGINT auto_increment，且主键值禁止被更新。
		2.【强制】InnoDB和MyISAM存储引擎表，索引类型必须为BTREE。
		3.【建议】主键的名称以pk_开头，唯一键以uni_或uk_开头，普通索引以idx_开头，一律使用小写格式，以字段的名称或缩写作为后缀。
		4.【建议】多单词组成的columnname，取前几个单词首字母,加末单词组成column_name。
			  如: sample表member_id上的索引: idx_sample_mid。
		5.【建议】单个表上的索引个数不能超过6个。
		6.【建议】在建立索引时，多考虑建立联合索引，并把区分度最高的字段放在最前面。
		7.【建议】在多表JOIN的SQL里,保证被驱动表的连接列上有索引,这样JOIN执行效率最高。
		8.【建议】建表或加索引时，保证表里互相不存在冗余索引。
			  比如：如果表里已经存在key(a,b),则key(a)为冗余索引,需要删除。
		
		-- (4)SQL编写
		 1.【强制】程序端SELECT语句必须指定具体字段名称，禁止写成*。
		 2.【建议】程序端insert语句指定具体字段名称，不要写成INSERT INTO t1 VALUES(...)。
		 3.【建议】除静态表或小表(100行以内), DML语句必须有WHERE条件,且使用索引查找。
		 4.【建议】INSERT INTO...VALUES(XX),(XX),(XX)..这里XX的值不要超过5000个。值过多虽然上线很快，但会引起主从同步延迟。
		 5.【建议】SELECT语句不要使用UNION,推荐使用UNION ALL,并且UNION子句个数限制在5个以内。
		 6.【建议】线上环境,多表JOIN不要超过5个表。
		 7.【建议】减少使用ORDER BY,和业务沟通能不排序就不排序,或将排序放到程序端去做。ORDER BY、GROUP BY, DISTINCT这些语句
			   较为耗费CPU,数据库的CPU资源是极其宝贵的。
		 8.【建议】包含了ORDER BY, GROUP BY, DISTINCT这些查询的语句, WHERE条件过滤出来的结果集请保持在1000行以内，否则SQL会很慢。
		 9.【建议】对单表的多次alter操作必须合并为一次对于超过100w行的大表进行alter TABLE,必须经过DBA审核，并在业务低峰期执行,
			   多个alter需整合在一起。因为alter table会产生表锁,期间阻塞对于该表的所有写入,对于业务可能会产生极大影响。
		
	-- 6.PowerDesigner使用
		-- 如下是ER模型自动转换的SQL语句(还TM有点好用啊这东西...)
		-- 注意：尽量用空数据库，因为会删除原表！
		
		/*==============================================================*/


		DROP TABLE IF EXISTS students;

		DROP TABLE IF EXISTS class;

		/*==============================================================*/
		/* Table: students                                               */
		/*==============================================================*/
		CREATE TABLE students
		(
		   stu_id               INT NOT NULL,
		   class_id             INT,
		   NAME                 VARCHAR(125),
		   age                  INT,
		   sex                  CHAR(1),
		   school               VARCHAR(125),
		   PRIMARY KEY (stu_id)
		);

		ALTER TABLE students COMMENT '学生信息';

		/*==============================================================*/
		/* Table: 班级表                                                   */
		/*==============================================================*/
		CREATE TABLE class
		(
		   class_id             INT NOT NULL,
		   class_name           VARCHAR(10),
		   class_count          INT,
		   class_info           VARCHAR(30),
		   PRIMARY KEY (class_id)
		);

		ALTER TABLE students ADD CONSTRAINT FK_class_students_r FOREIGN KEY (class_id)
		      REFERENCES class (class_id) ON DELETE RESTRICT ON UPDATE RESTRICT;
	