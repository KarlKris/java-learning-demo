package com.algorithm.foundation;

import java.util.Stack;

/**
 * 计算策略接口
 * @author: li-yuanwen
 * @date: 2021/5/9 13:31
 **/
public interface CalculateStrategy {


    /**
     *
     * @param vals 操作数栈
     * @return 计算结果
     */
    int calculate(Stack<Integer> vals);

}
