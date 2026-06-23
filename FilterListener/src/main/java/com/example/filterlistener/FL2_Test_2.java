package com.example.filterlistener; /**
 * ClassName: ${NAME}
 * Package: ${PACKAGE_NAME}
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/17 - 18:24
 * @Version: v1.0
 */

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

// 表示此过滤器拦截user路径下的直接请求和转发请求
@WebFilter(value="/user/*",dispatcherTypes = {DispatcherType.ERROR,DispatcherType.FORWARD})
    // 可以一次性地设置多个拦截方式
public class FL2_Test_2 implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
        System.out.println("fl2拦截");
    }

    public void destroy() {
    }

}
