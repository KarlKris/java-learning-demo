package reflect;

import cglib.CglibProxy;
import javassist.CannotCompileException;
import javassist.JavassistProxyFactory;
import javassist.NotFoundException;
import model.IUser;
import model.User;
import proxy.ProxyInvocation;

import java.lang.reflect.*;

/**
 * @Auther: li-yuanwen
 * @Date: 2021/3/6 15:39
 * @Description: 反射测试类
 **/
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NotFoundException, CannotCompileException {
        Class<?> aClass = Class.forName("model.User");
        String a1 = aClass.getName();

        Class<User> bClass = User.class;
        String b1 = bClass.getName();

        User user = new User();
        Class<? extends User> cClass = user.getClass();
        String c1 = cClass.getName();

        // true
        System.out.println(a1.equals(b1));
        //true
        System.out.println(b1.equals(c1));
        System.out.println("---------------------getDeclaredConstructors-------------------------");

        for (Constructor c : aClass.getDeclaredConstructors()) {
            System.out.println(c);
        }

        System.out.println("-----------------------getConstructors-----------------------");

        for (Constructor c : aClass.getConstructors()) {
            System.out.println(c);
        }
        System.out.println("----------------------newInstance------------------------");


        Constructor<?> constructor = aClass.getConstructor(String.class);
        User instance = (User) constructor.newInstance("小新");
        // 小新
        System.out.println(instance.getName());
        System.out.println("----------------------getDeclaredFields------------------------");

        for (Field f : aClass.getDeclaredFields()) {
            System.out.println(f.getName());
        }

        System.out.println("---------------------getFields-------------------------");

        // 公有字段
        for (Field f : aClass.getFields()) {
            System.out.println(f.getName());
        }

        System.out.println("----------------------------------------------");

        Field field = aClass.getDeclaredField("name");
        field.setAccessible(true);
        field.set(instance, "小新Pro");

        System.out.println(instance.getName());

        System.out.println("----------------------getMethods------------------------");

        for (Method method : aClass.getMethods()) {
            System.out.println(method);
        }

        System.out.println("----------------------getDeclaredMethods------------------------");

        for (Method method : aClass.getDeclaredMethods()) {
            System.out.println(method);
        }

        System.out.println("----------------------------------------------");

        Method method = aClass.getDeclaredMethod("setAge", int.class);
        method.setAccessible(true);
        method.invoke(instance, 23);

        System.out.println(instance.getAge());

        System.out.println("----------------------------------------------");

        ProxyInvocation invocation = new ProxyInvocation(instance);

        IUser proxyInstance = (IUser) Proxy.newProxyInstance(aClass.getClassLoader(), new Class[]{IUser.class}, invocation);
        proxyInstance.setSex("MALE");

        System.out.println(instance.getSex());

        System.out.println("----------------------CglibProxy------------------------");

        CglibProxy cglibProxy = new CglibProxy();
        User u = ((User) cglibProxy.createProxyedObj(User.class));
        u.setSex("cglib setSex");
        System.out.println(u.getSex());

        System.out.println("------------------Javassist----------------------------");

        System.out.println(JavassistProxyFactory.javassistFactoryProxy(user).getName());
        User javassistDefineClass = JavassistProxyFactory.javassistDefineClass(User.class);
        System.out.println(javassistDefineClass.getSex());

        System.out.println("----------------------------------------------");


        String code = "微信平台yyds";
        System.out.println("微信平台YyDs".equalsIgnoreCase(code));



    }



}
