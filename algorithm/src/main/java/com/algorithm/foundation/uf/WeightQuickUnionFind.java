package com.algorithm.foundation.uf;

/**
 * @author li-yuanwen
 */
public class WeightQuickUnionFind extends UnionFind {

    private int[] wz;

    public WeightQuickUnionFind(int n) {
        super(n);
        wz = new int[n];
        for (int i = 0; i < n; i++) {
            wz[i] = 1;
        }
    }

    @Override
    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    @Override
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if (i == j) {
            return;
        }

        if (wz[i] < wz[j]) {
            id[i] = j;
            wz[j]+= wz[i];
        }else {
            id[j] = i;
            wz[i]+=wz[j];
        }

        count--;
    }
}
