package com.example.servlet;

import jakarta.servlet.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: TS6_Test
 * Package: com.example.servlet_test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/5 - 15:43
 * @Version: v1.0
 */

// request其它功能：获取请求参数通用方式
// 通过TS6_Test_regist.html网页测试！
@WebServlet("/ts6") // 注：不同类的urlPatterns不能相同！
public class TS6_Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1.获取请求参数通用方式
            // {1}根据参数名获取参数值：String getParameter(String name)
                String username = req.getParameter("username");
                System.out.println("get");
                System.out.println(username);
            // {2}根据参数名称获取参数值的数组：String[] getParameterValues(String name)
                String[] hobbies = req.getParameterValues("hobby");
                for(String hobby:hobbies){
                    System.out.print(hobby+" ");
                }
                    // 问题：如果复选框为null(什么也不选)时浏览器会报错500！
                System.out.println();
            // {3}获取所有请求参数名称Enumeration<String> getParameterNames()
                Enumeration<String> parameterNames = req.getParameterNames();
                while(parameterNames.hasMoreElements()){ // 类似于JDBC的循环取值
                    String name = parameterNames.nextElement();
                    String[] hobbies2 = req.getParameterValues(name);
                    for(String hobby:hobbies2){
                        System.out.print(name+":"+hobby+" ");
                    }
                        // 注：getParameter仅会获取单个元素，若一个参数名称对应多个参数值时，应用方法getParameterValues
                }
                System.out.println();
            // {4}获取所有参数的map集合：Map<String, String[]> getParameterMap()
                Map<String, String[]> parameterMap = req.getParameterMap();
                // 获取键值对，单独获取键值方法见a_Java_Basics/a7_Collection/C6.java
                Set<String> set=parameterMap.keySet();
                for(String key:set){
                    String[] values = parameterMap.get(key);
                        // 注意此处的Map泛型为数组，不能直接用Iterator遍历，否则遍历得到的均为数组地址
                    for(String value:values){
                        System.out.print(key+"="+value+" ");
                    }
                }
                    // Set没有get方法获取元素，不能用普通for循环

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 对于doPost方法，可能会出现中文乱码，需要在获取参数前设置编码
        req.setCharacterEncoding("utf-8");

        // doPost中对应的方法
            // 由于doget和dopost中的方法无实质区别，所以会在dopost中调用doget的getParameter方法以减少代码量
            this.doGet(req, resp);

    }
}
