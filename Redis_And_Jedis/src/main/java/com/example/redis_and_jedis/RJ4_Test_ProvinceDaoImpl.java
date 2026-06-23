package com.example.redis_and_jedis;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * ClassName: RJ4_Test_ProvinceDaoImpl
 * Package: com.example.redis_and_jedis
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/27 - 11:12
 * @Version: v1.0
 */
public class RJ4_Test_ProvinceDaoImpl implements RJ4_Test_ProvinceDao {

    // 1.声明成员变量
    private JdbcTemplate template = new JdbcTemplate(RJ4_Test_JDBCUtils.getDataSource());

    @Override
    public List<RJ4_Test_Province> findAll() {
        // 1.定义sql
        String sql = "select * from province";
        // 2.执行sql
        List<RJ4_Test_Province> list = template.query(sql,new BeanPropertyRowMapper<RJ4_Test_Province>(RJ4_Test_Province.class));
        return list;
    }

}
