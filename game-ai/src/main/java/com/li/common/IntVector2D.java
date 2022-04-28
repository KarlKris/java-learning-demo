package com.li.common;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/**
 * int类型的Vector2D
 * @author li-yuanwen
 * @date 2022/4/27
 */
public class IntVector2D {

    public static final IntVector2D ZERO = new IntVector2D(0, 0);

    /** x坐标 **/
    private final int x;
    /** y坐标 **/
    private final int y;


    public IntVector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /** 获取斜边长度 **/
    public int getNorm() {
        return (int) getNorm0();
    }

    public int getNormSq() {
        return x * x + y * y;
    }

    /** 向量加 **/
    public IntVector2D add(IntVector2D intVector2D) {
        return new IntVector2D(x + intVector2D.getX(), y + intVector2D.getY());
    }

    /** 向量减 **/
    public IntVector2D subtract(IntVector2D intVector2D) {
        return new IntVector2D(x - intVector2D.getX(), y - intVector2D.getY());
    }

    /** 向量标准化 **/
    public IntVector2D normalize() {
        int s = getNorm();
        if (s == 0) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR);
        }
        return scalarMultiply(1 / s);
    }

    /** 向量距离 **/
    public int distance(IntVector2D v) {
        return (int) FastMath.sqrt(distanceSq(v));
    }

    /** 向量距离 **/
    public int distanceSq(IntVector2D v) {
        final int dx = v.x - x;
        final int dy = v.y - y;
        return dx * dx + dy * dy;
    }

    /** 向量数乘 **/
    public IntVector2D scalarMultiply(int a) {
        return new IntVector2D(x * a, y * a);
    }

    /** 向量数乘 **/
    public IntVector2D scalarMultiply(double a) {
        return new IntVector2D((int) (x * a), (int) (y * a));
    }

    /** 向量点乘 **/
    public int dotProduct(IntVector2D v) {
        return x * v.x + y * v.y;
    }

    /** 精细的向量长度 **/
    private double getNorm0() {
        return FastMath.sqrt(x * x + y * y);
    }

    /** 计算2个向量的角度 **/
    public static int angle(IntVector2D v1, IntVector2D v2) throws MathArithmeticException {
        double normProduct = v1.getNorm0() * v2.getNorm0();
        if (normProduct == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }

        int dot = v1.dotProduct(v2);
        double threshold = normProduct * 0.9999;
        if ((dot < -threshold) || (dot > threshold)) {
            // the vectors are almost aligned, compute using the sine
            final double n = FastMath.abs(MathArrays.linearCombination(v1.x, v2.y, -v1.y, v2.x));
            if (dot >= 0) {
                return (int) FastMath.asin(n / normProduct);
            }
            return (int) (FastMath.PI - FastMath.asin(n / normProduct));
        }

        // the vectors are sufficiently separated to use the cosine
        return (int) FastMath.acos(dot / normProduct);

    }

}
