package classload;

/**
 * @Description 类加载过程测试
 * @Author li-yuanwen
 * @Date 2021/1/13 20:17
 */
public class ClassLoadTest {

    public static class SSClass
    {
        static
        {
            System.out.println("SSClass");
        }
    }
    public static class SuperClass extends SSClass
    {
        static
        {
            System.out.println("SuperClass init!");
        }
        public static int value = 123;
        public SuperClass()
        {
            System.out.println("init SuperClass");
        }
    }
    public static class SubClass extends SuperClass
    {
        static
        {
            System.out.println("SubClass init");
        }
        static int a;
        public SubClass()
        {
            System.out.println("init SubClass");
        }
    }

    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }

}
