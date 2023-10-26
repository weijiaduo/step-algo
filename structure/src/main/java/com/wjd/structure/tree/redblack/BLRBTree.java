package com.wjd.structure.tree.redblack;

import java.util.Arrays;

import static com.wjd.structure.tree.redblack.RBTNode.BLACK;
import static com.wjd.structure.tree.redblack.RBTNode.RED;

/**
 * 双偏向（Both-Leaning）红黑树（2-3-4树）
 * <p>
 * 基于自底向上
 * <p>
 * 1. 红色节点可以出现在左右两边
 *
 * @author weijiaduo
 * @since 2023/2/2
 */
public class BLRBTree implements RBTree {

    /**
     * 根节点
     */
    private RBTNode root;

    @Override
    public Integer get(int val) {
        RBTNode node = get(root, val);
        return node != null ? node.val : null;
    }

    /**
     * 查找指定值对应的节点
     *
     * @param h   当前节点
     * @param val 指定值
     * @return 指定值的节点
     */
    private RBTNode get(RBTNode h, int val) {
        // 没有找到
        if (h == null) {
            return null;
        }
        // 找到对应的节点
        if (h.val == val) {
            return h;
        }
        if (h.val > val) {
            return get(h.left, val);
        } else {
            return get(h.right, val);
        }
    }

    @Override
    public void insert(int val) {
        if (get(val) != null) {
            return;
        }

        // 插入新节点
        RBTNode h = new RBTNode(val);
        root = insertNode(root, h);
        // 调整树结构
        balanceInsertion(h);

        // 根节点始终是黑色
        setColor(root, BLACK);
    }

    /**
     * 插入新节点
     *
     * @param h       当前节点
     * @param newNode 新节点
     * @return 新当前节点
     */
    private RBTNode insertNode(RBTNode h, RBTNode newNode) {
        if (h == null) {
            return newNode;
        }
        if (newNode.val < h.val) {
            h.left = insertNode(h.left, newNode);
            h.left.parent = h;
        } else {
            h.right = insertNode(h.right, newNode);
            h.right.parent = h;
        }
        return h;
    }

    /**
     * 修正插入后的红黑树结构
     *
     * @param h 新插入的节点
     */
    private void balanceInsertion(RBTNode h) {
        // 父节点是黑色时，不用调整
        RBTNode p = h.parent;
        if (!isRed(p)) {
            return;
        }

        // 父节点都是红色
        RBTNode gp = p.parent;
        if (p == gp.left) {
            // LR 双红节点
            if (h == p.right) {
                rotateLeft(p);
            }
            // LL 双红节点
            p = rotateRight(gp);
        } else {
            // RL 双红节点
            if (h == p.left) {
                rotateRight(p);
            }
            // RR 双红节点
            p = rotateLeft(gp);
        }

        // 父子反色
        setColor(p, RED);
        setColor(p.left, BLACK);
        setColor(p.right, BLACK);

        // 递归往上处理
        balanceInsertion(p);
    }

    @Override
    public void remove(int val) {
        RBTNode h = get(root, val);
        if (h == null) {
            return;
        }

        // 替换节点（后继/前继）
        h = swapReplacer(h);
        // 先调整树结构
        balanceDeletion(h);
        // 真正删除节点
        removeNode(h);

        // 根节点始终是黑色
        setColor(root, BLACK);
    }

    /**
     * 交换当前节点到后继/前继节点
     *
     * @param h 当前节点
     * @return 替换节点
     */
    private RBTNode swapReplacer(RBTNode h) {
        // 寻找交换节点
        if (h == null) {
            return null;
        }
        RBTNode r;
        if (h.right != null) {
            // 右边最小值，即后继值
            r = h.right;
            while (r.left != null) {
                r = r.left;
            }
        } else if (h.left != null) {
            // 左边最大值，即前继值
            r = h.left;
            while (r.right != null) {
                r = r.right;
            }
        } else {
            // 叶子节点
            return h;
        }

        // 交换两个节点
        RBTNode t = new RBTNode(-1);
        replaceNode(h, t);
        replaceNode(r, h);
        replaceNode(t, r);
        return r;
    }

    /**
     * 替换节点
     *
     * @param h 原始节点
     * @param r 替换节点
     */
    private void replaceNode(RBTNode h, RBTNode r) {
        if (h == null || r == null) {
            return;
        }

        RBTNode hp = h.parent, hl = h.left, hr = h.right;
        // 节点颜色保持原状
        r.color = h.color;
        // 左节点
        r.left = hl;
        if (hl != null) {
            hl.parent = r;
        }
        // 右节点
        r.right = hr;
        if (hr != null) {
            hr.parent = r;
        }
        // 父节点
        r.parent = hp;
        if (hp == null) {
            root = r;
        } else if (hp.left == h) {
            hp.left = r;
        } else {
            hp.right = r;
        }
        // 删除引用
        h.parent = h.left = h.right = null;
    }

    /**
     * 移除节点（后继最小值/前继最大值）
     *
     * @param h 被移除节点，确保没有孩子或只有1个孩子
     */
    private void removeNode(RBTNode h) {
        RBTNode p = h.parent;
        if (p == null) {
            if (h.left == null) {
                root = h.right;
            } else {
                root = h.left;
            }
        } else {
            if (h == p.left) {
                // 后继最小值
                p.left = h.right;
            } else {
                // 前继最大值
                p.right = h.left;
            }
        }
        h.parent = h.left = h.right = null;
    }

    /**
     * 修正删除节点前的红黑树结构
     *
     * @param h 被删除节点
     */
    private void balanceDeletion(RBTNode h) {
        // 删除红色节点，不影响平衡性
        RBTNode p = h.parent;
        if (p == null || isRed(h)) {
            return;
        }

        // 删除黑色节点
        if (h == p.left) {
            // 1. 兄弟是红色，先转成黑色
            RBTNode s = p.right;
            if (isRed(s)) {
                rotateLeft(p);
            }
            // 2. 从兄弟借红色孩子
            if (borrowRight(h)) {
                return;
            }
            // 3. 父节点下溢合并
            if (underflow(h)) {
                return;
            }
        } else {
            // 镜像处理
            RBTNode s = p.left;
            if (isRed(s)) {
                rotateRight(p);
            }
            if (borrowLeft(h)) {
                return;
            }
            if (underflow(h)) {
                return;
            }
        }

        // 递归向上调整
        balanceDeletion(p);
    }

    /**
     * 父节点下溢，父子节点合并
     *
     * @param h 当前节点
     * @return true下溢后已平衡/false下溢后未平衡
     */
    private boolean underflow(RBTNode h) {
        RBTNode p = h.parent;
        RBTNode s;
        if (p.left == h) {
            s = p.right;
        } else {
            s = p.left;
        }
        boolean balanced = isRed(p);
        setColor(p, BLACK);
        setColor(s, RED);
        return balanced;
    }

    /**
     * 从右兄弟借一个红色节点过来
     *
     * @param h 当前节点
     * @return true借用成功/false借用失败
     */
    private boolean borrowRight(RBTNode h) {
        RBTNode p = h.parent;
        RBTNode s = p.right;
        RBTNode sl = s.left, sr = s.right;
        if (!isRed(sl) && !isRed(sr)) {
            // 没有红色节点可借
            return false;
        }

        // 远侄子是黑色，近侄子是红色
        if (!isRed(sr)) {
            rotateRight(s);
        }
        // 远侄子是红色
        p = rotateLeft(p);
        setColor(p.left, BLACK);
        setColor(p.right, BLACK);
        return true;
    }

    /**
     * 从左兄弟借一个红色节点过来
     *
     * @param h 当前节点
     * @return true借用成功/false借用失败
     */
    private boolean borrowLeft(RBTNode h) {
        RBTNode p = h.parent;
        RBTNode s = p.left;
        RBTNode sl = s.left, sr = s.right;
        if (!isRed(sl) && !isRed(sr)) {
            // 没有红色节点可借
            return false;
        }

        // 远侄子是黑色，近侄子是红色
        if (!isRed(sl)) {
            rotateLeft(s);
        }
        // 远侄子是红色
        p = rotateRight(p);
        setColor(p.left, BLACK);
        setColor(p.right, BLACK);
        return true;
    }

    /**
     * 左旋，将红色节点转到左边
     *
     * @param h 当前节点
     * @return 新当前节点
     */
    private RBTNode rotateLeft(RBTNode h) {
        if (h == null || h.right == null) {
            return h;
        }

        RBTNode p = h.parent;
        RBTNode r = h.right;
        r.parent = p;
        if (p == null) {
            root = r;
            r.color = BLACK;
        } else {
            r.color = p.color;
            if (h == p.left) {
                p.left = r;
            } else {
                p.right = r;
            }
        }

        h.right = r.left;
        if (h.right != null) {
            h.right.parent = h;
        }
        h.parent = r;
        r.left = h;
        h.color = RED;
        return r;
    }

    /**
     * 右旋，将红色节点转到右边
     *
     * @param h 当前节点
     * @return 新当前节点
     */
    private RBTNode rotateRight(RBTNode h) {
        if (h == null || h.left == null) {
            return h;
        }

        RBTNode p = h.parent;
        RBTNode l = h.left;
        l.parent = p;
        if (p == null) {
            root = l;
            l.color = BLACK;
        } else {
            l.color = p.color;
            if (h == p.left) {
                p.left = l;
            } else {
                p.right = l;
            }
        }

        h.left = l.right;
        if (h.left != null) {
            h.left.parent = h;
        }
        h.parent = l;
        l.right = h;
        h.color = RED;
        return l;
    }

    /**
     * 设置节点颜色
     *
     * @param h     节点
     * @param color 颜色
     */
    private void setColor(RBTNode h, boolean color) {
        if (h != null) {
            h.color = color;
        }
    }

    /**
     * 是否是红色
     *
     * @param h 节点
     * @return true/false
     */
    private boolean isRed(RBTNode h) {
        return h != null && h.color == RED;
    }

    @Override
    public String toString() {
        return Arrays.toString(new RedBlackTreeSerializer().serialize(root));
    }

}
