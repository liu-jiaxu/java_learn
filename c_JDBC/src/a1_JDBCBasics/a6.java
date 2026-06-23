package a1_JDBCBasics;

/**
 * ClassName: a1_JDBCBasics.a6
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/2/5 - 16:52
 * @Version: v1.0
 */
public class a6 {

    // JDBC拓展

    /**
     * 3.jdbc中数据库事务实现
     *     事务：允许将数据回滚至最初状态
     *     事务特性：ACID原子性、一致性、隔离性、持久性
     *     案例：转账案例，涉及批量删除添加
     *     事务类型：自动提交，SQL语句在事务中，事务执行成功自动提交，失败则回滚
     *             手动提交：手动开启事务，手动提交或回滚
     */
    public static void main(String[] args) {

        /*
          转账类设计
              1.设计BankDao添加加钱减钱方法
              2.设计BankService调用加减钱方法
              3.设计测试类测试
         */

    }

}
