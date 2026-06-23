package cn.itcast.web.filter;
/**
 * ClassName: ${NAME}
 * Package: ${PACKAGE_NAME}
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/17 - 20:15
 * @Version: v1.0
 */

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {

    // 定义集合放置敏感词汇
    List<String> list = new ArrayList<String>();

    public void init(FilterConfig config) {

        try {
            // 1.加载文件，获取文件真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("敏感词汇.txt");
                // 以后规定所有getRealPath获取的文件都直接放到webapp文件下！
            // File realPath = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\CookieSession_And_JSP_Test\\src\\main\\resources\\敏感词汇.txt");
                // 用文件获取位置传入也可
            // 2.读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            // 3.将文件每一行数据添加到list中
            String line = null;
            while((line = br.readLine()) != null){
                list.add(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 1.创建代理对象，增强getParameter方法
        /*
          三个参数：
              1.类加载器：真实对象.getClass().getClassLoader()
              2.接口数组：真实对象.getClass().getInterface()
              3.处理器：new InvocationHandIer()
         */
        ServletRequest servletRequest = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 代理逻辑编写方法：代理对象调用的所有方法都会触发该方法执行
             *     1.proxy：代理对象
             *     2.method：代理对象调用的方法，被封装的对象
             *     3.args：代理对象调用方法传递的参数列表，存储在Object数组中
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 判断是否是getParameter方法
                if(method.getName().equals("getParameter")){
                    // 增强返回值
                    // 获取返回值
                    String value = (String) method.invoke(request, args);
                    if(value != null){
                        for(String str:list){
                            if(value.contains(str)){
                                value = value.replaceAll(str, "**");
                            }
                        }
                    }
                    return value;
                } else {
                    return method.invoke(request,args);
                }
            }
        });

        // 2.放行
        chain.doFilter(servletRequest, response);
    }

    public void destroy() {
    }

}
