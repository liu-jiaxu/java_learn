package test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ClassName: T4
 * Package: a1_Basics.test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/9/8 - 11:08
 * @Version: v1.0
 */

public class T4 {
    
    // 2.大乐透
    @Test
    public void test() {

        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(99) + 1;
        }
        System.out.println("大乐透号码：");
        for (int j : arr) {
            System.out.print(j + " ");
        }

    }

    // 3.扑克牌
    @Test
    public void test2() {

        String[] a = {"黑桃", "红桃", "梅花", "方块"};
        String[] b = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] poker = new String[54];
        /*for (int i = 0; i < 13; i++) {
            poker[i] = a[0] + b[i];
        }
        for (int i = 0; i < 13; i++) {
            poker[i + b.length] = a[1] + b[i];
        }
        for (int i = 0; i < 13; i++) {
            poker[i + b.length * 2] = a[2] + b[i];
        }
        for (int i = 0; i < 13; i++) {
            poker[i + b.length * 3] = a[3] + b[i];
        }*/
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                poker[j + b.length * i] =  a[i] + b[j];
            }
        }
        poker[52] = "小王";
        poker[53] = "大王";

        for (int i = 0; i < poker.length; i++) {
            if (i == 0 || i == 4 || i == 49) {
                System.out.print(poker[i] + "");
            }
        }

    }

    // 4.统计字符
    // a - 97 / z - 122
    @Test
    public void test3() {

        char[] a = {'a', 'l', 'f', 'm', 'f', 'o', 'b', 'b', 's', 'n'};
        int[] count = new int[26];
        for (char c : a) {
            count[c - 97]++;
        }
        for (int i = 0; i < count.length; i++) {
            System.out.println((char) (i + 97) + "出现的次数：" + count[i]);
        }

    }

}

// 1.SUV
class Auto {
    private String brand;
    private int length;
    private int price;

    public Auto() {
    }

    public Auto(String brand, int length, int price) {
        this.brand = brand;
        this.length = length;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "brand='" + brand + '\'' +
                ", length=" + length +
                ", price=" + price +
                '}';
    }

}

class SUV extends Auto {

    public SUV() {
        super();
    }

    public SUV(String brand, int length, int price) {
        super(brand, length, price);
    }

    public void show() {
        if (getLength() < 4295) {
            System.out.println("小型车：" + getBrand() + "\n\t" + "价格:" + getPrice() + "\n\t" + "车长:" + getLength());
        } else if(getLength() > 5070) {
            System.out.println("大型车：" + getBrand() + "\n\t" + "价格:" + getPrice() + "\n\t" + "车长:" + getLength());
        } else {
            System.out.println("中型车：" + getBrand() + "\n\t" + "价格:" + getPrice() + "\n\t" + "车长:" + getLength());
        }
    }

}

class TestSUV {

    @Test
    public void test() {

        List<SUV> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            SUV suv = new SUV("SUV", (rand.nextInt(2001) + 4000), rand.nextInt(100000) + 200000);
            list.add(suv);
        }

        for(SUV car : list) {
            if (car.getLength() > 4295 && car.getLength() < 5070) {
                car.show();
            }
        }

    }

}
