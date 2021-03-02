package com;


import com.model.RedBlackTreeNode;
import com.utils.TreePrintUtil;

import java.util.HashSet;
import java.util.Set;

//红黑树 0false 红色    1 true 黑色
//插入操作所面临的5个情形
//情形 1: 如果当前节点是根结点，为满足性质 2，所以直接把此结点 z 涂为黑色
//情形 2: 如果当前结点的父结点是黑色，由于不违反性质 2 和性质 4，红黑树没有被破坏，所以此时也是什么也不做
//情形 3: 当前结点的父结点是红色且祖父结点的另一个子结点(叔叔结点)是红色  将当前结点的父结点和叔叔结点涂黑，祖父结点涂红，再把祖父结点当做新节点(即当前节点的指针指向祖父节点)重新检查各种情形进行调整
//情形 4: 当前结点的父结点是红色，叔叔结点是黑色或者 nil，当前结点相对其父结点的位置和父节点相对祖父节点的位置不在同侧
//如果当前节点是父节点的右子，父节点是祖父节点的左子，以当前结点的父结点做为新结点(即当前节点的指针指向父节点)，并作为支点左旋
//如果当前节点是父节点的左子，父节点是祖父节点的右子，以当前结点的父结点做为新结点(即当前节点的指针指向父节点)，并作为支点右旋
//情形 5: 当前结点的父结点是红色，叔叔结点是黑色或者 nil，当前结点相对其父结点的位置和父节点相对祖父节点的位置在同侧
//首先把父结点变为黑色，祖父结点变为红色
//如果当前节点是父节点的左子，父节点是祖父节点的左子，以祖父结点为支点右旋
//如果当前节点是父节点的右子，父节点是祖父节点的右子，以祖父结点为支点左旋
//删除操作所面临的情形
//
public class RedBlackTree {

    public final String BlACK = "BlACK";
    public final String RED = "RED";

    public RedBlackTreeNode active;
    //始终指向根节点
    private RedBlackTreeNode root;

    public RedBlackTree(Set<Integer> set) {
        this.root = null;
        for (int i : set) {
            System.out.println("插入:" + String.valueOf(i));
            insert(i);
        }
    }

    public RedBlackTree() {
        this.root = null;
    }

    public void insert(int value) {
        RedBlackTreeNode node = new RedBlackTreeNode(value);
        //若还没有根节点
        if (root == null) {
            root = node;
            active = node;
        } else {
            root = findRoot();
            insertTreeNode(node);
            active = node;
        }
        while (searchActive() != null) {
            System.out.println("当前指针:" + active.getValue());
            adjustment();
        }
        toString();
        System.out.println();
    }

    //插入结点
    public void insertTreeNode(RedBlackTreeNode tn) {
        //临时引用
        RedBlackTreeNode temp;
        if (tn.getValue() == root.getValue()) {
            System.out.println("不允许插入已有的数");
            return;
        }
        //若新插入的结点小于根结点
        if (tn.getValue() < root.getValue()) {
            temp = root.getLeft();
            //若孩子为空，则注入到这个地方，且注入父节点
            if (temp == null) {
                root.setLeft(tn);
                tn.setParent(root);
                return;
            }
        }
        //新插入的结点大于节点
        else {
            temp = root.getRight();
            //若孩子为空，则注入到这个地方，且注入父节点
            if (temp == null) {
                root.setRight(tn);
                tn.setParent(root);
                return;
            }
        }
        root = temp;
        insertTreeNode(tn);
        return;
    }

    //解决插入后出现的5种情形
    public void adjustment() {
        //获得当前节点指针
        RedBlackTreeNode current = searchActive();
        //如果为null则代表现在满足性质，否则是下面5种情况
        if (current == null) {
            return;
        }
        //1.当前结点为根节点
        if (current.getParent() == null) {
            //根结点变黑色
            current.setColor(BlACK);
            //当前指针作废，结束
            active = null;
            return;
        }
        //2.当前结点的父节点是红色
        if (current.getParent().getColor().equals(RED)) {
            //且叔叔节点是红色
            RedBlackTreeNode uncle = getUncleTreeNode(current);
            if (uncle != null && uncle.getColor().equals(RED)) {
                System.out.println("当前指针:" + current.getValue() + "叔叔:" + uncle.getValue());
                //父节点变黑色
                current.getParent().setColor(BlACK);
                //叔叔结点变黑色
                uncle.setColor(BlACK);
                //祖父结点涂红
                current.getParent().getParent().setColor(RED);
                //修改当前节点指针,祖父成为当前结点
                active = current.getParent().getParent();
                return;
            }
            //3.叔叔结点不是红色
            else {
                //剩余两种情况均在这个方法解决
                locationOfJudgment(current);
                return;
            }
        } else {
            //5.父节点是黑色，什么都不做，修改指针即可
            active = null;
        }

    }

    //返回当前指针
    public RedBlackTreeNode searchActive() {
        return active;

    }

    //获得叔叔结点
    public RedBlackTreeNode getUncleTreeNode(RedBlackTreeNode tn) {
        RedBlackTreeNode parent = tn.getParent();
        RedBlackTreeNode grandpa = parent.getParent();
        //判断叔叔结点是否是NIL
        if (grandpa.getLeft() != null && grandpa.getLeft().equals(parent)) {
            return grandpa.getRight();
        }
        return grandpa.getLeft();
    }

    //爷孙三人位置判断是否同侧，并进行相应的旋转
    public void locationOfJudgment(RedBlackTreeNode me) {
        RedBlackTreeNode parent = me.getParent();
        RedBlackTreeNode grandpa = parent.getParent();

        RedBlackTreeNode leftUncle = grandpa.getLeft();
        RedBlackTreeNode rightUncle = grandpa.getRight();

        RedBlackTreeNode leftBro = parent.getLeft();
        //我在父亲的左侧
        if (leftBro != null && leftBro.equals(me)) {
            //父亲在祖父的右侧,即异侧
            if (rightUncle != null && rightUncle.equals(parent)) {
                //指针指向父亲
                active = parent;
                //以父亲为支点右旋
                rightRotale(parent);
            }
            //父亲在祖父的左侧,即同侧
            else {
                //父变黑，祖父变红
                parent.setColor(BlACK);
                grandpa.setColor(RED);
                //当前节点是父节点的左子，父节点是祖父节点的左子，以祖父结点为支点右旋
                rightRotale(grandpa);
                //完成
                active = null;

            }
        }
        //我在父亲的右侧
        else {
            //父亲在祖父的左侧,即异侧
            if (leftUncle != null && leftUncle.equals(parent)) {
                //指针指向父亲
                active = parent;
                //以父亲为支点左旋
                leftRotale(parent);
            }
            //父亲在祖父的右侧,即同侧
            else {
                System.out.println("开始左旋.....");
                //父变黑，祖父变红
                parent.setColor(BlACK);
                grandpa.setColor(RED);
                //当前节点是父节点的右子，父节点是祖父节点的右子，以祖父结点为支点左旋
                leftRotale(grandpa);
                //完成
                active = null;
            }
        }
    }

    //删除结点
    public void delete(int value) {
        try {
            RedBlackTreeNode max = findMaximinAndCopyValue(findNode(value));
            //调用方法
            deleteNode(max);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("......................");
            e.printStackTrace();
        }
    }

    //删除结点操作
    public void deleteNode(RedBlackTreeNode node) {
        RedBlackTreeNode parent = node.getParent();
        if (node.getLeft() == null && node.getRight() == null) {
            //如果子树均为空，则设置为NULL
            //因为左子树的最大值一定是在右子树
            parent.setRight(null);
            return;
        }
        //存在左子树
        RedBlackTreeNode leftChild = node.getLeft();
        //左子树代替最值结点
        parent.setRight(leftChild);
        leftChild.setParent(parent);
        //查看最值结点颜色
        if (node.getColor().equals(RED)) {
            //红色
            return;
        } else {
            //黑色
            //查看替换后的结点颜色
            if (leftChild.getColor().equals(RED)) {
                //红色
                //则把该节点便黑色即可
                leftChild.setColor(BlACK);
            } else {
                //黑色

            }
        }
    }

    //找到要删除的结点
    public RedBlackTreeNode findNode(int value) throws Exception {
        RedBlackTreeNode temp = root;
        while (root.getValue() != value) {
            if (temp.getValue() > value) {
                //往左子树查找
                temp = temp.getLeft();
            } else {
                //往右子树查找
                temp = temp.getRight();
            }
            //如果子树不存在，则证明没有存储这个结点
            if (temp == null) {
                throw new Exception("没有这个值。。。。。");
            }
        }
        return temp;
    }

    //找到最值结点并复制最值结点的值到删除节点
    public RedBlackTreeNode findMaximinAndCopyValue(RedBlackTreeNode current) {
        //保存当前指针的引用
        RedBlackTreeNode temp = current;
        //满足当前指针即为最值时，能够成功
        int maxValue = current.getValue();
        //找到左子树的最大值
        RedBlackTreeNode max = current.getLeft();
        while (max != null) {
            //判断当前指向的树是否有右子树
            if (max.getRight() == null) {
                //如果没有，则this为最大值
                maxValue = max.getValue();
                break;
            }
            //迭代左子树
            max = max.getLeft();
        }
        //复制最值结点
        temp.setValue(maxValue);
        return max;
    }

    //得到非空子树,因为左子树的最值，所以返回最值的左子树即可
    public RedBlackTreeNode getSub(RedBlackTreeNode current) {
        return current.getLeft();
    }

    //得到兄弟结点
    public RedBlackTreeNode getBro(RedBlackTreeNode current) {
        RedBlackTreeNode parent = current.getParent();
        if (parent.getLeft().equals(current)) {
            return parent.getRight();
        }
        return parent.getLeft();
    }


    //左旋
    public void leftRotale(RedBlackTreeNode tn) {
        //获得三个需要改变的结点，加上参数这个支点，一共四个
        RedBlackTreeNode grandpa = tn.getParent();
        RedBlackTreeNode rightSon = tn.getRight();
        RedBlackTreeNode leftgrandson = rightSon.getLeft();
        //开始左旋
        //右孩子变为祖父的孩子
        if (grandpa != null) {
            if (grandpa.getLeft() != null && grandpa.getLeft().equals(tn)) {
                grandpa.setLeft(rightSon);
            } else {
                grandpa.setRight(rightSon);
            }
            rightSon.setParent(grandpa);
        } else {
            rightSon.setParent(null);
        }
        //支点变为右孩子的左孩子
        rightSon.setLeft(tn);
        tn.setParent(rightSon);
        //左孙子变为支点的右孩子
        if (leftgrandson != null) {
            leftgrandson.setParent(tn);
        }
        tn.setRight(leftgrandson);
    }

    //右旋
    public void rightRotale(RedBlackTreeNode tn) {
        //获得三个需要改变的结点，加上参数这个支点，一共四个
        RedBlackTreeNode grandpa = tn.getParent();
        RedBlackTreeNode leftSon = tn.getLeft();
        RedBlackTreeNode rightgrandson = leftSon.getRight();
        //开始右旋
        //左孩子变为祖父的孩子
        if (grandpa != null) {
            if (grandpa.getLeft() != null && grandpa.getLeft().equals(tn)) {
                grandpa.setLeft(leftSon);
            } else {
                grandpa.setRight(leftSon);
            }
            leftSon.setParent(grandpa);
        } else {
            leftSon.setParent(null);
        }
        //支点变为左孩子的右孩子
        leftSon.setRight(tn);
        tn.setParent(leftSon);
        //右孙子变为支点的左孩子
        if (rightgrandson != null) {
            rightgrandson.setParent(tn);
        }
        tn.setLeft(rightgrandson);
    }


    //找根结点，即没有父母的结点
    public RedBlackTreeNode findRoot() {
        RedBlackTreeNode temp = root;
        root = root.getParent();
        if (root != null) {
            root = findRoot();
            return root;
        }
        return temp;
    }


    @Override
    public String toString() {
        root = findRoot();
        TreePrintUtil.displayTree(root);
        return "";


    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
//		for(int i=0;i<10;i++){
//			set.add((int)(Math.random()*100)+1);
//		}
        set.add(48);
        set.add(5);
        set.add(54);
        set.add(55);
        set.add(24);
        set.add(73);
        set.add(59);
        set.add(63);
        set.add(15);
        set.add(47);
        System.out.println(set);
        RedBlackTree rbt = new RedBlackTree(set);
        rbt.insert(58);
    }

}
