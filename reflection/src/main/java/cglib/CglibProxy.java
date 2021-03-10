package cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: li-yuanwen
 * @Date: 2021/3/6 22:40
 * @Description: cglib 动态代理
 **/
public class CglibProxy implements MethodInterceptor {

    public Object createProxyedObj(Class<?> aclass) {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(aclass);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {


        System.out.println("CGLIB 动态代理 : " + method.getName());
        // 这样会造成死循环
//        return method.invoke(o, objects);
        return methodProxy.invokeSuper(o, objects);
    }
}
