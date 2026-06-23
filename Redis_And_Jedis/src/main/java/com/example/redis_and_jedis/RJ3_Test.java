package com.example.redis_and_jedis;

/**
 * ClassName: RJ3_Test
 * Package: com.example.redis_and_jedis
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/27 - 9:30
 * @Version: v1.0
 */

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 一.Jedis连接池：JedisPool
 *     1.使用
 *         (1)创建JedisPool连接池对象
 *         (2)调用方法getResource()方法获取Jedis连接
 */
public class RJ3_Test {

    // 基本使用方法
    @Test
    public void test() {

        // 0.创建配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50); // 最大连接数
        config.setMaxIdle(10); // 最大空闲连接
        // 1.创建Jedis连接池对象
        JedisPool pool = new JedisPool(config,"localhost",6379);
        // 2.获取连接
        Jedis jedis = pool.getResource();
        // 3.使用
        jedis.set("jedis","123");
        System.out.println(jedis.get("jedis"));
        // 4.归还连接到连接池
        jedis.close();

    }

    // 使用连接池工具类RJ3_JedisPoolUtils
    @Test
    public void test2() {

        // 1.通过连接池工具类获取
        Jedis jedis = RJ3_JedisPoolUtils.getJedis();
        // 2.使用
        jedis.set("jedis2","456");
        System.out.println(jedis.get("jedis2"));
        // 3.关闭
        RJ3_JedisPoolUtils.closeJedis();

    }

}
