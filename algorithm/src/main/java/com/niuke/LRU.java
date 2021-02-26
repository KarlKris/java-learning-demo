package com.niuke;

import com.model.LRUMap;

import java.util.*;

/**
 * @Description 设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
 * set(key, value)：将记录(key, value)插入该结构
 * get(key)：返回key对应的value值
 * [要求]
 * set和get方法的时间复杂度为O(1)
 * 某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
 * 当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
 * 若opt=1，接下来两个整数x, y，表示set(x, y)
 * 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
 * 对于每个操作2，输出一个答案
 * @Author li-yuanwen
 * @Date 2021/2/21 19:37
 */
public class LRU {

    /**
     * lru design
     *
     * @param operators int整型二维数组 the ops
     * @param k         int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU(int[][] operators, int k) {
        // write code here

        LRUMap<Integer, Integer> map = new LRUMap<>(k);

        int length = operators.length;
        List<Integer> sout = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            int[] operator = operators[i];
            switch (operator[0]) {
                case 1 :
                    map.set(operator[1], operator[2]);
                    break;
                case 2 :
                    Integer v = map.get(operator[1]);
                    sout.add(v == null ? -1 : v);
                    break;
                default:
                    throw new RuntimeException();
            }
        }

        int size = sout.size();
        int[] soutArray = new int[size];
        for (int j = 0; j < size; j++) {
            soutArray[j] = sout.get(j);
        }

        return soutArray;
    }

    public static void main(String[] args) {
        int[][] ops = {{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};

        LRU lru = new LRU();
        int[] res = lru.LRU(ops, 3);

        int resLength = res.length;
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < resLength; i++) {
            builder.append(res[i]);
            if (i < resLength - 1) {
                builder.append(",");
            }
        }
        builder.append("]");
        System.out.println(builder.toString());
    }
}
