package javassist;

import javassist.util.proxy.MethodHandler;

import java.lang.reflect.Method;

/**
 * @Auther: li-yuanwen
 * @Date: 2021/3/6 22:54
 * @Description:
 **/
public class JavassistProxyHandler implements MethodHandler {

    private Object target;

    public JavassistProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {

        System.out.println("Javassist Proxy :" + method.getName());


        return method.invoke(target, objects);
    }
}
