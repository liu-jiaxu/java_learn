package com.example.cookiesession_and_jsp; /**
 * ClassName: ${NAME}
 * Package: ${PACKAGE_NAME}
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/12 - 17:05
 * @Version: v1.0
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

/**
 * 一.JSP
 *     1.指令
 *         (1)作用：配置JSP页面，导入配置文件
 *         (2)格式：<%@ 指令名称 属性名1=属性值1 属性名2=属性值2 ... %>
 *         (3)分类：
 *             [1]page：配置JSP页面
 *                 属性名：
 *                     {1}contentType:等同于response.setContentType()
 *                         例：contentType="text/html;charset=utf-8"
 *                         {{1}}设置响应体的mime类型以及字符集
 *                         {{2}}设置当前jsp页面的编码(只有高级开发工具生效，低级工具设置pageEncoding属性)
 *                     {2}language="java"
 *                     {3}buffer：缓冲区大小，默认8kb
 *                     {4}import：导入java包
 *                         例：import="java.util.List"
 *                     {5}errorPage：当前页面发生异常后自动跳转到指定页面
 *                         例：errorPage="404.jsp"
 *                     {6}isErrorPage：标识当前页面是否为错误页面(默认false)
 *                         例：isErrorPage="true"，设置为true后，可以用exception对象打印错误信息
 *             [2]include：导入页面的资源文件
 *                 例：<%@ include file="top.jsp" %>
 *             [3]taglib：导入资源
 *                 例：<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 *                     prefix：前缀，自定义的
 *     2.注释
 *         <%-- --%>：注释所有内容
 *         <!-- --> ： 仅注释html相关内容
 *     3.内置对象
 *         在jsp页面中不需要创建，直接使用的对象，共有9个
 *         变量名                               类型                  作用
 *         pageContext：当前页面的上下文对象      PageContext           当前页面共享数据，还可以获取其它八个对象
 *         request：当前页面的请求对象            HttpServletRequest    一次请求访问的多个资源(转发)
 *         session：当前页面的会话对象            HttpSession           一次会话的多个请求间
 *         application：当前页面的应用程序对象     ServletContext        所有用户间共享数据
 *         response：当前页面的响应对象           HttpServletResponse   响应对象
 *         page：当前页面对象                    Object                当前页面(Servlet)的对象，相当于this
 *         out：当前页面的输出对象                JSpWriter             输出对象，输出数据到页面上
 *         config：当前页面的配置对象             ServletConfig         Servlet的配置对象
 *         exception：当前页面的异常对象          Throwable             异常对象
 *
 * 二.MVC开发模式(遵循模式规范！)
 *     1.M：Model，模型，使用JavaBean完成
 *         完成具体的业务操作，如：查询数据库、封装对象
 *     2.V：View，视图，使用JSP完成
 *         展示数据
 *     3.C：Controller，控制器，使用Servlet完成
 *         获取用户输入
 *         调用模型
 *         将模型处理后的数据交给视图展示
 *     4.优缺点
 *         (1)耦合性低，便于维护，便于分工
 *         (2)重用性高
 *         (3)使得项目结构变得复杂，对开发人员要求高
 *
 * 三.EL表达式 (见CJ7_Test_el.jsp)
 *     1.概念：Expression Language 表达式语言
 *     2.作用：替换和简化jsp页面中的java代码的编写
 *     3.语法：${}
 *     4.注意：jsp默认支持el表达式，若原样输出，则配置：
 *         (1)忽略所有el表达式：设置page指令isELIgnored="true"
 *         (2)忽略当前el表达式：\${...}
 *     5.功能
 *         (1)运算
 *             [1]算术运算符：+ - * /(可以写成div) %(可以写成mod)
 *             [2]比较运算符：> < = ...
 *             [3]逻辑运算符：&& || !
 *             [4]空运算符：empty 用于判断字符串、数组、集合对象是否为null且长度是否为0
 *                 例：${empty list} -> return false不为空/true为空
 *                 补：not empty -> return true不为空/false为空 (与empty相反)
 *         (2)获取值：el表达式只能从域对象中获取值(四个域对象)
 *             [1]语法：
 *                 {1}${域名称.键名}：从指定域中获取指定键的值
 *                 {2}${键名}：依次从最小的域中查找是否有该键对应的值，直到找到为止
 *             [2]域名称(域从小到大排)
 *                 {1}pageScope -> pageContext
 *                 {2}requestScope -> request
 *                 {3}sessionScope -> session
 *                 {4}applicationScope -> application (ServletContext)
 *                 例：在request域中存储了name=zs
 *                     获取：${requestScope.name}
 *             [3]获取对象、集合的值
 *                 {1}对象：${域名称.键名.属性名}
 *                 {2}list集合：${域名称.键名[索引]}
 *                 {3}map集合：${域名称.键名.key名称}
 *         (3)隐式对象
 *             el表达式中有11个隐式对象
 *                pageScope：获取pageContext域属性，相当于pageContext.getAttribute("xxx")
 *                requestScope：获取request域属性，相当于request.getAttribute("xxx")
 *                sessionScope：获取session域属性，相当于session.getAttribute("xxx")
 *                applicationScope：获取application域属性，相当于application.getAttribute("xxx")
 *                param：对应参数，它是一个Map，其中key是参数，value是参数值，适用于单值的参数，相当于request.getParameter("xxx")
 *                paramValues：对应参数，她是一个Map，其中key是参数，value是多个参数值，适用于多值的参数，相当于request.getParameterValues("xxx")
 *                header：对应请求头，它是一个Map，其中key表示头名称，value是单个头值，适用于单值的请求头，相当于request.getHeader("xxx")
 *                headerValues：对应请求头，它是一个Map，其中key表示头名称，value是多个头值，适用于多值的请求头，相当于request.getHeaders("xxx")
 *                initParam：获取web.xml中<context-param>内的参数，${ initParam.xxx}，xxx就是<param-name>标签内的值，进而得到<param-value>中的值
 *                cookie：用于获取cookie，Map<String,Cookie>，其中key是cookie的name，value是cookie对象，例如${cookie.JSESSIONID.value }就是获取sessionId
 *                pageContext：可以获取JSP九大内置对象，相当于使用该对象调用getxxx()方法，例如pageContext.getRequest()可以写为${pageContext.request)
 *
 * 四.JSTL标签 (见CJ7_Test_jstl.jsp)
 *      1.概念：JavaServer Pages Tag Library (JSP标准标签库)，是由Apache组织提供的开源免费的jsp标签
 *      2.作用：简化和替换jsp页面上的java代码
 *      3.步骤
 *          (1)导入jstl相关jar包
 *          (2)引入标签库：taglib指令：<@ taglib prefix="" uri="">
 *          (3)使用标签
 *      4.常用jstl标签
 *          (1)if
 *          (2)choose
 *          (3)forEach
 *
 * 五.三层架构(web服务器中)
 *     1.界面层(表示层/web层)：用户看到的界面，用户可以通过界面上的组件和服务器进行交互
 *         作用：接收用户参数，封装数据，调用业务逻辑层完成处理，转发jsp页面完成显示
 *         包：cn.itcast.项目名.web
 *         框架：SpringMVC
 *     2.业务逻辑层(service层)：处理业务逻辑层
 *         作用：组合DAO层中的简单方法(curd)，形参复杂的功能(业务逻辑操作)
 *         包：cn.itcast.项目名.service
 *         框架：Spring
 *     3.数据访问层(dao层：Data Access Object)：操作数据存储文件
 *         作用：定义了对于数据库最基本的curd操作
 *         包：cn.itcast.项目名.dao
 *         框架：MyBatis
 *     4.一次交互：客户端浏览器 -> web服务器(界面层 -> 业务逻辑层 -> 数据访问层) -> 数据库DB
 *         浏览器发送请求到服务器，服务器的控制器Servlet负责接收请求并获取用户提交信息的参数，然后控制器调用业务逻辑层，
 *     业务逻辑层调用数据访问层获取数据，之后依次逐层向前返回数据，最后返回控制器Servlet，控制器将返回的数据封装到一个jsp
 *     页面中，充当视图作用给用户浏览器。
 *     5.注：
 *         实际业务中，通常不会直接由界面层访问dao层，因为dao层的curd方法过于单一不满足业务需求，需要经过业务逻辑层封装编写
 *     具体功能。今后通过SSM三大框架可以简化业务逻辑。
 *
 */

@WebServlet("/cj7")
public class CJ7_Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

}
