-- 锁

	-- 一.概述
		-- 1.锁是计算机协调多个进程或线程并发访问某一资源的机制。在程序开发中会存在多线程同步的问题，当多个线程并发访问某个数据的时候，
		--   尤其是针对一些敏感的数据(比如订单、金额等)，我们就需要保证这个数据在任何时刻最多只有一个线程在访问，保证数据的完整性和一致
		--   性。在开发过程中加锁是为了保证数据的一致性，这个思想在数据库领域中同样很重要。
		-- 2.在数据库中，除传统的计算资源(如CPU，RAM，I/O等)的争用以外，数据也是一种供许多用户共享的资源。为保证数据的一致性，需要对并
		--   发操作进行控制，因此产生了锁。同时锁机制也为实现MySQL的各个隔离级别提供了保证。锁冲突也是影响数据库并发访问性能的一个重要
		--   因素。所以锁对数据库而言显得尤其重要，也更加复杂。
	
	
	
	-- 二.MySQL并发事务访问相同记录
		-- 1.并发事务访问相同记录的情况大致可以划分为3种：
		-- [1]读-读情况
			-- 读-读情况，即并发事务相继读取相同的记录。读取操作本身不会对记录有任何影响，并不会引起什么问题，所以允许这种情况的发生。
			
		-- [2]写-写情况
			-- {1}写-写情况，即并发事务相继对相同的记录做出改动。在这种情况下会发生脏写的问题，任何一种隔离级别都不允许这种问题的发生。
			--    所以在多个未提交事务相继对一条记录做改动时，需要让它们排队执行，这个排队的过程其实是通过锁来实现的。这个所谓的锁其实
			--    是一个内存中的结构，在事务执行前本来是没有锁的，也就是说一开始是没有锁结构和记录进行关联的。
			-- {2}在锁结构里有很多信息，为了简化理解，只把两个比较重要的属性拿了出来：
				-- trx信息：代表这个锁结构是哪个事务生成的。
				-- is_waiting：代表当前事务是否在等待。false执行，true加锁等待
			-- {3}写写加锁示例：
				-- 当多个事务修改同一数据时，若此时内存中没有对应记录的锁结构，会自动在内存中生成。哪个事务对记录做修改，就会生成对
				-- 应锁结构与之关联。事务A修改记录时，生成锁结构且is_waiting=false表示加锁成功，可以执行，其他事务锁结构is_waiting=
				-- true加锁失败，继续等待，当事务A执行完提交后释放锁结构，此时其他某个事务B的is_waiting=false执行修改操作，依次执行。
			
		-- [3]读-写或写-读情况
			-- 读-写或写-读，即一个事务进行读取操作，另一个进行改动操作。这种情况下可能发生 脏读、不可重复读、幻读的问题。
			
		-- 2.加锁三种情况
			-- [1]不加锁
				-- 不需要在内存中生成对应的锁结构，可以直接执行操作。
			-- [2]获取锁成功，或加锁成功
				-- 在内存中生成了对应的锁结构，而且锁结构的is_waiting属性为false ，也就是事务可以继续执行操作。
			-- [3]获取锁失败，或加锁失败，或者没有获取到锁
				-- 在内存中生成了对应的锁结构，不过锁结构的is_waiting属性为true ，也就是事务需要等待，不可以继续执行操作。
		
		-- 3.并发问题的解决方案
			-- [1]读操作利用多版本并发控制(MVCE)，写操作进行加锁。所谓的MVCC ，就是生成一个ReadView，通过ReadView找到符合条件
			--    的记录版本(历史版本由undo日志构建)。查询语句只能读到在生成ReadView之前已提交事务所做的更改，在生成ReadView之前未提交
			--    的事务或者之后才开启的事务所做的更改是看不到的。而写操作肯定针对的是最新版本的记录，读记录的历史版本和改动记录的最新
			--    版本本身并不冲突，也就是采用MVCC时，读-写操作并不冲突。
			
			-- [2]读、写操作都采用加锁的方式。如果我们的一些业务场景不允许读取记录的旧版本，而是每次都必须去读取记录的最新版本。比如，
			--    在银行存款的事务中，你需要先把账户的余额读出来，然后将其加上本次存款的数额，最后再写到数据库中。在将账户余额读取出
			--    来后，就不想让别的事务再访问该余额，直到本次存款事务执行完成，其他事务才可以访问账户的余额。这样在读取记录的时候就
			--    需要对其进行加锁操作，这样也就意味着读操作和写操作也像写-写操作那样排队执行。
			
			--        脏读的产生是因为当前事务读取了另一个未提交事务写的一条记录，如果另一个事务在写记录的时候就给这条记录加锁，那么
			--    当前事务就无法继续读取该记录了，所以也就不会有脏读问题的产生了。
			
			--     	  不可重复读的产生是因为当前事务先读取一条记录，另外一个事务对该记录做了改动之后并提交之后，当前事务再次读取时会
			--    获得不同的值，如果在当前事务读取记录时就给该记录加锁，那么另一个事务就无法修改该记录，自然也不会发生不可重复读了。
			
			--        幻读问题的产生是因为当前事务读取了一个范围的记录，然后另外的事务向该范围内插入了新记录，当前事务再次读取该范围
			--    的记录时发现了新插入的新记录。采用加锁的方式解决幻读问题就有一些麻烦，因为当前事务在第一次读取记录时幻影记录并不存
			--    在，所以读取的时候加锁就有点尴尬（因为你并不知道给谁加锁）。
			
			-- [3]总结
			-- 采用MVCC方式的话，读-写 操作彼此并不冲突，性能更高。
			-- 采用加锁方式的话，读-写 操作彼此需要 排队执行，影响性能。
			-- 一般情况下我们当然愿意采用MVCC 来解决 读-写操作并发执行的问题，但是业务在某些特殊情况下，要求必须采用加锁的方式执行。
	
	
	
	-- 三.锁的不同角度分类
		-- 1.分类
			-- 数据操作类型
				-- 读锁/共享锁
				-- 写锁/排他锁
			-- 锁粒度
				-- 表级锁
					-- 表级别的S锁、X锁
					-- 意向锁
					-- 自增锁
					-- MDL锁
				-- 行级锁
					-- Record Locks
					-- Gap Locks
					-- Next-Key Locks
					-- 插入意向锁
				-- 页级锁
			-- 对待锁的态度
				-- 悲观锁
				-- 乐观锁
			-- 加锁方式
				-- 隐式锁
				-- 显式锁
			-- 其他
				-- 全局锁
				-- 死锁
				
		-- 2.数据操作类型-读锁、写锁
			-- 读锁(readlock)：共享锁(也可以是排它锁)，S表示。加锁成功后不会影响其他锁，无相互阻塞
			-- 写锁(writelock)：排它锁，X表示。加锁成功后，当前事务未提交则会阻塞其他锁，也会被其它未提交的事务锁阻塞
		
			-- 加S锁：
				SELECT * FROM TABLE LOCK IN SHARE MODE; -- 用这个！
				SELECT * FROM TABLE FOR SHARE; -- mysql8.0新增语法
			-- 加X锁：
				SELECT * FROM TABLE FOR UPDATE;
				
			-- 以下示例需要在控制台多个进程(连接)中分别操作
			USE atguigudb;
			-- 例1：加S锁后再加S锁：不会相互影响
				-- 连接1：
				BEGIN;
				SELECT employee_id
				FROM employees LOCK IN SHARE MODE;
				-- 连接2：
				BEGIN;
				SELECT employee_id
				FROM employees LOCK IN SHARE MODE;
				-- 最后要全部提交！
				COMMIT;
			-- 例2：加S锁后再加X锁：X锁被阻塞
				-- 连接1：
				BEGIN;
				SELECT employee_id
				FROM employees LOCK IN SHARE MODE;
				-- 连接2：
				BEGIN;
				SELECT employee_id
				FROM employees FOR UPDATE;
				-- 最后要全部提交！
				COMMIT;
			-- 例3：加X锁后再加S锁：S锁被阻塞
				-- 连接1：
				BEGIN;
				SELECT employee_id
				FROM employees FOR UPDATE;
				-- 连接2：
				BEGIN;
				SELECT employee_id
				FROM employees LOCK IN SHARE MODE;
				-- 最后要全部提交！
				COMMIT;
				
			-- MySQL8.0新特性
				-- 若锁被阻塞，则可设置参数跳过阻塞等待或查询 
					-- NOWAIT：立即返回报错
					-- SKIP LOCKED：返回结果中不包含被锁定的行
				
				-- 例1：NOWAIT
					-- 连接1： 
					BEGIN;
					SELECT * FROM employees FOR UPDATE;
					-- 连接2：
					BEGIN;
					SELECT * FROM employees FOR UPDATE nowait;
						-- 因为查询的数据加X锁了，所以nowait直接报错不再等待了
					-- 最后要全部提交！
					COMMIT;
				-- 例2：SKIP LOCKED
					-- 连接1： 
					BEGIN;
					SELECT * FROM employees FOR UPDATE;
					-- 连接2：
					SELECT * FROM employees FOR UPDATE skip locked;
						-- skip locked跳过X锁，直接查询剩余未加锁的行，没有则返回Empty set
					-- 最后要全部提交！
					COMMIT;
	
		-- 3.数据操作粒度-表级锁、页级锁、行锁
			-- [1]表锁(Table Lock)
				-- {1}表级别的S锁、X锁
					-- 仅使用在myisam中，innodb有行锁
					-- 加S锁
						LOCK TABLES t READ;
					-- 加X锁
						LOCK TABLES t WRITE;
						
					-- 例：
						SHOW OPEN TABLES WHERE in_use > 0; -- 查看加锁的表
						LOCK TABLES employees READ; -- 给表加读锁
						UNLOCK TABLES; -- 释放表锁
						
					-- 表共享读锁(Table Read Lock)
					-- 表独占写锁(Table Write Lock)
						-- 锁类型 自己可读  自己可写  自己可操作其他表  他人可读  他人可写
						--  读锁     是		否	     否		   是	  否，阻塞
						--  写锁     是		是	     否		否，阻塞  否，阻塞
					-- 注：即使给表记录加X锁也会阻塞所有S锁
						
					-- 例：
						LOCK TABLES employees WRITE;
						-- 本地连接
							SELECT * FROM employees; -- 可读
							UPDATE employees SET employee_id = 111 WHERE employee_id = 111; -- 可写
							SELECT * FROM class; -- 其他表不可操作
						-- 新连接
							SELECT * FROM employees; -- 阻塞，直至锁被删除
							UPDATE employees SET employee_id = 111 WHERE employee_id = 111; -- 阻塞
						UNLOCK TABLES;
							
					-- 总结
						-- myisam查询前表加读锁，增删改前表加写锁
						
				-- {2}意向锁(intention lock)
					-- 一种表锁，不与行级锁冲突的表级锁，当给一行记录加S/X锁后，系统会自动给高一级的空间结构(页或表)加
					-- 意向锁，此时意向锁会告知此页或表已经上过共享/排他锁了
					
					-- 意向共享锁(intention shared lock,IS)：事务有意向对表中的某些行加共享锁(S锁)
						SELECT COLUMN FROM TABLE LOCK IN SHARE MODE;
					-- 意向排他锁(intention exclusive lock,IX)：事务有意向对表中的某些行加排他锁(X锁)
						SELECT COLUMN FROM TABLE FOR UPDATE;
						
					-- 例：
						SELECT * FROM employees FOR UPDATE;
							-- 手动加上X锁时，会自动加上IX锁
							
					-- 意向共享锁、意向排它锁之间相互兼容
					-- 		意向共享锁(IS)  意向排它锁(IX)
					-- 共享锁(S)	     兼容	    互斥
					-- 排它锁(X)	     互斥	    互斥
				
				-- {3}自增锁(AUTO-INC锁)
					-- 表级锁
				
				-- {4}元数据锁(MDL锁)
					-- 隐式添加，访问表时自动加上。对表的增删改查加读锁，更改表结构时加写锁
				
			-- [2]InnoDB中的行锁(myisam不支持行锁)
				-- 行锁也称记录锁，锁住某一行。行级锁在存储引擎中实现
				-- 优点：锁定粒度小，发生锁冲突概率低，可以实现并发度高
				-- 缺点：锁的开销大，加锁慢，易出现死锁
				USE atguigudb2;
				CREATE TABLE student2 (
					id INT,
					`name` VARCHAR(20),
					class VARCHAR(20),
					PRIMARY KEY(id)
				) ENGINE = INNODB CHARSET = utf8;
				INSERT INTO student2 VALUES
				(1,'张三','一班'),
				(3,'李四','一班'),
				(8,'王五','二班'),
				(15,'赵六','二班'),
				(20,'钱七','三班');
				SELECT * FROM student2;
			
				-- {1}记录锁(Record Locks)
					-- 锁定某一行，其他线程无法访问或修改
					
					-- 连接1
					BEGIN;
					SELECT * FROM student2 WHERE id = 1 FOR UPDATE; -- 给表id=1的一条记录加X锁
					-- 最后要全部提交！
					COMMIT;
					-- 连接2
					BEGIN;
					SELECT * FROM student2 WHERE id = 2 LOCK IN SHARE MODE;
					SELECT * FROM student2 WHERE id = 3 LOCK IN SHARE MODE;
						-- 当连接1对id=1的记录加X锁后，其他连接的锁可以访问或修改表中的其他记录
					SELECT * FROM student2 WHERE id = 1 LOCK IN SHARE MODE;
					UPDATE student2 SET id = 2 WHERE id = 1;
						-- 但其他连接不能访问或修改id=1的记录，要等连接1释放X锁
					-- 最后要全部提交！
					COMMIT;
				
				-- {2}间隙锁(Gap Locks)
					-- 防止插入幻影记录。使某个范围无法插入字段
					-- 间隙共享和排他锁作用相同，可多次添加
					
					SELECT * FROM student2; -- 查询id间隙
					-- 连接1
					BEGIN;
					SELECT * FROM student2 WHERE id = 7 FOR UPDATE; 
						-- 因为表中没有id=7，所以是间隙锁，给表id为4-7之间加间隙锁，其他连接的锁就无法在此id范围插入数据了
					SELECT * FROM student2 WHERE id = 25 FOR UPDATE; 
						-- 对于大于最大id时，系统会设计间隙为最大id到设置id之间，此处即为(20,25)区间有间隙锁
						-- 对于小于最小id则类似
					-- 最后要全部提交！
					COMMIT;
					-- 连接2
					BEGIN;
					INSERT INTO student2 VALUES (5,'张三','一班');
						-- 其他连接无法在id=4-7之间插入数据，直至连接1锁释放
					-- 最后要全部提交！
					COMMIT;
					
					-- 可能产生死锁，两个连接相互设置间隙锁，又相互查询了间隙范围的数据，相互锁住产生死锁
				
				-- {3}临键锁(Next-Key Locks)
					-- 记录锁+间隙锁
					
					SELECT * FROM student2; -- 查询id间隙
					-- 连接1
					BEGIN;
					SELECT * FROM student2 WHERE id>=3 AND id<=8 FOR UPDATE; 
						-- 对id=3,8做记录锁，对id为(3,8)区间做间隙锁
					-- 最后要全部提交！
					COMMIT;
					-- 连接2
					BEGIN;
					SELECT * FROM student2 WHERE id = 3 LOCK IN SHARE MODE;
					UPDATE student2 SET id = 2 WHERE id = 8;
						-- 其他连接不能访问或修改id=3,8的记录，要等连接1释放记录锁
					INSERT INTO student2 VALUES (5,'张三','一班');
						-- 其他连接无法在id=4-7之间插入数据，直至连接1锁释放
					-- 最后要全部提交！
					COMMIT;
				
				-- {4}插入意向锁(Insert Intention Locks)
					-- 事务在等待时也需要在内存中生成锁，是一种Gap锁(间隙锁)
					-- 插入意向锁不影响其他事务获取记录任何类型的锁
					
					SELECT * FROM student2; -- 查询id间隙
					-- 连接1
					BEGIN;
					SELECT * FROM student2 WHERE id = 7 FOR UPDATE; 
						-- 给表id为4-7之间加间隙锁
					-- 最后要全部提交！
					COMMIT;
					-- 连接2
					BEGIN;
					INSERT INTO student2 VALUES (5,'张三','一班');
						-- 其他连接无法在id=4-7之间插入数据，直至连接1锁释放
						-- 此时为插入意向锁，待连接1提交后，连接2就可以执行了(注意系统超时会自动结束事务)
					-- 最后要全部提交！
					COMMIT;
					-- 连接3
					BEGIN;
					INSERT INTO student2 VALUES (6,'Tom','一班');
						-- 其他连接无法在id=4-7之间插入数据，直至连接1锁释放
						-- 连接1提交后，连接3和连接2没有影响关系，也可以执行
					-- 最后要全部提交！
					COMMIT;
					SELECT * FROM student2; 
						-- 查询发现连接2和连接3已经添加成功了
			
			-- [3]页锁(了解)
				-- {1}在页的粒度上进行锁定开销介于表锁和行锁之间，会出现死锁，并发度一般。
				-- {2}每个层级锁数量有限，因为锁空间的大小是有限的，当某个层级的锁超过本层级的阈值时，就会自动进行锁升级，
				--    用更大粒度的锁代替更小粒度的锁，例如用表锁代替行锁，锁空间降低但并发度数据下降
				-- {3}实际开发innodb用行锁，myisam用表锁
	
		-- 4.锁的态度-乐观锁、悲观锁
			-- [1]悲观锁
				-- 共享资源仅给一个线程使用，其他线程阻塞，适用于写多应用类型
				
			-- [2]乐观锁
				-- 通过程序实现，适用于多读的应用类型，提高吞吐量
				
		-- 5.加锁方式-显式锁、隐式锁
			-- [1]隐式锁
			-- [2]显式锁
			
		-- 6.其他锁-全局锁、死锁
			-- [1]全局锁
				-- 使整个库处于只读状态，增删改及建表修改表结构会被阻塞
				-- 典型使用在全库逻辑备份
				FLUSH TABLES WITH READ LOCK
				
			-- [2]死锁
				-- 两个事务持有对方的锁且等待对方释放，并且双方不会释放自己的锁
				
				-- 处理方式
					-- 等待，直到超时(默认innodb_lock_wait_timeout=50)后回滚undo量最小的思维
					SHOW VARIABLES LIKE 'innodb_lock_wait_timeout';
					SET innodb_lock_wait_timeout=50;
	
	
	
	-- 四.锁监控
		-- 1.分析行锁争夺情况
			SHOW STATUS LIKE 'innodb_row_lock%';
			-- Innodb_row_lock_current_waits:当前正在等待锁定的数量;
			-- Innodb_row_lock_time :从系统启动到现在锁定总时间长度; (等待总时长)
			-- Innodb_row_lock_time_avg：每次等待所花平均时间;(等待平均时长)· 
			-- Innodb_row_lock_time_max:从系统启动到现在等待最常的一次所花的时间;
			-- Innodb_row_lock_waits :系统启动后到现在总共等待的次数;(等待总次数)
	
			SELECT * FROM information_schema.INNODB_TRX;
			SELECT * FROM performance_schema.data_locks; -- 查看阻塞该事务的锁和该事务持有的锁
			SELECT * FROM performance_schema.data_lock_waits; 
			