package main.a2_Basic_Algorithms.a1_binarysearchtree;

import org.junit.jupiter.api.Test;

/**
 * ClassName: BSTTree_Test
 * Package: main.a2_Basic_Algorithms.a1_binarysearchtree
 * Description:二叉搜索树测试类
 *
 * @Author: zgh296
 * @Create: 2023/6/12 - 16:29
 * @Version: v1.0
 */
public class BSTTree_Test {

    // 二叉搜索树泛型及get方法测试
    @Test
    public void test() {

        /*
                    4
                   / \
                  2   6
                 / \ / \
                1  3 5  7
         */
        BSTTree.BSTNode<Integer, Object> n1 = new BSTTree.BSTNode<>(1, "小华");
        BSTTree.BSTNode<Integer, Object> n3 = new BSTTree.BSTNode<>(3, "小红");
        BSTTree.BSTNode<Integer, Object> n2 = new BSTTree.BSTNode<>(2, "小张", null, n1, n3);
        BSTTree.BSTNode<Integer, Object> n5 = new BSTTree.BSTNode<>(5, "小李");
        BSTTree.BSTNode<Integer, Object> n7 = new BSTTree.BSTNode<>(7, "小刚");
        BSTTree.BSTNode<Integer, Object> n6 = new BSTTree.BSTNode<>(6, "小昭", n5, n7);
        BSTTree.BSTNode<Integer, Object> root = new BSTTree.BSTNode<>(4, "小明", null, n2, n6);
        BSTTree<Integer, Object> tree = new BSTTree<>(root, 7);

        System.out.println(tree.get(1)); // 小华
        System.out.println(tree.get(7)); // 小刚
        System.out.println(tree.get(8)); // null
        System.out.println(tree.get2(4)); // 小明

        /*
                    d
                   / \
                  b   f
                 / \ / \
                a  c e  g
         */
        BSTTree.BSTNode<String, Object> n11 = new BSTTree.BSTNode<>("a", "小华");
        BSTTree.BSTNode<String, Object> n31 = new BSTTree.BSTNode<>("c", "小红");
        BSTTree.BSTNode<String, Object> n21 = new BSTTree.BSTNode<>("b", "小张", null, n11, n31);
        BSTTree.BSTNode<String, Object> n51 = new BSTTree.BSTNode<>("e", "小李");
        BSTTree.BSTNode<String, Object> n71 = new BSTTree.BSTNode<>("g", "小刚");
        BSTTree.BSTNode<String, Object> n61 = new BSTTree.BSTNode<>("f", "小昭", n51, n71);
        BSTTree.BSTNode<String, Object> root1 = new BSTTree.BSTNode<>("d", "小明", null, n21, n61);
        BSTTree<String, Object> tree1 = new BSTTree<>(root1);

        System.out.println(tree1.get("a")); // 小华
        System.out.println(tree1.get("c")); // 小红
        System.out.println(tree1.get("q")); // null
        System.out.println(tree1.get2("d")); // 小明
        System.out.println(tree1.size());

    }

    // 二叉搜索树其它增删改查方法测试
    @Test
    public void test2() {

        BSTTree<Integer, Object> tree = new BSTTree<>();
        tree.put(4, "d");
        tree.put(2, "b");
        tree.put(7, "g");
        tree.put(1, "a");
        BSTTree.BSTNode<Integer, Object> n3 = new BSTTree.BSTNode<>(3, "c");
        tree.put(n3);
        tree.put(6, "f");
        tree.put(8, "h");
        tree.put(5, "e");

        System.out.println(tree.getMinKey()); // 1
        System.out.println(tree.getMaxKey()); // 8
        System.out.println(tree.getMinValue()); // a
        System.out.println(tree.getMaxValue()); // h
        System.out.println(tree.size()); // 8

        System.out.println(tree.getPredecessorValue(1)); // null
        System.out.println(tree.getPredecessorValue(2)); // a
        System.out.println(tree.getPredecessorValue(5)); // d
        System.out.println(tree.getPredecessorKey(5)); // 4
        System.out.println(tree.getPredecessorKey(0)); // null
        System.out.println(tree.getSuccessorValue(1)); // b
        System.out.println(tree.getSuccessorValue(2)); // c
        System.out.println(tree.getSuccessorValue(5)); // f
        System.out.println(tree.getSuccessorKey(5)); // 6
        System.out.println(tree.getSuccessorKey(0)); // null

        System.out.println(tree.delete(5)); // e
        System.out.println(tree.delete(3)); // c
        System.out.println(tree.delete2(8)); // h
        System.out.println(tree.delete(10)); // null
        System.out.println(tree.delete2(10)); // null

        System.out.println(tree.size()); // 5
        tree.levelOrder(); // 4：d	2：b	7：g	1：a	6：f
        System.out.println();

        System.out.println(tree.getLessValue(4, true)); // [a, b, d]
        System.out.println(tree.getGreaterValue(3, true)); // [a, b, d]
        System.out.println(tree.getBetweenValue(1, 4, false, true)); // [g, f, d]
        System.out.println(tree.getBetweenValue(6, 2, false, true)); // [d, f]

        tree.updateKey(4,5); // 6：f 	2：b	7：g	1：a    5：d
        tree.updateValue(7,"d"); // 6：f 	2：b	7：d	1：a    5：d
        tree.update(1,-3, "f"); // 6：f 	2：b	7：d	-3：f    5：d
        tree.update(-3,-3, "z"); // 6：f 	2：b	7：d	-3：z    5：d
        System.out.println(tree.updateAllValue("f", "-c")); // 1
            // 6：-c 	2：b	7：d	-3：z    5：d

        System.out.println(tree.getValueCount(n3, "c")); // 1
        System.out.println(tree.getValueCount("d")); // 2

        System.out.println(tree.levelOrderUseList()); // [{6=-c}, {2=b, 7=d}, {-3=z, 5=d}]

        tree.deleteAll();
        tree.levelOrder(); // null

    }

    // 二叉搜索树遍历测试
    @Test
    public void test3() {

        BSTTree.BSTNode<Integer, Object> n1 = new BSTTree.BSTNode<>(1, "小华");
        BSTTree.BSTNode<Integer, Object> n3 = new BSTTree.BSTNode<>(3, "小红");
        BSTTree.BSTNode<Integer, Object> n2 = new BSTTree.BSTNode<>(2, "小张", null, n1, n3);
        BSTTree.BSTNode<Integer, Object> n5 = new BSTTree.BSTNode<>(5, "小李");
        BSTTree.BSTNode<Integer, Object> n7 = new BSTTree.BSTNode<>(7, "小刚");
        BSTTree.BSTNode<Integer, Object> n6 = new BSTTree.BSTNode<>(6, "小昭", n5, n7);
        BSTTree.BSTNode<Integer, Object> root = new BSTTree.BSTNode<>(4, "小明", null, n2, n6);
        BSTTree<Integer, Object> tree = new BSTTree<>(root, 7);
        /*
                    4
                   / \
                  2   6
                 / \ / \
                1  3 5  7
         */

        System.out.println(tree.levelOrderUseList()); // [{4=小明}, {2=小张, 6=小昭}, {1=小华, 3=小红, 5=小李, 7=小刚}]
        System.out.print("层序遍历：");
        tree.levelOrder(); // 层序遍历：4：小明	2：小张	6：小昭	1：小华	3：小红	5：小李	7：小刚
        System.out.println();
        System.out.print("前序遍历：");
        tree.preOrder(); // 前序遍历：4：小明	2：小张	1：小华	3：小红	6：小昭	5：小李	7：小刚
        System.out.println();
        System.out.print("中序遍历：");
        tree.inOrder(); // 中序遍历：1：小华	2：小张	3：小红	4：小明	5：小李	6：小昭	7：小刚
        System.out.println();
        System.out.print("后序遍历：");
        tree.postOrder(); // 后序遍历：1：小华	3：小红	2：小张	5：小李	7：小刚	6：小昭	4：小明
        System.out.println();

        System.out.print("层序遍历：");
        tree.levelOrder(root); // 层序遍历：4：小明	2：小张	6：小昭	1：小华	3：小红	5：小李	7：小刚
        System.out.println();
        System.out.print("前序遍历：");
        tree.preOrder(n6); // 前序遍历：6：小昭	5：小李	7：小刚
        System.out.println();
        System.out.print("中序遍历：");
        tree.inOrder(n2); // 中序遍历：1：小华	2：小张	3：小红
        System.out.println();
        System.out.print("后序遍历：");
        tree.postOrder(n5); // 后序遍历：5：小李
        System.out.println();

        System.out.println(tree.levelOrderKey()); // [4, 2, 6, 1, 3, 5, 7]
        System.out.println(tree.levelOrderValue()); // [小明, 小张, 小昭, 小华, 小红, 小李, 小刚]
        System.out.println(tree.inOrderKey()); // [1, 2, 3, 4, 5, 6, 7]
        System.out.println(tree.inOrderValue()); // [小华, 小张, 小红, 小明, 小李, 小昭, 小刚]

        System.out.println("深度遍历：");
        tree.order();

    }

    // 二叉搜索树一般方法测试
    @Test
    public void test4() {

        BSTTree.BSTNode<Integer, Object> n3 = new BSTTree.BSTNode<>(3, "小红");
        BSTTree.BSTNode<Integer, Object> n2 = new BSTTree.BSTNode<>(2, "小张", null, null, n3);
        BSTTree.BSTNode<Integer, Object> n5 = new BSTTree.BSTNode<>(5, "小红");
        BSTTree.BSTNode<Integer, Object> n6 = new BSTTree.BSTNode<>(6, "小张", n5, null);
        BSTTree.BSTNode<Integer, Object> root = new BSTTree.BSTNode<>(4, "小明", null, n2, n6);
        BSTTree<Integer, Object> tree = new BSTTree<>(root, 5);
        /*
                    4
                   / \
                  2   6
                   \ /
                   3 5
         */

        System.out.println(tree.isSymmetric(root)); // true
        System.out.println(tree.maxDepth()); // 3
        System.out.println(tree.maxDepth(n2)); // 2
        System.out.println(tree.minDepth()); // 3
        System.out.println(tree.minDepth(root)); // 3

        /*
            注：invertTreeNotChange方法不改变原二叉搜索树，invertTree改变原二叉搜索树
                tree树自身没有翻转，但tree2树则是tree树的翻转
                tree2树相当于tree树翻转了一次，之后又调用invertTree方法翻转一次，此时tree2 == tree
                tree3树相当于tree2树调用invertTree方法翻转了一次，此时tree3 == tree2
                最终三棵树相同！
         */
        BSTTree.BSTNode<Integer, Object> tree2Node = tree.invertTreeNotChange(root);
        BSTTree<Integer, Object> tree2 = new BSTTree<>(tree2Node, 5, true);
        BSTTree<Integer, Object> tree3 = new BSTTree<>(tree2.invertTree(root), 5, true);
        tree.levelOrder(); // 4：小明	2：小张	6：小张	3：小红	5：小红
        System.out.println();
        tree2.levelOrder(); // 4：小明	2：小张	6：小张	3：小红	5：小红
        System.out.println();
        tree3.levelOrder(); // 4：小明	2：小张	6：小张	3：小红	5：小红
        System.out.println();
        System.out.println(tree.equal(tree.getRoot(), tree2.getRoot())); // true
        System.out.println(tree.equal(tree.getRoot().getLeft(), tree.getRoot())); // false
        System.out.println(tree.equal(tree3.getRoot(), tree2.getRoot(), true, true)); // true

        System.out.println(tree.isValidBST(root)); // true
        System.out.println(tree.isValidBSTLong(root)); // true
        System.out.println(tree2.isValidBST(root)); // true
        System.out.println(tree3.isValidBSTLong(root)); // true

        BSTTree.BSTNode<Integer, Double> root4 = new BSTTree.BSTNode<>(4, 4.701,null);
        BSTTree<Integer, Double> tree4 = new BSTTree<>(root4);
        BSTTree.BSTNode<Integer, Double> b2 = new BSTTree.BSTNode<>(2, 2.349);
        tree4.put(b2);
        tree4.put(7, 7.121);
        tree4.put(1, 1.003);
        BSTTree.BSTNode<Integer, Double> b3 = new BSTTree.BSTNode<>(3, 3.332);
        tree4.put(b3);
        BSTTree.BSTNode<Integer, Double> b6 = new BSTTree.BSTNode<>(6, 6.845);
        tree4.put(b6);
        tree4.put(8, 8.267);
        BSTTree.BSTNode<Integer, Double> b5 = new BSTTree.BSTNode<>(5, 5.489);
        tree4.put(b5);
        /*
                     4
                   /  \
                  2    7
                 / \  / \
                1  3 6  8
                    /
                   5
         */

        System.out.println(tree4.levelOrderUseList()); // [{4=4.701}, {2=2.349, 7=7.121}, {1=1.003, 3=3.332, 6=6.845, 8=8.267}, {5=5.489}]
        System.out.println(tree4.size()); // 8
        System.out.printf("%.3f",tree4.rangeSumBST(root4, 4, 6, true, true)); // 17.035
        System.out.println();
        System.out.println(tree4.rangeSumBST2(root4, 4, 6)); // 17.035

        BSTTree.BSTNode<Integer, Double> result = tree4.lowestCommonAncestor(root4, b6, b5);
        System.out.println("" + result.getKey() + " " + result.getValue()); // 6 6.845
        BSTTree.BSTNode<Integer, Double> result2 = tree4.lowestCommonAncestor(root4, b3, b2);
        System.out.println("" + result2.getKey() + " " + result2.getValue()); // 2 2.349

    }

    // 字符串二叉树测试
    @Test
    public void test5() {

        String[] tokens = {"2", "1", "-", "3", "*"};
        BSTTree<Integer, String> tree = new BSTTree<>();
        BSTTree.BSTNode<Integer, String> treeNode = tree.constructExpressionTree(tokens);
        BSTTree<Integer, String> tree2 = new BSTTree<>(treeNode, 5);
        System.out.println(tree2.levelOrderValue()); // [*, -, 3, 2, 1]

        Integer[] preOrder = {8, 5, 1, 7, 10, 12};
        BSTTree<Integer, Integer> rootP = new BSTTree<>();
        BSTTree.BSTNode<Integer, Integer> treeNodeP = rootP.buildTreeUsePreOrder(preOrder);
        System.out.println(new BSTTree<>(treeNodeP, 6).levelOrderUseList()); // [{8=8}, {5=5, 10=10}, {1=1, 7=7, 12=12}]
        new BSTTree<>(treeNodeP, 6).levelOrder();
        System.out.println();

        Integer[] pre = {1, 2, 4, 3, 6, 7};
        Integer[] in = {4, 2, 1, 6, 3, 7};
        Integer[] post = {4, 2, 6, 7, 3, 1};
        BSTTree<Integer, Integer> root = new BSTTree<>();

        BSTTree.BSTNode<Integer, Integer> treeNode3 = root.buildTreeUsePreOrderInOrder(pre, in);
        BSTTree<Integer, Integer> tree3 = new BSTTree<>(treeNode3, 6);
        System.out.println(tree3.levelOrderValue()); // [1, 2, 3, 4, 6, 7]

        BSTTree.BSTNode<Integer, Integer> treeNode4 = root.buildTreeUseInOrderPostOrder(in, post);
        BSTTree<Integer, Integer> tree4 = new BSTTree<>(treeNode4, 6);
        System.out.println(tree4.levelOrderKey()); // [1, 2, 3, 4, 6, 7]

        System.out.println(tree4.isValidBST(treeNode4)); // false

    }

    // 二叉搜索树平衡度测试
    @Test
    public void test6() {

        BSTTree.BSTNode<Integer, Object> n1 = new BSTTree.BSTNode<>(1, "a1");
        BSTTree.BSTNode<Integer, Object> n3 = new BSTTree.BSTNode<>(3, "c3");
        BSTTree.BSTNode<Integer, Object> n4 = new BSTTree.BSTNode<>(4, "d4");
        BSTTree.BSTNode<Integer, Object> n5 = new BSTTree.BSTNode<>(5, "e5");
        BSTTree.BSTNode<Integer, Object> n6 = new BSTTree.BSTNode<>(6, "f6");
        BSTTree.BSTNode<Integer, Object> n7 = new BSTTree.BSTNode<>(7, "g7");
        BSTTree.BSTNode<Integer, Object> n8 = new BSTTree.BSTNode<>(8, "h8");
        BSTTree.BSTNode<Integer, Object> n9 = new BSTTree.BSTNode<>(9, "i9");

        BSTTree.BSTNode<Integer, Object> m1 = new BSTTree.BSTNode<>(1, "a1");
        BSTTree.BSTNode<Integer, Object> m3 = new BSTTree.BSTNode<>(3, "c3");
        BSTTree.BSTNode<Integer, Object> m4 = new BSTTree.BSTNode<>(4, "d4");
        BSTTree.BSTNode<Integer, Object> m5 = new BSTTree.BSTNode<>(5, "e5");
        BSTTree.BSTNode<Integer, Object> m6 = new BSTTree.BSTNode<>(6, "f6");
        BSTTree.BSTNode<Integer, Object> m8 = new BSTTree.BSTNode<>(8, "h8");
        BSTTree.BSTNode<Integer, Object> m9 = new BSTTree.BSTNode<>(9, "i9");

        // LL
        BSTTree<Integer, Object> LL = new BSTTree<>(n8);
        LL.put(n6);
        LL.put(n9);
        LL.put(n5);
        LL.put(n7);
        LL.put(n4);

        // RR
        BSTTree<Integer, Object> RR = new BSTTree<>(n3);
        RR.put(n1);
        RR.put(n5);
        RR.put(n4);
        RR.put(n6);
        RR.put(n9);

        // LR
        BSTTree<Integer, Object> LR = new BSTTree<>(m8);
        LR.put(m3);
        LR.put(m9);
        LR.put(m1);
        LR.put(m4);
        LR.put(m5);

        // RL
        BSTTree<Integer, Object> RL = new BSTTree<>(m3);
        RL.put(m1);
        RL.put(m6);
        RL.put(m5);
        RL.put(m9);
        RL.put(m4);

        System.out.println(LL.balanceFactor(n8)); // 2
        System.out.println(LL.levelOrderUseList()); // [{8=h8}, {6=f6, 9=i9}, {5=e5, 7=g7}, {4=d4}]
        BSTTree.BSTNode<Integer, Object> nodeLL2 = LL.balanceBSTToAVL(LL.getRoot());
        BSTTree<Integer, Object> LL2 = new BSTTree<>(nodeLL2);
        System.out.println(LL.levelOrderUseList()); // [{8=h8}, {6=f6, 9=i9}, {5=e5, 7=g7}, {4=d4}]
        System.out.println(LL2.levelOrderUseList()); // [{6=f6}, {5=e5, 8=h8}, {4=d4, 7=g7, 9=i9}]

        System.out.println(RR.balanceFactor(n3)); // -2
        System.out.println(RR.levelOrderUseList()); // [{3=c3}, {1=a1, 5=e5}, {4=d4, 6=f6}, {9=i9}]
        BSTTree.BSTNode<Integer, Object> nodeRR2 = RR.balanceBSTToAVL(RR.getRoot());
        BSTTree<Integer, Object> RR2 = new BSTTree<>(nodeRR2);
        System.out.println(RR.levelOrderUseList()); // [{3=c3}, {1=a1, 5=e5}, {4=d4, 6=f6}, {9=i9}]
        System.out.println(RR2.levelOrderUseList()); // [{5=e5}, {3=c3, 6=f6}, {1=a1, 4=d4, 9=i9}]

        System.out.println(LR.balanceFactor(m8)); // 2
        System.out.println(LR.levelOrderUseList()); // [{8=h8}, {3=c3, 9=i9}, {1=a1, 4=d4}, {5=e5}]
        BSTTree.BSTNode<Integer, Object> nodeLR2 = LR.balanceBSTToAVL(LR.getRoot());
        BSTTree<Integer, Object> LR2 = new BSTTree<>(nodeLR2);
        System.out.println(LR.levelOrderUseList()); // [{8=h8}, {3=c3, 9=i9}, {1=a1, 4=d4}, {5=e5}]
        System.out.println(LR2.levelOrderUseList()); // [{4=d4}, {3=c3, 8=h8}, {1=a1, 5=e5, 9=i9}]

        System.out.println(RL.balanceFactor(m3)); // -2
        System.out.println(RL.levelOrderUseList()); // [{3=c3}, {1=a1, 6=f6}, {5=e5, 9=i9}, {4=d4}]
        BSTTree.BSTNode<Integer, Object> nodeRL2 = RL.balanceBSTToAVL(RL.getRoot());
        BSTTree<Integer, Object> RL2 = new BSTTree<>(nodeRL2);
        System.out.println(RL.levelOrderUseList()); // [{3=c3}, {1=a1, 6=f6}, {5=e5, 9=i9}, {4=d4}]
        System.out.println(RL2.levelOrderUseList()); // [{5=e5}, {3=c3, 6=f6}, {1=a1, 4=d4, 9=i9}]

        BSTTree<Integer, Object> tree = new BSTTree<>();
        for (int i = 10; i > 1; i--) {
            tree.put(i, i);
        }
        System.out.println(tree.levelOrderUseList());
            // [{10=10}, {9=9}, {8=8}, {7=7}, {6=6}, {5=5}, {4=4}, {3=3}, {2=2}]
        System.out.println(new BSTTree<>(tree.balanceBSTToAVL(tree.getRoot())).levelOrderUseList());
            // [{6=6}, {5=5, 7=7}, {4=4, 10=10}, {3=3, 9=9}, {2=2, 8=8}]

        BSTTree<Integer, Object> tree2 = new BSTTree<>();
        for (int i = 1; i < 15; i++) { // 为了更好地测试转换效率可将i最值设置为1000+
            tree2.put(i, i);
        }
        System.out.println(tree2.levelOrderUseList());
            // [{1=1}, {2=2}, {3=3}, {4=4}, {5=5}, {6=6}, {7=7}, {8=8}, {9=9}, {10=10}, {11=11}, {12=12}, {13=13}, {14=14}]
        System.out.println(new BSTTree<>(tree2.balanceBSTToAVL(tree2.getRoot())).levelOrderUseList());
            // [{7=7}, {6=6, 8=8}, {1=1, 9=9}, {2=2, 10=10}, {3=3, 11=11}, {4=4, 12=12}, {5=5, 13=13}, {14=14}]
        BSTTree<Integer, Object> tree3 = new BSTTree<>(tree2.balanceBSTToBalance(tree2.getRoot()));
        System.out.println(tree3.size()); // 14
        System.out.println(tree3.levelOrderUseList());
            // [{7=7}, {4=4, 11=11}, {2=2, 5=5, 9=9, 13=13}, {1=1, 3=3, 6=6, 8=8, 10=10, 12=12, 14=14}]
        tree3.showBST(tree3.getRoot());

    }

    // 二叉搜索树树形结构打印
    @Test
    public void test7() {

        BSTTree<Integer, Object> tree = new BSTTree<>();
        tree.put(4, "d");
        tree.put(2, "b");
        tree.put(7, "g");
        tree.put(1, "a");
        BSTTree.BSTNode<Integer, Object> n3 = new BSTTree.BSTNode<>(3, "c");
        tree.put(n3);
        tree.put(6, "f");
        tree.put(8, "h");
        tree.put(5, "e");

        BSTTree<Integer, Object> tree2 = new BSTTree<>();
        for (int i = 15; i > 1; i--) {
            tree2.put(i, i);
        }
        BSTTree<Integer, Object> tree3 = new BSTTree<>(tree2.balanceBSTToAVL(tree2.getRoot()));

        BSTTree<Integer, Object> tree4 = new BSTTree<>();
        tree4.put(8, "s");
        tree4.put(4, "a");
        tree4.put(12, "h");
        tree4.put(3, "j");
        tree4.put(6, "e");
        tree4.put(10, "g");
        tree4.put(13, "y");
        tree4.put(2, "r");
        tree4.put(5, "l");
        tree4.put(7, "q"); // 这个节点打印时“\”符号被空格覆盖了
        tree4.put(11, "n");
        tree4.put(14, "u");

        tree.showBST(tree.getRoot());
        tree3.showBST(tree3.getRoot());
        tree4.showBST(tree4.getRoot());

    }

    // 查看所有方法名
    @Test
    public void test8() {

        BSTTree<Integer, Integer> tree = new BSTTree<>();
        tree.getALLPublicFunctions(); // 82
        System.out.println();
        tree.getALLFunctions(); // 95

    }

}
