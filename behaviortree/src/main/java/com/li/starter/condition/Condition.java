package com.li.starter.condition;

import com.li.starter.behaviour.Behaviour;

/**
 * 条件，另一种行为树叶子节点，是行为树查看游戏世界信息的主要途径。
 **/
public interface Condition extends Behaviour {

    /**
     * 条件是否满足
     **/
    boolean valid();
}
