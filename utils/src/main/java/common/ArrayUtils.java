package common;

/*
 * 打印数组元素
 */
public class ArrayUtils {

    public static void Array_Println(int num[]) {
        int size = num.length;
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(num[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    public static void twoDimensionalArrayPrintln(int[][] param) {
        int rLength = param.length;
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < rLength; i++) {
            int[] a = param[i];
            int aLength = a.length;

            sb.append("[");
            for (int j = 0; j < aLength; j++) {
                sb.append(param[i][j]);
                if (j != aLength - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");

            if (i != rLength - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        System.out.println(sb.toString());
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
