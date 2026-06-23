package com.example.redis_and_jedis;

/**
 * ClassName: RJ2_Test
 * Package: com.example.redis_and_jedis
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/24 - 11:04
 * @Version: v1.0
 */

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * 一.Jedis
 *     1.概念：Jedis是一款java操作redis数据库的工具
 *     2.步骤
 *         (1)下载jedis的jar包
 *         (2)使用
 */

public class RJ2_Test {

    // 使用方法，string测试
    @Test
    public void test() {

        // 1.获取连接
        Jedis jedis = new Jedis("localhost",6379); // 空参默认为localhost,6379
        // 2.操作
        jedis.set("username","lisi");
        System.out.println(jedis.get("username"));
        // setex()方法存储可以指定过期时间的key value
        jedis.setex("activecode",10,"hehe"); // 存入后10s删除
        // 3.关闭连接
        jedis.close();

    }

    // hash测试
    @Test
    public void test2() {

        // 1.获取连接
        Jedis jedis = new Jedis("localhost",6379);
        // 2.操作
        jedis.hset("name","amy","16");
        jedis.hset("name","lisi","19");
        jedis.hset("name","john","18");
        // 获取单个hash
        String hget = jedis.hget("name","amy");
        System.out.println(hget);
        // 获取所有map数据
        Map<String,String> map = jedis.hgetAll("name");
        Set<Map.Entry<String, String>> set = map.entrySet();
        Iterator<Map.Entry<String,String>> it = set.iterator();
        while(it.hasNext()) {
            System.out.println(it.next()+" ");
        }
        // 3.关闭连接
        jedis.close();

    }

    // list测试
    @Test
    public void test3() {

        // 1.获取连接
        Jedis jedis = new Jedis("localhost",6379);
        // 2.操作
        jedis.lpush("list","a","b","c");
        jedis.rpush("list","a","b","c");
        // 获取
        List<String> list = jedis.lrange("list", 0, -1);
        System.out.println(list); // 集合重写toStrong方法，可直接打印
        // 左删除并返回元素
        String list1 = jedis.lpop("list");
        System.out.println(list1);
        // 删除列表
        jedis.del("list");
        // 3.关闭连接
        jedis.close();

    }

    // set测试
    @Test
    public void test4() {

        // 1.获取连接
        Jedis jedis = new Jedis("localhost",6379);
        // 2.操作
        jedis.sadd("set","java","c++","php");
        // 获取
        Set<String> set = jedis.smembers("set");
        System.out.println(set);
        // 3.关闭连接
        jedis.close();

    }

    // sortedset测试
    @Test
    public void test5() {

        // 1.获取连接
        Jedis jedis = new Jedis("localhost",6379);
        // 2.操作
        jedis.zadd("sortset",3,"亚瑟");
        jedis.zadd("sortset",5,"后羿");
        jedis.zadd("sortset",4,"孙悟空");
        // 获取
        List<String> sortset = jedis.zrange("sortset", 0, -1);
        System.out.println(sortset);
        // 3.关闭连接
        jedis.close();

    }

}
