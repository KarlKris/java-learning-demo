package com.algorithm.foundation.uf;

/**
 * @author li-yuanwen
 */
public class QuickFind extends UnionFind {

    public QuickFind(int n) {
        super(n);
    }

    @Override
    public int find(int p) {
        return id[p];
    }

    @Override
    public void union(int p, int q) {
        int i = id[p];
        int j = id[q];

        if (i == j) {
            return;
        }

        for (int z = 0; z < id.length; z++) {
            if (id[z]== i) {
                id[z] = j;
            }
        }
        count--;

    }
}
