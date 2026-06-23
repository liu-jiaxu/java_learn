package main.a2_Basic_Algorithms.a3_redblacktree;

import main.a2_Basic_Algorithms.a1_binarysearchtree.BSTTree;
import org.junit.jupiter.api.Test;

/**
 * ClassName: RBTree_Test
 * Package: main.a2_Basic_Algorithms.a3_redblacktree
 * Description:红黑树测试类
 *
 * @Author: zgh296
 * @Create: 2023/6/20 - 10:08
 * @Version: v1.0
 */
public class RBTree_Test {

    // 如下是三种二叉树平衡方法效率的测试，红黑树最优，平衡二叉搜索树次之，近似AVL树最差
    // 该排序同样正确反映了三者的时间和空间复杂度
    @Test
    public void test() {

        RBTree tree = new RBTree();
        for (int i = 1; i < 2000; i++) { // 为了更好地测试转换效率可将i最值设置为1000+
            tree.put(i, i);
        }
        System.out.println(tree.levelOrderUseList()); // 23ms

    }

    @Test
    public void test2() {

        BSTTree<Integer, Object> tree2 = new BSTTree<>();
        for (int i = 1; i < 2000; i++) { // 为了更好地测试转换效率可将i最值设置为1000+
            tree2.put(i, i);
        }
        BSTTree<Integer, Object> tree3 = new BSTTree<>(tree2.balanceBSTToBalance(tree2.getRoot()));
        System.out.println(tree3.levelOrderUseList()); // 95ms

    }

    @Test
    public void test3() {

        BSTTree<Integer, Object> tree2 = new BSTTree<>();
        for (int i = 1; i < 2000; i++) { // 为了更好地测试转换效率可将i最值设置为1000+
            tree2.put(i, i);
        }
        BSTTree<Integer, Object> tree3 = new BSTTree<>(tree2.balanceBSTToAVL(tree2.getRoot()));
        System.out.println(tree3.levelOrderUseList()); // 2738ms

    }

}
