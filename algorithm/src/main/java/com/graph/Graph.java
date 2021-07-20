package com.graph;

/**
 * @author li-yuanwen
 * @date 2021/7/4 15:41
 * 图接口
 **/
public interface Graph<T> {

    /**
     * 添加顶点
     * @param t 顶点数据
     * @return 是否成功添加
     */
    boolean add(T t);

    /**
     * 顶点数
     * @return 顶点数
     */
    int v();

    /**
     * 边数
     * @return 边数
     */
    int e();

    /**
     * 查询所有顶点
     * @return 所有顶点
     */
    Iterable<T> allV();


    /**
     * 连接顶点v和顶点w,形成边
     * @param v 顶点v
     * @param w 顶点w
     */
    void addEdge(T v, T w);


    /**
     * 查询与顶点v相邻的所有顶点
     * @param v 顶点v
     * @return 与顶点v相邻的所有顶点
     */
    Iterable<T> adj(T v);

}
