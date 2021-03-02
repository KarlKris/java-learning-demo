package common;

/*
 * 打印数组元素
 */
public class ArrayUtils {

    public static void Array_Println(int num[]) {
        int size = num.length;
        for (int i = 0; i < size; i++) {
            System.out.print(num[i] + " ");
        }
        System.out.println();
    }

    //随机生成size为100的 范围1-100的数组
    public static int[] randomArray(int size) {
        int a[] = new int[size];
        int i = 0;
        while (i < size) {
            a[i] = RandomUtils.betweenInt(-10, 90, true);
            i++;
        }
        return a;
    }

}
