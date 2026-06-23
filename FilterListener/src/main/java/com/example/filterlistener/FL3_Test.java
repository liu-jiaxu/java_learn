package com.example.filterlistener; /**
 * ClassName: ${NAME}
 * Package: ${PACKAGE_NAME}
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/17 - 19:05
 * @Version: v1.0
 */

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 一.监听器
 *     1.概念：web的三大组件之一
 *     2.事件监听机制
 *         (1)事件：一件事情
 *         (2)事件源：事件发生的地方
 *         (3)监听器：一个对象
 *         (4)注册监听：将事件、事件源、监听器绑定在一起。当事件源上发生某个事件后，执行监听器代码
 *     3.ServletContextListener(接口)：监听ServletContext对象的创建和销毁
 *         (1)方法：
 *             [1]void contextDestroyed(ServletContextEvent sce)：ServletContext对象被销毁前调用的方法
 *             [2]void contextInitialized(ServletContextEvent sce)：ServletContext对象创建后调用的方法
 *         (2)步骤
 *             [1]定义一个类，实现ServletContextListener接口
 *             [2]复写方法
 *             [3]配置(web.xml或注解)
 *                 <listener>
 *                     <listener-class>com.example.filterlistener.FL3_Test</listener-class>
 *                 </listener>
 */

// 注解配置，无需指定路径！
// @WebListener()
public class FL3_Test implements ServletContextListener{

    /**
     * 监听ServletContext对象创建的，ServletContext对象服务器启动后自动创建
     * 在服务器启动后自动调用
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 加载资源文件
        // 1.获取ServletContext对象
        ServletContext servletContext = sce.getServletContext();
        //2.加载资源文件
        String init = servletContext.getInitParameter("contextConfigLocation");
        // 3.获取真实路径
        String realPath = servletContext.getRealPath(init);
        // 4.加载进内存
        try {
            FileInputStream fis = new FileInputStream(realPath);
            System.out.println(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("创建111");
    }

    /**
     * 服务器关闭后，ServletContext对象被销毁，服务器正常关闭后调用
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("销毁111");
    }

}
