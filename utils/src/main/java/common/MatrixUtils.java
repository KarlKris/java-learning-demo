package common;

import cn.hutool.core.util.ArrayUtil;

/**
 * @Description 矩阵
 * @Author li-yuanwen
 * @Date 2021/3/4 11:03
 */
public class MatrixUtils {


    public static int[][] maxtrixMultiple(int[][] a, int[][] b) {
        if (ArrayUtil.isEmpty(a) || ArrayUtil.isEmpty(b)) {
            throw new UnsupportedOperationException("array a or b is empty");
        }
        return doMaxtrixMultiple(a, b);
    }

    private static int[][] doMaxtrixMultiple(int[][] a, int[][] b) {
        // a矩阵的行
        int aRow = a.length;
        // a矩阵的列
        int aCol = a[0].length;
        for (int i = 1; i < aRow; i++) {
            if (aCol != a[i].length) {
                throw new UnsupportedOperationException("array a col is difference");
            }
        }
        int bRow = b.length;
        int bCol = b[0].length;
        for (int i = 1; i < bRow; i++) {
            if (bCol != b[i].length) {
                throw new UnsupportedOperationException("array b col is difference");
            }
        }

        // a矩阵的列=b矩阵的行才能相乘 = R(b矩阵的行，a矩阵的列)
        if (aCol != bRow) {
            throw new UnsupportedOperationException("array a and b cant mul");
        }
        //  R(a矩阵的行，b矩阵的列)
        int[][] res =  new int[aRow][bCol];
        for (int i = 0; i < aRow ; i++) {
            for (int j = 0; j < bCol; j++) {
                int rij = 0;
                for (int z = 0; z < aCol; z++) {
                    rij += (a[i][z] * b[z][j]);
                }
                res[i][j] = rij;
            }
        }

        return res;
    }



    public static void main(String[] args) {
        int[][] a = { {4, 6}, {5, 7}, {8, 9}};
        int[][] b = {{2, 1, 3}, {3, 1, 2}};
        ArrayUtils.twoDimensionalArrayPrintln(maxtrixMultiple(a, b));
    }

}
