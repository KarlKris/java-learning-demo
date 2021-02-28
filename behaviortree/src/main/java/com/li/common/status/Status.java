package com.li.common.status;

/**
 * 返回状态，每个行为在执行后都会传回一个状态
 **/
public enum Status {

    /**
     * 初始状态
     */
    INIT,

    /**
     * 完成状态
     **/
    SUCCESS,

    /**
     * 正在执行
     **/
    RUNNING,

    ;
}
