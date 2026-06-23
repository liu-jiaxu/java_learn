package test;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * ClassName: T6
 * Package: test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/9/14 - 11:03
 * @Version: v1.0
 */

public class T6 {

    @Test
    public void test() {

        FileWriter fileWriter = null;
        try {
            File file = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS.txt");
            fileWriter = new FileWriter(file);
            char[] arr = {'h', 'e', 'l', 'l', 'o'};
            fileWriter.write(arr, 0, arr.length);
            System.out.println("success!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test2() {

        FileReader fileReader = null;
        try {
            File file = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS.txt");
            fileReader = new FileReader(file);
            char[] buffer = new char[5];
            int data = fileReader.read(buffer);
            while (data != -1) {
                for (int i = 0; i < data; i++) {
                    System.out.print(buffer[i]);
                }
                data = fileReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
