package test;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.jsoniter.JsonIterator;
import com.jsoniter.JsonIteratorPool;
import com.jsoniter.output.JsonStream;
import com.jsoniter.output.JsonStreamPool;
import com.jsoniter.spi.JsoniterSpi;
import lombok.Getter;
import method.Bean;
import method.JavaBean;

import java.util.*;
import java.util.concurrent.*;

/**
 * @Description FastJson 测试
 * @Author li-yuanwen
 * @Date 2021/3/26 14:49
 */
public class JsonTest {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        JavaBean bean = new JavaBean();
        bean.setName("bean");
        bean.setList(list);

        String str = JsonStream.serialize(bean);
        System.out.println(str);

        String s = JSON.toJSONString(bean);
        System.out.println(s.getBytes().length == str.getBytes().length);

        double d = 1.0;
        System.out.println(doubleNotEqual(d));

        System.out.println(tryCatch().i);

        int count = 100000;
        System.out.println(calTime(count));
        System.out.println(calTimeWithTryCatch(count));

        String ip = "255.255.255.255";
        System.out.println("字符数组长度:" + ip.getBytes().length);


        System.out.println(ExecutorService.class.getName());
        System.out.println(ExecutorService.class.getTypeName());
        System.out.println(ExecutorService.class.getSimpleName());
        System.out.println(ExecutorService.class.getCanonicalName());

        ExecutorService executorService = new ThreadPoolExecutor(4, 4
                , 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        Set<Long> ids = new HashSet<>();
        CountDownLatch downLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            executorService.submit(()-> {
                long id = RandomUtil.randomLong();
                ids.add(id);
                ids.contains(RandomUtil.randomLong());
                if (RandomUtil.randomBoolean()) {
                    ids.remove(id);
                }
                downLatch.countDown();
            });
        }
        downLatch.await();
        executorService.shutdownNow();

    }

    public static int calTime(int count) {
        long s = System.nanoTime();
        for (int i = 0; i < count; i++) {
            new Object();
        }
        return (int) (System.nanoTime() - s);
    }

    public static int calTimeWithTryCatch(int count) {
        long s = System.nanoTime();
        try {
            for (int i = 0; i < count; i++) {
                new Object();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return (int) (System.nanoTime() - s);
    }

    public static TryBean tryCatch() {
        TryBean bean = new TryBean();
        try {
            boolean b = RandomUtil.randomBoolean();
            if (b) {
                throw new Exception("");
            }
            bean.inc();
            return bean;
        }catch (Exception e) {
            bean.inc();
            return bean;
        }finally {
            bean.inc();
        }
    }

    public static class TryBean {
        @Getter
        int i;
        void inc() {
            i++;
        }
    }

    public static boolean doubleNotEqual(double d) {
        return d != 1;
    }
}
