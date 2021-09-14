package com.rank.list;

import com.rank.RankItem;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author li-yuanwen
 * 通过数组实现的排行榜
 */
public class RankListByList {

    static final int ONCE = 100;

    /** 块排行榜 **/
    private final OnceList[] rankList;
    /** 排行榜阈值2下标 **/
    private final TreeMap<Long, Integer> lowest2Index;

    public RankListByList(int total) {
        int length = total / ONCE + 1;
        this.rankList = new OnceList[length];
        this.lowest2Index = new TreeMap<>();
    }


    public void add(RankItem item) {
        Map.Entry<Long, Integer> entry =
                this.lowest2Index.lowerEntry(item.getValue());
        // 下标
        int index = entry == null ? 0 : entry.getValue();

    }


    class OnceList {

        /** 实际排行榜 **/
        private final SkipList<RankItem> list = new SkipList<>();
        /** 最低排行榜阈值 **/
        private long lowestValue;


        void add(RankItem item) {

        }

    }


}
