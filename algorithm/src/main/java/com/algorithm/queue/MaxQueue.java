package com.algorithm.queue;

/**
 * @author li-yuanwen
 */
public class MaxQueue<I extends Comparable<I>> extends AbstractQueue<I> {

    public MaxQueue(I[] items) {
        super(items);
    }

    public MaxQueue(int size) {
        super(size);
    }

    @Override
    protected boolean compare(int i, int j) {
        return less(i, j);
    }

    private boolean less(int i, int j) {
        return get(i).compareTo(get(j)) < 0;
    }


}
