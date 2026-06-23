package com.example.redis_and_jedis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * ClassName: RJ4_Test_ServiceImpl
 * Package: com.example.redis_and_jedis
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/27 - 11:13
 * @Version: v1.0
 */
public class RJ4_Test_ServiceImpl implements RJ4_Test_Service {

        // 声明dao
        RJ4_Test_ProvinceDao dao = new RJ4_Test_ProvinceDaoImpl();

        @Override
        public List<RJ4_Test_Province> findAll() {
            return dao.findAll();
        }

        // 使用redis缓存
        @Override
        public String findAllJson() {
            // 1.获取redis客户端连接
            Jedis jedis = RJ4_Test_JedisPoolUtils.getJedis();
            String province_json = jedis.get("province");
            // 2.判断province_json数据是否为null
            if(province_json == null || province_json.length() == 0){
                // redis无数据时
                System.out.println("redis中无数据，查询数据库");
                // 从数据中查询
                List<RJ4_Test_Province> ps = dao.findAll();
                // 将list序列优化为json
                ObjectMapper mapper = new ObjectMapper();
                try {
                    province_json = mapper.writeValueAsString(ps);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                // 将json数据存入redis
                jedis.set("province", province_json);
                // 归还连接
                RJ3_JedisPoolUtils.closeJedis();
            } else {
                System.out.println("redis有数据，查询缓存");
            }
            return province_json;
        }

}
