package com.algorithm.foundation.impl;

import com.algorithm.foundation.CalculateStrategy;

import java.util.Stack;

/**
 * 除法策略
 * @author: li-yuanwen
 * @date: 2021/5/9 13:38
 **/
public class DivCalculateStrategy implements CalculateStrategy {

    @Override
    public int calculate(Stack<Integer> vals) {
        Integer down = vals.pop();
        return vals.pop() / down;
    }
}
