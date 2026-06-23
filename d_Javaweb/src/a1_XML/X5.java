package a1_XML;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: X5
 * Package: a1_XML
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/2 - 10:45
 * @Version: v1.0
 */
public class X5 {

    /**
     * 快捷查询方式
     *     1.selector：选择器
     *         使用方法：Elements  select(String cssQuery)
     *     2.XPath：XPath是一门在XML文档中查找信息的语言。
     *         使用时需要导入额外jar包：JsoupXpath-0.3.2.jar
     *         需要查询w3school参考手册语法：https://www.w3school.com.cn/xpath/xpath_syntax.asp
     */

    // 1.selector：选择器
    @Test
    public void test() throws IOException {

        // [1]获取path及Document对象
        Document document = Jsoup.parse(new File(X4.class.getClassLoader()
                .getResource("a1_XML/X5.xml").getPath()), "UTF-8");

        // [2]查询name标签
        Elements elements = document.select("name");
        System.out.println(elements);
        System.out.println();

        // [3]查询id值为itcast的元素
        Elements elements1 = document.select("#itcast");
        System.out.println(elements1);
        System.out.println();

        // [4]获取student标签且number属性值为heima_0001的age标签
        Elements elements2 = document.select("student[number=\"heima_0001\"] > age");
            // 此处的选择器语法复习web
        System.out.println(elements2);
        System.out.println();

    }

    // 2.XPath
    @Test
    public void test2() throws IOException, XpathSyntaxErrorException {

        // [1]获取path及Document对象
        Document document = Jsoup.parse(new File(X4.class.getClassLoader()
                .getResource("a1_XML/X5.xml").getPath()), "UTF-8");

        // [2]创建JXDocument对象
        JXDocument jxDocument = new JXDocument(document);

        // [3]结合xpath语法查询
            // {1}查询所有student标签
            List<JXNode> jxNodes = jxDocument.selN("//student");
            for(JXNode jxNode : jxNodes){
                System.out.println(jxNode);
            }
            System.out.println();
            // {2}查询所有student标签下的name标签
            List<JXNode> jxNodes2 = jxDocument.selN("//student//name");
            for(JXNode jxNode : jxNodes2){
                System.out.println(jxNode);
            }
            System.out.println();
            // {3}查询所有student标签下带有id属性的name标签
            List<JXNode> jxNodes3 = jxDocument.selN("//student//name[@id]");
            for(JXNode jxNode : jxNodes3){
                System.out.println(jxNode);
            }
            System.out.println();
            // {3}查询所有student标签下带有id属性的name标签，且属性值为itcast2
            List<JXNode> jxNodes4 = jxDocument.selN("//student//name[@id='itcast2']");
            for(JXNode jxNode : jxNodes4){
                System.out.println(jxNode);
            }
            System.out.println();

    }

}
