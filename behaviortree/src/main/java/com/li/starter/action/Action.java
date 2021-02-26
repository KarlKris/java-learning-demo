package com.li.starter.action;

import com.li.starter.behaviour.Behaviour;

/**
 * 抽象动作的概念，尝试改变游戏状态的样子行为被称为动作。
 * 在行为树中，叶子节点负责从游戏世界访问信息并对游戏世界造成改变。
 **/
public interface Action extends Behaviour {

    /**
     * 初始化
     **/
    void init();

    /**
     * 关闭
     **/
    void close();
}
