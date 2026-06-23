package main.a1_Basic_DataStructure.a5_binarytree;

import java.util.Stack;
import java.util.*;

/**
 * ClassName: BinaryTree
 * Package: main.a1_Basic_DataStructure.a5_binarytree
 * Description:二叉树类(建议使用二叉搜索树！)
 *
 * @Author: zgh296
 * @Create: 2023/5/31 - 9:38
 * @Version: v1.0
 */
public class BinaryTree<T extends Comparable<T>> {

    // 树节点类
    public static class TreeNode<T> {

        // 父节点
        private TreeNode<T> parent;
        // 左叶子节点
        TreeNode<T> left;
        // 节点值
        private T value;
        // 右叶子节点
        TreeNode<T> right;

        /**
         * 无参构造
         */
        public TreeNode() {
        }

        /**
         * 传入节点值的构造方法
         * @param value 节点值
         */
        public TreeNode(T value) {
            this.value = value;
        }

        /**
         * 传入左叶子节点和节点值的构造方法
         *
         * @param left  左叶子节点
         * @param value 节点值
         */
        public TreeNode(TreeNode<T> left, T value) {
            this.left = left;
            this.value = value;
        }

        /**
         * 传入节点值右叶子节点的构造方法
         *
         * @param value 节点值
         * @param right 右叶子节点
         */
        public TreeNode(T value, TreeNode<T> right) {
            this.value = value;
            this.right = right;
        }

        /**
         * 传入左叶子节点、节点值、右叶子节点的构造方法
         *
         * @param left  左叶子节点
         * @param value 节点值
         * @param right 右叶子节点
         */
        public TreeNode(TreeNode<T> left, T value, TreeNode<T> right) {
            this.left = left;
            this.value = value;
            this.right = right;
        }

        /**
         * 传入父节点、左叶子节点、节点值、右叶子节点的构造方法
         *
         * @param parent 父节点
         * @param left   左叶子节点
         * @param value  节点值
         * @param right  右叶子节点
         */
        public TreeNode(TreeNode<T> parent, TreeNode<T> left, T value, TreeNode<T> right) {
            this.parent = parent;
            this.left = left;
            this.value = value;
            this.right = right;
        }

        /**
         * 获取节点值
         * @return 节点值
         */
        public T getValue() {
            return value;
        }

        /**
         * 设置父节点
         * @param parent 父节点
         */
        public void setParent(TreeNode<T> parent) {
            this.parent = parent;
        }

        /**
         * 设置左叶子节点
         * @param left 左叶子节点
         */
        public void setLeft(TreeNode<T> left) {
            this.left = left;
        }

        /**
         * 设置右叶子节点
         * @param right 右叶子节点
         */
        public void setRight(TreeNode<T> right) {
            this.right = right;
        }

    }

    // 根节点
    private TreeNode<T> root = null;
    // 二叉树大小
    private int size = 0;

    /**
     * 无参构造(注：不要使用无参构造后添加树节点，换言之，不要向空的二叉树中添加树节点！)
     */
    public BinaryTree() {
    }

    /**
     * 传入根节点的构造方法
     * @param root 根节点
     */
    public BinaryTree(TreeNode<T> root) {
        this.root = root;
        setSize(root);
    }

    /**
     * 传入根节点的构造方法
     * @param root 根节点
     * @param size 二叉树包含节点数(包括根节点)
     */
    public BinaryTree(TreeNode<T> root, int size) {
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
    public BinaryTree(TreeNode<T> root, int size, boolean flag) {
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
     * 该方法用于无size参数的构造方法，作用是接收该二叉树的大小并设置size属性
     * @param node 二叉树
     */
    private void setSize(TreeNode<T> node) {
        // 相当于前序遍历一次获取size
        if (node == null) {
            return;
        }
        size ++;
        setSize(node.left); // 左
        setSize(node.right); // 右
    }

    /**
     * 获取二叉树节点数，一般用于新建二叉树对象时测试节点数与传入size是否匹配，除此以外可以直接使用size()方法获取节点数
     * @param root 二叉树
     */
    public int getTreeNodeNumber(TreeNode<T> root) {
        int number = 0;
        LinkedListStack<TreeNode<T>> stack = new LinkedListStack<>(size);
        TreeNode<T> curr = root;
        // 该思路与executeSearch搜索方法相同！
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                number ++;
                curr = curr.left;
            } else {
                TreeNode<T> pop = stack.pop();
                curr = pop.right;
            }
        }
        return number;
    }

    /**
     * 获取二叉树的根节点
     * @return 二叉树根节点
     */
    public TreeNode<T> getRoot() {
        return root;
    }

    /**
     * 获取二叉树的大小(节点数)
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
     * 二叉树的插入，插入值为null的树节点时，默认插入在最左侧
     * @param value 待插入的值
     */
    public boolean insert(T value) {
        // 二叉树为空时，直接新建节点
        if (size == 0) {
            root = new TreeNode<>(null, null, value, null);
            size++;
            return true;
        }
        // 插入值为null的树节点时，默认插入在最左侧(因为泛型无法比较null，因此必须提前判断null值情况！)
        if (value == null) {
            return insertNull("left");
        }
        // 二叉树不为空时，比较插入值与当前节点值的大小
        TreeNode<T> temp = root;
        while (true) {
            // 加入泛型后，无法直接比较，需要利用compareTo方法
            int result = (temp.value).compareTo(value);
            /*
            result
                == -1(或 < 0) temp.value < value
                == 0          temp.value = value
                == 1(或 > 0)  temp.value > value
             */
            // 若当前节点值 < 待插入值，则向右寻找更大的节点值，若当前节点无右节点，则新建右节点保存插入的数据
            if (result < 0) {
                if (temp.right != null) {
                    temp = temp.right;
                } else {
                    temp.right = new TreeNode<>(temp, null, value, null);
                    size++;
                    return true;
                }
            }
            // 若当前节点值 > 待插入值，则向左寻找更小的节点值，若当前节点无左节点，则新建左节点保存插入的数据
            if (result > 0) {
                if (temp.left != null) {
                    temp = temp.left;
                } else {
                    temp.left = new TreeNode<>(temp, null, value, null);
                    size++;
                    return true;
                }
            }
            // 若当前节点值 == 待插入值，则表示二叉树中存在该值，默认不插入
            if (result == 0) {
                return false;
            }
        }
    }

    /**
     * 二叉树的插入，插入值为null的树节点时，默认插入在最左侧
     * @param p 待插入的节点
     */
    public boolean insert(TreeNode<T> p) {
        if (p == null) {
            return false;
        }
        // 插入值为null的树节点时，默认插入在最左侧(因为泛型无法比较null，因此必须提前判断null值情况！)
        if (p.value == null) {
            return insertNull("left");
        }
        // 二叉树为空时，直接令根节点为插入节点
        if (size == 0) {
            root = p;
            size++;
            return true;
        }
        // 二叉树不为空时，比较插入节点值与当前节点值的大小
        TreeNode<T> temp = root;
        while (true) {
            // 加入泛型后，无法直接比较，需要利用compareTo方法
            int result = (temp.value).compareTo(p.value);
            /*
            result
                == -1(或 < 0) temp.value < p.value
                == 0          temp.value = p.value
                == 1(或 > 0)  temp.value > p.value
             */
            // 若当前节点值 < 待插入值，则向右寻找更大的节点值，若当前节点无右节点，则新建右节点保存插入的数据
            if (result < 0) {
                if (temp.right != null) {
                    temp = temp.right;
                } else {
                    temp.right = new TreeNode<>(temp, null, p.value, null);
                    size++;
                    return true;
                }
            }
            // 若当前节点值 > 待插入值，则向左寻找更小的节点值，若当前节点无左节点，则新建左节点保存插入的数据
            if (result > 0) {
                if (temp.left != null) {
                    temp = temp.left;
                } else {
                    temp.left = new TreeNode<>(temp, null, p.value, null);
                    size++;
                    return true;
                }
            }
            // 若当前节点值 == 待插入值，则表示二叉树中存在该值，默认不插入
            if (result == 0) {
                return false;
            }
        }
    }

    /**
     * 二叉树插入值为null的树节点
     * @param position 该节点插入位置。left：最左侧 / right：最右侧
     * @return 插入结果
     */
    public boolean insertNull(String position) {
        // 二叉树为空时，直接令根节点为插入的值为null的节点
        if (size == 0) {
            root = new TreeNode<>(null, null, null, null);
            size++;
            return true;
        }
        // 二叉树不为空
        TreeNode<T> temp = root;
        // 在最左侧插入null值树节点
        if ("left".equals(position)) {
            while (temp.left != null) {
                temp = temp.left;
            }
            temp.left = new TreeNode<>(temp, null, null, null);
            size++;
            return true;
        }
        // 在最右侧插入null值树节点
        if ("right".equals(position)) {
            while (temp.right != null) {
                temp = temp.right;
            }
            temp.right = new TreeNode<>(temp, null, null, null);
            size++;
            return true;
        }
        return false;
    }

    /**
     * 托孤方法
     * @param parent 删除节点的父节点
     * @param deleted 删除节点
     * @param child 删除节点的子节点(被顶上去的节点，删除操作后替换删除节点位置的子节点)
     */
    private void shift(TreeNode<T> parent, TreeNode<T> deleted, TreeNode<T> child) {
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
     * 二叉树删除节点操作
     * @param value 删除节点的value值
     * @return 删除结果
     */
    public boolean delete(T value) {
        TreeNode<T> node = root;
        // 新建树节点用于记录删除节点的父节点
        TreeNode<T> parent = null;
        // 二叉树为空时，提前进行报错处理
        if (node == null) {
            throw new NullPointerException("此二叉搜索树为空！");
        }
        // 泛型不能比较null值，要提前判断！
        if (value == null) { // 构造函数设置了value != null，因此无需判断node.value == null
            throw new IllegalArgumentException("参数value不能为null！");
        }
        // 查找待删除节点
        while (node != null) {
            // 加入泛型后，无法直接比较，需要利用compareTo方法
            int result = value.compareTo(node.value);
                /*
                result
                    == -1(或 < 0) value < node.value
                    == 0          value = node.value
                    == 1(或 > 0)  value > node.value
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
        // 遍历结束后未找到要删除的树节点，则返回false表示删除失败
        if (node == null) {
            return false;
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
        二叉树四种删除情况示例：
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
              1   3   6                 1   3
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
            TreeNode<T> s = node.right;
            // 后继节点父节点sp(此处不直接用s.parent还是为了防止构造函数未传参parent)
            TreeNode<T> sp = null;
            while (s.left != null) {
                sp = s;
                s = s.left;
            }
            // 判断删除和后继节点是否相邻以决定是否处理后继节点孩子节点的位置
            if (sp != node) {
                shift(sp, s, s.right);
                // 重新分配受影响节点的父子关系
                s.right.parent = sp;
                s.right = node.right;
                node.right.parent = s;
            }
            // 后继节点上顶代替删除节点，同时重新分配受影响节点的父子关系
            shift(parent, node, s);
            s.parent = parent;
            s.left = node.left;
            node.left.parent = s;
        }
        size --;
        return true;
    }

    /**
     * 供二叉树更新节点值时搜索参数值的方法
     * @param root 二叉树
     * @param value 待搜索值
     * @return 对应树节点及其父节点、所有子节点
     */
    private TreeNode<T> selectNode(TreeNode<T> root, T value) {
        TreeNode<T> flag = new TreeNode<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        while (!stack.empty() || root != null) {
            if (root != null) {
                if (root.value == value) {
                    flag = root;
                }
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop();
                if(root.value == value) {
                    flag = root;
                }
                root = root.right;
            }
        }
        return flag;
    }
    
    /**
     * 二叉树节点值的更新
     * @param value 待更新值
     * @param updateValue 更新值
     * @return 更新结果
     */
    public boolean update(T value, T updateValue) {
        TreeNode<T> temp = selectNode(root, value);
        // 注意设置为Integer时temp.value可能为null，需要用equals方法比较！
        if (Objects.equals(temp.value, value)) {
            // 注意，不能直接修改节点值，因为可能破坏二叉树数据结构性
            delete(value);
            insert(new TreeNode<>(updateValue));
            return true;
        }
        return false;
    }

    /**
     * 二叉树查找首个对应的树节点
     * @param root  待查找的二叉树
     * @param value 待查找的节点值
     * @return 对应节点
     */
    public TreeNode<T> select(TreeNode<T> root, Integer value) {
        // Java类Stack：栈先进后出
        LinkedListStack<TreeNode<T>> stack = new LinkedListStack<>(size);
        /*
        思路：
            1.考虑空二叉树情况，直接返回null即可，不进入循环
            2.当栈为空或二叉树不为空时，循环遍历二叉树
            3.当树节点不为null时，先判断此树节点的值与查找值是否匹配，不匹配则将该树节点压入栈中，并向下遍历左叶子节点
            4.当循环多次后树节点为null时，说明上一节点的左子节点为null，此时应返回上一节点，即从栈中弹出上一节点，重新判断
            5.依次循环步骤3、4，直至遍历结束或找到对应树节点
         */
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                if (Objects.equals(root.value, value)) {
                    return new TreeNode<>(root.value);
                }
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (Objects.equals(root.value, value)) {
                    return new TreeNode<>(root.value);
                }
                root = root.right;
            }
        }
        return null;
    }

    /**
     * 查找二叉树中树节点值与指定值匹配的树节点个数
     * @param root  待查找的二叉树
     * @param value 待查找的节点值
     * @return 匹配的树节点个数
     */
    public int selectTreeNodeCount(TreeNode<T> root, T value) {
        // Java类Stack：栈先进后出
        LinkedListStack<TreeNode<T>> stack = new LinkedListStack<>(size);
        int count = 0;
        /*
        思路：
            1.考虑空二叉树情况，直接返回null即可，不进入循环
            2.当栈为空或二叉树不为空时，循环遍历二叉树
            3.当树节点不为null时，先判断此树节点的值与查找值是否匹配，不匹配则将该树节点压入栈中，并向下遍历左叶子节点
            4.当循环多次后树节点为null时，说明上一节点的左子节点为null，此时应返回上一节点，即从栈中弹出上一节点，重新判断
            5.依次循环步骤3、4，直至遍历结束或找到对应树节点
         */
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                if (Objects.equals(root.value, value)) {
                    count ++;
                }
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (Objects.equals(root.value, value)) {
                    count ++;
                }
                root = root.right;
            }
        }
        return count;
    }

    /**
     * 二叉树层序遍历(广度优先)，使用集合存储二叉树
     * @return 集合
     */
    public List<List<T>> levelOrder() {
        List<List<T>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 使用链表队列存储
        LinkedListQueue<TreeNode<T>> queue = new LinkedListQueue<>();
        queue.offer(root);
        int c1 = 1; // 当前层的节点数

        // 循环打印和存入值
        while (!queue.isEmpty()) {
            List<T> level = new ArrayList<>();
            int c2 = 0; // 下一层的节点数
            for (int i = 0; i < c1; i++) {
                // 根节点root
                TreeNode<T> n = queue.poll();
                level.add(n.value);
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
            result.add(level);
            c1 = c2; // 一次循环后，下一层变为本层，节点数要替换
        }
        return result;
    }

    /**
     * 二叉树层序遍历
     * @param root 二叉树
     */
    public void levelOrder(TreeNode<T> root) {
        if(root == null) {
            return;
        }
        LinkedListQueue<TreeNode<T>> queue = new LinkedListQueue<>(size);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
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
    public void preOrder(TreeNode<T> node) {
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
    public void inOrder(TreeNode<T> node) {
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
    public void postOrder(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        postOrder(node.left); // 左
        postOrder(node.right); // 右
        System.out.print(node.value + "\t"); // 值
    }

    /**
     * 二叉树前序遍历(根左右)(深度优先)
     */
    public void preOrder2() {
        LinkedListStack<TreeNode<T>> stack = new LinkedListStack<>(size);
        TreeNode<T> curr = root;
        // 该思路与executeSearch搜索方法相同！
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                // 前序遍历与中序遍历唯一不同点是输出位置不一样，前序从根开始遍历输出，而中序要从最左叶子节点输出！
                System.out.print(curr.value + "\t");
                curr = curr.left;
            } else {
                TreeNode<T> pop = stack.pop();
                curr = pop.right;
            }
        }
    }

    /**
     * 二叉树中序遍历(左根右)(深度优先)
     */
    public void inOrder2() {
        LinkedListStack<TreeNode<T>> stack = new LinkedListStack<>(size);
        TreeNode<T> curr = root;
        // 该思路与select搜索方法相同！
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode<T> pop = stack.pop();
                System.out.print(pop.value + "\t");
                curr = pop.right;
            }
        }
    }

    /**
     * 二叉树后序遍历(左右根)(深度优先)
     */
    public void postOrder2() {
        LinkedListStack<TreeNode<T>> stack = new LinkedListStack<>(size);
        TreeNode<T> curr = root; // 当前节点
        TreeNode<T> pop = null; // 最近一次弹栈的元素
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr); // 压入栈，为了记住回来的路
                curr = curr.left;
            } else {
                // 不能处理完左叶子结点后直接将根节点弹出，需要判断右叶子节点是否存在，因此要用peek()
                TreeNode<T> peek = stack.peek(); // 栈顶元素
                if (peek.right == null || peek.right == pop) { // 右子树处理完成
                    pop = stack.pop();
                    System.out.print(pop.value + "\t");
                } else {
                    curr = peek.right;
                }
            }
        }
    }

    /**
     * 二叉树前中后序遍历
     */
    public void order() {
        LinkedList<TreeNode<T>> stack = new LinkedList<>();
        TreeNode<T> curr = root; // 代表当前节点
        TreeNode<T> pop = null; // 最近一次弹栈的元素
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                colorPrintln("前: " + curr.value, 31);
                stack.push(curr); // 压入栈，为了记住回来的路
                curr = curr.left;
            } else {
                TreeNode<T> peek = stack.peek();
                // 右子树可以不处理, 对中序来说, 要在右子树处理之前打印
                if (peek.right == null) {
                    colorPrintln("中: " + peek.value, 36);
                    pop = stack.pop();
                    colorPrintln("后: " + pop.value, 34);
                }
                // 右子树处理完成, 对中序来说, 无需打印
                else if (peek.right == pop) {
                    pop = stack.pop();
                    colorPrintln("后: " + pop.value, 34);
                }
                // 右子树待处理, 对中序来说, 要在右子树处理之前打印
                else {
                    colorPrintln("中: " + peek.value, 36);
                    curr = peek.right;
                }
            }
        }
    }

    /**
     * 二叉树遍历打印
     * @param origin 字符串
     * @param color 颜色
     */
    private void colorPrintln(String origin, int color) {
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }

    /**
     * 判断传入的二叉树是否对称
     * @param root 二叉树
     * @return 判断结果
     */
    public boolean isSymmetric(TreeNode<T> root) {
        return check(root.left, root.right);
    }

    /**
     * 二叉树对称检查方法
     * @param left 左节点
     * @param right 右节点
     * @return 判断结果
     */
    private boolean check(TreeNode<T> left, TreeNode<T> right) {
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
     * 获取二叉树最大深度(此处的深度包括根节点！)
     * @param node 二叉树
     * @return 该二叉树最大深度
     */
    public int maxDepth(TreeNode<T> node) {
        // 深度：从根出发，离根最远的节点的总边数(注：此处获得的深度包含根节点！)
        // 二叉树为null
        if (node == null) {
            return 0;
        }
        // 此处+1是加上根节点！
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }

    /**
     * 层序遍历获取二叉树最大深度(此处的深度包括根节点！)
     * @param root 二叉树
     * @return 该二叉树最大深度
     */
    public int maxDepth2(TreeNode<T> root) {
        if(root == null) {
            return 0;
        }
        java.util.Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level ++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode<T> node = queue.poll();
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
     * 获取二叉树最小深度(此处的深度包括根节点！)
     * @param node 二叉树
     * @return 该二叉树最小深度
     */
    public int minDepth(TreeNode<T> node) {
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
     * 层序遍历获取二叉树最小深度(此处的深度包括根节点！)
     * @param root 二叉树
     * @return 该二叉树最小深度
     */
    public int minDepth2(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        java.util.Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level ++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode<T> node = queue.poll();
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
     * 翻转二叉树
     * @param root 二叉树
     * @return 翻转后的二叉树
     */
    public TreeNode<T> invertTree(TreeNode<T> root) {
        invertTree_Test(root);
        return root;
    }

    /**
     * 翻转二叉树的具体递归操作
     * @param root 翻转后的二叉树
     */
    private void invertTree_Test(TreeNode<T> root) {
        // 递归结束条件：递归到树叶
        if (root == null) {
            return;
        }
        // 交换左右叶子节点的位置
        TreeNode<T> t = root.left;
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
    public TreeNode<String> constructExpressionTree(String[] tokens) {
        LinkedList<TreeNode<String>> stack = new LinkedList<>();
        /*
            1.遇到数字入栈
            2.遇到运算符出栈，建立节点关系，再入栈
         */
        for (String t : tokens) {
            switch (t) {
                // 运算符
                case "+", "-", "*", "/" -> {
                    TreeNode<String> right = stack.pop();
                    TreeNode<String> left = stack.pop();
                    TreeNode<String> parent = new TreeNode<>(t);
                    parent.left = left;
                    parent.right = right;
                    left.parent = parent;
                    right.parent = parent;
                    stack.push(parent);
                }
                // 数字
                default ->
                    stack.push(new TreeNode<>(t));
            }
        }
        return stack.peek();
    }

    /**
     * 根据前序与中序遍历结果构造二叉树(二叉树节点不重复)
     * @param preOrder 前序遍历
     * @param inOrder 中序遍历
     * @return 二叉树
     */
    public TreeNode<T> buildTreeUsePreOrderInOrder(T[] preOrder, T[] inOrder) {
        if (preOrder.length == 0) {
            return null;
        }
        // 创建根节点(前序起始为根节点)
        T rootValue = preOrder[0];
        TreeNode<T> root = new TreeNode<>(null,null, rootValue,null);
        // 区分左右子树
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootValue) {
                // 0 ~ i-1 左子树
                // i+1 ~ inOrder.length -1 右子树
                // 前序 1 2 4 3 6 7
                // 中序 4 2 1 6 3 7
                T[] inLeft = Arrays.copyOfRange(inOrder, 0, i); // [4,2]
                T[] inRight = Arrays.copyOfRange(inOrder, i + 1, inOrder.length); // [6,3,7]
                T[] preLeft = Arrays.copyOfRange(preOrder, 1, i + 1); // [2,4]
                T[] preRight = Arrays.copyOfRange(preOrder, i + 1, inOrder.length); // [3,6,7]
                // 切分数组后分别将左右子树进行递归
                root.left = buildTreeUsePreOrderInOrder(preLeft, inLeft); // 2
                root.right = buildTreeUsePreOrderInOrder(preRight, inRight); // 3
                break;
            }
        }
        return root;
    }

    /**
     * 根据中序与后序遍历结果构造二叉树(二叉树节点值不重复)
     * @param inOrder 中序遍历
     * @param postOrder 后序遍历
     * @return 二叉树
     */
    public TreeNode<T> buildTreeUseInOrderPostOrder(T[] inOrder, T[] postOrder) {
        if (inOrder.length == 0) {
            return null;
        }
        // 根
        T rootValue = postOrder[postOrder.length - 1];
        TreeNode<T> root = new TreeNode<>(null,null, rootValue,null);
        // 切分左右子树
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootValue) {
                T[] inLeft = Arrays.copyOfRange(inOrder, 0, i);
                T[] inRight = Arrays.copyOfRange(inOrder, i + 1, inOrder.length);
                T[] postLeft = Arrays.copyOfRange(postOrder, 0, i);
                T[] postRight = Arrays.copyOfRange(postOrder, i, postOrder.length - 1);
                root.left = buildTreeUseInOrderPostOrder(inLeft, postLeft);
                root.right = buildTreeUseInOrderPostOrder(inRight, postRight);
                break;
            }
        }
        return root;
    }

    // 问题1：如何将不规则二叉树转换为满二叉树？
    // 问题2：如何规则打印二叉树结构？

}
