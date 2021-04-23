package disruptor;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/4/22 14:45
 */
@NoArgsConstructor
@Getter
public class Event<T> {

    /** 事件名称 **/
    private String name;

    /** 事件内容 **/
    private T body;

    /** 事件产生时间 **/
    private long time;

    public void copy(Event<T> event) {
        this.name = event.name;
        this.body = event.body;
        this.time = event.time;
    }

    public Event(String name, T body) {
        this.name = name;
        this.body = body;
        this.time = System.currentTimeMillis();
    }

    public int getHandleTime() {
        return (int) (System.currentTimeMillis() - this.time);
    }

}
