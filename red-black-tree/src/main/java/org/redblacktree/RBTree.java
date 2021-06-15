package org.redblacktree;

/**
 * @Author: fizz
 * @Date:2021/6/15 16:21
 * @Version 1.0
 */
public class RBTree<T extends Comparable<T>> {

    private RBTNode<T> mRoot;    // 根结点

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public class RBTNode<T extends Comparable<T>> {
        boolean color;        // 颜色
        T key;                // 关键字(键值)
        RBTNode<T> left;    // 左孩子
        RBTNode<T> right;    // 右孩子
        RBTNode<T> parent;    // 父结点

        public RBTNode(T key, boolean color, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

    }

    /*
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)：
     *      px                              px
     *     /                               /
     *    x                               y
     *   /  \      --(左旋)->.           / \                #
     *  lx   y                          x  ry
     *     /   \                       /  \
     *    ly   ry                     lx  ly
     *
     *
     */
    private void leftRotate(RBTNode<T> x) {
        // 设置x的右孩子为y
        RBTNode<T> y = x.right;

        // 将 “y的左孩子” 设为 “x的右孩子”；
        // 如果y的左孩子非空，将 “x” 设为 “y的左孩子的父亲”
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;

        // 将 “x的父亲” 设为 “y的父亲”
        y.parent = x.parent;

        if (x.parent == null) {
            this.mRoot = y;            // 如果 “x的父亲” 是空节点，则将y设为根节点
        } else {
            if (x.parent.left == x)
                x.parent.left = y;    // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
            else
                x.parent.right = y;    // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
        }

        // 将 “x” 设为 “y的左孩子”
        y.left = x;
        // 将 “x的父节点” 设为 “y”
        x.parent = y;
    }


    /*
     * 对红黑树的节点(y)进行右旋转
     *
     * 右旋示意图(对节点y进行左旋)：
     *            py                               py
     *           /                                /
     *          y                                x
     *         /  \      --(右旋)->.            /  \                     #
     *        x   ry                           lx   y
     *       / \                                   / \                   #
     *      lx  rx                                rx  ry
     *
     */
    private void rightRotate(RBTNode<T> y) {
        //y的左孩子设置为x  （y的左孩子拷贝一份为 x）
        RBTNode<T> x = y.left;

        //先设置拷贝出来的x右孩子
        //x的父节点:判断y是否为根节点，
       // 为根节点设置x为根节点，
         //this.mRoot=x;
        //不为根节点在判断：y是否为左右孩子，
        //左孩子，x=y.parent.left
            //    右孩子:x=y.parent.right;
        //x的上层已经处理好，处理下层
        //x的右孩子设置y
        //y的父节点设置x;


        //将y的左孩子指向x的右孩子
        y.left = x.right;
        //判断x的右孩子情况：右孩子为空、不为空
        if (x.right != null) {
            x.right.parent = y;
        }
        //将x的父节点指向y父节点
        x.parent = y.parent;
        if (y.parent == null) {
            this.mRoot = x;
        } else {

            if (y == y.parent.left) {
                x = y.parent.left;
            } else {
                x = y.parent.right;
            }
        }
        x.right = y;
        y.parent = x;

    }

}
