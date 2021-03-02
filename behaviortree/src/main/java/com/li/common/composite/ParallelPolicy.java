package com.li.common.composite;

/** 并行器执行策略  **/
public enum ParallelPolicy {

    /** 某个子行为一旦成功或失败，立即返回 **/
    REQUICE_ONE,

    /** 所有子行为全成功或全失败才算成功或失败，否则正在运行 **/
    REQUICE_ALL,

    ;
}
