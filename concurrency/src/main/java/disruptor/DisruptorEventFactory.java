package disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/4/22 14:50
 */
public class DisruptorEventFactory implements EventFactory<Event<?>> {

    public Event<?> newInstance() {
        return new Event<Object>();
    }
}
