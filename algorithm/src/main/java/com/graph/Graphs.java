package com.graph;

/**
 * @author li-yuanwen
 * @date 2021/7/4 15:46
 * 图的工具类
 * 相关概念
 * 相邻顶点：两个顶点通过一条边相连
 * 顶点的度数：依附于它的边的总数
 * 自环：一条连接一个顶点和其自身的边
 * 平行边：连接同一对顶点的两条边
 **/
public class Graphs {

    /**
     * 计算图中顶点v的度数
     * @param graph 图
     * @param v 顶点
     * @param <T> 顶点抽象类型
     * @return 图中顶点v的度数
     */
    public static <T> int degree(Graph<T> graph, T v) {
        int count = 0;
        for (T t : graph.adj(v)) {
            count++;
        }
        return count;
    }

    /**
     * 计算图中所有顶点的最大度数
     * @param graph 图
     * @param <T> 顶点抽象类型
     * @return 图中所有顶点的最大度数
     */
    public static <T> int maxDegree(Graph<T> graph) {
        int max = 0;
        for (T t : graph.allV()) {
            max = Math.max(max, degree(graph, t));
        }
        return max;
    }

    /**
     * 计算所有顶点的平均度数
     * @param graph 图
     * @param <T> 顶点抽象类型
     * @return 所有顶点的平均度数
     */
    public static <T> double avgDegree(Graph<T> graph) {
        return 2.0 * graph.e() / graph.v();
    }

    /**
     * 计算图中自环的个数
     * @param graph 图
     * @param <T> 顶点抽象类型
     * @return 图中自环的个数
     */
    public static <T> int numberOfSelfLoops(Graph<T> graph) {
        int count = 0;
        for (T t : graph.allV()) {
            for (T v : graph.adj(t)) {
                if (t != v) {
                    continue;
                }
                count++;
            }
        }
        return count;
    }
}
