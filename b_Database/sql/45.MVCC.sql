-- MVCC

	-- 一.MVCC(多版本并发控制)
		-- 1.介绍
			-- MVCC(Multiversion Concurrency Control，多版本并发控制)，通过数据行的多个版本管理来实现数据库的并发控制。这项
			-- 技术使得在InnoDB的事务隔离级别下执行一致性读操作有了保证。换言之，就是为了查询一些正在被另一个事务更新的行，并
			-- 且可以看到它们被更新之前的值，这样在做查询的时候就不用等待另一个事务释放锁。

		-- 2.快照读与当前读
			-- MVCC在MySQL InnoDB中的实现主要是为了提高数据库并发性能，用更好的方式去处理读-写冲突(快照读-当前读)，做到即
			-- 使有读写冲突时，也能做到不加锁，非阻塞并发读，而这个读指的就是快照读，而非当前读。当前读实际上是一种加锁的
			-- 操作，是悲观锁的实现。而MVCC本质是采用乐观锁思想的一种方式。
			
			-- [1]快照读(读)
				-- 快照读又叫一致性读，读取的是快照数据。不加锁的简单的SELECT都属于快照读，即不加锁的非阻塞读;
					-- 例：
						SELECT * FROM player WHERE...
				-- 快照读是基于提高并发性能的考虑，快照读的实现是基于MVCC，它在很多情况下，避免了加锁操作，降低了开销。
				-- 既然是基于多版本，那么快照读可能读到的并不一定是数据的最新版本，而有可能是之前的历史版本。
				-- 快照读的前提是隔离级别不是串行级别，串行级别下的快照读会退化成当前读。
			-- [2]当前读(写)
				-- 当前读读取的是记录的最新版本(最新数据，而不是历史版本的数据)，读取时还要保证其他并发事务不能修改当前
				-- 记录，会对读取的记录进行加锁。加锁的SELECT，或者对数据进行增删改都会进行当前读。
				-- 例：
					SELECT * FROM student LOCK IN SHARE MODE; --  共享锁
					SELECT * FROM student FOR UPDATE; --  排他锁
					INSERT INTO student VALUES ... --  排他锁
					DELETE FROM student WHERE ... --  排他锁
					UPDATE student SET .... --  排他锁
					
		-- 3.隐藏字段，undo log版本链
			-- [1]隐藏字段
				-- undo日志的版本链，对于使用InnoDB存储引擎的表来说，它的聚簇索引记录中都包含两个必要的隐藏列。
					-- trx_id：每次一个事务对某条聚簇索引记录进行改动时，都会把该事务的事务id赋值给trx_id隐藏列。
					-- roll_pointer：每次对某条聚簇索引记录进行改动时，都会把旧的版本写入到undo日志中，然后这个
					--		 隐藏列就相当于一个指针，可以通过它来找到该记录修改前的信息。
			-- [2]undo log版本链
				-- undo日志串连成的链表
				
		-- 4.MVCC实现原理
			-- 隐藏字段、undo log、read view
			
			-- [1]ReadView
				-- ReadView就是事务A在使用MVCC机制进行快照读操作时产生的读视图。当事务启动时，会生成数据库系统当前的一个快照，，
				-- InnoDB为每个事务构造了一个数组，用来记录并维护系统当前活跃事务的ID ("活跃”指的就是，启动了但还没提交)。
				
			-- [2]设计思路
				-- 无效
				-- {1}使用READ UNCOMMITTED隔离级别的事务，由于可以读到未提交事务修改过的记录，所以直接读取记录的最新版本就好了。
				-- {2}使用SERIALIZABLE隔离级别的事务，InnoDB规定使用加锁的方式来访问记录。
				-- 有效
				-- {3}使用READ COMMITTED和REPEATABLE READ隔离级别的事务，都必须保证读到已经提交了的事务修改过的记录。假如另一个
				--    事务已经修改了记录但是尚未提交，是不能直接读取最新版本的记录的，核心问题就是需要判断一下版本链中的哪个版本
				--    是当前事务可见的，这是ReadView要解决的主要问题。
				
			-- [3]ReadView中主要包含4个重要的内容
				-- {1}creator_trx_id，创建这个Read View的事务ID。说明：只有在对表中的记录做改动时(执行INSERT、DELETE，UPDATE这些
				--    语句时)才会为事务分配事务id，否则在一个只读事务中的事务id值都默认为0。
				-- {2}trx_ids，表示在生成ReadView时当前系统中活跃的读写事务的事务id列表。
				-- {3}up_limit_id，活跃的事务中最小的事务ID。
				-- {4}low_limit_id，表示生成ReadView时系统中应该分配给下一个事务的id值。low_limit_id是系统最大的事务id值，这里要注
				--    意是系统中的事务id，需要区别于正在活跃的事务ID注意：low_limit_id并不是trx_ids中的最大值，事务id是递增分配的。
				--    比如，现在有id为1，2，3这三个事务，之后id为3的事务提交了。那么一个新的读事务在生成ReadView时，trx_ids就包括1
				--    和2，up_limit_id的值就是1，low_limit_id的值就是4。
				
				-- 例：系统有三个事务3，5，8，则trx_ids列表为[3,5,8]，up_limit_id=3，low_limit_id=9,
				--     creator_trx_id增删改时分配id3，5，8，其他默认为0
			
			-- [4]ReadView规则
				-- 依次将undo log的链表中的trx_id和ReadView中的各个参数比较
			
				-- {1}如果被访问版本的trx_id属性值与ReadView中的creator_trx_id值相同，意味着当前事务在访问它自己修改过的记录，所以
				--    该版本可以被当前事务访问。
				-- {2}如果被访问版本的trx_id属性值小于ReadView中的up_limit_id值，表明生成该版本的事务在当前事务生成ReadView前已经提
				--    交，所以该版本可以被当前事务访问。
				-- {3}如果被访问版本的trx_id属性值大于或等于ReadView中的low_limit_id 值，表明生成该版本的事务在当前事务生成ReadView
				--    后才开启，所以该版本不可以被当前事务访问，继续在链表中向下读其他trx_id。
				-- {4}如果被访问版本的trx_id属性值在ReadView的up_limit_id和low_limit_id之间，那就需要判断一下trx_id属性值是不是在 
				--    trx_ids列表中。
					-- (1)如果在，说明创建ReadView时生成该版本的事务还是活跃的，该版本不可以被访问，继续在链表中向下读其他trx_id。
					-- (2)如果不在，说明创建ReadView时生成该版本的事务已经被提交，该版本可以被访问。
					
				-- 隔离级别
					-- 读已提交：一次事务每次查询都要重新获取一次ReadView
					-- 不可重复读：一次事务仅在第一次查询时获取一次ReadView
					
			-- [5]示例：
				-- {1}READ COMMITTED隔离级别：每次查询都要重新获取一次ReadView
					-- 最初事务8
						INSERT INTO student VALUES
						(1,'c');
					-- 事务10
						BEGIN;
						UPDATE student SET NAME = "a" WHERE id = 1;
						UPDATE student SET NAME = "b" WHERE id = 2;
					-- 事务20
						BEGIN;
						UPDATE ...
					-- READ COMMITTED隔离级别新事务执行
						BEGIN;
						SELECT * FROM student WHERE id = 1;
						
					-- 新事务执行过程：
						-- (1)执行select时会生成一个ReadView，ReadView的trx_ids列表内容为[10,20]，up_limit_id=10，
						--    low_limit_id=21,creator_trx_id=0
						-- (2)undo log生成链表结构，依次从上往下将链表中的trx_id和ReadView中的参数比较
						-- (3)trx_id从上往下值依次为10、10、8(根据事务进行顺序排)，先拿最上的10与ReadView参数比较，
						--    发现10在trx_ids列表中，即该事务还是活跃的，未提交不可访问，此时需要继续在链表中向下读
						--    trx_id，依次判断
						-- (4)当读到trx_id=8时，发现其小于up_limit_id，表示该事务已经提交可访问，因此查询出此条数据  
						-- (5)当新事物重新进行查询操作时，仍会生成新的ReadView，重复此操作(例：事务10提交且事务20再
						--    执行一些修改操作，重新查询时，会生成不同的undo log链表和ReadView，此时由于事务10的提
						--    交，trx_ids列表的内容仅有[20]，此时重新判断比较trx_id和参数得到查询结果)
				
				-- {2}REPEATABLE READ隔离级别：仅在第一次查询时获取一次ReadView
					-- 最初事务8
						INSERT INTO student VALUES
						(1,'c');
					-- 事务10
						BEGIN;
						UPDATE student SET NAME = "a" WHERE id = 1;
						UPDATE student SET NAME = "b" WHERE id = 1;
					-- 事务20
						BEGIN;
						UPDATE ...
					-- READ COMMITTED隔离级别新事务执行
						BEGIN;
						SELECT * FROM student WHERE id = 1;
						
					-- 新事务执行过程：
						-- (1)-(4)同上
						-- (5)当新事物重新进行查询操作时，不会生成新的ReadView，重复使用第一次生成的ReadView(例：
						--    事务10提交且事务20再执行一些修改操作，重新查询时，仅会生成不同的undo log链表，此
						--    时trx_ids列表的内容仍为原先的[10,20]，此时重新判断比较trx_id和参数得到查询结果，
						--    本次查询结果应和第一次查询结果相同，规避不可重复读)
						
			-- [6]MVCC解决幻读(REPEATABLE READ隔离级别)
				-- 最初事务8
					INSERT INTO student VALUES(1,'c');
				-- 事务10
					BEGIN;
					INSERT INTO student VALUES(1,'a');
					INSERT INTO student VALUES(1,'b');
				-- 事务20
					BEGIN;
					SELECT * FROM student WHERE id = 1;
					
				-- 事务20执行过程：
					-- (1)执行select时会生成一个ReadView，ReadView的trx_ids列表内容为[10,20]，up_limit_id=10，
					--    low_limit_id=21,creator_trx_id=0
					-- (2)undo log生成链表结构，依次从上往下将链表中的trx_id和ReadView中的参数比较
					-- (3)trx_id从上往下值依次为10、10、8(根据事务进行顺序排)，先拿最上的10与ReadView参数比较，
					--    发现10在trx_ids列表中，即该事务还是活跃的，未提交不可访问，此时需要继续在链表中向下读
					--    trx_id，依次判断
					-- (4)当读到trx_id=8时，发现其小于up_limit_id，表示该事务已经提交可访问，因此查询出此条数据
					-- (5)此时事务20查询的结果是事务10操作之前的结果，解决了幻读

			-- [7]总结
				-- MVCC = 隐藏字段(trx_id+roll_pointer) + undo log(多版本) + ReadView(并发控制)
				
				-- 解决问题
					-- 读写之间阻塞问题：读写之间不阻塞
					-- 降低死锁频率：MVCC采用乐观锁，读数据不加锁，写数据加行锁
					-- 解决快照读问题：快照读不加锁会产生不可重复读和幻读问题，而MVCC采用不加锁的方式解决这些问题，
					--		   使查询当前时间点的快照时只可以看到此时间点之前提交更新的版本结果
				