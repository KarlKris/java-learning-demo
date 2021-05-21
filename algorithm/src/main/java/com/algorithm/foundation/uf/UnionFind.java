package com.algorithm.foundation.uf;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RandomUtil;

/**
 * @author li-yuanwen
 */
public abstract class UnionFind {

    /** 分量id(以触点作为索引) **/
    protected int[] id;
    /** 分量数量 **/
    protected int count;

    public UnionFind(int n) {
        count = n;
        id = new int[count];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public abstract int find(int p);

    public abstract void union(int p, int q);

    public static void main(String[] args) {
        int count = 100;
        UnionFind find1 = new QuickUnionFind(count);
        UnionFind find2 = new QuickFind(count);
        int i = 0;
        while (i++ < 50) {
            int p = RandomUtil.randomInt(count);
            int q = RandomUtil.randomInt(count);

            if (!find1.connected(p, q)) {
                find1.union(p, q);
                System.out.println("find1:" + p + "-->" + q);
            }

            if (!find2.connected(p, q)) {
                find2.union(p, q);
                System.out.println("find2:" + p + "-->" + q);
            }


        }
        System.out.println("find1:" + ArrayUtil.toString(find1.id));
        System.out.println("find2:" + ArrayUtil.toString(find2.id));


        System.out.println(ArrayUtil.equals(find1.id, find2.id));

    }
}
