package com.example.filterlistener;

/**
 * ClassName: FL1_Test
 * Package: com.example.filterlistener
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/17 - 9:49
 * @Version: v1.0
 */


import jakarta.servlet.*;

import java.io.IOException;

/**
 * 一.Javaweb三大组件
 *     1.Filter过滤器(重点)
 *     2.Listener监听器
 *     3.Servlet
 *
 * 二.Filter过滤器
 *     1.概念：web中的过滤器：当访问服务器的资源时，过滤器可以将请求拦截下来，为请求添加一些特殊的功能后再访问服务器资源
 *         一般用于完成同于的操作(如每个程序都需要设置一些内容，可以放到过滤器中)，例如：登录验证，统一编码，敏感字符过滤
 *     2.步骤
 *         (1)定义一个类，实现接口Filter
 *         (2)复写方法
 *         (3)配置拦截路径(配置web.xml或注解)
 *     3.细节
 *         (1)web.xml配置
 *             <filter>
 *             <filter-name>fl1</filter-name>
 *             <filter-class>com.example.filterlistener.FL1_Test</filter-class>
 *             </filter>
 *             <filter-mapping>
 *             <filter-name>fl1</filter-name>
 *             <url-pattern>/index.jsp</url-pattern>
 *             </filter-mapping>
 *         (2)过滤器执行流程
 *             [1]先执行doFilter方法，执行放行请求前的语句
 *             [2]执行放行语句doFilter，放行对应jsp资源
 *             [3]执行放行请求后的语句
 *         (3)过滤器生命周期方法
 *             [1]init：服务器启动后，会创建Filter对象。调用init方法
 *             [2]doFilter：每一次请求被拦截资源时会执行doFilter
 *             [3]destroy：服务器正常关闭后会执行destroy
 *                 注：要先配置Tomcat关闭端口(conf/service.xml中的-1改为8005)，才可以用idea正常关闭服务
 *         (4)过滤器配置
 *             [1]拦截路径配置
 *                 {1}具体资源路径：/index.jsp，只有访问index.jsp时才会拦截
 *                 {2}目录拦截：/user/*，拦截user目录下的所有资源
 *                     注：user不是文件路径，是注解WebServlet指定的真实路径
 *                 {3}后缀名拦截：*.jsp，访问所有后缀名为jsp时，过滤器都会执行
 *                 {4}拦截所有资源：/*，所有资源拦截
 *             [2]拦截方式配置(见FL2_Test)
 *                 {1}注解配置：设置dispatcherTypes属性
 *                     {{1}}REQUEST：默认值，表示浏览器直接请求资源后拦截
 *                     {{2}}FORWARD：转发访问资源时拦截
 *                     {{3}}INCLUDE： 包含访问资源时拦截
 *                     {{4}}ERROR：错误跳转资源时拦截
 *                     {{5}}ASYNC：异步访问资源时拦截
 *                 {2}web.xml配置
 *                     <filter-mapping>
 *                     <filter-name>fl1</filter-name>
 *                     <url-pattern>/index.jsp</url-pattern>
 *                     <dispatcherTypes>REQUEST</dispatcherTypes>
 *                     </filter-mapping>
 *         (5)过滤器链(多个过滤器)
 *             {1}执行顺序：两个过滤器为例：
 *                 过滤器1 -> 过滤器2 -> 资源执行 -> 过滤器2 -> 过滤器1
 *                 浏览器发送请求一次经过过滤器1、2，之后到服务器端找到资源执行，服务器返回响应时依次又经过过滤器2、1到浏览器
 *             {2}拦截先后顺序：
 *                 {{1}}注解配置：按照类名的字符串比较规则，值小的先拦截(例如两个过滤器类F13和F2，按字符串规则F13 < F2，则F13先执行)
 *                 {{2}}web.xml配置：<filter-mapping>定义在上面的最先执行
 */

/**
 * 案例1：登录验证(详见CookieSession_And_JSP_Test的web/filter/LoginFilter)
 *     1.访问S3案例资源，验证其是否登录
 *     2.若登录则直接提示成功
 *     3.未登录则跳转到登录页面，提示信息：请先登录
 *
 * 案例2：过滤敏感词汇(详见CookieSession_And_JSP_Test的web/filter/SensitiveWordsFilter)
 *     1.需求
 *         (1)敏感词汇：笨蛋、坏蛋
 *         (2)敏感词汇替换为**
 *     2.分析
 *         (1)对request对象的getParameter方法进行增强，产生一个新的request替换旧的request的信息
 *             如何增强对象功能：使用设计模式(一些通用的解决固定问题的方式)
 *                 [1]装饰模式(笨重，不使用)
 *                 [2]代理模式
 *                     {1}概念
 *                         {{1}}真实对象：被代理的对象
 *                         {{2}}代理对象：代理真实对象完成需求
 *                         {{3}}代理模式：代理对象代理真实对象，达到增强真实对象功能的目的
 *                     {2}实现方式
 *                         {{1}}动态代理：在内存中形成代理类(常用)
 *                         {{2}}静态代理：有一个类文件描述代理模式
 *                     {3}动态代理实现
 *                         {{1}}代理对象和真实对象实现相同的接口
 *                         {{2}}代理对象 = Proxy.newProxyInstance();
 *                         {{3}}使用代理对象调用方法
 *                         {{4}}增强方法
 *                     {4}增强方式
 *                         {{1}}增强参数列表
 *                         {{2}}增强返回值类型
 *                         {{3}}增强方法体执行逻辑
 *         (2)放行，将新的request对象传入doFilter
 */

// 过滤器使用
// @WebFilter("/*") // /*代表所有资源，表示访问所有资源时，都要执行此过滤器
    // 此类配置了web.xml，无需注解
public class FL1_Test implements Filter {

    // 服务器启动后，会创建Filter对象。调用init方法
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    // 每一次请求被拦截资源时会执行(可能会执行多次，因为浏览器会发送其它请求，例如请求页面图片等。需要手动更改配置，不自动启动浏览器)
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Hello Filter");
        // 放行请求
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // 服务器正常关闭后会执行destroy，释放资源
        // 注：要先配置Tomcat关闭端口(conf/service.xml中的-1改为8005)，才可以用idea正常关闭服务
    @Override
    public void destroy() {
        System.out.println("destroy");
    }

}
