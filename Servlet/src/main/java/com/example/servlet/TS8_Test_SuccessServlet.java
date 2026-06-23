package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: TS8_Test_SuccessServlet
 * Package: com.example.servlet
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/6 - 16:41
 * @Version: v1.0
 */
@WebServlet("/success")
public class TS8_Test_SuccessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取request域中共享的user对象
        TS8_Test_User user = (TS8_Test_User) req.getAttribute("user");
        if(user != null){
            // 设置编码
            resp.setContentType("text/html;charset=utf-8");
            // 输出
            resp.getWriter().write("登录成功，欢迎您"+user.getUsername());
        }

    }
}

