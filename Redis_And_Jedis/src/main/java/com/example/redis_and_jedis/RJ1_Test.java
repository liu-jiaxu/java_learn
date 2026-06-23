package com.example.redis_and_jedis;

/**
 * ClassName: RJ1_Test
 * Package: com.example.redis_and_jedis
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/23 - 20:41
 * @Version: v1.0
 */

public class RJ1_Test {

    /*
      一.Redis
          1.概念：redis是一款高性能的NOSQL系列的非关系型数据库，不支持SQL
              (1)redis使用c语言开发的高性能键值对数据库，50个并发100000个请求，读速度为110000次/s，写速度为81000次/s
              (2)redis支持数据类型：
                  字符串类型string、哈希类型hash(类似map)、列表类型list、集合类型set、有序集合类型sortedset
              (3)适用场景：
                  缓存、聊天好友在线列表、任务队列、排行榜、网站访问统计、数据过期处理、分布式集群架构中的session分离
              (4)关系型数据库：MySQL、Oracle
                  数据之间有关联关系，数据存储在硬盘文件上，以表形式存储
              (5)非关系型数据库：redis、hbase
                  数据之间无关联，存储在内存中(查询时有数据直接返回)，以键值对存储
          2.下载安装
              (1)官网：https://redis.io
              (2)中文网：https://www.redis.net.cn/
              (3)解压直接使用
                  [1]redis.windows.conf：配置文件
                  [2]redis-cli.exe：redis客户端
                  [3]redis-server.exe：redis服务器端
          3.命令操作
              (1)redis数据结构
                  redis存储的是：key-value格式的数据，其中key是字符串，value有五种可选数据类型
                      [1]字符串类型string：
                          {1}存储 set key value
                          {2}获取 get key
                          {3}删除 del key
                      [2]哈希类型hash(类似map)：map格式
                          {1}存储 hset key field(表示set中的具体键名) value 将哈希表 key 中的字段 field 的值设为 value
                          {2}获取 单个hget key field / 全部hgetall field
                          {3}删除 hdel key field
                      [3]列表类型list：linkedlist格式，支持重复元素
                          {1}存储 lpush key value：左插
                          {2}存储 rpush key value：右插
                          {3}获取 lrange key strat end：范围获取
                              lrange key 0 -1：获取所有元素
                          {4}删除 lpop key：删除最左边元素，并返回该元素
                          {4}删除 rpop key：删除最右边元素，并返回该元素
                      [4]集合类型set：不允许重复元素
                          {1}存储 sadd key value
                          {2}获取 smembers key：删除所有元素
                          {3}删除 srem key value：删除某个元素
                      [5]有序集合类型sortedset：不允许重复元素，且元素有序
                          {1}存储 zadd key score(指定一个数字，用于从小到大排序value) value
                          {2}获取 zrange key start end
                          {3}获取 zrange key start end withscores：获取范围value及score
                          {4}删除 zren key value
              (2)通用命令
                  {1}key * 查询所有键
                  {2}type key 获取key对应value的数据类型
                  {3}del key 删除指定的key value
          4.持久化操作
              (1)概念：redis是一个内存数据库，当redis服务器重启，获取电脑重启，数据会丢失，我们可以将redis内存中的数据持久化保存
                      到硬盘的文件中。
              (2)redis持久化机制：
                  [1]RDB:默认方式,不需要进行配置,默认就使用这种机制在一定的间隔时间中，检测key的变化情况，然后持久化数据
                      {1}编辑redis.windwos.conf文件
                          after 900 sec (15 min) if at least 1 key changed
                          save 900 1
                          after 300 sec (5 min) if at least 10 keys changed
                          save 300 10
                          after 60 sec if at least 10000 keys changed
                          save 60 10000
                      {2}重新启动redis服务器,并指定配置文件名称
                          例：D:\Javaweb2018\day23_redis\资料\redis\Windows-64\redis-2.8.9>redis-server.exe redis.windows.conf
                  [2]AOF:日志记录的方式,可以记录每一条命令的操作。可以每一次命令操作后,持久化数据
                      {1}编辑redis.windwos.conf文件
                          appendonly no(关闭aof) --> appendonly yes(开启aof)
                          appendsync always   ：每一次操作都持久化
                          appendsync everysec ：每隔一秒进行一次持久化
                          appendsync no       ：不进行持久化
          5.使用Java客户端操作redis(Jedis)(见RJ2_Test)
     */

}
