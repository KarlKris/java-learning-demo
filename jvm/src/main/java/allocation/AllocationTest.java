package allocation;

/**
 * @Description 内存回收测试
 * @Author li-yuanwen
 * @Date 2020/12/1 20:15
 */
public class AllocationTest {

    public static final int _1MB = 1024 * 1024;

    /**
     * VM参数 -verbose:gc -Xms20M -Xmx20M  -Xmn10M -XX:+PrintGCDetails
     *  Java堆为20M 10M新生代 10M老年代
     *
     **/
    public static void main(String[] args) {
        byte[] b1 = new byte[2 * _1MB];
        byte[] b2 = new byte[2 * _1MB];
        byte[] b3 = new byte[2 * _1MB];
        byte[] b4 = new byte[4 * _1MB];  // 出现一次Mirror GC
    }
}
