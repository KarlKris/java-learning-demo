package hotswap;

/**
 * @Description 为了多次载入执行类而加入的类加载器
 * @Author li-yuanwen
 * @Date 2021/1/21 20:53
 */
public class HotSwapClassLoader extends ClassLoader {

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    /**
     * 把defindClass方法开放出来，只有外部显式调用的时候才会使用到loadBytes()方法
     * 由虚拟机调用时,仍然按照原有的双亲委派规则使用loadClass()方法进行类加载
     **/
    public Class loadBytes(byte[] classBytes) {
        return defineClass(null, classBytes, 0, classBytes.length);
    }
}
