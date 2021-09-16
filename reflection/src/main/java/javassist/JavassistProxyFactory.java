package javassist;

import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

/**
 * @Auther: li-yuanwen
 * @Date: 2021/3/6 22:52
 * @Description: javassist 动态代理
 **/
public class JavassistProxyFactory {

    /**
     * 工厂代理
     */
    public static <T> T javassistFactoryProxy(T t) throws IllegalAccessException, InstantiationException {
        ProxyFactory proxyFactory = new ProxyFactory();

        proxyFactory.setSuperclass(t.getClass());

        Class<?> factoryClass = proxyFactory.createClass();

        ProxyObject proxyObject = (ProxyObject)factoryClass.newInstance();

        proxyObject.setHandler(new JavassistProxyHandler(t));

        return (T) proxyObject;

    }

    /**
     * 动态代理
     */
    public static <T> T javassistDefineClass(Class<T> aclass) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        String name = aclass.getName();

        ClassPool classPool = new ClassPool(true);
        // 创建一个代理类
        CtClass ctClass = classPool.makeClass(name + "&JavassistProxy");
        // 继承目标类
        ctClass.setSuperclass(classPool.get(name));

        // 添加默认构造函数
        ctClass.addConstructor(CtNewConstructor.defaultConstructor(ctClass));

        // 添加属性
        ctClass.addField(CtField.make("public " + name + " real = new " + name + "();", ctClass));

        // 添加方法
//        ctClass.addMethod(CtNewMethod.make("public String getSex() {"
//        + " return \"JavassistDefineClass:\" + real.getSex(); }", ctClass));

        Class<?> aClass = ctClass.toClass();

        return (T) aClass.newInstance();

    }
}
