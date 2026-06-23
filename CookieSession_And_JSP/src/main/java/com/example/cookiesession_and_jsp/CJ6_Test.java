package com.example.cookiesession_and_jsp; /**
 * ClassName: ${NAME}
 * Package: ${PACKAGE_NAME}
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/12 - 10:36
 * @Version: v1.0
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

/**
 * Session案例：
 *     1.需求
 *         (1)访问带有验证码的登录页面CJ6_Test_login.jsp
 *         (2)用户输入用户名、密码及验证码
 *             [1]若用户名或密码有误，跳转登录页面，提示用户名或密码有误
 *             [2]若验证码有误，跳转登录页面，提示验证码有误
 *             [3]输入正确，跳转到主页CJ6_Test_success.jsp，显示用户欢迎您
 *     2.分析
 *         (1)设置request编码
 *         (2)获取参数Map集合
 *         (3)获取验证码
 *         (4)将用户信息封装到User对象
 *         (5)判断生成验证码和输入验证码是否一致，从session中获取程序生成的验证码
 *             [1]验证码一致：再判断用户名和密码是否一致
 *                 {1}一致
 *                     {{1}}登陆成功
 *                     {{2}}存储数据到session
 *                     {{3}}跳转到主页CJ6_Test_success.jsp
 *                 {2}不一致
 *                     {{1}}提示错误信息
 *                     {{2}}跳转登录页面
 *             [2]验证码不一致
 *                 {1}提示错误信息
 *                 {2}跳转登录页面
 *         (6)跳转页面，返回正确/错误信息
 *     3.修改：加入了匹配数据库数据
 */

@WebServlet("/cj6")
public class CJ6_Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

}
