package com.example.redis_and_jedis;

/**
 * ClassName: RJ3_JedisPoolUtils
 * Package: com.example.redis_and_jedis
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/27 - 10:30
 * @Version: v1.0
 */

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * JedisPool工具类
 *     加载配置文件，配置连接池的参数
 *     提供获取连接的方法
 */

public class RJ3_JedisPoolUtils {

    private static JedisPool jedisPool;

    static {
        // 读取配置文件
        InputStream resourceAsStream = RJ3_JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        // 创建properties对象
        Properties properties = new Properties();
        // 关联文件
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 获取数据，设置到JedisPoolConfig中
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        config.setMaxTotal(Integer.parseInt(properties.getProperty("maxIdle")));
        // 初始化JedisPool
        jedisPool = new JedisPool(config,properties.getProperty("host"),Integer.parseInt(properties.getProperty("port")));

    }

    // 获取连接方法
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    // 关闭连接方法
    public static void closeJedis(){
        jedisPool.close();
    }

}
