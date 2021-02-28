package com.li.common;

import com.li.common.behaviour.Behaviour;

/**
 * @Auther: li-yuanwen
 * @Date: 2021/2/28 22:22
 * @Description: 行为树
 **/
public final class BehaviourTree {

    private Behaviour root;

    BehaviourTree(Behaviour root) {
        this.root = root;
    }

    public void start() {
        this.root.tick();
    }

}
