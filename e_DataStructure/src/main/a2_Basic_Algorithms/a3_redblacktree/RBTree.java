package main.a2_Basic_Algorithms.a3_redblacktree;

import main.a2_Basic_Algorithms.a1_binarysearchtree.LinkedListQueue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static main.a2_Basic_Algorithms.a3_redblacktree.RBTree.Color.BLACK;
import static main.a2_Basic_Algorithms.a3_redblacktree.RBTree.Color.RED;

/**
 * ClassName: RBTree
 * Package: main.a2_Basic_Algorithms.a3_redblacktree
 * Description:红黑树类
 *
 * @Author: zgh296
 * @Create: 2023/6/19 - 21:34
 * @Version: v1.0
 */
public class RBTree {

    /*
    红黑树特性
        1. 所有节点都有两种颜色：红与黑
        2. 所有 null 视为黑色
        3. 红色节点不能相邻
        4. 根节点是黑色
        5. 从根到任意一个叶子节点，路径中的黑色节点数一样（黑色完美平衡）
     */

    static class RBTNode {

        int key;
        Object value;
        RBTNode left;
        RBTNode right;
        // 父节点
        RBTNode parent;
        // 颜色，创建时默认为红色
        Color color = RED;

        public RBTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public RBTNode(int key) {
            this.key = key;
        }

        public RBTNode(int key, Color color) {
            this.key = key;
            this.color = color;
        }

        public RBTNode(int key, Color color, RBTNode left, RBTNode right) {
            this.key = key;
            this.color = color;
            this.left = left;
            this.right = right;
            if (left != null) {
                left.parent = this;
            }
            if (right != null) {
                right.parent = this;
            }
        }

        // 是否是左孩子
        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        // 叔叔(和父节点同级同侧的树节点)
        RBTNode uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            if (parent.isLeftChild()) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        // 兄弟
        RBTNode sibling() {
            if (parent == null) {
                return null;
            }
            if (this.isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }
    }

    // 枚举类型，红黑树只有两种颜色
    enum Color {
        RED, BLACK
    }

    // 根节点
    RBTNode root;

    // 判断红
    boolean isRed(RBTNode node) {
        return node != null && node.color == RED;
    }

    // 判断黑
    boolean isBlack(RBTNode node) {
        // return !isRed(node);
        return node == null || node.color == BLACK;
    }

    // 右旋 1. parent 的处理 2. 旋转后新根的父子关系
    private void rightRotate(RBTNode pink) {
        // 改方法详细描述见二叉搜索树BSTTree的rightRotate方法！
        // pink上层的父节点，用于修改后指定上层父节点parent的指向
        RBTNode parent = pink.parent;
        // 待修改位置的两个节点
        RBTNode yellow = pink.left;
        RBTNode green = yellow.right;
        // 指定父节点
        if (green != null) {
            green.parent = pink;
        }
        // 右旋具体操作
        yellow.right = pink;
        yellow.parent = parent;
        pink.left = green;
        pink.parent = yellow;
        // 指定上层父节点的子节点
        if (parent == null) {
            root = yellow;
        } else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }
    }

    // 左旋
    private void leftRotate(RBTNode pink) {
        // 改方法详细描述见二叉搜索树BSTTree的leftRotate方法！
        RBTNode parent = pink.parent;
        RBTNode yellow = pink.right;
        RBTNode green = yellow.left;
        if (green != null) {
            green.parent = pink;
        }
        yellow.left = pink;
        yellow.parent = parent;
        pink.right = green;
        pink.parent = yellow;
        if (parent == null) {
            root = yellow;
        } else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }
    }

    /**
     * 新增或更新
     * <br>
     * 正常增、遇到红红不平衡进行调整
     *
     * @param key   键
     * @param value 值
     */
    public void put(int key, Object value) {
        RBTNode p = root;
        RBTNode parent = null;
        while (p != null) {
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                p.value = value; // 更新
                return;
            }
        }
        RBTNode inserted = new RBTNode(key, value);
        if (parent == null) {
            root = inserted;
        } else if (key < parent.key) {
            parent.left = inserted;
            inserted.parent = parent;
        } else {
            parent.right = inserted;
            inserted.parent = parent;
        }
        fixRedRed(inserted);
    }

    void fixRedRed(RBTNode x) {
        // case 1 插入节点是根节点，变黑即可
        if (x == root) {
            x.color = BLACK;
            return;
        }
        // case 2 插入节点父亲是黑色，无需调整
        if (isBlack(x.parent)) {
            return;
        }
        /* case 3 当红红相邻，叔叔为红时
            需要将父亲、叔叔变黑、祖父变红，然后对祖父做递归处理
        */
        RBTNode parent = x.parent;
        RBTNode uncle = x.uncle();
        RBTNode grandparent = parent.parent;
        if (isRed(uncle)) {
            parent.color = BLACK;
            uncle.color = BLACK;
            grandparent.color = RED;
            fixRedRed(grandparent);
            return;
        }
        // case 4 当红红相邻，叔叔为黑时
        if (parent.isLeftChild() && x.isLeftChild()) { // LL
            parent.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
        } else if (parent.isLeftChild()) { // LR
            leftRotate(parent);
            x.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
        } else if (!x.isLeftChild()) { // RR
            parent.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        } else { // RL
            rightRotate(parent);
            x.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }
    }

    /**
     * 删除
     * <br>
     * 正常删、会用到李代桃僵技巧、遇到黑黑不平衡进行调整
     *
     * @param key 键
     */
    public void remove(int key) {
        /*
            李树代替桃树而死，原比喻兄弟互相爱护互相帮助，后转用来比喻以此代彼或代人受过。在军事策略上，
        李代桃僵是“三十六计”之一，指在敌我双方势均力敌，或者敌优我劣的情况下，用小的代价，换取大的胜利的
        谋略。其延伸涵义为：发展必然在个别与整体、暂时与长远的利益上有所取舍，有所牺牲，而后才有所发展。
         */
        RBTNode deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted);
    }

    public boolean contains(int key) {
        return find(key) != null;
    }

    // 查找删除节点
    private RBTNode find(int key) {
        RBTNode p = root;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    // 查找剩余节点
    private RBTNode findReplaced(RBTNode deleted) {
        // 删除节点无孩子或仅有一个孩子
        if (deleted.left == null && deleted.right == null) {
            return null;
        }
        if (deleted.left == null) {
            return deleted.right;
        }
        if (deleted.right == null) {
            return deleted.left;
        }
        // 删除节点左右孩子都有，找后继节点
        RBTNode s = deleted.right;
        while (s.left != null) {
            s = s.left;
        }
        return s;
    }

    // 处理双黑 (case3、case4、case5)
    private void fixDoubleBlack(RBTNode x) {
        if (x == root) {
            return;
        }
        RBTNode parent = x.parent;
        RBTNode sibling = x.sibling();
        // case 3 兄弟节点是红色
        if (isRed(sibling)) {
            if (x.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            parent.color = RED;
            sibling.color = BLACK;
            fixDoubleBlack(x);
            return;
        }
        if (sibling != null) {
            // case 4 兄弟是黑色, 两个侄子也是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                sibling.color = RED;
                if (isRed(parent)) {
                    parent.color = BLACK;
                } else {
                    fixDoubleBlack(parent);
                }
            }
            // case 5 兄弟是黑色, 侄子有红色
            else {
                // LL
                if (sibling.isLeftChild() && isRed(sibling.left)) {
                    rightRotate(parent);
                    sibling.left.color = BLACK;
                    sibling.color = parent.color;
                }
                // LR
                else if (sibling.isLeftChild() && isRed(sibling.right)) {
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                }
                // RL
                else if (!sibling.isLeftChild() && isRed(sibling.left)) {
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                }
                // RR
                else {
                    leftRotate(parent);
                    sibling.right.color = BLACK;
                    sibling.color = parent.color;
                }
                parent.color = BLACK;
            }
        } else {
            // @TODO 实际也不会出现，触发双黑后，兄弟节点不会为 null
            fixDoubleBlack(parent);
        }
    }

    private void doRemove(RBTNode deleted) {
        RBTNode replaced = findReplaced(deleted);
        RBTNode parent = deleted.parent;
        // 没有孩子
        if (replaced == null) {
            // case 1 删除的是根节点
            if (deleted == root) {
                root = null;
            } else {
                if (isBlack(deleted)) {
                    // 双黑调整
                    fixDoubleBlack(deleted);
                }
                // 红色叶子, 无需任何处理
                if (deleted.isLeftChild()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deleted.parent = null;
            }
            return;
        }
        // 有一个孩子
        if (deleted.left == null || deleted.right == null) {
            // case 1 删除的是根节点
            if (deleted == root) {
                root.key = replaced.key;
                root.value = replaced.value;
                root.left = root.right = null;
            } else {
                if (deleted.isLeftChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = deleted.right = deleted.parent = null;
                if (isBlack(deleted) && isBlack(replaced)) {
                    // @TODO 实际不会有这种情况 因为只有一个孩子时 被删除节点是黑色 那么剩余节点只能是红色不会触发双黑
                    fixDoubleBlack(replaced);
                } else {
                    // case 2 删除是黑，剩下是红
                    replaced.color = BLACK;
                }
            }
            return;
        }
        // case 0 有两个孩子 => 有一个孩子 或 没有孩子
        /*
            李代桃僵，交换删除节点和后继节点的值(直接将删除节点的值改为后继节点的值也行，因为后继节点最终会被删除)，
        若后继节点仍有两个孩子，则进行递归操作，直至找到后继结点只有一个或没有孩子的情况，最终删除这个后继节点，此时
        该情况就变成了待删除节点只有一个或没有孩子的情况
         */
        int t = deleted.key;
        deleted.key = replaced.key;
        replaced.key = t; // 此操作可以省略！

        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v; // 此操作可以省略！
        doRemove(replaced);
    }

    public List<Map<Integer, Object>> levelOrderUseList() {
        List<Map<Integer, Object>> result = new ArrayList<>();
        // 空的二叉搜索树直接判断
        if (root == null) {
            return result;
        }
        // 使用链表队列存储
        LinkedListQueue<RBTNode> queue = new LinkedListQueue<>();
        queue.offer(root);
        int c1 = 1; // 当前层的节点数
        // 循环打印和存入值
        while (!queue.isEmpty()) {
            // 注意Map实现类的底层put方法，不能使用hashMap(因为hashMap存储数据后会自动按key值的字符串顺序排序)
            // LinkedHashMap的put方法仅会执行尾插键值对，不会额外排序
            Map<Integer, Object> level = new LinkedHashMap<>(1000);
            // c2为下一层的节点数
            int c2 = 0;
            for (int i = 0; i < c1; i++) {
                // 根节点root
                RBTNode n = queue.poll();
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

}
