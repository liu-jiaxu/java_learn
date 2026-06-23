package a9_IOStream;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IOS6 {

    //例：图片解密
    @Test
    public void test() throws IOException {

        BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS6\\IOS6.jpg").toPath()));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS6\\IOS61(加密).jpg")));

        byte[] buffer = new byte[1024];
        int data = bufferedInputStream.read(buffer);
        while (data != -1) {

            //不可以用foreach修改，因为foreach只是修改了另外的对象，而没有修改原始的数组元素！
            for (int i = 0; i < data; i++) {
                buffer[i] = (byte) (buffer[i] ^ 5); //修改了存放在数组buffer中文件
            }

            bufferedOutputStream.write(buffer);
            data = bufferedInputStream.read(buffer);
        }
        System.out.println("succeed!");

        bufferedInputStream.close();
        bufferedOutputStream.close();

    }

    //例：图片解密
    @Test
    public void test2() throws IOException {

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS6\\IOS61(加密).jpg")));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS6\\IOS62(加密).jpg")));

        byte[] buffer = new byte[1024];
        int data = bufferedInputStream.read(buffer);
        while (data != -1) {

            //不可以用foreach修改，因为foreach只是修改了另外的对象，而没有修改原始的数组元素！
            for (int i = 0; i < data; i++) {
                buffer[i] = (byte) (buffer[i] ^ 5); //两次取^等于本身，相当于解密了
            }

            bufferedOutputStream.write(buffer);
            data = bufferedInputStream.read(buffer);
        }
        System.out.println("succeed!");

        bufferedInputStream.close();
        bufferedOutputStream.close();

    }

    //例：获取文本每个字符出现的次数（遍历文本每个字符，出现次数保存在Map中，再将Map数据写入文件）
    @Test
    public void test3() {

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS6\\IOS63.txt")));
            bufferedWriter = new BufferedWriter(new FileWriter(new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS6\\IOS64.txt")));
            Map<Character, Integer> map = new HashMap<>(); //Map对应的键值刚好可以表示字符和出现次数
            int data = bufferedReader.read();
            //注：此处不可以再用数组读入了，因为一旦使用数组再读入的就是一个字符串了，char类型不识别，而且也不会统计每个字符了！
            while (data != -1) {
                char ch = (char) data;
                //map.get(ch)返回对应value值，若没有返回null
				/*map.merge(ch, 1, Integer::sum);相当于下面的if-else操作*/
                if (map.get(ch) == null) {
                    map.put(ch, 1);
                } else {
                    map.put(ch, map.get(ch) + 1);
                }
                data = bufferedReader.read();
            }
            Set<Map.Entry<Character, Integer>> entryset = map.entrySet(); //Map返回Set类型的集合
            for (Map.Entry<Character, Integer> entry : entryset) { //用foreach遍历集合Set数据
                switch (entry.getKey()) {
                    case ' ':
                        bufferedWriter.write("空格=" + entry.getValue());
                        break;
                    case '\t':
                        bufferedWriter.write("tab键=" + entry.getValue());
                        break;
                    case '\r':
                        bufferedWriter.write("回车=" + entry.getValue());
                        break;
                    case '\n':
                        bufferedWriter.write("换行=" + entry.getValue());
                        break;
                    default:
                        bufferedWriter.write(entry.getKey() + "=" + entry.getValue());
                        break;
                }
                bufferedWriter.newLine(); //添加换行便于观看
            }
            System.out.println("succeed!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
