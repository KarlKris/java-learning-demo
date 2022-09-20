package com.rhino;

import com.li.nashorn.Nashorn;
import com.li.rhino.Rhino;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.*;
import org.openjdk.jmh.runner.options.*;

import javax.script.ScriptException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author li-yuanwen
 * @date 2022/9/19
 */
@BenchmarkMode({Mode.Throughput, Mode.AverageTime})
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 5)
@Threads(8)
@OutputTimeUnit(TimeUnit.SECONDS)
public class RhinoBenchmark {

    private final static ExpressionContext CONTEXT = new ExpressionContext();
    private final static String EXPRESSION = "getAttribute(1)+getAttribute(2)+getAttribute(3)+getAttribute(4)+getAttribute(5)+getAttribute(6)+getAttribute(7)+getAttribute(8)+getAttribute(9)+getAttribute(10)";

    private final static String NASHORN_EXPRESSION = "ctx.getAttribute(1)+ctx.getAttribute(2)+ctx.getAttribute(3)+ctx.getAttribute(4)+ctx.getAttribute(5)+ctx.getAttribute(6)+ctx.getAttribute(7)+ctx.getAttribute(8)+ctx.getAttribute(9)+ctx.getAttribute(10)";


    @Benchmark
    public void nashorn() throws ScriptException {
        for (int i = 0; i < 1000; i++) {
            Nashorn.eval(NASHORN_EXPRESSION, CONTEXT, Long.class);
        }
    }

    @Benchmark
    public void normal() {
        for (int i = 0; i < 1000; i++) {
            long value = CONTEXT.getAttribute(1)
                    + CONTEXT.getAttribute(2)
                    + CONTEXT.getAttribute(3)
                    + CONTEXT.getAttribute(4)
                    + CONTEXT.getAttribute(5)
                    + CONTEXT.getAttribute(6)
                    + CONTEXT.getAttribute(7)
                    + CONTEXT.getAttribute(8)
                    + CONTEXT.getAttribute(9)
                    + CONTEXT.getAttribute(10);
        }

    }

    @Benchmark
    public void rhino() {
        for (int i = 0; i < 1000; i++) {
            Rhino.eval(EXPRESSION, CONTEXT, Long.class);
        }
    }

    public static final class ExpressionContext {

        private final Map<Integer, Long> attrs;

        public ExpressionContext() {
            this.attrs = new HashMap<>(100);
            for (int i = 1; i <= 100; i++) {
                this.attrs.put(i, i * 10L);
            }
        }

        public long getAttribute(int attr) {
            return attrs.getOrDefault(attr, 0L);
        }

    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(RhinoBenchmark.class.getSimpleName())
//                .result("D:/my-workspace/JsBenchmark.json")
//                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(options).run();
    }


}
