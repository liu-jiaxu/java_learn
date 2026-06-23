package a1_Basics;


public class B7 {

    public static void main(String[] args) {

		/*
		数组
			1.有序排列，开辟内存空间连续
			2.引用数据类型，元素数据类型随意
			3.长度确定后无法修改，注意越界问题
		 */

		
		
		/*
		一维数组
			1.声明初始化
			2.长度、遍历
			3.数组初始化值和内存解析
		 */

        //初始化
        //第一个[]永远为空，初始化时元素赋值就不要写数组长度了，反之写了就不要赋值元素了
        int[] arr = {}; // 动态数组（未定义长度，长度随使用时改变，减少内存占用）
        int[] arr1 = new int[]{1, 2, 3, 4, 5}; //静态数组
        int[] arr2 = new int[3]; //静态数组（定义了长度）
        arr2[0] = 1;
        arr2[1] = 2;
        arr2[2] = 3;

        //长度与遍历
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + ",");
        }
        System.out.println();

        //初始化值
        //基本数据类型初始化值为0/0.0（char为空一个格，相当于0而不是真正的空格；boolean为false），引用数据类型（包括二维数组）初始化值为null
        double[] arr3 = new double[9];
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " "); //0.0
        }
        String[] arr4 = new String[9];
        for (int i = 0; i < arr4.length; i++) {
            System.out.print(arr4[i] + " "); //null
        }

        //内存解析
        //局部变量（main内的变量）存放在栈，全局变量、数组、对象存放在堆
        //引用数据类型会改变值
        //堆中的数组元素没有指针指向时自动垃圾回收，当执行完main函数后所有数组全部回收
        System.out.println();


        //二维数组

        //初始化
        int[][] arr5 = new int[][]{{1, 2, 3}, {3, 4}, {9, 0, 6}}; //静态初始化
        int[][] arr6 = {{1, 2, 3}, {3, 4}, {9, 0, 6}}; //类型推断（arr5!=arr6，因为不是同一对象！）
        int[][] arr7 = new int[3][4]; //动态初始化
        String[][] arr8 = new String[3][]; //动态初始化,因为列表示一维数组长度，与二维数组初始化无关所以可以不用定义
        arr8[1] = new String[4]; //给arr8二维数组的第二个元素赋值一个长度为4的一维数组

        //数组长度
        System.out.print(arr6.length + " " + arr6[1].length + " " + arr8.length); //3 2 3
        System.out.println();

        //遍历二维数组
        for (int i = 0; i < arr6.length; i++) { //i可表示二维数组长度（有多少行）
            for (int j = 0; j < arr6[i].length; j++) { //j可表示一维数组长度（有多少列）
                System.out.print(arr6[i][j]);
            }
            System.out.println();
        }
		
		/*
		初始化值：外层arr[i]；内层arr[i][j]
			1.int arr7[][]=new int[3][4];
				外层元素：地址值
				内层元素：与一维数组相同
			2.String arr8[][]=new String[3][];
				外层元素：null
				内层元素：error
		 */
        for (int i = 0; i < arr7.length; i++) {
            System.out.print(arr7[i]); //输出地址
        }
        System.out.println();
        for (int i = 0; i < arr7.length; i++) {
            for (int j = 0; j < arr7[i].length; j++) {
                System.out.print(arr7[i][j]); //与一维数组相同，默认为0
            }
            System.out.println();
        }

    }

}
