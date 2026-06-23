package com.example.redis_and_jedis; /**
 * ClassName: ${NAME}
 * Package: ${PACKAGE_NAME}
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/27 - 11:26
 * @Version: v1.0
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/rj4ps")
public class RJ4_Test_ProvinceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // initLogRecord.initLog(); // log4j报错时使用！

        /*// 1.调用service查询
        RJ4_Test_Service service = new RJ4_Test_ServiceImpl();
        List<RJ4_Test_Province> list = service.findAll();
        // 2.序列化list为Json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        // 3.响应结果
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(json);
        System.out.println(json);*/

        // 使用redis测试
        RJ4_Test_Service service2 = new RJ4_Test_ServiceImpl();
        String json2 = service2.findAllJson();
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(json2);
        System.out.println(json2);

    }

}
