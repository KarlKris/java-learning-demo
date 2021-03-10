package interrupt;

import java.util.concurrent.*;

/**
 * @Auther: li-yuanwen
 * @Date: 2021/3/8 10:32
 * @Description: 线程中断测试
 **/
public class ThreadInterruptTest {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        for (int i = 0; i < 10000; i++) {
            executorService.execute(new Task());
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        executorService.shutdownNow();

    }

    static class Task implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    continue;
                }
            }
        }
    }
}
