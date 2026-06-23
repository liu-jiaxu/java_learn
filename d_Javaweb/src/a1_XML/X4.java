package a1_XML;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * ClassName: X4
 * Package: a1_XML
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/2 - 8:42
 * @Version: v1.0
 */

// Jsoup入门
public class X4 {

    public static void main(String[] args) throws IOException {

        /*
          Jsoup步骤
              1.导入jar包：jsoup-1.11.2.jar
              2.获取Document对象
              3.获取对应的标签Element对象
              4.获取数据
         */

        // Jsoup步骤
            // (1)根据xml文档获取
                // [1]获取X4.xml的path
                String path = X4.class.getClassLoader().getResource("a1_XML/X4.xml").getPath(); // 注意路径！
                    /*
                      .class 获取字节码文件对象
                      getClassLoader() 获取类加载器
                      getResource("X4.xml") 找到对应资源位置
                      getPath() 获取字符串表示形式的路径
                     */

                // [2]解析xml文档，加载文档进内存，获取dom树 -> Document
                Document document = Jsoup.parse(new File(path), "UTF-8"); // 字符集和xml一致
                    // parse可以获取文件及设置字符集等，返回一个Document对象

                // [3]获取元素对象(和web基本一样，getElementById...)
                Elements elements = document.getElementsByTag("name");
                    // Elements继承ArrayList集合，获取其元素方法基本一致
                    System.out.println(elements.size()); // 获取集合大小

                // [4]获取指定name的element对象
                Element element1 = elements.get(0);
                Element element2 = elements.get(1);

                // [5]获取数据
                String name = element2.text();
                System.out.println(name);

                // [6]补：遍历集合元素
                for(Object item:elements) {
                    System.out.print(item+"\n");
                }
                System.out.println();

    }

    // 使用对象总结
        // 1.Jsoup：工具类，可以解析html或xml文档，返回Document
            // parse：解析html或xml文档，返回Document
                // parse(File in, String charsetName）：解析xml或html文件
                // parse(String html) :解析xml或html字符串
                // parse(URL url, int timeoutMillis) :通过网络路径获取指定的html或xml的文档对象
        // 2.Document：文档对象。代表内存中的dom树
            // 获取Element对象
                // getElementById(String id) :根据id属性值获取唯一的element对象
                // getElementsByTag(String tagName) :根据标签名称获取元素对象集合
                // getElementsByAttribute(String key) :根据属性名称获取元素对象集合
                // getElementsByAttributevalue(String key, String value) :根据对应的属性名和属性值获取元素对象集合
        // 3.Elements：元素Element对象的集合。继承ArrayList
        // 4.Element：元素对象
            // [1]获取子元素对象
                // getElementById(String id) :根据id属性值获取唯一的element对象
                // getElementsByTag(String tagName) :根据标签名称获取元素对象集合
                // getElementsByAttribute(String key) :根据属性名称获取元素对象集合
                // getElementsByAttributevalue(String key, String value) :根据对应的属性名和属性值获取元素对象集合
            // [2]获取属性值
                // string attr(String key) :根据属性名称获取属性值获取文本内容String text()
            // [3]获取文本内容
                // String html()：获取标签体的所有内容（包括字标签的字符串内容）
        // 5.Node：节点对象。
            // 是Document和Element的父类

    // 1.解析xml文档，获取dom树 -> Document
    @Test
    public void test() throws IOException {

        // [1]直接输出xml文档
            Document document = Jsoup.parse(new File(X4.class.getClassLoader()
                    .getResource("a1_XML/X4.xml").getPath()), "UTF-8");
            System.out.println(document);
        // [2]解析字符串文档
            String str = "123456"; // 此处的str替换为xml文档内容即可
            Document document1 = Jsoup.parse(str);
            System.out.println(document1);
        // [3]通过网络路径获取指定的html或xml文档对象
            URL url = new URL("https://www.baidu.com"); // 获取该网址内容，类似于爬虫
            Document document2 = Jsoup.parse(url,2000);
            System.out.println(document2);

    }

    // 2.Jsoup的Document对象
    @Test
    public void test2() throws IOException {

        // [1]获取path及Document对象
        Document document = Jsoup.parse(new File(X4.class.getClassLoader()
                .getResource("a1_XML/X4.xml").getPath()), "UTF-8");

        // [2]获取元素对象
            // {1}getElementById(String id) :根据id属性值获取唯一的element对象
                Element element = document.getElementById("itcast");
                System.out.println(element);
                System.out.println();
            // {2]getElementsByTag(String tagName) :根据标签名称获取元素对象集合
                Elements elements = document.getElementsByTag("name");
                System.out.println(elements);
                System.out.println();
            // {3}getElementsByAttribute(String key) :根据属性名称获取元素对象集合
                Elements elements1 = document.getElementsByAttribute("id");
                System.out.println(elements1);
                System.out.println();
            // {4}getElementsByAttributevalue(String key, String value):根据对应的属性名和属性值获取元素对象集合
                Elements elements2 = document.getElementsByAttributeValue("number","heima_0001");
                System.out.println(elements2);
                System.out.println();
    }

    // 3.Jsoup的Element对象
    @Test
    public void test3() throws IOException {

        Document document = Jsoup.parse(new File(X4.class.getClassLoader()
                .getResource("a1_XML/X4.xml").getPath()), "UTF-8");

        // [1]获取子元素对象
            // getElementsByAttributevalue(String key, String value) :根据对应的属性名和属性值获取元素对象集合
                // 之前通过Document获取
                    Elements elements = document.getElementsByAttributeValue("number","heima_0001");
                    System.out.println(elements);
                    System.out.println();
                // 通过Element获取
                    Element element_student = document.getElementsByTag("student").get(0);
                        // 此处仅获取第一个student元素
                    Elements elements_name = element_student.getElementsByTag("name");
                        // 获取student标签下的子元素name
                    System.out.println(elements_name);
                    System.out.println();

        // [2]获取属性值
            // string attr(String key)：根据属性名称获取属性值获取文本内容String text()
                // 获取student对象的属性值
                    String str = element_student.attr("number");
                    System.out.println(str);
                    System.out.println();

        // [3]获取文本内容
            // String text() :获取标签体的所有内容（包括字标签的字符串内容）
            // String html() :获取标签体的所有内容（不包括字标签的字符串内容）
                String text = element_student.text();
                String html = element_student.html();
                System.out.println(text);
                System.out.println();
                System.out.println(html);
                System.out.println();

    }

}
