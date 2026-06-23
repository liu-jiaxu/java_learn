/**
 * ClassName: test
 * Package: com.example.cookiesession_and_jsp_test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/15 - 9:56
 * @Version: v1.0
 */

/**
 * 注：使用的jar包均需导入到tomcat的jar文件夹中！
 * 案例：用户信息列表展示
 *     1.需求：用户信息的增删改查操作
 *     2.设计：
 *         (1)技术选型：servlet+jsp+MySQL+jdbcTemplate+druid+BeanUtils+tomcat
 *         (2)数据库设计：库表设计
 *             create database ...;
 *             use ...;
 *             create table user (
 *              id int primary key auto_increment,
 *              name varchar(20) not null,
 *              gender varchar(20) not null,
 *              age int not null,
 *              address varchar(200),
 *              qq varchar(20),
 *              email varchar(200)
 *             )
 *         (3)开发阶段
 *             [1]环境搭建
 *                 {1}创建数据库环境
 *                 {2}创建项目，导入jar包
 *             [2]编码
 *         (4)测试
 *         (5)部署，运维
 *    3.分析编码实现过程
 *         (1)导入前端页面配置，替换index.jsp
 *         (2)设计包分类具体功能：
 *             [1]dao：根据实际业务需求，封装数据库提供的具体增删改查功能
 *             [2]domain：存放各种JavaBean实现类
 *             [3]service：
 *             [4]util：连接数据库的工具包
 *             [5]web：实现前端servlet功能
 *                 {1}filter(过滤器、监听器等，之后学)
 *                 {2}servlet(实现功能)
 *         (3)在各个包中创建具体类并实现功能，同时修改前端配置
 *             [1]domain包下创建User类，对应user表的实体类，根据数据库表字段创建属性和get/set等方法
 *             [2]修改前端编写的index.html，将该文件复制到index.jsp中，同时修改跳转路径(虚拟路径(el表达式动态获取)+真实路径)
 *             [3]在servlet包下创建UserListServlet类，对应index.jsp文件中的跳转路径，该类用于调用用户查询方法并返回数据到页面
 *             [4]在service包下创建UserService用户管理业务接口，定义用户增删改查等功能，此接口对应上述[3]中调用的用户查询方法
 *                 补：具体查询所有用户方法findAll()，该方法返回一个list集合存储用户数据
 *             [5]在service中定义UserServiceImpl类，实现接口用户增删改查等功能，调用dao完成查询
 *                 补：定义接口的原因：
 *                     若项目完成之后定义的增删改查功能类不满足实际业务需求，仅需要修改对应功能类即可，不用修改接口了
 *             [6]在dao包下创建UserDao接口，该接口定义用户增删改查等方法，实现jdbc操作数据库
 *             [7]在dao包下创建UserDaoImpl类，实现接口，同时具体编写用户增删改查等功能，供service包下的UserServiceImpl类调用
 *                 补：dao包下的类用于实现基本的功能并和数据库交互
 *                    service包下的类用于修改完善dao包类的功能并与页面交互
 *             [8]dao包下创建好UserDao接口和UserDaoImpl类后，在service包下的UserServiceImpl类定义UserDao对象dao，利用该对象
 *                调用UserDao类中的具体方法来实现UserDaoImpl类的对应方法。例如：UserServiceImpl类的findAll()方法就是dao对象调
 *                用UserDaoImpl的findAll()方法实现的，功能是获取所有用户信息并返回一个list集合
 *                 补：private UserDao dao = new UserDaoImpl() -> 接口对象 = new实现类
 *                     利用接口对象new实现类应用了啊、java多态的特点，父类引用指向子类对象，父类(接口)的引用就能够直接调用子类
 *                 (实现类)的方法，类似于List list = new ArrayList()，主要是为了减少后期功能变更时代码的修改量
 *             [9]有了dao对象调用具体功能后，就可以在servlet包下的UserListServlet类中调用，具体方法也是“接口对象 = new实现类”，
 *                创建service对象，获取具体参数并返回前端页面。例如：service.findAll()方法获取所有用户参数并返回集合list，使用
 *                setAttributes()方法将list集合存储到页面中，并用getRequestDispatcher("/list.jsp").forward(req,resp)方法
 *                将页面转发到list.jsp
 *             [10]编写druid.properties配置文件和JDBCUtils类(该类之前写过，可直接复制作为模板使用)
 *             [11]在UserDaoImpI类中创建template对象用于具体操作数据库。例如：findAll()类
 *                 private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
 *                 public List<User> findAll() {
 *                 String sql = "select * from user";
 *                 List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
 *                 return users;
 *                 }
 *             [12]将前端html配置文件均改为jsp文件，并为jsp文件添加配置语句
 *             [13]将list.jsp表格中的用户信息更改为el表达式获取
 *             [14]按实际需求修改前端页面，例如按钮位置，添加文字，复选框等等(从bootstrap中文网 -> v3文档 -> 全局css样式，查找复制代码即可)
 *             [15]完成其它具体功能：
 *                 {1}简单功能
 *                     列表查询(findAll()已完成)
 *                     登录
 *                     添加
 *                     删除
 *                     修改
 *                 {2}复杂功能
 *                     删除选中信息
 *                     分页查询
 *                     复杂条件查询
 *             [16]登录功能：login.jsp。输入用户名，密码，验证码完成登录，成功跳转，错误提示信息
 *                 {1}调整页面html -> jsp，修改验证码路径，同时编写验证码servlet包下的CheckCodeServlet类(复制即可)
 *                 {2}实现验证码点击切换(jsp中编写onclick事件)
 *                 {3}jsp表单数据要提交到一个类中，在servlet包下新建LoginServlet类用于获取表单数据
 *                 {4}因为实现登录需要用户名和密码，所以要改造数据库的user表，插入两个字段username、password(均为varchar(32))，
 *                    并且在User中同样定义对应的属性和get/set方法，重写toString方法
 *                     示例：
 *                         ALTER TABLE USER
 *                         ADD username VARCHAR(32);
 *                         ALTER TABLE USER
 *                         ADD PASSWORD VARCHAR(32);
 *                 {5}在数据库添加一条用户信息，之后用于登录测试
 *                 {6}完成前端页面调整后，在servlet包下创建LoginServlet类，实现登录功能。首先获取用户输入的用户名、
 *                    密码、验证码，先判断验证码再判断用户名和密码，登录成功跳转，失败提示信息。(具体步骤如下)
 *                     {{1}}设置编码utf-8
 *                     {{2}}获取用户页面输入的验证码
 *                     {{3}}获取程序生成的验证码，同时进行忽略大小写匹配，若验证码正确则继续匹配用户名和密码，否则直接
 *                          报错提示并重新跳转到此登录页面(提示信息用setAttribute存储并用jsp的el表达式显示在页面上，
 *                          跳转用getRequestDispatcher()方法)
 *                     {{4}}封装对象User，使用getParameterMap()获取页面所有参数并返回一个map集合中，使用BeanUtils的
 *                     populate方法，将用户页面输入的数据保存到User类的对应属性中
 *                     {{5}}在UserDaoImpI类中编写登录方法findUserByUsernameAndPassword，并在UserServiceImlI类中
 *                          的登录方法使用dao对象调用
 *                     {{6}}调用service查询。在LoginServlet中创建UserService接口对象并调用login方法
 *                     {{7}}判断是否登录成功，成功则保存用户信息，跳转到index.jsp页面，失败返回信息并刷新
 *             [17]添加功能：点击添加按钮，跳转到添加页面add.jsp，完成数据添加后，点击提交按钮，会调用UserService的addUser
 *                 方法，此方法会调用UserDao的add方法向数据库添加数据，之后跳转到UserServletList页面并再次查询显示新数据
 *                     注：实际业务中，会有管理员登录表和用户信息表两张表，添加方法对应用户信息
 *                 {1}设置编码
 *                 {2}getParameterMap获取用户信息参数
 *                 {3}封装对象传值给user
 *                 {4}调用service的addUser方法添加用户信息
 *                 {5}重定向跳转到userListServlet
 *                 {6}在userservice和userdao中编写添加方法add
 *                 {7}修改前端页面布局及按钮功能等
 *             [18]删除功能
 *                 {1}获取id
 *                 {2}编写delete方法，调用service删除
 *                 {3}重定向
 *             [19]修改功能
 *             [20]删除选中功能
 *             [21]分页功能
 *             [22]复杂查询功能
 *             [23]调试程序
 */

public class CJ_Test_Introduce {



}
