package com.li.starter;

import com.li.common.BehaviourTreeBuilder;
import com.li.common.composite.SelectorBehaviour;

/**
 * @Description 行为树新手包入口
 * @Author li-yuanwen
 * @Date 2021/3/1 11:32
 */
public class StarterMain {

    public static void main(String[] args) {
        BehaviourTreeBuilder builder = new BehaviourTreeBuilder();
        builder.root(new SelectorBehaviour());

    }
}
