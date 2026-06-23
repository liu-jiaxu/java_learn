package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet; // 还是老问题，注意注解引入的包名改变了

import java.io.IOException;

/**
 * ClassName: TS3_Test
 * Package: com.example.servlet_test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/3 - 16:23
 * @Version: v1.0
 */

 /** 一.Servlet3.0
  *     1.支持注解配置，不需要配置web.xml了
  *         配置步骤：
  *             (1)创建JAVAEE项目，选择Servlet的版本3.0以上，可以不创建web.xml
  *             (2)定义一个类，实现Servlet接口
  *             (3)复写方法
  *             (4)在类上使用@WebServlet注解，进行配置(记得导入jar包且修改为jakarta)
  *
  * 二.IDEA和tomcat相关配置
  *     1.IDEA会为每一个tomcat项目单独建立一份配置文件
  *         运行时控制台信息显示：
  *         Using CATALINA_BASE:"C:\Users\zgh29\AppData\Local\JetBrains\IntelliJIdea2022.3\tomcat\ffedde2a-d128-43ab-a0d4-2c49c74b2e7e"
  *     2.必须在tomcat对应的webapp文件下创建html等文件，在WEB-INF中的文件浏览器无法直接访问
  *     3.断点调试，打断点 -> 调试
  */

// 最简单写法：WebServlet注解中的value可直接赋值路径且value可省略：@WebServlet("/ts3")
@WebServlet(urlPatterns = "/ts3")
    // @WebServlet("ts3")
    // 浏览器会根据url+/ts3地址执行这个类
public class TS3_Test implements Servlet {

     @Override
     public void init(ServletConfig servletConfig) throws ServletException {

     }

     @Override
     public ServletConfig getServletConfig() {
         return null;
     }

     @Override
     public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
         System.out.println("12345");
     }

     @Override
     public String getServletInfo() {
         return null;
     }

     @Override
     public void destroy() {

     }

 }
