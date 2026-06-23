package a2_Tomcat_And_Servlet;

/**
 * ClassName: TS1
 * Package: a2_Tomcat_And_Servlet
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/2 - 11:21
 * @Version: v1.0
 */
public class TS1 {

    /*
      一.web相关概念
          1.软件架构
              [1]C/S：客户端/服务器端
              [2]B/S：浏览器/服务器端
          2.资源分类
              [1]静态资源：用户访问后结果一致(html/css/javascript)
                  浏览器访问服务器后直接解析静态资源
              [2]动态资源：用户访问相同资源结果不一致(servlet/jsp,php,asp)
                  动态先转换为静态资源，再由服务器传递给浏览器
          3.网络通信三要素
              [1]IP：电子设备(计算机)在网络中的唯一标识
              [2]端口：应用程序在计算机中的唯一标识(0-65536)
              [3]传输协议：规定了数据传输规则
                  {1}基础协议
                      tcp：安全协议，三次握手，速度慢
                      udp：不安全协议，速度快

      二.web服务器软件
          服务器：安装了服务器的计算机
          服务器软件：接收了用户的请求，处理请求，做出响应
          web服务器软件：接收了用户的请求，处理请求，做出响应
              在web服务器(web容器)软件中，可以部署web项目，让用户通过浏览器来访问这些项目

          常见的java相关的web服务器软件
              webLogic：Oracle公司，大型的javaee服务器，支持所有的javaee规范，收费
              webSphere：IBM公司，大型的javaee服务器，支持所有的javaee规范，收费
              JBOSS：JBOSS公司，大型的javaee服务器，支持所有的javaee规范，收费
              Tomcat：Apache基金组织，中小型的javaee服务器，仅支持少量的javaee规范(servlet/jsp)，开源，免费

          javaee：Java语言在企业级开发中使用的技术规范的总和，共规定13项规范

          Tomcat：web服务器软件
              1.下载：http://tomcat.apache.org
              2.安装：解压即可，目录下不要有中文空格
              3.文件夹：
                  bin：可执行文件
                  conf：配置文件
                  lib：依赖jar包
                  logs：日志文件
                  temp：临时文件
                  webapps：存放web项目
                  work：存放运行时的数据
                  其他：通知文件、版本信息
              4.启动：(1)bin/startup.bat运行
                     (2)访问自己：浏览器访问localhost:8080
                        访问别人：浏览器访问别人的ip:8080
                     (3)问题：
                          [1]闪退：配置环境变量
                          [2]报错：端口号8080被占用，结束8080端口号对应程序或修改tomcat端口号
                              {1}结束占用端口进程：先在控制台输入netstat -ano查看本地地址对应PID数字，继续在任务管理器查看
                                                进程(注意勾选显示PID)，之后结束占用端口进程
                              {2}修改配置文件：conf下的server.xml文件打开，找到Connector标签修改port端口号保存即可
                              {3}注意：一般tomcat端口号设置为80，80是http协议默认端口号，访问时就不用输入端口号了
              5.关闭：
                  [1]正常关闭：bin/shutdown.bat关闭 或 ctrl+c
                  [2]强制关闭：点击关闭按钮
              6.卸载：删除文件夹即可
              7.配置：
                  [1]部署项目方式：
                      {1}直接将项目放到webapps目录下即可(在webapps目录下新建文件夹hello，在其中建立hello.html)
                      {2}访问hello文件下的hello.html文件：localhost/hello/hello.html
                  [2]简单部署方式：
                      {1}将项目压缩成.war压缩包，复制到webapps下，系统会自动解压，删除时删除war会自动删除解压文件
                  [3]xml系统文件配置：
                      {1}打开conf/server.xml文件，在<Host>标签中写入<Context docBase="D:\hello" path="/hello" />
                          docBase：项目存放路径
                          path：虚拟目录
                  [4]新建xml配置：(热部署，不用重启服务器，推荐部署方式)
                      {1}在conf/Catalina/localhost下创建任意名称的xml文件，在文件中编写
                          <Context docBase="D:\SoftwareInstallation\Tomcat" />
                          docBase：具体html的文件路径(不包括到html文件！)
                          虚拟目录：xml文件的名称
                          例：在目录下创建hellow.xml并写入上述Context标签，则访问3D.html为http://localhost:8080/hellow/3D.html
              8.静态项目和动态项目：
                  目录结构
                      java动态项目的目录结构：
                          -- 项目的根目录
                              -- WEB-INF目录(有这个目录表示为动态项目)：
                                  -- web.xml：web项目的核心配置文件
                                  -- classes目录：放置字节码文件的目录
                                  -- lib目录：放置依赖的jar包
                              -- 其他文件
              9.将Tomcat集成到IDEA中，并且创建javaee项目，部署项目
                  运行 -> 编辑配置 -> 编辑配置模板 -> TOMCAT服务器 -> 本地 -> 设置tomcat版本
                  详情见模块Tomcat，配置见康师傅视频
                  网址：http://localhost:8080/Tomcat_war_exploded/文件名
     */

    /*
      一.Servlet：servlet applet
          1.概念：运行在服务器端的小程序
              Servlet就是一个接口(规范)，定义了Java类被浏览器访问到(tomcat识别)的规则，自定义一个类实现Servlet接口，复写方法
          2.使用：(见模块Servlet)
              (1)创建JavaEE项目
              (2)定义类，实现Servlet接口
              (3)在web.xml中配置Servlet
     */

}
