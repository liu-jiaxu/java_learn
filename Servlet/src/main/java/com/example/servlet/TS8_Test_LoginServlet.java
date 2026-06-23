package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * ClassName: TS8_Test_LoginServlet
 * Package: com.example.servlet
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/6 - 16:27
 * @Version: v1.0
 */

// 登录类
@WebServlet("/ts8ls")
public class TS8_Test_LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // req参数是请求浏览器的信息，resp参数是向浏览器发送的信息

        // 1.设置编码
        req.setCharacterEncoding("UTF-8");
        /*// 2.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 3.封装user对象
        TS8_Test_User loginUser = new TS8_Test_User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);*/

        // 使用BeanUtils工具类，简化数据封装(简化2、3步)
        // 2.获取请求参数
        Map<String,String[]> map = req.getParameterMap(); // 获取所有参数的map集合
        // 3.创建User对象
        TS8_Test_User loginUser = new TS8_Test_User();
        try {
            BeanUtils.populate(loginUser,map); // 注：使用org.apache.commons.beanutils.BeanUtils这个包！
                // 将map集合的键值对信息，封装到对应的JavaBean对象中
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        // 4.调用TS8_Test_UserDao的login方法
        TS8_Test_UserDao dao = new TS8_Test_UserDao();
        TS8_Test_User user = dao.login(loginUser);
        // 5.判断
        if(user == null) {
            // 登录失败
            req.getRequestDispatcher("/fail").forward(req, resp);
        } else {
            // 登录成功
            req.setAttribute("user",user);
            req.getRequestDispatcher("/success").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
