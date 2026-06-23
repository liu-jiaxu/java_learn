package com.example.ajax_and_json;

/**
 * ClassName: AJ4_Test
 * Package: com.example.ajax_and_json
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/23 - 16:55
 * @Version: v1.0
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 案例：校验用户名是否存在
 *    分析：
 *        当用户名输入失焦后，发送ajax请求，查询数据库是否有该用户名，若存在则提示用户名已存在，若不存在提示信息可用
 */

@WebServlet("/aj4")
public class AJ4_Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json;character=utf-8"); // 在发送请求时设置格式为json，并防止中文乱码

        // 1.获取用户名
        String username = req.getParameter("username");
        // 2.调用service层判断用户名是否存在
        Map<String, Object> map = new HashMap<String, Object>();
        if("tom".equals(username)){ // 此处简化未查询数据库！
            // 存在
            map.put("userExist", true);
            map.put("msg","用户存在");
        } else {
            // 不存在
            map.put("userExist", false);
            map.put("msg","信息可用");
        }
        // 3.将map转为json，并且传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),map);

    }
}
