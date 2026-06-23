-- 函数

-- 三.日期和时间函数

-- 1.获取日期、时间
	-- CURDATE() ，CURRENT_DATE()返回当前日期，只包含年、月、日
	-- CURTIME() ， CURRENT_TIME()返回当前时间，只包含时、分、秒
	-- NOW() / SYSDATE() / CURRENT_TIMESTAMP() / LOCALTIME() / LOCALTIMESTAMP()返回当前系统日期和时间
	-- UTC_DATE()返回UTC（世界标准时间）日期
	-- UTC_TIME()返回UTC（世界标准时间(比中国早8小时)）时间 
	SELECT CURDATE(),CURTIME(),NOW(),SYSDATE()+0,UTC_DATE(),UTC_DATE()+0,UTC_TIME(),UTC_TIME()+0
	FROM DUAL;
	
-- 2.日期与时间戳的转换
	-- UNIX_TIMESTAMP()以UNIX时间戳的形式返回当前时间。
		SELECT UNIX_TIMESTAMP(); -- 时间戳：1970.1.1到现在的总时间(秒)
	-- UNIX_TIMESTAMP(date) 将时间date以UNIX时间戳的形式返回。
		SELECT UNIX_TIMESTAMP('2000-11-11 11:11:11');
		SELECT UNIX_TIMESTAMP(NOW());
	-- FROM_UNIXTIME(timestamp) 将UNIX时间戳的时间转换为普通格式的时间
		SELECT FROM_UNIXTIME(1667826332);
	
-- 3.获取月份、星期、星期数、天数等函数
	-- YEAR(date) / MONTH(date) / DAY(date) 返回具体的日期值
	-- HOUR(time) / MINUTE(time) / SECOND(time)返回具体的时间值
	-- MONTHNAME(date) 返回月份：January，...
	-- DAYNAME(date) 返回星期几：MONDAY，TUESDAY.....SUNDAY
	-- WEEKDAY(date) 返回周几，注意，周1是0，周2是1，。。。周日是6
	-- QUARTER(date) 返回日期对应的季度，范围为1～4
	-- WEEK(date) / WEEKOFYEAR(date) 返回一年中的第几周
	-- DAYOFYEAR(date) 返回日期是一年中的第几天
	-- DAYOFMONTH(date) 返回日期位于所在月份的第几天
	-- DAYOFWEEK(date)返回周几，注意：周日是1，周一是2，。。。周六是7
	
	SELECT YEAR(CURDATE()),MONTH(CURDATE()),DAY(CURDATE()),
	HOUR(CURTIME()),MINUTE(NOW()),SECOND(SYSDATE())
	FROM DUAL;
	-- 2022  11  7  21  14  12 记录一下学习时间2022-11-07 21:14:12... 
	
	SELECT MONTHNAME('2021-10-26'),DAYNAME('2021-10-26'),WEEKDAY('2021-10-26'),
	QUARTER(CURDATE()),WEEK(CURDATE()),DAYOFYEAR(NOW()),
	DAYOFMONTH(NOW()),DAYOFWEEK(NOW())
	FROM DUAL;

-- 4.日期的操作函数
	-- EXTRACT(type FROM date) 返回指定日期中特定的部分，type指定返回的值
	-- type对应值见jpg附录
	SELECT EXTRACT(MINUTE FROM NOW()),EXTRACT( WEEK FROM NOW()),
	EXTRACT( QUARTER FROM '2021-5-30'),EXTRACT( MINUTE_SECOND FROM NOW())
	FROM DUAL;

-- 5.时间和秒钟转换的函数
	-- TIME_TO_SEC(time) 将time转化为秒并返回结果值。转化的公式为： 小时*3600+分钟*60+秒
	-- SEC_TO_TIME(seconds) 将seconds(数值型)描述转化为包含小时、分钟和秒的时间
	SELECT TIME_TO_SEC('22:22:22'),SEC_TO_TIME(TIME_TO_SEC('22:22:22'));

-- 6.计算日期和时间的函数
	-- DATE_ADD(time1,INTERVAL time2) 返回时间time1+time2
	-- DATE_SUB(time1,INTERVAL time2) 返回时间time1-time2
	SELECT NOW(),DATE_ADD(NOW(),INTERVAL 1 YEAR),
	DATE_ADD(NOW(),INTERVAL -1 YEAR),
	DATE_SUB(NOW(),INTERVAL 2 YEAR)
	FROM DUAL;
	-- 2022-12-16 09:21:15	2023-12-16 09:21:15  2021-12-16 09:21:15  2024-12-16 09:21:15

	SELECT DATE_ADD(NOW(), INTERVAL 1 DAY) AS col1,DATE_ADD('2021-10-21 23:32:12',INTERVAL 1 SECOND) AS col2,
	ADDDATE('2021-10-21 23:32:12',INTERVAL 1 SECOND) AS col3,
	DATE_ADD('2021-10-21 23:32:12',INTERVAL '1_1' MINUTE_SECOND) AS col4, -- '1_1' MINUTE_SECOND 表示1分1秒
	DATE_ADD(NOW(), INTERVAL -1 YEAR) AS col5, -- 可以是负数
	DATE_ADD(NOW(), INTERVAL '1_1' YEAR_MONTH) AS col6 -- 需要单引号
	FROM DUAL;

	-- ADDTIME(time1,time2)返回time1加上time2的时间。当time2为一个数字时,代表的是秒,可以为负数
	-- SUBTIME(time1,time2)返回time1减去time2后的时间。当time2为一个数字时,代表的是秒,可以为负数
	-- DATEDIFF(date1,date2)返回date1- date2的日期间隔天数
	-- TIMEDIFF(time1, time2)返回time1- time2的时间间隔
	-- FROM_DAYS(N)返回从0000年1月1日起,N天以后的日期
	-- TO_DAYS(date)返回日期date距离0000年1月1日的天数
	-- LAST_DAY(date)返回date所在月份的最后一天的日期
	-- MAKEDATE(year,n)针对给定年份与所在年份中的天数返回一个日期
	-- MAKETIME(hour,minute,second) |将给定的小时、分钟和秒组合成时间并返回
	-- PERIOD_ADD(time,n)返回time加上n后的时间
	SELECT ADDTIME(NOW(),20),SUBTIME(NOW(),30),SUBTIME(NOW(),'1:1:3'),DATEDIFF(NOW(),'2021-10-01'),
	TIMEDIFF(NOW(),'2021-10-25 22:10:10'),FROM_DAYS(366),TO_DAYS('0000-12-25'),
	LAST_DAY(NOW()),MAKEDATE(YEAR(NOW()),32),MAKETIME(10,21,23),PERIOD_ADD(20200101010101,10)
	FROM DUAL;

-- 7.日期的格式化与解析
	--  格式化：日期 ---> 字符串
	--  解析：  字符串 ----> 日期

	-- 此时我们谈的是日期的显式格式化和解析
	-- 之前，我们接触过隐式的格式化或解析
	SELECT *
	FROM employees
	WHERE hire_date = '1993-01-13';

	-- 补充：
	-- %Y4位数字表示年份
	-- %y表示两位数字表示年份
	-- %M月名表示月份(January,...)
	-- %m两位数字表示月份(01,02,03。..)
	-- %b缩写的月名(Jan., Feb., ...)
	-- %c数字表示月份(1,2,3,...)
	-- %D英文后缀表示月中的天数,(1st,2nd,3rd,...)
	-- %d两位数字表示月中的天数(01,02...)
	-- %oe数字形式表示月中的天数(1,2,3,4,5....)
	
	-- %H两位数字表示小数,24小时制(01,02..)
	-- %h两位数字表示小时,12小时制(01,02..)
	-- %k数字形式的小时,24小时制(1,2,3)
	-- %l数字形式表示小时,12小时制(1,2,3,4....)
	-- %i两位数字表示分钟(00,01,02)
	-- %S和%s两位数字表示秒(00,01,02...)
	
	-- %W一周中的星期名称(Sunday...)
	-- %a一周中的星期缩写(Sun.,Mon.,Tues., ..)
	-- %w以数字表示周中的天数(0=Sunday,1=Monday....)
	-- %j以3位数字表示年中的天数(001,002...)
	-- %U以数字表示年中的第几周,(1,2,3。。)其中Sunday为周中第一天
	-- %ou以数字表示年中的第几周,(1,2,3。。)其中Monday为周中第一天
	-- %T%r12小时制
	-- %p24小时制AM或PM
	-- %%表示%
	
	-- 啥也不会啊......
	-- 1. January——Jan. 一月 [ˈdʒænjuəri]
	-- 2. February——Feb. 二月 [ˈfebruəri]
	-- 3. March——Mar. 三月 [mɑːtʃ]
	-- 4. April——Apr. 四月 [ˈeɪprəl]
	-- 5. May—无缩略 五月 [meɪ]
	-- 6. June——Jun. 六月 [dʒuːn]
	-- 7. July——Jul. 七月 [dʒuˈlaɪ]
	-- 8. August——Aug. 八月 [ˈɔːɡəst]
	-- 9. September——Sept. 九月 [sepˈtembə]
	-- 10. October——Oct. 十月 [ɒkˈtəʊbə]
	-- 11. November——Nov. 十一月 [nəʊˈvembə]
	-- 12. December——Dec. 十二月 [dɪˈsembə]
	-- 星期一：Monday（Mon.）
	-- 星期二：Tuesday（Tue.）
	-- 星期三：Wednesday（Wed.）
	-- 星期四：Thursday（Thu.）
	-- 星期五：Friday（Fri.）
	-- 星期六：Saturday（Sat.）
	-- 星期日：Sunday（Sun.）

	-- DATE_FORMAT(date,fmt) 按照字符串fmt格式化日期date值
	-- TIME_FORMAT(time,fmt) 按照字符串fmt格式化时间time值
	-- GET_FORMAT(date_type,format_type) 返回日期字符串的显示格式
	-- STR_TO_DATE(str, fmt)按照字符串fmt对str进行解析,解析为一个日期
	-- 格式化：
	SELECT DATE_FORMAT(CURDATE(),'%Y-%M-%D'),
	DATE_FORMAT(NOW(),'%y-%m-%d'),TIME_FORMAT(CURTIME(),'%h:%i:%S'),
	DATE_FORMAT(NOW(),'%Y-%M-%D %h:%i:%S %W %w %T %r')
	FROM DUAL;

	-- 解析：格式化的逆过程
	SELECT STR_TO_DATE('2021-October-25th 11:37:30 Monday 1','%Y-%M-%D %h:%i:%S %W %w')
	FROM DUAL;
	SELECT GET_FORMAT(DATE,'USA')
	FROM DUAL;
	SELECT CONCAT(DATE_FORMAT(CURDATE(),GET_FORMAT(DATE,'ISO')),' ',DATE_FORMAT(CURTIME(),GET_FORMAT(TIME,'ISO'))) AS 'time'
	FROM DUAL; 
	SELECT DATE_FORMAT(NOW(),GET_FORMAT(DATETIME,'USA'))
	FROM DUAL; -- GET_FORMAT和DATE_FORMAT函数配合使用可以直接得到许多日期格式！		
		