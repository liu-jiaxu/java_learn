package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: TS9_Test_3
 * Package: com.example.servlet
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/8 - 8:55
 * @Version: v1.0
 */

// 服务器输出字节数据到浏览器
@WebServlet("/ts93")
public class TS9_Test_3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1.获取字节输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        // 2.输出数据
        outputStream.write("你好啊！".getBytes("utf-8"));
            // getBytes()：使用平台的默认字符集将其编码变换为String字节序列，并将结果存储到新的字节数组中，不用设置编码了

    }

}
