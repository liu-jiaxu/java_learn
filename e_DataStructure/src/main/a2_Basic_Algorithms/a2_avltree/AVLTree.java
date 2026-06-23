package main.a2_Basic_Algorithms.a2_avltree;

import main.a2_Basic_Algorithms.a1_binarysearchtree.LinkedListQueue;

/**
 * ClassName: AVLTree
 * Package: main.a2_Basic_Algorithms.a2_avltree
 * Description:AVL树类
 *
 * @Author: zgh296
 * @Create: 2023/6/17 - 17:14
 * @Version: v1.0
 */
public class AVLTree {

    /*
    AVL 树是一种平衡二叉树，发明者的名字(Adelson-Velskii 以及 Landis)
        1.左右子树的高度差小于等于 1。
        2.其每一个子树均为平衡二叉树。
     */

    public static class AVLNode {

        // key值
        int key;
        // 节点值
        Object value;
        // 左孩子节点
        AVLNode left;
        // 右孩子节点
        AVLNode right;
        // 树高度
        int height = 1;

        /**
         * 传参key的构造方法
         * @param key key值
         */
        public AVLNode(int key) {
            this.key = key;
        }

        /**
         * 传参key和value的构造方法
         * @param key key值
         * @param value 节点值
         */
        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        /**
         * 传参key,value,left,right的构造方法
         * @param key key值
         * @param value 节点值
         * @param left 左孩子节点
         * @param right 右孩子节点
         */
        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }

    // 根节点
    AVLNode root;
    // AVL树节点个数
    int size = 0;

    /**
     * 构造函数
     * @param root 指定根节点
     */
    public AVLTree(AVLNode root) {
        this.root = root;
    }

    /**
     * 新建一个AVL树(深复制)
     * @param node 待复制的AVL树节点
     * @return 新的AVL树节点
     */
    public AVLNode buildNewAVLNode(AVLNode node) {
        if(root == null) {
            return null;
        }
        AVLNode newNode = new AVLNode(node.key, node.value);
        AVLTree newRoot = new AVLTree(newNode);
        LinkedListQueue<AVLNode> queue = new LinkedListQueue<>(size);
        queue.offer(root);
        while (!queue.isEmpty()) {
            AVLNode node2 = queue.poll();
            newRoot.put(node2.key, node2.value);
            if (node2.left != null) {
                queue.offer(node2.left);
            }
            if (node2.right != null) {
                queue.offer(node2.right);
            }
        }
        return newNode;
    }

    /**
     * 新增AVL树节点
     * @param key key值
     * @param value 节点值
     */
    public void put(int key, Object value) {
        root = doPut(root, key, value);
    }

    private AVLNode doPut(AVLNode node, int key, Object value) {
        // 树为空直接新增树节点
        if (node == null) {
            return new AVLNode(key, value);
        }
        // 新增的树节点key值与原先某个树节点相同，直接替换value
        if (key == node.key) {
            node.value = value;
            return node;
        }
        // 判断key和节点key的大小，选择向左还是向右
        if (key < node.key) {
            node.left = doPut(node.left, key, value);
        } else {
            node.right = doPut(node.right, key, value);
        }
        // 更新树的高度
        updateHeight(node);
        // 平衡AVL树
        return balance(node);
    }

    /**
     * 删除树节点
     * @param key 删除树节点的key值
     */
    public void remove(int key) {
        root = doRemove(root, key);
    }

    private AVLNode doRemove(AVLNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            node.left = doRemove(node.left, key);
        } else if (node.key < key) {
            node.right = doRemove(node.right, key);
        } else {
            if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                AVLNode s = node.right;
                while (s.left != null) {
                    s = s.left;
                }
                s.right = doRemove(node.right, s.key);
                s.left = node.left;
                node = s;
            }
        }
        if (node == null) {
            return null;
        }
        updateHeight(node);
        return balance(node);
    }
    
    /**
     * 获取AVL树的高度(包括指定节点！)
     * @param node AVL树指定节点
     * @return 树节点高度
     */
    private int getHeight(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 更新AVL树高度
     * @param node AVL树指定节点
     */
    private void updateHeight(AVLNode node) {
        node.height = 1 + Integer.max(getHeight(node.left), getHeight(node.right));
    }

    /**
     * 获取AVL树平衡因子(平衡因子：左子树高度 - 右子树高度，两者差不超过1则表示平衡)
     * @param node AVL树指定节点
     * @return 1 / 0 / -1 平衡<p>
     * > 1 / < -1 不平衡
     */
    private int balanceFactor(AVLNode node) {
        return getHeight(node.left) - getHeight(node.right);
    }

    /*
    失衡的四种情况
        LL(根节点右旋一次)
            失衡节点(图中 8)的 bf > 1，即左边更高
            失衡节点的左孩子(图中 6)的 bf >= 0 即左孩子这边也是左边更高或等高
                8                       6
               / \                     / \
              6   9     8右旋          5  8
             / \        -->          /  / \
            5   7                   4  7   9
           /
          4
        RR(根节点左旋一次)
            失衡节点(图中 3)的 bf < -1，即右边更高
            失衡节点的右孩子(图中 6)的 bf <= 0，即右孩子这边右边更高或等高
                3                           5
               / \                         / \
              1   5         3左旋          3  6
                 / \        -->          / \   \
                4   6                   1   4   9
                     \
                      9
        LR(根节点左孩子节点先左旋变成LL，之后根节点右旋)
            失衡节点(图中 8)的 bf > 1，即左边更高
            失衡节点的左孩子(图中 6)的 bf < 0 即左孩子这边是右边更高
                8                       8                       4
               / \                     / \                     / \
              3   9     3左旋          4  9      8右旋         3   8
             / \         -->         / \         -->         /   / \
            1   4                   3   5                   1   5   9
                 \                 /
                  5               1
        RL(根节点右孩子节点先右旋变成RR，之后根节点左旋)
            失衡节点(图中 3)的 bf < -1，即右边更高
            失衡节点的右孩子(图中 6)的 bf > 0，即右孩子这边左边更高
                3                        3                       5
               / \                      / \                     / \
              1   6       6右旋        1   5          3左旋     3   6
                 / \      -->             / \        -->      / \   \
                5   9                    4   6               1   4   9
               /                              \
              4                                9
     */

    /**
     * AVL树指定节点左旋
     * @param parent 待左旋的树节点
     * @return 旋转后的树节点
     */
    private AVLNode leftRotate_Test(AVLNode parent) {
        // 找到待左旋节点parent、左旋节点右孩子parentRightChild，右孩子的左孩子parentRightChildLiftChild
        AVLNode parentRightChild = parent.right;
        AVLNode parentRightChildLiftChild = parentRightChild.left;
        // 左旋过程：替换父子指向
        parentRightChild.left = parent; // 上位：parentRightChild变为父节点(该过程顺序不能变！)
        parent.right = parentRightChildLiftChild; // 换父：parentRightChildLiftChild父节点变为parent
        return parentRightChild;
    }

    /**
     * AVL树指定节点左旋
     * @param node 待左旋的树节点
     * @return 旋转后的树节点
     */
    private AVLNode leftRotate(AVLNode node) {
        /*
            为了防止改变原有的AVL树，需要新建树节点传入，但左右旋和右左旋方法调用左旋和右旋方法时不能新建
        树节点(因为左右旋和右左旋会调用左旋和右旋各一次，一共会创建两次新的树节点造成树节点重复)，因此需要把左旋
        和右旋方法单独重新调用，并在调用时传入新建的树节点
         */
        return leftRotate_Test(buildNewAVLNode(node));
    }

    /**
     * AVL树指定节点右旋
     * @param parent 待右旋的树节点
     * @return 旋转后的树节点
     */
    private AVLNode rightRotate_Test(AVLNode parent) {
        // 找到待右旋节点parent、右旋节点左孩子parentLeftChild，左孩子的右孩子parentLeftChildRightChild
        AVLNode parentLeftChild = parent.left;
        AVLNode parentLeftChildRightChild = parentLeftChild.right;
        // 右旋过程：替换父子指向
        parentLeftChild.right = parent; // 上位：parentLeftChild变为父节点(该过程顺序不能变！)
        parent.left = parentLeftChildRightChild; // 换父：parentLeftChildRightChild父节点变为parent
        return parentLeftChild;
    }

    /**
     * AVL树指定节点右旋
     * @param node 待右旋的树节点
     * @return 旋转后的树节点
     */
    private AVLNode rightRotate(AVLNode node) {
        /*
            为了防止改变原有的AVL树，需要新建树节点传入，但左右旋和右左旋方法调用左旋和右旋方法时不能新建
        树节点(因为左右旋和右左旋会调用左旋和右旋各一次，一共会创建两次新的树节点造成树节点重复)，因此需要把左旋
        和右旋方法单独重新调用，并在调用时传入新建的树节点
         */
        return rightRotate_Test(buildNewAVLNode(node));
    }

    /**
     * AVL树左右旋(指定树节点左孩子节点先左旋，之后指定节点右旋)
     * @param node 待左右旋的树节点
     * @return 旋转后的树节点
     */
    private AVLNode leftRightRotate(AVLNode node) {
        // 左右旋时为了防止改变原有AVL树也要新建节点(但仅需新建一次即可)，因此需要调用leftRotate_Test方法
        node.left = leftRotate_Test(node.left);
        return rightRotate(node);
    }

    /**
     * AVL树右左旋(指定树节点右孩子节点先右旋，之后指定节点左旋)
     * @param node 待右左旋的树节点
     * @return 旋转后的树节点
     */
    private AVLNode rightLeftRotate(AVLNode node) {
        // 右左旋时为了防止改变原有AVL树也要新建节点(但仅需新建一次即可)，因此需要调用rightRotate_Test方法
        node.right = rightRotate_Test(node.right);
        return leftRotate(node);
    }

    /**
     * 自动平衡一次AVL树指定节点(平衡算法与AVL树相同！)<p>
     * 注：该方法不会改变传入的AVL树，而是会得到一颗新的AVL树树节点！
     * @param node 指定节点
     * @return 平衡后的树节点
     */
    private AVLNode balance_Test(AVLNode node) {
        // 空的AVL树
        if (node == null) {
            return null;
        }
        // 传入树节点平衡因子，用于判断左旋还是右旋
        int bf = balanceFactor(node);
        // 传入树节点的左孩子节点的平衡因子，用于判断是否需要左右旋
        int bfl = balanceFactor(node.left);
        // 传入树节点的右孩子节点的平衡因子，用于判断是否需要右左旋
        int bfr = balanceFactor(node.right);
        if (bf > 1 && bfl >= 0) {
            // LL：左子树比右子树高 且 左子树的左孩子节点多于右孩子节点，仅需根节点右旋
            return rightRotate(node);
        } else if (bf > 1) {
            // LR：左子树比右子树高 且 左子树的左孩子节点少于右孩子节点，先将左子树左旋，再将根节点右旋
            return leftRightRotate(node);
        } else if (bf < -1 && bfr >= 0) {
            // RL：左子树比右子树矮 且 右子树的左孩子节点多于右孩子节点，先将右子树右旋，再将根节点左旋
            return rightLeftRotate(node);
        } else if (bf < -1) {
            // RR：左子树比右子树矮 且 右子树的左孩子节点少于右孩子节点，仅需根节点左旋
            return leftRotate(node);
        }
        return node;
    }

    /**
     * 自动平衡AVL树指定节点(平衡算法与AVL树相同！)<p>
     * 注：该方法不会改变传入的AVL树，而是会得到一颗新的AVL树树节点！
     * @param node 指定节点
     * @return 平衡后的树节点
     */
    public AVLNode balance(AVLNode node) {
        // 空的AVL树
        if (node == null) {
            return null;
        }
        // 传入树节点平衡因子，用于判断左旋还是右旋
        int bf = balanceFactor(node);
        // 多次平衡AVL树
        while (bf > 1 || bf < -1) {
            node = balance_Test(node);
            bf = balanceFactor(node);
        }
        return node;
    }

}
