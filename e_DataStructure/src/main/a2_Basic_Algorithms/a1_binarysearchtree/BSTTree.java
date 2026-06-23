package main.a2_Basic_Algorithms.a1_binarysearchtree;

import java.lang.reflect.Method;
import java.util.*;

/**
 * ClassName: BSTTree
 * Package: main.a2_Basic_Algorithms.a1_binarysearchtree
 * Description:二叉搜索树类(binary search tree)
 *
 * @Author: zgh296
 * @Create: 2023/6/12 - 15:53
 * @Version: v1.0
 */
public class BSTTree<K extends Comparable<K>, V> {
    // key的泛型为K，value的泛型为V
    // K extends Comparable<K>：指定泛型K的上限为Comparable<K>，即K必须是Comparable<K>的子类
    // 上限 extends，下限 super

    /*
    二叉搜索树（也称二叉排序树）是符合下面特征的二叉树：
        1. 树节点增加 key 属性，用来比较谁大谁小，key 不可以重复，不可以为 null
        2. 对于任意一个树节点，它的 key 比左子树的 key 都大，同时也比右子树的 key 都小
     */

    // 二叉搜索树节点(如需使用注意修改权限！)
    public static class BSTNode<K, V> {

        // 属性key，不能为null
        K key;
        // 值
        V value;
        // 父节点
        BSTNode<K, V> parent;
        // 左孩子节点
        BSTNode<K, V> left;
        // 右孩子节点
        BSTNode<K, V> right;

        /**
         * 传入key的构造方法
         * @param key key值(注：key值不允许为null！)
         */
        public BSTNode(K key) {
            if (key == null) {
                throw new IllegalArgumentException("参数key不能为null！");
            }
            this.key = key;
        }

        /**
         * 传入key - value的构造方法
         * @param key key值(注：key值不允许为null！)
         * @param value 节点值
         */
        public BSTNode(K key, V value) {
            if (key == null) {
                throw new IllegalArgumentException("参数key不能为null！");
            }
            this.key = key;
            this.value = value;
        }

        /**
         * 传入key - value以及根节点的构造方法
         * @param key key值(注：key值不允许为null！)
         * @param value 节点值
         * @param parent 根节点
         */
        public BSTNode(K key, V value, BSTNode<K, V> parent) {
            if (key == null) {
                throw new IllegalArgumentException("参数key不能为null！");
            }
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * 传入key - value以及左右孩子节点的构造方法
         * @param key key值(注：key值不允许为null！)
         * @param value 节点值
         * @param left 左孩子节点
         * @param right 右孩子节点
         */
        public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
            if (key == null) {
                throw new IllegalArgumentException("参数key不能为null！");
            }
            this.key = key;
            this.value = value;
            parent = null;
            this.left = left;
            this.right = right;
        }

        /**
         * 全参构造方法
         * @param key key值(注：key值不允许为null！)
         * @param value 节点值
         * @param parent 父节点
         * @param left 左孩子节点
         * @param right 右孩子节点
         */
        public BSTNode(K key, V value, BSTNode<K, V> parent, BSTNode<K, V> left, BSTNode<K, V> right) {
            if (key == null) {
                throw new IllegalArgumentException("参数key不能为null！");
            }
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        /**
         * 获取树节点key值
         * @return key值
         */
        public K getKey() {
            return key;
        }

        /**
         * 获取树节点value值
         * @return 树点值
         */
        public V getValue() {
            return value;
        }

        /**
         * 获取父节点
         * @return 父节点
         */
        public BSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * 获取左孩子节点
         * @return 左孩子节点
         */
        public BSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * 获取右孩子节点
         * @return 右孩子节点
         */
        public BSTNode<K, V> getRight() {
            return right;
        }

        /**
         * 设置树节点key值
         * @param key key值
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * 设置树节点value值
         * @param value 节点值
         */
        public void setValue(V value) {
            this.value = value;
        }

        /**
         * 设置父节点
         * @param parent 父节点
         */
        public void setParent(BSTNode<K, V> parent) {
            this.parent = parent;
        }

        /**
         * 设置左孩子节点
         * @param left 左孩子节点
         */
        public void setLeft(BSTNode<K, V> left) {
            this.left = left;
        }

        /**
         * 设置右孩子节点
         * @param right 右孩子节点
         */
        public void setRight(BSTNode<K, V> right) {
            this.right = right;
        }

    }

    // 根节点
    private BSTNode<K, V> root;
    // 二叉搜索树大小(节点数)
    private int size = 0;

    /**
     * 无参构造
     */
    public BSTTree() {
    }

    /**
     * 传入根节点的构造方法
     * @param root 根节点
     */
    public BSTTree(BSTNode<K, V> root) {
        this.root = root;
        setSize(root);
    }

    /**
     * 传入根节点和二叉搜索树大小的构造方法，同时检查size是否小于0报错
     * @param root 根节点
     * @param size 二叉搜索树大小(树节点数)
     */
    public BSTTree(BSTNode<K, V> root, int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size参数小于0！");
        }
        this.root = root;
        this.size = size;
    }

    /**
     * 传入根节点的构造方法
     * @param root 根节点
     * @param size 二叉搜索树包含节点数(包括根节点)
     * @param flag 是否检查传入的size大小，true：检查 / false：不检查
     */
    public BSTTree(BSTNode<K, V> root, int size, boolean flag) {
        if (size < 0) {
            throw new IllegalArgumentException("size参数小于0！");
        }
        // 注：要先传值在检查，否则未传值时getTreeNodeNumber方法中LinkedListStack栈容量未赋值，默认大小为0影响判断！
        this.root = root;
        this.size = size;
        if (flag && getTreeNodeNumber(root) != size) {
            throw new IllegalArgumentException("size与二叉搜索树节点数不符！");
        }
    }

    /**
     * 该方法用于无size参数的构造方法，作用是接收该二叉搜索树的大小并设置size属性
     * @param node 二叉搜索树
     */
    private void setSize(BSTNode<K, V> node) {
        // 相当于前序遍历一次获取size
        if (node == null) {
            return;
        }
        size ++;
        setSize(node.left); // 左
        setSize(node.right); // 右
    }

    /**
     * 获取二叉搜索树节点数，用于新建二叉搜索树对象时测试节点数与传入size是否匹配，除此以外可以直接使用size()方法获取节点数
     * @param root 二叉搜索树
     */
    public int getTreeNodeNumber(BSTNode<K, V> root) {
        int number = 0;
        LinkedListStack<BSTNode<K, V>> stack = new LinkedListStack<>(size);
        BSTNode<K, V> curr = root;
        // 该思路与executeSearch搜索方法相同！
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                number ++;
                curr = curr.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                curr = pop.right;
            }
        }
        return number;
    }

    /**
     * 获取当前二叉搜索树的根节点
     * @return 二叉搜索树的根节点(即二叉搜索树本身)
     */
    public BSTNode<K, V> getRoot() {
        return root;
    }

    /**
     * 获取二叉搜索树的大小(节点数)
     * @return 二叉搜索树大小
     */
    public int size() {
        return size;
    }

    /**
     * 判断二叉搜索树是否为空
     * @return 判断结果
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向二叉搜索树中存储key - value键值对，当key存在时，则修改原先的value值，若key不存在，则直接添加新的key - value
     * @param key key值(注：key值不允许为null！)
     * @param value 节点值
     */
    public void put(K key, V value) {
        BSTNode<K, V> node = root;
        // 新建树节点用于记录父节点，便于新增节点时指定其父节点
        // 此处及delete方法不直接使用parent属性的原因是为了防止构造方法传参时未指定parent父节点
        BSTNode<K, V> parent = null;
        // 泛型不能比较null值，要提前判断！
        if (key == null) { // 构造函数设置了key != null，因此无需判断node.key == null
            throw new IllegalArgumentException("参数key不能为null！");
        }
        // 二叉搜索树为空时，直接添加新节点为根节点即可！
        if (node == null) {
            root = new BSTNode<>(key, value, null, null, null);
            size ++;
            return;
        }
        while (node != null) {
            // 加入泛型后，无法直接比较，需要利用compareTo方法
            int result = key.compareTo(node.key);
                /*
                result
                    == -1(或 < 0) key < node.key
                    == 0          key = node.key
                    == 1(或 > 0)  key > node.key
                 */
            // parent节点为循环更新node节点的上一个的节点
            parent = node;
            if (result < 0) {
                node = node.left;
            } else if (result > 0) {
                node = node.right;
            } else {
                // 1.树中有key，直接更新值
                node.value = value;
                return;
            }
        }
        // 2.树中无key，新插入键值
        // 判断父节点parent是新增节点的左/右父节点
        int resultP = key.compareTo(parent.key);
        if (resultP < 0) {
            parent.left = new BSTNode<>(key, value, parent, null, null);
            size ++;
        } else if(resultP > 0) {
            parent.right = new BSTNode<>(key, value, parent, null, null);
            size ++;
        }
    }

    /**
     * 向二叉搜索树中插入新节点，当新节点key存在时，则修改原先节点的value值，若新节点key不存在，则直接添加新节点
     * @param node 插入的新节点(注：新节点key值不允许为null！)
     */
    public void put(BSTNode<K, V> node) {
        if (node == null) {
            throw new IllegalArgumentException("参数node不能为null！");
        }
        BSTNode<K, V> node2 = root;
        // 新建树节点用于记录父节点，便于新增节点时指定其父节点
        // 此处及delete方法不直接使用parent属性的原因是为了防止构造方法传参时未指定parent父节点
        BSTNode<K, V> parent = null;
        // 二叉搜索树为空时，直接添加新节点为根节点即可！
        if (root == null) {
            root = new BSTNode<>(node.key, node.value, null, null, null);
            size ++;
            return;
        }
        // 泛型不能比较null值，要提前判断！
        if (node.key == null) { // 构造函数设置了key != null，因此无需判断node.key == null
            throw new IllegalArgumentException("参数key不能为null！");
        }
        while (node2 != null) {
            // 加入泛型后，无法直接比较，需要利用compareTo方法
            int result = (node.key).compareTo(node2.key);
                /*
                result
                    == -1(或 < 0) key < node.key
                    == 0          key = node.key
                    == 1(或 > 0)  key > node.key
                 */
            // parent节点为循环更新node节点的上一个的节点
            parent = node2;
            if (result < 0) {
                node2 = node2.left;
            } else if (result > 0) {
                node2 = node2.right;
            } else {
                // 1.树中有key，直接更新值
                node2.value = node.value;
                return;
            }
        }
        // 2.树中无key，新插入键值
        // 判断父节点parent是新增节点的左/右父节点
        int resultP = (node.key).compareTo(parent.key);
        if (resultP < 0) {
            parent.left = new BSTNode<>(node.key, node.value, parent, null, null);
            size ++;
        } else if(resultP > 0) {
            parent.right = new BSTNode<>(node.key, node.value, parent, null, null);
            size ++;
        }
    }

    /**
     * 托孤方法
     * @param parent 删除节点的父节点
     * @param deleted 删除节点
     * @param child 删除节点的子节点(被顶上去的节点，删除操作后替换删除节点位置的子节点)
     */
    private void shift(BSTNode<K, V> parent, BSTNode<K, V> deleted, BSTNode<K, V> child) {
        // 待删除节点为根节点时，其无父节点，直接令其根节点 = 子节点即可
        if (parent == null) {
            root = child;
        } else if (parent.left == deleted) { // 下面两种情况对应删除节点仅有唯一孩子时
            // 删除节点仅有左/右一个孩子，且删除节点为父节点的左孩子，则将删除节点唯一的孩子托孤给父节点parent作为其左孩子
            parent.left = child;
        } else if (parent.right == deleted) {
            // 删除节点仅有左/右一个孩子，且删除节点为父节点的右孩子，则将删除节点唯一的孩子托孤给父节点parent作为其右孩子
            parent.right = child;
        }
        // 当删除节点无孩子节点时，则传入的child参数必为null，此时无论怎么赋值，均满足此情况
    }

    /**
     * 二叉搜索树删除节点操作
     * @param key 删除节点的key值
     * @return 删除节点的值
     */
    public V delete(K key) {
        BSTNode<K, V> node = root;
        // 新建树节点用于记录删除节点的父节点
        BSTNode<K, V> parent = null;
        // 二叉搜索树为空时，提前进行报错处理
        if (node == null) {
            throw new NullPointerException("此二叉搜索树为空！");
        }
        // 泛型不能比较null值，要提前判断！
        if (key == null) { // 构造函数设置了key != null，因此无需判断node.key == null
            throw new IllegalArgumentException("参数key不能为null！");
        }
        // 查找待删除节点
        while (node != null) {
            // 加入泛型后，无法直接比较，需要利用compareTo方法
            int result = key.compareTo(node.key);
                /*
                result
                    == -1(或 < 0) key < node.key
                    == 0          key = node.key
                    == 1(或 > 0)  key > node.key
                 */
            if (result < 0) {
                parent = node;
                node = node.left;
            } else if (result > 0) {
                parent = node;
                node = node.right;
            } else {
                /*
                    此处不写parent = node的原因是一旦在二叉搜索树中找到对应节点，再给parent节点赋值时则两个节点相同，
                不再是父子节点关系了
                 */
                break;
            }
        }
        // 遍历结束后未找到要删除的树节点，则返回null
        if (node == null) {
            return null;
        }
        // 删除操作
        /*
        要删除某节点（称为 D），必须先找到被删除节点的父节点，这里称为 Parent
            1. 删除节点左右孩子都没有(其实已经被涵盖在情况2、情况3 当中)，把 null 托孤给 Parent
            2. 删除节点没有左孩子，将右孩子托孤给 Parent
            3. 删除节点没有右孩子，将左孩子托孤给 Parent
            4. 删除节点左右孩子都有，可以将它的后继节点（称为 S）托孤给 Parent，设 S 的父亲为 SP，又分两种情况
                (1) SP 就是被删除节点，此时 D 与 S 紧邻，只需将 S 托孤给 Parent
                (2) SP 不是被删除节点，此时 D 与 S 不相邻，此时需要将 S 的后代托孤给 SP，再将 S 托孤给 Parent
                注：由于二叉搜索树数据存储结构，因此后继节点S一定只有右子节点，不用考虑左子节点的情况！
        */
        /*
        二叉搜索树四种删除情况示例：
            1. 删除节点左右孩子都没有(删除节点后，null值上顶)
                    4                            4
                   / \          删除3           /  \
                  3   6          -->         null  6
                       \                            \
                        8                            8
            2. 删除节点没有左孩子(删除节点右孩子上顶)
                    4                            4
                   / \          删除6           /  \
                  2   6          -->          2    8
                 / \   \                     / \
                1   3   8                   1   3
            3. 删除节点没有右孩子(删除节点左孩子上顶)
                    4                            4
                   / \          删除2           /  \
                  2   6          -->          1    6
                 /     \                            \
                1       8                            8
            4(1). 删除节点左右孩子都有，SP就是被删除节点(后继直接上顶)
                    7                          7
                   /                          /
                  4                          5
                 / \           删除4        /  \
                2   5           -->       2    6
               / \   \                   / \
              1   3   6                  1   3
            4(2). 删除节点左右孩子都有，SP不是被删除节点(先处理后继后代(后代节点直接代替SP位置即可)，再将后继上顶)
                    4                           5
                   / \                         / \
                  2   8                       2   8
                     / \        删除4            / \
                    7   9        -->            7  9
                   /                           /
                  5                           6
                   \
                    6
         */
        if (node.left == null && node.right == null) {
            // 1. 删除节点左右孩子都没有，此情况在执行情况2、23时获得的结果均相同且正确
            // 但需要注意父子关系修改不能使用情况2、3，需要单独写
            shift(parent, node, null);
        } else if (node.left == null) {
            // 2. 删除节点没有左孩子
            shift(parent, node, node.right);
            // 删除操作后，重新分配受影响节点的父子关系
            node.right.parent = parent;
        } else if (node.right == null) {
            // 3. 删除节点没有右孩子
            shift(parent, node, node.left);
            // 删除操作后，重新分配受影响节点的父子关系
            node.left.parent = parent;
        } else {
            // 4. 删除节点左右孩子都有
            /*
            三步走
                1.找到被删除节点的后继节点
                2.若删除节点和后继节点不相邻，处理后继节点孩子节点的位置
                3.后继节点上顶代替删除节点
             */
            // 后继节点s
            BSTNode<K, V> s = node.right;
            // 后继节点父节点sp(此处不直接用s.parent还是为了防止构造函数未传参parent)
            BSTNode<K, V> sp = null;
            while (s.left != null) {
                sp = s;
                s = s.left;
            }
            // 判断删除和后继节点是否相邻以决定是否处理后继节点孩子节点的位置
            if (sp != node) {
                // 这个托孤方法是为了先剔除原位置后继节点
                shift(sp, s, s.right);
                // 重新分配受影响节点的父子关系
                if (s.right != null) {
                    s.right.parent = sp;
                }
                // 这一步是将剔除的后继节点的右节点指定为删除节点的右节点，即后继结点上位
                s.right = node.right;
                node.right.parent = s;
            }
            // 后继节点上顶代替删除节点，同时重新分配受影响节点的父子关系
            shift(parent, node, s);
            s.parent = parent;
            // 这一步是为了指定上位的后继结点的左孩子节点
            s.left = node.left;
            node.left.parent = s;
        }
        size --;
        return node.value;
    }

    /**
     * 递归删除二叉搜索树的节点(注：该删除方法删除节点后没有指定改动节点的父节点！)
     * @param key 删除节点的key值
     * @return 删除节点的value值
     */
    public V delete2(K key) {
        ArrayList<V> result = new ArrayList<>();
        root = doDelete(root, key, result);
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * 内部递归方法，用于删除二叉搜索树节点
     * @param node 二叉搜索树
     * @param key key值
     * @param result 该集合用来保存被删除节点的值
     * @return node节点
     */
    private BSTNode<K, V> doDelete(BSTNode<K, V> node, K key, ArrayList<V> result) {
        if (node == null) {
            return null;
        }
        if (key == null) {
            throw new IllegalArgumentException("参数key不能为null！");
        }
        // 查找待删除节点
        int r = key.compareTo(node.key);
        if (r < 0) {
            node.left = doDelete(node.left, key, result);
            return node;
        } else if (r > 0) {
            node.right = doDelete(node.right, key, result);
            return node;
        }
        result.add(node.value);
        if (node.left != null && node.right != null) {
            BSTNode<K, V> s = node.right;
            while (s.left != null) {
                s = s.left;
            }
            s.right = doDelete(node.right, s.key, new ArrayList<>());
            s.left = node.left;
            return s;
        }
        size --;
        return node.left != null ? node.left : node.right;
    }

    /**
     * 删除二叉搜索树所有树节点
     */
    public void deleteAll() {
        root = null;
        size = 0;
    }

    /**
     * 更新二叉搜索树节点的key值
     * @param key 原先树节点的key值
     * @param updateKey 更新后的key值
     */
    public void updateKey(K key, K updateKey) {
        if (key == null || updateKey == null) {
            throw new IllegalArgumentException("参数key和updateKey不能为null！");
        }
        V value = doGet(root, key);
        if (delete(key) != null) {
            put(updateKey, value);
        }
    }

    /**
     * 更新二叉搜索树节点的value值
     * @param key 原先树节点的key值
     * @param updateValue 更新后的value值
     */
    public void updateValue(K key, V updateValue) {
        if (key == null) {
            throw new IllegalArgumentException("参数key和updateKey不能为null！");
        }
        /*该方法复杂度较高，直接单层遍历修改key对应的value值即可
        if (delete(key) != null) {
            put(key, updateValue);
        }*/
        BSTNode<K, V> node = root;
        int result = key.compareTo(node.key);

        while (node != null) {
            if (result > 0) {
                node = node.right;
            } else if (result < 0) {
                node = node.right;
            } else {
                node.value = updateValue;
                return;
            }
        }

    }

    /**
     * 更新二叉搜索树节点的key - value值
     * @param key 原先树节点的key值
     * @param updateKey 更新后的key值
     * @param updateValue 更新后的value值
     */
    public void update(K key, K updateKey, V updateValue) {
        // 注：更新方法需要先删除原树节点，再新增树节点，防止破坏二叉搜索树数据存储结构
        if (key == null || updateKey == null) {
            throw new IllegalArgumentException("参数key和updateKey不能为null！");
        }
        if (key == updateKey) {
            updateValue(key, updateValue);
            return;
        }
        if (delete(key) != null) {
            put(updateKey, updateValue);
        }
    }

    /**
     * 将二叉搜索树中所有的指定value值改为新的value值
     * @param value 要修改的value值
     * @param updateValue 更新后的value值
     * @return 更新值的次数
     */
    public int updateAllValue(V value, V updateValue) {
        // 注：此更新方法因为是修改value，因此不会破坏二叉搜索树结构，不用先删再插，直接遍历改值即可
        LinkedListQueue<BSTNode<K, V>> queue = new LinkedListQueue<>(size);
        int count = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            BSTNode<K, V> node = queue.poll();
            if (node.value == value) {
                node.value = updateValue;
                count ++;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return count;
    }

    /**
     * 具体查找二叉搜索树中key对应的节点值的方法
     * @param node 起始节点
     * @param key key值
     * @return 查找结果：value值
     */
    private V doGet(BSTNode<K, V> node, K key) {
        // 递归结束条件：没有找到对应key时返回null
        if (node == null) {
            return null;
        }
        // 泛型不能比较null值，要提前判断！
        if (key == null) { // 构造函数设置了key != null，因此无需判断node.key == null
            throw new IllegalArgumentException("参数key不能为null！");
        }
        // 加入泛型后，无法直接比较，需要利用compareTo方法
        int result = key.compareTo(node.key);
        /*
        result
            == -1(或 < 0) key < node.key
            == 0          key = node.key
            == 1(或 > 0)  key > node.key
         */
        /*
        三种情况：
            1.当前查找key < 当前节点key，继续向左查找
            2.当前节点key < 当前查找key，继续向右查找
            3.两者相等，表示找到对应key，返回对应value值即可
         */
        if (result < 0) {
            return doGet(node.left, key);
        } else if (result > 0) {
            return doGet(node.right, key);
        } else {
            return node.value;
        }
    }

    /**
     * 递归查找二叉搜索树中key对应的节点值
     * @param key key值
     * @return key对应的节点值value
     */
    public V get(K key) {
        return doGet(root, key);
    }

    /**
     * 查找二叉搜索树中key对应的节点值(非递归实现)
     * @param key key值
     * @return key对应的节点值value
     */
    public V get2(K key) {
        BSTNode<K, V> node = root;
        // 泛型不能比较null值，要提前判断！
        if (key == null) { // 构造函数设置了key != null，因此无需判断node.key == null
            throw new IllegalArgumentException("参数key不能为null！");
        }
        while (node != null) {
            // 加入泛型后，无法直接比较，需要利用compareTo方法
            int result = key.compareTo(node.key);
                /*
                result
                    == -1(或 < 0) key < node.key
                    == 0          key = node.key
                    == 1(或 > 0)  key > node.key
                 */
            if (result < 0) {
                node = node.left;
            } else if (result > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    /**
     * 用于递归查找二叉搜索树中最小的key值
     * @param node 二叉搜索树任意树节点
     * @return 最小的key值
     */
    private K doMinKey(BSTNode<K, V> node) {
        if (node == null) {
            return null;
        }
        // 左边已走到头
        if (node.left == null) {
            return node.key;
        }
        return doMinKey(node.left);
    }

    /**
     * 用于递归查找二叉搜索树中最小的key对应的value
     * @param node 二叉搜索树任意树节点
     * @return 最小key的value
     */
    private V doMinValue(BSTNode<K, V> node) {
        if (node == null) {
            return null;
        }
        // 左边已走到头
        if (node.left == null) {
            return node.value;
        }
        return doMinValue(node.left);
    }

    /**
     * 查找二叉搜索树中最小的key值
     * @return 最小的key值
     */
    public K getMinKey() {
        return doMinKey(root);
    }

    /**
     * 查找二叉搜索树中最小的key对应的value
     * @return 最小key的value
     */
    public V getMinValue() {
        return doMinValue(root);
    }

    /**
     * 用于递归查找二叉搜索树中最大的key值
     * @param node 二叉搜索树任意树节点
     * @return 最大的key值
     */
    private K doMaxKey(BSTNode<K, V> node) {
        if(node == null) {
            return null;
        }
        if(node.right == null) {
            return node.key;
        }
        return doMaxKey(node.right);
    }

    /**
     * 用于递归查找二叉搜索树中最大的key对应的value
     * @param node 二叉搜索树任意树节点
     * @return 最大key的value
     */
    private V doMaxValue(BSTNode<K, V> node) {
        if(node == null) {
            return null;
        }
        if(node.right == null) {
            return node.value;
        }
        return doMaxValue(node.right);
    }

    /**
     * 查找二叉搜索树中最大的key值
     * @return 最大的key值
     */
    public K getMaxKey() {
        return doMaxKey(root);
    }

    /**
     * 查找二叉搜索树中最大的key对应的value
     * @return 最大key的value
     */
    public V getMaxValue() {
        return doMaxValue(root);
    }

    /**
     * 获取指定key值的前任节点的key值<p>
     * 例：指定参数key = 3，则前任节点为key = 2，获取此时key值
     * @param key key值
     * @return 指定key值的前任节点的key值
     */
    public K getPredecessorKey(K key) {
        // 该方法与下方的getPredecessorValue处理方式相同，不再介绍
        BSTNode<K, V> node = root;
        BSTNode<K, V> ancestorFromLeft = null;
        if (key == null) {
            throw new IllegalArgumentException("参数key不能为null！");
        }
        while (node != null) {
            int result = key.compareTo(node.key);
            if (result < 0) {
                node = node.left;
            } else if (result > 0) {
                ancestorFromLeft = node;
                node = node.right;
            } else {
                break;
            }
        }
        if (node == null) {
            System.out.println("key = " + key + "在二叉搜索树中无对应树节点！");
            return null;
        }
        if (node.left != null) {
            return doMaxKey(node.left);
        }
        return ancestorFromLeft != null ? ancestorFromLeft.key : null;
    }

    /**
     * 获取指定key值的前任节点的value值<p>
     * 例：指定参数key = 3，则前任节点为key = 2，获取此时key = 2节点的value值
     * @param key key值
     * @return 指定key值的前任节点的value值
     */
    public V getPredecessorValue(K key) {
        BSTNode<K, V> node = root;
        // 设置祖先节点，便于节点没有左子树时的判断
        BSTNode<K, V> ancestorFromLeft = null;
        // 泛型不能比较null值，要提前判断！
        if (key == null) { // 构造函数设置了key != null，因此无需判断node.key == null
            throw new IllegalArgumentException("参数key不能为null！");
        }
        while (node != null) {
            // 加入泛型后，无法直接比较，需要利用compareTo方法
            int result = key.compareTo(node.key);
                /*
                result
                    == -1(或 < 0) key < node.key
                    == 0          key = node.key
                    == 1(或 > 0)  key > node.key
                 */
            if (result < 0) {
                node = node.left;
            } else if (result > 0) {
                // 每次循环都会获取到自左而来的祖先节点，记录祖先节点便于节点没有左子树情况的判断
                ancestorFromLeft = node;
                node = node.right;
            } else {
                // 此时找到了为key值的树节点
                break;
            }
        }
        // 若没有找到对应key值的树节点，说明二叉搜索树中无对应节点，应给予提示
        if (node == null) {
            System.out.println("key = " + key + "在二叉搜索树中无对应树节点！");
            return null;
        }
        /*
        二叉搜索树示例：
                        4
                      /   \
                     2     7
                    / \   / \
                   1  3  6   8
                        /
                       5
        1. 节点有左子树，此时前驱节点就是左子树的最大值，图中属于这种情况的有
            2 的前驱是 1
            4 的前驱是 3
            6 的前驱是 5
            7 的前驱是 6
        2. 节点没有左子树，若离它最近的祖先自从左而来，此祖先即为前驱，如
            3 的祖先 2 自左而来，前驱 2
            5 的祖先 4 自左而来，前驱 4
            8 的祖先 7 自左而来，前驱 7
            1 没有这样的祖先，前驱 null
         */
        // 找到了为key值的树节点时，分两种情况
        // 1. 节点有左子树，此时前驱节点就是左子树的最大值
        if (node.left != null) {
            return doMaxValue(node.left);
        }
        // 2. 节点没有左子树，若离它最近的祖先自从左而来，此祖先即为前驱
        /*
            在上述while循环结束后，若祖先节点ancestorFromLeft不为null，说明找到了最近一次自左而来的祖先，直接
        返回即可，若未找到说明指定key值的节点没有自左而来的祖先，需返回null
         */
        return ancestorFromLeft != null ? ancestorFromLeft.value : null;
    }

    /**
     * 获取指定key值的后继节点的key值<p>
     * 例：指定参数key = 3，则后继节点为key = 4，获取此时key值
     * @param key key值
     * @return 指定key值的后继节点的key值
     */
    public K getSuccessorKey(K key) {
        // 该方法与下方的getSuccessorValue处理方式相同，不再介绍
        BSTNode<K, V> node = root;
        BSTNode<K, V> ancestorFromRight = null;
        if (key == null) {
            throw new IllegalArgumentException("参数key不能为null！");
        }
        while (node != null) {
            int result = key.compareTo(node.key);
            if (result < 0) {
                ancestorFromRight = node;
                node = node.left;
            } else if (result > 0) {
                node = node.right;
            } else {
                break;
            }
        }
        if (node == null) {
            System.out.println("key = " + key + "在二叉搜索树中无对应树节点！");
            return null;
        }
        if (node.right != null) {
            return doMinKey(node.right);
        }
        return ancestorFromRight != null ? ancestorFromRight.key : null;
    }

    /**
     * 获取指定key值的后继节点的value值<p>
     * 例：指定参数key = 3，则后继节点为key = 4，获取此时key = 4节点的value值
     * @param key key值
     * @return 指定key值的后继节点的value值
     */
    public V getSuccessorValue(K key) {
        BSTNode<K, V> node = root;
        // 设置祖先节点，便于节点没有右子树时的判断
        BSTNode<K, V> ancestorFromRight = null;
        // 泛型不能比较null值，要提前判断！
        if (key == null) { // 构造函数设置了key != null，因此无需判断node.key == null
            throw new IllegalArgumentException("参数key不能为null！");
        }
        while (node != null) {
            // 加入泛型后，无法直接比较，需要利用compareTo方法
            int result = key.compareTo(node.key);
                /*
                result
                    == -1(或 < 0) key < node.key
                    == 0          key = node.key
                    == 1(或 > 0)  key > node.key
                 */
            if (result < 0) {
                // 每次循环都会获取到自右而来的祖先节点，记录祖先节点便于节点没有右子树情况的判断
                ancestorFromRight = node;
                node = node.left;
            } else if (result > 0) {
                node = node.right;
            } else {
                // 此时找到了为key值的树节点
                break;
            }
        }
        // 若没有找到对应key值的树节点，说明二叉搜索树中无对应节点，应给予提示
        if (node == null) {
            System.out.println("key = " + key + "在二叉搜索树中无对应树节点！");
            return null;
        }
        /*
        二叉搜索树示例：
                        5
                      /   \
                     2     7
                    / \   / \
                   1  3  6   8
                       \
                        4
        1. 节点有右子树，此时后继节点即为右子树的最小值，如
             2 的后继 3
             3 的后继 4
             5 的后继 6
             7 的后继 8
         2. 节点没有右子树，若离它最近的祖先自从右而来，此祖先即为后继，如
             1 的祖先 2 自右而来，后继 2
             4 的祖先 5 自右而来，后继 5
             6 的祖先 7 自右而来，后继 7
             8 没有这样的祖先，后继 null
         */
        // 找到了为key值的树节点时，分两种情况
        // 1. 节点有右子树，此时后继节点即为右子树的最小值
        if (node.right != null) {
            return doMinValue(node.right);
        }
        // 2. 节点没有右子树，若离它最近的祖先自从右而来，此祖先即为后继
        /*
            在上述while循环结束后，若祖先节点ancestorFromLeft不为null，说明找到了最近一次自右而来的祖先，直接
        返回即可，若未找到说明指定key值的节点没有自右而来的祖先，需返回null
         */
        return ancestorFromRight != null ? ancestorFromRight.value : null;
    }

    /**
     * 传入一个key值，获取二叉搜索树中所有key值小于(等于)传入key值的树节点的value值
     * @param key key值
     * @param equal 该参数用于决定是否获取等于传入key值的树节点<br>
     *              &emsp true：获取<br>
     *              &emsp false：不获取
     * @return 一个升序集合result，存储所有满足条件的树节点value值
     */
    public List<V> getLessValue(K key, boolean equal) {
        if (key == null) {
            throw new IllegalArgumentException("参数key不能为null！");
        }
        ArrayList<V> result = new ArrayList<>();
        BSTNode<K, V> node = root;
        // 该栈用于中间操作时处理找到的树节点
        LinkedList<BSTNode<K, V>> stack = new LinkedList<>();
        // 中序遍历二叉搜索树
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                // 处理值，若结果不满足，则表示已经遇到大于等于key的值了可以不用操作直接退出遍历即可
                int r = (pop.key).compareTo(key);
                if (r < 0) {
                    result.add(pop.value);
                } else if (r == 0 && equal) {
                    result.add(pop.value);
                } else {
                    break;
                }
                node = pop.right;
            }
        }
        return result;
    }

    /**
     * 传入一个key值，获取二叉搜索树中所有key值大于(等于)传入key值的树节点的value值
     * @param key key值
     * @param equal 该参数用于决定是否获取等于传入key值的树节点<br>
     *              &emsp true：获取<br>
     *              &emsp false：不获取
     * @return 一个降序集合result，存储所有满足条件的树节点value值
     */
    public List<V> getGreaterValue(K key, boolean equal) {
        if (key == null) {
            throw new IllegalArgumentException("参数key不能为null！");
        }
        ArrayList<V> result = new ArrayList<>();
        BSTNode<K, V> node = root;
        // 该栈用于中间操作时处理找到的树节点
        LinkedList<BSTNode<K, V>> stack = new LinkedList<>();
        // 中序遍历二叉搜索树
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.right;
            } else {
                BSTNode<K, V> pop = stack.pop();
                // 处理值，若结果不满足，则表示已经遇到大于等于key的值了可以不用操作直接退出遍历即可
                int r = (pop.key).compareTo(key);
                if (r > 0) {
                    result.add(pop.value);
                } else if (r == 0 && equal) {
                    result.add(pop.value);
                } else {
                    break;
                }
                node = pop.left;
            }
        }
        return result;
    }

    /**
     * 指定区间(key1, key2)，获取二叉搜索树中key值在该区间的树节点的value值<br>
     * 注：若给定区间key1 > key2，则会自动交换两者的值！
     * @param key1 区间左侧
     * @param key2 区间右侧
     * @param equal1 指定区间左侧是否闭合(即是否获取左区间的值)
     *               &emsp true：闭合<br>
     *               &emsp false：不闭合
     * @param equal2 指定区间右侧是否闭合(即是否获取右区间的值)
     *               &emsp true：闭合<br>
     *               &emsp false：不闭合
     * @return 一个升序集合result，存储所有满足条件的树节点value值
     */
    public List<V> getBetweenValue(K key1, K key2, boolean equal1, boolean equal2) {
        if (key1 == null || key2 == null) {
            throw new IllegalArgumentException("参数key不能为null！");
        }
        int r = (key1).compareTo(key2);
        if (r > 0) {
            K k = key1;
            key1 = key2;
            key2 = k;
        }
        ArrayList<V> result = new ArrayList<>();
        BSTNode<K, V> node = root;
        LinkedList<BSTNode<K, V>> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                // 处理值，若结果r2 > 0，则表示已经遇到大于等于key的值了可以不用操作直接退出遍历即可
                int r1 = (pop.key).compareTo(key1);
                int r2 = (pop.key).compareTo(key2);
                if (r1 > 0 && r2 < 0) {
                    result.add(pop.value);
                } else if (r1 == 0 && equal1) {
                    result.add(pop.value);
                } else if (r2 == 0 && equal2) {
                    result.add(pop.value);
                } else if (r2 > 0) {
                    break;
                }
                node = pop.right;
            }
        }
        return result;
    }

    /**
     * 查找二叉搜索树中指定值value在其中出现的次数
     * @param value 待查找的指定value值
     * @return value值出现的次数
     */
    public int getValueCount(V value) {
        LinkedListQueue<BSTNode<K, V>> queue = new LinkedListQueue<>(size);
        int count = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            BSTNode<K, V> node = queue.poll();
            if (node.value == value) {
                count ++;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return count;
    }

    /**
     * 从二叉搜索树指定节点开始，查找指定值value在其中出现的次数
     * @param node 二叉搜索树指定节点
     * @param value 待查找的指定value值
     * @return value值出现的次数
     */
    public int getValueCount(BSTNode<K, V> node, V value) {
        LinkedListQueue<BSTNode<K, V>> queue = new LinkedListQueue<>(size);
        int count = 0;
        queue.offer(node);
        while (!queue.isEmpty()) {
            BSTNode<K, V> node2 = queue.poll();
            if (node2.value == value) {
                count ++;
            }
            if (node2.left != null) {
                queue.offer(node2.left);
            }
            if (node2.right != null) {
                queue.offer(node2.right);
            }
        }
        return count;
    }

    /**
     * 从指定节点开始，获取一颗新的二叉搜索树BSTTree(深复制)
     * @return 新的二叉搜索树
     */
    public BSTTree<K, V> buildNewBSTTree(BSTNode<K, V> node) {
        return new BSTTree<>(node);
    }

    /**
     * 从指定节点开始，获取一颗新的二叉搜索树树节点BSTNode(深复制)
     * @return 新的二叉搜索树树节点
     */
    public BSTNode<K, V> buildNewBSTNode(BSTNode<K, V> node) {
        if(root == null) {
            return null;
        }
        BSTNode<K, V> newNode = new BSTNode<>(node.key, node.value);
        BSTTree<K, V> newRoot = new BSTTree<>(newNode);
        LinkedListQueue<BSTNode<K, V>> queue = new LinkedListQueue<>(size);
        queue.offer(root);
        while (!queue.isEmpty()) {
            BSTNode<K, V> node2 = queue.poll();
            newRoot.put(node2);
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
     * 二叉搜索树层序遍历(广度优先)，使用集合map存储二叉搜索树树节点的key - value，再用list集合存储这些树节点
     * @return 存储linkedHashMap集合的list集合
     */
    public List<Map<K, V>> levelOrderUseList() {
        List<Map<K, V>> result = new ArrayList<>();
        // 空的二叉搜索树直接判断
        if (root == null) {
            return result;
        }
        // 使用链表队列存储
        LinkedListQueue<BSTNode<K, V>> queue = new LinkedListQueue<>();
        queue.offer(root);
        int c1 = 1; // 当前层的节点数
        // 循环打印和存入值
        while (!queue.isEmpty()) {
            // 注意Map实现类的底层put方法，不能使用hashMap(因为hashMap存储数据后会自动按key值的字符串顺序排序)
            // LinkedHashMap的put方法仅会执行尾插键值对，不会额外排序
            Map<K, V> level = new LinkedHashMap<>(size);
            // c2为下一层的节点数
            int c2 = 0;
            for (int i = 0; i < c1; i++) {
                // 根节点root
                BSTNode<K, V> n = queue.poll();
                level.put(n.key, n.value);
                // 判断根节点左右节点是否存在，存在则加入队列
                if (n.left != null) {
                    queue.offer(n.left);
                    c2++;
                }
                if (n.right != null) {
                    queue.offer(n.right);
                    c2++;
                }
            }
            // 将map集合添加到list集合中
            result.add(level);
            // 一次循环后，下一层变为本层，节点数要替换
            c1 = c2;
        }
        return result;
    }

    /**
     * 二叉搜索树层序遍历key值
     * @return 存储key值的集合
     */
    public List<K> levelOrderKey() {
        if(root == null) {
            return null;
        }
        LinkedListQueue<BSTNode<K, V>> queue = new LinkedListQueue<>(size);
        List<K> keys = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BSTNode<K, V> node = queue.poll();
            keys.add(node.key);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return keys;
    }

    /**
     * 二叉搜索树层序遍历value值
     * @return 存储value值的集合
     */
    public List<V> levelOrderValue() {
        if(root == null) {
            return null;
        }
        LinkedListQueue<BSTNode<K, V>> queue = new LinkedListQueue<>(size);
        List<V> values = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BSTNode<K, V> node = queue.poll();
            values.add(node.value);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return values;
    }

    /**
     * 二叉树中序遍历key值(左根右)(深度优先)
     * @return 存储key值的集合
     */
    public List<K> inOrderKey() {
        LinkedListStack<BSTNode<K, V>> stack = new LinkedListStack<>(size);
        List<K> keys = new ArrayList<>();
        BSTNode<K, V> curr = root;
        // 该思路与executeSearch搜索方法相同！
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                keys.add(pop.key);
                curr = pop.right;
            }
        }
        return keys;
    }

    /**
     * 二叉树中序遍历value值(左根右)(深度优先)
     * @return 存储value值的集合
     */
    public List<V> inOrderValue() {
        LinkedListStack<BSTNode<K, V>> stack = new LinkedListStack<>(size);
        List<V> values = new ArrayList<>();
        BSTNode<K, V> curr = root;
        // 该思路与executeSearch搜索方法相同！
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                values.add(pop.value);
                curr = pop.right;
            }
        }
        return values;
    }

    /**
     * 二叉搜索树层序遍历
     */
    public void levelOrder() {
        if(root == null) {
            return;
        }
        LinkedListQueue<BSTNode<K, V>> queue = new LinkedListQueue<>(size);
        queue.offer(root);
        while (!queue.isEmpty()) {
            BSTNode<K, V> node = queue.poll();
            System.out.print(node.key +"：" + node.value + "\t");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 二叉树前序遍历(根左右)(深度优先)
     */
    public void preOrder() {
        /*
        思路：
            1.当栈为空或二叉搜索树不为空时，循环遍历二叉搜索树
            2.当树节点不为null时，将该树节点压入栈中，输出此时的树节点值，并向下遍历左叶子节点
            3.当树节点为null时，说明上一节点的左子节点为null，此时应返回上一节点，即从栈中弹出上一节点，
              因为上一节点之前已经遍历，因此需要找到它的右节点继续遍历
            4.依次循环步骤2、3，直至遍历结束
         */
        LinkedListStack<BSTNode<K, V>> stack = new LinkedListStack<>(size);
        BSTNode<K, V> curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                // 前序遍历与中序遍历唯一不同点是输出位置不一样，前序从根开始遍历输出，而中序要从最左叶子节点输出！
                System.out.print(curr.key +"：" + curr.value + "\t");
                curr = curr.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                curr = pop.right;
            }
        }
    }

    /**
     * 二叉树中序遍历(左根右)(深度优先)
     */
    public void inOrder() {
         /*
        思路：
            1.当栈为空或二叉搜索树不为空时，循环遍历二叉搜索树
            2.当树节点不为null时，将该树节点压入栈中，并向下遍历左叶子节点
            3.当树节点为null时，说明上一节点的左子节点为null，此时应返回上一节点，即从栈中弹出上一节点，
              输出此时的树节点值，因为上一节点之前已经遍历，因此需要找到它的右节点继续遍历
            4.依次循环步骤2、3，直至遍历结束
         */
        LinkedListStack<BSTNode<K, V>> stack = new LinkedListStack<>(size);
        BSTNode<K, V> curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                System.out.print(pop.key +"：" + pop.value + "\t");
                curr = pop.right;
            }
        }
    }

     /*
                 1
               /  \
              3    5
             / \  / \
            2  4 7   8
           /
         null
        stack: 1
        pop: 8
        peek: 5
        out:2 4 3 7 8 5 1
     */

    /**
     * 二叉树后序遍历(左右根)(深度优先)
     */
    public void postOrder() {
        LinkedListStack<BSTNode<K, V>> stack = new LinkedListStack<>(size);
        BSTNode<K, V> curr = root; // 当前节点
        BSTNode<K, V> pop = null; // 最近一次弹栈的元素
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr); // 压入栈，为了记住回来的路
                curr = curr.left;
            } else {
                // 不能处理完左叶子结点后直接将根节点弹出，需要判断右叶子节点是否存在，因此要用peek()
                BSTNode<K, V> peek = stack.peek(); // 栈顶元素
                if (peek.right == null || peek.right == pop) { // 右子树处理完成
                    pop = stack.pop();
                    System.out.print(pop.key +"：" + pop.value + "\t");
                } else {
                    curr = peek.right;
                }
            }
        }
    }

    /**
     * 二叉搜索树任意树节点开始层序遍历
     * @param node 二叉搜索树任意树节点
     */
    public void levelOrder(BSTNode<K, V> node) {
        if(node == null) {
            return;
        }
        LinkedListQueue<BSTNode<K, V>> queue = new LinkedListQueue<>(size);
        queue.offer(node);
        while (!queue.isEmpty()) {
            BSTNode<K, V> node2 = queue.poll();
            System.out.print(node2.key +"：" + node2.value + "\t");
            if (node2.left != null) {
                queue.offer(node2.left);
            }
            if (node2.right != null) {
                queue.offer(node2.right);
            }
        }
    }

    /**
     * 二叉搜索树任意树节点开始递归前序遍历(根左右)(深度优先)
     * @param node 二叉搜索树任意树节点
     */
    public void preOrder(BSTNode<K, V> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.key +"：" + node.value + "\t"); // 值
        preOrder(node.left); // 左
        preOrder(node.right); // 右
    }

    /**
     * 二叉搜索树任意树节点开始递归中序遍历(左根右)(深度优先)
     * @param node 二叉搜索树任意树节点
     */
    public void inOrder(BSTNode<K, V> node) {
        if (node == null) {
            return;
        }
        inOrder(node.left); // 左
        System.out.print(node.key +"：" + node.value + "\t"); // 值
        inOrder(node.right); // 右
    }

    /**
     * 二叉搜索树任意树节点开始递归后序遍历(左右根)(深度优先)
     * @param node 二叉搜索树任意树节点
     */
    public void postOrder(BSTNode<K, V> node) {
        if (node == null) {
            return;
        }
        postOrder(node.left); // 左
        postOrder(node.right); // 右
        System.out.print(node.key +"：" + node.value + "\t"); // 值
    }

    /**
     * 二叉搜索树前中后序遍历
     */
    public void order() {
        LinkedList<BSTNode<K, V>> stack = new LinkedList<>();
        BSTNode<K, V> curr = root; // 代表当前节点
        BSTNode<K, V> pop = null; // 最近一次弹栈的元素
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                colorPrintln("前: " + curr.key + "：" + curr.value, 31);
                stack.push(curr); // 压入栈，为了记住回来的路
                curr = curr.left;
            } else {
                BSTNode<K, V> peek = stack.peek();
                // 右子树可以不处理, 对中序来说, 要在右子树处理之前打印
                if (peek.right == null) {
                    colorPrintln("中: " + peek.key + "：" + peek.value, 36);
                    pop = stack.pop();
                    colorPrintln("后: " + pop.key + "：" + pop.value, 34);
                }
                // 右子树处理完成, 对中序来说, 无需打印
                else if (peek.right == pop) {
                    pop = stack.pop();
                    colorPrintln("后: " + pop.key + "：" + pop.value, 34);
                }
                // 右子树待处理, 对中序来说, 要在右子树处理之前打印
                else {
                    colorPrintln("中: " + peek.key + "：" + peek.value, 36);
                    curr = peek.right;
                }
            }
        }
    }

    /**
     * 二叉搜索树遍历打印
     * @param origin 字符串
     * @param color 颜色
     */
    private void colorPrintln(String origin, int color) {
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }

    /**
     * 从指定树节点开始比较两个二叉搜索树，若两者所指定的树形结构均相同，则判定两颗二叉搜索树相同
     * @param node1 指定树节点1
     * @param node2 指定树节点2
     * @param childNode 是否比较两个树节点的左右孩子节点指向 true：比较 / false：不比较
     * @param parentNode 是否比较两个树节点的父节点指向 true：比较 / false：不比较
     * @return 判断结果
     */
    public boolean equal(BSTNode<K, V> node1, BSTNode<K, V> node2, boolean childNode, boolean parentNode) {
        // 三种情况
        // 1.两者只有一个不是null
        if ((node1 == null && node2 != null) || (node1 != null && node2 == null)) {
            return false;
        }
        // 2.两者全是null
        if (node1 == null) {
            return true;
        }
        // 3.两者全都不是null
        LinkedListQueue<BSTNode<K, V>> queue = new LinkedListQueue<>(new BSTTree<>(node1).size);
        LinkedListQueue<BSTNode<K, V>> queue2 = new LinkedListQueue<>(new BSTTree<>(node2).size);
        queue.offer(node1);
        queue2.offer(node2);
        while (!queue.isEmpty() || !queue2.isEmpty()) {
            BSTNode<K, V> node11 = queue.poll();
            BSTNode<K, V> node22 = queue2.poll();
            if ((node11 == null && node22 != null) || (node11 != null && node22 == null)) {
                return false;
            }
            if (node11 == null) {
                return true;
            }
            // 正常情况仅判断顺序和key - value，若指定判断父子节点指向，则需要另外判断
            if ((node11.key != node22.key && node11.value != node22.value) ||
                (childNode && node11.left != node22.left && node11.right != node22.right) ||
                (parentNode && node11.parent != node22.parent)) {
                return false;
            }
            if (node11.left != null) {
                queue.offer(node11.left);
            }
            if (node11.right != null) {
                queue.offer(node11.right);
            }
            if (node22.left != null) {
                queue2.offer(node22.left);
            }
            if (node22.right != null) {
                queue2.offer(node22.right);
            }
        }
        return true;
    }

    /**
     * 从指定树节点开始比较两个二叉搜索树，若两者的顺序、key - value均相同，则判定两颗二叉搜索树相同
     * @param node1 指定树节点1
     * @param node2 指定树节点2
     * @return 判断结果
     */
    public boolean equal(BSTNode<K, V> node1, BSTNode<K, V> node2) {
        return equal(node1, node2, false, false);
    }

    /**
     * 判断传入的二叉搜索树指定节点及其子节点的value值是否对称
     * @param node 二叉搜索树指定节点
     * @return 判断结果
     */
    public boolean isSymmetric(BSTNode<K, V> node) {
        return check(node.left, node.right);
    }

    /**
     * 二叉搜索树对称检查方法
     * @param left 左节点
     * @param right 右节点
     * @return 判断结果
     */
    private boolean check(BSTNode<K, V> left, BSTNode<K, V> right) {
        // 左右节点均为null，满足对称
        if (left == null && right == null) {
            return true;
        }
        // 经过第一次if判断，之后的左右节点必不同时为null，左右有一个为null时，另一个对称的节点必不为null，直接不满足对称
        if (left == null || right == null) {
            return false;
        }
        // 左右节点不为null时
        return Objects.equals(left.value, right.value) && check(left.left, right.right) && check(left.right, right.left);
    }

    /**
     * 层序遍历获取二叉搜索树最大深度(此处的深度包括根节点！)
     * @return 该二叉搜索树最大深度
     */
    public int maxDepth() {
        if(root == null) {
            return 0;
        }
        java.util.Queue<BSTNode<K, V>> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level ++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                BSTNode<K, V> node = queue.poll();
                assert node != null;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return level;
    }

    /**
     * 获取二叉搜索树指定节点最大深度(此处的深度包括指定节点！)
     * @param node 二叉搜索树指定节点
     * @return 该二叉搜索树指定节点最大深度
     */
    public int maxDepth(BSTNode<K, V> node) {
        // 深度：从根出发，离根最远的节点的总边数(注：此处获得的深度包含根节点！)
        // 二叉树为null
        if (node == null) {
            return 0;
        }
        // 此处+1是加上根节点！
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }

    /**
     * 层序遍历获取二叉搜索树最小深度(此处的深度包括根节点！)
     * @return 该二叉搜索树最小深度
     */
    public int minDepth() {
        if (root == null) {
            return 0;
        }
        java.util.Queue<BSTNode<K, V>> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level ++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                BSTNode<K, V> node = queue.poll();
                // 与层序遍历最大深度唯一不同是只要找到第一个分支遍历完成(即找到叶子节点)，则直接返回即为最小深度！
                assert node != null;
                if (node.left == null && node.right == null) {
                    return level;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return level;
    }

    /**
     * 获取二叉搜索树最小深度(此处的深度包括指定节点！)
     * @param node 二叉搜索树指定节点
     * @return 该二叉搜索树指定节点最小深度
     */
    public int minDepth(BSTNode<K, V> node) {
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
     * 从指定节点开始翻转二叉搜索树<p>
     * 注：翻转后的二叉搜索树一般会破坏数据存储结构，因此不建议使用翻转后的二叉搜索树
     * @param node 二叉搜索树指定节点
     * @return 指定节点翻转后的二叉搜索树
     */
    public BSTNode<K, V> invertTree(BSTNode<K, V> node) {
        invertTree_Test(node);
        return node;
    }

    /**
     * 从指定节点开始翻转二叉搜索树，此操作不会修改原二叉搜索树，而是获得一颗新的翻转二叉搜索树<p>
     * 注：翻转后获取的二叉搜索树一般会破坏数据存储结构，因此不建议使用翻转后的二叉搜索树
     * @param node 二叉搜索树指定节点
     * @return 新的指定节点翻转后的二叉搜索树
     */
    public BSTNode<K, V> invertTreeNotChange(BSTNode<K, V> node) {
        BSTTree<K, V> tree = buildNewBSTTree(node);
        invertTree_Test(tree.root);
        return tree.root;
    }

    /**
     * 翻转二叉搜索树的具体递归操作
     * @param node 翻转后的二叉搜索树
     */
    private void invertTree_Test(BSTNode<K, V> node) {
        // 递归结束条件：递归到树叶
        if (node == null) {
            return;
        }
        // 交换左右叶子节点的位置
        BSTNode<K, V> t = node.left;
        node.left = node.right;
        node.right = t;
        // 向下递归左右叶子节点
        invertTree_Test(node.left);
        invertTree_Test(node.right);
    }

    /**
     * 判断二叉搜索树从指定节点开始是否合法<p>
     * 注：二叉搜索树为null时默认合法！
     * @param node 二叉搜索树指定节点
     * @return 判断结果
     */
    public boolean isValidBST(BSTNode<K, V> node) {
        if (node == null) {
            return true;
        }
        BSTNode<K, V> node2 = node;
        LinkedList<BSTNode<K, V>> stack = new LinkedList<>();
        // 这里因为引用数据类型K的首个值无法取到最值，因此无法用首个key和下个key遍历比较，只能用集合存储后再次判断
        List<K> keys = new ArrayList<>();
        // 此处用到中序遍历得到的前一个必定小于下一个值的特性判断二叉搜索树是否合法
        while (node2 != null || !stack.isEmpty()) {
            if (node2 != null) {
                stack.push(node2);
                node2 = node2.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                keys.add(pop.key);
                node2 = pop.right;
            }
        }
        for (int i = 0; i < keys.size() - 1; i++) {
            int result = keys.get(i).compareTo(keys.get(i + 1));
            if (result >= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断key值数据类型为Integer的二叉搜索树，从指定节点开始是否合法<p>
     * 注：二叉搜索树为null时默认合法！
     * @param node key值数据类型为Integer的二叉搜索树的指定节点
     * @return 判断结果
     */
    public boolean isValidBSTLong(BSTNode<Integer, V> node) {
        return doIsValidInteger(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * 具体判断二叉搜索树从指定节点开始是否合法
     * @param node 二叉搜索树指定节点
     * @param min key不能小于的最小值(例：根节点key的min -> 负无穷，max -> 正无穷)
     * @param max key不能大于的最大值
     * @return 判断结果
     */
    private boolean doIsValidInteger(BSTNode<Integer, V> node, long min, long max) {
        // 二叉搜索树为null
        if (node == null) {
            return true;
        }
        // 二叉搜索树当前值不满足要求
        if (node.key <= min || node.key >= max) {
            return false;
        }
        // 满足情况时，再判断其左右孩子是否合法，并递归判断
        // 两侧树节点判断，必须同时满足上下界才合法
        return doIsValidInteger(node.left, min, node.key) && doIsValidInteger(node.right, node.key, max);
    }

    /**
     * 指定一个区间(min, max)，得到二叉搜索树key值在此区间的所有树节点，计算出这些树节点value值的和<p>
     * 注：1.该方法已经指定泛型K为Integer，V为Double<p>
     * &emsp &nbsp 2.该方法执行效率不高，若对边界无要求，建议使用rangeSumBST2()方法<p>
     * &emsp &nbsp 3.若传入的min > max，则自动交换两者的值
     * @param node 二叉搜索树指定节点
     * @param min 左区间
     * @param max 右区间
     * @param equal1 指定左区间是否闭合 true：闭合 / false：不闭合
     * @param equal2 指定右区间是否闭合 true：闭合 / false：不闭合
     * @return 满足条件的所有树节点value值的和
     */
    public double rangeSumBST(BSTNode<Integer, Double> node, int min, int max, boolean equal1, boolean equal2) {
        if (min > max) {
            int m = max;
            max = min;
            min = m;
        }
        BSTNode<Integer, Double> node2 = node;
        LinkedList<BSTNode<Integer, Double>> stack = new LinkedList<>();
        double sum = 0;
        while (node2 != null ||!stack.isEmpty()) {
            if (node2!= null) {
                stack.push(node2);
                node2 = node2.left;
            } else {
                BSTNode<Integer, Double> pop = stack.pop();
                // 大于上限时停止遍历，但无法判断小于下限时停止，执行效率较低
                if (pop.key > max) {
                    break;
                }
                if ((pop.key > min && pop.key < max) || (pop.key == min && equal1) || (pop.key == max && equal2)) {
                    sum += pop.value;
                }
                node2 = pop.right;
            }
        }
        return sum;
    }

    /**
     * rangeSumBST2方法的具体递归操作
     * @param node 二叉搜索树指定节点
     * @param min 左区间
     * @param max 右区间
     * @return 满足条件的所有树节点value值的和
     */
    private double rangeSumBST2_Test(BSTNode<Integer, Double> node, int min, int max) {
        if (node == null) {
            return 0;
        }
        // 若当前树节点key值小于min值，则其所有左孩子节点key值必定小于min，不用再判断了
        if (node.key < min) {
            return rangeSumBST2(node.right, min, max);
        }
        // 若当前树节点key值大于max值，则其所有右孩子节点key值必定大于max，不用再判断了
        if (node.key > max) {
            return rangeSumBST2(node.left, min, max);
        }
        // 其它满足条件的情况，执行递归累加
        return node.value + rangeSumBST2(node.left, min, max) + rangeSumBST2(node.right, min, max);
    }

    /**
     * 指定一个区间[min, max]，得到二叉搜索树key值在此区间的所有树节点，计算出这些树节点value值的和<p>
     * 注：1.该方法已经指定泛型K为Integer，V为Double<p>
     * &emsp &nbsp 2.该方法执行效率高，但无法指定边界是否可取，若对边界有要求建议使用rangeSumBST()方法<p>
     * &emsp &nbsp 3.若传入的min > max，则自动交换两者的值
     * @param node 二叉搜索树指定节点
     * @param min 左区间
     * @param max 右区间
     * @return 满足条件的所有树节点value值的和
     */
    public double rangeSumBST2(BSTNode<Integer, Double> node, int min, int max) {
        if (min > max) {
            int m = max;
            max = min;
            min = m;
        }
        return rangeSumBST2_Test(node, min, max);
    }

    /**
     * 给点两个树节点，寻找两者的最近公共祖先(祖先包括自己)
     * @param node 二叉搜索树指定节点
     * @param p 待查找树节点1
     * @param q 待查找树节点2
     * @return 查找得到的公共祖先
     */
    public BSTNode<K, V> lowestCommonAncestor(BSTNode<K, V> node, BSTNode<K, V> p, BSTNode<K, V> q) {
        int pn = p.key.compareTo(node.key);
        int qn = q.key.compareTo(node.key);
        // 判断两个节点是否在同一侧，不在一侧则直接返回根节点即可
        while (pn < 0 && qn < 0 || pn > 0 && qn > 0) {
            // 若在一侧则继续向下找
            if (pn < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            pn = p.key.compareTo(node.key);
            qn = q.key.compareTo(node.key);
        }
        return node;
    }

    /**
     * 后缀表达式转二叉搜索树
     * @param tokens 后缀表达式
     * @return 二叉搜索树
     */
    public BSTNode<Integer, V> constructExpressionTree(V[] tokens) {
        LinkedList<BSTNode<Integer, V>> stack = new LinkedList<>();
        /*
            1.遇到数字入栈
            2.遇到运算符出栈，建立节点关系，再入栈
         */
        for (int i = 1; i <= tokens.length; i++) {
            V t = tokens[i - 1];
            // 运算符
            if ("+".equals(t) || "-".equals(t) || "*".equals(t) || "/".equals(t)) {
                BSTNode<Integer, V> right = stack.pop();
                BSTNode<Integer, V> left = stack.pop();
                BSTNode<Integer, V> parent = new BSTNode<>(i, t);
                parent.left = left;
                parent.right = right;
                left.parent = parent;
                right.parent = parent;
                stack.push(parent);
                // 数字
            } else {
                stack.push(new BSTNode<>(i, t));
            }
        }
        return stack.peek();
    }

    /**
     * 根据前序遍历结果构造二叉搜索树(二叉搜索树key值与value值相同，均存储遍历数值)<p>
     * 注：泛型K与V均要设置为数值型！
     * @param preOrder 前序遍历数组(数组长度大于0且无重复值)
     * @return 二叉搜索树
     */
    @SuppressWarnings("unchecked") // 抑制类型转换未检查警告
    public BSTNode<K, V> buildTreeUsePreOrder(V[] preOrder) {
        BSTNode<K, V> node = new BSTNode<>((K) preOrder[0], preOrder[0]);
        BSTTree<K, V> tree = new BSTTree<>(node);
        for (int i = 1; i < preOrder.length; i++) {
            V value = preOrder[i];
            tree.put((K) value, value); // 时间复杂度 nlog(n)
        }
        return node;
    }

    /**
     * 根据前序与中序遍历结果构造二叉搜索树(二叉搜索树key值与value值相同，均存储遍历数值)<p>
     * 注：1.泛型K与V均要设置为数值型！<p>
     * &emsp &nbsp 2.若传入的遍历不满足二叉搜索树的存储结构，则得到的二叉搜索树也不满足存储结构！
     * @param preOrder 前序遍历数组(数组长度大于0且无重复值)
     * @param inOrder 中序遍历数组(数组长度大于0且无重复值)
     * @return 二叉搜索树
     */
    @SuppressWarnings("unchecked")
    public BSTNode<K, V> buildTreeUsePreOrderInOrder(V[] preOrder, V[] inOrder) {
        if (preOrder.length == 0) {
            return null;
        }
        // 创建根节点(前序起始为根节点)
        V rootValue = preOrder[0];
        BSTNode<K, V> root = new BSTNode<>((K) rootValue, rootValue);
        // 区分左右子树
        for (int i = 0; i < inOrder.length; i++) {
            if (Objects.equals(inOrder[i], rootValue)) {
                // 0 ~ i-1 左子树
                // i+1 ~ inOrder.length -1 右子树
                // 前序 1 2 4 3 6 7
                // 中序 4 2 1 6 3 7
                V[] inLeft = Arrays.copyOfRange(inOrder, 0, i); // [4,2]
                V[] inRight = Arrays.copyOfRange(inOrder, i + 1, inOrder.length); // [6,3,7]
                V[] preLeft = Arrays.copyOfRange(preOrder, 1, i + 1); // [2,4]
                V[] preRight = Arrays.copyOfRange(preOrder, i + 1, inOrder.length); // [3,6,7]
                // 切分数组后分别将左右子树进行递归
                root.left = buildTreeUsePreOrderInOrder(preLeft, inLeft); // 2
                root.right = buildTreeUsePreOrderInOrder(preRight, inRight); // 3
                break;
            }
        }
        return root;
    }

    /**
     * 根据中序与后序遍历结果构造二叉搜索树(二叉搜索树key值均为1，value存储遍历数值)<p>
     * 注：1.泛型K与V均要设置为数值型！<p>
     * &emsp &nbsp 2.若传入的遍历不满足二叉搜索树的存储结构，则得到的二叉搜索树也不满足存储结构！
     * @param inOrder 中序遍历数组(数组长度大于0且无重复值)
     * @param postOrder 后序遍历数组(数组长度大于0且无重复值)
     * @return 二叉搜索树
     */
    @SuppressWarnings("unchecked")
    public BSTNode<K, V> buildTreeUseInOrderPostOrder(V[] inOrder, V[] postOrder) {
        if (inOrder.length == 0) {
            return null;
        }
        // 根
        V rootValue = postOrder[postOrder.length - 1];
        BSTNode<K, V> root = new BSTNode<>((K) rootValue, rootValue);
        // 切分左右子树
        for (int i = 0; i < inOrder.length; i++) {
            if (Objects.equals(inOrder[i], rootValue)) {
                V[] inLeft = Arrays.copyOfRange(inOrder, 0, i);
                V[] inRight = Arrays.copyOfRange(inOrder, i + 1, inOrder.length);
                V[] postLeft = Arrays.copyOfRange(postOrder, 0, i);
                V[] postRight = Arrays.copyOfRange(postOrder, i, postOrder.length - 1);
                root.left = buildTreeUseInOrderPostOrder(inLeft, postLeft);
                root.right = buildTreeUseInOrderPostOrder(inRight, postRight);
                break;
            }
        }
        return root;
    }

    // 下面利用AVL树旋转平衡思想，对二叉搜索树的平衡度进行修改，提高搜索等操作的效率

    /**
     * 获取二叉搜索树的高度，相当于获取树的深度(包括指定节点！)
     * @param node 二叉搜索树指定节点
     * @return 树节点高度
     */
    private int getHeight(BSTNode<K, V> node) {
        return maxDepth(node);
    }

    /**
     * 获取二叉搜索树平衡因子(平衡因子：左子树高度 - 右子树高度，两者差不超过1则表示平衡)
     * @param node 二叉搜索树指定节点
     * @return 1 / 0 / -1 平衡<p>
     * > 1 / < -1 不平衡
     */
    public int balanceFactor(BSTNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /*
    失衡的四种情况(平衡因子绝对值 > 1)
        LL(调整：根节点右旋一次)
            失衡节点(图中 8)的 bf > 1，即左边更高
            失衡节点的左孩子(图中 6)的 bf >= 0 即左孩子这边也是左边更高或等高
                8                       6
               / \                     / \
              6   9     8右旋          5  8
             / \        -->          /  / \
            5   7                   4  7   9
           /
          4
        RR(调整：根节点左旋一次)
            失衡节点(图中 3)的 bf < -1，即右边更高
            失衡节点的右孩子(图中 6)的 bf <= 0，即右孩子这边右边更高或等高
                3                           5
               / \                         / \
              1   5         3左旋          3  6
                 / \        -->          / \   \
                4   6                   1   4   9
                     \
                      9
        LR(调整：根节点左孩子节点先左旋变成LL，之后根节点右旋)
            失衡节点(图中 8)的 bf > 1，即左边更高
            失衡节点的左孩子(图中 6)的 bf < 0 即左孩子这边是右边更高
                8                       8                       4
               / \                     / \                     / \
              3   9     3左旋          4  9      8右旋         3   8
             / \         -->         / \         -->         /   / \
            1   4                   3   5                   1   5   9
                 \                 /
                  5               1
        RL(调整：根节点右孩子节点先右旋变成RR，之后根节点左旋)
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
     * 二叉搜索树指定节点左旋(改变原二叉搜索树)
     * @param parent 待左旋的树节点
     * @return 旋转后的树节点
     */
    private BSTNode<K, V> leftRotate_Test(BSTNode<K, V> parent) {
        // 找到待左旋节点parent、左旋节点右孩子parentRightChild，右孩子的左孩子parentRightChildLiftChild
        BSTNode<K, V> parentRightChild = parent.right;
        BSTNode<K, V> parentRightChildLiftChild = parentRightChild.left;
        // 记录父节点，便于右旋后替换
        BSTNode<K, V> parentParent = parent.parent;
        // 左旋过程：替换父子指向
        parentRightChild.left = parent; // 上位：parentRightChild变为父节点(该过程顺序不能变！)
        parent.right = parentRightChildLiftChild; // 换父：parentRightChildLiftChild父节点变为parent
        // 指定父节点
        parentRightChild.parent = parentParent;
        parent.parent = parentRightChild;
        if (parentRightChildLiftChild != null) {
            parentRightChildLiftChild.parent = parent;
        }
        // 指定原始根节点parentParent的子节点
        if (parentParent == null) {
            root = parentRightChild;
        } else if (parentParent.left == parent) {
            parentParent.left = parentRightChild;
        } else {
            parentParent.right = parentRightChild;
        }
        return parentRightChild;
    }

    /**
     * 二叉搜索树指定节点左旋(AVL平衡旋转)(不改变原二叉搜索树)
     * @param node 待左旋的树节点
     * @return 旋转后的树节点
     */
    public BSTNode<K, V> leftRotate(BSTNode<K, V> node) {
        /*
            为了防止改变原有的二叉搜索树，需要新建树节点传入，但左右旋和右左旋方法调用左旋和右旋方法时不能新建
        树节点(因为左右旋和右左旋会调用左旋和右旋各一次，一共会创建两次新的树节点造成树节点重复)，因此需要把左旋
        和右旋方法单独重新调用，并在调用时传入新建的树节点
         */
        return leftRotate_Test(buildNewBSTNode(node));
    }

    /**
     * 二叉搜索树指定节点右旋(改变原二叉搜索树)
     * @param parent 待右旋的树节点
     * @return 旋转后的树节点
     */
    private BSTNode<K, V> rightRotate_Test(BSTNode<K, V> parent) {
        // 找到待右旋节点parent、右旋节点左孩子parentLeftChild，左孩子的右孩子parentLeftChildRightChild
        BSTNode<K, V> parentLeftChild = parent.left;
        BSTNode<K, V> parentLeftChildRightChild = parentLeftChild.right;
        // 记录父节点，便于右旋后替换
        BSTNode<K, V> parentParent = parent.parent;
        // 右旋过程：替换父子指向
        parent.left = parentLeftChildRightChild; // 换父：parentLeftChildRightChild父节点变为parent
        parentLeftChild.right = parent; // 上位：parentLeftChild变为父节点(该过程顺序不能变！)
        // 指定父节点
        parentLeftChild.parent = parentParent;
        parent.parent = parentLeftChild;
        if (parentLeftChildRightChild != null) {
            parentLeftChildRightChild.parent = parent;
        }
        // 指定原始根节点parentParent的子节点
        if (parentParent == null) {
            root = parentLeftChild;
        } else if (parentParent.left == parent) {
            parentParent.left = parentLeftChild;
        } else {
            parentParent.right = parentLeftChild;
        }
        return parentLeftChild;
    }

    /**
     * 二叉搜索树指定节点右旋(AVL平衡旋转)(不改变原二叉搜索树)
     * @param node 待右旋的树节点
     * @return 旋转后的树节点
     */
    public BSTNode<K, V> rightRotate(BSTNode<K, V> node) {
        /*
            为了防止改变原有的二叉搜索树，需要新建树节点传入，但左右旋和右左旋方法调用左旋和右旋方法时不能新建
        树节点(因为左右旋和右左旋会调用左旋和右旋各一次，一共会创建两次新的树节点造成树节点重复)，因此需要把左旋
        和右旋方法单独重新调用，并在调用时传入新建的树节点
         */
        return rightRotate_Test(buildNewBSTNode(node));
    }

    /**
     * 二叉搜索树左右旋(指定树节点左孩子节点先左旋，之后指定节点右旋)(改变原二叉搜索树)
     * @param node 待左右旋的树节点
     * @return 旋转后的树节点
     */
    private BSTNode<K, V> leftRightRotate_Test(BSTNode<K, V> node) {
        node.left = leftRotate_Test(node.left);
        return rightRotate_Test(node);
    }

    /**
     * 二叉搜索树左右旋(AVL平衡旋转。指定树节点左孩子节点先左旋，之后指定节点右旋)(不改变原二叉搜索树)
     * @param node 待左右旋的树节点
     * @return 旋转后的树节点
     */
    public BSTNode<K, V> leftRightRotate(BSTNode<K, V> node) {
        // 为了防止改变原有二叉搜索树也要新建节点
        BSTNode<K, V> node2 = buildNewBSTNode(node);
        node2.left = leftRotate_Test(node2.left);
        return rightRotate_Test(node2);
    }

    /**
     * 二叉搜索树右左旋(指定树节点右孩子节点先右旋，之后指定节点左旋)(改变原二叉搜索树)
     * @param node 待右左旋的树节点
     * @return 旋转后的树节点
     */
    private BSTNode<K, V> rightLeftRotate_Test(BSTNode<K, V> node) {
        node.right = rightRotate_Test(node.right);
        return leftRotate_Test(node);
    }

    /**
     * 二叉搜索树右左旋(AVL平衡旋转。指定树节点右孩子节点先右旋，之后指定节点左旋)(不改变原二叉搜索树)
     * @param node 待右左旋的树节点
     * @return 旋转后的树节点
     */
    public BSTNode<K, V> rightLeftRotate(BSTNode<K, V> node) {
        // 为了防止改变原有二叉搜索树也要新建节点
        BSTNode<K, V> node2 = buildNewBSTNode(node);
        node2.right = rightRotate_Test(node2.right);
        return leftRotate_Test(node2);
    }

    /**
     * 自动平衡一次二叉搜索树指定节点(平衡算法与AVL树相同！)<p>
     * 注：该方法不会改变传入的二叉搜索树，而是会得到一颗新的二叉搜索树树节点！
     * @param node 指定节点
     * @return 平衡后的树节点
     */
    public BSTNode<K, V> balanceBSTOne(BSTNode<K, V> node) {
        // 空的二叉搜索树
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
     * 自动平衡二叉搜索树指定节点(平衡算法会得到近似AVL搜索树，而不是平衡二叉搜索树！)<p>
     * 注：1.该方法不会改变传入的二叉搜索树，而是会得到一颗新的二叉搜索树树节点！<p>
     * &emsp &nbsp 2.平衡二叉搜索树：除叶子节点外，之前的每一层都完全填充(满足完美二叉树)<p>
     * &emsp &nbsp 3.近似AVL搜索树：该树最高的两层之差满足绝对值 <= 1<p>
     * &emsp &nbsp 4.该方法在实际中执行效率极低(为了保证不改变原有二叉搜索树，每个树节点都要深复制一份再操作)，
     * 而且得到的近似AVL树的查询效率也没有太大改变，若不是必须执行此方法优化二叉搜索树，建议使用balanceBSTToBalance
     * 方法得到平衡二叉搜索树，该方法虽然会改变原有二叉搜索树，但执行效率高且修改后得到的平衡二叉搜索树查询效率极高！
     * @param node 指定节点
     * @return 平衡后的树节点
     */
    public BSTNode<K, V> balanceBSTToAVL(BSTNode<K, V> node) {
        // 空的二叉搜索树
        if (node == null) {
            return null;
        }
        // 传入树节点平衡因子，用于判断左旋还是右旋
        int bf = balanceFactor(node);
        // 多次平衡二叉搜索树
        while (bf > 1 || bf < -1) {
            node = balanceBSTOne(node);
            bf = balanceFactor(node);
        }
        return node;
    }

    /**
     * 自动平衡一次二叉搜索树指定节点(平衡算法与AVL树相同！)<p>
     * 注：该方法会改变传入的二叉搜索树，仅用于创建平衡二叉搜索树的balanceChangeBST操作
     * @param node 指定节点
     * @return 平衡后的树节点
     */
    private BSTNode<K, V> balanceChangeBSTOne(BSTNode<K, V> node) {
        // 空的二叉搜索树
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
            return rightRotate_Test(node);
        } else if (bf > 1) {
            // LR：左子树比右子树高 且 左子树的左孩子节点少于右孩子节点，先将左子树左旋，再将根节点右旋
            return leftRightRotate_Test(node);
        } else if (bf < -1 && bfr >= 0) {
            // RL：左子树比右子树矮 且 右子树的左孩子节点多于右孩子节点，先将右子树右旋，再将根节点左旋
            return rightLeftRotate_Test(node);
        } else if (bf < -1) {
            // RR：左子树比右子树矮 且 右子树的左孩子节点少于右孩子节点，仅需根节点左旋
            return leftRotate_Test(node);
        }
        return node;
    }

    /**
     * 自动平衡二叉搜索树指定节点(平衡算法与AVL树相同！)<p>
     * 注：该方法会改变传入的二叉搜索树，仅用于创建平衡二叉搜索树的balanceBSTToBalance_LinkedHashMap操作
     * @param node 指定节点
     * @return 平衡后的树节点
     */
    private BSTNode<K, V> balanceChangeBST(BSTNode<K, V> node) {
        // 空的二叉搜索树
        if (node == null) {
            return null;
        }
        // 传入树节点平衡因子，用于判断左旋还是右旋
        int bf = balanceFactor(node);
        // 多次平衡二叉搜索树
        while (bf > 1 || bf < -1) {
            node = balanceChangeBSTOne(node);
            bf = balanceFactor(node);
        }
        return node;
    }

    /**
     * 将二叉搜索树从指定节点开始变为平衡二叉搜索树的具体实现方法<p>
     * 注：该方法会改变传入的二叉搜索树，仅用于创建平衡二叉搜索树的balanceBSTToBalance操作
     * @param node 二叉搜索树指定节点
     * @return 一个顺序集合LinkedHashMap，该集合按层序遍历存储转换后得到的平衡二叉搜索树的key - value值
     */
    private Map<K, V> balanceBSTToBalance_LinkedHashMap(BSTNode<K, V> node) {
        /*
            具体方法是层序遍历一次二叉搜索树，每次遍历的树节点都要进行一次balanceChangeBST判断转换，转换
        后，将该树节点的key - value键值对存储到集合中，该键值对的位置与平衡二叉搜索树树节点位置一一对应
         */
        LinkedListQueue<BSTNode<K, V>> queue = new LinkedListQueue<>(size);
        Map<K, V> map = new LinkedHashMap<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            // 每从队列弹出一个树节点，都对其进行balanceChangeBST判断转换，得到转换后树节点的键值对存入map
            BSTNode<K, V> node2 = balanceChangeBST(queue.poll());
            map.put(node2.key, node2.value);
            if (node2.left != null) {
                queue.offer(node2.left);
            }
            if (node2.right != null) {
                queue.offer(node2.right);
            }
        }
        // 层序遍历后原二叉搜索树每个树节点都进行的判断转换并存入了map(转换后意味着满足平衡二叉搜索树了)
        return map;
    }

    /**
     * 将二叉搜索树从指定节点开始变为平衡二叉搜索树<p>
     * 注：1.该方法会改变原有二叉搜索树，请调用前备份原有二叉搜索树！<p>
     * &emsp &nbsp 2.平衡二叉搜索树：除叶子节点外，之前的每一层都完全填充(满足完美二叉树)<p>
     * &emsp &nbsp 3.若不满足该方法执行效率，推荐使用红黑树结构，但红黑树的查询效率略低于此方法得到的平衡二叉搜索树
     * @param node 指定树节点
     * @return 平衡二叉搜索树树节点
     */
    public BSTNode<K, V> balanceBSTToBalance(BSTNode<K, V> node) {
        // 得到一个顺序集合LinkedHashMap，该集合按层序遍历存储转换后得到的平衡二叉搜索树的key - value值
        Map<K, V> map = balanceBSTToBalance_LinkedHashMap(node);
        // 新建二叉搜索树，调用put方法按map集合顺序添加便得到一颗平衡二叉搜索树，最后返回树根节点即可
        BSTTree<K, V> fullTree = new BSTTree<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            fullTree.put(entry.getKey(), entry.getValue());
        }
        return fullTree.getRoot();
    }

    // 以下是打印二叉搜索树树形结构的方法

    private void writeArray(BSTNode<K, V> currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = currNode.key + "：" +currNode.value;
        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;
        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = " /  ";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }
        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "  \\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    /**
     * 打印二叉搜索树指定节点开始的树形结构<p>
     * 注：二维数组存储每个字符串，树形结构打印可能不完美或存在纰漏，当树节点过于紧密时，二维数组的空格可能会覆盖原有数据
     * @param root 指定树节点
     */
    public void showBST(BSTNode<K, V> root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = maxDepth(root);
        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i ++) {
            for (int j = 0; j < arrayWidth; j ++) {
                res[i][j] = " ";
            }
        }
        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth/ 2, res, treeDepth);
        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line: res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i ++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2: line[i].length() - 1;
                }
            }
            System.out.println(sb);
        }
    }

    /**
     * 获取该类中所有public方法及这些方法的总数量
     */
    public void getALLPublicFunctions() {
        /*
            查看所有方法getDeclaredMethods()
            查看所有public方法getMethods()
         */
        Method[] m = BSTTree.class.getMethods();
        System.out.println("public方法总数量：" + m.length);
        for (Method method : m) {
            System.out.println(method.getName());
        }
    }

    /**
     * 获取该类中所有方法及这些方法的总数量
     */
    public void getALLFunctions() {

        /*
            查看所有方法getDeclaredMethods()
            查看所有public方法getMethods()
         */
        Method[] m = BSTTree.class.getDeclaredMethods();
        System.out.println("方法总数量：" + m.length);
        for (Method method : m) {
            System.out.println(method.getName());
        }
    }

}
