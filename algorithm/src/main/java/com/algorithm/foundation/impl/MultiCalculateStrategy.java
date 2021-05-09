package com.algorithm.foundation.impl;

import com.algorithm.foundation.CalculateStrategy;

import java.util.Stack;

/**
 * 乘法策略
 * @author: li-yuanwen
 * @date: 2021/5/9 13:37
 **/
public class MultiCalculateStrategy implements CalculateStrategy {

    @Override
    public int calculate(Stack<Integer> vals) {
        return vals.pop() * vals.pop();
    }
}
