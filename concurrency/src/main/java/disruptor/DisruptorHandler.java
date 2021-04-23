package disruptor;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/4/22 14:45
 */
public class DisruptorHandler implements WorkHandler<Event<?>> {

    private AtomicInteger count = new AtomicInteger(0);
    private AtomicInteger time = new AtomicInteger(0);

    public void onEvent(Event<?> event) throws Exception {
        count.incrementAndGet();
        time.addAndGet(event.getHandleTime());
        System.out.println("-------事件[" + event.getBody() + "]开始处理-----");
        // 休眠
        Thread.sleep(2000);
    }
}
