package a1_XML;

/**
 * ClassName: X1
 * Package: a1_xml
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/1 - 8:36
 * @Version: v1.0
 */
public class X1 {

    /*
      1.XML概念
          Extensible Markup Language 可拓展标记语言
              可拓展：标签均为自定义(<user><student>...)
      2.功能
          (1)配置文件
          (2)在网络中传输
      3.xml与html的区别
          w3c：万维网联盟
          xml语法严格，标签自定义，用于存储数据
          html语法松散，标签预定义，用于展示数据
      4.xml对properties的优势
          xml自定义标签：自定义标签user和标签下面的存储数据
              <user>
                  <name>张三</name>
                  <age>23</age>
                  <sex>nan</sex>
              </user>
      5.语法
          (1)基本语法
              [1]xml文档的后缀名：.xml
              [2]xml第一行必须定义为文档声明
              [3]xml文档中有且仅有一个根标签
              [4]属性值必须用引号(单双引号均可),必须有结束标签或自闭和
              [5]xml标签区别大小写
          (2)组成部分
              [1]文档声明
                  {1}格式 <?xml 属性列表 ?>
                  {2}属性列表
                      version="1.0"(属性必须，最低版本1.0)
                      encoding="UTF-8"(编码方式。默认字符集ISO-8859-1)
                      standalone="yes"(是否独立)
              [2]指令
                  结合css控制标签格式，用于展示数据，由于xml多用于存储数据，因此不常用指令
              [3]标签
                  规则：名称可包含字母、数字、其它字符
                       不能以数字或标点符号或xml开始，不能包含空格
              [4]属性
                  id值唯一
              [5]文本内容CDATA输出
                   <![CDATA[
                      if(a > b && a < b){}
                  ]]>
      6.约束：规定xml文档的书写规范
          (1)框架使用者：程序员
              能够在xml中引入约束文档
              能够简单读懂约束文档
          (2)分类
              DTD：简单约束技术
                  [1]操作：引入DTD文档到xml文档中
                      {1}内部dtd：将约束规则定义在xml文档中
                      {2}外部dtd：将约束规则定义在外部dtd文件中
                          {{1}}本地：<!DOCTYPE 根标签名 SYSTEM "dtd文件的位置">
                          {{2}}网络：<!DOCTYPE 根标签名 PUBLIC "dtd文件名" "dtd文件位置URL">
              Schema：复杂约束技术(后缀名xsd)
                  可以约束标签的具体内容
          (3)解析：操作xml文档，将文档中的数据读取到内存中
              [1]解析(读取)：将文档内容读取到内存中
              [2]写入：将内存数据保存到xml中
              [3]解析方式：
                  {1}DOM：DOM树->html标签(用于服务器)
                      优点：操作方便，可以对文档curd
                      缺点：消耗内存，一次性全部读取所有内容
                  {2}SAX：逐行读取并释放，基于事件驱动(用于手机)
                      优点：不占内存
                      缺点：只能读取，不能增删改
              [4]xml常见解析器：
                  JAXP：sun公司提供，支持DOM和SAX方式，代码繁琐不推荐
                  DOM4J：优秀解析器，基于DOM实现
                  Jsoup：jsoup是一个用于处理真实HTML的Java库。它提供了一个非常方便的API，用于提取和操作数据，使用最好的DOM，CSS和类似jquery的方法。
                      Jsoup使用：
                          导入jar包
                          获取Document对象
                          获取对应的标签Element对象
                          获取数据
                  PULL：安卓操作系统内置解析器
     */

}
