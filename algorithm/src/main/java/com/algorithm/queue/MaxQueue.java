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
    public I get() {
        return items[1];
    }

    @Override
    public I del() {
        I i = items[1];
        exch(1, size--);
        items[size + 1] = null;
        sink(1);
        return i;
    }

}
