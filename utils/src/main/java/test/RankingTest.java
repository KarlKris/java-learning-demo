package test;

import cn.hutool.core.util.RandomUtil;
import method.RankItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description 排行榜测试类
 * @Author li-yuanwen
 * @Date 2021/4/27 16:47
 */
public class RankingTest {

    private List<RankItem> arrayList = new ArrayList<>(100);
    private ReadWriteLock arrayLock = new ReentrantReadWriteLock();
    private AtomicInteger arrayCount = new AtomicInteger(0);
    private AtomicLong arrayTime = new AtomicLong(0);

    private List<RankItem> linkedList = new LinkedList<>();
    private ReadWriteLock linkedLock = new ReentrantReadWriteLock();
    private AtomicInteger linkedCount = new AtomicInteger(0);
    private AtomicLong linkedTime = new AtomicLong(0);

    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    public void start() {

        executorService.submit(() -> {
            while (!Thread.interrupted()) {
                long id = RandomUtil.randomLong(2000);
                long value = RandomUtil.randomLong();
                RankItem item = new RankItem(id, value);
                long start = System.nanoTime();
                arrayLock.writeLock().lock();
                try {
                    int index = arrayList.indexOf(item);
                    if (index >= 0) {
                        // 添加
                        arrayList.remove(index);
                    }

                    int addTo = binarySearch(arrayList, item);
                    if (addTo < 0) {
                        arrayList.add(item);
                    } else {
                        arrayList.add(addTo, item);
                    }

                    // 截断
                    while (arrayList.size() > 2000) {
                        arrayList.remove(arrayList.size() - 1);
                    }
                } finally {
                    arrayLock.writeLock().unlock();
                }
                arrayTime.addAndGet(System.nanoTime() - start);
                arrayCount.incrementAndGet();

            }
        });

        executorService.submit(() -> {
            while (!Thread.interrupted()) {
                long id = RandomUtil.randomLong(2000);
                long value = RandomUtil.randomLong();
                RankItem item = new RankItem(id, value);
                long start = System.nanoTime();
                linkedLock.writeLock().lock();
                try {
                    int index = linkedList.indexOf(item);
                    if (index >= 0) {
                        // 添加
                        linkedList.remove(index);
                    }

                    int addTo = binarySearch(linkedList, item);
                    if (addTo < 0) {
                        linkedList.add(item);
                    } else {
                        linkedList.add(addTo, item);
                    }

                    // 截断
                    while (linkedList.size() > 2000) {
                        linkedList.remove(linkedList.size() - 1);
                    }
                } finally {
                    linkedLock.writeLock().unlock();
                }
                linkedTime.addAndGet(System.nanoTime() - start);
                linkedCount.incrementAndGet();

            }
        });





    }

    private void get() {
        executorService.submit(()->{
            int i = 0;
            while (i++ < 10) {
                int page = RandomUtil.randomInt(10);
                int from = page * 10;
                int to = (page + 1) * 10;
                long start = System.nanoTime();
                arrayLock.readLock().lock();
                try{
                    int size = arrayList.size();
                    if (from >= size) {
                        return;
                    }
                    if (to > size) {
                        to = size;
                    }
                    arrayList.subList(from, to);
                }finally {
                    arrayLock.readLock().unlock();
                }
                System.out.println("ArrayList subList from:" + from + " to:" + to + " 耗时:" + (System.nanoTime() - start));
            }
        });

        executorService.submit(()->{
            int i = 0;
            while (i++ < 10) {
                int page = RandomUtil.randomInt(200);
                int from = page * 10;
                int to = (page + 1) * 10;
                long start = System.nanoTime();
                linkedLock.readLock().lock();
                try{
                    int size = linkedList.size();
                    if (from >= size) {
                        return;
                    }
                    if (to > size) {
                        to = size;
                    }
                    linkedList.subList(from, to);
                }finally {
                    linkedLock.readLock().unlock();
                }
                System.out.println("LinkedList subList from:" + from + " to:" + to + " 耗时:" + (System.nanoTime() - start));
            }
        });
    }

    private void getAvg() {
        arrayLock.writeLock().lock();
        try {
            System.out.println("ArrayList Avg Add : " + arrayTime.get() / arrayCount.get());
        }finally {
            arrayLock.writeLock().unlock();
        }

        linkedLock.writeLock().lock();
        try {
            System.out.println("LinkedList Avg Add : " + linkedTime.get() / linkedCount.get());
        }finally {
            linkedLock.writeLock().unlock();
        }

    }

    private void shutdown() {
        executorService.shutdownNow();
        getAvg();
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
        RankingTest test = new RankingTest();
        test.start();
        Thread.sleep(1000);
        for (int i = 0 ; i < 10; i++) {
            test.get();
            Thread.sleep(1000);
        }

        test.get();
        Thread.sleep(1000);
        test.shutdown();
    }

}
