package common;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description 随机工具类
 * @Author li-yuanwen
 * @Date 2021/2/23 14:58
 */
public class RandomUtils {

    /** 随机值 **/
    public static int betweenInt(int min, int max, boolean include) {
        // 参数检查
        if (min > max) {
            throw new IllegalArgumentException("最小值[" + min + "]不能大于最大值[" + max + "]");
        } else if (!include && min == max) {
            throw new IllegalArgumentException("不包括边界值时最小值[" + min + "]不能等于最大值[" + max + "]");
        }
        // 修正边界值
        if (include) {
            max++;
        } else {
            min++;
        }
        return (int) (min + Math.random() * (max - min));
    }

}
