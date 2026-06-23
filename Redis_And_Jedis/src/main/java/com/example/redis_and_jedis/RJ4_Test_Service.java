package com.example.redis_and_jedis;

import java.util.List;

/**
 * ClassName: RJ4_Test_Service
 * Package: com.example.redis_and_jedis
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/27 - 11:06
 * @Version: v1.0
 */
public interface RJ4_Test_Service {

    public List<RJ4_Test_Province> findAll();

    public String findAllJson();

}
