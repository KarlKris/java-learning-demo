package com.algorithm.queue;

/**
 * @author li-yuanwen
 */
public class MinQueue<I extends Comparable<I>> extends AbstractQueue<I> {

    public MinQueue(I[] items) {
        super(items);
        assert isMinHeap();
    }

    public MinQueue(int size) {
        super(size);
    }


    @Override
    protected boolean compare(int i, int j) {
        return greater(i, j);
    }

    // is pq[1..n] a min heap?
    private boolean isMinHeap() {
        for (int i = 1; i <= size(); i++) {
            if (get(i) == null) {
                return false;
            }
        }
        for (int i = size() + 1; i < getLength(); i++) {
            if (get(i) != null) {
                return false;
            }
        }
        if (get(0) != null) {
            return false;
        }
        return isMinHeapOrdered(1);
    }

    // is subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeapOrdered(int k) {
        if (k > size()) {
            return true;
        }

        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= size() && greater(k, left)) {
            return false;
        }

        if (right <= size() && greater(k, right)) {
            return false;
        }
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

    protected boolean greater(int i, int j) {
        return get(i).compareTo(get(j)) > 0;
    }
}
