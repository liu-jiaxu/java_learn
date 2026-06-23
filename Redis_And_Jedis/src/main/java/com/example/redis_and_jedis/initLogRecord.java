package com.example.redis_and_jedis;

/**
 * ClassName: q
 * Package: com.example.redis_and_jedis
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/27 - 16:53
 * @Version: v1.0
 */

import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// log4j报错时使用！
public class initLogRecord {
    public static void initLog() {
        FileInputStream fileInputStream = null;
        try {
            Properties properties = new Properties();
            fileInputStream = new FileInputStream("src/main/resources/log4j.properties");
            properties.load(fileInputStream);
            PropertyConfigurator.configure(properties);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
