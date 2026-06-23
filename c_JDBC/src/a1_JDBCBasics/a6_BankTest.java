package a1_JDBCBasics;

import org.junit.Test;

/**
 * Description: 测试类
 */
public class a6_BankTest {

    @Test
    public void testBank() throws Exception {
        a6_BankService bankService = new a6_BankService();
        // 李华转账给刘思瑶500元
        bankService.transfer("刘思瑶", "李华", -500);
            // 此处的money可以也设置成非负，此处未设置
    }

}
