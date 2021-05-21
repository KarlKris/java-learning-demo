package com.li.module.shouweidigong;

import com.li.event.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author li-yuanwen
 * 黑板  存储全局共享信息，控制时间进度
 */
public class BlackBoard {

    /** 结束标识 **/
    private boolean finish;

    /** 时间轴 **/
    private int time = 1;

    /** 事件队列 **/
    private Map<Integer, List<Event>> events;

    public void initEvent(List<Event> initEvents) {
        this.events = new HashMap<>(2);
        this.events.put(time, initEvents);
    }

    public void go() {
        time++;
    }

    public void finish() {
        this.finish = true;
    }

    public boolean isFinish() {
        return finish;
    }

    public List<Event> getEvents() {
        List<Event> temp;
        return (temp = events.remove(time)) == null ? Collections.emptyList() : temp;
    }

    public void post(Event event) {
        List<Event> events = this.events.computeIfAbsent(event.getTime(), k -> new ArrayList<>());
        events.add(event);
    }

}
