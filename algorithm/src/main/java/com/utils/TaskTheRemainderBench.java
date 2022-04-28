package com.utils;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author li-yuanwen
 * @date 2021/11/1
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 4) // 先预热4轮
@Measurement(iterations = 10) // 进行10轮测试
public class TaskTheRemainderBench {

    private int num1;
    private int num2;
    private AtomicLong value;
    private int add;

    @Setup(Level.Trial)
    public void init() {
        num1 = 150;
        num2 = 500;
        value = new AtomicLong(0);
        add = 30;
    }

    @Benchmark
    public void test_num1() {
        long v = value.addAndGet(add);
        int s =(int) ( v % num1);
    }

    @Benchmark
    public void test_num2() {
        long v = value.addAndGet(add);
        int x = (int) (v % num2) ;
    }


    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(TaskTheRemainderBench.class.getSimpleName())
                .build();
        new Runner(options).run();
    }
}
