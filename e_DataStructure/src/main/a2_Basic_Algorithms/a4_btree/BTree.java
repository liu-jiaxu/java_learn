package main.a2_Basic_Algorithms.a4_btree;

import java.util.Arrays;

/**
 * ClassName: BTree
 * Package: main.a2_Basic_Algorithms.a4_btree
 * Description:B树类
 *
 * @Author: zgh296
 * @Create: 2023/6/20 - 16:30
 * @Version: v1.0
 */
public class BTree {

    /*
        1.存储使用
        B树结构非常适合应用于磁盘等大型存储器的高效操作
        AVL树和红黑树适合应用于内存存储
        2.100万的数据使用 avl 树来存储，树高是多少?
            假设AVL树是平衡的，那么树高将是log2(100万)=20。因此，AVL树的高度将是20。
        3.100万的数据，如果存储到B-树(最小度数是500)，那么树高大约是多少?
            如果将100万的数据存储到最小度数为500的B-树中，树的高度约为3。
            B-树的高度可以通过以下公式计算：h = log500(1000000) ≈ 3。因此，B-树的高度将是3
        4.Btree的特性
            度degree：树节点孩子数
            阶order：所有树节点中孩子数最大值
        特性1：每个节点 x 具有
            * 属性 n，表示节点 x 中 key 的个数
            * 属性 leaf，表示节点是否是叶子节点
            * 节点 key 可以有多个，以升序存储
        特性2：每个非叶子节点中的孩子数是 n + 1、叶子节点没有孩子
        特性3：最小度数t（节点的孩子数称为度）和节点中键数量的关系如下：
            | 最小度数t   | 键数量范围      |
            | --------- | -------------- |
            | 2         | 1 ~ 3          |
            | 3         | 2 ~ 5          |
            | 4         | 3 ~ 7          |
            | ...       | ...            |
            | t         | (t-1) ~ (2t-1) |
            其中，当节点中键数量达到其最大值时，即 3、5、7 ... 2t-1，需要分裂
        特性4：最小度数t（节点孩子数最小值）和阶o(节点孩子数最大值)的关系： o = 2t
        特性5：叶子节点的深度都相同
     */

    static class BNode {

        // 该节点是否为叶子节点
        boolean leaf = true;
        // 有效关键字key数量
        int keyNumber;
        // 树节点最小度数(树节点最少拥有的孩子数)
        int t;
        // 关键字数组
        int[] keys;
        // 孩子数组
        BNode[] children;

        /**
         * 构造方法
         * @param t 指定树节点最小孩子数t(t >= 2)<br>
         * 关键字个数最大值 = 2t - 1，孩子数最大值 = 2 * 孩子数最小值
         */
        public BNode(int t) {
            this.t = t;
            // 关键字个数最大值 = 2t - 1
            this.keys = new int[2 * t - 1];
            // 孩子数最大值 = 2 * 孩子数最小值
            this.children = new BNode[2 * t];
        }

        /**
         * 打印当前节点所有有效的key
         */
        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }

        /**
         * 多路查找BTree中对应key值的树节点
         * @param key key值
         * @return key值对应的树节点
         */
        public BNode get(int key) {
            int i = 0;
            // 因为所有树节点的key值均为升序，因此升序查找1即可
            while (i < keyNumber && keys[i] < key) {
                i++;
            }
            // 找到了对应的key值
            if (i < keyNumber && keys[i] == key) {
                return this;
            }
            // 若到了叶子节点还没有找到说明BTree中没有对应key值
            if (leaf) {
                return null;
            }
            // 当前树节点未找到，则去对应索引的子节点查找
            return children[i].get(key);
        }

        /**
         * 向指定位置插入key值
         * @param key 待插入的key值
         * @param index 指定索引位置
         */
        void insertKey(int key, int index) {
            // 将指定索引之后的数据向后挪一位，之后将key值插入指定索引位置
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);
            keys[index] = key;
            keyNumber++;
        }

        /**
         * 向指定位置插入child节点
         * @param child 待插入的child节点
         * @param index 指定索引位置
         */
        void insertChild(BNode child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;
        }

        // 移除指定index处的key
        int removeKey(int index) {
            int t = keys[index];
            System.arraycopy(keys, index + 1, keys, index, --keyNumber - index);
            return t;
        }

        // 移除最左边的key
        int removeLeftmostKey() {
            return removeKey(0);
        }

        // 移除最右边的key
        int removeRightmostKey() {
            return removeKey(keyNumber - 1);
        }

        // 移除指定index处的child
        BNode removeChild(int index) {
            BNode t = children[index];
            System.arraycopy(children, index + 1, children, index, keyNumber - index);
            children[keyNumber] = null;
            return t;
        }

        // 移除最左边的child
        BNode removeLeftmostChild() {
            return removeChild(0);
        }

        // 移除最右边的child
        BNode removeRightmostChild() {
            return removeChild(keyNumber);
        }

        // 复制当前节点的所有key和child到left
        void moveToLeft(BNode left) {
            int start = left.keyNumber;
            if (!leaf) {
                if (keyNumber + 1 >= 0) {
                    System.arraycopy(children, 0, left.children, start, keyNumber + 1);
                }
            }
            for (int i = 0; i < keyNumber; i++) {
                left.keys[left.keyNumber++] = keys[i];
            }
        }

        // index孩子处左边的兄弟
        BNode leftSibling(int index) {
            return index > 0 ? children[index - 1] : null;
        }

        // index孩子处右边的兄弟
        BNode rightSibling(int index) {
            return index == keyNumber ? null : children[index + 1];
        }

    }

    // 根节点
    BNode root;
    // 树节点最小度数
    int t;
    // 最小key数目
    final int MAX_KEY_NUMBER;
    // 最大key数目
    final int MIN_KEY_NUMBER;

    /**
     * 无参构造默认最小度数 = 2
     */
    public BTree() {
        this(2);
    }

    /**
     * 有参构造
     * @param t 最小度数
     */
    public BTree(int t) {
        this.t = t;
        root = new BNode(t);
        MAX_KEY_NUMBER = 2 * t - 1;
        MIN_KEY_NUMBER = t - 1;
    }

    /**
     * 判断key值是否存在
     * @param key key值
     * @return 判断结果
     */
    public boolean contains(int key) {
        return root.get(key) != null;
    }

    /**
     * 插入key值
     * @param key key值
     */
    public void put(int key) {
        doPut(root, key, null, 0);
    }

    private void doPut(BNode node, int key, BNode parent, int index) {
        int i = 0;
        // 先在当前节点查找，之后到其对应索引的孩子节点递归查找，直至找到进行插入操作
        while (i < node.keyNumber) {
            if (node.keys[i] == key) {
                return; // 更新：说明B树之前存在key值节点，直接更新即可(这里没有value，因此更新啥也不用做...)
            }
            if (node.keys[i] > key) {
                break; // 找到了插入位置，即为此时的 i
            }
            i++;
        }
        // 递归多次后到达了叶子节点，执行插入操作
        if (node.leaf) {
            node.insertKey(key, i);
        } else {
            // 不是叶子节点，继续向下递归
            doPut(node.children[i], key, node, i);
        }
        // 插入完成后，判断是否要进行分裂操作(有效关键字数目 == 最大key数目，即此时key数组满了)
        if (node.keyNumber == MAX_KEY_NUMBER) {
            split(node, parent, index);
        }
    }

    /**
     * <h3>分裂方法</h3>
     *
     * @param left   要分裂的节点
     * @param parent 分裂节点的父节点
     * @param index  分裂节点是第几个孩子
     */
    void split(BNode left, BNode parent, int index) {
        // 另外情况1：分裂的是根节点
        if (parent == null) {
            // 新根节点
            BNode newRoot = new BNode(t);
            newRoot.leaf = false;
            newRoot.insertChild(left, 0);
            this.root = newRoot;
            parent = newRoot;
        }
        // 1. 创建 right 节点，把 left 中 t 之后的 key 和 child 移动过去
        BNode right = new BNode(t);
        // 新建的节点和原先节点必在同一层，因此两者的leaf(是不是叶子节点)相同
        right.leaf = left.leaf;
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);
        // 另外情况2：分裂节点是非叶子的情况
        if (!left.leaf) {
            // 拷贝孩子节点
            System.arraycopy(left.children, t, right.children, 0, t);
            for (int i = t; i <= left.keyNumber; i++) {
                left.children[i] = null;
            }
        }
        // 有效关键字数量更新
        right.keyNumber = t - 1;
        left.keyNumber = t - 1;
        // 2. 中间的 key （t-1 处）插入到父节点
        int mid = left.keys[t - 1];
        parent.insertKey(mid, index);
        // 3. right 节点作为父节点的孩子
        parent.insertChild(right, index + 1);
        // 此处是否要考虑递归操作
    }

    /**
     * 删除key值
     * @param key key值
     */
    public void remove(int key) {
        doRemove(root, key, null, 0);
    }

    private void doRemove(BNode node, int key, BNode parent, int index) {
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] >= key) {
                break;
            }
            i++;
        }
        if (node.leaf) {
            if (notFound(node, key, i)) { // case 1
                return;
            }
            node.removeKey(i);  // case 2
        } else {
            if (notFound(node, key, i)) { // case 3
                doRemove(node.children[i], key, node, i);
            } else { // case 4
                BNode s = node.children[i + 1];
                while (!s.leaf) {
                    s = s.children[0];
                }
                int k = s.keys[0];
                node.keys[i] = k;
                doRemove(node.children[i + 1], k, node, i + 1);
            }
        }
        if (node.keyNumber < MIN_KEY_NUMBER) { // case 5
            balance(node, parent, index);
        }
    }

    private boolean notFound(BNode node, int key, int i) {
        return i >= node.keyNumber || node.keys[i] != key;
    }

    private void balance(BNode node, BNode parent, int i) {
        if (node == root) {
            if (root.keyNumber == 0 && root.children[0] != null) {
                root = root.children[0];
            }
            return;
        }
        BNode leftSibling = parent.leftSibling(i);
        BNode rightSibling = parent.rightSibling(i);
        if (leftSibling != null && leftSibling.keyNumber > MIN_KEY_NUMBER) {
            rightRotate(node, leftSibling, parent, i);
            return;
        }
        if (rightSibling != null && rightSibling.keyNumber > MIN_KEY_NUMBER) {
            leftRotate(node, rightSibling, parent, i);
            return;
        }
        if (leftSibling != null) {
            mergeToLeft(leftSibling, parent, i - 1);
        } else {
            mergeToLeft(node, parent, i);
        }
    }

    private void mergeToLeft(BNode left, BNode parent, int i) {
        BNode right = parent.removeChild(i + 1);
        left.insertKey(parent.removeKey(i), left.keyNumber);
        right.moveToLeft(left);
    }

    private void rightRotate(BNode node, BNode leftSibling, BNode parent, int i) {
        node.insertKey(parent.keys[i - 1], 0);
        if (!leftSibling.leaf) {
            node.insertChild(leftSibling.removeRightmostChild(), 0);
        }
        parent.keys[i - 1] = leftSibling.removeRightmostKey();
    }

    private void leftRotate(BNode node, BNode rightSibling, BNode parent, int i) {
        node.insertKey(parent.keys[i], node.keyNumber);
        if (!rightSibling.leaf) {
            node.insertChild(rightSibling.removeLeftmostChild(), node.keyNumber + 1);
        }
        parent.keys[i] = rightSibling.removeLeftmostKey();
    }

}
