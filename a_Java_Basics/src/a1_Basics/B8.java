package a1_Basics;

public class B8 {

    public static void main(String[] args) {
		
		/*
		数据结构
			线性表：顺序表（数组）、链表、栈、队列
			树形结构：二叉树
			图形结构：图
		算法
			排序：
				选择排序：直接选择、堆
				交换排序：冒泡、快速（重要）
				插入排序：直接插入、折半插入、希尔
				归并排序
				桶式排序
				基数排序
			搜索
		 */


        //数组中的算法
        //例1：二维数组元素随机赋值，求最大值、最小值、平均值、求和、转置输出
        int[][] arr9 = new int[4][4];
        System.out.println("原二维数组：");
        for (int i = 0; i < arr9.length; i++) {
            for (int j = 0; j < arr9[i].length; j++) {
                arr9[i][j] = (int) (Math.random() * 90 + 10);
                System.out.print(arr9[i][j] + " ");
            }
            System.out.println();
        }
        int max = arr9[0][0], min = arr9[0][0], sum = 0;
        double av;
        for (int i = 0; i < arr9.length; i++) {
            for (int j = 0; j < arr9[i].length; j++) {
                if (max < arr9[i][j]) {
                    max = arr9[i][j];
                }
                if (min > arr9[i][j]) {
                    min = arr9[i][j];
                }
                sum += arr9[i][j];
            }
        }
        av = 1.0 * sum / (arr9.length * arr9[0].length); //二维数组满元素情况下长度=arr.length*arr[0].length
        System.out.print("max:" + max + "\nmin:" + min + "\nav:" + av + "\nsum:" + sum + "\n");
        System.out.println("转置后二维数组：");
        int[][] arr10 = new int[4][4];
        for (int i = 0; i < arr9.length; i++) {
            for (int j = 0; j < arr9[i].length; j++) {
                arr10[j][i] = arr9[i][j];
            }
        }
        for (int i = 0; i < arr10.length; i++) {
            for (int j = 0; j < arr10[i].length; j++) {
                System.out.print(arr10[i][j] + " ");
            }
            System.out.println();
        }

        //长宽不同的二维数组转置示例
        int[][] aa = new int[][]{{1, 2}, {3, 4}, {5, 6}};
        int[][] bb = new int[aa[0].length][aa.length];
        for (int i = 0; i < aa.length; i++) {
            for (int j = 0; j < aa[i].length; j++) {
                bb[j][i] = aa[i][j];
            }
        }
        for (int i = 0; i < bb.length; i++) {
            for (int j = 0; j < bb[i].length; j++) {
                System.out.print(bb[i][j] + " ");
            }
            System.out.println();
        }
        /*增强for循环
        for (int[] ints : bb) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }*/

        //例2：将一维数组分别复制、反转、查找、从大到小排序
        double[] arr11 = new double[]{31.1, 12.32, 55.6, 71, 27.9, 12, 0.1, 1.1, 18.9, 22.1, 89.1};
        //复制
        System.out.print("复制后的数组：");
        double[] arr12 = new double[arr11.length];
        for (int i = 0; i < arr11.length; i++) {
            arr12[i] = arr11[i];
            System.out.print(arr12[i] + " ");
        } //注：arr11!=arr12，虽然元素相同，但两个数组不是同一个对象！
        System.out.println();
        //反转
        System.out.print("反转后的数组：");
        double arr13[] = new double[arr11.length];
        for (int i = 0; i < arr11.length; i++) {
            arr13[i] = arr11[arr11.length - i - 1];
            System.out.print(arr13[i] + " ");
        }
        System.out.println();
        //线性查找
        double d1 = 55.6;
        System.out.print(d1 + "的查找结果：");
        for (int i = 0; i < arr11.length; i++) {
            if (d1 == arr11[i]) { // 注；若为引用数据类型用d1.equals(arr[i])判断！
                System.out.println("find!索引为：" + i);
                break;
            } else if (d1 != arr11[i] && i == arr11.length - 1) { //注意要加上遍历完成的条件，不然每执行一次未找到就会输出一次！
                System.out.println("not find!");
            }
        }
        //冒泡排序O(n^2)
        System.out.print("冒泡排序后的数组：");
        for (int i = 0; i < arr11.length - 1; i++) {
            for (int j = 0; j < arr11.length - i - 1; j++) {
                if (arr11[j] > arr11[j + 1]) {
                    double temp = arr11[j];
                    arr11[j] = arr11[j + 1];
                    arr11[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < arr11.length; i++) {
            System.out.print(arr11[i] + " ");
        }
        System.out.println();
        //补充：二维数组排序的话要新建一维数组获取其元素再排序！
        //二分法查找(数组要正序排列，速度快)
        double d2 = 71.1;
        System.out.print(d2 + "二分法查找结果：");
        int head = 0, end = arr11.length - 1;
        boolean b1 = true;
        while (head <= end) {
            int middle = (head + end) / 2;
            if (d2 == arr11[middle]) {
                System.out.println("find!索引为：" + middle);
                b1 = false;
                break;
            } else if (d2 > arr11[middle]) {
                head = middle + 1;
            } else if (d2 < arr11[middle]) {
                end = middle - 1;
            }
        }
        if (b1) {
            System.out.println("not find!");
        }

    }

}
