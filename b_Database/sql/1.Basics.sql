-- 使用MySQL学习数据库！
	
/*
数据库概念
	数据库DB(Database):文件系统，保存数据
	数据库管理系统DBMS(Database Management System)：建立、使用、维护数据库
	结构化查询语言SQL(Structured Query Language)：数据库通信语言
		
常见数据库
	Oracle/MySQL(使用)/SQL Server/DB2/PostgreSQL  
	关系型数据库：二元关系(二维表格式)，行列形式(表)存储数据，方便查询，现实世界用关系模型表示
	非关系型数据库：基于键值存储，减少功能提供性能
		例：键值型数据库、文档型数据库、搜索引擎数据库、列式数据库、图形数据库
			
关系型数据库设计规则
	1.数据结构：数据表(数据在表(.ibd)中，表在库(.mdf)里)
	2.示例：
				学生信息表
		学号		姓名		年龄		性别
		110		张三		 20		 男
		111		李四		 19		 女
		112		王五		 21		 男
		113 		赵六		 22		 女
	3.E-R模型(实体集、属性、联系集)
		实体集--表(学生信息表)
		实体--表的一行(记录)(110 张三 20 男)
		属性--表的一列(字段)(年龄 20 19 21 22)
	4.表的四种关系
		一对一关联(一条记录对应一条记录)
			例：人和身份证一一关联、学生和学号一一关联
		一对多关联
			例：部门和工人一对多、省和市
		多对多关联(必须有中间表联系两表)
			例1：学生表：一行记录表示学生信息(姓名、学号...)
				课程表：一行记录表示课程信息(课程编号、授课老师...)
				选课表：一个学生可以选多门课，一门课也可以被多个学生选(多对多关联)
			例2：产品与订单，产品有多个不同订单，一个订单可有多个不同产品
		自我引用
			例：			 员工信息表
				姓名		工号		职别		主管工号
				张三		101		员工		  102
				李四		102		主管		  NULL
				王五		103		员工		  102
				张三的主管工号对应此表中的102李四，即自我引用
*/
	
/*
控制台使用SQL语句(SQL Server)
C:\Users\zgh29>osql -S127.0.0.1 -Usa -Pzgh2960425
1> use AList
2> select * from ListInfo
3> go
	stunumber    name            type       password
	2020123001   刘思瑶             1          lsy123                  
	2020123002    黄烨              2          hy123	                  
	2020123003   陈思雨             2          csy123	                 
	2020123004    郑旭              2          zx123                   
	2020123005   王华宇             1          why123	                  
(5 行受影响) 
	
控制台使用SQL语句(mysql)
	1.查询版本 mysql --version
	2.登录数据库 mysql -uroot -pzgh2960425(-u后为用户，-p后为密码)
	3.查询数据库 show databases;
	4.新建数据库 create database dbtest1;
	5.使用(选择)数据库(作为下次使用) use dbtest1;
	6.查询表 show tables;
	7.新建表 create table employee(id int,name varchar(15));
	8.查询数据(示例) select * from employee;
	9.添加数据(示例) insert into employee values(1001,'Tom');
	10.显示表设置 show create table employee;
	11.查询编码 show variables like 'character_%';
	12.修改编码：在my.ini文件下修改[mysql](63行左右，defalut-character-set=utf8)
		     和[mysqld](76行，character-set-server=utf8 collation-server=utf8_general_ci)，之后重启mysql80
	13.删除数据库 drop database dbtest1;

示例：
	C:\Users\zgh29>mysql --version
	mysql  Ver 8.0.31 for Win64 on x86_64 (MySQL Community Server - GPL)

	C:\Users\zgh29>mysql -uroot -pzgh2960425
	mysql: [Warning] Using a password on the command line interface can be insecure.
	Welcome to the MySQL monitor.  Commands end with ; or \g.
	Your MySQL connection id is 8
	Server version: 8.0.31 MySQL Community Server - GPL
		
	Copyright (c) 2000, 2022, Oracle and/or its affiliates.
		
	Oracle is a registered trademark of Oracle Corporation and/or its
	affiliates. Other names may be trademarks of their respective
	owners.
		
	Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
		
	mysql> show databases;
	+--------------------+
	| Database           |
	+--------------------+
	| information_schema |
	| mysql              |
	| performance_schema |
	| sys                |
	+--------------------+
	4 rows in set (0.01 sec)
		
	mysql> create database dbtest1;
	Query OK, 1 row affected (0.01 sec)
		
	mysql> show databases;
	+--------------------+
	| Database           |
	+--------------------+
	| dbtest1            |
	| information_schema |
	| mysql              |
	| performance_schema |
	| sys                |
	+--------------------+
	5 rows in set (0.00 sec)
		
	mysql> use dbtest1;
	Database changed
	mysql> show tables;
	Empty set (0.00 sec)
		
	mysql> create table employee(id int,name varchar(15));
	Query OK, 0 rows affected (0.01 sec)
		
	mysql> show tables;
	+-------------------+
	| Tables_in_dbtest1 |
	+-------------------+
	| employee          |
	+-------------------+
	1 row in set (0.00 sec)
		
	mysql> insert into employee values(1001,'Tom');
	Query OK, 1 row affected (0.00 sec)
		
	mysql> select * from employee;
	+------+------+
	| id   | name |
	+------+------+
	| 1001 | Tom  |
	+------+------+
	1 row in set (0.00 sec)
		
	mysql> show create table employee;
	+----------+---------------------------------------------------------------------------------------------------------------------------------------------------------+
	| Table    | Create Table                                                                                                                                            |
	+----------+---------------------------------------------------------------------------------------------------------------------------------------------------------+
	| employee | CREATE TABLE `employee` (
		`id` int DEFAULT NULL,
		`name` varchar(15) DEFAULT NULL
	) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci |
	+----------+---------------------------------------------------------------------------------------------------------------------------------------------------------+
	1 row in set (0.00 sec)
		
	mysql> show variables like 'character_%';
	+--------------------------+---------------------------------------------------------------------+
	| Variable_name            | Value                                                               |
	+--------------------------+---------------------------------------------------------------------+
	| character_set_client     | gbk                                                                 |
	| character_set_connection | gbk                                                                 |
	| character_set_database   | utf8mb4                                                             |
	| character_set_filesystem | binary                                                              |
	| character_set_results    | gbk                                                                 |
	| character_set_server     | utf8mb4                                                             |
	| character_set_system     | utf8mb3                                                             |
	| character_sets_dir       | D:\SoftwareInstallation\MySQL\Software Installation\share\charsets\ |
	+--------------------------+---------------------------------------------------------------------+
	8 rows in set, 1 warning (0.00 sec)
		
	mysql> drop database dbtest1;
	Query OK, 1 row affected (0.02 sec)
*/
	