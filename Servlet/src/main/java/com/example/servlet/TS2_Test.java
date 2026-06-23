package com.example.servlet;

// 注意Tomcat10以上把包名改为了jakarta而不是java了！
import jakarta.servlet.*;
import java.io.IOException;

/**
 * ClassName: TS2_Test
 * Package: com.example.servlet_test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/3 - 14:32
 * @Version: v1.0
 */

/**
 * 一.Servlet生命周期
 *     1.被创建：执行init方法，仅执行一次
 *         (1)默认时第一次访问时Servlet被创建，可以配置Servlet创建的时机(见web.xml的load-on-startup标签)
 *         (2)Servlet的init方法，仅执行一次，说明一个Servlet在内存中仅存在一个对象，Servlet是单例的，多个用户访问
 *            时可能有安全问题。解决：尽量不要在Servlet中定义成员变量，定义局部变量不共享，定义的成员变量不要修改值。
 *     2.提供服务：执行service方法，执行多次
 *     3.被销毁：执行destroy方法，仅一次
 *         服务器正常关闭时Servlet被销毁，销毁前执行destroy方法，一般用于释放资源
 */
public class TS2_Test implements Servlet {

    /**
     * 初始化方法
     * 在Servlet创建时执行，只会执行一次
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    /**
     * 获取ServletConfig(Servlet的配置对象)对象
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 提供服务方法
     * 每一次servlet被访问时均会执行
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service");
    }

    /**
     * 获取Servlet的一些信息：版本...
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 销毁方法
     * 在服务器正常关闭时执行一次
     */
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
