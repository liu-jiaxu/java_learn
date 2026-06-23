-- 事务

	-- 一.事务概述
		-- 1.存储引擎支持
			SHOW ENGINES; -- 查看支持事务(Transactions)的引擎，只有innodb支持
			
		-- 2.基本概念
			-- [1]事务：一组逻辑操作单元，使数据从一种状态变为另一种状态。
			-- [2]事务处理原则：保证所有事务都作为一个工作单元来执行。一个事务中的多个操作要么全都被事务提交(commit)，修改会永久保存；
			--		    要么就放弃本次的所有修改，整个事务回滚(rollback)到最初状态
			-- [3]例：A给B100元，A要-100元且同时B+100元，若A-100元后数据库管理系统宕机，B未+100元，则此时事务要保证全都不修改，回滚到
			-- 	  最初状态。若同时有C给D100元，则这和A -> B 100元是两个事务，不能合一！
			
		-- 3.事务的特性ACID
			-- ACID：原子性(atomicity)、一致性(consistency)、隔离性(isolation)、持久性(durability)
			-- 原子性是基础，隔离性是手段，一致性是约束条件，持久性是目的
			
			-- [1]原子性：事务是一个不可分割的工作单位，要么全部提交，要么全部回滚。
			-- [2]一致性：数据从一个合法性状态变换为另一个合法性状态(合法性状态：符合现实和业务约束)
				-- 例：从200元的卡里取300元，取钱后卡剩-100元，不符合现实
			-- [3]隔离性：一个事务的执行不能被其他事务干扰。一个事务内部的操作和数据对并发的事务是隔离的
				-- 例：一张卡2000元，同时从银行取2000元和从微信转账出2000元，事务之间相互影响，不满足隔离性
			-- [4]持久性：一旦事务提交后，数据的修改是永久的。
				-- 补：持久性通过事务日志(重做日志和回滚日志)保证。
				
		-- 4.事务的状态
			-- 活动的(active)
				-- 事务对应的数据库操作正在执行过程中时,我们就说该事务处在活动的状态。
			-- 部分提交的(partially committed)
				-- 当事务中的最后一个操作执行完成,但由于操作都在内存中执行,所造成的影响并没有刷新到磁盘时,我
				-- 们就说该事务处在部分提交的状态。
			-- 失败的(failed)
				-- 当事务处在活动的或者部分提交的状态时,可能遇到了某些错误(数据库自身的错误、操作系统错误或者
				-- 直接断电等)而无法继续执行,或者人为的停止当前事务的执行,我们就说该事务处在失败的状态。
			-- 中止的(aborted)
				-- 如果事务执行了一部分而变为失败的状态,那么就需要把已经修改的事务中的操作还原到事务执行前的状
				-- 态。换句话说,就是要撤销失败事务对当前数据库造成的影响。我们把这个撤销的过程称之为回滚。当回
				-- 滚操作执行完毕时,也就是数据库恢复到了执行事务之前的状态,我们就说该事务处在了中止的状态。
			-- 提交的（committed）
				-- 当一个处在部分提交的状态的事务将修改过的数据都同步到磁盘上之后,就可以说该事务处在提交的状态。
	
	
		
	-- 二.事务基础知识
		-- 1.事务过程
			-- 开启事务、一系列的DML操作、结束状态(提交commit，中止rollback)
			
		-- 2.显式事务
			-- [1]开启事务
				-- 使用关键字start transaction 或 begin
				-- start transaction后可加read only(只读)/read write(可读可写，默认)/with consistent snapshot(立即得到事务一致性快照)
				START TRANSACTION READ WRITE;
				UPDATE account SET balance = balance - 10 WHERE id = 1; 
				UPDATE account SET balance = balance + 10 WHERE id = 2;
				COMMIT;
					-- 本事务共两条UPDATE语句，最后为COMMIT提交事务(或是ROLLBACK)
					
			-- [2]保存点(savapoint)
				-- 设置保存点，可以使本次事务回滚到保存点处，不用回滚至最初状态了
				-- 设置保存点
					SAVEPOINT 保存点名称;
				-- 回滚至保存点
					ROLLBACK TO [SAVEPOINT];
				-- 删除保存点
					RELEASE SAVEPOINT 保存点名称;

		-- 3.隐式事务
			-- [1]关键字：autocommit 
			SHOW VARIABLES LIKE 'autocommit'; -- 默认是ON
			-- 指定autocommit为ON时，每条DML操作均为一个事务，执行后立即提交
			UPDATE account SET balance = balance - 10 WHERE id = 1; -- 此时这条DML操作是一个独立的事务
			UPDATE account SET balance = balance + 10 WHERE id = 2; -- 此时这条DML操作是一个独立的事务

			-- [2]关闭自动提交
				-- 方式1：
				SET autocommit = FALSE; -- 针对于DML操作是有效的，对DDL操作是无效的。
				-- 指定autocommit为OFF后，在CONNIT或ROLLBACK之前的DML语句均为一个事务
				UPDATE account SET balance = balance - 10 WHERE id = 1;
				UPDATE account SET balance = balance + 10 WHERE id = 2; 
				COMMIT; -- 或rollback;
				
				-- 方式2：我们在autocommit为true的情况下，使用start transaction或begin开启事务，那么DML操作就不会自动提交数据
				START TRANSACTION;
				UPDATE account SET balance = balance - 10 WHERE id = 1;
				UPDATE account SET balance = balance + 10 WHERE id = 2; 
				COMMIT; -- 或rollback;
				
			-- [3]不受autocommit控制的语句
				-- {1}数据定义语言DDL，CREATE,ALTER,DROP...
				-- {2}隐式使用或修改MySQL数据库中的表，ALTER USER,CREATE USER,DROP USER,GRANT,RENAME USER,REVOKE,SET PASSWORD
				-- {3}事务控制或关于锁定的语句，前一个事务未提交或回滚时又开启下一个事务，则上一个事务自动提交
				-- {4}加载数据的语句，LOAD DATA导入数据
				-- {5}MySQL的部分复制语句，START SLAVE,STOP SLAVE,RESET SLAVE,CHANGE MASTER TO
				-- {6}其他一些语句
				
		-- 4.案例分析
		SET autocommit = TRUE; 
			-- [1]commit和rollback
				USE atguigudb2;
				-- 情况1：
				CREATE TABLE user3(NAME VARCHAR(15) PRIMARY KEY);
				SELECT * FROM user3;

				BEGIN;
				INSERT INTO user3 VALUES('张三'); -- 此时不会自动提交数据(有BEGIN)
				COMMIT;

				BEGIN; -- 开启一个新的事务
				INSERT INTO user3 VALUES('李四'); -- 此时不会自动提交数据
				INSERT INTO user3 VALUES('李四'); -- 受主键的影响，不能添加成功
				ROLLBACK; -- 执行后回滚至上一次事务提交，因此数据“李四”没有添加

				SELECT * FROM user3; -- 执行ROLLBACK后只有数据“张三”

				-- 情况2：
				TRUNCATE TABLE user3; -- 清除表数据
				-- DDL操作会自动提交数据，不受autocommit变量的影响。

				SELECT * FROM user3;

				BEGIN;
				INSERT INTO user3 VALUES('张三'); -- 此时不会自动提交数据
				COMMIT;

				INSERT INTO user3 VALUES('李四'); --  默认情况下(即autocommit为true)，DML操作也会自动提交数据。
				INSERT INTO user3 VALUES('李四'); -- 事务的失败的状态
				
				ROLLBACK; -- 回滚最近一次提交，事务失败也算提交，因此回滚至第二次添加“李四”前，即已经添加了数据张三和李四

				SELECT * FROM user3;

				-- 情况3：
				TRUNCATE TABLE user3;

				SELECT * FROM user3;

				SELECT @@completion_type;
				SET @@completion_type = 1;
				-- Completion_type=0
					-- 这个MySQL的默认值，当我们commit提交事务之后，下一次如果我们还想开启事务，必须显式指定begin 
					-- 或者start transaction
				-- Completion_type=1
					-- 当我们提交事务后，相当于执行了commit and chain也就是开启了一个链式事务，也就是我们开启事务之
					-- 后会自动开启一个和我们上一个有相同隔离级别的事务
				-- Completion_type=2
					-- 当我们提交事务之后，会自动与服务器断开连接commit and release

				BEGIN;
				INSERT INTO user3 VALUES('张三'); 
				COMMIT;

				SELECT * FROM user3;

				-- 参数completion_type = 1设置后，开启链式事务，所有未在BEGIN/START TRANSACTION与COMMIT/ROLLBACK中的DML操作
				-- 均会变为链式事务上下链接，当一次执行COMMIT或ROLLBACK后，会一次性的全部提交或回滚
				-- 下方四条DML为链式事务
				INSERT INTO user3 VALUES('李四');
				INSERT INTO user3 VALUES('q'); 
				UPDATE user3 SET NAME = 'aa' WHERE NAME = 'q';
				UPDATE user3 SET NAME = 'bb' WHERE NAME = '张三'; 

				ROLLBACK; -- 一次性的回滚四条DML语句，回滚至添加数据“张三”之后

				SELECT * FROM user3;



			-- [2]INNODB和MyISAM
				CREATE TABLE test1(i INT) ENGINE = INNODB;
				CREATE TABLE test2(i INT) ENGINE = MYISAM;

				-- 针对于innodb表
				BEGIN;
				INSERT INTO test1 VALUES (1);
				ROLLBACK;

				SELECT * FROM test1;

				-- 针对于myisam表:不支持事务
				BEGIN;
				INSERT INTO test2 VALUES (1);
				ROLLBACK; -- 一旦提交无视回滚

				SELECT * FROM test2;



			-- [3]savepoint

				CREATE TABLE user4(NAME VARCHAR(15),balance DECIMAL(10,2));

				BEGIN;
				INSERT INTO user4(NAME,balance) VALUES('张三',1000);
				COMMIT;

				SELECT * FROM user4;

				BEGIN;
				UPDATE user4 SET balance = balance - 100 WHERE NAME = '张三';
				UPDATE user4 SET balance = balance - 100 WHERE NAME = '张三';

				SAVEPOINT s1;-- 设置保存点

				UPDATE user4 SET balance = balance + 1 WHERE NAME = '张三';

				ROLLBACK TO s1; -- 回滚到保存点

				SELECT * FROM user4;

				ROLLBACK; -- 回滚操作，回滚到上一次COMMIT

				SELECT * FROM user4;



	-- 三.事务隔离级别
		-- 1.数据并发问题
			-- [1]脏写(Dirty Write)
				-- 事务A修改另一个未提交事务B修改过的数据
				-- 例：A、B同时操作同一数据，A把原始数据c改为a后提交，而B修改原始数据为b后发现修改错误要回滚数据至c，则最
				--     后原始数据不变，仍未c，此时A的数据修改无效，称为脏写
			-- [2]脏读(Dirty Read)
				-- 事务A读取已经被事务B更新但未提交的字段
				-- 例：A、B同时操作同一数据c，A在查询数据c的同时，B错误的把数据c改为了b，但在回滚前此数据被A查询到了，则A
				--     查询到了脏数据b而不是原始数据c
			-- [3]不可重复读(Non-Repeatable Read)
				-- 事务A读取一个字段后事务B修改了此字段，事务A再次读取此字段后发现值不同，此时发生了不可重复读
				-- 例：A读一个字段c，B又修改此字段为b，A又重复读此字段，发现值变为b，此时发生不可重复读
			-- [4]幻读(Phantom)
				-- 事务A读取一个表后事务B在表中插入了新字段，事务A再次读取此表后发现多出几行，此时发生了幻读
				-- 注：插入的数据称为幻影记录，删除记录不是幻读
			
		-- 2.SQL中四种隔离级别
			-- [1]影响：脏写 > 脏读 > 不可重复读 > 幻读
			
			-- [2]隔离级别：低 -> 高
				-- {1}READ UNCOMMITTED：读未提交，解决脏写
				-- {2}READ COMMITTED：读已提交，解决脏写、脏读
				-- {3}REPEATABLE READ：可重复读，解决脏写、脏读、不可重复读
				-- {4}SERIALIZABLE：可串行化(单个事务操作数据期间，其它事务无法进行增删改)，全部解决，但有加锁读
					-- Oracle仅支持2、4，MySQL全部支持，
					-- Oracle默认为读已提交，MySQL默认为可重复读
			
			-- [3]查询隔离级别
				SELECT @@transaction_isolation;
				
			-- [4]修改隔离级别
				-- 方式一：
					SET [GLOBAL | SESSION] TRANSACTION ISOLATION LEVEL 隔离级别;
					-- 隔离级别四选一：READ UNCOMMITTED，READ COMMITTED，REPEATABLE READ，SERIALIZABLE
				-- 方式二：
					SET [GLOBAL | SESSION] TRANSACTION_ISOLATION = '隔离级别';
					-- 隔离级别四选一：READ-UNCOMMITTED，READ-COMMITTED，REPEATABLE-READ，SERIALIZABLE
				-- 设置对应会话或全局连接即可解决对应问题！
			