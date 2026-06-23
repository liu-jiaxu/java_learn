package test;

import java.util.Random;
import java.util.Scanner;

/**
 * ClassName: BombGame
 * Package: a1_Basics.test
 * Description:猜炸弹数
 *
 * @Author: 青理工 通信201 刘家旭
 * @Create: 2023/9/5 - 19:43
 * @Version: v1.0
 */

public class BombGame {

    /**
     * <h3>炸弹数</h3>
     * <br>随机定义一个1-100之间的整数（炸弹数），当猜的数==炸弹数时，提示”恭喜你，被猜对了！“，若输入的数不等于炸弹数，继续猜，直到猜中为止，游戏结束。
     * <br>
     * <br><h3>扩展</h3>
     * <br>每次猜完，再次猜之前，给出范围提示。如炸弹数是34。
     * <br>第一次猜的数是50，
     * <br>提示信息”请猜1-50之间的数“
     * <br>第二次猜的数是28，
     * <br>提示信息”请猜28-50之间的数“
     * <br>第三次猜的数是46，
     * <br>提示信息”请猜28-36之间的数
     * <br>以此类推，最后直到猜中炸弹数，游戏结束
     * <br>提示：Random类生成随机数，循环，循环次数不定
     * <br>
     * <br><h3>补充修改</h3>
     * <br>可以自定义猜数次数与炸弹数所在范围
     * @param start 炸弹数起始范围(包含)
     * @param end 炸弹数截止范围(包含)
     * @param count 用户可以猜数的次数
     */
    public static void bombGame(int start, int end, int count) {

        // 记录原始次数，便于最终记录猜数所用次数
        int degree = count;

        Random random = new Random();
        // 定义随机数
        int number = random.nextInt(end - start + 1) + start;
        System.out.println("请猜炸弹数，范围[" + start + " ," + end + "]，共" + count + "次机会：");

        // 循环判断
        // 若猜数次数无限则此处设置为while(true)即可
        while (count > 0) {
            // 用户输入猜的炸弹数
            int guess = new Scanner(System.in).nextInt();
            // 优先设置循环结束条件：次数用完时的情况。防止次数为0时仍输出提示语句！
            if (count == 1) {
                System.out.println("您的次数用完了，本次猜的数为：" + number);
                break;
            }
            // 提示用户非法注入范围外的数字！
            if (guess >= end || guess <= start) {
                count --;
                System.out.println("不要猜范围之外的数字哟！");
                System.out.println("你还有" + count  + "次机会！");
                continue;
            }
            // 以下是猜数的三种情况
            if (guess > number) {
                // 优先设置count--，使得提示语句中的count变量不用-1再提示了！
                count --;
                System.out.println("你还有" + count + "次机会！");
                System.out.println("请猜" + start + "到" + guess + "之间的数！");
                /*
                    注意此处要更改start和end的范围，以便提示猜数信息。
                    在第一次更改边界范围只需缩小边界到所猜数字，但之后的更改要修改边界！
                 */
                end = guess;
            } else if (guess < number) {
                count --;
                System.out.println("你还有" + count + "次机会！");
                System.out.println("请猜" + guess + "到" + end + "之间的数！");
                start = guess;
            } else {
                // 用户猜对提示信息
                // 注：最后一次猜数也要算到猜数总次数中，因此仍需count--
                count --;
                System.out.println("恭喜你猜对了，本次猜的数为" + number + "，共用" + (degree - count) + "次！");
                break;
            }
        }

    }

    // 简洁版(原始版本)
    public static void bomb() {

        Random random = new Random();
        int start = 1;
        int end = 100;
        int number = random.nextInt(end - start + 1) + start;
        System.out.println("请猜炸弹数");

        while (true) {
            int guess = new Scanner(System.in).nextInt();
            if (guess >= end || guess <= start) {
                System.out.println("不要猜范围之外的数字哟！");
                continue;
            }
            if (guess > number) {
                System.out.println("请猜" + start + "到" + guess + "之间的数！");
                end = guess;
            } else if (guess < number) {
                System.out.println("请猜" + guess + "到" + end + "之间的数！");
                start = guess;
            } else {
                System.out.println("恭喜你猜对了，本次猜的数为" + number);
                break;
            }
        }

    }

    public static void main(String[] args) {

        // 猜数测试
        bombGame(-20, 200, 20);

        /*
        知识点总结：
            1.使用Random类进行随机数的生成
            2.Scanner输入类的使用
                例：获取一个[START, END]范围的随机整数
                    int START = 1, END = 100;
                    Random random = new Random();
                    int number = random.nextInt(END - START +  1) + START;
            3.if-else语句的使用
            4.while循环的使用
            5.函数定义与调用
        思维：
            1.优先读懂题目要求，依次理清需要设置的变量和先后要进行的操作，有大体的解决思路
            2.使用Random类获取随机数
            3.重点：结合if-else判断和while循环进行解题
                (1)外部设置死循环，用于多次匹配用户输入数字和炸弹数
                (2)循环内部优先获取用户输入的数字
                (3)使用if-else判断进行三种情况的判断，在判断内部执行对应操作
            4.主函数测试程序
            5.优化
                (1)在用户输入和猜数后均使用输出语句提示信息，增加用户体验感
                (2)在函数参数列表中增加炸弹数范围及猜数次数，增加用户选择
                (3)添加猜数次数后，配置相应变量count记录，更改while死循环为猜数次数，当猜错时count自减，
            最终猜对时返回所用次数，并设置循环结束条件为次数用完
                (4)为防止用户不慎错误输入范围外的数字，在while中新增if条件判断
                (5)主函数测试程序
         */

    }

}
