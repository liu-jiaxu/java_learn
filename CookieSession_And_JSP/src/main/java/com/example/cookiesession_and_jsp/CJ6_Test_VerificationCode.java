package com.example.cookiesession_and_jsp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * ClassName: TS9_Test_4
 * Package: com.example.servlet
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/8 - 8:55
 * @Version: v1.0
 */

// 验证码
@WebServlet("/cj6vc")
public class CJ6_Test_VerificationCode extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1.创建对象，在内存中获取图片(验证码)
        int height = 50; // 宽
        int width = 100; // 长
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // 参数：宽，长，图片类型：三原色RGB

        // 2.美化图片(调用特定方法，了解即可，用到百度)
            // (1)创建画笔对象
            Graphics graphics = image.getGraphics();
            // (2)填充背景色(粉)
            graphics.setColor(Color.pink); // 选一个颜色，只要不修改，下面的代码均对应这个颜色
            // (3)填充粉色到矩形
            graphics.fillRect(0,0,width,height); // 参数：四个矩形的点
            // (4)画边框
            graphics.setColor(Color.BLUE); // 设置边框颜色和验证码颜色
            graphics.drawRect(0,0,width-1,height-1); // 参数：四个矩形的点
            // (5)写验证码(随机从指定字符串中获取四个字符)
            String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(); // 创建StringBuilder对象用于保存随机生成的字符
            for(int i = 1; i <= 4; i++) {
                int index = random.nextInt(str.length()); // 生成随机角标
                char c = str.charAt(index); // 获取随机字符
                sb.append(c); // 将获取的字符加入StringBuilder中
                graphics.drawString(c+"",width/5*i,height/2); // 写验证码
            }
            String checkCode_session = sb.toString();
            req.getSession().setAttribute("checkCode_session", checkCode_session); // 将验证码存入session
        // (6)画干扰线：随机画多条干扰线(设置起始结束点，并循环10次)
            graphics.setColor(Color.green);
            for (int i = 0; i < 10; i++) {
                int x1 = random.nextInt(width);
                int x2 = random.nextInt(width);
                int y1 = random.nextInt(width);
                int y2 = random.nextInt(width);
                graphics.drawLine(x1,x2,y1,y2); // 从x1划到x2，从y1划到y2
            }

        // 3.将图片输出到网页上
        ImageIO.write(image, "jpg", resp.getOutputStream());
            // 参数：图片流，图片起名，输出流

    }
}
