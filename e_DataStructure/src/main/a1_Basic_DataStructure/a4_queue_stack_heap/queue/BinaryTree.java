package main.a1_Basic_DataStructure.a4_queue_stack_heap.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BinaryTree
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.queue
 * Description:二叉树类
 *
 * @Author: zgh296
 * @Create: 2023/5/17 - 9:38
 * @Version: v1.0
 */
public class BinaryTree {

    public static class TreeNode {

        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }

        public TreeNode(TreeNode left, int value, TreeNode right) {
            this.left = left;
            this.value = value;
            this.right = right;
        }

        @Override
        public String toString() {
            return value + "";
        }

    }

    /**
     * 二叉树层序遍历，使用集合存储二叉树
     * @param root 二叉树
     * @return 集合
     */
    public List<List<Integer>> levelOredr(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        // 使用链表队列存储
        LinkedListQueue<TreeNode> queue = new LinkedListQueue<>();
        queue.offer(root);
        int c1 = 1; // 当前层的节点数

        // 循环打印和存入值
        while(!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int c2 = 0; // 下一层的节点数
            for (int i = 0; i < c1; i++) {
                // 根节点root
                TreeNode n = queue.poll();
                level.add(n.value);
                // 判断根节点左右节点是否存在，存在则加入队列
                if (n.left != null) {
                    queue.offer(n.left);
                    c2 ++;
                }
                if (n.right != null) {
                    queue.offer(n.right);
                    c2 ++;
                }
            }
            result.add(level);
            c1 = c2; // 一次循环后，下一层变为本层，节点数要替换
        }
        return result;
    }

}
