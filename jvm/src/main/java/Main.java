import equal.EqualModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description 杂测试类
 * @Author li-yuanwen
 * @Date 2021/3/2 17:53
 */
public class Main {

    /** 测试方法传递参数是对象时，是按值传递还是按引用传递 **/
    public static final void testTransfer(Person a ,Person b) {
        Person temp = a;
        a = b;
        b = temp;
    }

    public static void main(String[] args) {
        Person a = new Person("Tom", 17);
        Person b = new Person("Jack", 18);
        testTransfer(a, b);

        System.out.println(a);
        System.out.println(b);

        EqualModel tom = new EqualModel(17);
        EqualModel model = new EqualModel(18);
        System.out.println(tom == model);
        System.out.println(tom.equals(model));

        Integer i = null;
        Integer j = null;
        System.out.println(i==j);

        Integer z = 5;
        Integer x = 5;
        int c = 5;
        System.out.println(z==x);
        System.out.println(z==c);

        System.out.println(get());

        Collection<?>[] collections =
                {new HashSet<String>(), new ArrayList<String>(), new HashMap<String, String>().values()};
        Super subToSuper = new Sub();
        for(Collection<?> collection: collections) {
            System.out.println(subToSuper.getType(collection));
        }
    }

    public static Person get() {
        Person a = new Person("test", 1);;
        try{
            a.setAge(2);
            return a;
        }finally {
            a.setAge(3);
        }
    }


    static class Person {
        int age;
        private String name;
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


    // 静态方法是不会被重写的，调用时属于静态分配决定调用方法
    abstract static class Super {
        public static String getType(Collection<?> collection) {
            return "Super:collection";
        }
        public static String getType(List<?> list) {
            return "Super:list";
        }
        public String getType(ArrayList<?> list) {
            return "Super:arrayList";
        }
        public static String getType(Set<?> set) {
            return "Super:set";
        }
        public String getType(HashSet<?> set) {
            return "Super:hashSet";
        }
    }
    static class Sub extends Super {
        public static String getType(Collection<?> collection) {
            return "Sub"; }
    }
}
