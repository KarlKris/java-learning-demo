package com.li.common;

import com.li.common.behaviour.Behaviour;

import java.util.Stack;

/**
 * @Auther: li-yuanwen
 * @Date: 2021/2/28 22:23
 * @Description: 行为树构建类
 **/
public class BehaviourTreeBuilder {

    /** 栈 **/
    private Stack<Behaviour> stack;
    /** 行为树根节点 **/
    private Behaviour root;

    public BehaviourTreeBuilder root(Behaviour root) {
        initOrReset();
        this.root = root;
        // 入栈
        stack.push(root);
        return this;
    }

    public BehaviourTreeBuilder addBehaviour(Behaviour behaviour) {
        if (this.root == null) {
            throw new RuntimeException("root behaviour is null");
        }

        // 加入子行为
        stack.peek().addChild(behaviour);
        // 入栈
        stack.push(behaviour);

        return this;
    }

    public BehaviourTreeBuilder back() {
        stack.pop();
        return this;
    }

    public BehaviourTree build() {
        return new BehaviourTree(root);
    }

    private void initOrReset() {
        this.root = null;
        this.stack = new Stack<>();
    }

}
