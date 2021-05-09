package com.algorithm.foundation.impl;

import com.algorithm.foundation.CalculateStrategy;

import java.util.Stack;

/**
 * 减法策略
 * @author: li-yuanwen
 * @date: 2021/5/9 13:35
 **/
public class SubCalculateStrategy implements CalculateStrategy {

    @Override
    public int calculate(Stack<Integer> vals) {
        Integer right = vals.pop();
        return vals.pop() - right;
    }
}
