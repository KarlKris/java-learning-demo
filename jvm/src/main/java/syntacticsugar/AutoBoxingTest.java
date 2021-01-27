package syntacticsugar;

/**
 * @Description 自动装箱的陷阱
 * @Author li-yuanwen
 * @Date 2021/1/25 20:45
 */
public class AutoBoxingTest {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 127;
        Integer f = 127;
        Integer g = 128;
        Integer h = 128;

        Long i = 3L;

        System.out.println(c == d); // true
        System.out.println(e == f);// true
        System.out.println(g == h); // false -128 <= int <= 127 自动拆箱范围

        System.out.println(c == (a + b)); // true
        System.out.println(c.equals((a + b))); //true

        System.out.println(i == (a + b)); // true
        System.out.println(i.equals((a + b)));//false Integer 并不会向上转型为Long  equal不处理这个问题
    }
}
