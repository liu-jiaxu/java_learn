CREATE DATABASE cj_test;
USE cj_test;

CREATE TABLE USER (
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(20) NOT NULL,
	gender VARCHAR(20) NOT NULL,
	age INT NOT NULL,
	address VARCHAR(200),
	qq VARCHAR(20),
	email VARCHAR(200)
)

-- 插入字段
ALTER TABLE USER
ADD username VARCHAR(32);
ALTER TABLE USER
ADD PASSWORD VARCHAR(32);

-- 插入记录
INSERT INTO USER (`name`,gender,age,address,qq,email,username,`password`)
VALUES('李四','男',23,'北京','3211459982','3211459982@qq.com','lisi','123456');
INSERT INTO USER (`name`,gender,age,address,qq,email,username,`password`)
VALUES('王五','男',25,'北京','137798221','137798221@qq.com','wangwu','123456');

SELECT *
FROM USER;

DELETE FROM USER
WHERE id = 6;

-- 重置id自增键
SET @auto_id = 0;
UPDATE USER SET id = (@auto_id := @auto_id + 1);
ALTER TABLE USER AUTO_INCREMENT = 1;
