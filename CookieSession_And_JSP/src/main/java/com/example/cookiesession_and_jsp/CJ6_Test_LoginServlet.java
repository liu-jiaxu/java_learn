package com.example.cookiesession_and_jsp; /**
 * ClassName: ${NAME}
 * Package: ${PACKAGE_NAME}
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/12 - 11:21
 * @Version: v1.0
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

// CJ6_Test_login.jsp登录
@WebServlet("/cj6ls")
public class CJ6_Test_LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.设置编码
        request.setCharacterEncoding("utf-8");
        // 2.依次获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        // 3.获取生成的验证码
        HttpSession session = request.getSession();
        String check_Code_session = (String) session.getAttribute("checkCode_session");
        session.removeAttribute("checkCode_session"); // 删除验证码防止多次登录的安全问题
        // 4.先判断验证码是否正确
        if(check_Code_session != null && check_Code_session.equalsIgnoreCase(checkCode)){ // equalsIgnoreCase忽略大小写比较
                // 注：equalsIgnoreCase没有非空判断，要手动编写
            // 一致：判断用户名和密码
            try {
                CJ6_Test_User cj6TestUser = new CJ6_Test_User();
                cj6TestUser.setUsername(username);
                cj6TestUser.setPassword(password);
                    // 将用户页面输入的姓名和密码载入CJ6_Test_User类保存
                boolean result = CJ6_Test_UserDao.login(cj6TestUser); // 获取输入姓名密码和数据库数据匹配结果
                if(result){
                    // 登录成功
                    // 存储用户信息
                    session.setAttribute("user", username);
                    // 重定向到success.jsp
                    response.sendRedirect(request.getContextPath()+"/CJ6_Test_success.jsp");
                    CJ6_Test_JDBC.releaseConnection(); // 关闭数据库流
                } else {
                    // 登录失败：存储提示信息到request并转发到登录页面
                    request.setAttribute("login_error","用户名或密码错误");
                    request.getRequestDispatcher("/CJ6_Test_login.jsp").forward(request,response);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            // 不一致：存储提示信息到request并转发到登录页面
            request.setAttribute("cc_error","验证码错误");
            request.getRequestDispatcher("/CJ6_Test_login.jsp").forward(request,response);
        }

    }

}
