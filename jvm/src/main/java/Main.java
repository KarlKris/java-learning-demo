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
    }


    static class Person {
        int age;
        private String name;
        Person(String name, int age) {
            this.name = name;
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
}
