package main.a1_Basic_DataStructure.a5_binarytree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * ClassName: StringBinaryTree
 * Package: main.a1_Basic_DataStructure.a5_binarytree
 * Description:存储String类型的二叉树类(没啥作用，写着玩...)
 *
 * @Author: zgh296
 * @Create: 2023/6/6 - 16:17
 * @Version: v1.0
 */
public class StringBinaryTree {

    // 树节点类
    public static class TreeNode {

        // 父节点
        private TreeNode parent;
        // 左叶子节点
        private TreeNode left;
        // 节点值
        private String value;
        // 右叶子节点
        private TreeNode right;

        /**
         * 无参构造
         */
        public TreeNode() {
        }

        /**
         * 传入节点值的构造方法
         * @param value 节点值
         */
        public TreeNode(String value) {
            this.value = value;
        }

        /**
         * 传入左叶子节点和节点值的构造方法
         * @param left  左叶子节点
         * @param value 节点值
         */
        public TreeNode(TreeNode left, String value) {
            this.left = left;
            this.value = value;
        }

        /**
         * 传入节点值右叶子节点的构造方法
         * @param value 节点值
         * @param right 右叶子节点
         */
        public TreeNode(String value, TreeNode right) {
            this.value = value;
            this.right = right;
        }

        /**
         * 传入左叶子节点、节点值、右叶子节点的构造方法
         * @param left  左叶子节点
         * @param value 节点值
         * @param right 右叶子节点
         */
        public TreeNode(TreeNode left, String value, TreeNode right) {
            this.left = left;
            this.value = value;
            this.right = right;
        }

        /**
         * 传入父节点、左叶子节点、节点值、右叶子节点的构造方法
         * @param parent 父节点
         * @param left   左叶子节点
         * @param value  节点值
         * @param right  右叶子节点
         */
        public TreeNode(TreeNode parent, TreeNode left, String value, TreeNode right) {
            this.parent = parent;
            this.left = left;
            this.value = value;
            this.right = right;
        }

    }

    // 根节点
    private TreeNode root = null;
    // 二叉树大小
    private int size = 0;

    /**
     * 无参构造
     */
    public StringBinaryTree(){}

    /**
     * 传入根节点的构造方法
     * @param root 根节点
     * @param size 二叉树包含节点数(包括根节点)
     */
    public StringBinaryTree(TreeNode root, int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size参数小于0！");
        }
        this.root = root;
        this.size = size;
    }

    /**
     * 传入根节点的构造方法
     * @param root 根节点
     * @param size 二叉树包含节点数(包括根节点)
     * @param flag 是否检查传入的size大小，true：检查 / false：不检查
     */
    public StringBinaryTree(TreeNode root, int size, boolean flag) {
        if (size < 0) {
            throw new IllegalArgumentException("size参数小于0！");
        }
        // 注：要先传值在检查，否则未传值时getTreeNodeNumber方法中LinkedListStack栈容量未赋值，默认大小为0影响判断！
        this.root = root;
        this.size = size;
        if (flag && getTreeNodeNumber(root) != size) {
            throw new IllegalArgumentException("size与二叉树节点数不符！");
        }
    }

    /**
     * 获取二叉树节点数
     * @param root 二叉树
     */
    public int getTreeNodeNumber(TreeNode root) {
        int number = 0;
        LinkedListStack<TreeNode> stack = new LinkedListStack<>(size);
        TreeNode curr = root;
        // 该思路与executeSearch搜索方法相同！
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                number ++;
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
                curr = pop.right;
            }
        }
        return number;
    }

    /**
     * 获取二叉树的大小
     * @return 二叉树大小
     */
    public int size() {
        return size;
    }

    /**
     * 判断二叉树是否为空
     * @return 判断结果
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 二叉树层序遍历
     * @param root 二叉树
     */
    public void levelOrder(TreeNode root) {
        if(root == null) {
            return;
        }
        LinkedListQueue<TreeNode> queue = new LinkedListQueue<>(size);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.value + "\t");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 二叉树递归前序遍历(根左右)(深度优先)
     * @param node 二叉树
     */
    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + "\t"); // 值
        preOrder(node.left); // 左
        preOrder(node.right); // 右
    }

    /**
     * 二叉树递归中序遍历(左根右)(深度优先)
     * @param node 二叉树
     */
    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left); // 左
        System.out.print(node.value + "\t"); // 值
        inOrder(node.right); // 右
    }

    /**
     * 二叉树递归后序遍历(左右根)(深度优先)
     * @param node 二叉树
     */
    public void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left); // 左
        postOrder(node.right); // 右
        System.out.print(node.value + "\t"); // 值
    }

    /**
     * 获取二叉树最大深度(此处的深度包括根节点！)
     * @param node 二叉树
     * @return 该二叉树最大深度
     */
    public int maxDepth(TreeNode node) {
        // 深度：从根出发，离根最远的节点的总边数(注：此处获得的深度包含根节点！)
        // 二叉树为null
        if (node == null) {
            return 0;
        }
        // 此处+1是加上根节点！
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }

    /**
     * 获取二叉树最小深度(此处的深度包括根节点！)
     * @param node 二叉树
     * @return 该二叉树最小深度
     */
    public int minDepth(TreeNode node) {
        // 深度：从根出发，离根最远的节点的总边数(注：此处获得的深度包含根节点！)
        // 二叉树为null
        if (node == null) {
            return 0;
        }
        // 注意：若二叉树一个节点为null，则表示此分支无节点，不能参与最小深度的比较，应返回另外一边的分支节点
        if (minDepth(node.left) == 0) {
            return minDepth(node.right) + 1;
        }
        if (minDepth(node.right) == 0) {
            return minDepth(node.left) + 1;
        }
        // 此处+1是加上根节点！
        return Math.min(minDepth(node.left), minDepth(node.right)) + 1;
    }

    /**
     * 翻转二叉树
     * @param root 二叉树
     * @return 翻转后的二叉树
     */
    public TreeNode invertTree(TreeNode root) {
        invertTree_Test(root);
        return root;
    }

    /**
     * 翻转二叉树的具体递归操作
     * @param root 翻转后的二叉树
     */
    private void invertTree_Test(TreeNode root) {
        // 递归结束条件：递归到树叶
        if (root == null) {
            return;
        }
        // 交换左右叶子节点的位置
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        // 向下递归左右叶子节点
        invertTree_Test(root.left);
        invertTree_Test(root.right);
    }

    /**
     * 后缀表达式转二叉树
     * @param tokens 后缀表达式
     * @return 二叉树
     */
    public TreeNode constructExpressionTree(String[] tokens) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        /*
            1.遇到数字入栈
            2.遇到运算符出栈，建立节点关系，再入栈
         */
        for (String t : tokens) {
            switch (t) {
                // 运算符
                case "+", "-", "*", "/" -> {
                    TreeNode right = stack.pop();
                    TreeNode left = stack.pop();
                    TreeNode parent = new TreeNode(t);
                    parent.left = left;
                    parent.right = right;
                    left.parent = parent;
                    right.parent = parent;
                    stack.push(parent);
                }
                // 数字
                default -> {
                    stack.push(new TreeNode(t));
                }
            }
        }
        return stack.peek();
    }

    /**
     * 根据前序与中序遍历结果构造二叉树
     * @param preOrder 前序遍历
     * @param inOrder 中序遍历
     * @return 二叉树
     */
    public TreeNode buildTreeUsePreOrderInOrder(int[] preOrder, int[] inOrder) {
        if (preOrder.length == 0) {
            return null;
        }
        // 创建根节点
        int rootValue = preOrder[0];
        TreeNode root = new TreeNode(String.valueOf(rootValue));
        // 区分左右子树
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootValue) {
                // 0 ~ i-1 左子树
                // i+1 ~ inOrder.length -1 右子树
                int[] inLeft = Arrays.copyOfRange(inOrder, 0, i); // [4,2]
                int[] inRight = Arrays.copyOfRange(inOrder, i + 1, inOrder.length); // [6,3,7]

                int[] preLeft = Arrays.copyOfRange(preOrder, 1, i + 1); // [2,4]
                int[] preRight = Arrays.copyOfRange(preOrder, i + 1, inOrder.length); // [3,6,7]

                root.left = buildTreeUsePreOrderInOrder(preLeft, inLeft); // 2
                root.right = buildTreeUsePreOrderInOrder(preRight, inRight); // 3
                break;
            }
        }
        return root;
    }

    /**
     * 根据中序与后序遍历结果构造二叉树
     * @param inOrder 中序遍历
     * @param postOrder 后序遍历
     * @return 二叉树
     */
    public TreeNode buildTreeUseInOrderPostOrder(int[] inOrder, int[] postOrder) {
        if (inOrder.length == 0) {
            return null;
        }
        // 根
        int rootValue = postOrder[postOrder.length - 1];
        TreeNode root = new TreeNode(String.valueOf(rootValue));
        // 切分左右子树
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootValue) {
                int[] inLeft = Arrays.copyOfRange(inOrder, 0, i);
                int[] inRight = Arrays.copyOfRange(inOrder, i + 1, inOrder.length);

                int[] postLeft = Arrays.copyOfRange(postOrder, 0, i);
                int[] postRight = Arrays.copyOfRange(postOrder, i, postOrder.length - 1);

                root.left = buildTreeUseInOrderPostOrder(inLeft, postLeft);
                root.right = buildTreeUseInOrderPostOrder(inRight, postRight);
                break;
            }
        }
        return root;
    }

}
