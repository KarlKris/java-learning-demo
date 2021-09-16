package com.rank.list;

import cn.hutool.core.util.RandomUtil;
import com.rank.RankItem;
import com.rank.RankList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author li-yuanwen
 */
public class RankListByArrayList implements RankList {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final List<RankItem> lists = new ArrayList<>();

    /** 长度 **/
    private int size;

    public RankListByArrayList(int size) {
        this.size = size;
    }

    @Override
    public int getRankByPlayerId(long playerId) {
        RankItem item = new RankItem(playerId, 0L);
        lock.readLock().lock();
        try {
            int temp;
            return (temp = lists.indexOf(item)) == -1 ? -1 : temp + 1;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void update(long playerId, long curVal) {
        lock.writeLock().lock();
        try {
            RankItem item = new RankItem(playerId, curVal);
            int index = lists.indexOf(item);
            if (index >= 0) {
                // 添加
                lists.remove(index);
            }

            int addTo = binarySearch(lists, item);
            if (addTo < 0) {
                lists.add(item);
            } else {
                lists.add(addTo, item);
            }
            while (lists.size() > size) {
                lists.remove(lists.size() - 1);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<RankItem> getSubList(int from, int to) {
        lock.readLock().lock();
        try {
            int size = lists.size();
            if (from < 0) {
                // 防止subList前端越界
                from = 0;
            }
            if (from > size - 1) {
                return new LinkedList<RankItem>();
            }
            to = to + 1;
            if (to > size) {
                to = size;
            }
            return new ArrayList<RankItem>(lists.subList(from, to));
        } finally {
            lock.readLock().unlock();
        }
    }


    private static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<? super T> midVal = list.get(mid);
            int cmp = midVal.compareTo(key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid; // key found
            }
        }
        return low; // key not found
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(8, 8
                , 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        int total = 500000;
        CountDownLatch latch = new CountDownLatch(total);
        RankList list = new RankListByArrayList(total>>1);
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            executorService.submit(()->{
                try {
                    long id = RandomUtil.randomInt(5000000);
                    long value = RandomUtil.randomInt(500000000);
                    long t = System.nanoTime();
                    list.update(id, value);
                    System.out.println("单次更新耗时:" + (System.nanoTime() - t));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        System.out.println("耗时:" + (System.currentTimeMillis() - start));
        executorService.shutdown();
        System.out.println("done");
    }
}
