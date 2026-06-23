package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: TS9_Test_1
 * Package: com.example.servlet
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/8 - 8:55
 * @Version: v1.0
 */

// 重定向：运行此类后重定向到TS9_Test_1_login.html
@WebServlet("/ts91")
public class TS9_Test_1 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        /* // 复杂的重定向设置
        // 1.设置状态码302(302：该资源原本确实存在，但已经被临时改变了位置)
        response.setStatus(302);
        // 2.设置响应头location(location表示响应头对应路径)
        response.setHeader("location", "/ts92");
        // setheader("响应头名称","响应头设置")*/

        // 案例：打开登录页面TS9_Test_1_login.html，输入信息实现重定向
            response.setContentType("text/html;charset=utf-8");
            // 用 HttpServletRequest 对象的 getParameter() 方法获取用户名和密码
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            // 获取动态虚拟目录
            String contextPath = request.getContextPath();
            // 假设用户名和密码分别为 admin 和 123456 (注意匹配方法可以避免非空！)
            if ("admin".equals(username) && ("123456").equals(password)) {
                // 如果用户名和密码正确，重定向到 welcome.html
                response.sendRedirect(contextPath+"/TS9_Test_1_welcome.html");
                    // sendRedirect实现重定向
            } else {
                // 如果用户名和密码错误，重定向到 login.html
                response.sendRedirect(contextPath+"/TS9_Test_1_login.html");
            }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        this.doGet(request, response);

    }

}
