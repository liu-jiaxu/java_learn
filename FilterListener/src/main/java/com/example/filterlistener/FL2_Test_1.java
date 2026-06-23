package com.example.filterlistener; /**
 * ClassName: ${NAME}
 * Package: ${PACKAGE_NAME}
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/17 - 11:13
 * @Version: v1.0
 */

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

// 系统自动生成的Filter
// 表示此过滤器仅拦截user路径下的直接请求
@WebFilter(value="/user/*",dispatcherTypes = DispatcherType.REQUEST)
public class FL2_Test_1 implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
        System.out.println("fl1拦截");
    }

    public void destroy() {
    }

}
