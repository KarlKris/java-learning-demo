package disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import core.NamedThreadFactory;

import java.util.concurrent.ThreadFactory;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/4/22 14:44
 */
public class DisruptorMain {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("事件模块");
        ThreadFactory threadFactory = new NamedThreadFactory(threadGroup, "事件处理");
        // 3.创建ringBuffer 大小
        int ringBufferSize = 2; // ringBufferSize大小一定要是2的N次方

        Disruptor<Event<?>> disruptor = new Disruptor<Event<?>>(new DisruptorEventFactory(), ringBufferSize, threadFactory
                , ProducerType.SINGLE, new  YieldingWaitStrategy());
        DisruptorHandler[] handlers = new DisruptorHandler[2];
        for (int i = 0; i < 2; i++) {
            handlers[i] = new DisruptorHandler();
        }

        // 5.连接消费端方法
        disruptor.handleEventsWithWorkerPool(handlers);
        //6.启动
        disruptor.start();
        // 7.创建RingBuffer容器
        RingBuffer<Event<?>> ringBuffer = disruptor.getRingBuffer();
        // 8.创建生产者
        DisruptorProducer producer = new DisruptorProducer(ringBuffer);

        for (int i = 0; i < 100; i++) {
            Event<Integer> event = new Event<Integer>("test", i);
            producer.produce(event);
        }

        Thread.sleep(10000);
        disruptor.shutdown();

    }
}
