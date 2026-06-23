SELECT c.`type`, c.`name`, d.`price`
FROM category c INNER JOIN dish d ON c.`id` = d.`category_id`
WHERE d.`price` > 10000;

SELECT c.`type`, c.`name`, d.`price`, d.`create_time`
FROM category c INNER JOIN dish d 
ON c.`id` = d.`category_id`
WHERE c.`type` = 1 AND d.`price` BETWEEN 10000 AND 40000;

SELECT c.`type`, c.`name`, d.`update_time`
FROM category c INNER JOIN dish d 
ON c.`id` = d.`category_id`
WHERE YEAR(d.`update_time`) > 2022 
ORDER BY d.`update_time` DESC;


SELECT * 
FROM t_user u1 
WHERE 
NOT EXISTS(
	SELECT dept_id FROM t_user u2 WHERE nick_name='钱六'
	-- 外表和子表的关联关系
	AND u1.dept_id = u2.dept_id
);

SELECT COUNT(*)
FROM t_user u1
WHERE 
EXISTS (
	SELECT u2.`dept_id`
	FROM t_user u2
	WHERE u1.`dept_id` = u2.`dept_id` AND u2.`nick_name` = '钱六'
);

SELECT COUNT(*)
FROM t_user
WHERE dept_id IN (
	SELECT dept_id
	FROM t_user
	WHERE `nick_name` = '钱六'
);


USE `takeoutfood`;
SELECT d.name, d.price, d.update_time
FROM dish d
UNION ALL
SELECT s.name, s.price, s.update_time
FROM setmeal s

CREATE VIEW v
AS
SELECT c.id, c.`name` AS c_name, s.`name` AS s_name, s.price AS s_price, sd.name AS sd_name, sd.price AS sd_price  
FROM category c, setmeal s, setmeal_dish sd
WHERE c.id = s.category_id AND sd.setmeal_id = s.id

SELECT *
FROM v
WHERE s_name = '儿童套餐A计划';

ALTER VIEW v
AS
SELECT c.id, c.`name` AS c_name, s.id AS sid, s.`name` AS sname, s.price AS sprice, sd.name AS sdname, sd.price AS sdprice  
FROM category c, setmeal s, setmeal_dish sd
WHERE c.id = s.category_id AND sd.setmeal_id = s.id

SELECT *
FROM v;

DROP VIEW v1;
