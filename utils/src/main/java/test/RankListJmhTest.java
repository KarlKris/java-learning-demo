package test;

import cn.hutool.core.util.RandomUtil;
import method.RankItem;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/4/27 17:33
 */
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class RankListJmhTest {


    private List<RankItem> arrayList = new ArrayList<>(100);
    private ReadWriteLock arrayLock = new ReentrantReadWriteLock();

    private List<RankItem> linkedList = new LinkedList<>();
    private ReadWriteLock linkedLock = new ReentrantReadWriteLock();

    @Benchmark
    public void addArray() {
        long id = RandomUtil.randomLong(2000);
        long value = RandomUtil.randomLong();
        RankItem item = new RankItem(id, value);
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
    }

    @Benchmark
    public void addLinked() {
        long id = RandomUtil.randomLong(2000);
        long value = RandomUtil.randomLong();
        RankItem item = new RankItem(id, value);
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
    }

    @Benchmark
    public void subListArray() {
        int page = RandomUtil.randomInt(10);
        int from = page * 10;
        int to = (page + 1) * 10;
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
    }

    @Benchmark
    public void subListLinked() {
        int page = RandomUtil.randomInt(10);
        int from = page * 10;
        int to = (page + 1) * 10;
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

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(RankListJmhTest.class.getSimpleName())//benchmark 所在的类的名字，这里可以使用正则表达式对所有类进行匹配。
                .measurementIterations(2) //实际测量的迭代次数
                .mode(Mode.Throughput)
                .threads(4)
                .syncIterations(false)
                .forks(1)
                .result("F://test_list_ranklist.json")
                .build();
        new Runner(opt).run();
    }

}
