package disruptor;

import com.lmax.disruptor.RingBuffer;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/4/22 14:44
 */
public class DisruptorProducer {

    private RingBuffer<Event<?>> ringBuffer;

    public DisruptorProducer(RingBuffer<Event<?>> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public <T> void produce(Event<T> event) {
        // 1.ringBuffer 事件队列 下一个槽
        long sequence = ringBuffer.next();
        try {
            //2.取出空的事件队列
            Event<T> ev = (Event<T>) ringBuffer.get(sequence);
            //3.获取事件队列传递的数据
            ev.copy(event);
        } finally {
            //4.发布事件
            ringBuffer.publish(sequence);
            System.out.println("-------事件[" + event.getBody() + "]发布成功-----");
        }
    }
}
