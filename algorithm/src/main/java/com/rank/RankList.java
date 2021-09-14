package com.rank;

import java.util.List;

/**
 * @author li-yuanwen
 * 排行榜
 */
public interface RankList {


    /**
     * 查询玩家排名
     * @param playerId 玩家id
     * @return 排名
     */
    int getRankByPlayerId(long playerId);

    /**
     * 更新排行榜
     * @param rankItem 更新元素
     */
    void update(RankItem rankItem);


    /**
     * 获取指定排名范围的排行榜
     * @param from 上限
     * @param to 下限
     * @return 排行榜
     */
    List<RankItem> getSubList(int from, int to);

}
