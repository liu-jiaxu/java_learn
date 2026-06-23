package com.example.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * ClassName: TS11_Test_downloadServlet
 * Package: com.example.servlet
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/8 - 16:20
 * @Version: v1.0
 */

/**
 * 案例：文件下载需求
 *     1.页面显示超链接
 *     2.点击链接后弹出下载提示框
 *     3.完成图片文件下载
 *
 * 分析：
 *     1.超链接指向的资源如果能够被浏览器解析，则在浏览器中展示，否则弹出下载提示框，不满足需求
 *     2.任何资源都必须弹出下载提示框
 *     3.使用响应头设置资源的打开方式：content-disposition:attachment;filename=xxx
 *
 * 步骤：
 *     1.定义页面，编辑超链接href属性，指向servlet，传递资源名称filename
 *     2.定义servlet
 *         (1)获取文件名称
 *         (2)使用字节输入流加载文件进内存
 *         (3)指定response的响应头：content-disposition:attachment;filename=xxx
 *         (4)将数据写出到response输出流
 *
 * 问题：中文文件名称
 *     解决思路
 *         1.获取客户端使用的浏览器信息
 *         2.根据不同的信息，设置filename编码方式不同
 */

@WebServlet("/ts11ds")
public class TS11_Test_downloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1.获取文件名称
        String filename = req.getParameter("filename");
        // 2.使用字节输入流加载文件进内存
            // (1)获取文件服务器路径
            ServletContext servletContext = this.getServletContext();
            String contextPath = servletContext.getRealPath("/img/"+filename);
            // (2)用字节流关联
            FileInputStream fis = new FileInputStream(contextPath);
            // (3)设置response响应头
                // [1]设置响应头类型content-type
                String mineType = servletContext.getMimeType(filename);
                resp.setHeader("content-type", mineType);
                // [2]设置响应头打开方式：content-disposition:attachment;filename=xxx
                resp.setHeader("content-disposition", "attachment;filename="+filename);
                // [3]解决中文文件名失效问题
                    // {1}获取user_agent请求头
                    String header = req.getHeader("user-agent");
                    // {2}使用工具类方法编码文件名即可(jdk8.0之后已经删除此工具类，此处工具类由自己编写)
                    filename = DownLoadUtils.getFileName(header, filename);
                    resp.setHeader("content-disposition", "attachment;filename="+filename);
            // (4)将输入流的数据写到输出流中
            ServletOutputStream out = resp.getOutputStream();
            byte[] buffer = new byte[1024*8];
            int len = 0;
            while((len = fis.read(buffer))!= -1){
                out.write(buffer, 0, len);
            }
            fis.close();

    }

}
