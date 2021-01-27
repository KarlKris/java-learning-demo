package hotswap;

import java.lang.reflect.Method;

/**
 * @Description Java class 执行工具
 * @Author li-yuanwen
 * @Date 2021/1/22 14:29
 */
public class JavaclassExecuter {


    /**
     * 执行外部传过来的代表一个Java类的Byte数组
     * 将输入类的byte数组中代表java.lang.System的CONSTANT_Utf8_info常量修改为劫持后的HackSystem类
     * 执行方法为该类的main方法，输出结果为该类向System.out/err输出的信息。
     **/
    public static String execute(byte[] classBytes) {
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classBytes);
        byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "hotswap/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadBytes(modiBytes);
        try {
            Method method = clazz.getMethod("main", new Class[]{String[].class});
            method.invoke(null, new String[]{null});
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }

}
