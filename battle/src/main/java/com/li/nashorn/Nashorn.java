package com.li.nashorn;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.StopWatch;
import com.li.rhino.Rhino;

import javax.script.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author li-yuanwen
 * @date 2022/9/19
 */
public class Nashorn {


    private static final ScriptEngine ENGINE = new ScriptEngineManager().getEngineByName("js");

    private final static String NASHORN_EXPRESSION = "ctx.getAttribute(1)+ctx.getAttribute(2)+ctx.getAttribute(3)+ctx.getAttribute(4)+ctx.getAttribute(5)+ctx.getAttribute(6)+ctx.getAttribute(7)+ctx.getAttribute(8)+ctx.getAttribute(9)+ctx.getAttribute(10)";

    private final static Map<String, CompiledScript> COMPILED_SCRIPT = new ConcurrentHashMap<>();

    private final static ScriptContext SCRIPT_CONTEXT = ENGINE.getContext();
    private final static Bindings BINDINGS = ENGINE.createBindings();


    static {
        SCRIPT_CONTEXT.setBindings(BINDINGS, ScriptContext.GLOBAL_SCOPE);
    }

    public static <T> T eval(String expression, Object ctx, Class<T> type) throws ScriptException {
        BINDINGS.put("ctx", ctx);
        CompiledScript compiledScript = compile(expression);
        Object result = compiledScript.eval(SCRIPT_CONTEXT);
        return Convert.convert(type, result);
    }

    public static CompiledScript compile(String script) throws ScriptException {
        CompiledScript compiledScript = COMPILED_SCRIPT.get(script);
        if (compiledScript != null) {
            return compiledScript;
        }

        final Compilable compEngine = (Compilable) ENGINE;
        compiledScript = compEngine.compile(script);

        COMPILED_SCRIPT.put(script, compiledScript);
        return compiledScript;
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

    private final static String EXPRESSION = "getAttribute(1)+getAttribute(2)+getAttribute(3)+getAttribute(4)+getAttribute(5)+getAttribute(6)+getAttribute(7)+getAttribute(8)+getAttribute(9)+getAttribute(10)";


    public static void main(String[] args) throws ScriptException {

        ExpressionContext context = new ExpressionContext();
        Long value = eval(NASHORN_EXPRESSION, context, Long.class);
        StopWatch watch = new StopWatch();
        watch.start("nashorn.eval");
        for (int i = 0; i < 400000; i++) {
            value = eval(NASHORN_EXPRESSION, context, Long.class);

        }
        watch.stop();

        watch.start("rhino.eval");
        for (int i = 0; i < 400000; i++) {
            value = Rhino.eval(EXPRESSION, context, Long.class);
        }
        watch.stop();
        System.out.println(watch.prettyPrint());
    }

}
