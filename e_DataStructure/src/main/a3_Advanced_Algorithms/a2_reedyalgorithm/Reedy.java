package main.a3_Advanced_Algorithms.a2_reedyalgorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * ClassName: Reedy
 * Package: main.a3_Advanced_Algorithms.a2_reedyalgorithm
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/9/2 - 16:47
 * @Version: v1.0
 */

public class Reedy {

    /*
    贪心算法
        1.将寻找最优解的问题分为若干个步骤
        2.每一步骤都采用贪心原则，选取当前最优解
        3.因为没有考虑所有可能，局部最优的堆叠**不一定**让最终解最优
     */

    /*
    零钱兑换问题
        给定一个整数金额，一个数组存储不同金额的货币，使用这些货币凑出给定的总数金额
            1.计算有多少凑法
            2.计算哪种凑法使得硬币数最少
     */

    // 1.递归
    public int change(int[] coins, int amount) {
        return rec(0, coins, amount, new LinkedList<>(), true);
    }

    /**
     * 求凑成剩余金额的解的个数
     *
     * @param index     当前硬币索引
     * @param coins     硬币面值数组
     * @param remainder 剩余金额
     * @param stack     -
     * @param first     -
     * @return 解的个数
     */
    public int rec(int index, int[] coins, int remainder, LinkedList<Integer> stack, boolean first) {
        if(!first) {
            stack.push(coins[index]);
        }
        // 情况1：剩余金额 < 0 - 无解
        int count = 0;
        if (remainder < 0) {
            print("无解：", stack);
        }
        // 情况2：剩余金额 == 0 - 有解
        else if (remainder == 0) {
            print("有解：", stack);
            count = 1;
        }
        // 情况3：剩余金额 > 0 - 继续递归
        else {
            for (int i = index; i < coins.length; i++) {
                count += rec(i, coins, remainder - coins[i], stack, false);
            }
        }
        if (!stack.isEmpty()) {
            stack.pop();
        }
        return count;
    }

    private static void print(String prompt, LinkedList<Integer> stack) {
        ArrayList<Integer> print = new ArrayList<>();
        ListIterator<Integer> iterator = stack.listIterator(stack.size());
        while (iterator.hasPrevious()) {
            print.add(iterator.previous());
        }
        System.out.println(prompt + print);
    }

    @Test
    public void test() {

        // 1.递归
        Reedy reedy = new Reedy();
        //        int count = leetcode.coinChange(new int[]{1, 5, 10, 25}, 41);
        //        int count = leetcode.coinChange(new int[]{25, 10, 5, 1}, 41);
        //        int count = leetcode.coinChange(new int[]{5, 2, 1}, 5);
        //        int count = leetcode.coinChange(new int[]{1, 2, 5}, 5);
        int count = reedy.change(new int[]{15, 10, 1}, 21);
        System.out.println(count);

    }



    // 2.贪心：速度快，但可能得到错误解！
    public int coinChange(int[] coins, int amount) {
        // 每次循环都取最优解
        int remainder = amount;
        int count = 0;
        // 假定数组金额由大到小排序，否则需要先排序
        for (int coin: coins) {
            // 此处不用remainder >= coin是因为满足条件退出循环时，退出的是while循环而不是for，因此需要在while外判断！
            while (remainder > coin) {
                remainder -= coin;
                count ++;
            }
            if (remainder == coin) {
                remainder = 0;
                count ++;
                break;
            }
        }
        if (remainder > 0) {
            return -1;
        } else {
            return count;
        }
    }

    @Test
    public void test2() {

        // 1.递归
        Reedy reedy = new Reedy();
        //        int count = leetcode.coinChange(new int[]{1, 5, 10, 25}, 41);
        //        int count = leetcode.coinChange(new int[]{25, 10, 5, 1}, 41);
        //        int count = leetcode.coinChange(new int[]{5, 2, 1}, 5);
        //        int count = leetcode.coinChange(new int[]{1, 2, 5}, 5);
        int count = reedy.coinChange(new int[]{15, 10, 1}, 21);
        System.out.println(count); // 7：不是3！贪心算法逐步选取最优解导致错误！

    }

}
