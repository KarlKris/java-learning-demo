package com.algorithm.foundation;

import com.algorithm.foundation.impl.AddCalculateStrategy;
import com.algorithm.foundation.impl.DivCalculateStrategy;
import com.algorithm.foundation.impl.MultiCalculateStrategy;
import com.algorithm.foundation.impl.SubCalculateStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 算术表达式求值
 * @author: li-yuanwen
 * @date: 2021/5/9 13:07
 **/
public class Evaluate {

    private static Map<String, CalculateStrategy> tools;

    static {
        tools = new HashMap<>(4);
        tools.put("+", new AddCalculateStrategy());
        tools.put("-", new SubCalculateStrategy());
        tools.put("*", new MultiCalculateStrategy());
        tools.put("/", new DivCalculateStrategy());
    }

    /**
     * 利用两个栈实现求值
     * @param arithmeticExpressions 算术表达式
     * @return
     */
    public static int cal(String arithmeticExpressions) {
        // 操作数栈
        Stack<Integer> vals = new Stack<>();
        // 运算符栈
        Stack<String> ops = new Stack<>();

        for (String str : arithmeticExpressions.split("")) {
            // 遇到运算符则压入运算符栈
            switch (str) {
                case "(" : {
                    // 左括号忽略
                    break;
                }
                case ")" : {
                    // 右括号则拿出一个运算符，并将根据运算符计算值，然后压入操作数栈
                    doCalculate(vals, ops);
                    break;
                }
                default:{
                    if (tools.containsKey(str)) {
                        ops.push(str);
                    }else {
                        vals.push(Integer.valueOf(str));
                    }
                }
            }
        }

        // 计算
        while (!ops.isEmpty()) {
            doCalculate(vals, ops);
        }
        return vals.pop();

    }

    private static void doCalculate(Stack<Integer> vals, Stack<String> ops) {
        vals.push(tools.get(ops.pop()).calculate(vals));
    }

    public static void main(String[] args) {
        String arithmeticExpressions = "1+(2+3)*(4*5)";
        System.out.println(Evaluate.cal(arithmeticExpressions));
    }

}
