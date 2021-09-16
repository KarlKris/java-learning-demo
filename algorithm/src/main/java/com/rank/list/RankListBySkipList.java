package com.rank.list;

import cn.hutool.core.util.RandomUtil;
import com.rank.RankItem;
import com.rank.RankList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author li-yuanwen
 * 通过数组实现的排行榜
 */
public class RankListBySkipList implements RankList {

    /** 实际排行榜 **/
    private final SkipList<RankItem> list = new SkipList<>();
    /** 玩家2排行榜值 **/
    private final Map<Long, Long> playerId2Value;

    /** 读写锁 **/
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public RankListBySkipList(int cap) {
        this.playerId2Value = new HashMap<>(cap);
    }


    @Override
    public int getRankByPlayerId(long playerId) {
        Lock readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            Long value = playerId2Value.get(playerId);
            if (value == null) {
                return -1;
            }
            return list.getRank(new RankItem(playerId, value));
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void update(long playerId, long curVal) {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            Long value = playerId2Value.remove(playerId);
            if (value != null) {
                list.remove(new RankItem(playerId, value));
            }
            list.add(new RankItem(playerId, curVal));
            playerId2Value.put(playerId, curVal);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public List<RankItem> getSubList(int from, int to) {
        Lock readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            return list.getSubList(from, to);
        } finally {
            readLock.unlock();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(8, 8
                , 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        int total = 500000;
        CountDownLatch latch = new CountDownLatch(total);
        RankList list = new RankListBySkipList(total);
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
