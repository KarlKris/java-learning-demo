package com.rank;

import cn.hutool.core.util.RandomUtil;
import com.rank.list.RankListByArrayList;
import com.rank.list.RankListByConcurrentSkipListSet;
import com.rank.list.RankListBySkipList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author li-yuanwen
 */
@BenchmarkMode(Mode.AverageTime) // 吞吐量
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 4) // 先预热4轮
@Measurement(iterations = 10) // 进行10轮测试
public class RankListBenchmark {


    /** 排行榜 **/
    private RankList skipList;
    private RankList arrayList;
    private RankList concurrentSkipListSet;

    @Setup(Level.Trial)
    public void init() {
        skipList = new RankListBySkipList(10000);
        arrayList = new RankListByArrayList(10000);
        concurrentSkipListSet = new RankListByConcurrentSkipListSet();
    }


    @Benchmark
    public void skipListUpdate() {
        long id = RandomUtil.randomInt(500000);
        long value = RandomUtil.randomInt(500000000);
        skipList.update(id, value);
    }

    @Benchmark
    public void arrayListUpdate() {
        long id = RandomUtil.randomInt(500000);
        long value = RandomUtil.randomInt(500000000);
        arrayList.update(id, value);
    }

    @Benchmark
    public void concurrentSkipListSetUpdate() {
        long id = RandomUtil.randomInt(500000);
        long value = RandomUtil.randomInt(500000000);
        concurrentSkipListSet.update(id, value);
    }

//    @Benchmark
//    public void skipListGetRank() {
//        skipList.getRankByPlayerId(RandomUtil.randomInt(5000000));
//    }
//
//    @Benchmark
//    public void skipListGetSubList() {
//        int from = RandomUtil.randomInt(0, 100);
//        int to = from + 100;
//        skipList.getSubList(from, to);
//    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(RankListBenchmark.class.getSimpleName())
                .build();
        new Runner(options).run();
    }
}
