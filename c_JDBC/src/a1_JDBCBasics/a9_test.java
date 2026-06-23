package a1_JDBCBasics;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * ClassName: a1_JDBCBasics.a9_test
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/2/6 - 10:22
 * @Version: v1.0
 */
public class a9_test extends a9{ // 记得继承类

    // 使用a8和a9封装类一同测试增删改方法
    @Test
    public void test_a9_update() throws Exception {

        // a9封装类传入sql语句和占位符
        String sql = "insert into t_bank(account,money) values (?,?);";
        String sql2 = "update t_bank set money =? where account =?;";
        String sql3 = "delete from t_bank where account =?;";

        // 调用封装类修改方法并设置返回参数
        int i = executeUpdate(sql, "陆琪", "1000");
        int i2 = executeUpdate(sql2, "1500", "李华");
        int i3 = executeUpdate(sql3, "刘思瑶");
        System.out.println("i = " + i + "\ni2 = " + i2 + "\ni3 = " + i3);

    }

    // 使用a8和a9封装类一同测试查询方法
    @Test
    public void test_a9_select() throws Exception {

        // a9封装类传入sql语句和占位符
        String sql = "select * from t_test where id = ? or id = ? or account = ?;";

        // 调用封装类查询方法并将结果存入集合list以便查询结果
        List<User> list = executeSelect(User.class,sql,"1","4","李华");
        // 遍历集合list
        for (User user : list) {
            System.out.print(user + "\n");
        }

    }

}

class User {

    public Integer id;
    public String account;
    public Integer age;

    public User() {
    }

    public User(Integer id, String account, Integer age) {
        this.id = id;
        this.account = account;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", age=" + age +
                '}';
    }

}

