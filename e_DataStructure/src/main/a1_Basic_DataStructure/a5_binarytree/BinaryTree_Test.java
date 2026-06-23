package main.a1_Basic_DataStructure.a5_binarytree;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * ClassName: BinaryTree_Test
 * Package: main.a1_Basic_DataStructure.a5_binarytree
 * Description:二叉树测试类
 *
 * @Author: zgh296
 * @Create: 2023/5/31 - 15:37
 * @Version: v1.0
 */
public class BinaryTree_Test {

    // 二叉树遍历测试
    @Test
    public void test() {

        // 根节点root及下方的各个节点(该二叉树不满足数据存储结构，仅用于遍历)
        BinaryTree.TreeNode<Integer> root = new BinaryTree.TreeNode<>(
                new BinaryTree.TreeNode<>(
                        new BinaryTree.TreeNode<>(4),
                        2,
                        new BinaryTree.TreeNode<>(5)
                ),
                1,
                new BinaryTree.TreeNode<>(
                        new BinaryTree.TreeNode<>(
                                new BinaryTree.TreeNode<>(8),
                                6,
                                new BinaryTree.TreeNode<>(
                                        9,
                                        new BinaryTree.TreeNode<>(10)
                                )
                        ),
                        3,
                        new BinaryTree.TreeNode<>(7)
                )
        );

        BinaryTree<Integer> binaryTree = new BinaryTree<>(root, 7);
        List<List<Integer>> list = binaryTree.levelOrder();
        System.out.println("层序遍历");
        System.out.println(list);// [[1], [2, 3], [4, 5, 6, 7], [8, 9], [10]]
        binaryTree.levelOrder(root); // 1	2	3	4	5	6	7	8	9	10
        System.out.println();

        System.out.println("前序遍历 ");
        binaryTree.preOrder(root); // 1	2	4	5	3	6	8	9	10	7
        System.out.println();
        binaryTree.preOrder2();
        System.out.println();

        System.out.println("中序遍历");
        binaryTree.inOrder(root); // 4	2	5	1	8	6	9	10	3	7
        System.out.println();
        binaryTree.inOrder2();
        System.out.println();

        System.out.println("后序遍历");
        binaryTree.postOrder(root); // 4	5	2	8	10	9	6	7	3	1
        System.out.println();
        binaryTree.postOrder2();
        System.out.println();

        System.out.println("三种深度遍历");
        binaryTree.order();

    }

    // 二叉树增删改查测试
    @Test
    public void test2() {

        BinaryTree.TreeNode<Integer> root = new BinaryTree.TreeNode<>(null, null, 20, null); // [[20]]
        BinaryTree<Integer> binaryTree = new BinaryTree<>(root, 1);
        binaryTree.insert(24); // [[20], [24]]
        binaryTree.insert(35); // [[20], [24], [35]]
        binaryTree.insert(28); // [[20], [24], [35], [28]]
        binaryTree.insert(new BinaryTree.TreeNode<>(10)); // [[20], [10, 24], [35], [28]]
        binaryTree.insert(new BinaryTree.TreeNode<>(23)); // [[20], [10, 24], [23, 35], [28]]
        binaryTree.insert(new BinaryTree.TreeNode<>(25)); // [[20], [10, 24], [23, 35], [28], [25]]
        binaryTree.insert(new BinaryTree.TreeNode<>(null)); // [[20], [10, 24], [null, 23, 35], [28], [25]]
        binaryTree.insert(27); // [[20], [10, 24], [null, 23, 35], [28], [25], [27]]
        System.out.println(binaryTree.levelOrder()); // [[20], [10, 24], [null, 23, 35], [28], [25], [27]]

        binaryTree.delete(24); // [[20], [10, 23], [35], [28], [25], [27]]
        binaryTree.delete(10); // [[20], [23], [35], [28], [25], [27]]
        //binaryTree.delete(99);
        System.out.println(binaryTree.levelOrder()); // [[20], [null, 25], [23, 35], [28], [27]]

        binaryTree.update(27, 30); // [[20], [23], [35], [28], [25, 30]]
        binaryTree.update(28, 22); // [[20], [23], [22, 35], [25], [30]]
        binaryTree.update(0, 34); // 不变
        System.out.println(binaryTree.levelOrder()); // [[20], [null, 25], [23, 35], [22, 30]]
        System.out.println(binaryTree.size()); // 7

        BinaryTree<Integer> binaryTree2 = new BinaryTree<>(binaryTree.select(root, 23), 1); // [[23]]
        binaryTree2.insert(binaryTree2.select(root, 25)); // [[23], [25]]
        System.out.println(binaryTree2.levelOrder()); // [[23], [25]]
        System.out.println(binaryTree2.size()); // 2
        System.out.println(binaryTree2.isEmpty()); // false

    }

    // 二叉树其余方法测试
    @Test
    public void test3() {

        BinaryTree.TreeNode<Integer> root = new BinaryTree.TreeNode<>(
                new BinaryTree.TreeNode<>(
                        new BinaryTree.TreeNode<>(
                                new BinaryTree.TreeNode<>(9),
                                null,
                                new BinaryTree.TreeNode<>(null)
                        ),
                        2,
                        new BinaryTree.TreeNode<>(5)
                ),
                1,
                new BinaryTree.TreeNode<>(
                        new BinaryTree.TreeNode<>(5),
                        2,
                        new BinaryTree.TreeNode<>(
                                new BinaryTree.TreeNode<>(null),
                                null,
                                new BinaryTree.TreeNode<>(9)
                        )
                )
        );
        /* 二叉树示意图：
                           1
                        /    \
                      2       2
                     / \     / \
                    n   5   5   n
                   /\          /\
                  9 n         n  9
         */
        BinaryTree<Integer> binaryTree = new BinaryTree<>(root, 11, true);
        BinaryTree<Integer> binaryTree2 = new BinaryTree<>(root, binaryTree.getTreeNodeNumber(root), true);
        System.out.println(binaryTree2.getTreeNodeNumber(root)); // 11
        System.out.println(binaryTree.isSymmetric(root)); // true
        System.out.println(binaryTree.maxDepth(root)); // 4
        System.out.println(binaryTree.maxDepth2(root)); // 4
        System.out.println(binaryTree.minDepth2(root)); // 3
        System.out.println(binaryTree.minDepth2(root)); // 3

        BinaryTree.TreeNode<Integer> root3 = new BinaryTree.TreeNode<>(
                new BinaryTree.TreeNode<>(
                        new BinaryTree.TreeNode<>(
                                new BinaryTree.TreeNode<>(9),
                                null,
                                new BinaryTree.TreeNode<>(null)
                        ),
                        2
                ),
                1,
                new BinaryTree.TreeNode<>(
                        new BinaryTree.TreeNode<>(3),
                        7,
                        new BinaryTree.TreeNode<>(null)
                )
        );
        /* 二叉树示意图：
                           1
                        /    \
                      2       7
                     / \     / \
                    n       3   n
                   /\
                  9 n
         */
        BinaryTree<Integer> binaryTree3 = new BinaryTree<>(root3, 8, true);
        binaryTree3.levelOrder(root3); // 1	2	7	null	3	null	9	null
        System.out.println();
        BinaryTree.TreeNode<Integer> root4 = binaryTree3.invertTree(root3);
        BinaryTree<Integer> binaryTree4 = new BinaryTree<>(root4, 8, true);
        binaryTree4.levelOrder(root4); // 1	7	2	null	3	null	null	9
        System.out.println();

    }

    // 字符串二叉树测试
    @Test
    public void test4() {

        String[] tokens = {"2", "1", "-", "3", "*"};
        BinaryTree<String> BinaryTree = new BinaryTree<>();
        BinaryTree.TreeNode<String> treeNode = BinaryTree.constructExpressionTree(tokens);

        BinaryTree<String> BinaryTree2 = new BinaryTree<>(treeNode, 5);
        BinaryTree2.inOrder(treeNode); // 2	-	1	*	3
        System.out.println();

        Integer[] pre = {1, 2, 4, 3, 6, 7};
        Integer[] in = {4, 2, 1, 6, 3, 7};
        Integer[] post = {4, 2, 6, 7, 3, 1};
        BinaryTree<Integer> root = new BinaryTree<>();
        BinaryTree.TreeNode<Integer> treeNode3 = root.buildTreeUsePreOrderInOrder(pre, in);
        BinaryTree<Integer> stringBinaryTree3 = new BinaryTree<>(treeNode3, 6);
        System.out.println(stringBinaryTree3.levelOrder()); // [[1], [2, 3], [4, 6, 7]]
        BinaryTree.TreeNode<Integer> treeNode4 = root.buildTreeUseInOrderPostOrder(in, post);
        BinaryTree<Integer> stringBinaryTree4 = new BinaryTree<>(treeNode4, 6);
        System.out.println(stringBinaryTree4.levelOrder()); // [[1], [2, 3], [4, 6, 7]]

    }

}
