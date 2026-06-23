package com.example.ajax_and_json;

/**
 * ClassName: AJ3_Test
 * Package: com.example.ajax_and_json
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/23 - 15:39
 * @Version: v1.0
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * 一.JSON
 *     3.JSON数据和Java对象的相互转换
 *         (1)JSON -> Java(了解)
 *             步骤：
 *                 [1]导入jackson相关jar包
 *                 [2]创建Jackson核心对象ObjectMapper
 *                 [3]调用ObjectMapper的相关方法进行转换
 *                     方法：
 *                         {1}readValue(josn字符串数据,Class)
 *         (2)Java -> JSON
 *             步骤：
 *                 [1]导入jackson相关jar包
 *                 [2]创建Jackson核心对象ObjectMapper
 *                 [3]调用ObjectMapper的相关方法进行转换
 *                     方法：
 *                         {1}writeValue(参数1,obj)：
 *                             参数1：
 *                                 File：将obj对象转换为json字符串，并保存在指定文件中
 *                                 Writer：将obj对象转换为json字符串，并保存在指定字符输出流中
 *                                 OutputStream：将obj对象转换为json字符串，并保存在指定字节输出流中
 *                         {2}writeValueAsString(obj)：将对象转为json字符串
 *         (3)解析器
 *             [1]常见解析器：Jsonlib、Gson、fastjson、jackson
 *     4.注解
 *         (1)@JsonIgnore：排除(忽略)属性
 *         (2)@JsonFormat(pattern = "")：属性值格式化
 *     5.JSON -> Java集合
 *         (1)List：数组
 *         (2)Map：和JSON对象格式一致
 */

public class AJ3_Test {

    // Java -> JSON
    @Test
    public void test() throws IOException {

        // 1.创建Person对象
        AJ3_Person person = new AJ3_Person();
        person.setName("张三");
        person.setAge(19);
        person.setGender("male");
        person.setBirthDate(new Date()); // 注意注解！
        // 2.创建Jackson的核心对象
        ObjectMapper mapper = new ObjectMapper();
        // 3.转换
            /*
              方法：
                  1.writeValue(参数1,obj)：
                      参数1：
                          File：将obj对象转换为json字符串，并保存在指定文件中
                          Writer：将obj对象转换为json字符串，并保存在指定字符输出流中
                          OutputStream：将obj对象转换为json字符串，并保存在指定字节输出流中
                  2.writeValueAsString(obj)：将对象转为json字符串
             */
        String json = mapper.writeValueAsString(person);
        System.out.println(json);

        mapper.writeValue(new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\AJAX_And_JSON\\src\\main\\resources\\aj3_1.txt"),person);
        mapper.writeValue(new FileWriter("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\AJAX_And_JSON\\src\\main\\resources\\aj3_2.txt"),person);

    }

    // JSON -> Java集合List
    @Test
    public void test2() throws JsonProcessingException {

        // 1.创建Person对象
        AJ3_Person person = new AJ3_Person();
        person.setName("张三");
        person.setAge(19);
        person.setGender("male");
        person.setBirthDate(new Date());
        AJ3_Person person2 = new AJ3_Person();
        person2.setName("张三");
        person2.setAge(19);
        person2.setGender("male");
        person2.setBirthDate(new Date());
        AJ3_Person person3 = new AJ3_Person();
        person3.setName("张三");
        person3.setAge(19);
        person3.setGender("male");
        person3.setBirthDate(new Date());
        // 2.创建List集合
        List<AJ3_Person> list = new ArrayList<AJ3_Person>();
        list.add(person);
        list.add(person2);
        list.add(person3);
        // 3.创建Jackson的核心对象
        ObjectMapper mapper = new ObjectMapper();
        // 4.转换
        String json = mapper.writeValueAsString(list);
        System.out.println(json);

    }

    // JSON -> Java集合Map
    @Test
    public void test3() throws JsonProcessingException {

        // 1.创建Person对象
        AJ3_Person person = new AJ3_Person();
        person.setName("张三");
        person.setAge(19);
        person.setGender("male");
        person.setBirthDate(new Date());
        AJ3_Person person2 = new AJ3_Person();
        person2.setName("张三");
        person2.setAge(19);
        person2.setGender("male");
        person2.setBirthDate(new Date());
        AJ3_Person person3 = new AJ3_Person();
        person3.setName("张三");
        person3.setAge(19);
        person3.setGender("male");
        person3.setBirthDate(new Date());
        // 2.创建List集合
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("person", person);
        map.put("person2", person2);
        map.put("person3", person3);
        // 3.创建Jackson的核心对象
        ObjectMapper mapper = new ObjectMapper();
        // 4.转换
        String json = mapper.writeValueAsString(map);
        System.out.println(json);

    }

    // JSON -> Java
    @Test
    public void test4() throws JsonProcessingException {

        // 1.初始化JSON字符串
        String json = "{\"name\": \"John\",\"age\":\"20\",\"gender\":\"male\",\"birthDate\":\"1992-12-22\"}";
            // 复制json语句即可自动添加转义字符\
        // 2.创建ObjectMapper对象
        ObjectMapper mapper = new ObjectMapper();
        AJ3_Person person = mapper.readValue(json, AJ3_Person.class);
        System.out.println(person);

    }

}
