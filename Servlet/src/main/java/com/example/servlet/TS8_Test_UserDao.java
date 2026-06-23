package com.example.servlet;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * ClassName: TS8_Test_UserDao
 * Package: com.example.servlet_test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/6 - 11:52
 * @Version: v1.0
 */

// 操作数据库中user表的类
public class TS8_Test_UserDao {

    // 声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(TS8_Test_JDBCUtils.getDataSource());

    /**
     * 登录方法
     * @param loginUser 只有用户名和密码
     * @return user包含用户全部数据
     */
    public TS8_Test_User login(TS8_Test_User loginUser){

        // 调用queryForObject方法可能返回空值出现异常，需要用try-catch处理
        try {
            // 编写sql
            String  sql = "select * from user where username =? and password =?;";
            // 调用queryForObject方法
            TS8_Test_User ts8_test_user = template.queryForObject
                    (sql,new BeanPropertyRowMapper<TS8_Test_User>(TS8_Test_User.class),
                            loginUser.getUsername(),loginUser.getPassword());
                // 查询出一条记录并封装到一个对象中。可以返回的是String、Integer、Double或者自定义bean。但查询的记录为0条或者大于1条，抛出异常。
            return ts8_test_user;
        } catch(EmptyResultDataAccessException e) {
            return null;
        }

    }

}
