package com.rank;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author li-yuanwen
 */
@BenchmarkMode(Mode.Throughput) // 吞吐量
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
@Warmup(iterations = 4) // 先预热4轮
@Measurement(iterations = 10) // 进行10轮测试
public class RankListBenchmark {


    /** 排行榜 **/

    @Setup(Level.Trial)
    public void init() {

    }


    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(RankListBenchmark.class.getSimpleName())
                .build();
        new Runner(options).run();
    }
}
