package com.li.rhino;

import com.li.util.NumberUtils;
import org.mozilla.javascript.*;
import org.slf4j.*;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 公式执行器
 * @author Ramon
 */
public class Rhino {

	static Logger logger = LoggerFactory.getLogger(Rhino.class);

	/** JS参数对象包装工厂 */
	private static final NativeWraperFactory DEFAULT_WARP_FACTORY = new NativeWraperFactory();
	/** 全局脚本顶层对象 */
	private static final ImporterTopLevel TOP_LEVEL_SCOPE;

	static {
		ContextFactory global = ContextFactory.getGlobal();
		Context context = global.enterContext();
		context.setWrapFactory(DEFAULT_WARP_FACTORY);
		TOP_LEVEL_SCOPE = new ImporterTopLevel(context);
		try {
			/* abs */
			Method abs = Math.class.getDeclaredMethod("abs", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("abs", TOP_LEVEL_SCOPE, new FunctionObject("abs", abs, TOP_LEVEL_SCOPE));

			/* acos */
			Method acos = Math.class.getDeclaredMethod("acos", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("acos", TOP_LEVEL_SCOPE, new FunctionObject("acos", acos, TOP_LEVEL_SCOPE));

			/* asin */
			Method asin = Math.class.getDeclaredMethod("asin", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("asin", TOP_LEVEL_SCOPE, new FunctionObject("asin", asin, TOP_LEVEL_SCOPE));

			/* atan */
			Method atan = Math.class.getDeclaredMethod("atan", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("atan", TOP_LEVEL_SCOPE, new FunctionObject("atan", atan, TOP_LEVEL_SCOPE));

			/* atan2 */
			Method atan2 = Math.class.getDeclaredMethod("atan2", new Class<?>[] { double.class, double.class });
			TOP_LEVEL_SCOPE.putConst("atan2", TOP_LEVEL_SCOPE, new FunctionObject("atan2", atan2, TOP_LEVEL_SCOPE));

			/* cbrt */
			Method cbrt = Math.class.getDeclaredMethod("cbrt", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("cbrt", TOP_LEVEL_SCOPE, new FunctionObject("cbrt", cbrt, TOP_LEVEL_SCOPE));

			/* ceil */
			Method ceil = Math.class.getDeclaredMethod("ceil", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("ceil", TOP_LEVEL_SCOPE, new FunctionObject("ceil", ceil, TOP_LEVEL_SCOPE));

			/* copySign */
			Method copySign = Math.class.getDeclaredMethod("copySign", new Class<?>[] { double.class, double.class });
			TOP_LEVEL_SCOPE.putConst("copySign", TOP_LEVEL_SCOPE, new FunctionObject("copySign", copySign,
					TOP_LEVEL_SCOPE));

			/* cos */
			Method cos = Math.class.getDeclaredMethod("cos", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("cos", TOP_LEVEL_SCOPE, new FunctionObject("cos", cos, TOP_LEVEL_SCOPE));

			/* cosh */
			Method cosh = Math.class.getDeclaredMethod("cosh", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("cosh", TOP_LEVEL_SCOPE, new FunctionObject("cosh", cosh, TOP_LEVEL_SCOPE));

			/* exp */
			Method exp = Math.class.getDeclaredMethod("exp", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("exp", TOP_LEVEL_SCOPE, new FunctionObject("exp", exp, TOP_LEVEL_SCOPE));

			/* expm1 */
			Method expm1 = Math.class.getDeclaredMethod("expm1", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("expm1", TOP_LEVEL_SCOPE, new FunctionObject("expm1", expm1, TOP_LEVEL_SCOPE));

			/* floor */
			Method floor = Math.class.getDeclaredMethod("floor", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("floor", TOP_LEVEL_SCOPE, new FunctionObject("floor", floor, TOP_LEVEL_SCOPE));

			/* hypot */
			Method hypot = Math.class.getDeclaredMethod("hypot", new Class<?>[] { double.class, double.class });
			TOP_LEVEL_SCOPE.putConst("hypot", TOP_LEVEL_SCOPE, new FunctionObject("hypot", hypot, TOP_LEVEL_SCOPE));

			/* log */
			Method log = Math.class.getDeclaredMethod("log", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("log", TOP_LEVEL_SCOPE, new FunctionObject("log", log, TOP_LEVEL_SCOPE));

			/* log10 */
			Method log10 = Math.class.getDeclaredMethod("log10", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("log10", TOP_LEVEL_SCOPE, new FunctionObject("log10", log10, TOP_LEVEL_SCOPE));

			/* log1p */
			Method log1p = Math.class.getDeclaredMethod("log1p", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("log1p", TOP_LEVEL_SCOPE, new FunctionObject("log1p", log1p, TOP_LEVEL_SCOPE));

			/* max */
			Method max = Math.class.getDeclaredMethod("max", new Class<?>[] { double.class, double.class });
			TOP_LEVEL_SCOPE.putConst("max", TOP_LEVEL_SCOPE, new FunctionObject("max", max, TOP_LEVEL_SCOPE));

			/* min */
			Method min = Math.class.getDeclaredMethod("min", new Class<?>[] { double.class, double.class });
			TOP_LEVEL_SCOPE.putConst("min", TOP_LEVEL_SCOPE, new FunctionObject("min", min, TOP_LEVEL_SCOPE));

			/* pow */
			Method pow = Math.class.getDeclaredMethod("pow", new Class<?>[] { double.class, double.class });
			TOP_LEVEL_SCOPE.putConst("pow", TOP_LEVEL_SCOPE, new FunctionObject("pow", pow, TOP_LEVEL_SCOPE));

			/* random */
			Method random = Math.class.getDeclaredMethod("random", new Class<?>[] {});
			TOP_LEVEL_SCOPE.putConst("random", TOP_LEVEL_SCOPE, new FunctionObject("random", random, TOP_LEVEL_SCOPE));

			/* rint */
			Method rint = Math.class.getDeclaredMethod("rint", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("rint", TOP_LEVEL_SCOPE, new FunctionObject("rint", rint, TOP_LEVEL_SCOPE));

			/* round */
			Method round = Math.class.getDeclaredMethod("round", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("round", TOP_LEVEL_SCOPE, new FunctionObject("round", round, TOP_LEVEL_SCOPE));

			/* sin */
			Method sin = Math.class.getDeclaredMethod("sin", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("sin", TOP_LEVEL_SCOPE, new FunctionObject("sin", sin, TOP_LEVEL_SCOPE));

			/* sinh */
			Method sinh = Math.class.getDeclaredMethod("sinh", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("sinh", TOP_LEVEL_SCOPE, new FunctionObject("sinh", sinh, TOP_LEVEL_SCOPE));

			/* sqrt */
			Method sqrt = Math.class.getDeclaredMethod("sqrt", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("sqrt", TOP_LEVEL_SCOPE, new FunctionObject("sqrt", sqrt, TOP_LEVEL_SCOPE));

			/* tan */
			Method tan = Math.class.getDeclaredMethod("tan", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("tan", TOP_LEVEL_SCOPE, new FunctionObject("tan", tan, TOP_LEVEL_SCOPE));

			/* tanh */
			Method tanh = Math.class.getDeclaredMethod("tanh", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("tanh", TOP_LEVEL_SCOPE, new FunctionObject("tanh", tanh, TOP_LEVEL_SCOPE));

			/* toDegrees */
			Method toDegrees = Math.class.getDeclaredMethod("toDegrees", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("toDegrees", TOP_LEVEL_SCOPE, new FunctionObject("toDegrees", toDegrees,
					TOP_LEVEL_SCOPE));

			/* toRadians */
			Method toRadians = Math.class.getDeclaredMethod("toRadians", new Class<?>[] { double.class });
			TOP_LEVEL_SCOPE.putConst("toRadians", TOP_LEVEL_SCOPE, new FunctionObject("toRadians", toRadians,
					TOP_LEVEL_SCOPE));

			/* nextInt*/
//			Method nextInt = RandomUtils.class.getDeclaredMethod("nextInt", new Class<?>[] { int.class });
//			TOP_LEVEL_SCOPE.putConst("nextInt", TOP_LEVEL_SCOPE, new FunctionObject("nextInt", nextInt,
//					TOP_LEVEL_SCOPE));
//			STATIC_METHOD_IMPORT.add("nextInt");

		} catch (Exception e) {
			throw new RuntimeException("导入Math函数异常", e);
		}
		TOP_LEVEL_SCOPE.sealObject();
	}

	/**
	 * 判断是否是已解析的公式方法
	 * @param name
	 * @return
	 */
	public static boolean isConst(String name) {
		return TOP_LEVEL_SCOPE.isConst(name);
	}
	
	/**
	 * 转换double类型上下文对象
	 * @return
	 */
	public static Map<String, Double> toDoubleCtx() {
		return new HashMap<String, Double>() {
			private static final long serialVersionUID = -4573159388492417283L;
			@Override
			public Double get(Object key) {
				return getOrDefault(key, 0.0);
			}
			@Override
			public boolean containsKey(Object key) {
				if (Rhino.isConst(key.toString())) {
					return false;
				}
				return true;
			}
		};
	}
	
	private static final ConcurrentHashMap<String, CompiledExpression> COMPILED_CACHE = new ConcurrentHashMap<>();

	/**
	 * 编译公式
	 * @param expression 公式表达式
	 * @return
	 */
	public static CompiledExpression compile(String expression) {
		CompiledExpression compiled = COMPILED_CACHE.get(expression);
		if (compiled != null) {
			return compiled;
		}
		Context context = Context.enter();
		try {
			Script scriptObject = context.compileString(expression, "<expr>", -1, null);
			compiled = new CompiledExpression(expression, scriptObject);
			COMPILED_CACHE.put(expression, compiled);
			return compiled;
		} finally {
			Context.exit();
		}
	}

	/**
	 * 执行已编译的公式
	 * @param exp 已编译的公式
	 * @param ctx 公式上下文
	 * @param type 返回值类型
	 * @return
	 */
	public static <T> T execute(CompiledExpression exp, Object ctx, Class<T> type) {
		Context context = Context.enter();
		context.setWrapFactory(DEFAULT_WARP_FACTORY);
		try {
			// 参数转换为脚本对象
			Scriptable args = DEFAULT_WARP_FACTORY.wrapNewObject(context, TOP_LEVEL_SCOPE, ctx);
			Object result = exp.getScriptObject().exec(context, args);
			// UNWRAP JS Object
			if (result instanceof NativeJavaObject) {
				result = ((NativeJavaObject) result).unwrap();
			}
			if (!type.isInstance(result)) {
				if (Number.class.isAssignableFrom(type)) {
					// 数值类型转换
					if (result instanceof Number) {
						return NumberUtils.valueOf(type, (Number) result);
					}
				}
			}
			@SuppressWarnings("unchecked")
			T t = (T) result;
			return t;
		} finally {
			Context.exit();
		}
	}

	/**
	 * 执行公式
	 * @param expression 已编译的公式
	 * @param ctx 公式上下文
	 * @param type 返回值类型
	 * @return
	 */
	public static <T> T eval(String expression, Object ctx, Class<T> type) {
		CompiledExpression compiled = compile(expression);
		return execute(compiled, ctx, type);
	}
}
