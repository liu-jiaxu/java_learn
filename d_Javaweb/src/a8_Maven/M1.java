package a8_Maven;

/**
 * ClassName: M1
 * Package: a8_Maven
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/27 - 16:56
 * @Version: v1.0
 */
public class M1 {

    /*

      以下是Maven3.6.0的简要学习，具体学习见模块g_Git_And_Maven

      一.Maven
          1.介绍
              Maven是一个项目管理工具，它包含一个项目对象模型(pom：project object model)，一组标准集合，一个项目
          生命周期(project lifecycle)，一个依赖管理系统，和用来运行定义在生命周期阶段中插件目标的逻辑
          2.安装
              (1)官网下载解压
              (2)配置环境变量
          3.文件目录
              (1)bin：存放可执行文件，mvn最重要
              (2)boot：Maven启动器
              (3)conf：Maven配置文件，setting重要
              (4)lib：jar包
              (5)其它
          4.Maven仓库
              (1)本地仓库：用于存储远程仓库和中央仓库下载的插件和jar包，项目使用的插件和jar包优先从本地仓库寻找
              (2)远程仓库：解决从中央仓库下载文件缓慢的问题，团队自己搭建(内地阿里云)
              (3)中央仓库：地址http://repo1.maven.org/maven2，服务于整个互联网，由Maven团队自己维护，包含绝大多数jar包
              注：[1]配置settings.xml文件中的<mirrors>，选择内地阿里云远程仓库
                  <mirror>
      	                <id>nexus-aliyun</id>
       	            <mirrorOf>*</mirrorOf>
       	            <name>Nexus aliyun</name>
      	                <url>http://maven.aliyun.com/nexus/content/groups/public</url>
      	           </mirror>
      	           [2]配置settings.xml文件中的<localRepository>，配置本地仓库路径
      	           <localRepository>D:/SoftwareInstallation/Maven/RepMaven </localRepository>
          5.Maven项目目录
              -- 根目录
                  -- src(源码)
                     -- main(主工程代码)
                         -- java(主工程代码)
                         -- resources(配置文件)
                         -- webapp(web项目的资源目录jsp/html/WEB-INF...)
                     -- test(测试代码)
                         -- java(测试代码)
                         -- resources(测试需要的配置文件)
                  -- pom.xml(项目核心配置文件)
          6.Maven命令(控制台命令)
              (1)项目路径>mvn compile  将该项目中的类编辑为字节码文件保存在生成的target文件中
              (2)项目路径>mvn clean    清除target目录
              (3)项目路径>mvn package  执行complie命令并且将原项目打包成war文件一并存放在target下
              (4)项目路径>mvn install  将该项目配置到本地仓库中
          7.生命周期
              (1)编译->测试->打包->编译：同一生命周期中，执行后面的指令，前面的指令也会执行
              (2)三套独立的生命周期：
                  [1]Clean Lifecycle：构建之前进行的清理工作
                  [2]Default Lifecycle：构建的核心部分，编译、测试、打包、部署
                  [3]Site Lifecycle：生成项目报告、站点，发布站点

      二.idea中开发Maven项目(见项目Maven及Maven_Skeleton)
          1.配置：
              设置 -> 构建、执行、部署 -> 构建工具 -> Maven -> 修改三个路径
          2.Maven坐标
              坐标：被Maven管理的资源(jar包、插件)的唯一标识，pom.xml文件中配置，在创建时的高级设置中可以设置
                   由三部分构成：
                       groupId：组织名称
                       atifactI：模块名称
                       version：版本号
          3.Maven项目创建
              (1)不使用骨架：直接在新建项目中选择Maven项目
              (2)使用骨架：首次使用会下载资源，选择Maven Archetype并选择骨架
                  quickstart：java项目
                  webapp：web项目
              注：[1]要手动添加resource包并右键改为测试包
                 [2]不要在项目中创建Maven模块，在新项目中创建模块，否则先前导入的jar包会失效
          4.Maven测试(见项目Maven_Skeleton)
     */

}
