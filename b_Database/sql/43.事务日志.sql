-- 事务日志

	-- 一.介绍
		-- 事务有4种特性:原子性、一致性、隔离性和持久性。
		-- 事务的隔离性由锁机制实现。
		-- 事务的原子性、一致性和持久性由事务的redo日志和undo日志来保证。
		
		-- REDO LOG 称为重做日志，提供再写入操作，恢复提交事务修改的页操作，用来保证事务的持久性。
		-- UNDO LOG 称为回滚日志，回滚行记录到某个特定版本，用来保证事务的原子性、一致性。
		
		-- REDO和UNDO都可以视为是一种、恢复操作
		-- redo log：是存储引擎层(innodb)生成的日志，记录的是"物理级别"上的页修改操作，比如页号xxx、偏移量yyy写入了'zzz'数据。
		--	    主要为了保证数据的可靠性；
		-- undo log：是存储引擎层(innodb)生成的日志，记录的是逻辑操作日志，比如对某一行数据进行了INSERT语句操作，那么undo log就
		--	     记录一条与之相反的DELETE操作。主要用于事务的回滚(undo log记录的是每个修改操作的逆操作)和一致性非锁定读
		--	     (undo log回滚行记录到某种特定的版本---MVCC，即多版本并发控制)
	         
	         
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
	-- 二.redo日志
		-- 1.介绍
			-- 数据库中数据以页(16K)为单位存储，每次数据修改后会先在内存中修改，之后再覆盖到磁盘中，若每次仅修改多个页的
			-- 几个数据，需要将多个页全部刷盘，耗费大量资源，因此会先将这些修改先从内存中保存到一个redo日志中，再将此日志
			-- 操作写入磁盘。实际采用WAL技术(Write-Ahead-Logging)，先写日志，再写磁盘。当系统宕机时也可以通过redo log日志
			-- 恢复，保证ACID的D(持久性)。
			
		-- 2.特点
			-- 降低刷盘效率，占用空间小
			-- redo日志顺序写入磁盘
			-- 事务执行过程中，redo log不断记录(bin log直至事务提交才写入日志中)
			
		-- 3.组成
			-- [1]重做日志的缓冲(redo log buffer)，保存在内存中，易丢失
				-- 服务器启动时系统申请一片名为redo log buffer的连续内存空间(redo日志缓冲区)，这片空间被划分成若干个
				-- redo log block。一个redo log block占512字节。
				
				-- 参数设置：
					SHOW VARIABLES LIKE '%innodb_log_buffer_size%';
					-- 默认16M，max4096M，min1M
			-- [2]重做日志文件(redo log file)，保存在磁盘中，持久的
				-- cd /var/lib/mysql查看路径，ib_logfile0和ib_logfile1
				
		-- 4.流程
			-- update事务 -> 内存data buffer -> redo log buffer -> redo log file -> 磁盘data
			-- [1]先将数据从磁盘读入到内存，修改数据内存拷贝
			-- [2]生成重做日志并写入redo log buffer，记录的是数据修改后的值
			-- [3]事务提交后，将redo log buffer的内容刷新到redo log file，对redo log file采用追加写的方式
			-- [4]定期将内存中修改的数据刷新到磁盘中
			
		-- 5.redo log刷盘策略
			--     redo log buffer刷盘到redo log file的过程并不是真正刷到磁盘中，只是刷入到文件系统缓存(pagecache)中(这是
			-- 现代操作系统为了提高文件写入效率做的一个优化)，真正的写入会交给系统自己来决定(比如page cache足够大了)。那么
			-- 对于InnoDB来说就存在一个问题，如果交给系统来同步，同样如果系统宕机，那么数据也丢失了(虽然整个系统宕机的概率
			-- 还是比较小的)。
			--     针对这种情况，InnoDB给出innodb_flush_log_at_trx_commit参数，该参数控制commit提交事务时，如何将redo log buffer
			-- 中的日志刷新到redo log file中，它支持三种策略:
				-- 设置为0：表示每次事务提交时不进行刷盘操作。(系统默认master thread每隔1s进行一次重做日志的同步)
				-- 设置为1：表示每次事务提交时都将进行同步，刷盘操作(默认值)
				-- 设置为2：表示每次事务提交时都只把redo log buffer内容写入page cache，不进行同步。由os自己决定何时同步到磁盘文件。
		
		-- 6.不同刷盘策略
			USE atguigudb2;

			CREATE TABLE test_load(
			a INT,
			b CHAR(80)
			)ENGINE=INNODB;

			-- 创建存储过程，用于向test_load中添加数据
			DELIMITER//
			CREATE PROCEDURE p_load(COUNT INT UNSIGNED)
			BEGIN
			DECLARE s INT UNSIGNED DEFAULT 1;
			DECLARE c CHAR(80)DEFAULT REPEAT('a'，80);
			WHILE s<=COUNT DO
			INSERT INTO test_load SELECT NULL,c;
			COMMIT;
			SET s=s+1;
			END WHILE;
			END //
			DELIMITER;

			-- 测试1：
			-- 设置并查看：innodb_flush_log_at_trx_commit
			SHOW VARIABLES LIKE 'innodb_flush_log_at_trx_commit';

			SET GLOBAL innodb_flush_log_at_trx_commit = 1;

			-- 调用存储过程
			CALL p_load(3000); -- 4.858s
				-- 设置为1时耗时最多但最安全，防止宕机等影响

			-- 测试2：
			TRUNCATE TABLE test_load;
			SELECT COUNT(*) FROM test_load;

			SET GLOBAL innodb_flush_log_at_trx_commit = 0;
			SHOW VARIABLES LIKE 'innodb_flush_log_at_trx_commit';

			-- 调用存储过程
			CALL p_load(3000); -- 2.489s

			-- 测试3：
			TRUNCATE TABLE test_load;
			SELECT COUNT(*) FROM test_load;

			SET GLOBAL innodb_flush_log_at_trx_commit = 2;
			SHOW VARIABLES LIKE 'innodb_flush_log_at_trx_commit';

			-- 调用存储过程
			CALL p_load(3000); -- 2.973s
			
			
			
	-- 三.undo日志
		-- redo日志保证事务持久性，undo log保证事务原子性。事务数据更新的前置操作要先写入一个undo log
		
		-- 1.理解undo日志
			-- 保证原子性，事务操作全做或不做
			-- 保证系统出现错误时事务“不做(回滚)”的手段
				-- [1]插入：记录插入数据的主键，回滚时把主键对应记录全部删除即可
				-- [2]删除：记录删除记录的全部内容，回滚后再把这些记录的数据插入即可
				-- [3]更新：记录对应更新前数据的内容，回滚后把对应记录更新为旧值即可
			-- 这些为了增删改回滚对应的日志称为撤销日志或回滚日志undo log。日志不包括查询select。
			-- undo log也会产生redo log
			
		-- 2.作用
			-- [1]回滚数据
				-- 仅保证数据库逻辑地恢复至之前状态，但实际恢复的数据的存储空间和结构会发生改变，无法改变。
			-- [2]MVCC
				
		-- 3.存储结构(了解)
			-- [1]回滚段与undo页
				--     InnoDB对undo log的管理采用段的方式，也就是回滚段(rollback segment)。每个回滚段记录了1024个
				-- undo log segment，而在每个undo log segment段中进行undo页的申请。
				--     在InnoDB1.1版本之前(不包括1.1版本) ，只有一个rollback segment，因此支持同时在线的事务限制为
				-- 1024。虽然对绝大多数的应用来说都已经够用。从1.1版本开始InnoDB支持最大128个rollback segment，故
				-- 其支持同时在线的事务限制提高到了128*1024
				SHOW VARIABLES LIKE 'innodb_undo_logs';
				
				-- 从InnoDB1.2版本开始，可通过参数对rollback segment做进一步的设置。
					-- innodb_undo_directory：设置rollback segment文件所在的路径。这意味着rollback segment可以
					--			  存放在共享表空间以外的位置，即可以设置为独立表空间。该参数的默认
					--			  值为“.”，表示当前InnoDB存储引擎的目录。
					-- innodb_undo_logs：设置rollback segment的个数，默认值为128。在InnoDB1.2版本中，该参数用来
					--		     替换之前版本的参数innodb_rollback_segments。
					-- innodb_undo_tablespaces：设置构成rollback segment文件的数量，这样rollback segment可以较为
					--  			    平均地分布在多个文件中。设置该参数后，会在路径innodb_undo_directory
					--			    看到undo为前缀的文件，该文件就代表rollback segment文件。
					-- undo log 相关参数一般很少改动。
					
				-- undo页的重用
					-- undo页被设计可以重用，当事务提交时，并不会立刻删除undo页。因为重用，所以这个undo页可能混杂着
					-- 其他事务的undo log，undo log在commit后，会被放到一个链表 中，然后判断undo页的使用空间是否小于
					-- 3/4，如果小于3/4的话，则表示当前的undo页可以被重用，那么它就不会被回收，其他事务的undo log可以记
					-- 录在当前undo页的后面。由于undo log是离散的，所以清理对应的磁盘空间时，效率不高。
				
			-- [2]回滚段与事务
				-- {1}每个事务只会使用一个回滚段，一个回滚段在同一时刻可能会服务于多个事务。
				-- {2}当一个事务开始的时候，会制定一个回滚段，在事务进行的过程中，当数据被修改时，原始的数据会被复制到回滚段。
				-- {3}在回滚段中，事务会不断填充盘区，直到事务结束或所有的空间被用完。如果当前的盘区不够用，事务会在段中请求扩展
				--    下一个盘区，如果所有已分配的盘区都被用完，事务会覆盖最初的盘区或者在回滚段允许的情况下扩展新的盘区来使用。
				-- {4}回滚段存在于undo表空间中，在数据库中可以存在多个undo表空间，但同一时刻只能使用一个undo表空间。
				SHOW VARIABLES LIKE 'innodb_undo_tablespaces';
				-- {5}当事务提交时， InnoDB存储引擎会做以下两件事情：将undo log放入列表中，以供之后的purge操作判断undo log
				--    所在的页是否可以重用，若可以分配给下个事务使用。
				
			-- [3]回滚段中的数据分类
				-- {1}未提交的回滚数据(uncommitted undo information)：该数据所关联的事务并未提交，用于实现读一致性，
				--    所以该数据不能被其他事务的数据覆盖。
				-- {2}已经提交但未过期的回滚数据(committed undo information)：该数据关联的事务已经提交，但是仍受到
				--    undo retention参数的保持时间的影响。
				-- {3}事务已经提交并过期的数据(expired undo information)：事务已经提交，而且数据保存时间已经超过undo retention
				--    参数指定的时间，属于已经过期的数据。当回滚段满了之后，会优先覆盖"事务已经提交并过期的数据"。
				-- 补：事务提交后并不能马上删除undo log及undo log所在的页。这是因为可能还有其他事务需要通过undo log
				--     来得到行记录之前的版本。故事务提交时将undo log放入一个链表中，是否可以最终删除undo log及undo log
				--     所在页由purge线程来判断。

		-- 4. undo的类型
			-- 在InnoDB存储引擎中，undo log分为：
			-- insert undo log：insert undo log是指在insert操作中产生的undo log，因为insert操作的记录，只对事务本身可见，对其
			--                  他事务不可见(这是事务隔离性的要求) ，故该undo log可以在事务提交后直接删除，不需要进行purge操作
			-- update undo log：update undo log记录的是对deletefDupdate操作产生的undo log.该undo log可能需要提供Mvcc机制，因
			--                  此不能在事务提交时就进行删除。提交时放入undo log链表，等待purge线程进行最后的删除。

		-- 5.undo log的生命周期
			-- [1]简要生成过程
				-- 以下是undo+redo事务的简化过程
				-- 假设有2个数值，分别为A=1和B=2，然后将A修改为3， B修改为4 
					-- 1.start transaction;
					-- 2.记录A=1到undo log;
					-- 3.update A = 3;
					-- 4.记录 A=3 到redo log;
					-- 5.记录 B=2 到undo log;
					-- 6.update B = 4;
					-- 7.记录B= 4到redo log;
					-- 8.将redo log刷新到磁盘
					-- 9.commit
				-- 在1-8步骤的任意一步系统宕机，事务未提交，该事务就不会对磁盘上的数据做任何影响。
				-- 如果在8-9之间宕机，恢复之后可以选择回滚，也可以选择继续完成事务提交，因为此时redo log已经持久化。
				-- 若在9之后系统宕机，内存映射中变更的数据还来不及刷回磁盘，那么系统恢复之后，可以根据redo log把数据刷回磁盘。
			
			-- [2]详细生成过程
				-- 对于InnoDB引擎来说，每个行记录除了记录本身的数据之外，还有几个隐藏的列：
					-- DB_ROW_ID：如果没有为表显式的定义主键，并且表中也没有定义唯一索引，那么InnoDB会自动为表添加一个
					--	      row_id的隐藏列作为主键。
					-- DB_TRX_ID：每个事务都会分配一个事务ID，当对某条记录发生变更时，就会将这个事务的事务ID写入trxid中。
					-- DB-ROLL_PTR：回滚指针，本质上就是指向undo log的指针。
			
			-- [3]undo log的删除
				-- {1}针对于insert undo log因为insert操作的记录，只对事务本身可见，对其他事务不可见。故该undo log可以
				--    在事务提交后直接删除，不需要进行purge操作。
				-- {2}针对于update undo log该undo log可能需要提供MVCC机制，因此不能在事务提交时就进行删除。提交时放入
				--    undo log链表，等待purge线程进行最后的删除。
				-- 补：purge线程两个主要作用是：清理undo页和清除page里面带有Delete_Bit标识的数据行。在InnoDB中，事务
				--     中的Delete操作实际上并不是真正的删除掉数据行，而是一种Delete Mark操作，在记录上标识Delete_Bit，
				--     而不删除记录。是一种"假删除"，只是做了个标记，真正的删除工作需要后台purge线程去完成。
