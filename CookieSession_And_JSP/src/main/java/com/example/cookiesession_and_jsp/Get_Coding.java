package com.example.cookiesession_and_jsp;

import java.nio.charset.Charset;

/**
 * ClassName: Get_Coding
 * Package: com.example.cookiesession_and_jsp
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/9 - 15:39
 * @Version: v1.0
 */
public class Get_Coding {

    public static void main(String[] args) {

        // 获取平台默认编码
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());

    }

}
