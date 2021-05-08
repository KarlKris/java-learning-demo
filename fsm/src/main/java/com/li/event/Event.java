package com.li.event;

import lombok.Getter;

/**
 * @author li-yuanwen
 *
 * 可能导致状态改变的事件
 */
@Getter
public class Event<T> {

    /** 抛出事件的所有者id **/
    private int ownerId;

    /** 事件类型 **/
    private EventConstant eventCode;

    /** 时间内容 **/
    private T body;

    /** 时间轴 **/
    private int time;

    public Event(EventConstant eventCode, T body, int ownerId, int time) {
        this.eventCode = eventCode;
        this.body = body;
        this.ownerId = ownerId;
        this.time = time;
    }

}
