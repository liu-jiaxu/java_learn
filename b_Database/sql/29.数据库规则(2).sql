-- 数据库规则

	CREATE TABLE test2(id INT,salary DECIMAL(6,2),last_name VARCHAR(25));
	INSERT INTO test2 VALUES (1,2000,'Tom');

	-- 7.用户权限
		-- (1)权限列表
		SHOW PRIVILEGES;
		-- Privilege			Context					Comment
		-- Alter			Tables					To alter the table
		-- Alter routine		Functions,Procedures			To alter or drop stored functions/procedures
		-- APPLICATION_PASSWORD_ADMIN	Server Admin	
		-- AUDIT_ADMIN			Server Admin	
		-- BACKUP_ADMIN			Server Admin	
		-- BINLOG_ADMIN			Server Admin	
		-- BINLOG_ENCRYPTION_ADMIN	Server Admin	
		-- CLONE_ADMIN			Server Admin	
		-- CONNECTION_ADMIN		Server Admin	
		-- Create			Databases,Tables,Indexes		To create new databases and tables
		-- Create role			Server Admin				To create new roles
		-- Create routine		Databases				To use CREATE FUNCTION/PROCEDURE
		-- Create tablespace		Server Admin				To create/alter/drop tablespaces
		-- Create temporary tables	Databases				To use CREATE TEMPORARY TABLE
		-- Create user			Server Admin				To create new users
		-- Create view			Tables					To create new views
		-- Delete			Tables					To delete existing rows
		-- Drop				Databases,Tables			To drop databases, tables, and views
		-- Drop role			Server Admin				To drop roles
		-- ENCRYPTION_KEY_ADMIN		Server Admin	
		-- Event			Server Admin				To create, alter, drop and execute events
		-- Execute			Functions,Procedures			To execute stored routines
		-- File				File access on server			To read and write files on the server
		-- FLUSH_OPTIMIZER_COSTS	Server Admin	
		-- FLUSH_STATUS			Server Admin	
		-- FLUSH_TABLES			Server Admin	
		-- FLUSH_USER_RESOURCES		Server Admin	
		-- Grant option			Databases,Tables,Functions,Procedures	To give to other users those privileges you possess
		-- GROUP_REPLICATION_ADMIN	Server Admin	
		-- Index			Tables					To create or drop indexes
		-- INNODB_REDO_LOG_ARCHIVE	Server Admin	
		-- INNODB_REDO_LOG_ENABLE	Server Admin	
		-- Insert			Tables					To insert data into tables
		-- Lock tables			Databases				To use LOCK TABLES (together with SELECT privilege)
		-- PERSIST_RO_VARIABLES_ADMIN	Server Admin	
		-- Process			Server Admin				To view the plain text of currently executing queries
		-- Proxy			Server Admin				To make proxy user possible
		-- References			Databases,Tables			To have references on tables
		-- Reload			Server Admin				To reload or refresh tables, logs and privileges
		-- Replication client		Server Admin				To ask where the slave or master servers are
		-- Replication slave		Server Admin				To read binary log events from the master
		-- REPLICATION_APPLIER		Server Admin	
		-- REPLICATION_SLAVE_ADMIN	Server Admin	
		-- RESOURCE_GROUP_ADMIN		Server Admin	
		-- RESOURCE_GROUP_USER		Server Admin	
		-- ROLE_ADMIN			Server Admin	
		-- Select			Tables					To retrieve rows from table
		-- SERVICE_CONNECTION_ADMIN	Server Admin	
		-- SESSION_VARIABLES_ADMIN	Server Admin	
		-- SET_USER_ID			Server Admin	
		-- Show databases		Server Admin				To see all databases with SHOW DATABASES
		-- Show view			Tables					To see views with SHOW CREATE VIEW
		-- SHOW_ROUTINE			Server Admin	
		-- Shutdown			Server Admin				To shut down the server
		-- Super			Server Admin				To use KILL thread, SET GLOBAL, CHANGE MASTER, etc.
		-- SYSTEM_USER			Server Admin	
		-- SYSTEM_VARIABLES_ADMIN	Server Admin	
		-- TABLE_ENCRYPTION_ADMIN	Server Admin	
		-- Trigger			Tables					To use triggers
		-- Update			Tables					To update existing rows
		-- Usage			Server Admin				No privileges - allow connect only
		-- XA_RECOVER_ADMIN		Server Admin					
		
		-- (2)权限分布
			-- 表权限：select insert update delete create drop grant references index alter
			-- 列权限：select insert update references
			-- 过程权限：execute alter routine grant
			
		-- (3)授予权限原则
			-- 授权时满足需要最小权限
			-- 限制用户的登录主机(限制指定IP)
			-- 为每个用户设置满足复杂度的密码
			-- 定期清理不需要的用户(删除用户、回收权限)
	
		-- (4)授予权限(仅有root用户可以)
			USE mysql;
			SELECT `host`,`user`
			FROM `user`;
			-- 注：提示用户缺少权限时使用此语句！ 
			GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
			FLUSH PRIVILEGES;
			-- 方式一：通过封装权限到角色中，再赋予给用户
				-- [1]角色理解
					-- 角色是权限的集合，方便管理拥有相同权限的用户
					
				-- [2]创建角色(以下均为root用户)
					CREATE ROLE 'manager'@'localhost'; -- 创建名为manager的角色
					CREATE ROLE 'emp1','emp2','emp3'; -- 默认用户地址为%
					
				-- [3]给角色赋予权限
					GRANT ALL PRIVILEGES ON *.* TO 'manager'@'localhost';
					GRANT SELECT,UPDATE,DELETE ON *.* TO 'emp1'@'%';
					GRANT SELECT,UPDATE,DELETE ON *.* TO 'emp2'@'%' WITH GRANT OPTION;
						-- WITH GRANT OPTION：自己所拥有的权限可以赋予他人
				
				-- [4]查看角色权限
					SHOW GRANTS FOR 'manager'@'localhost';
					SHOW GRANTS FOR 'emp1'@'%';
				
				-- [5]回收角色权限
					REVOKE DELETE,UPDATE ON *.* FROM 'emp1';
					REVOKE ALL PRIVILEGES,GRANT OPTION FROM 'manager'@'localhost'; -- 回收用户所有权限
				
				-- [6]删除角色
					DROP ROLE 'emp3';
			
				-- [7]给用户赋予角色
					CREATE USER 'a' IDENTIFIED BY 'abc123';
					CREATE USER 'b' IDENTIFIED BY 'abc123';
					CREATE USER 'c' IDENTIFIED BY 'abc123';
					GRANT 'manager'@'localhost' TO 'a'@'%';
					GRANT 'emp1'@'%','emp2'@'%' TO 'b'@'%','c'@'%'; -- 一次性给多个用户赋予多个权限
				
				-- [8]激活用户角色(需要用户重新登陆)
					SET DEFAULT ROLE ALL TO
					'a'@'%'; -- 激活用户
					SET DEFAULT ROLE ALL TO
					'b'@'%','c'@'%'; -- 一次激活多个用户
					SELECT CURRENT_ROLE(); -- 查看用户已激活的角色(需要用户重启)
					
				-- [9]撤销用户角色
					REVOKE 'emp1'@'%','emp2'@'%' FROM 'b'@'%';
				
				-- [10]设置强制角色(给每个新建用户创建默认角色)
					SET PERSIST mandatory_roles = 'emp2@%.example.com'; -- 系统重启仍然有效
					SET GLOBAL mandatory_roles = 'emp2@%.example.com'; -- 系统重启失效
				
			-- 方式二：直接给用户授权
			-- 示例：
				-- 例1：给予用户lisi查询、更新数据库dbtest1所有表的权限
				GRANT SELECT,UPDATE ON dbtest1.* TO 'lisi'@'%';
				
				-- 例2：给予用户zhouyi查询、更新、删除数据库dbtest1的test1表的权限
				GRANT SELECT,UPDATE ON dbtest1.test1 TO 'zhouyi'@'localhost';
				GRANT DELETE ON dbtest1.test1 TO 'zhouyi'@'localhost'; -- 可多次赋予同一用户多个权限
				
				-- 例3：给予用户wangwu所有权限(但wangwu没有赋予他人权限的权限)
				GRANT ALL PRIVILEGES ON *.* TO 'wangwu'@'localhost'; -- *.*表示所有数据库下的所有表
				
				-- 例4：创建新用户zhaosi，赋予其查询、更新权限，并允许其将此权限赋予他人
				CREATE USER 'zhaosi' IDENTIFIED BY 'abc123';
				GRANT SELECT,UPDATE ON *.* TO 'zhaosi'@'%' WITH GRANT OPTION; -- WITH GRANT OPTION：自己所拥有的权限可以赋予他人
		
		-- (5)查看用户权限
			-- 用户查看自己权限
			SHOW GRANTS;
			-- root查看他人权限
			SHOW GRANTS FOR 'zhaosi'@'%';
		
		-- (6)收回权限(需用户重新登陆才生效)
			-- REVOKE 权限1,权限2... ON 数据库名称.表名称 FROM 用户名@用户地址;
			-- 例：收回zhouyi用户删除数据库表的权限
			REVOKE DELETE ON *.* FROM 'zhouyi'@'localhost'; -- 即使之前没有赋予的权限删除也能成功
			
		-- (7)权限表
			-- MySQL服务器通过权限表(user表、db表等)控制用户对数据库的访问，权限表存放在mysql数据库中。MySQL启动时，服务器将这些数据库
			-- 表中的权限信息的内容读入内存。
			
		-- (8)访问控制
			-- 连接核实阶段
				-- 核实用户的用户名、主机地址、用户密码，使用user表中的host、user和authentication_string3个字段匹配。
				-- 连接通过等待用户请求，核实未通过服务器拒绝访问
			-- 请求核实阶段
				-- user表 -> db表 -> table_priv表 -> columns_priv表
				-- 所有数据库 -> 具体数据库 -> 具体数据表 -> 具体列
	