package com.example.servlet;

import jakarta.servlet.http.HttpServlet;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import sun.security.jgss.HttpCaller;

import java.lang.reflect.InvocationTargetException;

/**
 * ClassName: TS8_Test_BeanUtilsTest
 * Package: com.example.servlet
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/7 - 10:22
 * @Version: v1.0
 */

// BeanUtils方法测试类
public class TS8_Test_BeanUtilsTest extends HttpServlet {

    @Test
    public void test() {

        TS8_Test_User user = new TS8_Test_User();
        try {
            // set/get方法测试
            BeanUtils.setProperty(user, "username", "zhangsan");
            System.out.println(user);

            String name = BeanUtils.getProperty(user, "username");
            System.out.println(name);

            // 重点：populate(Object obj,Map map)方法
                // 将map集合的键值对信息，封装到对应的JavaBean对象中

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

}
