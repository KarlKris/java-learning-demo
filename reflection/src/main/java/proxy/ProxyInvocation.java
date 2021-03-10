package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther: li-yuanwen
 * @Date: 2021/3/6 16:30
 * @Description:
 **/
public class ProxyInvocation implements InvocationHandler {

    private Object obj;

    public ProxyInvocation(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("ProxyInvocation invoke before");
        Object invocationReturn = method.invoke(obj, args);
        System.out.println("ProxyInvocation invoke after");
        return invocationReturn;
    }
}
