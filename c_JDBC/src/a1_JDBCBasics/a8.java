package a1_JDBCBasics;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * ClassName: a1_JDBCBasics.a8
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/2/6 - 9:07
 * @Version: v1.0
 */
public class a8 {

    // v1版本工具类封装测试
    @Test
    public void testv1() throws Exception {

        // 调用封装类即可
        Connection connection = a8_v1_jdbcutils.getConnection();

        String sql = "select * from t_bank;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String money = resultSet.getString("money");
            System.out.println(id+"--"+account+"--"+money);
        }

        a8_v1_jdbcutils.releaseConnection(connection);

    }

    @Test
    public void testv2() throws Exception {

        // 封装类连接测试
        a8_BankService bankService = new a8_BankService();
        // 李华转账给刘思瑶500元
        bankService.transfer("刘思瑶", "李华", -500);

    }

}
