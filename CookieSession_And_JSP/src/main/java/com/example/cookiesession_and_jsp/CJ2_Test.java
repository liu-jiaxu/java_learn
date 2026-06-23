package com.example.cookiesession_and_jsp; /**
 * ClassName: ${NAME}
 * Package: ${PACKAGE_NAME}
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/9 - 9:29
 * @Version: v1.0
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/cj2")
public class CJ2_Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        // 3.获取Cookie
        Cookie[] cookies = request.getCookies();
        // 遍历Cookie，获取数据
        if(cookies != null){
            for(Cookie cookie : cookies){
                String name = cookie.getName();
                String value = cookie.getValue();
                String name2 = URLDecoder.decode(name,"utf-8");
                String value2 = URLDecoder.decode(value,"utf-8"); // 编码设置
                response.getWriter().write(name2 + "=" + value2 + "<br>"); // 正常输出到浏览器
                System.out.println(name2+" "+value2); // 控制台输出正常，未出现乱码(注意设置tomcat的虚拟机选项！)
            }
        }

    }

}
