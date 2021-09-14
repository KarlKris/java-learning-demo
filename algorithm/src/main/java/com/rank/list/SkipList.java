package com.rank.list;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author li-yuanwen
 * 跳表(基于链表+索引的数据结构,能达到树的查询效率(logn),但复杂度比树低)
 * Skip list的性质
 * (1) 由很多层结构组成，level是通过一定的概率随机产生的。
 * (2) 每一层都是一个有序的链表,这里是降序
 * (3) 最底层(Level=1)的链表包含所有元素。
 * (4) 如果一个元素出现在Level=i 的链表中，则它在Level<i的链表也都会出现。
 * (5) 每个节点包含两个指针，一个指向同一链表中的下一个元素，一个指向下面一层的元素。
 */
public class SkipList<E extends Comparable<E>> {


    /** 跳表层数 定义成32层理论上对于2^32-1个元素的查询最优 **/
    private static final int MAX_LEVEL = 32;

    /** 当前跳表的有效层数 **/
    private int level = 0;

    /** 跳表的头部节点 **/
    private final SkipNode<E> header = new SkipNode<>(MAX_LEVEL, null);

    /** 随机数生成器 **/
    private static final Random RANDOM = new Random();
    /** 增加层数的阈值 **/
    private static final double E = Math.E - 2;


    /**
     * 检查跳表中是否包含某元素
     * @param value 元素
     * @return true 包含
     */
    public boolean contains(E value) {
        // 指向头节点
        SkipNode<E> cur = this.header;
        // 从顶层开始遍历当前层是否包含节点node,如果包含,直接返回true;
        // 否则在下一层中查找是否包含;
        // 如果最底层也不包含,则返回false
        for (int i = level; i >= 0; i--) {

            // 降序
            while (cur.next[i] != null && cur.next[i].value.compareTo(value) > 0) {
                cur = cur.next[i];
            }

            if (Objects.equals(cur.next[i].value, value)) {
                return true;
            }
        }

        return false;
    }


    /**
     * 添加元素至跳表
     * @param value 元素
     */
    public void add(E value) {
        // 指向头节点
        SkipNode<E> cur = this.header;
        // 前继节点
        SkipNode<E>[] predecessors = new SkipNode[MAX_LEVEL];
        // 找出待插入节点的前继节点
        for (int i = level; i >= 0; i--) {
            cur  = this.header;
            while (cur.next[i] != null && cur.next[i].value.compareTo(value) > 0) {
                cur = cur.next[i];
            }
            predecessors[i] = cur;
        }
        // 最底层链表
        cur = cur.next[0];
        // 如果带插入节点位置是空的或者与node节点值不同 将新节点插入到跳表中
        if (cur == null || !Objects.equals(cur.value, value)) {
            int nextLevel = randomLevel();
            // 若新增一层
            if (nextLevel > level) {
                predecessors[nextLevel] = header;
                level = nextLevel;
            }
            SkipNode<E> node = new SkipNode<>(MAX_LEVEL, value);
            for (int i = level; i >= 0; i--) {
                node.next[i] = predecessors[i].next[i];
                predecessors[i].next[i] = node;
            }
        }
    }


    /**
     * 移除跳表中的某个元素
     * @param value 元素
     */
    public void remove(E value) {
        // 指向头节点
        SkipNode<E> cur = this.header;
        // 前继节点
        SkipNode<E>[] predecessors = new SkipNode[MAX_LEVEL];
        // 找出待插入节点的前继节点
        for (int i = level; i >= 0; i--) {
            cur  = this.header;
            while (cur.next[i] != null && cur.next[i].value.compareTo(value) > 0) {
                cur = cur.next[i];
            }
            predecessors[i] = cur;
        }
        // 最底层链表
        cur = cur.next[0];
        // 跳表不包含
        if (!Objects.equals(cur.value, value)) {
            return;
        }

        for (int i = level; i >= 0; i--) {
            if (!Objects.equals(predecessors[i].value, value)) {
                continue;
            }
            predecessors[i].next[i] = cur.next[i];
        }

        // 如果删除元素val后level层元素数目为0，层数减少一层
        while (level > 0 && this.header.next[level] == null) {
            level--;
        }
    }


    /**
     * 利用随机数发生器来决定是否新增一层
     * @return
     */
    private int randomLevel() {
        double ins = RANDOM.nextDouble();
        int nextLevel = level;
        if (ins > E && level < MAX_LEVEL) {
            nextLevel++;
        }
        return nextLevel;
    }

    /**
     * 输出跳表中的元素
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = level; i >= 0; i--) {
            SkipNode<E> cur = this.header.next[i];
            sb.append(i + ":{");
            while (cur.next[i] != null) {
                sb.append(cur.value);
                sb.append(",");
                cur = cur.next[i];
            }
            sb.append(cur.value);
            sb.append("}");
            sb.append("\r\n");
        }
        return sb.toString();
    }


    /** 跳表节点 **/
    class SkipNode<E extends Comparable<E>> {

        /** 节点数据 **/
        private E value;
        /** 节点指向i层的节点 **/
        private SkipNode<E>[] next;

        @SuppressWarnings("unchecked")
        SkipNode(int level, E val) {
            this.next = new SkipNode[level];
            this.value = val;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int total = 10000;
        ExecutorService executorService = new ThreadPoolExecutor(4, 4
                , 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(total));
        SkipList<Integer> list = new SkipList<>();
        CountDownLatch latch = new CountDownLatch(total);
        for (int i = 0; i < total; i++) {
            executorService.execute(()->{
                try {
                    int random = RANDOM.nextInt(100);
                    list.add(random);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        System.out.println(list);
    }

}
