package a2_OOP;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//问题：为什么一个类的一个数组对象作为参数传递给该类的另一个对象时无法赋值？

class Like {
    private String name;
    public Like(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

//例：学生类
class Student {

    public int number;
    public int state;
    public double score;
    public Like like;

    public Student() {}

    public Student(int number, int state, double score) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    //打印学生信息
    public void print() {
        if (number < 10) {
            System.out.print("学号：" + number + "  年级：" + state + " 分数：" + score + "\n");
        } else {
            System.out.print("学号：" + number + " 年级：" + state + " 分数：" + score + "\n");
        }

    }

    //@Description打印state=state1的学生信息
    public void print_state(int state1) {
        if (state == state1) {
            if (number < 10) {
                System.out.print("学号：" + number + "  年级：" + state + " 分数：" + score + "\n");
            } else {
                System.out.print("学号：" + number + " 年级：" + state + " 分数：" + score + "\n");
            }
        }
    }

}

public class OOP2 {

    public static void main(String[] args) {

        Student[] student = new Student[20]; //创建一个Student类的数组
        //数组赋值
        for (int i = 0; i < student.length; i++) {
            student[i] = new Student();
            student[i].number = i + 1;
            student[i].state = (int) (Math.random() * 6 + 1);
            student[i].score = (int) (Math.random() * 40 + 60);
        }

        Scanner in = new Scanner(System.in);
        System.out.print("请输入要查找的年级（1-6）：");
        int state1 = in.nextInt();
        OOP2 opp2 = new OOP2(); //用另一个主类OOP2的对象来实现主类中的方法
        opp2.print_state(student, state1);
        System.out.println("**************************");

        //opp2.score_paixu(student);
        opp2.print(student);
        System.out.println("**************************");

        opp2.state_score_paixu(student);
        opp2.print(student);

    }

    //@Description 在主类中调用Student类的print方法
    public void print(Student[] student) {
        for (int i = 0; i < student.length; i++) {
            student[i].print();
        }
    }

    //@Description 在主类中调用Student类的print_state方法
    public void print_state(Student[] student, int state1) {
        for (int i = 0; i < student.length; i++) {
            student[i].print_state(state1);
        }
    }

    //@Description 按成绩冒泡排序
    public void score_paixu(Student[] student) {
        for (int i = 0; i < student.length - 1; i++) {
            for (int j = 0; j < student.length - i - 1; j++) {
                if (student[j].score > student[j + 1].score) {
                    Student temp = student[j];
                    student[j] = student[j + 1];
                    student[j + 1] = temp;
						/*
							注意：此处要写Student temp=student[j]而不是double temp=student[j].score。原因是前者会
						交换整个Student数组元素的位置，而后者仅会交换Student类元素中score的位置，而不会交换number和
						state的位置！
						 */
                }
            }
        }
    }

    /**
     * @Description 先按年级升序，年级相同按分数升序
     * @author zgh296
     * @date 2022.7.22 17:43
     */
    public void state_score_paixu(Student[] student) {
//        for (int i = 0; i < student.length - 1; i++) {
//            for (int j = 0; j < student.length - i - 1; j++) {
//                if (student[j].score > student[j + 1].score) {
//                    Student temp = student[j];
//                    student[j] = student[j + 1];
//                    student[j + 1] = temp;
//                }
//            }
//        }
//
//        for (int i = 0; i < student.length - 1; i++) {
//            for (int j = 0; j < student.length - i - 1; j++) {
//                if (student[j].state > student[j + 1].state) {
//                    Student temp = student[j];
//                    student[j] = student[j + 1];
//                    student[j + 1] = temp;
//                }
//            }
//        }

        Student[] sorted = Arrays.stream(student)
                .sorted(Comparator
                        .comparingDouble(Student::getScore).reversed()
                        .thenComparing(Student::getState)
                        .thenComparing(s -> s.getLike().getName())  // 指定比较 Like 的 name
                )
                .toArray(Student[]::new);

        // 将排序后的数组复制回原数组
        System.arraycopy(sorted, 0, student, 0, student.length);

    }

}
