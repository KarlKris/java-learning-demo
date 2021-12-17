package test;

import java.util.Random;
import java.util.Stack;

/**
 * @author li-yuanwen
 * @date 2021/12/17
 */
public class SkipList<T> {

    /**
     * 最大的层
     **/
    static final int MAX_LEVEL = 32;

    /**
     * 头节点，入口
     **/
    private SkipNode<T> headNode;
    /**
     * 层数
     **/
    private int highLevel;
    /**
     * 用于投掷硬币
     **/
    private Random random;

    public SkipList() {
        random = new Random();
        headNode = new SkipNode<T>(Integer.MIN_VALUE, null);
        highLevel = 0;
    }

    public SkipNode<T> search(int key) {
        SkipNode<T> team = headNode;
        while (team != null) {
            if (team.key == key) {
                return team;
            }
            // 右侧没有了，只能下降
            else if (team.right == null) {
                team = team.down;
            }
            //需要下降去寻找
            else if (team.right.key > key) {
                team = team.down;
            }
            //右侧比较小向右
            else {
                team = team.right;
            }
        }
        return null;
    }

    public void delete(int key) {
        //删除不需要考虑层数
        SkipNode<T> team = headNode;
        while (team != null) {
            //右侧没有了，说明这一层找到，没有只能下降
            if (team.right == null) {
                team = team.down;
            }
            //找到节点，右侧即为待删除节点
            else if (team.right.key == key) {
                //删除右侧节点
                team.right = team.right.right;
                //向下继续查找删除
                team = team.down;
            }
            //右侧已经不可能了，向下
            else if (team.right.key > key) {
                team = team.down;
            } else {
                //节点还在右侧
                team = team.right;
            }
        }
    }

    public void add(SkipNode<T> node) {

        int key = node.key;
        SkipNode<T> findNode = search(key);
        //如果存在这个key的节点
        if (findNode != null) {
            findNode.value = node.value;
            return;
        }

        //存储向下的节点，这些节点可能在右侧插入节点
        Stack<SkipNode<T>> stack = new Stack<>();
        //查找待插入的节点   找到最底层的哪个节点。
        SkipNode<T> team = headNode;
        //进行查找操作
        while (team != null) {
            //右侧没有了，只能下降
            if (team.right == null) {
                //将曾经向下的节点记录一下
                stack.add(team);
                team = team.down;
            }
            //需要下降去寻找
            else if (team.right.key > key) {
                //将曾经向下的节点记录一下
                stack.add(team);
                team = team.down;
            }
            //向右
            else {
                team = team.right;
            }
        }

        //当前层数，从第一层添加(第一层必须添加，先添加再判断)
        int level = 1;
        //保持前驱节点(即down的指向，初始为null)
        SkipNode<T> downNode = null;
        while (!stack.isEmpty()) {
            //在该层插入node
            //抛出待插入的左侧节点
            team = stack.pop();
            //节点需要重新创建
            SkipNode<T> nodeTeam = new SkipNode<T>(node.key, node.value);
            //处理竖方向
            nodeTeam.down = downNode;
            //标记新的节点下次使用
            downNode = nodeTeam;
            //右侧为null 说明插入在末尾
            if (team.right != null) {
                //右侧还有节点，插入在两者之间
                nodeTeam.right = team.right;
            }
            team.right = nodeTeam;
            //考虑是否需要向上
            //已经到达最高级的节点啦
            if (level > MAX_LEVEL) {
                break;
            }
            //[0-1]随机数
            double num = random.nextDouble();
            //运气不好结束
            if (num > 0.5) {
                break;
            }
            level++;
            //比当前最大高度要高但是依然在允许范围内 需要改变head节点
            if (level > highLevel) {
                highLevel = level;
                //需要创建一个新的节点
                SkipNode<T> highHeadNode = new SkipNode<T>(Integer.MIN_VALUE, null);
                highHeadNode.down = headNode;
                //改变head
                headNode = highHeadNode;
                //下次抛出head
                stack.add(headNode);
            }
        }

    }

    public void printList() {
        SkipNode<T> teamNode = headNode;
        int index = 1;
        SkipNode<T> last = teamNode;
        while (last.down != null) {
            last = last.down;
        }
        while (teamNode != null) {
            SkipNode<T> enumNode = teamNode.right;
            SkipNode<T> enumLast = last.right;
            System.out.printf("%-8s", "head->");
            while (enumLast != null && enumNode != null) {
                if (enumLast.key == enumNode.key) {
                    System.out.printf("%-5s", enumLast.key + "->");
                    enumLast = enumLast.right;
                    enumNode = enumNode.right;
                } else {
                    enumLast = enumLast.right;
                    System.out.printf("%-5s", "");
                }

            }
            teamNode = teamNode.down;
            index++;
            System.out.println();
        }
    }

    static class SkipNode<T> {
        int key;
        T value;
        //左右上下四个方向的指针
        SkipNode<T> right, down;

        public SkipNode(int key, T value) {
            this.key = key;
            this.value = value;
        }

    }

    public static void main(String[] args) {
        SkipList<Integer> list = new SkipList<Integer>();
        for (int i = 1; i < 20; i++) {
            list.add(new SkipNode<Integer>(i, 666));
        }
        list.printList();
        list.delete(4);
        list.delete(9);
        list.printList();
    }
}
