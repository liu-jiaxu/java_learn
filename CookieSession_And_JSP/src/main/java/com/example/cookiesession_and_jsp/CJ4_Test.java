package com.example.cookiesession_and_jsp; /**
 * ClassName: ${NAME}
 * Package: ${PACKAGE_NAME}
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/10 - 11:22
 * @Version: v1.0
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

/**
 * 一.JSP
 *     1.概念
 *         (1)Java Service Pages：java服务器页面
 *             一个特殊的页面，其中既可以指定定义html标签，又可以定义java代码
 *             用于简化书写
 *         (2)原理：JSP本质上是Servlet
 *             [1]服务器解析请求消息，找是否有index.jsp资源
 *             [2]找到后会将index.jsp转换为.java文件
 *             [3]编译.java文件，生成.class字节码文件
 *             [4]由字节码文件提供访问
 *         (3)JSP脚本：JSP定义java代码的方式
 *             [1]<% 代码 %>：定义的java代码，在service方法中，service方法可以定义什么，该脚本就可以定义什么
 *             [2]<%! 代码 %>：定义的java代码，在jsp转换后的java类的成员位置(定义成员变量，成员类等)
 *             [3]<%= 代码 %>：输出到页面上。输出语句可以定义什么，该脚本中就可以定义什么
 *         (4)JSP内置对象
 *             在jsp页面中不需要获取和创建，可以直接使用的对象。jsp共有9个内置对象
 *             注：使用时需要导入tomcat的jsp-api.jar包
 *             3个主要的对象：
 *                 [1]request
 *                 [2]response
 *                 [3]out：字符输出流对象，可以将数据输出到页面上
 *                     response.getWriter()和out.write()区别：
 *                         在tomcat服务器真正给客户端做出响应之前，会先找response缓冲区数据，再找out缓冲区数据。
 *                     response.getWriter()数据输出永远在out.write()之前。(不要两个一起用，会打乱布局，尽量用out.write())
 */

/**
 * 案例：使用jsp修改案例
 */

@WebServlet("/cj4")
public class CJ4_Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

}
