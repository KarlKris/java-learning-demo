package com.algorithm.foundation.uf;

/**
 * @author li-yuanwen
 */
public class QuickUnionFind extends UnionFind {

    public QuickUnionFind(int n) {
        super(n);
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

        id[i] = j;
        count--;
        
    }
}
