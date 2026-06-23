package com.example.cookiesession_and_jsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * ClassName: CJ6_Test_UserDao
 * Package: com.example.servlet_test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/6 - 11:52
 * @Version: v1.0
 */

// 操作数据库中user表的类
public class CJ6_Test_UserDao {

    // 注意此处使用数据库ts8_test_msg
    public static boolean login(CJ6_Test_User cj6TestUser) throws Exception {

        Connection connection = CJ6_Test_JDBC.getConnection();

        String  sql = "select * from user where username =? and password =?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,cj6TestUser.getUsername());
        preparedStatement.setObject(2,cj6TestUser.getPassword());
            // 将用户页面输入的姓名和密码作为参数传递给sql语句占位符
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next(); // 根据next原理可知，光标起始位置在第一行数据之前，若查询到数据，则光标下移，next()方法返回true

    }

}
